---
type: past-paper
course: CMIS 3142
lesson: L02
status: complete
tags: [cmis3142, past-paper, cmis3142/L02]
---
# PP 02 · Error Analysis: Past-Paper Questions

> [!info] Lesson: [[02.00 Error Analysis]] · Summary: [[02.99 Summary - Error Analysis]] · [[CMIS 3142 Past Paper Mapping]]
> Examined in **every** paper; the GPA/marks rounding scenario appears every year. Priority 🔴.

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2023 | 3(a) | Forward and backward error with examples | 5 |
| 2023 | 3(b) | Impact of rounding error on module grades and final GPA | 8 |
| 2023 | 3(c) | Method to minimise the loss | 4 |
| 2023 | 3(d) | Java methods equivalent to `Math.floor`, `Math.ceil` | 8 |
| 2024 | 2(a) | Relative error and true fractional relative error | – |
| 2024 | 2(b) | Max and min rounding error in final GPA (+ assumptions) and minimisation | – |
| 2025 | 2(a) | Impacts of rounding error in GPA (with examples) | – |
| 2025 | 2(b) | Impacts of chopping error in GPA (with examples) | – |
| 2025 | 2(c) | Java: read modules, GPA, round-off and chopping errors, their difference | – |
| 2026 | 5(c) | Impact of rounding: scaling 500 → 100, rounding marks for grading, GPA | 9 |
| 2026 | 5(d) | Solutions to the shortcomings | 6 |

> [!warning] Assumed grading scales
> The papers give grade points only in 2025 (A+ 4.0, A 4.0, B 3.0, B− 2.7, C 2.0, D 1.0). Any grade boundary (e.g. "A ≥ 75") or class threshold (e.g. "First Class ≥ 3.70") below is an **illustrative assumption**. This information could not be verified from the available course materials. State such values as assumptions in your answer.

---

## 2023 · Q3(a) (5 marks)
> **Question:** Describe forward error and backward error with suitable examples.

**Lesson:** [[02.05 Well-Posed Problems, Forward and Backward Error]]

### Expected answer
We compute $y = f(x)$ but obtain an approximation $\hat y$.
- **Forward error:** the difference between the computed and true outputs: absolute $|\hat y - y|$, relative $|\hat y - y|/|y|$. Natural to measure but usually only an upper bound is known, since $y$ is unknown.
- **Backward error:** the smallest change $\Delta x$ in the input such that $\hat y$ is the **exact** answer for the perturbed input, $\hat y = f(x + \Delta x)$: absolute $|\Delta x|$, relative $|\Delta x|/|x|$. "For what data have we actually solved the problem?"
- **Example (lecture):** $y = \sqrt2 = 1.41421\ldots$, computed $\hat y = 1.4$. Forward error $= |1.4 - 1.41421| \approx 0.0142$. Since $\sqrt{1.96} = 1.4$, the backward error is $|2 - 1.96| = 0.04$.
- They are linked by conditioning: relative forward error ≈ condition number × relative backward error ($0.01 \approx 0.5\times0.02$ here).

### Key points to include
Both definitions with formulas, the √2 example, the output-space vs input-space contrast.

---

## 2023 · Q3(b) (8 marks)
> **Question:** Describe the impact of the rounding off error in the calculation of grades (result) to each course module and final GPA in the faculty of applied sciences.

**Lesson:** [[02.04 Rounding Errors in GPA Calculation]] · [[02.02 Round-off and Chopping Errors]]

