---
type: moc
course: CMIS 3134
status: complete
tags: [cmis3134, past-paper, moc]
aliases: [CMIS 3134 PP Mapping, 3134 lesson to question mapping]
---
# CMIS 3134 · Past Paper Mapping: Lessons ↔ Questions

> [!info] How to use
> - Each lesson has PP files with the **exact question wording** and **expected answers**.
> - By year: [[CMIS 3134 Papers by Year]] · Priorities: [[CMIS 3134 High Priority Topics]] · Home: [[00. CMIS 3134 Course Overview]]
> - Earlier analysis (kept unchanged): [[3134 Topic Map & Past Papers]]

## 1. Available papers

| Academic year | Exam held | Format | Source file |
|---|---|---|---|
| 2019/2020 | June 2022 | 8 Qs, answer 6, 3 h | `CMIS 3134 20192020.pdf` |
| 2020/2021 | June/July 2023 | 8 Qs, answer 6, 3 h | `CMIS 3134 20202021.pdf` |
| 2021/2022 | Jan/Feb 2024 | 8 Qs, answer 6, 3 h | `CMIS 3134 20212022.pdf` (CMIS 3134 is pp. 5–8 of a multi-subject bundle) |
| 2022/2023 | Feb/Mar 2025 | 8 Qs, answer 6, 3 h | `CMIS 3134 20222023.pdf` |
| 2023/2024 | Feb 2026 | 8 Qs, answer 6, 3 h | `CMIS 3134 2023 - 2024 feb 2026.pdf` |

Every question is worth 100 marks: parts (a), (b), (c) = 20, (d) = 40 (except 2019/20 Q8(c)–(d): 30 + 30).

## 2. Lesson → question tree

