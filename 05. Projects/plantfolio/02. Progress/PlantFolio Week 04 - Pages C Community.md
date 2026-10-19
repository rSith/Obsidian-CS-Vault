---
type: project-note
project: PlantFolio
status: draft
tags: [project, web]
---
# PlantFolio Week 04 — Pages C: Community
> [!info] [[PlantFolio - Project Home]] · Stage F · 26 Oct – 1 Nov 2026 · branch `feature/pages-ui` (from commit `35da400`, local only, one pull request at the end)
> **Study notes:** [[01.00 Building Pages]] · **Revision:** [[01.99 Summary - Building Pages]]

> [!abstract] Week goal
> The pages where members find each other and swap plants: Explore, Public Profile, Exchange and Listing Detail.

## Tasks
| Page | Task | Notes | Module |
|---|---|---|---|
| P09 Explore | COM-01, COM-02, COM-03 (UI) | Unified search, Plants / People tabs, filter sidebar, active chips, matching people row | MOD-07 |
| P14 Public Profile | COM-05, COM-13 (UI) | Identity, stats row, tabs, collection collages, rating breakdown, latest review; no contact details | MOD-10 |
| P10 Exchange | COM-07 (UI) | How-it-works strip, filters, listing cards with type badge, distance, looking for, rating, interest count | MOD-08 |
| P11 Listing Detail | COM-08 (UI) | Gallery, summary, plant-story link, owner trust card, safety tips; state A (before interest) and state B (contact revealed) | MOD-08 |

- [x] P09 Explore (`explore.php?q=`) — UI built 2026-10-20 (steps 4.1–4.4): search, Plants / People tabs, filters, matching people row and no-results state (not wired). Still to check by hand: hover, keyboard focus and a real phone.
- [ ] P14 Public Profile (`user.php?u=`)
- [ ] P10 Exchange (`exchange.php`)
- [ ] P11 Listing Detail (`listing.php?id=`)

