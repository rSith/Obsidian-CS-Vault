---
type: project-note
project: PlantFolio
status: draft
tags: [project, web]
---
# PlantFolio Week 03 — Pages B: My Plants
> [!info] [[PlantFolio - Project Home]] · Stage F · 19–25 Oct 2026 · branch `feature/pages-ui` (commits `9e88762` … `e75070d`, local only, one pull request at the end)
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
- [x] P05 Plant Profile (`plant.php?id=`) — UI built 2026-10-14, owner and visitor views (not wired)
- [x] P06 Add / Edit Plant (`plant-form.php`) — UI built 2026-10-14: add, edit (`?id=`) and AI suggestion preview (`?ai`, `?ai=unsure`) (not wired)
- [x] P15 Settings (`settings.php`) — UI built 2026-10-14: five cards, each saving on its own (not wired)

## Learn this week
- Card grids, 4:3 images and the sticky save bar → [[01.02 Card Grids, Images and Sticky Bars]]
- Reading `?id=` from the address → [[01.03 Query Strings and GET Parameters in PHP]]
- Looping over rows with a partial → [[00.03 PHP Includes and Partials]]
- Form structure → [[00.05 HTML Forms]]
- The ⋯ plant menu → [[01.05 Dropdown Menus with details and summary]]
- The list view as stacked rows on phones → [[02.06 Mobile Patterns - Bottom Bar, Sheets and Stacked Tables]]
- Owner-only pages and the 404 for private plants → [[03.07 Access Control and Ownership Checks]]
- Save, redirect and the green "Saved" message → [[03.06 Form Handling - Validation, Redirects and Flash Messages]]
- Confidence words on the AI suggestion → [[05.07 Confidence Thresholds and Honest AI Results]]

## Checks for every page
- [ ] Matches its wireframe page and the Figma prototype
- [ ] Works at 375, 768 and 1024 px with no horizontal scroll and no console errors *(so far: screenshots at 375 and 1280 px for every page, and 768 px for My Garden and Settings; the pages have no JavaScript yet. Still to do: 768 px for P05 and P06, and a real phone.)*
- [ ] All output escaped with `e()`, images have alt text, inputs have labels *(written that way, not yet checked page by page)*

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
> [!note] Branch housekeeping (2026-10-14)
> Another session had made a fix on its own branch, `feature/visitor-preview` (commit `aeef387`): `?guest` now lasts from page to page until `?member`, kept in the session like a real login. The working folder was left on that branch. Ranshitha chose to switch back to `feature/pages-ui` and merge the fix in (merge commit `b6621af`, no conflicts). My Garden was re-checked afterwards.

### Step 3.4 — Mock data for the Plant Profile: species guide, reminders, story, photos, likes
> [!important] Goal (CORE-18, UI half)
> Give the Plant Profile (wireframe p12) everything it shows about one plant: care needs, all its reminders, its dated story, its photos and its likes.

**What was done (2026-10-14, branch `feature/pages-ui`, commit `5fca52f`)**
- `includes/mock-data.php`, a new "Plant Profile" section:
  - **`$mock_species`**: eight `plant_species` rows (light, water, humidity, notes, pet safety, default water and fertilise intervals), **keyed by id**.
  - **`$mock_plant_reminders`**: all twelve reminders of Nimali's plants (Monty: water every 7 days, fertilise every 30).
  - **`$mock_health_logs`**: the story, 19 entries, newest first. An entry from an AI tool has `source = 'ml'` and carries its prediction (Monty: "Species identified by AI, 91%"; Tommy: early blight, 92%).
  - **`$mock_plant_photos`**: gallery photos (four for Monty).
  - **`$mock_like_counts`**: likes per public plant (Monty 12).
- Five Lucide icons: `eye`, `droplets`, `paw-print`, `image`, `clock`.
- **Checked with a small PHP script** (no code change): every reminder keeps `next_due = last_done + interval_days`; each plant's soonest reminder and newest log are the ones already in `$mock_plants`; species names match.

**Why this way**
- **Keyed by id** (`$mock_species[1]`): the page finds a plant's species in one step, the way a `JOIN … ON plants.species_id = plant_species.id` does.
- **Separate arrays = separate tables.** A plant has many reminders, many logs and many photos (one-to-many), so each is its own list with a `plant_id`, filtered for the plant being shown, as `WHERE plant_id = ?` will do.
- **The data must agree with itself.** The Dashboard, My Garden and the profile now all show the same facts about Monty (water today, newest note 5 days ago). Checking it with a script catches a wrong date before a lecturer does.

