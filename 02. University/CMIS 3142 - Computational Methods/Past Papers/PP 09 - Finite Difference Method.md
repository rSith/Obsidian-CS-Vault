---
type: past-paper
course: CMIS 3142
lesson: L09
status: complete
tags: [cmis3142, past-paper, cmis3142/L09]
---
# PP 09 · Finite Difference Method: Past-Paper Questions

> [!info] Lesson: [[09.00 Finite Difference Method]] · Summary: [[09.99 Summary - Finite Difference Method]] · [[CMIS 3142 Past Paper Mapping]]
> Examined in **3 of 4** papers (2023, 2024, 2026). Priority 🔴. Two questions are **identical to the lecture examples** (2023 Q2(c) = Example 5.2; 2024 Q2(c) = Example 5.4).

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2023 | 2(a) | Derive $\partial^2f/\partial x\partial y$ from Taylor series (central, forward, backward) | 9 |
| 2023 | 2(b) | Comment on the truncation error in (a) | 3 |
| 2023 | 2(c) | Forward difference table, $y = x^3 + 2x + 1$, $x = 1..5$ | 6 |
| 2023 | 2(d) | Algorithm for the table, $x = 1..25$ | 7 |
| 2024 | 2(c) | $\Delta e^{ax}$, $\Delta^2e^x$, $\Delta\log x$ | – |
| 2024 | 2(d) | $\Delta^2\frac1x$, interval 1 | – |
| 2026 | 3(a) | Forward, backward, central differences via Taylor series | 10 |
| 2026 | 3(b) | Finite difference equation for compound interest | 5 |
| 2026 | 3(c) | FD table: principal 5000, 7.5%, 10 years | 10 |
| 2026 | 3(d) | Java program: FD table to 2nd difference for any principal, rate, duration | 25 |

---

## 2023 · Q2(a) (9 marks)
> **Question:** Derive $\frac{\partial^2f}{\partial x\partial y}$ from Taylor series using central difference, forward difference and backward difference where f is a function of x and y.

**Lesson:** [[09.03 Second and Mixed Derivatives]] §2 (full derivation)

### Expected answer
Let $h = \Delta x$, $k = \Delta y$, derivatives at $(x_i, y_j)$:
$$
f(x+h, y+k) = f + hf_x + kf_y + \tfrac12(h^2f_{xx} + 2hkf_{xy} + k^2f_{yy}) + \tfrac16(h^3f_{xxx} + 3h^2kf_{xxy} + 3hk^2f_{xyy} + k^3f_{yyy}) + \dots
$$
**Central:** write this for $(\pm h, \pm k)$ and form $f_{i+1,j+1} - f_{i-1,j+1} - f_{i+1,j-1} + f_{i-1,j-1}$. Terms even in $h$ or even in $k$ cancel, leaving $4hk\,f_{xy}$ plus fourth-order terms:
$$
\left(\frac{\partial^2f}{\partial x\partial y}\right)_{i,j} = \frac{f_{i+1,j+1} - f_{i-1,j+1} - f_{i+1,j-1} + f_{i-1,j-1}}{4\Delta x\Delta y} + O[(\Delta x)^2, (\Delta y)^2]
$$
**Forward:** with $f_{i,j+1} = f + kf_y + \frac12k^2f_{yy} + \dots$ and $f_{i+1,j} = f + hf_x + \frac12h^2f_{xx} + \dots$:
$f_{i+1,j+1} - f_{i,j+1} - f_{i+1,j} + f_{i,j} = hk\,f_{xy} + \frac12h^2kf_{xxy} + \frac12hk^2f_{xyy} + \dots$
$$
\left(\frac{\partial^2f}{\partial x\partial y}\right)_{i,j} = \frac{f_{i+1,j+1} - f_{i,j+1} - f_{i+1,j} + f_{i,j}}{\Delta x\Delta y} + O(\Delta x, \Delta y)
$$
**Backward:** replacing $h \to -h$, $k \to -k$:
$$
\left(\frac{\partial^2f}{\partial x\partial y}\right)_{i,j} = \frac{f_{i,j} - f_{i-1,j} - f_{i,j-1} + f_{i-1,j-1}}{\Delta x\Delta y} + O(\Delta x, \Delta y)
$$

### How to approach this question
3 marks per scheme: expansion(s), cancellation, final formula with error term.

## 2023 · Q2(b) (3 marks)
> **Question:** Comment on the truncation error in the equations in part (a).

