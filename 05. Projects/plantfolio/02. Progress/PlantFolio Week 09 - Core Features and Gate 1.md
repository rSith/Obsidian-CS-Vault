---
type: project-note
project: PlantFolio
status: draft
tags: [project, web]
---
# PlantFolio Week 09 — Core Features and Gate 1
> [!info] [[PlantFolio - Project Home]] · Stage B · 30 Nov – 6 Dec 2026 · ends with **Gate 1**
> **Study notes:** [[04.00 Core Features]] · **Revision:** [[04.99 Summary - Core Features]]

> [!abstract] Week goal
> The member's own features work from the database: collections, plants with photos, the health story and care reminders.

> [!warning] Before CORE-17: switch on the GD extension
> On 8 Oct 2026 this computer's `C:\xampp\php\php.ini` had `;extension=gd`, so PHP's image library was not loaded. Resizing uploads needs it. Remove the semicolon, restart Apache, and add the step to the README for the team. See [[04.02 Secure File Uploads in PHP]].

## Tasks
Replace one mock array at a time with a query function that returns the same shape, then delete it from `mock-data.php`.

- [ ] **CORE-13** Collections: create, rename, delete, privacy → [[04.01 CRUD in Plain PHP]]
- [ ] **CORE-14 / CORE-15** My Garden from the database, with search, filter and sort (reading wired, step 9.1; saving comes with 3.2–3.7)
- [ ] **CORE-16** Plant create and edit
- [ ] **CORE-17** Secure upload handler `includes/upload.php` → [[04.02 Secure File Uploads in PHP]]
- [ ] **CORE-18** Plant profile data
- [ ] **CORE-19** Health logs and the plant story
- [ ] **CORE-20** Delete a plant together with its files
- [ ] **CORE-21** Public view rules → [[03.07 Access Control and Ownership Checks]]
- [ ] **CORE-22** Reminders → [[04.03 Dates, Reminders and Due Logic]]
- [ ] **CORE-23** Mark done and Undo
- [ ] **CORE-24 / CORE-25** Dashboard data and empty states
- [ ] **CORE-27** Live counts on the landing page → [[04.05 Counts, Averages and Rating Rules in SQL]]
- [ ] **CORE-28** Phase 1 test pass
- [ ] **CORE-29** Peer reviews of MOD-01 – MOD-06

## Gate 1 demo
- [ ] Register → add a plant with a photo → log a health update → the reminder appears on the Dashboard → mark it done
- [ ] Tag `v0.8-gate1`

