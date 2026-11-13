---
type: project-note
project: Dengue Early-Warning
status: draft
tags: [project, ml, web]
---
# Phase 07 — Dashboard
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 19–20, target finish 2027-02-28
> **Study notes:** [[07.00 Dashboard]] · **Revision:** [[07.99 Summary - Dashboard]]

> [!abstract] Phase goal
> A simple web page a non-technical health officer could understand in 30 seconds: which districts are at risk, and how confident the system is.

## Steps
- [ ] Sketch the layout on paper: map on the left, detail panel on the right, "last updated" in the header
- [x] Serve the page from a Flask template (done 2026-11-13, Step 7.1, prototype) → [[07.06 Flask Templates with Jinja2]]
- [x] Lay out the page so it works on a phone (done 2026-11-13, Step 7.1, checked at 375 px) → [[07.01 CSS Layout with Flexbox and Grid]]
- [x] Fetch `/api/alerts/latest` and log it to the console (done 2026-11-13, Step 7.2: the page now reads `/api/alerts/latest` and `/api/predictions`; the demo JSON is gone) → [[07.02 JavaScript fetch and Promises]]
- [x] Map: district boundaries coloured by alert level, with predicted cases in a tooltip (done 2026-11-13, Step 7.1, demo forecast) → [[07.04 Leaflet and GeoJSON]]
- [x] Detail chart: actual against predicted, with the uncertainty band and threshold lines (done 2026-11-13, Step 7.1, demo forecast and band) → [[07.05 Chart.js]]
- [x] Alert table that responds to clicks (done 2026-11-13, Step 7.1) → [[07.03 DOM Manipulation and Events]]
- [x] Add "How to read this", the data sources, and the note that this is decision support (done 2026-11-13, Step 7.1)
- [ ] Check colour-blind readability, keyboard use and contrast; test with one non-technical person (2026-11-13, Step 7.2: contrast **measured** with the WCAG formula and fixed, all text 5.3:1 or more, map levels 3.1:1 or more from each other; keyboard via table buttons and drop-downs. **Still open: the test with a non-technical person**) → [[07.07 Dashboard UX and Accessibility]]

## Done when
- [x] The map shows every district coloured by alert level (2026-11-13; real forecasts from the API since Step 7.2)
- [x] Clicking a district shows its chart with thresholds (2026-11-13, map, table and drop-down checked)
- [x] "How to read this" and the data-source notes are visible (2026-11-13)
- [x] The page works on a phone screen (2026-11-13, 375 px frame: one column, no sideways page scroll)
- [ ] One non-technical person has tested it and their feedback is addressed

---
## Work log
Step sections are added here as each step is finished.

## Step 7.1 — Dashboard prototype, built early
> [!important] Goal
> Build the whole dashboard page now, before Phases 2–6, using the real case history and real alert thresholds, with a clearly labelled demo forecast standing in for the model. Fix the data format the page needs, so later phases build to it.

**What was done (2026-11-13)**
- Decided to build the frontend before finishing Phase 1 (population and the WER fallback are still open). Built it as a **contract-first prototype**: one JSON file is the agreement between back end and page.
- `src/export_dashboard.py` reads `cases_raw.csv` and `districts.csv` and writes two files to `app/static/data/` (not committed, the script rebuilds them):
  - `dashboard.json` (93 KB): the axis of weeks, and per district the alert, actual cases, demo forecast with its band, and the Watch and Warning lines.
  - `districts.geojson` (155 KB): the 25 district outlines from geoBoundaries (OpenStreetMap, ODbL), renamed from `Colombo District` to `Colombo`, coordinates rounded to 4 decimals. The raw download is cached in `data/raw/boundaries/`.
- Flask added early (uncommented in `requirements.txt`, Flask 3.1.3): one route `/` renders `app/templates/index.html`.
- The page: `app/static/css/dashboard.css` (CSS Grid, map | detail on wide screens, one column under 900 px) and `app/static/js/dashboard.js` (Leaflet 1.9.4 map, Chart.js 4.4.1 chart, sortable table, district drop-down). Both libraries load from cdnjs at fixed versions.

**Checked (2026-11-13), in Chrome**

