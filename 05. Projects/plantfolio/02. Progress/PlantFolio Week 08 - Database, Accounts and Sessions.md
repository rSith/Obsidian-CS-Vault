---
type: project-note
project: PlantFolio
status: stub
tags: [project, web]
---
# PlantFolio Week 08 — Database, Accounts and Sessions
> [!info] [[PlantFolio - Project Home]] · Stage B · 23–29 Nov 2026
> **Study notes:** [[03.00 Database and Accounts]] · **Revision:** [[03.99 Summary - Database and Accounts]]

> [!abstract] Week goal
> The database exists, and a visitor can register, log in and log out securely. From here on, the security rules in `CLAUDE.md` are checked on every change.

## Tasks
- [ ] **CORE-01** `database/schema.sql`: InnoDB, utf8mb4, foreign keys, re-runnable → [[03.01 MySQL Schema - Types, Keys and Foreign Key Rules]]
- [ ] **CORE-02** `database/seed.sql`, built from the mock data
- [ ] **CORE-03** `config.php` and `includes/db.php`: one PDO connection, exceptions on, emulated prepares off → [[03.02 PDO and Prepared Statements]]
- [ ] **CORE-07** Registration → [[03.06 Form Handling - Validation, Redirects and Flash Messages]] · [[03.03 Password Hashing and Login Lockout]]
- [ ] **CORE-08** Real username check
- [ ] **CORE-09** Login, logout, sessions → [[03.04 PHP Sessions and Login State]]
- [ ] **CORE-10** `includes/auth.php`: `require_login()`, ownership checks → [[03.07 Access Control and Ownership Checks]]
- [ ] **CORE-11** CSRF tokens → [[03.05 CSRF Tokens]]
- [ ] **CORE-12** Login lockout: 15 minutes after 5 failures
- [ ] **CORE-26** Settings saves (profile, password)

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
