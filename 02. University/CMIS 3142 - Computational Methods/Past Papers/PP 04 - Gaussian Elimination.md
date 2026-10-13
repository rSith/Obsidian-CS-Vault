---
type: past-paper
course: CMIS 3142
lesson: L04
status: complete
tags: [cmis3142, past-paper, cmis3142/L04]
---
# PP 04 · Gaussian Elimination: Past-Paper Questions

> [!info] Lesson: [[04.00 Gaussian Elimination]] · Summary: [[04.99 Summary - Gaussian Elimination]] · [[CMIS 3142 Past Paper Mapping]]
> Examined in **every** paper. Priority 🔴. The contractor problem appeared **twice** (2023, 2024).

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2023 | 6(a) | Describe Gaussian elimination | 8 |
| 2023 | 6(b)(i) | Contractor scenario → system of equations | 8 |
| 2023 | 6(b)(ii) | Solve by Gaussian elimination | 9 |
| 2024 | 5(a) | Develop an algorithm to solve a system of linear equations | – |
| 2024 | 6(a) | Describe Gaussian elimination | – |
| 2024 | 6(b)(i)(ii) | Same contractor scenario → equations → GE | – |
| 2025 | 6(a) | Describe Gaussian elimination | – |
| 2025 | 6(b) | Program in any language for Gaussian elimination | – |
| 2026 | 5(a) | Program in any language for Gaussian elimination | 15 |
| 2026 | 5(b) | Solve a 4×4 system by Gaussian elimination | 20 |

Related formulation question: 2025 Q4(b)(i) fruit prices → see [[PP 05 - Jacobi and Gauss-Seidel]].

---

## 2023 · Q6(a) (8 marks) · 2024 · Q6(a) · 2025 · Q6(a)
> **Question:** Describe the Gaussian elimination method to solve a given system of linear equations.

**Lesson:** [[04.02 Forward Elimination and Back Substitution]] §7 (model answer)

### Expected answer
1. **Purpose:** a direct method to solve $Ax = b$ ($n$ equations, $n$ unknowns, $A$ non-singular).
2. **Augmented matrix** $[A \mid b]$.
3. **Forward elimination:** for each pivot $k = 1, \dots, n-1$ and each row $i > k$: multiplier $m_{ik} = a_{ik}/a_{kk}$, row operation $R_i \leftarrow R_i - m_{ik}R_k$ (applied to $b$ too). After all steps the matrix is **upper triangular**.
4. **Back substitution:** $x_n = b_n/a_{nn}$; $x_i = \big(b_i - \sum_{j=i+1}^{n}a_{ij}x_j\big)/a_{ii}$ for $i = n-1, \dots, 1$.
5. **Pivoting:** if a pivot is 0 (or tiny), swap with a lower row having the largest $|a_{ik}|$ to avoid division by zero and reduce round-off.
6. **Example** (lecture): $-3x_1 + 2x_2 - x_3 = -1$, $6x_1 - 6x_2 + 7x_3 = -7$, $3x_1 - 4x_2 + 4x_3 = -6$. $R_2 + 2R_1$, $R_3 + R_1$, then $R_3 - R_2$ → $\left[\begin{smallmatrix}-3&2&-1&|&-1\\0&-2&5&|&-9\\0&0&-2&|&2\end{smallmatrix}\right]$ → $x_3 = -1$, $x_2 = 2$, $x_1 = 2$.
7. Cost ≈ $\frac23n^3$ operations.

### How to approach this question
Formulas + one small example; 8 marks expects more than a sentence on each phase.

---

## 2023 · Q6(b) (8 + 9 marks) · 2024 · Q6(b)
> **Question (2023 wording):** A contractor categorizes the workers his employees as follows:
>
> | No | Group | Gender | Age range |
> |---|---|---|---|
> | 1 | Teen | Male and female | 13 – 19 |
> | 2 | Man | Male | 20 – 55 |
> | 3 | Woman | Female | 20 – 50 |
> | 4 | Old | Male | Greater than 55 |
>
> The contractor's Workers Grouping Quotation to complete a particular job is as follows: 8 teens, 2 men, 3 women, and 2 old will cost 189 \$ per day. 3 teens, 6 men, 2 women, and 1 old will cost 192 \$ per day. 1 teen, 2 men, 9 women, and 4 old will cost 233 \$ per day. 2 teens, 3 men, 4 women, and 7 old will cost 224 \$ per day. Workers' daily wages per category is not mentioned in the quotation.
> i. Convert the above real world scenario into a system of linear equations to find the daily wages of each category. ii. Solve the problem using the Gaussian elimination method.
>
> *(2024 Q6(b) is the same scenario and numbers, worded "Payment for each category per day was not mentioned"; (i) "find the payment of each category per day".)*

