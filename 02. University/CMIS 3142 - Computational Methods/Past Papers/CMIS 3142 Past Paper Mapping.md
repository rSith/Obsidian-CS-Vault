---
type: past-paper-index
course: CMIS 3142
status: complete
tags: [cmis3142, past-paper, mapping]
aliases: [CMIS 3142 Past Paper Map, CMIS 3142 Question Mapping]
---
# CMIS 3142 · Past Paper Mapping (lesson → question)

> [!info] Back to [[00. CMIS 3142 Course Overview|Course Overview]] · Question by question: [[CMIS 3142 Papers by Year]]
> Sources: the four papers in the project (June/July **2023** = AY 2020/21, Jan/Feb **2024** = AY 2021/22, Feb/Mar **2025** = AY 2022/23, Feb **2026** = AY 2023/24). All papers: **6 questions, answer any 4, 2 hours.** 2024: calculators **not** permitted; 2025, 2026: permitted; 2023: not stated. Marks printed only on the 2023 and 2026 papers.

## 1. Lesson → question tree
```text
L01 Floating Point Numbers (IEEE 754) ............ 4/4 papers  🔴
    ├── 2023 Q1(a) convert 654, −456, 512, −128 → single           (8)
    ├── 2023 Q1(b) single vs double                                 (4)
    ├── 2023 Q1(c) Java decimal → double                            (8)
    ├── 2023 Q1(d) Java double → single                             (5)
    ├── 2024 Q1(a) ranges single/double
    ├── 2024 Q1(b) normalised vs denormalised
    ├── 2024 Q1(c) Java decimal ↔ double
    ├── 2024 Q1(d) Java single ↔ double + assumptions
    ├── 2025 Q1(a) overflow and underflow
    ├── 2025 Q1(b) special feature of denormalised
    ├── 2025 Q1(c) convert 256, 1023, 364 → single
    ├── 2025 Q1(d) Java octal ↔ hex
    ├── 2026 Q1(a) biased exponent, why bias                       (10)
    ├── 2026 Q1(b) single bits → decimal (×2)                      (10)
    ├── 2026 Q1(c) −13.625 → double                                 (5)
    ├── 2026 Q1(d) special values ±0, ±∞, NaN                      (10)
    └── 2026 Q1(e) Java decimal → double                           (15)

L02 Error Analysis ................................ 4/4 papers  🔴
    ├── 2023 Q3(a) forward & backward error                         (5)
    ├── 2023 Q3(b) rounding impact on grades & GPA                  (8)
    ├── 2023 Q3(c) minimise the loss                                (4)
    ├── 2023 Q3(d) Java floor/ceil                                  (8)
    ├── 2024 Q2(a) relative & true fractional relative error
    ├── 2024 Q2(b) max/min rounding error in GPA + minimise
    ├── 2025 Q2(a) rounding impacts in GPA
    ├── 2025 Q2(b) chopping impacts in GPA
    ├── 2025 Q2(c) Java GPA with round-off & chopping errors
    ├── 2026 Q5(c) rounding: 500→100 scaling, grading, GPA          (9)
    └── 2026 Q5(d) solutions                                        (6)

L03 Matrix Programming ............................ 4/4 papers  🔴
    ├── 2023 Q4(a) symmetric matrix class (element, Store_Element, Multiply)  (12)
    ├── 2023 Q4(b) enhance to sparse symmetric (discussion)         (5)
    ├── 2023 Q4(c) row × column, column × row (Java)                (8)
    ├── 2024 Q3    6 matrix classes with inheritance & polymorphism (whole question)
    ├── 2025 Q3(a) sparse symmetric representation
    ├── 2025 Q3(b–e) read/store, element, display stored, display full (Java)
    ├── 2026 Q2(a) sparse symmetric representation                 (10)
    ├── 2026 Q2(b) single Java program: store, display, element, multiply, count  (25)
    ├── 2026 Q2(c) changes for general sparse                      (15)
    └── 2026 Q6(a) symmetric matrix representation                 (10)

L04 Gaussian Elimination .......................... 4/4 papers  🔴
    ├── 2023 Q6(a) describe GE                                      (8)
    ├── 2023 Q6(b) contractor → equations (8) → solve by GE (9)
    ├── 2024 Q5(a) develop an algorithm for a linear system
    ├── 2024 Q6(a) describe GE
    ├── 2024 Q6(b) contractor (same as 2023) → equations → GE
    ├── 2025 Q6(a) describe GE
    ├── 2025 Q6(b) GE program (any language)
    ├── 2026 Q5(a) GE program (any language)                       (15)
    └── 2026 Q5(b) solve 4×4 by GE                                 (20)

L05 Jacobi & Gauss-Seidel ......................... 3/4 papers  🔴
    ├── 2024 Q4(a) Jacobi algorithm
    ├── 2024 Q4(b) Gauss-Seidel algorithm
    ├── 2024 Q4(c)(d) solve by Jacobi & GS (system diverges!)
    ├── 2024 Q4(e) compare & contrast
    ├── 2025 Q4(a) main difference
    ├── 2025 Q4(b) fruit scenario → Jacobi (10 it.) → GS (6 it.) → performance
    ├── 2025 Q4(c) modify Jacobi program into GS
    ├── 2026 Q4(c) GS from (1,2,3)                                 (15)
    └── 2026 Q4(d) GS algorithm                                    (10)

L06 LU & Cholesky ................................. 4/4 papers  🔴
    ├── 2023 Q5(a) Cholesky algorithm for AX = B                    (9)
    ├── 2023 Q5(b) solve the lecture 4×4                           (10)
    ├── 2023 Q5(c) limitations                                      (6)
    ├── 2024 Q5(c) solve 25/15/−5 system (b = 35, 33, 6)
    ├── 2025 Q5(b) Java Cholesky + conditions
    ├── 2025 Q5(c) solve 8/2/3 system
    ├── 2026 Q6(b) Java Cholesky with symmetric storage            (15)
    ├── 2026 Q6(c) specialty of Cholesky                            (5)
    └── 2026 Q6(d) solve 25/15/−5 system (b = 70, 66, 12)          (20)

L07 Fixed-Point Iteration ......................... 1/4 papers  🟡
    └── 2025 Q6(c) solve 4x³ − 3x² − 5x − 10 = 0

L08 SOR ........................................... 3/4 papers  🔴
    ├── 2024 Q5(b) SOR, ω = 1.25, (0,0,0)
    ├── 2025 Q5(a) SOR, ω = 1.25, (0,0,0)
    ├── 2026 Q4(a) SOR, ω = 1.25, (0,0,0)                          (10)
    └── 2026 Q4(b) Java SOR                                        (15)

L09 Finite Difference Method ...................... 3/4 papers  🔴
    ├── 2023 Q2(a) derive ∂²f/∂x∂y (central, forward, backward)     (9)
    ├── 2023 Q2(b) truncation error comment                         (3)
    ├── 2023 Q2(c) FD table y = x³ + 2x + 1, x = 1..5               (6)
    ├── 2023 Q2(d) algorithm for the table, x = 1..25               (7)
    ├── 2024 Q2(c) Δe^{ax}, Δ²e^x, Δlog x
    ├── 2024 Q2(d) Δ²(1/x)
    ├── 2026 Q3(a) forward/backward/central via Taylor             (10)
    ├── 2026 Q3(b) compound interest difference equation            (5)
    ├── 2026 Q3(c) compound interest FD table                      (10)
    └── 2026 Q3(d) Java FD table to 2nd difference                 (25)
```