### Expected answer
The truncation error is the sum of the neglected higher-order Taylor terms. **Central:** the third-order terms cancel by symmetry, so the error is $O[(\Delta x)^2, (\Delta y)^2]$: **second order** (halving both steps reduces the error about 4 times). **Forward and backward:** leading error $\pm\frac12(\Delta x f_{xxy} + \Delta y f_{xyy})$, i.e. $O(\Delta x, \Delta y)$: **first order** (halving steps only halves the error). All tend to zero as $\Delta x, \Delta y \to 0$; central is more accurate but needs points on all sides; one-sided formulas are used at boundaries. Very small steps increase round-off error.

---

## 2023 · Q2(c) (6 marks)
> **Question:** Construct a forward difference table for $y = f(x) = x^3 + 2x + 1$ for $x = 1,2,3,4,5$.

**Lesson:** [[09.04 Forward Difference Tables]] (= Example 5.2)

### Expected answer
$y$: $f(1) = 4$, $f(2) = 13$, $f(3) = 34$, $f(4) = 73$, $f(5) = 136$.

| $x$ | $y$ | $\Delta y$ | $\Delta^2y$ | $\Delta^3y$ | $\Delta^4y$ |
|---|---|---|---|---|---|
| 1 | 4 | | | | |
| | | 9 | | | |
| 2 | 13 | | 12 | | |
| | | 21 | | 6 | |
| 3 | 34 | | 18 | | 0 |
| | | 39 | | 6 | |
| 4 | 73 | | 24 | | |
| | | 63 | | | |
| 5 | 136 | | | | |

Third differences are constant (6) and the fourth difference is 0, as expected for a cubic polynomial.

## 2023 · Q2(d) (7 marks)
> **Question:** Write an algorithm to construct the above table for $x = 1, 2, \dots, 25$.

### Expected answer
```text
INPUT n = 25
1  For i = 1 to n:            D[i][0] = i^3 + 2*i + 1          // y values
2  For k = 1 to n−1:                                            // k-th difference column
       For i = 1 to n−k:      D[i][k] = D[i+1][k−1] − D[i][k−1]
       (optional) if column k is all zero, set maxK = k and stop
3  Print header  x, y, Δy, Δ²y, …
4  For i = 1 to n:
       print i, D[i][0], D[i][1], ..., D[i][min(maxK, n−i)]
STOP
```
Since $f$ is a cubic, only columns up to $\Delta^4$ (all zeros) are needed. Java version: [[09.04 Forward Difference Tables]] §4.

---

## 2024 · Q2(c)
> **Question:** Find i) $\Delta e^{ax}$ ii) $\Delta^2e^x$ iii) $\Delta\log(x)$

**Lesson:** [[09.05 Difference Operator Delta]] (= Example 5.4)

### Expected answer
With step $h$, $\Delta f(x) = f(x+h) - f(x)$:
1. $\Delta e^{ax} = e^{a(x+h)} - e^{ax} = e^{ax}e^{ah} - e^{ax} = e^{ax}(e^{ah} - 1)$
2. $\Delta^2e^x = \Delta[e^{x+h} - e^x] = \Delta[e^x(e^h - 1)] = (e^h - 1)\Delta e^x = (e^h - 1)^2e^x$
3. $\Delta\log x = \log(x+h) - \log x = \log\dfrac{x+h}{x} = \log\left(1 + \dfrac hx\right)$

