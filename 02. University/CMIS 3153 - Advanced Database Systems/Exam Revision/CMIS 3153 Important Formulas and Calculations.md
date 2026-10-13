---
type: revision
course: CMIS 3153
status: complete
tags: [cmis3153, revision, formula]
aliases: [CMIS 3153 Formula Sheet, CMIS 3153 Formulas]
---
# CMIS 3153 · Important Formulas and Calculations

> [!info] Back to [[00. CMIS 3153 Course Overview|Course Overview]] · Details: [[01.12 Worked File and Disk Calculations]], [[TUT 1 - Disk and File Calculations]], [[TUT 4 - Selectivity and Index Cost]]

## 1. Disk
| Quantity | Formula | Mini example |
|---|---|---|
| Surfaces | disks × 2 (double-sided) | 15 disks → 30 |
| Track size | $n(B+G)$ total, $nB$ useful | 20 × 640 = 12,800 B |
| Cylinder capacity | surfaces × track size | 30 × 12,800 = 384,000 B |
| Pack capacity | tracks/surface × cylinder capacity | 400 × 384,000 = 153.6 MB |
| One revolution | $60{,}000/p$ ms | 7200 rpm → 8.33 ms |
| Rotational delay | $rd = \frac12 \cdot 60{,}000/p = 30{,}000/p$ ms | 7200 rpm → 4.17 ms; 2400 → 12.5 ms |
| Transfer rate | $tr = $ track bytes / revolution ms | 12,800/25 = 512 B/ms |
| Block transfer time | $btt = B/tr$ | 512/512 = 1 ms |
| Bulk transfer rate | $btr = \frac{B}{B+G}\,tr$ | 0.8 × 512 = 409.6 B/ms |
| One block access | $s + rd + btt$ | 30 + 12.5 + 1 = 43.5 ms |
| $k$ random blocks | $k(s + rd + btt)$ | 20 × 43.5 = 870 ms |
| $k$ consecutive blocks | $s + rd + k\cdot btt$ (or $k \cdot B/btr$) | 30 + 12.5 + 20 = 62.5 ms |
| Transfer of a large block | size / rate (watch KB vs MB units) | 64 KB / 120 MB/s ≈ 0.55 ms |

> [!warning] Lecture typos to ignore
> "$rd = 3000/p$" and "$tr$ = 3600 bytes/ms" in L1 §5.1 are typos: use $rd = 30{,}000/p$ ms and $tr = 3000$ B/ms for the 50 KB / 3600 rpm example.

## 2. Files
| Quantity | Formula |
|---|---|
| Record size | $R = \sum$ field sizes (+ deletion marker) |
| Blocking factor | $bfr = \lfloor B/R \rfloor$ |
| Blocks | $b = \lceil r/bfr \rceil$ |
| Wasted space (unspanned) | $B - bfr \cdot R$ per block |
| Linear search | $b/2$ blocks average (found); $b$ (not found) |
| Binary search | $\lceil\log_2 b\rceil$ blocks |
| Record $i$ in a relative file | block $\lfloor i/bfr \rfloor$, position $i \bmod bfr$ |

**Tutorial 1 / 2021/22 Q6:** R = 114, bfr = 4, b = 5,000; contiguous linear search 3,167.5 ms; scattered 108,750 ms; binary 13 × 43.5 = 565.5 ms.

## 3. Hashing
| Item | Formula |
|---|---|
| Bucket | $h(K) = K \bmod M$ |
| Linear probing | $(h(K) + i) \bmod M$ |
| Quadratic probing | $(h(K) + i^2) \bmod M$ |
| Double hashing | $(h(K) + i\cdot h_2(K)) \bmod M$ |
| Records per bucket | $r / M$ ; comparisons ≈ half a bucket |
**2020/21 Q5(c):** slots 2:12, 3:13, 4:2, 5:3, 6:23, 7:5, 8:18, 9:15.

## 4. Indexes
| Quantity | Formula |
|---|---|
| Index entry | $R_i = V + P$ |
| Index bfr / fan-out | $bfr_i = fo = \lfloor B/R_i \rfloor$ |
| Primary index entries | $r_i = b$ (sparse) |
| Dense secondary entries | $r_i = r$ |
| Index blocks | $b_i = \lceil r_i / bfr_i \rceil$ |
| Single-level search | $\lceil\log_2 b_i\rceil + 1$ |
| Multilevel levels | $t = \lceil \log_{fo} r_1 \rceil$; search $t + 1$ |
| B-tree order $p$ (fits a block) | $p\cdot P + (p-1)(V + P_r) \le B$ |
| B+-tree internal order $p$ | $p\cdot P + (p-1)V \le B$ |
| B+-tree leaf order | $p_{leaf}(V + P_r) + P \le B$ |
| B+-tree utilization | entries ÷ (nodes × max entries) |
| Point lookup with B-tree | height + 1 row fetch |
**Lecture:** r = 30,000, B = 1024, R = 100 → b = 3,000; binary on data 12; primary index (45 blocks) 7; secondary (442 blocks) 10; multilevel (442 → 7 → 1) 4.
**2023/24 Q3(c):** utilization 20/24 = 83.3%.

## 5. Optimization
| Quantity | Formula |
|---|---|
| Selectivity | matching rows / total rows |
| Attribute selectivity | key: $sl = 1/r$; non-key: $sl = 1/d$ |
| Selection cardinality | $s = sl \cdot r$ (key: 1; non-key: $r/d$) |
| Composite index rows | $r / d_1 / d_2 / \dots$ (uniformity) |
| Index entries per page | $\lfloor(\text{page} - \text{overhead}) / (\text{key} + \text{pointer})\rfloor$ |
**Tutorial 4:** 250,000/1,000,000 = 0.25 > 0.10 → no index; 300,000/5,000,000 = 6% → clustered range scan; (country, city, age) → 200,000 → 1,000 → ≈ 13.

## 6. Distributed queries
- Bytes transferred = sum of relations/intermediate results shipped.
- Lecture: EMPLOYEE 1,000,000 B (site 1), DEPARTMENT 3,500 B (site 2), result at site 3: best = ship DEPARTMENT to site 1, then result: **403,500 B** (Q), **7,500 B** (Q′).
- Semijoin (result at site 2): Q: 400 + 340,000 = **340,400 B**; Q′: 900 + 3,900 = **4,800 B**.

## 7. Relational algebra identities
$$
R\cap S = R-(R-S)\qquad R\bowtie_\theta S = \sigma_\theta(R\times S)\qquad R\div S = \pi_A(R) - \pi_A\big((\pi_A(R)\times S)-R\big)
$$
$$
\pi_L(R\cup S)=\pi_L(R)\cup\pi_L(S)\qquad \pi_A(E_1-E_2)\supseteq \pi_A(E_1)-\pi_A(E_2)\ \ (\text{not equal in general})
$$