## Security checks for this week
- [ ] Ownership is checked before every edit and delete (try another user's ID in the address)
- [ ] A private plant returns Not found to other people
- [ ] Uploads: type checked with `finfo`, 5 MB limit, random file names, resized to 1600 px

---
## Work log
Step sections are added here as each step is finished.

> [!info] Phase 3 plan (11 Nov 2026)
> Roadmap dates no longer apply: the work goes phase by phase. Phase 3 ("My plants") steps: **3.1** read the garden from the database · 3.2 collections save (CORE-13) · 3.3 secure uploads (CORE-17, + Settings avatar) · 3.4 add/edit plant (CORE-16) · 3.5 plant profile read (CORE-18, 21) · 3.6 add to story, delete plant (CORE-19, 20) · 3.7 reminders add/done/undo (CORE-22, 23) · 3.8 Dashboard and Landing counts, test pass (CORE-24/25/27/28). Step numbers below are 9.N for step 3.N.

### Step 9.1 — The garden comes from the database

> [!important] Goal
> **CORE-14, CORE-15, CORE-24 (reading).** Every page shows the logged-in member's real plants, collections and reminders, and other members' real public plants, without any page being rewritten.

**What was done** (11 Nov 2026, branch `feature/accounts`, commit `9863492`)
- New `includes/garden-data.php`, one prepared statement per data set, each returning rows with **exactly the keys of the mock array** it replaces: `garden_plants()`, `other_public_plants()`, `garden_collections()`, `garden_reminders()`, `due_reminders()`, `species_list()`, `like_counts()`, `public_members()`, `garden_counts()`.
- In `mock-data.php`, right after the mock arrays are written, eight arrays are **refilled from those functions** for whoever is logged in (`$mock_plants`, `$mock_collections`, `$mock_plant_reminders`, `$mock_reminders`, `$mock_species`, `$mock_community_plants`, `$mock_like_counts`, `$mock_users`). The session "preview" code for unsaved forms still lies on top until steps 3.2–3.7.
- `$current_user`'s plant and collection counts are now real (Nimali: 12 plants, not the typed 20). The Phase 2 bridge no longer empties a real member's garden.

**Why this way** (Ranshitha agreed to the approach)
- `$mock_plants` is read by **10 pages and partials**. Swapping page by page would have made pages disagree (a new plant on My Garden but not on the Dashboard). Swapping the data source once keeps every page in step, which is what "mock data = future database rows" promised ([[00.07 Mock Data Shaped Like Database Rows]], callout).
- **Privacy in the query, not the page:** `other_public_plants()` has `WHERE p.is_public = 1`, so a private plant cannot leak through any page that forgets to check. Public counts for other people are counted from public rows only.

```sql
-- the soonest reminder of each plant, one row per plant (garden_plants)
LEFT JOIN (SELECT plant_id, task, interval_days, next_due,
                  ROW_NUMBER() OVER (PARTITION BY plant_id ORDER BY next_due, id) AS rn
           FROM care_reminders) r ON r.plant_id = p.id AND r.rn = 1
```
`ROW_NUMBER()` is a window function ([[06.02 SQL Joins, Aggregation and Indexes]]); the cover photos use `GROUP_CONCAT` ([[04.05 Counts, Averages and Rating Rules in SQL]], callout).

**How it was checked**
- A script compared every mock array with the query rows for Nimali: no key missing, every plant value equal (Monty's `last_logged_at` identical). Real differences only where the mock had typed numbers: 14 reminders (Stripes and Slim's from the seed), 4 due today (Stripes overdue), Balcony 1 plant (not 6).
- 28 pages as a visitor, as Nimali and as a new member: no PHP errors. Visitors get 404 for Vera and Penny (private).
- A new member got two plants by SQL (one public, one private with a typed species "Jade plant (probably)") and a reminder: they appeared on My Garden, Today's care, their Public Profile, People, and Community (page 2 of 20, and by search). The private one: 404 for a visitor and for Nimali.

**Problems and fixes**
- **The Phase 2 bridge would have hidden real plants.** It emptied `$mock_plants` for every member except Nimali, and runs after the swap. Changed so only the `?empty=1` preview empties.
- **A new member's public plant had no owner.** Community and the Plant Profile look the owner up in `$mock_users`, which knew only the 5 sample members, so the plant dropped out. Fixed by filling `$mock_users` from the database too (`public_members()`); the old hand-built row for new members was removed, because it would have overwritten the real counts with 0.
- Two false alarms from my tests: the Dashboard's empty state uses the class `panel-empty`, not `empty-state`; and Pebbles was missing from Community only because it is the 20th plant, on page 2.

> [!tip] What to remember
> When one array feeds many pages, swap the **source** (one place), not the pages. Prove the swap with a shape check (same keys, same values) before looking at any page.

**Check yourself**
1. Why was `$mock_plants` refilled inside `mock-data.php` instead of changing `garden.php`?
2. Why does `other_public_plants()` filter `is_public = 1` in SQL, when the pages also check it?
3. What does `ROW_NUMBER() OVER (PARTITION BY plant_id ORDER BY next_due)` give, and why `rn = 1`?

> [!success]- Answers
> 1. Ten pages and partials read it. Changing only My Garden would make the Dashboard, Plant Profile and others show different data. Refilling the array once changes them all together.
> 2. Defence in depth: the database never hands a private plant to code that might forget the check. Privacy rules are safest where the data is read.
> 3. It numbers each plant's reminders 1, 2, 3 … from the soonest. Keeping `rn = 1` gives exactly one row per plant, its soonest reminder, which the plant card shows as "Water today".
