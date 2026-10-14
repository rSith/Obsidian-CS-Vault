---
type: project-note
project: PlantFolio
status: draft
tags: [project, web]
---
# PlantFolio Week 02 — Pages A: First Impressions
> [!info] [[PlantFolio - Project Home]] · Stage F · 12–18 Oct 2026 · Landing: module MOD-10, branch `feature/landing-ui`
> **Study notes:** [[01.00 Building Pages]] · **Revision:** [[01.99 Summary - Building Pages]]

> [!abstract] Week goal
> The first pages a visitor meets, built to match their wireframes with mock data: Landing, Sign up and Log in, Dashboard, and the empty-garden state.

## Tasks
| Page | Task | Notes | Module |
|---|---|---|---|
| P01 Landing | CORE-27 | Hero, community collage, How it works in 3 steps, smart-tools teaser; counts from mock data | MOD-10 |
| P02 Sign up · Log in | CORE-07, CORE-09 (UI) | One page, two tabs; password meter; optional location; community panel | MOD-03 |
| P03 Dashboard | CORE-24 (UI) | Greeting and counts, Today's care (due = terracotta, overdue = red), Needs attention, Quick actions, Garden at a glance | MOD-06 |
| S1 Empty garden | CORE-25 (UI) | Shown on Dashboard and My Garden when the mock user has no plants | MOD-06 |

- [ ] P01 Landing (`index.php`)
- [ ] P02 Sign up and Log in (`register.php`, `login.php`)
- [ ] P03 Dashboard (`dashboard.php`)
- [ ] S1 Empty garden (`partials/empty-state.php`)
- [ ] Mark DES-05 Done: the Figma link is already in the README
- [ ] Finish DES-06, DES-07, DES-08 and DES-13 (ERD, schema reconciliation, data dictionary, species data)

## Learn this week
- Semantic HTML → [[00.06 Semantic HTML]]
- Page layout with grid → [[07.01 CSS Layout with Flexbox and Grid]] · [[01.02 Card Grids, Images and Sticky Bars]]
- Forms and labels → [[00.05 HTML Forms]] · [[00.09 Styling Forms with CSS]]
- The page pattern and navigation → [[01.01 Page Skeleton - Header, Footer and Navigation]]
- Showing a different page for an empty garden → [[01.04 Rendering States - Empty, Owner and Visitor Views]]

## Checks for every page
- [ ] Matches its wireframe page and the Figma prototype
- [ ] Works at 375, 768 and 1024 px with no horizontal scroll and no console errors
- [ ] All output escaped with `e()`, images have alt text, inputs have labels

---
## Work log
Step sections are added here as each step is finished.

### Step 2.1 — Mock data for the Landing page
> [!important] Goal (CORE-27, UI half)
> Give the Landing page (P01, wireframe p8) the data it needs before any HTML is written: the member count, the avatar row, the collage and four listings.

**What was done (2026-10-14, branch `feature/landing-ui`, commit `7a40ed1`)**
- Pull request #2 was already merged into `develop` (merge commit `acd6b04`). Checked first: every PHP file passes `php -l`, and the home page, `?guest`, `about.php` and `styleguide.php` load with no PHP errors. `feature/landing-ui` was then cut from `develop`.
- `includes/mock-data.php`, a new "Landing page" section:
  - `$mock_site_stats`: `member_count` 1248 (shown as "1,200+") and `open_listing_count` 37.
  - `$mock_recent_members`: the hero's avatar row AM · DG · RR · KJ (`@aroid.amaya`, `@dinesh.greens`, `@ruwan.roots`, `@kandy.jungle`).
  - `$mock_featured_plant`: Amaya's public plant Pip (*Pilea peperomioides*) with `like_count` 48, for the second collage card.
  - `$mock_prediction`: one `ml_predictions` row, Snake plant at 0.96, for the AI chip.
  - A fourth listing, "Tomato seedlings (6)", free, from `@kandy.jungle`, put at the **top** of `$mock_listings` because it is the newest.
