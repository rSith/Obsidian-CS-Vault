---
type: revision
course: CMIS 3134
status: complete
tags: [cmis3134, revision, priority]
aliases: [3134 high priority, CMIS 3134 priorities]
---
# CMIS 3134 · High Priority Topics

> [!info] Evidence base
> Ranked by how often each topic appears across the **five papers** (2019/20 to 2023/24) and by how many marks it carries. Full counts: [[CMIS 3134 Past Paper Mapping]]. Home: [[00. CMIS 3134 Course Overview]]

## 🔴 Tier 1: in every paper, mechanical, high marks
Learn these until you can do them without notes. Together they can fill **4 of the 6 questions** you need.

| # | Topic | Papers | Typical marks | Learn from | Practise with |
|---|---|---|---|---|---|
| 1 | **Arden's theorem**: statement + proof | 5/5 | 20 | [[08.02 Arden's Theorem]] | [[PP 08 - Arden's Theorem and RE-FA Conversion]] |
| 2 | **DFA minimization**: Myhill-Nerode **and** Equivalence | 5/5 (two parts per paper) | 40 + 40 | [[04.02 DFA Minimization - Myhill-Nerode Theorem]] · [[04.03 DFA Minimization - Equivalence Theorem]] | [[PP 04.2 - DFA Minimization]] |
| 3 | **NDFA → DFA** | 5/5 | 20–40 | [[04.01 NDFA to DFA - Subset Construction]] | [[PP 04.1 - NDFA to DFA Conversion]] |
| 4 | **RE → NDFA** | 5/5 | 20 | [[08.04 Regular Expression to Finite Automaton]] | [[PP 08 - Arden's Theorem and RE-FA Conversion]] |
| 5 | **Moore ⇄ Mealy** conversions | 5/5 | 20 + 20 | [[05.03 Moore to Mealy Conversion]] · [[05.04 Mealy to Moore Conversion]] | [[PP 05 - Moore and Mealy Machines]] |
| 6 | **DFA design** (4 small DFAs or one scenario) | 5/5 | 40 | [[03.10 Designing DFAs]] | [[PP 03.2 - Acceptability and Designing Automata]] |
| 7 | **LMD and RMD** | 5/5 | 20 | [[07.03 Derivations - Left-most and Right-most]] | [[PP 07.1 - Grammars, Derivations and Ambiguity]] |
| 8 | **Ambiguity** with two parse trees | 5/5 | 20 | [[07.05 Ambiguity]] | [[PP 07.1 - Grammars, Derivations and Ambiguity]] |

## 🔴 Tier 2: in every paper, essay or short-answer style
| # | Topic | Papers | Learn from | Practise with |
|---|---|---|---|---|
| 9 | **Language Processing System** (diagram, each tool's input and output) | 5/5, up to 40 | [[01.03 Language Processing System]] | [[PP 01.1 - Compilers, Translators and the Language Processing System]] |
| 10 | **Phases of a compiler** (diagram, each phase's job, errors) | 5/5, up to 40 | [[01.07 Phases of a Compiler]] | [[PP 01.2 - Phases of a Compiler]] |
| 11 | **Compiler vs interpreter vs assembler** (+ de-compiler) | 4/5 | [[01.05 Compiler, Interpreter, Assembler and De-compiler]] | [[PP 01.1 - Compilers, Translators and the Language Processing System]] |
| 12 | **Reading and writing REs** (strings, language in words, real-world tokens) | 5/5 | [[02.06 Reading and Writing Regular Expressions]] · [[02.05 Regular Expressions for Real-World Tokens]] | [[PP 02 - Regular Expressions]] |
| 13 | **Tokenizing a C program** | 4/5 | [[06.05 Tokenizing a C Program]] | [[PP 06 - Lexical Analysis]] |
| 14 | **Acceptability traces** (the machines are NDFAs) | 4/5, 40 | [[03.09 Acceptability of Strings]] | [[PP 03.2 - Acceptability and Designing Automata]] |
| 15 | **DFA vs NDFA**, formal definitions, role of δ | 5/5 | [[03.07 DFA vs NDFA]] · [[03.04 Formal Definition and the Transition Function]] | [[PP 03.1 - Automata Theory and Terminology]] |
| 16 | **Language of a grammar** + membership proofs | 4/5 | [[07.06 Language of a Grammar and Membership Proofs]] | [[PP 07.1 - Grammars, Derivations and Ambiguity]] |

## 🟡 Medium: 2–3 papers, or part of a Tier 1 question
- Terminology: alphabet, string, length, language, ε → [[03.02 Alphabet, Strings and Languages]]
- Σ\*, Σ⁺, powers of Σ and the proofs → [[03.03 Powers of Sigma, Kleene Star and Kleene Plus]]
- Valid tokens x\*, x?, ranges → [[02.04 Representing Valid Tokens - Repetition and Ranges]]
- RE vs regular grammar vs regular language → [[02.01 Regular Expressions and Lexical Analysis]]
- Operations on languages → [[02.02 Operations on Languages]]
- Moore/Mealy **design** (all of the last 3 papers, so rising) → [[05.05 Designing Mealy and Moore Machines]]
- Compare Moore and Mealy → [[05.02 Mealy vs Moore]]
- FA → RE → [[08.03 Finite Automaton to Regular Expression]]
- RE identities and algebraic proofs → [[08.01 Regular Expression Identities]]
- Symbol table, ICG, optimization, errors the compiler cannot detect → [[01.10 Symbol Table and Error Handler]] · [[01.09 Back-End Phases - Intermediate Code, Optimization, Code Generation]] · [[01.11 Errors a Compiler Cannot Detect]]
- Preprocessor, linker and loader → [[01.04 Preprocessor]] · [[01.06 Linker and Loader]]
- Java FSM question (3 papers, not since 2021/22) → [[PP 03.1 - Automata Theory and Terminology]]

## 🟢 Low: 0–1 papers
- Classification of FA (acceptor, classifier, transducer) → [[03.08 Classification of Finite Automata]]
- Parsing: LL(1), LR(0), top-down vs bottom-up, backtracking (2019/20 only, not in notes) → [[07.11 Parsing Strategies - Top-Down, Bottom-Up, LL(1), LR(0)]]
- Longest match, rule priority, special symbols → [[06.04 Longest Match Rule and Rule Priority]] · [[06.03 Specifications of Tokens and Special Symbols]]
- Left recursion, left factoring, FIRST/FOLLOW alone → [[07.08 Left Recursion and Left Factoring]] · [[07.09 First and Follow Sets]]
- Associativity and precedence alone → [[07.07 Associativity and Precedence]]

## Recommended plan if time is short
| Days left | Focus |
|---|---|
| 3+ | Tier 1 → Tier 2 → one timed paper from [[CMIS 3134 Papers by Year]] |
| 2 | Tier 1 only, plus the LPS and phases diagrams |
| 1 | Arden proof, both minimization procedures, both Moore/Mealy algorithms, subset construction: [[CMIS 3134 Algorithms and Procedures]] and [[CMIS 3134 Final Revision Summary]] |

> [!tip] Exam-room strategy
> Read all 8 questions first (5 minutes). Do the purely mechanical question first, usually the one with Arden + RE→NDFA + subset construction + minimization. Allow about **30 minutes per question**. In a 40-mark part, write the method steps before the working.
