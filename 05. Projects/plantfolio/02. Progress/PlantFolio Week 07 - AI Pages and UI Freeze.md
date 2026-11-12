---
type: project-note
project: PlantFolio
status: draft
tags: [project, web, ml]
---
# PlantFolio Week 07 — AI Pages and UI Freeze
> [!info] [[PlantFolio - Project Home]] · Stage F · 16–22 Nov 2026 · ends with the **UI freeze**
> **Study notes:** [[02.00 Interactivity and Responsive Design]] · [[05.07 Confidence Thresholds and Honest AI Results]]

> [!abstract] Week goal
> The two AI pages with mock results, the read-only views for visitors, and an accessibility pass. At the end, every page and state matches its wireframe.

## Tasks
| Page / feature | Task | Notes | Module |
|---|---|---|---|
| P07 Plant Identifier | ML-12 (UI) | Tool switcher, photo preview, tips, top match with confidence label, care chips, alternatives, honesty note | MOD-11 |
| P08 Health Check | ML-14 (UI) | Plant link, diagnosis, plain explanation, 4-step action plan, supported-crops statement, save buttons | MOD-12 |
| S2 Analysing · S3 Not sure | ML-17 (UI) | Disabled buttons while "analysing"; below 60 % never shown as an answer | MOD-11/12 |
| AI suggestion on P06 | ML-13 (UI) | Top 3 with Use buttons, Filled-by-AI tag, manual fallback | MOD-11 |
| Public read-only views | CORE-21 (UI) | P05 for visitors: owner card and like button instead of edit controls | MOD-05 |
| Accessibility and polish | TST-07 (early) | Alt text, labels, focus states, contrast check, hover and pressed states | all |

- [ ] P07 Plant Identifier (`identify.php`)
- [ ] P08 Health Check (`health-check.php`)
- [ ] States S2 Analysing and S3 Not sure
- [ ] AI suggestion block on P06
- [ ] Public read-only view of P05
- [ ] Accessibility and polish pass

## Learn this week
- How an AI result should be worded and when it must say "Not sure" → [[05.07 Confidence Thresholds and Honest AI Results]]
- One page shown differently to its owner and to a visitor → [[01.04 Rendering States - Empty, Owner and Visitor Views]]
- Accessibility → [[09.03 Applying Accessible Design]] · [[09.04 Introduction to ARIA and the Five Rules]]

## UI freeze (end of the week)
- [ ] All 15 pages and states S1–S6 match their wireframes with mock data
- [ ] Every page passes the 375 / 768 / 1024 px check with no console errors
- [ ] `develop` merged into `main` and tagged **`v0.5-ui`**

---
## Work log
Step sections are added here as each step is finished.

> [!note] Plant Profile simplification (P05, CORE-18 UI half, 6 steps)
> Started 2026-11-11 on branch `feature/plant-profile`, made from `feature/plant-stacks`. The page worked but showed the same facts in several places, and a plant's health problem was easy to miss. The plan has six steps: remove repeats → health banner → actions → compact Care tab → Up next card → efficiency. Wireframe p12 is only a guide now (decided 2026-10-23), so the departures are listed for the viva in each step.

### Step 7.1 — Remove the repeated content from the Plant Profile
> [!important] Goal (CORE-18, UI half)
> Show each fact once: one place for care facts, one for reminders, one for photos.

**What was done (2026-11-11, branch `feature/plant-profile`, commit `5cd2905`)**
- `public/plant.php`: the four care tiles under the buttons (Light, Water, Humidity, Next watering) and the "Care info from…" line were deleted.
- The tabs went from four to two: **Story** and **Care**. Reminders and Photos were removed.
- The meta line dropped the collection name and now reads `Thriving · Updated yesterday · With you since Aug 2025`.
- Gallery thumbnails became `<button>`s that show their photo in the large frame. The dashed **Add** tile is the only "add a photo" control left.
- `public/assets/css/plant.css`: about 160 lines of CSS for the deleted parts were removed. The thumbnail got a button reset and a ring for the photo being shown.
- `public/assets/js/app.js`: a small `data-gallery` script (UI only).

**Why this way**
- Before, Light/Water/Humidity were shown twice (tiles + Care tab), each reminder **three** times (Next watering tile, Reminders tab, Reminders card), and every photo twice (gallery + Photos tab). Repeats make the page longer without telling the user anything new.
- The breadcrumb `My Garden › Balcony › Tommy` already says where the plant lives, so the meta line does not need it.
- A thumbnail used to link to the Photos tab. With that tab gone, the natural job for a thumbnail is to show its photo big. Photos added with an update still appear in the Story timeline.
- `aria-pressed="true"` marks the thumbnail that is showing. The ring makes it visible, and screen readers announce it as "pressed", so the choice is not shown by colour alone.
- Departures from wireframe p12 (for the viva): element 5 "Care at a glance" removed, element 6 tabs cut from four to two, collection removed from the meta line. Reason each time: the same information was already on the page.

```js
// app.js: a click on a thumbnail shows its photo in the large frame
thumbs.forEach((thumb) => {
  thumb.addEventListener('click', () => {
    main.src = thumb.dataset.photoSrc;
    main.alt = thumb.dataset.photoAlt;
    thumbs.forEach((other) => other.setAttribute('aria-pressed', String(other === thumb)));
  });
});
```

**Problems and fixes**
- The first version labelled photos with `format_day()`, which drops the year ("10 Jan"). With photos from 2025 and 2026 that was unclear, so the labels now use `date('j M Y', …)`.

**Checked:** PHP lint; no PHP warnings for Monty, Tommy, Goldie, Stripes, the Care tab, an old `?tab=photos` link (falls back to Story), a missing plant and the visitor view; screenshots at 375, 800 and 1280 px.

> [!tip] What to remember
> - Before adding a box to a page, ask "is this fact already shown somewhere?"
> - `data-*` attributes carry values from PHP to JavaScript: `data-photo-src` holds the photo's address, and JS reads it as `thumb.dataset.photoSrc`.
> - The tabs are still plain links (`?tab=care`), so they work without JavaScript.

**Check yourself**
1. Why were the Reminders and Photos tabs removed, not just hidden?
2. What does `aria-pressed` do on the thumbnail buttons?
3. What happens if someone opens an old link `plant.php?id=42&tab=photos`?

> [!success]- Answers
> 1. They only repeated what the side card and the gallery already showed. Hiding them would keep code that serves no purpose.
> 2. It tells assistive technology which thumbnail is showing in the large frame ("pressed"). The CSS draws a ring on `[aria-pressed="true"]`, so sighted users see it too.
> 3. `photos` is no longer a key of `$tabs`, so the `array_key_exists()` check falls back to the Story tab. The page never breaks on an unknown tab.

### Step 7.2 — AI care guide card (UI with mock data)
> [!important] Goal (new feature, no Task Sheet ID yet; committed under CORE-18)
> Show an AI-written care guide for the plant's species in the Care tab: Light, Soil and fertiliser, Watering, Disease control, Structure and pruning. Frontend first: the backend (Ollama, cache table, endpoint) comes after the whole frontend is finished.

**What was done (2026-11-11, branch `feature/ai-care-guide` from `feature/plant-profile`, commit `340fc28`)**
- `includes/care-guide.php` (new): `care_guide_sections()` (the five keys, labels, icons), `care_guide_species($plant)` (which name the guide is for), `normalise_species()` (the cache key).
- `partials/care-guide.php` (new): the card, with five states: `ready`, `generating`, `offline`, `invalid`, `no-species`.
- `includes/mock-data.php`: `$mock_care_guides`, eight rows shaped like the future `care_guides` table. Pilea (Penny) and Philodendron (Heidi) have no row, so they show *Generating*.
- `public/plant.php`: works out the state and prints the card under the species guide. `&guide=…` previews the other states.
- `public/assets/css/plant.css`: one "AI care guide" block, so the look is easy to change.

**Why this way**
- **Stage F rule:** pages read mock arrays shaped like future rows. `guide_json` is stored as a JSON *string*, as MySQL will hold it, so the page already calls `json_decode()` like stage B will.
- **Cache key:** a species from our list uses its scientific name, so "Tomato" and "Solanum lycopersicum" share one guide. `normalise_species()` trims, lower-cases and squeezes spaces, so `"Tomato "` and `"tomato"` match.
- **One swap point:** later the ML identifier can supply the species; only `care_guide_species()` changes.
- **States in the URL** (`&guide=offline`), like `identify.php?offline`, so every state can be shown and tested without a backend.
- **Generating state:** the five labels stay and grey bars stand where the text will appear, so the user sees what is coming. `role="status"` makes screen readers announce it, and the pulse stops for people who ask for less motion.
- **Owner only:** Regenerate changes a guide that every member with that species shares. Visitors see the guide but no button.
- **Escaping:** every AI sentence goes through `e()`: AI text is treated like text a stranger typed.
- Wording: "fertiliser" (British, like the rest of the site); the JSON key stays `soil_fertilizer` as planned.

```php
function normalise_species($name)
{
    return mb_strtolower(preg_replace('/\s+/u', ' ', trim($name)));
}
```

**Planned for stage B (agreed in the proposal, not built):** `care_guides` table · `includes/db.php` · `includes/llm-client.php` (Ollama now, Gemini later, settings in config) · `public/api/care-guide.php` (takes the **plant id**, not a typed name) · `care-guide.js` with `fetch()` and `textContent` · `session_write_close()` before the slow call · Regenerate as an owner-only POST with a CSRF token · validate all five keys before saving. Ollama has no `llama3.2` yet (installed: qwen3.5:4b, qwen3.8:27b, llama3, gemma4:12b).

**Checked:** PHP lint; no PHP warnings in all five states, as owner and visitor; screenshots at 375 px (ready, generating, offline) and 1280 px.

> [!tip] What to remember
> - A **cache** saves a slow answer once and reuses it. The key must be normalised, or the same thing is saved twice.
> - Plan every state of an async feature (loading, success, each failure) before building it.
> - AI output is untrusted text: escape it like user input.

**Check yourself**
1. Why does the guide use the scientific name as its cache key?
2. Why is `guide_json` a JSON string in the mock data and not a PHP array?
3. Why can visitors not see Regenerate?

> [!success]- Answers
> 1. Common names vary ("Tomato", "tomato plant"); the scientific name is unique in `plant_species`, so every tomato shares one saved guide and the AI is called once.
> 2. MySQL stores it as text. Keeping the mock the same shape means the page already decodes it, so stage B only swaps the data source.
> 3. One guide is shared by everyone whose plant has that species. Letting anyone regenerate would let a stranger replace it and keep the computer busy with slow AI calls.

### Step 7.3 — Plant Profile follows my sketch: care guide under the name
> [!important] Goal (CORE-18, UI half)
> Rearrange P05 to match my hand sketch (tablet, 11 Nov): the AI care guide right under the name, the buttons below it, the Care tab crossed out.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `db04923`)**
- Right column order is now: name → species → status line → **AI care guide** (compact list) → **Log update · Health check · List for exchange · ⋯**.
- `partials/care-guide.php`: the card became a list: a small icon as the bullet, then **Light:** and its sentence on the same line. The heading is a small "✦ AI CARE GUIDE" label. The note and **Regenerate** (a text button, 44 px tall) share the bottom line.
- `public/plant.php`: the Care tab is gone, with the species facts list (Light, Water, Humidity, Pets, Usual schedule) and the green tip box. The `$tabs` / `?tab=` code was removed. "Story" is now an `<h2>` drawn like an active tab.
- `public/assets/css/plant.css`: the Care-tab styles were deleted and the AI guide block rewritten.

