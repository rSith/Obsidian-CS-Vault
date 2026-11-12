---
type: project-note
project: PlantFolio
status: draft
tags: [project, web]
---
# PlantFolio Week 08 — Database, Accounts and Sessions
> [!info] [[PlantFolio - Project Home]] · Stage B · 23–29 Nov 2026
> **Study notes:** [[03.00 Database and Accounts]] · **Revision:** [[03.99 Summary - Database and Accounts]]

> [!abstract] Week goal
> The database exists, and a visitor can register, log in and log out securely. From here on, the security rules in `CLAUDE.md` are checked on every change.

## Tasks
- [x] **DES-07/DES-08 catch-up**: stage F schema changes accepted into the database docs (step 8.0)
- [x] **CORE-01** `database/schema.sql`: InnoDB, utf8mb4, foreign keys, re-runnable → [[03.01 MySQL Schema - Types, Keys and Foreign Key Rules]] (step 8.1)
- [x] **CORE-02** `database/seed.sql`, built from the mock data (step 8.2)
- [x] **CORE-03** `config.php` and `includes/db.php`: one PDO connection, exceptions on, emulated prepares off → [[03.02 PDO and Prepared Statements]] (step 8.3)
- [x] **CORE-07** Registration → [[03.06 Form Handling - Validation, Redirects and Flash Messages]] · [[03.03 Password Hashing and Login Lockout]] (wired, step 8.5)
- [x] **CORE-08** Real username check (UI and wired, step 8.9) → [[02.03 Client-Side Form Validation]]
- [x] **CORE-09** Login, logout, sessions → [[03.04 PHP Sessions and Login State]] (wired, steps 8.6–8.8, with Remember me → [[03.08 Remember Me Tokens]])
- [ ] **CORE-10** `includes/auth.php`: `require_login()`, ownership checks → [[03.07 Access Control and Ownership Checks]] (steps 8.4, 8.7: sessions, current_user(), require_login() on the 8 member pages; ownership checks come with each page in Phase 3)
- [ ] **CORE-11** CSRF tokens → [[03.05 CSRF Tokens]] (helpers built in step 8.4; Sign up checks it; the other POST forms still to do)
- [x] **CORE-12** Login lockout: 15 minutes after 5 failures (wired, step 8.6)
- [x] **CORE-26** Settings saves (profile, password) (wired, steps 8.10–8.12; profile photo waits for CORE-17)

## Learn this week
PDO prepared statements, `password_hash()`, sessions. Start with the lesson overview: [[03.00 Database and Accounts]].

## Tests
- [ ] Test Plan cases TC-001 – TC-011, TC-028, TC-029 pass and are logged in the Task Sheet
- [ ] Wrong inputs tried: empty forms, wrong password, `<script>` in text fields, `' OR '1'='1` in a field

## Security checks for this week
- [ ] Every query uses a prepared statement
- [ ] Passwords stored only with `password_hash()`; one generic login error
- [ ] `session_regenerate_id(true)` on login; logout destroys the session
- [ ] Every POST form carries and checks a CSRF token

---
## Work log
Step sections are added here as each step is finished.

### Step 8.0 — Database design catch-up before the schema

> [!important] Goal
> **DES-07 / DES-08 (update).** Before writing `schema.sql`, make the database design match the finished frontend. Every field the pages added during stage F gets a decided type and place.

**What was done** (11 Nov 2026, branch `feature/community`, commits `5767aa9` and `ccd60d0`)
- `database/README.md`: the table plan now has **18 tables and 26 foreign keys**. A new section, "Stage F close (11 Nov 2026)", lists the seven accepted changes:
  1. `exchange_listings.light_level` and `water_need` (ENUM, NULL = "Not sure"), for the Exchange filters.
  2. `km_from_town` (0–30) on `users` (NULL when there is no town) and on `exchange_listings` (NOT NULL, default 0).
  3. New table `listing_categories (listing_id, category)` with a two-column primary key.
  4. `health_logs.type` (note, photo, care, health_check, milestone), `care_task`, and `status` may now be NULL.
  5. `care_reminders`: tasks mist, rotate, prune and repot; `remind_time`, `repeat_type`, `repeat_days`; `interval_days` may be NULL.
  6. New table `care_guides`, the cache for the AI care guide.
  7. Plant stacks: no change. Stacks are worked out when the page loads.
- `database/data-dictionary.md`: every new column with type, null, default and key, marked **(F)**; tables 17 and 18; the extra foreign key, unique key and checks; four new "Rules checked in PHP".
- Code comments no longer call these columns "proposed". `CLAUDE.md` now says stage B is the current stage.
- Pushed `feature/community` and merged it into `develop` as **PR #9** (merge commit `42a7d12`), opened and merged through the GitHub API because the `gh` CLI is not installed. The first push attempt was blocked by Claude Code's permission check until Ranshitha allowed GitHub actions. New branch `feature/schema` from `develop` for CORE-01.

**Why this way**
- The mock arrays were shaped like future rows, so every key a page reads must become a real column. Otherwise wiring would break pages. Fixing the design first means `schema.sql` is written once.
- **The UNIQUE key on reminders stays.** I first suggested dropping UNIQUE (plant_id, task) so a plant could have two water reminders. But the built popup already replaces the earlier reminder for the same task (with Undo), so the database keeps the rule the UI follows, and saving becomes `INSERT … ON DUPLICATE KEY UPDATE`.
- **Categories get a table, light gets a column.** A listing has exactly one light level, but can have several categories. Several values in one column would break first normal form, so it is a many-to-many link to a fixed list.
- **`care_guides.species` has no foreign key.** A member can type a species that isn't in `plant_species` (`custom_species`), and that plant should still get a guide.