- `public/assets/img/mock/pip.svg`: a placeholder drawing in the style of the others.
- Icons `camera`, `heart` and `arrow-right` downloaded from `lucide-static` 1.52.0 and added to `docs/credits.md`.
- `public/styleguide.php`: the sample member is now found **by id** (listing 7, Nimali's) instead of "the first listing".

**Why this way**
- **Counts are data, not text.** Wireframe p8 says the headline numbers are "read live from the database, never hard-coded". The page will print `$mock_site_stats['member_count']`; in stage B one `SELECT COUNT(*) FROM users` replaces the array.
- **Every array is shaped like a future query result**, with the real column names from `database/README.md` plus joined columns (`username` from `users`, `common_name` from `plant_species`) and calculated ones (`like_count`). A comment above each array names the query that will replace it.
- **The samples follow `docs/ROADMAP.md`.** Its "Other members" list already has `@aroid.amaya` and `@kandy.jungle`, and "Tomato seedlings (6)" is one of its sample listings. A first draft invented a member `@kavindi.j` and a different listing; both were changed to the roadmap's samples.
- **Newest first.** The array order copies `ORDER BY created_at DESC`, so "the four newest" is simply the first four rows.

```php
// Stage B: SELECT COUNT(*) FROM users · SELECT COUNT(*) FROM exchange_listings WHERE status = 'open'
$mock_site_stats = [
    'member_count'       => 1248,   // shown rounded down: "1,200+"
    'open_listing_count' => 37,
];
```
```php
// Found by id (Nimali's listing, 7), so adding listings to the mock data never changes the samples.
$sample_member = $mock_listings[array_search(7, array_column($mock_listings, 'id'))];
```

**Problems and fixes**
- **The style guide's sample member changed by accident.** It used `$mock_listings[0]`, and the new listing became row 0, so the samples showed Kavindi instead of Nimali. Fixed by looking the row up by its `id`: `array_column()` makes a list of all ids, `array_search()` finds the position of 7.

> [!tip] What to remember
> - A position like `[0]` breaks when the data changes; an `id` does not.
> - Invented sample values must be listed so they can be explained: 1,248 members, 37 open listings, Amaya's full name and Pip, Kavindi's full name, the 96% prediction.

**Check yourself**
1. Why does the Landing page read the member count from an array instead of typing "1,200+" into the HTML?
2. What does `$mock_featured_plant['like_count']` become in stage B?
3. Why was the new listing put first in `$mock_listings`?

> [!success]- Answers
> 1. The wireframe requires live counts. With the number in an array, stage B only swaps the array for a `COUNT(*)` query; the HTML stays the same.
> 2. A calculated column: `COUNT(*)` of the rows in `plant_likes` for that plant, joined into the plant query.
> 3. The array imitates `ORDER BY created_at DESC`. It was posted yesterday, the newest, so it belongs first, and "Fresh on the exchange" can take the first four rows.

Lessons: [[00.07 Mock Data Shaped Like Database Rows]] · [[04.05 Counts, Averages and Rating Rules in SQL]]

---
### Step 2.2 — Landing hero and community collage
> [!important] Goal (CORE-27, UI half)
> Build the top of the Landing page (wireframe p8, elements 2 and 3): the value proposition and the collage that previews the three core features.

**What was done (2026-10-14, branch `feature/landing-ui`, commit `777f2a0`)**
- `public/index.php` rewritten from the week-1 placeholder into the real page: tag "Plant care & exchange community", headline *Where your plants tell their story*, one sentence, **Start your garden, it's free** (the only filled button) and **Explore plants**, then the avatar row with "Join **1,200+** plant lovers".
- The collage: Monty's card with his *Thriving* badge, the chip "Water Monty today", the chip "Identified: Snake plant · 96%", and Amaya's card with ♡ 48.
- `public/assets/css/landing.css` (new), loaded through `$page_css`.
- Checked by screenshot at 375, 768 and 1280 px, as a visitor (`?guest`); no PHP errors.

**Why this way**
- **The count is rounded down in PHP**, `intdiv(1248, 100) * 100` gives 1200, printed as "1,200+". Rounding down keeps the claim true; `number_format()` adds the comma.
- **The collage is made of real data and real components** (`.card`, the status badge partial, the avatar partial), not a picture. When the mock rows change, the collage changes too.
- **The collage is `aria-hidden="true"` and has no links.** It is a decoration that repeats what "How it works" says in words. A screen reader would otherwise read a jumble of floating labels. Hidden content must never contain links, or keyboard users would land on something invisible to their screen reader.
- **Positions in percentages on a fixed-shape stage** (`aspect-ratio: 11 / 10`). On a phone the whole picture shrinks evenly instead of falling apart.
- **The chip's droplet uses the care colours**: terracotta when due today, red when overdue, worked out by `care_due()`. The words follow the date too, so the chip can never say "today" on the wrong day.
- **Mobile-first title size.** 46 px fits only two words per line on a phone, so the title starts at the H1 size (32 px) and grows to 46 px from 768 px. `max-width: 8.5em` makes it break into three lines on desktop, as on p8.
- **Members see this page for now.** Wireframe p8 says signed-in members skip it, but `dashboard.php` does not exist yet. The redirect is written as a comment at the top of the file for stage B.

```php
// "1,200+": rounded down to the hundred, so the claim stays true however many members there are.
$member_text = $member_count >= 100
    ? number_format(intdiv($member_count, 100) * 100) . '+'
    : number_format($member_count);
```
```css
.collage {
  position: relative;
  aspect-ratio: 11 / 10;   /* a fixed shape: children are placed in % of it */
}
.collage-card-main { top: 0; left: 4%; width: 60%; transform: rotate(-2deg); }
```

**Problems and fixes**
- **The title broke into two lines on desktop**, not three as on p8. Fixed with `max-width: 8.5em` from 768 px; `em` follows the font size, so the width means "about 8 big letters".
- **`@aroid.amaya` was cut to "@aroid.am…"** on Amaya's card. Fixed on tablet and desktop by making the card wider (50%) and the text smaller. On a 375 px phone it still shortens with "…", which `text-overflow: ellipsis` does on purpose instead of breaking the layout.
- **The reminder chip first had the word "today" typed into the HTML.** Changed so the words come from `care_due()`.

> [!tip] What to remember
> - `aria-hidden="true"` hides decoration from screen readers; never put a link inside it.
> - Absolute positions in `%` inside a box with `aspect-ratio` scale as one picture.

**Check yourself**
1. Why does the collage not use the `plant-card` partial?
2. Why is "1,200+" rounded **down** and worked out in PHP?
3. What will the redirect at the top of `index.php` do in stage B, and why is it only a comment now?

> [!success]- Answers
> 1. The partial contains a link to the plant's profile. Inside an `aria-hidden` area a link would be reachable by keyboard but silent to a screen reader. The collage copies the card's classes without the link.
> 2. Down, so the number on the page is never more than the truth. In PHP, because the real count comes from the database and changes; the HTML just prints the result.
> 3. It sends a signed-in member to `dashboard.php` with `header('Location: …')` and stops the page with `exit`. Today there is no session and no Dashboard, so the redirect would lead to "Not Found".

Lessons: [[01.01 Page Skeleton - Header, Footer and Navigation]] · [[01.02 Card Grids, Images and Sticky Bars]] · [[07.01 CSS Layout with Flexbox and Grid]]

---
