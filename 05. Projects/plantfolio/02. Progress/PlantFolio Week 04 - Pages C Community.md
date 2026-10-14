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
## Open points
- **Explore (P09), mock data:** Dinesh lives in **Negombo** (p16), while his snake plant listing is in Kuliyapitiya: a listing can be somewhere other than its owner's home town. *Monstera* 'Thai Constellation' has its own species row because p16 prints that name. The owners of Leafy and Holey (cut off on p16), Ruwan's counts, every member's `created_at`, the collections and the six plants after Holey are invented. All monsteras use the Monty placeholder drawing.
- **Explore (P09), page:** "Newest" sorts by plant id (no `created_at` on `plants`). A new search drops the filters (my choice). The species chip shows the common name ("Swiss cheese plant"), where p16 shows "Monstera". The People tab is a placeholder until step 4.4. Nimali appears among the people, and her own plants among the results: p16 shows Monty, so I kept both.
