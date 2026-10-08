---
type: project-note
project: Dengue Early-Warning
status: stub
tags: [project, ml]
---
# Phase 02 — Cleaning and EDA
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 6–8, target finish 2026-12-06
> **Study notes:** [[02.00 Cleaning and EDA]] · **Revision:** [[02.99 Summary - Cleaning and EDA]]

> [!abstract] Phase goal
> One clean weekly table (district × week), and evidence of how weather relates to cases, especially at which time lags.

## Steps
- [ ] Aggregate daily weather to weeks that match the case reports → [[01.05 Dates and Epidemiological Weeks]]
- [ ] Merge cases, weather and population into `data/processed/weekly.csv` → [[02.01 Pandas Merge, GroupBy, Resample and Shift]]
- [ ] Check data quality: missing weeks, duplicates, name spellings, impossible values → [[02.02 Data Quality Checks and Missing Data]]
- [ ] Flag unusual periods (2017, 2020–21) with a column
- [ ] Explore in `notebooks/02_eda.ipynb`: cases over time, the seasonal shape → [[02.03 Time-Series Basics - Trend, Seasonality and Autocorrelation]]
- [ ] Correlate cases with rainfall, temperature and humidity at lags 0 to 16 weeks, per district → [[02.04 Cross-Correlation and Lag Analysis]]
- [ ] Write 3 to 5 findings in plain language at the end of the notebook

## Done when
- [ ] `weekly.csv` has no duplicate rows, and its missing values are documented
- [ ] Unusual periods are flagged
- [ ] Lag-correlation plots exist for rainfall, temperature and humidity
- [ ] Findings are written in the notebook

---
## Work log
Step sections are added here as each step is finished.
