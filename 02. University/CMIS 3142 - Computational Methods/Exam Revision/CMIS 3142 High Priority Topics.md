---
type: revision
course: CMIS 3142
status: complete
tags: [cmis3142, revision, priority]
aliases: [CMIS 3142 Priority Topics]
---
# CMIS 3142 · High Priority Topics (ranked by past-paper evidence)

> [!info] Back to [[00. CMIS 3142 Course Overview|Course Overview]] · Evidence: [[CMIS 3142 Past Paper Mapping]]
> Rule: 🔴 = in 3–4 of the 4 papers (2023–2026); 🟡 = 1–2 papers; 🟢 = never examined. Ranking within 🔴 uses the number of papers and the marks involved.

## 🔴 Tier 1: appeared in all 4 papers
| Rank | Topic | Evidence | Learn | Practise |
|---|---|---|---|---|
| 1 | **IEEE 754** hand conversions + theory part | Q1 every year | [[01.03 IEEE 754 Single Precision]], [[01.05 IEEE 754 Double Precision]], [[01.06 Special Values and Denormalized Numbers]] | [[PP 01 - Floating Point Numbers]] |
| 2 | **Decimal → IEEE double Java program** | 2023, 2024, 2026 (15 marks in 2026) | [[01.08 Java Programs for IEEE 754]] | write it from memory |
| 3 | **Sparse symmetric matrix**: representation + Java | 2023, 2024, 2025, 2026 (35 marks in 2026) | [[03.06 Sparse Symmetric Matrices]] | [[PP 03 - Matrix Programming]] |
| 4 | **Cholesky by hand** | 2023, 2024, 2025, 2026 (20 marks in 2026) | [[06.02 Cholesky Method]] | [[PP 06 - LU and Cholesky]] |
| 5 | **Gaussian elimination**: describe, solve, program | 2023, 2024, 2025, 2026 | [[04.02 Forward Elimination and Back Substitution]], [[04.03 Gaussian Elimination Algorithm and Program]] | [[PP 04 - Gaussian Elimination]] |
| 6 | **GPA / marks rounding scenario** | 2023, 2024, 2025, 2026 | [[02.04 Rounding Errors in GPA Calculation]] | [[PP 02 - Error Analysis]] |

## 🔴 Tier 2: appeared in 3 of 4 papers
| Rank | Topic | Evidence | Learn |
|---|---|---|---|
| 7 | **Symmetric matrix storage/class** | 2023 Q4(a), 2024 Q3, 2026 Q6(a) | [[03.03 Symmetric Matrix Storage]] |
| 8 | **Cholesky algorithm / Java** | 2023 Q5(a), 2025 Q5(b), 2026 Q6(b) | [[06.03 Cholesky Java Program]] |
| 9 | **Cholesky conditions / limitations / specialty** | 2023 Q5(c), 2025 Q5(b), 2026 Q6(c) | [[06.02 Cholesky Method]] §8–9 |
| 10 | **SOR by hand, ω = 1.25** | 2024, 2025, 2026 | [[08.01 SOR Method]] |
| 11 | **Gauss-Seidel by hand + algorithm** | 2024, 2025, 2026 | [[05.02 Gauss-Seidel Method]] |
| 12 | **Finite differences** (tables, derivations, Δ) | 2023, 2024, 2026 | [[09.04 Forward Difference Tables]], [[09.02 First Derivative Differences and Truncation Error]] |
| 13 | **"Describe Gaussian elimination"** | 2023, 2024, 2025 | [[04.02 Forward Elimination and Back Substitution]] §7 |

## 🟡 Medium: 1–2 papers
- Single ↔ double Java (2023, 2024) · normalised vs denormalised / denormal feature (2024, 2025) · ranges (2024) · overflow/underflow (2025) · biased exponent (2026) · special values (2026) · octal ↔ hex Java (2025)
- Forward/backward error (2023) · relative errors (2024) · `floor`/`ceil` Java (2023) · chopping in GPA + GPA Java (2025)
- Class hierarchy with inheritance & polymorphism (2024, whole question) · row × column vectors (2023) · general sparse changes (2026)
- Jacobi by hand + algorithm (2024, 2025) · Jacobi vs GS (2024, 2025) · modify Jacobi → GS (2025)
- SOR Java (2026)
- Mixed-derivative derivation + truncation comment (2023) · FD definitions via Taylor (2026) · compound interest model (2026) · Δ operator (2024) · difference tables (2023, 2026)
- Fixed-point iteration (2025)

## 🟢 Never examined (learn definitions only)
Newton–Raphson · LU alone · machine precision · accuracy vs precision · well-posed problems · conditioning & stability · parallel GE · second-derivative formulas alone · consistency/stability/convergence of FD schemes.

## Strategy for choosing 4 of 6
1. **Q1 (IEEE 754)** is the most predictable: always take it if you have learned conversions + the Java program.
2. The **Cholesky** question (often with SOR or symmetric storage) and the **Gaussian elimination** question are next most predictable.
3. The **matrix programming** question is predictable in topic but long code; take it if you can write the sparse symmetric program fluently.
4. Error-analysis GPA parts are often **inside** another question (2026 Q5(c)(d)); they are "free" marks if you have the stage-by-stage answer ready.

## Two-week plan (suggested)
| Days | Focus |
|---|---|
| 1–2 | L01 conversions (all exam numbers) + decimal → double Java |
| 3–4 | L03 symmetric class + sparse symmetric program (write twice) |
| 5–6 | L06 Cholesky by hand (4 exam systems) + Java + limitations/specialty |
| 7 | L04 describe GE + contractor + 2026 4×4 + program |
| 8–9 | L05 Gauss-Seidel/Jacobi by hand + algorithms; L08 SOR (3 exam systems) |
| 10 | L02 GPA scenario answers + forward/backward error + floor/ceil |
| 11 | L09 tables, derivations, Δ operator, compound interest program |
| 12 | L07 fixed point; 🟢 definitions |
| 13–14 | Timed papers from [[CMIS 3142 Papers by Year]]; check with PP notes |
