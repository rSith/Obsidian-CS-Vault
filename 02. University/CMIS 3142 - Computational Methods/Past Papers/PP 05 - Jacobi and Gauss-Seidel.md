---
type: past-paper
course: CMIS 3142
lesson: L05
status: complete
tags: [cmis3142, past-paper, cmis3142/L05]
---
# PP 05 · Jacobi and Gauss-Seidel: Past-Paper Questions

> [!info] Lesson: [[05.00 Iterative Methods - Jacobi and Gauss-Seidel]] · Summary: [[05.99 Summary - Jacobi and Gauss-Seidel]] · [[CMIS 3142 Past Paper Mapping]]
> Examined in **3 of 4** papers (2024, 2025, 2026). Priority 🔴.

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2024 | 4(a) | Numerical algorithm for Jacobi | – |
| 2024 | 4(b) | Numerical algorithm for Gauss-Seidel | – |
| 2024 | 4(c) | Solve $x+y+z=3$, $2x-y-z=3$, $x-y+z=9$ by Jacobi | – |
| 2024 | 4(d) | Same system by Gauss-Seidel | – |
| 2024 | 4(e) | Compare and contrast Jacobi and Gauss-Seidel | – |
| 2025 | 4(a) | Main difference between Jacobi and Gauss-Seidel | – |
| 2025 | 4(b)(i) | Fruit-price scenario → system of equations | – |
| 2025 | 4(b)(ii) | Jacobi from (1, 1, 2), max 10 iterations | – |
| 2025 | 4(b)(iii) | Gauss-Seidel from (1, 1, 2), max 6 iterations | – |
| 2025 | 4(b)(iv) | Comment on the performance of both methods | – |
| 2025 | 4(c) | Modify a Jacobi program into Gauss-Seidel | – |
| 2026 | 4(c) | Solve $6x+2y+z=22$, $2x+8y+z=32$, $x+2y+10z=48$ by Gauss-Seidel from (1, 2, 3) | 15 |
| 2026 | 4(d) | Algorithm for Gauss-Seidel | 10 |

---

## 2024 · Q4(a)
> **Question:** Write a numerical algorithm for Jacobi method.

**Lesson:** [[05.01 Jacobi Method]] §6

### Expected answer
**Input:** $A = [a_{ij}]$ ($a_{ii} \ne 0$), $b$, initial approximation $XO = x^{(0)}$, tolerance $TOL$, maximum iterations $N$.
```text
Step 1  Set k = 1
Step 2  While (k <= N) do Steps 3–6
    Step 3  For i = 1, ..., n
                x_i = (1/a_ii) [ Σ_{j=1, j≠i}^{n} (−a_ij XO_j) + b_i ]
    Step 4  If ||x − XO|| < TOL then OUTPUT (x_1, ..., x_n); STOP
    Step 5  Set k = k + 1
    Step 6  For i = 1, ..., n set XO_i = x_i
Step 7  OUTPUT ("Maximum number of iterations exceeded"); STOP
```
Alternative Step 4 test: $\|x^{(k)} - x^{(k-1)}\|/\|x^{(k)}\| < TOL$.

## 2024 · Q4(b) · 2026 · Q4(d) (10 marks)
> **Question:** Write a numerical algorithm for Gauss-Seidel method. / Write an algorithm to solve a set of linear equations using the Gauss-Seidel method.

**Lesson:** [[05.02 Gauss-Seidel Method]] §7

### Expected answer
Same as Jacobi except Step 3 uses the **new** values for $j < i$:
```text
Step 1  Set k = 1
Step 2  While (k <= N) do Steps 3–6
    Step 3  For i = 1, ..., n
                x_i = (1/a_ii) [ − Σ_{j=1}^{i−1} a_ij x_j − Σ_{j=i+1}^{n} a_ij XO_j + b_i ]
    Step 4  If ||x − XO|| < TOL then OUTPUT (x_1, ..., x_n); STOP
    Step 5  Set k = k + 1
    Step 6  For i = 1, ..., n set XO_i = x_i
Step 7  OUTPUT ("Maximum number of iterations exceeded"); STOP
```

