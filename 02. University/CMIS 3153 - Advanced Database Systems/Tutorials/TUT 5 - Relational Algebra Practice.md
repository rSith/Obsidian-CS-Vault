---
type: tutorial
course: CMIS 3153
lesson: L03
tutorial: T5
priority: high
status: complete
tags: [cmis3153, tutorial, cmis3153/L03, relational-algebra]
aliases: [Tutorial 5, ADB Tutorial 5, T5 Relational algebra practice]
---
# TUT 5 · Relational Algebra Practice (ADB Tutorial 5)

> [!info] Lesson: [[03.00 Query Processing and Relational Algebra]] · Method: [[03.04 Writing Relational Algebra Queries]] · Back to [[CMIS 3153 Tutorials Index|Tutorials index]]
> **Memory rule given in the tutorial:** **Filter → Join → Reduce (project)**.
> **Exam links:** difference ("not reserved a red boat") and self-join ("at least two different boats") patterns appear in **2023/24 Q4(c)**.

Relations:
```text
STUDENT(SID, Name, Dept, Age)      COURSE(CID, Title, Dept)      ENROLL(SID, CID, Grade)
FACULTY(FID, Name, Dept)           TEACHES(FID, CID)
```
> [!warning] Natural-join traps in this schema
> `Dept` appears in STUDENT, COURSE and FACULTY; `Name` appears in STUDENT and FACULTY. A careless natural join would also equate these. Project to the needed columns **before** joining, or use explicit join conditions (done below).

**(i) All student names.**
$$\pi_{Name}(STUDENT)$$

**(ii) Student IDs of students in the "IT" department.**
$$\pi_{SID}(\sigma_{Dept='IT'}(STUDENT))$$

**(iii) Course IDs of courses taught by faculty from the "CSE" department.**
$$\pi_{CID}\big(\pi_{FID}(\sigma_{Dept='CSE'}(FACULTY)) \bowtie TEACHES\big)$$
Filter faculty → join with TEACHES on FID → reduce to CID.

**(iv) Names of students enrolled in at least one course.**
$$\pi_{Name}\big(STUDENT \bowtie \pi_{SID}(ENROLL)\big)$$

**(v) Course titles taken by students from the "ECE" department.**
$$\pi_{Title}\big(\pi_{SID}(\sigma_{Dept='ECE'}(STUDENT)) \bowtie ENROLL \bowtie \pi_{CID,\,Title}(COURSE)\big)$$
(Projecting COURSE first stops its `Dept` from being equated with the student's.)

**(vi) Names of faculty who do not teach any course.**
$$\pi_{Name}\big((\pi_{FID}(FACULTY) - \pi_{FID}(TEACHES)) \bowtie FACULTY\big)$$
"Not" → difference on the ID, then join back to get names (two faculty may share a name, so subtract IDs, not names).

**(vii) Names of students who received grade 'A' in courses offered by the "CSE" department.**
$$\pi_{Name}\big(STUDENT \bowtie \pi_{SID}(\sigma_{Grade='A'}(ENROLL) \bowtie \pi_{CID}(\sigma_{Dept='CSE'}(COURSE)))\big)$$

**(viii) Names of students enrolled in all courses offered by the "IT" department.** ("all" → division)
$$\pi_{Name}\Big(STUDENT \bowtie \big(\pi_{SID,\,CID}(ENROLL) \div \pi_{CID}(\sigma_{Dept='IT'}(COURSE))\big)\Big)$$
Without ÷:
$$
A = \pi_{SID}(ENROLL),\quad C = \pi_{CID}(\sigma_{Dept='IT'}(COURSE)),\quad Missing = \pi_{SID}\big((A \times C) - \pi_{SID,CID}(ENROLL)\big)
$$
$$\text{answer} = \pi_{Name}\big((A - Missing) \bowtie STUDENT\big)$$

**(ix) Names of students enrolled in at least two different courses.** (self-join)
$$\pi_{Name}\Big(STUDENT \bowtie \pi_{E1.SID}\big(\sigma_{E1.SID = E2.SID \wedge E1.CID \ne E2.CID}(\rho_{E1}(ENROLL) \times \rho_{E2}(ENROLL))\big)\Big)$$
Pairs of enrolments by the **same** student in **different** courses.

**(x) Student names who have never received grade 'F'.** ("never" → difference)
$$\pi_{Name}\big((\pi_{SID}(STUDENT) - \pi_{SID}(\sigma_{Grade='F'}(ENROLL))) \bowtie STUDENT\big)$$
(This includes students with no enrolments at all. If only enrolled students are meant, start from $\pi_{SID}(ENROLL)$.)

**(xi) Course titles not taken by any student.**
$$\pi_{Title}\big((\pi_{CID}(COURSE) - \pi_{CID}(ENROLL)) \bowtie COURSE\big)$$

**(xii) Names of faculty who teach only CSE department courses.**
Faculty who teach at least one course, minus those who teach some non-CSE course:
$$
T = \pi_{FID}(TEACHES),\qquad NonCSE = \pi_{FID}\big(TEACHES \bowtie \pi_{CID}(\sigma_{Dept \ne 'CSE'}(COURSE))\big)
$$
$$\text{answer} = \pi_{Name}\big((T - NonCSE) \bowtie FACULTY\big)$$

**(xiii) Student names enrolled in every course taught by faculty from the ECE department.** (division)
$$
C_{ECE} = \pi_{CID}\big(\pi_{FID}(\sigma_{Dept='ECE'}(FACULTY)) \bowtie TEACHES\big)
$$
$$\text{answer} = \pi_{Name}\Big(STUDENT \bowtie \big(\pi_{SID,\,CID}(ENROLL) \div C_{ECE}\big)\Big)$$

## Pattern summary
| Wording | Operator |
|---|---|
| "at least one" | join (or projection of the linking table) |
| "not / never / no" | difference |
| "all / every" | division |
| "at least two different" | self-join with ρ and ≠ |
| "only" | (those who do) − (those who do something else) |
