---
type: project-note
project: PlantFolio
status: draft
tags: [project, ml]
---
# PlantFolio Week 14 — AI in the App and Gate 3
> [!info] [[PlantFolio - Project Home]] · Stage M · 4–10 Jan 2027 · ends with **Gate 3**
> **Study notes:** [[05.00 Machine Learning Service]] · **Revision:** [[05.99 Summary - Machine Learning Service]]

> [!abstract] Week goal
> The AI features are part of the normal flows, behave honestly when unsure, and the site still works when the ML service is switched off.

## Tasks
- [x] **ML-13** AI suggestions in Add Plant (wired: Identify plant button, 12 Nov 2026)
- [x] **ML-16** Save a diagnosis to the plant story, and add a 7-day re-check reminder → [[04.03 Dates, Reminders and Due Logic]]
- [x] **ML-17** States wired: Analysing, Not sure → [[05.07 Confidence Thresholds and Honest AI Results]]
- [ ] **ML-18** ML test pass, including with Flask stopped
- [ ] **ML-19** Reviews of MOD-11 and MOD-12

## Gate 3
- [ ] Disease model ≥ 90 % test accuracy (no own model yet: Pl@ntNet API, D27)
- [ ] Top-3 accuracy measured for plant recognition
- [ ] Offline fallback verified
- [ ] Tag `v1.0-ml`

## Tests
- [ ] Test Plan cases TC-044 – TC-051
- [ ] Nothing is saved until the user presses Save
- [ ] A result below 60 % is shown as "Not sure", never as an answer (still true for species; Pl@ntNet disease results use "Possible match", D28)
- [ ] A photo of an unsupported plant gets the scope message, not a guess

---
## Work log
Step sections are added here as each step is finished.

### Step 14.1 — "Possible match" and AI advice on the Health Check (ML-14, ML-16, ML-17)

> [!important] Goal
> Make Pl@ntNet disease results usable and honest: a confidence word that fits its low scores (D28), and advice for each problem written by the local LLM (Ollama), as the AI care guide does.

**What was done** — 12 Nov 2026, branch `feature/ml-service`, commits `b64ee5f` (rule and page) and `723084b` (AI advice).
- `disease_level()` in `includes/helpers.php`: for Pl@ntNet results, **Possible match** when the top answer is at least 10% **and** at least twice the second, otherwise **Not sure**. Our own model keeps High / Medium / Not sure.
- Health Check page: "Possible problem" badge, the common name with the scientific name below it, "No clear problem found" when unsure, a "How sure is it?" note in place of the crop list. The crop tag comes from the linked plant.
- Story line: "Health check found late blight of potato · Possible match, 21%".
- `includes/disease-guide.php`, `public/api/disease-guide.php` and `assets/js/disease-guide.js`. When a Possible match has no saved advice, grey bars show while Ollama writes the symptoms, steps and status. The answer is saved in `disease_guides` under the EPPO code, so it is written only once. The page says "Advice written by AI".

**Why this way**
- **Under the fixed 60% rule every Pl@ntNet result would be "Not sure"** and nothing could be saved. "Possible match" is a new word, never High or Medium, so it does not claim more than the data supports.
- **"Twice the second" matters as much as the 10%.** One answer standing out from the others is a better sign than its raw score.
- **The endpoint reads the problem's name from the member's own saved result**, never from the request, so nobody can make the LLM write about anything else. Every AI sentence goes on the page with `textContent`.

```php
function disease_level($predictions)
{
    $top = $predictions[0];
    if (!is_plantnet_disease($top['label'])) {
        return confidence_level($top['confidence']);   // our own model: the fixed words
    }
    $second = (float) ($predictions[1]['confidence'] ?? 0);
    if (round($top['confidence'] * 100) >= 10 && $top['confidence'] >= 2 * $second) {
        return ['key' => 'possible', 'label' => 'Possible match'];
    }
    return ['key' => 'low', 'label' => 'Not sure'];
}
```

