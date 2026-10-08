---
type: project-note
project: Dengue Early-Warning
status: stub
tags: [project, ml]
---
# Phase 08 — Automation and MLOps
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 21–22, target finish 2027-03-14
> **Study notes:** [[08.00 Automation and MLOps]] · **Revision:** [[08.99 Summary - Automation and MLOps]]

> [!abstract] Phase goal
> The system updates itself every week, runs online, covers all 25 districts, and tells you when the model starts to degrade.

## Steps
- [ ] One pipeline script `src/pipeline.py`: collect weather → collect cases → build features → predict → write alerts → log metrics → [[08.01 Pipeline Scripts and Logging]]
- [ ] Schedule it weekly → [[08.02 Scheduling with cron and GitHub Actions]]
- [ ] Monitor the model: weekly error of past forecasts, input drift, a retraining flag → [[08.05 Model Monitoring - Data Drift and Concept Drift]]
- [ ] Version each model and store the version with every prediction; write the retraining plan → [[08.06 Model Versioning and Retraining]]
- [ ] Package the app and MySQL → [[08.03 Docker and Docker Compose]]
- [ ] Deploy and confirm `/api/health` responds → [[08.04 Cloud Deployment Basics]]
- [ ] Scale to all 25 districts and check that results hold up

> [!warning] This phase holds more work than two weeks
> If time runs short, do the steps in the order above and stop where you must. Running the dashboard locally for demos is the roadmap's own fallback.

## Done when
- [ ] The pipeline runs end to end with one command
- [ ] A scheduled run has completed successfully at least twice
- [ ] The app and database run in Docker and are deployed online
- [ ] All 25 districts are included
- [ ] The monitoring table records weekly error and a retraining flag

---
## Work log
Step sections are added here as each step is finished.