```text
01 Introduction to Compilers ......................... Q1–Q2 of EVERY paper
    ├── 2019/20 Q1(a) compiler vs assembler · Q1(c) symbol table · Q1(d) LPS (40)
    │           Q2(a) compilation steps · Q2(b) lexical vs syntax
    ├── 2020/21 Q1(a) swapped definitions · Q1(c) preprocessor · Q1(d) phases + diagram (40)
    │           Q2(a) C program on host machine
    ├── 2021/22 Q1(a) compiler vs interpreter/assembler · Q1(b) C program · Q1(d) LPS (40)
    │           Q2(a) linker/loader · Q2(b) phases · Q2(c) swapped syntax/semantic · Q2(d) ICG/opt/codegen/ST (40)
    ├── 2022/23 Q1(a) role of compiler · Q1(b) front-end analyses · Q1(c) no compilers
    │           Q2(a) optimization · Q2(b) phases · Q2(c) LPS bridges gap · Q2(d) single tool (40)
    └── 2023/24 Q1(a) why HLL not executable · Q1(b) phases & errors · Q1(c) int x="10"+5 · Q1(d) embedded (40)
                Q2(a) LPS diagram · Q2(b) compiler/decompiler/interpreter/assembler · Q2(c) ICG · Q2(d) compiles but wrong (40)
02 Regular Expressions ............................... every paper
    ├── 2019/20 Q2(c) example strings
    ├── 2020/21 Q2(b) reg. grammar vs language · Q2(c) tokens · Q2(d) strings (40) · Q6(a) 1101 in A/B
    ├── 2021/22 Q3(a) RE/grammar/language · Q3(b) tokens · Q3(c) decimal/id/NIC/email · Q3(d) construct + strings (40)
    ├── 2022/23 Q3(a)–(d) ≈ 2021/22 Q3
    └── 2023/24 Q3(a) RE & lexer · Q3(b) operations · Q3(c) binary/phone/reg/password · Q3(d) (aa+ab+ba+bb)* (40)
03 Automata ........................................... 1–2 questions every paper
    ├── 2019/20 Q3(a) FA · Q3(b) Java FSM · Q3(c) DFA vs NDFA · Q3(d) acceptability (40) · Q4(a) NDFA def · Q4(b) NDFA design · Q4(c) DFA not 01011
    ├── 2020/21 Q3(a)–(d) = 2019/20 Q3 · Q4(a) DFA def · Q4(b) terms · Q4(c) NDFA 2nd-last · Q4(d) 4 DFAs (40) · Q6(b) power/cardinality · Q6(c) Σ* proof
    ├── 2021/22 Q4(a) FA · Q4(b) Java · Q4(c) categorize · Q4(d) acceptability (40) · Q5(a) DFA def · Q5(b) terms · Q5(c) NDFA 3rd-last · Q5(d) 4 DFAs (40)
    ├── 2022/23 Q4(a) automata meaning · Q4(b) Σ* vs Σ⁺ · Q4(c) δ · Q4(d) acceptability (40) · Q5(a) Σ* proof · Q5(b) NFA easier · Q5(c) DFA vs NFA · Q5(d) 4 DFAs (40)
    └── 2023/24 Q4(a) self-acting · Q4(b) terms · Q4(c) Σ⁺ proof · Q4(d) ATM PIN DFA (40) · Q5(a) DFA⊆NDFA proof · Q5(b) NDFA easier · Q5(c) δ signatures · Q5(d) usernames DFA (40)
04 NDFA→DFA & Minimization ............................ every paper
    ├── 2019/20 Q4(d) NDFA→DFA (40) · Q5(d) Equivalence (40) · Q6(d) Myhill-Nerode (40)
    ├── 2020/21 Q5(d) Equivalence (40) · Q6(d) NDFA→DFA (40) · Q8(d) Myhill-Nerode (40)
    ├── 2021/22 Q6(c) NDFA→DFA · Q6(d) Myhill-Nerode (40) · Q7(d) Equivalence (40)
    ├── 2022/23 Q6(c) NDFA→DFA · Q6(d) Myhill-Nerode (40) · Q7(d) Equivalence (40)
    └── 2023/24 Q6(d) Equivalence (40) · Q7(c) NDFA→DFA · Q7(d) Myhill-Nerode (40)
05 Moore & Mealy ...................................... one question every paper
    ├── 2019/20 Q6(a) compare · Q6(b) Moore→Mealy · Q6(c) Mealy→Moore
    ├── 2020/21 Q8(a) compare · Q8(b) Moore→Mealy · Q8(c) Mealy→Moore
    ├── 2021/22 Q7(a) design Moore · Q7(b) Moore→Mealy · Q7(c) Mealy→Moore
    ├── 2022/23 Q7(a) design Mealy · Q7(b) Moore→Mealy · Q7(c) Mealy→Moore
    └── 2023/24 Q6(a) design Mealy (vending) · Q6(b) Mealy→Moore · Q6(c) Moore→Mealy
06 Lexical Analysis ................................... 4 of 5 papers
    ├── 2019/20 Q1(b) · 2020/21 Q1(b)  (Program 1)
    └── 2021/22 Q1(c) · 2022/23 Q1(d) (40)  (Program 2)
07 Syntax Analysis .................................... every paper (Q7 or Q8)
    ├── 2019/20 Q7(a) ambiguity · Q7(b) LMD/RMD · Q7(c) verify strings · Q7(d) LL(1) (40) ⚠ · Q8 parsing ⚠
    ├── 2020/21 Q7(a) derivations · Q7(b) ambiguity · Q7(c) LMD/RMD · Q7(d) verify strings (40)
    ├── 2021/22 Q8(a) language · Q8(b) ambiguity "aab" · Q8(c) LMD/RMD · Q8(d) verify strings (40)
    ├── 2022/23 Q8(a) language+derive · Q8(b) ambiguity "abab" · Q8(c) LMD/RMD · Q8(d) proofs (40)
    └── 2023/24 Q8 = 2022/23 Q8
08 Arden's Theorem & RE⇄FA ............................ every paper
    ├── 2019/20 Q2(d) identity proof (40) · Q5(a) Arden · Q5(b) RE→FM · Q5(c) FM→RE
    ├── 2020/21 Q5(a)–(c) = 2019/20 Q5(a)–(c)
    ├── 2021/22 Q6(a) Arden · Q6(b) RE→NDFA
    ├── 2022/23 Q6(a)–(b) = 2021/22 Q6(a)–(b)
    └── 2023/24 Q3(d)(ii) simplification · Q7(a) Arden · Q7(b) RE→NDFA
⚠ = not in the course notes
```