```sql
-- One listing, many categories: the pair is the key, so no duplicates and no id column
CREATE TABLE listing_categories (
  listing_id INT UNSIGNED NOT NULL,
  category ENUM('indoor','outdoor','succulents','air_purifying','flowering','edible','pet_friendly') NOT NULL,
  PRIMARY KEY (listing_id, category),
  FOREIGN KEY (listing_id) REFERENCES exchange_listings(id) ON DELETE CASCADE
);

-- A second "water Monty" reminder replaces the first, as the popup does
INSERT INTO care_reminders (plant_id, task, remind_time, repeat_type, interval_days, next_due)
VALUES (?, ?, ?, ?, ?, ?)
ON DUPLICATE KEY UPDATE remind_time = VALUES(remind_time), repeat_type = VALUES(repeat_type),
  interval_days = VALUES(interval_days), next_due = VALUES(next_due);
```
(This is a preview of CORE-01 and CORE-22; the real SQL comes in those steps.)

> [!tip] What to remember
> Design changes go into `database/README.md` and `data-dictionary.md` **before** `schema.sql`. The docs are the source of truth, and the schema copies them. A field shown on a page needs a column, and a value that can repeat needs its own table.

**Check yourself**
1. Why is `listing_categories` a separate table, but `light_level` is a column?
2. Why can `health_logs.status` be NULL now?
3. What does UNIQUE (plant_id, task) mean when a member adds a second water reminder for Monty?

> [!success]- Answers
> 1. A listing has one light level but can be in several categories. Storing a list in one column breaks first normal form, so a many-to-many relationship with a fixed list needs a link table.
> 2. A care entry ("Watered · 11 Nov") says nothing about the plant's health. NULL means "no health reading here", and the plant's `health_status` is taken from the newest log that has one.
> 3. There can only be one row per plant and task, so the new reminder replaces the old one (`ON DUPLICATE KEY UPDATE`). That is the same thing the Add a reminder popup does.

### Step 8.1 — `schema.sql`: the 18 tables

> [!important] Goal
> **CORE-01.** One SQL file that builds the whole PlantFolio database (18 tables, 26 foreign keys, unique keys, checks, indexes) exactly as `database/data-dictionary.md` describes, and gives the same empty database every time it runs.

**What was done** (11 Nov 2026, branch `feature/schema`, commit `41e67ba`)
- New `database/schema.sql`. It starts with `DROP TABLE IF EXISTS` for all 18 tables (foreign key checks off only while dropping), then creates them **parents first**: `towns`, `users`, `remember_tokens`, `collections`, `plant_species`, `plants`, `plant_photos`, `ml_predictions`, `health_logs`, … `care_guides`.
- Every table is InnoDB with `utf8mb4_unicode_ci`. Constraints have readable names (`fk_plants_user`, `uq_users_username`, `chk_ratings_score`), so an error message says which rule was broken.
- The local `plantfolio` database already had 16 **empty** tables from an older design (made 2 Nov). The new schema replaced them; no data was lost.
- Ran it twice on XAMPP's **MariaDB 10.4.32**: 18 tables, 26 foreign keys, 8 checks (our 5, plus 3 `JSON_VALID` checks MariaDB adds by itself to JSON columns).
- Tried to break it. Rejected: a duplicate username, `km_from_town` 45, a plant for a user that doesn't exist, deleting a town still in use (RESTRICT), a rating of 6, invalid JSON, a second water reminder for the same plant. Deleting a user removed their plants and reminders (CASCADE).

**Why this way**
- **Drop, then create** makes the file re-runnable: whoever imports it (me, a teammate, the examiner) always gets the same database.
- **Parents first**, because a foreign key can only point at a table that already exists. `ml_predictions` is created before `health_logs` because a story entry can point at a saved AI result.
- **One index can do two jobs.** `UNIQUE (user_id, name)` on `collections` starts with `user_id`, so it also serves the foreign key; `(plant_id, logged_at)` on `health_logs` serves the foreign key *and* "this plant's story, newest first".