**Why this way**
- The thing people come to a plant page for, "how do I look after it?", is now the first thing under its name, with no click needed.
- Two care sources (species facts and the AI guide) said similar things twice. Keeping one removes the repeat. The AI guide is the richer one: it also covers soil, disease and pruning.
- One section does not need a tab bar. A heading is simpler and still gives the page the same look.
- I kept the small "AI care guide" label and the "AI-generated guidance" note, although the sketch has no title. The site always says when AI wrote something (the "AI suggests, the user decides" rule).
- Old links with `?tab=care` still work: the page ignores the unknown parameter.

**Consequences for the P05 plan:** step 4 (compact Care tab) is no longer needed. The health banner (step 2) will sit between the status line and the care guide. `plant_species.light/water/humidity/pet_safe/notes` are no longer shown on P05 (still used elsewhere, e.g. P07's care chips).

> [!tip] What to remember
> - A layout change can delete code: removing the tabs removed the whole `?tab=` logic and two CSS sections.
> - Put the most-wanted information highest on the page.

**Check yourself**
1. Why is "Story" now a heading and not a tab?
2. What happens to an old bookmark `plant.php?id=47&tab=care`?

> [!success]- Answers
> 1. A tab bar is for switching between sections. With only one section left there is nothing to switch to, so a heading is the right element; it keeps the tab's underline look so the design stays the same.
> 2. Nothing breaks: the page no longer reads `tab`, so it shows the normal page with the care guide at the top.

### Step 7.4 — AI care guide back to the boxed card look
> [!important] Goal (CORE-18, UI half)
> Keep the guide under the name (step 7.3) but give it the card look from step 7.2, which I preferred over the compact list.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `5077ea9`)**
- `partials/care-guide.php` and its CSS block were restored from commit `340fc28` with `git show 340fc28:<file>`: the title with ✦, "For *Solanum lycopersicum* · 8 Nov 2026", one row per section (label beside text on tablet and up, stacked on a phone), dividers, and a footer with the note and **Regenerate**.
- Links no longer add `&tab=care` (the tab is gone). The offline text no longer says "the care facts above still apply" (they are gone too).
- Desktop only: the label column is 205 px instead of 220 px, because the card now shares the row with the photo. 205 px is just wide enough for "Structure and pruning" on one line.

**Why this way**
- Git keeps every committed version, so an earlier design can be brought back exactly instead of being rebuilt by hand.
- One media query per breakpoint changes only what that width needs; the phone layout (stacked) was not touched.

**Problems and fixes**
- At 190 px "Structure and pruning" wrapped onto two lines on desktop; 205 px fixed it. Checked at 375, 1024 and 1280 px.

> [!tip] What to remember
> - `git show <commit>:<path>` prints a file as it was in that commit.
> - A component that moves to a narrower place may need a different column width there.

**Check yourself**
1. How did we get the old card back without retyping it?
2. Why does the label column change only from 1024 px?

> [!success]- Answers
> 1. With `git show 340fc28:partials/care-guide.php` (and the same for the CSS), then small edits for the new position.
> 2. Below 1024 px the card is full width and has room for 220 px labels; from 1024 px it shares the row with the photo, so the text column needs the space.

### Step 7.5 — Two-column layout, health alert and quick facts
> [!important] Goal (CORE-18, UI half)
> Fix the layout: a short left column beside a long right one, actions below the guide, the plant's disease hidden in the Story, a tab with only one tab, and uneven spacing.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `d5b1b15`)**
- **Desktop (1024 px+):** left = photo → **Quick facts** → Reminders (visitors: owner card). Right = name → species → status → **actions** → **health alert** → AI care guide. **Story** full width underneath.
- **Phone:** one column: photo → name/actions → alert → guide → quick facts → reminders → story.
- `partials/health-alert.php` (new): "Early blight found · Health check on 10 Nov 2026 · High confidence, 92% · See how to treat it →". The link jumps to the guide's **Disease control** card (`#guide-disease-control`).
- **Quick facts** card: Collection (owner only), With you / Growing since, Visibility (owner only), Likes. These left the meta line and the Reminders card, so nothing is shown twice.
- AI care guide: five small cards in a two-column grid from 768 px; the fifth spans both columns.
- "Story" is a plain `<h2>`, no tab styling.
- `variables.css`: `--space-64` added. `plant.css` rewritten in commented sections, with spacing only from the scale.

**Why this way**
- **Grid areas:** the HTML is written in the phone order (gallery, info, aside). On a desktop, `grid-template-areas: "gallery info" "aside info"` moves the aside under the photo, so one HTML order serves both layouts with no duplicated markup.
- **Actions first:** the most-used buttons belong right under the status, not below reference content.
- **Health alert rule:** it shows only while the plant is Needs attention or Sick **and** its newest AI check found a disease. Goldie (marked by hand) and Stripes (never checked) get none. It always shows the confidence word with the number, and the tint matches the badge (red/amber), so colour is never the only signal. It replaces the bigger health banner planned in step 2.
- **No sticky column:** after the move both columns are about the same height. A sticky column taller than the screen would hide its bottom (Reminders) until the end of the section.
- **Breakpoint 1024, not 900:** CLAUDE.md fixes 768 and 1024.
- **Kept the spacing names** (`--space-16` …) instead of `--space-1 … --space-8`: renaming would touch all 18 stylesheets.

```css
@media (min-width: 1024px) {
  .plant-top {
    grid-template-columns: 5fr 7fr;
    grid-template-areas:
      "gallery info"
      "aside   info";
  }
}
```

**Problems and fixes**
- The visitor view showed "Growing since" twice (meta line + Quick facts); removed from the meta line.

**Checked:** PHP lint; no warnings for Monty, Tommy, Goldie, Stripes, Penny, Pearl, the guide previews and the visitor view; screenshots at 375, 1024 (visitor) and 1280 px.

> [!tip] What to remember
> - Write the HTML in the phone order, then rearrange for desktop with `grid-template-areas`.
> - One spacing scale (4 … 64) makes a page look calm; random pixel values make it look uneven.
> - Put actions near the top, reference content lower.

**Check yourself**
1. How can the Quick facts card be under the photo on a desktop but after the care guide on a phone, with one HTML?
2. When does the health alert appear?
3. Why was sticky left out?

> [!success]- Answers
> 1. The HTML order is gallery → info → aside (the phone order). From 1024 px a CSS grid with named areas places "aside" in the left column under "gallery", while "info" spans the right column.
> 2. When the plant's status is Needs attention or Sick and its newest AI health check found a disease (not "healthy").
> 3. After moving Quick facts and Reminders left, both columns are about equally tall, so there is no empty space to fill; and a sticky column taller than the screen hides its own bottom part.

### Step 7.6 — Health alert removed
> [!important] Goal (CORE-18, UI half)
> I decided the page does not need the health alert from step 7.5.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `40dfa0e`)**
- `partials/health-alert.php` deleted with `git rm`.
- `public/plant.php`: the `$health_alert` lookup and the partial call removed. The care guide now follows the action buttons directly.
- `public/assets/css/plant.css`: the health alert block removed.
- `partials/care-guide.php`: the card ids (`guide-disease-control` …) removed, because only the alert's link used them.
- `partials/README.md`: the row removed.

**Why this way**
- The Sick badge next to the name and the Health check entry at the top of the Story already say what is wrong. The alert repeated them.
- Removing a feature means removing *all* its parts (file, data lookup, markup, CSS, docs, and anything added only for it), so no dead code is left for someone to wonder about in the viva.

> [!tip] What to remember
> - Before deleting, search for every name the feature used (`health_alert`, `health-alert`, `guide-disease`), then search again afterwards to prove nothing is left.

**Check yourself**
1. Why were the care guide's card ids removed too?

> [!success]- Answers
> 1. They existed only so the alert's "See how to treat it" link could jump to the Disease control card. With no link, they were unused code.

### Step 7.7 — Action bar, aligned columns, tiles, story width, links
> [!important] Goal (CORE-18, UI half)
> Refine P05: actions in a full-width bar, both columns ending at one line, softer guide tiles, a narrower story, one link style.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `a2a98b6`)**
- **Action bar:** the buttons left the right column. They now sit in `.plant-action-bar`, full width under both columns and above "Story": a thin top line, 32 px above, 48 px below, 12 px between buttons. Under 480 px, Log update takes a full row.
- **Aligned columns (desktop):** the Reminders card (visitors: owner card) and the AI care guide end at exactly the same line. Measured: e.g. 1005 / 1005 px for Tommy, 846 / 846 px for Penny.
- **Guide tiles:** no border; cream background; 24 px padding; tiles in a row are equally tall.
- **Story:** at most 760 px wide. An entry without a photo gets a 48 px leaf tile, so every text starts at the same line. Story images got `width`, `height` and `loading="lazy"`.
- **Links (whole site):** no underline, underline on hover (`components.css`).

**Why this way**
- **Equal bottoms without fixed heights:**
  1. The grid uses `align-items: stretch`, so both columns become as tall as the taller one.
  2. The left column is a flex column, and its last card has `flex: 1`, so it grows into the space left.
  3. The right column is a flex column too, and the guide has `flex: 1`, so when the left side is taller the guide grows instead.

  Content stays at the top because nothing centres it. It only applies from 1024 px.
- **`a:where(:hover)`:** `a:hover` would beat class rules like `.btn` (more specific), so every button, menu item and card would get underlined on hover. `:where()` adds no specificity, so components that set their own `text-decoration` keep it.
- **Placeholder tile in the story:** reserving the same space in every entry is simpler and more robust than adding padding only to entries without photos.

```css
@media (min-width: 1024px) {
  .plant-top { align-items: stretch; }
  .plant-aside > :last-child { flex: 1; }   /* left column: last card grows */
  .plant-info { display: flex; flex-direction: column; }
  .plant-info .ai-guide { flex: 1; }        /* right column: the guide grows */
}
```

**Problems and fixes**
- My Python edit of the action block first cut at the wrong `</div>` (a search for `"  </div>"` also matched the end of `"          </div>"`). PHP lint caught it ("unexpected token else"), and the leftover lines were removed.
- `components.css` also holds my own uncommitted `.chip-add` change. Only the link hunks were staged (`git apply --cached` with a filtered diff), so my change stays uncommitted.

**Open choices (raised with me):** tile padding 24 instead of the asked 20 (20 is not on our spacing scale); radius `--radius-input` (10 px) instead of 12 (no 12 px token); breakpoints 1024/480 instead of 900; on phones the action bar now comes after Reminders, so it is far down the page.

> [!tip] What to remember
> - Equal-height columns: grid `stretch` + flex column + `flex: 1` on the part that should grow.
> - Specificity decides which rule wins; `:where()` is a way to write a default that never wins against a component.

**Check yourself**
1. How do the two columns end at the same line without any fixed height?
2. Why `a:where(:hover)` instead of `a:hover` for the underline?

