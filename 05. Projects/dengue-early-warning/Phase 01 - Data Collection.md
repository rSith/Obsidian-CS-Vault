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
- [ ] Write the case collector `src/collect_cases.py` → [[01.03 Web Scraping with BeautifulSoup and read_html]] · [[01.04 PDF Table Extraction with pdfplumber]] (in progress, Step 1.4: `denguedatahub` part done 2026-10-28; WER parser and downloader written 2026-10-29, download waiting on the site)
- [x] Write the weather collector `src/collect_weather.py` (done 2026-10-23, Step 1.3) → [[01.01 HTTP Requests and REST APIs in Python]] · [[01.02 Parsing JSON into Tables]]
- [ ] Collect population per district → [[01.08 Population Denominators and Incidence Rates]] · [[01.04 PDF Table Extraction with pdfplumber]] (in progress, Step 1.5: sources found and `src/collect_population.py` downloads and parses both tables, 2026-10-29)
- [ ] Write the data dictionary in `docs/data.md`
- [ ] Cache every download and wait between requests

> [!warning] Carried over from the Phase 0 data check
> These are not in the roadmap. They came out of checking the real sources, and they affect Phase 3.
> - [ ] Measure how late each case source publishes → [[01.06 Reporting Delay and Data Availability]]
> - [x] Choose the source that will feed the live system (decided 2026-10-11: Dengue Control Unit update, WER as fallback)
> - [x] Decide whether to collect all districts now (decided 2026-10-11: yes, all 26 units)
> - [x] Decide how the 26 reporting units map to 25 districts (decided 2026-10-11: keep 26 in the raw file, add Kalmunai to Ampara in Phase 2)
> - [ ] Confirm the week definition used in the reports → [[01.05 Dates and Epidemiological Weeks]] (2026-10-28: `denguedatahub` agrees with the WER PDFs: Saturday starts up to 2025, Monday starts from 2025-12-29. See Step 1.4.)

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

---
## Step 1.4 — Case collector (in progress)
> [!important] Goal
> A script that downloads the weekly case counts and saves them as one table, `cases_raw.csv`, with the columns `unit, year, week, week_start, week_end, cases`. Part 1 covers the `denguedatahub` file (Dec 2006 to May 2026). Part 2, still to do, parses WER PDFs for 2026 weeks 21 onwards.

**What was done (2026-10-28)**
- **Download part.** `download_datahub()` in `src/collect_cases.py` saves the `.rda` file from the `denguedatahub` GitHub repository to `data/raw/cases/` once and skips it on later runs.
- **Explored in a notebook**, `notebooks/01_cases.ipynb`. Read the file with `pyreadr` (added to `requirements.txt`) and checked the column types, missing values and the number of weeks per unit.
- **Build part.** `build_table()` turns the R table into `data/processed/cases_raw.csv`: unit names renamed to match `districts.csv`, `year`, `week` and `cases` as integers, the two date strings parsed into `week_start` and `week_end`.
- Set `jupyter.notebookFileRoot` to the workspace folder in `.vscode/settings.json`, so the notebook runs from the project root and the `data/...` paths work.

**Checked (2026-10-28)**

| Check | Result |
|---|---|
| Table size | 26,311 rows × 6 columns, 26 units |
| Dates | 2006-12-23 to 2026-05-17, over 19 years |
| Missing values | 0 in every column |
| Duplicate unit-week rows | 0 |
| Weeks per unit | 1,012 for every unit except Puttalam (1,011) |
| Cases | 0 to 2,631; the highest is Gampaha, 2017 week 28, the 2017 outbreak |
| Unknown unit names after renaming | none |

**Problems in the source data**
These were found and written down, not fixed. `cases_raw.csv` keeps the data exactly as published; Phase 2 cleaning deals with them.