```php
$mock_plant_reminders = [
    ['id' => 11, 'plant_id' => 42, 'task' => 'water',     'interval_days' => 7,
     'last_done' => mock_date(-7),  'next_due' => mock_date(0)],
    ['id' => 14, 'plant_id' => 42, 'task' => 'fertilise', 'interval_days' => 30,
     'last_done' => mock_date(-18), 'next_due' => mock_date(12)],
    // …
];
```

> [!tip] What to remember
> - One-to-many relationships become separate arrays (tables) linked by `plant_id`.
> - The same fact shown on three pages must come from data that agrees, so test the mock data too.

**Check yourself**
1. Why are the reminders not stored inside each plant's row?
2. Where does "Water · Every 7 days" on Monty's profile come from?

> [!success]- Answers
> 1. A plant can have several reminders (water and fertilise). A row has one value per column, so the reminders go in their own table, `care_reminders`, each with the `plant_id` of its plant.
> 2. From Monty's water reminder in `care_reminders`: `interval_days = 7`. The species guide only gives the *default* (also 7), which the member can change.

Lessons: [[00.07 Mock Data Shaped Like Database Rows]] · [[03.01 MySQL Schema - Types, Keys and Foreign Key Rules]] · [[04.03 Dates, Reminders and Due Logic]]

---
### Step 3.5 — Plant Profile: the owner's view
> [!important] Goal (CORE-18, UI half)
> Build wireframe p12, elements 1–7, for the plant's owner: the one page that tells a plant's story.

**What was done (2026-10-14, branch `feature/pages-ui`, commit `b7ed587`)**
- `public/plant.php` (new), for `plant.php?id=42`:
  1. **Breadcrumb**: My Garden › Living room › Monty (the collection links to `garden.php?collection=1`).
  2. **Gallery**: the big 4:3 photo, up to four thumbnails (they open the Photos tab) and a dashed **Add** tile.
  3. **Identity**: "Monty" in Lora at display size, *Monstera deliciosa* · Swiss cheese plant, the status badge, "Updated 5 days ago", the collection, "With you since Mar 2025".
  4. **Actions**: **Log update** (primary), Health check (`health-check.php?plant=42`), List for exchange, and the ⋯ menu from My Garden.
  5. **Care at a glance**: Light, Water ("Every 7 days" from the reminder), Humidity, and **Next watering**, tinted terracotta when due today and red when overdue, with *Mark watered*.
  6. **Tabs** as links, `?tab=care`, `?tab=reminders`, `?tab=photos`: a **timeline** story (AI entries with a sparkle and the confidence), the care guide (with pet safety and the usual schedule), all reminders with last done / next dates, and a photo grid.
  7. **Reminders card**: each reminder with Today / a date, then Public · shown in Explore and 12 likes.
- **Not found**: a wrong id (`?id=999`, `?id=abc`, no id) answers with HTTP **404** and "Plant not found", with no PHP warnings.
- `public/assets/css/plant.css` (new).

**Why this way**
- **A private plant answers exactly like a missing one.** If the page said "this plant is private", a stranger would learn that plant 46 exists. Same message, same 404: nothing leaks. Stage B keeps this rule with an ownership check (`$is_owner`).
- **`http_response_code(404)`** tells the browser and search engines that the page does not exist, while we still show a friendly page.
- **Tabs are links with `#plant-tabs`**, so the browser jumps down to the tabs after loading instead of to the top. Each tab has an address and works without JavaScript; week 5 can switch them without a reload.
- **Owner-only parts are wrapped in `if ($is_owner)`** from the start (actions, Add tiles, Mark watered, the reminders card), so the visitor view in the next step is mostly *leaving things out*.
- **The timeline is CSS only**: `::before` draws the line to the next entry and `::after` draws the dot. The newest dot is filled.

```php
$is_owner = $plant && $current_user && $plant['user_id'] === $current_user['id'];

if (!$plant || (!$plant['is_public'] && !$is_owner)) {
    http_response_code(404);
    // … print "Plant not found" with the header and footer, then:
    exit;
}
```

**Problems and fixes**
- **Odd gaps in "Species identified by AI: *Monstera deliciosa*"**: the line is a flex row, and in a flex row every piece of text becomes its own item with the gap between them. Wrapping the words in one `<span>` makes them one item.
- **One photo column was wider than the others.** The dashed square Add tile stretched to the row's height (photo plus date), and being square it then grew wider. Fixed with `align-self: start` and `minmax(0, 1fr)` columns, which stay equal whatever is inside.

> [!tip] What to remember
> - Same answer for "missing" and "not yours": privacy by not leaking.
> - `minmax(0, 1fr)` is the safe way to say "equal columns".
> - Flex rows split text into items; wrap mixed inline text in one element.

**Check yourself**
1. What does a visitor see at `plant.php?id=46` (Vera, private), and why exactly that?
2. Why does each tab link end in `#plant-tabs`?
3. Where do the words "Next watering · Today" and the terracotta colour come from?

