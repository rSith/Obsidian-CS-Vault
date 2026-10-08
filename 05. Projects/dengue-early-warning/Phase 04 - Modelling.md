---
type: project-note
project: Dengue Early-Warning
status: stub
tags: [project, ml]
---
# Phase 04 — Modelling
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 11–14, target finish 2027-01-17
> **Study notes:** [[04.00 Modelling]] · **Revision:** [[04.99 Summary - Modelling]]

> [!abstract] Phase goal
> A model that clearly beats simple baselines at forecasting cases 4 weeks ahead, chosen by a fair comparison.

## Steps
- [ ] Split by time: train, validation, and a final test period touched once → [[04.01 Time-Based Splits and Time-Series Cross-Validation]]
- [ ] Baselines first: persistence and seasonal → [[04.02 Forecasting Baselines - Persistence and Seasonal]]
- [ ] Linear models: [[04. Concepts/Machine Learning/Linear Regression|Linear Regression]] with scaled features, then Ridge → [[04.03 Regularisation - Ridge and Lasso]] · [[04.04 scikit-learn Pipelines and ColumnTransformer]]
- [ ] Count model → [[04.05 Poisson Regression for Counts]]
- [ ] Tree ensembles: [[Random Forest]], then → [[04.06 Gradient Boosting]]
- [ ] Optional: an [[04. Concepts/AI & Deep Learning/LSTM|LSTM]] on sequences of the past 12 weeks
- [ ] Record every experiment in one results table → [[04.08 Experiment Tracking]]
- [ ] Compare the best model with and without weather features
- [ ] Explain the best model and check that its top features make sense → [[04.07 SHAP and Feature Importance]]

## Done when
- [ ] Both baselines are evaluated
- [ ] At least 3 model families are compared on the same validation split
- [ ] The best model beats the seasonal baseline by a clear margin (state the percentage)
- [ ] A feature-importance plot is saved and interpreted
- [ ] The best model is saved to `models/` with joblib

---
## Work log
Step sections are added here as each step is finished.
