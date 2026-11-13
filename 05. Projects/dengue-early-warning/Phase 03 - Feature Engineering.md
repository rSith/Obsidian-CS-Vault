---
type: project-note
project: Dengue Early-Warning
status: done
tags: [project, ml]
---
# Phase 03 — Feature Engineering
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 9–10, target finish 2026-12-20
> **Study notes:** [[03.00 Feature Engineering]] · **Revision:** [[03.99 Summary - Feature Engineering]]

> [!abstract] Phase goal
> A model-ready table where every row holds only information that would have been available at prediction time, plus the target (cases 4 weeks later).

## Steps
- [x] Fix the convention: forecast week, horizon, target week (done 2026-11-13, Step 3.1) → [[03.01 Forecast Origin, Horizon and Target]]
- [x] Lagged weather features at the lags found in Phase 2 (done 2026-11-13, Step 3.2) → [[03.02 Lag and Rolling-Window Features]]
- [x] Rolling weather features: 4-week rainfall total, 8-week mean temperature (done 2026-11-13, Step 3.2: also 8-week rain and 4-week humidity)
- [x] Lagged case features and the change between them, respecting the reporting delay (done 2026-11-13, Step 3.2: lags 2–5) → [[01.06 Reporting Delay and Data Availability]]
- [x] Seasonality features: sine and cosine of the time of year (done 2026-11-13, Step 3.2) → [[03.04 Cyclical and One-Hot Encoding]]
- [x] District features: one-hot district, cases per 100,000 people (done 2026-11-13, Step 3.2)
- [x] Target: cases shifted 4 weeks into the future (done 2026-11-13, Step 3.2; horizon is a parameter)
- [x] Put it all in one function in `src/features.py` (done 2026-11-13, Step 3.2) → [[03.05 Python Functions and Modules]]
- [x] Leakage check: by hand for a few rows, and as a test (done 2026-11-13, Step 3.2) → [[03.03 Data Leakage]] · [[03.06 Unit Testing with pytest]]

## Done when
- [x] `src/features.py` builds the full feature table from `weekly.csv` in one call (2026-11-13)
- [x] Rows with missing lags (the first weeks of each district) are handled the same way everywhere (2026-11-13: kept as NaN, dropped only by `complete_rows()`)
- [x] The leakage check is done and noted (2026-11-13: test + two rows by hand)
- [x] At least 2 tests pass (2026-11-13: 6 pass)

---
## Work log
Step sections are added here as each step is finished.

## Step 3.1 — The convention: origin, delays and horizon
> [!important] Goal
> Fix, once, what a forecast "made in week $t$" may see and what it predicts, so every feature is measured from the same point → [[03.01 Forecast Origin, Horizon and Target]].

**What was done (2026-11-13)**
- Set the convention at the top of `src/features.py`:

| Setting | Value | Reason |
|---|---|---|
| Origin $t$ | The week the forecast is made, on its last day | One row = one district at one origin |
| `CASE_DELAY_WEEKS` $d$ | **2** | The Dengue Control Unit bulletin appears a median 7 days (3–10) after the week ends ([[Phase 01 - Data Collection]], Step 1.4, Part 3). At the end of week $t$ the bulletin for $t-1$ is usually out, the one for $t-2$ always |
| `WEATHER_DELAY_WEEKS` | **1** | Open-Meteo's final values are 5 days behind; week $t-1$ is complete |
| `HORIZON` $h$ | **4** (a parameter: 6 and 8 later) | One model per horizon, the direct approach |

- The real distance from the newest case data to the target is $d + h = 6$ weeks.
- Converted the Phase 2 lags to the origin. The best rain was the 8-week total ending 5 weeks before the case week; with $h = 4$ that is weeks $t-8$ to $t-1$, exactly the newest 8 weeks of weather available at the origin. Humidity 7–9 weeks before the case week is weeks $t-5$ to $t-3$, inside the 4-week humidity window.

**Why this way**
- **The delay of the live source, not of the history.** The model is trained on WER history but will run on the Dengue Control Unit bulletin; the backtest must be as blind as the live system → [[01.06 Reporting Delay and Data Availability]].
- **$d = 2$, not 1.** In some weeks the bulletin is 10 days late. A model trained with $d = 1$ would sometimes run without its most important input.
- **Rows are report weeks.** `weekly.csv` has every district in every week, so "$k$ weeks before" is `shift(k)` within a district. `check_grid()` stops if that stops being true.

---
## Step 3.2 — `build_features()`, tests and the leakage check
> [!important] Goal
> One function that turns `weekly.csv` into the model table for any horizon, tests that show it does what it says, and a check that no feature looks past the origin → [[03.02 Lag and Rolling-Window Features]] · [[03.03 Data Leakage]].

**What was done (2026-11-13)**
- `build_features(weekly, horizon=4, case_delay=2, weather_delay=1)` in `src/features.py` returns one row per district and origin week: `district`, `origin`, `target_week`, 41 features, `target`, and the target week's flags.

| Group | Features | Uses weeks |
|---|---|---|
| Recent cases | `log_cases_lag2` … `lag5`, `log_cases_mean4`, `log_cases_change` (lag 2 − lag 4), `incidence_latest` | $t-2$ … $t-5$ |
| Season of cases | `log_cases_last_year` (week $t+h-52$); `log_cases_usual`: median log cases for the target's week of the year over the previous 5 years, smoothed over ±1 week | a year or more before $t$ |
| Weather | `rain_8w`, `rain_4w` (totals), `rain_4w_before` (weeks $t-8$ … $t-5$), `humidity_4w`, `temp_8w` (means) | $t-1$ and older |
| Time of year | `season_sin`, `season_cos` of the target week | — |
| Place | `district_*`, one-hot, 25 columns | — |

