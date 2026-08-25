# DocFinder Upgrade Roadmap — Step-by-Step Implementation Guide

This guide breaks your improvement goals into five buildable phases. Each phase has concrete steps, the tools/libraries to use, and what "done" looks like before you move to the next one. Do them in order — each phase depends on the previous one working.

---

## Phase 1 — Real Dataset + ML Model (Python)

**Goal:** A trained model that takes a list of symptoms and returns the most likely disease(s) with confidence scores, served over HTTP.

### Step 1.1 — Set up the Python environment
```bash
mkdir docfinder-ml && cd docfinder-ml
python -m venv venv
source venv/bin/activate      # venv\Scripts\activate on Windows
pip install pandas numpy scikit-learn tensorflow fastapi uvicorn joblib matplotlib seaborn
```

### Step 1.2 — Get the dataset
Use **Disease-Symptom Prediction** (itachi9604) on Kaggle: 132 symptom columns → 42 disease labels, already split into `Training.csv` and `Testing.csv`.
- `kaggle.com/datasets/itachi9604/disease-symptom-description-dataset`
- Download via the Kaggle website, or `kaggle datasets download` if you set up the Kaggle CLI with an API token.
- Place `Training.csv` and `Testing.csv` in a `data/` folder.

Once this pipeline works end-to-end, swap in the larger **dhivyeshrk** dataset (773 diseases, 377 symptoms, 246,000 samples) for a more serious model — same code, bigger data.

### Step 1.3 — Explore and clean the data
```python
import pandas as pd

df = pd.read_csv("data/Training.csv")
print(df.shape)
print(df['prognosis'].value_counts())   # check class balance
df = df.loc[:, ~df.columns.str.contains('Unnamed')]  # drop stray index columns if present
```
Check: no missing values, no duplicate rows, symptom columns are all 0/1.

### Step 1.4 — Encode the target
```python
from sklearn.preprocessing import LabelEncoder

X = df.drop('prognosis', axis=1)
y = df['prognosis']

le = LabelEncoder()
y_encoded = le.fit_transform(y)   # disease names -> integers
```
Save `le` with `joblib` later — you'll need it to convert predictions back to disease names.

### Step 1.5 — Train a baseline model first
Always benchmark with something simple before jumping to deep learning — if a Random Forest gets 95%+ on this dataset (it likely will, since it's near-deterministic), that tells you the ceiling and gives you something to compare the ANN against.
```python
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score, classification_report

X_train, X_test, y_train, y_test = train_test_split(X, y_encoded, test_size=0.2, random_state=42, stratify=y_encoded)

rf = RandomForestClassifier(n_estimators=200, random_state=42)
rf.fit(X_train, y_train)
preds = rf.predict(X_test)
print("RF Accuracy:", accuracy_score(y_test, preds))
```

### Step 1.6 — Train the ANN (this is your "AI/DL" component)
```python
import tensorflow as tf
from tensorflow.keras import layers, models
from tensorflow.keras.utils import to_categorical

num_classes = len(le.classes_)
y_train_cat = to_categorical(y_train, num_classes)
y_test_cat = to_categorical(y_test, num_classes)

model = models.Sequential([
    layers.Input(shape=(X.shape[1],)),
    layers.Dense(128, activation='relu'),
    layers.Dropout(0.3),
    layers.Dense(64, activation='relu'),
    layers.Dropout(0.2),
    layers.Dense(num_classes, activation='softmax')
])

model.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])

history = model.fit(X_train, y_train_cat, validation_split=0.1, epochs=30, batch_size=16)

test_loss, test_acc = model.evaluate(X_test, y_test_cat)
print("ANN Test Accuracy:", test_acc)
```
This is a genuine feedforward neural network you can describe and defend in your report: input layer sized to your symptom count, two hidden dense layers with dropout for regularization, softmax output over disease classes.

### Step 1.7 — Save everything
```python
import joblib

model.save("disease_ann_model.keras")
joblib.dump(le, "label_encoder.pkl")
joblib.dump(list(X.columns), "symptom_columns.pkl")   # so the API knows the exact input order
```

### Step 1.8 — Wrap it in a FastAPI service
```python
# app.py
from fastapi import FastAPI
from pydantic import BaseModel
import tensorflow as tf
import joblib
import numpy as np

app = FastAPI()

model = tf.keras.models.load_model("disease_ann_model.keras")
le = joblib.load("label_encoder.pkl")
symptom_columns = joblib.load("symptom_columns.pkl")

class SymptomRequest(BaseModel):
    symptoms: list[str]

@app.post("/predict")
def predict(request: SymptomRequest):
    input_vector = np.zeros(len(symptom_columns))
    for s in request.symptoms:
        if s in symptom_columns:
            input_vector[symptom_columns.index(s)] = 1

    probs = model.predict(np.array([input_vector]))[0]
    top3_idx = probs.argsort()[-3:][::-1]

    return {
        "predictions": [
            {"disease": le.classes_[i], "confidence": float(probs[i])}
            for i in top3_idx
        ]
    }
```

### Step 1.9 — Run and test it
```bash
uvicorn app:app --reload --port 8000
```
```bash
curl -X POST http://localhost:8000/predict \
  -H "Content-Type: application/json" \
  -d '{"symptoms": ["fever", "cough", "fatigue"]}'
```

**Phase 1 done when:** the endpoint reliably returns sensible top-3 diseases with confidence scores for realistic symptom combinations.

---

## Phase 2 — Connect the ML Service to Your Java App

**Goal:** `DiseaseIdentificationApp` calls the Python service instead of (or alongside) the old rule-based `SymptomChecker`.

### Step 2.1 — Add a JSON library
Add to `pom.xml` (Jackson, for parsing the FastAPI response):
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.0</version>
</dependency>
```

### Step 2.2 — Create an ML client class
```java
package com.docfinder.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

public class MLPredictionService {

    private static final String ML_API_URL = "http://localhost:8000/predict";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Map<String, Object>> predict(List<String> symptoms) throws Exception {
        String body = mapper.writeValueAsString(Map.of("symptoms", symptoms));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ML_API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Map<String, Object> result = mapper.readValue(response.body(), Map.class);
        return (List<Map<String, Object>>) result.get("predictions");
    }
}
```

### Step 2.3 — Wire it into the UI
In `showSymptomCheckerScreen`, replace the call to `symptomChecker.analyzeSymptoms(selected)` with a call to `mlPredictionService.predict(selected)`, and update the results dialog to loop over the top-3 list instead of a single `Disease` object.

### Step 2.4 — Keep a fallback
Wrap the ML call in a try/catch — if the Python service is unreachable, fall back to the existing `SymptomChecker` logic so the app doesn't break during grading/demo if the ML service isn't running.

**Phase 2 done when:** selecting symptoms in the JavaFX app returns real ML predictions, with the old logic as a safety net.

---

## Phase 3 — Move to the Web

**Goal:** Replace the JavaFX desktop UI with a hosted website, and turn your Java code into a proper REST API.

### Step 3.1 — Set up a Spring Boot project
Use [start.spring.io] with dependencies: Spring Web, Spring Data JPA, MySQL Driver, Spring Security, Validation.

### Step 3.2 — Convert your model classes to JPA entities
Your `Person → User/Doctor` and `Disease → CommonDisease/RareDisease` hierarchies map directly to JPA inheritance:
```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int age;
    private String gender;
    private String contactNumber;
    // getters/setters
}

