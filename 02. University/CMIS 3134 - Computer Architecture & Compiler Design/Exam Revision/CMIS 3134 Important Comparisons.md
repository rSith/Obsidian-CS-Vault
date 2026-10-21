---
type: revision
course: CMIS 3134
status: complete
tags: [cmis3134, revision, comparisons]
aliases: [3134 comparisons, CMIS 3134 Comparisons]
---
# CMIS 3134 · Important Comparisons

> [!info] How to answer a "compare and contrast" question
> 1. Define both terms in one line each.
> 2. Give **similarities** (1–2 points). "Contrast" alone does not need them, but "compare" does.
> 3. Give a **table of 5–6 differences** on the same criteria.
> 4. Finish with an **example** or a diagram.
>
> Home: [[00. CMIS 3134 Course Overview]] · Definitions: [[CMIS 3134 Important Definitions]]

## 1. Compiler vs Interpreter vs Assembler (vs De-compiler)
Asked in 2019/20 Q1(a), 2020/21 Q1(a), 2021/22 Q1(a), 2023/24 Q2(b) → [[01.05 Compiler, Interpreter, Assembler and De-compiler]]

| Criterion | Compiler | Interpreter | Assembler | De-compiler ⚠ |
|---|---|---|---|---|
| Input → output | High-level → assembly / target code | High-level → executed directly | Assembly → machine code (object file) | Machine/object code → high-level |
| Unit translated | Whole program | One statement at a time | Whole assembly file | Whole binary |
| Errors | Reports all errors after analysis | Stops at the first error | Assembly errors | n/a |
| Object code | Yes | No | Yes (object file) | No (produces source) |
| Speed of the result | Fast execution | Slower execution | Fast | n/a |
| Phases | Six phases, with optimization | Analysis + execution | Close to one-to-one mapping | Reverse engineering |
| Example | gcc, javac | Python, JavaScript | NASM, MASM | Ghidra, JD-GUI |

**Similarity:** all are translators. They let people write in a form other than the binary the hardware understands.

## 2. Linker vs Loader
2021/22 Q2(a) → [[01.06 Linker and Loader]]

| Linker | Loader |
|---|---|
| Part of the tool chain | **Part of the operating system** |
| Input: object files + library files | Input: the executable |
| Output: one **executable** file | Output: program **in memory**, ready to run |
| Resolves references between modules; gives absolute addresses | Calculates size, creates memory space, initializes registers |
| Runs at build time | Runs every time the program is started |

## 3. Front end vs Back end of a compiler
2022/23 Q1(b) → [[01.07 Phases of a Compiler]]

| Front end (analysis) | Back end (synthesis) |
|---|---|
| Lexical, syntax, semantic analysis (+ ICG) | Code optimization, code generation |
| Depends on the **source language** | Depends on the **target machine** |
| Builds the intermediate representation | Produces the target program |

## 4. Lexical vs Syntax vs Semantic analysis
2019/20 Q2(b), 2021/22 Q2(c), 2023/24 Q1(b) → [[01.08 Front-End Phases and Error Detection]]

| | Lexical | Syntax | Semantic |
|---|---|---|---|
| Input | Character stream | Tokens | Parse tree |
| Output | Tokens | Parse tree | Annotated syntax tree |
| Uses | Regular expressions / FA | CFG / push-down automaton | Type rules, symbol table |
| Errors caught | Invalid token (`5ab`, `@`) | Missing `;`, unbalanced `( )` | Type mismatch, undeclared variable |
| Example | `int x = "10" + 5;` passes | passes | **fails**: string + int |

## 5. Errors a compiler catches vs does not catch
2023/24 Q2(d) → [[01.11 Errors a Compiler Cannot Detect]]

| Compile-time (caught) | Not caught by the compiler |
|---|---|
| Lexical, syntax, semantic errors | **Logical** errors (`+` instead of `*`, wrong condition) |
| Program does not compile | **Run-time** errors (divide by zero, null pointer, array out of bounds) |
| Found by the compiler phases | Found by testing and debugging |

## 6. RE vs regular grammar vs regular language
2020/21 Q2(b), 2021/22 Q3(a), 2022/23 Q3(a) → [[02.01 Regular Expressions and Lexical Analysis]]

| Regular expression | Regular grammar | Regular language |
|---|---|---|
| A **notation** for a pattern | A set of **rules** (defined by REs) | The **set of strings** described |
| `(a+b)*abb` | S → aS \| bS \| aA, A → bB, B → b | {abb, aabb, babb, …} |

## 7. x\* vs x⁺ vs x?, and bracket types
→ [[02.04 Representing Valid Tokens - Repetition and Ranges]]

| x\* | x⁺ | x? |
|---|---|---|
| zero or more | one or more | zero or one |
| {ε, x, xx, …} | {x, xx, …} | {ε, x} |

| [0-5] | (5-9) | [1-7) |
|---|---|---|
| inclusive: 0–5 | exclusive: 6, 7, 8 | half-open: 1–6 (state your reading) |

## 8. Σ\* vs Σ⁺
2022/23 Q4(b), 2023/24 Q4(c) → [[03.03 Powers of Sigma, Kleene Star and Kleene Plus]]

| Σ\* (Kleene star / closure) | Σ⁺ (positive closure) |
|---|---|
| $\Sigma^0 \cup \Sigma^1 \cup \dots$ | $\Sigma^1 \cup \Sigma^2 \cup \dots$ |
| **includes ε** | **excludes ε** |
| $\Sigma^* = \Sigma^+ \cup \{\varepsilon\}$ | $\Sigma^+ = \Sigma^* - \{\varepsilon\}$ |

