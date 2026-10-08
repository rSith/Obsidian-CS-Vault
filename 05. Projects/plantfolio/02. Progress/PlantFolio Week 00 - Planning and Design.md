---
type: project-note
project: PlantFolio
status: done
tags: [project, web]
---
# PlantFolio Week 00 — Planning and Design
> [!info] [[PlantFolio - Project Home]] · Stage 0 · 28 Sep – 4 Oct 2026
> **Study notes:** [[00.00 Project Foundations]]

> [!abstract] Stage goal
> Decide what to build and how it should look and fit together, before writing any page.

## What exists at the end of this stage
- [x] **Project proposal** (`docs/PlantFolio — Project Proposal.pdf`): scope, objectives, architecture, 10-table database, ML plan, timeline, risks, success criteria. Done 30 Sep 2026.
- [x] **Wireframes and UX structure** (`docs/PlantFolio_Wireframes_UX_Structure.pdf`, 25 pages): sitemap, flows A–D, design system, 15 page wireframes, mobile layouts, shared states S1–S6, build handoff. Done 30 Sep 2026.
- [x] **Clickable Figma prototype**: journeys A–D, 32 desktop and 4 mobile screens, design-system page.
- [x] **Repository skeleton** on GitHub: folder structure, `README.md`, `CONTRIBUTING.md`, `CLAUDE.md`, `.gitignore`, `.gitattributes`, pull-request template, a README in every folder. Committed 4 Oct 2026 (`cc3a874`).
- [x] **Build roadmap** (`docs/ROADMAP.md`, version 2, 4 Oct 2026): frontend first, week by week.
- [x] Milestone **MS-0 passed** on 30 Sep, as recorded in the roadmap.

## Decisions made in this stage
| Decision | Reason given in the project documents |
|---|---|
| **Frontend first:** build all 15 pages with mock data, then wire them | Visible progress from week 1; the UX is settled before any database work; each page teaches HTML, CSS and JavaScript before PHP |
| **Mock data shaped like database rows** | Wiring a page later means swapping the data source, not redesigning the page |
| **Plain PHP, hand-written CSS, vanilla JavaScript** | The stack is locked: no framework, so every line can be explained in the viva |
| **ML as a separate Flask service** | The site must keep working when the ML service is offline; pages fall back to manual entry |
| **Core features before ML, always** | If the backend stage overruns, the ML stage shrinks, never the other way round |
| **`main` ← `develop` ← `feature/*`** | `main` is always a working, demo-ready version |

## The folder structure, and why
| Folder | Holds | Why it is separate |
|---|---|---|
| `public/` | One PHP file per page, plus `assets/` and `uploads/` | The **web root**: the only folder a browser should be able to reach |
| `includes/` | Shared PHP logic: helpers, mock data, later database and login code | Reused by every page; blocked from the browser by `.htaccess` |
| `partials/` | Reusable pieces of a page: plant card, listing card, badges | Written once, shown on many pages |
| `config/` | `config.example.php` (committed) and `config.php` (ignored) | Passwords never reach GitHub |
| `database/` | Schema, seed data, ERD, species data | The database can be rebuilt from files |
| `ml-service/` | The Flask app, notebooks and models | A different language and a different server |
| `docs/` | Proposal, wireframes, roadmap, later the report | |

These are explained in [[00.01 How a PHP Page Is Served]] and [[00.03 PHP Includes and Partials]].

> [!tip] What to remember
> - The wireframes and the Task Sheet decide **what** to build. The roadmap decides **when**.
> - A page is "In progress" while it is UI-only and "Done" only when it is wired, tested and merged.
> - The schedule assumes a submission around late January 2027. Task PLN-04 confirms the date.

**Check yourself**
1. Why does the project build every page with mock data before creating the database?
2. Why is `public/` the only folder Apache should serve?

> [!success]- Answers
> 1. Progress is visible from week 1, the design is settled before database work begins, and because the mock data has the same shape as the future database rows, wiring a page later only swaps where the data comes from.
> 2. Everything outside it (`includes/`, `config/`, `partials/`) holds logic and passwords. If a browser could request those files directly, it could run them out of context or read their contents.