```sql
CREATE TABLE plants (
    id            INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id       INT UNSIGNED NOT NULL,
    collection_id INT UNSIGNED NULL DEFAULT NULL,
    nickname      VARCHAR(60)  NOT NULL,
    health_status ENUM('thriving','stable','needs_attention','sick') NOT NULL DEFAULT 'stable',
    -- ...
    PRIMARY KEY (id),
    CONSTRAINT fk_plants_user       FOREIGN KEY (user_id)       REFERENCES users (id)       ON DELETE CASCADE,
    CONSTRAINT fk_plants_collection FOREIGN KEY (collection_id) REFERENCES collections (id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

**Problems and fixes**
- **A bad value was accepted.** `INSERT … health_status = 'dying'` printed no error: MariaDB stored an empty value with only a *warning*. Cause: XAMPP's `my.ini` sets `sql_mode=NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION`, which is **not strict**. Too-long text is also cut short silently. With `STRICT_ALL_TABLES` on, both are rejected (`Data truncated…`, `Data too long…`). Fix planned for CORE-03: `db.php` switches every connection to strict mode, so it works on any machine without editing `my.ini`.
- My first reminder test failed for the wrong reason: the rejected "user 99" plant had still used up `AUTO_INCREMENT` value 1, so Monty got id 3 and the test pointed at plant 1. Lesson: a failed INSERT can still consume an id, so never assume ids in tests; look them up.

> [!tip] What to remember
> The schema is the **last line of defence**: PHP checks input first, but the database refuses anything that breaks a key, a foreign key or a CHECK. That only works fully in **strict mode**, which XAMPP does not switch on.

**Check yourself**
1. Why does `schema.sql` drop every table first, and why is that dangerous?
2. Why must `users` be created before `plants`?
3. XAMPP accepted `'dying'` in an ENUM column. Why, and how does PlantFolio stop it?

> [!success]- Answers
> 1. So it can be run again and always gives the same clean database. Dangerous because it deletes all data: never run it on a database with real members.
> 2. `plants.user_id` is a foreign key to `users.id`; MySQL can't create a reference to a table that doesn't exist yet.
> 3. XAMPP's MariaDB runs in non-strict mode, which turns a bad value into a warning and stores `''`. `db.php` sets `sql_mode` to include `STRICT_ALL_TABLES` on every connection, so the INSERT fails with an error instead.

### Step 8.2 — `seed.sql`: the mock data as real rows

> [!important] Goal
> **CORE-02.** Fill the 18 tables with the same sample content the pages show now (Nimali, Monty, Goldie, Kurunegala …), so wiring a page means swapping the data source and nothing on screen changes.

**What was done** (11 Nov 2026, branch `feature/schema`, commits `4fe7cf7` and fix `cb54f62`)
- New `database/seed.sql` (385 lines): 31 towns, 5 members, 14 species, 12 collections, 23 plants, 25 story entries, 14 reminders, 65 likes, 8 AI predictions, 4 disease guides, 14 listings with 26 categories, 23 interests, 10 ratings, 8 cached care guides.
- It was **generated** from `includes/mock-data.php` by a one-off PHP script (kept outside the repo), not typed by hand, so every value is exactly what the pages print. The committed file is plain SQL.
- **Same ids as the mock data**: Monty is still plant 42, Nimali user 1, so `plant.php?id=42` keeps working after wiring.
- **Relative dates**: `mock_date(-2)` became `CURDATE() - INTERVAL 2 DAY`; "just now" AI results became `NOW() - INTERVAL 3 MINUTE`. Old fixed dates (Member since Mar 2025) stay as written.
- **Demo password** `PlantFolio#2026` for all five members; only its `password_hash()` is stored. Noted in `database/README.md`.
- Running it again **empties and reloads** every table: it is also the reset script.
- Rows added that the mock data only implied: Stripes' and Slim's water reminders, Monty's first species prediction (id 1), Nimali's four older closed swaps (listings 1–4, so her reviews have a listing), and interest rows behind the "N interested" counts on other members' listings.

**Why this way**
- **One transaction around everything**: the tables are emptied with `DELETE FROM`, children first, and then filled, all between `START TRANSACTION` and `COMMIT`. If any row is wrong, the whole import is undone, including the deletes, so the old data stays.
- **Strict mode at the top of the file** (see step 8.1): a bad value stops the import instead of becoming a warning.
- Ids are written explicitly, so the foreign keys between seed rows are known in advance. After the import, `AUTO_INCREMENT` continues from the highest id.

```sql
START TRANSACTION;
DELETE FROM care_guides;         -- … every table, children first, users and towns last

INSERT INTO care_reminders (id, plant_id, task, repeat_type, interval_days, last_done, next_due) VALUES
    (11, 42, 'water', 'interval', 7, CURDATE() - INTERVAL 7 DAY, CURDATE()),                  -- Monty: due today
    (12, 43, 'water', 'interval', 5, CURDATE() - INTERVAL 7 DAY, CURDATE() - INTERVAL 2 DAY); -- Goldie: overdue 2 days
COMMIT;
```

**How it was checked**
- Imported twice (second time = reset): same row counts.
- Page-shaped queries: Today's care gives Goldie (−2), Stripes (−1), Monty and Sergeant (today); each open listing's interest count equals its mock `interest_count`; Haversine distances in SQL equal PHP's `town_distance_km()` (Kandy 37 km, Pannala 42 km).
- Every "Rule checked in PHP" from `data-dictionary.md` was turned into a query that counts rows breaking it: all 0.
- `password_verify('PlantFolio#2026', …)` on Nimali's stored hash: OK.

**Problems and fixes / things to remember for wiring**
- **First version used `TRUNCATE`** with `SET FOREIGN_KEY_CHECKS = 0`. That works in the `mysql` command line, but phpMyAdmin runs a short import one statement at a time and switches foreign key checks back **on** before each (learned on 2 Nov), and `TRUNCATE` refuses a table that other tables point at (`users`, `plants` …). Fix `cb54f62`: `DELETE FROM`, children first, inside the transaction. Tested with checks forced on (imports twice), and with a bad row added at the end: the import failed and all 23 plants were still there.
- Numbers the mock data only typed become real counts, so they shrink: Nimali shows **12 plants** (not 20), **5 ratings, average 4.4** (not 23 and 4.9), at most **4 likes** per plant (only 5 members exist). Expected, not a bug.
- Care entries store `note` as **NULL**, not `''`. `partials/story-entry.php` tests `!== ''`, which must change when the Story is wired (CORE-19).
- Photos are still `mock/monty.svg`. When `photo_url()` switches to `uploads/` (CORE-17) it must keep sending `mock/` paths to `assets/img/`.

> [!tip] What to remember
> Seed data is **test data with a purpose**: it must hit every state the pages draw (due today, overdue, sick, private plant, closed listing, unrated exchange, missing care guide). Writing dates relative to `CURDATE()` keeps those states true on the day of the demo.

**Check yourself**
1. Why is `seed.sql` wrapped in `START TRANSACTION … COMMIT`?
2. Why not write `next_due = '2026-11-11'` for Monty's reminder?
3. Why does the seed insert explicit ids instead of letting `AUTO_INCREMENT` choose?

