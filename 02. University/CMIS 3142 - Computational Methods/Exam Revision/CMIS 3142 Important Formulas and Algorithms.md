---
type: revision
course: CMIS 3142
status: complete
tags: [cmis3142, revision, formulas]
aliases: [CMIS 3142 Formula Sheet, CMIS 3142 Formulas]
---
# CMIS 3142 · Important Formulas and Algorithms (quick reference)

> [!info] Back to [[00. CMIS 3142 Course Overview|Course Overview]] · Each block links to the full explanation.

## 1. IEEE 754 ([[01.03 IEEE 754 Single Precision]], [[01.05 IEEE 754 Double Precision]])
| | S | E | F | Bias | Normalised | Denormalised |
|---|---|---|---|---|---|---|
| Single | 1 | 8 | 23 | 127 | $(-1)^S\,1.F\,2^{E-127}$ | $(-1)^S\,0.F\,2^{-126}$ |
| Double | 1 | 11 | 52 | 1023 | $(-1)^S\,1.F\,2^{E-1023}$ | $(-1)^S\,0.F\,2^{-1022}$ |

**Decimal → IEEE:** sign → binary (int ÷2, fraction ×2) → $1.F\times2^e$ → $E = e + \text{bias}$ → pad $F$ → S|E|F.
**Special:** $E = 0, F = 0$ → ±0 · $E = 0, F \ne 0$ → denormal · $E$ = 1s, $F = 0$ → ±∞ · $E$ = 1s, $F \ne 0$ → NaN.
**Ranges:** single normal $2^{-126}$ … $(2 - 2^{-23})2^{127}\approx3.4\times10^{38}$; denormal from $2^{-149}$. Double normal $2^{-1022}$ … $(2 - 2^{-52})2^{1023}\approx1.8\times10^{308}$; denormal from $2^{-1074}$.
**Single ↔ double:** $E_d = E_s - 127 + 1023$, $F_d = F_s$ + 29 zeros; reverse checks $e > 127$ (overflow), $e < -126$ (underflow).

