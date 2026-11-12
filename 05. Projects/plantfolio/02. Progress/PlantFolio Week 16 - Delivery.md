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
