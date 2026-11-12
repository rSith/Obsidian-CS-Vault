---
type: project-note
project: PlantFolio
status: draft
tags: [project, ml]
---
# PlantFolio Week 13 — Prediction Endpoints
> [!info] [[PlantFolio - Project Home]] · Stage M · 28 Dec 2026 – 3 Jan 2027
> **Study notes:** [[05.00 Machine Learning Service]] · **Revision:** [[05.99 Summary - Machine Learning Service]]

> [!abstract] Week goal
> The Flask service answers real prediction requests, and the Plant Identifier and Health Check pages show real results instead of mock ones.

## Tasks
- [x] **ML-09** `POST /predict/species` (wired, Pl@ntNet, 12 Nov 2026) → [[05.02 Serving a Keras Model with Flask]]
- [x] **ML-10** `POST /predict/disease` (wired, Pl@ntNet, 12 Nov 2026)
- [x] **ML-11** Log every call in `ml_predictions` through `includes/ml-client.php` → [[05.01 Calling the ML Service from PHP with cURL]]
- [x] **ML-12** Plant Identifier (P07) wired
- [x] **ML-14** Health Check (P08) wired

## Checks
- [x] Each endpoint returns the top 3 with confidence and a `model_version`, as the API contract says
- [ ] Errors return JSON with the right status: 400 bad image, 415 wrong type, 503 model not loaded
- [ ] The models load once when the service starts, not on every request
- [x] The confidence word is decided in PHP (fixed limits for our model; "Possible match" rule for Pl@ntNet, D28) → [[05.07 Confidence Thresholds and Honest AI Results]]
- [x] The browser never calls Flask directly

---
## Work log
Step sections are added here as each step is finished.

### Step 13.1 — Both endpoints answer from Pl@ntNet (ML-09, ML-10)

> [!important] Goal
> **ML-09 / ML-10:** `/predict/species` and `/predict/disease` give real answers. Ranshitha decided (12 Nov 2026) to finish with the **Pl@ntNet API only**, and to train our own disease model later if there is time.

**What was done** — 12 Nov 2026, branch `feature/ml-service`, commit `10184e4`.
- `ml-service/app.py`: both routes now go through one helper, `ask_plantnet()`. Species uses `/v2/identify/all`; disease uses `/v2/diseases/identify`.
- Disease labels look like `PHYTIN___Phytophthora infestans - Late blight of potato`: Pl@ntNet's EPPO code, then its name.
- `DISEASE_PROVIDER=model` in `ml-service/.env` switches back to a local `disease.keras`. TensorFlow is imported only then.
- Recorded as D27 in the repo's `DECISIONS.md`.

**Why this way**
- **The label is `CODE___Name`**, the same `___` pattern as PlantVillage's `Tomato___Early_blight`. The code never changes, so it is the key for saved advice; the name is what the member sees. No database change was needed.
- **PHP did not change.** Flask hides where an answer comes from, which is why a trained model can be swapped in later.

```python
pairs = [(f"{r['name']}___{r.get('description') or r['name']}", r["score"]) for r in answer["results"]]
return jsonify({"model_version": plantnet_version(answer, "diseases"), "predictions": top_predictions(pairs)})
```

**How it was tested** (real Pl@ntNet calls, Wikimedia Commons photos)

| Photo | Top answer | Score |
|---|---|---|
| Tomato late blight | Late blight ✓ | 15% |
| Tomato early blight | Late blight ✗ (early blight 2nd) | 22% |
| Powdery mildew | Cotton whitefly ✗ | 17% |
| Healthy Monstera (disease route) | Anthracnose | 5% |
| Monstera (species route) | *Monstera deliciosa* ✓ | 72% |

Through the site: Identify showed "Swiss cheese plant · Medium · 72%" with Add to my garden.

**Problems and fixes**
- The API docs disagree on the photo field. A real call showed it must be `images`; `image` gives 400 "Images is required".
- The key was first pasted into `.env.example`, which Git tracks. It was moved into `.env` (ignored) before any commit.
- An old Flask process kept answering on port 5000 after a restart: on Windows a venv's `python.exe` starts a second Python process, and only one of the two had been stopped.
- **My mistake:** while cleaning up test rows I deleted every `ml_predictions` row above id 955. That also removed 3 results Ranshitha had made in the browser at the same moment (no Story entries were linked; the photos were kept). Rule since then: delete only the exact ids a test created.

> [!tip] What to remember
> Pl@ntNet is strong at species (72% on a clear Monstera) but weak at diseases: its score is spread over about 2,500 diseases and pests, so even a right answer is only 15–25%. That is why Week 14 needed its own confidence rule.

**Check yourself**
1. Why is the disease label `CODE___Name` and not just the name?
2. What has to change in PHP to use our own model later?
3. Why did the right late-blight answer score only 15%?

> [!success]- Answers
> 1. The EPPO code never changes, so it is a safe key for the saved advice in `disease_guides`; the name is for people. One text column holds both without a schema change.
> 2. Nothing: set `DISEASE_PROVIDER=model` in `ml-service/.env` and put `disease.keras` with its class list in `models/`. Flask answers in the same shape.
> 3. The scores of about 2,500 classes add up to 1, so a lot of probability goes to look-alike diseases. A low score does not have to mean a wrong answer, but it does mean "check it yourself".
