# CMIS 3142 – Computational Methods
## Lecture Topic Map & Past Paper Question Mapping

**Lecturer (per slides):** T. Arudchelvam, Dept. of CMIS
**Sources analysed:** 12 lecture materials (PDFs + the project slide decks for lectures that only exist as slides), 4 past papers (2023, 2024, 2025, 2026 exams)

---

## Contents

1. [Sources analysed](#1-sources-analysed)
2. [Part A – Module structure: topics & subtopics by lecture](#part-a--module-structure-topics--subtopics-by-lecture)
3. [Part B – Past paper question mapping](#part-b--past-paper-question-mapping)
   - [B1. 2023 exam (AY 2020/2021)](#b1-exam-junejuly-2023--academic-year-20202021)
   - [B2. 2024 exam (AY 2021/2022)](#b2-exam-janfeb-2024--academic-year-20212022)
   - [B3. 2025 exam (AY 2022/2023)](#b3-exam-febmar-2025--academic-year-20222023)
   - [B4. 2026 exam (AY 2023/2024)](#b4-exam-february-2026--academic-year-20232024)
4. [Part C – Exam focus analysis](#part-c--exam-focus-analysis)

---

## 1. Sources analysed

### Lecture materials

| Code | File | Format | Notes |
|---|---|---|---|
| **L01** | Lecture_01 – Floating Point Numbers | Slides (.pptx, 35 slides) | Only available as slides; included because exams test it heavily |
| **L02** | Lecture_02 – Error Analysis | Slides (.pptx, 23 slides) | Only available as slides; several slides are formula images |
| **L03** | Lecture_03 – Matrix Programming | PDF (15 pp) + slides | Programming aspects of matrix types |
| **L04** | Lecture_04 – Gaussian Elimination | Slides (.pptx) | Worked 3×3 example |
| **L04.0** | Lecture_4_0 – Gaussian Elimination (algorithm) | PDF (1 p) | Sequential GE pseudocode |
| **L04.1** | Lecture_4_1 – Gaussian Elimination Example | PDF (2 pp) | Worked 3×3 example (Hicham Boukili blog) |
| **L04.2** | Lecture_4_2 – Gaussian_Elimination.c | C source | GE program in C |
| **L05** | Lecture_05_A – Jacobi and Gauss-Seidel | PDF (7 pp) | Iterative methods + convergence theory |
| **L06** | Lecture_06 – LU Decomposition & Cholesky | Slides (.pptx, 5 slides) | LU idea; points to Kreyszig p. 855 for Cholesky |
| **L06.A** | Lecture_6 – Cholesky Algorithm | PDF (1 p) | Cholesky algorithm + fully worked 4×4 example |
| **L06.J** | Lecture_06 – Cholesky (Cholesky.java) | PDF (2 pp) | Java implementation (Sedgewick & Wayne) |
| **L07** | Lecture_07 – Fixed Point Iteration | PDF (6 pp, Kreyszig §19.2) + slides | Fixed-point iteration, Newton–Raphson |
| **L08** | Lecture_08 – Successive Over-Relaxation | Slides (.pptx, 4 slides) | Very thin: exercise only (ω = 1.25) |
| **L09** | Lecture_09 – Finite Difference | Slides (.pptx, 14 slides) | Taylor series, FD formulas, truncation error |
| **L09.E** | Lecture_09 – Finite Difference Method Example | PDF (3 pp) | Forward-difference tables, Δ operator examples |

### Past papers

| Code | File | Exam sitting | Academic year | Calculators |
|---|---|---|---|---|
| **P2023** | CMIS 3142 202-2021 2023 | June/July 2023 | 2020/2021 | Not stated |
| **P2024** | CMIS 3142 2021-2022 2024 | Jan/Feb 2024 | 2021/2022 | **Not permitted** |
| **P2025** | CMIS 3142 2022-2023 2025 | Feb/Mar 2025 | 2022/2023 | Permitted |
| **P2026** | CMIS 3142 2023-2024 feb 2026 | February 2026 | 2023/2024 | Permitted |

All papers: **6 questions, answer any 4, 2 hours.**

---

## Part A – Module structure: topics & subtopics by lecture

The module follows the official syllabus outline (shown on L01 slide 5 / L02 slide 2):

> **Data Representation & Computational Error** → **Systems of Linear Equations (programming aspects of matrices)** → **Direct methods (Gaussian Elimination, LU, Cholesky)** → **Iterative methods (Jacobi, Gauss-Seidel, SOR)** → **Non-linear equations (Fixed point, Newton–Raphson)** → **Finite Difference Method**

The topic IDs below (e.g. **T1.3**) are used throughout Part B.

### Unit 1 – Data Representation (L01: Floating Point Numbers)

| ID | Topic | Subtopics / key content |
|---|---|---|
| T1.0 | Course info | References (Jennings; Kreyszig; Hoole), assessment (CA 30% / exam 70%), aims, ILOs, syllabus |
| T1.1 | Number system conversions | Binary ↔ decimal (integer & fractional parts); repeated ×2 algorithm for fractions; non-terminating binaries (0.1₁₀ = 0.000110011…₂); **Assignment 01**: Java decimal↔binary, decimal↔octal, decimal↔hexadecimal |
| T1.2 | Scientific notation | Decimal and binary normalised scientific notation |
| T1.3 | Floating-point representation | Sign, exponent, mantissa; fixed size (32/64-bit); more exponent bits → range, more mantissa bits → accuracy |
| T1.4 | IEEE 754 single precision | 1 / 8 / 23 bits; normalised rule (−1)^S × 1.F × 2^(E−127); worked examples (+101101.101, 0.75 → 0x3F400000, 0.1 → 0x3DCCCCCD and rounding of last digit); **Assignment 2**: Java decimal ↔ IEEE 754 single |
| T1.5 | Biased exponent | Why a bias is used (to represent negative exponents without two's complement); bias 127 / 1023; "hidden 1" |
| T1.6 | IEEE 754 double precision | 1 / 11 / 52 bits; (−1)^S × 1.F × 2^(E−1023) |
| T1.7 | Special values | ±0, ±∞, NaN; reserved exponent patterns E = 00…0 and E = 11…1 |
| T1.8 | Denormalised numbers | No hidden 1; (−1)^S × 0.F × 2^−126 (single) / 2^−1022 (double); allow numbers very close to 0 |
| T1.9 | IEEE 754 summary & ranges | Encoding summary table; ranges for normalised/denormalised in single & double; "interesting numbers" (smallest/largest denorm, smallest/largest normalised) |
| T1.10 | Overflow and underflow | Definitions, what the result becomes (0 / ±∞), practical implications |

### Unit 2 – Computational Error (L02: Error Analysis)

| ID | Topic | Subtopics / key content |
|---|---|---|
| T2.1 | Accuracy vs precision | Definitions |
| T2.2 | Error measures | True error, (true fractional) relative error, approximate relative error (slides 5–6, formula images) |
| T2.3 | Error types | Round-off (rounding) error; chopping / truncation error; chopping vs rounding comparison |
| T2.4 | Machine precision | Machine epsilon |
| T2.5 | Round-off with arithmetic | Accumulation in large computations; adding a large and small number (0.001 + 4000 with 4-digit mantissa) |
| T2.6 | Well-posed problems | Hadamard's three conditions (existence, uniqueness, continuous dependence) |
| T2.7 | Forward & backward error | Definitions and example |
| T2.8 | Conditioning of a problem | Well- vs ill-conditioned; condition number |
| T2.9 | Stability of an algorithm | Stable algorithm = exact solution of a nearby problem; accuracy = conditioning + stability |

### Unit 3 – Programming Aspects of Matrices (L03: Matrix Programming)

| ID | Topic | Subtopics / key content |
|---|---|---|
| T3.1 | Matrix types | Row vector, column vector, full matrix, symmetric, sparse, symmetric sparse |
| T3.2 | Full matrix → vector storage | Row-major A[i,j] = B[i·n + j]; column-major A[i,j] = C[j·n + i] (0-based Java indices); **Assignment 03**: Java m×n → row/column vector, locate element |
| T3.3 | Symmetric matrix storage | A[i,j] = A[j,i]; store lower triangle only in a row vector; index formula (i−1)i/2 + j; `elementAt()`; constructors |
| T3.4 | Abstract `Matrix` class (OOP design) | Abstract methods: display, elementAt, multiply (scalar / matrix / columnVector), add, sub, swapRows, swapColumns, transpose, inverse → inheritance/polymorphism exercise |
| T3.5 | Sparse matrix | Definition (most elements zero); representation schemes (slides 15–16) |
| T3.6 | Sparse symmetric matrix | rowVector `a` (values), integerVector `diagonal` (diagonal positions), integerVector `columnNums`, `nElements`; writing `elementAt(i,j)` |

### Unit 4 – Direct Methods: Gaussian Elimination (L04, L04.0, L04.1, L04.2)

| ID | Topic | Subtopics / key content |
|---|---|---|
| T4.1 | Systems of linear equations | Ax = b form; augmented matrix |
| T4.2 | Forward elimination | Row operations to upper-triangular form (L04 slides 5–8; L04.1 worked 3×3) |
| T4.3 | Back substitution | Solving the upper-triangular system |
| T4.4 | GE algorithm (pseudocode) | Loops k (step), i (row), j (column); multipliers stored in lower triangle; b stored as (n+1)th column; x stored in a(i,n+1) (L04.0) |
| T4.5 | Parallel GE (intro) | Parallelisable task E_k^i in forward elimination (L04.0, brief) |
| T4.6 | GE program | C implementation (L04.2); Java exercise (L04 slide 12) |

### Unit 5 – Iterative Methods: Jacobi & Gauss-Seidel (L05)

| ID | Topic | Subtopics / key content |
|---|---|---|
| T5.1 | Jacobi method – idea | Assumptions (unique solution, non-zero diagonal); rewrite each equation for its diagonal unknown; iterate from an initial guess |
| T5.2 | Jacobi – component formula & worked example | Stopping when successive iterates agree to 3 s.f. |
| T5.3 | Jacobi – matrix form | A = D − L − U; x^(k) = T_j x^(k−1) + c_j |
| T5.4 | Jacobi – numerical algorithm | Input n, A, b, x⁰, TOL, N; steps 1–7; stopping criteria (absolute & relative) |
| T5.5 | Gauss-Seidel – idea & formula | Use new values as soon as they are known |
| T5.6 | Gauss-Seidel – matrix form & algorithm | T_g = (D − L)⁻¹U; steps 1–7 |
| T5.7 | Convergence theory | Spectral radius ρ(T) < 1; Lemma 7.18, Theorem 7.19, Corollary 7.20 (error bounds); Theorem 7.21 (strict diagonal dominance ⇒ both converge) |
| T5.8 | Rate of convergence / comparison | Stein–Rosenberg theorem (7.22): relation between ρ(T_j) and ρ(T_g) |

### Unit 6 – Direct Methods: LU & Cholesky (L06, L06.A, L06.J)

| ID | Topic | Subtopics / key content |
|---|---|---|
| T6.1 | LU decomposition | A = LU; solve LZ = b, then UX = Z |
| T6.2 | Cholesky – conditions | Matrix must be **symmetric positive definite (SPD)**; theorem: succeeds iff SPD (otherwise zero diagonal or complex numbers) |
| T6.3 | Cholesky – algorithm | A = LLᵀ; L₁ = √a₁₁; for k = 2..n: solve L_{k−1} ℓ_k = a_k, ℓ_kk = √(a_kk − ℓ_kᵀℓ_k) |
| T6.4 | Cholesky – worked example | 4×4 A = [16 4 4 −4; 4 10 4 2; 4 4 6 −2; −4 2 −2 4], b = [32 26 20 −6]ᵀ → L, then Lc = b, Lᵀx = c → x = [1 2 1 −1]ᵀ |
| T6.5 | Cholesky – Java program | isSquare, isSymmetric checks; L computation; positive-definite check (L06.J) |

### Unit 7 – Iterative Methods: SOR (L08)

| ID | Topic | Subtopics / key content |
|---|---|---|
| T7.1 | Successive Over-Relaxation | Relaxation parameter ω; SOR as weighted Gauss-Seidel update (slide content is mainly images) |
| T7.2 | SOR worked exercise | 4x + 3y = 24; x + 4y − z = 30; −y + 4z = −24 with ω = 1.25 |

> ⚠️ The SOR notes are only 4 slides, but SOR is examined in **3 of 4 papers**. You will need the standard formula x_i^(k) = (1−ω)x_i^(k−1) + (ω/a_ii)[b_i − Σ_{j<i} a_ij x_j^(k) − Σ_{j>i} a_ij x_j^(k−1)].

### Unit 8 – Non-linear Equations (L07: Fixed Point Iteration)

| ID | Topic | Subtopics / key content |
|---|---|---|
| T8.1 | Solving f(x) = 0 | Algebraic vs transcendental equations; need for iterative methods |
| T8.2 | Fixed-point iteration | Rewrite as x = g(x); x_{n+1} = g(x_n); Example 1 (x² − 3x + 1, two rearrangements, convergence vs divergence) |
| T8.3 | Convergence of fixed-point iteration | Theorem 1: |g′(x)| ≤ K < 1 on interval J ⇒ convergence; proof via mean value theorem; contraction; Example 2 (x³ + x − 1) |
| T8.4 | Newton–Raphson method | Tangent derivation; x_{n+1} = x_n − f(x_n)/f′(x_n); algorithm with failure checks and relative stopping test; Examples 3–5 (√2, 2 sin x = x, x³ + x − 1) |

### Unit 9 – Finite Difference Method (L09, L09.E)

| ID | Topic | Subtopics / key content |
|---|---|---|
| T9.1 | Discretisation | Numerical grid; one algebraic equation per node; replace PDE terms by FD approximations |
| T9.2 | Taylor series basis | Expansion of f(x + Δx) and f(x − Δx) |
| T9.3 | First-derivative FD formulas | Forward, backward (1st order), central (2nd order) differences |
| T9.4 | Truncation error | Neglected higher-order terms; order of accuracy O(Δx), O(Δx²) |
| T9.5 | Second derivatives | Central / forward / backward second differences |
| T9.6 | Mixed derivatives | ∂²f/∂x∂y via Taylor series – central, forward, backward |
| T9.7 | Errors in numerical solutions | Round-off vs truncation error; consistency, stability, convergence |
| T9.8 | Forward difference tables | Building Δy, Δ²y, Δ³y… tables (Ex 5.1 data; Ex 5.2 y = x³ + 2x + 1, x = 1..5); finding missing terms (Ex 5.3) |
| T9.9 | Δ operator algebra | Δe^{ax}, Δ²eˣ, Δ log x (Ex 5.4); Δ of a rational function via partial fractions (Ex 5.5) |

---

## Part B – Past paper question mapping

**Legend for "Type":** 🧠 Theory/describe · 🔢 Hand calculation · 💻 Program/algorithm · 🌍 Applied/real-world scenario
**★** = question appears (almost) verbatim in the lecture materials.

### B1. Exam June/July 2023 – Academic Year 2020/2021

| Q | Question (summary) | Marks | Topic → Subtopic | Lecture source | Type |
|---|---|---|---|---|---|
| **1(a)** | Convert 654, −456, 512, −128 to IEEE 754 single precision | 8 | Unit 1 → T1.4 IEEE 754 single (also T1.1 binary conversion, T1.5 bias) | L01 slides 10–20 | 🔢 |
| **1(b)** | Difference between IEEE 754 single and double precision | 4 | Unit 1 → T1.4, T1.6 (bit layout, bias, range T1.9) | L01 slides 16, 23, 30 | 🧠 |
| **1(c)** | Java program: decimal → IEEE 754 double | 8 | Unit 1 → T1.6 double precision (+ T1.1 conversion algorithm) | L01 slides 11, 21 (Assignment 2), 23 | 💻 |
| **1(d)** | Java method: IEEE 754 double → single | 5 | Unit 1 → T1.4 & T1.6 (re-biasing exponent 1023→127, truncating mantissa 52→23 bits; T1.10 overflow/underflow) | L01 slides 16, 23, 33–35 | 💻 |
| **2(a)** | Derive ∂²f/∂x∂y from Taylor series using central, forward, backward differences | 9 | Unit 9 → T9.6 Mixed derivatives (T9.2 Taylor series) | L09 slides 3, 5, 12 | 🧠 |
| **2(b)** | Comment on truncation error in (a) | 3 | Unit 9 → T9.4 Truncation error | L09 slides 10, 14 | 🧠 |
| **2(c)** ★ | Forward difference table for y = x³ + 2x + 1, x = 1..5 | 6 | Unit 9 → T9.8 Forward difference tables | **L09.E Example 5.2 (identical)** | 🔢 |
| **2(d)** | Algorithm to construct the table for x = 1..25 | 7 | Unit 9 → T9.8 (algorithmic form) | L09.E Ex 5.1–5.2 | 💻 |
| **3(a)** | Forward error and backward error with examples | 5 | Unit 2 → T2.7 Forward/backward error | L02 slides 15–17 | 🧠 |
| **3(b)** | Impact of rounding-off error on module grades and final GPA | 8 | Unit 2 → T2.3 Round-off error, T2.5 accumulation in arithmetic | L02 slides 7–8, 10, 12–13 | 🌍 |
| **3(c)** | Propose a method to minimise loss in grade/GPA calculation | 4 | Unit 2 → T2.3 / T2.5 (keep full precision, round only at the end) | L02 slides 8–13 | 🌍 |
| **3(d)** | Write own Java equivalents of `Math.floor()` and `Math.ceil()` | 8 | Unit 2 → T2.3 Chopping vs rounding | L02 slides 9–10 | 💻 |
| **4(a)** | Efficient Java class for symmetric matrix: element(i,j), Store_Element(i,j,E), Multiply(A) | 12 | Unit 3 → T3.3 Symmetric matrix storage (+ T3.4 class design) | L03 slides 8–13 | 💻 |
| **4(b)** | Discuss enhancing it for a sparse symmetric matrix | 5 | Unit 3 → T3.6 Sparse symmetric matrix | L03 slides 14–17 | 🧠 |
| **4(c)** | Java methods for A·B and B·A (A row vector, B column vector) | 8 | Unit 3 → T3.1 Row/column vectors (inner product → scalar; outer product → matrix) | L03 slides 4–5, 10 | 💻 |
| **5(a)** | Algorithm to solve AX = B by Cholesky (A symmetric) | 9 | Unit 6 → T6.3 Cholesky algorithm (+ forward/back substitution, T6.1) | L06.A; L06.J | 💻 |
| **5(b)** ★ | Solve 4×4 system A = [16 4 4 −4; 4 10 4 2; 4 4 6 −2; −4 2 −2 4], B = [32 26 20 −6] | 10 | Unit 6 → T6.4 Cholesky worked example | **L06.A (identical example; x = [1, 2, 1, −1])** | 🔢 |
| **5(c)** | Limitations of the Cholesky method | 6 | Unit 6 → T6.2 SPD requirement, complex roots, zero pivots | L06.A theorem; L06.J checks | 🧠 |
| **6(a)** | Describe Gaussian elimination | 8 | Unit 4 → T4.1–T4.4 | L04; L04.0 | 🧠 |
| **6(b)(i)** | Contractor wage scenario → system of linear equations (4 unknowns) | 8 | Unit 4 → T4.1 Formulating Ax = b | L04 slides 2–4 | 🌍 |
| **6(b)(ii)** | Solve it by Gaussian elimination | 9 | Unit 4 → T4.2 Forward elimination, T4.3 Back substitution | L04; L04.1 | 🔢 |

### B2. Exam Jan/Feb 2024 – Academic Year 2021/2022 (no calculators)

*(No marks printed on this paper.)*

| Q | Question (summary) | Topic → Subtopic | Lecture source | Type |
|---|---|---|---|---|
| **1(a)** | Summarise the range of numbers representable in IEEE 754 single and double | Unit 1 → T1.9 Ranges (normalised & denormalised) | L01 slides 30, 32 | 🧠 |
| **1(b)** | Difference between normalised and denormalised numbers | Unit 1 → T1.8 Denormalised numbers (hidden 1, exponent rule) | L01 slides 18, 26–27 | 🧠 |
| **1(c)** | Java program: decimal → IEEE 754 double and vice versa | Unit 1 → T1.6 Double precision (+ T1.1) | L01 slides 11, 21, 23 | 💻 |
| **1(d)** | Java program: IEEE 754 single → double and vice versa; state assumptions | Unit 1 → T1.4, T1.6 (re-biasing; T1.10 overflow/underflow & precision loss as assumptions) | L01 slides 16, 23, 33–35 | 💻 |
| **2(a)** | Describe relative error and true fractional relative error | Unit 2 → T2.2 Error measures | L02 slides 5–6 | 🧠 |
| **2(b)** | Max and min rounding-off error in final GPA, with example and assumptions; propose a method to minimise it | Unit 2 → T2.3 Round-off error, T2.5 accumulation | L02 slides 8, 10, 12–13 | 🌍 |
| **2(c)** ★ | Find (i) Δe^{ax} (ii) Δ²eˣ (iii) Δlog(x) | Unit 9 → T9.9 Δ operator algebra | **L09.E Example 5.4 (identical)** | 🔢 |
| **2(d)** | Evaluate Δ²(1/x) with interval 1 | Unit 9 → T9.9 Δ operator algebra (same technique as Ex 5.5) | L09.E Example 5.5 | 🔢 |
| **3** | Using inheritance & polymorphism, one Java class each for full, symmetric, sparse, sparse symmetric, row vector, column vector — with addition, subtraction, multiplication, row swap, column swap | Unit 3 → T3.4 Abstract Matrix class + T3.1–T3.6 all matrix types | L03 slides 4–17 (esp. slide 10 abstract class, slide 11 exercise) | 💻 |
| **4(a)** | Numerical algorithm for Jacobi method | Unit 5 → T5.4 Jacobi algorithm | L05 p. 4 | 💻 |
| **4(b)** | Numerical algorithm for Gauss-Seidel | Unit 5 → T5.6 GS algorithm | L05 p. 6 | 💻 |
| **4(c)** | Solve x + y + z = 3; 2x − y − z = 3; x − y + z = 9 by Jacobi | Unit 5 → T5.1–T5.2 Jacobi iteration (T5.7 convergence check) | L05 pp. 1–2 | 🔢 |
| **4(d)** | Solve the same system by Gauss-Seidel | Unit 5 → T5.5 GS iteration | L05 pp. 4–5 | 🔢 |
| **4(e)** | Compare and contrast Jacobi and Gauss-Seidel | Unit 5 → T5.5 (main idea), T5.7–T5.8 convergence & rate (Stein–Rosenberg) | L05 pp. 4, 6–7 | 🧠 |
| **5(a)** | Develop an algorithm to solve a system of linear equations | Unit 4 → T4.4 GE algorithm (any direct/iterative algorithm acceptable: T5.4, T5.6, T6.3) | L04.0; L05; L06.A | 💻 |
| **5(b)** | Solve 10x + 2y − z = 7; x + 8y + 3z = −4; −2x − y + 10z = 9 by SOR, x⁰ = (0,0,0), ω = 1.25 | Unit 7 → T7.1–T7.2 SOR | L08 slides 3–4 | 🔢 |
| **5(c)** | Solve 25x + 15y − 5z = 35; 15x + 18y = 33; −5x + 11z = 6 by Cholesky | Unit 6 → T6.3 Cholesky algorithm (T6.4 procedure) | L06.A; L06 slide 5 (Kreyszig p. 855) | 🔢 |
| **6(a)** | Describe Gaussian elimination | Unit 4 → T4.1–T4.4 | L04; L04.0 | 🧠 |
| **6(b)(i)** | Contractor wage scenario → system of linear equations | Unit 4 → T4.1 Formulating Ax = b | L04 slides 2–4 | 🌍 |
| **6(b)(ii)** | Solve by Gaussian elimination | Unit 4 → T4.2–T4.3 | L04; L04.1 | 🔢 |

> ⚠️ **2024 Q4(c)–(d):** As written, this system is **not diagonally dominant** and both methods diverge (ρ(T_J) ≈ 1.73, ρ(T_GS) ≈ 3.73). The exact solution is x = 2, y = −3, z = 4. Showing that you checked convergence (Theorem 7.21 / ρ < 1, L05 p. 6) and commenting on the divergence is likely what earns full marks here.

### B3. Exam Feb/Mar 2025 – Academic Year 2022/2023

*(No marks printed on this paper.)*

| Q | Question (summary) | Topic → Subtopic | Lecture source | Type |
|---|---|---|---|---|
| **1(a)** | Describe overflow and underflow in IEEE 754 | Unit 1 → T1.10 Overflow & underflow | L01 slides 33–35 | 🧠 |
| **1(b)** | Special feature of denormalised numbers | Unit 1 → T1.8 Denormalised (no hidden 1, gradual underflow) | L01 slide 26 | 🧠 |
| **1(c)** | Convert 256, 1023, 364 to IEEE 754 single | Unit 1 → T1.4 IEEE 754 single (+ T1.1, T1.5) | L01 slides 10–20 | 🔢 |
| **1(d)** | Java program: octal → hexadecimal and vice versa | Unit 1 → T1.1 Number system conversions | L01 slide 13 (Assignment 01) | 💻 |
| **2 (stem)** | GPA example: credits × grade points / total credits, rounded to 2 d.p.; 40 course units × 3 credits | Unit 2 (context for (a)–(c)) | L02 | 🌍 |
| **2(a)** | Impacts of rounding-off error in GPA, with examples | Unit 2 → T2.3 Round-off error, T2.5 accumulation | L02 slides 8, 10, 12 | 🌍 |
| **2(b)** | Impacts of chopping error in GPA, with examples | Unit 2 → T2.3 Chopping/truncation error | L02 slides 9–10 | 🌍 |
| **2(c)(i–iv)** | Java: read module codes & credits, compute GPA, compute round-off & chopping errors, display their difference | Unit 2 → T2.2 Error measures + T2.3 rounding vs chopping | L02 slides 5–10 | 💻 |
| **3(a)** | Efficient representation of a sparse symmetric matrix to minimise memory | Unit 3 → T3.6 Sparse symmetric storage | L03 slides 14–17 | 🧠 |
| **3(b)** | Java method to read and store elements of a sparse symmetric matrix | Unit 3 → T3.6 | L03 slide 17 | 💻 |
| **3(c)** | Java method to return element at (row, column) | Unit 3 → T3.6 `elementAt(i,j)` | L03 slide 17 | 💻 |
| **3(d)** | Java method to display only stored elements with row & column numbers | Unit 3 → T3.6 | L03 slides 15–17 | 💻 |
| **3(e)** | Java method to display full matrix including zeros | Unit 3 → T3.6 (+ T3.2 display in matrix form) | L03 slides 6, 17 | 💻 |
| **4(a)** | Main difference between Jacobi and Gauss-Seidel | Unit 5 → T5.5 Main idea of GS | L05 p. 4 | 🧠 |
| **4(b)(i)** | Fruit-price scenario (Akila/Bala/Pathma) → system of equations | Unit 4 → T4.1 Formulating Ax = b | L04 slides 2–3 | 🌍 |
| **4(b)(ii)** | Solve by Jacobi, initial (1,1,2), max 10 iterations | Unit 5 → T5.1–T5.2 Jacobi iteration | L05 pp. 1–2 | 🔢 |
| **4(b)(iii)** | Solve by Gauss-Seidel, initial (1,1,2), max 6 iterations | Unit 5 → T5.5 GS iteration | L05 pp. 4–5 | 🔢 |
| **4(b)(iv)** | Comment on the performance of both methods | Unit 5 → T5.7–T5.8 Convergence & rate (system is diagonally dominant; ρ_J ≈ 0.48, ρ_GS ≈ 0.11 → GS much faster) | L05 pp. 6–7 | 🧠 |
| **4(c)** | How to modify a Jacobi Java program into Gauss-Seidel | Unit 5 → T5.4 vs T5.6 algorithms (use updated values immediately; single vector) | L05 pp. 4, 6 | 💻 |
| **5(a)** | Solve 15x + 2y + z = 22; 4x + 15y + 2z = 40; x + y + 15z = 48 by SOR, x⁰ = (0,0,0), ω = 1.25 | Unit 7 → T7.1–T7.2 SOR | L08 slides 3–4 | 🔢 |
| **5(b)** | Java program for Cholesky; essential conditions to apply Cholesky | Unit 6 → T6.5 Java implementation + T6.2 SPD conditions | L06.J; L06.A theorem | 💻 🧠 |
| **5(c)** | Solve 8x + 2y + 3z = 13; 2x + 10y + 4z = 16; 3x + 4y + 9z = 16 by Cholesky | Unit 6 → T6.3 Cholesky algorithm | L06.A | 🔢 |
| **6(a)** | Describe Gaussian elimination | Unit 4 → T4.1–T4.4 | L04; L04.0 | 🧠 |
| **6(b)** | Program in any language for Gaussian elimination | Unit 4 → T4.4 Algorithm, T4.6 Program | L04.0; **L04.2 (C program)** | 💻 |
| **6(c)** | Solve f(x) = 4x³ − 3x² − 5x − 10 by fixed-point iteration | Unit 8 → T8.2 Fixed-point iteration, T8.3 choosing g(x) with |g′(x)| < 1 | L07 pp. 1–4 | 🔢 |

### B4. Exam February 2026 – Academic Year 2023/2024

| Q | Question (summary) | Marks | Topic → Subtopic | Lecture source | Type |
|---|---|---|---|---|---|
| **1(a)** | What is a biased exponent in IEEE 754? Why is bias used? | 10 | Unit 1 → T1.5 Biased exponent | L01 slides 17–18 | 🧠 |
| **1(b)(i)** | Convert IEEE single `0 10000010 101000…0` to decimal | 5* | Unit 1 → T1.4 (reverse conversion) | L01 slides 16, 19 | 🔢 |
| **1(b)(ii)** | Convert IEEE single `1 10011010 1010011000…0` to decimal | 5* | Unit 1 → T1.4 (reverse conversion) | L01 slides 16, 19 | 🔢 |
| **1(c)** | Represent −13.625 in IEEE 754 double precision | 5 | Unit 1 → T1.6 Double precision (+ T1.1 fractional binary) | L01 slides 11, 23 | 🔢 |
| **1(d)** | Explain special values ±0, ±∞, NaN and their use | 10 | Unit 1 → T1.7 Special values | L01 slides 24–27 | 🧠 |
| **1(e)** | Java program: decimal → IEEE 754 double | 15 | Unit 1 → T1.6 (+ T1.1) | L01 slides 21, 23 | 💻 |
| **2(a)** | Suitable method to represent a sparse symmetric matrix | 10 | Unit 3 → T3.6 | L03 slides 14–17 | 🧠 |
| **2(b)(i)** | Java: read and store elements | 25 (b total) | Unit 3 → T3.6 | L03 slide 17 | 💻 |
| **2(b)(ii)** | Java: display the matrix | ↑ | Unit 3 → T3.6 (+ T3.2) | L03 slides 6, 17 | 💻 |
| **2(b)(iii)** | Java: return element at given row & column | ↑ | Unit 3 → T3.6 `elementAt(i,j)` | L03 slide 17 | 💻 |
| **2(b)(iv)** | Java: multiply two sparse symmetric matrices | ↑ | Unit 3 → T3.6 + T3.4 multiply (result not necessarily symmetric) | L03 slides 10, 17 | 💻 |
| **2(b)(v)** | Java: return number of non-zero elements | ↑ | Unit 3 → T3.6 (`nElements`) | L03 slide 17 | 💻 |
| **2(c)** | What changes are needed to use the program for general sparse matrices? | 15 | Unit 3 → T3.5 Sparse matrix vs T3.6 | L03 slides 14–17 | 🧠 |
| **3(a)** | Define forward, backward, central difference using Taylor series | 10 | Unit 9 → T9.2 Taylor series, T9.3 FD formulas | L09 slides 3, 5–9 | 🧠 |
| **3(b)** | Derive a finite difference equation to model compound interest | 5 | Unit 9 → T9.1 Discretisation / T9.8 difference equations (A_{n+1} = A_n(1 + r)) | L09 slides 2, 4; L09.E | 🌍 |
| **3(c)** | Form the finite difference table: principal 5000, rate 7.5%, 10 years | 10 | Unit 9 → T9.8 Forward difference tables | L09.E Ex 5.1–5.2 | 🔢 |
| **3(d)** | Java program to display the FD table up to 2nd difference for any principal, rate, duration | 25 | Unit 9 → T9.8 (programmatic table) | L09.E Ex 5.1–5.2 | 💻 |
| **4(a)** | Solve 6x + 2y + 2z = 10; 2x + 8y − 5z = 5; 3x − 4y + 10z = 9 by SOR, ω = 1.25, x⁰ = 0 | 10 | Unit 7 → T7.1–T7.2 SOR | L08 slides 3–4 | 🔢 |
| **4(b)** | Java program for SOR | 15 | Unit 7 → T7.1 (built on T5.6 GS algorithm) | L08; L05 p. 6 | 💻 |
| **4(c)** | Solve 6x + 2y + z = 22; 2x + 8y + z = 32; x + 2y + 10z = 48 by Gauss-Seidel, x⁰ = (1,2,3) | 15 | Unit 5 → T5.5 GS iteration | L05 pp. 4–5 | 🔢 |
| **4(d)** | Algorithm for Gauss-Seidel | 10 | Unit 5 → T5.6 GS algorithm | L05 p. 6 | 💻 |
| **5(a)** | Program in any language for Gaussian elimination | 15 | Unit 4 → T4.4, T4.6 | L04.0; **L04.2 (C program)** | 💻 |
| **5(b)** | Solve 4×4 system (x + 2y + z + w = 8; 2x + 3y + 2z + w = 12; x + y + 3z + 2w = 10; 3x + 4y + z + 2w = 16) by GE | 20 | Unit 4 → T4.2–T4.3 | L04; L04.1 | 🔢 |
| **5(c)(i–iii)** | Impact of rounding in exams: scaling 500 → 100, rounding marks for grading, calculating GPA | 9 | Unit 2 → T2.3 Round-off, T2.5 accumulation | L02 slides 8, 10, 12–13 | 🌍 |
| **5(d)** | Suggest solutions to the shortcomings | 6 | Unit 2 → T2.3 / T2.5 (defer rounding, keep precision) | L02 slides 8–13 | 🌍 |
| **6(a)** | Suitable method to represent a symmetric matrix | 10 | Unit 3 → T3.3 Symmetric storage | L03 slides 8–9, 12–13 | 🧠 |
| **6(b)** | Using that representation, Java program for Cholesky | 15 | Unit 6 → T6.5 Java Cholesky + Unit 3 → T3.3 | L06.J; L03 slides 12–13 | 💻 |
| **6(c)** | What is the specialty of the Cholesky method? | 5 | Unit 6 → T6.2 (SPD, A = LLᵀ, ~half the work/storage of LU) | L06.A; L06 slides 2–5 | 🧠 |
| **6(d)** | Solve 25x + 15y − 5z = 70; 15x + 18y = 66; −5x + 11z = 12 by Cholesky | 20 | Unit 6 → T6.3 Cholesky algorithm | L06.A | 🔢 |

\* 1(b) is 10 marks in total for both parts.

---

## Part C – Exam focus analysis

### C1. Topic frequency across papers

Each cell lists the questions on that topic (✗ = not examined).

| Unit / Topic | 2023 | 2024 | 2025 | 2026 | Papers |
|---|---|---|---|---|---|
| **U1 IEEE 754 & floating point** (L01) | Q1 (a–d) | Q1 (a–d) | Q1 (a–d) | Q1 (a–e) | **4/4** |
| **U2 Errors – rounding/chopping, GPA scenario** (L02) | Q3 (a–d) | Q2 (a–b) | Q2 (a–c) | Q5 (c–d) | **4/4** |
| **U3 Matrix programming – symmetric / sparse** (L03) | Q4 (a–c) | Q3 | Q3 (a–e) | Q2 (a–c), Q6 (a) | **4/4** |
| **U4 Gaussian elimination** (L04 series) | Q6 (a–b) | Q5(a), Q6 (a–b) | Q6 (a–b) | Q5 (a–b) | **4/4** |
| **U5 Jacobi / Gauss-Seidel** (L05) | ✗ | Q4 (a–e) | Q4 (a–c) | Q4 (c–d) | **3/4** |
| **U6 Cholesky** (L06 series) | Q5 (a–c) | Q5(c) | Q5 (b–c) | Q6 (b–d) | **4/4** |
| **U7 SOR** (L08) | ✗ | Q5(b) | Q5(a) | Q4 (a–b) | **3/4** |
| **U8 Fixed-point / Newton** (L07) | ✗ | ✗ | Q6(c) | ✗ | 1/4 |
| **U9 Finite differences** (L09, L09.E) | Q2 (a–d) | Q2 (c–d) | ✗ | Q3 (a–d) | **3/4** |

### C2. Subtopics that recur

| Subtopic | Times | Where |
|---|---|---|
| Decimal → IEEE 754 **double** – Java program (T1.6) | 3 | 2023 Q1c, 2024 Q1c, 2026 Q1e |
| Single ↔ double conversion – Java (T1.4/T1.6) | 2 | 2023 Q1d, 2024 Q1d |
| Hand conversion to/from IEEE 754 (T1.4/T1.6) | 3 | 2023 Q1a, 2025 Q1c, 2026 Q1b–c |
| Rounding/chopping error in the **GPA calculation** (T2.3/T2.5) | **4** | Every paper |
| **Sparse symmetric matrix** – representation + Java methods (T3.6) | 4 | 2023 Q4b, 2024 Q3, 2025 Q3, 2026 Q2 |
| Symmetric matrix storage class (T3.3) | 3 | 2023 Q4a, 2024 Q3, 2026 Q6a |
| **"Describe Gaussian elimination"** (T4.1–T4.4) | 3 | 2023 Q6a, 2024 Q6a, 2025 Q6a |
| Contractor wage scenario → GE (identical in 2 papers) | 2 | 2023 Q6b, 2024 Q6b |
| GE program (T4.6) | 2 | 2025 Q6b, 2026 Q5a |
| Jacobi/GS algorithms (T5.4/T5.6) | 3 | 2024 Q4a–b, 2025 Q4c, 2026 Q4d |
| Jacobi vs GS comparison (T5.5/T5.8) | 2 | 2024 Q4e, 2025 Q4a/b(iv) |
| SOR by hand, ω = 1.25, x⁰ = 0 (T7.2) | 3 | 2024 Q5b, 2025 Q5a, 2026 Q4a |
| Cholesky by hand (T6.3–T6.4) | 4 | 2023 Q5b, 2024 Q5c, 2025 Q5c, 2026 Q6d |
| Cholesky algorithm/program (T6.3/T6.5) | 3 | 2023 Q5a, 2025 Q5b, 2026 Q6b |
| Cholesky conditions/limitations/specialty (T6.2) | 3 | 2023 Q5c, 2025 Q5b, 2026 Q6c |
| Forward difference table (T9.8) | 2 | 2023 Q2c–d, 2026 Q3c–d |

### C3. Questions taken directly from lecture materials (★)

| Exam question | Matching lecture item |
|---|---|
| 2023 Q2(c) – FD table for y = x³ + 2x + 1, x = 1..5 | L09.E Example 5.2 (same function, same x values) |
| 2023 Q5(b) – 4×4 Cholesky (16, 4, 4, −4 …) | L06.A worked example (same A and b; x = [1, 2, 1, −1]) |
| 2024 Q2(c) – Δe^{ax}, Δ²eˣ, Δlog x | L09.E Example 5.4 |
| 2024 Q5(c) & 2026 Q6(d) – 25/15/−5 Cholesky matrix | Same coefficient matrix in both years, only b changes (35, 33, 6 → 70, 66, 12). Classic Kreyszig example (L06 slide 5 points to Kreyszig p. 855) |

### C4. Gaps between the lectures and the exams

**Listed in the syllabus or notes but never examined:**
- Newton–Raphson (T8.4), convergence theorem of fixed-point iteration (T8.3)
- LU decomposition on its own (T6.1)
- Well-posed problems (T2.6), conditioning (T2.8), stability (T2.9), machine precision (T2.4), accuracy vs precision (T2.1)
- Parallel Gaussian elimination (T4.5)
- Second derivatives (T9.5); consistency/stability of FD schemes (T9.7)
- ICCG and the Jacobian matrix: named in the syllabus slide but **not covered in any lecture material provided**

**Examined but only thinly covered in the notes (prepare from wider reading):**
- **SOR** (3/4 papers, 15–25 marks each): the L08 slides contain only an exercise. Learn the update formula and practise 2–3 iterations by hand, plus a Java SOR program.
- **GPA rounding/chopping scenarios** (4/4 papers): L02 has only definitions. Practise with the 2025 Q2 GPA example (32.7/13 = 2.5153846 → 2.52), working out the maximum error per module (±0.005 per rounded value) and how it accumulates over 40 modules.
- **Writing `floor`/`ceil` in Java** (2023 Q3d) and **octal ↔ hex** (2025 Q1d): these extend the L01 Assignment 01 and the L02 chopping material.
- **Compound interest as a finite difference model** (2026 Q3b–d): an applied version of the L09.E tables.
- **Inheritance/polymorphism matrix class hierarchy** (2024 Q3, a whole question): builds on the L03 abstract `Matrix` class (slide 10).

### C5. Checks on the exam systems (convergence / SPD)

| System | Method asked | Check | Result |
|---|---|---|---|
| 2024 Q4c (x+y+z=3 …) | Jacobi, GS | ρ(T_J) = 1.73, ρ(T_GS) = 3.73 | ❌ **Both diverge.** Comment on it; exact solution (2, −3, 4) |
| 2025 Q4b (fruits) | Jacobi, GS | ρ_J = 0.48, ρ_GS = 0.11 | ✅ Diagonally dominant; GS converges much faster |
| 2023/2024 Q6b (contractor, 4×4) | GE | — | Direct method, no convergence issue |
| 2024 Q5b | SOR | ρ_GS = 0.13 | ✅ Converges |
| 2025 Q5a | SOR | ρ_GS = 0.03 | ✅ Converges very fast |
| 2026 Q4a | SOR | ρ_GS = 0.58 | ✅ Converges (slowest of the SOR systems) |
| 2026 Q4c | GS | ρ_GS = 0.07 | ✅ Converges |
| 2024 Q5c / 2026 Q6d (25, 15, −5) | Cholesky | eigenvalues 4.50, 12.0, 37.5 | ✅ SPD |
| 2025 Q5c (8, 2, 3 …) | Cholesky | eigenvalues 4.95, 6.82, 15.2 | ✅ SPD |

### C6. Exam pattern summary

- **Fixed question slots.** Q1 is always IEEE 754, and a matrix-programming question plus a Gaussian elimination question appear every year. Cholesky has appeared every year since 2023.
- **Three kinds of question in almost every topic:** (i) describe the method, (ii) solve a small system by hand, (iii) write a Java program or algorithm for it. In the 2026 paper, programming questions carry about 40% of the marks.
- **Priority for revision:** IEEE 754 (L01) → sparse/symmetric matrix Java (L03) → Cholesky (L06) → Gaussian elimination (L04) → GPA rounding error (L02) → SOR & Gauss-Seidel (L05, L08) → finite differences (L09) → fixed-point iteration (L07).
- **Choosing 4 of 6:** the IEEE 754, Gaussian elimination and Cholesky questions are the most predictable. Matrix programming is predictable in topic but always needs code.
