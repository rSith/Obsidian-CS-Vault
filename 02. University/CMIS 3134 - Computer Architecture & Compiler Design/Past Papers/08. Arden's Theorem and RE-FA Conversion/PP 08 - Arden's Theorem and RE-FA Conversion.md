---
type: past-paper
course: CMIS 3134
lesson: L8
status: complete
tags: [cmis3134, past-paper, cmis3134/L8]
---
# PP 08 · Arden's Theorem, RE Identities and RE ⇄ FA: Past-Paper Questions

> [!info] Lesson: [[08.00 Arden's Theorem and RE-FA Conversion]] · Summary: [[08.99 Summary - Arden's Theorem and RE-FA Conversion]] · Parts (c)/(d) of the pipeline questions: [[PP 04.1 - NDFA to DFA Conversion]] · [[PP 04.2 - DFA Minimization]]

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2019/20 | 2(d) | Prove (0110 + 01)(10)\* = 01(10)\* | 40 |
| 2019/20 | 5(a) | Arden's theorem + proof | 20 |
| 2019/20 | 5(b) | Finite machines for 0\* + 0\*11\* and (01 + 10)\* | 20 |
| 2019/20 | 5(c) | Finite machine → RE | 20 |
| 2020/21 | 5(a), 5(b), 5(c) | **Identical to 2019/20 Q5(a)–(c)** | 20 each |
| 2021/22 | 6(a) | Arden's theorem + proof | 20 |
| 2021/22 | 6(b) | NDFA for 10 + (0 + 11)0\*1 | 20 |
| 2022/23 | 6(a), 6(b) | **Identical to 2021/22 Q6(a)–(b)** | 20 each |
| 2023/24 | 7(a) | Arden's theorem + proof | 20 |
| 2023/24 | 7(b) | NDFA for ab\* + (a + bb)a\*b\* | 20 |

---

## Arden's Theorem: 2019/20 Q5(a), 2020/21 Q5(a), 2021/22 Q6(a), 2022/23 Q6(a), 2023/24 Q7(a) (20 marks each)
> **Question:** State **Arden's Theorem** and prove that R = QP\* is a solution, and it is the one and only unique solution of it. (Suppose that P, Q, and R are regular expressions)
> (2023/24: "…prove that R = QP\* is a solution of R = Q + RP, and it is the only unique solution of it.")

