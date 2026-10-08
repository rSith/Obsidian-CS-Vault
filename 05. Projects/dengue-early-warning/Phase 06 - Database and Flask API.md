---
type: project-note
project: Dengue Early-Warning
status: stub
tags: [project, ml, web]
---
# Phase 06 — Database and Flask API
> [!info] [[Dengue Early-Warning - Project Home]] · Roadmap weeks 17–18, target finish 2027-02-14
> **Study notes:** [[06.00 Database and Flask API]] · **Revision:** [[06.99 Summary - Database and Flask API]]

> [!abstract] Phase goal
> Data, predictions and alerts live in MySQL and are served through a small, documented Flask API.

## Steps
- [ ] Draw the ER diagram, then create the tables: `districts`, `weekly_cases`, `weekly_weather`, `predictions` → [[06.01 Relational Schema Design and Normalisation]]
- [ ] Keep the database password in `.env` → [[06.06 Environment Variables and Configuration]]
- [ ] Load the CSV data into MySQL → [[06.03 SQLAlchemy]]
- [ ] Write `src/predict.py`: load the model, build features for the latest week, predict, apply thresholds, write rows to `predictions`
- [ ] Write the queries the endpoints need → [[06.02 SQL Joins, Aggregation and Indexes]]
- [ ] Build the four endpoints: `/api/districts`, `/api/predictions`, `/api/alerts/latest`, `/api/health` → [[06.04 Flask Routing and JSON APIs]]
- [ ] Return clear JSON with units, and proper status codes for errors → [[06.05 REST API Design and Status Codes]]
- [ ] Document each endpoint in the README with an example request and response; test with `curl` or Postman

## Done when
- [ ] The schema is created and the ER diagram is saved in `docs/`
- [ ] Historical data is loaded into MySQL
- [ ] `predict.py` writes a week of predictions and alerts
- [ ] All 4 endpoints return correct JSON and handle errors
- [ ] The API is documented in the README

---
## Work log
Step sections are added here as each step is finished.
