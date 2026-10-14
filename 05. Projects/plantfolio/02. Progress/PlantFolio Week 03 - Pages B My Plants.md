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
- [x] P05 Plant Profile (`plant.php?id=`) — UI built 2026-10-14, owner and visitor views (not wired)
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

## Open points
- **My Garden (P04):** the first card is Tommy, not Monty as on p11, because "Recently updated" sorts by the newest health log and Tommy had a Health Check yesterday. The chips say 7 + 6 + 5 = 18 plants while the mock data holds 8, so a collection shows fewer cards than its count until stage B. New collection (button and chip) and Delete do nothing yet. **Apply** is not on p11; it is needed until week 6 submits the lists on change. Lily, Pearl and their "Peace lily" / "Mexican snowball" common names are my additions from p11's pictures.
- **Plant Profile (P05):** the species columns `pet_safe`, `water_interval_days` and `fertilise_interval_days` are my names until DES-07/DES-13 fix them; the care texts and story notes are my samples. Every story entry shows its status badge (p12 shows it on the newest only). Monty's gallery shows the same placeholder drawing four times. **Log update**, **Mark watered** and **Like** do nothing yet (week 5 dialog, stage B saving). "Add" photo and "+ Add" reminder lead to the edit form, which is built next. The read-only view (CORE-21) was built now, not in week 7, because p12 includes it. Visitors see a "Pets" tile in place of the next watering, and no Reminders tab: my choices.
