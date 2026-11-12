---
type: project-note
project: PlantFolio
status: draft
tags: [project, web]
---
# PlantFolio Overnight Backend Build
> [!info] [[PlantFolio - Project Home]] · Stage B · night of 11–12 Nov 2026 · branch `backend-overnight` (14 commits, not pushed)
> Week notes: [[PlantFolio Week 09 - Core Features and Gate 1]] (step 9.1 is the start of this work). In the repo: `REVIEW.md` (start here), `PROGRESS.md`, `DECISIONS.md`.

> [!abstract] Goal
> Ranshitha asked for the whole backend to be built overnight, phase by phase, without stopping for questions: choices go in `DECISIONS.md`, progress in `PROGRESS.md`, the summary in `REVIEW.md`. Rules: commit after each step, test each step, never drop tables or delete her data.

> [!warning] One summary note, not a work log per step
> Decision D3: this note replaces the usual step-by-step entries. Each step below has the facts to turn it into a full work-log entry with "Check yourself" questions later.

## What was built
| Phase | Steps | Commits | In one line |
|---|---|---|---|
| 3 My plants | 3.2 – 3.8 | `dc76d7f` … `126eed0` | Collections, uploads, Add/Edit plant, profile, Story, gallery, delete, reminders (add, Done, Undo), Dashboard and Landing live |
| 4 Exchange | 4.1 – 4.3 | `1a1b178`, `6e0aeaf`, `568969c` | Listings, interests, exchanges, ratings, contact reveal, create/edit/close listing, likes |
| 5 AI care guide | 5 | `742a733` | Ollama writes one guide per species; cached; owner can Regenerate |
| 6 ML (PHP side) | 6 | `60e1f1a` | Identifier and Health Check send photos to Flask, log every result, offline fallback |
| 7 Security | 7 | `44b704d` | Only `public/` served, security headers, errors hidden outside development |

## The idea that carried the whole night
The mock arrays were shaped like database rows ([[00.07 Mock Data Shaped Like Database Rows]]). So each data set got **one query function returning the same keys** (`includes/garden-data.php`, `includes/exchange-data.php`). `mock-data.php` refilled its arrays from them, and every page showed real data **without its HTML changing**. Then each form was wired and its session-only "preview" code deleted.

```php
// mock-data.php after step 3.1: same array name, real rows
$mock_plants = $viewer_id ? garden_plants($viewer_id) : [];
```

## Patterns to study (each one is in the code many times)
- **Ownership inside the SQL.** `UPDATE … WHERE id = :id AND user_id = :user_id`. A hand-made request with someone else's id matches no row, so nothing changes. Rules like "open listing only" and "only the two members rate, once" are also in the SQL ([[03.07 Access Control and Ownership Checks]]).
- **Transactions** for multi-row saves: a new collection and its plants; Add plant (plants + reminders + first Story entry); Mark done (reminder + care line). Either all happen or none ([[03.02 PDO and Prepared Statements]]).
- **`rowCount()` counts changed rows, not matched rows.** Marking the same reminder done twice changes nothing, so ownership is checked with a `SELECT` instead.
- **Two cascade paths:** deleting a member needs `rater_id` set to NULL first ([[03.01 MySQL Schema - Types, Keys and Foreign Key Rules]], warning callout).
- **Uploads:** type from the file's bytes (`finfo`), not its name; random names; a second lock with `.htaccess` in the uploads folder ([[04.02 Secure File Uploads in PHP]]).
- **Post/Redirect/Get + toast in the session** for every form, including Undo ([[03.06 Form Handling - Validation, Redirects and Flash Messages]]).
- **Privacy in the query:** other members' plants are read `WHERE is_public = 1`, and contact details only through `listing_contact()`.
- **External services never break the site:** Ollama and Flask are called with cURL and time limits; no answer → a friendly state, nothing saved.

