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

- [x] P04 My Garden (`garden.php`) — UI built 2026-10-14 (not wired)
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
### Step 3.2 — My Garden: header, collection tabs, search, filter, sort and the card grid
> [!important] Goal (CORE-14 and CORE-15, UI half)
> Build wireframe p11, elements 1–6: the page that shows every plant Nimali owns, and lets her narrow it down by collection, name, health and order.

**What was done (2026-10-14, branch `feature/pages-ui`, commit `cd07926`)**
- `public/garden.php` (new):
  - **Header**: "My Garden", "18 plants in 3 collections", **New collection** (secondary) and **Add plant** (primary).
  - **Collection tabs**: *All plants 18 · Living room 7 · Balcony 6 · 🔒 Succulents 5 · + New collection*, as links with `?collection=`.
  - **Toolbar**: a GET form with the search box, *Status* and *Sort* lists, and **Apply**.
  - **Card grid**: the `plant-card` partial for every plant that passes the filters. "2 plants match · Clear filters" appears while a search or status is set.
  - **Empty results**: "No plants match" with Clear filters. **Empty garden** (`?empty`): state S1, as on the Dashboard.
  - A visitor (`?guest`) gets `302` to `login.php`.
- `includes/helpers.php`: new **`query_url($page, $params)`**, which builds an address with a query string and leaves out empty values.
- `public/assets/css/garden.css` (new): header, chip row, toolbar and grid. One card column on a phone, two on a tablet, four on a desktop.
- The `.panel` box moved from `dashboard.css` to `components.css`, because My Garden, Plant Profile and Settings use it too. The Dashboard was re-checked and looks the same.

**Why this way**
- **Every choice lives in the address**, like `garden.php?collection=3&status=sick&sort=name`. A filtered garden can be bookmarked, shared and reloaded, as the wireframes ask. It works with no JavaScript at all.
- **Whitelists.** `filter_input(…, FILTER_VALIDATE_INT)` for the collection id, `in_array(…, true)` for the status, `array_key_exists()` for the sort. A value that is not allowed falls back to the default: `?collection=99&status=evil&sort=x` simply shows all plants.
- **Filters combine.** Each link starts from `$filters`, the current choices, and changes only one. So picking a collection keeps the search, and Clear filters keeps the collection.
- **The PHP filter is a rehearsal of the SQL.** `array_filter()` is the `WHERE`; `usort()` is the `ORDER BY`. In stage B the same checked values go into one prepared query, and the sort is still picked from the whitelist (a column name can never be a `?` placeholder).
- **"Health, worst first"** ranks sick → needs attention → stable → thriving, so the plants that need help come to the top.
- **The lock has words for screen readers**: a visually hidden "Private:" before the name, because an icon alone says nothing to someone who cannot see it.

```php
$plants = array_filter($mock_plants, function ($plant) use ($collection_id, $q, $status) {
    if ($collection_id !== null && $plant['collection_id'] !== $collection_id) return false;
    if ($status !== '' && $plant['health_status'] !== $status) return false;
    // …the search looks in nickname, common_name and scientific_name
    return true;
});
usort($plants, fn ($a, $b) => $b['last_logged_at'] <=> $a['last_logged_at']);   // "Recently updated"
```

**Problems and fixes**
- **The Sort list was cut off on a phone** ("Recently u…"), with the two lists side by side. Now Apply sits beside the search box and each list has its own line. Week 6 moves these filters into a bottom sheet anyway (p23).
- **On a tablet, Apply wrapped to its own line.** The search box had a fixed 320 px; it now takes the leftover room and is limited to 320 px only from 1024 px.
- **Two small Python helper scripts failed** on Windows console encoding after writing the CSS; the files themselves were correct (checked with `git diff --stat`).

> [!tip] What to remember
> - Filters, sorting and tabs in the query string = shareable URLs and no JavaScript needed.
> - Never trust `$_GET`: check each value against the list of allowed values and fall back to a default.
> - `array_filter` ≈ `WHERE`, `usort` ≈ `ORDER BY`.

**Check yourself**
1. What does `garden.php?collection=99` show, and why?
2. Why must the sort column come from a whitelist even after stage B uses prepared statements?
3. Why is there an **Apply** button when the wireframe has none?

> [!success]- Answers
> 1. All plants. 99 is not one of Nimali's collection ids, so the check sets `$collection_id` to null, which means "All plants".
> 2. Placeholders (`?`) can only stand for **values**, not for column names or `ASC`/`DESC`. The `ORDER BY` part has to be written into the SQL, so it must only ever be one of our own fixed strings.
> 3. Without JavaScript, choosing an option in a `<select>` does not send the form. Apply sends it. In week 6 a few lines of JavaScript will submit on change and hide the button.

Lessons: [[01.03 Query Strings and GET Parameters in PHP]] · [[04.04 Search, Filters and Pagination]] · [[01.02 Card Grids, Images and Sticky Bars]] · [[01.04 Rendering States - Empty, Owner and Visitor Views]]

