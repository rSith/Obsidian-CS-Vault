---
type: lecture
course: CMIS 3134
lecture: 5
status: stub
priority: Tier 1
tags: [compilers]
aliases: [Moore and Mealy Machines]
---
# Moore and Mealy Machines
> [!info] CMIS 3134 · Lecture 5
> [[CMIS 3134 Course Home]] · Exam prep: [[3134 Topic Map & Past Papers|Topic map]] · Study priority: **Tier 1**
> ← [[3134-04 NDFA to DFA Conversion & DFA Minimization]] | [[3134-06 Lexical Analysis]] →

> [!tip]- Exam focus (5 papers, 2019/20–2023/24)
> - **Every paper** — Moore ↔ Mealy conversion (both directions, every year) (20 + 20 marks)
> - Repeated stem: the **same q0–q3 Moore and Mealy tables** for conversion (2020/21, 2022/23, 2023/24).

> [!question]- Past paper questions on this lecture (15)
> - **2019/20 Q6(a)** · 20 marks — Compare and contrast the **Moore machine** and **Mealy machine**
> - **2019/20 Q6(b)** · 20 marks — Convert the given **Moore machine into a Mealy machine** (q0–q5 table with Output column)
> - **2019/20 Q6(c)** · 20 marks — Convert the given machine (q0–q3, entries of the form `q3/0`) into the other form *(table supplied is in **Mealy** form — effectively Mealy → Moore)*
> - **2020/21 Q8(a)** · 20 marks — Compare and Contrast **Moore machine** and **Mealy machine**
> - **2020/21 Q8(b)** · 20 marks — Convert the given **Moore machine into the Mealy machine** (q0–q3 + Output column)
> - **2020/21 Q8(c)** · 20 marks — Convert the given **Mealy machine into the Moore machine** (entries `q3/0`, `q1/1`…)
> - **2021/22 Q7(a)** · 20 marks — Design a **Moore machine** for a binary input sequence: substring `101` → output "a"; substring `110` → output "b"; otherwise "c"
> - **2021/22 Q7(b)** · 20 marks — Convert the given **Moore machine into the Mealy machine**
> - **2021/22 Q7(c)** · 20 marks — Convert the given **Mealy machine into the Moore machine**
> - **2022/23 Q7(a)** · 20 marks — Design a **Mealy machine** for a binary input sequence: output "M" immediately after detecting `"01"`; "N" after `"10"`; "O" otherwise
> - **2022/23 Q7(b)** · 20 marks — Convert the given **Moore machine into the Mealy machine** (q0–q3 + Output column)
> - **2022/23 Q7(c)** · 20 marks — Convert the given **Mealy machine into the Moore machine** (entries `q3/0`, `q1/1`…)
> - **2023/24 Q6(a)** · 20 marks — Design a **Mealy machine** for a vending machine accepting coins of 5 and 10; once the total reaches or exceeds 20, dispense the item producing "D", otherwise produce "C"
> - **2023/24 Q6(b)** · 20 marks — Convert the **Mealy machine** in part (a) into a **Moore machine**
> - **2023/24 Q6(c)** · 20 marks — Convert the given **Moore machine into the Mealy machine** (Q0–Q3 with Output column)

## Topic checklist
- [ ] **5.1 Finite automata with output** — Two FSM types that generate output: Mealy and Moore
- [ ] **5.2 Mealy Machine** — Output depends on **present state AND present input**; 6-tuple `(Q, ∑, O, δ, X, q0)`; `δ: Q × ∑ → Q`; output function `X: Q × ∑ → O`; state table (Next state + Output per input); state diagram
- [ ] **5.3 Moore Machine** — Output depends on **present state only**; 6-tuple `(Q, ∑, O, δ, X, q0)`; output function `X: Q → O`; state table (Next state per input + single Output column); state diagram
- [ ] **5.4 Mealy vs Moore comparison** — Output dependence; Mealy generally fewer states / Moore more; output function of transitions vs of current state at clock edges; Mealy reacts in same clock cycle / Moore one cycle later (more decode logic, more circuit delay)
- [ ] **5.5 Moore → Mealy conversion (Algorithm 4)** — Step 1 blank Mealy table format · Step 2 copy all Moore transition states · Step 3 for state Qi with output m, copy m into the output column wherever Qi appears as a *next state*; worked example
- [ ] **5.6 Mealy → Moore conversion (Algorithm 5)** — Step 1 count distinct outputs per state Qi · Step 2 if all outputs same, keep Qi; if n distinct outputs, split into Qi0…Qin · Step 3 if the initial state's output is 1, insert a new initial state giving output 0; worked example (b→b0,b1; c→c0,c1)

## Notes
*Work through the checklist above. Write each topic in your own words, then tick it off.*


## Key definitions
> [!note] Term
>

## Summary (in my own words)


## Open questions
- [ ]
