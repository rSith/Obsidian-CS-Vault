---
type: project
stage: planning
repo: https://github.com/rSith/dengue-early-warning
tags: [project, ml, web]
---
# Dengue Early-Warning
> [!abstract] Goal
> Forecast weekly dengue cases for each Sri Lankan district 4 to 8 weeks ahead from rainfall, temperature, humidity and recent case counts, and raise a **Normal / Watch / Warning** alert when a district is heading for an unusually high count. Results are shown on a district map dashboard.

## Where things live
- **Code:** `Code Vault/Projects/Machine Learning-Projects/dengue-early-warning` (local Git repository, on GitHub as `rSith/dengue-early-warning` since 2026-10-11)
- **Roadmap:** `Code Vault/Projects/Machine Learning-Projects/Dengue Outbreak Early-Warning System — Project Roadmap.md` (outside this vault, so it cannot be linked)
- **Notes:** this folder. Start at [[00. Dengue Early-Warning Overview]], the map of every note.
  - **Phase notes** (below) record progress: the plan, the checklist and a work log of what was done.
  - **Lessons** in `01. Lessons/` explain what each phase needs you to know, one folder per phase.

## Plan
Each line links the progress note, then the lesson to study for it.
- [ ] **Phase 0** — Setup and domain knowledge → [[Phase 00 - Setup and Domain Knowledge]] · study [[00.00 Setup and Domain Knowledge]]
- [x] **Phase 1** — Data collection → [[Phase 01 - Data Collection]] · study [[01.00 Data Collection]]
- [x] **Phase 2** — Data cleaning and exploratory analysis → [[Phase 02 - Cleaning and EDA]] · study [[02.00 Cleaning and EDA]]
- [x] **Phase 3** — Feature engineering → [[Phase 03 - Feature Engineering]] · study [[03.00 Feature Engineering]]
- [x] **Phase 4** — Modelling → [[Phase 04 - Modelling]] · study [[04.00 Modelling]]
- [x] **Phase 5** — Evaluation and alert thresholds → [[Phase 05 - Evaluation and Alerts]] · study [[05.00 Evaluation and Alerts]]
- [ ] **Phase 6** — Database and Flask API → [[Phase 06 - Database and Flask API]] · study [[06.00 Database and Flask API]]
- [ ] **Phase 7** — Dashboard → [[Phase 07 - Dashboard]] · study [[07.00 Dashboard]]
- [ ] **Phase 8** — Automation, deployment and MLOps monitoring → [[Phase 08 - Automation and MLOps]] · study [[08.00 Automation and MLOps]]
- [ ] **Phase 9** — Documentation, report and portfolio → [[Phase 09 - Documentation and Portfolio]] · study [[09.00 Documentation and Portfolio]]

## Tech stack
- **Data and modelling:** Python, [[Pandas]], NumPy, [[Matplotlib]], seaborn, statsmodels, scikit-learn, SHAP
- **Backend:** Flask, SQLAlchemy, MySQL
- **Dashboard:** HTML, CSS, JavaScript, Leaflet.js, Chart.js
- **Tooling:** Git and GitHub, pytest, Docker, GitHub Actions

## Related notes
General concepts are explained once, in `04. Concepts`. The project notes link to them and do not repeat them.
- **Courses:** [[ML Specialization - Course Home]] · [[03. Self-Study/ML in Production/Machine Learning in Production|Machine Learning in Production]]
- **Workflow:** [[04. Concepts/Machine Learning|Machine Learning]] · [[04. Concepts/Machine Learning/Machine Learning Pipeline|Machine Learning Pipeline]] · [[04. Concepts/Machine Learning/Exploratory Data Analysis|Exploratory Data Analysis]] · [[Data Preprocessing]] · [[Model Evaluation]]
- **Models:** [[04. Concepts/Machine Learning/Supervised Learning|Supervised Learning]] · [[04. Concepts/Machine Learning/Regression|Regression]] · [[04. Concepts/Machine Learning/Linear Regression|Linear Regression]] · [[Multiple Linear Regression]] · [[Decision Trees]] · [[Random Forest]] · [[04. Concepts/Machine Learning/Classification|Classification]] · [[04. Concepts/AI & Deep Learning/LSTM|LSTM]] · [[04. Concepts/AI & Deep Learning/RNN|RNN]]
- **Tools:** [[Pandas]] · [[NumPy]] · [[Matplotlib]] · [[Seaborn]]