| Problem | Detail |
|---|---|
| Puttalam is missing one week | 2026 week 7, 2026-02-09 to 2026-02-15. Every other unit has it. Fill it in Phase 2, or take it from that WER issue. |
| Week start changes | Saturday starts up to 2025, Monday starts from 2025-12-29, the same change seen in the WER PDFs in Step 1.1. |
| Two days in no week | 2025-12-27 and 2025-12-28 fall between the last Saturday week and the first Monday week. |
| Wrong year label | The week 2025-12-20 to 2025-12-26 is labelled `year 2026, week 53`. It is 2025 week 53. Sorted by `year, week`, it would land at the end of 2026. |
| Odd week lengths in 2009 | Week 17 runs 2009-04-18 to 2009-04-25 (8 days), week 22 runs 2009-05-24 to 2009-05-29 (6 days), and the weeks in between start on Sunday. Probably a typing slip in the source. |
| Dates not zero-padded | `2/9/2026`, not `02/09/2026`. `format="%m/%d/%Y"` still reads them. |

**Why this way**
- **The raw table keeps the source's mistakes.** If `cases_raw.csv` silently fixed the year label, nobody could later tell what the source said. Raw stays raw; every fix happens in one visible place in Phase 2.
- **`week_start` is the key, not `year` and `week`.** The mislabelled week 53 and the 2009 dates show that week numbers cannot be trusted. Sorting and joining to weather by date avoids both problems.
- **Rename to the `districts.csv` spelling now.** Every later merge (weather, population, map) goes through `unit`. One spelling everywhere means the joins just work.
- **Stop on unknown names.** If a new copy of the file spells a unit differently, the script raises an error instead of quietly leaving that unit without weather in Phase 2.
- **Download and build are separate**, the same as in the weather collector: the build code can be fixed and re-run without the network.

```python
UNIT_NAMES = {"NuwaraEliya": "Nuwara Eliya", "Hambanthota": "Hambantota", "Kalmune": "Kalmunai"}
df = pyreadr.read_r(DATAHUB_FILE)["srilanka_weekly_data"]   # a dict of R objects, by name
"week_start": pd.to_datetime(df["start.date"], format="%m/%d/%Y"),
```

```python
# which week is Puttalam missing?
all_weeks = set(df["start.date"])
puttalam = set(df.loc[df["district"] == "Puttalam", "start.date"])
all_weeks - puttalam          # {'2/9/2026'}
```

```powershell
python -m src.collect_cases          # from the project root, with .venv active
```

**Still to do in this step**
- Parse the WER PDFs for 2026 weeks 21 onwards with `pdfplumber` and append them → [[01.04 PDF Table Extraction with pdfplumber]]. (2026-10-29: parser and downloader written, download waiting on the site. See Part 2 below.)
- A second copy of the `.rda` sits loose in `data/raw/` from an earlier download; the script uses `data/raw/cases/`. Delete the loose copy. (Gone, checked 2026-10-29.)

> [!tip] What to remember
> - `value_counts()` on the unit column is a quick completeness check: every unit should have the same number of weeks. The one that is short is where to look.
> - Set difference (`set_a - set_b`) finds exactly which rows are missing.
> - Check the length of every week and the gap to the next one. This found the 2009 slip and the 2-day hole that no missing-value check could find, because no value was missing.
> - `float64` for counts usually means the data came from R or had missing values at some point. Convert to `int` only after checking there are no `NaN`s, or the conversion fails.

**Check yourself**
1. Why does `cases_raw.csv` keep the wrong `year 2026, week 53` label instead of fixing it?
2. `df.isna().sum()` showed zero everywhere. How was the missing Puttalam week still found?
3. Why do you sort by `week_start` and not by `year` and `week`?

> [!success]- Answers
> 1. Raw data records what the source published. Fixing it silently would hide the problem; Phase 2 fixes it in code that can be read and re-run.
> 2. A missing week is a missing row, not a missing value. Counting rows per unit showed Puttalam one short, and a set difference of the start dates named the week.
> 3. The year label is wrong for one week, so sorting by `year, week` puts a December 2025 count at the end of 2026. The start date is always correct.

### Part 2 — WER PDFs (in progress)
> [!important] Goal
> Add 2026 week 21 onwards, which `denguedatahub` does not have yet, by downloading each WER issue and reading the dengue column from its table.