| Check | Result |
|---|---|
| Boundary names vs `districts.csv` | All 25 match after removing " District"; the script stops if not |
| Map | 25 districts coloured; hover shows name, level and forecast peak |
| Clicking map, table row, drop-down | All three update the panel, chart, selected row and map outline |
| Table sort | District A–Z and back; `aria-sort` follows |
| Missing weeks | 12 weeks (18 May to 3 Aug 2026) blank on the chart, named under it |
| Phone, 375 px frame | One column, page width 357 = screen width, map 420 px; the table scrolls inside its own box (394 / 291 px) |
| Console | No errors from the page (one from a browser extension) |

Result on the current data: **19 Warning, 2 Watch, 4 Normal**, data up to 10–16 Aug 2026.

**Why this way**
- **Contract first.** The page never reads CSV. It reads one JSON file whose URL comes from Flask (`data-src` on `<body>`). In Phase 6 the route returns the same JSON from the database and only that URL changes → [[06.04 Flask Routing and JSON APIs]].
- **Real wherever possible.** Case history and thresholds are real; only the forecast is fake, and the page says so in a banner, the legend ("Forecast (demo)") and the method list.
- **Thresholds are a simple endemic channel**: the 75th (Watch) and 90th (Warning) percentile of weekly cases for that week of the year over the previous 10 years → [[05.02 Endemic Channel and Alert Thresholds]]. Phase 5 replaces this with the evaluated version in `src/alerts.py`.
- **Week of the year from the date, not the published week number.** Week 53 and the 2026 change to Monday starts make the labels unreliable; `(middle day of year − 1) // 7 + 1` always works.
- **Alert = highest level the forecast reaches in weeks 4–8 ahead**, the horizon the project promises. Under 5 cases a week is always Normal, so a district going from 0 to 2 cases is not a Warning.
- **Colours ordered light → dark** (pale green, amber, deep red) and always with a text label, so the levels can be told apart without seeing colour → [[07.07 Dashboard UX and Accessibility]].
- **Keyboard route.** A Leaflet map cannot be used by keyboard, so the table (buttons) and the drop-down give the same choice.

```python
# Demo forecast: this year's level times last years' change over the same weeks
then  = value_near(series, last_week - shift)     # same week, 1–3 years ago
later = value_near(series, target - shift)        # target week, 1–3 years ago
values.append(latest * (later + 1) / (then + 1))  # +1: no divide-by-zero in tiny districts
```

```javascript
// Every way of choosing a district calls the same function
districtLayer.on("click", () => selectDistrict(name));
tr.querySelector("button").addEventListener("click", () => selectDistrict(row.district));
select.addEventListener("change", () => selectDistrict(select.value, { scroll: false }));
```

```powershell
python -m src.export_dashboard      # rebuild app/static/data/
flask --app app run --debug         # http://127.0.0.1:5000
```

**Problems and fixes**

| Problem | What was tried | Fix |
|---|---|---|
| Demo forecast dropped Colombo from 584 to 139 in one week | First version: median of the same week in the last 3 years | 2026 runs 2–4× above past years (national 1,574 in week 20 → 2,682 in week 33), so copying past values ignores the current level. Switched to a **seasonal ratio**: latest count × how much past years changed over the same weeks → [[04.02 Forecasting Baselines - Persistence and Seasonal]] |
| Watch/Warning lines jumped, e.g. Gampaha Warning to 650 for a single July week | — | Only 10 values per week of the year, so one epidemic year (2017) makes spikes. Each week is averaged with its neighbours, wrapping week 52 round to week 1 |
| Map covered in "API KEY REQUIRED" | CARTO light basemap | CARTO now needs a key; switched to OpenStreetMap tiles |
| "newest data" and "alert weeks" labels overlapped; forecast squeezed at the right edge | — | 78 weeks of history instead of 104; shorter label moved down a line and kept inside the chart |
| Detail sentence repeated the same date ("crosses … 7 Sept, peaking … 7 Sept") | — | Separate wording when the crossing week is the peak week |
| `DatetimeIndex.dayofyear` deprecation warning | — | `day_of_year` |

**Found in the data, for Phase 2**
- 2026 week 14 (30 Mar–5 Apr): Colombo 9 and Gampaha 10 against about 300 and 190 in the weeks around it, while Vavuniya has 39 against about 5. Looks like misplaced values; check against the WER issue.
- Every forecast starts from the single WER week 33, which has not yet been cross-checked against `denguedatahub` (Phase 01, Step 1.4). The page says so under Sources.

