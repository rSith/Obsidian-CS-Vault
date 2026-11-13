---
type: project-note
project: Dengue Early-Warning
status: done
tags: [project, ml, web]
---
# Phase 06 — Database and Flask API
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 17–18, target finish 2027-02-14
> **Study notes:** [[06.00 Database and Flask API]] · **Revision:** [[06.99 Summary - Database and Flask API]]

> [!abstract] Phase goal
> Data, predictions and alerts live in MySQL and are served through a small, documented Flask API.

## Steps
- [x] Draw the ER diagram, then create the tables (done 2026-11-13, Step 6.1; also `district_population` and `weeks`): `districts`, `weekly_cases`, `weekly_weather`, `predictions` → [[06.01 Relational Schema Design and Normalisation]]
- [x] Keep the database password in `.env` (done 2026-11-13, Step 6.1: read with python-dotenv; SQLite fallback until MySQL is set up) → [[06.06 Environment Variables and Configuration]]
- [x] Load the CSV data into MySQL (done 2026-11-13, Step 6.4: MySQL 8 on port 3307) → [[06.03 SQLAlchemy]]
- [x] Write `src/predict.py` (done 2026-11-13, Step 6.2): load the model, build features for the latest week, predict, apply thresholds, write rows to `predictions`
- [x] Write the queries the endpoints need (done 2026-11-13, Step 6.3) → [[06.02 SQL Joins, Aggregation and Indexes]]
- [x] Build the four endpoints (done 2026-11-13, Step 6.3): `/api/districts`, `/api/predictions`, `/api/alerts/latest`, `/api/health` → [[06.04 Flask Routing and JSON APIs]]
- [x] Return clear JSON with units, and proper status codes for errors (done 2026-11-13, Step 6.3) → [[06.05 REST API Design and Status Codes]]
- [x] Document each endpoint in the README with an example request and response; test with `curl` or Postman (done 2026-11-13, Step 6.3)

## Done when
- [x] The schema is created and the ER diagram is saved in `docs/` (2026-11-13: `docs/er_diagram.md`)
- [x] Historical data is loaded into MySQL (2026-11-13: MySQL 8.0.44, identical to the SQLite copy)
- [x] `predict.py` writes a week of predictions and alerts (2026-11-13: 75 live rows, plus the backfill)
- [x] All 4 endpoints return correct JSON and handle errors (2026-11-13: 11 API tests pass)
- [x] The API is documented in the README (2026-11-13)

---
## Work log
Step sections are added here as each step is finished.

## Step 6.1 — Schema, connection and loading
> [!important] Goal
> Put the data in a relational database with a clean schema, keep the connection string out of Git, and load the processed CSVs in one safe step → [[06.01 Relational Schema Design and Normalisation]] · [[06.03 SQLAlchemy]] · [[06.06 Environment Variables and Configuration]].

**What was done (2026-11-13)**
- **Checked the machine first:** MySQL 8.0 is installed (service `MySQL80`) but **stopped**, and there is no `.env`. Starting it and creating a user needs the MySQL root password, which is the user's own credential, so that part is left to the user (steps in the README, "Database setup").
- So that nothing waits on it, the code uses **SQLAlchemy Core**, which runs unchanged on MySQL and SQLite: `get_engine()` reads `DATABASE_URL` from `.env` with `python-dotenv`, and falls back to `sqlite:///data/processed/dengue.db` when it is not set.
- `src/db.py` defines six tables; ER diagram (Mermaid) in `docs/er_diagram.md`:

| Table | Key | Holds |
|---|---|---|
| `districts` | `id` | name, coordinates of the main town |
| `district_population` | district, year | population per year |
| `weeks` | `week_start` | week end and the three national flags |
| `weekly_cases` | district, week | WER-scale cases, published cases, source, weekly population |
| `weekly_weather` | district, week | rain, temperatures, humidity |
| `predictions` | `id`; unique (district, target week, horizon, model version) | forecast, 80 % interval, lines, alert, `live` or `backfill` |