**What was done (2026-10-29)**
- **Parser**, `parse_wer()` (commit `80ebbf1`). It finds the page whose text has a line starting `Table` that mentions "Medical Officers of Health", rebuilds the sideways table with `sideways_lines()`, reads the end date and week number from the title, and keeps the first two numbers after each unit name (dengue this week, dengue year to date). Tested on the two saved issues.
- **Already wired in.** `build_table()` calls `wer_weeks(after=<last denguedatahub week_start>)`, so only weeks newer than the `.rda` file are appended.
- **Downloader**, `list_wer_issues()` and `download_wer(after)`. The archive page lists every issue as a card with its week number, its own date range (`2025.01.25 - 2025.01.31`) and a Download link. The script reads the cards with BeautifulSoup, keeps issues dated after the last `denguedatahub` week, and saves each PDF once, waiting 2 seconds between files. `beautifulsoup4` uncommented in `requirements.txt`.
- **Small restructure.** `read_datahub()` was split out of `build_table()`, because the WER download needs the last `denguedatahub` date before the table is built.
- **Not yet downloaded.** The site was down all session (see Problems), so `cases_raw.csv` currently has 2026 weeks up to 20 from `denguedatahub`, then only week 33 from the one saved 2026 issue. Weeks 21 to 32 are a gap until the download runs.

**Why this way**
- **Read the date on the card, not the file name.** File names are not consistent: old ones look like `Vol 38 NO 01 English.pdf`, new ones like `en_67d929c3161cb_Vol_52_no_05-english.pdf` with a random code, and one is just `en_63ec87e11b430_WER.pdf`. All 968 cards in the archived page had the same week line and date line, so the date is the reliable key.
- **One overlap week on purpose.** An issue reports the week *before* its own date. Keeping issues dated after the last `denguedatahub` week start means the first issue saved repeats that last week. `wer_weeks()` drops it from the table, but the PDF is there to compare by hand with `denguedatahub`.
- **A failed download does not stop the build.** If the site is down, the script prints a warning and builds from the PDFs already saved. The live pipeline in Phase 8 needs this: a bad day on the server should give a slightly older table, not no table.
- **Honest User-Agent.** The request names the project and links to the repository, so the site owner can see who is downloading and why.

```python
for card in soup.select("li.product"):
    dates = WER_ISSUE_DATES.search(card.get_text())   # "2025.01.25 - 2025.01.31"
    link = card.find("a", href=True)
```

```python
HEADERS = {"User-Agent": "dengue-early-warning/0.1 (+https://github.com/rSith/dengue-early-warning)"}
```

**Problems and fixes**
- **509 Bandwidth Limit Exceeded.** The Epidemiology Unit's host ran out of bandwidth, so every page returned 509 (later 500). Nothing to fix on our side. To write the parser anyway, the page structure was read from a Wayback Machine copy (`web.archive.org/web/2026/https://www.epid.gov.lk/weekly-epidemiological-report`, snapshot of 2025-10-15). `list_wer_issues()` was tested on that copy: 968 issues, 2006-12-30 to 2025-08-02. There is no 2026 snapshot.
- **403 Forbidden from Python only.** curl got 500, but `requests` got 403. Testing with curl showed that the server blocks the User-Agent `python-requests/...` but not other names. Sending a User-Agent that names the project changed the 403 to the same 500 as everyone else.

**Still to do in Part 2**
- Run `python -m src.collect_cases` when the site is back. Check that issues dated 2026-05-18 onwards download, that every week has 26 units, and that weeks 21 to 33 follow each other with no gap.
- Compare the overlap week (11–17 May 2026) between the WER issue and `denguedatahub`.
- Check whether the WER issue for 2026 week 7 gives the missing Puttalam count.

**Later the same day (2026-10-29): site still down, and the fallback plan**
- Checked again with a browser User-Agent: the archive page now answers **500**. The Wayback Machine was also offline ("Temporarily Offline", then `429`), so the PDFs could not be fetched from there either.
- Worked out what is lost if the site never comes back. Very little:
  - **Training history is safe.** `denguedatahub` already gives about 19.5 years (2006-12-23 to 2026-05-17); the phase asks for 8 or more. The WER was only going to add the newest 12 weeks.
  - **The live system does not depend on the WER.** The Dengue Control Unit update was chosen as the live source on 2026-10-11; the WER was only the fallback.
  - **Most of the gap can come from the Dengue Control Unit.** Its PDFs cover 2026 weeks 19–37 except 20, 22, 23 and 31. Week 20 is in `denguedatahub`, so only **weeks 22, 23 and 31** would be truly missing. Leave them missing in the raw file and decide in Phase 2 how to treat them.
  - **The collector already copes.** A failed WER download prints a warning and builds from the PDFs already saved, so "re-run from scratch with no manual steps" still holds.
