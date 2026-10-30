---
type: stage
stage: "A4"
tags: [self-study, dlai-short-course]
---
# A4 · Agents, tools & agent evaluation
> [!info] [[00. DLAI Short Courses - Home]] · 5 courses · 10h10m of video (6h16m essential)

> [!abstract] Objective
> Build controllable agents that reach tools through MCP, and evaluate their behaviour.

**Enter with:** A2 function calling; A3 retrieval to use as a tool.

**After this stage I can:** Build a stateful, tool-using agent, expose your own tools through an MCP server, and trace and score its runs.

> [!example] Consolidation project
> A research agent that calls your A3 RAG system through your own MCP server, with traces and a 15-case evaluation suite covering tool choice and final answers.
> - [ ] Project done → 

## Courses (in order)
- [ ] [[AI Agents in LangGraph]] — 1h42m · Essential
- [ ] [[MCP - Build Rich-Context AI Apps with Anthropic]] — 1h58m · Essential
- [ ] [[Evaluating AI Agents]] — 2h36m · Essential
- [ ] [[AI Agentic Design Patterns with AutoGen]] — 1h35m · Recommended
- [ ] [[Agent Skills with Anthropic]] — 2h19m · Recommended

## Progress
```base
filters:
  and:
    - type == "course"
    - stage == "A4"
views:
  - type: table
    name: A4 courses
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
