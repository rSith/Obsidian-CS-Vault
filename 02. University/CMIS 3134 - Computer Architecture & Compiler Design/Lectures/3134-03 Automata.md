---
type: lecture
course: CMIS 3134
lecture: 3
status: stub
priority: Tier 1
tags: [compilers]
aliases: [Automata, Finite Automata]
---
# Automata
> [!info] CMIS 3134 · Lecture 3
> [[CMIS 3134 Course Home]] · Exam prep: [[3134 Topic Map & Past Papers|Topic map]] · Study priority: **Tier 1**
> ← [[3134-02 Regular Expressions]] | [[3134-04 NDFA to DFA Conversion & DFA Minimization]] →

> [!tip]- Exam focus (5 papers, 2019/20–2023/24)
> - **Every paper** — DFA construction (4-part question: substring contains / excludes / parity / starts-ends) (40 marks)
> - **4 of 5 papers** — string acceptability on a supplied automaton (same A→B→C→D automaton and strings in 2019/20 and 2020/21)
> - **4 of 5 papers** — compare and contrast DFA and NDFA
> - **4 of 5 papers** — formal definition of a DFA / NDFA
> - Repeated stem: *Can we design an FSM to accept all valid Java codes / codes that never loop?* (2019/20–2021/22)
> - Repeated stem: *Explain Alphabet, String, Length of a String, Language* (2020/21, 2021/22, 2023/24)
> - Low priority: acceptors / classifiers / transducers (never asked directly).

> [!question]- Past paper questions on this lecture (41)
> - **2019/20 Q3(a)** · 20 marks — What is meant by the **finite automaton**?
> - **2019/20 Q3(b)** · 20 marks — Can we design an FSM for: (i) all valid Java codes (ii) valid Java codes that never go into an infinite loop — justify
> - **2019/20 Q3(c)** · 20 marks — Compare and contrast **DFA** and **NDFA**
> - **2019/20 Q3(d)** · 40 marks — Check acceptability of `bba`, `bab`, `abbabcba`, `cbacabab`, `cabbbabaacc` on the given 4-state automaton (A,B,C,D)
> - **2019/20 Q4(a)** · 20 marks — Provide a **Formal Definition for an NDFA**
> - **2019/20 Q4(b)** · 20 marks — Design an **NDFA** for all strings containing only a's and b's where there are at least 4 a's and beginning with b
> - **2019/20 Q4(c)** · 20 marks — Construct a **DFA** accepting strings over {0,1} that do **not** contain `"01011"` anywhere
> - **2020/21 Q3(a)** · 20 marks — What is meant by the **finite automaton**?
> - **2020/21 Q3(b)** · 20 marks — FSM for (i) all valid Java codes (ii) Java codes that never go into an infinite loop
> - **2020/21 Q3(c)** · 20 marks — Compare and contrast **DFA** and **NDFA**
> - **2020/21 Q3(d)** · 40 marks — Check acceptability of `bba`, `bab`, `abbabcba`, `cbacabab`, `cabbbabaacc` on the given automaton
> - **2020/21 Q4(a)** · 20 marks — Provide a **Formal Definition for a DFA**
> - **2020/21 Q4(b)** · 20 marks — Explain with examples: (i) **Alphabet** (ii) **String** (iii) **Length of a String** (iv) **Language**
> - **2020/21 Q4(c)** · 20 marks — Design an **NDFA** for all strings over {a,b} in which the **second last** symbol is always 'a'
> - **2020/21 Q4(d)** · 40 marks — Construct a **DFA** that: (i) does not contain `"xyxyy"` (ii) has an **even** number of 1's (iii) contains `"acb"` (iv) contains `"aababb"` as substring
> - **2020/21 Q6(b)** · 20 marks — What is the **power of alphabet (∑)** and **cardinality of alphabet (∑)**? Explain with examples
> - **2020/21 Q6(c)** · 20 marks — Using power and cardinality of ∑, prove `∑*` is the set of all possible strings of all lengths over {p, q}
> - **2021/22 Q4(a)** · 20 marks — What is meant by the **finite automaton**?
> - **2021/22 Q4(b)** · 20 marks — FSM for (i) all valid Java codes (ii) Java codes that never go into an infinite loop
> - **2021/22 Q4(c)** · 20 marks — **Categorize the finite automation machines** with their examples
> - **2021/22 Q4(d)** · 40 marks — Check acceptability of `bba`, `aababbbbcba`, `cbacabbb`, `cababbaba` on the given automaton
> - **2021/22 Q5(a)** · 20 marks — Provide a **Formal Definition for a DFA**
> - **2021/22 Q5(b)** · 20 marks — Explain with examples: (i) **Alphabet** (ii) **String** (iii) **Length of a String** (iv) **Language**
> - **2021/22 Q5(c)** · 20 marks — Design an **NDFA** for all strings over {a,b} in which the **third last** symbol is always "b"
> - **2021/22 Q5(d)** · 40 marks — Construct a **DFA** that: (i) does not contain `"xxyy"` (ii) has an **odd** number of "1"s (iii) contains `"acb"` (iv) contains `"abababbb"` as a substring
> - **2022/23 Q4(a)** · 20 marks — What does **"Automata"** mean, and why is it significant in computing?
> - **2022/23 Q4(b)** · 20 marks — What is the **Kleene Star (∑*)**, and how does it differ from the **Kleene Closure (∑+)**? Provide an example
> - **2022/23 Q4(c)** · 20 marks — In the 5-tuple `(Q, ∑, q0, F, δ)` of a finite machine, what role does the **transition function (δ)** play?
> - **2022/23 Q4(d)** · 40 marks — Check acceptability of `abaaab`, `babbbba`, `bbbaaabab`, `abababbba` on the given automaton (A, B, C)
> - **2022/23 Q5(a)** · 20 marks — Show that `∑*` is the set of all possible strings of all lengths (i.e. language) where ∑ is the alphabet
> - **2022/23 Q5(b)** · 20 marks — Why might **NFAs** be computationally easier to design for certain problems, even though **DFAs** are simpler in execution?
> - **2022/23 Q5(c)** · 20 marks — Compare and contrast a **DFA** and an **NFA**
> - **2022/23 Q5(d)** · 40 marks — Construct a **DFA** that: (i) accepts all strings over {a,b,c} that **start with** "abc" (ii) does not contain the substring "pqp" over {p,q} (iii) has an **even number of 0s and an odd number of 1s** over {0,1} (iv) ends with "111" with at least four symbols
> - **2023/24 Q4(a)** · 20 marks — What is meant by the term **Automaton**? Explain why it is referred to as a **"self-acting"** machine
> - **2023/24 Q4(b)** · 20 marks — Define with examples: (i) **Alphabet** (ii) **String** (iii) **Language** (iv) **Empty String**
> - **2023/24 Q4(c)** · 20 marks — Prove that `∑+ = ∑* − {ε}`
> - **2023/24 Q4(d)** · 40 marks — An ATM accepts a PIN that: contains only digits {0,1}; must start with 1; must end with 0; must contain an **even number of 1s**; length ≥ 3. (i) Construct a **DFA** accepting only valid PINs (ii) test `10`, `110110`, `110`, `11011` showing all steps
> - **2023/24 Q5(a)** · 20 marks — **Formally prove** that every **DFA** is also an **NDFA**
> - **2023/24 Q5(b)** · 20 marks — Discuss why **NDFAs** might be computationally easier to design for certain problems, even though **DFAs** are simpler in execution
> - **2023/24 Q5(c)** · 20 marks — Explain why in a **DFA** the transition function is `δ: Q × ∑ → Q` while in an **NDFA** it is `δ: Q × ∑ → 2^Q`
> - **2023/24 Q5(d)** · 40 marks — Online registration usernames over {a, b}: must contain **exactly two 'a's**, any number of 'b's, order does not matter. (i) Construct a **DFA** (ii) modify it to accept usernames with **at least two 'a's** and explain the changes

