---
type: past-paper
course: CMIS 3142
lesson: L08
status: complete
tags: [cmis3142, past-paper, cmis3142/L08]
---
# PP 08 · Successive Over-Relaxation (SOR): Past-Paper Questions

> [!info] Lesson: [[08.00 Successive Over-Relaxation]] · Summary: [[08.99 Summary - SOR]] · [[CMIS 3142 Past Paper Mapping]]
> Examined in **3 of 4** papers, always with $\omega = 1.25$ and start $(0, 0, 0)$. Priority 🔴.

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2024 | 5(b) | SOR: $10x+2y-z=7$, $x+8y+3z=-4$, $-2x-y+10z=9$ | – |
| 2025 | 5(a) | SOR: $15x+2y+z=22$, $4x+15y+2z=40$, $x+y+15z=48$ | – |
| 2026 | 4(a) | SOR: $6x+2y+2z=10$, $2x+8y-5z=5$, $3x-4y+10z=9$ | 10 |
| 2026 | 4(b) | Java program for SOR | 15 |

**Formula used in all answers** ([[08.01 SOR Method]]):
$$
x_i^{(k)} = (1-\omega)x_i^{(k-1)} + \frac{\omega}{a_{ii}}\Big[b_i - \sum_{j<i}a_{ij}x_j^{(k)} - \sum_{j>i}a_{ij}x_j^{(k-1)}\Big],\qquad 1 - \omega = -0.25
$$

---

## 2024 · Q5(b)
> **Question:** Solve the following equations using SOR (Successive over-relaxation) method (with initial guess (0,0,0) and w = 1.25): i) 10x + 2y − z = 7 ii) x + 8y + 3z = −4 iii) −2x − y + 10z = 9

### Expected answer
Diagonally dominant ($10 > 3$, $8 > 4$, $10 > 3$) → converges.

**SOR equations:**
$$
x \leftarrow -0.25x + \frac{1.25}{10}(7 - 2y + z),\quad
y \leftarrow -0.25y + \frac{1.25}{8}(-4 - x - 3z),\quad
z \leftarrow -0.25z + \frac{1.25}{10}(9 + 2x + y)
$$

**Iteration 1** (from 0, 0, 0):
- $x = 0 + 0.125(7) = 0.8750$
- $y = 0 + 0.15625(-4 - 0.875 - 0) = 0.15625(-4.875) = -0.7617$
- $z = 0 + 0.125(9 + 1.75 - 0.7617) = 0.125(9.9883) = 1.2485$

**Iteration 2:**
- $x = -0.25(0.875) + 0.125(7 + 1.5234 + 1.2485) = -0.2188 + 1.2215 = 1.0027$
- $y = -0.25(-0.7617) + 0.15625(-4 - 1.0027 - 3.7456) = 0.1904 - 1.3669 = -1.1765$
- $z = -0.25(1.2485) + 0.125(9 + 2.0055 - 1.1765) = -0.3121 + 1.2286 = 0.9165$

| $k$ | $x$ | $y$ | $z$ |
|---|---|---|---|
| 1 | 0.8750 | −0.7617 | 1.2485 |
| 2 | 1.0027 | −1.1765 | 0.9165 |
| 3 | 1.0330 | −0.9219 | 1.0389 |
| 4 | 0.9771 | −1.0342 | 0.9803 |
| 5 | 1.0118 | −0.9841 | 1.0099 |
| 6 | 0.9943 | −1.0077 | 0.9951 |
| 7 | 1.0027 | −0.9962 | 1.0024 |
| 8 | 0.9987 | −1.0018 | 0.9988 |

**Solution: $x = 1$, $y = -1$, $z = 1$** (check: $10 - 2 - 1 = 7$ ✓). (2024 had no calculators: two full iterations plus the converged answer and check is a realistic exam answer.)

---

## 2025 · Q5(a)
> **Question:** Solve the following equations using SOR (Successive over-relaxation) method (with initial guess (0,0,0) and w = 1.25): i) 15x + 2y + z = 22 ii) 4x + 15y + 2z = 40 iii) x + y + 15z = 48

### Expected answer
Strongly diagonally dominant → converges quickly.
$$
x \leftarrow -0.25x + \frac{1.25}{15}(22 - 2y - z),\quad
y \leftarrow -0.25y + \frac{1.25}{15}(40 - 4x - 2z),\quad
z \leftarrow -0.25z + \frac{1.25}{15}(48 - x - y)
$$
**Iteration 1:**
- $x = 0.083333(22) = 1.8333$
- $y = 0.083333(40 - 7.3333) = 0.083333(32.6667) = 2.7222$
- $z = 0.083333(48 - 1.8333 - 2.7222) = 0.083333(43.4444) = 3.6204$

