---
type: project-note
project: Dengue Early-Warning
status: done
tags: [project, ml]
---
# Phase 05 — Evaluation and Alerts
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 15–16, target finish 2027-01-31
> **Study notes:** [[05.00 Evaluation and Alerts]] · **Revision:** [[05.99 Summary - Evaluation and Alerts]]

> [!abstract] Phase goal
> Turn forecasts into Normal / Watch / Warning alerts and show, on unseen test data, how many real outbreaks the system would have caught early.

## Steps
- [x] Build the endemic channel for each district and week of year (done 2026-11-13, Step 5.1) → [[05.02 Endemic Channel and Alert Thresholds]]
- [x] Define the alert levels: Watch above mean + 1 SD, Warning above mean + 2 SD (done 2026-11-13, Step 5.1: on log cases; forecasts use lowered lines, Step 5.2)
- [x] Define a true outbreak the same way, using actual cases (done 2026-11-13: 2+ weeks above the Warning line, at least 5 cases)
- [x] Choose thresholds on **validation** data, showing the precision–recall trade-off (done 2026-11-13, Step 5.2: $\mu + 1.25\sigma$) → [[05.03 Alert Metrics - Precision, Recall and Lead Time]]
- [x] Add prediction intervals (done 2026-11-13, Step 5.2: from CV errors; coverage 79 % validation, 83 % test) → [[05.04 Prediction Intervals and Quantile Regression]]
- [x] Run the final test **once** on the held-out period (done 2026-11-13, Step 5.3): MAE and RMSE per district and overall, precision and recall for Warnings, lead time → [[05.01 Forecast Error Metrics - MAE and RMSE]]
- [x] Plot actual against predicted for each district, with alert periods shaded (done 2026-11-13, Step 5.3) → [[05.05 Charts That Communicate Results]]
- [x] Write an honest error analysis: which outbreaks were missed, and why (done 2026-11-13, Step 5.3)

> [!note] Order of two steps
> The roadmap lists "run the final test" before "tune for recall". Tuning after the test would use the test data twice, so here the thresholds are chosen on validation data first.

## Done when
- [x] The endemic channel is computed for every district and week (2026-11-13)
- [x] The final test is run once and the results are recorded (2026-11-13: `reports/test_*.csv`)
- [x] Precision, recall and average lead time are reported (2026-11-13: 67 % of outbreaks caught, 2.5 weeks ahead, 30 % of Warning weeks are outbreak weeks)
- [x] Actual-versus-predicted figures with shaded alerts are saved (2026-11-13: `05_test_national.png`, `05_test_districts.png`)
- [x] The error analysis is written (2026-11-13, Step 5.3)

---
## Work log
Step sections are added here as each step is finished.

## Step 5.1 — The endemic channel
> [!important] Goal
> A "normal" range for every district and week of the year, built only from earlier years, so that forecasts and actual counts can both be called Normal, Watch or Warning → [[05.02 Endemic Channel and Alert Thresholds]].

**What was done (2026-11-13)**
- `endemic_channel()` in `src/alerts.py`, with the four fixes from lesson 05.02:
  - on **log(1 + cases)**, so one large year does not set the line;
  - for year $Y$, the **5 most recent usable years before $Y$**;
  - **pooled over ±2 weeks** of the year (up to 25 values; at least 10 required);
  - **leaving out epidemic, COVID and incomplete weeks** (Phase 2 flags).
- Lines for actual counts: **Watch** $\mu + 1\sigma$, **Warning** $\mu + 2\sigma$ (log scale). A week also needs **at least 5 cases** to count, so a jump from 0 to 2 in a tiny district is not an outbreak.
- `add_thresholds()` attaches the lines for any week; `level()` classifies counts.

**Checked**: a channel for all 25 districts × 52 weeks in every year 2009–2027. Share of district-weeks at Warning level: 2–5 % in quiet years (2022, 2024, 2025), 9 % in 2023, 22 % in 2026, 64 % in 2017.

**Problems and fixes**

| Problem | What was tried | Fix |
|---|---|---|
| 2022 had a channel for only 700 of 1,300 district-weeks | Plain "previous 5 years" (2017–2021) | 2017 (epidemic) and 2020–21 (COVID) are mostly flagged, leaving under 10 values for many weeks. Now the 5 most recent **usable** years (under half their weeks flagged): for 2022, 2019, 2018, 2016, 2015, 2014. Full coverage |

---
## Step 5.2 — Choosing the alert rule on validation
> [!important] Goal
> Decide how a **forecast** becomes a Warning, using validation years only (2022–23), and fix the rule before the test → [[05.03 Alert Metrics - Precision, Recall and Lead Time]] · [[05.04 Prediction Intervals and Quantile Regression]].