Past-paper notes per lesson: [[PP 01 - Floating Point Numbers]] · [[PP 02 - Error Analysis]] · [[PP 03 - Matrix Programming]] · [[PP 04 - Gaussian Elimination]] · [[PP 05 - Jacobi and Gauss-Seidel]] · [[PP 06 - LU and Cholesky]] · [[PP 07 - Fixed Point Iteration]] · [[PP 08 - Successive Over-Relaxation]] · [[PP 09 - Finite Difference Method]]

## 2. Frequency of each lesson
| Lesson | 2023 | 2024 | 2025 | 2026 | Papers | Priority |
|---|---|---|---|---|---|---|
| [[01.00 Floating Point Numbers\|L01 IEEE 754]] | Q1 | Q1 | Q1 | Q1 | **4/4** | 🔴 |
| [[02.00 Error Analysis\|L02 Error analysis]] | Q3 | Q2(a)(b) | Q2 | Q5(c)(d) | **4/4** | 🔴 |
| [[03.00 Matrix Programming\|L03 Matrix programming]] | Q4 | Q3 | Q3 | Q2, Q6(a) | **4/4** | 🔴 |
| [[04.00 Gaussian Elimination\|L04 Gaussian elimination]] | Q6 | Q5(a), Q6 | Q6(a)(b) | Q5(a)(b) | **4/4** | 🔴 |
| [[05.00 Iterative Methods - Jacobi and Gauss-Seidel\|L05 Jacobi/GS]] | ✗ | Q4 | Q4 | Q4(c)(d) | **3/4** | 🔴 |
| [[06.00 LU Decomposition and Cholesky\|L06 Cholesky]] | Q5 | Q5(c) | Q5(b)(c) | Q6(b)–(d) | **4/4** | 🔴 |
| [[07.00 Non-linear Equations - Fixed Point Iteration\|L07 Fixed point]] | ✗ | ✗ | Q6(c) | ✗ | 1/4 | 🟡 |
| [[08.00 Successive Over-Relaxation\|L08 SOR]] | ✗ | Q5(b) | Q5(a) | Q4(a)(b) | **3/4** | 🔴 |
| [[09.00 Finite Difference Method\|L09 Finite differences]] | Q2 | Q2(c)(d) | ✗ | Q3 | **3/4** | 🔴 |