## Learn this week
- Reusing partials with different data → [[00.03 PHP Includes and Partials]]
- Query strings: reading `?id=` and `?q=` → [[01.03 Query Strings and GET Parameters in PHP]]
- Two states of one page (before and after "I'm interested") → [[01.04 Rendering States - Empty, Owner and Visitor Views]]
- A row that scrolls sideways on a phone (Explore's matching people) → [[01.06 Sideways Scrolling Rows and Scroll Snap]]

## Checks for every page
- [ ] Matches its wireframe page and the Figma prototype *(so far: P09 compared with p16 side by side; not yet with Figma)*
- [ ] Works at 375, 768 and 1024 px with no horizontal scroll and no console errors *(so far: P09 measured at 375, 768, 1024 and 1280 px in 22 states, visitor and member, and screenshots at all three sizes; no JavaScript yet. Rechecked after the step 4.4 review fixes: 7 states × 4 widths, no sideways scroll, both tabs 44 px tall. Still to do: a real phone.)*
- [ ] All output escaped with `e()`, images have alt text, inputs have labels *(so far: P09 checked: `<script>` in the search prints as text everywhere, every image has `alt`, every input has a label)*
- [ ] No contact details are printed before interest is expressed *(so far: P09 has none: member and plant cards print only public columns)*

---
## Work log
Step sections are added here as each step is finished.

### Step 4.1 — Mock data for Explore: other members' public plants
> [!important] Goal (COM-01, UI half)
> Give Explore (wireframe p16) a community to search: other members' public plants, each with its owner, town and likes, so that searching "monstera" finds the six cards on p16.

**What was done (2026-10-15, branch `feature/pages-ui`, commit `35da400`)**
- `includes/mock-data.php`:
  - **`$mock_community_plants`** (new): 11 plants from four members. The six monsteras of p16 are **Thai Con** (@kandy.jungle), **Big Momma** and **Holey** (@aroid.amaya), **Swiss** (@dinesh.greens), **Leafy** (@ruwan.roots), plus Nimali's **Monty**. Pip, Hugo, Marble, Spike, Rosie and Mother Aloe give the filters something to filter.
  - **`$mock_species`**: four new rows (9–12): *Monstera* 'Thai Constellation', *Monstera adansonii*, *Pilea peperomioides* and *Philodendron hederaceum*.
  - **`$mock_users`**: Amaya (Colombo), Kavindi (Kandy), Dinesh (Negombo) and Ruwan (Kurunegala) added beside Nimali, with plant counts and ratings.
  - **`$mock_like_counts`**: the p16 numbers (Thai Con 87, Big Momma 48, Swiss 31, Monty 12, Leafy 9, Holey 7) and invented ones for the rest.
  - Ruwan's aloe and Dinesh's snake plant listings now point at the plant they offer (`plant_id` 61 and 59).
  - **`$mock_public_plants`** (new, at the end of the file): every public plant, Nimali's and everyone else's, joined with its owner plus two calculated columns, `like_count` and `on_exchange`.
  - The `?empty` switch now removes only Nimali's likes, not everyone's.
- `public/plant.php` looks for a plant in both lists, so a card opens Thai Con read-only (owner card "Grown by Kavindi Jayawardena"). A plant with no story entries shows "No story entries yet." instead of an empty list.

**Why this way**
- **Two lists, one table.** `$mock_plants` stays "Nimali's plants", because My Garden and the Dashboard read it. Other members' plants get their own list. In stage B both are rows of the same `plants` table, and Explore's query is simply `WHERE plants.is_public = 1`.
- **Joined, not copied.** Each community plant stores only `user_id`. The owner's name and town are added when `$mock_public_plants` is built, the way a SQL `JOIN users` adds them. A member's town is written once, in `$mock_users`.
- **Private plants never get in.** The loop skips `is_public = 0`, so Vera and Pearl can never reach Explore. In stage B the `WHERE` clause does the same job.
- **Built after the `?empty` switch.** The list is made at the very end of the file, so a brand-new Nimali (`?empty`) has no plants in it while other members keep theirs.

```php
foreach (array_merge($mock_plants, $mock_community_plants) as $plant) {
    if (!$plant['is_public']) {
        continue;   // stage B: WHERE plants.is_public = 1
    }
    $owner = $mock_users[$plant['user_id']];
    $mock_public_plants[] = array_merge($plant, [
        'username'    => $owner['username'],
        'location'    => $owner['location'],
        'like_count'  => $mock_like_counts[$plant['id']] ?? 0,
        'on_exchange' => in_array($plant['id'], $listed_plant_ids, true) ? 1 : 0,
    ]);
}
```

**Problems and fixes**
- The `?empty` switch used to set `$mock_like_counts = []`, which would also have wiped the other members' likes. It now removes only the ids of Nimali's plants, and that line has to run *before* `$mock_plants` is emptied.

> [!tip] What to remember
> - Store a fact once (the town on the user) and join it where it is needed; never copy it into every plant.
> - Privacy is decided where the data is loaded, not where it is printed: a private row must never reach the page.

**Check yourself**
1. Why does each community plant store `user_id` and not the owner's town?
2. What SQL would give `on_exchange`?
3. Searching "monstera" gives 6 plants, while p16 says 126. Is that a bug?

> [!success]- Answers
> 1. The town belongs to the user. If Dinesh moves, one row changes and every plant shows the new town. A copy in each plant could go out of date.
> 2. `EXISTS (SELECT 1 FROM exchange_listings WHERE plant_id = plants.id AND status = 'open') AS on_exchange`.
> 3. No. The page counts the rows it really has. The mock data holds 17 public plants. In stage B the same code counts the database.

Lessons: [[00.07 Mock Data Shaped Like Database Rows]] · [[04.05 Counts, Averages and Rating Rules in SQL]]

---
### Step 4.2 — Explore: search, tabs, community cards, active filters and sort
> [!important] Goal (COM-01 and COM-02, UI half)
> Build `explore.php` (wireframe p16, elements 1, 2, 4 and 5): one search box, the Plants / People tabs with their counts, removable chips for the active filters, a sort, and a grid of community plant cards.

**What was done (2026-10-15, branch `feature/pages-ui`, commit `7d3dc1d`)**
- `public/explore.php` (new):
  - Reads **`q`, `tab`, `species[]`, `health`, `location`, `exchange` and `sort`** from the address and checks each one against what is allowed. A wrong value falls back to the default.
  - The search looks in the nickname, both species names and the owner's username. **A search that starts with `@`** opens the People tab (p16: "Typing @ switches to People").
  - Each **active filter is a chip** whose link is the same address without that filter. "Clear all" appears when there are two or more.
  - **Sort** is Most liked (default), Newest or Name A–Z, in a `<details>` dropdown of links, so it opens without JavaScript.
  - People are counted already (for the tab), but the People tab only shows a placeholder until step 4.4.
- `partials/plant-card.php`: a **community version**, `partial('plant-card', ['plant' => $plant, 'community' => true])`, with ♡ likes on the photo, and the owner's avatar, @username and town instead of the care hint and collection.
- `public/assets/css/explore.css` (new): the white top band, the tabs drawn like the segmented control, the results bar, and a grid with 1, 2 or 3 columns. `components.css`: the community card styles. `partials/README.md` mentions the new option.
- Checked: `?q=monstera` gives Thai Con 87, Big Momma 48, Swiss 31, Monty 12, Leafy 9, Holey 7, **exactly the order on p16**. `<script>` typed into the search prints as text. A hand-made address with wrong values (`species[0][]=1`, `health=xx`, `tab=evil`, `location=Mars`) shows all 17 plants with no PHP errors. Screenshots at 1280 and 375 px: no sideways scroll.

**Why this way**
- **One `$filters` array, one `$explore_url()`.** Every link on the page is "the current address with one thing changed". A small arrow function does that with `array_merge($filters, $changes)`, so no link can forget another filter.
- **Species arrive as an array.** `species[]=1&species[]=10` becomes `[ '1', '10' ]` in `$_GET`. Each value is checked with `is_string()` and `ctype_digit()` before it is used, because a hand-made address can put an array inside the array.
- **Most liked ties fall back to the name.** Comparing `[likes, name]` pairs with `<=>` sorts by likes and then by name in one line, so Big Momma and Pip (both 48) never swap places between visits.
- **"Newest" sorts by id.** `plants` has no `created_at`. Ids grow with every new row, so the highest id is the newest plant.
- **A new search drops the filters** but keeps the tab and the sort. The species counts belong to the old search, so filters from it would often hide everything.

```php
$filters = ['q' => $q, 'species' => $species_filter, 'health' => $health, /* … */];
$explore_url = fn (array $changes) => query_url('explore.php', array_merge($filters, $changes));

// The chip for "Thriving" links to the same page without that one filter:
$active_filters[] = ['label' => health_label($health), 'href' => $explore_url(['health' => ''])];
```

**Problems and fixes**
- A curl test with `species[0][]=1` in the address failed without reaching the page, because curl treats `[ ]` as a pattern. Adding `-g` sent the address as typed. The page itself was fine.

> [!tip] What to remember
> - Filters in the address: read, check against a whitelist, fall back to a default, and build every link from the same array.
> - A link that removes a filter is just the current address with that one value emptied.

**Check yourself**
1. Why are the tabs and sort options links and not buttons?
2. What does `$explore_url(['sort' => 'name'])` return on `explore.php?q=monstera&health=thriving`?
3. Why is each `species[]` value checked with `is_string()` before `ctype_digit()`?

> [!success]- Answers
> 1. A link changes the address, so each tab and sort can be bookmarked, shared and reached with Back, which p16's UX notes ask for. A button would need a form or JavaScript.
> 2. `explore.php?q=monstera&health=thriving&sort=name`: the other filters are kept and only the sort changes.
> 3. `?species[0][]=1` makes the value an array. `ctype_digit()` expects a string, so checking the type first keeps hand-made addresses from causing errors.

Lessons: [[01.03 Query Strings and GET Parameters in PHP]] · [[00.03 PHP Includes and Partials]]

---
### Step 4.2a — Fix: the header lit My Garden on other members' plants
> [!important] Goal (CORE-21 and COM-01, review fix)
> Ranshitha opened Thai Con from Explore and the header showed **My Garden** as the current section. Someone else's plant is part of Explore, not your garden.

**What was done (2026-10-15, branch `feature/pages-ui`, commit `b75cdc1`)**
- `includes/header.php`: `$area = $nav_area ?? current_area();`. A page may now say which section it belongs to; otherwise the file name decides, as before.
- `public/plant.php`: `$nav_area = $is_owner ? 'garden' : 'explore';`
- Checked: Thai Con as Nimali or as a visitor → **Explore**. Monty as Nimali → **My Garden**. My Garden, Explore and Dashboard are unchanged.

**Why this way**
- `current_area()` only knows the file name, and `plant.php` serves two kinds of plant. The owner check, which the page already does for the breadcrumb, is what tells them apart. The header now follows the breadcrumb: "My Garden › Living room › Monty" or "Explore › @kandy.jungle › Thai Con".
- **Not where you came from.** Nimali's own Monty still lights My Garden even when she opens it from Explore. Reading the previous page (`HTTP_REFERER`) is unreliable: browsers may leave it out, and a shared link has none. "Whose plant is it?" always has an answer.
- The `??` operator means "use `$nav_area` if the page set it, otherwise `current_area()`", so no other page had to change.
- The phone bottom bar has no Explore button, so on a phone nothing is lit on someone else's plant. That is correct: it is not Home, Garden, Exchange or Profile.

```php
// plant.php
$nav_area = $is_owner ? 'garden' : 'explore';

// includes/header.php
$area = $nav_area ?? current_area();
```

> [!tip] What to remember
> - Let the data decide when the file name cannot: one page file can belong to two sections.
> - `??` gives a default only when the variable is missing or null.

**Check yourself**
1. Why not use the address of the previous page to choose the lit link?
2. Which link is lit when Nimali opens her own Monty from Explore, and why?

> [!success]- Answers
> 1. The browser may not send it, and a shared or typed link has none. The owner check works for every visitor.
> 2. My Garden: it is her plant, and the breadcrumb says "My Garden › Living room › Monty". Header and breadcrumb always agree.

---
### Step 4.3 — Explore: the filter sidebar
> [!important] Goal (COM-03, UI half)
> The filter sidebar of p16 (element 3): species checkboxes with counts, health chips, a location list and the "Available for exchange" switch. On a phone and a tablet it folds behind a **Filters** button (p23); on a desktop it sits beside the results.

**What was done (2026-10-15, branch `feature/pages-ui`, commit `5f573aa`)**
- `public/explore.php`:
  - **One GET form** with `species[]` checkboxes, `health` radio buttons drawn as chips (with **Any** to take the choice back), a `location` list of the towns that have public plants, and an `exchange` switch. Hidden fields carry the search, tab and sort, so **Apply filters** keeps them.
  - **Species counts.** The plants are now filtered in two passes: every filter except species (`$before_species`), then species. The counts come from the first pass. The most common species come first; the first five are shown and the rest fold under **"+ N more"**, which opens by itself when one of them is ticked.
  - A ticked species stays in the list even when it has 0 plants, so it can always be unticked.
  - "Filters" with a leaf-green number showing how many filters are on, and **Clear all** at the top of the form.
- `public/assets/css/explore.css`: the panel, the chips-as-radios, and the desktop layout, a 220 px column beside the results (`grid-template-columns: 220px minmax(0, 1fr)`).
- `public/assets/img/icons/sliders-horizontal.svg` (Lucide 1.52.0, same version as the others) for the Filters button, and listed in `docs/credits.md`.
- Checked: `?q=monstera` lists only the three monstera species (Swiss cheese plant 3, Swiss cheese vine 2, Thai Constellation 1). `?q=zzz&species[]=3` keeps "Snake plant 0" ticked. Ticking Aloe vera (folded) opens "+ N more". Screenshots at 1280, 768 and 375 px, with the phone panel closed and open: no sideways scroll. The People tab has no sidebar.

**Why this way**
- **Counts leave out their own filter.** If the species counts used every filter, ticking Swiss cheese plant would make every other species show 0, and you could never add a second one. That is how shop filters work. In SQL it is a second query: `GROUP BY species_id` with the same `WHERE`, minus the species condition.
- **Radio buttons for health, checkboxes for species.** A plant has one health status, so only one makes sense at a time. A search can want several species. The real `<input>` stays in the page for the keyboard and screen readers. Only the chip after it is seen, styled with `input:checked + .chip`.
- **One `<details>`, two behaviours.** On a phone, `<details>` gives the Filters button with no JavaScript (lesson 01.05). On a desktop the panel must always be open. A closed `<details>` hides everything except its `<summary>` inside a box called **`::details-content`**. Setting `content-visibility: visible` on that box shows the filters, and the summary is hidden.
- **A safe fallback.** That CSS sits inside `@supports selector(::details-content)`. A browser that does not know it simply keeps the Filters button on the desktop too, so nothing is ever lost.

```css
@media (min-width: 1024px) {
  @supports selector(::details-content) {
    .filter-panel > summary { display: none; }                         /* no button on a desktop */
    .filter-panel::details-content { content-visibility: visible; }   /* always open */
  }
}
```

```php
// Counts: the search and the other filters count, the species filter itself does not
foreach ($before_species as $plant) {
    $species_counts[$plant['species_id']] = ($species_counts[$plant['species_id']] ?? 0) + 1;
}
```

**Problems and fixes**
- My first test printed no species names: the HTML puts the name and the count on separate lines, and `grep` reads one line at a time. Removing the line breaks first (`tr -d '\n'`) fixed the test. The page was right all along.

> [!tip] What to remember
> - A filter's counts ignore that filter itself, or the other options all drop to 0.
> - Radio = one choice, checkbox = many. A visually hidden input plus `:checked + .chip` gives custom looks without losing the keyboard.
> - New CSS goes inside `@supports` with a fallback that still works.

**Check yourself**
1. Why does ticking "Swiss cheese plant" not set "Swiss cheese vine" to 0?
2. What does `name="species[]"` do that `name="species"` would not?
3. What happens on the desktop in a browser that does not support `::details-content`?

> [!success]- Answers
> 1. The species counts come from `$before_species`, the plants after every filter except species, so ticking a species never changes the counts beside it.
> 2. The `[]` tells PHP to collect every ticked box into an array, `$_GET['species'] = ['1', '10']`. Without it, only the last ticked box would arrive.
> 3. The `@supports` block is skipped, so the Filters button stays and the panel opens when pressed, as on a phone.

Lessons: [[01.05 Dropdown Menus with details and summary]] · [[04.04 Search, Filters and Pagination]] · [[01.03 Query Strings and GET Parameters in PHP]]

---
### Step 4.4 — Explore: People tab, matching people row and no-results state
> [!important] Goal (COM-01 and COM-02, UI half)
> Finish P09 (wireframe p16): the **People tab** ("lists profiles with rating and plant count"), the **"Plant lovers matching" row** above the plants (element 6), and a **no-results state** that "suggests removing the last filter and shows popular plants instead of a blank page" (UX notes). Then the page checks.

**What was done (2026-10-20, branch `feature/pages-ui`, commit `85c38bc`)**
- `partials/member-card.php` (new): one card for a member: avatar, **@username**, full name, town with a pin, "64 plants · ★ 5.0 (11)", and a sage tag **"2 matching plants"** when the search found some of their plants. The whole card links to `user.php?u=<username>` (built later this week). With `'compact' => true` it becomes the short card of the row: avatar, @username and one line, "42 plants · ★ 4.8 (14)". It prints only public columns, never email or phone.
- `public/explore.php`:
  - **People tab**: the count ("5 people"), a line saying why these people match, and a grid of member cards (1 column on a phone, 2 on a tablet, 3 on a desktop). Nobody found: an empty state, *No members match "zzzz"*, with **Show all members**.
  - **Matching row** on the Plants tab: only for a search that matched someone, at most three cards, and **See all 5 people**, which opens the People tab with the same search. `?q=monstera` gives @aroid.amaya, @kandy.jungle and @dinesh.greens, **the three on p16, in the same order**. *(Since the review fix `e8505f9`, "See all" shows only when the row leaves someone out.)*
  - **No plants found**: a message naming the search, then buttons. With filters on: **Remove "Kandy"** (the last chip) and **Clear all filters** (two or more filters) or **Clear the search**. Without filters: **Show all plants**. Under it, **Popular plants**: the six most-liked public plants. *(Changed in the review fix `e8505f9`: when the search word finds nothing even without the filters, the buttons are **Clear the search** and **Show all plants**.)*
  - `match_count` is only counted for a plain word search: with no word every plant would "match", and an `@` search is about names.
  - **Bug fix:** a hand-made address with a list where one word belongs (`q[]=x`, `sort[]=x`) gave a PHP warning or even a fatal error. Every single value now goes through `$get_text()`, which accepts text only.
  - Small tidy-ups: the two tab addresses are worked out once (`$plants_tab_url`, `$people_tab_url`), and the "most liked" comparison became a named function, `most_liked_first()`, used by the sort and by Popular plants.
- `partials/empty-state.php`: an action may now give `'url'` (a full address built with `query_url()`) instead of `'href'` (a page name). The chip links on Explore are full addresses.
- `public/assets/css/components.css`: the member card styles (phone: avatar beside the text; tablet and up: upright and centred, avatar 72 px). The empty state lets a very long word break (`overflow-wrap: anywhere`).
- `public/assets/css/explore.css`: the sideways strip for the row, the People grid, the Popular plants section and one heading style for both new headings.
- `partials/README.md` and `public/styleguide.php`: the member card is listed and shown in both looks.
- **Checked:**
  - `php -l` on every changed file.
  - curl, as a visitor and as Nimali: `?q=monstera` (6 plants, 5 people), `&tab=people`, `?q=@kandy` (opens People, 1 person), `?q=zzzz`, `?q=Perera` (finds Nimali by surname, no plants), `?q=monstera&health=sick&location=Kandy`. Hand-made addresses `tab=evil`, `q[]=x`, `tab[]=people&sort[]=x&health[]=y&location[]=z&exchange[]=1`, `q[a][b]=x`: no PHP errors. `q=<script>alert(1)</script>` prints as text in the title, the search box, the heading and the empty state.
  - Screenshots looked at: 1280 px (Plants and People for monstera, @kandy, zzzz, the filtered no-results, the People no-results), 768 px (monstera Plants and People, zzzz) and a true 375 px (monstera top and further down, People, @kandy, zzzz further down). The style guide's new section at 1280 px.
  - **No sideways scroll, measured:** for 11 addresses × 4 widths (375, 768, 1024, 1280) × visitor and member = 88 page views, the page's `scrollWidth` equals its `clientWidth` every time, including a search for a 100-letter word.
  - Every image has `alt`, there is one `<h1>`, no duplicate `id`s, no email or phone anywhere on the page. The page has no JavaScript, so there can be no console errors.
  - The other pages (Landing, Dashboard, My Garden, both plant views, Add plant, Settings, About, Log in, Sign up, the style guide, `?empty`) still have no PHP errors after the shared changes.
- **Review fixes, same day (commits `e8505f9` and `a11847a`):** an independent review found eight problems in this step, plus a wrong explanation and some missing wording in this note. All were reproduced, then fixed; see **Problems and fixes** below.

**Why this way**
- **One partial, two looks.** The same member appears in the row and on the People tab, so both are drawn by one file. A `compact` option changes the layout but not the data, the same idea as the plant card's `community` option in step 4.2. If the rating format changes, it changes in one place.
- **The row scrolls, the page does not.** Three cards do not fit side by side on a phone. Stacking them would push the plants a screen down. A strip that scrolls sideways keeps them in one short row, and the second card peeks in at the edge so people know there is more. Only the strip has `overflow-x: auto`, so the page stays 375 px wide (lesson 01.06).
- **"The last filter" is the last chip.** The filter form sends every choice at once, so the page cannot know which one was ticked last. The last chip in the row is the closest honest answer, and its link already removes just that filter. If that still finds nothing, the next page suggests the next one. This only applies when the search word finds plants and the filters hide them all: when the word finds nothing on its own, no filter is to blame, so the page offers **Clear the search** and **Show all plants** instead (review fix).
- **Popular plants ignore the search and the filters.** Their job is to show the page is not broken and give something to look at. They are the same community cards, so they open the same plant pages.
- **Text only from the address.** PHP turns `q[]=x` into an array. The old line `trim((string) ($_GET['q'] ?? ''))` turned that array into the word "Array" with a warning: the warning came from the `(string)` cast, not from `trim()`. A bare `trim()` on an array is worse: in PHP 8 it stops the page with a fatal TypeError. An array used as an array key (`array_key_exists($sort, …)`) also stops the page with a fatal error. Checking `is_string()` first, in one small function, protects all six single values. The species list already did this in step 4.2. Since the review fix, `$get_text()` also checks `mb_check_encoding()`: bytes that are not real UTF-8 text (`q=%FF`) make `e()` print an empty string.
- **Phone layout for the People card.** A centred card with a big avatar left a lot of white space on a phone and made each card about 270 px tall. Beside the text it takes about 160 px. From 768 px there is room for the upright card of a profile grid.

The code as it stands after the review fixes:

```php
// A hand-made address can send a list where one word belongs, or bytes that are not UTF-8: only real text is accepted
$get_text = fn ($name) => (is_string($_GET[$name] ?? null) && mb_check_encoding($_GET[$name], 'UTF-8')) ? $_GET[$name] : '';
$q    = mb_substr(trim($get_text('q')), 0, 100);
$sort = $get_text('sort');   // then checked against the allowed sorts, as before

// The row: only on the Plants tab, only for a search, three at most
$people_row = ($tab === 'plants' && $term !== '') ? array_slice($people, 0, 3) : [];

// No plants: suggest removing the last chip, unless the search word finds nothing even without the filters
$search_found_nothing = $term !== '' && !$found;
$last_filter          = ($active_filters && !$search_found_nothing) ? end($active_filters) : null;
```

```php
<?php // "See all" only when the row leaves someone out (p16: 3 of 8) ?>
<?php if ($people_count > count($people_row)): ?>
  <a class="panel-link" href="<?= e($people_tab_url) ?>">See all <?= e($people_count) ?> people</a>
<?php endif; ?>
```

```php
<?php // explore.php: the same partial in two places ?>
<?php partial('member-card', ['member' => $member, 'compact' => true]); ?>   <?php // the row ?>
<?php partial('member-card', ['member' => $member]); ?>                      <?php // the People tab ?>
```

```css
/* Phone: one strip that scrolls sideways, each card snapping into place */
.people-row-list {
  display: grid;
  grid-auto-columns: 240px;                /* narrower than any phone: the next card peeks in */
  grid-auto-flow: column;
  overflow-x: auto;                        /* only the strip scrolls */
  scroll-snap-type: x mandatory;
}
.people-row-list > * { scroll-snap-align: start; }
```

**Problems and fixes**
- **`q[]=x` and `sort[]=x` broke the page.** Step 4.2's test tried `species[0][]=1`, `tab=evil` and other wrong *words*, but no list in a single-value field. This step's test found a warning ("Array to string conversion") for `q[]=x` and a fatal error ("Illegal offset type") for `sort[]=x`. Fixed with `$get_text()`. The same problem is still in `garden.php`, `plant.php` and `settings.php` (see Open points).
- **A 100-letter search word pushed the page sideways** at 375 px: the empty-state heading could not break it. `overflow-wrap: anywhere` on the empty state and on the two new headings fixed it.
- **First People card on a phone was too tall** (centred, lots of white space). Changed to avatar beside the text on phones.
- **Screenshot tool:** headless Edge sometimes returned before the PNG was written, so the screenshot seemed to fail. The helper script now waits for the file.
- **Measuring the page width:** a test page opened from the disk (`file:///`) is not allowed to read pages from `localhost` (a different origin), even with the browser's security switched off. A temporary PHP built-in server (`php -S localhost:8099`, with a small router kept in the scratch folder, not the repo) served the test page and the site from one origin. The first runs printed only "waiting": the generated JavaScript had a real line break inside a string. A sanity test with a 600 px-wide page showed the measurement does catch overflow.
- **Review fixes (2026-10-20, commits `e8505f9` for the page and `a11847a` for the styles).** An independent review of this step reported the problems below. Each one was reproduced with curl, a screenshot or PHP itself before it was fixed.
  - **"See all 1 person".** The link printed for every match, so a search that matched one member (`?q=Perera`, `?q=goldie`) read "See all 1 person". With two or three matches it promised more than the row already showed. It now shows only when `$people_count > count($people_row)`, as on p16 (3 of 8 shown). The People tab is always one tap away in the tabs, so nothing is lost.
  - **The no-results state blamed the filters when the search word found nothing by itself.** `?q=zzzz&health=sick&location=Kandy` said *No plants match "zzzz" with these filters* and offered Remove "Kandy" and Clear all filters. Both led to another empty page. The new check `$search_found_nothing` covers this case: the title is *No plants match "zzzz"*, the primary button is **Clear the search** (it keeps the filters), and **Show all plants** (no search, no filters, same sort) is the exit that always works. Clearing the search can still find 0 plants (Sick + Kandy has none), but that page then suggests removing a filter, so it is never a dead end.
  - **`role="status"` on the result counts.** A `role` on a heading replaces its heading role. Screen readers could not jump to "6 plants" or "5 people", and on the People tab the outline went from the `h1` straight to the `h3` cards. Removed. Nothing changes without a reload yet; week 6 can put a live region on a wrapper when the results update in place.
  - **`q=%FF` blanked the headings.** Bytes that are not real UTF-8 make `htmlspecialchars()`, inside `e()`, return an empty string. The headings *No plants match "…"* and *No members match "…"* printed completely empty, and so did the page title. `$get_text()` now also requires `mb_check_encoding($value, 'UTF-8')`, so such a value counts as "not given", like an array.
  - **A bare "?" at the end of some links** (`explore.php?`, for example Show all plants). `query_url()` dropped `null` and `''` but kept an empty list (`'species' => []`), and `http_build_query()` prints nothing for an empty list. `query_url()` in `includes/helpers.php` now drops `[]` too, so every page gets clean links.
  - **The Plants / People tabs were 40 px tall**, under the 44 px touch-target rule. My checks had measured every new control but not the tabs from step 4.2. Now 44 px. The shared segmented control and the Log in / Sign up tabs are still 40 px (see Open points).
  - **"Filters" sat about 12 px above the first results heading on a desktop** when no filter was on, where p16 has them on one line. Each heading was centred in its own box: the row head was 44 px tall because of its See all link, the filter head had no 44 px child until Clear all appeared. Both heads now have `min-height: 44px`. Measured at 1024 and 1280 px: both start on the same pixel row in every state tried.
  - **The strip's cards were 240 px, not 80%.** A grid column with `minmax(240px, 80%)` only grows past 240 px when the strip has spare room, and two or three cards already overflow a phone, so they stayed at 240 px. The page looked fine, but the rule and lesson 01.06 described something else. The rule is now a plain `grid-auto-columns: 240px`: it looks the same and says what the screen shows. Lesson 01.06 is corrected.
  - **This note gave the wrong reason for the `q[]=x` warning.** It said `trim()` on an array gives a warning. The warning came from the `(string)` cast; `trim()` on an array is a fatal TypeError in PHP 8 (checked with the PHP command line). Corrected under **Why this way**. The Open points also lacked some of the invented wording; added.
  - **Re-checked:** `php -l`. curl as a visitor and as Nimali on 26 addresses (14 Explore addresses, including `q=%FF`, `q=%C0%AF`, `q[]=x`, `sort[]=x` and `<script>`, and the 12 other pages after the `helpers.php` change): no PHP errors, and the script tag still prints as text. Screenshots at 1280 px (monstera, Perera, zzzz with filters, monstera with filters) and a true 375 px. Measured 7 states × 4 widths (375, 768, 1024, 1280): no sideways scroll, both tabs 44 px, no heading with a `role`.
  - **Screenshot tool:** two headless Edge screenshots started at the same time failed, because they share one browser profile folder. Taking them one after the other fixed it.

> [!tip] What to remember
> - Same data in two places → one partial with an option, not two copies.
> - A row may scroll sideways; the page never. Scroll only the box that needs it.
> - Anything in `$_GET` can be an array, or bytes that are not real text. Check `is_string()` (and the encoding) before treating a value as text.
> - A no-results page should always offer a next step and something to look at. Before blaming the filters, check that the search word finds something on its own.
> - A `role` on a heading replaces the heading. Put a live region on a wrapper, never on the `<h2>`.
> - Show a "See all" link only when something is left out.

**Check yourself**
1. Why is the member card one partial with a `compact` option instead of two partials?
2. What did `explore.php?sort[]=x` do before this step, what does it do now, and why?
3. On a phone, the three member cards are wider than the screen together. Why does the page not scroll sideways?

> [!success]- Answers
> 1. Both show the same member row from the same data. One file means the username, rating and link are written once, so the row and the People tab can never disagree. The option only changes the layout.
> 2. Before: a fatal error, because `$sort` was an array and `array_key_exists()` cannot use an array as a key. Now `$get_text('sort')` returns `''` for anything that is not text, `''` is not an allowed sort, so the page falls back to Most liked.
> 3. Only the strip has `overflow-x: auto`. Its extra width becomes a scroll inside the strip, and its negative margins and padding add up to exactly the screen width. The page's `scrollWidth` equals its `clientWidth`, which was measured at 375 px.

Lessons: [[00.03 PHP Includes and Partials]] · [[01.06 Sideways Scrolling Rows and Scroll Snap]] · [[01.03 Query Strings and GET Parameters in PHP]] · [[01.04 Rendering States - Empty, Owner and Visitor Views]] · [[04.04 Search, Filters and Pagination]]

---
## Open points
- **Explore (P09), mock data:** Dinesh lives in **Negombo** (p16), while his snake plant listing is in Kuliyapitiya: a listing can be somewhere other than its owner's home town. *Monstera* 'Thai Constellation' has its own species row because p16 prints that name. The owners of Leafy and Holey (cut off on p16), Ruwan's counts, every member's `created_at`, the collections and the six plants after Holey are invented. All monsteras use the Monty placeholder drawing.
- **Explore (P09), page:** "Newest" sorts by plant id (no `created_at` on `plants`). A new search drops the filters (my choice). The species chip shows the common name ("Swiss cheese plant"), where p16 shows "Monstera". The People tab was a placeholder until step 4.4, which filled it (see the next bullets). Nimali appears among the people, and her own plants among the results: p16 shows Monty, so I kept both.
- **Explore (P09), filters:** **Apply filters** is not on p16; it is needed until week 6 applies them on every change. Health has an **Any** chip (not on p16) so a choice can be taken back without JavaScript. The species list is one row per species, not p16's groups ("Monstera", "Succulents"). "Clear all" shows both in the sidebar and beside the chips (the sidebar is hidden on a phone). p23's bottom sheet for filters waits for week 6; until then the panel opens in place.
- **Explore (P09), member card (step 4.4):** a partial (`partials/member-card.php`), because the row and the People tab show the same thing. The compact card keeps the rating count, "★ 4.8 (14)", where p16 shows "★ 4.8": the rating partial looks the same everywhere. The compact avatar is the 40 px default, not `sm` (28 px): on p16 it is as tall as the two text lines. On the People tab the avatar is 72 px (the profile's 96 px crowds a card a third of the page wide). On a phone the People card puts the avatar beside the text; from 768 px it stands upright and centred. "N matching plants" shows only on the People tab, only for a plain word search. Nimali appears among the people when she is logged in, as before.
- **Explore (P09), People tab and row (step 4.4):** the People tab is ordered by most matching plants, then the biggest garden; without a search, the biggest garden first. A muted line under the count says why these people match (not on p16). Its wording is mine: *Names, usernames and towns that match "…", and members who grow a matching plant.* (for an `@` search: *Names and usernames that match "…".*). The row shows only for a search that matched someone, at most three cards, also above "0 plants" (e.g. `?q=Perera` finds Nimali by surname). "See all 5 people" shows only when the row leaves someone out, so a search matching one to three members has no link (review fix; p16 shows it with 3 of 8, and "See all 1 person" read badly). It has no arrow, and quotes are straight, as on p16. Its heading and "Popular plants" use Poppins semibold like p16's row heading, not Lora. Copy never uses he or she ("See all 5 people", not "See her listings").
- **Explore (P09), no results (step 4.4):** "the last filter" is the last chip in the row, because one form sends every filter at once. Buttons when the search word finds plants but the filters hide them all: Remove "*last chip*" (e.g. Remove "Kandy"), then Clear all filters (two or more filters) or Clear the search. When the search word finds nothing on its own (review fix): Clear the search (only with filters on; it keeps them), then Show all plants (drops the search and the filters, keeps the sort, grid icon); the filters are not blamed. Popular plants are the six most-liked public plants (ties by name), ignoring the search and the filters; six fills 1, 2 and 3 columns evenly. All wording is mine: *No plants match "zzzz"* (+ *with these filters*, only when the filters are to blame), *No plants match these filters*, *No public plants yet* with *Plants appear here when members make them public.*, *Check the spelling or try a shorter word. Until then, here are some popular plants.*, *Remove a filter to widen the search. Until then, here are some popular plants.*, *Popular plants · The most-liked public plants in the community.*, *No members match "…"* (or *No members yet*) with *Check the spelling, or try part of a name, a town or an @username.* and Show all members.
- **Explore (P09), code (step 4.4):** `partials/empty-state.php` accepts `'url'` beside `'href'`. Address values are text only, and valid UTF-8 only (`$get_text()`, review fix). `query_url()` in `includes/helpers.php` now also leaves out empty lists, so no link ends in a bare "?" (review fix, affects every page, no visible change). The result counts are plain headings, without `role="status"` (review fix). **Same bug still open in earlier pages:** `garden.php?q[]=x` (warning), `garden.php?sort[]=x`, `plant.php?id=42&tab[]=x` and `settings.php?saved[]=x` (fatal errors). Suggestion: move `$get_text()` (with its encoding check) into `includes/helpers.php` as a shared helper and use it on those pages, as its own small fix step. A second suggestion for the same step: add `ENT_SUBSTITUTE` to `htmlspecialchars()` in `e()`, so a broken character shows as � instead of blanking the whole value on any page (not done: it changes a shared week-1 helper). To decide in DES-07: whether a member's public `plant_count` (42, 64 …) counts private plants too.
- **Explore (P09), touch targets and layout (step 4.4 review fixes):** the Plants / People tabs are now 44 px tall, 4 px taller than the shared segmented control (`components.css`, `.segmented label`) and the Log in / Sign up tabs (`auth.css`, `.auth-tab`), which are still 40 px, under the 44 px rule. To decide: raise those to 44 px too, as a small fix step for the earlier pages. The "Filters" head and the row head are always 44 px tall, so on a desktop "Filters" lines up with the first results heading, as on p16. The phone strip's cards are a fixed 240 px (it was `minmax(240px, 80%)`, which drew 240 px anyway); about 107 px of the next card shows at 375 px.