**What was done (2026-11-13)**
- `src/evaluate.py`: every alert is a row (district, issue week, target week). Outbreak weeks with actual count above the Warning line are grouped into **episodes**; an episode is caught if a warning targets a week from 2 weeks before its start to its end; **lead time** = weeks from the earliest such warning's issue to the episode's start.
- **Naive rule** for comparison (lesson 05.03 §7): warn at week $t$ when the newest known week, $t-2$, is already at Warning level.
- **Prediction interval** (80 %): the Ridge forecast moved by the 10th and 90th percentiles of its log errors in the **training-period CV folds** (2016–19), so validation is not used to set it. Errors: $-0.68$ to $+1.34$ on the log scale, so the truth can be 3.8 × the forecast but only half of it. Coverage on validation **79.2 %**.
- Candidate rules: forecast above $\mu + k\sigma$ for $k$ = 2.0 to 0.75, and "upper end of the 80 % interval above $\mu + 2\sigma$".

**Validation 2022–23** (35 outbreaks of 2+ weeks; 6 % of district-weeks are outbreak weeks):

| Rule | Weeks warned | Outbreaks caught | Mean lead | Warning weeks that are outbreak weeks |
|---|---|---|---|---|
| $\mu + 2\sigma$ (roadmap) | 1.8 % | 23 % | 3.6 weeks | 34 % |
| $\mu + 1.5\sigma$ | 5.7 % | 40 % | 4.6 | 26 % |
| **$\mu + 1.25\sigma$ (chosen)** | **10.8 %** | **60 %** | **4.2** | **21 %** |
| $\mu + 1\sigma$ | 19.8 % | 77 % | 5.2 | 15 % |
| upper 80 % > $\mu + 2\sigma$ | 59.9 % | 97 % | 5.8 | 9 % |
| naive ($t-2$ already high) | — | 100 % | **−1.7** (late) | — |

**Decision**: forecast **Warning above $\mu + 1.25\sigma$, Watch above $\mu + 0.25\sigma$** (both 0.75 σ below the lines for actual counts). Rule: the highest recall that keeps Warnings to at most twice the true outbreak rate. Written in `src/alerts.py` (`FORECAST_WARNING_SD`) and **committed before the test** (`36bd913`).

**Problems and fixes**

| Problem | What was tried | Fix |
|---|---|---|
| The interval rule showed 92 % precision but warned 60 % of the time | Precision counted per warning episode | Long warning stretches counted as one "true alarm". Precision is now counted on **weeks**, and the share of weeks warned is reported |
| 53 of 88 "outbreaks" lasted one week | — | Recall is counted on episodes of **2+ consecutive weeks**; single-week blips are noise |

**Why this way**
- **A forecast is smoother than reality** (lesson 05.02 §6), so the line for forecasts must be lower than the line for actual counts.
- **Recall first, with a limit.** A missed outbreak costs more than a false alarm, but a system that warns all the time is ignored.
- **Choose on validation, commit, then test.** The git history shows the rule existed before the test results.

---
## Step 5.3 — The final test, run once, and the error analysis
> [!important] Goal
> Score the saved models and the chosen rule **once** on target weeks from 2024 to Nov 2026, and explain what went wrong → [[05.01 Forecast Error Metrics - MAE and RMSE]] · [[05.05 Charts That Communicate Results]].

**What was done (2026-11-13)**
- `python -m src.evaluate test` loads `ridge_h4/h6/h8` (trained to Dec 2023), predicts every test week, and writes `reports/test_results.csv`, `test_by_district.csv`, `test_alerts.csv`, `test_forecasts_h4.csv`, `test_settings.json`. **It refuses to run again** once `test_results.csv` exists (checked: a second run stopped with exit code 1 and changed nothing).
- Weeks from the WER and from the Dengue Control Unit (from 18 May 2026, the 2026 epidemic) are scored separately.
- `python -m src.evaluate figures` draws `reports/figures/05_test_national.png` and `05_test_districts.png` from the saved forecasts, without re-running the test.

**Forecast accuracy, test 2024 – Nov 2026** (MAE in cases; 3,700 district-weeks)

| | Ridge | Persistence | Seasonal | Ridge vs better baseline |
|---|---|---|---|---|
| **All, 4 weeks ahead** | **26.3** (RMSE 83.1) | 34.5 | 34.6 | **−24 %** |
| WER weeks (to 17 May 2026) | 16.1 | 19.9 | 21.9 | −19 % |
| Dengue Control Unit weeks (2026 epidemic) | 76.6 | 106.5 | 97.4 | −21 % |
| All, 6 weeks ahead | 28.7 | 40.9 | 34.6 | −17 % |
| All, 8 weeks ahead | 29.8 | 45.5 | 34.6 | −14 % |

