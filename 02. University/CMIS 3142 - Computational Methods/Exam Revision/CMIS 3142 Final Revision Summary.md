---
type: revision
course: CMIS 3142
status: complete
tags: [cmis3142, revision, summary]
aliases: [CMIS 3142 Cram Sheet, CMIS 3142 Final Summary]
---
# CMIS 3142 · Final Revision Summary

> [!info] Back to [[00. CMIS 3142 Course Overview|Course Overview]] · Formula sheet: [[CMIS 3142 Important Formulas and Algorithms]] · Paper: 6 questions, answer 4, 2 hours.

## L01 Floating point 🔴 ([[01.99 Summary - Floating Point Numbers|summary]])
- Single 1/8/23, bias 127; double 1/11/52, bias 1023. $(-1)^S1.F2^{E-\text{bias}}$; hidden 1.
- Convert: sign → binary → $1.F\times2^e$ → $E = e + \text{bias}$ → pad F right. Powers of 2 → F = 0.
- $E = 0$: zero/denormal ($0.F\times2^{-126}$, gradual underflow). $E$ = 1s: ∞ ($F = 0$) / NaN ($F \ne 0$).
- Overflow → ±∞ (> $3.4\times10^{38}$ single); underflow → 0 (< $2^{-149}$).
- Bias: negative exponents without sign bit; compare floats like integers.
- Answers: 654 = `0 10001000 0100011100…` · −456 = `1 10000111 11001000…` · 512 = `0 10001000 0…` · −128 = `1 10000110 0…` · 256 = `0 10000111 0…` · 1023 = `0 10001000 111111111 0…` · 364 = `0 10000111 01101100…` · `0 10000010 101…` = 13 · `1 10011010 1010011…` = −221 249 536 · −13.625 (double) = `1 10000000010 101101 0…`.

## L02 Error analysis 🔴 ([[02.99 Summary - Error Analysis|summary]])
- $E_t = T - A$; fractional $(T-A)/T$; $\varepsilon_t$ = ×100%; $\varepsilon_a$ uses successive iterates.
- Round-off: nearest, ≤ ½ unit, ± cancels. Chopping: delete digits, < 1 unit, one-sided, accumulates.
- Large + small number loses the small one; errors build up in long computations.
- GPA: $32.7/13 = 2.5153846$ → 2.52 (round) / 2.51 (chop). Stages: scaling (±0.5 mark, 500→100), grade boundary jumps, GPA (±0.005), class boundary. **Fix: keep full precision, round once at the end, round not chop, borderline review.**
- Forward error $|\hat y - y|$; backward $|\Delta x|$ with $\hat y = f(x+\Delta x)$; √2 ≈ 1.4 → 0.0142 / 0.04.
- Well-posed: exists, unique, continuous. Cond ≈ $|xf'/f|$. Stable = exact answer to nearby problem.

## L03 Matrix programming 🔴 ([[03.99 Summary - Matrix Programming|summary]])
- Row-major $i\cdot n + j$; column-major $j\cdot n + i$.
- Symmetric: lower triangle in row vector, $k = i(i+1)/2 + j$ ($i \ge j$), swap if $j > i$; product not symmetric.
- Sparse: mostly zeros; linked list (header/element nodes) or arrays.
- Sparse symmetric: `a`, `diagonal`, `columnNums`, `nElements`; row $i$ = `diagonal[i-1]+1 … diagonal[i]`; not found → 0; off-diagonal counted twice.
- General sparse: whole rows, `rowStart`, no swap.
- Abstract `Matrix` + 6 subclasses: inheritance + polymorphism; symmetric swap breaks symmetry.

## L04 Gaussian elimination 🔴 ([[04.99 Summary - Gaussian Elimination|summary]])
- $[A|b]$ → $R_i \leftarrow R_i - (a_{ik}/a_{kk})R_k$ → upper triangular → back substitution. Pivot if 0.
- Pseudocode loops k, i, j; $b$ = column $n+1$; multipliers in lower triangle.
- Contractor: teen 10, man 20, woman 15, old 12. 2026 4×4: (1, 2, 1, 2). Lecture 3×3: (2, 2, −1).