> [!success]- Answers
> 1. The grid stretches both columns to the taller one's height; inside each column a flex layout lets one card (Reminders on the left, the guide on the right) take the leftover space with `flex: 1`.
> 2. `a:hover` has specificity (0,1,1), more than a single class like `.btn` (0,1,0), so it would underline buttons on hover. `:where()` counts as zero, so `a:where(:hover)` is (0,0,1) and any component's own rule wins.

### Step 7.8 — Health check and List for exchange beside the name
> [!important] Goal (CORE-18, UI half)
> Put Health check and List for exchange at the top right of the name block, as in my sketch.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `02b36f0`)**
- `public/plant.php`: the name, species and status line are wrapped in `.plant-identity` inside a new `.plant-head`. Next to it is `.plant-head-actions` with the two buttons (owner only).
- The two buttons were **moved**, not copied: the action bar now holds Log update and the ⋯ menu only, so no button appears twice.
- `public/assets/css/plant.css`: from 768 px, `.plant-head` is a non-wrapping flex row and the buttons stack in a column on the right, left-aligned text. On a phone the row wraps, so the buttons sit side by side under the status line.

**Why this way**
- Health check and List for exchange are about *this plant*, so they sit beside its name. Log update is the main daily action and keeps its own bar.
- `flex-wrap` lets one piece of HTML be "beside" on wide screens and "underneath" on narrow ones.

> [!tip] What to remember
> - When a control moves, remove it from the old place; a page with the same button twice makes users wonder whether they do different things.

**Check yourself**
1. How do the buttons end up under the status line on a phone but beside the name on a tablet?

> [!success]- Answers
> 1. `.plant-head` is a flex row that may wrap. On a phone there is not enough width, so the button group wraps onto the next line. From 768 px `flex-wrap: nowrap` keeps it on the right, and `flex-direction: column` stacks the two buttons.

### Step 7.9 — Three small buttons as tall as the name block; badge and action bar removed
> [!important] Goal (CORE-18, UI half)
> Put Log update with the other two buttons beside the name, make all three smaller and line them up with the top and bottom of the name block; drop the "Sick" badge.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `cc03350`)**
- `.plant-head-stack`: **Log update** (green), **Health check**, **List for exchange**, all `btn-sm`. The ⋯ menu sits just before the stack. Visitors get their Like button in the same place.
- The status badge left the name block, which now reads name · species · "Updated yesterday".
- The action bar is gone (nothing was left in it). The Story follows the columns again (48 px; 64 px on a desktop).

**Why this way**
- **Lining up exactly:** from 768 px `.plant-head` uses `align-items: stretch`, so the button column is as tall as the name block. The stack uses `justify-content: space-between`, so the first button touches the top line and the last one the bottom line.
- **Measured, not guessed:** at 36 px the three buttons made 116 px, taller than the 107 px name block, so the block would have grown. At 32 px (mouse screens only; phones keep 44 px touch targets) they fit. Measured result: name top 134 px, "Updated" bottom 241 px, stack 134–241 px.
- **⋯ menu:** `align-self: flex-start` keeps it a small round button at the top instead of stretching it.

**Problems and fixes**
- The first try ended 3 px short: the stack's parent had `align-items: flex-start`, so only the parent was stretched, not the stack inside it. Setting `align-items: stretch` on the parent fixed it.

> [!tip] What to remember
> - `stretch` only works one level down: every flex container in the chain must let its child stretch.
> - Check alignment with numbers (`getBoundingClientRect()`), not only by eye.

**Check yourself**
1. Why do the buttons start exactly at the name's top and end at the "Updated" line?
2. Why are they 32 px only from 768 px up?

> [!success]- Answers
> 1. The name block and the button column are flex items of `.plant-head` with `align-items: stretch`, so the column is as tall as the name block; inside it, `justify-content: space-between` pushes the first button to the top and the last to the bottom.
> 2. Below 768 px people use fingers, which need 44 px targets; a mouse is precise, so smaller buttons are fine there.

### Step 7.10 — ⋯ menu removed from the plant page
> [!important] Goal (CORE-18, UI half)
> Keep only the three stacked buttons beside the plant's name.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `5bb9122`)**
- `public/plant.php`: the `partial('plant-menu', …)` call beside the stack is gone.
- `public/assets/css/plant.css`: the two rules that placed the menu (`order`, `align-self`) are gone.
- `partials/plant-menu.php` itself stays: My Garden's plant cards and stack popup still use it.

**Why this way**
- Remove the *use*, not the shared partial: deleting a partial that other pages include would break them.
- Side effect to know for the viva: on the plant page there is now no way to **Move to collection** or **Delete** (Delete never worked here anyway, its confirmation popup only exists on My Garden). Both remain on My Garden.

> [!tip] What to remember
> - Before deleting a partial, search which pages include it (`partial('plant-menu'`).

**Check yourself**
1. Why was `partials/plant-menu.php` not deleted?

> [!success]- Answers
> 1. My Garden's plant cards and stack popup still include it; only the plant page stopped using it.

### Step 7.11 — Story by type, filters, Done on reminders, Edit button (Plant Profile)
> [!important] Goal (CORE-18, CORE-19, CORE-23, UI half)
> Make the Story show what kind of thing happened, let the user filter it, and tick off reminders without a reload. Frontend only: nothing is saved yet.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `d3579d7`)**
- **Header:** green **Edit** button top right (→ `plant-form.php?id=…`). **Health check** and **List for exchange** as small outline buttons under "Updated …". Log update is gone; story entries are now added on the Edit page.
- **`partials/story-entry.php` (new):** draws each entry by its `type`:
  - care: one line, "🌧 Watered · 10 Nov", in the task's colour
  - note / photo: a card, with the photo 160 px
  - health_check: the AI result with the confidence word ("High confidence, 92%")
  - milestone: a small sage card
  - A status badge appears only on entries that have a status.
- **Story panel:** desktop: a sticky card beside the timeline with **All / Photos / Health / Care** and stats (watered this month, last update, number of entries). Phone: the filters as a sideways-scrolling chip row, no stats.
- **Reminders:** labels Today / Tomorrow / In N days / Overdue (red) and a **Done** button: a care line appears at the top of the Story, the date moves on, the stats follow, the button stays pressed.
- **Mock data:** every `health_logs` row got `type` and `care_task`, plus 6 care rows. New helpers `care_done_label()` and `due_label()`. `header.php` now accepts `$page_js`. New script `public/assets/js/story.js`.

**Why this way**
- **One partial per entry:** the same markup is used for every entry, and stage B only changes where the rows come from.
- **`data-*` attributes for filters:** each `<li>` says what it is (`data-kind`, `data-photo`, `data-health`). The script only toggles `hidden`, so without JavaScript every entry still shows.
- **`<template>`:** the Done button copies a ready-made, empty care line from a `<template>` tag and fills it with `textContent`. No HTML is built from strings.
- **Proposed database change (not applied):** `health_logs.type ENUM('note','photo','care','health_check','milestone')`, `health_logs.care_task` (NULL), and `status` must allow NULL. My prompt mentioned a "Part C" for this that I never sent; it still has to go into `schema.sql` and the data dictionary in stage B.
- **Kept from the design system:** status words Thriving / Stable / Needs attention / Sick (my prompt said Healthy / Stable / Sick); overdue in **red**, not amber (red = overdue is a fixed rule).

**Checked:** PHP lint, no warnings for 6 plants and the visitor view; a scripted click test: Done adds "Watered · 11 Nov", the date becomes "In 2 days", the stats go 2 → 3 waterings and 6 → 7 entries, the screen-reader line says "Watered. Next on 13 Nov."; the filters show care 4, photos 2, health 3. Screenshots at 375 and 1280 px.

```js
// story.js: a filter only hides and shows what PHP already printed
story.querySelectorAll('.story-entry').forEach((entry) => {
  entry.hidden = !matchesFilter(entry);
});
```

> [!tip] What to remember
> - In stage F, "saving" means changing the page; after a reload the mock data is back.
> - `<template>` holds HTML the browser does not show; JavaScript copies it with `content.firstElementChild.cloneNode(true)`.

**Check yourself**
1. What happens to a Done click after you reload the page, and why?
2. Why do the filters work through `hidden` instead of asking the server?

> [!success]- Answers
> 1. It is gone: stage F only changes the page in the browser. Stage B sends it to the server with `fetch()` first and saves it in `health_logs` and `care_reminders`.
> 2. All entries are already on the page, so hiding some is instant and needs no request; without JavaScript the full Story still shows.

### Step 7.12 — "Add to Tommy's story" card on Edit plant
> [!important] Goal (CORE-16, CORE-19, UI half)
> Record updates (watering, notes, milestones, photos) on the Edit page, without needing the main Save button.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `63ba3e3`)**
- **Card `#story-update`:** in the left column under the photo on a desktop (that space was empty), after Plant details on a phone.
- **Fields:** chips Watered · Fertilised · Pruned · Repotted · Note · Milestone; "What's new?" (required for Note and Milestone); optional photo with a preview; optional status; date (today, not in the future).
- **Add to story:** the entry appears in the "Latest entries" list without a reload, the message says "Added to Tommy's story." (plus "Next watering on 13 Nov." for Watered or Fertilised when that reminder exists), and the form clears.
- **Latest entries:** the 3 newest, each with **Edit** (fills the form, button becomes "Save entry") and **Delete** (a popup that names the entry). An AI health check can be deleted but not edited.
- **Info box:** "Use Add to Tommy's story on this page to record updates. They will appear in the plant's Story."
- **Navbar:** the bar is `position: sticky`; jumping to `#story-update` stops the card 15 px below it (measured: card top 80 px, bar bottom 65 px).

**Why this way**
- **A form may not sit inside another form**, and the whole Edit page is one form. So the card's fields point to a separate empty `<form id="story-form">` after it with `form="story-form"`. They belong to that form, Save changes does not send them, and the card works on its own.
- **Grid areas with `:has()`:** the HTML is in phone order (photo, details, story). On a desktop `grid-template-areas: "side main" "story main"` moves the card under the photo. `:has(.story-update)` limits this to the Edit page, because the Add page has no story card and an unknown area name would break its grid.
- **Safety now:** everything typed goes on the page with `textContent`; the test typed `<b>First</b>` and it showed as text, not bold. The photo's type and size are checked in JavaScript; stage B checks them again on the server with `finfo`.

**Problems and fixes**
- A Bash heredoc containing apostrophes failed again ("unexpected EOF"); the JavaScript was added with the Edit tool instead (see [[plantfolio-local-verification]]-style note: write files with the editor, not heredocs).
- The first CSS put `grid-area` on the photo and details columns for every page, which would have broken Add plant; it was limited to the Edit page with `:has()` and Add plant was checked again.

> [!tip] What to remember
> - The `form` attribute connects a field to a form anywhere on the page, by its id.
> - `<dialog>` with `<form method="dialog">`: each button closes the popup with its `value`, readable as `dialog.returnValue`.

**Check yourself**
1. Why does the story card not send its fields when you press Save changes?
2. How does the script know whether you pressed Delete or Cancel in the popup?
3. Why is the photo checked in JavaScript if the server checks it again?

