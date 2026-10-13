---
type: project-note
project: PlantFolio
status: draft
tags: [project, web]
---
# PlantFolio Week 01 — Foundation
> [!info] [[PlantFolio - Project Home]] · Stage F · 5–11 Oct 2026 · Module MOD-01 · branch `feature/scaffold-ui`
> **Study notes:** [[00.00 Project Foundations]] · **Revision:** [[00.99 Summary - Project Foundations]]

> [!abstract] Week goal
> The shared foundation every page will stand on: design tokens, components, partials, the page header and footer, and a style guide that shows them all.

## Tasks
Status as found in the repository on 8 Oct 2026. "UI built" means the code exists on the feature branch; it is not merged or reviewed yet.

- [ ] **PLN-07** Dev environment: XAMPP (PHP 8+), Git, VS Code, Python 3.10+; `phpinfo()` loads. *PHP 8.2.12 and Python 3.13.2 were found on this computer; tick this once you have confirmed the rest.*
- [ ] **PLN-09** Create `develop`, protect `main` and `develop`, set `develop` as the default branch. *`develop` exists and is the default branch on GitHub. Branch protection was not checked.*
- [x] `config/config.php`, `includes/helpers.php` with `e()`, first `includes/mock-data.php` (MOD-01)
- [x] **CORE-04** `variables.css`: palette, health colours, fonts, spacing, radii. *UI built.*
- [x] **CORE-05** `components.css` and `partials/`: buttons, form controls, badges, status chips, confidence meter, plant card, listing card, toast. *UI built.*
- [x] **CORE-06** `header.php` (public and logged-in), mobile bottom bar, `footer.php`, active-page highlight. *UI built on 13 Oct 2026 (step 1.8).* → [[01.01 Page Skeleton - Header, Footer and Navigation]]
- [x] `public/styleguide.php`: gallery of every component

**In parallel, due for MS-1.** None of these files exists in the repository yet: `docs/architecture.md` (DES-09), `docs/api-contract.md` (DES-10), `docs/conventions.md` (DES-11), `docs/security-checklist.md` (DES-12), `database/erd.png` (DES-06), `database/data-dictionary.md` (DES-08), `database/species.csv` (DES-13). The ERD, schema reconciliation, data dictionary and species data should finish by the end of week 2, because mock data uses their column names.

## Done when
- [ ] The style guide shows every component from wireframe p7 correctly at 375 px and 1024 px
- [x] A blank page shows the header, bottom bar and footer. *Checked on 13 Oct 2026 with screenshots of `index.php` at 375, 768, 1024 and 1280 px. Still to check by hand: hover, keyboard focus and a real phone.*

## Learn this week
- CSS custom properties → [[00.05 CSS Custom Properties and Design Tokens]]
- Flexbox and grid → [[07.01 CSS Layout with Flexbox and Grid]]
- PHP `include` and `require`, arrays and `foreach` → [[00.02 PHP Basics for Templates]] · [[00.03 PHP Includes and Partials]]

---
## Work log

### Step 1.1 — Escaping helper and first mock data
> [!important] Goal (MOD-01)
> Give every page one safe way to print a value, and some data to print.

**What was done (2026-10-04, commit `f3a1c43`)**
- `includes/helpers.php` with the function `e()`.
- `includes/mock-data.php` with `$current_user`, the signed-in member from the wireframes.
- `public/index.php` changed to load both files and print the username.

**Why this way**
- `e()` wraps `htmlspecialchars()`. It turns `<`, `>`, `&` and quotes into harmless codes, so text typed by a user can never run as HTML or JavaScript.
- It is used on **everything printed, mock data included**, so the habit exists before real user input arrives.
- The `(string)` cast lets numbers and `null` pass through without a PHP warning.

```php
function e($value)
{
    return htmlspecialchars((string) $value, ENT_QUOTES, 'UTF-8');
}
```

> [!tip] What to remember
> - Escape at the moment of **output**, not when data is saved.
> - `ENT_QUOTES` converts both `"` and `'`, which matters inside HTML attributes.

**Check yourself**
1. What attack does `e()` prevent, and how?
2. Why escape mock data that you wrote yourself?

