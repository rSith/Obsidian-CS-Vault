---
type: past-paper
course: CMIS 3142
lesson: L06
status: complete
tags: [cmis3142, past-paper, cmis3142/L06]
---
# PP 06 · LU and Cholesky: Past-Paper Questions

> [!info] Lesson: [[06.00 LU Decomposition and Cholesky]] · Summary: [[06.99 Summary - LU and Cholesky]] · [[CMIS 3142 Past Paper Mapping]]
> Cholesky is examined in **every** paper. Priority 🔴. LU on its own has never been asked.

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2023 | 5(a) | Algorithm for $AX = B$ by Cholesky (A symmetric) | 9 |
| 2023 | 5(b) | Solve the 4×4 lecture example | 10 |
| 2023 | 5(c) | Limitations of the Cholesky method | 6 |
| 2024 | 5(c) | Solve $25x+15y-5z=35$, $15x+18y=33$, $-5x+11z=6$ | – |
| 2025 | 5(b) | Java program for Cholesky + essential conditions | – |
| 2025 | 5(c) | Solve $8x+2y+3z=13$, $2x+10y+4z=16$, $3x+4y+9z=16$ | – |
| 2026 | 6(b) | Java Cholesky using the symmetric representation | 15 |
| 2026 | 6(c) | Specialty of the Cholesky method | 5 |
| 2026 | 6(d) | Solve $25x+15y-5z=70$, $15x+18y=66$, $-5x+11z=12$ | 20 |

---

## 2023 · Q5(a) (9 marks)
> **Question:** Develop an algorithm to solve a given matrix equation $AX = B$ using the Cholesky decomposition method where A is a symmetric matrix, and X and B are column vectors.

**Lesson:** [[06.02 Cholesky Method]] §3–5

### Expected answer
```text
INPUT n, A (n×n), B (n×1)
1. If A is not symmetric (a_ij ≠ a_ji for some i, j): OUTPUT "not symmetric"; STOP
2. Factorise A = L Lᵀ:
     for i = 1 to n
         for j = 1 to i
             s = Σ_{k=1}^{j−1} l_ik l_jk
             if i = j:
                 d = a_ii − s
                 if d ≤ 0: OUTPUT "A is not positive definite"; STOP
                 l_ii = √d
             else:
                 l_ij = (a_ij − s) / l_jj
3. Forward substitution (L C = B):  for i = 1..n:  c_i = (b_i − Σ_{k<i} l_ik c_k) / l_ii
4. Back substitution (Lᵀ X = C):    for i = n..1:  x_i = (c_i − Σ_{k>i} l_ki x_k) / l_ii
OUTPUT X
```
(The handout's block form is also accepted: $L_1 = \sqrt{a_{11}}$; for $k = 2..n$ solve $L_{k-1}\ell_k = a_k$, $\ell_{kk} = \sqrt{a_{kk} - \ell_k^T\ell_k}$.)

### Key points to include
SPD requirement and the check; factorisation formulas; both substitutions; output.

---

## 2023 · Q5(b) (10 marks)
> **Question:** Using the algorithm you developed in part (a), solve the matrix equation where
> $$A = \begin{bmatrix}16&4&4&-4\\4&10&4&2\\4&4&6&-2\\-4&2&-2&4\end{bmatrix},\quad B = \begin{bmatrix}32\\26\\20\\-6\end{bmatrix}$$

**Lesson:** [[06.02 Cholesky Method]] §6 (this is the handout's worked example)

### Expected answer
$A$ is symmetric ✓.
- $\ell_{11} = \sqrt{16} = 4$; $\ell_{21} = 4/4 = 1$; $\ell_{31} = 4/4 = 1$; $\ell_{41} = -4/4 = -1$
- $\ell_{22} = \sqrt{10 - 1^2} = 3$; $\ell_{32} = (4 - 1\cdot1)/3 = 1$; $\ell_{42} = (2 - (-1)(1))/3 = 1$
- $\ell_{33} = \sqrt{6 - 1^2 - 1^2} = 2$; $\ell_{43} = (-2 - (-1)(1) - (1)(1))/2 = -1$
- $\ell_{44} = \sqrt{4 - (-1)^2 - 1^2 - (-1)^2} = 1$
$$
L = \begin{bmatrix}4&0&0&0\\1&3&0&0\\1&1&2&0\\-1&1&-1&1\end{bmatrix}
$$
**$LC = B$:** $c_1 = 32/4 = 8$; $c_2 = (26 - 8)/3 = 6$; $c_3 = (20 - 8 - 6)/2 = 3$; $c_4 = -6 + 8 - 6 + 3 = -1$.
**$L^TX = C$:** $x_4 = -1$; $x_3 = (3 + x_4)/2 = 1$; $x_2 = (6 - x_3 - x_4)/3 = 2$; $x_1 = (8 - x_2 - x_3 + x_4)/4 = 1$.
$$
\boxed{X = (1,\ 2,\ 1,\ -1)^T}
$$
Check row 1: $16 + 8 + 4 + 4 = 32$ ✓.

---

## 2023 · Q5(c) (6 marks)
> **Question:** What are the limitations in the Cholesky method?

**Lesson:** [[06.02 Cholesky Method]] §8

### Expected answer
1. Works **only for symmetric positive definite** matrices; not for non-symmetric, indefinite or singular systems (use LU/GE).
2. If $A$ is not SPD the algorithm **breaks down**: a zero diagonal entry in $L$ (division by zero) or a **square root of a negative number** (complex values / NaN), as the handout theorem states.
3. Checking positive definiteness beforehand is expensive; usually discovered only during factorisation.
4. Requires $n$ **square roots** (costly, extra round-off).
5. For **sparse** matrices, $L$ suffers **fill-in** (non-zeros appear where $A$ had zeros), losing the memory advantage.
6. Still an $O(n^3)$ direct method; for very large sparse systems iterative methods may be better; accuracy limited by the conditioning of $A$.

---

## 2024 · Q5(c)
> **Question:** Solve the following equations using the Cholesky decomposition method: i) 25x + 15y − 5z = 35 ii) 15x + 18y = 33 iii) −5x + 11z = 6

