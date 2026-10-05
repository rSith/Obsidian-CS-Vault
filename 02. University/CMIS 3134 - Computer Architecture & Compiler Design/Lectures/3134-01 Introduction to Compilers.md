---
type: lecture
course: CMIS 3134
lecture: 1
status: stub
priority: Tier 2
tags: [compilers]
aliases: [Introduction to Compilers]
---
# Introduction to Compilers
> [!info] CMIS 3134 · Lecture 1
> [[CMIS 3134 Course Home]] · Exam prep: [[3134 Topic Map & Past Papers|Topic map]] · Study priority: **Tier 2**
> [[3134-02 Regular Expressions]] →

> [!tip]- Exam focus (5 papers, 2019/20–2023/24)
> - **Every paper** — Phases of the compiler / compilation process (20–40 marks)
> - **Every paper** — Language Processing System (20–40 marks)
> - ⚠️ **De-compiler** (2023/24) isn't in the notes; it was one small item.

> [!question]- Past paper questions on this lecture (31)
> - **2019/20 Q1(a)** · 20 marks — Compare and contrast **Compiler** and **Assembler**
> - **2019/20 Q1(c)** · 20 marks — Briefly explain the use of having a **Symbol Table** in compilers
> - **2019/20 Q1(d)** · 40 marks — Discuss the workflow of the **Language Processing System** with its inputs and outputs
> - **2019/20 Q2(a)** · 20 marks — List down the main steps of the **compilation process**
> - **2019/20 Q2(b)** · 20 marks — Differentiate **Lexical Analysis** and **Syntax Analysis**
> - **2020/21 Q1(a)** · 20 marks — *"A compiler converts Low-level language to assembly language. An assembler converts High-level language to machine-level language."* Do you agree? Justify *(both definitions are deliberately swapped/wrong)*
> - **2020/21 Q1(c)** · 20 marks — Briefly explain the usages of the **Pre-Processor** in a Language Processing System
> - **2020/21 Q1(d)** · 40 marks — Explain the **phases of a compiler** with a suitable diagram
> - **2020/21 Q2(a)** · 20 marks — Briefly explain how a **C compiler executes** a simple C program on a host machine
> - **2021/22 Q1(a)** · 20 marks — What is meant by a **compiler** and how does it differ from an **Interpreter** and an **Assembler**?
> - **2021/22 Q1(b)** · 20 marks — Briefly explain how a **C compiler executes** a simple program on a host machine
> - **2021/22 Q1(d)** · 40 marks — Discuss the workflow of a **Language Processing System** with its inputs and outputs
> - **2021/22 Q2(a)** · 20 marks — What are the uses of having a **Linker** and a **Loader** in the execution process?
> - **2021/22 Q2(b)** · 20 marks — What are the main **phases of the compilation process**?
> - **2021/22 Q2(c)** · 20 marks — *"Syntax Analysis checks whether the constructed parse tree follows the rules of language and Semantic Analysis checks whether the token arrangements follow the source code grammar."* Do you agree? Justify *(the two definitions are swapped)*
> - **2021/22 Q2(d)** · 40 marks — Explain with their uses: (i) **Intermediate Code Generation** (ii) **Code Optimization** (iii) **Code Generation** (iv) **Symbol Table**
> - **2022/23 Q1(a)** · 20 marks — What is the primary role of a **compiler** in programming?
> - **2022/23 Q1(b)** · 20 marks — List the **three analyses performed at the front end** of a compiler and explain how they contribute to translation
> - **2022/23 Q1(c)** · 20 marks — What challenges would programmers face if **compilers did not exist**?
> - **2022/23 Q2(a)** · 20 marks — Why is **optimization** considered a critical task for compilers?
> - **2022/23 Q2(b)** · 20 marks — What are the main phases of the **compilation process**?
> - **2022/23 Q2(c)** · 20 marks — Briefly explain how the **Language Processing System** bridges the gap between human-readable code and hardware operations
> - **2022/23 Q2(d)** · 40 marks — Could a single tool replace the **compiler, assembler, linker, and loader**? Justify and explain the challenges
> - **2023/24 Q1(a)** · 20 marks — Briefly explain why computers **cannot directly execute or understand** high-level programming languages such as C or Python
> - **2023/24 Q1(b)** · 20 marks — Briefly explain **Lexical Analysis, Syntax Analysis, Semantic Analysis, and Symbol Table**, highlighting how each phase contributes to the **detection and reporting of errors**
> - **2023/24 Q1(c)** · 20 marks — For `int x = "10" + 5;` — identify the **compiler phase that detects the error** and justify why/how
> - **2023/24 Q1(d)** · 40 marks — Embedded system with limited memory and processing power: (i) why is **code optimization** especially important? (ii) which compiler phases play the most critical role in improving execution speed and memory usage?
> - **2023/24 Q2(a)** · 20 marks — With the aid of a **diagram**, explain the **Language Processing System** used to execute a program
> - **2023/24 Q2(b)** · 20 marks — Distinguish between: (i) **Compiler** (ii) **De-compiler** (iii) **Interpreter** (iv) **Assembler** ⚠️
> - **2023/24 Q2(c)** · 20 marks — What is **Intermediate Code Generation**? Why do compilers generate intermediate code instead of directly producing machine code?
> - **2023/24 Q2(d)** · 40 marks — A C program **compiles successfully but produces incorrect output**: (i) which compiler phase(s) might still have worked correctly and why? (ii) what types of errors are **not detected by the compiler**?