> [!success]- Answers
> 1. So the import is all or nothing: if one INSERT fails, every row inserted before it is rolled back and no half-filled database is left.
> 2. A fixed date is only "due today" on that one day. `CURDATE()` is worked out when the seed is loaded, so the demo always has something due today and something overdue.
> 3. Other rows point at them (plants → collections, logs → plants), and the pages' links use them (`plant.php?id=42`). Fixed ids keep those references and links correct.

### Step 8.3 — `db.php`: one shared PDO connection

> [!important] Goal
> **CORE-03.** One function, `db()`, that every page calls to talk to the database: one connection per request, exceptions on, emulated prepares off, `utf8mb4`.

**What was done** (11 Nov 2026, branch `feature/schema`, commit `1a3651e`)
- New `includes/db.php` with `db()`, built as in [[03.02 PDO and Prepared Statements]] section 4, plus three additions:
  1. **Strict SQL mode** on every connection (the fix for the problem found in step 8.1).
  2. **MySQL's time zone set to PHP's** (`+05:30`), so `CURDATE()` and `date()` agree on what "today" is.
  3. **A safe failure**: if the connection fails, the reason goes to the PHP error log and the visitor sees a plain message. In `development` the message includes the reason and a hint ("Is MySQL running in XAMPP…?").
- `config/config.php` needed no change: the `db_*` settings were already in `config.example.php` from week 1.
- No page uses `db()` yet. That starts with Phase 2 (accounts).

**Why this way**
- **`static $pdo`** keeps the connection between calls in the same request: ten queries on a page share one connection instead of opening ten.
- **`require_once helpers.php`** at the top: `db()` needs `config()`, and PHP's time zone must already be set when `date('P')` runs.
- **Never show the PDO exception's trace**: it lists the arguments of `new PDO()`, which include the database password.

```php
function db(): PDO
{
    static $pdo = null;
    if ($pdo === null) {
        $dsn = 'mysql:host=' . config('db_host') . ';dbname=' . config('db_name') . ';charset=utf8mb4';
        try {
            $pdo = new PDO($dsn, config('db_user'), config('db_pass'), [
                PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
                PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
                PDO::ATTR_EMULATE_PREPARES   => false,
            ]);
        } catch (PDOException $error) {
            error_log('PlantFolio: database connection failed: ' . $error->getMessage());
            http_response_code(500);
            exit('Sorry, PlantFolio is having trouble right now. …');   // (development shows the reason)
        }
        $pdo->exec("SET SESSION sql_mode = 'STRICT_ALL_TABLES,…'");
        $pdo->exec("SET time_zone = '" . date('P') . "'");
    }
    return $pdo;
}
```