## Log
- 2026-10-08: roadmap reviewed and data sources checked; project folder, local Git repository and these notes created. Planned start 2026-10-12.
- 2026-10-08: study notes written for all ten phases (`01. Lessons`), with an overview map and a progress note for each phase.
- 2026-10-11: virtual environment `.venv` created and the seven Phase 0 packages installed; three activation path mistakes and their fixes recorded in Phase 00, Step 0.5.
- 2026-10-11: all six data sources inspected by hand (Phase 01, Step 1.1). Found that the WER is 8 weeks behind, not 7, and that the reporting week changed from Saturday–Friday to Monday–Sunday in 2026.
- 2026-10-11: the five source decisions confirmed (Phase 01, Step 1.1) and the first commit made, `b329938` (Phase 00, Step 0.6). Not pushed; the GitHub repository does not exist yet.
- 2026-10-11: district coordinates table `data/reference/districts.csv` created, 26 units mapped to 25 districts with the main town as the weather point (Phase 01, Step 1.2).
- 2026-10-11: GitHub repository `rSith/dengue-early-warning` created and the first commit pushed (Phase 00, Step 0.7).
- 2026-10-11: weather collector `src/collect_weather.py` written and first run made: 388 of 546 files saved before Open-Meteo's rate limit stopped it (Phase 01, Step 1.3, in progress). New lesson note [[01.07 Resumable Downloads - Caching and Rate Limits]].
- 2026-10-23: weather download finished on the second run: all 546 files, `weather_daily.csv` built and checked (197,600 rows, no gaps, no missing values) and its columns added to the data dictionary (Phase 01, Step 1.3).
- 2026-10-28: case collector started (Phase 01, Step 1.4, in progress): `denguedatahub` file downloaded and built into `cases_raw.csv` (26,311 rows, 2006-12-23 to 2026-05-17). Found a missing Puttalam week, a mislabelled week 53, a 2-day gap at the week-start change and odd 2009 week lengths; all kept as published for Phase 2.
- 2026-10-29: WER part of the case collector (Phase 01, Step 1.4, Part 2): PDF parser committed, then a downloader that reads the archive page with BeautifulSoup. The site was down (509), so the page was studied from a Wayback copy; also found the site blocks the `python-requests` User-Agent (403). Weeks 21 to 32 still to download.
- 2026-10-29: WER site still down (500) and the Wayback Machine offline too; fallback plan written in Phase 01, Step 1.4 (Dengue Control Unit PDFs fill all but weeks 22, 23 and 31). Population started (Phase 01, Step 1.5, in progress): `src/collect_population.py` downloads the 2014–2024 and 2025–2026 tables and parses them with `extract_table()`, because `extract_text()` splits numbers in the 2025–2026 PDF. Districts add up to the Sri Lanka row exactly. No official district table for 2006–2013, so those years will be back-filled at each district's 2014–2019 growth rate. New lesson note [[01.08 Population Denominators and Incidence Rates]].
- 2026-11-13: dashboard prototype built ahead of order (Phase 07, Step 7.1): Flask page with Leaflet map, Chart.js detail chart and sortable table, fed by `src/export_dashboard.py` → `dashboard.json`. Real case history and endemic-channel thresholds, demo seasonal-ratio forecast. Current data gives 19 Warning, 2 Watch, 4 Normal. Phase 1 population and WER fallback still open; WER site still 500.
- 2026-11-13: population step finished (Phase 01, Step 1.5): `population.csv` saved, 525 rows. Found the census adjustment made the national total rise 2024→2025 while the published figures fall; fixed by stepping back from 2025 at the 2025→2026 growth instead of the pre-COVID rate. New `check_series()` tested against three deliberate mistakes; columns added to `docs/data.md`.
- 2026-11-13: case collector finished with the Dengue Control Unit fallback (Phase 01, Step 1.4, Part 3): the site's WordPress media list gave all 44 weekly bulletins of 2026, so `cases_raw.csv` now runs to 1 Nov 2026 with no gap and a `source` column. The two systems agree nationally (within 3 %) but not by district (Kilinochchi ×0.43, Colombo ×1.13), and number 2025 weeks one apart; Phase 2 must correct the break. Bulletins appear about 7 days after the week ends. Phase 1 now needs only the from-scratch re-run.
- 2026-11-13: **Phase 1 done** (two days before its target). All processed files deleted and rebuilt by the three collectors with no manual step; old rows identical (Phase 01, Step 1.6). The rebuild also picked up the new week 45 bulletin by itself.
- 2026-11-13: Phase 2 started (Phase 02, Step 2.1): Dengue Control Unit weeks turned into WER-equivalent counts with a shrunk per-district factor $(W+200)/(N+200)$, 0.89 (Colombo) to 1.47 (Kilinochchi). Tested on 2026 weeks it never saw: about 10 % less error in district totals; most of the rest is week-to-week noise.
- 2026-11-13: `weekly.csv` built (Phase 02, Step 2.2): 25 districts × 1,037 weeks with WER-scale cases, weather on the case weeks and interpolated population. Found 2026 week 14 scrambled in `denguedatahub` (the only such week in 19 years), repaired from the Dengue Control Unit; without it the source factors are 0.90–1.21 and the correction cuts held-out error by 19 %. Found 12 incomplete weeks to flag next.
- 2026-11-13: unusual weeks flagged in `weekly.csv` (Phase 02, Step 2.3): 12 incomplete weeks, 94 COVID weeks (Mar 2020 – Dec 2021, cases at 0.2–0.65 × normal), 134 epidemic weeks (over 3 × the usual for the time of year: 2017, 2019, 2023, 2026 and 2009–2012).
- 2026-11-13: **Phase 2 done** (Phase 02, Step 2.4): EDA notebook with five findings. Two dengue seasons (January in the north and east, June–July and December in the west); Colombo and Gampaha 35 % of cases; autocorrelation 0.72 at 8 weeks; with season and trend removed, 8-week rain ending 5 weeks earlier and humidity 7–9 weeks earlier correlate about 0.16 with cases. A same-week temperature link turned out to be a shared trend.
- 2026-11-13: **Phase 3 done** (Phase 03, Steps 3.1–3.2): `build_features()` with case delay 2 weeks (from the Dengue Control Unit's publication times), weather delay 1, horizon 4; 41 features; 6 pytest tests pass, including a leakage test shown to catch a one-week leak. Recent cases correlate 0.79 with the target, usual level 0.68, humidity 0.34, 8-week rain 0.31.
- 2026-11-13: **Phase 4 done** (Phase 04, Steps 4.1–4.3): Ridge on log cases beats the seasonal baseline by 27.4 % at 4 weeks (MAE 22.6 vs 31.2) and persistence by 11 %; random forest, boosting and Poisson models are no better; tuning and weather barely matter; every model fails in the 2017 epidemic. Saved `ridge_h4/h6/h8`; test period 2024 onwards still untouched.
- 2026-11-13: **Phase 5 done** (Phase 05, Steps 5.1–5.3): endemic channel on log cases; forecast Warning at mu + 1.25 sd chosen on validation and committed before the test. Final test (2024 – Nov 2026, run once): MAE 26.3 vs 34.5 for the best baseline (−24 %); 35 of 52 outbreaks caught, 2.5 weeks ahead on average; the forecast lags in the fast June–July 2026 epidemic (data 6 weeks old).
- 2026-11-13: Phase 6 nearly done (Phase 06, Steps 6.1–6.3): six-table schema with ER diagram, loader, `predict.py` (forecasts from the week of 9 Nov; backfill matches the Phase 5 test forecasts), four-endpoint Flask API with 11 tests and README docs. Runs on SQLite until MySQL is set up: the MySQL service is stopped and needs the user's root password.