## 3. Frequency table (number of papers out of 5)

| Concept | 19/20 | 20/21 | 21/22 | 22/23 | 23/24 | Papers | Priority |
|---|---|---|---|---|---|---|---|
| Arden's theorem + proof | ✔ | ✔ | ✔ | ✔ | ✔ | **5** | 🔴 |
| RE → finite machine | ✔ | ✔ | ✔ | ✔ | ✔ | **5** | 🔴 |
| NDFA → DFA | ✔ | ✔ | ✔ | ✔ | ✔ | **5** | 🔴 |
| Minimization: Myhill-Nerode | ✔ | ✔ | ✔ | ✔ | ✔ | **5** | 🔴 |
| Minimization: Equivalence | ✔ | ✔ | ✔ | ✔ | ✔ | **5** | 🔴 |
| Moore → Mealy | ✔ | ✔ | ✔ | ✔ | ✔ | **5** | 🔴 |
| Mealy → Moore | ✔ | ✔ | ✔ | ✔ | ✔ | **5** | 🔴 |
| DFA (or NDFA) design | ✔ | ✔ | ✔ | ✔ | ✔ | **5** | 🔴 |
| Phases of a compiler | ✔ | ✔ | ✔ | ✔ | ✔ | **5** | 🔴 |
| Language Processing System | ✔ | ✔ | ✔ | ✔ | ✔ | **5** | 🔴 |
| LMD / RMD derivations | ✔ | ✔ | ✔ | ✔ | ✔ | **5** | 🔴 |
| Ambiguity | ✔ | ✔ | ✔ | ✔ | ✔ | **5** | 🔴 |
| RE reading / writing | ✔ | ✔ | ✔ | ✔ | ✔ | **5** | 🔴 |
| Compiler vs interpreter/assembler(/de-compiler) | ✔ | ✔ | ✔ | (role) | ✔ | 4–5 | 🔴 |
| Tokenize a C program | ✔ | ✔ | ✔ | ✔ | | **4** | 🔴 |
| Acceptability traces | ✔ | ✔ | ✔ | ✔ | (own DFA) | **4** | 🔴 |
| DFA vs NDFA | ✔ | ✔ | | ✔ | ✔ | **4** | 🔴 |
| DFA/NDFA formal definition / role of δ | ✔ | ✔ | ✔ | ✔ | ✔ | **5** | 🔴 |
| Terminology (alphabet, string, …) | | ✔ | ✔ | | ✔ | 3 | 🟡 |
| Σ\*, Σ⁺ (definitions / proofs) | | ✔ | | ✔ | ✔ | 3 | 🟡 |
| Valid tokens (x\*, x?, ranges) | | ✔ | ✔ | ✔ | | 3 | 🟡 |
| Real-world token REs | | | ✔ | ✔ | ✔ | 3 | 🟡 |
| RE vs regular grammar vs regular language | | ✔ | ✔ | ✔ | (RE + lexer) | 3–4 | 🟡 |
| Java FSM question | ✔ | ✔ | ✔ | | | 3 | 🟡 |
| Language of a grammar / proofs | | ✔ | ✔ | ✔ | ✔ | 4 | 🔴 |
| "Does G generate w?" | ✔ | ✔ | ✔ | | | 3 | 🟡 |
| Moore/Mealy design | | | ✔ | ✔ | ✔ | 3 | 🟡 (rising: all of the last 3) |
| Compare Moore & Mealy | ✔ | ✔ | | | | 2 | 🟡 |
| FA → RE | ✔ | ✔ | | | | 2 | 🟡 |
| RE identities / simplification | ✔ | | | | ✔ | 2 | 🟡 |
| Symbol table / ICG / optimization (separately) | ✔ | | ✔ | ✔ | ✔ | 4 | 🟡–🔴 |
| Preprocessor · linker/loader | | ✔ | ✔ | (✔) | | 2–3 | 🟡 |
| Classification of FA | | | ✔ | | | 1 | 🟢 |
| Parsing (LL(1), LR(0), top-down/bottom-up, backtracking) | ✔ | | | | | 1 | 🟢 (not in notes) |
| Longest match, rule priority, left recursion, left factoring, FIRST/FOLLOW alone, associativity/precedence alone | | | | | | 0 | 🟢 |