- `feature_columns()` lists the inputs; `complete_rows()` drops rows whose features cannot exist yet. Rows are kept with `NaN` by `build_features()`, so every caller handles them the same way.
- Uncommented `pytest` in `requirements.txt`; wrote 6 tests in `tests/test_features.py` on a small made-up table whose case values encode their own week and district (week 10 of the first district has 1,010 cases).

**Checked (2026-11-13)**

| Check | Result |
|---|---|
| Real table | 25,925 rows; 23,425 complete from origin 22 Nov 2008; 23,325 with a target; **100 live rows** (the newest 4 weeks of each district, features but no target) |
| Tests | **6 passed**: lag 2 of week 10 = week 8; target of week 100 = week 104; no lag crosses into another district; the rain window ends at $t-1$; leakage test at $h = 4$ and $h = 8$ |
| Can the leakage test fail? | Scrambling cases from $t-2$ (one week too early) changes exactly `log_cases_lag2`, `mean4`, `change`, `incidence_latest`; scrambling weather from $t-1$ changes exactly the four weather windows; the correct boundary changes nothing |
| By hand, Colombo, origin 15 Jun 2019 | lag 2 = 233 = cases in week 1 Jun; `rain_8w` 372.4 = rain 20 Apr–8 Jun; last year 380 = week 14 Jul 2018; target 474 = week 13 Jul 2019 |
| By hand, Jaffna, origin 5 Jan 2026 (across the week-start change) | lag 2 = 54 = report week of 20 Dec 2025; `rain_8w` 659.5 = report weeks 8 Nov–29 Dec |

Correlation of each feature with log target (complete rows): recent cases 0.73–0.80, usual level 0.68, last year 0.58, incidence 0.44, humidity 0.34, 8-week rain 0.31, 4-week rain 0.27, change 0.09, temperature −0.05, **season sine and cosine about 0**.

**Why this way**
- **Log cases.** Counts span 0 to 2,600; log1p keeps epidemics from dominating and turns "twice as many" into the same step everywhere.
- **"Usual level" from previous years only, and the target's week of the year.** Every value is at least 52 weeks before the target, so before the origin for any horizon below 52 − $d$.
- **One function with parameters.** Training, the backtest and the live pipeline call the same code with the same delays → [[03.05 Python Functions and Modules]].
- **The leakage test is general.** It does not check each feature's formula; it scrambles everything the origin may not see and requires the features not to move. A new feature added later is covered automatically → [[03.06 Unit Testing with pytest]].
- **Season sine/cosine near 0 is expected.** The season means opposite things in Jaffna (January) and Colombo (July), so it helps only together with the district: a tree model can combine them; a linear model would need district × season terms. A point for Phase 4.

```python
lagged = {k: log_cases.groupby(by).shift(k) for k in range(case_delay, case_delay + 4)}
shifted = df["rain_mm"].groupby(by).shift(weather_delay)            # shift before rolling
rain_8w = shifted.groupby(by).rolling(8, min_periods=8).sum()
target  = g["cases"].shift(-horizon)                                 # the only look ahead
```

```python
# leakage test: nonsense in everything the origin may not see; features must not move
scrambled.loc[week_no > t - d, ["cases", "population"]] = 999_999
scrambled.loc[week_no > t - w, ["rain_mm", "humidity", "temp_mean"]] = -1
pd.testing.assert_series_equal(before[columns], after[columns])
```

**Problems and fixes**

| Problem | What was tried | Fix |
|---|---|---|
| `log_cases_usual` jumped between neighbouring weeks (Colombo 5.05 → 5.61 → 5.10) | — | A median of 5 values for one week is noisy; averaged each week of the year with its neighbours, wrapping week 52 to week 1 (as for the dashboard thresholds) |
| The Jaffna check by hand did not match at first | Counted calendar weeks back from 5 Jan 2026 | Features count **report weeks** (rows). Across the Saturday → Monday change, two report weeks back is the week of 20 Dec (16 days). Matches when checked by rows; it reaches further back, so it is safe |

**Small leaks accepted and noted**
- `population` is interpolated between 1 July estimates, and the next year's estimate is published later. It moves incidence by under 1 % a year; accepted.
- The source factors in `weekly.csv` use 2026 weeks 1–20 ([[Phase 02 - Cleaning and EDA#Step 2.1 — Put the Dengue Control Unit weeks on the WER scale|Phase 02, Step 2.1]]). If the final test period includes them, re-estimate the factors from 2025 only for that evaluation.

> [!tip] What to remember
> - Write the delays as named settings at the top of the module; every lag follows from them.
> - Shift before rolling, inside `groupby`, so the window ends at the right week and never crosses districts.
> - A leakage test that scrambles "everything the origin may not see" covers features not yet written.
> - Prove a test can fail before trusting that it passes.

**Check yourself**
1. Why is the newest case feature lag 2 and not lag 1?
2. What does the leakage test change, and what must stay the same?
3. Why do `season_sin` and `season_cos` hardly correlate with the target on their own?

> [!success]- Answers
> 1. The bulletin for week $t-1$ is sometimes not out until 10 days after that week ends, so on the last day of week $t$ it may be missing; week $t-2$ always exists. The backtest must be as blind as the live system.
> 2. It replaces every case count after week $t-2$ and every weather value after week $t-1$ with nonsense, rebuilds the table, and requires all features of origin $t$ to be unchanged; only the target may differ.
> 3. The same time of year is the peak in some districts (Jaffna, January) and the low in others (Colombo, January), so across all districts the effect cancels. It is useful only combined with the district.
