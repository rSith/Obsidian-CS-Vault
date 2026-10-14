---
type: project-note
project: PlantFolio
status: draft
tags: [project, web]
---
# PlantFolio Week 02 — Pages A: First Impressions
> [!info] [[PlantFolio - Project Home]] · Stage F · 12–18 Oct 2026 · Landing: module MOD-10, branch `feature/landing-ui` (PR #3, merged) · Sign up / Log in: module MOD-03, branch `feature/accounts-ui`
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

- [x] P01 Landing (`index.php`), **CORE-27**. *UI built on 14 Oct 2026 (steps 2.1–2.4), on `feature/landing-ui`, not pushed. Still to check by hand: hover, keyboard focus and a real phone. Becomes Done in stage B, when the counts are live.*
- [x] P02 Sign up and Log in (`register.php`, `login.php`), **CORE-07, CORE-09**. *UI built on 14 Oct 2026 (steps 2.5–2.7), on `feature/accounts-ui`, not pushed. Still to check by hand: hover, keyboard focus and a real phone. Becomes Done in stage B, when sign-up and log-in work.*
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
### Step 2.3 — How it works and the smart tools teaser
> [!important] Goal (CORE-27, UI half)
> Build wireframe p8 elements 4 and 5: three steps that mirror the product, and an introduction to the two AI tools "as helpers, not gimmicks".

**What was done (2026-10-14, branch `feature/landing-ui`, commit `b6cac49`)**
- **How it works** (`id="how-it-works"`, the target of the header link): label, the H2 *Three simple habits for happier plants*, and three cream cards on a white band: 1 · Add your plants (camera), 2 · Track their care (droplet), 3 · Share and swap (repeat). Texts from p8.
- **Smart tools**, a sage band: *A second opinion when you need one*, the rule in words ("They suggest, you decide: nothing is saved until you press Save, and when the model is not sure, it says so"), and two cards, Plant Identifier and Health Check. Each shows a sample result with the shared `confidence-meter` partial and a link "Try …".
- `includes/mock-data.php`: `$mock_prediction` became **`$mock_predictions`**, two `ml_predictions` rows: Sergeant's species result (Snake plant, 0.96) and Tommy's disease result (`Tomato___Early_blight`, 0.92, as in the roadmap samples).
- `includes/helpers.php`: new **`prediction_name()`**.

**Why this way**
- **The steps are an `<ol>`**, an ordered list, because the order is the point: add, then track, then swap. A screen reader announces "list, 3 items". The numbers are printed in the titles (as on p8), so the list's own numbers are switched off with `list-style: none`.
- **The steps live in a PHP array** and one `foreach` prints them, so all three cards share exactly the same markup.
- **The teaser shows real-looking results, not promises.** A confidence bar with the fixed word (High) shows the AI as a helper that tells you how sure it is. That is the wireframe's "helpers, not gimmicks" and the design rule "AI suggests, the user decides".
- **`prediction_name()`**: the ML contract (`ml-service/README.md`) returns disease classes like `Tomato___Early_blight`. People should read "Early blight". The helper is in `helpers.php` because P08 Health Check needs it too.
- **Section colours alternate** cream → white → sage, so each part reads as its own block without extra lines or headings.
- The links "Try Plant Identifier" are text links, not buttons: the design system allows **one primary button per area**, and the page's one is "Start your garden".

```php
function prediction_name($prediction)
{
    if ($prediction['type'] === 'species') {
        return $prediction['common_name'] ?? $prediction['predicted_label'];
    }
    $parts = explode('___', $prediction['predicted_label']);   // ['Tomato', 'Early_blight']
    return ucfirst(strtolower(str_replace('_', ' ', end($parts))));   // "Early blight"
}
```

> [!tip] What to remember
> - Use `<ol>` when order matters, `<ul>` when it does not.
> - A model's raw class name is data; the words people read are made by a helper, in one place.

**Check yourself**
1. Why are the three steps an `<ol>` and not three `<div>`s?
2. What does `prediction_name()` return for `Tomato___healthy`, and why does that matter?
3. Why are "Try Plant Identifier" and "Try Health Check" links and not green buttons?

> [!success]- Answers
> 1. They are a sequence. `<ol>` tells the browser and screen readers it is an ordered list of three items; `<div>`s carry no meaning.
> 2. "Healthy". The page can show any class the model returns without a hand-written list of names.
> 3. One primary button per area. The Landing page's one goal is sign-up, "Start your garden"; extra filled buttons would compete with it.

Lessons: [[00.06 Semantic HTML]] · [[05.07 Confidence Thresholds and Honest AI Results]]

---
### Step 2.4 — Fresh on the exchange, and the full-page check
> [!important] Goal (CORE-27, UI half)
> Finish the page with the section the wireframe's UX notes put below the fold, then check the whole page at every width.

**What was done (2026-10-14, branch `feature/landing-ui`, commit `8bd5f36`)**
- **Fresh on the exchange**: label, the H2 *Plants looking for a new home*, a link "See all 37 listings" (the count from `$mock_site_stats`) and the four newest listings, each printed by the `listing-card` partial from week 1.
- Grid: 1 column on a phone, 2 on a tablet, 4 on a desktop, the breakpoints of wireframe p23.
- **Full check**: screenshots at 375, 768 and 1280 px; page width measured at 375, 768, 1024 and 1280 px, as a visitor and as a member: no horizontal scroll anywhere, no PHP warnings.

**Why this way**
- **`array_slice($mock_listings, 0, 4)`** takes the first four rows. Because the mock array is newest first, those are the four newest, the same rows `ORDER BY created_at DESC LIMIT 4` will return in stage B.
- **The partial is reused unchanged.** The Exchange page (P10) will print the same cards, so a fix to the card fixes both pages.
- **"See all" is a text link**, again because "Start your garden" is the page's one primary button.

```php
// Stage B: … WHERE status = 'open' ORDER BY created_at DESC LIMIT 4. The mock array is already newest first.
$fresh_listings = array_slice($mock_listings, 0, 4);
```
```php
<?php foreach ($fresh_listings as $listing): ?>
  <?php partial('listing-card', ['listing' => $listing]); ?>
<?php endforeach; ?>
```

**Problems and fixes**
- **How to test "no horizontal scroll" at a true 375 px.** A headless browser window cannot be narrower than about 500 px. A temporary HTML file loaded the page in iframes of each width and compared `scrollWidth` with `clientWidth`; equal numbers mean nothing sticks out. The file was deleted afterwards.

> [!tip] What to remember
> - `array_slice()` does in PHP what `LIMIT` does in SQL.
> - "No horizontal scroll" can be measured: the page's `scrollWidth` must equal its `clientWidth`.

**Check yourself**
1. What will replace `array_slice($mock_listings, 0, 4)` in stage B?
2. Why does the Landing page not have its own listing-card markup?
3. Name the three things still to check by hand before the page is called finished.

> [!success]- Answers
> 1. A query on `exchange_listings` joined with `users`, `WHERE status = 'open' ORDER BY created_at DESC LIMIT 4`.
> 2. It uses the shared `listing-card` partial, so the card looks and behaves the same on the Landing page and on the Exchange page, and a change happens in one file.
> 3. Hover states, keyboard focus (Tab through the page) and a real phone.

Lessons: [[01.02 Card Grids, Images and Sticky Bars]] · [[00.03 PHP Includes and Partials]]

---
### Step 2.5 — Shared pieces for Sign up and Log in
> [!important] Goal (CORE-07 and CORE-09, UI half)
> Build what `register.php` and `login.php` share before building either page: the tabs, the community panel, the layout, and a quieter top bar.

**What was done (2026-10-14, branch `feature/accounts-ui`, commit `2d37c72`)**
- Pull request #3 (Landing) merged into `develop` by Ranshitha (merge commit `9b7db4b`); `feature/accounts-ui` cut from the updated `develop`.
- `includes/header.php`: a new page option **`$simple_header = true`**. The top bar then shows only the logo: no navigation, no buttons, no phone bottom bar. `footer.php` now asks `$show_bottom_bar` instead of `$is_member`.
- `partials/auth-tabs.php` (new): **Sign up · Log in** as two links, drawn like the segmented control. Expects `$active`.
- `partials/auth-panel.php` (new): the community panel, the quote from `@kandy.jungle` (Kandy · 64 plants) and a plant picture. Expects `$member`.
- `includes/mock-data.php`: `$mock_quote_member`, Kavindi's `users` row with `plant_count`.
- `public/assets/css/auth.css` (new) and `.input-prefix` in `components.css` for the "@" in front of a username.
- Checked: the home page, About and the style guide, as member and visitor, still load with no PHP errors and still show the bottom bar only to members.

**Why this way**
- **Tabs are links, not JavaScript.** The wireframe names two files, `register.php` and `login.php`. Both draw the same card, so moving between them looks like switching a tab, works without JavaScript, and each form has its own address that can be shared or bookmarked.
- **Two partials**, because both pages show the tabs and the panel, which is the rule for making a partial. (The plan said one partial; it became two, because the tabs sit in the form column and the panel in the other column.)
- **A quiet top bar on these pages.** p9 shows no navigation. A visitor who is signing up should not be pulled away by menu links, a common pattern on sign-up pages. The logo still leads home.
- **The quote is page text, the member is data.** There is no testimonials table, so the sentence is written in the partial; the username, town and plant count come from a `users` row, as they will in stage B.
- **The panel comes after the form in the HTML.** A screen reader and the Tab key meet the form first. On a desktop CSS grid places the panel on the left, as on p9, without changing that order.

```php
$simple_header   = !empty($simple_header);
$show_bottom_bar = $is_member && !$simple_header;   // footer.php prints the phone bar when this is true
```
```css
@media (min-width: 1024px) {
  .auth-shell { grid-template-columns: 5fr 7fr; }
  .auth-main  { grid-row: 1; grid-column: 2; }   /* first in the HTML, shown on the right */
  .auth-panel { grid-row: 1; grid-column: 1; }
}
```

> [!tip] What to remember
> - HTML order is reading order for screen readers and the Tab key; CSS grid can change the **visual** order without changing it.
> - A page talks to the header through variables set before the include: `$page_title`, `$page_css`, and now `$simple_header`.

**Check yourself**
1. Why are the Sign up and Log in tabs links between two pages instead of JavaScript tabs?
2. What does `$show_bottom_bar` decide, and why was it added instead of using `$is_member`?
3. Why is the community panel second in the HTML when it appears first on a desktop?

> [!success]- Answers
> 1. The wireframe names two files, each form gets its own URL, and it works without JavaScript. Because both pages share the card and the tabs, the switch still feels like a tab change.
> 2. Whether the phone bottom bar is printed. A member on a page with the simple header should not see it, so "is a member" is no longer enough on its own.
> 3. So the form is read and reached first by screen readers and the keyboard. Grid places it on screen; the HTML order stays logical.

Lessons: [[00.03 PHP Includes and Partials]] · [[01.01 Page Skeleton - Header, Footer and Navigation]] · [[07.01 CSS Layout with Flexbox and Grid]]

---
### Step 2.6 — The Sign up page
> [!important] Goal (CORE-07, UI half)
> Build `register.php` (wireframe p9): the form that gets a new member registered in under a minute.

**What was done (2026-10-14, branch `feature/accounts-ui`, commit `5507fa8`)**
- `public/register.php` (new): tabs (Sign up pressed), *Create your garden*, and the fields **Full name**, **Username** (with "@" in front), **Email**, **Password** (empty four-step meter and the one rule), **Location (optional)** with the reason it is asked, the **guidelines checkbox**, and one full-width **Create account** button. Then "Already have a garden? Log in" and the community panel.
- Fields are empty, with grey example text (placeholders) from the wireframe's sample: Nimali Perera, nimali.grows, Kurunegala.
- Checked by screenshot at 375 and 1280 px; no PHP errors as visitor or member.

**Why this way**
- **Every input has a `<label for>`** that matches its `id`. Clicking the label focuses the field, and a screen reader reads the label when the field is reached. A placeholder is only an example; it disappears on typing, so it can never replace a label.
- **`name` attributes match the `users` columns** (`full_name`, `username`, `email`, `location`). In stage B, `$_POST['full_name']` goes straight into the column of the same name.
- **`autocomplete` hints** (`name`, `username`, `email`, `new-password`, `address-level2`) let the browser fill in details and offer to save a strong password. `new-password` tells it this is a *new* password, not a log-in.
- **The browser checks the basics today**: `required`, `type="email"`, `minlength="8"`, and `pattern`. Week 6 adds friendlier inline messages; stage B checks everything again on the server, because anything in the browser can be switched off.
- **`aria-describedby`** links a field to its hint, so a screen reader reads "At least 8 characters, including a number" when the password field is reached.
- **The guidelines link opens in a new tab** (`target="_blank" rel="noopener"`), so reading them never loses what was typed. A hidden "(opens in a new tab)" warns screen-reader users.
- **No show-password eye yet.** A button that does nothing is worse than none; it comes with the week-6 JavaScript, together with the live meter and the live username check.

```php
<label class="field-label" for="password">Password</label>
<input class="input" type="password" id="password" name="password"
       autocomplete="new-password" minlength="8" pattern="(?=.*\d).{8,}" required aria-describedby="password-hint">
<span class="field-hint" id="password-hint">At least 8 characters, including a number.</span>
```

> [!tip] What to remember
> - Label + `for`/`id`, `name` = database column, `autocomplete`, `aria-describedby` for hints.
> - Browser checks help the user; only the server's checks protect the database.

**Check yourself**
1. Why is a placeholder not enough without a label?
2. What does `pattern="(?=.*\d).{8,}"` require?
3. Why will the server check the form again in stage B when the browser already checks it?

> [!success]- Answers
> 1. It disappears as soon as the user types, it is often too faint to read, and screen readers may not announce it. The label stays visible and is always read.
> 2. At least 8 characters (`.{8,}`), at least one of which is a digit (`(?=.*\d)` looks ahead for a digit without using up characters).
> 3. Browser checks can be skipped (switching off JavaScript or `required` in DevTools, or sending the request with another tool). The server is the only place the rules cannot be bypassed.

Lessons: [[00.05 HTML Forms]] · [[03.06 Form Handling - Validation, Redirects and Flash Messages]]

---
### Step 2.7 — The Log in page, and checks for both pages
> [!important] Goal (CORE-09, UI half)
> Build `login.php` (p9, state B), then check Sign up and Log in together at every width and with the keyboard.

**What was done (2026-10-14, branch `feature/accounts-ui`, commit `00512dc`)**
- `public/login.php` (new): the same card with **Log in** pressed, *Welcome back*, **Email or username**, **Password**, **Remember me**, **Forgot password?**, one full-width **Log in** button, and "New to PlantFolio? Create your garden".
- **Checks, both pages** (no code change, so no commit): no horizontal scroll at 375, 768, 1024 and 1280 px, as visitor and member; every input has a label; screenshots at 375, 768 and 1280 px.
- **Tab order, Sign up:** Skip to content › logo › Sign up › Log in › Full name › Username › Email › Password › Location › checkbox › community guidelines › Create account › Log in › footer links. **Log in:** … › Email or username › Password › Remember me › Forgot password? › Log in › Create your garden › footer links. The community panel has nothing to focus, so it adds no stops.

**Why this way**
- **One field for "Email or username"**, as on p9. People forget which one they used; stage B looks the value up in both columns.
- **`autocomplete="current-password"`** here, `new-password` on Sign up: the browser offers the saved password on Log in and suggests a new strong one on Sign up.
- **No error message is drawn yet.** In stage B every failure shows the same words, "Email or password is incorrect", so the page never reveals whether an account exists. The rule is written as a comment at the top of the file.
- **"Forgot password?" goes to Contact** (`about.php#contact`). There is no reset page in the page list; see Open points.

```php
// Stage B: … find the user by email or username, password_verify(),
//   session_regenerate_id(true), then go to the Dashboard. Every failure shows the same message,
//   "Email or password is incorrect", and 5 failures lock the account for 15 minutes (CORE-12).
```

> [!tip] What to remember
> - One generic log-in error: it never says *which* part was wrong.
> - Test the Tab order: it should follow the page top to bottom, with no invisible stops.

**Check yourself**
1. Why does Log in say "Email or password is incorrect" instead of "No account with that email"?
2. Why is `autocomplete` different on the two password fields?

> [!success]- Answers
> 1. A specific message tells an attacker which emails have accounts (account enumeration). One message for every failure gives nothing away.
> 2. `current-password` asks the browser to fill in the saved password; `new-password` asks it to suggest and save a new one.

Lessons: [[03.03 Password Hashing and Login Lockout]] · [[03.04 PHP Sessions and Login State]]

---

## Open points
- **Invented sample values on the Landing page:** 1,248 members, 37 open listings, the full names Amaya Madushani and Kavindi Jayawardena (`@kandy.jungle`), Amaya's plant Pip with 48 likes, Kavindi's rating 5.0 (11), and the dates of the two predictions. Swap them for the team's choices if you prefer.
- **Reminder chip says "Water Monty today"**, while p8 says "Water Goldie today". In the mock data Goldie's watering is two days *overdue* (red), which would look like an alarm on a welcome page, and the chip sits under Monty's card.
- **Members see the Landing page** until `dashboard.php` exists; the redirect is a comment at the top of `index.php` for stage B.
- **Designed without a wireframe:** the smart tools section, Fresh on the exchange, and the phone layout (p23 has no Landing screen). Compare with the Figma prototype and change anything that differs.
- **Placeholder drawing `pip.svg`**, like the other mock pictures, to be replaced by a real photo with a `docs/credits.md` entry.
- **Sign up / Log in (P02):** "Forgot password?" links to Contact, because no password-reset page is in the page list or the Task Sheet that I know of: decide whether to add one. The username rule (lowercase letters, numbers, dots, underscores, 3–30 characters) and the "Full name" field being required are my choices; p9 does not state them. The top bar on these two pages shows only the logo.