- `src/load_db.py` empties and refills the five data tables **in one transaction** (children first because of the foreign keys).
- Uncommented `sqlalchemy`, `pymysql`, `python-dotenv` in `requirements.txt` (SQLAlchemy 2.1.8, PyMySQL 2.2.8); `.env.example` explains both URLs.

**Checked**: 25 districts, 525 population rows, 1,037 weeks, 25,925 case rows and 25,925 weather rows; a join query gives Colombo 197,989 cases over 1,037 weeks; 134 epidemic weeks, as in `weekly.csv`.

**Why this way**
- **Normalised a little beyond the roadmap.** Population changes yearly, so it gets its own table instead of one value in `districts`; the national flags are stored once per week in `weeks` instead of 25 times.
- **One transaction.** If loading fails half way, the old data stays; the API never sees a half-loaded database.
- **Core, not the ORM.** The project moves tables of rows, not objects; Core keeps the SQL visible and still uses bound parameters.

**Problems and fixes**

| Problem | What was tried | Fix |
|---|---|---|
| MySQL service stopped, no `.env` | — | SQLite fallback for now; README lists the four steps for MySQL |
| `pymysql` cannot send `numpy.int64` or `numpy.bool_`, which pandas returns (SQLite accepts them, so it would only fail on MySQL) | — | `records()` in `src/db.py` turns a DataFrame into plain Python values and `None`; used by both loaders |

---
## Step 6.2 — `predict.py`: this week's forecasts and the backfill
> [!important] Goal
> A script that reads the data from the database, forecasts 4, 6 and 8 weeks ahead from the current week, applies the alert lines and writes the rows to `predictions`.

**What was done (2026-11-13)**
- `read_weekly()` rebuilds the weekly table from the database with three joins, so the model reads exactly what the API serves.
- **Which week is "now".** The newest case week is L = 2–8 Nov 2026. The forecast is made in week L+1 (9–15 Nov, the week of the run). `add_current_week()` appends that week with no cases or weather; by the delay rule of Phase 3 its features use cases to L−1 and weather to L. Targets: 7 Dec (4 weeks), 21 Dec (6), 4 Jan 2027 (8).
- Each saved model now carries **its own 80 % interval** (`interval_quantiles()` in `train.py`, from its CV errors; added to the saved files with `python -m src.train intervals`): on the log scale −0.68 to +1.34 at 4 weeks, −0.70 to +1.50 at 6, −0.75 to +1.58 at 8.
- Alert lines for forecasts from Phase 5 (Watch $\mu + 0.25\sigma$, Warning $\mu + 1.25\sigma$), stored with each row.
- `--backfill` also writes what the saved models say for every target week since 2024 (they were trained on targets to Dec 2023, so these are out-of-sample), for the dashboard chart.
- Writing replaces the same model's rows for the same target weeks in one transaction, so re-running is safe.

**Checked (2026-11-13)**

| Check | Result |
|---|---|
| Rows written | 11,625 (75 live: 25 districts × 3 horizons) |
| **Backfill = Phase 5 test forecasts** | All 3,700 matched rows agree to 0.005 cases (rounding): the database path gives the same answers as the CSV path |
| Re-run without `--backfill` | Replaces the 75 live rows only |
| This week (origin 9 Nov), 4 weeks ahead | 4 Warning (Kandy, Kegalle among them), 15 Watch, 6 Normal |

---
## Step 6.3 — The Flask API, tests and documentation
> [!important] Goal
> Four GET endpoints returning clear JSON, errors with the right status codes, tests, and a README a stranger could use → [[06.04 Flask Routing and JSON APIs]] · [[06.05 REST API Design and Status Codes]] · [[06.02 SQL Joins, Aggregation and Indexes]].

**What was done (2026-11-13)**
- `app/__init__.py` became an **app factory**, `create_app(database_url=None)`, so tests can give it their own database; `flask --app app run` finds it by name.
- `app/api.py`, a Blueprint under `/api`:

| Endpoint | Returns | Errors |
|---|---|---|
| `GET /api/health` | status, newest case week, newest forecast week | 503 if the database is down |
| `GET /api/districts` | 25 districts with coordinates and the latest population (a join on a "latest year per district" subquery) | — |
| `GET /api/predictions?district=&horizon=&weeks=` | history (newest `weeks`, default 104) and forecasts for one district and horizon | 400 missing district or bad parameter; 404 unknown district |
| `GET /api/alerts/latest` | this week's 4-, 6- and 8-week forecasts per district; the district's alert = the highest of the three; Warnings first; a summary count | 404 if no forecasts yet |

- Every error is JSON, `{"error": ..., "status": ...}`, including unknown `/api/...` paths (404) and POST (405). Field names say their units; a `units` block explains them.
- **11 API tests** in `tests/test_api.py` on a temporary SQLite database with two made-up districts: each endpoint, case-insensitive names, oldest-first history, every error status, the "highest level over horizons" rule, and **503 on an empty database**. All 17 tests pass.
- Tried the real server with `curl`: `/api/health` 200, unknown district 404, the dashboard page 200.
- README: running order of all scripts, MySQL setup, and each endpoint with a real example request and response.

**Why this way**
- **Bound parameters only.** The district name goes into the query as a parameter (`func.lower(name) == value`), never into SQL text, so it cannot change the query.
- **Status codes that say whose fault it is:** 400 the request is wrong, 404 the thing does not exist, 503 the server cannot reach its data.
- **The district's alert is the highest over 4–8 weeks**, because the project promises warnings 4 to 8 weeks ahead; a Warning at 6 weeks should not be hidden by a Normal at 4.

```python
found = conn.execute(select(districts.c.id, districts.c.name)
                     .where(func.lower(districts.c.name) == name.strip().lower())).first()
if found is None:
    raise ApiError(404, f"unknown district '{name}'; see /api/districts")
```