> [!tip] What to remember
> - Agree the JSON shape first; then the page and the back end can be built in either order.
> - A seasonal forecast must start from *this year's* level, or a high year looks like a sudden drop.
> - Percentiles from few years are noisy; smooth across neighbouring weeks before using them as thresholds.
> - `fetch()` does not work on a page opened from disk (`file://`); serve it, here with Flask.

**Check yourself**
1. Why does the page get its data URL from Flask instead of writing `/static/data/dashboard.json` in the JavaScript?
2. The first demo forecast for Colombo was 139 when the latest week was 584. What was wrong with the method?
3. Why does the table have buttons in the district column when the map can already be clicked?

> [!success]- Answers
> 1. In Phase 6 the data comes from an API route. Only the template's `data_url` changes; the JavaScript stays the same.
> 2. It copied past years' values for those weeks and ignored that 2026 is running far higher, so it predicted an instant fall. The seasonal ratio keeps this year's level and applies only the usual seasonal change.
> 3. The map cannot be used with a keyboard or a screen reader. The table buttons and the drop-down give everyone the same way to choose a district.

**Update (2026-11-13, later): data refreshed.** The Dengue Control Unit fallback ([[Phase 01 - Data Collection]], Step 1.4, Part 3) filled the 12 missing weeks and added weeks 34–44. After `python -m src.export_dashboard` the page shows data up to 26 Oct–1 Nov 2026 with no gaps: **8 Warning, 4 Watch, 13 Normal** (was 19, 2, 4). The new weeks show the June–July 2026 epidemic, so the old "high" week 33 was its tail, not a rise. The Sources note now names the Dengue Control Unit and says its district counts are not yet adjusted to the WER history.

---
## Step 7.2 — The dashboard reads the API
> [!important] Goal
> Replace the demo forecast with the real one: the page reads the Phase 6 API, shows this week's forecasts, intervals and alerts from MySQL, and passes a measured accessibility check.

**What was done (2026-11-13)**
- **Data from the API**, not a file:
  - `GET /api/alerts/latest` for the header, map, summary and table (one request);
  - `GET /api/predictions?district=&horizon=&weeks=78` for the chart, fetched when a district is first chosen and kept in a cache, so going back is instant.
  - The template puts both URLs on `<body>` with `url_for`, so the JavaScript never hard-codes a path.
- **One API addition**: each district in `/api/alerts/latest` now carries `latest_cases`, so the table needs no 25 extra requests (test updated).
- **Page changes**: header "Case data up to" and "Forecast made"; a **horizon drop-down** (4, 6 or 8 weeks); the detail sentence names the week, the forecast, the line it crosses, and every horizon at that level; stats show this week's forecast and its 80 % range; the chart draws actual cases, the backfilled forecasts since 2024 (dashed), the live forecast (a large dot), the 80 % band and the Watch and Warning lines; the table shows the latest count, the 4- and 8-week forecasts and the change. The "Prototype / demo" banner became a plain decision-support notice, and "How to read this" now states the real method and its tested performance.
- **Removed** `src/export_dashboard.py` and its demo forecast; the map outlines now come from `src/export_boundaries.py`.
- A newer request wins: each chart request gets a number, and an answer that arrives after a newer choice is ignored.
- New test: the page carries the two API URLs. **18 tests pass.**

**Checked in Chrome (2026-11-13)**

| Check | Result |
|---|---|
| Header | Case data up to 2 – 8 Nov 2026; forecast made week of 9 Nov 2026 |
| Map and summary | 6 Warning, 14 Watch, 5 Normal (from MySQL) |
| Kandy (first, Warning) | "forecast for the week of 7 Dec 2026 (4 weeks ahead) is about 213 cases … Warning line 172. Warning at 4, 6, 8 weeks ahead"; range 107 – 821 |
| Colombo from the table | Watch; 329 for 7 Dec; range 166 – 1,266 |
| Horizon 8 | "Forecast, week of 4 Jan": 358 |
| Sort by 4-week forecast | Colombo 329, Gampaha 261, Kandy 213 |
| Phone, 375 px | One column, no sideways page scroll; both drop-downs fit; the 6-column table scrolls in its own box |
| Console | No errors from the page |

**Contrast, measured** (WCAG: text 4.5:1, shapes 3:1)

