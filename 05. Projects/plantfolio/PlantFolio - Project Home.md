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
