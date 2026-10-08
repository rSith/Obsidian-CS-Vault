---
type: project-note
project: PlantFolio
status: stub
tags: [project, web]
---
# PlantFolio Week 09 — Core Features and Gate 1
> [!info] [[PlantFolio - Project Home]] · Stage B · 30 Nov – 6 Dec 2026 · ends with **Gate 1**
> **Study notes:** [[04.00 Core Features]] · **Revision:** [[04.99 Summary - Core Features]]

> [!abstract] Week goal
> The member's own features work from the database: collections, plants with photos, the health story and care reminders.

> [!warning] Before CORE-17: switch on the GD extension
> On 8 Oct 2026 this computer's `C:\xampp\php\php.ini` had `;extension=gd`, so PHP's image library was not loaded. Resizing uploads needs it. Remove the semicolon, restart Apache, and add the step to the README for the team. See [[04.02 Secure File Uploads in PHP]].

## Tasks
Replace one mock array at a time with a query function that returns the same shape, then delete it from `mock-data.php`.

- [ ] **CORE-13** Collections: create, rename, delete, privacy → [[04.01 CRUD in Plain PHP]]
- [ ] **CORE-14 / CORE-15** My Garden from the database, with search, filter and sort
- [ ] **CORE-16** Plant create and edit
- [ ] **CORE-17** Secure upload handler `includes/upload.php` → [[04.02 Secure File Uploads in PHP]]
- [ ] **CORE-18** Plant profile data
- [ ] **CORE-19** Health logs and the plant story
- [ ] **CORE-20** Delete a plant together with its files
- [ ] **CORE-21** Public view rules → [[03.07 Access Control and Ownership Checks]]
- [ ] **CORE-22** Reminders → [[04.03 Dates, Reminders and Due Logic]]
- [ ] **CORE-23** Mark done and Undo
- [ ] **CORE-24 / CORE-25** Dashboard data and empty states
- [ ] **CORE-27** Live counts on the landing page → [[04.05 Counts, Averages and Rating Rules in SQL]]
- [ ] **CORE-28** Phase 1 test pass
- [ ] **CORE-29** Peer reviews of MOD-01 – MOD-06

## Gate 1 demo
- [ ] Register → add a plant with a photo → log a health update → the reminder appears on the Dashboard → mark it done
- [ ] Tag `v0.8-gate1`

## Security checks for this week
- [ ] Ownership is checked before every edit and delete (try another user's ID in the address)
- [ ] A private plant returns Not found to other people
- [ ] Uploads: type checked with `finfo`, 5 MB limit, random file names, resized to 1600 px

---
## Work log
Step sections are added here as each step is finished.
