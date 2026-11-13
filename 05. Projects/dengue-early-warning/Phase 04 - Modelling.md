---
type: project-note
project: Dengue Early-Warning
status: done
tags: [project, ml]
---
# Phase 04 — Modelling
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 11–14, target finish 2027-01-17
> **Study notes:** [[04.00 Modelling]] · **Revision:** [[04.99 Summary - Modelling]]

> [!abstract] Phase goal
> A model that clearly beats simple baselines at forecasting cases 4 weeks ahead, chosen by a fair comparison.

## Steps
- [x] Split by time: train, validation, and a final test period touched once (done 2026-11-13, Step 4.1) → [[04.01 Time-Based Splits and Time-Series Cross-Validation]]
- [x] Baselines first: persistence and seasonal (done 2026-11-13, Step 4.1) → [[04.02 Forecasting Baselines - Persistence and Seasonal]]
- [x] Linear models (done 2026-11-13, Step 4.2: Ridge, tuned α; α = 0.1 is close to plain linear regression): [[04. Concepts/Machine Learning/Linear Regression|Linear Regression]] with scaled features, then Ridge → [[04.03 Regularisation - Ridge and Lasso]] · [[04.04 scikit-learn Pipelines and ColumnTransformer]]
- [x] Count model (done 2026-11-13, Step 4.2) → [[04.05 Poisson Regression for Counts]]
- [x] Tree ensembles (done 2026-11-13, Step 4.2): [[Random Forest]], then → [[04.06 Gradient Boosting]]
- [ ] Optional (skipped 2026-11-13, see Step 4.2): an [[04. Concepts/AI & Deep Learning/LSTM|LSTM]] on sequences of the past 12 weeks
- [x] Record every experiment in one results table (done 2026-11-13: `reports/experiments.csv`) → [[04.08 Experiment Tracking]]
- [x] Compare the best model with and without weather features (done 2026-11-13, Step 4.2: ±0.3 % for Ridge)
- [x] Explain the best model and check that its top features make sense (done 2026-11-13, Step 4.3) → [[04.07 SHAP and Feature Importance]]

## Done when
- [x] Both baselines are evaluated (2026-11-13: persistence 25.5, seasonal 31.2)
- [x] At least 3 model families are compared on the same validation split (2026-11-13: linear, Poisson GLM, forest, boosting)
- [x] The best model beats the seasonal baseline by a clear margin (state the percentage) (2026-11-13: Ridge **27.4 %** at 4 weeks, 24.1 % at 6, 21.5 % at 8; 11 % better than persistence at 4)
- [x] A feature-importance plot is saved and interpreted (2026-11-13: `reports/figures/04_feature_importance.png`)
- [x] The best model is saved to `models/` with joblib (2026-11-13: `ridge_h4`, `h6`, `h8`)

---
## Work log
Step sections are added here as each step is finished.

## Step 4.1 — Time split and baselines
> [!important] Goal
> Split the feature table by time, keep a final test period untouched, and score the simple forecasts every model must beat → [[04.01 Time-Based Splits and Time-Series Cross-Validation]] · [[04.02 Forecasting Baselines - Persistence and Seasonal]].

**What was done (2026-11-13)**
- `src/train.py`: `load_table()` builds the features (Phase 3) and keeps complete rows with a target; `split_by_time()` splits by **target week**:

| Part | Target weeks | Rows (h = 4) | Use |
|---|---|---|---|
| Train | to 2021 | 14,475 | Fit; tuning folds inside it |
| Validation | 2022–2023 | 2,550 | Compare models, choose one |
| **Test** | **2024 onwards** | — | **Not loaded in Phase 4.** Scored once in Phase 5 |

