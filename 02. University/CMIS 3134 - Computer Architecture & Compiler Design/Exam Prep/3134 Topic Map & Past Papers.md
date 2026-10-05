---
type: exam-prep
course: CMIS 3134
status: done
tags: [compilers, exam-prep]
---
> [!info] Exam prep · [[CMIS 3134 Course Home]]

# CMIS 3134 — Computer Architecture & Compiler Design
## Complete Lecture-Note Topic Map + Past-Paper Question Mapping

**Wayamba University of Sri Lanka — Faculty of Applied Sciences**
**Department of Computing and Information Systems — Level 3, Semester I**

Prepared: 20 September 2026
Source material analysed: 9 lecture-note PDFs + 5 past papers (2019/2020 → 2023/2024)

---

## 0. Source Inventory & Reference Codes

Throughout this document the following short codes are used in the mapping tables.

| Code | File | Pages | Type | Title |
|---|---|---|---|---|
| **LN1** | `1Introduction to Compilers.pdf` | 5 | Typed | Introduction to Compilers (Theory Notes: 1) |
| **LN2** | `2. Regular Expressions.pdf` | 4 | Typed | Regular Expressions (Theory Notes: 2) |
| **LN3** | `3. Automata.pdf` | 7 | Typed | Automata (Theory Notes: 3) |
| **LN4** | `4. NDFA to DFA Conversion & DFA Minimization.pdf` | 8 | Typed | NDFA→DFA Conversion & DFA Minimization (Theory Notes: 4) |
| **LN5** | `5. Moore and Mealy Machines.pdf` | 7 | Typed | Moore and Mealy Machines (Theory Notes: 5) |
| **LN6** | `6. Lexical Analysis.pdf` | 3 | Typed | Lexical Analysis (Theory Notes: 6) |
| **LN7** | `7. Syntax Analysis.pdf` | 11 | Typed | Syntax Analysis (Theory Notes: 7) |
| **HN-A** | `compier note.pdf` | 10 | Handwritten (scanned) | Summary notes covering LN1–LN3 material + DFA practice |
| **HN-B** | `Compiler note.pdf` | 42 | Handwritten (scanned) | Extended notes: DFA/NDFA, minimization, Moore/Mealy, RE identities, **Arden's Theorem**, RE→FM, grammars, parse trees |

### Past papers

| Code | File | Academic Year | Held | Pages | Questions |
|---|---|---|---|---|---|
| **P19/20** | `CMIS 3134  20192020.pdf` | 2019/2020 | June 2022 | 5 | 8 (answer 6) |
| **P20/21** | `CMIS 3134  20202021.pdf` | 2020/2021 | June/July 2023 | 5 | 8 (answer 6) |
| **P21/22** | `CMIS 3134  20212022.pdf` | 2021/2022 | Jan/Feb 2024 | 4 | 8 (answer 6) |
| **P22/23** | `CMIS 3134  20222023.pdf` | 2022/2023 | Feb/March 2025 | 4 | 8 (answer 6) |
| **P23/24** | `CMIS 3134 2023 - 2024 feb 2026.pdf` | 2023/2024 | February 2026 | 4 | 8 (answer 6) |

> ⚠️ **Important note on the 2021/2022 file:** that PDF is not a single paper — it is a **54-page bundle of every Level 3 Semester I paper** for Jan/Feb 2024 (CMIS 3114, CMIS 3134, CMIS 3142, CMIS 3153, ELTN, IMGT, MATH, MMOD, STAT…). The CMIS 3134 paper occupies only pages 4–7 of that bundle. Everything mapped below is from the correct CMIS 3134 section.

**Paper format (identical across all five years):** 8 questions, answer any 6, three hours. Every question has parts a)–d) worth **20 / 20 / 20 / 40 marks** respectively (total 100 per question).

---

# PART 1 — Lecture Note Topic & Subtopic Structure

## LN1 — Introduction to Compilers *(Theory Notes: 1)*

> 📝 **My notes:** [[3134-01 Introduction to Compilers]]

| # | Topic | Subtopics |
|---|---|---|
| 1.1 | **What is a Compiler** | Translates one language to another without changing meaning; target code must be efficient & optimized in time and space |
| 1.2 | **Compiler Design Principles** | Translation & optimization process; error detection & recovery; **front end** = lexical + syntax + semantic analysis; **back end** = code generation + optimization |
| 1.3 | **Hardware / Software Relationship** | Hardware understands electronic charge ≡ binary; binary alphabet {0, 1}; why writing binary directly is impractical |
| 1.4 | **Language Processing System** | Full pipeline: Source Code → Preprocessor → Compiler → Assembler → Linker → Loader → Memory; inputs and outputs at each stage |
| 1.5 | **Execution of a C program on a host machine** | 5 steps: (1) user writes C, (2) compiler → assembly, (3) assembler → machine code/object, (4) linker → executable, (5) loader → memory & execute |
| 1.6 | **Preprocessor** | Macro-processing, augmentation, file inclusion, language extension |
| 1.7 | **Interpreter** | Statement-by-statement translation & execution; stops at first error — contrasted with compiler (whole program, many passes, reports all errors) |
| 1.8 | **Assembler** | Assembly → machine code; output = object file (machine instructions + placement data) |
| 1.9 | **Linker** | Merges object files into an executable; locates referenced modules/routines; resolves absolute references |
| 1.10 | **Loader** | Part of OS; loads executable into memory; calculates program size; creates memory space; initializes registers |
| 1.11 | **Phases of a Compiler** | Sequence where each phase consumes previous phase's output |
| 1.11.1 | → Lexical Analysis | Text scanner; character stream → lexemes → tokens `<token-name, attribute-value>` |
| 1.11.2 | → Syntax Analysis | Tokens → parse tree / syntax tree; token arrangements checked against source-code grammar |
| 1.11.3 | → Semantic Analysis | Checks parse tree follows language rules; type compatibility; identifier declaration-before-use; output = annotated syntax tree |
| 1.11.4 | → Intermediate Code Generation | Code for an abstract machine; between high-level and machine language; eased translation to target |
| 1.11.5 | → Code Optimization | Removes unnecessary code lines; re-arranges statement sequence; speeds execution without wasting CPU/memory |
| 1.11.6 | → Code Generation | Optimized intermediate code → target machine language; re-locatable machine code |
| 1.11.7 | → Symbol Table | Data structure maintained across **all** phases; identifier names + types; fast search/retrieval; scope management |

## LN2 — Regular Expressions *(Theory Notes: 2)*

> 📝 **My notes:** [[3134-02 Regular Expressions]]

| # | Topic | Subtopics |
|---|---|---|
| 2.1 | **Role in Lexical Analysis** | Scans/identifies a finite set of valid strings/tokens/lexemes; searches for the pattern defined by language rules |
| 2.2 | **Regular Grammar & Regular Language** | Grammar defined by REs = *regular grammar*; language defined by regular grammar = *regular language*; RE as recursive definition; efficient implementation |
| 2.3 | **Operations on Languages** | **Union** `L ∪ M = {s │ s in L or s in M}`; **Concatenation** `LM = {st │ s in L and t in M}`; **Kleene Closure** `L*` = zero or more occurrences |
| 2.4 | **Notations** | Union `(r)│(s)`; Concatenation `(r)(s)`; Kleene closure `(r)*`; grouping `(r)` |
| 2.5 | **Precedence & Associativity** | `*`, concatenation `.`, `│` are left-associative; `*` highest precedence; `.` second; `│` lowest |
| 2.6 | **Representing valid tokens** | `x*` = zero or more `{ε, x, xx, …}`; `x+` = one or more `= x.x*`; `x?` = at most one `{x}` or `{ε}`; `[a-z]`, `[A-Z]`, `[0-9]` |
| 2.7 | **Representing occurrence of symbols** | `letter = [a–z]` or `[A–Z]`; `digit = 0│1│…│9` or `[0-9]`; `sign = [ + │ - ]` |
| 2.8 | **Representing language tokens** | `Decimal = (sign)?(digit)+`; `Identifier = (letter)(letter │ digit)*`; finite automata used to verify RE validity |
| 2.9 | **Recursive definition of a RE** | `ε` (L(ε)={ε}); `φ` (L(φ)={}); `x` (L={x}); `X + Y` → L(X)∪L(Y); `X . Y` → L(X).L(Y); `R*` → (L(R))*; repeated application |
| 2.10 | **Worked RE ⇄ Regular Set examples** | `(0+10*)`, `(0*10*)`, `(0+ε)(1+ε)`, `(a+b)*`, `(a+b)*abb`, `(11)*`, `(aa)*(bb)*b`, `(aa+ab+ba+bb)*` |

## LN3 — Automata *(Theory Notes: 3)*

> 📝 **My notes:** [[3134-03 Automata]]

