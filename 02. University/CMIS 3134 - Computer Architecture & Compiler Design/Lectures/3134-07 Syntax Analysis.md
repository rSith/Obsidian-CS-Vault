---
type: lecture
course: CMIS 3134
lecture: 7
status: stub
priority: Tier 2
tags: [compilers]
aliases: [Syntax Analysis, Context-Free Grammar]
---
# Syntax Analysis
> [!info] CMIS 3134 · Lecture 7
> [[CMIS 3134 Course Home]] · Exam prep: [[3134 Topic Map & Past Papers|Topic map]] · Study priority: **Tier 2**
> ← [[3134-06 Lexical Analysis]] | [[3134-08 Arden's Theorem & RE-FA Conversion]] →

> [!tip]- Exam focus (5 papers, 2019/20–2023/24)
> - **Every paper** — Left-most & Right-most Derivations (20 marks)
> - **Every paper** — Ambiguity of a CFG (20 marks)
> - Q8 grammar set `S→aSb│bSa│ab│ba` + derivations for `aababab` — **2022/23 and 2023/24 are effectively identical**.
> - Low priority: left recursion, left factoring, First/Follow sets, associativity/precedence (never asked directly).
> - ⚠️ Parsing block (LL(1), LR(0), top-down vs bottom-up, backtracking) was asked only in 2019/20 and isn't in any notes. It's Tier 4, so skip it unless you have time.

> [!question]- Past paper questions on this lecture (24)
> - **2019/20 Q7(a)** · 20 marks — What is meant by a context-free grammar being **ambiguous**? Explain with an example
> - **2019/20 Q7(b)** · 20 marks — Generate **Left Most and Right Most Derivation** for `(id + id) * id + id` with `E→E+E │ E*E │ (E) │ id`
> - **2019/20 Q7(c)** · 20 marks — Verify whether `S → 0B│1A, A → 0S│1AA│ε, B → 1│1S│0DD` generates `0010101`, `110100101110`
> - **2019/20 Q7(d)** · 40 marks — Construct the **LL(1) / Predictive Parser** for `S→A, A→aDC, C→dC│ε, D→b, E→g` ⚠️
> - **2019/20 Q8(a)** · 20 marks — Compare and contrast **Top-Down Parsers** and **Bottom-Up Parsers** ⚠️
> - **2019/20 Q8(b)** · 20 marks — What is meant by **Back Tracking**? Explain with a suitable example ⚠️
> - **2019/20 Q8(c)** · 30 marks — Develop the **Parser table for LR(0)** for `S→AA, A→aA│b` ⚠️
> - **2019/20 Q8(d)** · 30 marks — Using the Q8(c) parser table, construct the **stack implementation** for input `"aabb"` ⚠️
> - **2020/21 Q7(a)** · 20 marks — Find the derivations of `G = ({S,A,B},{a,b},S,{S→AB, A→aA│a, B→bB│b})`
> - **2020/21 Q7(b)** · 20 marks — What is meant by a CFG being **ambiguous or unambiguous**? Explain with an example
> - **2020/21 Q7(c)** · 20 marks — Generate **Left Most and Right Most Derivations** for `(id + id * id) + (id * id)`
> - **2020/21 Q7(d)** · 40 marks — Verify whether `S→aB│bA, A→aS│bAA│ε, B→b│bS│aBB` generates `aababab`, `bbabaababbba`, `aaaaabbbb`, `abababbbbaaa`
> - **2021/22 Q8(a)** · 20 marks — For `G = ({S},{a,b},S,{S→aSbb │ abb})` find the **language generated**
> - **2021/22 Q8(b)** · 20 marks — Determine whether `S→AB│aaB, A→a│Aa, B→b` is **ambiguous** for the string `"aab"`
> - **2021/22 Q8(c)** · 20 marks — Generate **Left-most and Right-most Derivations** for `"aabbabba"` using `S→aB│bA, S→a│aS│bAA, S→b│aS│aBB`
> - **2021/22 Q8(d)** · 40 marks — Verify whether `S→aB│bA, A→aS│bAA│ε, B→b│bS│aBB` generates `aababab`, `bbabaababbba`, `aaaaabbbb`, `abababbbbaaa`
> - **2022/23 Q8(a)** · 20 marks — For `G = ({S},{a,b},S,{S→aSb│bSa│ab│ba})`: (i) what **language** is generated? (ii) derive the string `"aabbab"`
> - **2022/23 Q8(b)** · 20 marks — For `G = ({S},{a,b},S,{S→aSb│bSa│SS│ε})`: (i) is this grammar **ambiguous** for `"abab"`? (ii) if ambiguous, provide **two distinct parse trees** for `"abab"`
> - **2022/23 Q8(c)** · 20 marks — Generate **Left-most and Right-most Derivations** for `"aababab"` using `G = ({S,A,B},{a,b},S,{S→aB│bA, A→aS│bAA│ε, B→b│bS│aBB})`
> - **2022/23 Q8(d)** · 40 marks — (i) Prove `G = ({S,A,B},{a,b},S,{S→AB│aB, A→a│Aa, B→b})` generates all strings of the form `aⁿb` where n ≥ 1. (ii) Prove `G = ({S},{a,b},S,{S→aSb│bSa│SS│ε})` generates all strings with an **equal number of a's and b's**
> - **2023/24 Q8(a)** · 20 marks — For `G = ({S},{a,b},S,{S→aSb│bSa│ab│ba})`: (i) what **language** is generated? (ii) derive the string `"aabbab"`
> - **2023/24 Q8(b)** · 20 marks — For `G = ({S},{a,b},S,{S→aSb│bSa│SS│ε})`: (i) is this grammar **ambiguous** for `"abab"`? Discuss (ii) provide **two distinct parse trees** for `"abab"`
> - **2023/24 Q8(c)** · 20 marks — Generate **Left-most and Right-most derivations** for `"aababab"` using `G = ({S,A,B},{a,b},S,{S→aB│bA, A→aS│bAA│ε, B→b│bS│aBB})`
> - **2023/24 Q8(d)** · 40 marks — (i) Prove `G = ({S,A,B},{a,b},S,{S→AB│aB, A→a│Aa, B→b})` generates all strings of the form `aⁿb`, n ≥ 1. (ii) Prove `G = ({S},{a,b},S,{S→aSb│bSa│SS│ε})` generates all strings with an **equal number of a's and b's**

## Topic checklist
- [ ] **7.1 Syntax Analysis / Parsing** — Second phase; why REs are insufficient (cannot check balancing tokens such as parentheses); uses **CFG**, recognized by push-down automata; CFG is a **superset** of Regular Grammar
- [ ] **7.2 Context-Free Grammar** — Four components: non-terminals **V** (syntactic variables), terminal symbols **Σ** (tokens), productions **P** (LHS non-terminal, arrow, RHS sequence), start symbol **S**; derivation by repeatedly replacing a non-terminal
- [ ] **7.3 CFG worked example** — Palindrome language `L = {w │ w = wR}` is not regular but is context-free; `G = (V, Σ, P, S)` with `V={Q,Z,N}`, `Σ={0,1}`, `P={Q→Z│Q→N│Q→ℇ│Z→0Q0│N→1Q1}`
- [ ] **7.4 Syntax Analyzers / Parsers** — Input = token stream from lexical analyzer; analyses against production rules; output = parse tree; parses whole code even with errors; uses error-recovery strategies
- [ ] **7.5 Derivation** — Sequence of production rules to obtain the input string; two decisions — which non-terminal to replace, and which production to use
    - [ ] 7.5.1 Left-most Derivation — Sentential form scanned/replaced left to right → left-sentential form
    - [ ] 7.5.2 Right-most Derivation — Scanned/replaced right to left → right-sentential form
    - [ ] 7.5.3 Worked example — `E→E+E │ E→E*E │ E→id` for input `id + id * id`, both derivations shown step by step
- [ ] **7.6 Parse Tree** — Graphical depiction of a derivation; start symbol = root; 5-step construction example for `a + b * c`; all leaves terminal, all interior nodes non-terminal, in-order traversal gives the input string; depicts associativity & precedence (deepest sub-tree traversed first)
- [ ] **7.7 Ambiguity** — Grammar G ambiguous if more than one parse tree (left or right derivation) for at least one string; example `E→E+E │ E→E–E │ E→id` with `id + id – id`; inherently ambiguous languages; ambiguity is bad for compiler construction; no automatic detection/removal — rewrite grammar, or set associativity & precedence
- [ ] **7.8 Associativity** — Operand with operators on both sides; left-associative `(id op id) op id` — addition, multiplication, subtraction, division; right-associative `id op (id op id)` — exponentiation
- [ ] **7.9 Precedence** — Two different operators sharing an operand; `2+3*4` → `2 + (3*4)`; reduces ambiguity
- [ ] **7.10 Left Recursion** — Non-terminal 'A' whose derivation has 'A' as left-most symbol; problematic for top-down parsers (infinite loop); **immediate** `A => Aα │ β`; **indirect** `S => Aα │ β, A => Sd`
- [ ] **7.11 Removal of Left Recursion** — `A => Aα│β` becomes `A => βA'`, `A' => αA'│ε`; plus the general algorithm eliminating all direct and indirect left recursion; worked example
- [ ] **7.12 Left Factoring** — Used when productions share a common prefix; `A ⟹ αβ │ α𝜸` becomes `A => αA'`, `A' => β │ 𝜸`; one production per prefix
- [ ] **7.13 First and Follow Sets** — Needed for parser-table construction; decision of `T[A, t] = α`
    - [ ] 7.13.1 First Set — Terminal derived in the first position by a non-terminal; algorithm: if α terminal → FIRST(α)={α}; if α→ℇ → FIRST(α)={ℇ}; if α→𝜸1…𝜸n and any FIRST(𝜸) contains t then t ∈ FIRST(α)
    - [ ] 7.13.2 Follow Set — Terminal immediately following a non-terminal; if α is start symbol → FOLLOW()= $; for α→AB, FIRST(B) ⊆ FOLLOW(A) except ℇ; if B ⟹ ℇ then FOLLOW(α) ⊆ FOLLOW(A)
- [ ] **7.14 Limitations of Syntax Analyzers** — Cannot determine whether a token is valid, declared before use, initialized before use, or whether an operation on a token type is valid — these belong to Semantic Analysis

## Notes
*Work through the checklist above. Write each topic in your own words, then tick it off.*


## Key definitions
> [!note] Term
>

## Summary (in my own words)


## Open questions
- [ ]
