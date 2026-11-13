---
type: project-note
project: Dengue Early-Warning
status: draft
tags: [project, ml]
---
# Phase 02 — Cleaning and EDA
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 6–8, target finish 2026-12-06
> **Study notes:** [[02.00 Cleaning and EDA]] · **Revision:** [[02.99 Summary - Cleaning and EDA]]

> [!abstract] Phase goal
> One clean weekly table (district × week), and evidence of how weather relates to cases, especially at which time lags.

## Steps
- [x] Correct the change of case source on 18 May 2026, per district (added 2026-11-13, Step 2.1)
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

## Step 2.1 — Put the Dengue Control Unit weeks on the WER scale
> [!important] Goal
> From 18 May 2026 the case counts come from the Dengue Control Unit, which counts districts differently from the WER that the 19-year history comes from ([[Phase 01 - Data Collection]], Step 1.4, Part 3). Turn those counts into WER-equivalent counts, district by district, before anything else uses them.

**What was done (2026-11-13)**
- Started `src/build_weekly.py`, the script that will build `weekly.csv`. This first part:
  - `overlap()` matches the weeks both sources cover: 65 weeks (2025 weeks 1–45 and 2026 weeks 1–20). Up to Dec 2025 the WER week starts on Saturday, so it is matched to the Monday 2 days later (the two weeks share 5 of 7 days).
  - `source_factors()` gives each unit a factor $f$ that turns a Dengue Control Unit count into a WER-equivalent one. Saved to `data/processed/source_factors.csv`.
  - `adjust_cases()` adds `cases_adjusted` = `cases` × $f$ on Dengue Control Unit rows only. `cases` stays as published.
- Checked first that a fixed factor makes sense: is each district's ratio stable over time?

**Is the ratio stable?** WER ÷ NDCU in three periods:

| | 2025 Jan–Jun | 2025 Jul–Dec | 2026 Jan–May |
|---|---|---|---|
| National | 0.98 | 1.01 | 0.97 |
| Colombo | 0.91 | 0.91 | 0.85 |
| Matara | 0.98 | 1.00 | 1.02 |
| Gampaha | 1.00 | 1.03 | **0.84** |
| Kilinochchi | 1.39 | 1.16 | **4.26** |
| Nuwara Eliya | 1.27 | 1.24 | **2.52** |

The 18 districts with more than 1,000 cases move by at most 0.2 and sit close to 1. The 8 small ones jump around, because their ratios rest on a few hundred cases.

**The factor: a ratio with shrinkage**

$$f = \frac{W + k}{N + k}, \qquad k = 200$$

$W$ and $N$ are the district's WER and Dengue Control Unit totals over the overlap. $k$ adds 200 pretend cases to both sides, as if both systems had counted them alike, which pulls the factor towards 1 when the district has little data. Large districts keep their own ratio; small ones move only part of the way.

| District | $W$ | $N$ | Raw ratio | Factor |
|---|---|---|---|---|
| Colombo | 16,912 | 19,072 | 0.887 | **0.888** |
| Matale | 1,828 | 1,417 | 1.290 | 1.254 |
| Nuwara Eliya | 583 | 356 | 1.638 | 1.408 |
| Kilinochchi | 259 | 112 | 2.312 | **1.471** |
| Mullaitivu | 94 | 43 | 2.186 | 1.210 |

All factors: 0.89 to 1.47. The Dengue Control Unit weeks drop from 76,079 published cases to 74,594 WER-equivalent (−2 %).

**Does it help? A fair test.** Factors estimated from 2025 only, applied to 2026 weeks 1–20, compared with the real WER counts for those weeks:

| Correction | Weekly error per district (MAE) | Error in district totals |
|---|---|---|
| None | 11.81 cases | 3,527 |
| Raw ratio ($k = 0$) | 11.39 | 3,146 |
| $k = 50$ | 11.31 | 3,167 |
| **$k = 200$** | **11.23** | 3,164 |
| $k = 500$ | 11.17 | 3,138 |

The correction removes about 10 % of the error in district totals and 5 % of the weekly error. The choice of $k$ hardly matters. **Most of the gap is week-to-week noise that no single factor can remove**: the two systems do not record the same cases in the same week. The largest remaining error is Gampaha, whose ratio moved from about 1.0 in 2025 to 0.84 in 2026.

**Why this way**
- **Correct the scale, keep the published number.** `cases` is untouched; `cases_adjusted` is a separate column, so the correction can be removed or redone.
- **Shrinkage instead of the raw ratio.** A raw ratio of 2.3 for Kilinochchi would multiply every future week by 2.3 on the evidence of 112 cases. Shrinkage costs nothing for large districts and protects the small ones. It is the same idea as a Bayesian prior worth $k$ cases.
- **Test on weeks the factor never saw.** Checking the factor on the weeks it was estimated from would always look good.
- **This matters most for the live forecast.** In training, the new weeks are about 2.5 % of the history. In the live system, the latest weeks the model reads will all be Dengue Control Unit weeks.

```python
factor = (W + PRIOR_CASES) / (N + PRIOR_CASES)          # PRIOR_CASES = 200
factor = cases["unit"].map(factors["factor"]).where(cases["source"] == "ndcu", 1.0)
cases_adjusted = cases["cases"] * factor
```

**For later phases**
- Keep `source` in `weekly.csv`, so the model can still tell the two systems apart.
- **Leakage check for Phase 4** → [[03.03 Data Leakage]]: the factors use 2026 weeks 1–20. If the final test period includes those weeks, estimate the factors from 2025 only for the final evaluation.

> [!tip] What to remember
> - Before correcting with one number, check the number is stable over time; if it is not, a single factor is mostly noise.
> - Shrinkage, $(W + k)/(N + k)$, is a simple way to trust a ratio only as far as its data allows.
> - Test a correction on data it was not fitted on, and compare with doing nothing.

**Check yourself**
1. Why is Kilinochchi's factor 1.47 and not its raw ratio of 2.31?
2. The correction cut the weekly error by only 5 %. Why not more?
3. Why does the table keep `cases` as well as `cases_adjusted`?

> [!success]- Answers
> 1. Its ratio rests on 259 and 112 cases and jumped from 1.16 to 4.26 between periods. With $k = 200$, $(259 + 200)/(112 + 200) = 1.47$: the data move the factor away from 1, but only as far as they are worth.
> 2. Most of the weekly difference is noise: the two systems put different cases in different weeks. A scale factor fixes a steady over- or under-count, not random week-to-week differences.
> 3. `cases` is the published number. Keeping it means the correction can be checked, removed or redone with other factors without downloading anything.
