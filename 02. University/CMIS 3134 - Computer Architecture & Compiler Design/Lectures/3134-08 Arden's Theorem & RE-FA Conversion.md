---
type: lecture
course: CMIS 3134
lecture: 8
status: stub
priority: Tier 1
tags: [compilers]
aliases: ["Arden's Theorem", RE to Finite Machine]
---
# Arden's Theorem, RE ⇄ FA Conversion & RE Identities
> [!info] CMIS 3134 · Extra topic
> [[CMIS 3134 Course Home]] · Exam prep: [[3134 Topic Map & Past Papers|Topic map]] · Study priority: **Tier 1**
> Source: handwritten extended notes (`Compiler note.pdf`, sections B.13–B.17). **These topics aren't in the typed lecture notes but are examined every year.**
> ← [[3134-07 Syntax Analysis]]

> [!tip]- Exam focus (5 papers, 2019/20–2023/24)
> - **Every paper** — Arden's Theorem — statement + full proof (20 marks)
> - **Every paper** — RE → Finite Machine construction (20 marks)
> - Repeated stem (**all 5 papers**): *State Arden's Theorem and prove that R = QP\* is the one and only unique solution.*
> - Repeated stem: *Draw the NDFM for `10 + (0 + 11)0*1`* (2021/22, 2022/23).
> - ⚠️ FA → RE conversion (2019/20, 2020/21) and RE algebraic identities (2019/20, 2023/24) appear only in the handwritten notes.

> [!question]- Past paper questions on this lecture (13)
> - **2019/20 Q2(d)** · 40 marks — Prove that `(0110 + 01)(10)*` is equal to `01(10)*` ⚠️
> - **2019/20 Q5(a)** · 20 marks — State **Arden's Theorem** and prove `R = QP*` is the one and only unique solution ⚠️
> - **2019/20 Q5(b)** · 20 marks — Draw the corresponding **Finite Machine** for (i) `0* + 0*11*` (ii) `(01 + 10)*` ⚠️
> - **2019/20 Q5(c)** · 20 marks — Convert the given **Finite Machine into a Regular Expression** (states A, B, C) ⚠️
> - **2020/21 Q5(a)** · 20 marks — State **Arden's Theorem** and prove `R = QP*` is the unique solution ⚠️
> - **2020/21 Q5(b)** · 20 marks — Draw the **Finite Machine** for (i) `0* + 0*11*` (ii) `(01 + 10)*` ⚠️
> - **2020/21 Q5(c)** · 20 marks — Convert the given **Finite Machine into a Regular Expression** (A, B, C) ⚠️
> - **2021/22 Q6(a)** · 20 marks — State **Arden's Theorem** and prove `R = QP*` is the unique solution ⚠️
> - **2021/22 Q6(b)** · 20 marks — Draw the corresponding **Non-Deterministic Finite Machine** for `10 + (0 + 11)0*1` over ∑ = {0,1} ⚠️
> - **2022/23 Q6(a)** · 20 marks — State **Arden's Theorem** and prove `R = QP*` is the only unique solution ⚠️
> - **2022/23 Q6(b)** · 20 marks — Draw the corresponding **Non-Deterministic Finite Machine** for `10 + (0 + 11)0* 1` over ∑ = {0,1} ⚠️
> - **2023/24 Q7(a)** · 20 marks — State **Arden's Theorem** and prove that `R = QP*` is a solution of `R = Q + RP`, and it is the only unique solution ⚠️
> - **2023/24 Q7(b)** · 20 marks — Draw the corresponding **Non-Deterministic Finite Machine** for `ab* + (a + bb)a*b*` over ∑ = {a, b} ⚠️

## Topic checklist
- [ ] **B.13 Regular Expression algebraic identities** — `φ + R = R` · `φR = Rφ = φ` · `εR = Rε = R` · `ε* = ε`, `φ* = ε` · `R + R = R` · `R*R* = R*` · `RR* = R*R = R+` · `(R*)* = R*` · `ε + R*R = R*` · `(PQ)*P = P(QP)*` · `(P+Q)* = (P*Q*)* = (P*+Q*)*` · `(P+Q)R = PR + QR`
- [ ] **B.14 RE simplification worked examples** — `(1+00*1)+(1+00*1)(0+10*1)*(0+10*1) = 0*1(0+10*1)*`; length-exactly-2 and length-at-most-2 languages over {a,b} reduced to `(a+b)(a+b)` and `(ε+a+b)(ε+a+b)`
- [ ] **B.15 Arden's Theorem** — *If P and Q are two REs over ∑ and P does not contain ε, then `R = Q + RP` has the unique solution `R = QP*`*; **full algebraic proof** by repeated substitution
- [ ] **B.16 FA → Regular Expression** — Writing state equations `q1 = ε + q1a …`, substitution, applying Arden's theorem, extracting the RE for the final state; multiple worked examples
- [ ] **B.17 RE → Finite Machine construction rules** — Rule 1 `(a+b)` · Rule 2 `(a.b)` · Rule 3 `a*` · Rule 4 `a+`; worked examples `ba*b`, `(a+b)c`, `a(bc)*`, `(a+b)*(abb + a*b)`, `10+(0+11)0*1`

## Notes
*Work through the checklist above. Write each topic in your own words, then tick it off.*


## Key definitions
> [!note] Term
>

## Summary (in my own words)


## Open questions
- [ ]
