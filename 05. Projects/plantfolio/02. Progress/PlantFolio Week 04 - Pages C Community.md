---
type: project-note
project: PlantFolio
status: done
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
- [x] P14 Public Profile (`user.php?u=`) — UI built 2026-10-20 (step 4.5): identity, stats, tabs (Collections, Plants, Listings, Ratings), collection collages, trader rating and latest review, owner and visitor views, and a 404 for unknown members (not wired). Review fixes in `3ef2094`. Still to check by hand: hover, keyboard focus and a real phone.
- [x] P10 Exchange (`exchange.php`) — UI built 2026-10-20 (step 4.6): listing actions, how-it-works strip, search, Swap / Free, distance and species filters, sort, listing cards, no-results state, visitor view without distances (not wired). Still to check by hand: hover, keyboard focus and a real phone.
- [x] P11 Listing Detail (`listing.php?id=`) — UI built 2026-10-20 (step 4.7): photo gallery, summary, About this plant with the link to the plant's story, interest card in state A and state B (contact details only after "I'm interested", only the fields the owner chose), owner view, closed listing, visitor view with Log in and back, owner trust card, safe swapping tips and a 404 (not wired). Review fixes in `3dd2532` and `5d33ed3`. Still to check by hand: hover, keyboard focus and a real phone.

*(Step 4.8, close-out on 2026-10-21: the four pages were checked together and the small problems left by the reviews were fixed, see the work log. Keyboard focus is now checked on all four pages by pressing Tab through them: every stop shows a focus ring. Hover and a real phone are still to do by hand, with the Figma comparison: see "Decisions waiting for Ranshitha". Week 4 is UI built; nothing is wired until stage B.)*