**How it was tested**
- Late blight 21% → Possible match ✓. Early blight → Possible match, but named late blight ✗. Powdery mildew → Possible match "Cotton whitefly" ✗. Healthy Monstera 5% → No clear problem found ✓.
- Save: a Not sure result is refused; a Possible match is saved to Tommy's Story with status from the advice. The test entries were removed and Tommy's status restored.
- Advice endpoint: no token → 403, GET → 405, another member's result → 404, logged out → 403. A real answer took 11 s and was saved as `PHYTIN`; reloading shows the saved advice at once.
- In Chrome: the grey bars, then the whitefly advice filled in; no console errors from the site.

**Problems and fixes**
- `llama3` described late blight as "yellow or white spots"; it really shows dark, water-soaked patches. AI advice can be wrong, so the page says to check it against the leaf. A larger local model might do better (open question).

> [!tip] What to remember
> A confidence word must match how the number behaves. Our own model's percentages and Pl@ntNet's spread-out scores are not the same kind of number, so they get different words, and the honest one for Pl@ntNet is "Possible match".

**Check yourself**
1. Why does Pl@ntNet need a different rule from the fixed High / Medium / Not sure?
2. Why does the advice endpoint take a prediction id, not the disease name?
3. Two of three test photos got a wrong "Possible match". How does the page stay honest about that?

> [!success]- Answers
> 1. Its scores are spread over about 2,500 classes, so right answers score 15–25%. With the 60% rule every result would be "Not sure", which hides useful answers. The new rule looks at whether one answer stands out (≥ 10% and twice the next).
> 2. The name is read from the member's own saved result, so the endpoint cannot be used to make the LLM write about anything else, and someone else's results stay private (404).
> 3. It never says High or Medium for Pl@ntNet, it shows the other possibilities with their percentages, it says the advice was written by AI and to check it against the leaf, and it warns "This is a suggestion, not a diagnosis".

### Step 14.2 — The Health Check names the plant too (ML-14)

> [!important] Goal
> Ranshitha's request after trying a diseased Monstera leaf: the result should also say **which plant** is in the photo, not only the problem.

**What was done** — 12 Nov 2026, branch `feature/ml-service`, commit `11abb20`.
- When no garden plant is chosen, `health-check.php` sends the same photo to `/predict/species` after `/predict/disease`, and logs that answer as a `species` row with the same `image_path`.
- `species_for_photo()` in `includes/ml-client.php` finds that row again when the result page opens.
- The result shows "Plant: Tomato (*Solanum lycopersicum*) · identified by AI, High 97%", the leaf tag says "Tomato leaf", and "No clear problem found" starts with "This looks like a Swiss cheese plant (72%)".

**Why this way**
- **Only when no plant is chosen.** If the member chose Tommy, we already know he is a tomato, so no second Pl@ntNet request is spent (500 a day).
- **Linked by the photo, not a new column.** Both rows share `image_path`, so no schema change was needed, and every AI answer is still logged (ML-11).
- **Species keep the fixed words** (High / Medium / Not sure): Pl@ntNet's species scores are high when it is right, unlike its disease scores. Below 60% the page says "Plant not identified for sure".

```php
if (!$plant) {
    $species_answer = ml_predict('species', $file, (new finfo(FILEINFO_MIME_TYPE))->file($file));
    if ($species_answer['ok']) {
        save_prediction($current_user['id'], null, 'species', $species_answer, $upload['path'], ...);
    }
}
```

