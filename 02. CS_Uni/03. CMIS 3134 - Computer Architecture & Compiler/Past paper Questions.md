Source papers analysed (all "Answer 6 of 8 questions", each Q worth 100 marks split into a–d parts):

- **2019/2020** (exam sat June 2022)
- **2020/2021** (exam sat June/July 2023)
- **2021/2022** (exam sat Jan/Feb 2024)
- **2022/2023** (exam sat Feb/March 2025)

Format used below: **Year – Q#(part)** — short description of what was asked.

---

## 1. Introduction to Compilers

### What is a Compiler?

- **2019/2020 – Q1(a)** Compare and contrast a Compiler and an Assembler
- **2020/2021 – Q1(a)** "A compiler converts low-level→assembly, an assembler converts high-level→machine-level" — agree/justify
- **2021/2022 – Q1(a)** What is a compiler and how does it differ from an Interpreter and an Assembler?
- **2022/2023 – Q1(a)** Primary role of a compiler in programming
- **2022/2023 – Q1(c)** Challenges programmers would face if compilers did not exist

### Compiler Design

- **2020/2021 – Q2(a)** Briefly explain how a C compiler executes a simple C program on a host machine
- **2021/2022 – Q1(b)** Briefly explain how a C compiler executes a simple program on a host machine
- **2022/2023 – Q2(a)** Why is optimization considered a critical task for compilers?
- **2022/2023 – Q2(d)** Could a single tool replace the compiler, assembler, linker and loader? Justify

### High-Level Language and Machine Language

- **2020/2021 – Q1(a)** Statement about compiler/assembler converting low-level vs high-level vs machine-level language — agree/justify

---

## 2. Language Processing System

### Overall Program Translation Process

- **2019/2020 – Q1(d)** Discuss the workflow of the Language Processing System with inputs/outputs
- **2021/2022 – Q1(d)** Discuss the workflow of a Language Processing System with inputs/outputs
- **2022/2023 – Q2(c)** How the Language Processing System bridges human-readable code and hardware operations

### Preprocessor

- **2020/2021 – Q1(c)** Briefly explain the usages of the Pre-Processor in a Language Processing System

### Compiler / Interpreter (as LPS components)

- **2021/2022 – Q1(a)** Compiler vs Interpreter vs Assembler (see also "What is a Compiler?" above)

### Assembler

- **2019/2020 – Q1(a)**, **2020/2021 – Q1(a)**, **2021/2022 – Q1(a)** (all listed above — compiler vs assembler)

### Linker

- **2021/2022 – Q2(a)** What are the uses of having a Linker and a Loader in the execution process?

### Loader

- **2021/2022 – Q2(a)** (same question as above, covers both Linker and Loader)

---

## 3. Phases of a Compiler

### General "phases/steps of compilation" overview

- **2019/2020 – Q2(a)** List the main steps of the compilation process
- **2020/2021 – Q1(d)** Explain the phases of a compiler with a suitable diagram
- **2021/2022 – Q2(b)** What are the main phases of the compilation process?
- **2022/2023 – Q2(b)** What are the main phases of the compilation process?

### Lexical Analysis

- **2019/2020 – Q1(b)** Perform lexical analysis to generate tokens of a given C program
- **2019/2020 – Q2(b)** Differentiate Lexical Analysis and Syntax Analysis
- **2020/2021 – Q1(b)** Perform a lexical analyzer to generate tokens of a given program
- **2021/2022 – Q1(c)** Perform a lexical analyzer to generate tokens of a given program
- **2022/2023 – Q1(d)** Perform a lexical analyzer to generate tokens of a given program

### Syntax Analysis

- **2019/2020 – Q2(b)** Differentiate Lexical Analysis and Syntax Analysis
- **2021/2022 – Q2(c)** "Syntax Analysis checks parse-tree rules; Semantic Analysis checks token arrangement vs grammar" — agree/justify

### Semantic Analysis

- **2021/2022 – Q2(c)** (same statement-justification question, covers Semantic Analysis too)

### Intermediate Code Generation

- **2021/2022 – Q2(d)(i)** Explain Intermediate Code Generation with its use

### Code Optimization

- **2021/2022 – Q2(d)(ii)** Explain Code Optimization with its use
- **2022/2023 – Q2(a)** Why is optimization a critical task for compilers?

### Code Generation

- **2021/2022 – Q2(d)(iii)** Explain Code Generation with its use

### Symbol Table

- **2019/2020 – Q1(c)** Briefly explain the use of having a Symbol Table in compilers
- **2021/2022 – Q2(d)(iv)** Explain the Symbol Table with its use

---

## 4. Regular Expressions

### Regular Languages (regular grammar vs regular language vs RE)