## L05 Jacobi / Gauss-Seidel 🔴 ([[05.99 Summary - Jacobi and Gauss-Seidel|summary]])
- Jacobi: old values only. GS: new values immediately. $A = D - L - U$; $T_j = D^{-1}(L+U)$, $T_g = (D-L)^{-1}U$.
- Converges iff $\rho(T) < 1$; strictly diagonally dominant ⇒ both converge; GS faster.
- Algorithm: input A, b, XO, TOL, N; update; $\|x - XO\| < TOL$ stop; XO = x; max-iteration failure.
- 2024 system diverges (not dominant; exact (2, −3, 4)). Fruits: (1, 2, 3), GS much faster. 2026 GS from (1,2,3): (2, 3, 4).
- Jacobi → GS program: one array, use `x[j]` not `xOld[j]`.

## L06 Cholesky 🔴 ([[06.99 Summary - LU and Cholesky|summary]])
- SPD (square, symmetric, positive definite) ⇒ $A = LL^T$; $Lc = b$, $L^Tx = c$.
- $\ell_{ii} = \sqrt{a_{ii} - \sum\ell_{ik}^2}$, $\ell_{ij} = (a_{ij} - \sum\ell_{ik}\ell_{jk})/\ell_{jj}$.
- Fails iff not SPD (zero pivot or √negative). Limitations: SPD only, square roots, fill-in, $O(n^3)$. Specialty: one factor, half work/storage, no pivoting, tests SPD.
- 4×4: $L$ rows (4), (1,3), (1,1,2), (−1,1,−1,1); $x = (1, 2, 1, -1)$. 25/15/−5: $L$ = (5), (3,3), (−1,1,3); $b = (35,33,6) \to (1,1,1)$; $b = (70,66,12) \to (2,2,2)$. 8/2/3: (1, 1, 1).

## L07 Fixed point 🟡 ([[07.99 Summary - Fixed Point Iteration|summary]])
- $x = g(x)$, $x_{n+1} = g(x_n)$; converges if $|g'| \le K < 1$ near root.
- $4x^3 - 3x^2 - 5x - 10$: $g = \sqrt[3]{(3x^2 + 5x + 10)/4}$, $|g'(2)| \approx 0.35$ → root 2.
- Newton: $x_{n+1} = x_n - f/f'$ (not examined).

## L08 SOR 🔴 ([[08.99 Summary - SOR|summary]])
- $x_i = (1 - \omega)x_i^{old} + \omega\cdot(\text{GS value})$; ω = 1.25 → $-0.25x^{old} + 1.25\cdot\text{GS}$.
- $0 < \omega < 2$; ω = 1 is GS. Answers: 2024 → (1, −1, 1); 2025 → (1, 2, 3); 2026 → (1, 1, 1).

## L09 Finite differences 🔴 ([[09.99 Summary - Finite Difference Method|summary]])
- Forward $\frac{f_{i+1}-f_i}{\Delta x}$ $O(\Delta x)$; backward $\frac{f_i - f_{i-1}}{\Delta x}$ $O(\Delta x)$; central $\frac{f_{i+1}-f_{i-1}}{2\Delta x}$ $O(\Delta x^2)$ (from Taylor (a), (b), (a)−(b)).
- $f_{xy}$ central $/4\Delta x\Delta y$ ($O(\Delta x^2,\Delta y^2)$); forward/backward $/\Delta x\Delta y$ ($O(\Delta x, \Delta y)$).
- Table $x^3 + 2x + 1$: 4, 13, 34, 73, 136 | 9, 21, 39, 63 | 12, 18, 24 | 6, 6 | 0.
- Δ: $e^{ax}(e^{ah}-1)$; $(e^h - 1)^2e^x$; $\log(1 + h/x)$; $\Delta^2(1/x) = 2/[x(x+1)(x+2)]$.
- Compound interest: $\Delta A_n = rA_n$; 5000 at 7.5% → 5375, 5778.13, …, 10305.16; $\Delta A_0 = 375$, $\Delta^2A_0 = 28.13$.

## Exam technique
- Every topic: **describe → solve by hand → code/algorithm**. Label steps; show formulas before numbers; check answers in an original equation.
- Before iterating, **check diagonal dominance**; before Cholesky, **state SPD**.
- In code, comment each phase and handle edge cases (zero pivot, $j > i$ swap, not found → 0, max iterations).
- State **assumptions** whenever a question is open (GPA scales, rounding rules, overflow handling).
