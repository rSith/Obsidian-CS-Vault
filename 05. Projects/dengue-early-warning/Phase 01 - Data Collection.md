---
type: project-note
project: Dengue Early-Warning
status: draft
tags: [project, ml]
---
# Phase 01 — Data Collection
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 3–5, target finish 2026-11-15
> **Study notes:** [[01.00 Data Collection]] · **Revision:** [[01.99 Summary - Data Collection]]

> [!abstract] Phase goal
> At least 8 years of weekly dengue cases and matching weather for the chosen districts, saved as raw files, plus a script that downloads them again from scratch.

## Steps
- [x] Find the case data: which sources, which format (PDF, web table, file), how far back (done 2026-10-11, Step 1.1)
- [ ] Write the case collector `src/collect_cases.py` → [[01.03 Web Scraping with BeautifulSoup and read_html]] · [[01.04 PDF Table Extraction with pdfplumber]]
- [x] Write the weather collector `src/collect_weather.py` (done 2026-10-23, Step 1.3) → [[01.01 HTTP Requests and REST APIs in Python]] · [[01.02 Parsing JSON into Tables]]
- [ ] Collect population per district
- [ ] Write the data dictionary in `docs/data.md`
- [ ] Cache every download and wait between requests

> [!warning] Carried over from the Phase 0 data check
> These are not in the roadmap. They came out of checking the real sources, and they affect Phase 3.
> - [ ] Measure how late each case source publishes → [[01.06 Reporting Delay and Data Availability]]
> - [x] Choose the source that will feed the live system (decided 2026-10-11: Dengue Control Unit update, WER as fallback)
> - [x] Decide whether to collect all districts now (decided 2026-10-11: yes, all 26 units)
> - [x] Decide how the 26 reporting units map to 25 districts (decided 2026-10-11: keep 26 in the raw file, add Kalmunai to Ampara in Phase 2)
> - [ ] Confirm the week definition used in the reports → [[01.05 Dates and Epidemiological Weeks]]

## Done when
- [ ] `cases_raw.csv` covers the chosen districts for 8 or more years
- [x] Daily weather is downloaded for the same districts and years (2026-10-23: 26 units, 2006-01-01 to 2026-10-22)
- [ ] The population table is saved
- [ ] Both collector scripts re-run from scratch with no manual steps
- [ ] The data dictionary is written

---
## Work log
Step sections are added here as each step is finished.

---
## Step 1.1 — Inspect every data source by hand
> [!important] Goal
> Before writing any collector, know for each source its format, the dates it covers, how late it is, how it spells the district names and what its licence allows.

**What was done (2026-10-11)**
- Opened all six sources and wrote down the same five facts for each. The raw notes are in the repository at `docs/data/inspected_data.md`. The cleaned summary is the Sources table in `docs/data.md`.
- Saved two WER issues to compare layouts: Vol. 46 No. 52 (December 2019) and Vol. 53 No. 34 (August 2026).

| Source | Format | Covers | Behind on 11 Oct | Licence |
|---|---|---|---|---|
| WER (Epidemiology Unit) | One PDF per week, about 1,020 files | Dec 2006 → 16 Aug 2026 | 8 weeks | All rights reserved, acknowledge the source |
| Dengue Control Unit weekly update | One PDF per week, no archive page | 2026 weeks 19–37, four weeks missing | 4 weeks | None stated |
| `denguedatahub` | One R data file (`.rda`) | 23 Dec 2006 → 17 May 2026 | 21 weeks | GPL-3 |
| Open-Meteo | JSON API | 1940 → today | 5 days for final values | CC BY 4.0 |
| NASA POWER | JSON API | 1981 → 3 days ago | 3 days | Attribution required |
| Census and Statistics | One-page PDFs | 2014–2026, yearly | — | All rights reserved |

**What the two WER PDFs showed**
The dengue table is on page 3, rotated 90°, and is real text. Each row is a unit name followed by pairs of numbers: `A` is the cases for the week and `B` is the running total for the year. Dengue is the first pair.

