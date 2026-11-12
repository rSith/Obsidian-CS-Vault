---
type: project-note
project: PlantFolio
status: draft
tags: [project, web]
---
# PlantFolio Week 15 — Testing
> [!info] [[PlantFolio - Project Home]] · Stage D · 11–17 Jan 2027
> **Study notes:** [[06.00 Testing and Delivery]] · **Revision:** [[06.99 Summary - Testing and Delivery]]

> [!abstract] Week goal
> Find the problems before the examiners do: security, wrong inputs, real phones, real users. Start the report sections in parallel.

## Tasks
- [ ] **TST-02** Security review → [[06.02 Security Review of a PHP Application]]
- [ ] **TST-03** Input validation
- [ ] **TST-04** Journeys A–D → [[06.01 Test Cases, Journeys and Regression Testing]]
- [ ] **TST-05** Responsive on real phones → [[06.06 Designing Responsively and Site Testing]]
- [ ] **TST-06** Browsers
- [ ] **TST-07** Accessibility → [[09.03 Applying Accessible Design]]
- [ ] **TST-08** Usability test with 5–10 students → [[05.10 Usability Testing Methods]]
- [ ] **TST-09** Performance → [[06.07 Web Performance Optimisation]]
- [ ] **DEP-05** README and setup guide
- [ ] **DEP-07** Code clean-up
- [ ] **DEP-08** Notebooks and model card
- [ ] **DEP-11 – DEP-14** Report sections → [[09.01 Technical and Scientific Writing]]

## Checks
- [ ] Every failure is logged in the Task Sheet's Issue Log with its severity
- [ ] No open Critical or High issues at the end of the week
- [ ] The usability test's findings are written down, with what was changed

---
## Work log
Step sections are added here as each step is finished.

### Step 15.1 — Full QA pass: 16 bugs found and fixed

> [!important] Goal
> **TST-02, TST-03, TST-04 (first pass), TST-10.** Test every page and every feature end to end, try wrong inputs and other members' ids, fix what breaks, and write it down in `QA_REPORT.md`. No task above is ticked yet: real phones (TST-05), clicking through popups in a real browser and the usability test are still to do.

**What was done** (12 Nov 2026, branch `feature/ml-service`, commits `ede2d6d` fixes, `a943874` report, `d7c11dd` my waiting CLAUDE.md edits)
- Backup first: `database/backup_before_qa.sql` (git-ignored: it holds real emails and password hashes).
- `schema.sql` + `seed.sql` imported into a scratch database and compared with the live one: identical.
- 449 scripted checks with throwaway `qa.*` members (curl with a cookie jar and the CSRF token), a sweep of 70 addresses as visitor and member with `error_reporting(E_ALL)`, and 249 page-and-width checks in headless Edge (375, 768, 1280 px).
- 16 bugs: 2 High, 7 Medium, 7 Low. All fixed and re-tested. The full table is in `QA_REPORT.md`.
- Not tested, and said so: a real phone, clicking popups and menus (no browser extension), Flask really stopped (simulated with a closed port), an LLM time-out.

**Why this way**
- Throwaway members, never `seed.sql`: importing the seed would delete my own accounts. Test rows were removed through the app's own Delete account, which tested that feature too.
- A test is a request **plus the expected answer**. "The page opened" proves little; "the database row now says X" proves the feature.
- Fix, then run the same test again, then run the whole sweep: a fix can break something else.

**The lines that matter**

The two High bugs were both "a column may be NULL, the code assumed it was not":
```php
// plant-form.php, Edit: a typed species lives in custom_species, not in plant_species
'species' => $plant['scientific_name'] ?? ($plant['custom_species'] ?? ''),

// listing-form.php: plants.photo may be NULL, exchange_listings.photo may not
} elseif ($plant && ($plant['photo'] ?? '') === '') {
    $errors['plant'] = $plant['nickname'] . ' has no photo yet. …';
}
```
Log out became a POST form with the token, because a link can be triggered from any other website:
```php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    check_csrf();
    log_out();
}
```
One CSS line fixed sideways scrolling on six pages:
```css
body { overflow-wrap: anywhere; }   /* a word with no spaces breaks instead of widening the page */
```

**Problems and fixes**
- My first sweeps showed no errors, but PHP 8 "Deprecated" notices are hidden by XAMPP's `php.ini` (`E_ALL & ~E_DEPRECATED`). With `E_ALL` switched on for the test, three `null` bugs appeared.
- `curl -F "name=<i>x</i>"` reads a **file** when the value starts with `<`. Text fields need `--form-string`.
- A page with no form has no CSRF token to copy, so a test must take the token from a page that has one.
- One leftover scan photo looked like my test file, but a row of **my own account** pointed at it (I had used the site during the test). Checked the database before deleting, so it was kept.

> [!tip] What to remember
> - Every column that allows NULL is a test case: open the page with that column empty.
> - Anything that changes state (even logging out) is a POST with a CSRF token.
> - When a row is deleted, its **files** are not: collect the file names first, delete the files after.
> - Test with worst-case text: one 100-letter word, `<script>`, an apostrophe.

**Check yourself**
1. Why was Log out changed from a link to a form?
2. Deleting an account removed every row through the foreign keys. What was still left behind, and why could the database not clean it up?
3. Why did phone photos lie on their side after uploading, although they looked right in the phone's gallery?

> [!success]- Answers
> 1. A link is a GET request, and another website can make a visitor's browser send it (a hidden image is enough). A POST needs the CSRF token from our own page, which the other site cannot read.
> 2. The photo files in `public/uploads/`. The database stores only the path; `ON DELETE CASCADE` removes rows, not files. `member_photo_files()` lists the paths before the DELETE, and `forget_photo()` removes each file afterwards if no other row uses it.
> 3. The camera stores the pixels sideways and adds an EXIF "Orientation" note. Re-drawing the picture with GD (the resize to 1600 px) drops that note, so `upload.php` now turns the picture first (`imagerotate()`).