---
### Step 3.3 — My Garden: the ⋯ menu and the list view, and checks for the whole page
> [!important] Goal (CORE-14, UI half)
> Add the two things from p11's UX notes: the card menu (Edit, Move to collection, List for exchange, Delete) and a list view that shows the plants as a table.

**What was done (2026-10-14, branch `feature/pages-ui`, commit `ea8c49c`)**
- `partials/plant-menu.php` (new): the ⋯ menu as a `<details>` dropdown. Edit → `plant-form.php?id=42`, Move to collection → `plant-form.php?id=42#collection`, List for exchange → `listing-form.php?plant=42`, **Delete** in red (does nothing until week 5's confirmation dialog).
- `partials/plant-card.php`: takes an optional **`$menu = true`**; only My Garden passes it, so the style guide and other pages are unchanged.
- `includes/helpers.php`: new **`care_hint($plant)`**, "Water today" / "Overdue 2 days" / "In 3 days", now shared by the card and the table.
- `garden.php`: **`?view=list`** and a grid/list switch at the right end of the toolbar. The list is a real `<table>` (photo, plant, collection, status, next care, menu).
- `components.css`: a `<button>` styled as a menu item, the red `.menu-item-danger`, and the menu's place on the card photo. `garden.css`: the switch and the table.
- **Checks** (no code change): no PHP errors in any variant; the open menu fits inside its card; screenshots at 375, 768 and 1280 px in grid and list view, filtered and empty; the Dashboard, Landing page and style guide still load without errors.

**Why this way**
- **One menu partial, two places.** The card and the table row need the same four actions. If Delete gets its dialog in week 5, it changes in one file.
- **The menu sits above the card link.** The whole card is one big link (the `::after` trick from week 1). `z-index: 1` lifts the menu above it, so a click on ⋯ opens the menu instead of the profile.
- **Edit and Delete are different elements.** Edit, Move and List *go to another page*, so they are links. Delete *does something here*, so it is a `<button>`, and it is red because it destroys data.
- **A table on a desktop, stacked rows on a phone.** Five columns do not fit in 375 px. CSS Grid with `grid-template-areas` re-draws each row as photo · name / status / next care · menu. From 768 px it becomes a normal table again. The HTML is the same on both.
- **The view is in the address too** (`?view=list`), and the search form carries it in a hidden field, so Apply does not switch you back to the grid.

```css
.garden-table tr {                     /* phone: each row is a small grid */
  display: grid;
  grid-template-columns: 48px 1fr auto;
  grid-template-areas:
    "photo name   menu"
    "photo status menu"
    "photo care   menu";
}
```

**Problems and fixes**
- **Columns were shifted by one on the desktop table**: Status sat under "Collection". The phone rule `.garden-table .cell-collection { display: none }` is more specific than `.garden-table td { display: table-cell }`, so the cell stayed hidden. Fixed by repeating the same two classes in the tablet rule.
- **Photos were squeezed** in the narrow photo column, because `img { max-width: 100% }` lets an image shrink with its cell. Fixed with `max-width: none` on the table thumbnail.
- **"Overdue 2 days" ran under the ⋯ button on a phone.** Status and next care now have their own lines.
- Two commit messages were corrected before anything was pushed (wording only).

> [!tip] What to remember
> - Specificity decides which rule wins, not the order of media queries alone: two classes beat one class plus an element.
> - Link to go somewhere, button to do something, red for destroying.
> - One HTML table can look like a table on a desktop and like cards on a phone, using only CSS.

**Check yourself**
1. Why does clicking ⋯ not open Monty's profile, even though the whole card is a link?
2. Which rule kept the Collection column hidden, and why did it win?
3. Why is Delete not a link to `plant.php?id=42&delete=1`?

> [!success]- Answers
> 1. The card link's `::after` covers the card, but the menu has `position: absolute` and `z-index: 1`, so it sits on top and receives the click.
> 2. `.garden-table .cell-collection { display: none }`: two classes (specificity 0,2,0) beat `.garden-table td` (one class and one element, 0,1,1), whatever the media query.
> 3. A link sends a GET request, and GET must never change data: a browser preloading links, or someone sharing the address, could delete a plant. Deleting will be a POST form with a CSRF token, after a confirmation dialog that names the plant.

Lessons: [[01.05 Dropdown Menus with details and summary]] · [[02.06 Mobile Patterns - Bottom Bar, Sheets and Stacked Tables]] · [[03.05 CSRF Tokens]]

---

## Open points
- **My Garden (P04):** the first card is Tommy, not Monty as on p11, because "Recently updated" sorts by the newest health log and Tommy had a Health Check yesterday. The chips say 7 + 6 + 5 = 18 plants while the mock data holds 8, so a collection shows fewer cards than its count until stage B. New collection (button and chip) and Delete do nothing yet. **Apply** is not on p11; it is needed until week 6 submits the lists on change. Lily, Pearl and their "Peace lily" / "Mexican snowball" common names are my additions from p11's pictures.
