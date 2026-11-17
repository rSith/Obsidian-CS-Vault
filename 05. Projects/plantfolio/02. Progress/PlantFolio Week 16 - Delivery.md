---
type: project-note
project: PlantFolio
status: draft
tags: [project, web]
---
# PlantFolio Week 16 — Delivery
> [!info] [[PlantFolio - Project Home]] · Stage D · 18–24 Jan 2027 · release **v1.0**, submission about 26 Jan 2027
> **Study notes:** [[06.00 Testing and Delivery]] · **Revision:** [[06.99 Summary - Testing and Delivery]]

> [!abstract] Week goal
> Fix what testing found, prove the project installs from scratch, and hand in: report, slides, demo and the tagged release.

## Tasks
- [ ] **TST-10** Bug fixes
- [ ] **TST-11** Final regression → [[06.01 Test Cases, Journeys and Regression Testing]]
- [ ] **DEP-02** Production config → [[06.03 Production Configuration and Release]]
- [ ] **DEP-03 / DEP-04** Deploy *(if hosting online)*
- [ ] **DEP-06** Fresh install from the README
- [ ] **DEP-15** Assemble the report
- [ ] **DEP-16** Slides → [[09.03 Presenting and Storytelling]]
- [ ] **DEP-17** Backup demo video
- [ ] **DEP-18** Rehearse twice
- [ ] **DEP-19** Tag `v1.0` and submit
- [ ] **DEP-20** Present

## Checks
- [ ] A teammate installed the project on a clean computer using only the README
- [ ] Errors are hidden from visitors and written to a log in production mode
- [ ] `styleguide.php` is removed or protected
- [ ] The GitHub release has the SQL dump and the notebooks
- [ ] The demo works with the internet switched off, or the backup video is ready

## After submission (weeks 17–20)
Retrospective in `docs/retrospective.md` (MNT-01), feedback fixes, portfolio polish.

---
## Work log
Step sections are added here as each step is finished.

### Step 16.1 — Release cleanup, setup guide and a shareable zip

> [!important] Goal
> **DEP-05, DEP-07, DEP-06 (first try), DEP-02 (checked).** Remove what only the frontend stage needed, write a setup guide for XAMPP and for ProFreeHost, and make one zip that is safe to give to anyone. Hosting itself (DEP-03/04) is still to do: it needs my own ProFreeHost account.

**What was done** (12 Nov 2026, branch `feature/release-cleanup` from `feature/ml-service`, commits `2c26bcc`, `69817fa`)
- My choices: **safe cleanup** (mock-data.php stays for now), **project zip + guide**, AI pages **left as they are** online, **demo data** online.
- Removed: the stage F preview addresses that showed sample data (`?empty=1`, `?analysing`, the sample photo on `?offline`, `plant-form.php?ai`, `plant.php?…&guide=`), the sample arrays nobody read, the Vercel files. `styleguide.php` answers 404 unless `app_env` is `development`.
- Moved `PROGRESS.md`, `REVIEW.md`, `DECISIONS.md` into `docs/`. New `docs/SETUP.md`. README, config/README and the CLAUDE.md status table updated.
- `plantfolio.zip` on my Desktop (4.1 MB, 212 files), made with `git archive` from the last commit.
- Fresh-install test: the zip extracted into a new folder with a new database and `app_env` = `production`. All pages open, demo login, photo upload, private folders blocked, a wrong DB password shows only the friendly message. With Flask and Ollama unreachable (the hosted case) the AI pages show "couldn't reach" in about 2 seconds. Test folder and databases deleted afterwards.

**Why this way**
- `git archive` packs only what git tracks, so `config/config.php` (passwords), `ml-service/.env` (API key), `venv/`, member uploads and the database backup **cannot** end up in the zip: they are in `.gitignore`.
- The zip has no folder inside: Windows "Extract All" makes the `plantfolio` folder, and on the host it can be extracted straight into `htdocs`.
- Free hosting runs only PHP, so Flask and Ollama cannot run there. The pages were already built to say so instead of breaking (the rule "the site must never break because of ML"), so nothing had to change for hosting.

**The lines that matter**
```bash
git archive --format=zip -9 -o ~/Desktop/plantfolio.zip HEAD   # only tracked files, from the last commit
```
```php
// styleguide.php: a building tool, not part of the site
if (config('app_env') !== 'development') {
    http_response_code(404);
    exit('Not found');
}
```
On the host, `config/config.php` changes only `app_env` → `'production'`, `base_url` → the site address + `/public`, and the four database values.

**Problems and fixes**
- My first edit of SETUP.md from Python failed: `\x` in the Windows path `C:\xampp` is read as an escape code. Done with the editor instead.
- Identify's `&guide=` looked like a preview too, but `care-guide.js` uses it after a real problem, so it stayed. Only the Plant Profile's copy went.