## Learn this week
- Reusing partials with different data → [[00.03 PHP Includes and Partials]]
- Query strings: reading `?id=` and `?q=` → [[01.03 Query Strings and GET Parameters in PHP]]
- Two states of one page (before and after "I'm interested") → [[01.04 Rendering States - Empty, Owner and Visitor Views]]
- A row that scrolls sideways on a phone (Explore's matching people) → [[01.06 Sideways Scrolling Rows and Scroll Snap]]

## Checks for every page
- [ ] Matches its wireframe page and the Figma prototype *(so far: P09 compared with p16, P14 with p21 and P10 with p17 and p23 side by side; P11 with p18 (no phone layout on p18; p23 for the phone rules); not yet with Figma)* *(Step 4.8: all four compared again after the review fixes, screenshots at 375, 768 and 1280 px beside p16, p21, p17, p18 and p23; they match apart from the differences in the Open points. **Left unticked: the Figma comparison needs Ranshitha.**)*
- [x] Works at 375, 768 and 1024 px with no horizontal scroll and no console errors *(so far: P09 measured at 375, 768, 1024 and 1280 px in 22 states, visitor and member, and screenshots at all three sizes; no JavaScript yet. Rechecked after the step 4.4 review fixes: 7 states × 4 widths, no sideways scroll, both tabs 44 px tall. P14 measured at 375, 768, 1024 and 1280 px in 20 states (80 page views, visitor, owner and empty), screenshots at all three sizes, the ⋯ menu open at 375 px; no JavaScript. Rechecked after the step 4.5 review fixes: 16 addresses (profile, Explore, My Garden, style guide, Dashboard, Plant Profile) × 4 widths, no plant-card badge covers another, no sideways scroll except a pre-existing 17 px on My Garden at 375 px (see Open points). P10 measured at 375, 768, 1024 and 1280 px in 5 states × member and visitor (40 page views), no sideways scroll; screenshots at all three sizes. P11 measured at 375, 768, 1024 and 1280 px in 13 states (52 page views: state A, state B after a real POST, after Withdraw, the owner, closed as owner and as visitor, the mock interest, the visitor, the 404, Log in with `?next=`), no sideways scroll; screenshots at all three sizes, the true 375 px through the DevTools protocol. Rechecked after the step 4.7 review fixes: 9 addresses (states A and B, the owner, closed, the 404, the visitor, Log in with `?next=`) × 4 widths, no sideways scroll; the two state B buttons share one line from 344 px up. Still to do: a real phone.)* *(Step 4.8: after all the fixes, 48 addresses (the four pages in their states as Nimali, visitor and empty Nimali, and every other page) × 375, 768, 1024 and 1280 px = 192 page views, no sideways scroll; this found and fixed a sideways scroll on Add / Edit plant at 768 px (`e8bc521`) and My Garden's 17 px (`a65c913`). Screenshots looked at at all three sizes. Ticked for the widths, measured in the browser at exact widths; the real phone is a separate check below.)*
- [x] All output escaped with `e()`, images have alt text, inputs have labels *(so far: P09 checked: `<script>` in the search prints as text everywhere, every image has `alt`, every input has a label. P14 checked: `<script>` in `?u=` prints as text, every image has `alt`, no duplicate ids; the page has no inputs. P10 checked: `<script>` in the search prints as text, every input and select has a label, the card photos have `alt`. P11 checked: a note with `<script>` and a line break prints as text, with the line break kept; every image has `alt` (a thumbnail: "Show photo 2 of 3"); the note has a visible label; one `<h1>`)* *(Step 4.8: a script listed every `<?= … ?>` in `public/`, `partials/` and `includes/` that is not a single `e()`, `icon()`, `nav_current()` or `saved_message()` call: of 818, the 88 left print only fixed words or values wrapped in `e()`. 138 rendered pages: 409 images, all with `alt`; 432 form controls, all labelled. `<script>` in every address value of every page printed as text. Every not-found page now has its `<h1>` (`dcc1ccd`).)*
- [x] No contact details are printed before interest is expressed *(so far: P09 has none: member and plant cards print only public columns. P14 has none: no email, phone, `mailto:` or `tel:` in any state. P10 has none: listing cards print only public columns. P11 prints them only in state B, and only the fields the owner ticked (and, on the owner's own open listing, the owner's own shared details, `$own_contact`: their own data, so nothing is revealed): curl on every listing as a visitor, as Nimali and as the empty Nimali, and on Exchange, the Landing page, five profiles, Explore, two plant pages and the Dashboard found no email, phone, `mailto:` or `tel:` anywhere else. Rechecked after the step 4.7 review fixes: the same result, 62 checks)* *(Step 4.8: 86 curl checks after all the fixes; 75 found nothing, and the 11 hits are all expected: state B on six listings with only the ticked fields, Nimali's own details on her listings 7 and 6, her own email on her own Settings page, and the site's sample address on About.)*
- [ ] Tested on a real phone *(Ranshitha: CLAUDE.md asks for it before a page is called finished. Not possible from here.)*
- [ ] Hover checked by hand *(Ranshitha: the CSS has hover styles for the cards, buttons and links, but nobody has moved a mouse over the pages yet. Keyboard focus was checked in step 4.8.)*

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
### Step 4.5 — Public Profile: identity, stats, tabs, collages and trader rating
> [!important] Goal (COM-05 and COM-13, UI half)
> Build `user.php?u=` (wireframe p21, warm style): a member's public portfolio and trust record as a trader. Identity under a sage cover band, a stats row, one main action, tabs for Collections, Plants, Listings and Ratings, collection collages, the rating breakdown and the latest review. Only public things, and never contact details.

**What was done (2026-10-20, branch `feature/pages-ui`, commits `c20072e`, `69bab66` and `c0181dd`)**
- `includes/mock-data.php` (commit `c20072e`):
  - **`$mock_users`**: every member gains `bio`, `collection_count` and `swap_count`. Their `plant_count` and `collection_count` are now **public-only**, because these rows are what other people see. Nimali shows **13 plants in 2 collections** to others, while her own Dashboard and My Garden (`$current_user`) still say 18 in 3.
  - **`$mock_collections`** (Nimali's) gain `cover_photos`, the three photos of the collage.
  - **`$mock_community_collections`** (new): the other members' collections, the ids 21 to 52 that `$mock_community_plants` already pointed at, two of them private. Each member's public collections add up to their plant count (Amaya 12 + 30 = 42).
  - **`$mock_user_ratings`** (new): 10 written reviews shaped like `user_ratings` rows, joined with the rater (`rater_username`, `rater_full_name`, `rater_avatar`) and the listing (`listing_title`). Each finished exchange appears twice, once in each direction. Nimali's newest is the one on p21: *"Healthy cuttings, well packed. Easy and friendly swap!"* by @dinesh.greens on 14 Sep.
  - **`$mock_rating_counts`** (new): the result of a `GROUP BY rated_id, score`, how many 5★, 4★ … each member received. Each member's counts add up to their `rating_count` and average to their `rating_avg`.
  - **`$mock_listings`**: Nimali's **Peace lily division** from p17 (id 6), so "See listings (2)" matches p21.
  - The `?empty` switch also clears her swaps, ratings and received reviews. *(Since the review fix `3ef2094`, also the reviews she wrote on other members' profiles.)*
- `public/user.php` (new, commit `69bab66`):
  - **Whose profile:** `?u=nimali.grows`, also `?u=@Nimali.Grows` (lower-cased, the `@` removed). A member with no `?u` sees their own profile. An unknown or missing name answers HTTP **404**, "Member not found", with a **Find people** button (Explore's People tab). Only text from the address is accepted (`$get_text()`, as on Explore).
  - **Identity:** the sage cover band with a decorative drawing (`alt=""`), the avatar (96 px, 120 px from 768 px) hanging over the band, the name in Lora, "@nimali.grows · Kurunegala · Member since Mar 2025", and the bio.
  - **Main action:** visitors and other members get **See listings (2)**, which opens the Listings tab. The owner gets **Edit profile** (Settings) instead. *(Review fix `3ef2094`: before it, a member with no open listings, such as @aroid.amaya, got no main action at all, only the ⋯ menu. Now they get **See plants (42)**, which opens the Plants tab. Only a member with no listings and no public plants has the ⋯ menu alone.)* A ⋯ `<details>` menu: **Report profile** (About › Contact) and **Community guidelines** for others; **Account settings**, **Privacy settings** and **Go to My Garden** for the owner.
  - **Stats row:** plants, collections, swaps completed, and "4.9 trader rating · 23" (or "– no ratings yet").
  - **Tabs** with counts, as links (`?tab=plants#tab-plants`) with `aria-current="page"`. **Collections**: one collage card per public collection (one large photo, two small); a card opens the Plants tab with only that collection (`&collection=1`). **Plants**: the public plants as community plant cards. **Listings**: the open listings as listing cards. **Ratings**: the written reviews (avatar, @username linking to that member's profile, stars, date, comment, and what was swapped).
  - **Trader rating card:** the big average, stars, "23 ratings", one bar per score from 5★ to 1★ with its count, then the latest review and "See all reviews (5)". Beside the tabs on a desktop, under them on a phone and a tablet.
  - **Owner view:** the same public page plus a note: *This is how other people see your profile. Hidden from them: 1 private collection and 5 private plants.* with a Privacy settings link.
  - **Empty states** for no public collections, plants, open listings or ratings: a next step for the owner (Go to My Garden, Add a plant, List a plant) and a plain sentence for everyone else.
  - The header lights **Profile** on your own profile and **Explore** on anyone else's (`$nav_area`, as on the Plant Profile in step 4.2a).
- `partials/stars.php` (new): five stars filled up to a score, read out as one image ("Rated 4.9 out of 5"). The style guide uses it too.
- `partials/plant-card.php`: a new option, `'show_owner' => false`, for community cards: the collection instead of the owner, because every card on a profile has the same owner.
- `public/assets/css/profile.css` (new), `public/assets/img/profile-cover.svg` (new: three plants from the mock drawings, without their background boxes), and small additions to `components.css`, `partials/README.md` and `public/styleguide.php`.
- **Fix (commit `c0181dd`):** the ⋯ menu no longer breaks "Community guidelines" over two lines.
- **Two sessions.** The first session stopped after commit `69bab66` (usage limit), before the checks and this note. The second session ran the checks below, made the menu fix and wrote this section.
- **Checked** (second session):
  - `php -l` on the five changed PHP files.
  - curl on 30 addresses as a visitor, as Nimali and as the empty Nimali: 200 for real members; 404 for `?u=nobody`, `?u=<script>alert(1)</script>`, `?u[]=x`, `?u=%FF`, a 300-letter name, and a visitor with no `?u`. No PHP warnings anywhere. The script tag prints as text, and a long name is cut to 40 letters in the message. `tab=bogus`, `tab[]=x`, `collection[]=1`, `collection=-1`, `collection=21` (someone else's) and `collection=3` (private) all fall back safely.
  - **Privacy:** Vera, Pearl, the Succulents collection and the two private community collections never appear to other people. No email, phone, `mailto:` or `tel:` anywhere.
  - **Other pages after the mock-data change:** every other page built so far (Landing, Dashboard, My Garden, three plant views, Explore in three states, Settings, the style guide, About, Log in, Sign up, Add plant) × visitor, member and empty: no PHP errors. Nimali's Dashboard and My Garden still say 18 plants in 3 collections; her plant's owner card (seen by a visitor) and her Explore people card say 13 plants, the public count. The Landing page's fresh listings are unchanged.
  - **Screenshots looked at:** 1280 px (Nimali as a visitor and as the owner, Kavindi's Plants, Listings and Ratings, the 404, the empty profile's Collections and Ratings), 768 px (Nimali as a visitor, the owner on Ratings) and a true 375 px (Nimali as a visitor and as the owner, the owner with the bottom bar, Kavindi's three tabs, the 404, the empty profile, and the open ⋯ menu).
  - **Measured:** 20 addresses × 4 widths (375, 768, 1024, 1280) = 80 page views with no sideways scroll. The ⋯ button is 44 × 44 px and every menu item 44 px. One `<h1>` on every profile, every image has `alt`, no duplicate ids. The page has no JavaScript, so there can be no console errors.
- **Review fixes, same day (commit `3ef2094`):** four independent reviews (notes, wireframe, code and UX) reported 16 points, several of them the same problem seen twice. Each was reproduced first. 15 were real and are fixed; one is about older shared navigation code and is left for the week close-out step. The most important: on a desktop, Goldie's **"Needs attention" badge was half covered by the likes**. Details under **Problems and fixes**.

**Why this way**
- **Public numbers for public eyes.** The stats, the tab counts and Explore's people cards are read by other people, so they count only public plants and collections. "18 plants" with only 13 visible would tell a stranger that 5 are hidden. Nimali's own pages still count everything. In stage B this is `AND is_public = 1` inside the `COUNT()` query.
- **The owner sees the public page.** p21 is the profile "shown as another member sees it". So the owner gets the same page plus a note, not a different page. The note's numbers are her full counts minus her public counts, so the two can never disagree.
- **Privacy is decided when the data is loaded.** Private collections are removed with `array_filter()` before any HTML is written. A private collection's id in the address (`&collection=3`) is simply not found, so the page shows all public plants and never says the collection exists.
- **A GROUP BY result, not 23 rows.** The bars only need "how many of each score". The query returns one row per score that someone gave, so the page starts every score at 0 and fills in the rows. A score nobody gave still gets its empty bar.
- **The average must match the bars.** p21's 20 / 2 / 1 averages 4.83, which prints 4.8, while p16, p17, p18 and p21 all print 4.9. One 4★ became a 5★: 21 / 1 / 1 = 112 / 23 = 4.87, printed 4.9.
- **Written reviews and ratings are not the same.** A rating can come without a comment. The Ratings tab lists the 5 written reviews and says "out of 23 ratings"; the tab count and the breakdown use all 23.
- **Tabs as links with `#tab-…`.** Each tab has its own address and works without JavaScript (week 5 adds the tab script). The `#tab-ratings` part makes the browser scroll down to the tabs and, on a phone, scroll the sideways tab row until the chosen tab is in view. The id sits on a `<span>` inside the link, because a link that is the target of `#…` gets keyboard focus and shows its focus ring.
- **One layout map per screen size.** `grid-template-areas` names the parts of the profile head (avatar, name, about, actions, stats). The phone and the tablet layouts are two different maps of the same HTML.
- **No he or she.** p21 says "See her listings"; the page is written for any member, so it says "See listings (2)".

```php
// Whose profile: the one in the address, or your own; nobody → 404
$asked_for = mb_strtolower(ltrim(trim($get_text('u')), '@'));
if ($asked_for === '' && $current_user) {
    $asked_for = $current_user['username'];   // nothing in the address: a member sees their own profile
}
// … look the name up in $mock_users …
if (!$member) {
    http_response_code(404);   // tells browsers and search engines that the page does not exist
    // … the "Member not found" empty state, the footer, exit
}
$is_owner = $current_user && $member['id'] === $current_user['id'];
```

```php
// Public collections only (stage B: WHERE user_id = ? AND is_public = 1)
$collections = array_values(array_filter(
    array_merge($mock_collections, $mock_community_collections),
    fn ($collection) => $collection['user_id'] === $member['id'] && $collection['is_public']
));

// The breakdown: every score starts at 0, the GROUP BY rows fill in the rest
$score_counts = [5 => 0, 4 => 0, 3 => 0, 2 => 0, 1 => 0];
foreach ($mock_rating_counts as $row) {
    if ($row['rated_id'] === $member['id']) {
        $score_counts[$row['score']] = $row['rating_count'];
    }
}
```

```php
<?php // One bar per score: CSS draws the width, the hidden text is what a screen reader reads ?>
<span class="rating-bar" aria-hidden="true"><span class="rating-bar-fill" style="width: <?= e(round($count / $rating_count * 100)) ?>%"></span></span>
<span class="visually-hidden"><?= e($score) ?> <?= $score === 1 ? 'star' : 'stars' ?>: <?= e(count_words($count, 'rating', 'ratings')) ?></span>
```

```css
.profile-head {                      /* phone: the avatar and the buttons on one row, the rest below */
  display: grid;
  grid-template-columns: auto minmax(0, 1fr);
  grid-template-areas:
    "avatar actions"
    "name   name"
    "about  about"
    "stats  stats";
}
@media (min-width: 768px) {          /* tablet and up: the name beside the avatar */
  .profile-head {
    grid-template-columns: auto minmax(0, 1fr) auto;
    grid-template-areas:
      "avatar name  actions"
      "avatar about about"
      ".      stats stats";
  }
}
```

The code that matters from the review fixes (commit `3ef2094`):

```css
/* components.css: the plant card's badges in one row that wraps, instead of two boxes placed in opposite corners */
.card-media-badges {
  position: absolute;
  top: var(--space-12);
  right: var(--space-12);
  left: var(--space-12);
  display: flex;
  flex-wrap: wrap;          /* not enough room for both? the second badge moves down a line */
  gap: var(--space-8);      /* and when they do fit, they are always at least 8 px apart */
}
.card-media-badges .badge { position: static; }       /* placed by the row now */
.card-media .plant-card-likes { margin-left: auto; }  /* pushed to the right end, also on a line of its own */
```

```php
<?php // user.php: the main action. The owner edits; others see what is on offer, listings first, then plants ?>
<?php if ($is_owner): ?>
  … Edit profile …
<?php elseif ($listing_count > 0): ?>
  … See listings (<?= e($listing_count) ?>) …
<?php elseif ($member['plant_count'] > 0): ?>
  <a class="btn btn-primary" href="<?= e($tab_url('plants')) ?>"><?= icon('leaf') ?> See plants (<?= e($member['plant_count']) ?>)</a>
<?php endif; ?>
```

**Problems and fixes**
- **The ⋯ menu broke "Community guidelines" over two lines** (61 px tall), found by measuring the open menu. The panel hangs from the 44 px button, so its width shrinks to its 220 px minimum. `white-space: nowrap` on the profile's menu items lets the panel grow to fit them (commit `c0181dd`). At 375 px it still ends inside the screen (from 128 to 359 px).
- **The first `?empty` screenshot showed "Member not found".** The screenshot browser was still a visitor from the previous `?guest` screenshot, and `?empty` only empties a logged-in Nimali; a visitor with no `?u` gets the 404. The page was right. `?member&empty` (both switches at once) gave the empty profile.
- **After the CSS fix the measurement still said 61 px.** The headless browser had kept the old `profile.css` in its cache. Deleting its scratch profile folder made it load the new file, and the items measured 44 px.
- **Review fixes (2026-10-20, commit `3ef2094`).** Four independent reviews of this step reported the problems below. Each was reproduced first, with a measurement in the browser, curl or a screenshot.
  - **The likes covered the health word.** On a desktop the Plants tab showed 3 cards across, but beside the rating card the tabs are only two-thirds of the page, so each card was about 200 px wide. The health badge (top left) and the likes (top right) were two boxes placed separately in opposite corners, and on such a narrow card they met: Goldie read "Needs attentio" with "♡ 3" on top (measured 21 px of overlap at 1024 px, 7 px at 1280 px). That breaks the rule "health always shown with its word". Two fixes: (1) the profile's plant cards stay **2 across** on a desktop too, like the listing cards (about 310 to 330 px wide); (2) the shared plant card now puts both badges in **one flex row that wraps** (`.card-media-badges`), so they are always at least 8 px apart, and on a card too narrow for both the likes move down a line instead of covering the badge. Tested by squeezing a card to 170 px: the likes dropped under "Needs attention". Explore, My Garden and the style guide look the same as before (screenshots at 375, 768, 1024 and 1280 px), and a click on a badge still opens the plant, while My Garden's ⋯ button still opens its menu.
  - **No main action for a member without open listings.** @aroid.amaya's profile had only the ⋯ menu, while p21 always shows a green button to visitors. Now: **See plants (N)** when there is nothing on the exchange, opening the Plants tab (see the code above). One primary button, a verb label.
  - **The stars were too faint to read.** An empty star was a light outline (`--color-line-strong`, about 1.5 : 1 on white) and a filled star is amber (about 2 : 1), both under the 3 : 1 that WCAG asks of shapes that carry meaning. A review's score was only visible as stars (the "Rated 4 out of 5" label is for screen readers only). Now each review prints the score in figures, **"4/5"**, through a new option of `partials/stars.php`, `'show_score' => true` (hidden from screen readers, which already hear the label). And the shared `.stars` draws empty stars in `--color-muted`, a clearly visible thin outline. Checked everywhere stars appear: the rating card (the big "4.9" already stands beside its stars), the reviews and the style guide.
  - **Heading levels skipped from h1 to h3** in the tab panels (the card names are `<h3>`). Each panel now opens with an `<h2>` naming its tab, hidden visually because the chosen tab already shows the name. An open collection keeps its visible `<h2>`, "Plants in Living room · 7 plants".
  - **The @username links of the reviews were 22 px and 19 px tall**, under the 44 px touch-target rule. Both are now `inline-flex` with `min-height: 44px`, like the other standalone links of this page.
  - **Collection cards did not line up.** The name and the count shared a line when there was room, so "Living room / 7 plants" wrapped while "Balcony 6 plants" did not, side by side (375 px; Kavindi's cards at 1024 px). A card is only 160 to 230 px wide and a name can be anything, so the count now always sits under the name.
  - **The "1★" star sat about 4 px left of the others** in the breakdown, because a "1" is narrower than a "5" in Poppins. The score column is now right-aligned (`justify-content: flex-end`): every star is at the same x position (measured).
  - **Two wording slips.** A member with exactly one rating and no comment would have read "all 1 rating was given without a comment"; that case now says "the only rating was given without a comment". A long unknown name, cut to 40 letters, printed "…." (the ellipsis and then a full stop); the full stop is now left out after a cut name.
  - **The `?empty` preview still showed brand-new Nimali as the author of reviews** on other members' profiles, dated months before. A code comment said this was on purpose, but the Open points did not. Since "?empty empties only her own rows", and the reviews she wrote are her rows, they now go too. The other members keep their counts and averages, so those ratings simply count as ratings without a comment and every number still adds up (Dinesh: "No written reviews yet: all 15 ratings were given without a comment.").
  - **One column, two meanings.** Nimali's collection rows said `plant_count` was a plain `COUNT()` on plants, while the other members' rows said it counts public plants, and the profile prints both. A public collection can hold a private plant (Add plant allows it), so the profile's count and collage must skip private plants. Only the comments changed: the profile's stage-B query adds `AND plants.is_public = 1` to the `COUNT()` and the `GROUP_CONCAT()`. Her 13 public plants are exactly the 7 in Living room and the 6 in Balcony, so the mock numbers already agree.
  - **Tidy-up.** `count_words()` ("1 plant", "13 plants") moved from the page into `includes/helpers.php`, so the next pages can use it. `review_date()` now builds on the shared `format_day()`, and the reviewer array is built in one place, `review_rater()`, instead of twice.
  - **This note had gaps** (found by the notes review): the Open points did not list every invented sentence of the empty states, the Plants tab's order, or the leaf tile of a small collage, and the Explore code bullet still said "to decide in DES-07" about public counts, which this step decided. All added to the Open points below, keeping the old text.
  - **Re-checked:** `php -l` on the six changed PHP files. curl as a visitor, as Nimali and as the empty Nimali on 26 addresses (the profile in every tab, Amaya, Kavindi, Dinesh, Ruwan, `?u=nobody`, `<script>`, `u[]=x`, `%FF`, a 300-letter name, and the other pages after the `helpers.php` change): no PHP errors, 404 where expected, still no email or phone. Measured 16 addresses × 4 widths = 64 page views: no badge covers another, one `<h1>`. Screenshots looked at: 1280 px (Nimali's Collections and Plants, Kavindi's Ratings), 1024 px (Nimali's Plants, the owner view, Kavindi's Collections), a true 375 px (Nimali's Plants and Ratings, Amaya's header, the collections, the 404), and the style guide, Explore and My Garden.
  - **Found while re-checking (older, not caused by these fixes):** My Garden scrolls **17 px sideways at 375 px**. The hidden word "Private:" inside the Succulents chip is positioned outside the chip row that scrolls sideways, so the page grows instead of the row. It measured the same with these fixes undone (`git stash`), so it belongs to week 3 and is listed in the Open points.
  - **Screenshot tool:** the measurements and true-width screenshots used the browser's DevTools protocol (a small script in the scratch folder) to emulate exact widths such as 375 px, instead of the iframe trick.

> [!tip] What to remember
> - Count for the viewer: numbers that other people see count only public things.
> - Remove private rows when the data is loaded, before any HTML, and treat a private id in the address like one that does not exist.
> - A `GROUP BY` returns no row for a value nobody has: start every bucket at 0.
> - Mock numbers must agree with each other: an average has to come from its distribution.
> - Unknown member → `http_response_code(404)` plus a friendly page with a way on.
> - Two badges on one photo: one flex row that wraps (`gap`, `margin-left: auto`), never two boxes placed in opposite corners that can meet on a narrow card.
> - A score must be readable as text, not only as faint stars or colours: print "4/5" beside them.
> - Every visitor deserves a main action: when the first choice is empty (no listings), offer the next best one.

**Check yourself**
1. Nimali has 18 plants. Why does her profile say 13, and where does the owner note's "5 private plants" come from?
2. The `GROUP BY` for Nimali returns three rows (5★, 4★ and 3★). How does the page still draw five bars?
3. What does `user.php?u=nimali.grows&tab=plants&collection=3` show a visitor, and why?

> [!success]- Answers
> 1. The profile is what other people see, so it counts only public plants, and her Succulents collection (5 plants) is private. The note subtracts the public count (`$member`, 13) from her full count (`$current_user`, 18).
> 2. `$score_counts` starts as `[5 => 0, 4 => 0, 3 => 0, 2 => 0, 1 => 0]`. The rows only overwrite the scores they have, so 2★ and 1★ keep 0 and get an empty bar.
> 3. All of her public plants, with no filter. Collection 3 is private, so it is not in `$collections` and the filter is ignored: the page neither shows its plants nor tells the visitor that it exists.

Lessons: [[01.04 Rendering States - Empty, Owner and Visitor Views]] · [[01.03 Query Strings and GET Parameters in PHP]] · [[01.05 Dropdown Menus with details and summary]] · [[04.05 Counts, Averages and Rating Rules in SQL]] · [[00.07 Mock Data Shaped Like Database Rows]] · [[00.03 PHP Includes and Partials]] · [[07.01 CSS Layout with Flexbox and Grid]] · [[02.04 HTTP Protocol]]

---
### Step 4.6 — Exchange Marketplace: listing actions, how it works, filters and listing cards
> [!important] Goal (COM-07, UI half)
> Build `exchange.php` (wireframe p17, phone layout p23): the page where members find plants to swap or take for free. The two listing actions, the four-step "how it works" strip, one filter form (search, All / Swap / Free, distance from your town, species) with a sort, and a grid of listing cards. Closed listings stay hidden, and contact details never appear on a card. Browsing is public.

**What was done (2026-10-20, branch `feature/pages-ui`, commits `341dbb6` and `a818ac2`)**
- `public/exchange.php` (new, commit `341dbb6`):
  - **Title and actions** (p17, element 1): "Plant exchange", the subtitle from p17, then **My exchanges** (secondary, `my-exchanges.php`) and **List a plant** (primary, `listing-form.php`). A visitor gets one button, **Log in to list a plant** (`login.php`).
  - **How it works** (element 2): an ordered list of the four steps, *Browse listings → Tap "I'm interested" → Get the owner's contact → Swap, then rate*. Two by two on a phone, one row from 768 px, with a › between the steps from 1024 px.
  - **One GET form** (element 3): a search box (title, "looking for", species and @username), an **All / Swap / Free** segmented control made of radio buttons, a **Near Kurunegala · 25 km** select (5, 10, 25, 50 km or Any distance), a **Species: Any** select (only the species on offer right now) and a **Search** button. Every value is read with `$get_text()` and checked against a whitelist, as on Explore; a wrong value falls back to the default. *(Review fix `c4bb634`: it is now the shared `get_text()` in `includes/helpers.php`.)*
  - A listing of type **`either`** ("Swap or free") shows under both Swap and Free.
  - **Results bar**: "7 open listings near Kurunegala" (just "8 open listings" with Any distance or for a visitor), one removable chip per active filter, **Clear all**, and a `<details>` sort menu: **Newest** (default), **Nearest**, **Most interest**. Ties fall back to the newest, so the order never jumps.
  - **Listing cards** (elements 4 to 7) through the shared `partials/listing-card.php`, unchanged: type badge, interest count, town and distance, "Looking for", owner and trader rating. 1 column on a phone, 2 on a tablet, 3 on a desktop.
  - **No results**: the empty-state partial. With a search: *No listings match "zzzz"* and **Show all listings** (plus **Clear the search** first, when filters are on too). With filters only: *No listings match these filters* and **Clear all filters**. *(Changed by the review fixes, commit `20a4fb0`: the buttons are now chosen by what really finds listings, see Problems and fixes.)*
  - **Visitors** see every open listing without distances, no Near filter and no Nearest sort, and a short line: *Log in to see how far away each plant is, measured from the town on your profile.*
- `public/assets/css/exchange.css` (new): the head, the steps strip, the filter form (on a phone the line of choices scrolls sideways on its own; from 768 px it is a grid that fills the line), and the card grid. *(Review fix `20a4fb0`: on a phone the type now has a line of its own, and only the two selects share the line that scrolls.)*
- `includes/mock-data.php`:
  - **`$mock_listings` now matches p17:** Tomato seedlings moved to **Wariyapola · 14 km · 1 week ago** (it said Kandy · 42 km · yesterday), and three new open rows: **Echeveria cuttings (5)** (@dinesh.greens, Pannala · 22 km, 2 interested), **Monstera cutting with a node** (@ruwan.roots, type `either`, nobody interested yet) and **Heartleaf philodendron cuttings (4)** (@kandy.jungle, Kandy · 42 km, the one beyond 25 km). Plus one **closed** row, listing 5 (Nimali's Marble Queen swap with Dinesh from the reviews), to prove closed listings stay hidden.
  - Every row gains **`species_id`, `common_name`, `scientific_name`** (for the Species filter and the search) and **`exchanged_with`** (a column from the table plan).
  - **A visitor's `distance_km` is null** on every page, because a visitor has no town to measure from.
  - `$mock_site_stats['open_listing_count']` is now **counted from the rows**, so the Landing page says "See all 8 listings" and the Exchange page then shows exactly 8.
- `public/index.php` and `public/styleguide.php`: show **open** listings only (the closed row must never be offered).
- `public/assets/css/components.css`: the results bar (count, filter chips, Clear all, sort) **moved here from `explore.css`**, because Explore and Exchange both use it. Explore looks the same.
- **Fix (commit `a818ac2`):** at 768 px the Species select dropped onto a second line, and then the distance text was cut off ("25 kr"). The filter line is now a grid at 768 px and up, and each segment is at least 44 × 44 px instead of having wide padding. The visitor's "Log in" link got a taller tap area. The Kandy listing is 6 days old instead of 1 day, so it is no longer the first Landing card right beside the pothos card that uses the same placeholder drawing.
- **Checked:**
  - `php -l` on every changed PHP file.
  - **curl as Nimali, as a visitor and as the empty Nimali** (`?member` then `?empty`), 28 addresses each: `type=free` (3 near Kurunegala), `type=swap` (5), `distance=5` (4), `distance=50` (8), `species=6` (1, the tomatoes), `q=pothos` (2), `q=@ruwan` (2), `q=zzzz`, `q=zzzz&type=swap`, `type=free&species=3`, `sort=nearest`, `sort=interest`, and bad values: `type=evil`, `distance=abc`, `distance=05`, `species=999`, `species=-1`, `species=1e2`, `q[]=x`, `type[]=a&distance[]=b&species[]=1&sort[]=x`, `sort=evil`, `q=%FF`, `<script>` and a 300-letter word. **No PHP warnings anywhere**; every bad value falls back to its default; the script tag prints as text; a visitor's `sort=nearest` falls back to Newest. No email, phone, `mailto:` or `tel:` on the page.
  - **Other pages after the mock-data change:** Landing, Dashboard, My Garden, Plant Profile (Monty and Hugo), Explore (also "Available for exchange"), the profiles of Nimali, Kavindi and Amaya, and the style guide, as visitor, member and empty: no PHP errors. Kavindi's profile now says "See listings (2)"; Amaya still has no listings, so her profile still shows the "See plants" case.
  - **Screenshots looked at:** 1280 px (member and visitor), 1024 px (Swap + 10 km with two chips), 768 px (member and visitor, before and after the fix) and a true 375 px (member and visitor, the filter line scrolled to its end, the no-results state). The Landing page's "Fresh on the exchange" at 1280 px and Kavindi's Listings tab.
  - **Measured** with the browser's DevTools protocol: 5 addresses × 4 widths (375, 768, 1024, 1280) × member and visitor = 40 page views, **no sideways scroll**. At 375 px the filter line scrolls inside itself (782 px of content in 375 px). One `<h1>`. Every form control and button is at least 44 px tall, and the visitor's "Log in" link 45 px. The removable filter chips are 36 px tall from 768 px up: that is the shared chip size (`components.css`), the same on Explore (see Open points).

**Why this way**
- **Distance comes from the member's town, not from GPS** (p17, UX notes). A visitor has no town, so there is nothing to measure from: the query returns `NULL` for `distance_km`, the card already leaves the distance out when it is null, and the Near filter and the Nearest sort are not offered. Inventing a town for visitors would show wrong distances.
- **25 km is the default for members, so it needs no chip.** p17 opens on "Near Kurunegala · 25 km". A filter the member did not choose should not look like one; a changed distance gets a chip, and removing it goes back to 25 km. That is also why `distance` only appears in the address when it differs from 25.
- **`either` belongs to both.** "Swap or free" means the owner accepts both, so a member looking for free plants must see it too. One `in_array($listing['type'], [$type, 'either'])` does it.
- **Species through the plant, or its own column.** A listing that offers one of the owner's plants already has a species through `plant_id → plants.species_id`. Seedlings and cuttings listed without a plant need their own `species_id`. In stage B one `COALESCE()` picks whichever exists.
- **One form, one button, no JavaScript yet.** Changing a select does nothing until **Search** is pressed; week 6 sends the form on every change and moves it into a bottom sheet on a phone (p23). Links for the type would apply at once but lose a typed search, so all choices stay in one form, like Explore's sidebar.
- **The line scrolls, the page does not.** On a phone the type control and the two selects are wider than the screen. Only that line has `overflow-x: auto`, and `position: relative` keeps the hidden radio buttons inside it. Without it they could stretch the page, the bug found on My Garden in step 4.5. *(Since the review fix `20a4fb0` the radio buttons are not in the scrolling line any more: on a phone the type control has its own line. The shared `.segmented` box is itself `position: relative`, so its hidden radios stay inside it, and the line no longer needs the rule.)*
- **Shared CSS goes to `components.css`.** The results bar was in `explore.css`. Copying it into `exchange.css` would mean two copies that drift apart, so it moved.

```php
// exchange.php: the distance choice. A visitor has no town, so only 'any' is possible for them.
$viewer_town = $current_user['location'] ?? '';
$has_town    = $viewer_town !== '';

$distance_choices = ['5', '10', '25', '50', 'any'];
$default_distance = $has_town ? '25' : 'any';   // p17 opens on "Near Kurunegala · 25 km"
$distance = $get_text('distance');
if (!$has_town || !in_array($distance, $distance_choices, true)) {
    $distance = $default_distance;
}
```

```php
// One filter per condition; a listing of type 'either' passes both Swap and Free
if ($type !== '' && !in_array($listing['type'], [$type, 'either'], true)) {
    return false;
}
if ($distance !== 'any' && $listing['distance_km'] !== null && $listing['distance_km'] > (int) $distance) {
    return false;
}
```

```php
// mock-data.php: a visitor has no town, so stage B's query returns NULL for distance_km
if (!$current_user) {
    foreach ($mock_listings as $index => $listing) {
        $mock_listings[$index]['distance_km'] = null;
    }
}
```

```css
/* Phone: the line of choices scrolls sideways on its own; position: relative keeps the hidden radios inside it */
.exchange-filter-row {
  position: relative;
  display: flex;
  overflow-x: auto;
}
@media (min-width: 768px) {          /* tablet and up: a grid that shares the room between the selects */
  .exchange-filter-row {
    display: grid;
    grid-auto-flow: column;
    grid-template-columns: auto;              /* the segmented control: as wide as it needs */
    grid-auto-columns: minmax(0, 1fr);        /* each select: an equal share of the rest */
    overflow: visible;
  }
}
```

The code that matters from the review fixes (commits `c4bb634` and `20a4fb0`):

```php
// helpers.php: one piece of valid text from the address, shared by Explore, the Public Profile and Exchange
function get_text($name)
{
    $value = $_GET[$name] ?? null;

    return (is_string($value) && mb_check_encoding($value, 'UTF-8')) ? $value : '';
}
```

```php
// exchange.php, no results: count first, then offer only a button that finds listings
$further_count = ($has_town && $distance !== 'any')
    ? count(filter_listings($open_listings, $term, $type, 'any', $species))   // the same search, any distance
    : 0;
$without_search_count = ($q !== '' && $active_filters)
    ? count(filter_listings($open_listings, '', $type, $distance, $species))  // the same filters, no search
    : 0;

if ($further_count > 0) {
    $actions[] = ['label' => 'Search any distance', 'url' => $exchange_url(['distance' => 'any']), 'icon' => 'map-pin'];
} elseif ($without_search_count > 0) {
    $actions[] = ['label' => 'Clear the search', 'url' => $exchange_url(['q' => '']), 'icon' => 'search'];
}
// then "Show all listings": no search, no filters, any distance
```

```css
/* Phone: the type on its own line; only the two selects scroll, so Species peeks in at the edge */
.exchange-choices { display: flex; flex-direction: column; }
@media (min-width: 768px) {
  .exchange-choices { flex-direction: row; }     /* the type and the selects on one line again */
}
@media (min-width: 1024px) {
  .exchange-search  { flex: 100 1 280px; }       /* side by side: the search takes almost all spare room */
  .exchange-choices { flex: 1 1 auto; }          /* wrapped onto their own line: they fill it */
}
```

**Problems and fixes**
- **Bash and apostrophes.** The first try wrote the new mock rows with a heredoc in Bash, and the apostrophes in titles like *Pothos 'Golden'* broke it ("unexpected EOF"). Nothing was changed. The rows went into a file in the scratch folder, and a small Python script put them into `mock-data.php`.
- **A Python script stopped half way.** It moved the results-bar CSS and then crashed while *printing* the moved text (the Windows console cannot show the "─" line characters). Both files had already been written correctly; `git diff` confirmed it before going on.
- **The first `?empty` test looked like a visitor** (8 listings, no distances). The test browser was still a visitor from the `?guest` run before, and `?empty` only empties a logged-in Nimali (the same surprise as in step 4.5). `?member` first, then `?empty`, gave the right result: 5 listings near Kurunegala, none of them hers.
- **Tablet layout, twice.** At 768 px the three choices did not fit on one line (723 px of controls in 720 px), so Species dropped onto its own line. A grid that shares the room fixed that, but then "Near Kurunegala · 25 km" was cut off. Narrower segments (at least 44 × 44 px, no extra padding) gave the selects enough room (commit `a818ac2`).
- **Two identical drawings side by side.** The philodendron listing uses the same placeholder drawing as Hugo, its plant, which is the pothos drawing. As the newest listing it sat first on the Landing page, right beside the pothos card. Making it 6 days old moved it away from there; the real fix is real photos.
- **The "Log in" link was 19 px tall.** `inline-flex` with `min-height: 44px` made it taller but pushed the two lines of the note apart. Vertical padding on an inline link makes the tap area bigger without moving the lines.
- **Review fixes (2026-10-20, commits `c4bb634` and `20a4fb0`).** Four independent reviews of this step reported the problems below. Each was reproduced first, with curl or a measurement in the browser.
  - **Buttons that led to another empty page** (two reviews). As Nimali, `?q=hoya` said *No listings match "hoya"* and offered only **Show all listings**, which went to `exchange.php`: still the hidden 25 km limit, 7 listings, and not the one listing that matches (Kavindi's philodendron in Kandy, 42 km, *Looking for: any hoya or a small fern*). `?species=12` (the philodendron, offered in the Species menu) gave 0, and **Clear all filters** went back to 25 km, which hides it again. And `?type=free&species=2&q=a` offered **Clear the search**, which led to `?type=free&species=2`: 0 again. The cause: the default 25 km is a real filter, only without a chip, and the buttons were chosen by rules ("is there a search?") instead of by results. Fix (commit `20a4fb0`): the filter became a function, `filter_listings()`, so the empty state can run it again with one choice loosened and count the result (see the code above). It offers **Search any distance** when that finds something, and the text names the distance (*Nothing within 25 km of Kurunegala, but there is 1 listing further away.*); otherwise **Clear the search** when the filters alone find something; and then **Show all listings**, which now really shows all of them (no search, no filters, any distance, the same sort). Followed every button of 14 empty addresses as Nimali, as a visitor and as the empty Nimali: 48 buttons, none leads to 0 listings.
  - **The Species select was hidden on a phone.** At a true 375 px the *Near Kurunegala · 25 km* select is 290 px wide (a select is as wide as its longest option) and pushed Species completely off the screen (it started at 486 px), with no scrollbar to show that more exists. First idea: shorter option words. Measured in the browser by changing the options, they do not help enough: the width comes from the longest option, *Any distance*, plus the native arrow, so even "25 km" labels left only 5 px of Species visible. Fix: a wrapper, `.exchange-choices`, gives the All / Swap / Free control its own full-width line on a phone, and only the two selects share the scrolling line. Species now starts at 314 px, so 61 px of it (the leaf and "Sp") peeks in at the edge, like p23's "Spe…". A visitor's single Species select fills its line. From 768 px the wrapper puts everything on one line again, so tablet and desktop look as before.
  - **An empty gap at 1024 px.** Between 1024 and about 1050 px a member's choices (712 px) do not fit beside the search (at least 280 px), so they wrapped onto a second line and stopped at 736 px, leaving about 265 px empty on the right. Fix: the choices may grow (`flex: 1 1 auto`), so on a line of their own they fill it (two 398 px selects at 1024 px). The search got `flex-grow: 100`, so when both share one line it still takes almost all of the spare room, as before: at 1280 px the search is 316 px and each select 266 px, exactly as before, and a visitor's search at 1024 px is still about 533 px.
  - **`$get_text()` was copied into three pages** (Explore, the Public Profile and Exchange). It is now one function, `get_text()`, in `includes/helpers.php` (commit `c4bb634`), as the Explore code bullet in the Open points suggested. 29 addresses (including `q[]=x`, `tab[]=x`, `u[]=x`, `%FF` and `<script>`) × Nimali, visitor and empty Nimali gave byte-identical pages before and after the change (compared by running them again with the change put aside by `git stash`), with no PHP warnings. `garden.php`, `plant.php` and `settings.php` still read `$_GET` directly (see the Open points).
  - **Gaps in the notes** (the notes review). The Week 02 Open points still listed "37 open listings" as invented and still said "When `exchange.php` exists, decide…"; both got a short *(Step 4.6: …)* pointer, keeping the old text. The Exchange (P10) bullets below now also say what the Landing page's "Fresh on the exchange" shows since this step, and point to the visitor-link decision.
  - **Re-checked:** `php -l` on the four changed PHP files. curl: 29 Exchange addresses × Nimali, visitor and empty Nimali (87 pages, the bad values too): all 200, no PHP warnings, no email or phone, the script tag printed as text, and the counts unchanged (Nimali 7, visitor 8, empty Nimali 5; Swap 5, Free 3, 5 km 4, 50 km 8). Landing still says "See all 8 listings"; Explore and the Public Profile give the same pages as before; Dashboard, My Garden, the Plant Profile, Settings and the style guide have no PHP errors. Measured 6 addresses × 4 widths (375, 768, 1024, 1280): no sideways scroll (a 300-letter search word too), one `<h1>`, every input labelled. Screenshots looked at: a true 375 px (Nimali's filters with Species peeking in, the visitor's filters, the *hoya* empty state and its Search any distance result), 1024 px (Nimali's page top with the two filter lines, the visitor's single line) and 1280 px (Nimali's page top, the philodendron empty state).

> [!tip] What to remember
> - Filters in the address: read with `$get_text()`, check against a whitelist, fall back to a default, and leave the default out of the address.
> - A default choice is not an active filter: no chip for it.
> - When a value cannot exist for someone (a visitor's distance), make it `null` where the data is loaded and hide what depends on it; never invent it.
> - One row of controls may scroll sideways on a phone, never the page; `position: relative` keeps hidden inputs inside the scrolling box.
> - CSS used by two pages belongs in `components.css`.
> - A button in an empty state must lead to results: count first, then offer the smallest change that finds something. A hidden default (the 25 km) is a filter too, so the way out must be able to remove it.
> - A sideways-scrolling row needs the next control to peek in at the edge, or nobody knows there is more.
> - Code copied into a third page belongs in `includes/helpers.php` (now `get_text()`).

**Check yourself**
1. Why does a member see "7 open listings near Kurunegala" while a visitor sees "8 open listings"?
2. What happens with `exchange.php?distance=abc`, and with `?distance=5` for a visitor?
3. Where does the species of "Tomato seedlings (6)" come from, and how will stage B find it?

> [!success]- Answers
> 1. A member starts with the 25 km limit from p17, which hides Kavindi's Kandy listing (42 km). A visitor has no town, so there is no distance limit and all 8 open listings show. The closed listing is hidden for both.
> 2. `abc` is not in `$distance_choices`, so the member gets the default, 25 km, with no chip and no error. A visitor has no town, so any distance falls back to Any: all listings, no Near filter.
> 3. It is listed without a plant (`plant_id` null), so it has its own `species_id` (6, Tomato), a proposed `exchange_listings` column. Stage B: `COALESCE(plants.species_id, exchange_listings.species_id)`, then a `LEFT JOIN plant_species`.

Lessons: [[01.03 Query Strings and GET Parameters in PHP]] · [[04.04 Search, Filters and Pagination]] · [[01.06 Sideways Scrolling Rows and Scroll Snap]] · [[01.04 Rendering States - Empty, Owner and Visitor Views]] · [[00.07 Mock Data Shaped Like Database Rows]] · [[00.03 PHP Includes and Partials]] · [[07.01 CSS Layout with Flexbox and Grid]]

---
### Step 4.7 — Listing Detail: gallery, summary, interest card (states A and B), owner card and tips
> [!important] Goal (COM-08, UI half)
> Build `listing.php?id=` (wireframe p18): everything a member needs to decide on a swap, then the owner's contact details once they press **I'm interested**. Public page, but showing interest needs an account. **State A** is the page before interest, **state B** after it, with only the contact details the owner chose to share. The owner sees their own listing differently, a closed listing takes no interest, and a wrong id gets a friendly 404.

**What was done (2026-10-20, branch `feature/pages-ui`, commits `f3e16bf`, `e8752df` and `46d7d10`)**
- `public/listing.php` (new, commit `f3e16bf`):
  - **Which listing:** `?id=` is read with `get_text()` and must be digits only (`ctype_digit()`), so `id[]=1`, `abc`, `-1`, `1e2` and a missing id all answer HTTP **404** with *Listing not found* and a **Browse the exchange** button, like the Plant Profile's 404.
  - **Photo gallery** (p18, element 1): the large 4:3 photo, then a thumbnail per photo. Without JavaScript a thumbnail is a link to the same page with `?photo=2#gallery`, which shows that photo large; the chosen thumbnail has a leaf-green border and `aria-current="true"`. Any other value (`photo=99`, `photo=abc`, `photo[]=1`) shows photo 1. Alt text: "Photo 2 of 3: Pothos 'Golden', 3 rooted cuttings"; a thumbnail's alt says what the link does, "Show photo 2 of 3".
  - **Summary card** (elements 2 and 4): the type badge (Swap, Free or Swap or free), a **Closed** badge when closed, "Posted 18 Oct · 4 interested", the title in Lora as the page's `<h1>`, "Kurunegala · about 3 km from you" (the distance only for members, and not for the owner) *(review correction: that example is listing 7, Nimali's own, so it never shows; Ruwan's listing 8 shows "Kurunegala · about 5 km from you")*, and **Looking for** in a sage box.
  - **About this plant** (element 3): the description, then a details list: **Species** (common name and italic scientific name), **Size**, **Condition**, and **Comes from** "Goldie · See the story", a link to `plant.php?id=43`. The line only appears when the source plant is public or the viewer owns it.
  - **Interest card** (element 6), one card with five situations: **state A** (a labelled note, max 300 characters, the primary **I'm interested**, and "Your contact is not shared. You will see Ruwan's email and phone number."); **state B** ("Interest sent to Ruwan · 20 Oct", "Contact Ruwan to arrange the pick-up", the shared details as `mailto:` and `tel:` links, the member's own note, a disabled **Interest sent** and **Withdraw interest**); **the owner** ("This is your listing", "4 people are interested.", which of their details interested members see, **Manage in My exchanges** and **Edit listing**); **closed** ("This listing is closed", no form); **a visitor** (**Log in to show interest**, which comes back to the listing after Log in).
  - **The buttons work without saving anything:** both forms POST to the page, the page calls a Mock-only helper that remembers the change in the session, then redirects back to the listing (Post/Redirect/Get).
  - **Owner trust card** (element 5): avatar, name, "@ruwan.roots · Kurunegala", "★ 4.6 (9) · 5 swaps · since May 2025" and **View profile**. **Safe swapping** (element 7): the three rules as a short list, and a link to the community guidelines.
- `public/assets/css/listing.css` (new): one column on a phone, cards side by side in pairs on a tablet, and two columns on a desktop (commit `e8752df` changed the desktop grid, see Problems and fixes).
- `includes/mock-data.php`:
  - **Every listing row** got the detail columns: `description`, `share_email`, `share_phone` (from the table plan) and two **proposed DES-07 columns**, `size` and `plant_condition`. Listing 7's are from p18, the rest are invented.
  - **`$mock_listing_photos`** (new): rows of a **proposed `listing_photos` table** (id, listing_id, path, created_at), like `plant_photos`. Listing 7 has 3 photos as on p18, listing 8 has 2, the others only their card photo.
  - **`$mock_user_contacts`** (new): the `email` and `phone` columns of `users`, kept apart from `$mock_users`, which other people see.
  - **`$mock_listing_interests`** (new): Nimali's own interest rows (id, listing_id, user_id, message, status, created_at). One is in the mock data: she asked Dinesh for his echeveria cuttings (listing 11), so one of its "2 interested" is her.
  - **`mock_send_interest()`** and **`mock_withdraw_interest()`** (new, Mock-only) keep her changes in `$_SESSION['preview_interests']`. At the end of the file they are laid over the mock rows, and each listing's `interest_count` follows (one more, or one less), so the Exchange card and the detail page always show the same number. `?member` puts the mock data back; `?empty` and Create account start with no interests.
  - Nimali's **phone** is now `+94 77 000 1234` (p18 and p19); it was `null`.
- `includes/helpers.php`: **`post_text()`** (the same as `get_text()` for a POST field), **`return_path()`** (the only addresses Log in may return to) and **`first_name()`** ("Ruwan Rathnayake" gives "Ruwan").
- `public/login.php`: reads **`?next=`** through `return_path()`, carries it in a hidden field, and after Log in opens the listing instead of the Dashboard. The intro then says "Log in to show your interest. You will come straight back to the listing."
- Icons **mail**, **phone** and **shield** (Lucide 1.52.0, downloaded like the others) and `docs/credits.md`.
- Commit `46d7d10`: a code comment said the "Interest withdrawn" message shows "once"; it shows on the page the redirect returns to, and again on a reload of that address. Comment only.
- **Checked:**
  - `php -l` on the four changed PHP files.
  - **Bad input, curl** as Nimali, as a visitor and as the empty Nimali (`?member` then `?empty` in one cookie jar), 34 addresses each (102 pages): every listing id, `999`, `abc`, no id, `id=`, `id[]=1`, `id[a][b]=1`, `-1`, `1e2`, `7.0`, a 23-digit id, `%FF`, `<script>`, `photo=99`, `photo=0`, `photo=abc`, `photo[]=1`, `photo=-1`, `photo=2`, `photo=3`, `withdrawn=1`, `withdrawn[]=1`. **No PHP warnings anywhere**; 404 exactly where expected (for the empty Nimali also her own listings 5, 6 and 7, because a brand-new account has none).
  - **The POST flow, curl:** listing 8, state A (2 interested) → POST *I'm interested* with a note holding `<script>` and a line break → **302** to `listing.php?id=8#interest` → state B with `mailto:ruwan.r@example.com` and `tel:+94700005678`, **3 interested**, the note printed as text → **reload: still state B, still 3**, and the Exchange card also says 3. A second POST (a resent form) changes nothing. **Withdraw** → 302 to `…&withdrawn=1#interest` → state A, "Interest withdrawn. Ruwan no longer sees your request.", 2 interested, also on the card; a reload stays there. Withdraw again: nothing. Sending again with a note of spaces only: state B without "Your note". `action[]=x`, `action=evil` and `note[]=x` cause no error; a 400-letter note is kept as 300. The mock interest on 11: state B with Dinesh's email only, then withdrawn → 1 interested on the page and the card. A POST by the owner (7) or on the closed listing (5) changes nothing; a POST to `id=999` gets the 404; a visitor's POST is sent to `login.php?next=listing.php%3Fid%3D8`. The Dashboard still says "4 people are interested" in listing 7. `?member` puts everything back. The empty Nimali starts without the interest on 11 (1 interested), and an interest she sends stays after a reload.
  - **Log in with `?next=`:** `listing.php?id=8` returns there. Refused (Dashboard instead): `https://evil.example`, `//evil.example`, `/listing.php?id=8`, `listing.php?id=8&x=1`, `listing.php?id=0`, `listing.php?id=08`, `listing.php?id=8#x`, `javascript:alert(1)`, an 11-digit id, `next[]=…`, and (PHP command line) the same address with a hidden line break at the end. A member who opens `login.php?next=listing.php%3Fid%3D8` goes straight to the listing. Plain Log in still opens the Dashboard.
  - **Privacy, curl:** no `@example.com`, `mailto:`, `tel:` or `+94` in state A (listings 8, 9, 10, 12, 13), for a visitor (all 9 listings), for the empty Nimali, or on Exchange, the Landing page, five profiles (also their Listings tabs), Explore, two plant pages and the Dashboard, before and after she showed interest in five listings. In state B only the shared fields appear: listings 9, 10 and 11 email only, 12 phone only, 8 and 13 both. A private source plant (tested by making Lily private for a moment, then `git checkout` of the file): a visitor gets no "Comes from" line, the owner still gets the link.
  - **Screenshots looked at:** 1280 px (state A, state B after a real POST, the owner on 7, closed as the owner and as a visitor, the visitor, the 404 for `999` and for `abc`), 1024 px (state B), 768 px (state B top and bottom, the owner, closed as a visitor) and a true 375 px through the DevTools protocol (A, B, the owner, closed as the owner, the visitor, the 404; a second set with the bottom bar hidden, because in a full-page screenshot the fixed bar is drawn over the middle of the page).
  - **Measured** (DevTools protocol): 13 states × 4 widths (375, 768, 1024, 1280) = 52 page views, **no sideways scroll**, one `<h1>` on every listing page, every image has `alt`, no duplicate ids, every input labelled. Small targets left: only the shared breadcrumb link "Exchange" (19 px tall, see Open points). The visitor's "Create a free account" link measured 43 px in that run; after the fix the visitor states were measured again at the four widths and it no longer shows up.

**Why this way**
- **Post/Redirect/Get.** After the POST the page answers with a redirect, and the browser loads the listing again with GET. A reload then repeats that GET, never the POST, so the browser never asks "resend the form?" and an interest is never sent twice. The page itself also refuses a second interest and a withdraw without an interest, because a hand-made request can skip the form.
- **The mock data plays the database.** In stage B the button will INSERT a `listing_interests` row and every page will count it with `COUNT(*)`. To make the preview behave the same, the helpers store the change in the session and `mock-data.php` lays it over the rows and fixes `interest_count` for every page. listing.php never adds numbers itself.
- **Privacy is decided when the data is loaded.** The contact details live in their own array, which only listing.php reads, and only into `$contact` when the viewer has a live interest (and into `$own_contact` when the owner opens their own open listing: their own details, so nothing is revealed). In stage B the query itself joins `listing_interests`: without an interest it returns no row at all, so a bug in the HTML could not show what was never loaded. A private source plant is not loaded for other people either.
- **The owner decides what is shared.** `share_email` and `share_phone` are the owner's ticks on the listing form (p19). `shared_contact()` turns a field the owner did not tick into `null`, and the template only prints what is not null.
- **No open redirect.** `?next=` is typed by whoever makes the link, so `login.php?next=https://evil.example` must not send a member to another site after they log in. Instead of trying to spot bad addresses, `return_path()` accepts one exact pattern. It ends with `\z`, not `$`: in PHP `$` also accepts a line break at the very end (checked on the command line).
- **One desktop grid with spanning items.** Two rows of two columns (the Plant Profile's way) left an empty band under the photos whenever the interest card was taller. Letting the right-hand cards span two rows lets each column stack without waiting for the other (see Problems and fixes).
- **Thumbnails are links for now.** Week 5's script can swap the photo without a reload; until then a link with `?photo=` works everywhere and has its own address.

```php
// listing.php: the two buttons (Post/Redirect/Get). Stage B checks the CSRF token first.
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $back = 'listing.php?id=' . $listing['id'];
    if (!$current_user) {
        header('Location: ' . query_url('login.php', ['next' => $back]));   // log in first, then come back
        exit;
    }
    $action = post_text('action');
    if ($is_open && !$is_owner) {
        if ($action === 'interest' && !$my_interest) {
            $note = mb_substr(trim(post_text('note')), 0, 300);
            mock_send_interest($listing['id'], $current_user['id'], $note === '' ? null : $note);
        } elseif ($action === 'withdraw' && $my_interest) {
            mock_withdraw_interest($my_interest);
            $back .= '&withdrawn=1';
        }
    }
    header('Location: ' . url($back) . '#interest');   // the GET that a reload repeats
    exit;
}
```

```php
// Only in state B, and only the fields the owner ticked
function shared_contact($listing, $contact)
{
    return [
        'email' => $listing['share_email'] ? $contact['email'] : null,
        'phone' => $listing['share_phone'] ? $contact['phone'] : null,
    ];
}
$contact = ($my_interest && $is_open) ? shared_contact($listing, $mock_user_contacts[$listing['user_id']]) : null;

// Stage B: without a live interest this query returns no row at all
// SELECT IF(l.share_email, u.email, NULL) AS email, IF(l.share_phone, u.phone, NULL) AS phone
// FROM exchange_listings l JOIN users u ON u.id = l.user_id
// JOIN listing_interests i ON i.listing_id = l.id AND i.user_id = ? AND i.status <> 'withdrawn'
// WHERE l.id = ? AND l.status = 'open'
```

```php
// helpers.php: the only addresses Log in may return to
function return_path($value)
{
    return (is_string($value) && preg_match('/^listing\.php\?id=[1-9][0-9]{0,9}\z/', $value)) ? $value : '';
}

// mock-data.php: the counts follow the preview, as COUNT() would
foreach ($mock_listings as $index => $listing) {
    $mock_listings[$index]['interest_count'] += (int) in_array($listing['id'], $active_interest_ids, true)
                                              - (int) in_array($listing['id'], $seeded_interest_ids, true);
}
```

```css
/* Desktop: four parts in a grid of three rows. Row 1 is as tall as the photos, so About starts right under them;
   row 2 only grows when the summary and interest card are taller; row 3 (1fr) takes a long About. */
@media (min-width: 1024px) {
  .listing-layout {
    grid-template-columns: minmax(0, 7fr) minmax(0, 5fr);
    grid-template-rows: auto auto 1fr;
    gap: 0 var(--space-32);              /* the gaps are margins, so an empty row 2 adds none */
  }
  .listing-gallery     { grid-column: 1; grid-row: 1; }
  .listing-side-top    { grid-column: 2; grid-row: 1 / span 2; }   /* summary + interest card */
  .listing-about       { grid-column: 1; grid-row: 2 / span 2; }
  .listing-side-bottom { grid-column: 2; grid-row: 3; }            /* owner + tips */
}
```

```php
// Review fixes (commit 3dd2532). listing.php: a line break counts once, as it does in the textarea
$note = mb_substr(trim(str_replace("\r\n", "\n", post_text('note'))), 0, 300);
header('Location: ' . url($back) . '#summary');   // the title, the new count and the card (was #interest)

// login.php: the fragment is added only after return_path() has checked the plain address
$after_login = $next !== '' ? $next . '#summary' : 'dashboard.php';
```

```css
/* Review fixes (commit 5d33ed3) */
.listing-title    { font-size: var(--text-h2); text-wrap: balance; }   /* no lone last word */
@media (min-width: 1024px) {
  .listing-title  { font-size: var(--text-h1); }                       /* 32 px only in the desktop column */
}
.listing-withdraw { padding-inline: var(--space-8); }                  /* both buttons on one line */
```

**Problems and fixes**
- **An empty band on the desktop.** The first layout (commit `f3e16bf`) used two rows of two columns, like the Plant Profile. In state B the interest card is taller than the photos, so About had to wait for it: about 140 px of empty space under the thumbnails. The fix (commit `e8752df`) puts the four parts into one grid of three rows, with the right-hand cards spanning rows. Measured afterwards: About starts 32 px under the photos and the owner card 16 px under the interest card in states A and B and on the owner view. Only when the interest card is shorter than the photos (a visitor) is there some space on the right, about 68 px, which is accepted. Phone and tablet did not change.
- **A hand-made `?withdrawn=1`** showed "Interest withdrawn" on a listing the member had never touched. The message now needs the member's own row with status `withdrawn`.
- **The visitor's "Create a free account" link** sat lower than its sentence (it was inside a flex row meant for the lock icon) and was 43 px tall. It got its own line style and is now a 44 px `inline-flex` link.
- **"Aloe vera (Aloe vera)".** For a plant whose common and scientific names are the same, the scientific name is now left out.
- **Screenshot tool:** after the POST the browser was scrolled down to `#interest`, so the full-page screenshot showed the sticky header in the middle of the page. The script now scrolls to the top first. A JavaScript snippet with `||` broke the script's `|`-separated arguments. Windows Python cannot open `/c/…` paths, so `cygpath -w` gave it a Windows path. The 52-view measurement took longer than two minutes and was run in the background.
- **Review fixes (2026-10-21, commits `3dd2532` and `5d33ed3`).** Four independent reviews of this step reported the problems below. Each was reproduced first, in the browser or with curl.
  - **A note with line breaks lost its last letters** (two reviews). The textarea's `maxlength="300"` counts a line break as one character, but the browser sends it as two (`\r\n`). Typed in Edge: four lines of 74 letters, each followed by a line break, is exactly 300 in the textarea; the server received 304 characters, `trim()` took off the last line break (302), and `mb_substr(…, 0, 300)` cut the last two letters: 297 were shown. Fix (`3dd2532`): every `\r\n` becomes `\n` before `trim()` and the cut, as in the code above. The same test now shows 299 characters (the line break at the very end is trimmed) and nothing is lost; curl with a 299-character note with `\r\n` line breaks keeps every letter too.
  - **After Log in, the I'm interested button was off the screen on a phone.** **Log in to show interest** came back to `listing.php?id=8` with no fragment, so at 375 px the page opened at the top: the note box at 734 px, the button at 900–944 px, and the bottom bar from 748 px. Fix (`3dd2532`): `login.php` adds `#summary` to `$after_login`, but only after `return_path()` has checked the plain address, so the check itself is unchanged and the hidden field still carries `listing.php?id=8`. Re-tested the open-redirect guard by GET (as a logged-in member) and by POST (as a visitor), 16 values each (15 attacks and the one allowed address): `//evil.example`, `https://evil.example`, `http:evil.example`, `/\evil.example`, `\\evil.example`, `javascript:alert(1)`, the listing address with a line break at the end, with `\r\nLocation: https://evil.example`, with `#x`, with `#summary` (so the fragment cannot be doubled), with `@evil.example`, with `&x=1`, with a space in front, `id=08`, an 11-digit id; plus `next[]=` and `next=%0A`. All go to the Dashboard; only `listing.php?id=8` returns to `listing.php?id=8#summary`.
  - **On a desktop the jump hid the title and the count.** After **I'm interested** or **Withdraw**, `#interest` scrolled 309 px at 1280 × 900, although the card already fitted on the screen. It hid the title, the photo and the "Posted … · 3 interested" line, which is the number that had just changed. Two ways were weighed: a very large `scroll-margin-top` on the card from 1024 px (the browser cannot scroll above the top, so it stays there), or aiming the jump at the summary card. The first is a trick with a magic value, and on a short window it could leave the card out of view, so the second was chosen (`3dd2532`): the summary card has `id="summary"`, all three returns (I'm interested, Withdraw, Log in) go to `#summary`, and the interest card lost its unused `id="interest"`. Measured in Edge: at 1280 × 900 the page moves 54 px (only the breadcrumb goes under the top bar, the same as a thumbnail's `#gallery`), the title, the count and the whole card (335–693 px) are in view. At 768 × 1024 the summary and the card sit side by side at the top. At 375 × 812 the title, the count and the whole card (301–664 px, bar at 748) are in view, and after Log in the button is at 509–553 px. At 375 × 667 the button after Log in is at 509–553 px (bar at 603), but in state B the Interest sent / Withdraw row is under the bottom bar: the message and the contact details are in view, and a short scroll shows the row (see the Open points).
  - **"Withdraw interest" dropped onto its own line** at 375 and 768 px. The two buttons needed 147 + 8 + 161 = 316 px in a row of 302 to 309 px, so Withdraw wrapped, and its button padding put its text 17 px in from the card's edge. p18 shows them on one line. Fix (`5d33ed3`): the disabled **Interest sent** lost its tick (p18 has none, and the green message above already has one; 24 px saved) and **Withdraw interest** has 8 px side padding instead of 16 px (16 px saved): 123 + 8 + 145 = 276 px. Measured: one line at 344, 360, 375, 768, 1024 and 1280 px; at 340 px Withdraw still wraps.
  - **Lone words in the title on a tablet.** From 768 px the title jumped to 32 px inside the half-width summary card (302 px), so *Tomato seedlings (6)*, *Echeveria cuttings (5)* and *Snake plant 'Laurentii'* ended with "(6)", "(5)" and "'Laurentii'" alone on the second line. Other widths had it too ("Monstera cutting with a / node" at 375 and 1280 px). Fix (`5d33ed3`): the title keeps the H2 size (24 px) until 1024 px, and `text-wrap: balance` makes the browser even out the lines (a browser that does not know it simply wraps as before). Measured all seven titles at 340 to 1280 px: no line ends with a lone short word ("Monstera cutting / with a node", "Pothos 'Golden', / 3 rooted cuttings"). At 1024 px *Heartleaf philodendron cuttings (4)* takes three lines, "Heartleaf / philodendron / cuttings (4)", because the two long words do not fit side by side in 343 px at 32 px.
  - **A stale comment in `login.php`.** Its stage-B plan said "then go to the Dashboard"; it now says `$after_login` (the checked listing, or the Dashboard) (`3dd2532`).
  - **Notes corrections** (the notes review): the "Checks for every page" item and "Why this way" now say that the owner sees their own shared details (`$own_contact`); the summary card example "about 3 km" is marked as never shown; the Lessons line now links [[03.07 Access Control and Ownership Checks]] (its section C is this exact pattern) and [[03.05 CSRF Tokens]]; the P11 Open points now record that p18 says "Posted 28 Sep"; and the Week 02 and Week 03 Open points got short *(Step 4.7: …)* pointers (`?next=` on Log in, Nimali's phone and Settings).
  - **Problems while checking.** The first measurement after the CSS change still showed 32 px titles: Edge had kept the old `listing.css` in its cache. The test script now turns the browser cache off (the DevTools protocol's `Network.setCacheDisabled`); anyone checking by hand needs Ctrl + F5 after a CSS change. The next run printed the titles of the wrong listings, because the script measured the old page before the new one had loaded; it now waits until the new page has replaced the old one. One run hung because the previous headless Edge was still closing; the script now waits for it to go first.
  - **Re-checked:** `php -l` on `listing.php` and `login.php`. curl: 34 listing addresses × Nimali, visitor and empty Nimali (102 pages): no PHP warnings, and 404 exactly as before (12, 12 and 23). The POST flow: state A (2 interested) → **302** to `listing.php?id=8#summary` → state B with `mailto:` and `tel:` and 3 interested → reload: still B → **Withdraw** → 302 to `…&withdrawn=1#summary` → state A with "Interest withdrawn" and 2 interested; the Exchange card agrees at every step, and the guards (a second interest, the owner, the closed listing, `id=999`, bad fields, a 400-letter note cut to 300) behave as before. Visitor → **Log in to show interest** → Log in → `listing.php?id=8#summary` in state A, in curl and in Edge. Privacy: 62 checks (state A, visitors, the empty Nimali, Exchange, the Landing page, five profiles, Explore, two plant pages, the Dashboard) with no email, phone, `mailto:` or `tel:`; state B shows only the ticked fields (9, 10 and 11 email, 12 phone, 8 and 13 both); the owner's own details on 7 (both) and 6 (email). Measured 9 addresses × 4 widths (375, 768, 1024, 1280): no sideways scroll, one `<h1>` (none on the 404, as before), every image has `alt`. Screenshots looked at: 375 px (state B on 11 with both buttons on one line, state A on 13 with the balanced two-line title, and the screen as a person sees it after Log in, after I'm interested and after Withdraw, at 375 × 812 and 375 × 667), 768 px (state B on 11, the owner on 7) and 1280 px (state B on 11, and after Log in, I'm interested and Withdraw at 1280 × 900).

> [!tip] What to remember
> - Form that changes data: POST, check, change, then **redirect** (Post/Redirect/Get). A reload repeats only the GET.
> - Check on the server what the form already prevents (the owner, a closed listing, a second interest): requests can be made by hand.
> - Private data is decided where it is **loaded**: no live interest, no contact row. The owner's choices (`share_email`, `share_phone`) decide which fields exist at all.
> - A `?next=` address is input. Accept an exact local pattern (whitelist), and end a PHP regex with `\z`, not `$`.
> - Mock helpers should change the mock data, not the page: then every page agrees, as with a real database.
> - Two columns that stack independently: let the cards span grid rows instead of using rows of two.
> - A textarea's `maxlength` counts a line break as one character, but the browser sends two (`\r\n`): turn `\r\n` into `\n` before cutting a text on the server.
> - Add anything to a checked address (like `#summary`) only after the check.
> - A jump after a redirect should land on what the member needs to see next (the title, the new count and the card), not scroll it away.

**Check yourself**
1. Why does *I'm interested* POST to `listing.php` and then redirect, instead of printing state B straight away?
2. Nimali opens listing 8 and has not shown interest. Name two things that stop Ruwan's email from reaching the page.
3. What happens with `login.php?next=https://evil.example`, and why does `return_path()` end with `\z`?

> [!success]- Answers
> 1. Post/Redirect/Get. Without the redirect, the page shown after the POST is the POST's answer: a reload would ask to send the form again and could send a second interest. After the redirect the page is a normal GET, which can be reloaded and bookmarked.
> 2. `$contact` is only filled when `$my_interest` is a live (not withdrawn) interest, so state A has nothing to print; and the contact details are in their own array (`$mock_user_contacts`, stage B: a query that joins `listing_interests`), which cards, profiles and visitors never read. Even in state B, a field the owner did not tick is `null`.
> 3. `return_path()` returns `''`, so the member logs in and goes to the Dashboard, not to the other site. With `$` the pattern would also accept the same address with a line break at the end; `\z` means the very end of the text.

Lessons: [[03.06 Form Handling - Validation, Redirects and Flash Messages]] · [[03.04 PHP Sessions and Login State]] · [[06.02 Security Review of a PHP Application]] · [[01.04 Rendering States - Empty, Owner and Visitor Views]] · [[01.03 Query Strings and GET Parameters in PHP]] · [[00.07 Mock Data Shaped Like Database Rows]] · [[07.01 CSS Layout with Flexbox and Grid]] · [[03.07 Access Control and Ownership Checks]] · [[03.05 CSRF Tokens]]

---
### Step 4.8 — Week 4 checks and close-out
> [!important] Goal (COM-01, COM-02, COM-03, COM-05, COM-07, COM-08 and COM-13, close-out)
> Check the four week 4 pages together: follow the links between them, run the four "Checks for every page" again after all the review fixes, fix the small problems that the earlier reviews left for this step (most of them in shared code), bring the team documents up to date, and close the week.

**What was done (2026-10-21, branch `feature/pages-ui`, commits `aed2bbe` … `e71453a`)**
Twelve small commits, one problem each; each bullet names the files that changed.
- **`aed2bbe` Hand-made addresses no longer break the week 3 pages.** My Garden (`q`, `status`, `sort`, `view`), the Plant Profile (`tab`), Settings (`saved`, and the form fields `form`, `new_password`, `confirm_password`) and Add plant (`ai`, `pick`) now read every value with `get_text()` and `post_text()` from `includes/helpers.php`, like the week 4 pages. Before: `garden.php?q[]=x` gave a warning; `garden.php?sort[]=x`, `plant.php?id=42&tab[]=x`, `settings.php?saved[]=x` and a sent form with `form[]=x` stopped with fatal errors; `new_password[]=x` printed "Array to string conversion". The ids still go through `filter_input(INPUT_GET, 'id', FILTER_VALIDATE_INT)`, which was already safe. A search of `public/` now finds no direct `$_GET`/`$_POST` read except the `species[]` list on Explore (checked one by one since step 4.2) and the `?ai` switch (`isset()` only).
- **`2b5b531` `e()` keeps the text when one byte is broken.** `ENT_SUBSTITUTE` in `includes/helpers.php` (see Why this way).
- **`a9ecff0` The bell count has its own name.** `includes/header.php` called it `$interest_count`, the same name the Dashboard uses for "4 people are interested in …"; the header overwrote the Dashboard's value. It is now `$bell_count`. Both numbers are 4 today, so nothing changed on screen.
- **`dc100be` Screen readers hear which link is lit.** A new helper, `nav_current()`, gives the lit link of the top bar, the phone bottom bar and the account menu an `aria-current`. Until now the colour was the only sign on most pages.
- **`cfd9645` Stylesheets carry a version.** A new helper, `asset_url()`, adds `?v=` and the file's last change time to the four stylesheet links in the header, so a browser never keeps an old copy after a CSS change (in step 4.7 Edge did).
- **`a65c913` My Garden no longer scrolls 17 px sideways at 375 px** (the week 3 bug found in step 4.5): `position: relative` on `.collection-tabs` in `garden.css`.
- **`dcc1ccd` Every not-found page has an `<h1>`.** `partials/empty-state.php` takes `'heading' => 'h1'`; "Plant not found" (`plant.php`, `plant-form.php`), "Member not found" and "Listing not found" use it. They look exactly as before.
- **`a8e394a` 44 px tap targets** for three shared controls: the header buttons (**Log in**, **Join free**, and the member's **Add plant**) from 768 px up (`layout.css`), the removable filter chips of Explore and Exchange (`.filter-chip`), and the breadcrumb links ("Exchange", "My Garden") (`components.css`).
- **`ab7904d` Exchange no longer blames "these filters"** when the only chip is "Any distance" (`exchange.php?q=zzzz&distance=any`).
- **`e8bc521` Add / Edit plant no longer scrolls sideways on a tablet.** Found by this step's measurements, see Problems and fixes.
- **`98d8082` Team documents.** `database/README.md`: this week's proposed columns and table, marked *proposal (week 4)* in the "Added by DES-07" column. `partials/README.md`: `plant-menu.php` (week 3) was missing, and the "Used by" lists of `listing-card`, `rating`, `avatar` and `confidence-meter` were out of date (the `empty-state` line, with its new option, came with `dcc1ccd`).
- **`e71453a` Style guide.** A new section, "Results bar: count, filter chips, sort", and two community plant cards (Thai Con and Big Momma) under Nimali's cards. `docs/ROADMAP.md` is unchanged: nothing in it is wrong.

**Checked**
- **Journeys, by following the real links** (a small script reads each `href` on the page and opens it), as Nimali, as a visitor and as the empty Nimali. Every page on the way answered 200 with one `<h1>`.
  1. Explore "monstera" → the first card, Thai Con → the owner card's **View profile** → @kandy.jungle.
  2. Explore People tab → the first member card → @aroid.amaya.
  3. Kavindi's Listings tab → Heartleaf philodendron cuttings (4) → **View profile** → Kavindi's profile.
  4. Visitor: Landing **See all 8 listings** → Exchange (8 cards) → Pothos 'Golden' → breadcrumb **Exchange** → Exchange. Members are sent from the Landing page to the Dashboard (on purpose since week 2), so their journey starts at the header's Exchange link (7 cards for Nimali, 5 for the empty Nimali).
- **Header and bottom bar**, on 138 pages (every page in `public/` as Nimali, visitor and empty Nimali): every lit link has `aria-current`, and no navigation link that is not lit has it. Exchange is lit on Exchange (`page`) and on every listing (`true`); Explore on Explore, on other members' plants and profiles; My Garden on Nimali's plants and on Add plant; Profile on her own profile (`page`) and on Settings (`true`). For a visitor, Explore is lit on Explore, plants and profiles, and **nothing is lit on Exchange or a listing**, because the visitor's Exchange link points at `index.php#fresh` (see Decisions).
- **No PHP errors:** the 138 pages, and 461 hand-made addresses × Nimali, visitor and empty Nimali (1,383 requests): `q[]=x`, `sort[]=x`, `tab[]=x`, `saved[]=x`, `id[]=1`, `%FF` in `q`, `id`, `u`, `tab`, `sort` and `saved`, `<script>` in `q`, `id`, `tab` and `u`, and `status[]`, `view[]`, `collection[]`, `ai[]`, `pick[]`, `u[]`, `species[0][]`, `type[]`, `distance[]`, `health[]`, `location[]`, `exchange[]`, `photo[]`, `withdrawn[]`, `next[]` on **every** page, plus the preview switches `member[]=1`, `empty[]=1` and `guest[]=1`. No warning anywhere, and no `<script>` printed as code. Settings sent with `form[]=x`, `new_password[]=x` or `confirm_password[]=x`: no error, and the right message under each field.
- **Nothing else changed:** 52 good addresses gave byte-identical pages before and after `aed2bbe`, and the 138 pages before and after `2b5b531` and `a9ecff0`. A not-found page at 1280 and 375 px is pixel-identical before and after `dcc1ccd`; a listing and a plant page are pixel-identical below the header before and after `a8e394a`.
- **The interest flow again** (P11): listing 8, 2 interested → *I'm interested* → 302 to `#summary` → state B, 3 interested, `mailto:` and `tel:` → *Withdraw* → 2 interested, "Interest withdrawn"; a visitor's POST goes to Log in and comes back to the listing.
- **The four "Checks for every page"** for P09, P14, P10 and P11:
  1. **Wireframe:** screenshots at 1280 px compared with p16, p21, p17 and p18, and the phone views with p23: they match, apart from the differences recorded in the Open points. The Figma prototype is not compared yet (Ranshitha).
  2. **Widths:** 48 addresses (the four pages in their states, as Nimali, visitor and empty Nimali, plus every other page after the shared changes) × 375, 768, 1024 and 1280 px = **192 page views, no sideways scroll** (after `e8bc521`). Screenshots looked at: a true 375 px (all four pages), 768 px (the profile and a listing, as a visitor) and 1280 px (all four, and the style guide's new sections). No page has JavaScript yet, so there can be no console errors.
  3. **Escaping, alt text, labels:** a script listed every `<?= … ?>` in `public/`, `partials/` and `includes/` that is not a single `e()`, `icon()`, `nav_current()` or `saved_message()` call: of 818, the 88 left print fixed words (`' is-active'`, `'plant'` or `'plants'`) or values wrapped in `e()`, and the empty state's tag can only be `h1` or `h2`. In the 138 rendered pages: 409 images, all with `alt`; 432 form controls, all with a label.
  4. **Contact details:** 86 curl checks. 75 found no email, phone, `mailto:` or `tel:`. The other 11 are all expected: state B on six listings (only the fields each owner ticked), Nimali's own details on her own listings 7 and 6, her own email on her own Settings page, and the site's sample contact address on About.
- **Touch targets** on the week 4 pages and the Landing page: none under 44 px at any of the four widths. **Keyboard:** Tab pressed through 14 page views of the four pages (1280 and 375 px): every stop shows a focus ring.

**Why this way**
- **One helper per job, used everywhere.** The array bug was fixed on Explore in step 4.4 and moved into `get_text()` in step 4.6, but the older pages kept their own lines. A fix that lives in one page protects one page. The same thinking made `nav_current()` and `asset_url()` helpers instead of code in the header.
- **`aria-current="page"` or `"true"`.** `page` means "this link opens the page you are on". On a listing, the lit Exchange link opens `exchange.php`, another page, so `page` would be wrong; `true` means "the current item in this set" and fits a section link ([[01.01 Page Skeleton - Header, Footer and Navigation]] explains `aria-current`). The helper takes the same test as the `.is-active` class, so the colour and the attribute can never disagree.
- **A version from the file's change time.** A browser keeps a stylesheet it has loaded and may reuse it without asking again (see [[06.08 HTTP Caching and ETags]]). A different address is a different file for the browser, so `listing.css?v=1792511088` is loaded fresh as soon as the number changes, and the number only changes when the file does. `?v=` + `time()` (the current time) would also work, but then no browser could ever reuse its copy.
- **`ENT_SUBSTITUTE` is safe.** Without it, `htmlspecialchars()` returns an empty string for any text holding a byte that is not valid UTF-8, so one broken byte hid a whole name or heading (step 4.4 found it with `q=%FF`). With it, the broken byte becomes "�" and the rest stays. The escaping of `<`, `>`, `&` and quotes is exactly the same ([[00.04 Output Escaping in PHP]]).
- **Bigger tap areas without changing the look.** The header buttons now match the 44 px round buttons beside them. A breadcrumb link is a word inside a sentence-like row: 13 px of padding above and below an *inline* link makes the tap area 45 px tall but does not push the lines apart (the same trick as Exchange's "Log in" link in step 4.6). The filter chips grow to 44 px, the height of Clear all and the sort button beside them. The other 36 px controls from 768 px up (`.btn-sm`, the other chips) follow a week 1 rule, "a mouse does not need 44 px"; changing that is Ranshitha's decision.
- **An `<h1>` on a not-found page.** The empty state's title is an `<h2>` because it usually sits under the page's own heading. On a page that is *only* the empty state there was no main heading, and screen reader users jump to the `<h1>` first. An option keeps one partial for both cases.
- **Name a class after what it means.** `.site-header-member` hides the logo text on a tablet because a member's bar holds more buttons. It was printed when the bottom bar was shown, which was the same thing until Add plant hid the bottom bar in week 3.

```php
// helpers.php: the attribute for a lit link ("page" for this very page, "true" for its section)
function nav_current($is_lit, $href)
{
    if (!$is_lit) {
        return '';
    }
    $page = basename((string) parse_url($href, PHP_URL_PATH));   // 'garden.php' or a full address: the file name
    return $page === current_page() ? ' aria-current="page"' : ' aria-current="true"';
}

// header.php: the same test gives the class and the attribute
<a class="nav-link<?= $link['area'] === $area ? ' is-active' : '' ?>" href="…"<?= nav_current($link['area'] === $area, $link['href']) ?>>

// helpers.php: a stylesheet address that changes when the file changes
function asset_url($path)
{
    $file = __DIR__ . '/../public/' . ltrim($path, '/');
    return url($path) . (is_file($file) ? '?v=' . filemtime($file) : '');
}

// helpers.php: e() with ENT_SUBSTITUTE
return htmlspecialchars((string) $value, ENT_QUOTES | ENT_SUBSTITUTE, 'UTF-8');
```

```php
// garden.php: before (an array in the address stopped the page) and after
$sort = $_GET['sort'] ?? 'updated';   // garden.php?sort[]=x: an array, then array_key_exists() → fatal error
$sort = get_text('sort');             // '' for anything that is not one piece of text → the default below
if (!array_key_exists($sort, $sort_options)) {
    $sort = 'updated';
}
```

```css
/* components.css: a 45 px tap area that does not make the breadcrumb row taller */
.breadcrumb a { padding: 13px 0; }

/* layout.css: the header's buttons as tall as the round buttons beside them */
.header-actions .btn { min-width: 44px; min-height: 44px; }
```

**Problems and fixes**
- **Add / Edit plant scrolled sideways on a tablet** (week 3 bug, found by this step's measurements): 834 px of page in a 768 px window, and too wide in every window from 768 to about 833 px. The top bar got `.site-header-member` only when the bottom bar was shown, and Add plant hides the bottom bar, so the logo text stayed and the member's links and buttons did not fit. Putting the old stylesheet back for a moment showed 831 px before this step (this step's 44 px buttons added 3 px), so the bug was older. Fixed in `e8bc521`: the class now follows `$is_member`.
- **The first bad-address run said a visitor's My Garden had a warning.** A visitor is sent to Log in before My Garden runs, so that was impossible. The list of test values contained `member[]=1`, and `?member` with any value logs the preview in as Nimali, so the "visitor" run became a member half way. The switches now run last, on their own.
- **The first screenshot comparison said "the same" for every pair**, even where the header buttons had visibly grown. The script built the second file name wrongly on Windows and compared each picture with itself. A new script showed the differences exactly where expected: only the header (rows 10 to 54) on most pages, and the chips on Explore and Exchange.
- **The journey script followed the bottom bar's Profile link** instead of the owner card: a `\b` in a regular expression written inside a Python string became a "backspace" character, so the search for `<main>` never matched and the whole page was searched. Searching for `<main ` fixed it.
- **The keyboard check first stopped after 9 Tab stops** (two icon buttons looked the same to the script) and then reported every card without a focus ring (the card's ring is drawn on its `::after`, which covers the whole card). Both were mistakes in the script; the page was right.

> [!tip] What to remember
> - Fix a shared problem in shared code, then use it on **every** page, not only the new ones.
> - Anything in `$_GET` or `$_POST` can be a list or broken bytes, on old pages too: read single values with `get_text()` and `post_text()`.
> - `aria-current="page"` for the page itself, `"true"` for the current section; give it with the same test as the highlight colour.
> - A versioned address (`?v=` + the file's change time) makes browsers load a changed file and keep an unchanged one.
> - `ENT_SUBSTITUTE`: a broken byte shows as "�" instead of blanking the value; escaping stays the same.
> - Padding on an inline link enlarges the tap area without moving the lines.
> - Check the checker: a test that cannot fail (a file compared with itself, a "visitor" who is logged in) proves nothing.

**Check yourself**
1. What did `garden.php?sort[]=x` do before this step, and what does it do now?
2. On `listing.php` the lit Exchange link has `aria-current="true"`, on `exchange.php` it has `aria-current="page"`. Why?
3. How does `asset_url()` make a browser load a changed stylesheet, and why not add `?v=` with the current time?

> [!success]- Answers
> 1. Before: PHP turned `sort[]=x` into an array, and `array_key_exists()` cannot use an array as a key, so the page stopped with a fatal error. Now `get_text('sort')` returns `''` for anything that is not one piece of valid text, `''` is not an allowed sort, and the page shows "Recently updated".
> 2. `page` means "this link opens the page you are on". On `exchange.php` that is true. On a listing, the link opens another page; it is only the section the listing belongs to, which `true` ("the current item of this set") says.
> 3. It adds `?v=` and the file's last change time (`filemtime()`). When the file changes, the address changes, and a browser treats a new address as a new file. The current time would change on every page load, so the browser could never reuse its copy and would download every stylesheet every time.

Lessons: [[01.03 Query Strings and GET Parameters in PHP]] · [[00.04 Output Escaping in PHP]] · [[01.01 Page Skeleton - Header, Footer and Navigation]] · [[02.06 Mobile Patterns - Bottom Bar, Sheets and Stacked Tables]] · [[06.08 HTTP Caching and ETags]] · [[00.06 Component CSS and the Style Guide]] · [[00.03 PHP Includes and Partials]]

---
## Decisions waiting for Ranshitha
Every open choice from this week's Open points (and the one due from Week 02), in one list. Nothing below was changed without you.
1. **Visitor Exchange link** (Week 02, now due): keep `index.php#fresh`, or send visitors to `exchange.php`, which works for them since step 4.6? While it stays on `#fresh`, a visitor sees no lit link on Exchange and listing pages.
2. **Succulents privacy** (P14): make Succulents public to match p21 (her profile would show 18 plants and 3 collections), or keep it private (13 and 2 now).
3. **Size and condition** (P11, for week 5): add two optional fields to P12 Create Listing, or drop the proposed `size` and `plant_condition` columns.
4. **Phone number** (P11): where does a member enter or change it: Settings › Account and security, or the listing form (P12)? No page lets them today.
5. **A private source plant** (P11): hide the whole "Comes from" line from other people (now), or show the plant's name without a link?
6. **Withdraw interest** (P11, week 5): should the S6 confirm dialog ask first? Today it does not, because sending interest again undoes it.
7. **Log in `next`** (P11): should the Sign up tab and the header's Log in button also bring a member back to the listing?
8. **40 px controls** (P09): raise the shared segmented control and the Log in / Sign up tabs to 44 px, as the Explore tabs already are?
9. **36 px from 768 px** (close-out): keep the week 1 rule that `.btn-sm`, chips, the ⋯ button and the grid/list buttons are 36 px on tablets and desktops ("a mouse does not need 44 px"), or make them 44 px everywhere, because tablets are touch screens? This step raised only the header buttons, the filter chips and the breadcrumb links.
10. **DES-07 proposals** (now in `database/README.md`): accept or drop `exchange_listings.species_id`, `size` and `plant_condition`, `listing_interests` UNIQUE (`listing_id`, `user_id`), `user_ratings.created_at` and the `listing_photos` table.
11. **Landing Fresh cards** (week 2 page, found here): at 1024 to 1280 px the four cards cut long usernames ("@nimali.g…", "@dinesh.gr…"). Accept it, let the rating move under the name on narrow cards, or show three cards at 1024 px?
12. **Your own checks:** compare the four pages with the Figma prototype, test them on a real phone, and try hover by hand.

---
## Open points
- **Explore (P09), mock data:** Dinesh lives in **Negombo** (p16), while his snake plant listing is in Kuliyapitiya: a listing can be somewhere other than its owner's home town. *Monstera* 'Thai Constellation' has its own species row because p16 prints that name. The owners of Leafy and Holey (cut off on p16), Ruwan's counts, every member's `created_at`, the collections and the six plants after Holey are invented. All monsteras use the Monty placeholder drawing.
- **Explore (P09), page:** "Newest" sorts by plant id (no `created_at` on `plants`). A new search drops the filters (my choice). The species chip shows the common name ("Swiss cheese plant"), where p16 shows "Monstera". The People tab was a placeholder until step 4.4, which filled it (see the next bullets). Nimali appears among the people, and her own plants among the results: p16 shows Monty, so I kept both.
- **Explore (P09), filters:** **Apply filters** is not on p16; it is needed until week 6 applies them on every change. Health has an **Any** chip (not on p16) so a choice can be taken back without JavaScript. The species list is one row per species, not p16's groups ("Monstera", "Succulents"). "Clear all" shows both in the sidebar and beside the chips (the sidebar is hidden on a phone). p23's bottom sheet for filters waits for week 6; until then the panel opens in place.
- **Explore (P09), member card (step 4.4):** a partial (`partials/member-card.php`), because the row and the People tab show the same thing. The compact card keeps the rating count, "★ 4.8 (14)", where p16 shows "★ 4.8": the rating partial looks the same everywhere. The compact avatar is the 40 px default, not `sm` (28 px): on p16 it is as tall as the two text lines. On the People tab the avatar is 72 px (the profile's 96 px crowds a card a third of the page wide). On a phone the People card puts the avatar beside the text; from 768 px it stands upright and centred. "N matching plants" shows only on the People tab, only for a plain word search. Nimali appears among the people when she is logged in, as before.
- **Explore (P09), People tab and row (step 4.4):** the People tab is ordered by most matching plants, then the biggest garden; without a search, the biggest garden first. A muted line under the count says why these people match (not on p16). Its wording is mine: *Names, usernames and towns that match "…", and members who grow a matching plant.* (for an `@` search: *Names and usernames that match "…".*). The row shows only for a search that matched someone, at most three cards, also above "0 plants" (e.g. `?q=Perera` finds Nimali by surname). "See all 5 people" shows only when the row leaves someone out, so a search matching one to three members has no link (review fix; p16 shows it with 3 of 8, and "See all 1 person" read badly). It has no arrow, and quotes are straight, as on p16. Its heading and "Popular plants" use Poppins semibold like p16's row heading, not Lora. Copy never uses he or she ("See all 5 people", not "See her listings").
- **Explore (P09), no results (step 4.4):** "the last filter" is the last chip in the row, because one form sends every filter at once. Buttons when the search word finds plants but the filters hide them all: Remove "*last chip*" (e.g. Remove "Kandy"), then Clear all filters (two or more filters) or Clear the search. When the search word finds nothing on its own (review fix): Clear the search (only with filters on; it keeps them), then Show all plants (drops the search and the filters, keeps the sort, grid icon); the filters are not blamed. Popular plants are the six most-liked public plants (ties by name), ignoring the search and the filters; six fills 1, 2 and 3 columns evenly. All wording is mine: *No plants match "zzzz"* (+ *with these filters*, only when the filters are to blame), *No plants match these filters*, *No public plants yet* with *Plants appear here when members make them public.*, *Check the spelling or try a shorter word. Until then, here are some popular plants.*, *Remove a filter to widen the search. Until then, here are some popular plants.*, *Popular plants · The most-liked public plants in the community.*, *No members match "…"* (or *No members yet*) with *Check the spelling, or try part of a name, a town or an @username.* and Show all members.
- **Explore (P09), code (step 4.4):** `partials/empty-state.php` accepts `'url'` beside `'href'`. Address values are text only, and valid UTF-8 only (`$get_text()`, review fix). `query_url()` in `includes/helpers.php` now also leaves out empty lists, so no link ends in a bare "?" (review fix, affects every page, no visible change). The result counts are plain headings, without `role="status"` (review fix). **Same bug still open in earlier pages:** `garden.php?q[]=x` (warning), `garden.php?sort[]=x`, `plant.php?id=42&tab[]=x` and `settings.php?saved[]=x` (fatal errors). Suggestion: move `$get_text()` (with its encoding check) into `includes/helpers.php` as a shared helper and use it on those pages, as its own small fix step. A second suggestion for the same step: add `ENT_SUBSTITUTE` to `htmlspecialchars()` in `e()`, so a broken character shows as � instead of blanking the whole value on any page (not done: it changes a shared week-1 helper). To decide in DES-07: whether a member's public `plant_count` (42, 64 …) counts private plants too. *(Decided in step 4.5: public things only; see the first P14 bullet, "counts and privacy".)* *(Step 4.6 review fix `c4bb634`: the helper now exists as `get_text()` in `includes/helpers.php`, used by Explore, the Public Profile and Exchange. `garden.php`, `plant.php` and `settings.php` do not use it yet: re-checked on 2026-10-20, the four addresses above still give the warning and the three fatal errors. The `ENT_SUBSTITUTE` suggestion is still open too.)* *(Step 4.8: both done. `garden.php`, `plant.php`, `settings.php` and `plant-form.php` read every value with `get_text()` and `post_text()` (commit `aed2bbe`): the four addresses above and a sent Settings form with `form[]=x` no longer cause any error. `e()` has `ENT_SUBSTITUTE` (commit `2b5b531`).)*
- **Explore (P09), touch targets and layout (step 4.4 review fixes):** the Plants / People tabs are now 44 px tall, 4 px taller than the shared segmented control (`components.css`, `.segmented label`) and the Log in / Sign up tabs (`auth.css`, `.auth-tab`), which are still 40 px, under the 44 px rule. To decide: raise those to 44 px too, as a small fix step for the earlier pages. *(Step 4.8: left as they are; see Decisions waiting for Ranshitha, item 8.)* The "Filters" head and the row head are always 44 px tall, so on a desktop "Filters" lines up with the first results heading, as on p16. The phone strip's cards are a fixed 240 px (it was `minmax(240px, 80%)`, which drew 240 px anyway); about 107 px of the next card shows at 375 px.
- **Public Profile (P14), counts and privacy (step 4.5):** decided here: a member's public `plant_count` and `collection_count` count **public things only** (this answers the DES-07 question in the Explore code bullet above). So Nimali shows **13 plants and 2 collections** on her profile, on her Explore people card and on her plants' owner card, where p21 shows 18 and 3; her own Dashboard and My Garden keep 18 and 3. p21 also shows her **Succulents** collage, but Succulents has been private (`is_public` 0) in the mock data since week 3. I kept privacy working instead of flipping it, so others see only Living room and Balcony. **To decide:** make Succulents public to match p21 (the profile would then show 18 and 3), or keep it private. The tab counts are the full public counts (Nimali's Plants 13, Kavindi's 64), while the mock sample holds fewer cards (6 and 3): the same sample gap as My Garden's 18 plants and 8 cards. *(Review fix `3ef2094`:)* a collection's `plant_count` and collage on the profile also count **public plants only**: in stage B the profile's query adds `AND plants.is_public = 1` to the `COUNT()` and the `GROUP_CONCAT()`, because a public collection may hold a private plant. My Garden keeps counting every plant. In the mock data both give the same numbers (Nimali's 13 public plants are the 7 in Living room and the 6 in Balcony).
- **Public Profile (P14), ratings (step 4.5):** p21's breakdown 20 / 2 / 1 averages 4.83 (printed 4.8), so I used **21 / 1 / 1** (4.87, printed 4.9) to keep the 4.9 shown on p16, p17, p18 and p21. The other members' breakdowns are invented to match their p16 ratings: Amaya 11 × 5★ + 3 × 4★ = 4.8, Kavindi 11 × 5★ = 5.0, Dinesh 11 / 3 / 1 = 4.7, Ruwan 6 / 2 / 1 = 4.6. **`user_ratings.created_at` is a proposed column for DES-07** (p21 dates the latest review; the table plan has no date). Only ratings with a comment are rows in `$mock_user_ratings`; the others exist only in the counts. Every review except Dinesh's of 14 Sep, and every review date, is invented. Listing ids 1 to 5 stand for Nimali's closed exchanges (titles invented; they are not in `$mock_listings`, which holds open listings only). *(Step 4.6: listing 5 is now in `$mock_listings` as a closed row, to prove the Exchange hides closed listings.)* A review date shows "14 Sep" in the current year and "8 Mar 2025" in an earlier one (my rule). *(Review fixes `3ef2094`:)* each review prints its score in figures after the stars, **"4/5"** (not on p21; `partials/stars.php` option `'show_score' => true`), and empty stars are drawn in the muted text colour instead of the light line colour, everywhere `.stars` appears (the profile, the style guide, later the S4 Rate dialog). With exactly one rating and no comment the Ratings tab would say *No written reviews yet: the only rating was given without a comment.* (more than one: *…all 15 ratings were given without a comment.*); both sentences are mine.
- **Public Profile (P14), mock data (step 4.5):** invented: the bios of Amaya, Kavindi, Dinesh and Ruwan (Nimali's is from p21); every `swap_count` except Nimali's 12; every `collection_count`; the other members' collections (names, plant counts, and two private ones, Amaya's *Propagation box* and Dinesh's *Propagation jars*); and the collage photos, which borrow placeholder drawings for plants that are not in the sample. `cover_photos` is a calculated column (stage B: `GROUP_CONCAT` of the photos of the three most-liked plants). `swap_count` means closed swap listings the member took part in, as owner or as `exchanged_with`; a free give-away can be rated too, so ratings can outnumber swaps. Nimali's second open listing, **Peace lily division** (id 6, from p17: Kurunegala · 1 km · 1 week ago), was added so "See listings (2)" matches p21; its "Looking for: a fern or a calathea" is invented (cut off on p17). *(Review fix `3ef2094`:)* the `?empty` preview now also removes the reviews Nimali **wrote**, because a brand-new member has finished no exchange; the other members keep their counts and averages, so on their profiles those ratings count as ratings without a comment (in `?empty`, nobody else has a written review, so their Ratings tabs say *No written reviews yet* and their rating cards have no Latest review).
- **Public Profile (P14), page (step 4.5):** "See listings (2)", not p21's "See her listings (2)" (no he or she). The ⋯ menu items are my choice: Report profile → `about.php#contact` (no report form yet) and Community guidelines for others; Account settings, Privacy settings and Go to My Garden for the owner. Edit profile opens `settings.php#profile`. A visitor with no `?u` gets the 404 (*The address does not say which member to show. You can find people on Explore.*); an unknown name gets *Nobody is called @… Check the spelling, or search for the member on Explore.* The owner's note is not on p21 (*This is how other people see your profile. Hidden from them: 1 private collection and 5 private plants.* + Privacy settings). A collection card opens the Plants tab filtered to that collection (*Plants in Living room · 7 plants*, *Show all plants*): not on p21. Plant cards on a profile show the collection instead of the owner; listing cards still show the owner row (the shared partial is unchanged). The Ratings tab starts with a summary line (*5 written reviews out of 23 ratings. Ratings without a comment still count in the average.*), and "See all reviews (5)" sits under the latest review: neither is on p21. The rating card sits beside the tabs only from 1024 px; at 768 px it is full width under the tabs, with the bars and the latest review side by side. All empty-state wording is mine: *No public collections yet*, *No public plants yet*, *No open listings*, *No ratings yet*, with Go to My Garden, Add a plant, List a plant or Browse the exchange; with no ratings the stats row says "– no ratings yet" and the card *No ratings yet · Ratings appear after a completed swap.* *(Added after the review, `3ef2094`:)* the empty states' sentences are mine too: Collections *Collections you make public in My Garden appear here as photo collages.* (owner) and *@… has not shared a collection yet.* (others); Plants *Plants you make public appear here, for everyone to see.* and *@… has not shared a plant yet.*; Listings *List a plant to swap it or give it away. Your open listings appear here.* and *@… has nothing on the exchange right now.*; Ratings *You get / @… gets a rating after each completed swap. Ratings and reviews appear here.*; and, when every rating came without a comment, *No written reviews yet: all N ratings were given without a comment.* The Plants tab lists the most recently updated plants first (by `last_logged_at`, newest health log), my choice: a living portfolio. A collection with fewer than three plants fills the empty collage tiles with a leaf on sage instead of leaving a hole (my choice).
- **Public Profile (P14), review fixes (commit `3ef2094`):** **main action** for visitors and other members: See listings (N) when the member has open listings, otherwise **See plants (N)** (opens the Plants tab), otherwise nothing but the ⋯ menu (no listings and no public plants: nothing to point at). p21 only shows the listings case. Plant and listing cards are **2 across from 768 px at every width**, also beside the rating card on a desktop (3 across made plant cards about 200 px wide and the likes covered "Needs attention"). The shared plant card now holds its photo badges in one wrapping row (`.card-media-badges` in `components.css`), which changes nothing visible on Explore, My Garden or the style guide at today's card widths but keeps the health word uncovered on any narrow card. A collection card always shows the name, then the count on the next line; p21 puts them on one line, which made neighbouring cards uneven whenever one name was longer. Each tab panel opens with a visually hidden `<h2>` naming the tab. The breakdown's score column is right-aligned so the stars line up. The two review @username links are 44 px tall touch targets. An unknown name cut at 40 letters ends with "…" and no full stop. `count_words()` now lives in `includes/helpers.php` for every page.
- **Public Profile (P14), links and small open questions (step 4.5):** the @usernames of the reviews and of the latest review link to that member's profile. Usernames on listing cards and community plant cards stay plain text, because the whole card is already one link (to the listing or the plant) and a link inside a link is not allowed; the Plant Profile's owner card already has **View profile**. The 404 page has no `<h1>` (the empty state's title is an `<h2>`), exactly like the Plant Profile's "Plant not found": to decide in a small shared fix whether the empty state should print an `<h1>` on not-found pages. On a phone, a typed address with `?tab=ratings` but without `#tab-ratings` leaves the chosen tab off the right edge of the tab row ("R" peeks in); the page's own links always include it. The `?empty` preview keeps Nimali's bio and "Member since Mar 2025", so the owner's *Add a short bio* prompt only appears for a real account without a bio (stage B). The shared header's Log in and Join free buttons are 36 px tall on visitor pages at 768 px and wider (pre-existing, not part of this page). **Found during the review-fix checks (pre-existing, week 3):** My Garden (`garden.php`) scrolls 17 px sideways at 375 px. The visually hidden "Private:" inside the Succulents chip is `position: absolute`, and the nearest positioned box is outside the chip row that scrolls sideways (`.collection-tabs`), so it widens the page instead of the row. Fix for the week close-out: `position: relative` on that row (tried in the browser only, not committed: the 17 px went to 0). *(Step 4.8: every not-found page now has its `<h1>`, with the same look (commit `dcc1ccd`); the header's Log in and Join free are 44 px tall (commit `a8e394a`); and My Garden's 17 px is fixed with exactly that rule (commit `a65c913`, measured 392 → 375 px).)*
- **Exchange (P10), mock data (step 4.6):** the p17 cards keep p17's towns, distances, ages and interest counts. **Tomato seedlings (6)** now follows p17: **Wariyapola · 14 km · 1 week ago**, 3 interested (it said Kandy · 42 km · yesterday). It stays Kavindi's (her bio promises seedlings to give away), so a Kandy member lists in Wariyapola: `exchange_listings.location` is the pick-up town typed on the listing form, which can differ from the owner's home town, like Dinesh's listings. **Echeveria cuttings (5)** (Pannala · 22 km, 2 interested) is given to **@dinesh.greens** (p17 cuts its owner off), 9 days old ("1 week ago"). Invented rows: **Monstera cutting with a node** (id 12, @ruwan.roots, Kurunegala · 5 km, 4 days, type **`either`**, 0 interested, offers Leafy) and **Heartleaf philodendron cuttings (4)** (id 13, @kandy.jungle, Kandy · 42 km, 6 days, 1 interested, offers Hugo), the one beyond 25 km; and the closed row, listing 5, *Pothos 'Marble Queen' cuttings (3)*, Nimali's swap with Dinesh from the reviews (`status` `closed`, `exchanged_with` 4, created 6 Sep, 3 interested). Every `looking_for` that p17 cuts off is invented: Echeveria *a string of pearls or any trailing succulent*, Monstera *a pothos or a philodendron, or free to a beginner*, philodendron *any hoya or a small fern*, Marble Queen *a snake plant pup*. Ids are not in date order (the Newest sort uses `created_at`). Photos are placeholder drawings: the philodendron uses Hugo's pothos drawing, the Monstera the Monty drawing, the Echeveria Pearl's. Kavindi now has 2 open listings ("See listings (2)" on her profile); Amaya still has none. Leafy and Hugo now count as "on the exchange" on Explore (6 plants instead of 4). *(Added after the review:)* the Landing page's "Fresh on the exchange" (the four newest open listings) now shows **Pothos 'Golden'**, **Aloe vera pups (4)**, the invented **Monstera cutting with a node** and **Snake plant 'Laurentii'**, so @ruwan.roots appears twice. **Tomato seedlings (6)**, which week 2 put first as the newest listing, is no longer there, because p17 makes it 1 week old (a pointer was added to the Week 02 Open points).
- **Exchange (P10), proposed columns and counts (step 4.6):** **`exchange_listings.species_id` is a proposed column for DES-07**, used only when a listing has no plant (seedlings, cuttings). Each row carries `species_id`, `common_name` and `scientific_name` as calculated columns: stage B `COALESCE(plants.species_id, exchange_listings.species_id) AS species_id`, then `LEFT JOIN plant_species`. Every row also has `exchanged_with` (already in the table plan). p17 says "24 open listings near Kurunegala"; the page counts its rows, so it says **7** (8 with Any distance). The Landing page's open listing count is now counted from the rows (**8**, it was a typed 37), so "See all 8 listings" leads to exactly 8 cards. Listing 5 is now in `$mock_listings` as a closed row (see the P14 ratings bullet, which said ids 1 to 5 were not in it). The Landing page and the style guide now show open listings only.
- **Exchange (P10), visitors and distance (step 4.6):** a visitor has no town, so `distance_km` is **null for visitors on every page** (Landing, Exchange, Public Profile): the cards show "Kurunegala · 2 days ago" without km (until now the Landing page showed members' distances to visitors). On Exchange a visitor gets no Near filter and no Nearest sort (`sort=nearest` falls back to Newest), sees every open listing, and a line *Log in to see how far away each plant is, measured from the town on your profile.* (my wording). A visitor gets one button, **Log in to list a plant**, and no My exchanges (a visitor has none to manage). The visitor header's Exchange link still goes to `index.php#fresh` (team decision, unchanged). *(Added after the review:)* Week 02's Open points said to decide, once `exchange.php` exists, whether visitors should go to the full marketplace instead. It exists now and works for visitors, so that decision is due (a pointer was added there). *(Step 4.8: see Decisions waiting for Ranshitha, item 1.)*
- **Exchange (P10), filters and page (step 4.6):** members start at **25 km** (as p17); choices 5, 10, 25, 50 km and Any distance (my choice), shown as *Near Kurunegala · 25 km* and *Any distance*. The default distance has no chip; a changed one has (*Within 5 km*, *Any distance*), and removing it goes back to 25 km. The Species select lists only the species on offer now, A–Z, by common name (*Species: Any* first). The search looks in the title, "looking for", both species names and the username (`@ruwan` works). Type is radio buttons in the segmented control inside the one GET form, so nothing applies until **Search** is pressed (secondary button, not on p17; week 6 applies on change). The Search button sits beside the box; on a desktop the box, the button and the choices share one line, and the sort moved to the results line (p17 has it at the end of the filter line: a `<details>` menu would be cut off inside the line that scrolls on a phone). Sorts: **Newest** (default), **Nearest** (members only), **Most interest**; ties fall back to the newest. The ›-arrows between the steps show only from 1024 px; on a phone the steps are two by two. On a phone the type control and the selects scroll sideways in one line (p23's chips row); p23's search and filter icon buttons, bottom sheet and floating "List a plant" button wait for week 6. Each segment is at least 44 × 44 px (the shared segmented control is 40 px tall); every control of the form is 52 px tall so they line up. The results line is *N open listings near Kurunegala*, or *N open listings* with Any distance or for a visitor (my wording; p17 prints it smaller and muted, here it is the results heading as on Explore). *(Review fixes `20a4fb0`:)* on a phone the All / Swap / Free control now has **a line of its own** (full width, so each segment is about 112 px wide) and only the two selects share the line that scrolls sideways, so the Species select peeks in at the edge (61 px at 375 px; before, the 290 px distance select pushed it fully off the screen). p23 shows the type chips in the same row as "Near me" and "Spe…". One select cannot show p23's short "Near me" on a phone and p17's "Near Kurunegala · 25 km" on a desktop, so I kept p17's wording and moved the type up (my choice; week 6's bottom sheet may change this again). A visitor's Species select fills its line on a phone. Between 1024 and about 1050 px a member's choices do not fit beside the search: they wrap onto a second line and now fill it (`flex-grow`); from about 1060 px the search and the choices share one line, as before.
- **Exchange (P10), no results and small open questions (step 4.6):** all wording is mine: *No listings match "…"* (+ *with these filters*) with *Check the spelling or try a shorter word, like a plant name.*; *No listings match these filters* with *Try another type or species, or a longer distance.*; and, only if nothing at all is open, *No open listings near Kurunegala* / *No open listings yet* with *New listings appear here as soon as members post them.* Buttons: **Clear the search** (keeps the filters) then **Show all listings** (keeps the sort), or **Clear all filters**. *(Review fixes `20a4fb0`, replacing these buttons:)* the page counts before it offers a button, so no button leads to another empty page. First choice: **Search any distance** (map-pin icon, primary), when a member's distance limit hides listings that match, with the text *Nothing within 25 km of Kurunegala, but there is 1 listing further away.* (or *are N listings*). Otherwise **Clear the search**, only when the filters alone find listings. Then **Show all listings**, which now really shows all of them: no search, no filters, **any distance**, the same sort (it used to keep the 25 km); it is left out when the first button already leads to the same address. **Clear all filters** is no longer in the empty state (the results bar's **Clear all** still is). *Try another type or species, or a longer distance.* became *Try another type or species.*, because it now only shows when a longer distance would not help. *No open listings near Kurunegala* (no search, no filters) now always comes with Search any distance, and *No open listings yet* only when nothing at all is open. All wording mine. The listing card partial is unchanged: its type badge (top left) and interest count (bottom right) are in different corners, so unlike the plant card they cannot cover each other, and it did not need the `.card-media-badges` row. The results-bar CSS moved from `explore.css` to `components.css` (no visible change on Explore). **Touch targets, for the close-out:** the removable filter chips are 36 px tall from 768 px up (the shared `.chip` rule, the same on Explore), under the 44 px rule. *(Step 4.8: now 44 px on every screen through `.filter-chip`, commit `a8e394a`.)* Listing cards link to `listing.php`, and the actions to `listing-form.php` and `my-exchanges.php`, which are not built yet: `listing.php` comes in step 4.7, the other two in week 5 (404 until then). *(Step 4.7: `listing.php` exists now, and every card on Exchange, the Landing page and the profiles opens a complete page; listing 5, the closed one, shows the closed state.)*
- **Listing Detail (P11), mock data (step 4.7):** every listing row has `description`, `share_email`, `share_phone` (table plan) and two **proposed DES-07 columns, `size` and `plant_condition`** (short optional text for p18's details list; not `condition`, a reserved word in MySQL). The other way would be to write size and condition into the description, but then the page could not show them as separate rows. **To decide in week 5:** p19 (Create Listing) has no Size or Condition field, so P12 needs two optional fields, or the columns go. Listing 7's text, size and condition are p18's; all other descriptions, sizes, conditions and share choices are invented: email and phone on 7, 8, 13 and the closed 5; email only on 6, 9, 10 and 11; phone only on 12. **`listing_photos` is a proposed table for DES-07** (id, listing_id, path, created_at), like `plant_photos`: the gallery shows the card photo (`exchange_listings.photo`) first, then these rows. Listing 7 has 3 photos (p18), listing 8 has 2 (invented), the rest have 1 and show no thumbnails; the extra photos repeat the placeholder drawing. **Contact details** are a separate array, `$mock_user_contacts` (the `email` and `phone` columns of `users`), so the public member rows never hold them. Invented: amaya.m@, kavindi.j@, dinesh.g@ and ruwan.r@ at **example.com** (a domain kept for examples), phones `+94 76 000 2345`, `+94 71 000 3456`, `+94 70 000 5678`; Dinesh has no phone. **Nimali's phone is now `+94 77 000 1234`** (p18 and p19; it was `null`). Settings (p22) has no phone field, so it stays consistent, but **no page lets a member enter or change a phone number. To decide:** an optional Phone field in Settings › Account and security, or on the listing form (P12). **`$mock_listing_interests`** holds Nimali's own interests. One is in the mock data: listing 11 (Dinesh's echeveria cuttings, status `seen`, sent 6 days ago; message and date invented), so it opens in state B and one of its "2 interested" is her. Proposed for DES-07/08: **UNIQUE (`listing_id`, `user_id`)** on `listing_interests`, one row per member and listing (withdrawing sets `status` to `withdrawn`; showing interest again sets it back to `new`). *(Review: p18 says "Posted 28 Sep · 4 interested", but p17's card for the same listing says "2 days ago", which step 4.6 kept. I kept p17, so the card and this page agree. `created_at` is `mock_date(-2)`, so the printed date moves with the day the page is opened: "Posted 18 Oct" on 20 Oct, "Posted 19 Oct" on 21 Oct.)*
- **Listing Detail (P11), the preview of states A and B (step 4.7):** nothing is saved in stage F. *I'm interested* and *Withdraw interest* are POST forms; the page calls the Mock-only `mock_send_interest()` / `mock_withdraw_interest()`, which keep the change in the session, then redirects (Post/Redirect/Get). The change shows on every page: `interest_count` gets one more or one less, so the Exchange card, the profile's Listings tab and the detail page agree (listing 8: 2 → 3 → 2). **`?member` puts the mock interests back**, so every demo starts the same; **`?empty` and Create account start with none** (the empty Nimali sees listing 11 with 1 interested, in state A), but interests sent after that stay. Log out and Log in keep them. A visitor's POST goes to Log in with the return address; a POST by the owner, on a closed listing or a second time changes nothing; a POST to an unknown id gets the 404. After Withdraw: *Interest withdrawn. Ruwan no longer sees your request.* (my wording; `?withdrawn=1`, shown only when the member's row really is withdrawn, like Settings' `?saved=`). **Withdraw has no confirmation dialog:** it can be undone at once by showing interest again. To decide in week 5 whether the S6 confirm dialog should ask first. The note is optional, 300 characters at most (`maxlength`, and cut again on the server), and **quoted back in state B as "Your note"** with its line breaks (not on p18, my choice: it reminds the member what was sent). The placeholder is "Hi Ruwan, …" and the hint *Up to 300 characters. Ruwan sees it with your request.* (mine).
- **Listing Detail (P11), privacy (step 4.7):** contact details appear only in state B and only the fields the owner ticked; never on cards, profiles, the Landing page, the Dashboard, for visitors or in state A (checked with curl). State A names the fields but not their values: *Your contact is not shared. You will see Ruwan's email and phone number.* (p18: "…You will see Nimali's."; naming the fields is my addition). **The owner's own view shows which of their own details interested members see** (*Interested members see your:* email, phone), not on p18. A closed listing shows no form and no contact details, even to someone who had shown interest. **A private source plant:** p18 says "otherwise no link"; I went further and left the whole **Comes from** line out for other people, because private plants must never reach them (p19 shows that a listing can be made from a private plant such as Vera). The owner's own description may still name it: that is their text. To decide if the plant's name without a link should show instead.
- **Listing Detail (P11), page and wording (step 4.7):** "See the story", not p18's "See her story" (no he or she); the link has a hidden "of Goldie" for screen readers. **The visitor's card is mine:** *Interested in this plant?*, *Log in to tell Ruwan. You will then see Ruwan's email and phone number.*, the primary **Log in to show interest**, and *New to PlantFolio? Create a free account*. **Log in returns to the listing:** `login.php?next=listing.php?id=8`, accepted only through `return_path()` (exactly `listing.php?id=` and 1 to 10 digits, no leading 0); its intro then says *Log in to show your interest. You will come straight back to the listing.* (mine). The Sign up tab and the header's Log in button do not carry `next` yet (to decide; a new account would come back with an empty garden). **The owner's card:** *This is your listing*, *4 people are interested.* (or *No one is interested yet.*), **Manage in My exchanges** (primary, `my-exchanges.php?listing=7`) and **Edit listing** (`listing-form.php?id=7`); both pages come in week 5 (404 until then). **Closed:** *This listing is closed*; the owner reads *You exchanged this plant with Dinesh Gunasekara.* and **Go to My exchanges**, everyone else *This plant has found a new home, so the listing no longer takes interest.* and **Browse the exchange**. A grey **Closed** badge sits beside the type badge. **404:** *Listing not found* · *This listing does not exist, or its owner has removed it.* · **Browse the exchange**. State B says *Contact Ruwan to arrange the pick-up* for a free listing and *…the swap* otherwise (p18 only shows "the swap"). "Posted 18 Oct · 4 interested" leaves out the interest part at 0, like the card. The distance ("about 5 km from you") is hidden for visitors and for the owner. The scientific name is left out when it equals the common name ("Aloe vera", not "Aloe vera (Aloe vera)"). "Looking for" starts with a capital letter. The owner card has a small label, **Offered by** (not on p18; the Plant Profile's owner card says "Grown by"). **Safe swapping** is a list of three lines with ticks and a link, *Read the community guidelines* (p18: one line with dots, no link). The Swap badge is sage on the white card (white on a photo). *(Review fixes `3dd2532` and `5d33ed3`:)* after **I'm interested**, **Withdraw interest** and **Log in to show interest** the browser now lands on `#summary`, the summary card, instead of `#interest`: on a phone it skips the photos and shows the title, the "N interested" count and the card under them; on a desktop the page only moves past the breadcrumb, so the count that just changed stays in view (my choice; the other idea was a very large desktop `scroll-margin-top` on the card). The disabled **Interest sent** has no tick any more, as on p18 (the green message above it keeps its tick). A note's line breaks now count once on the server, as in the textarea, so a note the textarea accepted is never cut.
- **Listing Detail (P11), layout (step 4.7):** p18 has no phone layout, so the phone order is mine: photos, summary, interest card, About, owner card, tips (the action right after what is offered, then the details). The keyboard follows the same order on every screen size. p23 says inner pages such as a listing hide the bottom bar and show a back button over the photo: that is week 6, as for the Plant Profile. Tablet: the photos full width, then the summary and interest card side by side, and the owner and tips cards side by side. Desktop: 7 : 5 columns in one grid of three rows (step 4.7, commit `e8752df`); when the interest card is shorter than the photos (a visitor) about 68 px stay empty on the right, accepted. Thumbnails are 64 px (80 px from 768 px) and are links that reload the page with `?photo=N#gallery`; week 5's script could swap the photo without a reload. `photo=02` is read as 2 and `id=08` opens listing 8 (both digits only, harmless). *(Review fix `5d33ed3`:)* the title keeps the H2 size (24 px) until 1024 px, because on a tablet the summary card is half the width, and every title is balanced (`text-wrap: balance`, ignored by older browsers), so no line ends with a lone word. **Interest sent** and **Withdraw interest** share one line from a 344 px wide screen up (Withdraw has 8 px side padding instead of 16 px); on a narrower screen Withdraw drops onto its own line. **On a short phone** (375 × 667) the jump to `#summary` leaves state B's Interest sent / Withdraw row under the bottom bar, so a short scroll is needed; week 6 hides the bottom bar on inner pages (p23), which gives that room back. To check then. *(Step 4.8: still open, for week 6.)*
- **Listing Detail (P11), small open questions (step 4.7):** **touch target, for the close-out:** the breadcrumb link "Exchange" is 19 px tall, from the shared `.breadcrumb` rule in `components.css`, the same on the Plant Profile; a fix should change the shared rule. *(Step 4.8: done in the shared rule: 13 px of padding on the inline link gives a 45 px tap area without moving the row, commit `a8e394a`.)* The 404 has no `<h1>` (the empty state's title is an `<h2>`), like the other not-found pages (see the P14 bullet). *(Step 4.8: fixed for all of them, commit `dcc1ccd`.)* In `?empty`, Nimali's own listings (5, 6, 7) answer 404, because a brand-new account has no listings.
- **Close-out (step 4.8), my choices:** a section link gets `aria-current="true"` and only the link to the page itself gets `"page"` (`nav_current()`). The account menu now marks **My public profile** on your own profile and **Settings** on Settings with `aria-current="page"`, which also gives them the menu's highlight, as the Plant Tools items already had. `asset_url()` versions the stylesheets only: icons are printed into the page, and the mock photos are replaced in stage B anyway. `ENT_SUBSTITUTE` changes nothing visible today, because `get_text()` already refuses broken bytes in the address and the mock data is valid; it protects stage B. The ids keep `filter_input(…, FILTER_VALIDATE_INT)`, which was already safe. The header's buttons are 44 px from 768 px up, the member's **Add plant** too; the removable filter chips are 44 px on every screen; every other chip and `.btn-sm` keeps the week 1 rule of 36 px from 768 px (Decisions, item 9). Exchange's empty state says *with these filters* only when a narrowing filter is on (a type, a species, or a distance other than Any). The style guide's results bar prints its count in a `<p>`, not an `<h2>` as on the pages, so the style guide's own headings stay in order.
- **Close-out (step 4.8), found and left for the week 6 responsive pass** (older pages, measured at 375 and 768 px, under the 44 px rule): Plant Profile: **Mark watered** 32 px tall and the Reminders **Add** link 21 px at every width, the ⋯ button 36 × 36 px from 768 px. Dashboard: **All reminders** and **My Garden** links 21 px, the plant names in Needs attention 23 px, the small buttons 36 px from 768 px. My Garden: the grid / list buttons 36 × 36 px at every width, the list view's plant names 24 px, the collection chips 36 px from 768 px. Add plant: the health choices 40 px, the AI **Use** buttons 50 × 36 px at 768 px. Settings: the "Change email" line 21 px, the Save buttons 36 px from 768 px. Log in and Sign up: the tabs 40 px, **Forgot password?** 22 px, the "community guidelines" link 20 px. About: the section chips 36 px from 768 px. Footer: **About** is 42 px wide. Best fixed after Decisions items 8 and 9. In the 192 page views measured, the week 4 pages and the Landing page have none, apart from that footer link. Also still open: the Landing page's Fresh cards cut long usernames at 1024 to 1280 px (Decisions, item 11), and on a short phone the P11 state B buttons sit under the bottom bar after the jump (week 6).