@Entity
public class Doctor extends Person {
    private String specialization;
    private String clinicAddress;
    private String clinicHours;
    private double latitude;
    private double longitude;
}
```

### Step 3.3 — Replace DAOs with Spring Data repositories
```java
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecializationContainingIgnoreCase(String specialization);
}
```
This alone removes the SQL-injection risk from your current DAOs, since JPA parameterizes queries automatically.

### Step 3.4 — Build REST controllers
```java
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired private DoctorRepository doctorRepository;

    @GetMapping
    public List<Doctor> getAll() { return doctorRepository.findAll(); }

    @PostMapping
    public Doctor register(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
```
Similarly build `/api/auth/login`, `/api/auth/register`, `/api/symptoms/predict` (which internally calls your Python FastAPI service via `RestTemplate` or `WebClient`).

### Step 3.5 — Build the frontend
Start simple: plain HTML/CSS/JS or a lightweight React app with pages matching your existing 5 screens (Login, Register, Dashboard, Symptom Checker, Doctor Directory). Call your Spring Boot API with `fetch()`/`axios`.

### Step 3.6 — Secure your configuration
Move `db.properties` values (URL, username, password) into environment variables, and add `db.properties` (or `application.properties` with real credentials) to `.gitignore`. Never commit real credentials again.

### Step 3.7 — Deploy
- Containerize each piece: Java backend, Python ML service, and (if separate) the frontend, each with its own `Dockerfile`.
- Use `docker-compose` locally to run backend + ML service + MySQL together.
- Deploy to a free/cheap host for a student project: Render, Railway, or Fly.io for the backend and ML service; a managed MySQL instance (PlanetScale, Railway MySQL, or AWS RDS free tier) for the database.

**Phase 3 done when:** you can open a URL in a browser (not launch a JAR) and use the full symptom-checker flow end to end.

---

## Phase 4 — Doctor Features

**Goal:** Doctors can register, patients can find nearby doctors, and doctors can publish real availability.

### Step 4.1 — New database tables
```sql
CREATE TABLE doctor_registration (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    doctor_id BIGINT,
    status ENUM('PENDING','APPROVED','REJECTED') DEFAULT 'PENDING',
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);

CREATE TABLE doctor_schedule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    doctor_id BIGINT,
    day_of_week ENUM('MON','TUE','WED','THU','FRI','SAT','SUN'),
    start_time TIME,
    end_time TIME,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);