## 4. Repeated questions (near-certain to return)

| Repeated question | Years |
|---|---|
| State Arden's theorem and prove R = QP\* is the unique solution | **all 5** |
| Same A–E DFA minimized (Myhill-Nerode or Equivalence) | 2019/20, 2020/21, 2021/22, 2022/23 |
| Same A–G DFA, Equivalence theorem | 2019/20, 2020/21 |
| Same q0–q3 Moore table → Mealy | 2020/21, 2022/23 |
| Same q0–q3 Mealy table → Moore | 2019/20, 2020/21, 2022/23 |
| Same A–D automaton and 5 strings | 2019/20, 2020/21 |
| Same C programs to tokenize | 2019/20 = 2020/21; 2021/22 = 2022/23 |
| Java FSM question | 2019/20, 2020/21, 2021/22 |
| Draw NDFA for 10 + (0 + 11)0\*1 (→ DFA → Myhill-Nerode) | 2021/22, 2022/23 |
| Whole Q8 (grammars) | 2022/23 = 2023/24 |
| Whole Q5 (RE→FM, FM→RE, Arden) | 2019/20 = 2020/21 |
| Decimal / identifier / NIC / email REs | 2021/22, 2022/23 |
| Grammar S → aB \| bA, A → aS \| bAA \| ε, B → b \| bS \| aBB with the same 4 strings | 2020/21, 2021/22 (+ LMD/RMD of "aababab" in 2022/23, 2023/24) |

## 5. Unique questions (appeared once)
De-compiler (2023/24) · `int x = "10" + 5` (2023/24) · embedded-system optimization (2023/24) · compiles-but-wrong-output (2023/24) · single tool replacing the tool chain (2022/23) · power and cardinality of Σ (2020/21) · 1101 in A/B (2020/21) · classification of FA (2021/22) · every DFA is an NDFA (2023/24) · ATM PIN and username DFAs (2023/24) · vending machine Mealy (2023/24) · parsing block (2019/20).

## 6. Corrections found while checking the papers

| Item | Earlier analysis / study guide | What the paper actually says / correct answer |
|---|---|---|
| 2019/20 Q7(c) grammar | B → 1 \| 1S \| 0**DD** | B → 1 \| 1S \| 0**BB** |
| 2019/20 & 2020/21 Q3(d), 5th string | cabbbabaacc | **cababbabaacc** (accepted either way) |
| Acceptability of `bba` (2019/20, 2020/21) | traced as accepted | **rejected**: the machine has no B –b→ C or C –a→ D arrow |
| The A–D automata | treated as DFAs | they are **NDFAs** (A on b → {A, B}; in 2021/22, B on b → {A, C}) |
| Grammar with A → ε (2020/21, 2021/22, 2022/23, 2023/24) | analysed as the "equal a's and b's" grammar | as printed it is **not** that grammar: all strings have #b ≥ #a and end in b, so none of the test strings, and not "aababab", is derivable |
| "bbabaababbba" | 6 a's, 6 b's | **5 a's, 7 b's** |
| 2022/23 Q3(b)(iii), 2021/22 Q3(b)(iii) | [1-7], [a-g] | printed **[1 – 7)** and **[a – g)** (mixed brackets) |
| A–G DFA minimization | (not solved) | **F is unreachable**; result {A}, {B,C}, {D,E}, {G} |
