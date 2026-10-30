---
type: stage
stage: "A5"
tags: [self-study, dlai-short-course]
---
# A5 · Safety, red teaming & testing
> [!info] [[00. DLAI Short Courses - Home]] · 3 courses · 4h33m of video

> [!abstract] Objective
> Make LLM apps safe to ship and keep them safe as they change.

**Enter with:** A working RAG app from A3 and evaluation habits from [[Building Systems with the ChatGPT API]] and [[Evaluating AI Agents]].

**After this stage I can:** Add guards, attack your own app, and block regressions with automated evals.

> [!example] Consolidation project
> Harden the A3/A4 app with three guards, a written red-team report, and an eval suite that runs on every push in CI.
> - [ ] Project done → 

## Courses (in order)
- [ ] [[Safe and reliable AI via guardrails]] — 1h42m · Essential
- [ ] [[Red Teaming LLM Applications]] — 1h39m · Essential
- [ ] [[Automated Testing for LLMOps]] — 1h12m · Essential

## Progress
```base
filters:
  and:
    - type == "course"
    - stage == "A5"
views:
  - type: table
    name: A5 courses
    order:
      - file.name
      - status
      - roadmap_priority
      - duration_min
      - level
    sort:
      - property: order
        direction: ASC
```