```text
2019:  Colombo 661 19567 0 60 1 14 ...      (12 diseases, name written "Kalmune")
2026:  Colombo 584 18464 0 17 0 2 ...       (14 diseases, name written "Kalmunai")
```

**Problems and fixes**
Three things written down earlier turned out to be wrong once the PDFs were open.

| Earlier belief | What the PDFs show | Effect |
|---|---|---|
| WER weeks run Saturday to Friday | True in 2019 (14–20 Dec 2019). In 2026 they run **Monday to Sunday** (10–16 Aug 2026). | One resample rule cannot serve every year. Join weather to cases by the week's start and end dates. |
| The newest WER week was 15–21 Aug, 7 weeks behind | The newest issue is dated 17–23 Aug but reports **10–16 Aug**. An issue always reports the week before its own date. That is **8 weeks** behind. | The case delay for the WER is 8, not 7. |
| The WER archive starts at 2019 | It goes back to Vol. 34 (December 2006). | 19 years of history exist as PDFs. |

The first inspection also answered "does the layout change between years?" with "no". Comparing the two saved issues shows it does: two diseases were added, the table title changed and two unit names are spelled differently. Dengue stayed the first column, which is what the parser will rely on.

**Why this way**
- **Inspect by hand before coding.** The source decides the collector. Reading one ready-made file from 2006 is a small job. Extracting tables from 1,020 PDFs is a large one. Without the inspection the large job would have been started by default.
- **Record the same five facts for every source.** A fixed list makes sources comparable and shows gaps, such as the missing licence on the Dengue Control Unit site.
- **Save two issues that are years apart.** One file cannot show whether a layout is stable.

**Decisions (confirmed 2026-10-11)**
- History: `denguedatahub` for Dec 2006 to May 2026, after spot-checking some weeks against WER PDFs.
- From May 2026 onwards: parse WER PDFs (weeks 21 to 33 so far).
- Live system: Dengue Control Unit update first because it is 4 weeks faster, WER as the fallback.
- Weather: Open-Meteo, because its grid is finer than NASA POWER's 50 km cells and its licence is clear.
- Units: keep all 26 in the raw file; add Kalmunai to Ampara in Phase 2, because population exists only for Ampara.

> [!tip] What to remember
> - An issue's date and the week it reports are different. Read the date above the table, not the date on the cover.
> - "Does the layout change?" can only be answered by putting two files side by side.
> - The week definition changed at some point between December 2025 and May 2026. Always store `week_start` and never trust a week number alone → [[01.05 Dates and Epidemiological Weeks]].
> - Three sources, three spellings: `Nuwara Eliya`, `NuwaraEliya`, `Nuwara-eliya`. A name lookup table is needed before any merge.

**Check yourself**
1. The WER issue is dated 17–23 August and today is 11 October. Why is the data 8 weeks behind and not 7?
2. Weather is daily. Why can you not turn it into weeks with one `resample("W-FRI")` for the whole history?
3. The 2026 WER table has two more diseases than the 2019 table. Why does a parser that reads "the first two numbers after the name" still work?

> [!success]- Answers
> 1. The issue reports the week before its own date, 10–16 August. From 16 August to 11 October is 56 days, which is 8 weeks.
> 2. `W-FRI` makes Saturday-to-Friday weeks. That matches the reports up to 2025, but the 2026 reports use Monday-to-Sunday weeks. From 2026 the weather weeks would cover different days from the case weeks.
> 3. The new diseases were added on the right. Dengue is still the first pair of numbers, so its position counted from the name did not move.

---
## Step 1.2 — District coordinates table
> [!important] Goal
> One small table that gives every reporting unit a name, the district it belongs to and a point on the map. The weather collector needs the points; every later merge needs the names.

