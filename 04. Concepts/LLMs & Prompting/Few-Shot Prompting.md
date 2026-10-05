---
type: concept
status: draft
tags: [prompting]
---
# Few-Shot Prompting
> [!warning] Copied from the course notes. Rewrite it in your own words, then set `status: done`.

> [!note]
> **Few-shot prompting means providing two or more examples** to establish a clearer, more reliable pattern.

Following the same logic — zero examples, one example, now several — research such as *Language Models are Few-Shot Learners* found that few-shot examples dramatically improve LLM performance, and that few-shot accuracy tends to scale even more rapidly with model size than zero-shot or one-shot.

**When to use it:**
- Complex patterns with multiple variations
- Classification tasks with multiple categories
- Standardizing formats across diverse inputs
- Domain-specific tasks that need context
- When consistency across varied outputs really matters

#### Notes
- Diversity in examples matters
- Include edge cases and failure cases
- Keep examples concise but complete
- Test with a few different chats and different numbers of examples to find what works best

#### Example

```
Analyze the business decision with the requested level of detail.

Decision: Opening a new store location
Analysis Level: Quick take
Response: New location appears viable given foot traffic data and competitor
absence, though initial investment is substantial.

Decision: Switching software providers
Analysis Level: Standard review
Response: Migration offers long-term cost savings of 30% and better feature set.
However, switching requires 2-month transition period with potential disruption to
operations. Staff training needed but vendor provides support. Recommend phased
rollout starting with non-critical systems.

Decision: Launching a new product line
Analysis Level: Comprehensive assessment
Response: [full multi-paragraph example demonstrating comprehensive depth...]

Decision: Implementing remote work policy
Analysis Level: Standard review
Response:
```

## Related
- [[One-Shot Prompting]] · [[Context Placement]] · [[Core Prompting Techniques]]
