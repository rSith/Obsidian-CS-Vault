---
type: project-note
project: PlantFolio
status: draft
tags: [project, ml]
---
# PlantFolio Week 11 — ML Service Skeleton
> [!info] [[PlantFolio - Project Home]] · Stage M · 14–20 Dec 2026
> **Study notes:** [[05.00 Machine Learning Service]] · **Revision:** [[05.99 Summary - Machine Learning Service]]

> [!abstract] Week goal
> PHP and Flask can talk to each other, the dataset is split, and the recognition approach is decided. No real model is needed yet.

## Tasks
- [x] **ML-01** Flask skeleton (wired, 12 Nov 2026) with `/health` → [[05.02 Serving a Keras Model with Flask]]
- [x] **ML-02** PHP ↔ Flask (wired in the overnight build, tested with real Flask 12 Nov) hello-world call: cURL multipart, 10 s timeout, offline handling → [[05.01 Calling the ML Service from PHP with cURL]]
- [ ] **ML-03** PlantVillage split 70 / 15 / 15 → [[05.05 Image Datasets - Splits, Augmentation and Domain Shift]]
- [x] **ML-07** Decide: own MobileNetV2 model or the Pl@ntNet API for plant recognition → **Pl@ntNet** (12 Nov 2026)

## Learn this week
Flask routing and JSON, PHP cURL. The Flask basics are already explained in [[06.04 Flask Routing and JSON APIs]].

## Checks
- [x] With Flask running, a PHP page shows the reply from `/health`
- [x] With Flask stopped, the same page shows the manual fallback within 10 seconds and does not break
- [ ] The three dataset parts share no images
- [x] The decision for ML-07 is written down with its reason

---
## Work log
Step sections are added here as each step is finished.

### Step 11.1 — The Flask ML service (ML-01) and the species decision (ML-07)

> [!important] Goal
> **ML-01:** a Flask service that answers `GET /health`, `POST /predict/disease` and `POST /predict/species` in the shape `includes/ml-client.php` already expects. **ML-07:** decide how plants are recognised.

**What was done** — 12 Nov 2026, branch `feature/ml-service` (from `backend-overnight`), commit `e56a0c5`.
- `ml-service/app.py`: the three routes, JSON errors (400, 413, 415, 422, 502, 503), listens on `127.0.0.1` only.
- `ml-service/requirements.txt` pinned (Flask 3.1.3, TensorFlow 2.21.0, Keras 3.15.1, Pillow, NumPy, requests) and a `venv` on Python 3.13.
- `ml-service/.env.example`: the Pl@ntNet key goes in `ml-service/.env`, which Git ignores.
- **Decision (ML-07):** species come from the **Pl@ntNet API**, not a model we train. Flask forwards the photo and returns the same JSON as the disease route, so PHP did not change. Recorded as D26 in the repo's `DECISIONS.md`.
- `ml-service/README.md` now has the **model contract** the training notebook must follow.

**Why this way**
- **Flask calls Pl@ntNet, not PHP.** The website talks to one ML service with one answer shape; where an answer comes from is Flask's business. The API key stays in the Python service and never reaches the website code.
- **The model is loaded once, at start-up.** Loading a Keras model takes seconds, and a prediction takes milliseconds.
- **The service starts without a model.** `/predict/disease` answers `503` until `models/disease.keras` exists, and PHP shows its "couldn't reach" state. So the site never breaks because of ML.
- **Photos are checked again in Python** (5 MB, JPG/PNG, actually decodable), even though PHP already checked them: never trust the caller.
- **The scaling lives inside the model** (a `Rescaling` layer), so `app.py` just sends 0–255 pixels and can't get the preprocessing wrong.

```python
disease_model, disease_classes, disease_version = load_disease_model()   # once, at start-up

@app.post("/predict/disease")
def predict_disease():
    if disease_model is None:
        return error("The disease model is not loaded.", 503)
    ...
    probabilities = disease_model.predict(pixels[np.newaxis, ...], verbose=0)[0]
    return jsonify({"model_version": disease_version,
                    "predictions": top_predictions(zip(disease_classes, probabilities))})
```

**How it was tested** (with a throwaway, untrained 38-class model that was deleted afterwards)
- `/health` reported both parts. No field → 400, a real GIF → 415, PHP code named `.jpg` → 400, a 6 MB file → 413, GET → 405, an unknown path → 404, species without a key → 503.
- Through the site: Health Check posted a photo, Flask answered, the result was logged in `ml_predictions` and shown as "Not sure · 4%". Identify (no key yet) went to the "couldn't reach the Plant Identifier" page. The test rows and photos were removed.

**Problems and fixes**
- TensorFlow can't use the RTX 4060 on native Windows (GPU support ended after 2.10; it needs WSL2). Running a trained model on the CPU is fast enough, and training happens on Colab.

> [!tip] What to remember
> One ML service, one answer shape: `{"model_version", "predictions": [{"label", "confidence"}]}`. Flask hides *where* an answer comes from (our model or Pl@ntNet), and every failure becomes "couldn't reach" in PHP.

**Check yourself**
1. Why does Flask, and not PHP, call Pl@ntNet?
2. Why is the model loaded outside the route function?
3. What does the site do if `disease.keras` is missing?

> [!success]- Answers
> 1. The website then talks to one service with one answer shape, `ml-client.php` didn't change, and the API key stays in the git-ignored `ml-service/.env`, away from the website code.
> 2. Loading takes seconds and would slow every request; loading once at start-up makes each prediction fast. See [[05.02 Serving a Keras Model with Flask]].
> 3. Flask still starts, `/predict/disease` answers 503, `ml_predict()` returns `ok => false` and the page shows its manual fallback. The site never breaks because of ML.