- **Plan:** wait about a week. If the site is still down, add a Dengue Control Unit part to `collect_cases.py`, compare its weeks 19–20 with `denguedatahub` for all 26 units, add a `source` column (`datahub` / `wer` / `ndcu`) to `cases_raw.csv`, and record the WER as discontinued in `docs/data.md`. Other things to try: the Wayback Machine again, a newer `denguedatahub` release, or emailing the Epidemiology Unit.
- Meanwhile, moved on to population (Step 1.5), which needs neither site.

> [!tip] What to remember
> - When a site is down, the Wayback Machine often has a copy of the page. That is enough to learn its HTML structure and write the parser.
> - A 403 that only Python gets is usually a User-Agent filter. Compare with curl before assuming you are banned.
> - Choose files by what the page *says* about them (dates, titles), not by guessing URL patterns.

**Check yourself**
1. Why does the downloader pick issues by the date on the card and not by the volume number in the file name?
2. The last `denguedatahub` week starts 2026-05-11. Which week does the first downloaded issue report, and why is that useful?
3. Why does the script carry on when the WER download fails, instead of stopping?

> [!success]- Answers
> 1. File names change style across years and some have no volume number at all, while every card shows its date in the same format.
> 2. The issue dated 18–24 May reports 11–17 May, the week `denguedatahub` already has. It is dropped from the table, but it lets you check that the two sources agree.
> 3. The PDFs already saved are still valid. Building from them gives a table that is at most a little out of date, which matters for the weekly pipeline later.

---
## Step 1.5 — Population per district (in progress)
> [!important] Goal
> A script, `src/collect_population.py`, that downloads the official mid-year population tables and saves `data/processed/population.csv` with one row per district per year, 2006 to 2026, and the columns `district, year, population, provisional, estimated, census_base`. Phase 2 divides cases by it to get cases per 100,000 people → [[01.08 Population Denominators and Incidence Rates]].

The step is done in six small parts:

| # | Part | Status |
|---|---|---|
| 1 | Download the two PDFs | Done, checked 2026-10-29 |
| 2 | Parse a PDF into rows | Done, checked 2026-10-29 |
| 3 | Check names and totals | Code written, not yet run |
| 4 | Join the two tables, mark the census base, fill 2006–2013 | To do |
| 5 | Check the final table (525 rows, no gaps) | To do |
| 6 | Data dictionary, notes, progress log | To do |

**What was done (2026-10-29)**

*Finding the source.* The Census and Statistics site (Vital Statistics page) has two district tables, both PDF only:

| Years | File | Based on | Provisional |
|---|---|---|---|
| 2014–2024 | `Mid-year_population_by_district_and_sex_2014_2024.pdf`, 3 pages, 4 years per page | 2012 census | 2018 onwards (`*`) |
| 2025–2026 | `Mid-year_population_by_district_and_sex_2025-2026.pdf`, 1 page | 2024 census | both years |

Both are in thousands, give Total, Male and Female, and list 25 districts plus a `Sri Lanka` row. Ampara only, no Kalmunai, as expected.

*Looking for 2006–2013.* No official district table was found:
- The yearly Vital Statistics pages (for example 2010) have only births, infant deaths and deaths by district, not population.
- The Central Bank's *Socio-Economic Data 2014* gives national totals only, and they show a break: **2011: 20,869 thousand → 2012: 20,328 thousand.** The country did not lose 540,000 people. Estimates before 2012 were built on the 2001 census, which could not fully count the North and East during the war; the 2012 census reset the base.
- **Decision:** do not mix older official figures into the series even if found, because they would make a false jump in 2012. Instead, extend the 2012-based series backwards with each district's average yearly growth over 2014–2019, and mark those rows `estimated=True`. 2019 is the end of the window because 2020–2024 are provisional and affected by COVID and emigration.

*Part 1: download.* `download_population()` saves both PDFs to `data/raw/population/` under short names, once. Checked: both files start with `%PDF-` (real PDFs, not saved error pages), 515,910 and 112,156 bytes, and a second run printed "Already have …" twice and downloaded nothing.