**Lesson:** [[06.02 Cholesky Method]] §7

### Expected answer
$A = \begin{bmatrix}25&15&-5\\15&18&0\\-5&0&11\end{bmatrix}$ is symmetric; positive definite (all square-root arguments below are positive).
- $\ell_{11} = \sqrt{25} = 5$, $\ell_{21} = 15/5 = 3$, $\ell_{31} = -5/5 = -1$
- $\ell_{22} = \sqrt{18 - 9} = 3$, $\ell_{32} = (0 - (-1)(3))/3 = 1$
- $\ell_{33} = \sqrt{11 - 1 - 1} = 3$
$$
L = \begin{bmatrix}5&0&0\\3&3&0\\-1&1&3\end{bmatrix},\qquad L^T = \begin{bmatrix}5&3&-1\\0&3&1\\0&0&3\end{bmatrix}
$$
**$Lc = b$:** $5c_1 = 35 \Rightarrow c_1 = 7$; $3(7) + 3c_2 = 33 \Rightarrow c_2 = 4$; $-7 + 4 + 3c_3 = 6 \Rightarrow c_3 = 3$.
**$L^Tx = c$:** $3z = 3 \Rightarrow z = 1$; $3y + z = 4 \Rightarrow y = 1$; $5x + 3y - z = 7 \Rightarrow x = 1$.
$$
\boxed{x = y = z = 1}
$$
Calculators were not allowed in 2024: this matrix gives integers throughout.

---

## 2025 · Q5(b)
> **Question:** Write a Java program to solve a system of linear equations using Cholesky method. Describe the essential conditions to be satisfied to apply the Cholesky method.

**Lesson:** [[06.03 Cholesky Java Program]] §2 (complete tested program) · [[06.02 Cholesky Method]] §2

### Expected answer
**Conditions:** $A$ must be (1) **square**, (2) **symmetric** ($a_{ij} = a_{ji}$), (3) **positive definite** ($x^TAx > 0$ for all $x \ne 0$; equivalently all eigenvalues / leading principal minors positive; in practice every value under the square root is positive). Then $A = LL^T$ exists with positive diagonal.

