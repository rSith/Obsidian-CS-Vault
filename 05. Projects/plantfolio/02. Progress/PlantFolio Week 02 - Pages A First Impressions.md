---
type: project-note
project: PlantFolio
status: stub
tags: [project, web]
---
# PlantFolio Week 02 — Pages A: First Impressions
> [!info] [[PlantFolio - Project Home]] · Stage F · 12–18 Oct 2026
> **Study notes:** [[01.00 Building Pages]] · **Revision:** [[01.99 Summary - Building Pages]]

> [!abstract] Week goal
> The first pages a visitor meets, built to match their wireframes with mock data: Landing, Sign up and Log in, Dashboard, and the empty-garden state.

## Tasks
| Page | Task | Notes | Module |
|---|---|---|---|
| P01 Landing | CORE-27 | Hero, community collage, How it works in 3 steps, smart-tools teaser; counts from mock data | MOD-10 |
| P02 Sign up · Log in | CORE-07, CORE-09 (UI) | One page, two tabs; password meter; optional location; community panel | MOD-03 |
| P03 Dashboard | CORE-24 (UI) | Greeting and counts, Today's care (due = terracotta, overdue = red), Needs attention, Quick actions, Garden at a glance | MOD-06 |
| S1 Empty garden | CORE-25 (UI) | Shown on Dashboard and My Garden when the mock user has no plants | MOD-06 |

- [ ] P01 Landing (`index.php`)
- [ ] P02 Sign up and Log in (`register.php`, `login.php`)
- [ ] P03 Dashboard (`dashboard.php`)
- [ ] S1 Empty garden (`partials/empty-state.php`)
- [ ] Mark DES-05 Done: the Figma link is already in the README
- [ ] Finish DES-06, DES-07, DES-08 and DES-13 (ERD, schema reconciliation, data dictionary, species data)

## Learn this week
- Semantic HTML → [[00.06 Semantic HTML]]
- Page layout with grid → [[07.01 CSS Layout with Flexbox and Grid]] · [[01.02 Card Grids, Images and Sticky Bars]]
- Forms and labels → [[00.05 HTML Forms]] · [[00.09 Styling Forms with CSS]]
- The page pattern and navigation → [[01.01 Page Skeleton - Header, Footer and Navigation]]
- Showing a different page for an empty garden → [[01.04 Rendering States - Empty, Owner and Visitor Views]]

## Checks for every page
- [ ] Matches its wireframe page and the Figma prototype
- [ ] Works at 375, 768 and 1024 px with no horizontal scroll and no console errors
- [ ] All output escaped with `e()`, images have alt text, inputs have labels

---
## Work log
Step sections are added here as each step is finished.