**Lesson:** [[04.01 Systems of Linear Equations and Augmented Matrix]] · [[04.02 Forward Elimination and Back Substitution]]

### Expected answer
**(i) Formulation.** Let $t, m, w, o$ = daily wage (\$) of one teen, man, woman, old worker.
$$
\begin{aligned}
8t + 2m + 3w + 2o &= 189\\
3t + 6m + 2w + o &= 192\\
t + 2m + 9w + 4o &= 233\\
2t + 3m + 4w + 7o &= 224
\end{aligned}
\qquad
\left[\begin{array}{cccc|c}8&2&3&2&189\\3&6&2&1&192\\1&2&9&4&233\\2&3&4&7&224\end{array}\right]
$$

**(ii) Gaussian elimination** (fractions keep it exact):

*Step 1, pivot 8:* $R_2 \leftarrow R_2 - \frac38R_1$, $R_3 \leftarrow R_3 - \frac18R_1$, $R_4 \leftarrow R_4 - \frac14R_1$
$$
\left[\begin{array}{cccc|c}8&2&3&2&189\\0&\frac{21}{4}&\frac78&\frac14&\frac{969}{8}\\0&\frac74&\frac{69}{8}&\frac{15}{4}&\frac{1675}{8}\\0&\frac52&\frac{13}{4}&\frac{13}{2}&\frac{707}{4}\end{array}\right]
\quad(\text{decimals: } 5.25,\ 0.875,\ 0.25,\ 121.125;\ 1.75,\ 8.625,\ 3.75,\ 209.375;\ 2.5,\ 3.25,\ 6.5,\ 176.75)
$$

*Step 2, pivot $\frac{21}{4}$:* $R_3 \leftarrow R_3 - \frac13R_2$, $R_4 \leftarrow R_4 - \frac{10}{21}R_2$
$$
\left[\begin{array}{cccc|c}8&2&3&2&189\\0&\frac{21}{4}&\frac78&\frac14&\frac{969}{8}\\0&0&\frac{25}{3}&\frac{11}{3}&169\\0&0&\frac{17}{6}&\frac{134}{21}&\frac{1667}{14}\end{array}\right]
\quad(8.3333,\ 3.6667,\ 169;\ 2.8333,\ 6.3810,\ 119.0714)
$$

*Step 3, pivot $\frac{25}{3}$:* $R_4 \leftarrow R_4 - \frac{17}{50}R_3$
$$
\left[\begin{array}{cccc|c}8&2&3&2&189\\0&\frac{21}{4}&\frac78&\frac14&\frac{969}{8}\\0&0&\frac{25}{3}&\frac{11}{3}&169\\0&0&0&\frac{1797}{350}&\frac{10782}{175}\end{array}\right]
\quad(5.1343,\ 61.6114)
$$

*Back substitution:*
- $o = \dfrac{10782/175}{1797/350} = \dfrac{21564}{1797} = 12$
- $w = \dfrac{169 - \frac{11}{3}(12)}{25/3} = \dfrac{(169 - 44)\cdot3}{25} = 15$
- $m = \dfrac{\frac{969}{8} - \frac78(15) - \frac14(12)}{21/4} = \dfrac{121.125 - 13.125 - 3}{5.25} = \dfrac{105}{5.25} = 20$
- $t = \dfrac{189 - 2(20) - 3(15) - 2(12)}{8} = \dfrac{80}{8} = 10$

**Daily wages: teen \$10, man \$20, woman \$15, old \$12.**
Check equation 3: $10 + 40 + 135 + 48 = 233$ ✓.

### Key points to include
- Define the unknowns with units; augmented matrix; every row operation written; back substitution; check.

### How to approach this question
Order the unknowns as in the table. In 2024 (no calculator), the fraction route above is the safe one.

---

## 2024 · Q5(a)
> **Question:** Develop an algorithm to solve a given system of linear equation.

**Lesson:** [[04.03 Gaussian Elimination Algorithm and Program]]

