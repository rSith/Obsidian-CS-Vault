---
type: project-note
project: Dengue Early-Warning
status: draft
tags: [project, ml]
---
# Phase 00 — Setup and Domain Knowledge
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 1–2, target finish 2026-10-25
> **Study notes:** [[00.00 Setup and Domain Knowledge]] · **Revision:** [[00.99 Summary - Setup and Domain Knowledge]]

> [!abstract] Phase goal
> A clean project workspace and enough understanding of dengue to make sensible modelling choices later.

## Done when
- [ ] Repository created with folder structure, README and `.gitignore` (local repository done; GitHub repository not created yet)
- [ ] Virtual environment works from `requirements.txt`
- [ ] Notes from at least 3 papers saved in `docs/literature.md`
- [ ] Problem statement written

---
## Step 0.1 — Check the plan against the real data sources
> [!important] Goal
> Find out, before building anything, whether weekly district case data exists and how late it is published.

**What was done (2026-10-08)**
- Read the 10-phase roadmap and looked up the sources it names.
- **Weekly Epidemiological Report (WER):** the newest issue on 8 Oct covered 15–21 Aug, about 7 weeks behind. Its reporting weeks run Saturday to Friday.
- **National Dengue Control Unit:** a daily update PDF dated 6 Oct and weekly updates up to week 37, so it is faster than the WER.
- **`denguedatahub` R package:** already holds weekly cases per district from 2006, compiled from the WER.
- Found two similar projects to read in this phase (links are in `docs/literature.md`).
- All of this is recorded in the repository at `docs/data.md`.

**Why this matters**
The roadmap predicts 4 weeks ahead using "cases 1, 2, 3 and 4 weeks ago". If the newest published week is 7 weeks old, those numbers do not exist on the day the forecast is made. A model tested on history can use them, because the history file is complete, but the live system cannot. The test would then report an accuracy the real system can never reach.

**Still open**
- Which source feeds the live system (WER or the Dengue Control Unit updates).
- Whether to revise the roadmap for the reporting delay and to collect all districts from the start.

> [!tip] What to remember
> - Check how late a data source publishes before designing features around it.
> - "In the historical file" is not the same as "available on the day of the forecast".
> - The reports list 26 reporting units, because Kalmunai is reported separately from Ampara. The map and population data use 25 districts.

**Check yourself**
1. The newest case report is 7 weeks old and you want a forecast for 4 weeks from today. How many weeks beyond your newest data point is that?
2. Why does a backtest look better than the live system if it uses numbers that were published later?

> [!success]- Answers
> 1. 11 weeks: 7 weeks to reach today, plus 4 more.
> 2. It gives the model information that nobody had on the forecast day. The live system never gets that information, so its real error is larger than the test shows.

---
## Step 0.2 — Project folder structure
> [!important] Goal
> One layout, kept for the whole project, so every file has an obvious home.

**What was done (2026-10-08)**
Created `dengue-early-warning/` with this structure. The Python files in `src/` hold only a description for now; the code is written in later phases.

```
dengue-early-warning/
├── data/raw/          downloaded files, never edited
├── data/processed/    cleaned tables
├── notebooks/         exploration
├── src/               reusable code (collectors, features, training, alerts, prediction, pipeline)
├── tests/             pytest tests
├── models/            trained models
├── reports/figures/   saved plots
├── app/               Flask API and dashboard
└── docs/              problem statement, literature, data dictionary, progress log
```

**Why this way**
- **`data/raw/` and `data/processed/` are separate.** Raw files are never edited, so every cleaned table can be rebuilt by re-running the code. A cleaning mistake costs a re-run, not the original data.
- **`notebooks/` and `src/` are separate.** Notebooks are for trying things out. Anything the weekly pipeline needs must live in `src/`, because code in a notebook cannot be imported or tested.
- **`pytest.ini` contains `pythonpath = .`** so a test can write `from src.features import ...` without extra setup.
- **`.gitkeep` files** sit in the empty folders. Git does not track empty folders, so an empty placeholder file keeps the folder in the repository.