| # | Topic | Subtopics |
|---|---|---|
| 3.1 | **What is an Automaton** | From Greek *αὐτόματα* = "self-acting"; abstract self-propelled computing device following a predetermined sequence of operations |
| 3.2 | **Finite Automaton (FA) / FSM** | An automaton with a finite number of states |
| 3.3 | **Formal definition of a FA** | 5-tuple `(Q, ∑, δ, q0, F)` — Q states, ∑ alphabet, δ transition function, q0 initial state, F ⊆ Q final states |
| 3.4 | **Related terminologies** | |
| 3.4.1 | → Alphabet | Any finite set of symbols; e.g. ∑ = {a, b, c, d} |
| 3.4.2 | → String | Finite sequence of symbols from ∑; e.g. 'cabcad' |
| 3.4.3 | → Length of a String | `│S│` = number of symbols; `│S│ = 0` → empty string (λ or ε) |
| 3.4.4 | → Kleene Star `∑*` | Unary operator; infinite set of all strings of all lengths **including λ**; `∑* = ∑⁰ ∪ ∑¹ ∪ ∑² ∪ …` |
| 3.4.5 | → Kleene Closure / Plus `∑+` | All strings of all lengths **excluding λ**; `∑+ = ∑* − {λ}` |
| 3.4.6 | → Language | A subset of ∑* for some alphabet ∑; finite or infinite |
| 3.5 | **Types of Finite Automata** | DFA and NDFA/NFA |
| 3.6 | **Deterministic Finite Automaton (DFA)** | For each input symbol the next state is determinable; formal 5-tuple with `δ: Q × ∑ → Q` |
| 3.7 | **Graphical representation of a DFA** | Digraph/state diagram; vertices = states; labelled arcs = transitions; initial state = empty single incoming arc; final state = double circles; worked example + transition table |
| 3.8 | **Non-deterministic Finite Automaton (NDFA)** | Machine can move to any *combination* of states; formal 5-tuple with `δ: Q × ∑ → 2^Q` (power set); worked example + transition table |
| 3.9 | **DFA vs NDFA comparison** | Single vs multiple next states; ε-transitions not in DFA / permitted in NDFA; backtracking allowed in DFA / not always in NDFA; DFA more space / NDFA less; acceptance: DFA transits to a final state vs NDFA at least one path ends in a final state |
| 3.10 | **Acceptors, Classifiers, Transducers** | **Acceptor/Recognizer** — computes a Boolean function, all states accept or reject; **Classifier** — more than two final states, single output on termination; **Transducer** — produces output from current input and/or previous state → **Mealy** (state + input) and **Moore** (state only) |
| 3.11 | **Acceptability by DFA and NDFA** | Accepted iff `δ*(q0, S) ∈ F`; language `L = {S │ S ∈ ∑* and δ*(q0,S) ∈ F}`; not accepted iff `δ*(q0,S′) ∉ F`; complement language L′; worked example of accepted/rejected strings |

## LN4 — NDFA to DFA Conversion & DFA Minimization *(Theory Notes: 4)*

> 📝 **My notes:** [[3134-04 NDFA to DFA Conversion & DFA Minimization]]

| # | Topic | Subtopics |
|---|---|---|
| 4.1 | **NDFA → DFA: problem statement** | Given `X = (Qx, ∑, δx, q0, Fx)` accepting L(X), design `Y = (Qy, ∑, δy, q0, Fy)` with L(Y) = L(X) |
| 4.2 | **NDFA → DFA algorithm (6 steps)** | 1 Create state table from NDFA · 2 Create blank state table for the DFA · 3 Mark start state q0 · 4 Find combination of states {Q0…Qn} per input alphabet · 5 Re-apply step 4 for each newly generated DFA state · 6 Any state containing an NDFA final state is a DFA final state |
| 4.3 | **Worked NDFA→DFA example** | NDFA table with `∅` entries → DFA table with composite states `[a,b,c,d,e]`, `[d,e]`, `[b,d,e]`, `[c,e]` → state diagram |
| 4.4 | **DFA Minimization — Myhill-Nerode Theorem** | Step 1 table of all state pairs (Qi, Qj), unmarked · Step 2 mark pairs where Qi ∈ F and Qj ∉ F (or vice versa) · Step 3 repeat: mark unmarked (Qi,Qj) if `{δ(Qi,A), δ(Qj,A)}` is marked for some input · Step 4 combine remaining unmarked pairs into single states |
| 4.5 | → Worked Myhill-Nerode example | 6-state DFA {a…f}; transitive marking of (a,f) and (b,f); unmarked {a,b}, {c,d}, {c,e}, {d,e} recombined to {c,d,e}; final minimized DFA = {f}, {a,b}, {c,d,e} |
| 4.6 | **DFA Minimization — Equivalence Theorem** | Two states combinable into {X,Y} if **not distinguishable**; distinguishable iff ∃ string S with exactly one of δ(X,S), δ(Y,S) accepting; DFA minimal iff all states distinguishable |
| 4.7 | → Equivalence algorithm (Algorithm 3) | Step 1 partition Q into final / non-final = P0 (0-equivalent), counter k=0 · Step 2 increment k; split each partition where states are k-distinguishable · Step 3 repeat while Pk ≠ Pk−1 · Step 4 combine kth equivalent sets as new states |
| 4.8 | → Worked Equivalence example | 6-state DFA; `P0 = {(c,d,e),(a,b,f)}` → `P1 = {(c,d,e),(a,b),(f)}` → `P2 = P1` → 3-state reduced DFA + transition table |

## LN5 — Moore and Mealy Machines *(Theory Notes: 5)*

> 📝 **My notes:** [[3134-05 Moore and Mealy Machines]]

| # | Topic | Subtopics |
|---|---|---|
| 5.1 | **Finite automata with output** | Two FSM types that generate output: Mealy and Moore |
| 5.2 | **Mealy Machine** | Output depends on **present state AND present input**; 6-tuple `(Q, ∑, O, δ, X, q0)`; `δ: Q × ∑ → Q`; output function `X: Q × ∑ → O`; state table (Next state + Output per input); state diagram |
| 5.3 | **Moore Machine** | Output depends on **present state only**; 6-tuple `(Q, ∑, O, δ, X, q0)`; output function `X: Q → O`; state table (Next state per input + single Output column); state diagram |
| 5.4 | **Mealy vs Moore comparison** | Output dependence; Mealy generally fewer states / Moore more; output function of transitions vs of current state at clock edges; Mealy reacts in same clock cycle / Moore one cycle later (more decode logic, more circuit delay) |
| 5.5 | **Moore → Mealy conversion (Algorithm 4)** | Step 1 blank Mealy table format · Step 2 copy all Moore transition states · Step 3 for state Qi with output m, copy m into the output column wherever Qi appears as a *next state*; worked example |
| 5.6 | **Mealy → Moore conversion (Algorithm 5)** | Step 1 count distinct outputs per state Qi · Step 2 if all outputs same, keep Qi; if n distinct outputs, split into Qi0…Qin · Step 3 if the initial state's output is 1, insert a new initial state giving output 0; worked example (b→b0,b1; c→c0,c1) |

## LN6 — Lexical Analysis *(Theory Notes: 6)*

> 📝 **My notes:** [[3134-06 Lexical Analysis]]

| # | Topic | Subtopics |
|---|---|---|
| 6.1 | **Lexical Analysis — role** | First phase; takes modified source from preprocessor; breaks syntaxes into a series of tokens; removes whitespace and comments; generates error on invalid token; works closely with syntax analyzer; passes data on demand |
| 6.2 | **Tokens & Lexemes** | Lexeme = sequence of (alphanumeric) characters in a token; predefined rules per lexeme; rules defined by grammar as a **pattern**; patterns defined by regular expressions |
| 6.3 | **Token categories** | Keywords, constants, identifiers, strings, numbers, operators, punctuation symbols |
| 6.4 | → Worked example | `int value = 100;` → `int` (keyword), `value` (identifier), `=` (operator), `100` (constant), `;` (symbol) |
| 6.5 | **Specifications of Tokens** | |
| 6.5.1 | → Alphabets | Binary {0,1}; Hexadecimal {0–9,A–F}; English {a-z, A-Z} |
| 6.5.2 | → Strings | Finite sequence of alphabets; length = occurrences; `│Lexical│ = 7`; empty string ε |
| 6.5.3 | → Special symbols | Arithmetic `+ - % * /`; Punctuation `, ; . ->`; Assignment `=`; Special assignment `+= /= *= -=`; Comparison `== != < <= > >=`; Preprocessor `#`; Location specifier `&`; Logical `& && │ ││ !`; Shift `>> >>> << <<<` |
| 6.5.4 | → Language | Finite set of strings over a finite set of alphabets; set operations apply; describable by regular expressions |
| 6.6 | **Longest Match Rule** | Scans letter by letter; word completes at whitespace/operator/special symbol; lexeme determined by longest match among available tokens; e.g. `int intvalue;` |
| 6.7 | **Rule Priority** | Reserved words/keywords take priority over user input |

## LN7 — Syntax Analysis *(Theory Notes: 7)*

> 📝 **My notes:** [[3134-07 Syntax Analysis]]

| # | Topic | Subtopics |
|---|---|---|
| 7.1 | **Syntax Analysis / Parsing** | Second phase; why REs are insufficient (cannot check balancing tokens such as parentheses); uses **CFG**, recognized by push-down automata; CFG is a **superset** of Regular Grammar |
| 7.2 | **Context-Free Grammar** | Four components: non-terminals **V** (syntactic variables), terminal symbols **Σ** (tokens), productions **P** (LHS non-terminal, arrow, RHS sequence), start symbol **S**; derivation by repeatedly replacing a non-terminal |
| 7.3 | → CFG worked example | Palindrome language `L = {w │ w = wR}` is not regular but is context-free; `G = (V, Σ, P, S)` with `V={Q,Z,N}`, `Σ={0,1}`, `P={Q→Z│Q→N│Q→ℇ│Z→0Q0│N→1Q1}` |
| 7.4 | **Syntax Analyzers / Parsers** | Input = token stream from lexical analyzer; analyses against production rules; output = parse tree; parses whole code even with errors; uses error-recovery strategies |
| 7.5 | **Derivation** | Sequence of production rules to obtain the input string; two decisions — which non-terminal to replace, and which production to use |
| 7.5.1 | → Left-most Derivation | Sentential form scanned/replaced left to right → left-sentential form |
| 7.5.2 | → Right-most Derivation | Scanned/replaced right to left → right-sentential form |
| 7.5.3 | → Worked example | `E→E+E │ E→E*E │ E→id` for input `id + id * id`, both derivations shown step by step |
| 7.6 | **Parse Tree** | Graphical depiction of a derivation; start symbol = root; 5-step construction example for `a + b * c`; all leaves terminal, all interior nodes non-terminal, in-order traversal gives the input string; depicts associativity & precedence (deepest sub-tree traversed first) |
| 7.7 | **Ambiguity** | Grammar G ambiguous if more than one parse tree (left or right derivation) for at least one string; example `E→E+E │ E→E–E │ E→id` with `id + id – id`; inherently ambiguous languages; ambiguity is bad for compiler construction; no automatic detection/removal — rewrite grammar, or set associativity & precedence |
| 7.8 | **Associativity** | Operand with operators on both sides; left-associative `(id op id) op id` — addition, multiplication, subtraction, division; right-associative `id op (id op id)` — exponentiation |
| 7.9 | **Precedence** | Two different operators sharing an operand; `2+3*4` → `2 + (3*4)`; reduces ambiguity |
| 7.10 | **Left Recursion** | Non-terminal 'A' whose derivation has 'A' as left-most symbol; problematic for top-down parsers (infinite loop); **immediate** `A => Aα │ β`; **indirect** `S => Aα │ β, A => Sd` |
| 7.11 | → Removal of Left Recursion | `A => Aα│β` becomes `A => βA'`, `A' => αA'│ε`; plus the general algorithm eliminating all direct and indirect left recursion; worked example |
| 7.12 | **Left Factoring** | Used when productions share a common prefix; `A ⟹ αβ │ α𝜸` becomes `A => αA'`, `A' => β │ 𝜸`; one production per prefix |
| 7.13 | **First and Follow Sets** | Needed for parser-table construction; decision of `T[A, t] = α` |
| 7.13.1 | → First Set | Terminal derived in the first position by a non-terminal; algorithm: if α terminal → FIRST(α)={α}; if α→ℇ → FIRST(α)={ℇ}; if α→𝜸1…𝜸n and any FIRST(𝜸) contains t then t ∈ FIRST(α) |
| 7.13.2 | → Follow Set | Terminal immediately following a non-terminal; if α is start symbol → FOLLOW()= $; for α→AB, FIRST(B) ⊆ FOLLOW(A) except ℇ; if B ⟹ ℇ then FOLLOW(α) ⊆ FOLLOW(A) |
| 7.14 | **Limitations of Syntax Analyzers** | Cannot determine whether a token is valid, declared before use, initialized before use, or whether an operation on a token type is valid — these belong to Semantic Analysis |