> [!success]- Answers
> 1. Cross-site scripting. Characters with a special meaning in HTML are replaced by entity codes, so the browser shows them as text and does not treat them as markup or script.
> 2. To build the habit, and because that mock value will be replaced by a database value typed by a user. A page that already escapes everything needs no change when the data source is swapped.

Lesson: [[00.04 Output Escaping in PHP]]

---
### Step 1.2 — Design tokens
> [!important] Goal (CORE-04)
> Put every colour, font, size and spacing value from wireframe p6 in one file, under a name.

**What was done (2026-10-04, commit `62df1fa`; more tokens added in `d49d4f4` and `8692e22`)**
- `public/assets/css/variables.css`: brand palette, health-status colours, fonts, type scale, 8-point spacing, corner radii, content width.
- `index.php` restyled to use only tokens.

**Why this way**
- Other CSS files use `var(--color-leaf)` and never repeat a hex code. Changing a colour means changing one line.
- Health-status tokens are **named after the database values** (`--status-needs-attention-text`), so the class name, the token and the stored value all match.
- `--color-danger` reuses the Sick colour, because wireframe p6 defines no separate red.
- Text sizes are in `rem`, so text grows when a user raises their browser's font size.
- Breakpoints cannot be tokens: CSS does not allow `var()` inside `@media`, so `768px` and `1024px` are typed directly.

```css
:root {
  --color-leaf: #4A7043;        /* primary buttons, links */
  --status-sick-text: #9A3524;
  --space-16: 16px;
  --radius-card: 14px;
}
```

> [!tip] What to remember
> - A token is a named design decision. Components use the name, never the raw value.
> - Tokens not listed on wireframe p6 (hover colours, focus ring, avatar tints) were chosen by eye from p7 and are marked as such in the file.

**Check yourself**
1. Where must a custom property be declared to be available on every element?
2. Why are text sizes in `rem` and spacing in `px`?

> [!success]- Answers
> 1. On `:root`, the top of the document. Custom properties are inherited, so every element below can read them.
> 2. `rem` follows the user's font-size setting, which keeps text readable for people who enlarge it. Spacing was kept on the fixed 8-point scale from the design system.

Lesson: [[00.05 CSS Custom Properties and Design Tokens]]

---
### Step 1.3 — Base styles, buttons, badges and the style guide
> [!important] Goal (CORE-05)
> Start the shared component stylesheet, and a page that shows every component in one place.

**What was done (2026-10-04, commit `d49d4f4`)**
- `public/assets/css/components.css`: base styles, buttons (`.btn` plus a look and a size), badges.
- `public/styleguide.php`: a gallery to compare with wireframes p6 and p7.

**Why this way**
- **`box-sizing: border-box`** on everything, so padding and borders count inside an element's width and a 100 % wide box never overflows.
- **One base class plus modifiers:** `.btn` gives the shape, `.btn-primary` the look, `.btn-sm` the size.
- **`:focus-visible`** shows the focus outline for keyboard users and not on mouse clicks.
- **Reduced motion is respected:** animations are switched off for people who have asked their system for less movement.
- **A badge always contains its word.** Colour is never the only signal.
- The style guide is a **dev-only page**. It is removed or protected before release.

> [!tip] What to remember
> - Build a component once, show it in the style guide, then reuse it. If it looks wrong, there is one place to fix.
> - `.btn` is also used on `<a>` links. A disabled link uses `aria-disabled`, because links have no `disabled` attribute.

**Check yourself**
1. What does `box-sizing: border-box` change?
2. Why is `:focus-visible` used instead of `:focus`?

> [!success]- Answers
> 1. The declared width then includes padding and border. Without it, a box with `width: 100%` plus padding is wider than its container and causes horizontal scrolling.
> 2. `:focus` also matches after a mouse click, which draws an outline most mouse users do not want. `:focus-visible` matches when the browser decides a focus indicator is needed, mainly for keyboard navigation.

Lesson: [[00.06 Component CSS and the Style Guide]]

---
### Step 1.4 — Form controls, chips and the segmented control
> [!important] Goal (CORE-05)
> Inputs, selects, checkboxes, toggles and filter chips that look right and still behave like real form controls.

