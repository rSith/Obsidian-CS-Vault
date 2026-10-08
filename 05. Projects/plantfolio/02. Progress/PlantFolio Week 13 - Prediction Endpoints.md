---
type: project-note
project: PlantFolio
status: stub
tags: [project, ml]
---
# PlantFolio Week 13 — Prediction Endpoints
> [!info] [[PlantFolio - Project Home]] · Stage M · 28 Dec 2026 – 3 Jan 2027
> **Study notes:** [[05.00 Machine Learning Service]] · **Revision:** [[05.99 Summary - Machine Learning Service]]

> [!abstract] Week goal
> The Flask service answers real prediction requests, and the Plant Identifier and Health Check pages show real results instead of mock ones.

## Tasks
- [ ] **ML-09** `POST /predict/species` → [[05.02 Serving a Keras Model with Flask]]
- [ ] **ML-10** `POST /predict/disease`
- [ ] **ML-11** Log every call in `ml_predictions` through `includes/ml-client.php` → [[05.01 Calling the ML Service from PHP with cURL]]
- [ ] **ML-12** Plant Identifier (P07) wired
- [ ] **ML-14** Health Check (P08) wired

## Checks
- [ ] Each endpoint returns the top 3 with confidence and a `model_version`, as the API contract says
- [ ] Errors return JSON with the right status: 400 bad image, 415 wrong type, 503 model not loaded
- [ ] The models load once when the service starts, not on every request
- [ ] The confidence word is decided in PHP with the fixed limits → [[05.07 Confidence Thresholds and Honest AI Results]]
- [ ] The browser never calls Flask directly

---
## Work log
Step sections are added here as each step is finished.
