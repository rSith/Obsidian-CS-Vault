---
type: project-note
project: Dengue Early-Warning
status: stub
tags: [project, ml]
---
# Phase 05 — Evaluation and Alerts
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 15–16, target finish 2027-01-31
> **Study notes:** [[05.00 Evaluation and Alerts]] · **Revision:** [[05.99 Summary - Evaluation and Alerts]]

> [!abstract] Phase goal
> Turn forecasts into Normal / Watch / Warning alerts and show, on unseen test data, how many real outbreaks the system would have caught early.

## Steps
- [ ] Build the endemic channel for each district and week of year → [[05.02 Endemic Channel and Alert Thresholds]]
- [ ] Define the alert levels: Watch above mean + 1 SD, Warning above mean + 2 SD
- [ ] Define a true outbreak the same way, using actual cases
- [ ] Choose thresholds on **validation** data, showing the precision–recall trade-off → [[05.03 Alert Metrics - Precision, Recall and Lead Time]]
- [ ] Add prediction intervals → [[05.04 Prediction Intervals and Quantile Regression]]
- [ ] Run the final test **once** on the held-out period: MAE and RMSE per district and overall, precision and recall for Warnings, lead time → [[05.01 Forecast Error Metrics - MAE and RMSE]]
- [ ] Plot actual against predicted for each district, with alert periods shaded → [[05.05 Charts That Communicate Results]]
- [ ] Write an honest error analysis: which outbreaks were missed, and why

> [!note] Order of two steps
> The roadmap lists "run the final test" before "tune for recall". Tuning after the test would use the test data twice, so here the thresholds are chosen on validation data first.

## Done when
- [ ] The endemic channel is computed for every district and week
- [ ] The final test is run once and the results are recorded
- [ ] Precision, recall and average lead time are reported
- [ ] Actual-versus-predicted figures with shaded alerts are saved
- [ ] The error analysis is written

---
## Work log
Step sections are added here as each step is finished.
