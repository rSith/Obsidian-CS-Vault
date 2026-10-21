---
type: revision
course: CMIS 3134
status: complete
tags: [cmis3134, revision, algorithms]
aliases: [3134 algorithms, CMIS 3134 procedures]
---
# CMIS 3134 · Algorithms and Procedures

> [!info] Why this note exists
> More than half of every paper is mechanical. The marking pattern is **method marks** (write the steps) + **working marks** (show every table) + **answer marks** (final table and diagram). Learn each procedure in this order: **name it → list the steps → execute → final machine → quick check.**
> Home: [[00. CMIS 3134 Course Overview]]

## 1. NDFA → DFA (subset construction) 🔴
→ [[04.01 NDFA to DFA - Subset Construction]] · examples: [[PP 04.1 - NDFA to DFA Conversion]]

1. Write the NDFA transition table.
2. Create a blank DFA table with the same input columns.
3. Start state of the DFA = **[q0]**.
4. For the current DFA state [S] and each input a, write $\bigcup_{q \in S} \delta(q, a)$ as the next state.
5. Each **new** set that appears becomes a row; repeat step 4 until no new sets appear.
6. Every set that **contains an NDFA final state** is a final state of the DFA. ∅ is the **dead state** (loops to itself on all inputs).

> [!tip] Checks
> Show the union for each cell (e.g. δ([q0,q1], a) = δ(q0,a) ∪ δ(q1,a) = {q0} ∪ {q2} = [q0,q2]). Include ∅. Draw the final DFA.

## 2. DFA minimization: Myhill-Nerode (table filling) 🔴
→ [[04.02 DFA Minimization - Myhill-Nerode Theorem]] · examples: [[PP 04.2 - DFA Minimization]]

0. Remove unreachable states.
1. Draw a table of all pairs (Qi, Qj) (the lower triangle).
2. **Mark** every pair where one state is final and the other is not.
3. Repeat: for an **unmarked** pair (Qi, Qj), mark it if for some input A the pair {δ(Qi, A), δ(Qj, A)} is **already marked**. Continue until a full pass marks nothing new.
4. **Combine** all unmarked pairs into single states; draw the reduced DFA.

> [!tip] Write the reason for each mark, e.g. "(B, A): on 1 → (D, B), which is marked, so mark (B, A)."

## 3. DFA minimization: Equivalence theorem (partitioning) 🔴
→ [[04.03 DFA Minimization - Equivalence Theorem]]

0. Remove unreachable states.
1. **P0** = { F, Q − F } (final and non-final groups).
2. **P(k+1):** inside each group of Pk, two states stay together only if, for **every** input, they go to states in the **same group** of Pk. Otherwise split.
3. Stop when **Pk = Pk−1**. Each group is one state of the minimal DFA.
4. Build the reduced table: start state = the group with q0; final states = groups containing final states.

## 4. Moore → Mealy (Algorithm 4) 🔴
→ [[05.03 Moore to Mealy Conversion]]

1. Draw a blank Mealy table (next state + output for each input).
2. Copy every transition of the Moore machine.
3. For each state Qi with output m, write **m as the output wherever Qi is the next state**.