### Key points to include
Inputs, non-zero diagonal assumption, the update formula, stopping test, max-iteration failure exit.

---

## 2024 · Q4(c)(d)
> **Question:** (c) Solve the following equations using Jacobi method: i) x + y + z = 3 ii) 2x − y − z = 3 iii) x − y + z = 9. (d) Solve the above equations using the Gauss-Seidel method.

**Lesson:** [[05.01 Jacobi Method]] · [[05.02 Gauss-Seidel Method]] · [[05.03 Convergence of Iterative Methods]]

### Expected answer
**Convergence check first.** $|1| < |1| + |1|$, $|-1| < |2| + |-1|$, $|1| < |1| + |-1|$: the matrix is **not diagonally dominant**, and no reordering of the equations makes it strictly dominant (the best candidate, $x$ from equation (ii), gives $|2| = |-1| + |-1|$, not $>$). Convergence is therefore **not guaranteed** (Theorem 7.21 does not apply).

**Rearrangement** (each equation for its diagonal unknown):
$$
x = 3 - y - z,\qquad y = 2x - z - 3,\qquad z = 9 - x + y
$$

**(c) Jacobi** from $(0, 0, 0)$:
| $k$ | $x$ | $y$ | $z$ |
|---|---|---|---|
| 0 | 0 | 0 | 0 |
| 1 | 3 | −3 | 9 |
| 2 | −3 | −6 | 3 |
| 3 | 6 | −12 | 6 |
| 4 | 9 | 3 | −9 |
| 5 | 9 | 24 | 3 |

**(d) Gauss-Seidel** from $(0, 0, 0)$:
| $k$ | $x$ | $y$ | $z$ |
|---|---|---|---|
| 1 | 3 | 3 | 9 |
| 2 | −9 | −30 | −12 |
| 3 | 45 | 99 | 63 |
| 4 | −159 | −384 | −216 |

Both sequences **diverge** (the iteration matrices have spectral radii $\rho(T_j) \approx 1.73$ and $\rho(T_g) \approx 3.73$, both > 1). The exact solution, found by Gaussian elimination, is $x = 2$, $y = -3$, $z = 4$ (check: $2 - 3 + 4 = 3$, $4 + 3 - 4 = 3$, $2 + 3 + 4 = 9$ ✓).

> [!warning] What the examiner probably wanted
> The question gives no starting vector or stopping rule, and the system is not diagonally dominant, so neither method can produce the answer. The strongest answer: show the convergence check, show a few iterations demonstrating divergence, explain why ($\rho(T) > 1$, no diagonal dominance), and give the exact solution by a direct method. It is also possible the paper contains a typo; the solution notes cannot confirm the intended system.

## 2024 · Q4(e)
> **Question:** Compare and contrast Jacobi and Gauss-Seidel method.

**Lesson:** [[05.03 Convergence of Iterative Methods]] §5

### Expected answer
**Similarities:** both iterative methods for $Ax = b$; both rewrite equation $i$ for $x_i$ (need $a_{ii} \ne 0$); both start from an initial guess and stop on a tolerance or maximum iterations; both have the form $x^{(k)} = Tx^{(k-1)} + c$ and converge iff $\rho(T) < 1$; both are guaranteed to converge for strictly diagonally dominant $A$.

**Differences:**
| Jacobi | Gauss-Seidel |
|---|---|
| uses only values from the previous iteration | uses each new value immediately in the same iteration |
| $T_j = D^{-1}(L+U)$ | $T_g = (D-L)^{-1}U$ |
| needs two vectors (old, new) | one vector, updated in place |
| slower | usually faster (Stein–Rosenberg: $\rho(T_g) < \rho(T_j)$ when both converge, for matrices with non-positive off-diagonals) |
| components independent → easy to parallelise | sequential |
| result independent of equation order within a sweep | depends on the order |

---