## HN-A — Handwritten Summary Notes *(`compier note.pdf`, 10 pages)*

> 📝 **My notes:** [[3134-01 Introduction to Compilers]] · [[3134-02 Regular Expressions]] · [[3134-03 Automata]]

Mirrors and condenses LN1–LN3, with extra worked practice.

| # | Topic | Subtopics |
|---|---|---|
| A.1 | Compiler definition & purpose | Compiler → Assembly → (Assembler) → Machine Language; efficiency/optimization |
| A.2 | Language Processing System diagram | Source Code → Pre-processor → Compiler → Assembler → Linker (↔ library files, relocatable modules) → Loader → Memory |
| A.3 | Compiler phases diagram | Source Code → Lexical → Syntax → Semantic → Intermediate Code Generator → Machine-Independent Code Optimiser → Code Generator → Machine-Dependent Code Optimiser; with **Error Handler** and **Symbol Table** spanning all phases |
| A.4 | Lexical Analysis — **7 types of tokens** | Special characters, Identifiers (method names / variable names), Operators, Constants, Keywords (void, double, int, return), Punctuators `( ) { } ;`, Literals |
| A.5 | → Worked tokenization | Full `int main(){ int x, a=2, b=3, c=5; x = a+b*c; printf("The value is %d", x); }` tokenized as `<keyword, int>`, `<identifier, main>`, `<punctuator, (>` … |
| A.6 | Syntax / Semantic / ICG / Optimization / Code Generation / Symbol Table | One-paragraph definitions of each phase |
| A.7 | Regular Expressions | Regular grammar/language; Union, Concatenation, Kleene closure; notations |
| A.8 | Representing tokens | `x*`, `x+`, `x?`; `[0,1,2,3,4,5]` inclusive vs `(0,…,5)` exclusive; `[0-9]` range |
| A.9 | Language token REs | `sign=[+│-]`, `digit=[0-9]`, `letter=[a-z]│[A-Z]`; `Decimal=(sign)?(digit)+`; `Identifier=(letter)(letter│digit)*` and `(letter)(letter│digit│_)*` |
| A.10 | **Real-world RE examples** | **NIC** = 9 digits + `(V│X)`; **Telephone (mobile)** = `(+94)?(0)(1│2│…│8)(digit)⁷`; **Index/registration numbers** |
| A.11 | Automata | "Self-acting"; can decide if a Java program is valid but **cannot** identify an infinite loop |
| A.12 | Formal definition of FA | Q, ∑, δ, q0, F |
| A.13 | **Classification tree of Finite Automata** | FA **without output** → DFA, NDFA/NFA · FA **with output** → Mealy, Moore |
| A.14 | Alphabet / String / Length | ∑={a,b,c,d}; "az" invalid; `∑0` = length-0 strings |
| A.15 | **Power of sigma** `∑ⁿ` and `∑*`, `∑+` | `∑⁰={ε}`, `∑¹={a,b}`, `∑²={aa,ab,ba,bb}` …; `∑* = ∑⁰∪∑¹∪∑²∪…`; `∑+ = ∑* − λ` |
| A.16 | DFA graphical representation | Vertices/arcs/initial/final conventions; example transition table |
| A.17 | **Validation rules of a DFA** | # of states ≥ 1 · exactly 1 initial state · final states one or more · every state must have a transition for **all** inputs · no multiple transitions for one input |
| A.18 | DFA construction practice | Strings starting with '0'; starting with '0' and length = 2; starting with 0 **or** length 2; even # of 1's; containing "aabb"; **not** containing "aabb" |

## HN-B — Handwritten Extended Notes *(`Compiler note.pdf`, 42 pages)*