**Program** (core):
```java
static double[] choleskySolve(double[][] A, double[] b) {
    int n = b.length;
    double[][] L = new double[n][n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j <= i; j++) {
            if (A[i][j] != A[j][i]) throw new IllegalArgumentException("Not symmetric");
            double sum = 0;
            for (int k = 0; k < j; k++) sum += L[i][k] * L[j][k];
            if (i == j) {
                double d = A[i][i] - sum;
                if (d <= 0) throw new IllegalArgumentException("Not positive definite");
                L[i][i] = Math.sqrt(d);
            } else L[i][j] = (A[i][j] - sum) / L[j][j];
        }
    double[] c = new double[n];                           // L c = b
    for (int i = 0; i < n; i++) {
        double s = b[i];
        for (int k = 0; k < i; k++) s -= L[i][k] * c[k];
        c[i] = s / L[i][i];
    }
    double[] x = new double[n];                           // L^T x = c
    for (int i = n - 1; i >= 0; i--) {
        double s = c[i];
        for (int k = i + 1; k < n; k++) s -= L[k][i] * x[k];
        x[i] = s / L[i][i];
    }
    return x;
}
```
Plus a `main` reading $n$, $A$, $b$ and printing $x$.

---

## 2025 · Q5(c)
> **Question:** Solve the following system of equations using the Cholesky decomposition method: i) 8x + 2y + 3z = 13 ii) 2x + 10y + 4Z = 16 iii) 3x + 4y + 9z = 16

**Lesson:** [[06.02 Cholesky Method]]

### Expected answer
$A = \begin{bmatrix}8&2&3\\2&10&4\\3&4&9\end{bmatrix}$ symmetric ✓ (calculators permitted in 2025).
- $\ell_{11} = \sqrt8 = 2.8284$
- $\ell_{21} = 2/2.8284 = 0.7071$, $\ell_{31} = 3/2.8284 = 1.0607$
- $\ell_{22} = \sqrt{10 - 0.7071^2} = \sqrt{9.5} = 3.0822$
- $\ell_{32} = (4 - 1.0607\times0.7071)/3.0822 = (4 - 0.75)/3.0822 = 1.0544$
- $\ell_{33} = \sqrt{9 - 1.0607^2 - 1.0544^2} = \sqrt{9 - 1.125 - 1.1118} = \sqrt{6.7632} = 2.6006$
$$
L = \begin{bmatrix}2.8284&0&0\\0.7071&3.0822&0\\1.0607&1.0544&2.6006\end{bmatrix}
$$
**$Lc = b$:** $c_1 = 13/2.8284 = 4.5962$; $c_2 = (16 - 0.7071\times4.5962)/3.0822 = 12.75/3.0822 = 4.1366$; $c_3 = (16 - 1.0607\times4.5962 - 1.0544\times4.1366)/2.6006 = 6.7633/2.6006 = 2.6006$.
**$L^Tx = c$:** $z = 2.6006/2.6006 = 1$; $y = (4.1366 - 1.0544\times1)/3.0822 = 1$; $x = (4.5962 - 0.7071\times1 - 1.0607\times1)/2.8284 = 1$.
$$
\boxed{x = y = z = 1}
$$
Check: $8 + 2 + 3 = 13$ ✓. (Exact forms: $\ell_{11} = 2\sqrt2$, $\ell_{21} = 1/\sqrt2$, $\ell_{31} = 3/(2\sqrt2)$, $\ell_{22} = \sqrt{19/2}$.)

---

## 2026 · Q6(b) (15 marks)
> **Question:** Using the above method [representation of a symmetric matrix, Q6(a)], write a Java program to solve a set of linear equations using the Cholesky method.

**Lesson:** [[06.03 Cholesky Java Program]] §3 (tested) · [[03.03 Symmetric Matrix Storage]]

