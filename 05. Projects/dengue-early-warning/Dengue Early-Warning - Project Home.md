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
- **Notes:** this folder. Start at [[00. Dengue Early-Warning Overview]], the map of every note.
  - **Phase notes** (below) record progress: the plan, the checklist and a work log of what was done.
  - **Lessons** in `01. Lessons/` explain what each phase needs you to know, one folder per phase.

## Plan
Each line links the progress note, then the lesson to study for it.
- [ ] **Phase 0** — Setup and domain knowledge → [[Phase 00 - Setup and Domain Knowledge]] · study [[00.00 Setup and Domain Knowledge]]
- [ ] **Phase 1** — Data collection → [[Phase 01 - Data Collection]] · study [[01.00 Data Collection]]
- [ ] **Phase 2** — Data cleaning and exploratory analysis → [[Phase 02 - Cleaning and EDA]] · study [[02.00 Cleaning and EDA]]
- [ ] **Phase 3** — Feature engineering → [[Phase 03 - Feature Engineering]] · study [[03.00 Feature Engineering]]
- [ ] **Phase 4** — Modelling → [[Phase 04 - Modelling]] · study [[04.00 Modelling]]
- [ ] **Phase 5** — Evaluation and alert thresholds → [[Phase 05 - Evaluation and Alerts]] · study [[05.00 Evaluation and Alerts]]
- [ ] **Phase 6** — Database and Flask API → [[Phase 06 - Database and Flask API]] · study [[06.00 Database and Flask API]]
- [ ] **Phase 7** — Dashboard → [[Phase 07 - Dashboard]] · study [[07.00 Dashboard]]
- [ ] **Phase 8** — Automation, deployment and MLOps monitoring → [[Phase 08 - Automation and MLOps]] · study [[08.00 Automation and MLOps]]
- [ ] **Phase 9** — Documentation, report and portfolio → [[Phase 09 - Documentation and Portfolio]] · study [[09.00 Documentation and Portfolio]]

## Tech stack
- **Data and modelling:** Python, [[Pandas]], NumPy, [[Matplotlib]], seaborn, statsmodels, scikit-learn, SHAP
- **Backend:** Flask, SQLAlchemy, MySQL
- **Dashboard:** HTML, CSS, JavaScript, Leaflet.js, Chart.js
- **Tooling:** Git and GitHub, pytest, Docker, GitHub Actions

## Related notes
General concepts are explained once, in `04. Concepts`. The project notes link to them and do not repeat them.
- **Courses:** [[ML Specialization - Course Home]] · [[03. Self-Study/ML in Production/Machine Learning in Production|Machine Learning in Production]]
- **Workflow:** [[04. Concepts/Machine Learning|Machine Learning]] · [[04. Concepts/Machine Learning/Machine Learning Pipeline|Machine Learning Pipeline]] · [[04. Concepts/Machine Learning/Exploratory Data Analysis|Exploratory Data Analysis]] · [[Data Preprocessing]] · [[Model Evaluation]]
- **Models:** [[04. Concepts/Machine Learning/Supervised Learning|Supervised Learning]] · [[04. Concepts/Machine Learning/Regression|Regression]] · [[04. Concepts/Machine Learning/Linear Regression|Linear Regression]] · [[Multiple Linear Regression]] · [[Decision Trees]] · [[Random Forest]] · [[04. Concepts/Machine Learning/Classification|Classification]] · [[04. Concepts/AI & Deep Learning/LSTM|LSTM]] · [[04. Concepts/AI & Deep Learning/RNN|RNN]]
- **Tools:** [[Pandas]] · [[NumPy]] · [[Matplotlib]] · [[Seaborn]]

## Log
- 2026-10-08: roadmap reviewed and data sources checked; project folder, local Git repository and these notes created. Planned start 2026-10-12.
- 2026-10-08: study notes written for all ten phases (`01. Lessons`), with an overview map and a progress note for each phase.
