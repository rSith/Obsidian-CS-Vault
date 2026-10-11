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
- [ ] Write the weather collector `src/collect_weather.py` → [[01.01 HTTP Requests and REST APIs in Python]] · [[01.02 Parsing JSON into Tables]]
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
- [ ] Daily weather is downloaded for the same districts and years
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