## Topic checklist
- [ ] **1.1 What is a Compiler** — Translates one language to another without changing meaning; target code must be efficient & optimized in time and space
- [ ] **1.2 Compiler Design Principles** — Translation & optimization process; error detection & recovery; **front end** = lexical + syntax + semantic analysis; **back end** = code generation + optimization
- [ ] **1.3 Hardware / Software Relationship** — Hardware understands electronic charge ≡ binary; binary alphabet {0, 1}; why writing binary directly is impractical
- [ ] **1.4 Language Processing System** — Full pipeline: Source Code → Preprocessor → Compiler → Assembler → Linker → Loader → Memory; inputs and outputs at each stage
- [ ] **1.5 Execution of a C program on a host machine** — 5 steps: (1) user writes C, (2) compiler → assembly, (3) assembler → machine code/object, (4) linker → executable, (5) loader → memory & execute
- [ ] **1.6 Preprocessor** — Macro-processing, augmentation, file inclusion, language extension
- [ ] **1.7 Interpreter** — Statement-by-statement translation & execution; stops at first error — contrasted with compiler (whole program, many passes, reports all errors)
- [ ] **1.8 Assembler** — Assembly → machine code; output = object file (machine instructions + placement data)
- [ ] **1.9 Linker** — Merges object files into an executable; locates referenced modules/routines; resolves absolute references
- [ ] **1.10 Loader** — Part of OS; loads executable into memory; calculates program size; creates memory space; initializes registers
- [ ] **1.11 Phases of a Compiler** — Sequence where each phase consumes previous phase's output
    - [ ] 1.11.1 Lexical Analysis — Text scanner; character stream → lexemes → tokens `<token-name, attribute-value>`
    - [ ] 1.11.2 Syntax Analysis — Tokens → parse tree / syntax tree; token arrangements checked against source-code grammar
    - [ ] 1.11.3 Semantic Analysis — Checks parse tree follows language rules; type compatibility; identifier declaration-before-use; output = annotated syntax tree
    - [ ] 1.11.4 Intermediate Code Generation — Code for an abstract machine; between high-level and machine language; eased translation to target
    - [ ] 1.11.5 Code Optimization — Removes unnecessary code lines; re-arranges statement sequence; speeds execution without wasting CPU/memory
    - [ ] 1.11.6 Code Generation — Optimized intermediate code → target machine language; re-locatable machine code
    - [ ] 1.11.7 Symbol Table — Data structure maintained across **all** phases; identifier names + types; fast search/retrieval; scope management

## Notes
*Work through the checklist above. Write each topic in your own words, then tick it off.*


## Key definitions
> [!note] Term
>

## Summary (in my own words)


## Open questions
- [ ]
