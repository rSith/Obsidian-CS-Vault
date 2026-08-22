## What is an LLM?
>[!note] An LLM is basically a large mathematical model containing billions of learned numbers called parameters or weights.

During training, the model looks at huge amounts of text and learns patterns such as:
- Which words commonly appear together
- How sentences are structured
- How questions and answers are related
- Patterns in programming languages
- Relationships between different concepts

The model does not store knowledge like a normal database. Instead, much of what it has learned is represented through its parameters (weights).

## LLMs Generate Text One Token at a Time

An LLM does not normally write an entire paragraph at once. Instead, it works roughly like this:
>[!important] Input → Predict next token → Add token → Predict next token → Repeat
>

A **token** is a small piece of text. It can be a complete word, part of a word, punctuation, etc.

>[!quote] Example 
>**"The sky is".....** 
>The model might predict: *"blue"*
>Then it considers: **"The sky is blue"**
>and predicts what should come next. This process continues until the response is finished.

So, an LLM is essentially continuously predicting what should come next based on the available context.

## Determinism vs. Randomness

The mathematical calculations inside an **LLM are essentially deterministic**: *given the same model, context, and settings, the probability calculations are reproducible.*

However, the model can choose between multiple possible tokens.
For example:
>[!quote] Example 
"The cat is ......"
> Possible predictions might be:
>- Sleeping — 45%
>- Eating — 25%
>- Running — 15%
>- Playing — 10%
>- Other — 5%
>
>The system can sample from these possibilities instead of always selecting exactly the highest-probability token.

This is why you can sometimes get different answers to the same question.

## The Transformer and Self-Attention

A major breakthrough happened in 2017 with the **[[Transformer architecture]]**.

Before Transformers, architectures such as **[[RNN]]**(Recurrent Neural Network) and **[[LSTM]]** had more difficulty handling relationships between words that were far apart in a long sentence.

Transformers introduced **Self-Attention**.
> [!IMPORTANT] Self-attention allows the model to determine which other tokens are important when understanding a particular token.

For example:

>[!QUOTE] Example
>*"John gave the book to David because he needed it."*
>The model needs to determine who "he" refers to. Attention mechanisms help the model consider relationships between the words.

This technology became one of the key foundations of modern LLMs.

## Controlling the Model's Output

When an LLM generates text, several **inference parameters can influence how it chooses** **tokens**.

### Temperature
> [!note] Temperature controls ==how much randomness== is used when selecting tokens.

- **Low temperature** → More predictable and focused answers
- **High temperature** → More varied and creative answers

For example, *when generating code, a lower temperature is often useful because you usually want predictable output.*

### Top-P
>[!note] Top-P, also called Nucleus Sampling, ==limits the choices== considered by the model.
>Instead of considering every possible token, the model considers the smallest group of tokens whose combined probability reaches a certain threshold.

In simple terms:

Top-P **controls how many likely possibilities the model considers** when choosing the next token.

## Context Windows and Memory
An LLM itself does not automatically remember everything from previous conversations.

> [!note] A context window is the ==**amount of information** the **model can process at one time**.==

>[!quote] Example
During a conversation, the system may provide the model with something like:
**Previous messages + Current message → LLM → Response**
> When you send another message, relevant conversation history can be included again as part of the context.

Therefore, the model's apparent "memory" in a chat is often created by ==providing previous information back to the model==, rather than the model permanently remembering every conversation.

If the **conversation becomes larger** than the model's context window, **older information may need to be removed, summarized, or otherwise managed.**

## Why Is This Important for Prompt Engineering?

Understanding these concepts helps explain why prompting works.

For example, 

>[!quote] Example
[[Few-Shot Prompting]] gives the model examples of the type of answer you want.
>
Instead of saying:
"Classify this sentence."
>
>You can provide examples:
>
"I love this movie." → Positive
"This movie is terrible." → Negative
"The movie was okay." → ?
>
The examples give the model additional context and help guide its next-token predictions.

Similarly, techniques such as [[Chain-of-Thought prompting]] attempt to guide the model through a reasoning process by providing or requesting intermediate reasoning steps, although the exact behavior and best practices depend on the model.

---
>[!bug] Main Idea to Remember
An LLM **reads the available context**, **calculates probabilities** for **possible next tokens**, **chooses a token**, **adds it to the context**, and **repeats** this process until it produces the response.


Understanding these concepts gives you a strong foundation for learning prompt engineering:

- Tokens
- Context
- Attention
- Probability
- Parameters
- Temperature
- Top-P
- Context windows
- Prompting techniques