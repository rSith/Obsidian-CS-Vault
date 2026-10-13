---
type: past-paper
course: CMIS 3142
lesson: L07
status: complete
tags: [cmis3142, past-paper, cmis3142/L07]
---
# PP 07 · Fixed-Point Iteration: Past-Paper Questions

> [!info] Lesson: [[07.00 Non-linear Equations - Fixed Point Iteration]] · Summary: [[07.99 Summary - Fixed Point Iteration]] · [[CMIS 3142 Past Paper Mapping]]
> Examined **once** (2025). Priority 🟡. Newton–Raphson has never been asked.

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2025 | 6(c) | Solve $f(x) = 4x^3 - 3x^2 - 5x - 10$ by fixed-point iteration | – |

---

## 2025 · Q6(c)
> **Question:** Find the solution to f(x) = 4x³ − 3x² − 5x − 10 using the fixed iteration method.

**Lesson:** [[07.01 Fixed-Point Iteration]] §5

### Expected answer
**1. Locate the root.** $f(1) = 4 - 3 - 5 - 10 = -14$, $f(2) = 32 - 12 - 10 - 10 = 0$, $f(3) = 108 - 27 - 15 - 10 = 56$. The sign changes between 1 and 3 and $f(2) = 0$, so the real root is at (or near) $x = 2$.

**2. Rewrite as $x = g(x)$ with $|g'(x)| < 1$ near the root.**
$$
4x^3 = 3x^2 + 5x + 10 \;\Rightarrow\; x = g(x) = \left(\frac{3x^2 + 5x + 10}{4}\right)^{1/3}
$$
$$
g'(x) = \frac{6x + 5}{12}\left(\frac{3x^2 + 5x + 10}{4}\right)^{-2/3},\qquad g'(2) = \frac{17}{12}\cdot 6^{-2/3} = \frac{17}{12}\cdot\frac{1}{3.3019} \approx 0.354 < 1
$$
On $[1.5, 2.5]$, $|g'(x)| \le 0.36 < 1$, so by the convergence theorem ($|g'(x)| \le K < 1$ on an interval containing the root) the iteration converges for any starting value in that interval.

(Not suitable: $x = \frac{4x^3 - 3x^2 - 10}{5}$, since $g'(x) = \frac{12x^2 - 6x}{5}$, $|g'(2)| = 7.2 > 1$ → diverges.)

**3. Iterate** $x_{n+1} = \sqrt[3]{(3x_n^2 + 5x_n + 10)/4}$ with $x_0 = 1.5$:

| $n$ | $x_n$ | | $n$ | $x_n$ |
|---|---|---|---|---|
| 0 | 1.500000 | | 6 | 1.999016 |
| 1 | 1.823408 | | 7 | 1.999652 |
| 2 | 1.937472 | | 8 | 1.999877 |
| 3 | 1.977855 | | 9 | 1.999956 |
| 4 | 1.992157 | | 10 | 1.999985 |
| 5 | 1.997222 | | | |

e.g. $x_1 = \sqrt[3]{(3(2.25) + 7.5 + 10)/4} = \sqrt[3]{6.0625} = 1.823408$.

**Solution: $x \approx 2.0000$** (exact root $x = 2$; check $f(2) = 0$). The other two roots of the cubic are complex ($-0.625 \pm 0.927i$), so $x = 2$ is the only real solution.

### Key points to include
- Root location (sign change).
- The rearrangement $x = g(x)$ **and** the convergence justification $|g'(x)| < 1$.
- Iteration table to a stated tolerance (e.g. stop when $|x_{n+1} - x_n| < 10^{-4}$).

### How to approach this question
Markers look for a sensible $g$ with justification; a $g$ that diverges with no comment loses most marks. If your first rearrangement diverges, say so, then switch.

> [!note] Faster alternative (not asked)
> Newton–Raphson from $x_0 = 1.5$: 2.3269, 2.0549, 2.0019, 2.0000 (see [[07.02 Newton-Raphson Method]]).
