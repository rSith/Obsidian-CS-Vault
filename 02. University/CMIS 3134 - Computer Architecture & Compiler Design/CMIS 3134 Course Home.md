---
type: course
course: CMIS 3134
level: 3
semester: 1
tags: [compilers]
---
# CMIS 3134 — Computer Architecture & Compiler Design

> [!info] Paper format (same in all five past papers)
> 8 questions, answer any **6**, three hours. Every question has parts a–d worth **20 / 20 / 20 / 40** marks.

## Lectures
1. [[3134-01 Introduction to Compilers]] — compiler vs interpreter, language processing system, phases of a compiler, symbol table
2. [[3134-02 Regular Expressions]] — operations on languages, notation, precedence, regular expressions for tokens
3. [[3134-03 Automata]] — finite automata terminology, DFA, NDFA, acceptability
4. [[3134-04 NDFA to DFA Conversion & DFA Minimization]] — subset construction, Myhill-Nerode and Equivalence-theorem minimization
5. [[3134-05 Moore and Mealy Machines]] — Mealy vs Moore, conversions in both directions
6. [[3134-06 Lexical Analysis]] — tokens, lexemes, token categories, longest match
7. [[3134-07 Syntax Analysis]] — CFG, derivations, parse trees, ambiguity, left recursion, First/Follow
8. [[3134-08 Arden's Theorem & RE-FA Conversion]] — Arden's theorem + proof, FA → RE, RE → finite machine, RE identities (handwritten notes only)

> [!warning] Computer Architecture
> All your current material is the compiler half of this course. Add architecture lectures here when you get them.

## Exam prep
- [[3134 Topic Map & Past Papers]] — lecture topic map, every past-paper question mapped (2019/20–2023/24), cross-year analysis

## Study priority (from your exam analysis)
1. **Tier 1:** Arden's theorem → [[3134-08 Arden's Theorem & RE-FA Conversion]] · RE → NDFA → DFA → minimized DFA → [[3134-04 NDFA to DFA Conversion & DFA Minimization]] · Moore ↔ Mealy → [[3134-05 Moore and Mealy Machines]] · DFA construction → [[3134-03 Automata]]
2. **Tier 2:** compiler phases + language processing system → [[3134-01 Introduction to Compilers]] · tokenizing a C program → [[3134-06 Lexical Analysis]] · derivations & ambiguity → [[3134-07 Syntax Analysis]] · RE construction → [[3134-02 Regular Expressions]]
3. **Tier 3:** automata terminology, DFA vs NDFA, CFG proofs
4. **Tier 4:** the 2019/20 parsing block (LL(1), LR(0)), which hasn't been examined since

## Progress
```base
filters:
  and:
    - course == "CMIS 3134"
    - type == "lecture"
views:
  - type: table
    name: Lectures
    order:
      - file.name
      - note.priority
      - note.status
```