### Expected answer
**Round-off error** = difference between the exact value and the rounded value; at most half a unit in the last kept place.
1. **Module marks:** component marks (CA, final paper, scaled papers) are often rounded before being combined. Each rounding adds up to ±0.5 mark, and **errors accumulate** when several rounded parts are added (e.g. 4 components → up to ±2 marks).
2. **Grades at boundaries:** grades come from mark ranges. A rounding difference of under 1 mark near a boundary changes the **grade** (assumed A ≥ 75: 74.5 → 75 = A, but 74.4 → 74 = lower grade), so a tiny numerical error becomes a whole grade-point step (e.g. 0.3 or more).
3. **GPA:** $\text{GPA} = \sum(\text{credits}\times\text{grade point})/\sum\text{credits}$. Rounding the final GPA to 2 d.p. changes it by up to ±0.005; rounding semester GPAs first and then combining them **compounds** the error.
4. **Class/award boundaries:** a GPA of 3.695 rounds to 3.70 and may cross an (assumed) First Class boundary, while 3.694 does not; rounding decides the class for borderline students.
5. **Fairness/consistency:** different rounding points or rules (half-up vs half-even vs chopping) give different results for the same student; chopping always disadvantages students.
6. Example: 2025 data, exact GPA $32.7/13 = 2.5153846$; rounded 2.52 (+0.0046), chopped 2.51 (−0.0054).

### Key points to include
Definition, effect at each stage (marks → grade → GPA → class), accumulation, boundary effect, an example.

### How to approach this question
8 marks: about 4 impacts, each with a short numerical example.

---

## 2023 · Q3(c) (4 marks)
> **Question:** Propose a suitable method to minimize the loss in the calculation of grades of each course module and final GPA to students in the Faculty of Applied Sciences.

**Lesson:** [[02.04 Rounding Errors in GPA Calculation]] §5

### Expected answer
- **Keep full precision** (double) for all intermediate values (component marks, weighted totals, semester GPAs) and **round only once at the end** (final mark → grade; final GPA → 2 d.p.).
- Use **rounding (round half up), never chopping**, with a published, consistent rule.
- Scale the **total** once (e.g. total × 100/500) rather than scaling and rounding each part.
- Use **integer arithmetic** where possible (grade points × 10, credits as integers) so sums are exact.
- **Review borderline cases** (within 0.5 mark of a grade boundary or 0.005 of a class boundary).

---

## 2023 · Q3(d) (8 marks)
> **Question:** Consider the following built-in methods in Java: i. `Math.floor(a)` rounds off a specified double value downward and returns it (i.e. `Math.floor(3.30)` returns 3.0) ii. `Math.ceil(a)` rounds off a specified double value upward and returns it (i.e. `Math.ceil(3.30)` returns 4.00). Write separate Java methods that serve the same purpose as each of the above two built-in methods.

**Lesson:** [[02.02 Round-off and Chopping Errors]] §5

### Expected answer
```java
static double myFloor(double a) {
    long t = (long) a;        // cast chops toward zero
    if (a < t) t = t - 1;     // negative non-integer: chopping went up, step down
    return (double) t;
}

static double myCeil(double a) {
    long t = (long) a;        // cast chops toward zero
    if (a > t) t = t + 1;     // positive non-integer: chopping went down, step up
    return (double) t;
}
```
Tests: `myFloor(3.30) = 3.0`, `myFloor(-3.30) = -4.0`, `myCeil(3.30) = 4.0`, `myCeil(-3.30) = -3.0`, `myFloor(5.0) = myCeil(5.0) = 5.0`.

### Key points to include
- The cast is **chopping toward zero**, so negative numbers need the correction in floor and positive in ceil.
- Whole numbers must be returned unchanged.

### How to approach this question
4 marks each: code + a short explanation + test values including a negative one.

---

## 2024 · Q2(a)
> **Question:** Describe the relative error and true fractional relative error.

**Lesson:** [[02.01 Accuracy, Precision and Error Measures]]

### Expected answer
- **True error:** $E_t = \text{true value} - \text{approximation}$.
- **True fractional relative error:** the true error divided by the true value, $\dfrac{\text{true} - \text{approx}}{\text{true}}$ (dimensionless).
- **(True percent) relative error:** the same expressed as a percentage, $\varepsilon_t = \dfrac{\text{true} - \text{approx}}{\text{true}}\times100\%$.
- Why relative: it measures error **relative to the size** of the quantity. Example: an error of 1 cm in 10 000 cm (bridge) is 0.01%, but in 10 cm (rivet) is 10%.
- When the true value is unknown, iterative methods use the **approximate relative error** $\varepsilon_a = \frac{x_{new} - x_{old}}{x_{new}}\times100\%$.
- Example: $\pi \approx 3.14$: $E_t = 0.0015927$, fractional relative error $= 0.000507$, $\varepsilon_t = 0.0507\%$.