## Problems found during the night
1. **Apache served the whole repository**: `.git` (all source and history), `seed.sql`, `CLAUDE.md`. Fixed with a root `.htaccess` that answers 404 outside `public/`.
2. **The page sweep could not see PHP warnings**: PHP prints `<b>Warning</b>:`, and the test searched for `Warning:`. Once fixed it found 15 warnings on the Landing page (its collage read the logged-in member's data, empty for visitors) and 2 on the Plant Profile. Both fixed, and every test rerun.
3. **My sweep changed demo data**: opening Nimali's My Exchanges marks interests as seen. Her 4 new interests were set back, and the sweep now opens another tab.
4. **Apache and MySQL had stopped** mid-way (restart); started again with XAMPP's own commands.
5. **Git Bash path conversion** turned `//` at the start of an argument into `/`, which dropped a character in two files during scripted edits. Caught by `php -l` and fixed.

## What is left (from REVIEW.md)
- [x] Switch GD on (php.ini line 931) and get the LLM model (`ollama pull llama3.2`, or `llm_model` = `llama3:latest`). Done 2026-11-12: GD on in Apache, `llm_model` = `llama3:latest`; care guide generated through the site
- [ ] Test the care guide in a browser, a full plant's life and a full swap, on a phone
- [ ] Flask service and trained models (ML-01, ML-03 – ML-10); AI suggestion on Add plant (ML-13)
- [ ] Collection rename and privacy (no UI yet)
- [ ] Pages call the data functions directly; delete `mock-data.php`
- [ ] Turn this note into work-log entries with "Check yourself" questions

## Check yourself
1. Why could the pages show real data before any page file was changed?
2. A member sends `plant=42` (Nimali's Monty) to Delete plant. What stops it?
3. Why does the care-guide endpoint read the species from the plant instead of taking it from the request?

> [!success]- Answers
> 1. Every query function returns rows with exactly the keys of the mock array it replaces, and `mock-data.php` refills those arrays from the database. The pages read the same array names as before.
> 2. `DELETE FROM plants WHERE id = :id AND user_id = :user_id`: with the member's own id from the session, no row matches, so nothing is deleted.
> 3. So nobody can use the endpoint to make the LLM write about anything they type. Reading it from a plant the viewer may see also keeps private plants private (404).

---

## After the night: review fixes

### Fix 1: Add plant said nothing when it refused a save

> [!important] Goal
> CORE-16 (Add / Edit plant, wired). When the form refuses a save, the member must see that it was not saved and why.

**What was done:** 2026-11-12, branch `backend-overnight`, commit `2509a4b`. Only `public/plant-form.php` changed.

**What happened:** I added a plant with "How many? 2" and pressed Save twice. Nothing seemed to happen. The Apache access log showed both POSTs answered `200` (the form again) instead of `302` (redirect to My Garden), so the server had refused the form. Comparing page sizes with test cases pointed to a photo error, most likely "Use a JPG or PNG photo." A phone photo can be HEIC inside but named `.jpg`. The browser check in `app.js` trusts the name (`file.type`), and the server's `finfo` reads the real bytes.

**Why it looked like nothing happened:**
- The only message was small red text under the photo box, with no notice at the top of the form.
- Browsers never refill a file field after a reload (security), so the photo box went empty and the page looked like a fresh form.

**Why this way:** I reused the Create listing pattern (`alert alert-danger form-errors`), so both forms behave the same. The notice sits at the top of the form because a reloaded page opens at the top. This needs no JavaScript, so it also works without it.

```php
<?php if ($add_errors): ?>
  <p class="alert alert-danger form-errors" role="alert"><?= icon('triangle-alert') ?>
    <span><?= $is_edit ? 'Your changes were not saved.' : 'Your plant was not saved.' ?>
      Please check <?= e(count_words(count($add_errors), 'field', 'fields')) ?> below.<?= $photo_sent ? ' Then choose your photo again.' : '' ?></span></p>
<?php endif; ?>
```

`$photo_sent` is true when `$_FILES['photo']['error']` is anything but `UPLOAD_ERR_NO_FILE`, meaning a photo was chosen, even a refused one.

**Tested (as Nimali, with curl):** a text file named `.jpg` and a 6 MB file are both refused, and the notice adds "Then choose your photo again". An empty nickname shows the notice without the photo sentence. A good JPG with "How many? 2" saves 2 plants and redirects. No PHP warnings. Test plants and the throwaway member `test.twin` were deleted afterwards.

> [!tip] What to remember
> A status code tells you what the server decided: **302 after a POST = saved and redirected; 200 = the form came back with errors.** Check the access log before guessing. And the server must always check the file's real content, because the browser's check only reads the name.

**Check yourself**
1. Why did the photo box look empty after the failed save?
2. The browser check in `app.js` accepted the photo, but the server refused it. How can both be right?
3. How did the Apache access log show that the form was refused, not lost?

> [!success]- Answers
> 1. Browsers never put a file back into `<input type="file">` after a page load, so no page can quietly upload a file from your computer. The member has to choose the photo again, and the notice now says so.
> 2. The browser's `file.type` comes from the file name. `finfo` reads the file's bytes, so a HEIC photo renamed `.jpg` passes the first check and fails the second.
> 3. Each POST to `plant-form.php` got `200`, so the page was drawn again. A successful save answers `302` with a redirect to `garden.php`.

### Fix 2: a portrait photo stretched its card

> [!important] Goal
> CORE-14 (My Garden). Every card photo stays 4:3, whatever the shape of the uploaded photo.

**What was done:** 2026-11-12, branch `backend-overnight`, commit `2b63eac`. Only `public/assets/css/components.css` changed.

**What happened:** after adding two Monstera plants, their stack card in My Garden was much taller than the My1stPlant card, which stretched to the same height with an empty white area. The Monstera photo is portrait (683 × 1024) and My1stPlant's is landscape (625 × 350). A test page with a single card holding the same portrait photo broke the same way, so the stack was not the cause: the shared `.card-media` rule was.

**Why it happens:** `aspect-ratio: 4 / 3` gives a box a *preferred* height. But a box with a preferred ratio also gets an **automatic minimum size**: it is never shorter than its content. A tall photo is tall content, so the box grew to fit it. A landscape photo is shorter than the 4:3 box, which is why it never showed up before.

```css
.card-media {
  position: relative;
  aspect-ratio: 4 / 3;
  min-height: 0;   /* the box may be shorter than the photo, so object-fit: cover crops it */
}
```

**Checked the other photo frames:** the Plant Profile and Listing photos put `aspect-ratio` on the `<img>` itself, and an image follows it fine. The drop-area preview is `position: absolute`. The Create listing picker squares stayed square. Only `.card-media` needed the fix.

**Problems and fixes:** the first screenshot after the fix looked unchanged because headless Edge used the old CSS from its cache. A fresh browser profile showed the fix working. Real pages avoid this, because `asset_url()` adds `?v=<file time>` to every CSS link.

> [!tip] What to remember
> `aspect-ratio` on a **container** needs `min-height: 0` (or `overflow: hidden`) if its content can be taller than the ratio. On a **replaced element** like `<img>`, it just works. Always test cards with both a portrait and a landscape photo.

**Check yourself**
1. Why did the bug appear only with the Monstera photo?
2. Why does `.plant-photo` on the Plant Profile not need `min-height: 0`?
3. Why do members not need to clear their browser cache to get the fixed CSS?

> [!success]- Answers
> 1. It is portrait: at card width it is taller than a 4:3 box, and the box's automatic minimum size made it grow to fit. A landscape photo is shorter than the box, so nothing grew.
> 2. There the `aspect-ratio` is on the `<img>` itself. An image is a replaced element, so it takes the 4:3 size and `object-fit: cover` crops its picture. The minimum-size rule only applies to boxes that hold content.
> 3. `asset_url()` adds `?v=` plus the file's modification time to the CSS link. Editing the file changes the address, so the browser downloads it again.

### Change 3: All plants stacks by species

> [!important] Goal
> CORE-14 (My Garden). My decision: in **All plants**, plants of the same species share one stack whatever their collection. Inside a collection, a stack holds only that collection's plants (as before).

**What was done:** 2026-11-12, branch `backend-overnight`, commit `77f76a6`. Changed `public/garden.php`, `partials/plant-stack.php` and `public/assets/css/garden.css`.

**Why:** I had two Monsteras, Monty in Living Room and Monty 2 in no collection. All plants showed them as two separate cards, because the stack key was *species + collection*. All plants shows the whole garden, so it should group by kind of plant.

**Why this way:** a stack is only a **display grouping**, worked out in PHP over the query's rows. Each plant keeps its own row, health and reminders. So the grouping can differ per tab with no database change. Only the key changes:

```php
if ($plant['species_id'] === null) {
    $key = null;                                   // typed species: never stacked
} elseif ($open_collection === null) {
    $key = (string) $plant['species_id'];          // All plants: by species
} else {
    $key = $plant['species_id'] . '-' . ($plant['collection_id'] ?? 'none');   // inside a collection
}
```

Three details for stacks that mix collections (in `plant-stack.php`):
- **The card names a collection only when every plant is in it.** `array_unique()` over the collection ids leaves one value when they all match. "Living Room" on a stack that includes Monty 2 would be wrong.
- **Each popup row says where its plant is** in All plants ("Living Room", "No collection"), in small grey text (`.stack-row-collection`).
- **"Choose plants" only inside a collection tab.** It adds more of this species *to that collection*, and All plants has no collection to add to.

**Tested:** a throwaway member `test.mixed` was set up like my garden. All plants showed one ×2 stack with the two row labels and no Choose plants. Living Room showed Monty alone. Nimali's Snake plant stack was unchanged, with Choose plants still in her Living room tab. No PHP warnings. Checked with a screenshot, then deleted the test member, which also removed its plants and collection.

> [!tip] What to remember
> Grouping for display belongs in the page logic, not in the database. The same rows can be grouped differently per view by changing one key.

**Check yourself**
1. Why did changing how plants stack need no change to the database?
2. Why does a stack in All plants sometimes show no collection name?
3. Why is "Choose plants" hidden in All plants?

> [!success]- Answers
> 1. Stacking happens in PHP after the query: plants with the same key go into one `$grid_items` entry. Each plant is still its own row in `plants`.
> 2. The stack may hold plants from different collections. One name would be wrong for some of them, so the card shows a name only when all plants share it. The popup rows show each plant's own collection.
> 3. It adds more of this species to the open collection. In All plants there is no open collection to add them to.