> [!tip] What to remember
> - Share code with `git archive`, never by zipping the working folder: the folder holds passwords and member photos.
> - `app_env` = `production` on any server: visitors see a friendly message, the details go to the log.
> - A free PHP host cannot run Python or a local LLM. Online, the AI tools say they can't be reached; demo them from the laptop.

**Check yourself**
1. Why can the shared zip never contain `config/config.php`, even by accident?
2. What three kinds of values change in `config.php` on ProFreeHost, and why must `base_url` end in `/public`?
3. Why do the Plant Identifier and AI care guide not work online, and what does a visitor see instead?

> [!success]- Answers
> 1. It is listed in `.gitignore`, so git never tracks it, and `git archive` packs only tracked files.
> 2. `app_env` (production, to hide errors), `base_url` (the online address), and the four database values from the host's MySQL page. Only `public/` is the website; the root `.htaccess` forwards the site's address to `/public/`, and `url()` builds every link from `base_url`.
> 3. Free hosting runs PHP only: there is no Python for the Flask service and no Ollama. PHP's cURL call fails within the 3-second connect time-out, and the page shows "We couldn't reach…" with the manual way. Saved care guides from the demo data still show.

### Step 16.2 — UI polish: motion, landing page glow-up, phone fixes

> [!important] Goal
> **UI polish (no Task Sheet ID yet).** Make the site more attractive and nicer to use without changing the design system: motion, a livelier landing page, and the phone problems left over from the 23 Oct Dashboard audit. My choice on 16 Nov: steps 1–3 now, real photos next.

**What was done** (16 Nov 2026, branch `feature/ui-polish` from `feature/release-cleanup`, commits `e883266`, `7787b77`, `76037cc`)
- **Motion foundation** (`e883266`): motion tokens in `variables.css` (`--ease-out`, `--duration-reveal` …). Buttons shrink a little when pressed; filled buttons rise with a shadow on hover. Card hover lift now only for a mouse. Listing and Community photos zoom slowly on hover. Cards in the main grids (Garden, Community, landing, public profile) fade up as they scroll into view, a few milliseconds apart.
- **Landing page** (`7787b77`): soft sage and terracotta colour pools and two faint outline leaves behind the hero; the hero text rises in line by line on arrival and the collage chips float slowly. New **stats strip** with four real counts (members, public plants, open listings, story entries of public plants). Steps show "Step 1" and a leaf-green icon tile. A forest-green **closing panel** ("Every plant has a story. Start writing yours.") before the footer.
- **Phone fixes** (`76037cc`): a focused field can no longer hide behind the bottom bar; links inside the page glide (smooth scroll); no grey flash or zoom delay on taps; a round **Back to top** button after 1.5 screens of scrolling.
- Checked: headless Edge screenshots at 1366 and 500 px, no console errors, no PHP warnings. Not yet: a real phone, and seeing the animations myself.

**Why this way**
- **Only parts below the screen are hidden** before they reveal. What a visitor sees on arrival is never hidden and shown again, so nothing blinks, and no inline script in `<head>` was needed.
- **Everything is optional.** Without JavaScript, or when the system says "reduce motion", the page is simply all there. Movement makes some people feel unwell; `prefers-reduced-motion` is how the operating system tells the page.
- **`@media (hover: hover) and (pointer: fine)`** = "this device has a mouse". On a phone `:hover` sticks after a tap, so a tapped card would stay lifted.
- **`translate` instead of `transform`** for the collage animation: the cards are already tilted with `transform: rotate()`, and animating `transform` would have wiped the tilt.
- Photos zoom only on cards **without the ⋯ menu**: the zoom needs `overflow: hidden` on the photo frame, which would have cut off the menu's dropdown on My Garden.
- Stats are **real numbers, public rows only**: small on a demo site, but never a false claim.

**The lines that matter**
```js
// app.js: hide only what is still below the screen, then let IntersectionObserver say when it arrives
const prepare = (part) => {
  if (part.getBoundingClientRect().top > window.innerHeight) {
    part.classList.add('reveal');      // opacity 0, 16px lower
    observer.observe(part);            // the callback adds .is-visible → CSS transition fades it up
  }
};
```
```css
/* components.css: hover effects only for a mouse */
@media (hover: hover) and (pointer: fine) {
  .plant-card:hover { transform: translateY(-2px); box-shadow: var(--shadow-card); }
}
```
```css
/* layout.css: room for the phone bar when the browser scrolls a focused field into view */
html:has(body.has-bottom-bar) {
  scroll-padding-bottom: calc(var(--bottom-bar-height) + 28px + var(--space-16));
}
```

**Problems and fixes**
- The Back to top button first moved focus to the skip link, but that link shows itself on `:focus`, so it would pop up after a mouse click. Focus goes to the logo instead.
- The closing heading left "yours." alone on its last line: `text-wrap: balance` evens the lines.