## 2025 · Q4(a)
> **Question:** What is the main difference between Jacobi method and Gauss-Seidel method?

### Expected answer
In the **Jacobi** method, all new values $x_i^{(k)}$ are computed from the values of the **previous** iteration $x^{(k-1)}$; they stay unchanged until the whole iteration is finished. In **Gauss-Seidel**, each newly computed value is **used immediately** in the remaining equations of the **same** iteration (e.g. new $x_1$ is used to compute $x_2$). This usually makes Gauss-Seidel converge faster and needs only one solution vector.

---

## 2025 · Q4(b)
> **Question:** There are three students called Akila, Bala and Pathma and they have 15, 28 and 18 rupees respectively. Akila bought 8 pine apples, 2 mangos and 1 apple; Bala bought 4 pine apples 9 mangos and 2 apples; Pathma bought 1 pine apple, 1 mangos and 5 apples;
> i) Formulate a system of linear equations to find the price of each fruit. ii) Solve the above problem using Jacobi method for which you may use the initial values (1, 1, 2) respectively. You may go for a maximum of 10 iterations. iii) Solve the above problem using Gauss Seidel method for which you may use the initial values (1, 1, 2) respectively. You may go for a maximum of 6 iterations. iv) Comment on the performance of both of those methods.

**Lesson:** [[04.01 Systems of Linear Equations and Augmented Matrix]] · [[05.01 Jacobi Method]] · [[05.02 Gauss-Seidel Method]] · [[05.03 Convergence of Iterative Methods]]

### Expected answer
**(i)** Let $p, m, a$ = price (Rs) of one pineapple, mango, apple (assume each spent all her/his money):
$$
8p + 2m + a = 15,\qquad 4p + 9m + 2a = 28,\qquad p + m + 5a = 18
$$
Strictly diagonally dominant ($8 > 3$, $9 > 6$, $5 > 2$) → both methods converge.
$$
p = \frac{15 - 2m - a}{8},\qquad m = \frac{28 - 4p - 2a}{9},\qquad a = \frac{18 - p - m}{5}
$$

**(ii) Jacobi** from $(1, 1, 2)$. Iteration 1: $p = \frac{15 - 2 - 2}{8} = 1.3750$, $m = \frac{28 - 4 - 4}{9} = 2.2222$, $a = \frac{18 - 1 - 1}{5} = 3.2000$.

| $k$ | $p$ | $m$ | $a$ |
|---|---|---|---|
| 0 | 1.0000 | 1.0000 | 2.0000 |
| 1 | 1.3750 | 2.2222 | 3.2000 |
| 2 | 0.9194 | 1.7889 | 2.8806 |
| 3 | 1.0677 | 2.0623 | 3.0583 |
| 4 | 0.9771 | 1.9569 | 2.9740 |
| 5 | 1.0140 | 2.0159 | 3.0132 |
| 6 | 0.9944 | 1.9908 | 2.9940 |
| 7 | 1.0030 | 2.0038 | 3.0030 |
| 8 | 0.9987 | 1.9980 | 2.9986 |
| 9 | 1.0007 | 2.0009 | 3.0007 |
| 10 | 0.9997 | 1.9996 | 2.9997 |

**(iii) Gauss-Seidel** from $(1, 1, 2)$. Iteration 1: $p = \frac{15 - 2(1) - 2}{8} = 1.3750$; $m = \frac{28 - 4(1.375) - 2(2)}{9} = \frac{18.5}{9} = 2.0556$; $a = \frac{18 - 1.375 - 2.0556}{5} = 2.9139$.

| $k$ | $p$ | $m$ | $a$ |
|---|---|---|---|
| 1 | 1.3750 | 2.0556 | 2.9139 |
| 2 | 0.9969 | 2.0205 | 2.9965 |
| 3 | 0.9953 | 2.0029 | 3.0004 |
| 4 | 0.9992 | 2.0003 | 3.0001 |
| 5 | 0.9999 | 2.0000 | 3.0000 |
| 6 | 1.0000 | 2.0000 | 3.0000 |

