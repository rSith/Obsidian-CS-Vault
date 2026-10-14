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

- [ ] P09 Explore (`explore.php?q=`)
- [ ] P14 Public Profile (`user.php?u=`)
- [ ] P10 Exchange (`exchange.php`)
- [ ] P11 Listing Detail (`listing.php?id=`)

## Learn this week
- Reusing partials with different data → [[00.03 PHP Includes and Partials]]
- Query strings: reading `?id=` and `?q=` → [[01.03 Query Strings and GET Parameters in PHP]]
- Two states of one page (before and after "I'm interested") → [[01.04 Rendering States - Empty, Owner and Visitor Views]]

## Checks for every page
- [ ] Matches its wireframe page and the Figma prototype
- [ ] Works at 375, 768 and 1024 px with no horizontal scroll and no console errors
- [ ] All output escaped with `e()`, images have alt text, inputs have labels
- [ ] No contact details are printed before interest is expressed

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
## Open points
- **Explore (P09), mock data:** Dinesh lives in **Negombo** (p16), while his snake plant listing is in Kuliyapitiya: a listing can be somewhere other than its owner's home town. *Monstera* 'Thai Constellation' has its own species row because p16 prints that name. The owners of Leafy and Holey (cut off on p16), Ruwan's counts, every member's `created_at`, the collections and the six plants after Holey are invented. All monsteras use the Monty placeholder drawing.
- **Explore (P09), page:** "Newest" sorts by plant id (no `created_at` on `plants`). A new search drops the filters (my choice). The species chip shows the common name ("Swiss cheese plant"), where p16 shows "Monstera". The People tab is a placeholder until step 4.4. Nimali appears among the people, and her own plants among the results: p16 shows Monty, so I kept both.
- **Explore (P09), filters:** **Apply filters** is not on p16; it is needed until week 6 applies them on every change. Health has an **Any** chip (not on p16) so a choice can be taken back without JavaScript. The species list is one row per species, not p16's groups ("Monstera", "Succulents"). "Clear all" shows both in the sidebar and beside the chips (the sidebar is hidden on a phone). p23's bottom sheet for filters waits for week 6; until then the panel opens in place.