**How it was checked** (a test script outside the repo, run with XAMPP's PHP)
1. `db()->prepare(… WHERE p.id = :id)` with 42 returned Monty, Swiss cheese plant, thriving. The id came back as the integer `42`, not the string `"42"`, because real prepared statements keep MySQL's types.
2. `db() === db()`: the same object, so one connection.
3. Emulated prepares `0`; `sql_mode` strict; MySQL `+05:30 22:01:59` = PHP `+05:30 22:01:59`.
4. Inserting `health_status = 'dying'` through PDO threw an exception (before strict mode it would have been saved as `''`).
5. Searching for the nickname `' OR '1'='1` found **0** rows: the placeholder treats it as plain text.
6. `fetch()` for plant 99999 returned `false`.
7. A copy with a wrong password: development showed "Access denied for user 'root'@'localhost'" and the hint; production showed only "Sorry, PlantFolio is having trouble right now"; the password was in neither the output nor the log.

> [!tip] What to remember
> `db()` is the **only** place that connects to MySQL. Every page asks it for the connection, so security settings (exceptions, real prepares, strict mode) are set once and can't be forgotten on one page.

**Check yourself**
1. Why does `db()` keep the connection in a `static` variable?
2. What would go wrong without `SET time_zone`?
3. Why does `db.php` catch the connection exception instead of letting PHP print it?

> [!success]- Answers
> 1. A `static` variable keeps its value between calls of the function during one request, so the connection is created once and reused by every query on the page.
> 2. MySQL might use a different clock from PHP. Near midnight `CURDATE()` and `date('Y-m-d')` could give different days, so a reminder could be "due today" in the query but shown as tomorrow (or overdue) on the page.
> 3. PHP's uncaught exception page shows the stack trace, which includes the arguments passed to `new PDO()`: the database username and password. Catching it lets us log the reason privately and show the visitor a plain message.

**Phase 1 complete:** CORE-01, CORE-02 and CORE-03 done on `feature/schema` (3 commits, not pushed). Next: Phase 2, accounts (CORE-07 registration first).

## Phase 2: accounts

### Step 8.4 — `auth.php`: sessions and CSRF tokens

> [!important] Goal
> **CORE-11 and the start of CORE-10.** The small account helpers every page will share, built before Sign up because a real sign-up needs a session to log the member in and a CSRF token on its form.

**What was done** (11 Nov 2026, new branch `feature/accounts` from `feature/schema`, commit `dfe8567`)
- New `includes/auth.php`:
  - `start_session()`: session strict mode, cookie `HttpOnly` + `SameSite=Lax` (+ `Secure` on HTTPS).
  - `csrf_token()` / `csrf_field()` / `check_csrf()`: a 64-character random token per session, compared with `hash_equals()`; a wrong or missing token stops the request with 403 "This form has expired…".
  - `current_user()`: the logged-in member's `users` row with `towns.name AS location` (the mock key), **without** `password_hash` or the lockout columns; looked up once per request.
  - `log_in($user_id)`: `session_regenerate_id(true)`, stores the id, renews the CSRF token. `log_out()`: empties the session, deletes the cookie, destroys it.
- `mock-data.php` now starts its session through `start_session()`, so every page gets the safe cookie.
- Checked: the response now sends `Set-Cookie: PHPSESSID=…; path=/; HttpOnly; SameSite=Lax`, and 10 pages (member and visitor) load without PHP errors.

**Why this way**
- **Visitors never touch the database.** `current_user()` queries only when the session holds a `user_id`, so public pages still work if MySQL is stopped.
- **The CSRF token lives in the session**, so another site can make a browser *send* a form to PlantFolio but can't *read* our page to learn the token.

```php
function check_csrf(): void
{
    $sent = $_POST['csrf_token'] ?? '';
    if (!is_string($sent) || !hash_equals(csrf_token(), $sent)) {
        http_response_code(403);
        exit('This form has expired. Go back, refresh the page and try again.');
    }
}
```

> [!tip] What to remember
> Three different defences against three attacks: `HttpOnly` (XSS stealing the cookie), `session_regenerate_id` + strict mode (session fixation), CSRF token + `SameSite` (another site submitting our forms).

**Check yourself**
1. Why is the CSRF token compared with `hash_equals()` and not `===`?
2. What does `session.use_strict_mode` stop?

> [!success]- Answers
> 1. `===` stops at the first different character, so the time it takes leaks how much of a guess was right. `hash_equals()` always takes the same time.
> 2. A session id that the server never created. Without it, an attacker could put a made-up id into a victim's browser and use it after the victim logs in (session fixation).

### Step 8.5 — Sign up saves a real member

> [!important] Goal
> **CORE-07 (wired).** Create account checks everything on the server, saves the `users` row with `password_hash()`, logs the new member in and opens their empty Dashboard.

**What was done** (11 Nov 2026, branch `feature/accounts`, commit `40c2791`)
- `public/register.php` on POST: `check_csrf()` → read every field with `post_text()` → validate → one query checks username **and** email are free → `INSERT` with `password_hash()` → `log_in()` → redirect to `dashboard.php`.
- Rules: name 1–100; username `^[a-z0-9._]{3,30}\z`, saved lowercase; email by `FILTER_VALIDATE_EMAIL`, saved lowercase; password 8–72 with a number (not trimmed); town optional but must be a row of `towns` (the list is now read from the database), km a whole number 0–30, NULL without a town; guidelines box ticked.
- Errors appear under each field (`.field-error`, `aria-invalid`, `aria-describedby`), with a summary line "Please check the 2 fields marked below." Typed values come back; the password never does.
- `public/logout.php` now calls `log_out()`.
- **Bridge in `mock-data.php`** (Ranshitha chose option A): a member who really logged in becomes `$current_user`. Nimali (user 1) keeps her mock garden with her real row laid over it; any other real member sees the brand-new-member view (the same as `?empty=1`) until Phase 3 makes the pages read the database.

**Why this way**
- **Server-side validation even though the form has `required` and `pattern`:** anyone can send a POST with curl and skip the browser.
- **Post/Redirect/Get:** after saving, the page redirects. Refreshing the Dashboard repeats a harmless GET, not the sign-up.
- **The 72-character limit:** bcrypt uses only the first 72 bytes ([[03.03 Password Hashing and Login Lockout]]).
- **Two checks for "already taken":** the SELECT gives a friendly message; the UNIQUE key in the database catches two people signing up with the same name at the same instant (SQLSTATE `23000`), which becomes the same form message.

```php
try {
    $stmt = db()->prepare('INSERT INTO users (username, full_name, email, password_hash, town_id, km_from_town)
                           VALUES (:username, :full_name, :email, :password_hash, :town_id, :km_from_town)');
    $stmt->execute([..., 'password_hash' => password_hash($password, PASSWORD_DEFAULT), ...]);
    log_in((int) db()->lastInsertId());
    header('Location: ' . url('dashboard.php'));
    exit;
} catch (PDOException $error) {
    if ($error->getCode() !== '23000') {
        throw $error;                        // a real fault, not a duplicate
    }
    $errors['username'] = '@' . $values['username'] . ' was just taken. Try another username.';
}
```

**How it was checked** (curl with a cookie jar, playing a browser)
- No CSRF token → 403; wrong token → 403.
- Empty form → 5 messages. Username `nimali.grows` and email `NIMALI.P@example.com` → both "already taken" (capitals don't help). Town "Atlantis" → "Choose a town from the list". km 99 → refused.
- Username `' OR '1'='1` → format error (it never reaches SQL as code anyway). A 74-character password → "72 characters or fewer".
- Valid sign-up as "Kasun `<b>Silva</b>`", username typed `Kasun.Test` → 302 to the Dashboard; the row has username `kasun.test`, a 60-character `$2y$10$…` hash, Kandy, 4 km. The Dashboard prints `Kasun &lt;b&gt;Silva` (escaped), shows no Nimali plants, and Garden, Settings, Community, My Exchanges and the new member's Public Profile load with no PHP errors.
- Logged in, `register.php` → Dashboard. Log out → `PHPSESSID=deleted`; the Dashboard then sends to Log in.
- Registering `kasun.test` again → "already taken". The test account was deleted afterwards.

**Problems and fixes**
- My first "are typed values kept?" test printed nothing: `grep` reads one line at a time, and the input's attributes are spread over two lines. Searching with `grep -A1` showed `value="kasun.test"`: it worked all along.
- `plant.php?id=42` (Monty) gives **404** to a real new member. Not new: Nimali's own `?empty=1` preview does the same, because the new-member view removes her plants. It disappears when the Plant Profile reads the database (Phase 3).

> [!tip] What to remember
> Validate on the server, store only the hash, redirect after a successful POST, and let the database's UNIQUE key be the final judge of "already taken".

**Check yourself**
1. The form already has `required` and `pattern`. Why check everything again in PHP?
2. Why does Sign up refuse passwords longer than 72 characters?
3. What is Post/Redirect/Get, and what would happen without it here?

> [!success]- Answers
> 1. Browser checks run on the visitor's computer and can be skipped (DevTools, curl, a hand-made POST). Only the server's checks can be trusted.
> 2. `password_hash()` uses bcrypt, which reads only the first 72 bytes. Anything after that would be ignored at login, so a longer password would give a false sense of security.
> 3. After a successful POST the server answers with a redirect, and the browser loads the next page with GET. Without it, refreshing the page would send the sign-up form again: a second account attempt, and a "taken" error for your own username.

### Step 8.6 — Real Log in with the 15-minute lockout

> [!important] Goal
> **CORE-09 and CORE-12 (wired).** Log in checks the password for real; five wrong passwords in a row lock the account for 15 minutes.

**What was done** (11 Nov 2026, branch `feature/accounts`, commit `3d44aa4`)
- `attempt_login($login, $password)` in `includes/auth.php`, built from the sketch in [[03.03 Password Hashing and Login Lockout]] section 5 with six changes (listed in a callout there): username *or* email, a dummy hash for unknown users, MySQL counts failures, the count restarts at the lock, minutes left from MySQL, rehash on success.
- `public/login.php`: `check_csrf()`, empty-field message, `attempt_login()`, then `log_in()` and a redirect to `?next` (a listing) or the Dashboard. One message, above both fields, for every failure; the typed login is kept, the password never.
- Ranshitha chose to show **"Too many attempts. Try again in 15 minutes."** while locked, instead of the generic message: it reveals the account exists, but only after 5 tries, and a real member would otherwise not know why the right password fails.

**Why this way**

```sql
UPDATE users
SET locked_until  = IF(failed_logins + 1 >= 5, NOW() + INTERVAL 15 MINUTE, locked_until),
    failed_logins = IF(failed_logins + 1 >= 5, 0, failed_logins + 1)
WHERE id = :id
```
MySQL runs `SET` from left to right, so `locked_until` must come first: it has to see the *old* `failed_logins`.

**How it was checked**
- No token → 403. Empty fields → "Enter your email or username and your password." Unknown user and `' OR '1'='1` → the generic message (the typed text came back escaped: `&#039; OR &#039;1&#039;=&#039;1`).
- Five wrong passwords: the database showed `failed_logins` 1, 2, 3, 4, then 0 with `locked_until` 15 minutes ahead. The **right** password then gave "Try again in 15 minutes".
- After moving `locked_until` into the past: one wrong password gave `failed_logins = 1`, no new lock. Right password with the email in CAPITALS → Dashboard, counter cleared.
- `?next=listing.php?id=8` → back to the listing at `#summary`; `next=https://evil.example` → Dashboard.
- Timing, three runs: real user + wrong password 0.110–0.118 s, unknown user 0.125–0.136 s.

> [!tip] What to remember
> A login form leaks information in three ways: the message, the lockout, and the **time** it takes. PlantFolio keeps the message the same and the time about the same.

### Step 8.7 — `require_login()`, and the fake login removed

> [!important] Goal
> **CORE-10 (part).** Members-only pages send visitors to Log in and back again, and the stage F preview login (which had no password) is gone.

**What was done** (11 Nov 2026, branch `feature/accounts`, commit `63440d1`)
- `require_login()` in `auth.php` replaces the "Stage F stand-in" block on the eight member pages (dashboard, garden, plant-form, identify, health-check, listing-form, my-exchanges, settings). It redirects to `login.php?next=<this page and its query>`.
- `return_path()` (helpers.php) now accepts any of our own pages by file name with a plain query string, and still refuses `https://…`, `//…`, `/etc/passwd`, `..\…`, `<script>`, a hidden line break, and the account pages themselves. `#summary` is added only for listings.
- **Removed:** `?member`, `?guest`, `mock_log_in_and_go()`, `mock_become_visitor()`. `$current_user` now comes only from the session. `?empty=1` stays (it changes the view, it logs nobody in). `includes/README.md` and the style guide hint updated.

**Why this way**
- With a real login, `?member` would have been a **back door**: anyone could add it to an address and see Nimali's pages, email and phone. Previewing as Nimali now means logging in as `nimali.grows`.
- Coming back to the page you wanted (`?next`) makes the redirect feel like a pause, not a dead end; `return_path()` makes sure it cannot become an **open redirect**.

**How it was checked**
- As a visitor, all eight pages (and `dashboard.php?member`) → 302 to `login.php?next=…`.
- Logging in from `login.php?next=plant-form.php%3Fid%3D42` → back to `plant-form.php?id=42`.
- 22 page views as Nimali and 8 as a visitor: all 200 (the Landing page sends a member to the Dashboard, 302, as designed), no PHP errors.

### Step 8.8 — "Remember me"

> [!important] Goal
> **CORE-09 (Remember me).** The checkbox keeps a member logged in on that browser for 30 days, safely.

**What was done** (11 Nov 2026, branch `feature/accounts`, commit `e25548f`)
- `remember_me_set()`, `remember_me_log_in()`, `remember_cookie()` in `auth.php`; `current_user()` tries the token when the session has no login; `log_out()` deletes this browser's token and both cookies. Explained from the start in the new note [[03.08 Remember Me Tokens]].

**How it was checked**
- Logged in with the box ticked, then deleted the session cookie (a closed browser): the Dashboard still opened, and the token in the database had a **new** selector.
- The old cookie replayed → Log in. Right selector with a wrong validator → Log in, and that token was deleted, so even the genuine cookie stopped working.
- The database held `SHA2(validator)`, not the validator. Expiry 30 days. An expired token → refused and removed.
- Logout → `plantfolio_remember=deleted`, `PHPSESSID=deleted`, 0 rows. Login without the box → no token. A garbage cookie (`<script>`) → ignored.

**Problems and fixes**
- Two checks first looked like failures. The test script read the cookie from curl's cookie file, where `:` is stored as `%3A`, so the script never split selector from validator. Decoding it first showed the code was right. Lesson: when a test fails, check the test before the code.

> [!tip] What to remember
> Treat a remember-me token like a password that the computer chose: random, stored only as a hash, compared with `hash_equals()`, used once, and deleted on logout.

**Check yourself**
1. Why does the lockout UPDATE set `locked_until` before `failed_logins`?
2. Why would `?member` have been a security hole after step 8.6?
3. Why does the `remember_tokens` table store `sha256(validator)` and not the validator?

> [!success]- Answers
> 1. MySQL evaluates `SET` assignments left to right, using the new values. If `failed_logins` were reset to 0 first, the `locked_until` test would see 0 and never lock.
> 2. It switched the preview to "logged in as Nimali" without a password, so anyone could read her member pages and contact details.
> 3. So that a stolen copy of the database cannot be turned into working cookies. The validator is long and random, so a fast hash like SHA-256 cannot be reversed by guessing.

### Step 8.9 — Live username check

> [!important] Goal
> **CORE-08 (UI and wired together).** While a visitor types a username on Sign up, the page says whether it is free, without reloading.

**What was done** (11 Nov 2026, branch `feature/accounts`, commit `89288e5`)
- `username_problem($username)` in `includes/auth.php`: format check, then `SELECT 1 FROM users WHERE username = :username`. Returns the error message or `null`. Sign up (`register.php`) now uses it too, and only checks the email itself.
- New `public/api/check-username.php` (the first file in `public/api/`): `GET ?username=` → JSON `{"available": …, "message": …}`, `Cache-Control: no-store`, no CSRF token because it only reads.
- New `public/assets/js/register.js`: debounce 400 ms, `AbortController`, answer shown in an `aria-live` line under the hint (`.field-ok` green, `.field-error` red; new `.field-ok` style in `components.css`).
- The Week 6 UI half had never been built, so both halves were done in one step, as described in [[02.03 Client-Side Form Validation]] section 6 (a callout there lists what changed).

**Why this way**
- **One rule, two callers.** If the live check and the submit check had their own copies, they could disagree ("available" while typing, "taken" on submit).
- **The live check never decides.** If JavaScript or the endpoint fails, nothing is shown and the server still checks on submit (progressive enhancement, [[02.01 Progressive Enhancement and Script Organisation]]).
- **Showing "taken" leaks nothing new:** every member's profile is already public at `user.php?u=…`. Emails are never checked live.

```js
if (request) {
  request.abort();                     // a newer name replaces the old question
}
request = new AbortController();
const response = await fetch(url, { signal: request.signal });
const answer = await response.json();
if (input.value.trim().toLowerCase() === username) {   // still the same name?
  show(answer.message, answer.available ? 'ok' : 'taken');
}
```

**How it was checked**
- Endpoint with curl: `kasun.silva` available; `nimali.grows`, `NIMALI.GROWS` and `  Aroid.Amaya ` taken; `ab`, `bad name!`, `' OR '1'='1`, `<script>x</script>` → the format message; empty and `username[]=x` → "Choose a username."; headers `application/json` and `no-store`.
- The full Sign up test script ran again with the same results as step 8.5.
- In Chrome: typing `nimali.grows` one letter at a time sent **one** request; `Ab` sent none; the empty line was 0 px high, 21 px with a message; real keyboard typing of `kandy.jungle` showed "@kandy.jungle is already taken" in red under the hint. The only console error came from another Chrome extension (`companion.bundle.js`), not the page.

> [!tip] What to remember
> A live check is a convenience on top of the real check. Debounce cuts the number of requests; `AbortController` stops an old, slow answer from overwriting a new one.

**Check yourself**
1. Why does `check-username.php` need no CSRF token?
2. What problem does `AbortController` solve that debounce alone does not?
3. The live check says "available", but Sign up then says "taken". How?

> [!success]- Answers
> 1. CSRF protects requests that *change* something. This endpoint only reads, and another site cannot read its answer (the browser's same-origin rule).
> 2. Answers can arrive out of order. Without cancelling, a slow reply about an older, shorter name could replace the reply about the name now in the field.
> 3. Someone else signed up with that name between the check and the submit. The server check (and the `UNIQUE` key) is the real decision.

### Step 8.10 — Settings saves Profile, Privacy and Reminders

> [!important] Goal
> **CORE-26 (first part).** The Profile, Privacy and Reminders cards save to the member's own `users` row.

**What was done** (11 Nov 2026, branch `feature/accounts`, commit `0852ea7`)
- Every Settings form (6) now carries `csrf_field()`; the page calls `check_csrf()` on every POST.
- **Profile:** name 1–100; username through `username_problem()` (keeping your own is fine); bio up to 160, empty → NULL; town required (from the `towns` table), 0–30 km. Typed values and per-field errors come back after a failed save; a UNIQUE race becomes a form error.
- **Privacy / Reminders:** `new_plants_public` and `dashboard_reminders` saved as 0 or 1; only exactly `"1"` counts as on.
- **Decisions (Ranshitha):** the username hint now says "Your profile link changes too." (the database keeps no old usernames, so p22's "old @link keeps working" could not be kept); the profile photo waits for the upload handler (CORE-17).
- `register.js` renamed **`username-check.js`** and also runs on Settings; `data-current-username` stops it calling your own name "taken".

**Why this way**
- **The id comes from the session, never the form.** `UPDATE users … WHERE id = :id` uses `$me['id']` from `current_user()`. A test sent extra `id=1&user_id=1` fields: Nimali's row was untouched ([[03.07 Access Control and Ownership Checks]]).

**How it was checked:** no token → 403; all-wrong profile → 4 messages; km 45 refused; valid save with `<script>` in the bio → stored as typed, shown escaped; privacy and reminders off/on; hand-made value `2` → saved as 0.

### Step 8.11 — Change email and password

> [!important] Goal
> **CORE-26 (second part).** Email and password changes, both protected by the current password.

**What was done** (11 Nov 2026, commit `b09db51`)
- The lockout code moved into `check_password_and_count()`, shared by `attempt_login()` and the new `current_password_problem()`. A wrong current password in Settings **counts towards the same 5-try lock** as Log in.
- **Email:** valid, not your current one, not another account's, then the current password; saved lowercase. The `<details>` form stays open with the typed address after an error.
- **Password:** 8–72 characters with a number, typed twice, then the current password; `password_hash()`. Afterwards: **every Remember me token of the member is deleted** and the session id is regenerated; this browser stays logged in.

**Why this way**
- A computer left logged in should not let a passer-by change the email (and then reset the password) or guess the password through Settings without limit.
- A new password should lock out anyone who had the old one, including a Remember me cookie on a lost phone.

**How it was checked:** the Log in test script gave the same results after the refactor; every email and password error; 5 wrong current passwords → locked, then "Try again in 15 minutes" even with the right one; after a change, Remember me tokens 1 → 0, new session id, still logged in; at Log in the old password failed and the new one worked.

### Step 8.12 — Delete account

> [!important] Goal
> **CORE-26 (last part).** Delete account really deletes, after the current password.

**What was done** (11 Nov 2026, commit `22308ad`)
- The popup gained a **"Your password"** field (Ranshitha's decision). Wrong → the popup opens again with the message (`data-open-on-load`); Cancel and × got `formnovalidate` so they still close it while the field is empty.
- Right → in one transaction `UPDATE user_ratings SET rater_id = NULL WHERE rater_id = ?`, then `DELETE FROM users WHERE id = ?`; `log_out()`; redirect to the Landing page. The stage F "nothing was deleted" toast is gone.

**Problems and fixes**
- **The first real delete did nothing and returned 200.** With PHP's errors shown on screen (development), an uncaught exception produces a 200 page, so the test only saw "no redirect". Isolating step by step (without Remember me: worked; with Remember me alone: worked) ruled out my first two guesses; my first isolation test was itself flawed (its first round deleted the account the second round needed). Running the `DELETE` directly in MySQL showed the cause: **error 1452** on `fk_ratings_rater`.
- **Cause:** a review the member wrote on their *own* listing is reached by two foreign-key paths in one DELETE. Every listing owner who has rated someone has one, so **Nimali could not delete her account**. Fix: clear `rater_id` first, in the same transaction. Checked by deleting each seed member inside `START TRANSACTION … ROLLBACK`: all OK, seed intact. Explained in [[03.01 MySQL Schema - Types, Keys and Foreign Key Rules]] (warning callout) and recorded in `database/data-dictionary.md`.
- To remember for delivery (Phase 7/8): in production PHP must not display errors, so a crash returns 500, not a 200 page.

**How it was checked** (a test member with a collection, plant, story entry, reminder, like on Monty, closed listing with a category, an interest, and ratings both ways): no token → 403; wrong password → popup again, nothing deleted; right password → 302 to the Landing page, both cookies deleted; afterwards 0 rows in every table for them, the review **by** them kept with `rater_id` and `listing_id` NULL, the review **about** them gone; their login refused.

> [!tip] What to remember
> Sensitive changes (email, password, deleting the account) ask for the current password, and those attempts count towards the lockout. Test deletes against a parent that has *every* kind of child; two cascade paths meeting on one row is a real InnoDB failure.

**Check yourself**
1. Why does Settings use `$me['id']` in every UPDATE instead of an id from the form?
2. Why does a password change delete all Remember me tokens?
3. Why did deleting Nimali fail, and what does the fix do?

> [!success]- Answers
> 1. Anything in a form can be edited by the sender. The session says who is logged in; using its id means a member can only ever change their own row.
> 2. If someone else had the old password and logged in with Remember me (or a phone was lost), their token would keep them logged in for 30 days. Deleting every token ends those logins.
> 3. Her review on her own listing was reached twice in one DELETE: the listing's CASCADE set its `listing_id` to NULL while its `rater_id` still pointed at the user being deleted, so InnoDB refused it (1452). Setting `rater_id` to NULL first, in the same transaction, leaves only one path.

**Phase 2 (accounts) complete** on `feature/accounts`, not pushed: sign up, live username check, log in with lockout and Remember me, `require_login()`, and all Settings cards except the profile photo (CORE-17). CORE-10's ownership checks and CORE-11's tokens on the other forms come with each page in Phase 3.