**Priority rule used:** 🔴 = topic in 3–4 of the 4 papers; 🟡 = 1–2 papers; 🟢 = in the lectures/syllabus but never examined.

## 3. Repeated questions (asked more than once)
| Question type | Times | Where |
|---|---|---|
| GPA / marks rounding scenario | **4** | 2023 Q3(b)(c), 2024 Q2(b), 2025 Q2, 2026 Q5(c)(d) |
| Sparse symmetric representation + Java | **4** | 2023 Q4(b), 2024 Q3, 2025 Q3, 2026 Q2 |
| Cholesky solved by hand | **4** | 2023 Q5(b), 2024 Q5(c), 2025 Q5(c), 2026 Q6(d) |
| Decimal → IEEE double in Java | 3 | 2023 Q1(c), 2024 Q1(c), 2026 Q1(e) |
| Hand conversion to/from IEEE 754 | 3 | 2023 Q1(a), 2025 Q1(c), 2026 Q1(b)(c) |
| "Describe Gaussian elimination" | 3 | 2023 Q6(a), 2024 Q6(a), 2025 Q6(a) |
| Symmetric matrix storage/class | 3 | 2023 Q4(a), 2024 Q3, 2026 Q6(a) |
| Cholesky algorithm / program | 3 | 2023 Q5(a), 2025 Q5(b), 2026 Q6(b) |
| Cholesky conditions / limitations / specialty | 3 | 2023 Q5(c), 2025 Q5(b), 2026 Q6(c) |
| SOR by hand, ω = 1.25, start 0 | 3 | 2024 Q5(b), 2025 Q5(a), 2026 Q4(a) |
| Gauss-Seidel by hand | 3 | 2024 Q4(d), 2025 Q4(b)(iii), 2026 Q4(c) |
| Jacobi/GS algorithms | 3 | 2024 Q4(a)(b), 2026 Q4(d) (+ 2025 Q4(c) program change) |
| GE program / algorithm | 3 | 2024 Q5(a), 2025 Q6(b), 2026 Q5(a) |
| Single ↔ double Java | 2 | 2023 Q1(d), 2024 Q1(d) |
| Contractor scenario (**identical**) | 2 | 2023 Q6(b), 2024 Q6(b) |
| Jacobi by hand | 2 | 2024 Q4(c), 2025 Q4(b)(ii) |
| Jacobi vs GS comparison | 2 | 2024 Q4(e), 2025 Q4(a)/(b)(iv) |
| Forward difference table | 2 | 2023 Q2(c)(d), 2026 Q3(c)(d) |
| Normalised vs denormalised / denormal feature | 2 | 2024 Q1(b), 2025 Q1(b) |
| **Same Cholesky matrix** 25/15/−5 | 2 | 2024 Q5(c), 2026 Q6(d) |

## 4. Questions taken straight from the lecture materials
| Exam question | Lecture item |
|---|---|
| 2023 Q2(c): table for $y = x^3 + 2x + 1$ | L09 Example 5.2 (identical) |
| 2023 Q5(b): 4×4 Cholesky | L06 Cholesky handout example (identical, $x = (1, 2, 1, -1)$) |
| 2024 Q2(c): $\Delta e^{ax}$, $\Delta^2e^x$, $\Delta\log x$ | L09 Example 5.4 (identical) |
| SOR with ω = 1.25 (every year) | L08 slide 4 exercise uses ω = 1.25 |

## 5. Questions that appeared only once
- 2023: forward/backward error; `floor`/`ceil` in Java; row × column vectors; Cholesky limitations; mixed-derivative derivation; table algorithm for $x = 1..25$.
- 2024: IEEE ranges; relative error; class hierarchy with inheritance/polymorphism; Δ operator questions.
- 2025: overflow/underflow; octal ↔ hex Java; chopping error in GPA; GPA Java program; modifying Jacobi → GS; fixed-point iteration.
- 2026: biased exponent; IEEE bits → decimal; special values; general sparse changes; FD definitions via Taylor; compound-interest model, table and Java; SOR Java; Cholesky specialty.

## 6. Never examined (🟢), though in the lectures/syllabus
Newton–Raphson · LU decomposition on its own · machine precision · accuracy vs precision · well-posed problems · conditioning and stability · parallel Gaussian elimination · second-derivative formulas alone · consistency/stability/convergence of FD schemes · ICCG and the Jacobian matrix (in the syllabus, **no lecture material**).

## 7. Paper structure pattern
- **Q1 is always IEEE 754.** One question is always matrix programming, one Gaussian elimination, one Cholesky (often combined with SOR or matrix storage).
- Most topics are examined in three ways: **describe** the method, **solve by hand**, **write Java/algorithm**. In 2026 about 40% of marks were programming.
- Choosing 4 of 6: IEEE 754, Gaussian elimination and Cholesky are the most predictable; matrix programming is predictable in topic but always needs code.