**Still to do for the "Done when" list**: load into **MySQL** itself (the user's steps in the README), then rerun `load_db` and `predict --backfill` against it. Done 2026-11-13, see Step 6.4.

> [!tip] What to remember
> - Write database code that does not care which database it talks to; switch with one setting.
> - Load in one transaction, and make every writer safe to run twice.
> - Check a new data path against an old one: the backfill matched the Phase 5 forecasts to 0.005.
> - Every error a client can cause gets a JSON body and a 4xx code; server-side trouble gets 5xx.

**Check yourself**
1. Why is population in its own table rather than a column of `districts`?
2. The newest case week is 2–8 Nov. Why is the forecast made "in" 9–15 Nov, and why does it not use the 2–8 Nov count?
3. What does `/api/predictions?district=Atlantis` return, and why that status?

> [!success]- Answers
> 1. It changes every year. One column could hold only one year; a row per year in `districts` would repeat the name and coordinates 21 times.
> 2. That is the week the forecast is run in, after the newest bulletin. The delay rule says the newest case week that is always available is two weeks before the origin, because in some weeks the bulletin appears 10 days late; the model was trained and tested that way, so it must run that way.
> 3. 404 with `{"error": "unknown district 'Atlantis'; see /api/districts", "status": 404}`: the request is well formed but asks for something that does not exist. A missing district parameter would be 400.

---
## Step 6.4 — Moving to MySQL 8
> [!important] Goal
> Run the database on MySQL 8 as the roadmap asks, without disturbing the XAMPP MySQL server the user needs for another project, and prove MySQL holds exactly the same data as SQLite.

**What was done (2026-11-13)**
- **First attempt failed.** `Start-Service MySQL80` gave "Failed to start service". MySQL 8's own error log needs administrator rights, so the cause was found from the outside: the Windows Application log showed `c:\xampp\mysql\bin\mysqld.exe` (MariaDB 10.4, XAMPP) listening on **port 3306**, the port MySQL 8 also wanted.
- **Choice:** XAMPP's MariaDB was needed for a web project, so both servers had to run together. Compared:

| | MySQL 8 on port 3307 (chosen) | XAMPP MariaDB on 3306 |
|---|---|---|
| Phase 8 automation | Windows service, starts with the computer | Runs only when XAMPP is opened |
| Separation | Own server | Shared with the web project |
| Version | Current (8.0.44) | MariaDB 10.4, support ended 2024, a crashed system table |

- **Set-up by the user** (needs the MySQL root password, so not done by Claude): `port=3307` in `C:\ProgramData\MySQL\MySQL Server 8.0\my.ini` (both lines, edited as administrator), `Restart-Service MySQL80`, `Set-Service MySQL80 -StartupType Automatic`, database `dengue` and user `dengue_app` with rights on `dengue.*` only, and `.env` with `DATABASE_URL=mysql+pymysql://dengue_app:…@localhost:3307/dengue`.
- Loaded: `python -m src.load_db` (6 s), `python -m src.predict --backfill` (8 s).

**Checked (2026-11-13)**

| Check | Result |
|---|---|
| Connection | MySQL 8.0.44, port 3307, database `dengue`, user `dengue_app@localhost`; `.env` ignored by Git |
| Rows | Same as SQLite: 25 / 525 / 1,037 / 25,925 / 25,925 / 11,625 |
| **MySQL = SQLite, value by value** | Cases, weather and all predictions: largest difference **0.0** (after the fix below) |
| Keys | InnoDB; 6 foreign keys; unique key `one_forecast_per_model` |
| Foreign key enforced | Inserting a case row for district 999 is refused (MySQL error 1452) |
| Re-running `predict.py` | Still 11,625 rows: it replaces, never duplicates |
| Backfill vs Phase 5 test forecasts | 3,700 rows, largest difference 0.005 (rounding) |
| API on MySQL | All four endpoints 200; unknown district 404 |
| Tests | 17 pass |

**Problems and fixes**

| Problem | What was tried | Fix |
|---|---|---|
| MySQL 8 would not start | — | XAMPP's MariaDB held port 3306; MySQL 8 moved to 3307 |
| The port edit did not take at first: `my.ini` still said 3306, and MySQL 8 started on 3306 because XAMPP had been stopped | — | The file must be saved with administrator rights (Notepad "Run as administrator", or VS Code "Retry as Admin") |
| `.env` was missing, so the code quietly used SQLite | Looked for `.env.txt` and other names | Created with `Copy-Item .env.example .env` |
| **54 of 11,625 forecasts differed by 0.01 between MySQL and SQLite** | Compared the tables column by column | SQLAlchemy `Float` becomes MySQL `FLOAT`, **single precision** (about 7 digits): cases and weather lost a little precision, features read back slightly differently, and a few forecasts rounded the other way. Changed every column to `Double` (`DOUBLE` in MySQL, `REAL` in SQLite); added `python -m src.load_db --rebuild`, because `create_all` does not change existing columns. After the rebuild: difference 0.0 |

> [!tip] What to remember
> - "Failed to start service" hides the reason. Look for what holds the port (`Get-NetTCPConnection -LocalPort 3306`) and read the event log.
> - Two database servers can live on one computer if each has its own port.
> - In MySQL, `FLOAT` is single precision. Use `DOUBLE` for measurements unless storage really matters.
> - A silent fallback (SQLite when `.env` is missing) is convenient but can hide a missing file: print which database is in use, as `load_db` does.
> - Compare a new database with the old one value by value, not only by row counts; the row counts were identical while 54 values differed.

**Check yourself**
1. Why did MySQL 8 refuse to start, and why was moving it the better fix than stopping XAMPP?
2. Why did 54 forecasts differ by 0.01 between MySQL and SQLite, when the code was the same?
3. Why is `--rebuild` needed after changing a column type in `src/db.py`?

> [!success]- Answers
> 1. XAMPP's MariaDB already listened on port 3306. The web project needs XAMPP, so stopping it would break that project; on 3307 both run at once, and MySQL 8 can start with Windows for the weekly pipeline.
> 2. `Float` became single-precision `FLOAT` in MySQL, so stored cases and weather were rounded to about 7 significant digits. The features read back were very slightly different, and a few forecasts crossed a rounding boundary at the second decimal.
> 3. `metadata.create_all()` only creates tables that do not exist; it never alters an existing column. The tables must be dropped and created again, then refilled.
