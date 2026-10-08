---
type: project-note
project: Dengue Early-Warning
status: stub
tags: [project, ml]
---
# Phase 01 — Data Collection
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 3–5, target finish 2026-11-15
> **Study notes:** [[01.00 Data Collection]] · **Revision:** [[01.99 Summary - Data Collection]]

> [!abstract] Phase goal
> At least 8 years of weekly dengue cases and matching weather for the chosen districts, saved as raw files, plus a script that downloads them again from scratch.

## Steps
- [ ] Find the case data: which sources, which format (PDF, web table, file), how far back
- [ ] Write the case collector `src/collect_cases.py` → [[01.03 Web Scraping with BeautifulSoup and read_html]] · [[01.04 PDF Table Extraction with pdfplumber]]
- [ ] Write the weather collector `src/collect_weather.py` → [[01.01 HTTP Requests and REST APIs in Python]] · [[01.02 Parsing JSON into Tables]]
- [ ] Collect population per district
- [ ] Write the data dictionary in `docs/data.md`
- [ ] Cache every download and wait between requests

> [!warning] Carried over from the Phase 0 data check
> These are not in the roadmap. They came out of checking the real sources, and they affect Phase 3.
> - [ ] Measure how late each case source publishes → [[01.06 Reporting Delay and Data Availability]]
> - [ ] Choose the source that will feed the live system
> - [ ] Decide whether to collect all districts now (every report already lists all of them)
> - [ ] Decide how the 26 reporting units map to 25 districts (Kalmunai and Ampara)
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