### Expected answer
Gaussian elimination with $b$ stored as column $n+1$ (lecture algorithm, plus a pivot check):
```text
INPUT n, augmented matrix a(1..n, 1..n+1)
Forward elimination:
  for k = 1 to n−1
      (pivoting) find p ≥ k with largest |a(p,k)|; swap rows p and k
      if a(k,k) = 0 then OUTPUT "no unique solution"; STOP
      for i = k+1 to n
          m = a(i,k) / a(k,k)
          for j = k to n+1
              a(i,j) = a(i,j) − m * a(k,j)
Back substitution:
  for i = n down to 1
      s = a(i,n+1)
      for j = i+1 to n:  s = s − a(i,j) * x(j)
      x(i) = s / a(i,i)
OUTPUT x(1..n)
```
(Any correct algorithm, such as Jacobi, Gauss-Seidel or Cholesky with stopping/condition checks, is also acceptable; GE is the most general for a square non-singular system.)

---

## 2025 · Q6(b) · 2026 · Q5(a) (15 marks)
> **2025:** Write program in any programming language to solve a system of linear equations using Gaussian elimination.
> **2026:** Write a program in any programming language to solve a set of linear equations using the Gaussian elimination method.

**Lesson:** [[04.03 Gaussian Elimination Algorithm and Program]] (lecture C program + tested Java program)

### Expected answer
```java
import java.util.Scanner;

public class GaussianElimination {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[][] a = new double[n][n + 1];             // augmented matrix, column n = b
        for (int i = 0; i < n; i++)
            for (int j = 0; j <= n; j++) a[i][j] = sc.nextDouble();

        for (int k = 0; k < n - 1; k++) {                // forward elimination
            int max = k;                                  // partial pivoting
            for (int i = k + 1; i < n; i++) if (Math.abs(a[i][k]) > Math.abs(a[max][k])) max = i;
            double[] t = a[k]; a[k] = a[max]; a[max] = t;
            if (a[k][k] == 0) { System.out.println("No unique solution"); return; }
            for (int i = k + 1; i < n; i++) {
                double m = a[i][k] / a[k][k];
                for (int j = k; j <= n; j++) a[i][j] -= m * a[k][j];
            }
        }
        double[] x = new double[n];                       // back substitution
        for (int i = n - 1; i >= 0; i--) {
            double s = a[i][n];
            for (int j = i + 1; j < n; j++) s -= a[i][j] * x[j];
            x[i] = s / a[i][i];
        }
        for (int i = 0; i < n; i++) System.out.printf("x%d = %.4f%n", i + 1, x[i]);
    }
}
```
Tested on the contractor system → 10, 20, 15, 12.

### Key points to include
Input of the augmented matrix; three nested loops; back substitution; zero-pivot handling; output. The lecture's C program (no pivoting) is also a full-mark answer if reproduced correctly.

---

## 2026 · Q5(b) (20 marks)
> **Question:** Solve the set of following equations using the Gaussian Elimination Method: i) x + 2y + z + w = 8 ii) 2x + 3y + 2z + w = 12 iii) x + y + 3z + 2w = 10 iv) 3x + 4y + z + 2w = 16

**Lesson:** [[04.02 Forward Elimination and Back Substitution]]

### Expected answer
$$
\left[\begin{array}{cccc|c}1&2&1&1&8\\2&3&2&1&12\\1&1&3&2&10\\3&4&1&2&16\end{array}\right]
$$
*Step 1 (pivot 1):* $R_2 - 2R_1$, $R_3 - R_1$, $R_4 - 3R_1$:
$$
\left[\begin{array}{cccc|c}1&2&1&1&8\\0&-1&0&-1&-4\\0&-1&2&1&2\\0&-2&-2&-1&-8\end{array}\right]
$$
*Step 2 (pivot −1):* $R_3 - R_2$, $R_4 - 2R_2$:
$$
\left[\begin{array}{cccc|c}1&2&1&1&8\\0&-1&0&-1&-4\\0&0&2&2&6\\0&0&-2&1&0\end{array}\right]
$$
*Step 3 (pivot 2):* $R_4 + R_3$:
$$
\left[\begin{array}{cccc|c}1&2&1&1&8\\0&-1&0&-1&-4\\0&0&2&2&6\\0&0&0&3&6\end{array}\right]
$$
*Back substitution:* $3w = 6 \Rightarrow w = 2$; $2z + 2w = 6 \Rightarrow z = 1$; $-y - w = -4 \Rightarrow y = 2$; $x + 2y + z + w = 8 \Rightarrow x = 8 - 4 - 1 - 2 = 1$.

$$
\boxed{x = 1,\ y = 2,\ z = 1,\ w = 2}
$$
Check (iv): $3 + 8 + 1 + 4 = 16$ ✓.

### How to approach this question
20 marks: write the augmented matrix, each step with its row operations and the full matrix after the step, back substitution, check.