> [!success]- Answers
> 1. "Plant not found" with status 404, the same as for an id that does not exist, so nobody can learn which private plants exist.
> 2. `#plant-tabs` is the id of the tab bar. After the new page loads, the browser scrolls to it, so the reader stays at the tabs instead of jumping back to the top.
> 3. From Monty's water reminder: `care_due(next_due)` returns the state `due`, which becomes the class `care-tile-due` (terracotta tint), and the page prints "Today" for that state.

Lessons: [[01.03 Query Strings and GET Parameters in PHP]] · [[01.04 Rendering States - Empty, Owner and Visitor Views]] · [[03.07 Access Control and Ownership Checks]] · [[01.02 Card Grids, Images and Sticky Bars]]

---
### Step 3.6 — Plant Profile: the read-only view for visitors, and checks for the whole page
> [!important] Goal (CORE-21, UI half; the roadmap lists it in week 7)
> p12 says "Logged in · public read": visitors see the same page read-only, with an owner card and a like button instead of edit controls.

**What was done (2026-10-14, branch `feature/pages-ui`, commit `3f0e04a`)**
- `includes/mock-data.php`: **`$mock_users`**, members as *other people* see them: public columns only (no email, no phone) plus plant count and rating. The Public Profile (P14) will reuse it.
- `public/plant.php`, when the viewer is **not the owner**:
  - Breadcrumb **Explore › @nimali.grows › Monty**.
  - **Like · 12** instead of the actions; a visitor gets **Log in to like · 12**, a link to Log in.
  - The fourth care tile shows **Pets: Not pet-safe** instead of the owner's next watering.
  - **No Reminders tab** (`?tab=reminders` falls back to the story), no Add tiles, no "Edit care schedule".
  - **Owner card** ("Grown by" Nimali Perera, @nimali.grows · Kurunegala, 18 plants, ★ 4.9 (23) as a trader, likes, View profile) instead of the reminders card.
  - "With you since" becomes "Growing since".
- **Checks** (no code change), with curl keeping one session like a browser: as a visitor, Monty and Tommy load with the owner card and no "Log update"; private Vera (46) and Pearl (49) answer **404**; after `?member`, Vera answers 200. No PHP errors in any case. Screenshots at 1280 and 375 px in both views; every tab, Goldie's red "overdue" tile, and the 404 page.

**Why this way**
- **Hide, do not disable.** A visitor cannot log Monty's health, so the button is not shown at all. A greyed-out button would only invite a click that cannot work.
- **Reminders are private.** They are the owner's to-do list. The story and the care guide are what the plant "tells" the community.
- **No contact details on the owner card.** The privacy rule says contact details appear only after "I'm interested" on an exchange listing, and only the fields the owner chose.
- **`$mock_users` is not `$current_user`.** `$current_user` is *who is looking* (null for a visitor); `$mock_users[1]` is *whose plant it is*. Mixing them up would make the owner card disappear in visitor mode.

```php
if (!$is_owner) {
    unset($tabs['reminders']);   // the tab disappears for everyone but the owner
}
// later: ?tab=reminders is no longer a key of $tabs, so the whitelist check falls back to 'story'
```

**Problems and fixes**
- **A Python edit script stopped half-way**: one piece of text it searched for had different line breaks. It writes the file only at the end, so `plant.php` was untouched (only the CSS had been appended), and the script was run again with the exact text.
- **Screenshots in visitor mode**: the headless browser forgets its session between runs, so only an address with `&guest` shows the visitor view. This is how the test tool works, not a bug in the site.

> [!tip] What to remember
> - "Who is looking" (`$current_user`) and "whose page it is" (the owner) are two different things.
> - Removing a tab from the whitelist also blocks its URL.
> - Show visitors only what they can use; never show private data.

**Check yourself**
1. A visitor types `plant.php?id=42&tab=reminders`. What happens, and which line of code makes it happen?
2. Why does the visitor see "Log in to like" as a link, but a member sees a button?
3. Where must the owner check happen in stage B, in the HTML or before it?

> [!success]- Answers
> 1. The Story tab is shown. `unset($tabs['reminders'])` removes the key, so `array_key_exists($tab, $tabs)` fails and `$tab` falls back to `'story'`.
> 2. A visitor cannot like anything yet, so the useful step is to go to Log in (a link goes somewhere). A member can like right here (a button does something; stage B makes it a POST form).
> 3. Before it, at the top of the page: load the plant, compare `user_id` with the session's user, and decide what to show. Hiding buttons in the HTML is only the look; the pages that *save* (Log update, Delete) must check ownership again on the server.

