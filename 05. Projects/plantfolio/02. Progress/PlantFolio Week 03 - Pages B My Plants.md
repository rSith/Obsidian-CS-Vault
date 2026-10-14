---
type: project-note
project: PlantFolio
status: draft
tags: [project, web]
---
# PlantFolio Week 03 — Pages B: My Plants
> [!info] [[PlantFolio - Project Home]] · Stage F · 19–25 Oct 2026
> **Study notes:** [[01.00 Building Pages]] · **Revision:** [[01.99 Summary - Building Pages]]

> [!abstract] Week goal
> The pages where a member manages their own plants: My Garden, Plant Profile, Add or Edit Plant, and Settings.

## Tasks
| Page | Task | Notes | Module |
|---|---|---|---|
| P04 My Garden | CORE-14, CORE-15 (UI) | Collection tabs with counts and lock icon, search/filter/sort bar, plant cards with next-care hint, card menu | MOD-04 |
| P05 Plant Profile | CORE-18 (UI) | Breadcrumb, gallery, identity and status, actions, care at a glance, tabs (Story, Care guide, Reminders, Photos), reminders card | MOD-05 |
| P06 Add / Edit Plant | CORE-16 (UI) | A full page, not a modal: photo upload area, details, current health, reminder defaults, visibility, sticky save bar | MOD-05 |
| P15 Settings | CORE-26 (UI) | Section menu; Profile, Account and security, Privacy, Reminders, Delete account cards | MOD-10 |

- [ ] P04 My Garden (`garden.php`)
- [ ] P05 Plant Profile (`plant.php?id=`)
- [ ] P06 Add / Edit Plant (`plant-form.php`)
- [ ] P15 Settings (`settings.php`)

## Learn this week
- Card grids, 4:3 images and the sticky save bar → [[01.02 Card Grids, Images and Sticky Bars]]
- Reading `?id=` from the address → [[01.03 Query Strings and GET Parameters in PHP]]
- Looping over rows with a partial → [[00.03 PHP Includes and Partials]]
- Form structure → [[00.05 HTML Forms]]

## Checks for every page
- [ ] Matches its wireframe page and the Figma prototype
- [ ] Works at 375, 768 and 1024 px with no horizontal scroll and no console errors
- [ ] All output escaped with `e()`, images have alt text, inputs have labels

---
## Work log
Step sections are added here as each step is finished.

> [!note] Changed plan (2026-10-14)
> The lecturer needs to see pages soon, so the remaining pages are being built back to back on **one branch, `feature/pages-ui`** (cut from `feature/dashboard-ui` while PR #5 was still open), one page at a time when Ranshitha says "go", with one pull request at the end. Week numbers in this vault stay as the roadmap order.

### Step 3.1 — Mock data for My Garden: collections, Lily and Pearl
> [!important] Goal (CORE-14, UI half)
> Give My Garden (wireframe p11) everything it prints: the collections for the tabs, all eight plants on p11, and a date to sort "Recently updated" by.

**What was done (2026-10-14, branch `feature/pages-ui`, commit `9e88762`)**
- `includes/mock-data.php`:
  - **Lily** (*Spathiphyllum wallisii*, Stable, Living room, water tomorrow) and **Pearl** (*Echeveria elegans*, Thriving, Succulents, water in 6 days) added to `$mock_plants`, which now holds the eight plants in the order of p11.
  - Every plant got a new joined column, **`last_logged_at`**: the date of its newest `health_logs` entry.
  - **`$mock_collections`** (new): Living room 7 · Balcony 6 · Succulents 5 (private, `is_public = 0`). Each row is a `collections` row plus `plant_count`.
  - The `?empty` switch now empties `$mock_collections` too.
- `public/assets/img/mock/lily.svg` and `pearl.svg`: placeholder drawings in the style of the others.
- Eight Lucide icons added for this page: `folder-plus`, `layout-grid`, `list`, `ellipsis`, `pencil`, `trash-2`, `folder-input`, `x`, and listed in `docs/credits.md`.

**Why this way**
- **"Recently updated" needs a date.** The `plants` table has no `updated_at` column. The newest health log is what "updated" means for a plant: a new status, a note or a photo. In stage B that is `MAX(health_logs.logged_at)`, joined in like the species and the collection.
- **Pearl is private** because the Succulents collection is private. A public plant inside a private collection would be confusing, so the mock data follows the same rule Vera already did.
- **Counts versus sample.** The chips say 7 + 6 + 5 = 18 plants, the same 18 as the Dashboard and the header. `$mock_plants` only holds 8 of them, so filtering Balcony shows only Tommy. In stage B both come from the database and agree automatically.

```php
$mock_collections = [
    ['id' => 1, 'user_id' => 1, 'name' => 'Living room', 'is_public' => 1, 'plant_count' => 7],
    ['id' => 2, 'user_id' => 1, 'name' => 'Balcony',     'is_public' => 1, 'plant_count' => 6],
    ['id' => 3, 'user_id' => 1, 'name' => 'Succulents',  'is_public' => 0, 'plant_count' => 5],
];
```

**Problems and fixes**
- A Python helper script failed on a leftover line before it wrote anything; the edit was then made directly in the editor. The first commit message said "six icons" instead of eight and was corrected before anything was pushed.

> [!tip] What to remember
> - A sort option needs a column to sort by. If the table has none, decide which joined value means what the label says.
> - Mock counts and mock lists can disagree; real queries cannot. Note it so nobody reports it as a bug.

**Check yourself**
1. Which SQL would give each plant its `last_logged_at`?
2. Why is `plant_count` not a column of the `collections` table?

> [!success]- Answers
> 1. A `LEFT JOIN` on `health_logs` with `MAX(health_logs.logged_at) AS last_logged_at` and `GROUP BY plants.id` (`LEFT`, so a plant with no logs yet still appears).
> 2. It would have to be updated every time a plant is added, moved or deleted, and could go wrong. `COUNT(*)` on `plants` always gives the true number.

Lessons: [[00.07 Mock Data Shaped Like Database Rows]] · [[04.05 Counts, Averages and Rating Rules in SQL]]

---