> [!success]- Answers
> 1. Its fields have `form="story-form"`, so they belong to the separate story form, not the main form around the page.
> 2. The popup's form uses `method="dialog"`; each button has a `value` ("delete" or "cancel"), and the dialog's `close` event reads `returnValue`.
> 3. JavaScript gives instant feedback, but it can be switched off or bypassed, so only the server's check (`finfo`, 5 MB) is a real protection.

### Step 7.13 — The three buttons stacked again, Edit on top
> [!important] Goal (CORE-18, UI half)
> Go back to the stacked buttons beside the name (step 7.9), now **Edit · Health check · List for exchange**.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `7ba288c`)**
- `public/plant.php`: one `.plant-head-stack` beside `.plant-identity` holds Edit (green), Health check and List for exchange; visitors get only Like.
- `public/assets/css/plant.css`: the stack CSS from step 7.9 came back (taken from commit `5bb9122` with `git show`). A visitor's single button stays at the top with `.plant-head-stack:has(> :only-child)`.
- Measured: stack 134–241 px, the same as the name block, at 1280, 1024 and 768 px. Phone: the buttons wrap onto a row under the name.

> [!tip] What to remember
> - When a design comes back, take the old code from git instead of rewriting it; it was already tested.

**Check yourself**
1. Why does a visitor's Like button not stretch down to the "Updated" line?

> [!success]- Answers
> 1. `justify-content: space-between` would push a single button to the top anyway, but `:has(> :only-child)` sets `flex-start` to make that explicit; the column is as tall as the name block, the button keeps its own 32 px height.

### Step 7.14 — One Add a reminder popup everywhere; Add to story becomes a popup
> [!important] Goal (CORE-19, CORE-22, UI half)
> Use the Dashboard's "Add a reminder" popup on the plant page and Edit plant too; turn "Add to Tommy's story" into a popup on the plant page; remove the Story filter panel; shrink the gap above the Story.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `acf2514`)**
- **Shared reminder code (refactor):** the popup and its checks were moved out of `dashboard.php` into:
  - `includes/reminder-form.php`: `reminder_defaults()`, `reminder_form_save()` (checks + saving), `reminder_undo()`, `reminder_toast_take()`, `first_due()`
  - `partials/reminder-dialog.php`: the popup itself
  - `partials/reminder-toast.php`: the toast with Undo

  Its CSS moved from `dashboard.css` to `components.css`. The Dashboard now calls these and works exactly as before (tested: add, toast, Undo, five error messages).
- **Plant page:** "+ Add" in Reminders opens the popup titled "Add a reminder for Tommy", with no "Which plant?" step (the plant is a hidden field). After saving, the page reloads with the toast and the new reminder in the card.
- **Edit plant:** an "Add a reminder" button under Care reminders opens the same popup. Reminders for tasks the two switches don't cover (mist, prune …) are listed there.
- **Add to story popup** on the plant page ("+ Add to story"): the same fields as the old card. The new entry goes into the timeline by date, Watered/Fertilised also moves that reminder, and a toast says "Added to Tommy's story. Next watering on 13 Nov." The card, its form and its delete popup were removed from Edit plant. The info box there links to `plant.php?id=47&story=1`, which opens the popup at once.
- **Removed:** the Story filter/stats panel (HTML, CSS, JS). **Gap** above the Story: 64 → 32 px.

**Why this way**
- **One copy, not three:** copying 140 lines of popup and 60 lines of checks into two more pages would mean three places to fix every bug. A partial plus an include file keeps one copy.
- **Ownership check stays on the server:** on a plant's page `reminder_form_save()` only accepts that plant's id. Tested: sending plant 42 through Tommy's page answers "Choose one of your plants."
- **The popup sits outside Edit plant's main form**, because a form may not sit inside another.
- **Close buttons use `formmethod="dialog"`:** they close the popup without sending the form; story.js lets those through and stops only the real "Add to story".

```php
// plant.php: the same function the Dashboard uses, with only this plant allowed
[$reminder_values, $reminder_errors] = reminder_form_save([$plant['id'] => $plant]);
```

**Problems and fixes**
- Moving the Dashboard toast, my script stopped at the toast's inner `<?php endif; ?>` and left four lines behind; PHP lint caught it ("unexpected endif") and the lines were removed.
- `data-dialog-close` does not exist in app.js; the close buttons use `formmethod="dialog"` like every other popup.

**Checked:** PHP lint and no warnings on the Dashboard, plant pages and Add/Edit plant. curl tests: add a reminder from all three pages, Undo, errors, wrong plant refused, "Save changes" still goes to the plant page. Scripted clicks: Add to story (error, care, milestone with `<b>` shown as text, a back-dated note in the right place, Cancel), Done. Screenshots at 1280 px.

> [!tip] What to remember
> - When a second page needs the same feature, move it into a partial (HTML) and an include file (PHP functions) instead of copying it.
> - Keep the safety check on the server, even when the page hides the choice.

**Check yourself**
1. Why does the plant page still check the plant id if the popup sends it as a hidden field?
2. What did moving the popup into a partial change for the Dashboard?

> [!success]- Answers
> 1. A hidden field can be changed in the browser's developer tools; only the server's check (the id must be in the allowed list) is a real protection.
> 2. Nothing visible: it calls `partial('reminder-dialog', …)` and `reminder_form_save()` instead of having the code inline, and the tests showed it behaves the same.

### Step 7.15 — Edit plant shows only the plant's details
> [!important] Goal (CORE-16, UI half)
> Remove the info box and the Care reminders section from Edit plant: reminders and the story now live on the plant's page.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `9fe8a7b`)**
- `public/plant-form.php`:
  - On edit, the info box, the Care reminders section (Water/Fertilise switches, other reminders, "Add a reminder") and the Add a reminder popup with its toast are gone.
  - Its reminder POST handling and the `$other_reminders` list are gone too.
  - The intro now reads "Change the details, photo, collection or who can see it."
- **Add plant keeps Care reminders:** a new plant still needs its first schedule.
- `public/plant.php`: the Reminders card's "+ Add" link used to fall back to Edit plant's reminders without JavaScript. It now points to `plant.php?id=…&reminder=1`, which opens the popup on load (new optional `$open` in `partials/reminder-dialog.php`).
- Comments and READMEs updated: the shared reminder popup is used by the Dashboard and the Plant Profile.

**Why this way**
- Each place has one job: Edit plant changes *what the plant is* (name, species, collection, photo, visibility); the plant's page records *what happens to it* (story, reminders).
- The shared reminder code from step 7.14 stays as it is; only Edit plant stopped calling it.

> [!tip] What to remember
> - When a feature leaves a page, also remove its server code and fix every link that pointed to it.

**Check yourself**
1. Why does Add plant still have Care reminders when Edit plant does not?

> [!success]- Answers
> 1. A new plant has no page yet where reminders could be added, so the form sets its first schedule; after that, the plant's page manages them.

### Step 7.16 — Add a photo popup and a scrolling thumbnail strip
> [!important] Goal (CORE-17, CORE-18, UI half)
> The Add tile opens a popup to add a photo; the new photo shows on the plant's page; many photos scroll sideways.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `4174daa`)**
- **Popup "Add a photo of Monty":**
  - a drop area (click to choose, or drag a photo in) that shows the chosen photo
  - "JPG or PNG, up to 5 MB." with friendly errors ("Choose a photo first.", "Choose a JPG or PNG photo of 5 MB or less.")
  - "Taken on" (today)
  - Cancel / **Add photo**
- **After Add photo:** the photo becomes the **first thumbnail** and the **large photo**, the toast says "Photo added to Monty's gallery", and the popup closes and clears.
- **Thumbnail strip:** every photo, not only four, 80 px squares in a row that **scrolls sideways** (`overflow-x: auto`, `scroll-snap`). The Add tile sits outside the strip, so it is always visible.
- `app.js`: one click listener on the whole gallery (event delegation), so a thumbnail added later also works.
- `story.js` → **`plant.js`** (renamed with `git mv`): it now holds all the plant page's scripts.

**Why this way**
- **Event delegation:** `gallery.addEventListener('click', …)` with `event.target.closest('[data-photo-src]')` catches clicks on thumbnails that did not exist when the page loaded; a listener per thumbnail would miss new ones.
- **`URL.createObjectURL(file)`** gives the chosen file a temporary address in this browser tab, so it can be shown without uploading. It disappears on reload: stage F saves nothing.
- **Stage B:** `fetch()` sends the photo; `includes/upload.php` checks it with `finfo` (JPG/PNG), 5 MB, gives it a random name, resizes it to 1600 px and saves a `plant_photos` row. The JavaScript checks are only for quick feedback.
- **Drag and drop:** the dropped file is put into the hidden file input (`input.files = event.dataTransfer.files`), so drop and click take the same path.

**Problems and fixes**
- My first test said the error messages did not appear. The page has two forms using `data-photo-hint` (Add to story and Add a photo), and the test searched the whole page; the real code searches only inside its own form. With the test fixed, everything passed.

> [!tip] What to remember
> - Elements added later need event delegation (one listener on a parent).
> - `overflow-x: auto` + `scroll-snap-type` makes a simple swipeable row without any library.

**Check yourself**
1. Why does the new thumbnail respond to clicks even though it was created after app.js ran?
2. Why does the added photo disappear after a reload?

> [!success]- Answers
> 1. The click listener is on the gallery around it, not on each thumbnail; clicks bubble up to the gallery, which looks for the nearest `[data-photo-src]`.
> 2. Stage F only shows it in the page through a temporary object URL; nothing is uploaded or saved until stage B.

### Step 7.17 — The Reminders card works like Today's care
> [!important] Goal (CORE-22, CORE-23, UI half)
> Make the plant page's Reminders box look and behave like the Dashboard's Today's care box.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `635350c`)**
- **Same rows:** `partials/care-row.php` (the Dashboard's row) is used on the plant page in a new **compact** look: no photo or plant name; the task is the title, the due tag sits under it, and "Mark done" is on the right.
- **Same tags:** "Due today" (terracotta), "Overdue 2 days" (red), and a grey "Tomorrow" / "In 7 days". A "**N due**" count sits next to the title, and the dashed **+ Add a reminder** button is at the bottom.
- **Same Mark done:** a real form (POST), handled by the new shared `reminder_mark_done()` in `includes/reminder-form.php` (moved out of `dashboard.php`, together with `next_due_after()`). It:
  1. moves the date on by the reminder's rule (a one-off reminder is finished)
  2. adds a **care line to the Story** ("Watered · 11 Nov"), now also when you press Mark done on the Dashboard
  3. leaves the **toast with Undo** ("Watered Monty · next on 18 Nov"). Undo puts the date back *and* removes the Story line.
- **Preview storage:** new `mock_add_health_log()` / `mock_remove_health_log()` keep the Story lines in the session (`preview_health_logs`, reset with the other preview lists); the plant's "Updated today" follows.
- **Removed:** the JavaScript-only Done buttons, `due_label()`, and `care_done_label()`, which did the same job as the older `task_done_label()`.

**Why this way**
- **One behaviour, one code:** the plant page and the Dashboard now run the same function, so they can't drift apart, and stage B changes only one place (UPDATE `care_reminders` + INSERT `health_logs` in one transaction).
- **Server form instead of JavaScript:** a Mark done must survive a reload and offer Undo, like on the Dashboard. A plain POST + redirect does that and works without JavaScript.
- **Safety:** the page passes only its own plant's reminders to `reminder_mark_done()`. Tested: sending Tommy's reminder through Monty's page changes nothing.

**Problems and fixes**
- A Bash heredoc failed again (apostrophes in the code), so the edits were written as script files first.
- I found two helpers doing the same thing (`care_done_label()` from step 7.11 and the older `task_done_label()`); the newer one was removed.

> [!tip] What to remember
> - Before writing a helper, search `helpers.php` for one that already does it.
> - Undo must take back *everything* an action did (here: the date and the Story line).

**Check yourself**
1. What three things happen when you press Mark done, and what does Undo do?
2. Why is Mark done a form (POST) and not just JavaScript?

> [!success]- Answers
> 1. The reminder's next date moves on, a care line is added to the plant's Story, and a toast appears; Undo restores the old date and removes that Story line.
> 2. It changes data, which must be saved on the server (in stage F the session) so it survives a reload and can be undone; a POST + redirect also works without JavaScript and cannot be repeated by refreshing.

### Step 7.18 — "+ Add" back on top; no empty band in the care guide
> [!important] Goal (CORE-18, UI half)
> Put "+ Add" back at the top of the Reminders card, remove the empty space inside the AI care guide, keep both columns ending on one line.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `c7817cd`)**
- `public/plant.php`: the dashed "+ Add a reminder" button at the bottom of Reminders became the **"+ Add"** link in the card's head again; it opens the same popup.
- `public/assets/css/plant.css`: `.ai-guide-list` changed from `align-content: start` to **`align-content: stretch`**.

