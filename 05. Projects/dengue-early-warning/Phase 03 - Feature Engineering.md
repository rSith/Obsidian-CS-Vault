---
type: project-note
project: Dengue Early-Warning
status: stub
tags: [project, ml]
---
# Phase 03 — Feature Engineering
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 9–10, target finish 2026-12-20
> **Study notes:** [[03.00 Feature Engineering]] · **Revision:** [[03.99 Summary - Feature Engineering]]

> [!abstract] Phase goal
> A model-ready table where every row holds only information that would have been available at prediction time, plus the target (cases 4 weeks later).

## Steps
- [ ] Fix the convention: forecast week, horizon, target week → [[03.01 Forecast Origin, Horizon and Target]]
- [ ] Lagged weather features at the lags found in Phase 2 → [[03.02 Lag and Rolling-Window Features]]
- [ ] Rolling weather features: 4-week rainfall total, 8-week mean temperature
- [ ] Lagged case features and the change between them, respecting the reporting delay → [[01.06 Reporting Delay and Data Availability]]
- [ ] Seasonality features: sine and cosine of the time of year → [[03.04 Cyclical and One-Hot Encoding]]
- [ ] District features: one-hot district, cases per 100,000 people
- [ ] Target: cases shifted 4 weeks into the future
- [ ] Put it all in one function in `src/features.py` → [[03.05 Python Functions and Modules]]
- [ ] Leakage check: by hand for a few rows, and as a test → [[03.03 Data Leakage]] · [[03.06 Unit Testing with pytest]]

## Done when
- [ ] `src/features.py` builds the full feature table from `weekly.csv` in one call
- [ ] Rows with missing lags (the first weeks of each district) are handled the same way everywhere
- [ ] The leakage check is done and noted
- [ ] At least 2 tests pass

---
## Work log
Step sections are added here as each step is finished.
