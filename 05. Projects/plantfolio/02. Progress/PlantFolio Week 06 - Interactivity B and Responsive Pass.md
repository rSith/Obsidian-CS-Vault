---
type: project-note
project: PlantFolio
status: stub
tags: [project, web]
---
# PlantFolio Week 06 — Interactivity B and Responsive Pass
> [!info] [[PlantFolio - Project Home]] · Stage F · 9–15 Nov 2026
> **Study notes:** [[02.00 Interactivity and Responsive Design]] · **Revision:** [[02.99 Summary - Interactivity and Responsive Design]]

> [!abstract] Week goal
> Forms that check themselves, filters that produce shareable addresses, photo previews, and every page working properly on a phone.

## Tasks
| Feature | Task | Notes | Module |
|---|---|---|---|
| Client-side validation | | Inline errors on P02, P06, P12, P15 (on blur and on submit); the server re-checks in stage B | all |
| Live username check | CORE-08 (UI) | `fetch('api/check-username.php')` against a mock list of taken names | MOD-03 |
| Filter chips and sort | COM-03, CORE-15 (UI) | Filters build a GET query string so URLs are shareable | MOD-07 |
| Upload preview | CORE-16 (UI) | Preview, drag-and-drop, `accept="image/*" capture="environment"`, 5 MB and type check in JavaScript | MOD-05 |
| Responsive pass | COM-16 | Filters in a bottom sheet, My Exchanges table → stacked cards, floating List a plant, mobile + sheet, inner pages hide the bar | MOD-01 |

- [ ] Client-side validation on P02, P06, P12, P15
- [ ] Live username check (`public/api/check-username.php`)
- [ ] Filter chips and sort that build a query string
- [ ] Upload preview with drag-and-drop
- [ ] Responsive pass on every page

## Learn this week
- Validation in the browser → [[02.03 Client-Side Form Validation]]
- `fetch()` and JSON → [[07.02 JavaScript fetch and Promises]]
- `URLSearchParams` → [[02.04 URLSearchParams and Shareable Filter URLs]]
- `FileReader` and the File API → [[02.05 Upload Preview with the File API]]
- Media queries written mobile-first → [[06.05 Media Queries and Breakpoints]] · [[06.03 Mobile Web, One Web and Mobile First]]
- Phone layouts → [[02.06 Mobile Patterns - Bottom Bar, Sheets and Stacked Tables]]

## Checks
- [ ] A form with errors shows each message beside its field and moves focus to the first one
- [ ] A filtered address can be copied, pasted in a new tab and shows the same results
- [ ] A file over 5 MB or of the wrong type is refused before upload
- [ ] Every page at 375 px: no horizontal scroll, touch targets at least 44 × 44 px
- [ ] Tested on a real phone

---
## Work log
Step sections are added here as each step is finished.