**Iteration 2:**
- $x = -0.25(1.8333) + 0.083333(22 - 5.4444 - 3.6204) = -0.4583 + 1.0779 = 0.6196$
- $y = -0.25(2.7222) + 0.083333(40 - 2.4784 - 7.2407) = -0.6806 + 2.5234 = 1.8428$
- $z = -0.25(3.6204) + 0.083333(48 - 0.6196 - 1.8428) = -0.9051 + 3.7948 = 2.8897$

| $k$ | $x$ | $y$ | $z$ |
|---|---|---|---|
| 1 | 1.8333 | 2.7222 | 3.6204 |
| 2 | 0.6196 | 1.8428 | 2.8897 |
| 3 | 1.1305 | 2.0142 | 3.0155 |
| 4 | 0.9637 | 2.0060 | 2.9986 |
| 5 | 1.0082 | 1.9960 | 3.0000 |
| 6 | 0.9986 | 2.0015 | 3.0000 |
| 7 | 1.0001 | 1.9996 | 3.0000 |
| 8 | 1.0000 | 2.0001 | 3.0000 |

**Solution: $x = 1$, $y = 2$, $z = 3$** (check: $15 + 4 + 3 = 22$ ✓).

> [!note] Observation worth writing
> For this very diagonally dominant system plain Gauss-Seidel already converges extremely fast ($\rho_{GS} \approx 0.03$); over-relaxation with $\omega = 1.25$ actually **overshoots** (see $x$: 1.83 → 0.62 → 1.13). $\omega$ helps most when Gauss-Seidel is slow.

---

## 2026 · Q4(a) (10 marks)
> **Question:** Solve the following equations using SOR (Successive over-relaxation) method. Assume ω = 1.25 using the initial values for x=0, y=0 and z=0: i) 6x + 2y + 2z = 10 ii) 2x + 8y − 5z = 5 iii) 3x − 4y + 10z = 9

### Expected answer
Diagonal dominance: $6 > 4$ ✓, $8 > 7$ ✓, $10 > 7$ ✓ → converges.
$$
x \leftarrow -0.25x + \frac{1.25}{6}(10 - 2y - 2z),\quad
y \leftarrow -0.25y + \frac{1.25}{8}(5 - 2x + 5z),\quad
z \leftarrow -0.25z + \frac{1.25}{10}(9 - 3x + 4y)
$$
**Iteration 1:**
- $x = 0.208333(10) = 2.0833$
- $y = 0.15625(5 - 4.1667 + 0) = 0.15625(0.8333) = 0.1302$
- $z = 0.125(9 - 6.25 + 0.5208) = 0.125(3.2708) = 0.4089$

**Iteration 2:**
- $x = -0.25(2.0833) + 0.208333(10 - 0.2604 - 0.8177) = -0.5208 + 1.8587 = 1.3379$
- $y = -0.25(0.1302) + 0.15625(5 - 2.6758 + 2.0443) = -0.0326 + 0.6826 = 0.6500$
- $z = -0.25(0.4089) + 0.125(9 - 4.0137 + 2.6001) = -0.1022 + 0.9483 = 0.8461$

| $k$ | $x$ | $y$ | $z$ |
|---|---|---|---|
| 1 | 2.0833 | 0.1302 | 0.4089 |
| 2 | 1.3379 | 0.6500 | 0.8461 |
| 3 | 1.1255 | 0.9280 | 0.9554 |
| 4 | 1.0172 | 0.9778 | 0.9936 |
| 5 | 1.0076 | 0.9982 | 0.9978 |
| 6 | 0.9998 | 0.9988 | 1.0000 |
| 7 | 1.0005 | 1.0002 | 0.9999 |
| 8 | 0.9999 | 0.9999 | 1.0000 |

**Solution: $x = y = z = 1$** (check: $6 + 2 + 2 = 10$ ✓). This is the slowest of the exam SOR systems (weakest diagonal dominance).

---

## 2026 · Q4(b) (15 marks)
> **Question:** Write a Java program to solve a set of linear equations using the SOR method.

**Lesson:** [[08.02 SOR Java Program]] (complete tested program)

### Expected answer (core)
```java
static double[] sor(double[][] A, double[] b, double omega, double tol, int maxIter) {
    int n = b.length;
    double[] x = new double[n];                          // initial guess 0
    for (int k = 1; k <= maxIter; k++) {
        double maxChange = 0;
        for (int i = 0; i < n; i++) {
            double sum = b[i];
            for (int j = 0; j < n; j++) if (j != i) sum -= A[i][j] * x[j];   // newest values
            double xNew = (1 - omega) * x[i] + omega * sum / A[i][i];        // relaxation
            maxChange = Math.max(maxChange, Math.abs(xNew - x[i]));
            x[i] = xNew;
        }
        if (maxChange < tol) return x;                   // converged
    }
    System.out.println("Maximum number of iterations exceeded");
    return x;
}
```
With a `main` that reads $n$, the augmented rows and $\omega$, checks $0 < \omega < 2$ and non-zero diagonal, and prints $x$.
