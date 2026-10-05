---
type: lecture
course: CMIS 3134
lecture: 4
status: stub
priority: Tier 1
tags: [compilers]
aliases: [NDFA to DFA Conversion, DFA Minimization]
---
# NDFA to DFA Conversion & DFA Minimization
> [!info] CMIS 3134 · Lecture 4
> [[CMIS 3134 Course Home]] · Exam prep: [[3134 Topic Map & Past Papers|Topic map]] · Study priority: **Tier 1**
> ← [[3134-03 Automata]] | [[3134-05 Moore and Mealy Machines]] →

> [!tip]- Exam focus (5 papers, 2019/20–2023/24)
> - **Every paper** — NDFA → DFA conversion (20–40 marks)
> - **Every paper** — DFA minimization — Myhill-Nerode (40 marks)
> - **Every paper** — DFA minimization — Equivalence Theorem (40 marks)

> [!question]- Past paper questions on this lecture (15)
> - **2019/20 Q4(d)** · 40 marks — Find the equivalent **DFA** for the NDFA `X = ({q0…q4},{0,1},δ,q0,{q4})` given by the table
> - **2019/20 Q5(d)** · 40 marks — Minimize the given DFA (A–G) using the **Equivalence Theorem**
> - **2019/20 Q6(d)** · 40 marks — Minimize the given DFA (A–E) using the **Myphill-Nerode Theorem**
> - **2020/21 Q5(d)** · 40 marks — Minimize the given DFA (A–G) using the **Equivalence Theorem**
> - **2020/21 Q6(d)** · 40 marks — Find the equivalent **DFA** for the NDFA `X = ({q0…q4},{a,b},δ,q0,{q1})`
> - **2020/21 Q8(d)** · 40 marks — Minimize the given DFA (A–E) using the **Myphill-Nerode Theorem**
> - **2021/22 Q6(c)** · 20 marks — Find the equivalent **DFA** for the NDFA in part (b)
> - **2021/22 Q6(d)** · 40 marks — Minimize the DFA in part (c) using the **Myphill-Nerode Theorem**
> - **2021/22 Q7(d)** · 40 marks — Minimize the given DFA using the **Equivalence Theorem**
> - **2022/23 Q6(c)** · 20 marks — Find the equivalent **DFA** for the NDFA in part (b)
> - **2022/23 Q6(d)** · 40 marks — Minimize the DFA in part (c) using the **Myphill-Nerode Theorem**
> - **2022/23 Q7(d)** · 40 marks — Minimize the given DFA (A–E) using the **Equivalence Theorem**
> - **2023/24 Q6(d)** · 40 marks — Minimize the given DFA (A–F, table form) using the **Equivalence Theorem**
> - **2023/24 Q7(c)** · 20 marks — Find the equivalent **DFA** for the NDFA machine in part (b)
> - **2023/24 Q7(d)** · 40 marks — Minimize the DFA in part (c) using the **Myhill-Nerode Theorem**

## Topic checklist
- [ ] **4.1 NDFA → DFA: problem statement** — Given `X = (Qx, ∑, δx, q0, Fx)` accepting L(X), design `Y = (Qy, ∑, δy, q0, Fy)` with L(Y) = L(X)
- [ ] **4.2 NDFA → DFA algorithm (6 steps)** — 1 Create state table from NDFA · 2 Create blank state table for the DFA · 3 Mark start state q0 · 4 Find combination of states {Q0…Qn} per input alphabet · 5 Re-apply step 4 for each newly generated DFA state · 6 Any state containing an NDFA final state is a DFA final state
- [ ] **4.3 Worked NDFA→DFA example** — NDFA table with `∅` entries → DFA table with composite states `[a,b,c,d,e]`, `[d,e]`, `[b,d,e]`, `[c,e]` → state diagram
- [ ] **4.4 DFA Minimization — Myhill-Nerode Theorem** — Step 1 table of all state pairs (Qi, Qj), unmarked · Step 2 mark pairs where Qi ∈ F and Qj ∉ F (or vice versa) · Step 3 repeat: mark unmarked (Qi,Qj) if `{δ(Qi,A), δ(Qj,A)}` is marked for some input · Step 4 combine remaining unmarked pairs into single states
- [ ] **4.5 Worked Myhill-Nerode example** — 6-state DFA {a…f}; transitive marking of (a,f) and (b,f); unmarked {a,b}, {c,d}, {c,e}, {d,e} recombined to {c,d,e}; final minimized DFA = {f}, {a,b}, {c,d,e}
- [ ] **4.6 DFA Minimization — Equivalence Theorem** — Two states combinable into {X,Y} if **not distinguishable**; distinguishable iff ∃ string S with exactly one of δ(X,S), δ(Y,S) accepting; DFA minimal iff all states distinguishable
- [ ] **4.7 Equivalence algorithm (Algorithm 3)** — Step 1 partition Q into final / non-final = P0 (0-equivalent), counter k=0 · Step 2 increment k; split each partition where states are k-distinguishable · Step 3 repeat while Pk ≠ Pk−1 · Step 4 combine kth equivalent sets as new states
- [ ] **4.8 Worked Equivalence example** — 6-state DFA; `P0 = {(c,d,e),(a,b,f)}` → `P1 = {(c,d,e),(a,b),(f)}` → `P2 = P1` → 3-state reduced DFA + transition table

## Notes
*Work through the checklist above. Write each topic in your own words, then tick it off.*


## Key definitions
> [!note] Term
>

## Summary (in my own words)


## Open questions
- [ ]