## 9. DFA vs NDFA ★
2019/20 Q3(c), 2020/21 Q3(c), 2022/23 Q5(c), 2023/24 Q5(b)–(c) → [[03.07 DFA vs NDFA]]

| Criterion | DFA | NDFA |
|---|---|---|
| Transition function | $\delta: Q \times \Sigma \rightarrow Q$ | $\delta: Q \times \Sigma \rightarrow 2^Q$ |
| Next state | exactly one | zero, one or many |
| ε-moves | not allowed | allowed |
| Missing transitions | not allowed (every state needs every symbol) | allowed (go to ∅) |
| Acceptance | the single path ends in F | **at least one** path ends in F |
| Design | harder | easier (fewer states, fewer rules) |
| Execution | simple and fast | must track sets of states |
| Space | more states | fewer states |
| Backtracking | allowed | not always possible |
| Power | **same**: every NDFA has an equivalent DFA (subset construction) | |

**Every DFA is an NDFA:** replace each δ(q, a) = p by δ′(q, a) = {p}. Then δ′: Q × Σ → 2^Q, so the DFA satisfies the NDFA definition and accepts the same strings (2023/24 Q5(a)).

## 10. Acceptor vs Classifier vs Transducer
2021/22 Q4(c) → [[03.08 Classification of Finite Automata]]

| Acceptor | Classifier | Transducer |
|---|---|---|
| accept / reject (Boolean) | more than two outcomes, single output at the end | output for each input (Mealy, Moore) |

## 11. Myhill-Nerode vs Equivalence theorem
→ [[04.02 DFA Minimization - Myhill-Nerode Theorem]] · [[04.03 DFA Minimization - Equivalence Theorem]]

| Myhill-Nerode (table filling) | Equivalence theorem (partitioning) |
|---|---|
| Works on **pairs** of states | Works on **groups** (partitions) |
| Triangular table | P0, P1, P2, … |
| Start: mark (final, non-final) pairs | Start: P0 = {F}, {Q − F} |
| Repeat: mark a pair if some input sends it to a marked pair | Repeat: split a group if its states go to different groups on some input |
| Stop: no new marks | Stop: Pk = Pk−1 |
| Merge: unmarked pairs | Merge: states in the same final group |
| Same result | Same result |

## 12. Mealy vs Moore ★
2019/20 Q6(a), 2020/21 Q8(a) → [[05.02 Mealy vs Moore]]

| Criterion | Mealy | Moore |
|---|---|---|
| Output function | $X: Q \times \Sigma \rightarrow O$ | $X: Q \rightarrow O$ |
| Output depends on | current state **and** input | current state **only** |
| Where output is written | on the arrows (`0/1`) | in the states (`q1/0`) |
| Output length for n inputs | **n** | **n + 1** (initial state also outputs) |
| Number of states | fewer | usually more |
| Reaction | same clock cycle | one clock cycle later |
| Hardware | less decoding logic | more decoding logic, more delay |
| Output changes | when input changes (can glitch) | only at clock edges (stable) |

**Similarities:** both are finite automata with output (transducers), 6-tuples, deterministic δ: Q × Σ → Q, and convertible into each other.

## 13. Keyword vs identifier, constant vs literal, operator vs punctuator
→ [[06.02 Tokens, Lexemes and Patterns]]

| Keyword | Identifier | Constant | String literal | Operator | Punctuator |
|---|---|---|---|---|---|
| reserved word | programmer-chosen name | numeric value | quoted text (one token) | computes | separates |
| `int`, `return` | `main`, `age`, `printf` | `25` | `"Max is %d"` | `= + <` | `( ) { } ; ,` |

## 14. Token vs lexeme vs pattern

| Token | Lexeme | Pattern |
|---|---|---|
| class (`<identifier, age>`) | actual text (`age`) | rule (`letter(letter\|digit)*`) |

## 15. Regular grammar vs CFG
→ [[07.01 Why Regular Expressions Are Not Enough]]

| Regular grammar | Context-free grammar |
|---|---|
| Recognised by a finite automaton | Recognised by a push-down automaton |
| Used for tokens | Used for program structure |
| Cannot count / match brackets | Can match balanced brackets |
| Regular ⊂ context-free | |

## 16. LMD vs RMD
→ [[07.03 Derivations - Left-most and Right-most]]

| LMD | RMD |
|---|---|
| replace the left-most non-terminal | replace the right-most non-terminal |
| left-sentential forms | right-sentential forms |
| used by top-down parsers | reverse is used by bottom-up parsers |
| for an unambiguous grammar both give the **same parse tree** | |

## 17. Ambiguous vs unambiguous grammar
2019/20 Q7(a), 2020/21 Q7(b) → [[07.05 Ambiguity]]

| Ambiguous | Unambiguous |
|---|---|
| ≥ 2 parse trees for some string | exactly one parse tree for every string |
| E → E + E \| E \* E \| id | E → E + T \| T, T → T \* F \| F, F → id |
| cannot be detected or removed automatically | precedence and associativity built in |

## 18. Top-down vs bottom-up parsing ⚠
2019/20 Q8(a) → [[07.11 Parsing Strategies - Top-Down, Bottom-Up, LL(1), LR(0)]]

| Top-down | Bottom-up |
|---|---|
| root → leaves | leaves → root |
| LMD | RMD in reverse |
| expand (predict) | shift / reduce |
| recursive descent, LL(1) | LR(0), SLR, LALR, CLR |
| cannot handle left recursion | handles left recursion |
