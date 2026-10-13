---
type: tutorial-index
course: CMIS 3153
status: complete
tags: [cmis3153, tutorial, index]
aliases: [CMIS 3153 Tutorials, ADB Tutorials]
---
# CMIS 3153 · Tutorials Index (Tutorial question → topic)

> [!info] Back to [[00. CMIS 3153 Course Overview|Course Overview]] · Every tutorial question is answered in full in its tutorial note.

| Tutorial | Questions | Lesson / topic note | Repeated in exams? |
|---|---|---|---|
| [[TUT 1 - Disk and File Calculations]] | Q1 (a)–(d) track/cylinder/pack capacity | [[01.02 Magnetic Disk Structure]] | |
| | Q1 (e)–(g) tr, btt, rd, btr, access time, random vs consecutive | [[01.03 Disk Access Time and Performance]] | method of 2022/23 Q1(c), 2023/24 Q1(c) |
| | Q2 (a)–(e) STUDENT file R, bfr, b, linear and binary search | [[01.12 Worked File and Disk Calculations]] | **identical to 2021/22 Q6** |
| | Q3–Q4 (a)–(f) EMPLOYEE file, wasted space, tr, btr, searches | [[01.12 Worked File and Disk Calculations]] | |
| [[TUT 2 - Records, File Organization and B-Trees]] | Q1/Q7, Q2/Q8, Q9 variable-length records | [[01.05 Records, Blocking and File Allocation]] | 2020/21 Q3(a) |
| | Q3, Q4 tables per file; record allocation | [[01.05 Records, Blocking and File Allocation]] | |
| | Q5 logical vs physical files | [[01.06 File Operations and Access Methods]] | |
| | Q6 file size vs organization; Q11 organization vs access method | [[01.06 File Operations and Access Methods]] | |
| | Q10 file headers | [[01.05 Records, Blocking and File Allocation]] | 2022/23 Q7(d) |
| | Q12 problems of sorted files | [[01.07 Heap and Sorted Files]] | 2022/23 Q1(d), 2023/24 Q1(d) |
| | Q13 B-tree node, order p | [[02.07 B-Trees]] | |
| | Q14 B+-tree nodes, order p; Q15 why B+ over B | [[02.08 B+ Trees]] | 2023/24 Q3(a) |
| [[TUT 3 - Relational Model and Relational Algebra]] | Q1–Q2 relation, schema | [[03.05 Relational Algebra, SQL and Database Design]] | |
| | Q3 referential integrity with π | [[03.05 Relational Algebra, SQL and Database Design]] | 2021/22 Q5(a) (keys) |
| | Q4 (a)–(f) Lives/Works/Located-in/Manages | [[03.04 Writing Relational Algebra Queries]] | **identical to 2020/21 Q7(d)**; ≈ 2022/23 Q4(c)(i) |
| [[TUT 4 - Selectivity and Index Cost]] | Ex 1–2, Exercises 2, 5 selectivity, bitmap, B-tree I/O | [[04.07 Indexing in Query Optimization]] | 2022/23 Q5(a), 2023/24 Q2(a) |
| | Ex 3, Exercises 3, 6 clustered vs non-clustered ranges | [[02.05 Clustered vs Non-Clustered Indexes and Index Selection]] | 2020/21 Q6(c), 2023/24 Q2(b) |
| | Ex 4, Exercise 7 composite index; Ex 7, Exercise 10 covering index; Exercise 1 partial index | [[02.09 Index Maintenance, Composite and Covering Indexes]] | |
| | Ex 5, Exercises 4, 8 hash index | [[01.08 Hash Files and Collision Handling]] | 2021/22 Q1(c), 2020/21 Q5(b)(ii) |
| | Ex 6, Exercise 9 index size and height | [[02.06 Multilevel Indexes]] | |
| [[TUT 5 - Relational Algebra Practice]] | (i)–(xiii) STUDENT/COURSE/ENROLL/FACULTY/TEACHES | [[03.04 Writing Relational Algebra Queries]] | patterns in 2023/24 Q4(c) |

## Lecture exercises (no separate tutorial)
| Lecture | Exercises | Where answered |
|---|---|---|
| L2 Indexing | Lab Ex 1–6 (CREATE INDEX, composite, EXPLAIN, DROP INDEX) | [[02.09 Index Maintenance, Composite and Covering Indexes]] |
| L3 Query Processing | Ex 1 (IT employees > 30), Ex 2 (nulls, aggregates, division), Ex 3 (multiset RA), Ex 4 (comparisons), Ex 5 (normalization) | [[03.04 Writing Relational Algebra Queries]], [[03.03 Set Operations and Joins]], [[03.05 Relational Algebra, SQL and Database Design]] |
| L4 Query Optimization | Practice Q1–Q4, Essays Q1–Q12 | [[04.05 Query Trees and Query Graphs]], [[04.06 Heuristic Optimization and Transformation Rules]], [[04.07 Indexing in Query Optimization]], [[04.02 Query Cost and Cost Models]] |
| L5 Distributed DBs | Ex 1–5 | [[05.02 CAP Theorem]], [[05.07 Distributed Concurrency Control and Recovery]], [[05.01 DDB Concepts and Architecture]], [[08.02 NoSQL vs Relational Databases]] |
| ORDBMS | Discussion Qs (slide 50) | [[06.05 Object Identity vs Primary Key]], [[06.03 Object-Relational Mapping]], [[06.01 Relational vs Object-Oriented Databases]] |