**What was done (2026-10-11)**
- Created `data/reference/districts.csv` with 26 rows and four columns: `unit`, `district`, `latitude`, `longitude`.
- The rows are in the order of the WER table and use the spelling of the 2026 WER.
- Looked up each point with the Open-Meteo geocoding API and took the first result inside Sri Lanka.
- Recorded the columns in the data dictionary in `docs/data.md`.

**Checked:** pandas reads 26 rows, 26 different units and 25 different districts. Kalmunai is the only row where `unit` and `district` differ. Latitudes run from 5.95 to 9.67 and longitudes from 79.83 to 81.83, which is inside Sri Lanka. Git does not ignore the file.

**Why this way**
- **The point is the main town, not the centre of the district.** Dengue spreads where people live. In a large district such as Anuradhapura or Monaragala the middle of the map is thinly populated land far from the town, and its rainfall is not the rainfall the patients lived in.
- **Two name columns.** `unit` is what the health reports use (26 values). `district` is what the map and the population table use (25 values). Keeping both in one table means the Kalmunai decision is written down once, as data, and Phase 2 can add Kalmunai to Ampara with a `groupby("district")`.
- **A new folder, `data/reference/`.** The table is made by hand and no script can recreate it, so it has to be committed. `data/raw/` and `data/processed/` are both ignored by Git, so it could not go there.
- **Four decimal places.** That is about 11 metres, far finer than the weather grid, which is several kilometres wide. More digits would add nothing.

```text
https://geocoding-api.open-meteo.com/v1/search?name=Kalmunai&count=5&countryCode=LK
```

**Problems and fixes**
A place name is not unique. The search returned three places called Kalmunai (in Ampara, Kilinochchi and Puttalam districts), three called Gampaha and two called Badulla. The first result was the right one each time, because results are ordered by population, but each one was checked against the district name in the result before it was used.

> [!tip] What to remember
> - Always pass `countryCode=LK` and read the district of the result. The same town name exists in several districts.
> - A lookup table made by hand belongs in Git. Data that a script downloads does not.
> - The weather for a unit is the weather at one point. It stands in for the whole district, which is an approximation to mention in the report.

**Check yourself**
1. Why is the main town a better weather point than the geometric centre of a district?
2. The table has 26 rows but only 25 different values in `district`. Why?
3. Why is this file committed when the files in `data/raw/` are not?

> [!success]- Answers
> 1. Cases happen where people live. The centre of a large district can be forest or farmland with different rainfall from the town where most patients are.
> 2. Kalmunai is a health reporting unit inside Ampara district, so its `district` value is Ampara, the same as the Ampara row.
> 3. It was made by hand, so no script can rebuild it. Raw downloads can be fetched again by the collector, so they stay out of Git.

---
## Step 1.3 — Weather collector
> [!important] Goal
> A script that downloads daily rainfall, temperature and humidity for all 26 units from 2006 to today, and can be run again at any time without downloading the same thing twice.

**What was done (2026-10-11 and 2026-10-23)**
- Chose Open-Meteo over NASA POWER (decision in Step 1.1).
- **Explored in a notebook first**, `notebooks/01_weather.ipynb`: one request for Colombo, 2024, then the reply turned into a table. No missing values in any of the five variables.
- **Wrote `src/collect_weather.py`** in two parts: a download part that saves one raw JSON file per unit and year in `data/raw/weather/`, and a build part that reads those files into `data/processed/weather_daily.csv`.
- **First full run:** started 20:56 and stopped at 21:09 with a `429` error, after saving 388 of 546 files (18 units complete, and Puttalam up to 2015).
- **Second run (2026-10-23, 23:11 to 23:17):** the same command, run again from the project root. It made 176 requests and none failed: the 158 missing files, plus the 18 files for 2026 that were already there, downloaded again because the year is not over. The 370 finished past-year files were skipped. Then the script built `data/processed/weather_daily.csv`.
- **Added the six weather columns** to the data dictionary in `docs/data.md`.

**Checked (2026-10-23)**

