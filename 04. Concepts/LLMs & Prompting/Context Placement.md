---
type: concept
status: draft
tags: [prompting]
---
# Context Placement
> [!warning] Copied from the course notes. Rewrite it in your own words, then set `status: done`.

> [!note]
> **Where you place information in your prompt affects how well the model uses it** — not just how much context you give it.

As context windows and applications grow, *where* you put information matters as much as *what* you put in. The paper *Lost in the Middle: How Language Models Use Long Contexts* tested models with multi-document questions and key-value retrieval tasks, moving the relevant document's position around (beginning, middle, end).

**Findings:**
- Performance is highest when key information is at the **beginning** or **end** of the context — beginning tends to outperform end.
- Information placed in the **middle** of a long context performs worse — sometimes even worse than giving the model *no supporting documents at all*.
- Bigger context windows don't guarantee the model actually uses everything you put in them; information in the middle can effectively get "lost."

**Practical takeaways:**
- Start new chats when possible — don't let context grow indefinitely
- Keep context as small as it needs to be
- Put your most critical information **first**
- If new important information comes up later, add it to the **end**
- Treat the middle of a long prompt/conversation as the place for lower-priority details

## Related
- [[Few-Shot Prompting]] · [[LLMs]] · [[Core Prompting Techniques]]
