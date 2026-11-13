---
type: project-note
project: Dengue Early-Warning
status: draft
tags: [project, ml]
---
# Phase 08 — Automation and MLOps
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 21–22, target finish 2027-03-14
> **Study notes:** [[08.00 Automation and MLOps]] · **Revision:** [[08.99 Summary - Automation and MLOps]]

> [!abstract] Phase goal
> The system updates itself every week, runs online, covers all 25 districts, and tells you when the model starts to degrade.

## Steps
- [x] One pipeline script `src/pipeline.py` (done 2026-11-13, Step 8.1): collect weather → collect cases → build features → predict → write alerts → log metrics → [[08.01 Pipeline Scripts and Logging]]
- [x] Schedule it weekly (done 2026-11-13, Step 8.5: Tuesday 07:00, first run 17 Nov) → [[08.02 Scheduling with cron and GitHub Actions]]
- [x] Monitor the model (done 2026-11-13, Step 8.2): weekly error of past forecasts, input drift, a retraining flag → [[08.05 Model Monitoring - Data Drift and Concept Drift]]
- [x] Version each model and store the version with every prediction; write the retraining plan (done 2026-11-13, Step 8.3) → [[08.06 Model Versioning and Retraining]]
- [x] Package the app and MySQL (done 2026-11-13, Step 8.5: both containers healthy) → [[08.03 Docker and Docker Compose]]
- [x] Deploy and confirm `/api/health` responds (done 2026-11-13, Step 8.6: https://dengue-early-warning.vercel.app, Aiven PostgreSQL) → [[08.04 Cloud Deployment Basics]]
- [x] Scale to all 25 districts and check that results hold up (all 25 since Phase 1; tested in Phase 5)

> [!warning] This phase holds more work than two weeks
> If time runs short, do the steps in the order above and stop where you must. Running the dashboard locally for demos is the roadmap's own fallback.

## Done when
- [x] The pipeline runs end to end with one command (2026-11-13: `python -m src.pipeline`, 2 min 46 s)
- [ ] A scheduled run has completed successfully at least twice (2026-11-13: one test start via Task Scheduler, result 0; first timed run Tue 17 Nov)
- [x] The app and database run in Docker and are deployed online (2026-11-13: Docker locally, both healthy; online on Vercel + Aiven PostgreSQL)
- [x] All 25 districts are included
- [x] The monitoring table records weekly error and a retraining flag (2026-11-13: `monitoring_error`)

---
## Work log
Step sections are added here as each step is finished.

## Step 8.1 — The weekly pipeline in one command
> [!important] Goal
> One command that runs the whole weekly update, logs every step, records each run, and fails loudly → [[08.01 Pipeline Scripts and Logging]].

**What was done (2026-11-13)**
- Each script's `if __name__ == "__main__":` block became a `main()` function (no other change), so `src/pipeline.py` can call them in order: collect weather → collect cases → population → build weekly table → load database → predict (live) → monitor.
- **Logging** with Python's `logging` to `logs/pipeline.log` and the screen; each step's start, end and seconds. What the steps `print` is redirected into the log, one whole line at a time.
- **Failing loudly**: any exception is logged with its traceback, the run is marked `failed` in a new `pipeline_runs` table, and the process exits with code 1.
- **Freshness check**: a warning when no new case week arrived since the last run (the bulletin may be late).
- **No automatic retraining** (see Step 8.3).

**Checked (2026-11-13)**

| Run | Result |
|---|---|
| 1 | **Failed at "load database"**, exit code 1, traceback in the log, `failed` row in `pipeline_runs` (see Problems) |
| 2 | **OK** in 2 min 46 s, exit code 0: weather 60 s, cases 90 s, the rest 1–5 s each; warned "no new case week since the last run" |
| 3 (through the scheduler wrapper `scripts/run_pipeline.ps1`) | **OK**, exit code 0 |

**Problems and fixes**

| Problem | What was tried | Fix |
|---|---|---|
| `load_db` failed on MySQL: error **1451**, cannot delete a district that forecasts point to | — | It deleted and refilled `districts` every time. On the first MySQL load `predictions` was empty, so it never showed. Now districts are synced (added or updated, never deleted), so their ids stay fixed |
| The tests had not caught it | — | **SQLite ignores foreign keys** unless each connection runs `PRAGMA foreign_keys = ON`. Added that in `get_engine()`; a new test reloads with a forecast present, and the old behaviour is now refused by SQLite too |
| `print(a, b, c)` became three log lines | — | `print` calls `write()` once per piece; the log writer now waits for the newline |

---
## Step 8.2 — Monitoring: forecast error and input drift
> [!important] Goal
> Each week, measure how wrong the forecasts were once the real counts arrive, check whether the inputs still look like the training data, and raise a flag for retraining → [[08.05 Model Monitoring - Data Drift and Concept Drift]].

**What was done (2026-11-13)**
- `src/monitor.py` and two tables:
  - **`monitoring_error`**: per target week, horizon and model version, the MAE over the districts, the MAE on log cases, the 8-week rolling MAE, the reference (the model's MAE on the Phase 5 test: 26.3 / 28.7 / 29.8 cases at 4 / 6 / 8 weeks), and **`retrain_flag`** = rolling MAE > 1.3 × reference (the roadmap's rule).
  - **`drift_checks`**: the **population stability index** (PSI) of `log_cases_lag2`, `rain_8w` and `humidity_4w` over the last 8 weeks, against the **same weeks of the year** in the training years (to 2023). PSI under 0.1 stable, 0.1–0.25 moderate, above 0.25 major.

$$\text{PSI} = \sum_{\text{bins}} (a_i - e_i)\,\ln\frac{a_i}{e_i}$$

with $e_i$ and $a_i$ the shares of training and recent values in each decile of the training values.

**Results (2026-11-13)** — monthly mean of the weekly 4-week-ahead MAE

| Month | MAE | MAE (log) | 8-week MAE | Flag |
|---|---|---|---|---|
| 2026-03 | 14.4 | 0.34 | 14.4 | — |
| 2026-05 | 37.2 | 0.50 | 27.6 | — |
| 2026-06 | **152.8** | **0.95** | 111.3 | **yes** |
| 2026-07 | **144.6** | **0.90** | **157.9** | **yes** |
| 2026-09 | 28.8 | 0.50 | 47.3 | yes |
| 2026-11 | 17.8 | 0.51 | **17.3** | no |

- The flag fired in the **June–October 2026 epidemic** and in **February 2024** (end of the 2023–24 epidemic): the same periods the error analysis found ([[Phase 05 - Evaluation and Alerts]], Step 5.3). The log error doubled too, so it is real degradation, not only larger numbers. It cleared in November.
- **Input drift**: `log_cases_lag2` PSI **0.66, major**: cases in Sep–Nov 2026 are far above the same weeks in 2008–2023 (dengue has risen; 2026 is a record year). `rain_8w` 0.08 and `humidity_4w` 0.06, stable.

**Why this way**
- **Error once the truth arrives** catches concept drift (the link between inputs and cases changing); **PSI on inputs** catches data drift before any truth exists. One metric of each, as lesson 08.05 advises, before adding more.
- **Same weeks of the year** as the reference: comparing the rainy season with the whole year would call every monsoon "drift".
- **Recomputed in full each run**: the monitoring table is small and always consistent with `predictions`.

---
## Step 8.3 — Model versions and the retraining plan
> [!important] Goal
> Know which model made every forecast, never lose an old model, and decide in advance when and how to retrain → [[08.06 Model Versioning and Retraining]].

**What was done (2026-11-13)**
- Every prediction already stores `model_version` (`ridge-h4-to-2023-12-30`: model, horizon, last training target week).
- `archive_model()` in `src/train.py`: before a new model replaces `models/ridge_h4.joblib`, the old file moves to `models/archive/<version>.joblib`. Checked on a copy: the archived file has the right version and training date.
- `docs/retraining.md`: when (the flag that **stays on outside an epidemic**, major input drift for several weeks, every six months, any data change), how (new train / validation / test periods, retrain, compare with the old model, score the new test once, update the monitoring reference), and how to roll back.
- **The pipeline never retrains on its own.** A new model changes what officials are told, so a person decides.

---
## Step 8.4 — Scheduling and Docker (files ready)
> [!important] Goal
> Run the pipeline every week without anyone starting it, and package the app and database in containers → [[08.02 Scheduling with cron and GitHub Actions]] · [[08.03 Docker and Docker Compose]].

**What was done (2026-11-13)**
- **Scheduling: Windows Task Scheduler, not GitHub Actions.** The database is MySQL on this PC; a cloud runner could not reach it. `scripts/run_pipeline.ps1` runs the pipeline with the project's environment and passes the exit code back; `scripts/register_task.ps1` creates a task for **Tuesday 07:00**, run as soon as possible if the PC was off (the bulletin for the week ending Sunday usually appears 5–8 days later). Wrapper tested: exit code 0. **Registering the task waits for the user's OK**, because it changes the system.
- **Docker**: `Dockerfile` (python 3.13-slim, only `app/` and `src/db.py`, packages in `requirements-app.txt`, **gunicorn** on port 8000, non-root user, health check on `/api/health`); `docker-compose.yml` with a `mysql:8.0` container on host port **3308** (3306 is XAMPP, 3307 the local MySQL 8), a named volume for its data, and passwords from `.env`; `.dockerignore` keeps `.env`, data and models out. `docker compose config` validates the file. **Building waits for the user's OK**: it downloads the two base images.
- **Deployment online** needs an account with a host, which only the user can create; running locally is the roadmap's fallback.
- **All 25 districts** have been in every step since Phase 1.

> [!tip] What to remember
> - A pipeline must fail loudly: exit code, log, and a record the next person can see.
> - Test with the database you deploy on. SQLite skipped the foreign-key check that MySQL enforced.
> - Monitor two things: error once the truth arrives (concept drift), and inputs before it does (data drift); compare like with like (same season).
> - Keep every model you ever served, named by the version stored with its forecasts.
> - Never retrain automatically in a system that warns people; let monitoring prompt a person.

**Check yourself**
1. Why did the foreign-key bug show up only on the third MySQL session, and never in the tests?
2. The retrain flag was on in July 2026 and off in November. Should the model have been retrained in July?
3. Why is the drift reference "the same weeks of the year in training" and not all training weeks?

> [!success]- Answers
> 1. The first MySQL load ran on an empty database, so deleting districts broke nothing; once `predictions` held rows that pointed to them, MySQL refused. The tests used SQLite, which ignores foreign keys unless `PRAGMA foreign_keys = ON` is set.
> 2. Not just because of the flag: any model's error rises in an epidemic, and this flag cleared when the epidemic ended. The plan says to look at why first; here the stronger reasons to retrain are the major drift in case level and that the models stop at 2023.
> 3. Inputs are seasonal: rain and cases in October are not like the yearly average. Comparing with all weeks would report drift every season; comparing with the same weeks shows only real change.

---
## Step 8.5 — Task registered, Docker running, ready to go online
> [!important] Goal
> Turn on the weekly schedule, run the app and database in containers, and prepare everything for an online host that only the user can sign up to.

**What was done (2026-11-13)** — the user approved all three: register the task, build the images, put it online.

**1. Weekly task.** `scripts/register_task.ps1` created **"Dengue Early Warning - weekly pipeline"**: every **Tuesday 07:00** (`DaysOfWeek` 4 = Tuesday), start as soon as possible if missed, runs as the user while logged on (no password stored). First scheduled run: **Tuesday 17 Nov 2026**. Started once through Task Scheduler to test the task itself: ran 19:21, **Last Run Result 0**.

**2. Docker.**
- Docker Desktop was installed but not running (`dockerDesktopLinuxEngine` pipe missing); started it.
- The container database needs `DB_PASSWORD` and `DB_ROOT_PASSWORD`; generated 24-character random values straight into `.env` (never shown, never committed).
- `docker compose up -d --build`: images `mysql:8.0` (1.1 GB unpacked) and the app (268 MB). Loaded the container database from the host on port 3308: 25,925 weekly rows, 11,625 forecasts.

| Check | Result |
|---|---|
| Containers | `db` healthy, `app` healthy (its own health check calls `/api/health`) |
| `/api/health`, `/api/alerts/latest` | ok; 6 Warning, 14 Watch, 5 Normal (same as MySQL 8) |
| Dashboard page and map file | 200, 200 |
| Process | gunicorn, 2 workers, user `appuser` (not root) |
| Empty database (before loading) | 503 "database unavailable" and `unhealthy`: the error path works too |
| Port from the host | with `PORT=10000` the app answers on 10000 (what Render does) |

**3. Online: prepared.**
- **Hosts checked (2026-11-13):** Render's free web service runs a Dockerfile from GitHub (512 MB, sleeps after 15 idle minutes, about a minute to wake). **Aiven retired its free MySQL tier on 6 Nov 2026**; its free **PostgreSQL** has no time limit and no card. Render's own free Postgres expires after about 30 days. Chosen: **Render (app) + Aiven PostgreSQL (database)**.
- SQLAlchemy Core makes the database swap one setting: added the PostgreSQL driver `psycopg[binary]` 3.2.13 (both requirement files). The local system stays on MySQL 8.
- `src/publish.py` loads the online database from the same CSVs and writes the same forecasts; the weekly pipeline now ends with a **publish online** step that does nothing unless `ONLINE_DATABASE_URL` is in `.env`. The PC does the work; the online app only reads.
- The Dockerfile takes the port from `$PORT`; `districts.geojson` is now committed (Render builds from GitHub, and the file was ignored).
- **Waiting for the user:** create the Aiven PostgreSQL service and the Render web service (accounts and passwords are the user's).

**Problems and fixes**

| Problem | What was tried | Fix |
|---|---|---|
| Docker: "failed to connect to the docker API" | — | The engine runs inside Docker Desktop, which was closed; started it and waited for `docker info` |
| `load_dotenv()` failed with `AssertionError` in a script read from standard input | — | It looks for `.env` by walking up from the calling file, and stdin has none; pass the path, `load_dotenv(".env")` |
| Free MySQL hosting gone | Looked for current free tiers | PostgreSQL online; the code was already database-neutral |

> [!tip] What to remember
> - A container health check that calls the real API endpoint shows a broken database at once (`unhealthy`).
> - Hosts give the port in `$PORT`; a shell-form `CMD` with `exec` reads it and still lets gunicorn receive stop signals.
> - Database-neutral code (SQLAlchemy Core) turned "free MySQL disappeared" into a one-line change.
> - Keep the heavy work where the data is; let the online app only read.

**Check yourself**
1. Why does the Dockerfile's `CMD` use `sh -c "exec gunicorn ..."` instead of a plain list?
2. Why does the online system use PostgreSQL when the local one is MySQL, and what had to change in the code?

> [!success]- Answers
> 1. A plain list does not expand `${PORT}`; the shell does. `exec` then replaces the shell with gunicorn, so gunicorn is process 1 and gets the stop signal directly.
> 2. Free hosted MySQL was retired (Aiven, 6 Nov 2026); free PostgreSQL remains. The code uses SQLAlchemy Core, so only the driver (`psycopg`) and the URL change.

---
## Step 8.6 — Online: Aiven PostgreSQL and Vercel
> [!important] Goal
> Put the dashboard and API online for free, fed every week by the local pipeline.

**What was done (2026-11-13)**
- **Aiven PostgreSQL 18.7**, free plan (1 CPU, 1 GB RAM, 1 GB storage, Asia Pacific). Measured first: the whole MySQL database is **8.2 MB** (predictions 3.0, weather 3.0, cases 2.0) and grows about 30 KB a week, so 1 GB lasts for decades.
- `get_engine()` now accepts the URI exactly as hosts show it (`postgres://...`) and turns it into `postgresql+psycopg://...`.
- `python -m src.publish --backfill` loaded the online database in 2 min 53 s. **Checked: identical to the local MySQL** (11,625 forecasts, same alerts, largest difference 0.0; same weekly cases). The real API run against it: health, alerts, district lookup (case-insensitive `lower()` works in PostgreSQL), 404, dashboard.
- The Docker image Render would build was run locally on `PORT=10000` against Aiven: health ok, same alerts.
- **Render dropped**: its "Free" plan asked for a card to verify the account. **Vercel Hobby** (no card): `vercel_entry.py` (`app = create_app()`), `pyproject.toml` (only the web app's packages; `[tool.vercel] entrypoint = "vercel_entry:app"`; a build step copying `app/static` to `public/static`), `vercel.json` (keeps data, models, notebooks and tests out of the function bundle). The user imported the GitHub repository and set `DATABASE_URL`.
- **Live: https://dengue-early-warning.vercel.app**

**Checked online (2026-11-13)**

| Check | Result |
|---|---|
| `/api/health` | ok; 5.1 s on a cold start, 1.4–2.5 s after |
| `/api/alerts/latest` | 6 Warning, 14 Watch, 5 Normal (same as local) |
| `/api/predictions?district=Atlantis` | 404 |
| Dashboard in Chrome | header "2 – 8 Nov 2026 / week of 9 Nov 2026", 25 districts on the map, 25 table rows, Kandy's Warning sentence, chart drawn, no error |

**Problems and fixes**

| Problem | What was tried | Fix |
|---|---|---|
| Free MySQL hosting gone (Aiven retired it on 6 Nov 2026) | — | PostgreSQL online; SQLAlchemy Core made it a driver and URL change |
| Hidden paste into the script failed (`getpass` on Windows takes Ctrl+V as a control character) | Read the clipboard instead; the copy button had not copied | Accept the URI as Aiven shows it; it was saved to `.env` |
| Render asked for a card for the free plan | — | Vercel Hobby instead |
| On the first Vercel deploy `/static/*` returned 404 | The build step's `public/` copy was not served | Stopped excluding `app/static` from the function bundle, so Flask serves the files; 200 after the automatic redeploy |
| Secrets in the chat (database URI with password; a payment card in a screenshot) | — | Card: not written to any file or commit, screenshot copy deleted, user advised to block it with the bank. Database: user to reset the Aiven password and update `.env` and Vercel |

> [!tip] What to remember
> - Measure the data before worrying about a free tier's size.
> - "Free plan" can still mean "card required"; check before building around a host.
> - A serverless host serves static files differently; test every asset URL after the first deploy, not only the API.
> - Never paste passwords or card details into chats or screenshots; if it happens, rotate them.

**Check yourself**
1. Why can the online app be so small and cheap to run?
2. What did the first live check miss if it had tested only `/api/health`?

> [!success]- Answers
> 1. It only reads: the downloads, model and forecasts run on the local PC, and `src/publish.py` copies the results online once a week. The app needs Flask, SQLAlchemy and a database driver, and the database is 8 MB.
> 2. The dashboard's CSS, JavaScript and map file returned 404, so the page would have loaded without its map and chart.