```python
PDF_URLS = {
    "population_2014_2024.pdf": (
        "https://www.statistics.gov.lk/Resource/en/Population/Vital_Statistics/"
        "Mid-year_population_by_district_and_sex_2014_2024.pdf"
    ),
    "population_2025_2026.pdf": (...),
}

for name, url in PDF_URLS.items():
    path = RAW_DIR / name
    if path.exists():
        print(f"Already have {path}, skipping")
        continue                      # not return: there is more than one file
    response = requests.get(url, headers=HEADERS, timeout=60)
    response.raise_for_status()
    path.write_bytes(response.content)
    time.sleep(DELAY_SECONDS)         # only after a real download
```

*Part 2: parse.* `parse_population_pdf(path)` reads each page with `extract_table()` and returns long rows of `district, year, population, provisional`. Row 0 of each table has a year above its Total column (`['District', '2025*', None, None, '2026*', None, None]`), row 1 is the Total/Male/Female sub-header, data starts at row 2.

```python
DISTRICT_NAMES = {"Nuwara-eliya": "Nuwara Eliya"}     # PDF spelling -> districts.csv

table = page.extract_table()
header, body = table[0], table[2:]
for col, label in enumerate(header):
    if col == 0 or label is None:
        continue                                      # a year sits only above Total
    for line in body:
        total = line[col].replace(" ", "").replace(",", "")
        rows.append({
            "district": DISTRICT_NAMES.get(line[0], line[0]),
            "year": int(label.rstrip("*")),
            "population": int(total) * 1000,          # table is in thousands
            "provisional": label.endswith("*"),
        })
```

**Checked (2026-10-29)**

| Check | Result |
|---|---|
| 2014–2024 file | 286 rows = 26 (25 districts + Sri Lanka) × 11 years |
| 2025–2026 file | 52 rows = 26 × 2 |
| Provisional flag | False for 2014–2017, True for 2018–2026, as marked in the PDFs |
| District names | All 25 match `districts.csv` after the `Nuwara-eliya` rename |
| 25 districts vs the Sri Lanka row | Difference **0** in all 13 years |
| Colombo | 2,353,000 (2014) → 2,446,000 (2024) → 2,371,000 (2025) |

**Problems and fixes**

| Problem | What was tried | Fix |
|---|---|---|
| The web page reader could not open the 2014–2024 PDF; it returned "raw compressed PDF data" | — | Downloaded the file and read it with `pdfplumber` instead |
| `extract_text()` breaks the 2025–2026 file: spaces inside numbers, `Kalutara 1 ,302 6 29 6 73` | `x_tolerance` 3, 4 and 5: no change, so the gaps are real in the layout | `extract_table()`. It uses the cell borders, so each number stays in one cell (`'1 ,302'`); removing spaces and commas gives `1302`. Works on both files. |
| The text strategy, `extract_table({"vertical_strategy": "text", ...})`, cut words across cells (`'tal M'`, `'ale Fem'`) | — | Not used; the default (border lines) is right for these files |
| The district spelling differs: `Nuwara-eliya` in the PDF | — | `DISTRICT_NAMES` rename map, the same idea as `UNIT_NAMES` in the case collector |
| Typos in the docstring and comments (`Satistcs`, `dat/raw`, `namees`, `easir`) and imports not grouped | — | Still to fix. Standard library first, blank line, then `pandas`, `pdfplumber`, `requests` together; two blank lines between top-level functions. **Fixed 2026-10-29**, along with two new typos from a half-finished edit (`statistcs`, a stray `/ch` in the path); all docstrings now use `"""`. Output unchanged after the tidy-up. |
| The planned failure test did **not** fail: reading the Male column instead of Total still gave a gap of 0 | Ran the shifted parser on a scratch copy of the script | A column shift moves the Sri Lanka row too, so male districts add up to male Sri Lanka. Added a row-level check in the parser: Total must equal Male + Female (±1 thousand). The shifted run now stops with `Sri Lanka 2014: Total 10059 is not Male 10719 + Female 20970` |

**Found for Part 4: the census-base jump.** From 2024 (2012 census base) to 2025 (2024 census base) some districts change far more than a year's growth of about 1 %:

| District | 2024 → 2025 |
|---|---|
| Mullaitivu | **+23.0 %** (100,000 → 123,000) |
| Vavuniya | **−12.2 %** |
| Nuwara Eliya | −6.8 % |
| Mannar | +6.0 % |
| Jaffna | −5.4 % |
| Sri Lanka | −0.7 % |

These are corrections, not real changes: the 2012-based estimates drifted for 12 years and the 2024 census measured again. The `census_base` column will mark which series each row comes from. Growth over 2014–2019 ranges from 0.42 % a year (Mullaitivu) to 1.80 % (Ampara, Kilinochchi); these are the rates for the back-fill.

**Part 3 (written, not yet run).** `check_population(table)` stops on unknown or missing names and on totals that do not add up, then drops the Sri Lanka row. The tolerance is 26 × 500 = 13,000: every figure is rounded to the nearest thousand, so 25 districts and the national row can each be 500 off. Today the difference is 0, but a correct table rounded differently should not fail, and a column mistake (Male instead of Total) is off by millions.

```python
TOTAL_TOLERANCE = 26 * 500

known = set(pd.read_csv(DISTRICTS_FILE)["district"])        # 26 rows -> 25 names
found = set(table["district"]) - {"Sri Lanka"}
if found != known:
    raise ValueError(f"... unknown {sorted(found - known)}, missing {sorted(known - found)}")
national = table[table["district"] == "Sri Lanka"].set_index("year")["population"]
summed = table[table["district"] != "Sri Lanka"].groupby("year")["population"].sum()
gap = (summed - national).abs()
```

To test it: change `line[col]` to `line[col + 1]` (the Male column) for one run and confirm it raises "Districts do not add up to Sri Lanka".

**Part 3: run and tested (2026-10-29).** The `__main__` block now joins both PDFs with `pd.concat` and passes them through `check_population()`.

| Test | Expected | Result |
|---|---|---|
| Real data | Pass | Passed: 25 districts, largest gap 0; 325 rows = 25 × 13 years (2014–2026) |
| District renamed back to `Nuwara-eliya` | Stop | Stopped: `unknown ['Nuwara-eliya'], missing ['Nuwara Eliya']` |
| Male column read as Total | Stop | **Passed — the check missed it.** Every row shifts, Sri Lanka included, so the wrong columns still add up |
| Same shift, after adding Total = Male + Female | Stop | Stopped: `Total 10059 is not Male 10719 + Female 20970` |

The fix lives in the parser, because that is where all three columns are still side by side:

```python
total, male, female = (to_thousands(line[c]) for c in (col, col + 1, col + 2))
if abs(total - male - female) > 1:      # each figure rounded to a thousand
    raise ValueError(...)
```

So the two checks guard against different mistakes: **Total = Male + Female** catches a wrong column (the same mistake in every row); **districts = Sri Lanka** catches a wrong or missing row in one district.

**Part 4: one series, 2006–2026 (2026-10-29).** `build_series(table)` fills two gaps per district.

*The choice.* Three ways to handle the 2024 → 2025 census break were compared:

| Option | What it does | Verdict |
|---|---|---|
| A. As published | Keep both series, mark the break with `census_base` | Leaves fake jumps (Mullaitivu +23 %) that the model would read as a real fall in incidence |
| B. Scale all old years | Multiply 2006–2024 by the district's factor | Removes the jump, but makes 2012 wrong: the old series *was* a census count that year |
| **C. Phase in (chosen)** | Factor 1.0 in 2012, rising in a straight line to the full factor in 2024 | Removes the jump and keeps 2012 correct; matches how the drift built up. This is the standard *intercensal adjustment* |

*The formulas.* For each district, with $r$ its yearly growth over 2014–2019 (final figures, before COVID):

$$r = \left(\frac{P_{2019}}{P_{2014}}\right)^{1/5} - 1 \qquad \text{factor} = \frac{P_{2025} / (1 + r)}{P_{2024}}$$

$$P^{\text{back-fill}}_y = \frac{P_{2014}}{(1 + r)^{2014 - y}} \quad (2006 \le y \le 2013)$$

$$P^{\text{adjusted}}_y = P_y \times \left(1 + (\text{factor} - 1)\cdot\frac{\max(0,\ y - 2012)}{12}\right) \quad (y \le 2024)$$

