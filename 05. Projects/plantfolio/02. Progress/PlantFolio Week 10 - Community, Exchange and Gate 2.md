---
type: project-note
project: PlantFolio
status: stub
tags: [project, web]
---
# PlantFolio Week 10 — Community, Exchange and Gate 2
> [!info] [[PlantFolio - Project Home]] · Stage B · 7–13 Dec 2026 · ends with **Gate 2: coursework complete**
> **Study notes:** [[04.00 Core Features]] · **Revision:** [[04.99 Summary - Core Features]]

> [!abstract] Week goal
> Search, the exchange marketplace and trader ratings work end to end. After this week the project can be submitted.

> [!warning] The heaviest week
> The roadmap says so itself. If it overflows, Gate 2 moves into week 11 and the ML stage gives up the time. Core features come before ML, always.

## Tasks
- [ ] **COM-01 / COM-02** Explore and people search → [[04.04 Search, Filters and Pagination]]
- [ ] **COM-03 / COM-04** Filters and pagination, 12 per page
- [ ] **COM-05 / COM-13** Public profile and rating breakdown → [[04.05 Counts, Averages and Rating Rules in SQL]]
- [ ] **COM-06** Create and edit a listing
- [ ] **COM-07** Marketplace
- [ ] **COM-08** Listing detail
- [ ] **COM-09** Interest and contact reveal → [[03.07 Access Control and Ownership Checks]]
- [ ] **COM-10** My Exchanges
- [ ] **COM-11** Mark exchanged, or close
- [ ] **COM-12** Trader ratings: one per person per exchange
- [ ] **COM-14** Dashboard exchange card
- [ ] **COM-15** Likes *(Could)*
- [ ] **COM-16** Responsive re-check
- [ ] **COM-17** Phase 2 test pass
- [ ] **COM-18** Peer reviews of MOD-07 – MOD-10
- [ ] **COM-19** Merge to `main`, tag **`v1.0-core`** with a database dump

## Gate 2
- [ ] All nine core features work end to end
- [ ] Phase 2 tests pass

## Security checks for this week
- [ ] Contact details appear only after "I'm interested", and only the fields the owner chose
- [ ] Search input cannot change the SQL (try `' OR '1'='1`)
- [ ] A member cannot rate the same exchange twice, or rate themselves

---
## Work log
Step sections are added here as each step is finished.