**What was done (2026-10-04, commit `8692e22`)**
- Form styles: `.field`, `.field-label`, `.input`, `.field-hint`, `.field-error`, checkbox, radio, toggle.
- `.chip` filters and the Swap / Free / Either segmented control.

**Why this way**
- **Real controls, restyled.** The toggle is a real checkbox drawn as a switch. The segmented control is built from radio buttons. Both work with the keyboard, with screen readers and **without JavaScript**.
- **`accent-color`** recolours the built-in checkbox and radio and keeps all their normal behaviour.
- **`aria-invalid`** is styled directly. It is what a screen reader announces, so the look and the meaning cannot drift apart.
- **`.visually-hidden`** hides text from sight and keeps it for screen readers.
- Inputs use a 16 px font on phones, because smaller text makes iOS Safari zoom in when the field is tapped.

> [!tip] What to remember
> - Prefer a native control with new styling over a `div` that imitates one.
> - A `<label>` that wraps its input makes the words clickable too.

**Check yourself**
1. Why build the segmented control from radio buttons?
2. What is the difference between hiding with `display: none` and with `.visually-hidden`?

> [!success]- Answers
> 1. Radio buttons already allow exactly one choice, move with the arrow keys, are announced correctly and submit with the form. A custom widget would need all of that rebuilt in JavaScript.
> 2. `display: none` removes the element for everyone, screen readers included. `.visually-hidden` keeps it available to assistive technology while taking it out of the visual layout.

Lessons: [[00.06 Component CSS and the Style Guide]] · [[00.05 HTML Forms]] · [[00.09 Styling Forms with CSS]]

---
### Step 1.5 — Icons and the helpers `icon()`, `config()`, `url()`
> [!important] Goal (CORE-05)
> Show icons with no internet connection and no build step, and stop writing addresses and settings by hand.

**What was done (2026-10-04, commit `308785a`)**
- 14 Lucide icons saved as SVG files in `public/assets/img/icons/`, with their licence and a credits entry.
- Three helpers in `includes/helpers.php`: `icon()`, `config()`, `url()`. A `timezone` setting was added to the config.

**Why this way**
- **Inline SVG** (the helper prints the SVG code into the page) lets an icon take the colour of the text around it, through `stroke="currentColor"`. An `<img>` cannot do that. Icons are sized in `em`, so they scale with the text.
- **`icon()` accepts only letters, digits and hyphens.** A name can therefore never point to a file outside the icons folder.
- Icons are **`aria-hidden`**: the text beside an icon carries the meaning.
- **`config()`** uses a `static` variable, so `config.php` is read once per request however often the function is called.
- **`url()`** builds every address from `base_url`. Moving the site means changing one setting.
- **Time zone:** XAMPP ships with another country's time zone, which would make "due today" wrong for several hours each evening. The site sets `Asia/Colombo`.

```php
function url($path = '')
{
    return rtrim(config('base_url'), '/') . '/' . ltrim($path, '/');
}
```

> [!tip] What to remember
> - `icon()` is printed **without** `e()`: it returns the project's own SVG file, not user text. Everything else is escaped.
> - Never build a file path from unchecked input.

**Check yourself**
1. Why can `icon()` output be printed without escaping, when everything else must be escaped?
2. What does the `static` keyword do inside `config()`?

> [!success]- Answers
> 1. Its content comes from files the project ships, and the name is restricted to a safe pattern, so no user-supplied text reaches the page. Escaping it would turn the SVG markup into visible text.
> 2. A static variable keeps its value between calls to the function. The config file is loaded on the first call and reused afterwards.

Lessons: [[00.03 PHP Includes and Partials]] · [[00.06 Component CSS and the Style Guide]]

---
### Step 1.6 — Tabs, breadcrumb, avatars and the card partials
> [!important] Goal (CORE-05)
> The first reusable pieces of page: each takes one row of data and prints its HTML.

**What was done (2026-10-04, commits `483fda1` and `b914aff`)**
- CSS for tabs, breadcrumb and avatars; `partials/avatar.php`.
- `partials/plant-card.php`, `listing-card.php`, `rating.php`, `status-badge.php`.
- Mock data grew: `$mock_plants` and `$mock_listings`, with six mock plant pictures.
- Helpers for labels, dates and avatars: `health_label()`, `task_label()`, `listing_type()`, `days_until()`, `care_due()`, `time_ago()`, `initials()`, `avatar_tint()`, `photo_url()`, and `partial()`.