- Training leaves out targets in incomplete and COVID weeks (so the last training target is 7 Mar 2020); validation leaves out incomplete weeks.
- Scores: **MAE in cases** (the roadmap's measure, dominated by Colombo and Gampaha) and **MAE of log1p** (a relative error, fair to small districts).
- Baselines, at $h = 4$: persistence (newest known count, week $t-2$) **MAE 25.5**; seasonal (usual level for the week of the year) **31.2**; same week last year 31.0.

**Why this way**
- **Split by target week, not origin.** What matters is which weeks were *predicted*; a 2022 forecast may use 2021 data, just as it would live.
- **Test from 2024.** It holds the 2026 epidemic and the change of case source. The source factors (Phase 2) only touch target weeks after 18 May 2026, all inside the test, so Phase 5 can score those weeks separately; nothing in train or validation depends on them.
- **Persistence is the stronger baseline** (18 % better than seasonal), so "beats seasonal" is the roadmap's test but "beats persistence" is the honest one.

---
## Step 4.2 — Models, tuning and three horizons
> [!important] Goal
> Compare at least three model families on the same validation years, tune them fairly, and see how the result holds at 6 and 8 weeks.

**What was done (2026-11-13)**
- Five models, each predicting log1p(cases) unless noted: **Ridge** (scaled features, in a `Pipeline` with a `ColumnTransformer`) → [[04.03 Regularisation - Ridge and Lasso]] · [[04.04 scikit-learn Pipelines and ColumnTransformer]]; **Poisson regression** on counts → [[04.05 Poisson Regression for Counts]]; **Random Forest** → [[Random Forest]]; **gradient boosting** (`HistGradientBoostingRegressor`), also with a Poisson loss on counts → [[04.06 Gradient Boosting]]. Plus the average of Ridge and the forest.
- Tuned Ridge, the forest and boosting with **time-series cross-validation**: expanding window, folds = target years 2016, 2017, 2018, 2019, each trained on all earlier years. Best settings by mean CV MAE: Ridge α = 100; forest `min_samples_leaf` 5, `max_features` 0.3; boosting learning rate 0.03, 15 leaves, 80 per leaf.
- Every run is appended to `reports/experiments.csv` (date, horizon, model, features, MAE, log MAE, % vs seasonal, rows). The same run at $h$ = 4, 6 and 8, and with and without weather.
- The optional LSTM was skipped: TensorFlow is not installed, and a sequence model is unlikely to beat Ridge on 15,000 rows. It stays a portfolio extra.

**Results, validation 2022–23** (MAE in cases; % = improvement on the seasonal baseline)

| Model | h = 4 | h = 6 | h = 8 |
|---|---|---|---|
| Seasonal | 31.2 | 31.2 | 31.2 |
| Persistence | 25.5 (18 %) | 27.8 (11 %) | 29.4 (6 %) |
| **Ridge** | **22.6 (27.4 %)** | **23.7 (24.1 %)** | **24.5 (21.5 %)** |
| Random forest | 22.9 (26.6 %) | 24.1 | 25.1 |
| Gradient boosting | 24.2 (22.5 %) | 25.3 | 26.1 |
| Poisson regression | 26.0 (16.8 %) | 28.9 | 31.4 |
| Boosting, Poisson loss | 26.1 (16.2 %) | 28.4 | 31.1 |
| Ridge + forest average | 22.5 (28.0 %) | 23.4 | 24.2 |

| Weather question | h = 4 | h = 6 | h = 8 |
|---|---|---|---|
| Ridge, with / without weather | 22.63 / 22.65 | 23.68 / 23.62 | 24.48 / 24.55 |
| Boosting, with / without weather | 24.18 / 24.51 | 25.27 / 25.89 | 26.11 / 27.08 |

Cross-validation MAE by fold (Ridge, α = 100): 2016 **15.3**, 2017 **93.3**, 2018 **16.4**, 2019 **31.7**.

**What it means**
- **Ridge wins at every horizon**, and its lead over persistence grows with the horizon (11 % → 15 % → 17 %): far ahead, knowing the season matters more than copying the last count.
- **Every model fails in an epidemic year** (2017 fold: MAE 93–100 against about 15 in normal years). Counts rose beyond anything in training; **tree models cannot predict outside the range they were trained on**, while a linear model on log cases can carry growth forward. The likely reason Ridge beats the trees here.
- **Tuning hardly mattered** (under 3 % of CV MAE across each grid): the features, not the settings, decide the result.
- **Weather adds almost nothing to Ridge** (±0.3 %) and 1–4 % to boosting, consistent with the weak correlations in Phase 2.
- **The average of Ridge and the forest** is about 1 % better; not worth a second model to maintain and explain. **Chosen: Ridge.**

---
## Step 4.3 — Explaining the model and saving it
> [!important] Goal
> Check that what the chosen model relies on makes sense, and save it for Phase 5 → [[04.07 SHAP and Feature Importance]].

**What was done (2026-11-13)**
- `plot_importance()` in `src/train.py` → `reports/figures/04_feature_importance.png`:
  - **Permutation importance** on validation: the rise in MAE (cases) when one feature is shuffled, 10 repeats; the 25 district columns shuffled together.
  - **Ridge coefficients**, in log cases per standard deviation of each feature.
- `save_best()` refits Ridge on train + validation (targets to 30 Dec 2023) and saves `models/ridge_h4.joblib`, `ridge_h6`, `ridge_h8`: the pipeline plus its columns, horizon, delays, training end and validation scores.
- SHAP was not used: for a linear model the standardised coefficients already give each feature's effect, and `shap` is not installed.

| Feature | Permutation importance | Coefficient |
|---|---|---|
| `log_cases_usual` | **2.27** | +0.29 |
| `log_cases_lag2` | **1.95** | +0.28 |
| `log_cases_lag4`, `lag3` | 1.58, 1.34 | +0.23, +0.22 |
| district (all 25) | 1.08 | — |
| `temp_8w` | 1.00 | +0.26 |
| `humidity_4w` | 0.76 | +0.19 |
| rain (all three) | ≈ 0 | ≤ 0.06 |
| `season_sin`, `season_cos` | ≤ 0.3 | ≈ 0 |

**Interpretation**
- The model leans on **the usual level for the time of year and the most recent counts**, as Phase 2 predicted. Epidemiologically sensible: dengue follows the season and changes gradually.
- **Humidity** carries the weather signal; **rain adds nothing** once humidity and recent cases are known.
- **Temperature's coefficient is partly "where", not "when".** The coolest district, Nuwara Eliya, has the *highest* district term (+0.51): the positive temperature coefficient predicts few cases for the cold hills, and the district term pushes back up. Temperature and district overlap, so neither coefficient alone is the effect of warmth.
- Season sine and cosine barely help a linear model, as expected in Phase 3: the season means opposite things in Jaffna and Colombo.

**Why this way**
- **Permutation importance on validation** measures what the model uses on data it did not train on, in the same units as the score.
- **Shuffle the district columns as one group.** One-hot columns shuffled one at a time each look unimportant.
- **Refit on train + validation before saving.** The test (2024 onwards) is still unseen; Phase 5 scores this model once.

> [!tip] What to remember
> - Beat persistence, not only the seasonal average; it is the stronger baseline.
> - Time-series CV: train on the past, test on the next year, repeat. Never shuffle.
> - Trees cannot extrapolate beyond their training range; in an epidemic that matters.
> - When features overlap (temperature and place), coefficients cannot be read one at a time.
> - If tuning barely moves the score, spend the time on features or data instead.

**Check yourself**
1. Why was the split made on the target week and not on the origin week?
2. Why might Ridge beat the random forest when cases reach new highs?
3. Why does the cool district Nuwara Eliya get the largest positive district coefficient?

> [!success]- Answers
> 1. The split is about which weeks are predicted. A forecast for 2022 may use 2021 data, exactly as it would live; what must stay unseen is the case count in the predicted weeks.
> 2. A tree predicts an average of training targets in a leaf, so it cannot go above the highest values it has seen; a linear model on log cases extends a rising trend.
> 3. Its low temperature, times the positive temperature coefficient, already predicts very few cases; the district term corrects that back up. The two features share information, so neither coefficient alone means "the effect of temperature" or "the effect of the place".
