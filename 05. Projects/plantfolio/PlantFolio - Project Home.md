---
type: project
stage: building
repo: https://github.com/rSith/plantfolio
tags: [project, web, ml]
---
# PlantFolio
> [!abstract] Goal
> A mobile-responsive web platform where plant lovers build a profile for every plant, track its health, swap plants with others and diagnose problems from a photo. Group coursework for the **Plant Care & Exchange Community** brief, extended with two ML features: plant recognition and leaf-disease detection.

## Where things live
- **Code:** `C:\xampp\htdocs\plantfolio` · GitHub: <https://github.com/rSith/plantfolio>
- **Runs at:** <http://localhost/plantfolio/public/> (XAMPP)
- **Build order:** `docs/ROADMAP.md` in the repository, week by week
- **Design:** the wireframes and proposal PDFs in `docs/`, and the [Figma prototype](https://www.figma.com/design/el5WGQz2oTerXiAfFiKrPS)
- **Tasks:** the PlantFolio Task Sheet (IDs such as `CORE-14`, `COM-07`, `ML-12`), kept outside the repository
- **Notes:** this folder. Start at [[00. PlantFolio Overview]], the map of every note.
  - **Week notes** in `02. Progress/` record what was done: tasks, checks and a work log.
  - **Lessons** in `01. Lessons/` explain what each stage needs you to know.

## Team
Ranshitha (team leader and lead developer) builds every module. Six teammates review, test and present.

## Tech stack (locked)
- **Pages:** plain PHP 8, one file per page in `public/`, no framework
- **Styling:** hand-written CSS with design tokens, no Bootstrap or Tailwind
- **Scripts:** vanilla JavaScript with `fetch()`, no React or jQuery
- **Database:** MySQL (InnoDB, utf8mb4) through PDO prepared statements
- **Local server:** XAMPP (Apache, PHP, MySQL)
- **ML service:** Python, Flask and TensorFlow/Keras, called from PHP with cURL
- **Version control:** Git and GitHub, `main` ← `develop` ← `feature/*`

## Plan
Each stage links the lesson to study; each week links its progress note.

**Stage 0 · Planning and design** (to 4 Oct 2026)
- [x] Week 0 → [[PlantFolio Week 00 - Planning and Design]]

**Stage F · Frontend with mock data** (weeks 1–7, 5 Oct – 22 Nov) · study [[00.00 Project Foundations]], [[01.00 Building Pages]], [[02.00 Interactivity and Responsive Design]]
- [ ] Week 1 → [[PlantFolio Week 01 - Foundation]]
- [ ] Week 2 → [[PlantFolio Week 02 - Pages A First Impressions]]
- [ ] Week 3 → [[PlantFolio Week 03 - Pages B My Plants]]
- [ ] Week 4 → [[PlantFolio Week 04 - Pages C Community]]
- [ ] Week 5 → [[PlantFolio Week 05 - Pages D and Interactivity A]]
- [ ] Week 6 → [[PlantFolio Week 06 - Interactivity B and Responsive Pass]]
- [ ] Week 7 → [[PlantFolio Week 07 - AI Pages and UI Freeze]]

**Stage B · Backend and database** (weeks 8–10, 23 Nov – 13 Dec) · study [[03.00 Database and Accounts]], [[04.00 Core Features]]
- [ ] Week 8 → [[PlantFolio Week 08 - Database, Accounts and Sessions]]
- [ ] Week 9 → [[PlantFolio Week 09 - Core Features and Gate 1]]
- [ ] Week 10 → [[PlantFolio Week 10 - Community, Exchange and Gate 2]]

**Stage M · ML features** (weeks 11–14, 14 Dec – 10 Jan) · study [[05.00 Machine Learning Service]]
- [ ] Week 11 → [[PlantFolio Week 11 - ML Service Skeleton]]
- [ ] Week 12 → [[PlantFolio Week 12 - Training the Models]]
- [ ] Week 13 → [[PlantFolio Week 13 - Prediction Endpoints]]
- [ ] Week 14 → [[PlantFolio Week 14 - AI in the App and Gate 3]]

**Stage D · Testing and delivery** (weeks 15–16, 11 – 24 Jan 2027) · study [[06.00 Testing and Delivery]]
- [ ] Week 15 → [[PlantFolio Week 15 - Testing]]
- [ ] Week 16 → [[PlantFolio Week 16 - Delivery]]

## Related notes
General concepts and web theory are explained once, elsewhere in the vault. The project notes link to them and do not repeat them.
- **Course:** [[00. Course Overview]] (CMIS 3124, Web Designing and E-Commerce)
- **Concepts:** [[Neural Network]] · [[Deep Learning]] · [[04. Concepts/Machine Learning/Classification|Classification]] · [[Model Evaluation]]
- **Other project:** [[00. Dengue Early-Warning Overview]], whose lessons on Git, Flask, SQL and JavaScript are reused here

## Log
- 2026-10-04: repository skeleton committed; first shared components built on `feature/scaffold-ui` (tokens, components, partials, style guide).
- 2026-10-08: notes set up in the vault: overview map, a progress note per roadmap week, and lessons for every stage.
- 2026-10-13: CORE-06 UI built (commit `2b75f83`): shared header, footer and phone bottom bar; `index.php` shows the frame. Left in week 1: move the style guide onto the frame.
- 2026-10-13: extra page `about.php` built (commit `037a4cc`) for the footer links: About, Community guidelines, Contact. Contact details are samples until week 16.
- 2026-10-13: style guide moved onto the shared frame (commit `1090305`); both week 1 "done when" checks pass by screenshot. Next: push, pull request, then week 2.
- 2026-10-14: branch pushed and pull request #2 opened (`feature/scaffold-ui` into `develop`); PLN-07 and PLN-09 confirmed. Week 1 is complete except for merging the pull request.
- 2026-10-14: pull request #2 merged into `develop` (week 1 complete). CORE-27 Landing page UI built on `feature/landing-ui` in four commits (`7a40ed1` … `8bd5f36`): mock data, hero and collage, How it works and smart tools, Fresh on the exchange. Not pushed yet.
- 2026-10-14: pull request #3 (Landing) merged. CORE-07/CORE-09 Sign up and Log in UI built on `feature/accounts-ui` in three commits (`2d37c72`, `5507fa8`, `00512dc`); both pages checked at four widths and with the keyboard. Not pushed yet.
- 2026-10-14: pull request #4 (Sign up / Log in) merged. CORE-24 Dashboard and CORE-25 empty garden UI built on `feature/dashboard-ui` in four commits (`03148e7` … `ba6842e`); preview the empty state with `dashboard.php?empty`. All week 2 pages are now UI built. Not pushed yet.
- 2026-10-14: plan changed: the remaining pages are built one at a time on one branch, `feature/pages-ui` (from `feature/dashboard-ui`, PR #5 still open), with one pull request at the end. CORE-14/CORE-15 My Garden UI built in three commits (`9e88762`, `cd07926`, `ea8c49c`): collections and filters in the address, grid and list view, plant ⋯ menu. Not pushed.
- 2026-10-14: fix on `feature/visitor-preview` (`aeef387`): the visitor preview now lasts across pages (session), and Log in / Sign up switch to visitor mode, so the logo leads visitors to the Landing page. Pushed; pull request still to open.
- 2026-10-14: `feature/visitor-preview` (`?guest` kept in the session) merged into `feature/pages-ui` (`b6621af`). CORE-18 Plant Profile UI built in three commits (`5fca52f`, `b7ed587`, `3f0e04a`), including the read-only view for visitors (CORE-21) and a 404 for missing or private plants. Not pushed.
- 2026-10-14: review fixes (`081b461` on `feature/pages-ui`): Landing, Sign up and Log in share the visitor header; visitor Exchange → Landing `#fresh`; logo → top of Landing; mock Log in / Create account / Log out so a demo can walk from Landing into the member pages.
- 2026-10-14: review fix (`14c0d00`): after Create account the empty garden (S1) now lasts on every page (kept in the session) until Log in or `?member`.
- 2026-10-14: CORE-16 Add / Edit Plant UI built in three commits (`0082fa9`, `57e87e8`, `5c828f9`): add form, edit mode (`?id=`), and the AI species suggestion preview (`?ai`, `?ai=unsure`, ML-13 UI). Not pushed.
- 2026-10-14: option A chosen (`f938a63`): Save plant now opens My Garden and Save changes opens the plant's profile, without saving anything until stage B.
- 2026-10-14: CORE-26 Settings UI built in two commits (`2b3b308`, `e75070d`): five cards that save on their own, with the password check from p22. All four week 3 pages are now UI built. Not pushed.
- 2026-10-14: review fix (`7229d21`): opening the Landing page no longer logs the member out. A new browser starts as a visitor; Log in, Create account and Log out decide; members are sent from Landing, Log in and Sign up to the Dashboard.