---

## 2024 · Q2(b)
> **Question:** Describe the maximum and minimum rounding off error, with example, in the calculation of the final GPA (Mention the assumptions, if any). Propose a method to minimize the above error.

**Lesson:** [[02.04 Rounding Errors in GPA Calculation]] §4–5

### Expected answer
**Assumptions:** final GPA reported to 2 decimal places, rounded half up; grade points fixed per grade (e.g. A = 4.0, B = 3.0); intermediate values kept exact unless stated; 120 credits (40 modules × 3 credits) for the degree.

- **Minimum rounding error = 0**: when the exact GPA already has at most 2 decimals, e.g. $\text{GPA} = 390/120 = 3.25$ → no error.
- **Maximum rounding error = 0.005** (half of the last unit 0.01): e.g. exact 3.695 → 3.70 (error 0.005); exact $2.5153846$ → 2.52 (error 0.0046).
- If **intermediate results are rounded** (each semester GPA to 2 d.p., then combined), the errors **accumulate**: each rounding adds up to 0.005, so the final error can exceed 0.005 (worst case all in the same direction).
- If **module marks are rounded before grading**, the worst case is a whole grade step per module near a boundary: with 120 credits, one module's grade point change $\Delta g$ changes the GPA by $3\Delta g/120 = \Delta g/40$; e.g. +0.3 in every module → GPA +0.3.
- (Chopping instead: max error approaches 0.01, always downward.)

**Minimise:** keep full precision and round **only once** at the end; round (not chop); do not round intermediate values; integer arithmetic for credits × grade points; consistent published rule; manual review of borderline cases.

### How to approach this question
State assumptions → min (0, with example) → max (0.005, with example) → accumulation → method.

---

## 2025 · Q2 stem
> **Question (stem):** Suppose a student has completed one course unit with 2-credits, three course units each of 3-credits and two course units each of 1-credit with grades, A, C, B, D, B− and A+ respectively. The calculation of GPA is given below:
> $$\text{GPA} = \frac{(2*4.0)+(3*2.0)+(3*3.0)+(3*1.0)+(1*2.7)+(1*4.0)}{2+3+3+3+1+1} = \frac{32.7}{13} = 2.5153846 = 2.52$$
> GPA is considered to be rounded up to two decimal points. Assume that a student should complete forty course units each of 3 credits to complete a Degree programme.

## 2025 · Q2(a)
> **Question:** Describe, with suitable examples, the impacts of rounding off error in the GPA calculation.

**Lesson:** [[02.04 Rounding Errors in GPA Calculation]]