> 📝 **My notes:** [[3134-08 Arden's Theorem & RE-FA Conversion]]

**This is the only source covering Arden's Theorem, RE→Finite Machine, FA→RE and the RE algebraic identities — all of which are examined every year.**

| # | Topic | Subtopics |
|---|---|---|
| B.1 | DFA fundamentals | State diagram anatomy; 5-tuple; transition-function table; validation rules |
| B.2 | DFA construction practice | Start with 0; start with 0 and length 2; even/odd number of 1s; contains "aabb"; does not contain "aabb" |
| B.3 | NDFA | Definition; `δ: Q × ∑ → 2^Q`; number of states = `2^Q`; **DFA vs NDFA** comparison; "Every DFA is an NDFA, but every NDFA is not a DFA" |
| B.4 | NDFA construction practice | Start with 10; contain 10; end with 10; ends with 1 |
| B.5 | **NDFA → DFA conversion** | Subset-construction worked repeatedly: 1st-step table (sets), 2nd-step table (renamed states), **dead state** handling, resulting diagram |
| B.6 | **DFA Minimization — Equivalence method** | Equivalent states `δ(A,x)→F ∧ δ(B,x)→F`, or `δ(A,x)↛F ∧ δ(B,x)↛F`; **types of equivalence**: 0th (│x│=0), 1st (│x│=1), nth (│x│=n); multiple full worked examples with successive partitions until `Pk = Pk−1` |
| B.7 | **DFA Minimization — Myhill-Nerode Theorem** | 4-step algorithm; upper-triangular pair table; marking final vs non-final; transitive marking; combining unmarked pairs; worked examples with 5- and 6-state DFAs |
| B.8 | **Finite machines with output** | Side-by-side **Mealy** `(Q, I, Δ, δ, λ, q0)` with `λ: Q × I → Δ` vs **Moore** `(Q, I, Δ, δ, λ, q0)` with `λ: Q → Δ`; **length of output = length of input** (Mealy) vs **length of input + 1** (Moore); worked trace of input `1101` on both |
| B.9 | **Construction of Mealy machines** | 1's complement of a binary string; print 'a' whenever sequence `01` encountered; string ending with `aa` or `bb`; **2's complement** |
| B.10 | **Construction of Moore machines** | Print 'a' when sequence `01` encountered; count occurrences of substring `abb` |
| B.11 | **Mealy → Moore conversion** | State-splitting by distinct outputs (`B → Ba, Bb`; `C → Ca, Cb`); full transition-table method |
| B.12 | **Moore → Mealy conversion** | Output migrated onto incoming transitions; worked tables |
| B.13 | **Regular Expression algebraic identities** | `φ + R = R` · `φR = Rφ = φ` · `εR = Rε = R` · `ε* = ε`, `φ* = ε` · `R + R = R` · `R*R* = R*` · `RR* = R*R = R+` · `(R*)* = R*` · `ε + R*R = R*` · `(PQ)*P = P(QP)*` · `(P+Q)* = (P*Q*)* = (P*+Q*)*` · `(P+Q)R = PR + QR` |
| B.14 | → RE simplification worked examples | `(1+00*1)+(1+00*1)(0+10*1)*(0+10*1) = 0*1(0+10*1)*`; length-exactly-2 and length-at-most-2 languages over {a,b} reduced to `(a+b)(a+b)` and `(ε+a+b)(ε+a+b)` |
| B.15 | **Arden's Theorem** | *If P and Q are two REs over ∑ and P does not contain ε, then `R = Q + RP` has the unique solution `R = QP*`*; **full algebraic proof** by repeated substitution |
| B.16 | → **FA → Regular Expression** | Writing state equations `q1 = ε + q1a …`, substitution, applying Arden's theorem, extracting the RE for the final state; multiple worked examples |
| B.17 | **RE → Finite Machine construction rules** | Rule 1 `(a+b)` · Rule 2 `(a.b)` · Rule 3 `a*` · Rule 4 `a+`; worked examples `ba*b`, `(a+b)c`, `a(bc)*`, `(a+b)*(abb + a*b)`, `10+(0+11)0*1` |
| B.18 | **Grammar** | `G = {V, T, S, P}` — variables, terminals, start symbol, production rules; worked derivation of language `ab` |
| B.19 | **Regular Grammar** | **Left linear** (`A → Bx`… variable first) vs **right linear** (`A → xB`); `S = abS │ b` examples |
| B.20 | → Language from grammar | `L = {aⁿbⁿ : n ≥ 0}`; `L = {ab, aab, aaabbb, …}`; step-by-step derivations |
| B.21 | **Derivation & Derivation (Parse) Tree** | Ordered rooted tree graphically representing derivation from a CFG; root/vertex/leaf structure; worked trees |
| B.22 | **Left-most and Right-most derivation trees** | Side-by-side leftmost vs rightmost derivation trees for the same input |
| B.23 | **Ambiguity** | `E→E+E │ E–E │ a│b` with input `a−b+a` giving two distinct parse trees; ambiguous vs non-ambiguous grammar definitions |

---

# PART 2 — Past Paper Question → Topic Mapping

> **Legend** — "Lecture Note(s)" column gives the primary source first, supporting sources after. Section numbers refer to Part 1 above.
> ⚠️ marks a question whose topic is **not covered** in the typed lecture notes LN1–LN7.

---

## 2.1 — Past Paper 2019/2020 *(held June 2022, 5 pages, 8 questions)*

| Q | Part | Marks | Question (abbreviated) | Topic → Subtopic | Lecture Note(s) |
|---|---|---|---|---|---|
| **1** | a | 20 | Compare and contrast **Compiler** and **Assembler** | Introduction to Compilers → Compiler / Assembler | **LN1** §1.1, §1.8 · HN-A A.1 |
| 1 | b | 20 | Perform a **lexical analyzer** to generate tokens of the given C program (`int main(){int a=20,b=30; if(a<b)…}`) | Lexical Analysis → Tokens, token categories | **LN6** §6.2–6.4 · LN1 §1.11.1 · HN-A A.4–A.5 |
| 1 | c | 20 | Briefly explain the use of having a **Symbol Table** in compilers | Phases of a Compiler → Symbol Table | **LN1** §1.11.7 · HN-A A.6 |
| 1 | d | 40 | Discuss the workflow of the **Language Processing System** with its inputs and outputs | Language Processing System | **LN1** §1.4, §1.5 · HN-A A.2 |
| **2** | a | 20 | List down the main steps of the **compilation process** | Phases of a Compiler | **LN1** §1.11 · HN-A A.3 |
| 2 | b | 20 | Differentiate **Lexical Analysis** and **Syntax Analysis** | Phases → Lexical vs Syntax Analysis | **LN1** §1.11.1–1.11.2 · LN6 §6.1 · LN7 §7.1 |
| 2 | c | 20 | Provide example strings generated by: (i) `(0 + 10*)` (ii) `(0+1)*011` (iii) `(ε + x)(y + ε)` (iv) `(aa)*(bb)*b` | Regular Expressions → RE ⇄ Regular Set | **LN2** §2.10 |
| 2 | d | 40 | Prove that `(0110 + 01)(10)*` is equal to `01(10)*` | Regular Expressions → **Algebraic identities** | ⚠️ **HN-B** B.13–B.14 · LN2 §2.9 (partial) |
| **3** | a | 20 | What is meant by the **finite automaton**? | Automata → FA / FSM definition | **LN3** §3.1–3.2 · HN-A A.11 |
| 3 | b | 20 | Can we design an FSM for: (i) all valid Java codes (ii) valid Java codes that never go into an infinite loop — justify | Automata → limits of finite automata | **LN3** §3.2 · HN-A A.11 |
| 3 | c | 20 | Compare and contrast **DFA** and **NDFA** | Automata → DFA vs NDFA comparison | **LN3** §3.9 · HN-B B.3 |
| 3 | d | 40 | Check acceptability of `bba`, `bab`, `abbabcba`, `cbacabab`, `cabbbabaacc` on the given 4-state automaton (A,B,C,D) | Automata → Acceptability by DFA/NDFA | **LN3** §3.11, §3.7 |
| **4** | a | 20 | Provide a **Formal Definition for an NDFA** | Automata → NDFA formal definition | **LN3** §3.8 · HN-B B.3 |
| 4 | b | 20 | Design an **NDFA** for all strings containing only a's and b's where there are at least 4 a's and beginning with b | Automata → NDFA design | **LN3** §3.8 · HN-B B.4 |
| 4 | c | 20 | Construct a **DFA** accepting strings over {0,1} that do **not** contain `"01011"` anywhere | Automata → DFA construction (substring exclusion) | **LN3** §3.6–3.7 · HN-A A.18 · HN-B B.2 |
| 4 | d | 40 | Find the equivalent **DFA** for the NDFA `X = ({q0…q4},{0,1},δ,q0,{q4})` given by the table | NDFA→DFA Conversion | **LN4** §4.1–4.3 · HN-B B.5 |
| **5** | a | 20 | State **Arden's Theorem** and prove `R = QP*` is the one and only unique solution | Regular Expressions → **Arden's Theorem** | ⚠️ **HN-B** B.15 |
| 5 | b | 20 | Draw the corresponding **Finite Machine** for (i) `0* + 0*11*` (ii) `(01 + 10)*` | **RE → Finite Machine construction** | ⚠️ **HN-B** B.17 |
| 5 | c | 20 | Convert the given **Finite Machine into a Regular Expression** (states A, B, C) | **FA → Regular Expression** (via Arden's) | ⚠️ **HN-B** B.15–B.16 |
| 5 | d | 40 | Minimize the given DFA (A–G) using the **Equivalence Theorem** | DFA Minimization → Equivalence Theorem | **LN4** §4.6–4.8 · HN-B B.6 |
| **6** | a | 20 | Compare and contrast the **Moore machine** and **Mealy machine** | Moore & Mealy → comparison | **LN5** §5.4 · HN-B B.8 |
| 6 | b | 20 | Convert the given **Moore machine into a Mealy machine** (q0–q5 table with Output column) | Moore → Mealy conversion | **LN5** §5.5 · HN-B B.12 |
| 6 | c | 20 | Convert the given machine (q0–q3, entries of the form `q3/0`) into the other form *(table supplied is in **Mealy** form — effectively Mealy → Moore)* | Mealy → Moore conversion | **LN5** §5.6 · HN-B B.11 |
| 6 | d | 40 | Minimize the given DFA (A–E) using the **Myphill-Nerode Theorem** | DFA Minimization → Myhill-Nerode | **LN4** §4.4–4.5 · HN-B B.7 |
| **7** | a | 20 | What is meant by a context-free grammar being **ambiguous**? Explain with an example | Syntax Analysis → Ambiguity | **LN7** §7.7 · HN-B B.23 |
| 7 | b | 20 | Generate **Left Most and Right Most Derivation** for `(id + id) * id + id` with `E→E+E │ E*E │ (E) │ id` | Syntax Analysis → Derivation | **LN7** §7.5, §7.5.3 · HN-B B.22 |
| 7 | c | 20 | Verify whether `S → 0B│1A, A → 0S│1AA│ε, B → 1│1S│0DD` generates `0010101`, `110100101110` | Syntax Analysis → CFG & Derivation | **LN7** §7.2, §7.5 · HN-B B.18–B.20 |
| 7 | d | 40 | Construct the **LL(1) / Predictive Parser** for `S→A, A→aDC, C→dC│ε, D→b, E→g` | Syntax Analysis → **Predictive parsing table** | ⚠️ **NOT COVERED** in any supplied note. LN7 §7.13 (First/Follow) is the prerequisite only |
| **8** | a | 20 | Compare and contrast **Top-Down Parsers** and **Bottom-Up Parsers** | Syntax Analysis → Parsing strategies | ⚠️ **NOT COVERED** in any supplied note |
| 8 | b | 20 | What is meant by **Back Tracking**? Explain with a suitable example | Syntax Analysis → Backtracking in parsing | ⚠️ **NOT COVERED** in any supplied note |
| 8 | c | 30 | Develop the **Parser table for LR(0)** for `S→AA, A→aA│b` | Syntax Analysis → LR(0) parsing | ⚠️ **NOT COVERED** in any supplied note |
| 8 | d | 30 | Using the Q8(c) parser table, construct the **stack implementation** for input `"aabb"` | Syntax Analysis → LR(0) stack trace | ⚠️ **NOT COVERED** in any supplied note |

---

## 2.2 — Past Paper 2020/2021 *(held June/July 2023, 5 pages, 8 questions)*

| Q | Part | Marks | Question (abbreviated) | Topic → Subtopic | Lecture Note(s) |
|---|---|---|---|---|---|
| **1** | a | 20 | *"A compiler converts Low-level language to assembly language. An assembler converts High-level language to machine-level language."* Do you agree? Justify *(both definitions are deliberately swapped/wrong)* | Introduction → Compiler & Assembler definitions | **LN1** §1.1, §1.8 · HN-A A.1 |
| 1 | b | 20 | Perform a **lexical analyzer** to generate tokens of the given C program | Lexical Analysis → Tokens | **LN6** §6.2–6.4 · LN1 §1.11.1 · HN-A A.4–A.5 |
| 1 | c | 20 | Briefly explain the usages of the **Pre-Processor** in a Language Processing System | Language Processing System → Preprocessor | **LN1** §1.6, §1.4 · HN-A A.2 |
| 1 | d | 40 | Explain the **phases of a compiler** with a suitable diagram | Phases of a Compiler | **LN1** §1.11 · HN-A A.3 |
| **2** | a | 20 | Briefly explain how a **C compiler executes** a simple C program on a host machine | Introduction → C program execution (5 steps) | **LN1** §1.5 |
| 2 | b | 20 | Differentiate between **Regular Grammar** and **Regular Language** | Regular Expressions → Regular grammar vs regular language | **LN2** §2.2 · HN-A A.7 |
| 2 | c | 20 | Explain the valid tokens: (i) `x*` (ii) `x?` (iii) `[0-5]` (iv) `(5-9)` | Regular Expressions → Representing valid tokens | **LN2** §2.6 · HN-A A.8 |
| 2 | d | 40 | Example strings + output for (i) `(a+b)((a+b)(a+b))*` (ii) `(aa+ab+ba+bb)*` (iii) `(aa)*` (iv) `(aa)*(bb)*b` | Regular Expressions → RE ⇄ Regular Set | **LN2** §2.10 |
| **3** | a | 20 | What is meant by the **finite automaton**? | Automata → FA / FSM definition | **LN3** §3.1–3.2 · HN-A A.11 |
| 3 | b | 20 | FSM for (i) all valid Java codes (ii) Java codes that never go into an infinite loop | Automata → limits of finite automata | **LN3** §3.2 · HN-A A.11 |
| 3 | c | 20 | Compare and contrast **DFA** and **NDFA** | Automata → DFA vs NDFA comparison | **LN3** §3.9 · HN-B B.3 |
| 3 | d | 40 | Check acceptability of `bba`, `bab`, `abbabcba`, `cbacabab`, `cabbbabaacc` on the given automaton | Automata → Acceptability | **LN3** §3.11, §3.7 |
| **4** | a | 20 | Provide a **Formal Definition for a DFA** | Automata → DFA formal definition | **LN3** §3.6 · HN-B B.1 |
| 4 | b | 20 | Explain with examples: (i) **Alphabet** (ii) **String** (iii) **Length of a String** (iv) **Language** | Automata → Related terminologies | **LN3** §3.4.1–3.4.6 · LN6 §6.5 · HN-A A.14 |
| 4 | c | 20 | Design an **NDFA** for all strings over {a,b} in which the **second last** symbol is always 'a' | Automata → NDFA design | **LN3** §3.8 · HN-B B.4 |
| 4 | d | 40 | Construct a **DFA** that: (i) does not contain `"xyxyy"` (ii) has an **even** number of 1's (iii) contains `"acb"` (iv) contains `"aababb"` as substring | Automata → DFA construction | **LN3** §3.6–3.7 · HN-A A.18 · HN-B B.2 |
| **5** | a | 20 | State **Arden's Theorem** and prove `R = QP*` is the unique solution | Regular Expressions → **Arden's Theorem** | ⚠️ **HN-B** B.15 |
| 5 | b | 20 | Draw the **Finite Machine** for (i) `0* + 0*11*` (ii) `(01 + 10)*` | **RE → Finite Machine construction** | ⚠️ **HN-B** B.17 |
| 5 | c | 20 | Convert the given **Finite Machine into a Regular Expression** (A, B, C) | **FA → Regular Expression** | ⚠️ **HN-B** B.15–B.16 |
| 5 | d | 40 | Minimize the given DFA (A–G) using the **Equivalence Theorem** | DFA Minimization → Equivalence Theorem | **LN4** §4.6–4.8 · HN-B B.6 |
| **6** | a | 20 | For `A. 110*(0+1)` and `B. 1(0+1)*101` — is the string `"1101"` acceptable by A, B, or Both? Justify | Regular Expressions → RE membership / regular sets | **LN2** §2.9–2.10 |
| 6 | b | 20 | What is the **power of alphabet (∑)** and **cardinality of alphabet (∑)**? Explain with examples | Automata → Kleene Star / powers of ∑ | **LN3** §3.4.4–3.4.5 · HN-A A.15 |
| 6 | c | 20 | Using power and cardinality of ∑, prove `∑*` is the set of all possible strings of all lengths over {p, q} | Automata → Kleene Star proof | **LN3** §3.4.4 · HN-A A.15 |
| 6 | d | 40 | Find the equivalent **DFA** for the NDFA `X = ({q0…q4},{a,b},δ,q0,{q1})` | NDFA→DFA Conversion | **LN4** §4.1–4.3 · HN-B B.5 |
| **7** | a | 20 | Find the derivations of `G = ({S,A,B},{a,b},S,{S→AB, A→aA│a, B→bB│b})` | Syntax Analysis → CFG & Derivation | **LN7** §7.2, §7.5 · HN-B B.18–B.20 |
| 7 | b | 20 | What is meant by a CFG being **ambiguous or unambiguous**? Explain with an example | Syntax Analysis → Ambiguity | **LN7** §7.7 · HN-B B.23 |
| 7 | c | 20 | Generate **Left Most and Right Most Derivations** for `(id + id * id) + (id * id)` | Syntax Analysis → Derivation | **LN7** §7.5, §7.5.3 · HN-B B.22 |
| 7 | d | 40 | Verify whether `S→aB│bA, A→aS│bAA│ε, B→b│bS│aBB` generates `aababab`, `bbabaababbba`, `aaaaabbbb`, `abababbbbaaa` | Syntax Analysis → CFG & Derivation | **LN7** §7.2, §7.5 |
| **8** | a | 20 | Compare and Contrast **Moore machine** and **Mealy machine** | Moore & Mealy → comparison | **LN5** §5.4 · HN-B B.8 |
| 8 | b | 20 | Convert the given **Moore machine into the Mealy machine** (q0–q3 + Output column) | Moore → Mealy conversion | **LN5** §5.5 · HN-B B.12 |
| 8 | c | 20 | Convert the given **Mealy machine into the Moore machine** (entries `q3/0`, `q1/1`…) | Mealy → Moore conversion | **LN5** §5.6 · HN-B B.11 |
| 8 | d | 40 | Minimize the given DFA (A–E) using the **Myphill-Nerode Theorem** | DFA Minimization → Myhill-Nerode | **LN4** §4.4–4.5 · HN-B B.7 |

---

## 2.3 — Past Paper 2021/2022 *(held Jan/Feb 2024, 4 pages, 8 questions)*

| Q | Part | Marks | Question (abbreviated) | Topic → Subtopic | Lecture Note(s) |
|---|---|---|---|---|---|
| **1** | a | 20 | What is meant by a **compiler** and how does it differ from an **Interpreter** and an **Assembler**? | Introduction → Compiler / Interpreter / Assembler | **LN1** §1.1, §1.7, §1.8 · HN-A A.1 |
| 1 | b | 20 | Briefly explain how a **C compiler executes** a simple program on a host machine | Introduction → C program execution | **LN1** §1.5 |
| 1 | c | 20 | Perform a **lexical analyzer** to generate tokens of the given program (`void main(){int age; age=25; …}`) | Lexical Analysis → Tokens | **LN6** §6.2–6.4 · LN1 §1.11.1 · HN-A A.4–A.5 |
| 1 | d | 40 | Discuss the workflow of a **Language Processing System** with its inputs and outputs | Language Processing System | **LN1** §1.4, §1.5 · HN-A A.2 |
| **2** | a | 20 | What are the uses of having a **Linker** and a **Loader** in the execution process? | Language Processing System → Linker, Loader | **LN1** §1.9, §1.10 · HN-A A.2 |
| 2 | b | 20 | What are the main **phases of the compilation process**? | Phases of a Compiler | **LN1** §1.11 · HN-A A.3 |
| 2 | c | 20 | *"Syntax Analysis checks whether the constructed parse tree follows the rules of language and Semantic Analysis checks whether the token arrangements follow the source code grammar."* Do you agree? Justify *(the two definitions are swapped)* | Phases → Syntax vs Semantic Analysis | **LN1** §1.11.2–1.11.3 · LN7 §7.1, §7.14 |
| 2 | d | 40 | Explain with their uses: (i) **Intermediate Code Generation** (ii) **Code Optimization** (iii) **Code Generation** (iv) **Symbol Table** | Phases of a Compiler → back-end phases | **LN1** §1.11.4–1.11.7 · HN-A A.6 |
| **3** | a | 20 | What is meant by a **Regular Expression**? How do **regular grammar** and **regular language** differ from a regular expression? | Regular Expressions → definition, regular grammar/language | **LN2** §2.1–2.2 · HN-A A.7 |
| 3 | b | 20 | Explain the valid tokens: (i) `l+` (ii) `l*` (iii) `[a-g]` (iv) `(M-W)` | Regular Expressions → Representing valid tokens | **LN2** §2.6 · HN-A A.8 |
| 3 | c | 20 | Represent using REs: (i) Any **Decimal Value** (ii) Any **Identifier** (iii) Any **NIC number** containing x or v at the end (iv) Any **Email address** ending with `"@gmail.com"` | Regular Expressions → Representing language tokens | **LN2** §2.7–2.8 · HN-A A.9–A.10 |
| 3 | d | 40 | (i) Construct REs for: strings starting with 1 and ending with 0 over {0,1}; strings starting and ending with "a" with any combination of "b"s in between over {a,b}. (ii) Give example strings and output for `(a + ab)*` and `b*(aaa)*b*` | Regular Expressions → RE construction & RE ⇄ Regular Set | **LN2** §2.9–2.10 |
| **4** | a | 20 | What is meant by the **finite automaton**? | Automata → FA / FSM definition | **LN3** §3.1–3.2 · HN-A A.11 |
| 4 | b | 20 | FSM for (i) all valid Java codes (ii) Java codes that never go into an infinite loop | Automata → limits of finite automata | **LN3** §3.2 · HN-A A.11 |
| 4 | c | 20 | **Categorize the finite automation machines** with their examples | Automata → Types of FA; Acceptors / Classifiers / Transducers | **LN3** §3.5, §3.10 · **HN-A A.13** (classification tree) |
| 4 | d | 40 | Check acceptability of `bba`, `aababbbbcba`, `cbacabbb`, `cababbaba` on the given automaton | Automata → Acceptability | **LN3** §3.11, §3.7 |
| **5** | a | 20 | Provide a **Formal Definition for a DFA** | Automata → DFA formal definition | **LN3** §3.6 · HN-B B.1 |
| 5 | b | 20 | Explain with examples: (i) **Alphabet** (ii) **String** (iii) **Length of a String** (iv) **Language** | Automata → Related terminologies | **LN3** §3.4.1–3.4.6 · LN6 §6.5 · HN-A A.14 |
| 5 | c | 20 | Design an **NDFA** for all strings over {a,b} in which the **third last** symbol is always "b" | Automata → NDFA design | **LN3** §3.8 · HN-B B.4 |
| 5 | d | 40 | Construct a **DFA** that: (i) does not contain `"xxyy"` (ii) has an **odd** number of "1"s (iii) contains `"acb"` (iv) contains `"abababbb"` as a substring | Automata → DFA construction | **LN3** §3.6–3.7 · HN-A A.18 · HN-B B.2 |
| **6** | a | 20 | State **Arden's Theorem** and prove `R = QP*` is the unique solution | Regular Expressions → **Arden's Theorem** | ⚠️ **HN-B** B.15 |
| 6 | b | 20 | Draw the corresponding **Non-Deterministic Finite Machine** for `10 + (0 + 11)0*1` over ∑ = {0,1} | **RE → Finite Machine construction** | ⚠️ **HN-B** B.17 |
| 6 | c | 20 | Find the equivalent **DFA** for the NDFA in part (b) | NDFA→DFA Conversion | **LN4** §4.1–4.3 · HN-B B.5 |
| 6 | d | 40 | Minimize the DFA in part (c) using the **Myphill-Nerode Theorem** | DFA Minimization → Myhill-Nerode | **LN4** §4.4–4.5 · HN-B B.7 |
| **7** | a | 20 | Design a **Moore machine** for a binary input sequence: substring `101` → output "a"; substring `110` → output "b"; otherwise "c" | Moore & Mealy → **Moore machine construction** | **LN5** §5.3 · **HN-B B.10** |
| 7 | b | 20 | Convert the given **Moore machine into the Mealy machine** | Moore → Mealy conversion | **LN5** §5.5 · HN-B B.12 |
| 7 | c | 20 | Convert the given **Mealy machine into the Moore machine** | Mealy → Moore conversion | **LN5** §5.6 · HN-B B.11 |
| 7 | d | 40 | Minimize the given DFA using the **Equivalence Theorem** | DFA Minimization → Equivalence Theorem | **LN4** §4.6–4.8 · HN-B B.6 |
| **8** | a | 20 | For `G = ({S},{a,b},S,{S→aSbb │ abb})` find the **language generated** | Syntax Analysis → CFG → language generated | **LN7** §7.2–7.3 · HN-B B.18, B.20 |
| 8 | b | 20 | Determine whether `S→AB│aaB, A→a│Aa, B→b` is **ambiguous** for the string `"aab"` | Syntax Analysis → Ambiguity | **LN7** §7.7 · HN-B B.23 |
| 8 | c | 20 | Generate **Left-most and Right-most Derivations** for `"aabbabba"` using `S→aB│bA, S→a│aS│bAA, S→b│aS│aBB` | Syntax Analysis → Derivation | **LN7** §7.5, §7.5.3 · HN-B B.22 |
| 8 | d | 40 | Verify whether `S→aB│bA, A→aS│bAA│ε, B→b│bS│aBB` generates `aababab`, `bbabaababbba`, `aaaaabbbb`, `abababbbbaaa` | Syntax Analysis → CFG & Derivation | **LN7** §7.2, §7.5 |

---

## 2.4 — Past Paper 2022/2023 *(held Feb/March 2025, 4 pages, 8 questions)*

| Q | Part | Marks | Question (abbreviated) | Topic → Subtopic | Lecture Note(s) |
|---|---|---|---|---|---|
| **1** | a | 20 | What is the primary role of a **compiler** in programming? | Introduction → Compiler definition | **LN1** §1.1 · HN-A A.1 |
| 1 | b | 20 | List the **three analyses performed at the front end** of a compiler and explain how they contribute to translation | Compiler Design Principles → front end (lexical, syntax, semantic) | **LN1** §1.2, §1.11.1–1.11.3 · LN6 §6.1 · LN7 §7.1 |
| 1 | c | 20 | What challenges would programmers face if **compilers did not exist**? | Hardware/Software relationship → binary programming | **LN1** §1.3 |
| 1 | d | 40 | Perform a **lexical analyzer** to generate tokens of the given program (`void main(){int age; age=25; …}`) | Lexical Analysis → Tokens | **LN6** §6.2–6.4 · LN1 §1.11.1 · HN-A A.4–A.5 |
| **2** | a | 20 | Why is **optimization** considered a critical task for compilers? | Phases → Code Optimization | **LN1** §1.11.5 · HN-A A.6 |
| 2 | b | 20 | What are the main phases of the **compilation process**? | Phases of a Compiler | **LN1** §1.11 · HN-A A.3 |
| 2 | c | 20 | Briefly explain how the **Language Processing System** bridges the gap between human-readable code and hardware operations | Language Processing System | **LN1** §1.3–1.5 · HN-A A.2 |
| 2 | d | 40 | Could a single tool replace the **compiler, assembler, linker, and loader**? Justify and explain the challenges | Language Processing System → roles of each tool | **LN1** §1.4, §1.6–1.10 |
| **3** | a | 20 | What is meant by a **Regular Expression**? How do **regular grammar** and **regular language** differ from it? | Regular Expressions → definition, regular grammar/language | **LN2** §2.1–2.2 · HN-A A.7 |
| 3 | b | 20 | Explain the valid tokens: (i) `a+` (ii) `s*` (iii) `[1-7]` (iv) `(A-T)` | Regular Expressions → Representing valid tokens | **LN2** §2.6 · HN-A A.8 |
| 3 | c | 20 | Represent using REs: (i) Any **Decimal Value** (ii) Any **Identifier** (iii) Any **NIC number** with x or v at the end (iv) Any **Email address** ending `"@gmail.com"` | Regular Expressions → Representing language tokens | **LN2** §2.7–2.8 · HN-A A.9–A.10 |
| 3 | d | 40 | (i) REs for: strings starting with 1 and ending with 0; strings starting and ending with "a" with any "b"s in between. (ii) Final output and example strings for `(a + b)*` and `b*(aaa)*b*` | Regular Expressions → RE construction & RE ⇄ Regular Set | **LN2** §2.9–2.10 |
| **4** | a | 20 | What does **"Automata"** mean, and why is it significant in computing? | Automata → definition & etymology | **LN3** §3.1 · HN-A A.11 |
| 4 | b | 20 | What is the **Kleene Star (∑*)**, and how does it differ from the **Kleene Closure (∑+)**? Provide an example | Automata → Kleene Star vs Kleene Plus | **LN3** §3.4.4–3.4.5 · HN-A A.15 |
| 4 | c | 20 | In the 5-tuple `(Q, ∑, q0, F, δ)` of a finite machine, what role does the **transition function (δ)** play? | Automata → Formal definition → transition function | **LN3** §3.3, §3.6, §3.8 · HN-B B.1 |
| 4 | d | 40 | Check acceptability of `abaaab`, `babbbba`, `bbbaaabab`, `abababbba` on the given automaton (A, B, C) | Automata → Acceptability | **LN3** §3.11, §3.7 |
| **5** | a | 20 | Show that `∑*` is the set of all possible strings of all lengths (i.e. language) where ∑ is the alphabet | Automata → Kleene Star | **LN3** §3.4.4 · HN-A A.15 |
| 5 | b | 20 | Why might **NFAs** be computationally easier to design for certain problems, even though **DFAs** are simpler in execution? | Automata → DFA vs NDFA | **LN3** §3.9 · HN-B B.3 |
| 5 | c | 20 | Compare and contrast a **DFA** and an **NFA** | Automata → DFA vs NDFA comparison | **LN3** §3.9 · HN-B B.3 |
| 5 | d | 40 | Construct a **DFA** that: (i) accepts all strings over {a,b,c} that **start with** "abc" (ii) does not contain the substring "pqp" over {p,q} (iii) has an **even number of 0s and an odd number of 1s** over {0,1} (iv) ends with "111" with at least four symbols | Automata → DFA construction | **LN3** §3.6–3.7 · HN-A A.18 · HN-B B.2 |
| **6** | a | 20 | State **Arden's Theorem** and prove `R = QP*` is the only unique solution | Regular Expressions → **Arden's Theorem** | ⚠️ **HN-B** B.15 |
| 6 | b | 20 | Draw the corresponding **Non-Deterministic Finite Machine** for `10 + (0 + 11)0* 1` over ∑ = {0,1} | **RE → Finite Machine construction** | ⚠️ **HN-B** B.17 |
| 6 | c | 20 | Find the equivalent **DFA** for the NDFA in part (b) | NDFA→DFA Conversion | **LN4** §4.1–4.3 · HN-B B.5 |
| 6 | d | 40 | Minimize the DFA in part (c) using the **Myphill-Nerode Theorem** | DFA Minimization → Myhill-Nerode | **LN4** §4.4–4.5 · HN-B B.7 |
| **7** | a | 20 | Design a **Mealy machine** for a binary input sequence: output "M" immediately after detecting `"01"`; "N" after `"10"`; "O" otherwise | Moore & Mealy → **Mealy machine construction** | **LN5** §5.2 · **HN-B B.9** |
| 7 | b | 20 | Convert the given **Moore machine into the Mealy machine** (q0–q3 + Output column) | Moore → Mealy conversion | **LN5** §5.5 · HN-B B.12 |
| 7 | c | 20 | Convert the given **Mealy machine into the Moore machine** (entries `q3/0`, `q1/1`…) | Mealy → Moore conversion | **LN5** §5.6 · HN-B B.11 |
| 7 | d | 40 | Minimize the given DFA (A–E) using the **Equivalence Theorem** | DFA Minimization → Equivalence Theorem | **LN4** §4.6–4.8 · HN-B B.6 |
| **8** | a | 20 | For `G = ({S},{a,b},S,{S→aSb│bSa│ab│ba})`: (i) what **language** is generated? (ii) derive the string `"aabbab"` | Syntax Analysis → CFG → language generated & derivation | **LN7** §7.2–7.3, §7.5 · HN-B B.18–B.20 |
| 8 | b | 20 | For `G = ({S},{a,b},S,{S→aSb│bSa│SS│ε})`: (i) is this grammar **ambiguous** for `"abab"`? (ii) if ambiguous, provide **two distinct parse trees** for `"abab"` | Syntax Analysis → Ambiguity & Parse Tree | **LN7** §7.6–7.7 · HN-B B.21, B.23 |
| 8 | c | 20 | Generate **Left-most and Right-most Derivations** for `"aababab"` using `G = ({S,A,B},{a,b},S,{S→aB│bA, A→aS│bAA│ε, B→b│bS│aBB})` | Syntax Analysis → Derivation | **LN7** §7.5, §7.5.3 · HN-B B.22 |
| 8 | d | 40 | (i) Prove `G = ({S,A,B},{a,b},S,{S→AB│aB, A→a│Aa, B→b})` generates all strings of the form `aⁿb` where n ≥ 1. (ii) Prove `G = ({S},{a,b},S,{S→aSb│bSa│SS│ε})` generates all strings with an **equal number of a's and b's** | Syntax Analysis → CFG → language proof | **LN7** §7.2–7.3 · HN-B B.18–B.20 |

---

## 2.5 — Past Paper 2023/2024 *(held February 2026, 4 pages, 8 questions)* — **most recent**

| Q | Part | Marks | Question (abbreviated) | Topic → Subtopic | Lecture Note(s) |
|---|---|---|---|---|---|
| **1** | a | 20 | Briefly explain why computers **cannot directly execute or understand** high-level programming languages such as C or Python | Hardware/Software relationship → binary language | **LN1** §1.3 |
| 1 | b | 20 | Briefly explain **Lexical Analysis, Syntax Analysis, Semantic Analysis, and Symbol Table**, highlighting how each phase contributes to the **detection and reporting of errors** | Phases of a Compiler → front end + Symbol Table | **LN1** §1.11.1–1.11.3, §1.11.7 · LN6 §6.1 · LN7 §7.1, §7.14 · HN-A A.3 |
| 1 | c | 20 | For `int x = "10" + 5;` — identify the **compiler phase that detects the error** and justify why/how | Phases → Semantic Analysis (type compatibility) | **LN1** §1.11.3 · LN7 §7.14 |
| 1 | d | 40 | Embedded system with limited memory and processing power: (i) why is **code optimization** especially important? (ii) which compiler phases play the most critical role in improving execution speed and memory usage? | Phases → Code Optimization & Code Generation | **LN1** §1.11.4–1.11.6 · HN-A A.6 |
| **2** | a | 20 | With the aid of a **diagram**, explain the **Language Processing System** used to execute a program | Language Processing System | **LN1** §1.4–1.5 · **HN-A A.2** |
| 2 | b | 20 | Distinguish between: (i) **Compiler** (ii) **De-compiler** (iii) **Interpreter** (iv) **Assembler** | Introduction → Compiler / Interpreter / Assembler | **LN1** §1.1, §1.7, §1.8 · ⚠️ *"De-compiler" is not defined in any supplied note* |
| 2 | c | 20 | What is **Intermediate Code Generation**? Why do compilers generate intermediate code instead of directly producing machine code? | Phases → Intermediate Code Generation | **LN1** §1.11.4 · HN-A A.6 |
| 2 | d | 40 | A C program **compiles successfully but produces incorrect output**: (i) which compiler phase(s) might still have worked correctly and why? (ii) what types of errors are **not detected by the compiler**? | Phases of a Compiler + Limitations of Syntax Analysis | **LN1** §1.11 · **LN7** §7.14 |
| **3** | a | 20 | What is meant by a **Regular Expression**? Why are regular expressions important in the design of a **lexical analyzer**? | Regular Expressions → role in lexical analysis | **LN2** §2.1–2.2 · LN6 §6.2 · HN-A A.7 |
| 3 | b | 20 | Explain with examples the operations on languages: (i) **Union** (ii) **Concatenation** (iii) **Kleene Closure Star (L*)** (iv) **Kleene Closure Plus (L+)** | Regular Expressions → Operations on languages | **LN2** §2.3–2.4 · LN3 §3.4.4–3.4.5 · HN-A A.7 |
| 3 | c | 20 | Construct suitable REs for: (i) a **binary number** (only 0s and 1s) (ii) a valid **Sri Lankan mobile phone number** starting with 07 and containing 10 digits (iii) a valid **university registration number** in the format `YYYY/ABC/NNN` (iv) a **password** that starts with a letter, has at least 6 characters, and contains only letters and digits | Regular Expressions → Representing language tokens | **LN2** §2.6–2.8 · **HN-A A.10** (telephone/index-number patterns) |
| 3 | d | 40 | For `(aa + ab + ba + bb)*`: (i) describe the **language generated** and the common property of all its strings (ii) can it be described by a **simpler expression**? If yes, write and describe an equivalent RE | Regular Expressions → RE ⇄ Regular Set + **algebraic simplification** | **LN2** §2.10 · ⚠️ **HN-B B.13–B.14** (simplification identities) |
| **4** | a | 20 | What is meant by the term **Automaton**? Explain why it is referred to as a **"self-acting"** machine | Automata → definition & etymology | **LN3** §3.1 · HN-A A.11 |
| 4 | b | 20 | Define with examples: (i) **Alphabet** (ii) **String** (iii) **Language** (iv) **Empty String** | Automata → Related terminologies | **LN3** §3.4.1–3.4.6 · LN6 §6.5 · HN-A A.14 |
| 4 | c | 20 | Prove that `∑+ = ∑* − {ε}` | Automata → Kleene Closure / Plus | **LN3** §3.4.4–3.4.5 · **HN-A A.15** |
| 4 | d | 40 | An ATM accepts a PIN that: contains only digits {0,1}; must start with 1; must end with 0; must contain an **even number of 1s**; length ≥ 3. (i) Construct a **DFA** accepting only valid PINs (ii) test `10`, `110110`, `110`, `11011` showing all steps | Automata → DFA construction + Acceptability | **LN3** §3.6–3.7, §3.11 · HN-A A.17–A.18 · HN-B B.2 |
| **5** | a | 20 | **Formally prove** that every **DFA** is also an **NDFA** | Automata → DFA & NDFA formal definitions | **LN3** §3.6, §3.8 · **HN-B B.3** ("Every DFA is NDFA") |
| 5 | b | 20 | Discuss why **NDFAs** might be computationally easier to design for certain problems, even though **DFAs** are simpler in execution | Automata → DFA vs NDFA | **LN3** §3.9 · HN-B B.3 |
| 5 | c | 20 | Explain why in a **DFA** the transition function is `δ: Q × ∑ → Q` while in an **NDFA** it is `δ: Q × ∑ → 2^Q` | Automata → Formal definitions → transition function | **LN3** §3.6, §3.8 · HN-B B.1, B.3 |
| 5 | d | 40 | Online registration usernames over {a, b}: must contain **exactly two 'a's**, any number of 'b's, order does not matter. (i) Construct a **DFA** (ii) modify it to accept usernames with **at least two 'a's** and explain the changes | Automata → DFA construction & modification | **LN3** §3.6–3.7 · HN-A A.18 · HN-B B.2 |
| **6** | a | 20 | Design a **Mealy machine** for a vending machine accepting coins of 5 and 10; once the total reaches or exceeds 20, dispense the item producing "D", otherwise produce "C" | Moore & Mealy → **Mealy machine construction** | **LN5** §5.2 · **HN-B B.9** |
| 6 | b | 20 | Convert the **Mealy machine** in part (a) into a **Moore machine** | Mealy → Moore conversion | **LN5** §5.6 · HN-B B.11 |
| 6 | c | 20 | Convert the given **Moore machine into the Mealy machine** (Q0–Q3 with Output column) | Moore → Mealy conversion | **LN5** §5.5 · HN-B B.12 |
| 6 | d | 40 | Minimize the given DFA (A–F, table form) using the **Equivalence Theorem** | DFA Minimization → Equivalence Theorem | **LN4** §4.6–4.8 · HN-B B.6 |
| **7** | a | 20 | State **Arden's Theorem** and prove that `R = QP*` is a solution of `R = Q + RP`, and it is the only unique solution | Regular Expressions → **Arden's Theorem** | ⚠️ **HN-B** B.15 |
| 7 | b | 20 | Draw the corresponding **Non-Deterministic Finite Machine** for `ab* + (a + bb)a*b*` over ∑ = {a, b} | **RE → Finite Machine construction** | ⚠️ **HN-B** B.17 |
| 7 | c | 20 | Find the equivalent **DFA** for the NDFA machine in part (b) | NDFA→DFA Conversion | **LN4** §4.1–4.3 · HN-B B.5 |
| 7 | d | 40 | Minimize the DFA in part (c) using the **Myhill-Nerode Theorem** | DFA Minimization → Myhill-Nerode | **LN4** §4.4–4.5 · HN-B B.7 |
| **8** | a | 20 | For `G = ({S},{a,b},S,{S→aSb│bSa│ab│ba})`: (i) what **language** is generated? (ii) derive the string `"aabbab"` | Syntax Analysis → CFG → language generated & derivation | **LN7** §7.2–7.3, §7.5 · HN-B B.18–B.20 |
| 8 | b | 20 | For `G = ({S},{a,b},S,{S→aSb│bSa│SS│ε})`: (i) is this grammar **ambiguous** for `"abab"`? Discuss (ii) provide **two distinct parse trees** for `"abab"` | Syntax Analysis → Ambiguity & Parse Tree | **LN7** §7.6–7.7 · HN-B B.21, B.23 |
| 8 | c | 20 | Generate **Left-most and Right-most derivations** for `"aababab"` using `G = ({S,A,B},{a,b},S,{S→aB│bA, A→aS│bAA│ε, B→b│bS│aBB})` | Syntax Analysis → Derivation | **LN7** §7.5, §7.5.3 · HN-B B.22 |
| 8 | d | 40 | (i) Prove `G = ({S,A,B},{a,b},S,{S→AB│aB, A→a│Aa, B→b})` generates all strings of the form `aⁿb`, n ≥ 1. (ii) Prove `G = ({S},{a,b},S,{S→aSb│bSa│SS│ε})` generates all strings with an **equal number of a's and b's** | Syntax Analysis → CFG → language proof | **LN7** §7.2–7.3 · HN-B B.18–B.20 |

---

# PART 3 — Cross-Year Analysis

## 3.1 The paper has a fixed skeleton

Across all five years the eight questions map onto the **same seven blocks** in almost the same order. Knowing this is worth a lot in the exam room.

| Block | Topic area | Primary notes | P19/20 | P20/21 | P21/22 | P22/23 | P23/24 |
|---|---|---|---|---|---|---|---|
| **A** | Compiler basics, LPS, phases, lexical tokens | LN1, LN6 | Q1, Q2a-b | Q1, Q2a | Q1, Q2 | Q1, Q2 | Q1, Q2 |
| **B** | Regular Expressions | LN2 | Q2c-d | Q2b-d, Q6a | Q3 | Q3 | Q3 |
| **C** | Automata basics, terminologies, acceptability | LN3 | Q3 | Q3, Q6b-c | Q4 | Q4, Q5a-c | Q4a-c, Q5a-c |
| **D** | DFA/NDFA design & construction | LN3, HN-B | Q4a-c | Q4 | Q5 | Q5d | Q4d, Q5d |
| **E** | Arden's + RE→FM + NDFA→DFA + minimization | HN-B, LN4 | Q4d, Q5 | Q5, Q6d | Q6 | Q6 | Q7 |
| **F** | Moore & Mealy machines | LN5, HN-B | Q6 | Q8 | Q7 | Q7 | Q6 |
| **G** | Grammars, CFG, derivations, ambiguity | LN7, HN-B | Q7 | Q7 | Q8 | Q8 | Q8 |
| **H** | ⚠️ Parsing (LL(1), LR(0), top-down/bottom-up) | *none* | Q7d, Q8 | — | — | — | — |

## 3.2 Topics examined in **all five** papers (highest priority)

| # | Topic | Typical marks | Source |
|---|---|---|---|
| 1 | **Arden's Theorem** — statement + full proof | 20 | ⚠️ HN-B B.15 only |
| 2 | **RE → Finite Machine** construction | 20 | ⚠️ HN-B B.17 only |
| 3 | **NDFA → DFA conversion** | 20–40 | LN4 §4.1–4.3 |
| 4 | **DFA minimization — Myhill-Nerode** | 40 | LN4 §4.4–4.5 |
| 5 | **DFA minimization — Equivalence Theorem** | 40 | LN4 §4.6–4.8 |
| 6 | **Moore ↔ Mealy conversion** (both directions, every year) | 20 + 20 | LN5 §5.5–5.6 |
| 7 | **DFA construction** (4-part question: substring contains / excludes / parity / starts-ends) | 40 | LN3 §3.6–3.7, HN-A A.18, HN-B B.2 |
| 8 | **Left-most & Right-most Derivations** | 20 | LN7 §7.5 |
| 9 | **Ambiguity of a CFG** | 20 | LN7 §7.7 |
| 10 | **Phases of the compiler / compilation process** | 20–40 | LN1 §1.11 |
| 11 | **Language Processing System** | 20–40 | LN1 §1.4–1.5 |
| 12 | **Regular expression construction for real-world tokens** | 20 | LN2 §2.7–2.8, HN-A A.10 |

## 3.3 Topics examined in **four of five** papers

- **"Perform a lexical analyzer to generate tokens"** using an almost identical C program — P19/20 Q1b, P20/21 Q1b, P21/22 Q1c, P22/23 Q1d (absent only from P23/24). The `void main(){int age; age=25; age=age+5; printf…}` program is reused verbatim in P21/22 and P22/23.
- **String acceptability on a supplied automaton** — P19/20 Q3d, P20/21 Q3d, P21/22 Q4d, P22/23 Q4d. The *same* A→B→C→D automaton and the *same* five strings (`bba`, `bab`, `abbabcba`, `cbacabab`, `cabbbabaacc`) appear in both P19/20 and P20/21.
- **Compare and contrast DFA and NDFA** — P19/20 Q3c, P20/21 Q3c, P22/23 Q5c, P23/24 Q5b.
- **Formal definition of a DFA / NDFA** — P19/20 Q4a, P20/21 Q4a, P21/22 Q5a, P23/24 Q5a/5c.

## 3.4 Repeated verbatim question stems

These recur almost word-for-word and are near-certain to reappear:

| Stem | Years |
|---|---|
| *"State Arden's Theorem and prove that R = QP* is a solution, and it is the one and only unique solution of it."* | **All 5** |
| *"State whether we can design a finite state machine for the following scenarios… (i) To accept all valid Java codes (ii) …that never go into an infinite loop"* | P19/20, P20/21, P21/22 |
| *"Briefly explain the following terminologies with suitable example(s): Alphabet, String, Length of a String, Language"* | P20/21, P21/22, P23/24 (as "Empty String") |
| *"Represent the following language tokens using regular expressions: Any Decimal Value / Any Identifier / Any NIC number… / Any Email address…"* | P21/22, P22/23 |
| *"Draw the corresponding Non-Deterministic Finite Machine for `10 + (0 + 11)0*1`"* | P21/22, P22/23 |
| *"Convert the following Moore machine into the Mealy machine"* + *"Convert the following Mealy machine into the Moore machine"* (same q0–q3 tables) | P20/21, P22/23, P23/24 |
| Q8 grammar set: `S→aSb│bSa│ab│ba` + `S→aSb│bSa│SS│ε` + derivations for `"aababab"` + the two `aⁿb` / equal-a's-and-b's proofs | **P22/23 and P23/24 are effectively identical** |

> **P22/23 and P23/24 share an almost identical Question 8 and very similar Q3, Q5, Q6, Q7.** If you prepare one thoroughly, you have effectively prepared both.

## 3.5 ⚠️ Coverage gaps — topics examined but *not* in the typed lecture notes

| Gap | Where examined | Where (if anywhere) it is covered | Severity |
|---|---|---|---|
| **Arden's Theorem** (statement + proof) | Every paper, 20 marks | HN-B B.15 only | 🔴 **High** — guaranteed 20 marks each year |
| **RE → Finite Machine** construction rules | Every paper, 20 marks | HN-B B.17 only | 🔴 **High** |
| **FA → Regular Expression** conversion | P19/20 Q5c, P20/21 Q5c | HN-B B.16 only | 🟠 Medium |
| **RE algebraic identities / simplification** | P19/20 Q2d, P23/24 Q3d(ii) | HN-B B.13–B.14 only | 🟠 Medium |
| **LL(1) / Predictive Parser table** | P19/20 Q7d (40 marks) | ❌ Nowhere — LN7 §7.13 First/Follow is only the prerequisite | 🔴 **High if it returns** |
| **LR(0) parser table + stack implementation** | P19/20 Q8c–d (60 marks) | ❌ Nowhere | 🔴 **High if it returns** |
| **Top-Down vs Bottom-Up Parsers** | P19/20 Q8a | ❌ Nowhere | 🟠 Medium |
| **Backtracking in parsing** | P19/20 Q8b | ❌ Nowhere | 🟠 Medium |
| **De-compiler** | P23/24 Q2b | ❌ Nowhere | 🟢 Low (one item of four) |
| **Power / cardinality of alphabet** (terminology) | P20/21 Q6b–c | LN3 §3.4.4 + HN-A A.15 | 🟢 Low |

**On the parsing block (Q7d + Q8 of P19/20):** this is the single biggest gap — 100 marks in that paper. It has **not** reappeared in P20/21, P21/22, P22/23 or P23/24, where Q8 became grammar/derivation/ambiguity instead. The trend over the last four years suggests it has been dropped, but since you may answer any 6 of 8, it is safely skippable even if it returns.

## 3.6 Lecture-note content **never** examined in these five papers

Useful for deciding what *not* to spend time on:

| Content | Source |
|---|---|
| **Left Recursion** (immediate/indirect) and its removal | LN7 §7.10–7.11 |
| **Left Factoring** | LN7 §7.12 |
| **First and Follow Sets** (in their own right) | LN7 §7.13 |
| **Associativity and Precedence of operators** (as standalone questions) | LN7 §7.8–7.9 |
| **Longest Match Rule** and **Rule Priority** | LN6 §6.6–6.7 |
| **Special symbols table** (arithmetic/punctuation/shift operators) | LN6 §6.5.3 |
| **Acceptors / Classifiers / Transducers** (only indirectly, via P21/22 Q4c) | LN3 §3.10 |
| **Parse tree construction from the E→E+E example** (asked only as derivations) | LN7 §7.6 |

---

# PART 4 — Suggested Study Priority

> [!tip] My notes for each tier
> - **Tier 1:** [[3134-08 Arden's Theorem & RE-FA Conversion]] · [[3134-04 NDFA to DFA Conversion & DFA Minimization]] · [[3134-05 Moore and Mealy Machines]] · [[3134-03 Automata]]
> - **Tier 2:** [[3134-01 Introduction to Compilers]] · [[3134-06 Lexical Analysis]] · [[3134-07 Syntax Analysis]] · [[3134-02 Regular Expressions]]
> - **Tier 3:** [[3134-03 Automata]] (terminology) · [[3134-07 Syntax Analysis]] (CFG proofs)
> - **Tier 4:** [[3134-07 Syntax Analysis]] (parsing block)


Based purely on the frequency and mark-weight evidence above:

### Tier 1 — do these first (appear every year, 60–100 marks/paper available)
1. **Arden's Theorem** — memorise the statement and reproduce the proof (HN-B B.15). Then practise **FA → RE** with it.
2. **RE → NDFA → DFA → Minimized DFA** as one continuous pipeline — this is literally one whole question (Q6/Q7) every year, worth 100 marks.
3. **Moore ↔ Mealy conversions** in both directions, plus **designing** a Mealy/Moore machine from a word problem (substring detection, vending machine, complement).
4. **DFA construction** from constraints — contains/does-not-contain a substring, parity of symbols, starts/ends with.

### Tier 2 — high yield, cheap to learn
5. **Compiler phases + Language Processing System** — draw both diagrams from memory; this is 40–60 marks of Q1/Q2 every year.
6. **Lexical analyzer tokenization** of a small C program — mechanical marks, practise the `<token, value>` format.
7. **Left-most / Right-most derivations** and **ambiguity** with two parse trees.
8. **Regular expression construction** for decimal / identifier / NIC / email / phone / registration number / password.

### Tier 3 — round out
9. Automata terminologies (alphabet, string, length, language, ∑*, ∑+) and the proofs `∑+ = ∑* − {ε}`.
10. DFA vs NDFA comparison; formal definitions; role of δ.
11. CFG language-generated and language proofs (`aⁿb`, equal a's and b's).

### Tier 4 — only if time permits
12. The P19/20 parsing block (LL(1), LR(0), top-down/bottom-up, backtracking) — not examined since, and you can drop 2 of 8 questions.

---

*End of analysis.*
