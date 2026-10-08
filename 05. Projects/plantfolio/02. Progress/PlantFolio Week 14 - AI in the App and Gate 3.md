---
type: project-note
project: PlantFolio
status: stub
tags: [project, ml]
---
# PlantFolio Week 14 — AI in the App and Gate 3
> [!info] [[PlantFolio - Project Home]] · Stage M · 4–10 Jan 2027 · ends with **Gate 3**
> **Study notes:** [[05.00 Machine Learning Service]] · **Revision:** [[05.99 Summary - Machine Learning Service]]

> [!abstract] Week goal
> The AI features are part of the normal flows, behave honestly when unsure, and the site still works when the ML service is switched off.

## Tasks
- [ ] **ML-13** AI suggestions in Add Plant
- [ ] **ML-16** Save a diagnosis to the plant story, and add a 7-day re-check reminder → [[04.03 Dates, Reminders and Due Logic]]
- [ ] **ML-17** States wired: Analysing, Not sure → [[05.07 Confidence Thresholds and Honest AI Results]]
- [ ] **ML-18** ML test pass, including with Flask stopped
- [ ] **ML-19** Reviews of MOD-11 and MOD-12

## Gate 3
- [ ] Disease model ≥ 90 % test accuracy
- [ ] Top-3 accuracy measured for plant recognition
- [ ] Offline fallback verified
- [ ] Tag `v1.0-ml`

## Tests
- [ ] Test Plan cases TC-044 – TC-051
- [ ] Nothing is saved until the user presses Save
- [ ] A result below 60 % is shown as "Not sure", never as an answer
- [ ] A photo of an unsupported plant gets the scope message, not a guess

---
## Work log
Step sections are added here as each step is finished.