### Expected answer
- In the example, rounding 2.5153846 → **2.52** gives round-off error $2.52 - 2.5153846 = 0.0046$ (in the student's favour here); the maximum possible is ±0.005 per rounding.
- Rounding can go **either way** (e.g. 2.5149 → 2.51 loses 0.0049), so errors partly cancel over many students; but at **class boundaries** it decides the award (3.695 → 3.70).
- For the full degree (40 units × 3 credits = 120 credits), each module's contribution is $3g/120$. If module marks or semester GPAs are **rounded before combining**, up to 40 rounding errors can **accumulate**, and a borderline mark rounded up/down can change a grade (e.g. B− 2.7 ↔ B 3.0, changing the GPA by $3(0.3)/120 = 0.0075$ per module).
- Rounding the final GPA once keeps the error within ±0.005.

## 2025 · Q2(b)
> **Question:** Describe, with suitable examples, the impacts of chopping error in the GPA calculation.

### Expected answer
- Chopping simply drops the digits: 2.5153846 → **2.51**, chopping error $2.5153846 - 2.51 = 0.0054$ (larger than the round-off error 0.0046 here).
- Chopping error is **always in the same direction** (GPA never increases), up to almost 0.01 per chop; e.g. 3.6999 → 3.69 (error 0.0099). It **systematically disadvantages** every student.
- When intermediate values are chopped (marks, semester GPAs), the errors **add up** instead of cancelling: with many chopped values the total loss grows.
- At boundaries: an exact 3.6999 would be First Class (assumed ≥ 3.70) with rounding but not with chopping.

## 2025 · Q2(c)
> **Question:** Write a Java programme to perform the following tasks: i. Read the module codes and credit values. ii. Calculate the GPA value. iii. Calculate the round off error and chopping error while calculating the GPA. iv. Display the difference between the round off error and chopping error.

**Lesson:** [[02.04 Rounding Errors in GPA Calculation]] §6 (complete tested program)

### Expected answer
Key parts (assumption: grade points are also read, since GPA needs them):
```java
double weighted = 0; int totalCredits = 0;
for (int i = 0; i < n; i++) { weighted += credit[i] * gp[i]; totalCredits += credit[i]; }
double gpa     = weighted / totalCredits;                 // exact
double rounded = Math.round(gpa * 100) / 100.0;           // round to 2 d.p.
double chopped = Math.floor(gpa * 100) / 100.0;           // chop to 2 d.p.
double roundOffError = Math.abs(gpa - rounded);
double choppingError = Math.abs(gpa - chopped);
System.out.printf("Difference = %.7f%n", choppingError - roundOffError);
```
With the 2025 data: exact 2.5153846, rounded 2.52 (error 0.0046154), chopped 2.51 (error 0.0053846), difference 0.0007692.

---

## 2026 · Q5(c) (9 marks)
> **Question:** Describe the impact of rounding off in the following tasks of undergraduate exams: i) Giving 500 marks for the paper and then scale it to 100 ii) Rounding the marks for grading iii) Calculating the GPA

**Lesson:** [[02.04 Rounding Errors in GPA Calculation]] §3

### Expected answer
**(i) Scaling 500 → 100:** scaled mark $= \text{raw}/5$, so each raw mark becomes a multiple of 0.2 (e.g. 372 → 74.4, 373 → 74.6). Rounding the scaled mark to an integer merges about 5 raw marks into one value (74.4 → 74, 74.6 → 75): maximum error **±0.5 scaled mark (±2.5 raw marks)**. If each question/section is scaled and rounded separately and then added, the errors **accumulate** (5 sections → up to ±2.5 marks). Chopping would lose up to 1 mark every time, always downward.

**(ii) Rounding marks for grading:** grades use boundaries. Rounding near a boundary changes the **grade** itself (assumed A ≥ 75: 74.5 → 75 → A, while 74.4 → 74 → lower grade), so a 0.1-mark difference becomes a grade-point step (e.g. 0.3–1.0). Different rounding rules give different grades for the same performance.

**(iii) Calculating the GPA:** $\text{GPA} = \sum c_ig_i/\sum c_i$. Rounding the final GPA to 2 d.p. → error ≤ 0.005; rounding semester/year GPAs first → errors compound; rounding near class boundaries (e.g. 3.695 → 3.70) changes the class. Earlier rounding errors in marks/grades propagate into the GPA.

### How to approach this question
3 marks per part: mechanism + numerical example + consequence.

## 2026 · Q5(d) (6 marks)
> **Question:** Give a suitable solution if you find any shortcomings in the above calculations.

### Expected answer
1. Scale the **total** raw mark once, keep decimals: $\text{mark} = \text{raw}\times100/500$ in `double`; do not round each section.
2. Keep **full precision** through every stage; **round once**, only at the point of reporting (final mark/grade; final GPA).
3. Use **rounding (half up)**, not chopping; publish one rule for everyone.
4. Use exact arithmetic where possible (integers: raw marks, credits × grade points × 10).
5. Compute GPA from **unrounded** semester totals ($\sum c_ig_i$ and $\sum c_i$ over all modules), not from rounded semester GPAs.
6. **Borderline review**: flag students within 0.5 mark of a grade boundary or 0.005 of a class boundary for moderation.
