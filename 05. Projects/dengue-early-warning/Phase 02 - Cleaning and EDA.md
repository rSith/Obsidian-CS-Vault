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
- [x] Aggregate daily weather to weeks that match the case reports (done 2026-11-13, Step 2.2) → [[01.05 Dates and Epidemiological Weeks]]
- [x] Merge cases, weather and population into `data/processed/weekly.csv` (done 2026-11-13, Step 2.2) → [[02.01 Pandas Merge, GroupBy, Resample and Shift]]
- [x] Check data quality: missing weeks, duplicates, name spellings, impossible values (done 2026-11-13, Step 2.2: one scrambled week repaired, one missing unit-week filled, 12 incomplete weeks found for flagging) → [[02.02 Data Quality Checks and Missing Data]]
- [x] Flag unusual periods (2017, 2020–21) with a column (done 2026-11-13, Step 2.3: incomplete, COVID and epidemic weeks)
- [ ] Explore in `notebooks/02_eda.ipynb`: cases over time, the seasonal shape → [[02.03 Time-Series Basics - Trend, Seasonality and Autocorrelation]]
- [ ] Correlate cases with rainfall, temperature and humidity at lags 0 to 16 weeks, per district → [[02.04 Cross-Correlation and Lag Analysis]]
- [ ] Write 3 to 5 findings in plain language at the end of the notebook

## Done when
- [x] `weekly.csv` has no duplicate rows, and its missing values are documented (2026-11-13: none missing; repaired rows marked in `fix`)
- [x] Unusual periods are flagged (2026-11-13: three flag columns in `weekly.csv`)
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

> [!warning] Correction (2026-11-13, later)
> The factors and test above included 2026 week 14, which is scrambled in `denguedatahub` (Step 2.2). With that week left out of the overlap (`DATAHUB_ERRORS` in the code), the factors range **0.90 to 1.21** instead of 0.89 to 1.47 (Colombo 0.902, Nuwara Eliya 1.207, Kilinochchi **1.100** instead of 1.471, Vavuniya 1.136). The ratio in the small districts is steadier too (Kilinochchi 1.39, 1.16, 1.22 instead of a jump to 4.26). The held-out test now shows a clearer gain:
>
> | Correction | Weekly error (MAE) | Error in district totals |
> |---|---|---|
> | None | 9.33 | 2,590 |
> | $k = 0$ | 8.93 | 2,168 |
> | **$k = 200$** | **8.77** | **2,088 (−19 %)** |
> | $k = 500$ | 8.70 | 2,047 |

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

## Step 2.2 — Weekly weather, repairs and `weekly.csv`
> [!important] Goal
> One table, `data/processed/weekly.csv`: one row per district per week with cases on the WER scale, weather over the same days as the case week, and population. Repair what the other source can repair; check the rest.

**What was done (2026-11-13)**
- **Looked at the case weeks first.** 1,037 weeks: 1,035 of 7 days, one of 8 and one of 6 (2009), and one 2-day gap (27–28 Dec 2025, where the week start moves from Saturday to Monday). One unit-week missing (Puttalam, 2026 week 7).
- **Found a scrambled week.** In 2026 week 14 (30 Mar–5 Apr) `denguedatahub` has a believable national total (1,217) but the numbers sit in the wrong units: Colombo 9, Gampaha 10, Matale 256, Kilinochchi 117, where the Dengue Control Unit has 313, 187, 17 and 1, and the weeks either side agree with it. The `.rda` file itself holds these values, so it is not our parser.
- **Searched for others** in two ways:
  - On the 65 overlap weeks, the rank correlation across the 26 units between the two sources: median 0.96, lowest otherwise 0.81, **week 14: −0.02**.
  - On all 1,010 `denguedatahub` weeks, the rank correlation with the average of the week before and after: median 0.90, **week 14: −0.04**, every other week 0.48 or more. It is the only scrambled week in 19 years.
