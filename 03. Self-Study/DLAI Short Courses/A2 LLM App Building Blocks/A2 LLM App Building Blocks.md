---
type: stage
stage: "A2"
tags: [self-study, dlai-short-course]
---
# A2 · LLM app building blocks
> [!info] [[00. DLAI Short Courses - Home]] · 5 courses · 7h22m of video (5h02m essential)

> [!abstract] Objective
> Connect LLMs to code, tools and data reliably.

**Enter with:** A1, and comfort with Python classes (Pydantic models are classes).

**After this stage I can:** Turn free text into validated records, let a model call your functions, and scaffold an app in LangChain with a Gradio UI.

> [!example] Consolidation project
> A "text to records" tool that parses emails or transcripts into validated Pydantic objects, stores them in SQLite through a function call, and shows results in Gradio.
> - [ ] Project done → 

## Courses (in order)
- [ ] [[Function-calling and data extraction with LLMs]] — 1h19m · Essential
- [ ] [[Pydantic for LLM Workflows]] — 1h50m · Essential
- [ ] [[LangChain for LLM Application Development]] — 1h53m · Essential
- [ ] [[Getting Structured LLM Output]] — 1h21m · Recommended
- [ ] [[Building Generative AI Applications with Gradio]] — 59m · Recommended

## Progress
```base
filters:
  and:
    - type == "course"
    - stage == "A2"
views:
  - type: table
    name: A2 courses
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