**Why this way**
- **A partial expects one row-shaped array** (`$plant`, `$listing`). It works the same with mock data now and database rows later.
- **`partial()` runs the file inside a function**, so the partial's variables never leak into the page.
- **Mock rows include joined columns** (`scientific_name` from `plant_species`, `collection_name` from `collections`), exactly as a future `JOIN` query will return them.
- **`mock_date()`** counts dates from today, so "Water today" and "Overdue 2 days" stay true on whatever day the page is opened, including the day of the demo.
- **Cards:** `aspect-ratio` keeps every photo 4:3 and `object-fit` crops instead of stretching. One real link sits on the title, and its `::after` covers the whole card, so the card is clickable without wrapping everything in a link.
- **Avatars:** `crc32()` of the username picks one of four tints, so a member gets the same colour on every page. `mb_substr` counts letters, not bytes, so Sinhala and Tamil names work.
- **Tabs** are plain links for now. The current one gets `.is-active`. Switching without a reload comes in week 5.

```php
<?php foreach ($mock_plants as $plant): ?>
  <?php partial('plant-card', ['plant' => $plant]); ?>
<?php endforeach; ?>
```

> [!tip] What to remember
> - Privacy rule in the listing card: contact details are never printed there. They appear only after "I'm interested".
> - Add a partial only when the same markup appears on two or more pages.

**Check yourself**
1. Why must a mock array use the real column names from the database plan?
2. How is the whole card made clickable with only one link?

> [!success]- Answers
> 1. The page and its partials read values by key. If the keys already match the future query's columns, stage B replaces the array with a query and the page does not change.
> 2. The link's `::after` pseudo-element is stretched over the card with absolute positioning. Screen readers still meet one link with a clear name, and other links inside the card can be raised above it.

Lessons: [[00.03 PHP Includes and Partials]] · [[00.07 Mock Data Shaped Like Database Rows]] · [[01.02 Card Grids, Images and Sticky Bars]]

---
### Step 1.7 — Care row, confidence meter, rating, toast and alerts
> [!important] Goal (CORE-05)
> The last shared components of wireframe p7.

**What was done (2026-10-04, commit `50488d7`; documentation aligned on 2026-10-05, commit `fc4413b`)**
- `partials/care-row.php` and `confidence-meter.php`; star rating, toast and alert styles; `$mock_reminders`; `confidence_level()` in the helpers.
- Documentation: `created_at` recorded on listings in `database/README.md`, and the modal partial moved to week 5.

**Why this way**
- **Confidence words are fixed** by the design system: High from 80 %, Medium 60–79 %, Not sure below 60 %. The word is decided from the **rounded** percentage, so it always agrees with the number the user sees.
- **The confidence bar is `aria-hidden`:** it only repeats the number beside it.
- **Care row:** on a phone the button shows only a tick, so the full action lives in `aria-label` ("Mark done: Water Goldie").
- **Rating:** sighted users see `★ 4.9 (23)`. Screen readers get a full sentence in `.visually-hidden` text.
- **Every mock reminder keeps the rule** `next_due = last_done + interval_days`, as the database plan requires.
- **Toast and Mark done are CSS only for now.** The button does nothing in stage F; JavaScript arrives in week 5 and the real POST in stage B.

> [!tip] What to remember
> - Red is used only for overdue care and destructive actions. Terracotta means due today.
> - Below 60 % confidence the result is never shown as an answer.

**Check yourself**
1. Why is the confidence word decided from the rounded percentage?
2. Why does the Mark done button need an `aria-label`?

> [!success]- Answers
> 1. A value of 0.796 shows as 80 %. If the word used the raw value it would say Medium beside "80 %", which contradicts the rule the user can see.
> 2. On a phone the button shows only an icon, and icons are hidden from screen readers. Without a label it would be announced as an unnamed button.

Lessons: [[05.07 Confidence Thresholds and Honest AI Results]] · [[07.07 Dashboard UX and Accessibility]]

---
### Step 1.8 — The page frame: header, footer and phone bottom bar
> [!important] Goal (CORE-06)
> Write the top and the bottom of every page once, so the fifteen pages only supply what goes between.