(The handout's line for (i) has a typo, $e^{a(x+h)} - e^x$; the correct result is $e^{ax}(e^{ah} - 1)$.)

## 2024 · Q2(d)
> **Question:** Evaluate $\Delta^2\frac1x$ by taking '1' as the interval of differencing.

### Expected answer
$$
\Delta\frac1x = \frac1{x+1} - \frac1x = \frac{-1}{x(x+1)}
$$
$$
\Delta^2\frac1x = \frac{-1}{(x+1)(x+2)} + \frac{1}{x(x+1)} = \frac{-x + (x+2)}{x(x+1)(x+2)} = \frac{2}{x(x+1)(x+2)}
$$
Check at $x = 1$: $f(3) - 2f(2) + f(1) = \frac13 - 1 + 1 = \frac13 = \frac{2}{6}$ ✓.

---

## 2026 · Q3(a) (10 marks)
> **Question:** Define the forward difference, backward difference and central difference using the Taylor series expansion of a continuous differentiable function.

**Lesson:** [[09.02 First Derivative Differences and Truncation Error]] §2–3

### Expected answer
Taylor series about $x_i$, step $\Delta x$:
$$
f(x_{i+1}) = f(x_i) + \Delta x f'(x_i) + \frac{\Delta x^2}{2!}f''(x_i) + \frac{\Delta x^3}{3!}f'''(x_i) + \dots\quad(a)
$$
$$
f(x_{i-1}) = f(x_i) - \Delta x f'(x_i) + \frac{\Delta x^2}{2!}f''(x_i) - \frac{\Delta x^3}{3!}f'''(x_i) + \dots\quad(b)
$$
- **Forward** (from (a), neglecting $\Delta x^2$ and higher): $f'(x_i) = \dfrac{f_{i+1} - f_i}{\Delta x} + O(\Delta x)$
- **Backward** (from (b)): $f'(x_i) = \dfrac{f_i - f_{i-1}}{\Delta x} + O(\Delta x)$
- **Central** ((a) − (b); the $f''$ terms cancel): $f'(x_i) = \dfrac{f_{i+1} - f_{i-1}}{2\Delta x} + O(\Delta x^2)$

Forward/backward are first-order accurate, central second-order. The neglected terms form the **truncation error**. (Unequal spacing: divide by $x_{i+1} - x_i$, $x_i - x_{i-1}$, $x_{i+1} - x_{i-1}$.)

---

## 2026 · Q3(b) (5 marks)
> **Question:** Derive a finite difference equation to model compound interest. (Compound interest is the interest calculated on the original principal as well as on the interest that has been added from previous periods. In other words, it is "interest on interest")

**Lesson:** [[09.04 Forward Difference Tables]] §5

### Expected answer
Let $A_n$ = amount after $n$ periods (years), $P$ = principal, $r$ = interest rate per period. Interest for period $n+1$ is charged on the **whole current amount** $A_n$ (principal + earlier interest):
$$
A_{n+1} = A_n + rA_n \quad\Longrightarrow\quad \boxed{\Delta A_n = A_{n+1} - A_n = rA_n},\qquad A_0 = P
$$
Equivalently $A_{n+1} = (1 + r)A_n$, whose solution is $A_n = P(1+r)^n$. Also $\Delta^2A_n = r\,\Delta A_n = r^2P(1+r)^n$. (It is the forward-difference version, step 1, of $dA/dt = rA$.)

## 2026 · Q3(c) (10 marks)
> **Question:** Form the finite difference table with principal value 5000, and the interest rate 7.5% for 10 years.

### Expected answer
$A_{n+1} = 1.075A_n$, $A_0 = 5000$:

| Year $n$ | $A_n$ | $\Delta A_n$ | $\Delta^2A_n$ |
|---|---|---|---|
| 0 | 5000.00 | 375.00 | 28.13 |
| 1 | 5375.00 | 403.13 | 30.23 |
| 2 | 5778.13 | 433.36 | 32.50 |
| 3 | 6211.48 | 465.86 | 34.94 |
| 4 | 6677.35 | 500.80 | 37.56 |
| 5 | 7178.15 | 538.36 | 40.38 |
| 6 | 7716.51 | 578.74 | 43.41 |
| 7 | 8295.25 | 622.14 | 46.66 |
| 8 | 8917.39 | 668.80 | 50.16 |
| 9 | 9586.19 | 718.96 | |
| 10 | 10305.16 | | |

Checks: $\Delta A_0 = 0.075\times5000 = 375$; $\Delta^2A_0 = 0.075\times375 = 28.125$; $A_{10} = 5000\times1.075^{10} = 10305.16$. Each column is geometric (ratio 1.075); the differences never become constant, unlike a polynomial.

## 2026 · Q3(d) (25 marks)
> **Question:** Write a Java program to display the Finite difference table till the second derivative for any values for principal value, interest rate and the duration.

**Lesson:** [[09.04 Forward Difference Tables]] §5.3 (tested program)

### Expected answer
```java
import java.util.Scanner;

public class CompoundInterestTable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Principal: ");               double P = sc.nextDouble();
        System.out.print("Annual interest rate (%): "); double r = sc.nextDouble() / 100.0;
        System.out.print("Duration (years): ");         int N = sc.nextInt();

        double[] A = new double[N + 1];                 // A(n+1) = (1 + r) A(n)
        A[0] = P;
        for (int n = 0; n < N; n++) A[n + 1] = (1 + r) * A[n];

        double[] d1 = new double[N];                    // first differences
        double[] d2 = new double[N - 1];                // second differences
        for (int n = 0; n < N; n++)     d1[n] = A[n + 1] - A[n];
        for (int n = 0; n < N - 1; n++) d2[n] = d1[n + 1] - d1[n];

        System.out.printf("%5s %14s %12s %12s%n", "Year", "Amount A", "Delta A", "Delta^2 A");
        for (int n = 0; n <= N; n++) {
            System.out.printf("%5d %14.2f", n, A[n]);
            if (n < N)     System.out.printf(" %12.2f", d1[n]);
            if (n < N - 1) System.out.printf(" %12.2f", d2[n]);
            System.out.println();
        }
    }
}
```

### Key points to include
Input of all three parameters; the difference equation in the loop; first and second difference arrays; formatted table; correct handling of the shorter difference columns. (Input validation, e.g. duration ≥ 2, is a bonus.)