**How it was tested**
- Late blight leaf, no plant chosen → "Tomato leaf", "Plant: Tomato (*Solanum lycopersicum*) · identified by AI, High 97%".
- Healthy Monstera → "This looks like a Swiss cheese plant (72%). The photo does not clearly match a disease or pest…"
- With Tommy chosen → only the disease row was logged; the tag "Tomato leaf" came from Tommy.
- Only my exact test rows were deleted afterwards (Ranshitha's own rows, made at the same time, were left alone).

**Problems and fixes**
- One check went to "couldn't reach": Pl@ntNet took about 14 s and PHP gives up after 10 s. That is the fallback working, but two Pl@ntNet calls in a row make a check slower (often 5–10 s).

> [!tip] What to remember
> Two rows can describe one photo: the disease answer and the species answer share `image_path`. Asking a second question only when the answer is unknown saves time and API quota.

**Check yourself**
1. Why is the species asked only when no garden plant is chosen?
2. How does the result page find the species answer for a disease result?

> [!success]- Answers
> 1. A chosen plant already has a species, so the extra Pl@ntNet request (time and one of 500 daily requests) would add nothing.
> 2. `species_for_photo()` looks for the member's newest `species` row with the same `image_path` as the disease result.

### Step 14.3 — The Ollama care guide on Identify (ML-12)

> [!important] Goal
> Ranshitha's question: was the "Read care guide" on Identify written by Ollama? It was not. It was our own `plant_species` text (light, water, humidity, notes), so only species in our list had one. Now it opens the **AI care guide**, the same five sections as the Plant Profile, for any species Pl@ntNet names. Wired.

**What was done** — 12 Nov 2026, branch `feature/ml-service`, commit `af50632`.
- `api/care-guide.php` also takes `prediction=<id>`: the species is the `predicted_label` of the member's **own** species result of 60% or more.
- `partials/care-guide.php` takes an optional `source` (`['plant' => id]` or `['prediction' => id]`) and `retry_url`, so one partial draws the card on both pages.
- `care-guide.js` waits until the closed "Read care guide" `<details>` is opened before asking Ollama.
- `identify.php` reads the saved guide with `care_guide_get()`; none saved means the card starts as Generating. The quick chips (light, water, pets) stay for species in our list.
- The `.ai-guide` styles moved from `plant.css` to the shared `components.css`.

**Why this way**
- **One saved guide per species, shared.** The cache key is the scientific name, so Monty's guide (*Monstera deliciosa*) also appears instantly on Identify.
- **The species never comes from the request.** The browser sends only an id; the server reads the species from a row the member owns. Otherwise anyone could make our LLM write about any text.
- **Only when opened.** A new guide takes Ollama 10 s to 2 min, which is wasted if the member never opens it.
- **No guide for "Not sure"** (below 60%): that result is not an answer, so there is nothing to write a guide for.

```php
if ($prediction_id) {
    $prediction = $viewer ? get_prediction($viewer['id'], $prediction_id, 'species') : null;
    if (!$prediction || confidence_level($prediction['confidence'])['key'] === 'low') {
        http_response_code(404);   // missing, unsure or another member's: the same answer
        exit(json_encode(['state' => 'error', 'message' => 'Result not found.']));
    }
    $species = $prediction['predicted_label'];
}
```

```js
const details = card.closest('details');
if (details && !details.open) {
  details.addEventListener('toggle', generate, { once: true });
} else {
  generate();
}
```

**How it was tested**
- Saved species (Spathiphyllum, prediction 5): the card is ready at once.
- New species (temporary test results for *Ocimum tenuiflorum* and *Mentha spicata*): Generating, no request until "Read care guide" was opened, then filled in by Ollama in about 13 s. In Chrome: no console errors from the page.
- `?guide=offline`: the card opens with "The care guide can't be written right now" and Try again.
- Endpoint: the unsure result 6 and another member's result 976 both answered 404 "Result not found". `plant=42` still works, and the Plant Profile looks the same after the CSS move.
- Only my exact test rows were deleted afterwards (results 977, 978 and those two guides).

> [!tip] What to remember
> To reuse a feature on a second page, make the shared parts take their inputs (`source`, `retry_url`) instead of copying them. The security rule stays the same: the browser sends an id, and the server decides what it is allowed to mean.

**Check yourself**
1. Why does the browser send `prediction=5` and not `species=Monstera deliciosa`?
2. Why does Identify show the Monstera guide instantly when Monty already has one?
3. When does Identify ask Ollama for a guide?

> [!success]- Answers
> 1. A species name from the browser could be any text, so anyone could use our LLM to write anything. With an id, the server reads the species from the member's own result (60% or more) and answers 404 otherwise.
> 2. `care_guides` keeps one row per species, keyed by the normalised scientific name. Both pages look up the same key.
> 3. Only when no guide is saved for that species and the member opens "Read care guide" (the `toggle` event on `<details>`).

### Step 14.3 — Identify plant and "About" on Add a plant (ML-13)

> [!important] Goal
> **ML-13:** on Add a plant, the AI identifies the plant from its photo and fills in Species. Ranshitha asked for an **Identify plant** button under the photo, and a short AI introduction to the plant below the photo.

**What was done** — 12 Nov 2026, branch `feature/ml-service`, commit `dd2cad0`.
- **Identify plant** is a second submit button of the same form: `name="action" value="identify"` with `formnovalidate`, so an empty Nickname does not stop it.
- PHP saves the photo in `uploads/scans/`, asks Flask (Pl@ntNet), logs the answer, keeps what was typed in the session (`plant_form_draft`), and reopens the form as `plant-form.php?prediction=<id>`. That page already showed the top 3, "Filled by AI" and the species' reminders.
- Flask offline: the photo is kept for this form (`plant_form_photo`) and a notice says "We couldn't identify the plant right now".
- **Bug fixed:** Save from a result (also the Identifier's "Add to my garden") lost the photo. The form now posts back to `?prediction=&pick=`; Save uses that result's own photo and links the result to the new plant.
- **About <species>:** a card under the photo, written by Ollama through `api/species-about.php` and saved in the species' `care_guides` row as an extra `about` key.

**Why this way**
- **A button, not an automatic upload:** nothing is sent until the member asks, and it works without JavaScript. `plant-form.js` only changes the label to "Identifying…".
- **Reuse:** the `?prediction=` page was already built and tested for the Identifier, so Identify plant only had to get there.
- **The photo is never named by the form.** Save reads it from the member's own result in the database or from the session, so nobody can attach someone else's file.
- **No new table for "About".** `guide_json` is JSON, so an `about` key sits beside the five sections. When a species has no guide yet, one LLM answer writes the guide **and** the about text, so the new plant's profile opens with its care guide ready. Older guides get the key added with `JSON_SET`.

```php
<button class="btn btn-secondary btn-sm" type="submit" name="action" value="identify" formnovalidate data-identify-button>
  <?= icon('sparkles') ?> <span>Identify plant</span>
</button>
```

```js
// Disabled only after the browser has read the form: a disabled button's action=identify would not be sent
setTimeout(() => { identify.disabled = true; }, 0);
```

**How it was tested**
- No photo → "Choose a photo first, then press Identify plant." (the nickname stayed).
- With a photo, nickname, "How many? 2" and Stable → *Monstera deliciosa* filled by AI, all three kept; "About Swiss cheese plant" card; "Use" on the 2nd suggestion kept the nickname.
- About endpoint: no token 403, a pick that does not exist 404, another member's result 404; a real answer in 9 s, saved next to the five sections.
- Save → the plant got the identified photo and the result its `plant_id`.
- Flask stopped → `?identify=offline`, photo and nickname kept, notice shown; Save used the kept photo; a fresh form afterwards had no old photo.
- Only my exact test plants, result and photos were deleted afterwards.

**Problems and fixes**
- **"The AI's introduction could not be read" for Fiddle-leaf fig** (found by Ranshitha, fixed in `36c04da`). Its care guide was saved before `about` existed, so only the short about-only prompt ran. Asked for "one key, about", llama3 answered `{"Ficus Lyrata": "…"}`, which the check refused. Fix: the prompt shows the exact shape `{"about": "your sentences"}`, and one text under another key is still accepted.
- The same answers were **wrong**: "Strangler Fig" from "Central and South America". `species_for_prompt()` now adds the common name from `plant_species` ("Ficus lyrata" (common name: Fiddle-leaf fig)); 3 of 3 runs then said West Africa and violin-shaped leaves. Lesson: give a small model every fact you already have, and show it the answer shape.

> [!tip] What to remember
> One form can have two submit buttons doing different jobs: the button's `name`/`value` tells PHP which one was pressed, and `formnovalidate` lets the helper button skip the browser's `required` checks.

**Check yourself**
1. Why does the Identify button have `formnovalidate`?
2. Why is the button disabled with `setTimeout(…, 0)` and not straight away?
3. Where is the "About" text stored, and why there?

> [!success]- Answers
> 1. Nickname is `required`, and members choose the photo first; without it the browser would refuse to send the form for identifying.
> 2. A disabled button is left out of the form data, so `action=identify` would not reach PHP. Disabling it after the browser has built the form data keeps the value and still stops a double click.
> 3. In the species' `care_guides` row, as an `about` key in `guide_json`. No schema change, one LLM answer writes it together with the care guide, and every member with that species shares it.