**What was done (2026-10-13, branch `feature/scaffold-ui`, commit `2b75f83`). UI built, not wired.**
- `includes/header.php`: the `<head>`, a skip link, the top bar in two versions (visitor and member) and the opening `<main>`.
- `includes/footer.php`: closes `<main>`, then the footer and the phone bottom bar (Home, Garden, +, Exchange, Profile).
- `public/assets/css/layout.css`: the frame's styles and the `.container` content column.
- `components.css`: two new shared components, the round icon button (`.icon-btn`) and the dropdown menu (`.menu`).
- `helpers.php`: `current_page()` and `current_area()`. `variables.css`: `--header-height`, `--bottom-bar-height`.
- `mock-data.php`: `new_interest_count` for the bell, and the `?guest` preview switch.
- Nine more Lucide icons, added to `docs/credits.md`.
- `public/index.php` is now the blank page that shows the frame.

**Why this way**
- **A tag opened in one file is closed in the other.** `header.php` opens `<body>` and `<main>`; `footer.php` closes them. A page includes both and writes only its own content.
- **One `if` chooses the header.** `$is_member = !empty($current_user);`. Mock data sets `$current_user` now; the session will set it in stage B. A visitor is `null` in both stages, so the header does not change.
- **`?guest` is a preview switch.** It sets `$current_user = null` in `mock-data.php`, so any page can be checked as a visitor. It lives in the mock file and disappears with it.
- **The highlight follows the area, not the file.** `current_area()` holds the sitemap of wireframe p4 in one array. "My Garden" stays lit on `plant.php` and `plant-form.php`. The link whose own file is open also gets `aria-current="page"`, which a screen reader announces.
- **Menus are `<details>` elements,** so they open without JavaScript. They share `name="site-menu"`, so only one is open at a time. → [[01.05 Dropdown Menus with details and summary]]
- **Mobile first.** The base CSS is the phone: links hidden, bottom bar shown. From 768 px the top navigation appears and the bottom bar is hidden. From 1024 px the logo name and the "Add plant" label return.
- **`position: sticky` for the top bar, `fixed` for the bottom bar.** The footer gets extra bottom padding on phones, so the fixed bar never covers its text.
- **`--header-height` changes inside a media query** in `variables.css`. Everything that reads the token follows, with no media query of its own.

```php
// includes/header.php — which version, and which link is lit
$is_member = !empty($current_user);
$area      = current_area();
```
```php
<a class="nav-link<?= $link['area'] === $area ? ' is-active' : '' ?>" href="<?= e(url($link['href'])) ?>"<?= $link['href'] === current_page() ? ' aria-current="page"' : '' ?>><?= e($link['label']) ?></a>
```

**Problems and fixes**
- **At 768 px the avatar was pushed off the right edge.** Five links, four buttons and the logo name do not fit. Fix: between 768 and 1023 px the logo shows only its mark and "Add plant" shows only its "+", and the gap between links is 12 px instead of 24 px. Both links keep their name through `aria-label`.
- **The raised "+" button covered the last line of the footer** on a phone. Fix: the spare room was moved from `<body>` into the footer and made 16 px taller than the bar.
- **The first headless Edge screenshot took about two minutes** and the command timed out. It finished in the background; later screenshots were fast.

> [!tip] What to remember
> - The page pattern is now: helpers → mock data → `$page_title` → `include header.php` → HTML → `include footer.php`.
> - Wrap page content in `<div class="container page">`. A full-width coloured band goes outside the container.
> - `sticky` scrolls with the page until it reaches the edge; `fixed` never moves.

**Check yourself**
1. How does the header know which navigation link to highlight?
2. How does one `header.php` show two different headers, and what changes in stage B?
3. Why is the bottom bar hidden from 768 px, and why does the footer have extra bottom padding on phones?

> [!success]- Answers
> 1. `current_page()` takes the file name from `$_SERVER['SCRIPT_NAME']`. `current_area()` looks that name up in an array of areas and returns, for example, `garden`. Each link carries its area name; the one that matches gets the class `is-active`.
> 2. It tests `!empty($current_user)`. In stage F the mock data sets that variable (and `?guest` clears it). In stage B the session sets it. The header file itself stays the same.
> 3. From tablet width the top bar has room for the navigation, so a second navigation would only repeat it. The bar is `position: fixed`, which takes it out of the page flow, so without the padding it would lie on top of the last content.