- **2020/2021 – Q2(b)** Differentiate between Regular Grammar and Regular Language
- **2021/2022 – Q3(a)** What is a Regular Expression? How do regular grammar and regular language differ from a RE?
- **2022/2023 – Q3(a)** Same question as above

### Regular Expression Notation and Rules (valid tokens: *, +, ranges, etc.)

- **2020/2021 – Q2(c)** Explain valid tokens: x*, x⁺, [0-5], (5-9)
- **2021/2022 – Q3(b)** Explain valid tokens: 1⁺, 1*, [a–g], (M–W)
- **2022/2023 – Q3(b)** Explain valid tokens: a⁺, s*, [1–7], (A–T)

### Precedence and Associativity of Regular Expressions

- **2019/2020 – Q2(d)** Prove that (0110+01)(10)* is equal to 01(10)* (RE identity/equivalence proof)

### Regular Expressions for Tokens

- **2019/2020 – Q2(c)** Provide example strings generated from given REs
- **2020/2021 – Q2(d)** Provide example strings/final output for given REs
- **2021/2022 – Q3(c)** Represent tokens (decimal value, identifier, NIC no. with x/v, Gmail address) using REs
- **2021/2022 – Q3(d)** Construct REs for described languages + give example strings/output
- **2022/2023 – Q3(c)** Represent tokens (decimal value, identifier, NIC no., Gmail address) using REs
- **2022/2023 – Q3(d)** Construct REs for described languages + give example strings/output

### Recursive Definition of Regular Expressions

- _No question in any of the four papers explicitly tests the recursive/inductive definition of REs._

---

## 5. Automata

### Automaton (what is a finite automaton?)

- **2019/2020 – Q3(a)**, **2020/2021 – Q3(a)**, **2021/2022 – Q4(a)** — "What is meant by the finite automaton?"
- **2022/2023 – Q4(a)** What does "Automata" mean, and why is it significant in computing?

### Fundamental Automata Terminology (alphabet, string, length, language, 5-tuple, cardinality)

- **2020/2021 – Q4(b)** Explain: Alphabet, String, Length of a String, Language (with examples)
- **2020/2021 – Q6(b)** Power of alphabet (Σ) and cardinality of alphabet (Σ)
- **2021/2022 – Q5(b)** Explain: Alphabet, String, Length of a String, Language
- **2022/2023 – Q4(c)** In the 5-tuple (Q, Σ, q₀, F, δ), what role does the transition function (δ) play?

### Kleene Star, Kleene Plus, and Languages

- **2020/2021 – Q6(c)** Prove Σ* is the set of all possible strings of all lengths over {p, q}
- **2022/2023 – Q4(b)** What is Kleene Star (Σ*) and how does it differ from Kleene Closure/Plus (Σ⁺)?
- **2022/2023 – Q5(a)** Show that Σ* is the set of all possible strings (i.e., language)

### Types of Finite Automata (DFA, NDFA/NFA)

- **2019/2020 – Q3(b)** Can a FSM be designed to accept all valid Java code / all Java code that never loops infinitely?
- **2019/2020 – Q3(c)** Compare and contrast DFA and NDFA
- **2019/2020 – Q4(a)** Provide a formal definition for an NDFA
- **2020/2021 – Q3(b), (c)** Same FSM-scenario and DFA-vs-NDFA questions
- **2020/2021 – Q4(a)** Provide a formal definition for a DFA
- **2021/2022 – Q4(b)** Same FSM-scenario question
- **2021/2022 – Q4(c)** Categorize the finite automata machines with examples
- **2021/2022 – Q5(a)** Provide a formal definition for a DFA
- **2022/2023 – Q5(b)** Why might NFAs be computationally easier to design than DFAs, even though DFAs are simpler to execute?
- **2022/2023 – Q5(c)** Compare and contrast a DFA and an NFA

### Acceptors, Classifiers, and Transducers

- _No question in any of the four papers explicitly names/tests this classification._

### Acceptability by DFA and NDFA

