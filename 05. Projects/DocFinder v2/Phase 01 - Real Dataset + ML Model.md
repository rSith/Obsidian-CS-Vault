---
type: project-note
project: DocFinder v2
status: draft
tags: [project, ml]
---
# Phase 01 — Real Dataset + ML Model
> [!info] [[DocFinder v2 - Project Home]] · Plan: [[DocFinder Upgrade Roadmap]]

## Machine Learning Lifecycle
### Step 01 : Problem Definition
#### Business Needs and technical solutions 
- Patients often have symptoms but do not know which type of doctor they should visit. This can cause delays in getting proper treatment and may lead to unsafe self-medication based on online advice.
- People in rural and underserved areas have more difficulty finding nearby doctors, their specialties, and their working hours.
- From a technical point of view, the current DocFinder application only works as a local JavaFX desktop application. It uses simple, hardcoded rules to match symptoms with diseases, which is not accurate enough for real clinical use and cannot be easily accessed by the general public.

#### Project Objectives
- Replace the current rule-based symptom checker with a trained machine learning model that can predict diseases more accurately using real medical data.
- Change the current academic desktop application into an industry-ready web application that can be hosted online.
- Add complete healthcare directory features, such as doctor registration, schedule management, and location-based searches for nearby doctors.

#### Project Scope
- **Data & AI**: Obtain a real disease-symptom dataset, such as *SymbiPredict* or a suitable *Kaggle medical dataset*, and train a [[Machine Learning|machine learning]] model such as an [[Neural Network|Artificial Neural Network]] or [[Random Forest]].
- **Microservices**: Deploy the trained AI model as a separate Python FastAPI microservice. The main application can send prediction requests to this service using HTTP.
- **Web Architecture**: Convert the existing Java backend into a Spring Boot REST API and replace the JavaFX interface with a modern web frontend such as React or Vue.js

#### Success Criteria
- The AI model should accept the patient's symptoms and return the most likely diseases with reliable confidence scores.
- Doctors should be able to register on the platform, receive approval from administrators, and manage their available schedules.
- Patients should be able to find nearby doctors using location-based filtering.
- The complete web application and ML service should be containerized using Docker and hosted publicly on a cloud platform.
----
### Step 02: Data Collection
#### Step 1.1 — Set up the Python environment
>[!important] Goal
>Create a clean, isolated Python environment for developing DocFinder's Machine Learning System.
>- **Dependency Management**: Use `venv` to safely manage libraries
>- **ML Development**: The environment supports data cleaning, feature engineering, model training, model comparison, and prediction.