The 80 % interval held **83.0 %** of test weeks.

**Alerts, test** (52 outbreaks of 2+ weeks; 8.9 % of district-weeks are outbreak weeks)

| Rule | Weeks warned | Caught | Before the start | Mean lead | Warning weeks that are outbreak weeks |
|---|---|---|---|---|---|
| **$\mu + 1.25\sigma$ (chosen)** | 13.9 % | **35 (67 %)** | 25 | **2.5 weeks** | 30 % |
| $\mu + 2\sigma$ (roadmap) | 5.0 % | 22 (42 %) | 15 | 1.9 | 36 % |
| naive ($t-2$ already high) | — | 52 (100 %) | 0 | **−1.3** (late) | — |

**Error analysis**
- **In a fast epidemic the forecast lags.** National cases went from 1,574 (11 May) to 7,782 (29 Jun 2026) in seven weeks; the forecast for that peak week was 1,944, and it kept rising (3,753 on 10 Aug) after the real count had fallen to 2,682. Each forecast sees cases only up to 2 weeks before it is made and predicts 4 weeks ahead, so it works from a picture **6 weeks old**. A rise this steep outruns it; the model learns "high stays high" and follows the curve late. This is why the Dengue Control Unit weeks have 4–5 × the error of the earlier test weeks (and why the lead time fell from 4.2 weeks in validation to 2.5).
- **17 missed outbreaks**, in three groups:
  - **9 in the June–July 2026 epidemic** (Colombo 8 Jun, Galle, Kandy, Matale 29 Jun, Kilinochchi, Mullaitivu ×2, Mannar 22 Jun): rose faster than the 6-week-old data could show;
  - **small northern districts** (Mannar ×3 overall, Mullaitivu ×2, Kilinochchi): outbreaks of a handful of cases, under 5 a week most of the year, where week-to-week noise is as large as the signal;
  - **out-of-season rises early in 2026** (Ratnapura Feb and Mar, Kalutara 9 Mar, Hambantota Jan): the model leans on the usual level for the time of year, which was low then.
- **Relative error is worst in the small districts** (MAE ÷ mean cases 0.77–0.84 in Mannar, Kilinochchi, Vavuniya, Mullaitivu; 0.39–0.50 in Colombo, Gampaha, Kandy, Kalutara).
- **The source change** cannot be separated from the epidemic here: the Dengue Control Unit weeks are the epidemic weeks. The per-district correction (Step 2.1) was applied; whether it was right cannot be tested without WER data for those weeks.
- **What would help**: a shorter case delay (the bulletin is usually out after 7 days; $d = 1$ when it is), a growth feature that reacts to the last 2 weeks more strongly, or weekly re-training during an epidemic. For Phase 8: these are the improvements to try, judged on a new test period.

**Why this way**
- **One run, guarded in code.** Running the test again after changes would turn it into a second validation set.
- **Score the two sources separately**, so the change of source cannot hide inside an overall number.
- **Episodes and lead time to the start**, not to the peak (lesson 05.03 §6); the naive rule shows that "caught" alone means little: it catches everything, always late.

> [!tip] What to remember
> - Choose thresholds on validation, write them down (commit), then test once.
> - Count outbreaks as episodes, measure lead time to the start, and always compare with the naive "it is already high" rule.
> - A forecast with a reporting delay works from an old picture; in a fast epidemic it will lag. Say so in the report.
> - Precision of warning *episodes* can flatter a rule that warns all the time; report the share of weeks warned.

**Check yourself**
1. Why is the forecast Warning line lower ($\mu + 1.25\sigma$) than the line that defines an outbreak ($\mu + 2\sigma$)?
2. The naive rule caught all 52 outbreaks. Why is it not the better system?
3. Why did the model miss the start of the June 2026 epidemic in Colombo?

> [!success]- Answers
> 1. A forecast predicts the expected count, which is smoother than reality and rarely reaches extreme values; at $\mu + 2\sigma$ it caught only 23 % of validation outbreaks. Lowering the line (chosen on validation) offsets that.
> 2. It warns only after an outbreak week has been published, on average 1.3 weeks after the start. An early-warning system must warn before; the model did so for 25 outbreaks, 2.5 weeks ahead on average.
> 3. Cases tripled in about five weeks, while each forecast uses cases at least 2 weeks old to predict 4 weeks ahead. The model saw the start of the rise only after it was well under way, and leans on recent counts and the usual level, so it followed late.
