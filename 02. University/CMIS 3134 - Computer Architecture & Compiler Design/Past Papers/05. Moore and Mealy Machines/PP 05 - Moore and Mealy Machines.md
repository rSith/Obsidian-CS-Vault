---
type: past-paper
course: CMIS 3134
lesson: L5
status: complete
tags: [cmis3134, past-paper, cmis3134/L5]
---
# PP 05 · Moore and Mealy Machines: Past-Paper Questions

> [!info] Lesson: [[05.00 Moore and Mealy Machines]] · Summary: [[05.99 Summary - Moore and Mealy Machines]] · All conversions checked by computer (the converted machines produce identical outputs on 2000 random inputs).

| Paper | Q | Topic | Marks | Repeated? |
|---|---|---|---|---|
| 2019/20 | 6(a) | Compare Moore and Mealy | 20 | = 2020/21 Q8(a) |
| 2019/20 | 6(b) | Moore → Mealy (q0–q5) | 20 | |
| 2019/20 | 6(c) | "Moore → Malay", but the table is Mealy (q0–q3) | 20 | = 2020/21 Q8(c), 2022/23 Q7(c) |
| 2020/21 | 8(b) | Moore → Mealy (q0–q3) | 20 | = 2022/23 Q7(b) |
| 2021/22 | 7(a) | Design Moore: 101 → a, 110 → b, else c | 20 | |
| 2021/22 | 7(b) | Moore → Mealy (diagram Q1–Q5) | 20 | |
| 2021/22 | 7(c) | Mealy → Moore (diagram q1–q4) | 20 | |
| 2022/23 | 7(a) | Design Mealy: M after 01, N after 10, else O | 20 | |
| 2023/24 | 6(a) | Design Mealy vending machine | 20 | |
| 2023/24 | 6(b) | Convert (a) to Moore | 20 | |
| 2023/24 | 6(c) | Moore → Mealy (Q0–Q3) | 20 | |

---

## 2019/20 · Q6(a) and 2020/21 · Q8(a) (20 marks)
> **Question:** Compare and contrast the **Moore machine** and **Mealy machine**.

**Lesson:** [[05.02 Mealy vs Moore]]

### Expected answer
**Similarities:** both are finite state machines **with output** (transducers); both are 6-tuples (Q, Σ, O, δ, X, q0) with δ: Q × Σ → Q; neither has final states; each can be converted into the other.

| Mealy | Moore |
|---|---|
| Output depends on **present state and present input** | Output depends on **present state only** |
| Output function X: Q × Σ → O | X: Q → O |
| Output on the **transitions** (e.g. 0/1) | Output **inside the states** (e.g. q1/0) |
| Generally **fewer states** | Generally **more states** |
| Reacts faster, in the **same clock cycle** | Reacts **one clock cycle later**; more decoding logic, more circuit delay |
| Output is a function of transitions | Output is a function of the current state at clock edges |
| Output length = input length n | Output length = n + 1 |

Small example: 1's complement Mealy machine (one state, 0/1 and 1/0): input 101 → output 010 (3 outputs); a Moore version gives 4 outputs.

### How to approach this question
Lead with the defining difference, then a table, similarities and a tiny example.

---

## 2019/20 · Q6(b) (20 marks)
> **Question:** Convert the following Moore machine into a Malay [Mealy] machine.
>
> | Present State | Next (0) | Next (1) | Output |
> |---|---|---|---|
> | → q0 | q5 | q2 | 1 |
> | q1 | q0 | q5 | 0 |
> | q2 | q0 | q5 | 1 |
> | q3 | q4 | q3 | 0 |
> | q4 | q4 | q3 | 1 |
> | q5 | q1 | q0 | 0 |

**Lesson:** [[05.03 Moore to Mealy Conversion]]

### Expected answer
Algorithm 4: (1) blank Mealy table; (2) copy the transitions; (3) for each next state Qi, write Qi's Moore output.

| Present | 0: Next | 0: Out | 1: Next | 1: Out |
|---|---|---|---|---|
| → q0 | q5 | 0 | q2 | 1 |
| q1 | q0 | 1 | q5 | 0 |
| q2 | q0 | 1 | q5 | 0 |
| q3 | q4 | 1 | q3 | 0 |
| q4 | q4 | 1 | q3 | 0 |
| q5 | q1 | 0 | q0 | 1 |

e.g. δ(q0,0) = q5 and q5 outputs 0, so the entry is q5/0. Six states, unchanged; start state q0.
(Note: q3 and q4 are unreachable from q0; they are still converted as given.)

---

## 2020/21 · Q8(b) and 2022/23 · Q7(b) (20 marks)
> **Question:** Convert the following Moore machine into the Mealy machine.
>
> | Present State | Next (0) | Next (1) | Output |
> |---|---|---|---|
> | → q0 | q3 | q1 | 1 |
> | q1 | q0 | q3 | 0 |
> | q2 | q2 | q2 | 0 |
> | q3 | q1 | q0 | 1 |