> [!tip] What to remember
> - Motion is decoration: the page must work and look complete without it (no JS, reduced motion).
> - `IntersectionObserver` tells you when an element enters the screen, without checking on every scroll event.
> - Gate hover effects with `(hover: hover)`; touch screens have no real hover.

**Check yourself**
1. Why does the scroll reveal hide only the cards that start below the screen?
2. What does `prefers-reduced-motion` do, and what happens to the reveal and the floating chips when it is on?
3. Why do the collage cards animate `translate` and not `transform`?

> [!success]- Answers
> 1. `app.js` runs after the page is first drawn (`defer`). Hiding what is already on screen would make it disappear and come back: a blink. Cards below the screen were never seen, so hiding them is invisible.
> 2. It is the operating system's "reduce motion" setting, readable in CSS and JS. `app.js` then never adds `.reveal`, and the landing animations sit inside `@media (prefers-reduced-motion: no-preference)`, so nothing moves: everything is just there.
> 3. The cards are tilted with `transform: rotate()`. An animation of `transform` would replace the rotation while it runs; `translate` is a separate property that adds to it.

### Step 16.3 — Real photos in the landing collage, floating cards

> [!important] Goal
> **UI polish (no Task Sheet ID yet).** My request: real plant photos for the two landing cards (Goldie and Monty) instead of the cartoon drawings, and a floating animation for the cards.

**What was done** (16 Nov 2026, branch `feature/ui-polish`, commit `2ae9cf6`)
- Two free-licence photos from **Wikimedia Commons**: a golden pothos in a pot (Slyronit, CC BY-SA 4.0) for Goldie, a Monstera leaf (Princesleaf, CC BY-SA 3.0) for Monty. Cropped to 4:3 with Python (Pillow) and saved at 960 px as `public/assets/img/mock/goldie.jpg` and `monty.jpg` (96 KB and 135 KB).
- Database: plants 42 and 43 and Monty's newest gallery photo (`plant_photos` 204) point at the new files; the same change in `database/seed.sql`, so a fresh install has them too. Every page that shows Monty or Goldie now shows the photo.
- **Photo credits** on `about.php` (#about): title, author, link and licence for each photo.
- The two collage cards **float**: up 10 px with a 0.75° sway, 8 s and 9 s per cycle, starting at different times, so the cards and chips never move together. Off with reduced motion.

**Why this way**
- **CC BY-SA** lets anyone reuse a photo if they credit the author, link the licence and say what was changed ("cropped"). That is why the credits line exists; without it, using the photo breaks the licence.
- Different durations (8 s, 9 s, 6 s for the chips) keep the pieces drifting in and out of step, which looks natural instead of mechanical.
- `rotate` and `translate` are separate CSS properties that add to `transform: rotate(-2deg)`, so the card keeps its tilt while it sways.

**The lines that matter**
```css
@keyframes landing-float-card {
  50% { translate: 0 -10px; rotate: 0.75deg; }
}
.collage-card {
  animation: landing-rise var(--duration-reveal) var(--ease-out) 200ms both,   /* arrive */
             landing-float-card 8s ease-in-out 1s infinite;                     /* then float for ever */
}
```

**Problems and fixes**
- Monty's gallery shows the **newest** photo first (`ORDER BY created_at DESC`). I first changed the oldest row (201), saw the order, and moved the photo to row 204.

> [!tip] What to remember
> - A free photo is not free of rules: CC BY-SA needs a credit with author, source, licence and changes.
> - On the live site, upload the two `.jpg` files and `about.php`, and run the same `UPDATE` lines in phpMyAdmin.

**Check yourself**
1. What four things does the CC BY-SA credit need?
2. Why do the two cards use different animation durations?

> [!success]- Answers
> 1. The title or a link to the work, the author, the licence (with a link), and what was changed (here: cropped).
> 2. So they drift in and out of step with each other and the chips; the same timing would make the whole collage bob as one block.

**Addendum: leafy side margins** (commit `0e393be`)
- My two leaf-pattern images fill the empty left and right margins of the landing page on screens 1200 px and wider: `public/assets/img/landing-leaves-left.jpg` and `-right.jpg`, converted from PNG (about 125 KB each).
- How: two empty `<div class="landing-margin">` placed `absolute` against `<body>`, as tall as the page. `background-size: cover` makes the picture as tall as the page, `background-position: left bottom` shows its leafy outer edge, and `mask-image: linear-gradient(...)` fades it out towards the content. The hero (`z-index: 2`) lies on top, so the leaves start below it.
- Narrower screens hide the strips (`display: none`): the margins would be too thin to show leaves.
- **Removed the same day** (revert `ab7fd31`): seen in the browser, I didn't like the look, so the strips, CSS and both images were taken out with `git revert`. The landing page is back to how it was after `2ae9cf6`. Lesson: `git revert` undoes a commit with a new commit, so the history still shows what was tried.
