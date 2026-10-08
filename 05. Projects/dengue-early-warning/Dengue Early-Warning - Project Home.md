---
type: project
stage: planning
repo:
tags: [project, ml, web]
---
# Dengue Early-Warning
> [!abstract] Goal
> Forecast weekly dengue cases for each Sri Lankan district 4 to 8 weeks ahead from rainfall, temperature, humidity and recent case counts, and raise a **Normal / Watch / Warning** alert when a district is heading for an unusually high count. Results are shown on a district map dashboard.

## Where things live
- **Code:** `Code Vault/Projects/Machine Learning-Projects/dengue-early-warning` (local Git repository, not on GitHub yet)
- **Roadmap:** `Code Vault/Projects/Machine Learning-Projects/Dengue Outbreak Early-Warning System — Project Roadmap.md` (outside this vault, so it cannot be linked)
- **Notes:** this folder. One note per phase, plus `Lessons/` for concepts learned along the way.

## Plan
- [ ] **Phase 0** — Setup and domain knowledge → [[Phase 00 - Setup and Domain Knowledge]]
- [ ] **Phase 1** — Data collection
- [ ] **Phase 2** — Data cleaning and exploratory analysis
- [ ] **Phase 3** — Feature engineering
- [ ] **Phase 4** — Modelling
- [ ] **Phase 5** — Evaluation and alert thresholds
- [ ] **Phase 6** — Database and Flask API
- [ ] **Phase 7** — Dashboard
- [ ] **Phase 8** — Automation, deployment and MLOps monitoring
- [ ] **Phase 9** — Documentation, report and portfolio

## Tech stack
- **Data and modelling:** Python, [[Pandas]], NumPy, [[Matplotlib]], seaborn, statsmodels, scikit-learn, SHAP
- **Backend:** Flask, SQLAlchemy, MySQL
- **Dashboard:** HTML, CSS, JavaScript, Leaflet.js, Chart.js
- **Tooling:** Git and GitHub, pytest, Docker, GitHub Actions

## Related notes
- [[ML Specialization - Course Home]] · [[Machine Learning in Production]]
- [[Machine Learning Pipeline]] · [[Exploratory Data Analysis]] · [[Data Preprocessing]] · [[Model Evaluation]] · [[Linear Regression]] · [[Random Forest]] · [[LSTM]]

## Log
- 2026-10-08: roadmap reviewed and data sources checked; project folder, local Git repository and these notes created. Planned start 2026-10-12.