Lessons: [[01.04 Rendering States - Empty, Owner and Visitor Views]] · [[03.07 Access Control and Ownership Checks]] · [[03.04 PHP Sessions and Login State]]

---
### Step 3.7 — Add a plant: the form
> [!important] Goal (CORE-16, UI half)
> Build wireframe p13 in add mode: a new plant in under a minute, photo first.

**What was done (2026-10-14, branch `feature/pages-ui`, commit `0082fa9`)**
- `public/plant-form.php` (new), members only (a visitor is sent to Log in):
  1. **Photo upload**: a dashed drop area with "Add a photo of your plant", Choose photo, and "JPG or PNG, up to 5 MB".
  2. **Suggested species** card: explains what happens once a photo is added (the real suggestion is step 3.9).
  3. **Plant details**: Nickname, Species (a text box with a list of known species), Collection, Date acquired.
  4. **"How is it doing right now?"**: Thriving · Stable · Needs attention · Sick, as one segmented control.
  5. **Care reminders**: Water every 7 days and Fertilise every 30 days, each with an on/off switch and a start date.
  6. **Who can see this plant?**: Public · shown in Explore / Private · only you.
  7. **Sticky save bar**: "You can change everything later.", Cancel (back to My Garden) and **Save plant**.
- `public/assets/css/plant-form.css` (new). `includes/header.php`: new option **`$hide_bottom_bar`**. `.field-optional` moved from `auth.css` to `components.css`. Icons `arrow-left` and `image-up`.

**Why this way**
- **The drop area is a `<label>`** for the hidden file input. Clicking a label activates its input, so a click anywhere on the box opens the file picker, with no JavaScript. `accept="image/jpeg,image/png"` limits the picker; `capture="environment"` offers the rear camera on a phone. The server still checks the real file type in stage B (`finfo`), because `accept` is only a hint.
- **`enctype="multipart/form-data"`**: the only form encoding that can carry a file.
- **`<datalist>` for the species**: typing suggests the species we know, but any name is allowed (the AI or the member may know one we do not). Stage B looks the name up and stores its `species_id`.
- **Radio buttons inside a `<fieldset>` with a `<legend>`**: a screen reader then says "How is it doing right now?" before each choice.
- **Every field reads from `$values`**, so the edit form in the next step only has to fill `$values` from the plant's row.
- **No bottom bar on this page.** p23: inner pages hide the bar; here the save bar takes the bottom of a phone screen instead.

```php
<label class="dropzone" for="plant-photo"> … Choose photo </label>
<input class="visually-hidden" type="file" id="plant-photo" name="photo"
       accept="image/jpeg,image/png" capture="environment">
```

**Problems and fixes**
- **A line ran through "Care reminders"**: a `<legend>` is drawn *on* its fieldset's top border. The border moved to a wrapper `<div class="form-section">`.
- **"Needs attention" wrapped onto two lines** on a desktop, where the four choices had equal widths. From 768 px each choice is now as wide as its words (`flex: 1 1 auto`, `nowrap`); on a phone they sit two by two.
- **On a phone the save bar collided with the round + button** of the bottom bar, then floated with the form showing underneath. Solved with `$hide_bottom_bar`, as p23 describes.

> [!tip] What to remember
> - A file input needs `method="post"` and `enctype="multipart/form-data"`.
> - `accept` helps the user; only the server's check protects the site.
> - Group radio buttons in a `<fieldset>` with a `<legend>`.

**Check yourself**
1. Why can a click on the whole dashed box open the file picker without JavaScript?
2. A user renames `virus.php` to `virus.jpg`. Does `accept="image/jpeg"` stop it? What does?
3. Why is the phone bottom bar hidden on this page only?

> [!success]- Answers
> 1. The box is the `<label>` of the file input (`for="plant-photo"`). Clicking a label does the same as clicking its input.
> 2. No: `accept` only filters the picker, and the name can be anything. Stage B checks the file's real content with `finfo` (JPG or PNG only), limits it to 5 MB, gives it a random name and never runs it.
> 3. The form has its own sticky save bar at the bottom of the screen. Two bars would cover each other; p23 says inner pages hide the bottom bar. Other pages are not changed.

Lessons: [[02.05 Upload Preview with the File API]] · [[04.02 Secure File Uploads in PHP]] · [[01.02 Card Grids, Images and Sticky Bars]]

---
### Step 3.8 — Edit a plant: the same form, filled in
> [!important] Goal (CORE-16, UI half)
> p13, UX notes: "Edit mode reuses this page, pre-filled, with a Delete plant link that opens a confirmation."