**Why this way**
- When the left column is taller, the guide card stretches to the same bottom line (step 7.7). With `start`, the extra height stayed empty above the footer. With `stretch`, the grid rows share it, so the tiles grow a little instead.
- **Measured** at 1280 and 1024 px for Tommy, Monty, Penny and the visitor view: the two cards end on the same pixel, and the space under the last tile is exactly the list's 24 px padding.

> [!tip] What to remember
> - `align-content` decides what a grid does with extra height: `start` leaves it empty at the bottom, `stretch` shares it between the rows.

**Check yourself**
1. Where did the empty band in the care guide come from?

> [!success]- Answers
> 1. The card was stretched to line up with the taller left column, and its tile grid kept its rows at the top (`align-content: start`), so the extra height stayed empty under the last tile.

### Step 7.19 — "New collection" chip stands out
> [!important] Goal (CORE-14, UI half)
> Commit the waiting style change before starting the Community page on a new branch.

**What was done (2026-11-11, branch `feature/ai-care-guide`, commit `766a501`)**
- `components.css`: the dashed **+ New collection** chip on My Garden has a leaf-green border and forest text, and fills with sage on hover.

> [!tip] What to remember
> - Commit (or put aside) unfinished changes **before** creating a new branch, so each branch holds one piece of work.

**Check yourself**
1. Why was this change committed on `feature/ai-care-guide` and not on the new branch?

> [!success]- Answers
> 1. It belongs to My Garden, not to the Community page; committing it first keeps `feature/community` about one thing only.

### Step 7.20 — Swap/Free badge on community plant cards; person-card partial
> [!important] Goal (COM-01, COM-07, UI half) — step 1 of the Community page
> A public plant that is on an open exchange listing shows a small "Swap" or "Free" badge next to its likes; clicking it opens that listing.

**What was done (2026-11-11, branch `feature/community`, commit `cee4943`)**
- `includes/mock-data.php`: every public plant gets **`listing_id`** and **`listing_type`** (the open listing that offers it, or null). Stage B: `LEFT JOIN exchange_listings ON exchange_listings.plant_id = plants.id AND exchange_listings.status = 'open'`.
- `partials/plant-card.php`: with `'community' => true`, the likes and the new badge sit together in `.plant-card-social`. A "Swap or free" listing shows "Swap".
- `partials/member-card.php` was renamed **`person-card.php`** (and its CSS classes), the name the Community spec asked for.

**Why this way**
- The whole card is already one big link to the plant (`.card-link::after` covers the card). A second link on top needs **`position: relative; z-index: 1`** to be clickable above it. A link inside a link is not allowed in HTML, so the badge is a *sibling*, not a child, of the plant link.
- The badge is about 26 px tall; an invisible `::before` frame makes the tap area 44 px, the project's minimum.

```css
.card-media-badges .plant-card-offer {   /* two classes: beats .card-media-badges .badge (position: static) */
  position: relative;
  top: auto;
  left: auto;
  z-index: 1;
}
.plant-card-offer::before { content: ""; position: absolute; inset: -9px -4px; }   /* 44 px tap area */
```