### Expected answer
Store $A$ as its lower triangle in a row vector, index $k = i(i+1)/2 + j$ ($i \ge j$). $L$ is lower triangular, so it uses the same packed layout:
```java
static int idx(int i, int j) { if (j > i) { int t = i; i = j; j = t; } return i * (i + 1) / 2 + j; }

static double[] solve(double[] A, double[] b, int n) {      // A packed, n(n+1)/2 values
    double[] L = new double[n * (n + 1) / 2];
    for (int i = 0; i < n; i++)
        for (int j = 0; j <= i; j++) {
            double sum = 0;
            for (int k = 0; k < j; k++) sum += L[idx(i, k)] * L[idx(j, k)];
            if (i == j) {
                double d = A[idx(i, i)] - sum;
                if (d <= 0) throw new ArithmeticException("Not positive definite");
                L[idx(i, i)] = Math.sqrt(d);
            } else L[idx(i, j)] = (A[idx(i, j)] - sum) / L[idx(j, j)];
        }
    double[] c = new double[n];
    for (int i = 0; i < n; i++) {                            // L c = b
        double s = b[i];
        for (int k = 0; k < i; k++) s -= L[idx(i, k)] * c[k];
        c[i] = s / L[idx(i, i)];
    }
    double[] x = new double[n];
    for (int i = n - 1; i >= 0; i--) {                       // L^T x = c, (L^T)[i][k] = L[k][i]
        double s = c[i];
        for (int k = i + 1; k < n; k++) s -= L[idx(k, i)] * x[k];
        x[i] = s / L[idx(i, i)];
    }
    return x;
}
```
Test: $A$ = `{25, 15, 18, -5, 0, 11}`, $b = (70, 66, 12)$ → $x = (2, 2, 2)$.

### Key points to include
Packed storage + index function; why $L$ fits it; SPD check; both substitutions; symmetry guaranteed by the storage.

---

## 2026 · Q6(c) (5 marks)
> **Question:** What is the specialty in the Cholesky method?

**Lesson:** [[06.02 Cholesky Method]] §9

### Expected answer
- Factorises an SPD matrix as $A = LL^T$: a lower-triangular matrix times **its own transpose**, so only **one** factor is computed and stored.
- Exploits symmetry: about **half the work** ($\approx n^3/3$ vs $2n^3/3$ for LU) and **half the storage**; $A$ and $L$ both fit in packed triangular storage.
- **No pivoting** needed and numerically **stable** for SPD matrices.
- Succeeds **iff** $A$ is SPD, so it also **tests** positive definiteness.

---

## 2026 · Q6(d) (20 marks)
> **Question:** Solve the following set of equations using Cholesky method: i) 25x+15y−5z=70; ii) 15x+18y=66; iii) −5x+11z=12

**Lesson:** [[06.02 Cholesky Method]] §7

### Expected answer
Same $A$ as 2024 Q5(c), so the same factor:
$$
L = \begin{bmatrix}5&0&0\\3&3&0\\-1&1&3\end{bmatrix}
$$
(work: $\ell_{11} = \sqrt{25} = 5$; $\ell_{21} = 15/5 = 3$; $\ell_{31} = -5/5 = -1$; $\ell_{22} = \sqrt{18 - 9} = 3$; $\ell_{32} = (0 + 3)/3 = 1$; $\ell_{33} = \sqrt{11 - 1 - 1} = 3$)

**Verify** $LL^T = A$: row 2 · col 2 $= 9 + 9 = 18$ ✓; row 3 · col 3 $= 1 + 1 + 9 = 11$ ✓.

**$Lc = b$:** $c_1 = 70/5 = 14$; $c_2 = (66 - 3\cdot14)/3 = 8$; $c_3 = (12 + 14 - 8)/3 = 6$.
**$L^Tx = c$:** $z = 6/3 = 2$; $y = (8 - z)/3 = 2$; $x = (14 - 3y + z)/5 = (14 - 6 + 2)/5 = 2$.
$$
\boxed{x = y = z = 2}
$$
Check: $50 + 30 - 10 = 70$ ✓.

### How to approach this question
20 marks: conditions (symmetric, PD), each $\ell_{ij}$ with formula, $L$, verification, forward substitution, back substitution, check.
