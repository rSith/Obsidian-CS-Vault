---
type: tutorial
course: CMIS 3153
lesson: L03
tutorial: T3
priority: high
status: complete
tags: [cmis3153, tutorial, cmis3153/L03, relational-algebra]
aliases: [Tutorial 3, ADB Tutorial 3, T3 Relational algebra]
---
# TUT 3 · Relational Model and Relational Algebra (ADB Tutorial 3)

> [!info] Lesson: [[03.00 Query Processing and Relational Algebra]] · Method: [[03.04 Writing Relational Algebra Queries]] · Back to [[CMIS 3153 Tutorials Index|Tutorials index]]
> **Exam links:** Q4 (a)–(f) is **identical to 2020/21 Q7(d)(i)–(vi)** and nearly identical to **2022/23 Q4(c)(i)** (employee/works/company/manages, First Bank Corporation). Q1–Q3 support 2021/22 Q5(a).

---

## Q1. What is a relation, and what two properties must its elements have?
A **relation** is a table: a **set of tuples** over a set of attributes, each value taken from the attribute's **domain**.
Two properties of its elements (tuples):
1. **Tuples are distinct**: a relation is a **set**, so there are no duplicate tuples (every relation has a key).
2. **Attribute values are atomic** (single, indivisible values; first normal form).
(Also: the order of tuples, and of attributes, has no significance.)

## Q2. Relation vs relation schema
| Relation schema | Relation (instance) |
|---|---|
| the **definition/structure**: relation name, attributes with domains, keys, e.g. `N1({A, B}, {A})` | the **set of tuples** stored for that schema at a given time |
| changes rarely (design) | changes with every insert/delete/update |
| like a table's column headings | like the table's current rows |

## Q3. Express $N_1[B] \subseteq N_2[B]$ with projection
$r_1$ over $N_1(\{A, B\}, \{A\})$, $r_2$ over $N_2(\{B, C\}, \{B\})$. The constraint says every B-value of $r_1$ must appear as a B-value in $r_2$ (B in $N_1$ is a **foreign key** to $N_2$, whose key is B):
$$
\pi_B(r_1) \subseteq \pi_B(r_2) \qquad\Longleftrightarrow\qquad \pi_B(r_1) - \pi_B(r_2) = \emptyset
$$

## Q4. RA queries on Lives / Works / Located-in / Manages
Schema: `Lives(pname, street, city)`, `Works(pname, cname, salary)`, `Located-in(cname, city)`, `Manages(pname, mname)`.

> [!tip] Method
> Filter → Join → Reduce ([[03.04 Writing Relational Algebra Queries]]). Natural joins match **same-named** attributes; watch out for `city`, which appears in both Lives and Located-in.

**(a) Names of all employees who work for City Bank.**
$$\pi_{pname}\big(\sigma_{cname = 'City\ Bank'}(Works)\big)$$
Filter Works to City Bank rows, keep the names.

**(b) Name and city of all employees who work for City Bank.**
$$\pi_{pname,\ city}\big(\sigma_{cname = 'City\ Bank'}(Works) \bowtie Lives\big)$$
The natural join matches on `pname` (the hint: "join condition is the same person name in Lives and Works"); `city` comes from Lives.

**(c) Name, street and city of all employees who work for City Bank and earn more than \$10,000.**
$$\pi_{pname,\ street,\ city}\big(\sigma_{cname = 'City\ Bank' \wedge salary > 10000}(Works) \bowtie Lives\big)$$

**(d) All employees who live in the same city as the company they work for.**
$$\pi_{pname}\big(Lives \bowtie Works \bowtie Located\text{-}in\big)$$
*Why it works:* Lives ⋈ Works matches on `pname`; joining Located-in then matches on **both** `cname` **and** `city`, so only rows where the person's city = the company's city survive. With explicit conditions:
$$\pi_{Lives.pname}\big(\sigma_{Lives.city = Located\text{-}in.city}(Lives \bowtie_{Lives.pname = Works.pname} Works \bowtie_{Works.cname = Located\text{-}in.cname} Located\text{-}in)\big)$$

**(e) All persons who do not work for City Bank.** (hint: set difference)
$$\pi_{pname}(Lives) - \pi_{pname}\big(\sigma_{cname = 'City\ Bank'}(Works)\big)$$
All persons (taken from Lives, which lists every person) minus those who work for City Bank. Alternative if "persons" means employees in Works: $\pi_{pname}(Works) - \pi_{pname}(\sigma_{cname='City\ Bank'}(Works))$. Note that $\pi_{pname}(\sigma_{cname \ne 'City\ Bank'}(Works))$ is **not** the same: it would include someone who works for City Bank **and** another company.

**(f) All employees who live in the same city and on the same street as their manager.** (hint: rename; Lives used twice)
$$
\pi_{pname}\Big(Lives \bowtie Manages \bowtie \rho_{M(mname,\ street,\ city)}(Lives)\Big)
$$
*How it works:* $\rho_M$ renames the second copy of Lives so its person column is called `mname`. Lives ⋈ Manages matches the employee's `pname`; joining M then matches on `mname` (the manager), **and** on `street` and `city` (same names), so only employees living at their manager's street and city remain.
Explicit version:
$$\pi_{E.pname}\big(\sigma_{E.street = M.street \wedge E.city = M.city}(\rho_E(Lives) \bowtie_{E.pname = Manages.pname} Manages \bowtie_{Manages.mname = M.pname} \rho_M(Lives))\big)$$

---

## Exam variant: 2022/23 Q4(c) (employee/works/company/manages)
`employee(person-name, street, city)` replaces Lives; `company(company-name, city)` replaces Located-in; "First Bank Corporation" replaces City Bank; salary "per annum". The answers are the same patterns: see [[PP 03.3 - Relational Algebra Queries]].