**Problems and fixes**
- First try: the badge ignored `z-index`. An older rule with *more specific* selectors set `position: static`, and `z-index` does nothing on a static box. Fixed by using two classes in the selector.
- Then the badge sat 12 px lower than the likes: `.card-media .badge` also gives badges `top` and `left`, which a *relative* box obeys. Fixed with `top: auto; left: auto` (found in step 7.21's screenshots).

> [!tip] What to remember
> - A link can sit above a stretched card link with `position: relative` + `z-index`; never put a link inside a link.
> - `z-index` only works on a positioned box; check which rule wins in DevTools' "Computed" tab.

**Check yourself**
1. Why does clicking the Swap badge open the listing and not the plant?
2. Where will `listing_id` come from in stage B?

> [!success]- Answers
> 1. The badge is its own link, lifted above the card's stretched link with `position: relative; z-index: 1`, so the click lands on the badge first.
> 2. From a LEFT JOIN of `plants` with the open row of `exchange_listings` for that plant; a plant with no open listing gets NULL.

### Step 7.21 — The Community page: Plants, Exchange and People in one page
> [!important] Goal (COM-01, COM-02, COM-03, COM-07, UI half) — steps 2–5 of the Community page
> Merge Explore and Exchange into `community.php`: one search, three tabs, a filter sidebar that changes with the tab, a sort per tab, the old cards, and an empty state for each tab.

**What was done (2026-11-11, branch `feature/community`, commit `30073ad`)**
Steps 2 to 5 of the plan (Plants tab, Exchange tab, People tab, filters and sort) were built and committed together, because the three tabs share one form, one sidebar and one results bar.
- **`public/community.php`**: header (Community, subtitle, **My exchanges** + **List a plant**; a visitor gets **Log in to list a plant**), the search box, the tab bar *Plants (n) · Exchange (n) · People (n)*, the sidebar, the results bar and the cards.
- **The address holds everything:** `community.php?tab=exchange&q=pothos&type=swap&species[]=2&sort=nearest`. Default tab: plants. Every value is checked against a whitelist; a wrong one falls back to its default.
- **Sidebar per tab:** Plants: Species (ticks with counts, "+ N more"), Health chips, Location, **Available for exchange**. Exchange: Type (All / Swap / Free), Distance ("Distance · near Kurunegala", *Within 25 km*; visitors get a "Log in to see distances" line), Species. People (new): Location, Minimum rating (Any / 4+ / 4.5+). **Apply filters** and **Clear all**.
- **Sorts:** Plants *Most liked, Newest* · Exchange *Newest, Nearest (members), Most interested* · People *Top rated, Most plants*.
- **Tab counts follow the search:** each count is what that tab would show for the current word (with its default filters).
- **"Available for exchange"** sends the visitor to the Exchange tab, keeping the word and the ticked species (302 redirect at the top of the page; `community.js` submits the form as soon as it is switched on).
- **Exchange tab:** "How the exchange works" with an × that hides it for good on that browser ([[02.07 Web Storage - localStorage and try-catch]]).
- **Empty states:** a friendly title and line for each tab, with **Clear filters** and/or **Clear the search** (and **Search any distance** first when the 25 km limit hides listings). The Plants tab still shows popular plants under it.
- **`includes/community-data.php`:** one function per tab: `community_plants()`, `community_listings()`, `community_people()`, plus `community_species_list()` for the sidebar counts. Each has the prepared SQL it becomes in stage B in its comment.
- **`public/assets/css/community.css`:** made from `explore.css` and `exchange.css`. **`public/assets/js/community.js`:** the filter panel, the strip's ×, the switch, and a short address (empty fields are left out).

**Why this way**
- **One form, two places.** The search box is at the top and the filters are in the sidebar, but they must be sent together. The sidebar's fields join the search form with the HTML **`form="community-form"`** attribute, so **Search** and **Apply filters** both send the word *and* every filter.
- **Functions per tab, not inline code.** The page runs each search more than once (the open tab with its filters, the other two for their counts), and stage B swaps only the bodies of these functions for PDO queries.
- **Switching tab keeps only the search.** Health, type, rating and the sorts belong to one tab.
- **No prepared statements yet:** stage F has no database. Each function's comment shows the exact query (with `?` placeholders) it becomes in stage B.

```html
<form id="community-form" method="get" action="community.php" role="search">
  <input type="hidden" name="tab" value="exchange">
  <input type="search" name="q" value="pothos">
  <button type="submit">Search</button>
</form>
…
<aside class="community-filters">
  <input type="radio" name="type" value="swap" form="community-form">   <!-- outside the form, still sent with it -->
  <button type="submit" form="community-form">Apply filters</button>
</aside>
```

```php
// community-data.php: one tab's search (stage F filters the mock rows; stage B runs a prepared query)
function community_listings(array $listings, $term, array $filters, $skip_species = false)
{
    return array_filter($listings, function ($listing) use ($term, $filters, $skip_species) {
        if ($listing['status'] !== 'open') { return false; }
        if ($filters['type'] !== '' && !in_array($listing['type'], [$filters['type'], 'either'], true)) { return false; }
        // … search word, distance, species
        return true;
    });
}
```

**Problems and fixes**
- My first test of addresses with `species[]=…` printed a blank page. The page was fine: **curl** treats `[` `]` as a pattern of its own. `curl -g` (globbing off) fixed the test.
- At 375 px the Sort button dropped to its own line. The word "Sort:" is now hidden on phones the way `.visually-hidden` hides text, so screen readers still hear it and **Filters · count · Sort** fit on one row.
- The distance option "Near Kurunegala · 25 km" was cut off in the sidebar even at 240 px; fixed in step 7.23.

> [!tip] What to remember
> - `form="id"` lets a field anywhere on the page belong to a form.
> - Keep search state in the address: shareable links, a working Back button, and the server does the filtering ([[02.04 URLSearchParams and Shareable Filter URLs]]).
> - Put each query in its own function now, so stage B changes the data source and not the page.

**Check yourself**
1. The filters are in the sidebar and the search box is at the top. How are they sent together?
2. Why does each tab show a count even when it is not open?
3. What happens when "Available for exchange" is switched on?

> [!success]- Answers
> 1. They are one form: the search box is inside `<form id="community-form">` and every sidebar field has `form="community-form"`, so pressing Search or Apply sends all of them.
> 2. The page runs all three search functions with the current word (the closed tabs with their default filters), so a visitor sees where the matches are before switching.
> 3. The form is sent at once (`community.js`); the page sees `tab=plants&exchange=1` and redirects (302) to `?tab=exchange`, keeping the word and the ticked species.

### Step 7.22 — One "Community" link; Explore and Exchange redirect
> [!important] Goal (COM-01, COM-07, UI half) — step 6 of the Community page
> Replace the two nav items with one, send old addresses to the new page, and update every link.

**What was done (2026-11-11, branch `feature/community`, commit `4727293`)**
- **Top bar:** "Explore" and "Exchange" → **Community** (members and visitors; the visitor's old Exchange link to `index.php#fresh` is gone, which also settles Week 04's Decision 1). **Phone bar:** Exchange → **Community** with a new Lucide icon, `users` (added to `docs/credits.md`).
- **Lit link:** `current_area()` has a `'community'` area: `community.php`, `listing.php`, `listing-form.php`, `my-exchanges.php`; another member's plant or profile sets `$nav_area = 'community'`.
- **`explore.php` and `exchange.php`** are now a few lines each: a **301** redirect to `community.php?tab=plants` (or `people`) and `?tab=exchange`, keeping the whole query.
- **Links:** Landing, Listing Detail (breadcrumb, buttons), Plant Profile (breadcrumb "Community"), Public Profile, and Create Listing: **Back**, **Cancel** and **Publish** now lead to `community.php?tab=exchange`.
- `explore.css` and `exchange.css` deleted; CLAUDE.md, ROADMAP and the READMEs list the merged page.

**Why this way**
- **301 means "moved for good"**: browsers and search engines update bookmarks and results to the new address. A 302 ("for now") is used inside the page for the exchange switch, because that is a choice, not a move ([[02.04 HTTP Protocol]] · [[07.02 HTTP and the Web]]).
- **Keep the query:** `explore.php?q=monstera&tab=people` must still open the same search, so the redirect copies `$_GET` and only sets `tab`.

```php
<?php
// explore.php: the whole file now
$params        = $_GET;
$params['tab'] = (($params['tab'] ?? '') === 'people') ? 'people' : 'plants';
header('Location: community.php?' . http_build_query($params), true, 301);
exit;
```

**Checked:** the four kinds of old address answer 301 to the right tab with their query; `explore.php?…&exchange=1` ends on the Exchange tab. As Nimali and as a visitor, "Community" is lit on Community, listings, Create Listing, My exchanges and other members' plants and profiles; My Garden on her own plants. No PHP warnings on any page.

> [!tip] What to remember
> - Moving a page: redirect the old address with 301 and keep its query string, then update every link anyway.
> - `exit` after `header('Location: …')`, or the rest of the script still runs.

**Check yourself**
1. What is the difference between a 301 and a 302 redirect, and where is each used here?
2. Why update the links if the old addresses redirect anyway?

> [!success]- Answers
> 1. 301 = moved permanently (old Explore/Exchange addresses); 302 = found elsewhere for now (the "Available for exchange" switch, a one-off choice).
> 2. A redirect costs an extra request each time, and the lit nav link and breadcrumbs should name the real page; redirects are only a safety net for old bookmarks.

### Step 7.23 — Responsive checks; distance fits the sidebar
> [!important] Goal (COM-07, UI half) — step 7 of the Community page
> Check the page at phone, tablet and desktop widths and fix what does not fit.

**What was done (2026-11-11, branch `feature/community`, commit `e63bad8`)**
- **Distance:** the town moved into the label, *Distance · near Kurunegala*, and the options became *Within 5 km … Any distance*, so the choice fits the 240 px sidebar.
- **Layout (built in step 7.21):** below **900 px** the sidebar hides behind a **Filters** button that slides a panel in from the left, with a dimmed page behind it; from 900 px it is a column. The tab bar scrolls sideways on its own if it does not fit. Cards: 1 column on a phone, 2 from 768 px, 3 from 1024 px.

**Why this way**
- **900 px** is this page's own breakpoint (the spec asked for it); the rest of the site uses 768 and 1024.
- **The panel works without JavaScript:** the Filters button is a link to `#community-filters`, and CSS shows the panel while it is the `:target`. With JavaScript, a class does the job and adds Esc, the dimmed page, no background scrolling, and focus moving into the panel and back ([[02.02 Tabs, Modals, Toasts and Focus]] · [[02.06 Mobile Patterns - Bottom Bar, Sheets and Stacked Tables]]).

```css
@media (max-width: 899.98px) {
  .community-filters { position: fixed; inset: 0 auto 0 0; transform: translateX(-100%); visibility: hidden; }
  .community-filters.is-open,
  .community-filters:target { transform: none; visibility: visible; }
}
```

**Checked (Chrome and Edge):** 375, 768 and 1024 px × the three tabs: no sideways scroll; Filters button and panel below 900 px, column from 900 px. Panel: opens, focus moves in, Esc and the dimmed page close it, focus returns to the button. Empty fields are left out of the address (`?tab=plants&health=sick`). The switch opens the Exchange tab. The strip's × hides it, and it stays hidden after a reload. No console errors from PlantFolio (the only one came from a Chrome extension).

**Problems and fixes**
- Headless Edge stopped returning output during the test, so the interaction checks ran in Chrome instead (through a temporary test page, deleted afterwards).
- Chrome would not shrink its window below the desktop width, so the phone panel was checked by the scripted test and the earlier 375 px screenshots, not by eye. **Still to do by you:** a real phone, hover by hand, and the Figma comparison.

> [!tip] What to remember
> - Test the widths in the spec (375 / 768 / 1024) and look for sideways scroll first: `document.documentElement.scrollWidth` must not be wider than the window.
> - `:target` gives a no-JavaScript fallback for a panel opened by a link.

**Check yourself**
1. How does the filter panel open on a phone without JavaScript?
2. Why does the panel use `visibility: hidden` as well as moving off-screen?

> [!success]- Answers
> 1. The Filters button is a link to `#community-filters`; the address then ends in that id, and the CSS rule `.community-filters:target` shows the panel.
> 2. Off-screen alone would still let the keyboard Tab into the hidden fields; `visibility: hidden` takes them out of the tab order until the panel opens.

### Step 7.24 — Community plant cards made clearer (UX review A)
> [!important] Goal (COM-01, COM-07, UI half)
> Fix the card problems from Ranshitha's UX review: badges that wrapped, a like count that looked like a button, usernames cut off, pastel backgrounds.

**What was done (2026-11-11, branch `feature/community`, commit `5abbdd5`)**
- `partials/plant-card.php` (community version only): the photo shows **only the health badge**. The body shows the town with a pin, then **"Open to swap"** (or "Free to a good home", "Swap or free") as a small tag link to the listing, the **like count as plain text**, and the **@username as a link** to `user.php?u=`.
- `components.css`: the new rules; 44 px tap areas (an invisible `::before` frame on the tag, padding on the username); the whole card gets the focus ring while one of its links has keyboard focus (`:has(:focus-visible)`).
- `alt=""` on community card drawings: the name link already says which plant it is.
- The ten placeholder SVGs and `.card-media` now share one neutral background (`--color-neutral-bg`, `#ECE8DD`).

**Why this way**
- **Likes are information, not a button:** there is no like feature (COM-15 is optional), and something that looks clickable but does nothing breaks trust. Ranshitha chose to move it into the card body, because plain text on a real photo could fail the 4.5:1 contrast rule.
- **Three links on one card without nesting:** the name link stretches over the card; the tag and the username are *siblings* lifted above it with `position: relative; z-index: 1`.
- **SVG colours cannot use CSS tokens:** an SVG loaded with `<img>` ignores the page's CSS, so its hex values were edited directly (the same value as the token).

> [!tip] What to remember
> - Never make data look like a control. If it isn't clickable, don't draw a button around it.
> - Ctrl+F5 after changing an image: browsers keep images longer than pages.

**Check yourself**
1. Why is the like count no longer a white pill on the photo?
2. How can the @username be a link when the whole card is already a link?

> [!success]- Answers
> 1. There is no like feature, so it must not look like a button; and plain text over a real photo could fail 4.5:1 contrast, so it moved into the white card body.
> 2. The card's link is on the name, and its `::after` covers the card. The username is a separate link (not inside it), lifted above that layer with `position: relative; z-index: 1`.

### Step 7.25 — Tabs above the search, compact header, sticky filters, 2-column phone grid (UX review B)
> [!important] Goal (COM-01, UI half)
> Less space above the results, tabs that clearly control the search, and far more plants on a phone screen.

**What was done (2026-11-11, branch `feature/community`, commit `a2db29c`)**
- The tabs moved **above the search box**; one short placeholder per tab ("Search plants", "Search listings", "Search people") with a fuller hidden label for screen readers.
- On a member's phone the header's **List a plant** is hidden (the bottom bar's + does the same).
- `layout.css`: the footer leaves room for the + button, which rises 28 px above the bar.
- From 900 px the filter sidebar is **sticky** under the top bar and scrolls on its own.
- Plant cards: **2 columns below 768 px**; below 480 px square photos, a tighter card, a smaller name and no avatar. Listing and person cards keep 1 column.

**Why this way**
- **Footer, not `main`:** the review asked for padding on `main`, but the footer always comes after it and is what sits at the very bottom, so the room was added there (on every page).
- **Equal heights for free:** grid items stretch to the height of their row, and the owner row is pushed down with `margin-top: auto`.

```css
.community-filters {
  position: sticky;
  top: calc(var(--header-height) + var(--space-16));
  max-height: calc(100vh - var(--header-height) - var(--space-32));
  overflow-y: auto;
}
```

> [!tip] What to remember
> - `position: sticky` needs a `top` and a parent that is taller than the sticky box (here the grid row).

**Check yourself**
1. Why does the sidebar need `max-height` and `overflow-y: auto` as well as `sticky`?

> [!success]- Answers
> 1. A sticky box taller than the window could never show its bottom part (Apply, the last filters); the max height plus its own scrolling keeps all of it reachable.

### Step 7.26 — Exchange filter in place, search chip, 12 per page (UX review C and D)
> [!important] Goal (COM-03, COM-04, UI half)
> "Available for exchange" filters the Plants grid itself; the search word becomes a removable chip; results come 12 at a time with Load more. All of it without JavaScript first.

**What was done (2026-11-11, branch `feature/community`, commit `2545dc5`)**
- `community-data.php`: a new Plants filter, `exchange = '1'`: only plants an open listing offers (stage B: `AND exchange_listings.id IS NOT NULL`). The old redirect to the Exchange tab is gone, and so is the "Opens the Exchange tab" hint.
- The search word is the first chip (`“monstera” ×`); the results bar's **Clear all** clears the word and the filters, the sidebar's clears only the filters.
- **12 per page on every tab:** "Showing 12 of 19 plants" and a **Load more** link to `?page=2`, which shows the first **24** (stage B: `LIMIT ? OFFSET ?`).
- The parts JavaScript will replace are marked `data-swap="…"`; the count sits in `<span aria-live="polite">` inside the heading.

**Why this way**
- **`?page=2` shows 24, not cards 13–24:** without JavaScript the link opens a new page, and the reader should still see the cards above. The script fetches the same address and appends only the cards it does not have yet.
- **`page=` is not kept in other links:** a new filter, sort or tab starts again at the top.
- **`aria-live` on a span, not on the `<h2>`:** the heading stays a heading for screen-reader navigation, and the span is announced when its text changes.

> [!tip] What to remember
> - Build pagination as plain links first; JavaScript only makes it smoother.

**Check yourself**
1. What does `?page=2` show, and why not only cards 13 to 24?

> [!success]- Answers
> 1. The first 24 cards. Without JavaScript the link loads a whole new page, so it must still show what was above; with JavaScript only the missing cards are appended.

### Step 7.27 — Filters apply on change, live count in the phone panel, Load more (UX review E)
> [!important] Goal (COM-03, COM-04, UI half)
> On a desktop, every filter applies at once; on a phone, the panel's button says how many results it will show; Load more appends without a reload.

**What was done (2026-11-11, branch `feature/community`, commit `07c154a`)**
- `community.js`, `loadResults(address)`: fetches the **same address a form would open**, reads it with `DOMParser`, and copies over each `[data-swap]` part (tabs, chips, count, sort, filters, results). The address follows with `history.pushState`, **Back/Forward** reload the earlier choice, and focus goes back to the field that changed.
- From 900 px the Apply button is hidden, but only when JavaScript runs (`.js` class on `<html>`).
- The phone panel's button becomes **"Show 7 plants"** after each change (one fetch per pause of 250 ms); nothing applies until it is pressed.
- **Load more** appends the next cards, updates "Showing 19 of 19 plants" (also `aria-live`) and moves focus to the first new card.

**Why this way**
- **No separate endpoint:** the server already renders the right results for any address, so the script reuses that page. One source of truth, and the JavaScript version cannot drift from the no-JavaScript one.
- **AbortController:** ticking three boxes quickly starts three fetches; each new one cancels the older one, so an old answer can never overwrite a newer one.

```js
const page = new DOMParser().parseFromString(await response.text(), 'text/html');
document.querySelectorAll('[data-swap]').forEach((part) => {
  const fresh = page.querySelector('[data-swap="' + part.dataset.swap + '"]');
  if (fresh) {
    part.innerHTML = fresh.innerHTML;
  }
});
history.pushState({ community: true }, '', address);
```

**Checked (Chrome):** desktop health → 11 plants, + exchange → 3, Back → 11, Back → 19; Load more 12 → 19 with focus on the first new card; Exchange type and distance, People rating + search; phone panel "Show 19 → 2 → 0 plants" with the address unchanged, Esc returns focus to Filters; 375/390, 768, 900 and 1280 px with no sideways scroll; no console errors, no PHP warnings, `<script>` and `' OR '1'='1` in the search printed as text. **Still to do by you:** a real phone, hover, the Figma comparison, and the page with JavaScript switched off in DevTools.

> [!tip] What to remember
> - Progressive enhancement: links and forms first, then `fetch` + swap the same HTML.
> - `pushState` keeps the address in step with what is shown; `popstate` handles Back.

**Check yourself**
1. Where do the new results come from when a checkbox changes?
2. Why is the desktop Apply button hidden only when the `js` class is present?

> [!success]- Answers
> 1. From the server: the script fetches `community.php` with the new choices in the address, exactly what the form would open, and copies the changed parts into the page.
> 2. Without JavaScript nothing applies on change, so the button is the only way to send the filters; the script adds the `js` class, and only then can the button go.

### Step 7.28 — Listing cards in two columns on a phone
> [!important] Goal (COM-07, UI half)
> Ranshitha's request: on a phone the Exchange tab's listing cards should be as small as the plant cards on the Plants tab.

**What was done (2026-11-11, branch `feature/community`, commit `696899b`)**
- `community.php`: the Exchange grid gets the same class as the Plants grid. The class was renamed from `community-grid-plants` to **`community-grid-compact`**, because it is no longer only for plants.
- `community.css`: 2 columns below 768 px; below 480 px square photos, smaller badges and text, no avatar, and **"Looking for" cut to two lines** with `line-clamp` (the full text is on the listing page). People cards keep one column.

**Why this way**
- One class for "compact cards on a phone" instead of a second copy of the same rules: a later change applies to both tabs at once.
- **Measured** at 390 px: all seven listing cards 385 px tall (equal rows), no sideways scroll.

```css
.community-grid-compact .listing-card-wants {
  display: -webkit-box;
  overflow: hidden;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;   /* at most two lines, then "…" */
  line-clamp: 2;
}
```

> [!tip] What to remember
> - Name a class after what it does ("compact"), not after where it was first used ("plants").

**Check yourself**
1. Why was `community-grid-plants` renamed?

> [!success]- Answers
> 1. The same compact phone layout now serves the Exchange grid too; a name that says "plants" would mislead whoever reads the listing markup.

### Step 7.29 — Exchange first; Plants without health
> [!important] Goal (COM-01, COM-07, UI half)
> Ranshitha's decisions after asking "do we need the Plants tab?": keep it, but make Exchange the main tab and slim Plants down.

**What was done (2026-11-11, branch `feature/community`, commit `4f3b69e`)**
- `community.php`: the tabs are **Exchange · Plants · People**, and a missing or wrong `tab=` opens **Exchange**. So the nav's "Community" link lands on the exchange.
- `partials/plant-card.php`: new option **`'show_health' => false`**; the Community page passes it, so its plant cards have no health badge. The Public Profile and the plant's own page still show health.
- Plants tab: the **Health** filter and the **Available for exchange** switch are gone (and their chips and code in `community-data.php`). Plants on offer have their own tab.
- `explore.php`: an old `?exchange=1` address now redirects to the Exchange tab.

**Why this way**
- The order of the `$tabs` array is the order the tabs are drawn, and its fallback is the default tab: one line each.
- An **option** on the shared card, not a change for everyone: the profile is a member's own showcase, where health is part of the story.
- Old addresses with `health=` or `exchange=1` are simply ignored now: every value is checked against what is allowed, so a removed filter cannot break the page.

> [!tip] What to remember
> - Removing a filter: take out the field, its chip, its query condition and its default; then check that old addresses with that value still load.

**Check yourself**
1. What opens when someone visits `community.php` with no `tab=`?
2. Why do profile cards still show health when Community cards do not?

> [!success]- Answers
> 1. The Exchange tab: it is the fallback when `tab` is missing or not one of the three.
> 2. The Community page passes `'show_health' => false` to the shared card; the profile does not, so it keeps the default (shown).

### Step 7.30 — Category, Light and Watering filters on the Exchange tab; folding filter groups
> [!important] Goal (COM-03, COM-07, UI half)
> Ranshitha's request, from a shop-style filter panel she liked: help swappers find the right listing by what kind of plant it is and how it is cared for.

**What was done (2026-11-11, branch `feature/community`, commits `70b8016` (fix), `672c868`)**
- **Exchange sidebar:** new groups **Category** (Indoor plants, Outdoor plants, Succulents & cacti, Air purifying, Flowering, Herbs & edibles, Pet friendly), **Light** (low, medium, bright) and **Watering** (low, moderate, high), each option with a count. The image's **Price range** was left out: the exchange has no money. Ranshitha also chose to skip a "What you get" group.
- **Every sidebar group on all three tabs folds** open and shut: a `<details>` whose `<summary>` holds an icon, the title and a chevron (⌃ when open). After a live update, a group the member folded stays folded.
- `partials/filter-checks.php` draws any list of ticks with counts (species, category, light, water).
- `community-data.php`: the listing search checks the three new groups; `community_option_counts()` counts each option. `$skip` names the one group to leave out when counting it.
- Mock data: categories, light and watering for every listing (invented). New Lucide icons: trees, wind, flower-2, carrot (no cactus icon exists, so Succulents uses sprout).
- **Fix found on the way (`70b8016`):** step 7.28 had deleted the 1024 px rule, so desktops showed 2 cards per row instead of 3.

**Why this way**
- **Any inside a group, all across groups:** ticking Indoor and Outdoor should widen the search (either), while Indoor plus Bright light should narrow it (both). That is how shop filters behave too.
- **Counts without the group's own filter:** otherwise ticking Indoor would set every other category to 0, and the member could never add a second one.
- **A table for categories, a column for light and water:** a listing has *several* categories (many-to-many: `listing_categories`), but only *one* light level and one watering need (a column each, NULL = "Not sure").

```php
// community-data.php: inside one group ANY ticked option, and every group must match
if ($skip !== 'category' && $filters['category'] && !array_intersect($listing['categories'], $filters['category'])) {
    return false;
}
if ($skip !== 'light' && $filters['light'] && !in_array($listing['light_level'], $filters['light'], true)) {
    return false;
}
```

**Problems and fixes**
- My option counts were first stored in `$counts`, which already held the three tab counts: four "Undefined array key" warnings. Renamed to `$option_counts`.

> [!tip] What to remember
> - Several values per row → a separate table; one value per row → a column.
> - Re-check a layout at every breakpoint after replacing CSS: a deleted rule can hide outside the width you are looking at.

**Check yourself**
1. A member ticks Indoor plants and Succulents, and Bright light. Which listings appear?
2. Why is `listing_categories` a table and `light_level` a column?

> [!success]- Answers
> 1. Listings that are Indoor **or** Succulents **and** have Bright light (2 in the mock data).
> 2. A listing can have several categories but only one light level; several values per row need their own table (one row per listing and category).

### Step 7.31 — "Help people find it" on Create Listing
> [!important] Goal (COM-06, COM-07, UI half)
> Owners file their listing under the same choices the Exchange filters use, so swappers can find it.

**What was done (2026-11-11, branch `feature/community`, commit `530b229`)**
- `listing-form.php`: a new **step 3, "Help people find it"** (contact becomes step 4). **Category** chips: tick any, **at least one** (checked on the server). **Light** and **Watering**: one choice each, with **Not sure** first and chosen by default. Edit fills them from the listing; starting from a pet-safe plant ticks **Pet friendly** (from `plant_species.pet_safe`).
- `listing.php`: "About this plant" shows the category badges, Light and Watering, so a visitor sees why it matched.
- `.chip-choice` (inputs drawn as chips) moved from `community.css` to `components.css`, shared by both pages.
- `database/README.md`: the proposal (`light_level`, `water_need`, `listing_categories`), marked "not accepted yet".

**Why this way**
- **Category required, light and water optional** (Ranshitha's choice): every listing can then be found by category, while a beginner who does not know the light level can still list.
- **The same lists everywhere:** the form and the filters both read `listing_categories()`, `light_levels()` and `water_needs()` from `helpers.php`, so a new category appears in both at once.
- **The server keeps only known values:** a hand-made `categories[]=bogus` or `light_level=x` is dropped, like every other address or form value.

**Checked:** sending without a category shows *Choose at least one category, so people can find your listing.* under the chips and keeps what was typed; with one, the form goes on to the Exchange tab; Pearl (echeveria) starts with Pet friendly ticked; listing 6 shows Indoor plants · Flowering · Air purifying, Low light, High watering. No PHP warnings.

> [!tip] What to remember
> - "At least one tick box" cannot be done with HTML `required`; check it on the server and print the error under the group.

**Check yourself**
1. Why does the Light group start on "Not sure" instead of having no choice?
2. Where do the form's category choices come from?

> [!success]- Answers
> 1. Radio buttons always send one value once one is checked; "Not sure" makes the default honest and is stored as NULL, so the listing simply does not match a light filter.
> 2. From `listing_categories()` in `helpers.php`, the same function the Exchange filters use.

### Step 7.32 — Near any town, and a distance slider
> [!important] Goal (COM-07, UI half)
> Ranshitha's request: choose any major town in Sri Lanka, then drag a slider to set how far from it ("Within 25 km").

**What was done (2026-11-11, branch `feature/community`, commit `65c2807`)**
- `mock-data.php`: **`$mock_towns`**, 31 towns with latitude and longitude (every district capital, a few large towns, and the listings' own towns), shaped like the **`towns` table already accepted in DES-07**. Every listing's `distance_km` is now **calculated** from the viewer's town instead of typed.
- `helpers.php`: **`town_distance_km()`**, the Haversine formula: the straight-line distance between two points on the Earth.
- Exchange sidebar, Distance group: **Near** (a list of the towns, A–Z) and **How far**, an `<input type="range">` from 5 to 100 km in steps of 5; the far end means **any distance**. A member starts on their own town; a visitor on "Choose a town", with the slider switched off until they choose one (Ranshitha's choice).
- The cards, the Nearest sort and the chips ("Near Kandy", "Within 60 km") follow the chosen town (Ranshitha's choice). The same town reads **"in town"** on cards and "in your town" on the listing page.
- `community.js`: while the slider moves, its `<output>` and `aria-valuetext` say "Within 40 km"; the results change when it is let go.

**Why this way**
- **Coordinates, not typed distances:** with any town to measure from, the distance must be worked out. Stage B does the same sum in SQL with the `towns` table, so the mock and the real site will agree.
- **Two events:** `input` fires on every step of the drag (cheap: only the words change); `change` fires when it is let go (one fetch).
- **A disabled field is not sent:** a visitor with no town sends no distance, so nothing is filtered by it.

```php
function town_distance_km(array $towns, $from, $to)
{
    $lat1 = deg2rad($towns[$from]['latitude']);
    $lat2 = deg2rad($towns[$to]['latitude']);
    $dlat = $lat2 - $lat1;
    $dlon = deg2rad($towns[$to]['longitude'] - $towns[$from]['longitude']);
    $a    = sin($dlat / 2) ** 2 + cos($lat1) * cos($lat2) * sin($dlon / 2) ** 2;
    return (int) round(6371 * 2 * atan2(sqrt($a), sqrt(1 - $a)));   // 6371 km: the Earth's radius
}
```

**Problems and fixes**
- The slider's focus ring crossed the "5 km · 50 km · Any" scale under it; the scale now sits below the slider's tap area.
- **Changed numbers:** the p17 distances were typed (Pothos 3 km, Kuliyapitiya 18 km). Measured between town centres, listings in Kurunegala are "in town" and Kuliyapitiya is 36 km, so the default "near Kurunegala · 25 km" now shows **5** listings instead of 7.

> [!tip] What to remember
> - Store places as coordinates and calculate distances; never store a distance that depends on who is looking.
> - Range slider: `input` while dragging, `change` when let go.

**Check yourself**
1. Why did the listings' distances change from the wireframe's numbers?
2. A visitor opens the Exchange tab. Why is no listing hidden by distance?

> [!success]- Answers
> 1. They are now calculated between town centres with the Haversine formula, as stage B will do, instead of typed; two places in the same town are 0 km apart ("in town").
> 2. A visitor starts with no town chosen, so the slider is disabled; a disabled field is not sent, and listings have no distance to compare.

### Step 7.33 — Every location: a nearest town, then how far from it
> [!important] Goal (COM-03, COM-06, COM-07, UI half)
> Ranshitha's request: every place where someone filters by or enters a location works like the Exchange distance filter: choose the nearest town, then say how far from it.

**What was done (2026-11-11, branch `feature/community`, commit `e2fcba2`)**
| Where | Before | Now |
|---|---|---|
| Community, Plants and People tabs | "Location": a list of towns (exact match) | **Near + How far** (`partials/near-filter.php`, the same as Exchange), measured to the owner's or member's town; starts on **any distance** |
| Sign up | Location: typed text | **Your nearest town** + **How far from the town** (`partials/town-picker.php`), optional |
| Settings | Town: typed text | Nearest town + How far, starting on Nimali's Kurunegala, 3 km |
| Create Listing | Town: typed text | **Pick-up: nearest town** + How far, required; a town not on the list is refused |

- The "how far" slider runs **0–30 km** (0 = "In the town"), Ranshitha's choice. It is shown **only on Listing Detail** ("Pick-up: about 3 km from Kurunegala"); profiles and cards keep the town only (her choice, and the privacy rule).
- `community_too_far()` in `community-data.php` is now the one distance check for all three tabs.
- The slider CSS moved to `components.css`; its words ("Within 40 km", "About 7 km away") and "choosing a town switches the slider on" moved to `app.js`, so every page has them.
- Proposed for stage B: `users.km_from_town` and `exchange_listings.km_from_town` (TINYINT, 0–30).

**Why this way**
- **One partial per job:** `near-filter.php` for searching, `town-picker.php` for entering. Five places, two pieces of HTML: a fix in one reaches every page.
- **The km has no direction:** "5 km from Kurunegala" could be north or south, so it cannot make the distance between two people more exact. Distances stay town centre to town centre; the km is a helpful hint for planning a pick-up.
- **A town from a list, not typed:** typed text breaks on every typo ("Kurunagala"), and a distance needs the town's coordinates (DES-07 decision 4).

> [!tip] What to remember
> - When the same control appears in several places, build it once as a partial and pass in what differs (label, required, hint).
> - Keep privacy in mind when adding detail: show the rough distance where it helps (pick-up), not on public profiles.

**Check yourself**
1. Why does the "how far from the town" number not change the distances on cards?
2. What happens on Create Listing if someone sends `location=Narnia`?

> [!success]- Answers
> 1. A distance has no direction, so 5 km from Kurunegala tells nothing about the distance to someone else; distances are worked out between town centres.
> 2. The town is not in the towns list, so the form comes back with *Choose the town nearest to where the plant can be picked up.* under the field.

### Step 7.34 — Full frontend QA pass, and four fixes
> [!important] Goal (CORE-16, CORE-26, TST-07, COM-07, UI half)
> Test every page of stage F (P01–P15 and about.php) before the UI freeze: links, popups, forms, uploads, Community, redirects, "Not found" pages, phone/tablet/desktop widths and the keyboard. Report first, then fix only the approved bugs.

**What was done (2026-11-11, branch `feature/community`, commits `e393a07`, `f39f2f1`, `9aeaeb7`, `cd01259`)**
- **Phase 1, test only.**
  - A script followed every link as a visitor (88 addresses) and as a member (221).
  - Every form was sent empty, with 10,000 characters, with arrays, with `<script>` and `' OR '1'='1`, and with a .txt, a 6 MB and a fake PNG file.
  - In Chrome every popup was opened and closed with ×, Cancel, Esc and a click outside, and every page was measured at 375, 768 and 1280 px.
  - Result: **no PHP warnings, no dead links, no JS errors, no sideways scroll**. Missing, invalid and private IDs all show a friendly "Not found". `explore.php` and `exchange.php` keep their query string in the 301 redirect.
- **Bugs found and fixed:**

| # | Bug | Fix | Commit |
|---|---|---|---|
| 1 | Add/Edit plant, Create listing, Identify, Health check and the Settings avatar showed **no preview** and **kept any file** (a .txt, 6 MB). Only the Plant Profile popups checked photos. | One rule `photoProblem()` and a shared preview in `app.js`, switched on with `data-photo-input`, `data-photo-hint`, `data-photo-preview`. `plant.js` uses the same rule. Drop areas now accept a dropped photo. | `e393a07` |
| 2 | Settings **Delete account** did nothing. | A popup that names the account, with Cancel focused (S6). In stage F, Delete only shows a toast: "Preview: nothing was deleted." | `f39f2f1` |
| 3 | Touch targets under 44 px: garden grid/list toggle (36), "All reminders" (21), Identify/Health check switch (40), @username links on My exchanges (20). | The tap area grows, the look does not: padding with a matching negative margin, or an invisible `::before` layer. | `9aeaeb7` |
| 4 | Exchange "no results" said "Try another word, type or species" but has more filters. | "Try another word or fewer filters." | `cd01259` |

- **Left as they are (Ranshitha's OK):**
  - Community writes default values into the address (`near=Kurunegala&distance=25`). This is only cosmetic.
  - The rating form (S4) stays inline, not a popup.
  - No nav item is lit on Identify, Health check or About.
  - The delete toast has no Undo.

**Why this way**
- **One copy of the photo rule:** before, the rule existed twice in `plant.js` and nowhere else. A rule repeated per page drifts apart, so it lives once in `app.js`, which every page loads, and pages only add `data-` attributes.
- **The browser check is only a help:** a member can switch JavaScript off, so stage B checks again on the server with `finfo` and the size.
- **Bigger tap area, same look:** the 44 px rule is about where a finger can land, not about how big a button looks.

```js
// app.js: the one photo rule, used by every photo field and by plant.js
function photoProblem(file) {
  if (!['image/jpeg', 'image/png'].includes(file.type) || file.size > PHOTO_MAX_BYTES) {
    return PHOTO_ERROR;   // 'Choose a JPG or PNG photo of 5 MB or less.'
  }
  return null;
}
```
```css
/* garden.css: the toggle looks 36px, takes taps from 44px */
.view-toggle-btn::before { content: ""; position: absolute; inset: -4px; }
```

**Problems and fixes**
- Dragging a photo onto "Drag it here" made the browser **open the file and leave the form**, because a `<label>` does not accept a drop. The shared code now catches `dragover`/`drop` and puts the file into the field.
- The Settings "email · Change" row (21 px tall) was given the padding trick too. A tap test (`elementFromPoint`) showed the heading above and the "Current password" label below covered the new area. The change was taken back, so that row stays as it was (it is 294 px wide).
- A grep found `plant.php` linking to private plants (404), but only from the dev-only style guide, so it was not a bug.

> [!tip] What to remember
> - Test in two layers: a script for breadth (every link and form), a browser for behaviour (popups, keyboard, widths).
> - Keep one copy of a rule and switch it on with `data-` attributes. Check in the browser for comfort, on the server for safety.
> - A touch target is the area that takes the tap. It can be bigger than what you see.

**Check yourself**
1. If `app.js` already refuses a 6 MB photo, why does stage B still check the size on the server?
2. How can the grid/list toggle meet the 44 px rule while it still looks 36 px?
3. Why does `plant.js` call `photoProblem()` instead of keeping its own check?

> [!success]- Answers
> 1. The browser check can be skipped (JavaScript off, or a hand-made request with curl). Only the server's check (`finfo` for the type, and the size) can be trusted.
> 2. An invisible `::before` layer, 4 px bigger on every side (`inset: -4px`), belongs to the button and takes the taps around it.
> 3. So the rule exists once: changing the limit in `app.js` changes it for every photo field on the site. `app.js` loads first (both scripts use `defer`), so the function is ready.

Related: [[02.05 Upload Preview with the File API]] · [[02.02 Tabs, Modals, Toasts and Focus]] · [[02.06 Mobile Patterns - Bottom Bar, Sheets and Stacked Tables]]
