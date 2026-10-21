---
type: revision
course: CMIS 3134
status: complete
tags: [cmis3134, revision, definitions]
aliases: [3134 definitions, CMIS 3134 Definitions]
---
# CMIS 3134 · Important Definitions

> [!info] How to use
> - These are the definitions the papers ask for directly ("What is…?", "Define…", "Briefly explain…"). They are worded as in the lecture notes where possible.
> - A 20-mark definition question expects: **definition + purpose + one example** (and a diagram if it fits).
> - ⚠ = not in the course notes (standard textbook definition). Home: [[00. CMIS 3134 Course Overview]]

## L1 · Compilers and the language processing system → [[01.00 Introduction to Compilers]]

| Term | Definition | Asked |
|---|---|---|
| **Compiler** | A program that translates code written in one language into another language **without changing the meaning** of the program, and makes the target code **efficient in time and space**. Converts high-level language → assembly language. | 19/20, 20/21, 21/22, 22/23, 23/24 |
| **Interpreter** | Translates and executes the source program **one statement at a time**; stops at the **first error**; produces no object code. | 21/22, 23/24 |
| **Assembler** | Translates **assembly language → machine code**. Its output is an **object file**: machine instructions plus the data needed to place them in memory. | 19/20, 20/21, 21/22, 23/24 |
| **De-compiler** ⚠ | Translates **machine/object code back into a high-level language**: the reverse of a compiler. | 23/24 |
| **Preprocessor** | Part of the compiler that produces input for the compiler. Jobs: **macro processing, augmentation, file inclusion, language extension**. | 20/21 |
| **Linker** | Merges object files (and library files) into an **executable**. It finds referenced modules/routines in a program and determines their memory locations, giving **absolute references**. | 21/22 |
| **Loader** | **Part of the operating system.** Loads the executable into memory: calculates its size, creates memory space and initializes registers, then the program runs. | 21/22 |
| **Language Processing System (LPS)** | The chain of tools that turns source code into a running program: **Preprocessor → Compiler → Assembler → Linker → Loader → Memory**. | every paper |
| **Lexical analysis** | First phase: reads the character stream, groups characters into **lexemes** and outputs **tokens** `<token-name, attribute-value>`; removes whitespace and comments. | 19/20, 22/23, 23/24 |
| **Syntax analysis (parsing)** | Takes tokens and builds a **parse tree**, checking the arrangement of tokens against the **grammar**. | 19/20, 22/23, 23/24 |
| **Semantic analysis** | Checks that the parse tree follows the **rules of the language** (type compatibility, declared before use); outputs an **annotated syntax tree**. | 22/23, 23/24 |
| **Intermediate code generation (ICG)** | Produces code for an **abstract machine**, between high-level and machine language; easy to translate into target code and makes the compiler portable (retargetable). | 21/22, 23/24 |
| **Code optimization** | Removes unnecessary code and rearranges statements so the program **runs faster without wasting CPU or memory**, without changing its meaning. | 21/22, 22/23, 23/24 |
| **Code generation** | Translates the optimized intermediate code into **re-locatable machine code** for the target machine. | 21/22 |
| **Symbol table** | A data structure **maintained throughout all phases**. It stores identifier names with their types (and other attributes), allows fast searching, and helps with **scope management**. | 19/20, 21/22, 23/24 |
| **Error handler** | Detects, reports and recovers from errors found in every phase. | 23/24 |

## L2 · Regular expressions → [[02.00 Regular Expressions]]

| Term | Definition |
|---|---|
| **Regular expression** | A notation for specifying **patterns**; each pattern names a **set of strings**. REs are defined **recursively** and can be implemented efficiently (by finite automata). |
| **Regular grammar** | A grammar defined by regular expressions. |
| **Regular language** | A language defined by a regular grammar (equivalently, by an RE or a finite automaton). |
| **Union** $L \cup M$ | Strings in L or in M. |
| **Concatenation** $LM$ | $\{xy : x \in L,\ y \in M\}$. |
| **Kleene star** $L^*$ | Zero or more concatenations of strings from L; **contains ε**. |
| **Kleene plus** $L^+$ | One or more concatenations; $L^+ = LL^*$; contains ε only if L does. |
| **Recursive definition** | ε is an RE for {ε}; φ for { }; x for {x}; if X and Y are REs then so are X + Y (union), X·Y (concatenation) and X\* (star). |

## L3 · Automata → [[03.00 Automata]]