The number of states does not change. (The Mealy machine drops the Moore machine's first output, so it gives n outputs instead of n + 1.)

## 5. Mealy → Moore (Algorithm 5) 🔴
→ [[05.04 Mealy to Moore Conversion]]

1. For each state Qi, list the outputs on the transitions **entering** Qi.
2. If all are the same, keep Qi with that output. If there are n different outputs, **split** Qi into Qi0, Qi1, … (one per output); each copy has the **same outgoing transitions** as Qi.
3. Redirect every entering transition to the copy with the matching output.
4. If the output of the initial state is 1, add a new initial state with output 0 that has the same outgoing transitions (lecture rule, Algorithm 5 step 3). If nothing enters the initial state, it keeps a single copy.

## 6. Finite automaton → regular expression (Arden) 🟡
→ [[08.03 Finite Automaton to Regular Expression]]

1. State Arden's theorem: R = Q + RP ⇒ R = QP\* (P has no ε).
2. Write one equation per state from its **incoming** arrows: qj = Σ qi·a for each arrow qi –a→ qj. Add **+ ε** to the start state.
3. Substitute and use Arden to remove self-references. Name **Q** and **P** at each step.
4. The answer is the expression for the final state (sum of all final states if there are several).

## 7. Regular expression → finite automaton 🔴
→ [[08.04 Regular Expression to Finite Automaton]]

1. Decompose the RE into its top-level union and concatenation parts.
2. Use the rules: a + b → parallel arrows · a·b → states in series · a\* → self-loop on a state · a⁺ → one arrow then a self-loop.
3. Draw the NDFA (start arrow, double circles), give its table.
4. Test 3–4 accepted and 1–2 rejected strings.

## 8. Arden's theorem proof 🔴 (every paper)
→ [[08.02 Arden's Theorem]]

1. **Statement** with the condition "P does not contain ε".
2. **QP\* is a solution:** Q + QP\*P = Q(ε + P\*P) = QP\*.
3. **Uniqueness:** substitute R = Q + RP into itself n times → R = Q(ε + P + … + Pⁿ) + RPⁿ⁺¹. Strings of RPⁿ⁺¹ are longer than n (P has no ε); for w ∈ R pick n ≥ |w|, so w ∈ QP\*. Also QP\* ⊆ R. So R = QP\*. ∎

## 9. Designing a DFA from a description 🔴
→ [[03.10 Designing DFAs]]

1. Decide **what each state must remember** (prefix seen so far, count mod k, last symbols, parity…).
2. "Contains w": states = longest prefix of w matched so far; final = whole w seen (loops).
3. "Does **not** contain w" / complement: build the positive DFA, then **swap final and non-final** states (the DFA must be complete).
4. "Starts with w": a chain of states for w, then a final state looping on everything; wrong symbol → dead state.
5. "Ends with w": like "contains" but the final state does not trap.
6. Two conditions (e.g. even 0s **and** odd 1s): product of the states (EE, EO, OE, OO).
7. Check: every state has a transition for **every** symbol; test strings.

## 10. Acceptability trace 🔴
→ [[03.09 Acceptability of Strings]]

- DFA: δ(q0, s₁) = …, step by step; accepted if the last state ∈ F.
- **NDFA:** track the **set** of current states after each symbol; accepted if the final set contains a final state. If the set becomes ∅, reject immediately.

## 11. Leftmost/rightmost derivation and ambiguity 🔴
→ [[07.03 Derivations - Left-most and Right-most]] · [[07.05 Ambiguity]]

- **LMD/RMD:** one step per line; underline the non-terminal replaced; write the rule used.
- **Ambiguity:** find one string with **two different parse trees** (or two different LMDs); draw both trees. For "not ambiguous", argue that each step has only one possible choice.

## 12. "Does G generate w?" 🟡
→ [[07.06 Language of a Grammar and Membership Proofs]]

1. Find an **invariant** that every sentential form satisfies (e.g. #a = #b, ends in b, length even).
2. If w breaks the invariant → **not generated** (say which invariant).
3. Otherwise give an explicit derivation.

## 13. Tokenizing a program 🔴
→ [[06.05 Tokenizing a C Program]]

1. Say that preprocessor lines, whitespace and comments produce **no tokens**.
2. Go line by line; write `<category, lexeme>` for every keyword, identifier, constant, string literal, operator and punctuator.
3. A whole string literal is **one** token; `printf` is an identifier.
4. Give the **total** and the count per category.

## 14. LL(1) and LR(0) parsing ⚠ 🟢 (2019/20 only)
→ [[07.09 First and Follow Sets]] · [[07.11 Parsing Strategies - Top-Down, Bottom-Up, LL(1), LR(0)]]

- **LL(1):** FIRST and FOLLOW → table: put X → α under FIRST(α); if ε ∈ FIRST(α), also under FOLLOW(X) → no cell has two entries ⇒ LL(1).
- **LR(0):** augment S′ → S → item sets with closure/goto → shift on terminals, goto on non-terminals, reduce in every column for completed items → stack trace.