**What was done (2026-10-14, branch `feature/pages-ui`, commit `57e87e8`)**
- `public/plant-form.php?id=42` → **Edit Monty**: "Back to Monty", every field filled from his row, the current photo with **Change photo**, his own reminders (water every 7 days, fertilise every 30, starting on their next due dates), and **Save changes**.
- **Health is not on the edit form.** A note says to use **Log update** on the plant's page instead.
- **Delete plant** (red, on the left of the save bar); on a phone only its bin shows, named "Delete plant" for screen readers.
- A task the plant has no reminder for starts **switched off**, with the species' usual interval ready (Vera: water on, fertilise off, every 60 days).
- **Only the owner**: `?id=999`, `?id=abc`, or a plant of someone else → 404 "Plant not found". A visitor → Log in.
- The links from the Plant Profile now land on their fields: `#photo`, `#collection`, `#reminders`.
- Checked: Monty, Vera, Tommy, Lily, missing ids, the add form again; no PHP errors; screenshots at 1280 and 375 px.

**Why this way**
- **One form, two jobs.** Add and edit share all the HTML. Only `$values`, the title, the Back and Cancel links, the button words and the form's `action` change. Fixing a field later fixes it in both.
- **Health belongs in the story.** If editing the form could change Monty to "Sick", the story would have no date and no note for it. The first status comes from the add form; every later one from Log update.
- **The ownership check happens before any HTML**, and again in stage B when saving, because the `?id=` in the address can be changed by anyone.

```php
foreach ($mock_plants as $row) {
    if ($row['id'] === $plant_id && $row['user_id'] === $current_user['id']) {
        $plant = $row;   // found, and it is hers
        break;
    }
}
if (!$plant) { http_response_code(404); /* "Plant not found" */ exit; }
```

**Problems and fixes**
- **A Bash command broke on an apostrophe** ("plant's") inside the edit script. The script was saved to a file and run from there; nothing had been changed by the failed attempt.
- **The save bar took two lines on a phone** (Delete plant, Cancel, Save changes). On a phone Delete now shows only its bin, and the bar's padding and gaps are smaller.

> [!tip] What to remember
> - Reuse one form for add and edit; fill it from a `$values` array.
> - Check ownership on the server for every `?id=`, both when showing the form and when saving it.