**Prices: pineapple Rs 1, mango Rs 2, apple Rs 3.**

**(iv) Performance.** Both converge (diagonally dominant matrix). Gauss-Seidel reaches 4-decimal accuracy in about 5–6 iterations, while Jacobi after 10 iterations is still only accurate to about 3 decimals and **oscillates** above and below the solution. Gauss-Seidel is faster because each new value is used immediately; its iteration matrix has a much smaller spectral radius ($\approx 0.11$ vs $\approx 0.48$ for Jacobi), so the error shrinks by a factor of about 9 per iteration instead of about 2.

### How to approach this question
Formulate → check dominance → rearranged equations → show iteration 1 in full for each method → tables (4 d.p.) → comment with numbers.

---

## 2025 · Q4(c)
> **Question:** Suppose you have a Java program to solve a system of linear equations using Jacobi method. Describe the way to modify the computer program for solving the system of linear equation using Gauss Seidel method.

**Lesson:** [[05.02 Gauss-Seidel Method]] §9

### Expected answer
1. Jacobi keeps two arrays: it reads old values `xOld[]` and writes new values `x[]`, then copies `x` into `xOld` after each sweep.
2. For Gauss-Seidel, use **a single array** `x[]`: in the inner sum replace `A[i][j] * xOld[j]` by `A[i][j] * x[j]`, and store the result straight into `x[i]`. Then values with $j < i$ are already the new ones.
3. Keep a copy of `x` taken **at the start** of each sweep only for the stopping test ($\|x - x_{old}\| < TOL$).
4. Remove the end-of-sweep copy step. Input, max iterations and output stay the same.
```java
// Jacobi:                         // Gauss-Seidel:
sum -= A[i][j] * xOld[j];          sum -= A[i][j] * x[j];
x[i] = sum / A[i][i];              x[i] = sum / A[i][i];
```

---

## 2026 · Q4(c) (15 marks)
> **Question:** Solve the following equations using Gauss-Seidel method with initial values x=1; y=2; z=3: i) 6x + 2y + z = 22 ii) 2x + 8y + z = 32 iii) x + 2y + 10z = 48

**Lesson:** [[05.02 Gauss-Seidel Method]]

### Expected answer
Diagonally dominant ($6 > 3$, $8 > 3$, $10 > 3$) → converges.
$$
x = \frac{22 - 2y - z}{6},\qquad y = \frac{32 - 2x - z}{8},\qquad z = \frac{48 - x - 2y}{10}
$$
**Iteration 1** (start $x = 1, y = 2, z = 3$):
- $x = \frac{22 - 2(2) - 3}{6} = \frac{15}{6} = 2.5000$
- $y = \frac{32 - 2(2.5) - 3}{8} = \frac{24}{8} = 3.0000$
- $z = \frac{48 - 2.5 - 2(3)}{10} = \frac{39.5}{10} = 3.9500$

**Iteration 2:**
- $x = \frac{22 - 6 - 3.95}{6} = \frac{12.05}{6} = 2.0083$
- $y = \frac{32 - 4.0167 - 3.95}{8} = \frac{24.0333}{8} = 3.0042$
- $z = \frac{48 - 2.0083 - 6.0083}{10} = \frac{39.9833}{10} = 3.9983$

| $k$ | $x$ | $y$ | $z$ |
|---|---|---|---|
| 0 | 1.0000 | 2.0000 | 3.0000 |
| 1 | 2.5000 | 3.0000 | 3.9500 |
| 2 | 2.0083 | 3.0042 | 3.9983 |
| 3 | 1.9989 | 3.0005 | 4.0000 |
| 4 | 1.9998 | 3.0000 | 4.0000 |
| 5 | 2.0000 | 3.0000 | 4.0000 |

**Solution: $x = 2$, $y = 3$, $z = 4$** (check: $12 + 6 + 4 = 22$ ✓).

### How to approach this question
15 marks: dominance check, rearranged equations, two iterations in full showing that new values are used, table, final answer and check.
