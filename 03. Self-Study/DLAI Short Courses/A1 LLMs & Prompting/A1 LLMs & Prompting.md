---
type: stage
stage: "A1"
tags: [self-study, dlai-short-course]
---
# A1 · How LLMs work & prompting
> [!info] [[00. DLAI Short Courses - Home]] · 4 courses · 6h35m of video (5h19m essential)

> [!abstract] Objective
> Use an LLM API with confidence and understand what happens inside the model.

**Enter with:** Python basics (functions, data structures, pip, notebooks). No ML needed for [[ChatGPT Prompt Engineering for Developers]] and [[Building Systems with the ChatGPT API]]; [[Attention in Transformers - Concepts and Code in PyTorch]] assumes matrix multiplication.

**After this stage I can:** Build a multi-step LLM pipeline with moderation and a small evaluation set, and explain tokenization, attention and the KV cache.

> [!example] Consolidation project
> A study-helper or support assistant that classifies a request, answers it through chained prompts, checks its own output, and is scored on 20 test questions.
> - [ ] Project done → 

## Courses (in order)
- [ ] [[ChatGPT Prompt Engineering for Developers]] — 1h40m · Essential
- [ ] [[Building Systems with the ChatGPT API]] — 1h55m · Essential
- [ ] [[How Transformer LLMs Work]] — 1h44m · Essential
- [ ] [[Attention in Transformers - Concepts and Code in PyTorch]] — 1h16m · Recommended

## Progress
```base
filters:
  and:
    - type == "course"
    - stage == "A1"
views:
  - type: table
    name: A1 courses
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