**Check yourself**
1. Nimali changes the address to `plant-form.php?id=51` (Pip, Amaya's plant). What does she see, and why?
2. Why does the edit form not offer the four health choices?

> [!success]- Answers
> 1. "Plant not found" (404). The loop only accepts a plant whose `user_id` is hers; Pip's is not, so `$plant` stays null. It is the same answer as for a plant that does not exist, so nothing is revealed.
> 2. A health change must go in the plant's story with its date and note. That is Log update's job; a status changed silently on the edit form would leave a gap in the story.

Lessons: [[04.01 CRUD in Plain PHP]] · [[03.07 Access Control and Ownership Checks]]

---
### Step 3.9 — Add a plant: the AI species suggestion
> [!important] Goal (ML-13 UI half, roadmap week 7; built now because it is the main picture of p13)
> Show what happens after a photo is uploaded: the AI suggests a species, the member decides (p13, elements 2–4).

**What was done (2026-10-14, branch `feature/pages-ui`, commit `5c828f9`)**
- `includes/mock-data.php`: **`$mock_species_result`**, the answer of the Flask service in the exact shape of the API contract (`model_version` + `predictions`: Monstera deliciosa 91%, Philodendron bipinnatifidum 6%, Epipremnum pinnatum 2%), plus the uploaded file's name and size. **`$mock_species_result_unsure`**: the same photo with a top result of 48%.
- `public/plant-form.php?ai` (preview switch, add mode only):
  - The photo shows with **Change photo** and "monty.jpg · 2.4 MB · uploaded".
  - **Suggested species** card: the top species in a sage box with the confidence meter (**High · 91%**) and **Applied**; two alternatives with grey bars and **Use**; **None of these? Choose the species manually**.
  - The Species field is filled, with a **✦ Filled by AI** tag and a **×** inside the field to clear it.
  - Care reminders come from the species guide: "Suggested from the Swiss cheese plant care guide".
- `?ai=unsure` (state **S3**): a "**Not sure.**" message, nothing filled in, all three shown only as possibilities.
- **Use**, **×** and **Choose manually** are links (`?pick=1`, `?pick=none`); `?pick` is checked against a whitelist (`?pick=abc` falls back safely).
- Checked every variant with curl (no PHP errors) and screenshots at 1280 and 375 px; edit mode ignores `?ai`.

**Why this way**
- **AI suggests, the member decides** (design system rule). The top result is *applied* only when it is High or Medium; it is shown, labelled and reversible, and nothing is stored until **Save plant**.
- **Below 60% it is never an answer** (S3): the field stays empty and the member is told to choose or retake the photo. Alternatives always have grey bars, so a low number never looks like a result.
- **The mock has the API's exact shape.** In stage M, `json_decode()` of Flask's reply gives this same array, so this page will not change.
- **If Flask is offline, the form stays manual** (CLAUDE.md: the site must never break because of ML). The suggestion is an extra on top of a form that works without it.
- **`(int) 'abc'` is 0**: casting first would have turned a nonsense value into "apply the top suggestion" even when the model was unsure. The text is compared against the allowed values instead.

```php
$top_level = confidence_level($top['confidence']);   // High ≥ 80 · Medium 60–79 · Not sure < 60
$is_unsure = $top_level['key'] === 'low';
$pick      = $_GET['pick'] ?? ($is_unsure ? 'none' : '0');   // unsure: fill nothing by default
```

**Problems and fixes**
- **"× Clear" wrapped under the label** and pushed the Species box out of line with Nickname. The × now sits inside the field, as on p13, named "Clear the species the AI filled in" for screen readers.
- **Green bars made 48% look like an answer** in the unsure state. Alternatives now use the grey "Not sure" colour.
- A selector that matched nothing was caught in the script before it ran.

> [!tip] What to remember
> - The fixed confidence words: High ≥ 80 %, Medium 60–79 %, Not sure < 60 %. Below 60 % is never presented as an answer.
> - Mock data for an external service should copy the service's JSON shape exactly.
> - Check a choice against a whitelist *before* converting its type.

**Check yourself**
1. The model returns 55% for Monstera. What does the form do?
2. Why are Use and × links, and what will week 7 change?
3. What happens on this page if the Flask service is switched off in stage M?

> [!success]- Answers
> 1. 55% rounds to "Not sure": the Species field stays empty, the card shows "Not sure." with the possibilities, and the member chooses or types the species.
> 2. Links work without JavaScript: the page reloads with `?pick=`. Week 7 adds JavaScript to apply a suggestion without reloading; the links stay as the fallback.
> 3. `ml-client.php` times out or gets no answer, so there is no suggestion card: the form stays fully manual and the plant can still be saved.

Lessons: [[05.07 Confidence Thresholds and Honest AI Results]] · [[05.01 Calling the ML Service from PHP with cURL]] · [[01.03 Query Strings and GET Parameters in PHP]]

---
### Step 3.10 — Where Save and Cancel lead
> [!important] Goal (CORE-16, UI half; Ranshitha chose option A)
> After Save, the demo should move on to the next page instead of reloading the form.

**What was done (2026-10-14, branch `feature/pages-ui`, commit `f938a63`)**
- Ranshitha asked where Cancel and Save lead. Cancel already worked: **add → My Garden** (p13, element 7), **edit → the plant's profile**. Save reloaded the same form, because nothing handles the POST yet.
- Two options were offered: A, a stage F redirect without saving; B, leave it until stage B. **Ranshitha chose A.**
- `public/plant-form.php`: on POST, **Save plant** opens **My Garden**; **Save changes** opens **the plant's profile** (`plant.php?id=42`). Nothing is saved.
- Tested with curl: add and `?ai` → `garden.php`; `?id=42` → `plant.php?id=42`; `?id=999` → 404; a visitor → Log in; opening the form normally still shows it.

**Why this way**
- **Add → My Garden, not a profile.** A new plant has no profile until stage B gives it an id; opening Monty's page after adding "Pip" would look wrong. Stage B will open the new plant's own profile.
- **The redirect comes after the ownership check**, so even a pretend save for someone else's plant gets "Plant not found". Security habits should be in place before the real save exists.
- **Post/Redirect/Get again**, as with Log in: the answer to the form is a redirect, so Refresh on the next page never sends the form twice.
- **Known side effect:** the new plant does not appear in My Garden, because nothing is saved. Say so during a demo.

```php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    header('Location: ' . ($is_edit ? url('plant.php?id=' . $plant['id']) : url('garden.php')));
    exit;
}
```

> [!tip] What to remember
> - Cancel is a link (it goes somewhere); Save is a submit button (it sends the form).
> - Put access checks before any action, even a pretend one.

**Check yourself**
1. Why does Save plant (add) go to My Garden now, but to the plant's profile in stage B?
2. What would happen without the `exit` after `header('Location: …')`?

> [!success]- Answers
> 1. In stage F nothing is saved, so the new plant has no id and no profile; My Garden is the honest place to go back to. In stage B the insert returns the new id, so the redirect can open `plant.php?id=<new id>`.
> 2. PHP would carry on and print the whole form after the redirect header. The browser would still follow the redirect, but the server would do useless work, and any code below (for example a save) would run when it should not.

Lessons: [[03.06 Form Handling - Validation, Redirects and Flash Messages]] · [[03.07 Access Control and Ownership Checks]]

---
### Step 3.11 — Settings: the page and its five cards
> [!important] Goal (CORE-26, UI half)
> Build wireframe p22: one place to manage the public profile, password, privacy defaults and reminder preferences.

**What was done (2026-10-14, branch `feature/pages-ui`, commit `2b3b308`)**
- `public/settings.php` (new), members only (a visitor is sent to Log in):
  1. **Section menu**: Profile · Account and security · Privacy · Reminders · Delete account (red). Jump links to each card; a list on a phone, a column that stays in view on a desktop.
  2. **Profile**: avatar with **Change photo**, Display name, Username (with "@" and the hint "Your old @link keeps working after a change"), Bio with "87 / 160", Town, **Save profile**.
  3. **Account and security**: the email with **Change email** (opens a small form in place), then Current / New / Confirm new password and **Update password**.
  4. **Privacy**: "New plants are public" and "Show approximate area on listings" switches, **Save privacy**.
  5. **Reminders**: "Show care tasks on Dashboard" and **Email reminders**, disabled with a **Coming later** tag.
  6. **Delete account** in a red card (the confirmation dialog comes in week 5).
- `includes/mock-data.php`: three preference values on Nimali's users row: `new_plants_public`, `show_listing_area`, `dashboard_reminders`, **proposed columns for DES-07**.
- `public/assets/css/settings.css` (new). `.field-row` moved from `plant-form.css` to `components.css`; every `.field` now keeps its natural height (`align-content: start`). Icon `upload`.
- Checked: no PHP errors on Settings, the plant form, Sign up, Log in and the Dashboard after the shared CSS changes; screenshots at 1280, 768 and 375 px.

**Why this way**
- **Five forms, not one** (p22, UX notes: "each card saves on its own"). If Nimali types a new bio and a wrong password, the password error must not throw her bio away. Separate `<form>`s also mean each POST only carries one card, with a hidden `form` field saying which.
- **Upcoming features are shown, disabled and labelled** (p22): "Email reminders · Coming later" is visible so nobody thinks it was forgotten, and disabled so nobody thinks it works.
- **A switch is a checkbox**: an unticked checkbox is not sent at all, so stage B must read a missing value as 0.
- **Change email in a `<details>`**: it opens in place, without JavaScript, and needs the current password, because email is how an account is recovered.
- **`scroll-margin-top`** on each card: the menu's jump stops below the sticky top bar instead of hiding the card's title under it.

```html
<form method="post" action="settings.php#privacy">
  <input type="hidden" name="form" value="privacy">
  …
</form>
```

**Problems and fixes**
- **The Display name box was taller than the Username box** next to it: grid items stretch to the tallest neighbour, and Username has a hint under it. `align-content: start` on `.field` fixed it everywhere.
- **A grey disabled Remove** showed when there was no photo, which looked broken. Remove now appears only when there is a photo.
- **"Account and security" wrapped** in a 200 px menu; the column is now 220 px.
- An `!important` was replaced with a more specific selector (`li.settings-menu-danger` beats `li + li`).

> [!tip] What to remember
> - One form per independent save.
> - An unticked checkbox sends nothing.
> - Show unfinished features disabled with a label; never fake them.

**Check yourself**
1. Why does Settings have five `<form>` elements instead of one?
2. Nimali switches "New plants are public" off and saves. What does the browser send for it?
3. Why does changing the email ask for the current password?

> [!success]- Answers
> 1. Each card saves on its own (p22): an error in one card (a wrong password) never discards what was typed in another (the bio), and each POST only carries the fields of one card.
> 2. Nothing: an unticked checkbox is left out of the form data. Stage B reads the missing `new_plants_public` as 0.
> 3. Email is how an account is recovered. If someone found Nimali logged in on a shared computer, the password stops them from taking the account over by changing the email.

Lessons: [[00.05 HTML Forms]] · [[03.06 Form Handling - Validation, Redirects and Flash Messages]]

---
### Step 3.12 — Settings: saving each card, and the password check
> [!important] Goal (CORE-26, UI half)
> Make each card's button lead somewhere, like Save on the plant form (option A), and show the password error p22 draws.

**What was done (2026-10-14, branch `feature/pages-ui`, commit `e75070d`)**
- `public/settings.php`: on POST, the hidden `form` field says which card was sent (`profile`, `email`, `password`, `privacy`, `reminders`; anything else is ignored).
- A card that passes its checks **redirects back to itself** (`settings.php?saved=privacy#privacy`) and shows a green message in that card: "Privacy saved." **Nothing is stored** until stage B.
- The **password card is checked on the server**, as p22 shows: "**Passwords don't match**" under Confirm new password, and "Use at least 8 characters, including a number." under New password. The field gets a red border (`aria-invalid="true"`) and the message is linked to it (`aria-describedby`).
- Tested with curl: every card's redirect; a mismatch (error shown, the password itself never printed back); a short password; an unknown form; `?saved=` with a script tag (ignored). Screenshots of the error and of a saved message.

**Why this way**
- **Post/Redirect/Get** for every successful save, so Refresh never sends a form twice; the `#privacy` in the redirect brings the member back to the card they saved.
- **The server checks again**, although the browser already checks the length rule: anyone can switch the browser's checks off. The "don't match" rule is only checked on the server, so it is the one p22 shows.
- **Passwords are never printed back into the form**, even after an error: a page's HTML can be cached or seen by others. The member types them again.
- **An error lives under its field** and is linked to it, so a screen reader reads "Confirm new password, invalid, Passwords don't match" together.
- **Only `?saved=` values on the list are shown**: the message text comes from our array, never from the address, so `?saved=<script>` prints nothing.

```php
if ($new !== $confirm) {
    $errors['confirm_password'] = "Passwords don't match";
}
if (!$errors) {
    header('Location: ' . url('settings.php?saved=' . $form . '#' . $card_of[$form]));
    exit;
}
```

**Problems and fixes**
- None in the code. The Bash shell still breaks on an apostrophe ("don't") inside a command, so the edit was written as a script file first.

> [!tip] What to remember
> - Validate on the server even when the browser already does.
> - After an error: keep the safe values, never print passwords back, put the message under its field.
> - Text shown on the page comes from your own list, not from `$_GET`.

**Check yourself**
1. Why is the mismatch error shown by PHP, not by the browser?
2. After the error, why are all three password boxes empty?
3. What stops `settings.php?saved=<script>alert(1)</script>` from running a script?

> [!success]- Answers
> 1. HTML has no attribute for "must equal another field", so the browser cannot check it. The server compares the two values. (Week 6 JavaScript can also show it while typing; the server check stays.)
> 2. Passwords are never printed back into HTML: the page could be cached, saved or seen over a shoulder. The member types them again.
> 3. `?saved=` is only used if it is one of the keys of `$saved_messages`; the message printed is our own text, escaped with `e()`. Anything else is ignored.

Lessons: [[03.06 Form Handling - Validation, Redirects and Flash Messages]] · [[03.03 Password Hashing and Login Lockout]] · [[00.04 Output Escaping in PHP]]

---

## Open points
- **My Garden (P04):** the first card is Tommy, not Monty as on p11, because "Recently updated" sorts by the newest health log and Tommy had a Health Check yesterday. The chips say 7 + 6 + 5 = 18 plants while the mock data holds 8, so a collection shows fewer cards than its count until stage B. New collection (button and chip) and Delete do nothing yet. **Apply** is not on p11; it is needed until week 6 submits the lists on change. Lily, Pearl and their "Peace lily" / "Mexican snowball" common names are my additions from p11's pictures.
- **Plant Profile (P05):** the species columns `pet_safe`, `water_interval_days` and `fertilise_interval_days` are my names until DES-07/DES-13 fix them; the care texts and story notes are my samples. Every story entry shows its status badge (p12 shows it on the newest only). Monty's gallery shows the same placeholder drawing four times. **Log update**, **Mark watered** and **Like** do nothing yet (week 5 dialog, stage B saving). "Add" photo and "+ Add" reminder lead to the edit form, which is built next. The read-only view (CORE-21) was built now, not in week 7, because p12 includes it. Visitors see a "Pets" tile in place of the next watering, and no Reminders tab: my choices.
- **Add / Edit Plant (P06):** **Save plant** opens My Garden and **Save changes** opens the plant's profile (option A, step 3.10), but nothing is saved until stage B, so a new plant does not appear in My Garden yet. **Delete plant** does nothing until the week 5 confirmation. Health is not on the edit form (it goes through Log update): my choice, p13 does not say. The species field accepts any name (a `<datalist>`, not a fixed list): decide in stage B what happens with a species we have no guide for. `?ai` and `?ai=unsure` are preview switches until the ML service exists; the AI suggestion (ML-13) was built now, not in week 7. The phone bottom bar is hidden on this page (`$hide_bottom_bar`), as p23 says for inner pages; the other pages keep it until the week 6 responsive pass.
- **Settings (P15):** the three preference values `new_plants_public`, `show_listing_area` and `dashboard_reminders` are **proposed new `users` columns** for DES-07 (names are mine). Every Save shows its green message but **stores nothing** until stage B, so the old values come back on the next visit. Change photo, Remove and Delete account do nothing yet (uploads in stage B, the dialog in week 5). "Change email" opens a small form in place, which p22 does not draw. The phone layout is one long page with the menu as a list of jump links; p22's "separate screens on mobile" waits for the week 6 responsive pass.