### Expected answer
(This is the lecture's Algorithm 4 example renamed: a, b, c, d → q0, q1, q2, q3.)

| Present | 0: Next | 0: Out | 1: Next | 1: Out |
|---|---|---|---|---|
| → q0 | q3 | 1 | q1 | 0 |
| q1 | q0 | 1 | q3 | 1 |
| q2 | q2 | 0 | q2 | 0 |
| q3 | q1 | 0 | q0 | 1 |

Check with input 01: Moore states q0 → q3 → q0, outputs 1, 1, 1; Mealy outputs 1, 1. This is the Moore output without its first symbol. ✔

---

## 2023/24 · Q6(c) (20 marks)
> **Question:** Convert the following Moore machine into the Mealy machine.
>
> | Present State | Next (0) | Next (1) | Output |
> |---|---|---|---|
> | Q0 (Initial) | Q1 | Q2 | 0 |
> | Q1 | Q0 | Q3 | 0 |
> | Q2 | Q3 | Q0 | 0 |
> | Q3 | Q3 | Q3 | 1 |

### Expected answer
| Present | 0: Next | 0: Out | 1: Next | 1: Out |
|---|---|---|---|---|
| → Q0 | Q1 | 0 | Q2 | 0 |
| Q1 | Q0 | 0 | Q3 | 1 |
| Q2 | Q3 | 1 | Q0 | 0 |
| Q3 | Q3 | 1 | Q3 | 1 |

Every arrow entering Q3 carries output 1; all others carry 0.

---

## 2019/20 · Q6(c), 2020/21 · Q8(c), 2022/23 · Q7(c) (20 marks)
> **Question:** Convert the following Mealy machine into the Moore machine. (2019/20 prints "Convert the following Moore machine into a Malay machine", but the table is in Mealy form.)
>
> | Present State | a | b |
> |---|---|---|
> | → q0 | q3/0 | q1/1 |
> | q1 | q0/1 | q3/0 |
> | q2 | q2/1 | q2/0 |
> | q3 | q1/0 | q0/1 |

> [!note] Which direction?
> In 2019/20 the wording says Moore → Mealy, but the cells (`q3/0`) show the given machine is already **Mealy**. Point this out and convert **Mealy → Moore**. It is the same machine as 2020/21 Q8(c) and 2022/23 Q7(c), and the lecture's Algorithm 5 example renamed (a, b, c, d → q0, q1, q2, q3).

### Expected answer
**Step 1: outputs on transitions entering each state**
- q0: from q1 (a/1), from q3 (b/1) → {1} → keep **q0**, output 1.
- q1: from q0 (b/1), from q3 (a/0) → {0, 1} → split into **q10, q11**.
- q2: from q2 (a/1), from q2 (b/0) → {0, 1} → split into **q20, q21**.
- q3: from q0 (a/0), from q1 (b/0) → {0} → keep **q3**, output 0.

**Step 2: Moore table** (copies share outgoing transitions; arrows go to the copy matching their output)

| State | a | b | Output |
|---|---|---|---|
| q0 | q3 | q11 | 1 |
| q10 | q0 | q3 | 0 |
| q11 | q0 | q3 | 1 |
| q20 | q21 | q20 | 0 |
| q21 | q21 | q20 | 1 |
| q3 | q10 | q0 | 0 |

**Step 3:** the initial state q0 has output 1, so add a **new initial state q0′** with output **0** and the same transitions as q0 (a → q3, b → q11).

| State | a | b | Output |
|---|---|---|---|
| → q0′ | q3 | q11 | 0 |

(q2's copies are unreachable from the start, as q2 was in the original.)

Check (input ab): Mealy q0 –a/0→ q3 –b/1→ q0 gives **0 1**; Moore q0′ → q3 → q0 gives **0 0 1**, which is the Mealy output with one extra leading symbol. ✔

### Key points to include
- Identify the table as Mealy · Step 1 written out for every state · split states with identical outgoing transitions · Step 3 applied · Moore table + diagram.

---

## 2021/22 · Q7(a) (20 marks)
> **Question:** Design a Moore machine for a binary input sequence such that if it has a substring 101, the machine outputs "a"; if the input has a substring 110, it outputs "b" otherwise it outputs "c".

**Lesson:** [[05.05 Designing Mealy and Moore Machines]]

### Expected answer
Moore: the output belongs to the state, so states record the **longest useful suffix**; separate states for "just completed 101" (output a) and "just completed 110" (output b).

| State (meaning) | 0 | 1 | Output |
|---|---|---|---|
| → S0 (no useful suffix) | S0 | S1 | c |
| S1 (…1) | S10 | S11 | c |
| S10 (…10) | S0 | SA | c |
| S11 (…11) | SB | S11 | c |
| SA (…101) | S10 | S11 | **a** |
| SB (…110) | S0 | SA | **b** |

6-tuple: Q = {S0, S1, S10, S11, SA, SB}, Σ = {0,1}, O = {a, b, c}, δ as above, X as in the Output column, q0 = S0.
Trace 110101 → states S0 S1 S11 SB SA S10 SA → outputs **c c c b a c a** (7 outputs for 6 inputs).
Assumption: overlapping patterns allowed; output a/b at the moment the substring is completed.

---

## 2021/22 · Q7(b) (20 marks)
> **Question:** Convert the following Moore machine into the Mealy machine. [diagram]

**Moore machine read from the diagram:** Q1/0 (start), Q2/0, Q3/1, Q4/0, Q5/1.

| State | 0 | 1 | Output |
|---|---|---|---|
| → Q1 | Q1 | Q2 | 0 |
| Q2 | Q3 | Q4 | 0 |
| Q3 | Q3 | Q4 | 1 |
| Q4 | Q2 | Q5 | 0 |
| Q5 | Q2 | Q5 | 1 |

### Expected answer
| Present | 0: Next/Out | 1: Next/Out |
|---|---|---|
| → Q1 | Q1/0 | Q2/0 |
| Q2 | Q3/1 | Q4/0 |
| Q3 | Q3/1 | Q4/0 |
| Q4 | Q2/0 | Q5/1 |
| Q5 | Q2/0 | Q5/1 |

> [!note] Reading the diagram
> Arrows read from the 2021/22 diagram: Q1 loops on 0, Q1 –1→ Q2; Q2 –0→ Q3, Q2 –1→ Q4; Q3 loops on 0, Q3 –1→ Q4 (long curved arrow); Q4 –0→ Q2, Q4 –1→ Q5; Q5 loops on 1, Q5 –0→ Q2. Check these against your copy of the paper.

---

## 2021/22 · Q7(c) (20 marks)
> **Question:** Convert the following Mealy machine into the Moore machine. [diagram]

**Mealy machine read from the diagram:**

| State | a | b |
|---|---|---|
| → q1 | q1/1 | q2/0 |
| q2 | q3/1 | q3/1 |
| q3 | q4/0 | q1/1 |
| q4 | q2/1 | q4/1 |

### Expected answer
**Step 1:** q1 ← {1} (q1 a/1, q3 b/1) · q2 ← {0,1} (q1 b/0, q4 a/1) · q3 ← {1} (q2 a/1, b/1) · q4 ← {0,1} (q3 a/0, q4 b/1).
**Step 2:** keep q1 (1) and q3 (1); split q2 → q20, q21 and q4 → q40, q41.

| State | a | b | Output |
|---|---|---|---|
| q1 | q1 | q20 | 1 |
| q20 | q3 | q3 | 0 |
| q21 | q3 | q3 | 1 |
| q3 | q40 | q1 | 1 |
| q40 | q21 | q41 | 0 |
| q41 | q21 | q41 | 1 |

**Step 3:** q1 (initial) outputs 1, so add a new initial state **q0** with output **0** and q1's transitions (a → q1, b → q20).

---

## 2022/23 · Q7(a) (20 marks)
> **Question:** Design a Mealy machine for a binary input sequence such that: Output "M" immediately after detecting the substring "01". Output "N" immediately after detecting "10". Output "O" otherwise.

### Expected answer
"Immediately" means output on the transition, so a **Mealy** machine. A state remembers the previous symbol.

| State | 0 | 1 |
|---|---|---|
| → q0 (start) | qA / O | qB / O |
| qA (last 0) | qA / O | qB / **M** |
| qB (last 1) | qA / **N** | qB / O |

6-tuple: Q = {q0, qA, qB}, Σ = {0,1}, O = {M, N, O}, q0. Trace 0110 → O M O N; 1001 → O N O M. Overlap allowed.

---

## 2023/24 · Q6(a) (20 marks)
> **Question:** Design a Mealy machine for a vending machine that accepts coins of 5 and 10. Once the total reaches or exceeds 20, the machine dispenses the item by producing "D" (Dispense). Otherwise, it produces "C" (Continue).

### Expected answer
State = running total (0, 5, 10, 15). Mealy, because the output depends on the coin just inserted.

| State | coin 5 | coin 10 |
|---|---|---|
| → S0 (0) | S5 / C | S10 / C |
| S5 (5) | S10 / C | S15 / C |
| S10 (10) | S15 / C | S0 / D |
| S15 (15) | S0 / D | S0 / D |

Assumptions: after dispensing, reset to 0; no change returned. Σ = {5, 10}, O = {C, D}. Traces: 5,5,10 → C, C, D; 10,10 → C, D; 5,10,5 → C, C, D.

---

## 2023/24 · Q6(b) (20 marks)
> **Question:** Convert the Mealy machine in part (a) into a **Moore** machine.

### Expected answer
Incoming outputs: S5 {C}, S10 {C}, S15 {C}, S0 {D} (S0 is only entered by dispensing). As a Moore state S0 would output D at the start, before any coin. So keep a start state **S0** with output C and add **S0D** ("just dispensed", output D) for the dispensing arrows:

| State | coin 5 | coin 10 | Output |
|---|---|---|---|
| → S0 | S5 | S10 | C |
| S5 | S10 | S15 | C |
| S10 | S15 | S0D | C |
| S15 | S0D | S0D | C |
| S0D | S5 | S10 | D |

Check: 10, 5, 5 → Moore states S0 S10 S15 S0D → outputs C C C D (the Mealy output C C D with one leading symbol). ✔
