---
type: project-note
project: PlantFolio
status: stub
tags: [project, ml]
---
# PlantFolio Week 11 — ML Service Skeleton
> [!info] [[PlantFolio - Project Home]] · Stage M · 14–20 Dec 2026
> **Study notes:** [[05.00 Machine Learning Service]] · **Revision:** [[05.99 Summary - Machine Learning Service]]

> [!abstract] Week goal
> PHP and Flask can talk to each other, the dataset is split, and the recognition approach is decided. No real model is needed yet.

## Tasks
- [ ] **ML-01** Flask skeleton with `/health` → [[05.02 Serving a Keras Model with Flask]]
- [ ] **ML-02** PHP ↔ Flask hello-world call: cURL multipart, 10 s timeout, offline handling → [[05.01 Calling the ML Service from PHP with cURL]]
- [ ] **ML-03** PlantVillage split 70 / 15 / 15 → [[05.05 Image Datasets - Splits, Augmentation and Domain Shift]]
- [ ] **ML-07** Decide: own MobileNetV2 model or the Pl@ntNet API for plant recognition

## Learn this week
Flask routing and JSON, PHP cURL. The Flask basics are already explained in [[06.04 Flask Routing and JSON APIs]].

## Checks
- [ ] With Flask running, a PHP page shows the reply from `/health`
- [ ] With Flask stopped, the same page shows the manual fallback within 10 seconds and does not break
- [ ] The three dataset parts share no images
- [ ] The decision for ML-07 is written down with its reason

---
## Work log
Step sections are added here as each step is finished.