| Term | Definition |
|---|---|
| **Automaton** | From the Greek *αὐτόματα*, **"self-acting"**: an abstract self-propelled computing device that follows a predetermined sequence of operations automatically. |
| **Finite automaton (FA / FSM)** | An automaton with a **finite number of states**. |
| **Alphabet Σ** | A finite, non-empty set of symbols, e.g. {0, 1}. |
| **String** | A finite sequence of symbols from Σ, e.g. 0110. |
| **Length \|S\|** | The number of symbols in S. |
| **Empty string ε (λ)** | The string with no symbols; \|ε\| = 0. |
| **Language** | A subset of Σ\*; may be finite or infinite. |
| **Σⁿ** | All strings of length n over Σ; $\|\Sigma^n\| = \|\Sigma\|^n$ (the cardinality of Σⁿ). |
| **Σ\* (Kleene star / closure)** | $\Sigma^0 \cup \Sigma^1 \cup \Sigma^2 \cup \dots$: every string over Σ, **including ε**. |
| **Σ⁺ (Kleene plus / positive closure)** | $\Sigma^1 \cup \Sigma^2 \cup \dots = \Sigma^* - \{\varepsilon\}$. |
| **DFA** | A 5-tuple $(Q, \Sigma, \delta, q_0, F)$ with $\delta: Q \times \Sigma \rightarrow Q$: for every state and input there is **exactly one** next state. |
| **NDFA** | A 5-tuple $(Q, \Sigma, \delta, q_0, F)$ with $\delta: Q \times \Sigma \rightarrow 2^Q$: a state and input can lead to **any set of states** (including ∅). |
| **Transition function δ** | Gives the next state(s) from the current state and input symbol; it defines how the machine moves. |
| **Accepted string** | S is accepted if $\delta^*(q_0, S) \in F$. For an NDFA, if **at least one** path ends in a final state. |
| **Acceptor** | An FA that computes a Boolean function: accept or reject. |
| **Classifier** | Has more than two final states and gives a single output when it terminates. |
| **Transducer** | Produces output based on the current input and/or state (Mealy and Moore machines). |

## L4 · Conversion and minimization → [[04.00 NDFA to DFA Conversion and DFA Minimization]]

| Term | Definition |
|---|---|
| **Subset construction** | Builds a DFA whose states are **sets of NDFA states**; L(DFA) = L(NDFA). |
| **Dead (trap) state** | A non-final state that loops to itself on every input; in subset construction it is the state ∅. |
| **Unreachable state** | A state that cannot be reached from the start state by any string; remove it before minimizing. |
| **Distinguishable states** | p and q are distinguishable if some string S takes one of them to a final state and the other to a non-final state. |
| **k-equivalent** | p and q are k-equivalent if no string of length ≤ k distinguishes them. |
| **Minimal DFA** | A DFA with the fewest states for its language; a DFA is minimal **if and only if every pair of states is distinguishable** (and all states are reachable). |

## L5 · Machines with output → [[05.00 Moore and Mealy Machines]]

| Term | Definition |
|---|---|
| **Mealy machine** | A 6-tuple $(Q, \Sigma, O, \delta, X, q_0)$ where the output function $X: Q \times \Sigma \rightarrow O$: the output depends on the **current state and the current input**, written on the arrows (`a/1`). |
| **Moore machine** | A 6-tuple $(Q, \Sigma, O, \delta, X, q_0)$ where $X: Q \rightarrow O$: the output depends **only on the current state**, written inside the state (`q1/0`). |

## L6 · Lexical analysis → [[06.00 Lexical Analysis]]

| Term | Definition |
|---|---|
| **Token** | A pair `<token-name, attribute-value>` naming a class of lexemes (keyword, identifier, constant, literal, operator, punctuator). |
| **Lexeme** | The actual sequence of characters in the source that matches a token's pattern, e.g. `age`. |
| **Pattern** | The rule (usually an RE) describing the lexemes of a token, e.g. `letter(letter\|digit)*`. |
| **Longest match rule** | The lexer takes the **longest** prefix that matches any token (`intvalue` is one identifier, not `int` + `value`). |
| **Rule priority** | When two patterns match the same lexeme, the **reserved word** wins (`int` is always a keyword). |

## L7 · Syntax analysis → [[07.00 Syntax Analysis]]

| Term | Definition |
|---|---|
| **Context-free grammar** | A 4-tuple $(V, \Sigma, P, S)$: non-terminals, terminals, productions of the form A → α, and a start symbol. Recognised by a **push-down automaton**. |
| **Derivation** | A sequence of production applications that turns the start symbol into the string. |
| **Left-most derivation (LMD)** | At every step, the **left-most** non-terminal is replaced. |
| **Right-most derivation (RMD)** | At every step, the **right-most** non-terminal is replaced. |
| **Parse tree** | A tree with the start symbol at the root, non-terminals as interior nodes and terminals as leaves; reading the leaves left to right gives the string. |
| **Ambiguous grammar** | A grammar with **more than one parse tree** (equivalently, more than one LMD or RMD) for **at least one string**. |
| **Associativity** | Decides which side an operand goes to when the same operator appears on both sides: + − \* / are left-associative, ^ is right-associative. |
| **Precedence** | Decides which operator applies first when different operators share an operand: `2 + 3 * 4 = 2 + (3 * 4)`. |
| **Left recursion** | A ⇒⁺ Aα; it makes a top-down parser loop forever. |
| **Left factoring** | Rewriting A → αβ \| αγ as A → αA′, A′ → β \| γ so that a predictive parser can choose. |
| **FIRST(α) / FOLLOW(A)** | Terminals that can **begin** a string derived from α / terminals that can appear **immediately after** A ($ for the start symbol). |
| **Backtracking** ⚠ | A top-down parser undoes a failed production choice, resets the input pointer and tries another production. |

## L8 · Arden's theorem → [[08.00 Arden's Theorem and RE-FA Conversion]]

| Term | Definition |
|---|---|
| **Arden's theorem** | Let P and Q be REs over Σ. If **P does not contain ε**, then **R = Q + RP** has the **unique** solution **R = QP\***. |
