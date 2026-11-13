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
- [ ] Fetch `/api/alerts/latest` and log it to the console (2026-11-13: the page fetches `static/data/dashboard.json`, the same shape the API will return; switch the URL in Phase 6) → [[07.02 JavaScript fetch and Promises]]
- [x] Map: district boundaries coloured by alert level, with predicted cases in a tooltip (done 2026-11-13, Step 7.1, demo forecast) → [[07.04 Leaflet and GeoJSON]]
- [x] Detail chart: actual against predicted, with the uncertainty band and threshold lines (done 2026-11-13, Step 7.1, demo forecast and band) → [[07.05 Chart.js]]
- [x] Alert table that responds to clicks (done 2026-11-13, Step 7.1) → [[07.03 DOM Manipulation and Events]]
- [x] Add "How to read this", the data sources, and the note that this is decision support (done 2026-11-13, Step 7.1)
- [ ] Check colour-blind readability, keyboard use and contrast; test with one non-technical person (2026-11-13: colours ordered light to dark with text labels, table and drop-down usable by keyboard; contrast not measured, nobody has tested it yet) → [[07.07 Dashboard UX and Accessibility]]

## Done when
- [x] The map shows every district coloured by alert level (2026-11-13, prototype with demo forecast)
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