## Topic checklist
- [ ] **3.1 What is an Automaton** — From Greek *αὐτόματα* = "self-acting"; abstract self-propelled computing device following a predetermined sequence of operations
- [ ] **3.2 Finite Automaton (FA) / FSM** — An automaton with a finite number of states
- [ ] **3.3 Formal definition of a FA** — 5-tuple `(Q, ∑, δ, q0, F)` — Q states, ∑ alphabet, δ transition function, q0 initial state, F ⊆ Q final states
- [ ] **3.4 Related terminologies**
    - [ ] 3.4.1 Alphabet — Any finite set of symbols; e.g. ∑ = {a, b, c, d}
    - [ ] 3.4.2 String — Finite sequence of symbols from ∑; e.g. 'cabcad'
    - [ ] 3.4.3 Length of a String — `│S│` = number of symbols; `│S│ = 0` → empty string (λ or ε)
    - [ ] 3.4.4 Kleene Star `∑*` — Unary operator; infinite set of all strings of all lengths **including λ**; `∑* = ∑⁰ ∪ ∑¹ ∪ ∑² ∪ …`
    - [ ] 3.4.5 Kleene Closure / Plus `∑+` — All strings of all lengths **excluding λ**; `∑+ = ∑* − {λ}`
    - [ ] 3.4.6 Language — A subset of ∑* for some alphabet ∑; finite or infinite
- [ ] **3.5 Types of Finite Automata** — DFA and NDFA/NFA
- [ ] **3.6 Deterministic Finite Automaton (DFA)** — For each input symbol the next state is determinable; formal 5-tuple with `δ: Q × ∑ → Q`
- [ ] **3.7 Graphical representation of a DFA** — Digraph/state diagram; vertices = states; labelled arcs = transitions; initial state = empty single incoming arc; final state = double circles; worked example + transition table
- [ ] **3.8 Non-deterministic Finite Automaton (NDFA)** — Machine can move to any *combination* of states; formal 5-tuple with `δ: Q × ∑ → 2^Q` (power set); worked example + transition table
- [ ] **3.9 DFA vs NDFA comparison** — Single vs multiple next states; ε-transitions not in DFA / permitted in NDFA; backtracking allowed in DFA / not always in NDFA; DFA more space / NDFA less; acceptance: DFA transits to a final state vs NDFA at least one path ends in a final state
- [ ] **3.10 Acceptors, Classifiers, Transducers** — **Acceptor/Recognizer** — computes a Boolean function, all states accept or reject; **Classifier** — more than two final states, single output on termination; **Transducer** — produces output from current input and/or previous state → **Mealy** (state + input) and **Moore** (state only)
- [ ] **3.11 Acceptability by DFA and NDFA** — Accepted iff `δ*(q0, S) ∈ F`; language `L = {S │ S ∈ ∑* and δ*(q0,S) ∈ F}`; not accepted iff `δ*(q0,S′) ∉ F`; complement language L′; worked example of accepted/rejected strings

## Notes
*Work through the checklist above. Write each topic in your own words, then tick it off.*


## Key definitions
> [!note] Term
>

## Summary (in my own words)


## Open questions
- [ ]