- **2019/2020 – Q3(d)** Check if given strings are acceptable by a given automaton (show steps)
- **2019/2020 – Q4(b)** Design an NDFA (all a's/b's, ≥4 a's, begins with b)
- **2019/2020 – Q4(c)** Construct a DFA (does not contain "01011")
- **2020/2021 – Q3(d)** Check string acceptability by given automaton
- **2020/2021 – Q4(c)** Design an NDFA (second-last symbol always 'a')
- **2020/2021 – Q4(d)** Construct DFAs (4 language variants: no "xyxyy", even #1's, contains "acb", contains "aababb")
- **2021/2022 – Q4(d)** Check string acceptability by given automaton
- **2021/2022 – Q5(c)** Design an NDFA (third-last symbol always 'b')
- **2021/2022 – Q5(d)** Construct DFAs (4 variants: no "xxyy", odd #1's, contains "acb", contains "abababbb")
- **2022/2023 – Q4(d)** Check string acceptability by given automaton
- **2022/2023 – Q5(d)** Construct DFAs (4 variants: start with "abc", no "pqp", even 0's & odd 1's, ends with "111" with ≥4 symbols)

---

## 6. NDFA to DFA Conversion / DFA Minimization

### NDFA to DFA Conversion

- **2019/2020 – Q4(d)** Find the equivalent DFA for a given NDFA (transition table given)
- **2020/2021 – Q6(d)** Find the equivalent DFA for a given NDFA (transition table given)
- **2021/2022 – Q6(c)** Find the equivalent DFA for an NDFA machine (built in part b)
- **2022/2023 – Q6(c)** Find the equivalent DFA for an NDFA machine (built in part b)

### DFA Minimization Using Myhill–Nerode Theorem

- **2019/2020 – Q6(d)** Minimize a given DFA using the Myhill–Nerode Theorem
- **2020/2021 – Q8(d)** Minimize a given DFA using the Myhill–Nerode Theorem
- **2021/2022 – Q6(d)** Minimize the DFA (from Q6c) using the Myhill–Nerode Theorem
- **2022/2023 – Q6(d)** Minimize the DFA (from Q6c) using the Myhill–Nerode Theorem

### DFA Minimization Using Equivalence Theorem

- **2019/2020 – Q5(d)** Minimize a given DFA using the Equivalence Theorem
- **2020/2021 – Q5(d)** Minimize a given DFA using the Equivalence Theorem
- **2021/2022 – Q7(d)** Minimize a given DFA using the Equivalence Theorem
- **2022/2023 – Q7(d)** Minimize a given DFA using the Equivalence Theorem

### Partition Method for DFA Minimization

- _Not explicitly named in any paper (the Equivalence-Theorem questions above are the closest match, since that method uses partition refinement, but "Partition Method" is never used as the exact term)._

---

## 7. Moore and Mealy Machines

### Mealy Machine (design)

- **2022/2023 – Q7(a)** Design a Mealy machine for a binary sequence (output M/N/O based on substrings "01"/"10")

### Moore Machine (design)

- **2021/2022 – Q7(a)** Design a Moore machine for a binary sequence (outputs a/b/c based on substrings "101"/"110")

### General Compare/Contrast Moore vs Mealy

- **2019/2020 – Q6(a)**, **2020/2021 – Q8(a)** Compare and contrast the Moore machine and the Mealy machine

### Moore → Mealy Machine Conversion

- **2019/2020 – Q6(b)** Convert a given Moore machine (table) into a Mealy machine
- **2020/2021 – Q8(b)** Convert a given Moore machine (table) into a Mealy machine
- **2021/2022 – Q7(b)** Convert a given Moore machine (state diagram) into a Mealy machine
- **2022/2023 – Q7(b)** Convert a given Moore machine (table) into a Mealy machine

### Mealy → Moore Machine Conversion

- **2019/2020 – Q6(c)** Convert a given Mealy machine (table) into a Moore machine
- **2020/2021 – Q8(c)** Convert a given Mealy machine (table) into a Moore machine
- **2021/2022 – Q7(c)** Convert a given Mealy machine (state diagram) into a Moore machine
- **2022/2023 – Q7(c)** Convert a given Mealy machine (table) into a Moore machine

---

## Notes on scope

- Arden's Theorem and RE ⇄ Finite Machine conversion appear every year (grouped with the minimization question, e.g. 2019/2020 Q5, 2020/2021 Q5, 2021/2022 Q6, 2022/2023 Q6) but weren't part of your requested topic list, so they're only mentioned here for completeness.
- Context-Free Grammars, ambiguity, derivations, and parsing (LL(1), LR(0), top-down/bottom-up parsers — from `7__Syntax_Analysis.pdf`) also appear every year (2019/2020 Q7–Q8, 2020/2021 Q7, 2021/2022 Q8, 2022/2023 Q8) but are outside the topic list you gave me. Let me know if you'd like those broken down the same way.
- One uploaded file (`CMIS_3134__20212022.pdf`) actually contains a different course's paper (CMIS 3114 – Data Communication & Computer Networks) bundled in front of the real CMIS 3134 paper; I skipped that unrelated content and only analyzed the Compiler Design questions.

## Pattern takeaways for revision

- **Every single year** asks: lexical-analyzer token generation, phases/steps of compilation, DFA/NDFA acceptability check, NDFA→DFA conversion, DFA minimization (one year Myhill-Nerode, one year Equivalence Theorem — both always appear across the two relevant questions), and a Moore⇄Mealy conversion pair. These are near-guaranteed question types.
- **Frequently repeated verbatim or near-verbatim** across years: the "compiler vs assembler" question, the FSM-for-Java-code scenario question, valid-token explanation, and the "represent tokens with REs" (decimal/identifier/NIC/email) question.