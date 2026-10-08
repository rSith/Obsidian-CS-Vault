---
type: project-note
project: PlantFolio
status: stub
tags: [project, web, ml]
---
# PlantFolio Week 07 — AI Pages and UI Freeze
> [!info] [[PlantFolio - Project Home]] · Stage F · 16–22 Nov 2026 · ends with the **UI freeze**
> **Study notes:** [[02.00 Interactivity and Responsive Design]] · [[05.07 Confidence Thresholds and Honest AI Results]]

> [!abstract] Week goal
> The two AI pages with mock results, the read-only views for visitors, and an accessibility pass. At the end, every page and state matches its wireframe.

## Tasks
| Page / feature | Task | Notes | Module |
|---|---|---|---|
| P07 Plant Identifier | ML-12 (UI) | Tool switcher, photo preview, tips, top match with confidence label, care chips, alternatives, honesty note | MOD-11 |
| P08 Health Check | ML-14 (UI) | Plant link, diagnosis, plain explanation, 4-step action plan, supported-crops statement, save buttons | MOD-12 |
| S2 Analysing · S3 Not sure | ML-17 (UI) | Disabled buttons while "analysing"; below 60 % never shown as an answer | MOD-11/12 |
| AI suggestion on P06 | ML-13 (UI) | Top 3 with Use buttons, Filled-by-AI tag, manual fallback | MOD-11 |
| Public read-only views | CORE-21 (UI) | P05 for visitors: owner card and like button instead of edit controls | MOD-05 |
| Accessibility and polish | TST-07 (early) | Alt text, labels, focus states, contrast check, hover and pressed states | all |

- [ ] P07 Plant Identifier (`identify.php`)
- [ ] P08 Health Check (`health-check.php`)
- [ ] States S2 Analysing and S3 Not sure
- [ ] AI suggestion block on P06
- [ ] Public read-only view of P05
- [ ] Accessibility and polish pass

## Learn this week
- How an AI result should be worded and when it must say "Not sure" → [[05.07 Confidence Thresholds and Honest AI Results]]
- One page shown differently to its owner and to a visitor → [[01.04 Rendering States - Empty, Owner and Visitor Views]]
- Accessibility → [[09.03 Applying Accessible Design]] · [[09.04 Introduction to ARIA and the Five Rules]]

## UI freeze (end of the week)
- [ ] All 15 pages and states S1–S6 match their wireframes with mock data
- [ ] Every page passes the 375 / 768 / 1024 px check with no console errors
- [ ] `develop` merged into `main` and tagged **`v0.5-ui`**

---
## Work log
Step sections are added here as each step is finished.