| Check | Result |
|---|---|
| Raw files | 546 of 546 (26 units × 21 years) |
| Table size | 197,600 rows × 7 columns |
| Dates | 2006-01-01 to 2026-10-22 (yesterday) for every unit |
| Rows per unit | 7,600 for every unit, the exact number of days in that range, so no day is missing |
| Duplicate unit-date rows | 0 |
| Missing values | 0 in every column |
| Ranges | rain 0–322 mm per day, mean temperature 11.9–32.8 °C, humidity 33–100 % |

The extremes make sense. The coldest unit is Nuwara Eliya (mean 16.9 °C), the hill station, followed by Badulla and Kandy; all others are about 25–27 °C. The wettest day is Kilinochchi on 2021-01-11 with 322 mm, during the north-east monsoon.

**Why this way**
- **Notebook for exploring, `.py` file for the real code.** In a notebook the reply stays in memory, so it can be examined cell by cell without calling the API again. But a notebook cannot be imported by the weekly pipeline or tested with pytest, so the collector lives in `src/`.
- **One file per unit and year.** If a request fails, only one year is lost, and a finished year never has to be fetched again.
- **Download and build are separate.** A mistake in the table code is fixed and re-run without using the network.
- **Start in 2006.** Cases start in late December 2006, and Phase 2 looks at rainfall up to 16 weeks earlier, so the weather has to begin before the cases do.
- **The current year is always downloaded again.** It is still growing, and its last 5 days are provisional values that Open-Meteo replaces later.
- The whole script is explained part by part in [[01.07 Resumable Downloads - Caching and Rate Limits]].

```python
r = requests.get(URL, params=params, timeout=60)
r.raise_for_status()                    # an error reply becomes a Python error
df = pd.DataFrame(r.json()["daily"])    # (366, 6) for Colombo 2024
```

```powershell
python -m src.collect_weather           # from the project root, with .venv active
```

**Problems and fixes**

| Problem | Cause | Fix |
|---|---|---|
| VS Code asked which kernel to use for the notebook | A notebook needs a Python to run its cells | Choose **Python Environments...**, then `.venv (Python 3.13.2)`. The global Python does not have `pandas` or `requests`. |
| `df.shape` printed nothing | A notebook shows only the value of the **last line** of a cell | Use `print(df.shape)`, or give it its own cell |
| `429 Too Many Requests` at request 388 | Open-Meteo's hourly limit. A long request counts as several calls: about 13 for one year of five variables, by estimate from this run. | Wait about an hour and run the same command again. The 370 finished past-year files are skipped. |
| The module docstring disappeared from the top of the file | It was overwritten while pasting the code | Put back above the imports |

> [!tip] What to remember
> - `raise_for_status()` did its job: without it, the script would have saved 158 error messages as if they were weather files.
> - A `429` is not a bug. The request was fine; the server asked for a pause.
> - Save each reply the moment it arrives. That is what made the failed run cost only waiting time.
> - The cache knows only the unit and the year. After changing the variables or a coordinate, delete the old files.
> - A year counts as final when its file was saved after 7 January of the next year. That is why a 2025 file saved in October 2026 is kept, and a 2026 file is fetched again on every run.
> - Run the script from the project root. Its paths (`data/raw/weather`) are relative to the folder you run it from, and `python -m src.collect_weather` finds `src` only there.

**Check yourself**
1. The first run failed at file 388. Why does the second run not start again at Colombo 2006?
2. Why is `Colombo_2026.json` downloaded on every run, while `Colombo_2024.json` is downloaded once?
3. You add wind speed to `DAILY` and run the script. The new column is missing for almost every year. Why?

> [!success]- Answers
> 1. Each reply was saved as its own file as soon as it arrived. Before every request the script checks for the file, finds it, and skips the request.
> 2. 2024 is over and its numbers are final. 2026 gets a new day every day, and its newest days are provisional.
> 3. The files for past years already exist, so the script did not ask the server again. The saved files hold the old list of variables. Delete `data/raw/weather/` and download again.
