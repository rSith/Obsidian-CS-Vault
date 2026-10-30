---
type: stage
stage: "A6"
tags: [self-study, dlai-short-course]
---
# A6 · Open models, fine-tuning & serving
> [!info] [[00. DLAI Short Courses - Home]] · 5 courses · 8h37m of video (7h11m essential)

> [!abstract] Objective
> Work below the API: run, adapt, compress and serve open models.

**Enter with:** PyTorch basics and [[How Transformer LLMs Work]] from A1. If you know TensorFlow/Keras but not PyTorch, learn PyTorch tensors, modules and a training loop first. Can run in parallel with A3–A5.

**After this stage I can:** Fine-tune a small open model, quantize it, and explain and measure serving trade-offs.

> [!example] Consolidation project
> Fine-tune a small open model on one narrow task, quantize it, serve it locally, and compare quality, memory and latency before and after.
> - [ ] Project done → 

## Courses (in order)
- [ ] [[Open Source Models with Hugging Face]] — 2h13m · Essential
- [ ] [[Finetuning Large Language Models]] — 1h35m · Essential
- [ ] [[Quantization Fundamentals with Hugging Face]] — 1h14m · Essential
- [ ] [[Efficiently Serving LLMs]] — 2h9m · Essential
- [ ] [[Post-training of LLMs]] — 1h26m · Recommended

## Progress
```base
filters:
  and:
    - type == "course"
    - stage == "A6"
views:
  - type: table
    name: A6 courses
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