## 2. Errors ([[02.01 Accuracy, Precision and Error Measures]])
$$
E_t = T - A,\qquad \varepsilon_t = \frac{T - A}{T}\times100\%,\qquad |\varepsilon_a| = \left|\frac{x^{new} - x^{old}}{x^{new}}\right|\times100\%
$$
$$
\left|\frac{fl(x) - x}{x}\right| \le \varepsilon_{mach},\qquad \text{cond} = \frac{|\Delta y|/|y|}{|\Delta x|/|x|} \approx \left|\frac{xf'(x)}{f(x)}\right|
$$
Max error at $d$ = last-place unit: rounding $d/2$, chopping < $d$. GPA $= \sum c_ig_i/\sum c_i$.

## 3. Matrix storage ([[03.03 Symmetric Matrix Storage]], [[03.06 Sparse Symmetric Matrices]])
- Row-major $A[i,j] = B[i\cdot n + j]$; column-major $A[i,j] = C[j\cdot m + i]$ (0-based).
- Symmetric packed: $k = \frac{i(i+1)}{2} + j$ for $i \ge j$ (0-based); $\frac{(i-1)i}{2} + j$ (1-based); swap if $j > i$; size $\frac{n(n+1)}{2}$.
- Sparse symmetric `elementAt(i,j)`: swap if $j > i$; search `columnNums[start..diagonal[i]]`, `start = diagonal[i-1] + 1` (0 for row 0); else 0.

## 4. Gaussian elimination ([[04.03 Gaussian Elimination Algorithm and Program]])
```text
for k = 1 to n−1
    for i = k+1 to n
        m = a(i,k)/a(k,k)
        for j = k to n+1:  a(i,j) = a(i,j) − m·a(k,j)
for i = n down to 1
    x(i) = ( a(i,n+1) − Σ_{j>i} a(i,j)·x(j) ) / a(i,i)
```

## 5. Jacobi, Gauss-Seidel, SOR ([[05.01 Jacobi Method]], [[05.02 Gauss-Seidel Method]], [[08.01 SOR Method]])
$$
\text{Jacobi: } x_i^{(k)} = \frac{1}{a_{ii}}\Big[b_i - \sum_{j\ne i}a_{ij}x_j^{(k-1)}\Big]
$$
$$
\text{Gauss-Seidel: } x_i^{(k)} = \frac{1}{a_{ii}}\Big[b_i - \sum_{j<i}a_{ij}x_j^{(k)} - \sum_{j>i}a_{ij}x_j^{(k-1)}\Big]
$$
$$
\text{SOR: } x_i^{(k)} = (1-\omega)x_i^{(k-1)} + \frac{\omega}{a_{ii}}\Big[b_i - \sum_{j<i}a_{ij}x_j^{(k)} - \sum_{j>i}a_{ij}x_j^{(k-1)}\Big]
$$
Matrix forms with $A = D - L - U$: $T_j = D^{-1}(L+U)$, $c_j = D^{-1}b$; $T_g = (D-L)^{-1}U$, $c_g = (D-L)^{-1}b$; $x^{(k)} = Tx^{(k-1)} + c$.
Convergence: iff $\rho(T) < 1$; strictly diagonally dominant ⇒ Jacobi and GS converge; $\|x - x^{(k)}\| \le \|T\|^k\|x^{(0)} - x\|$; SOR needs $0 < \omega < 2$.

**Algorithm skeleton (Jacobi/GS/SOR):**
```text
Input A, b, XO, TOL, N
k = 1
while k <= N
    for i = 1..n:  x_i = (update formula)
    if ||x − XO|| < TOL: output x; stop
    k = k + 1;  XO = x
output "maximum number of iterations exceeded"
```

## 6. LU and Cholesky ([[06.01 LU Decomposition]], [[06.02 Cholesky Method]])
LU: $A = LU$; $LZ = b$ (forward), $UX = Z$ (back).
Cholesky ($A$ SPD): $A = LL^T$
$$
\ell_{ii} = \sqrt{a_{ii} - \sum_{k<i}\ell_{ik}^2},\qquad \ell_{ij} = \frac{a_{ij} - \sum_{k<j}\ell_{ik}\ell_{jk}}{\ell_{jj}}\ (j < i)
$$
$$
c_i = \frac{b_i - \sum_{k<i}\ell_{ik}c_k}{\ell_{ii}},\qquad x_i = \frac{c_i - \sum_{k>i}\ell_{ki}x_k}{\ell_{ii}}
$$
Handout form: $L_1 = \sqrt{a_{11}}$; for $k = 2..n$: solve $L_{k-1}\ell_k = a_k$; $\ell_{kk} = \sqrt{a_{kk} - \ell_k^T\ell_k}$.

## 7. Non-linear equations ([[07.01 Fixed-Point Iteration]], [[07.02 Newton-Raphson Method]])
$$
x_{n+1} = g(x_n),\quad |g'(x)| \le K < 1 \text{ on } J \Rightarrow \text{convergence},\quad |x_n - s| \le K^n|x_0 - s|
$$
$$
x_{n+1} = x_n - \frac{f(x_n)}{f'(x_n)},\qquad \text{stop if } |x_{n+1} - x_n| \le \varepsilon|x_{n+1}|
$$

## 8. Finite differences ([[09.02 First Derivative Differences and Truncation Error]], [[09.03 Second and Mixed Derivatives]])
$$
f(x\pm\Delta x) = f(x) \pm \Delta x f' + \frac{\Delta x^2}{2!}f'' \pm \frac{\Delta x^3}{3!}f''' + \dots
$$
| Derivative | Forward | Backward | Central |
|---|---|---|---|
| $f'_i$ | $\frac{f_{i+1}-f_i}{\Delta x} + O(\Delta x)$ | $\frac{f_i - f_{i-1}}{\Delta x} + O(\Delta x)$ | $\frac{f_{i+1}-f_{i-1}}{2\Delta x} + O(\Delta x^2)$ |
| $f''_i$ | $\frac{f_{i+2}-2f_{i+1}+f_i}{\Delta x^2} + O(\Delta x)$ | $\frac{f_i - 2f_{i-1} + f_{i-2}}{\Delta x^2} + O(\Delta x)$ | $\frac{f_{i+1}-2f_i+f_{i-1}}{\Delta x^2} + O(\Delta x^2)$ |
| $f_{xy}$ | $\frac{f_{i+1,j+1}-f_{i,j+1}-f_{i+1,j}+f_{i,j}}{\Delta x\Delta y}$ | $\frac{f_{i,j}-f_{i-1,j}-f_{i,j-1}+f_{i-1,j-1}}{\Delta x\Delta y}$ | $\frac{f_{i+1,j+1}-f_{i-1,j+1}-f_{i+1,j-1}+f_{i-1,j-1}}{4\Delta x\Delta y}$ |

**Δ operator** ([[09.05 Difference Operator Delta]]): $\Delta f = f(x+h) - f(x)$; $\Delta e^{ax} = e^{ax}(e^{ah}-1)$; $\Delta^2e^x = (e^h-1)^2e^x$; $\Delta\log x = \log(1 + h/x)$; $\Delta^2\frac1x = \frac{2}{x(x+1)(x+2)}$ ($h = 1$).
**Tables** ([[09.04 Forward Difference Tables]]): $\Delta^ky_i = \Delta^{k-1}y_{i+1} - \Delta^{k-1}y_i$. **Compound interest:** $\Delta A_n = rA_n$, $A_{n+1} = (1+r)A_n$, $A_n = P(1+r)^n$.