- **The second screen found a different problem: incomplete weeks.** 12 weeks have under half the cases of their neighbours, mostly with many units at zero (typical is 2 of 26): for example 2022-09-10, 53 cases against about 583, 23 units at zero; 2009-04-04, 2012-04-21 and 2022-04-09 fall around the April New Year holidays. Nothing to repair them from, so they will be **flagged in Step 2.3**, not changed.
- **`src/build_weekly.py` now builds `weekly.csv`:**
  1. Factors from the overlap without week 14 (correction to Step 2.1 above).
  2. `clean_cases()`: week 14 and the missing Puttalam week are filled from the Dengue Control Unit figure × factor, with `source = ndcu` and a `fix` note (27 unit-weeks).
  3. `weekly_weather()`: every day goes to the case week that contains it (`merge_asof` on the week start, then drop days after the week end). Rain is the mean daily rain × 7; temperature and humidity are means.
  4. `to_districts()`: Kalmunai is added to Ampara (cases summed, the two weather points averaged), as decided on 2026-10-11.
  5. `add_population()`: population on each week's middle day, by straight-line interpolation between the 1 July figures; `incidence` = cases per 100,000 people.
  6. `check_weekly()` stops on repeated rows, missing or negative cases, missing weather, impossible weather, or a week without every district.

**Checked (2026-11-13)**

| Check | Result |
|---|---|
| Size | 25,925 rows = 25 districts × 1,037 weeks, 23 Dec 2006 to 8 Nov 2026 |
| Repeats, missing values | None |
| Weather days per week | 7 for all but the 8- and 6-day weeks of 2009 |
| Weather by hand | Colombo 15 Jun 2019 (Saturday week) and 1 Jun 2026 (Monday week), Ampara 30 Nov 2024 (two points averaged): rain and temperature equal to the sums and means of the daily rows |
| Repaired rows | Week 14: Colombo 313 published → 282.3 WER-equivalent; Puttalam week 7: 21 → 18.9 |
| Ranges | Rain 0 to 648 mm a week; mean temperature 13.4 to 31.6 °C (Nuwara Eliya is the cold end); humidity 51–98 %; incidence up to 156 per 100,000 a week |
| Population | Changes smoothly week to week (Colombo 2,372,997 → 2,372,958 → 2,372,920 around July 2024) |
| Rows by source | `datahub` 25,274, `ndcu` 626, `wer` 25 |

**`weekly.csv` columns:** `district`, `week_start`, `week_end`, `cases` (WER-equivalent, used for modelling), `cases_reported` (as published), `source`, `fix`, `weather_days`, `rain_mm`, `temp_mean`, `temp_max`, `temp_min`, `humidity`, `population`, `incidence`.

**Why this way**
- **Weeks from the case reports, not a calendar.** The weather must cover exactly the days the cases cover, including the Saturday-to-Monday change and the odd 2009 weeks. `merge_asof` puts each day into the latest week that starts on or before it.
- **Rain per 7 days.** An 8-day week would otherwise look wetter.
- **Repair from the other source, mark it, never hide it.** The NDCU figure is the best estimate of the true value; `fix` and `source` say which rows were changed and why.
- **Repair before estimating.** The scrambled week inflated the small districts' WER totals (Kilinochchi 117 cases in one week, against about 1 normally), so the first factors were wrong. Order matters: find bad data, then fit anything on it.
- **Interpolate population.** A yearly step on 1 January would show up as a jump in incidence.

```python
days = pd.merge_asof(weather.sort_values("date"), weeks, left_on="date", right_on="week_start", direction="backward")
days = days[days["date"] <= days["week_end"]]            # 27-28 Dec 2025 belong to no week
```

```python
# a week whose units do not rank like its neighbours' is suspect
neighbours = (w.shift(1) + w.shift(-1)) / 2
r = w.loc[week].corr(neighbours.loc[week], method="spearman")
```

**Problems and fixes**

| Problem | What was tried | Fix |
|---|---|---|
| Colombo 9 and Matale 256 in 2026 week 14 | Looked for a shift between units: no pattern; the numbers match no order of the true values | The week is wrong in `denguedatahub` itself. Listed in `DATAHUB_ERRORS`, left out of the factors and replaced by the Dengue Control Unit figure × factor |
| The Step 2.1 factors and the Phase 1 district comparison included that week | — | Recomputed without it; dated corrections added under both |
| Printing rows where `fix != ""` listed every row | — | An empty text is read back from CSV as missing (NaN); filter with `fix.notna()` |

> [!tip] What to remember
> - A believable total can hide wrong parts. Check data at the level you will use it.
> - Rank correlation with neighbouring weeks is a cheap way to find a week whose numbers are in the wrong rows.
> - Find and repair bad data **before** fitting anything on it; one bad week changed four factors.
> - Build weekly weather on the case weeks themselves, not on calendar weeks.

**Check yourself**
1. How was 2026 week 14 found, and why did the national total not show it?
2. Why is rainfall scaled to 7 days but not left as a plain sum?
3. Why are the 12 incomplete weeks flagged rather than repaired like week 14?

