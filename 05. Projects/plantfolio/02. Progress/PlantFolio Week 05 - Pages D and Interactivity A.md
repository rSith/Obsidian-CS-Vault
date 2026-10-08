---
type: project-note
project: PlantFolio
status: stub
tags: [project, web]
---
# PlantFolio Week 05 — Pages D and Interactivity A
> [!info] [[PlantFolio - Project Home]] · Stage F · 2–8 Nov 2026
> **Study notes:** [[02.00 Interactivity and Responsive Design]] · **Revision:** [[02.99 Summary - Interactivity and Responsive Design]]

> [!abstract] Week goal
> The last two pages, then the first JavaScript: tabs, modals, toasts and the grid/list toggle. JavaScript in this stage changes what is shown and saves nothing.

## Tasks
| Page / feature | Task | Notes | Module |
|---|---|---|---|
| P12 Create Listing | COM-06 (UI) | Pick-a-plant strip, Swap/Free/Either, Looking for, location and contact-sharing choices, live preview card | MOD-08 |
| P13 My Exchanges | COM-10 (UI) | Section tabs, listing selector, interest table with requester rating and New/Seen tag, rating prompt | MOD-09 |
| Tabs | | P05 tabs, P09 Plants/People, P13 sections, P14 profile tabs: one shared script | MOD-01 |
| Modals S4, S5, S6 | COM-12, CORE-19, CORE-20 (UI) | Rate a trader, Log a health update, Delete confirmation; close on ×, Cancel, scrim, Esc | MOD-01 |
| Toasts with Undo | CORE-23 (UI) | "Watered Monty · next on 7 Oct — Undo"; auto-hide | MOD-06 |
| Grid / list toggle | CORE-14 (UI) | My Garden list view becomes a table | MOD-04 |

- [ ] P12 Create Listing (`listing-form.php`)
- [ ] P13 My Exchanges (`my-exchanges.php`)
- [ ] Shared tabs script
- [ ] `partials/modal.php` and the three modals
- [ ] Toast with Undo
- [ ] Grid / list toggle on My Garden

## Learn this week
- How the scripts are organised, and why forms work without them → [[02.01 Progressive Enhancement and Script Organisation]]
- DOM events, `classList`, `dataset` → [[07.03 DOM Manipulation and Events]]
- Tabs, modals, toasts and where the keyboard focus goes → [[02.02 Tabs, Modals, Toasts and Focus]] · [[09.07 Accessible Tab Widget]]

## Checks
- [ ] Every modal closes with ×, Cancel, a click outside and the Esc key
- [ ] Focus moves into a modal when it opens and returns to the button that opened it
- [ ] Tabs can be used with the keyboard
- [ ] No inline `onclick`, scripts load with `defer`, no `console.log` left behind

---
## Work log
Step sections are added here as each step is finished.
