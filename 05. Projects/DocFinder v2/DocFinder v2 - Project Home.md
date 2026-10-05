---
type: project
stage: planning
repo:
tags: [project, ml, web]
---
# DocFinder v2
> [!abstract] Goal
> Turn the DocFinder JavaFX desktop app into a public web app with a **trained ML symptom checker** and a full doctor directory.

## Why (from [[Phase 01 - Real Dataset + ML Model|Phase 01]])
- Patients often have symptoms but don't know which type of doctor to visit, which delays treatment and pushes people to self-medicate.
- People in rural areas struggle to find nearby doctors, their specialties and working hours.
- The current app is desktop-only and matches symptoms with hard-coded rules.

## Plan — [[DocFinder Upgrade Roadmap]]
- [ ] **Phase 1** — Real dataset + ML model, served by a FastAPI microservice → [[Phase 01 - Real Dataset + ML Model]]
- [ ] **Phase 2** — Connect the ML service to the Java app
- [ ] **Phase 3** — Move to the web (Spring Boot REST API + React/Vue frontend)
- [ ] **Phase 4** — Doctor features (registration, admin approval, schedules, nearby search, booking)
- [ ] **Phase 5** — Production hardening (tests, JWT, CI/CD, monitoring)

## Related notes
- [[Machine Learning in Production]] · [[Machine Learning Pipeline]] · [[Neural Network]] · [[Random Forest]]

## Log
-