**Lesson:** [[08.02 Arden's Theorem]]

### Expected answer
**Statement:** Let P and Q be two regular expressions over Σ. **If P does not contain ε**, then the equation **R = Q + RP** has a **unique** solution **R = QP\***.

**Part 1: QP\* is a solution.**
Q + RP = Q + (QP\*)P = Q(ε + P\*P) = Q(ε + P⁺) = QP\* = R. ✔ (using ε + P\*P = P\*)

**Part 2: uniqueness.**
R = Q + RP
= Q + (Q + RP)P = Q + QP + RP²
= Q + QP + QP² + RP³
⋮
= Q(ε + P + P² + … + Pⁿ) + RPⁿ⁺¹

Since P does not contain ε, every string in Pⁿ⁺¹ has length ≥ n + 1, so every string in RPⁿ⁺¹ has length > n. For any string w ∈ R with |w| = k, choose n ≥ k: w cannot come from RPⁿ⁺¹, so w ∈ Q(ε + P + … + Pⁿ) ⊆ QP\*. Hence R ⊆ QP\*. Also QP\* ⊆ R (every term QPⁱ appears in the expansion). Therefore **R = QP\* is the only solution.** ∎

**Why P must not contain ε:** if P = ε, R = Q + R holds for many R (e.g. Q = a: R = a, R = a + b, …), so the solution is not unique.

### Key points to include
- Complete statement with the ε condition · Part 1 with the identity named · Part 2 with ≥ 3 substitution steps and the length argument · ∎.

### How to approach this question
Memorise it and do it first. About 6 minutes for 20 guaranteed marks.

---

## 2019/20 · Q5(b) and 2020/21 · Q5(b) (20 marks)
> **Question:** Draw the corresponding **Finite Machine** for the following regular expressions. i. 0\* + 0\*11\* ii. (01 + 10)\*

**Lesson:** [[08.04 Regular Expression to Finite Automaton]]

### Expected answer
**(i) 0\* + 0\*11\*** = 0\*(ε + 11\*) = **0\*1\***

| State | 0 | 1 |
|---|---|---|
| → \*q0 | q0 | q1 |
| \*q1 | – | q1 |

q0 is start and final with a 0-loop (0\*); q0 –1→ q1 (final) with a 1-loop (0\*11\*). A missing move means rejection (add a dead state D with q1 –0→ D for a complete DFA).

**(ii) (01 + 10)\***

| State | 0 | 1 |
|---|---|---|
| → \*q0 | q1 | q2 |
| q1 | – | q0 |
| q2 | q0 | – |

q0 is start and final (ε accepted); 0 then 1, or 1 then 0, returns to q0. Tests: ε ✔, 01 ✔, 0110 ✔, 1001 ✔, 00 ✘, 011 ✘.

### Key points to include
- Decomposition of each RE · construction rules used · diagram with start arrow and double circles · test strings.

---

## 2019/20 · Q5(c) and 2020/21 · Q5(c) (20 marks)
> **Question:** Convert the following Finite Machine into Regular Expression. [Diagram: A (start): b → A, a → B; B: a → B, b → C; C (final): b → A, a → B]

**Lesson:** [[08.03 Finite Automaton to Regular Expression]]

### Expected answer
**Equations (incoming arrows, ε on the start state):**
- A = ε + Ab + Cb
- B = Aa + Ba + Ca
- C = Bb

**Solve:**
1. B = Aa + Ba + Bba = Aa + B(a + ba). By Arden (Q = Aa, P = a + ba): **B = Aa(a + ba)\***.
2. C = Bb = Aa(a + ba)\*b.
3. A = ε + Ab + Aa(a + ba)\*bb = ε + A(b + a(a + ba)\*bb). By Arden (Q = ε): **A = (b + a(a + ba)\*bb)\***.
4. **C = (b + a(a + ba)\*bb)\* a(a + ba)\* b**

**Simplified equivalent:** the machine accepts exactly the strings ending in "ab", so the RE is equivalent to **(a + b)\*ab** (checked by computer up to length 11).

### Key points to include
- State Arden's theorem · equations from incoming arrows · name Q and P at each Arden step · final RE for state C.

---

## 2021/22 · Q6(b) and 2022/23 · Q6(b) (20 marks)
> **Question:** Draw the corresponding **Non-Deterministic Finite Machine** for the following regular expression, over Σ = {0, 1}. 10 + (0 + 11)0\* 1

### Expected answer
Decomposition: **10** + **(0 + 11) · 0\* · 1**

| State | 0 | 1 |
|---|---|---|
| → A | {D} | {B, E} |
| B | {C} | ∅ |
| \*C | ∅ | ∅ |
| D | {D} | {C} |
| E | ∅ | {D} |

- 10: A –1→ B –0→ C
- (0 + 11): A –0→ D or A –1→ E –1→ D
- 0\*: D self-loop on 0; then 1: D –1→ C
- Non-determinism: A on 1 → {B, E}.
- Tests: 10 ✔, 01 ✔, 001 ✔, 1101 ✔, 11001 ✔, 11 ✘, 100 ✘.
(This is the same construction as your handwritten notes' example of this RE.)

---

## 2023/24 · Q7(b) (20 marks)
> **Question:** Draw the corresponding **Non-Deterministic Finite Machine** for the following regular expression, over Σ = {a, b}. ab\* + (a + bb)a\*b\*

### Expected answer
Decomposition: **ab\*** + **(a + bb) · a\* · b\***

| State | a | b |
|---|---|---|
| → q0 | {q1, q2} | {q3} |
| \*q1 | ∅ | {q1} |
| \*q2 | {q2} | {q4} |
| q3 | ∅ | {q2} |
| \*q4 | ∅ | {q4} |

- ab\*: q0 –a→ q1 (final), q1 loops on b.
- (a + bb): q0 –a→ q2, or q0 –b→ q3 –b→ q2.
- a\*b\*: q2 (final) loops on a; q2 –b→ q4 (final); q4 loops on b.
- Tests: a ✔, ab ✔, bb ✔, abab ✘, bbaab ✔, b ✘.
- Remark: ab\* ⊆ (a + bb)a\*b\*, so the RE equals (a + bb)a\*b\*, but draw the machine for the RE as given.

---

## 2019/20 · Q2(d) (40 marks)
> **Question:** Prove that (0110 + 01)(10)\* is equal to 01(10)\*

**Lesson:** [[08.01 Regular Expression Identities]]

### Expected answer
Identities used: εR = Rε = R; (P + Q)R = PR + QR (and R(P+Q) = RP + RQ); RR\* = R⁺; ε + R⁺ = R\* (i.e. ε + RR\* = R\*); R + R = R.

$$
\begin{aligned}
(0110 + 01)(10)^* &= (01\cdot10 + 01\cdot\varepsilon)(10)^* && (01 = 01\varepsilon) \\
&= 01(10 + \varepsilon)(10)^* && \text{(distributive law, factor 01)} \\
&= 01[\,10(10)^* + (10)^*\,] && \text{(distributive law, } \varepsilon R = R) \\
&= 01[\,(10)^+ + (10)^*\,] && (RR^* = R^+) \\
&= 01[\,(10)^+ + \varepsilon + (10)^+\,] && ((10)^* = \varepsilon + (10)^+) \\
&= 01[\,\varepsilon + (10)^+\,] && (R + R = R) \\
&= 01(10)^* && (\varepsilon + R^+ = R^*) \qquad \blacksquare
\end{aligned}
$$

**Check:** both sides generate 01, 0110, 011010, 01101010, … (verified for all strings up to length 11).

### Key points to include
- List the identities first · name the identity on every line · string check at the end.

### How to approach this question
At 40 marks, the justifications count as much as the algebra. Write one step per line.