CREATE TABLE appointment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    patient_id BIGINT,
    doctor_id BIGINT,
    appointment_time DATETIME,
    status ENUM('BOOKED','COMPLETED','CANCELLED') DEFAULT 'BOOKED',
    FOREIGN KEY (patient_id) REFERENCES user(id),
    FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);
```
Add `latitude` and `longitude` columns directly to the `doctor` table (already suggested in Step 3.2).

### Step 4.2 — Doctor registration + admin approval
- Doctor fills a registration form (reuse your existing registration screen pattern, add specialization, clinic address, license number).
- New doctor accounts default to `PENDING` in `doctor_registration`.
- Add a simple admin role that can approve/reject — a protected `/api/admin/doctors/pending` endpoint plus one screen listing pending doctors with Approve/Reject buttons.

### Step 4.3 — "Nearby doctors" search
Simplest correct approach without needing PostGIS: use the **Haversine formula** directly in SQL.
```sql
SELECT *, (
    6371 * acos(
        cos(radians(:userLat)) * cos(radians(latitude)) *
        cos(radians(longitude) - radians(:userLng)) +
        sin(radians(:userLat)) * sin(radians(latitude))
    )
) AS distance_km
FROM doctor
HAVING distance_km < :radiusKm
ORDER BY distance_km ASC;
```
Get the user's coordinates from the browser's Geolocation API (`navigator.geolocation.getCurrentPosition`) and pass them to your backend.

### Step 4.4 — Doctor schedule management
- A "My Schedule" screen for logged-in doctors: add/edit rows in `doctor_schedule` (day, start time, end time).
- Patients viewing a doctor's profile see their weekly availability rendered from these rows.

### Step 4.5 — Appointment booking
- Patient picks a doctor + an available time slot → creates an `appointment` row.
- Before confirming, check for time conflicts (query existing `BOOKED` appointments for that doctor at that time).
- Simple confirmation screen; email/SMS notifications are a nice-to-have, not required for a first version.

**Phase 4 done when:** a doctor can register, get approved, set weekly hours, appear in "nearby doctors" search, and a patient can book a slot with them.

---

## Phase 5 — Production Hardening

**Goal:** The parts that separate a course project from something you'd actually trust with real patient data.

### Step 5.1 — Eliminate SQL injection
If you kept any raw JDBC (rather than full JPA), convert every query to `PreparedStatement`:
```java
String sql = "SELECT * FROM User WHERE username = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, username);
```

### Step 5.2 — Real automated tests
Replace `TestUser.java`, `TestAllLogic.java`, etc. with JUnit 5 tests:
```java
@SpringBootTest
class SymptomCheckerTest {
    @Test
    void shouldReturnDiseaseWhenSymptomsMatch() {
        // arrange, act, assert
    }

    @Test
    void shouldThrowWhenSymptomsEmpty() {
        assertThrows(InvalidSymptomException.class, () -> checker.analyzeSymptoms(List.of()));
    }
}
```
Add tests for: registration validation, login with wrong password, doctor search filtering, appointment conflict detection.

### Step 5.3 — Authentication with JWT
Replace "logged in = showed the dashboard screen" with real stateless auth: issue a JWT on login, require it on protected endpoints (`/api/doctors/register`, `/api/appointments`), validate it in a Spring Security filter.

### Step 5.4 — CI/CD
Add a GitHub Actions workflow that runs `mvn test` and the Python test suite on every push, so broken code can't merge silently.

### Step 5.5 — Monitoring and model upkeep
- Log every prediction (symptoms in, disease out, confidence) to a table.
- Periodically compare predictions against what doctors actually diagnosed (if you capture that), to see if the model needs retraining.
- This is the piece that turns "I trained a model once" into "I run a maintained ML system" — good talking point for your report and for job interviews.

### Step 5.6 — Basic security hygiene
- Serve everything over HTTPS (free via Let's Encrypt or your hosting provider).
- Rate-limit the login and registration endpoints.
- Never log raw passwords, even hashed ones, in application logs.

**Phase 5 done when:** the SQL injection issue is closed, tests actually run and pass in CI, and credentials/secrets are nowhere in your git history.

---

## Suggested order for your remaining course timeline

If you're time-constrained for your CMIS 2113 deadlines, prioritize in this order: **Phase 1 → Phase 2 → Phase 5 (Steps 5.1–5.2 only) → Phase 3 → Phase 4.** That gets you a working ML feature and a secure, tested codebase — the two things a grader will check first — before you spend time on hosting and the doctor marketplace features, which are impressive but secondary to the core "OOP + working system" requirement.