The factor ranges from 0.868 (Vavuniya: old estimate 15 % too high) to 1.225 (Mullaitivu: 22 % too low); most districts are within 4 %.

```python
weight = max(0, year - OLD_CENSUS) / (NEW_CENSUS - OLD_CENSUS)
population = wide[year] * (1 + (factor - 1) * weight)
```

*Checked.*

| Check | Result |
|---|---|
| Rows | 525 = 25 × 21 years (2006–2026) |
| 2024 → 2025 change | Now equal to each district's growth rate: 0.42 % (Mullaitivu) to 1.80 % (Ampara, Kilinochchi). Before: −12.2 % to +23.0 % |
| Largest yearly change anywhere | +3.18 % (Mannar 2018), −1.28 % (Kandy 2023). Both sit on years that are already uneven in the published figures (2018 is where the provisional estimates start) |
| Mullaitivu | 91,885 (2006) → 94,212 (2012) → 122,489 (2024) → 123,000 (2025) |
| Flags | `estimated` True for 2006–2024 (475 rows: back-filled or adjusted), False for 2025–2026 (50 rows, exactly as published) |

`estimated` turned out True for every year up to 2024, because every 2013–2024 figure is adjusted a little. Only 2025–2026 are untouched official numbers. That is the honest answer, not a bug.

**Why this way**
- **Download and parse are separate**, as in the other collectors: the parser can be fixed and re-run without the network.
- **Short local file names, URLs kept in the code.** Easy to type, and the code still records where each file came from.
- **`extract_table()` over `extract_text()`.** The table has drawn cell borders, so the table reader keeps each number whole even when the text reader splits it. The WER needed character positions because its table is sideways; this one does not.
- **Whole people, not thousands.** `population` is multiplied by 1,000 once, so later formulas have no hidden factor.
- **Keep the Sri Lanka row until the check.** It is a free test of the parser, then it is dropped.
- **One consistent series beats a mix of official numbers.** A smooth estimate on one census base is a better denominator than official figures from two bases with a fake jump between them.

**Still to do in this step**
- ~~Run Part 3 and the deliberate-failure test.~~ Done 2026-10-29 (see above).
- ~~Part 4: combine the two tables, add `census_base` (2012 or 2024) and `estimated`, back-fill 2006–2013.~~ Done 2026-10-29 with option C (see above).
- Part 5: check 25 districts × 21 years = 525 rows, no gaps, no duplicates.
- Part 6: population columns in `docs/data.md` (note the Mullaitivu and Kilinochchi caveat for 2006–2009), `docs/progress.md`, commit.

> [!tip] What to remember
> - When `extract_text()` gives broken numbers, try `extract_table()` before fighting with tolerances. If the PDF has cell borders, the table reader is more reliable.
> - A national total row is a built-in test. Use it, then drop it.
> - Run every check against a deliberate mistake before trusting it. A check that has never failed may not be able to fail: the district-sum check passed a whole-column error because the total row shifted with it.
> - Before mixing two official series, check whether they share a base. A census changes the base, and a sudden jump in the year it changes is an artefact, not a real event.
> - Population changes about 1 % a year, cases change ten-fold within a year. A small error in the denominator barely moves the incidence rate, except where people really moved, such as the Northern districts during the war (2006–2009).

**Check yourself**
1. Why was `extract_table()` used and not `extract_text()` for the population PDFs?
2. Official national figures show 20.9 million in 2011 and 20.3 million in 2012. What happened, and why does it matter for the project?
3. The Mullaitivu population goes from 100,000 in 2024 to 123,000 in 2025. Is that real growth? How will the table show it?

> [!success]- Answers
> 1. In the 2025–2026 file, `extract_text()` puts spaces inside numbers (`1 ,302`, `6 29`), so splitting a line on spaces gives too many pieces. `extract_table()` follows the drawn cell borders and keeps each number in one cell, so removing spaces and commas gives the right value.
> 2. The base changed from the 2001 census to the 2012 census, which counted the North and East properly. Mixing the two series would make every district's incidence jump in 2012 for no real reason, and a model could learn that jump.
> 3. No. It is the switch from 2012-census estimates to the 2024 census. The `census_base` column marks each row as 2012 or 2024, so the jump can be seen and explained.