| Pair | Before | After |
|---|---|---|
| All text (body, muted, notice, badges, links, ▲▼) | 5.3 – 16:1 | unchanged, all pass |
| Map: Watch next to Normal | **1.52:1** | **3.12:1** |
| Map: Warning next to Watch | 3.41:1 | 3.18:1 |
| Map: Normal against the district border | **1.32:1** (white border) | **5.95:1** (dark grey border) |

Fix: the map, legend and chart lines got their own darker colours (`--watch-fill` #b86a00, `--warning-fill` #650916, border #4a5450). The badges keep the lighter amber, since they always show the word and dark text on the deep amber would fail; the Warning badge now uses the maroon fill, so badge, legend and map agree. Lightness now falls by a clear step from Normal to Watch to Warning, which is what colour-blind readers rely on.

**Problems and fixes**

| Problem | What was tried | Fix |
|---|---|---|
| The browser showed the old prototype page after the change | Checked the files on disk: they were new | The Flask server started at 12:07 that morning was still running on port 5000, and Windows sent the requests to it. Stopped it (process 37408); stop test servers when done |
| "Case data up to 2 Nov – **7** Nov" | — | The week end was built with `toISOString()`, which converts to UTC; in Sri Lanka (UTC+5:30) local midnight is the previous day in UTC. Now formatted in local time: 2 – 8 Nov |
| "not yet reported" cut off at the chart's right edge | — | Drawn only when it fits |
| No colour set passed every contrast check at once | Searched combinations | Two of the rules pull opposite ways (Watch must be dark against Normal, light under dark badge text), so map colours and badge colours were separated |

**Why this way**
- **One request for the overview, one per district for detail.** The first screen needs all 25 districts but only this week's numbers; the chart needs 78 weeks for one district.
- **Measure contrast, do not judge it by eye.** The two failing pairs looked "fine" on a good screen.
- **The table is the text alternative to the map** (named in the map's `aria-label`), so keyboard and screen-reader users get the same information.

> [!tip] What to remember
> - Before trusting a browser test, make sure only one server is listening on the port.
> - `toISOString()` is UTC: east of Greenwich it can move a local date back by a day.
> - Contrast rules: text 4.5:1, shapes and lines 3:1. Colours that carry a word can be lighter than colours that carry the meaning alone.
> - Ignore stale responses: number each request and draw only the newest.

**Check yourself**
1. Why does the page fetch the chart data only when a district is chosen?
2. Why did "2 Nov – 7 Nov" appear, and why only in some time zones?
3. Why can the Watch badge stay light amber when the map's Watch colour had to become darker?

> [!success]- Answers
> 1. The first screen needs only this week's numbers for all districts, which one request gives. 78 weeks of history and forecasts for 25 districts would make the first load slow; one district at a time, cached, is enough.
> 2. `toISOString()` gives the UTC date. Local midnight on 8 Nov in Sri Lanka is 18:30 on 7 Nov in UTC, so the date moved back a day. West of UTC (or at UTC) it would not show.
> 3. The badge always shows the word "Watch", so its colour only supports the text, and its text contrast passes (6.2:1). On the map, colour is the only signal, so neighbouring levels need 3:1 between them.

---
## Step 7.3 — District names above the colours
> [!important] Goal
> Make place names readable on the map: the coloured fills covered the base map's own town names (feedback from the user, 2026-11-13).

**What was done (2026-11-13)**
- Each district's name is drawn at its **main town**, using the coordinates from `/api/districts` (the page now fetches it with the alerts and the outlines).
- The names are Leaflet markers with a text-only `divIcon`, so they sit in the **marker pane, above the coloured shapes** (the overlay pane).
- Dark text with a white outline (`text-shadow`), so it reads on pale green, amber and maroon alike.
- `pointer-events: none` and `interactive: false`: a click on a name still reaches the district underneath.
- Three crowded towns are drawn to one side: Kurunegala and Nuwara Eliya end at their town, Badulla starts at its town.

**Checked**: 25 names on the map; the element under the "Kandy" name is the district shape (so clicks pass through); measuring every pair of label boxes found **no overlaps** (before the nudge: Kurunegala/Matale and Nuwara Eliya/Badulla overlapped). Live after Vercel's automatic redeploy.

> [!tip] What to remember
> - Leaflet draws in panes: tiles, then shapes (overlay pane), then markers. Put text that must stay readable in a higher pane.
> - Labels on a map should not catch clicks meant for the shapes beneath them.
> - Check label collisions by measuring boxes, not only by eye.