> [!tip] What to remember
> - Raw data is read-only. Cleaning always writes a new file.
> - Explore in a notebook, then move working code into a function in `src/`.

**Check yourself**
1. Why should you never fix a typo directly in a raw data file?
2. What problem does `.gitkeep` solve?

> [!success]- Answers
> 1. The fix would be invisible and could not be repeated. If the file is downloaded again, the typo returns. A fix written in code is recorded and runs every time.
> 2. Git ignores empty folders. The placeholder file makes the folder appear in the repository so the layout survives a fresh clone.

---
## Step 0.3 — Keep data, models and secrets out of Git
> [!important] Goal
> Commit code and documentation only. Large files, private settings and passwords stay on the computer.

**What was done (2026-10-08)**
Wrote a `.gitignore`. The important part:

```gitignore
.venv/
.env
data/raw/*
!data/raw/.gitkeep
```

Also added `.env.example`, which lists the settings the project needs without real values.

**Why this way**
- **`.venv/`** can be recreated from `requirements.txt`, so it does not belong in Git.
- **`.env`** will hold the database password from Phase 6. A password pushed to GitHub is public for good, even if it is deleted later.
- **Data files** are large, and permission to redistribute the case data has not been confirmed. The collector script is committed instead, so anyone can download the data themselves.
- **`data/raw/*` then `!data/raw/.gitkeep`:** the first line ignores everything inside the folder, and the `!` line makes one exception. Writing `data/raw/` would ignore the folder itself, and Git would then never look inside it, so the exception could not work.

**Checked:** `git ls-files --others --exclude-standard` lists the `.gitkeep` files, so the exceptions work. The ignore rules have not been tested with a real data file yet.

> [!tip] What to remember
> - `.env` holds real values and is ignored. `.env.example` holds the variable names and is committed.
> - To keep one file in an ignored folder, ignore `folder/*` and then add `!folder/file`.

**Check yourself**
1. Why is deleting a password in a later commit not enough?
2. What is the difference between `data/raw/` and `data/raw/*` in `.gitignore`?

> [!success]- Answers
> 1. Git keeps every earlier version, so the password is still in the history. It has to be changed, not just deleted.
> 2. `data/raw/` ignores the folder, so Git does not look inside and no exception can apply. `data/raw/*` ignores the contents, which leaves room for a `!` exception.

---
## Step 0.4 — Requirements file and local Git repository
> [!important] Goal
> Record which packages the project needs, and start version control.

**What was done (2026-10-08)**
- `requirements.txt` lists the seven Phase 0 packages: pandas, numpy, matplotlib, seaborn, scikit-learn, requests, jupyter. Packages for later phases are in the file but commented out, grouped by phase.
- Ran `git init -b main` to create a local repository. Nothing is committed yet.
- Found on this computer: Python 3.13.2 and Git 2.46.0.

**Why this way**
- Installing only what the current phase needs keeps the environment small, and each package is learned when its phase starts.
- Versions are not pinned yet. Before saving a trained model in Phase 4, pin them, because a model saved with one scikit-learn version may not load with another.

> [!tip] What to remember
> - `requirements.txt` is the recipe; `.venv/` is the result. Commit the recipe, never the result.

---
## Next steps
- [ ] Create the virtual environment and install the packages → [[00.02 Python Virtual Environments]]
  ```powershell
  python -m venv .venv
  .venv\Scripts\Activate.ps1
  pip install -r requirements.txt
  ```
- [ ] Make the first commit, create the GitHub repository `dengue-early-warning` and push → [[00.01 Git Branching and Commit Messages]]
- [ ] Read about dengue transmission and Sri Lanka's two monsoon seasons → [[00.03 Dengue Transmission and the Mosquito Life Cycle]] · [[00.04 Dengue in Sri Lanka - Seasonality and Burden]]
- [ ] Write notes on at least 3 papers in `docs/literature.md` → [[00.05 Reading Research Papers]]
- [ ] Write the problem statement in `docs/problem-statement.md`
- [ ] Decide the two open points in Step 0.1 → [[01.06 Reporting Delay and Data Availability]]