Lessons: [[01.01 Page Skeleton - Header, Footer and Navigation]] · [[01.05 Dropdown Menus with details and summary]] · [[00.03 PHP Includes and Partials]]

---
### Step 1.9 — About page for the footer links
> [!important] Goal (no task ID yet)
> Give the three footer links, and the "community guidelines" link on the sign-up form, a real page to open.

**What was done (2026-10-13, branch `feature/scaffold-ui`, commit `037a4cc`). UI built.**
- `public/about.php`: one public page with three sections, `#about`, `#guidelines` and `#contact`.
- `includes/footer.php`: the three links now point to `about.php#about`, `about.php#guidelines` and `about.php#contact`.
- `layout.css`: `.prose` (a narrow column for reading), `.prose-nav` and `.rule-list`.
- `CLAUDE.md`, `docs/ROADMAP.md` and `public/README.md` list the extra page.

**Why this way**
- **One page, not three.** Wireframe p4 names three footer links, but the 15-page list has no file for them. One static page with three sections covers all of them with the least to build and explain.
- **A link can open a page at a section.** `about.php#guidelines` loads the page and scrolls to the element with `id="guidelines"`. `scroll-padding-top` in `layout.css` stops the heading from hiding under the sticky top bar.
- **Content is an array at the top, HTML loops below.** `$guidelines` holds the six rules. The same page pattern as everywhere else.
- **The contact details are samples.** `hello@plantfolio.example` uses the `.example` ending, which is reserved for examples and can never be a real address. A grey notice on the page says so. The real details are added in week 16.
- **The rule numbers are drawn by CSS.** `counter-reset`, `counter-increment` and `content: counter(rule)` number the list, so the HTML stays a plain `<ol>`.
- **Built on the scaffold branch,** because it completes the footer of the shared frame.

```php
<ol class="rule-list">
  <?php foreach ($guidelines as $rule): ?>
    <li>
      <h3><?= e($rule['title']) ?></h3>
      <p><?= e($rule['text']) ?></p>
    </li>
  <?php endforeach; ?>
</ol>
```

> [!tip] What to remember
> - This page is **not in the wireframes**. Be ready to say why it was added: the wireframes ask for the links but give them no page.
> - The guidelines text is a first draft. The team should read and edit it.
> - Replace the sample contact details before submission (noted in the roadmap, week 16).

**Check yourself**
1. What does the `#guidelines` part of `about.php#guidelines` do?
2. Why is the sample address `hello@plantfolio.example` and not something like `plantfolio@gmail.com`?

> [!success]- Answers
> 1. It is a fragment. After the page loads, the browser scrolls to the element whose `id` is `guidelines`. It is not sent to the server.
> 2. `.example` is a reserved ending that no one can register, so a sample address can never reach a real person. An invented Gmail address might belong to someone.

Lessons: [[01.01 Page Skeleton - Header, Footer and Navigation]] · [[02.03 URL Structure and Domains]]

---
## Open points
- **`docs/credits.md` has an uncommitted change** that puts four spaces before `# Credits`. In Markdown that turns the heading into a code block. It looks accidental. *13 Oct: only the icon line of that file was committed; the four spaces were then removed, so the file matches the commit again. Closed.*
- **Footer links have no pages.** Wireframe p4 names About, Community guidelines and Contact, but the page list has no file for them. *Closed on 13 Oct: one extra page, `about.php`, built in step 1.9. Still to do: real contact details (week 16), the team's edit of the guidelines text, and a task ID for the page.*
- **The bell has no page either.** It links to My Exchanges, because the only notifications in the wireframes are new interests in a listing (p10).
- **Search on phones.** Wireframe p23 shows only the bell and the avatar in the phone top bar. The search button was kept as well, because Explore is not in the bottom bar and would otherwise be unreachable on a phone.
- **Remaining this week:** move `styleguide.php` onto the new header and footer, then the "done when" check of the style guide.
- After that: open the UI pull request for `feature/scaffold-ui` into `develop`, as CONTRIBUTING.md describes.
