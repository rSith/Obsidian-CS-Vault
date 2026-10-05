# Core Prompting Techniques



---

## 1. [Standard Prompts](https://sgoldfarb2.github.io/practical-prompt-engineering/lessons/core-prompting-techniques/standard-prompt)

> ✏️ **A standard prompt is just a direct question or instruction** — no special formatting or technique involved.

This is the prompting technique you're already using every time you talk to an LLM, whether you realize it or not. It's just the official academic term for asking a straightforward question or giving a direct command, like:

- "Write me a JavaScript function to sort an array and remove duplicates"
- "Explain to me why thunder is so scary"
- "When did the video game Apex Legends release?"

Nothing fancy — but it's the foundation every other prompting technique builds on.

**Key idea:** this works exactly like asking a friend a question. Vague questions get vague answers; precise questions get precise answers. Compare telling a coworker *"this doesn't work"* vs. *"on line 56, when I pass an empty array into this function, I get this error..."* — the model, like a person, works with the context you give it.

#### Example

⚠️ Watch out: 

---

## 2. [Zero Shot](https://sgoldfarb2.github.io/practical-prompt-engineering/lessons/core-prompting-techniques/zero-shot)

> ✏️ **Zero-shot prompting means asking the model to complete a task with no examples provided** — you rely entirely on its training data.

All zero-shot prompts are standard prompts, but not all standard prompts are truly zero-shot. Don't overthink the distinction — just know that zero-shot prompts skip examples entirely, and you've probably written plenty without realizing it.

**When to use it:** for simple, common tasks the model has seen extensively in training — writing code, proofreading, classifying feedback, etc. Anything "generic enough" that examples aren't necessary.

#### Example

```
Classify the customer rating into neutral, negative or positive.
Text: The product was okay. It worked, but wasn't the easiest to understand how to use.
Sentiment:
```

This is efficient — you don't need to spend tokens on examples for a task the model is already "smart" enough to generalize.

---

## 3. [One Shot](https://sgoldfarb2.github.io/practical-prompt-engineering/lessons/core-prompting-techniques/one-shot)

> ✏️ **One-shot prompting means providing exactly one example** to demonstrate the pattern you want.

Think of it like pair programming or a code review: *"here's a prior example — now build this new thing based on that pattern."* It leverages the model's ability to generalize from minimal data, and is more reliable than zero-shot when your intent isn't fully obvious from instructions alone.

**Good for:** simple, well-defined patterns, straightforward transformations, or when you don't have time to prepare many examples but still want more consistency than zero-shot.

#### Notes on choosing your example
- Choose it carefully — it sets the pattern
- Make it representative of the majority case, not an edge case
- Include every element you want reflected in the output
- Pair it with explicit instructions for anything the example doesn't cover
- Don't overload the example trying to cover every scenario — let the model generalize

#### Example

```
Write an engaging introduction for a blog post about remote work productivity.

Example:
Topic: Benefits of morning exercise
Introduction: "Picture this: It's 6 AM, your alarm goes off, and instead of hitting
snooze, you lace up your sneakers. Sound impossible? Here's the thing—those who
exercise before breakfast report 23% higher energy levels throughout their workday.
But the real secret isn't just the exercise itself; it's what happens to your brain
chemistry in those precious morning hours."

Now write an introduction for: Remote work productivity tips
```

---

## 4. [Few Shot](https://sgoldfarb2.github.io/practical-prompt-engineering/lessons/core-prompting-techniques/few-shot)

> ✏️ **Few-shot prompting means providing two or more examples** to establish a clearer, more reliable pattern.

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

---

## 5. [Context Placement](https://sgoldfarb2.github.io/practical-prompt-engineering/lessons/core-prompting-techniques/context-placement)

> ✏️ **Where you place information in your prompt affects how well the model uses it** — not just how much context you give it.

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

---

### Quick Comparison

| Technique | # of Examples | Best For |
|---|---|---|
| Standard Prompt | 0 (just instructions) | General, everyday requests |
| Zero Shot | 0 | Simple, common tasks the model already "knows" |
| One Shot | 1 | Establishing a clear pattern quickly |
| Few Shot | 2+ | Complex, varied, or domain-specific tasks |
| Context Placement | N/A | Structuring long prompts/conversations for max recall |