> [!success]- Answers
> 1. Its 26 units rank nothing like the weeks either side (correlation −0.04) or like the Dengue Control Unit for the same week (−0.02). The values were in the wrong units, so their sum was still about right.
> 2. Two 2009 weeks are 8 and 6 days long; a plain sum would make them look wetter or drier than they were. Mean daily rain × 7 keeps every week on the same scale.
> 3. Week 14 had a second source for the same week; the incomplete weeks (2009–2023) do not. Guessing them would invent data, so they are kept and flagged for the model to handle.

## Step 2.3 — Flag unusual weeks
> [!important] Goal
> Mark the weeks that are not normal (reporting gaps, the COVID-19 period, epidemics) with flag columns in `weekly.csv`, by rules worked out from the data, without removing or changing any value.

**What was done (2026-11-13)**
- Looked at the national series against a "usual" level: the median incidence for the same week of the year (1–52) over the previous 5 years.
- Chose three rules and added `add_flags()` to `src/build_weekly.py`. Each flag is national (the same for all 25 districts in a week):

| Flag | Rule | Weeks |
|---|---|---|
| `flag_incomplete` | National cases under half the mean of the week before and after | 12 |
| `flag_covid` | Weeks that touch 20 Mar 2020 (first island-wide curfew) to 31 Dec 2021 | 94 (14 Mar 2020 – 25 Dec 2021) |
| `flag_epidemic` | National incidence over 3 × the usual for that week of the year | 134 |

**What the data showed**
- **COVID-19.** Against the 2015–2019 normal, monthly cases fell from 1.30 × in Feb 2020 to **0.37 × in Mar 2020** and stayed at **0.18–0.65 ×** every month to Dec 2021 (lowest Apr 2020 and Aug 2021, the lockdowns). 2022 recovers only partly (0.44–1.09 ×; the fuel crisis of mid-2022 may play a part), so it is not flagged; the EDA should keep an eye on it.
- **Epidemic threshold.** 2 × the usual flags 267 weeks and 2.5 × flags 180, too many to call unusual: cases have risen over the years, so a 5-year baseline is often outrun. **3 ×** picks out the known epidemic years: 2017 (44 weeks), 2019 (15), 2023 (8), 2026 (14), and 2009–2012, which were large rises after the low years 2007–2008 (about 6,000 cases a year).
- **Incomplete weeks:** 2009-04-04, 2011-04-09, 2011-09-03, 2011-11-05, 2012-03-17, 2012-04-21, 2012-04-28, 2012-07-14, 2016-06-04, 2022-04-09, 2022-09-10 (53 cases against about 583), 2023-11-04. Four sit around the April New Year.
- No week has two flags.

**Why this way**
- **Flag, do not delete.** The roadmap asks for it, and Phase 4 can then choose: leave flagged weeks out of training, weight them down, or check the model on them separately.
- **Rules from the data, written in code.** The same rule runs again on new weeks; a list of dates typed by hand would not.
- **National flags.** A reporting gap or a curfew affects the whole country. District-level "outbreaks" are what the alerts are for (Phase 5), so they are not flagged here.
- **Not model inputs.** `flag_incomplete` uses the following week, so it is not known at forecast time → [[03.03 Data Leakage]]. `flag_epidemic` uses only past years and this week, but it is close to the alert the model is meant to predict.

```python
by_year  = nat.pivot_table(index="year", columns="season_week", values="incidence")
baseline = by_year.shift(1).rolling(5, min_periods=3).median()   # previous 5 years only
flag_epidemic = incidence > 3.0 * usual
```

> [!tip] What to remember
> - Mark odd periods with a column; deleting them hides them from everyone who uses the table later.
> - Compare against "the same week in other years", not against the yearly average: dengue is seasonal.
> - Choose a threshold by looking at how many weeks it flags; a flag that fires a quarter of the time is not marking anything unusual.

**Check yourself**
1. Why is 2022 not flagged as part of the COVID period?
2. Why does the epidemic rule use only previous years for its baseline?
3. Can `flag_incomplete` be used as a model input?

> [!success]- Answers
> 1. From 2022 the case level is mixed (0.44–1.09 × normal) rather than steadily low, so a fixed end on 31 Dec 2021 matches the data better. 2022 stays visible for the EDA.
> 2. So the flag for a week depends only on what was known by then. It can run on live data, and an epidemic year cannot raise its own baseline.
> 3. No. It compares the week with the one after it, which is not known when the forecast is made.
