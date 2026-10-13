---
type: past-paper-index
course: CMIS 3153
status: complete
tags: [cmis3153, past-paper, mapping]
aliases: [CMIS 3153 Past Paper Map, CMIS 3153 Question Mapping]
---
# CMIS 3153 · Past Paper Mapping (lesson → question)

> [!info] Back to [[00. CMIS 3153 Course Overview|Course Overview]] · Question by question: [[CMIS 3153 Papers by Year]]
> Sources: four papers: AY **2020/21** (June 2023), AY **2021/22** (Jan/Feb 2024), AY **2022/23** (Feb/Mar 2025), AY **2023/24** (header Feb/Mar 2025, file named Feb 2026). All: **7 questions, answer 5, 2½ hours**. Based on the project's *CMIS3153 Topic and Past Paper Analysis*, re-checked against the scanned papers.

## 1. Lesson → question tree
```text
L01 Data Storage and File Organization ............. 4/4 papers 🔴 (Q1 since 2022/23)
    ├── 2020/21 Q3(a) fixed vs variable records; Q3(b) spanned vs unspanned
    ├── 2020/21 Q3(c) block address; Q3(d) time components of block access
    ├── 2020/21 Q5(c) linear probing hash table
    ├── 2021/22 Q1(c) hash index pros/cons
    ├── 2021/22 Q2(a) disk vs memory; (b) seek/rotational/transfer; (c) sequential file placement; (d) RAID
    ├── 2021/22 Q6(a)–(e) STUDENT file calculations   (= Tutorial 1 Q2)
    ├── 2022/23 Q1(a) disk performance factors; (b) buffering; (c) 8 + 4.17 ms; (d) sequential vs indexed
    ├── 2022/23 Q7(c) HDD mechanics; Q7(d) file directories ⚠️
    └── 2023/24 Q1(a) slow queries from disk; (b) buffering; (c) 9 + 4.17 + 0.55 ms; (d) sequential vs indexed

L02 Indexing Structures ............................. 4/4 papers 🔴 (Q2 + Q3 since 2022/23)
    ├── 2020/21 Q5(a) primary vs secondary; Q5(b) primary-index choice, hash vs tree
    ├── 2020/21 Q6(a) purpose; (b) clustered vs unclustered; (c) workload index selection
    ├── 2021/22 Q1(a) primary/clustering/secondary; (b) only one clustering; (d) multilevel
    ├── 2022/23 Q2(a) indexing; (b) clustered vs non-clustered; (c)(i) maintenance; (c)(ii) B-tree GPA
    ├── 2022/23 Q3(a)–(e) B+-tree insert 90/42/79, delete 23/50
    ├── 2022/23 Q7(e) indexing impact on insert/update/delete
    ├── 2023/24 Q2(b) range + exact-match; (c)(i) maintenance; (c)(ii) B-tree Total_Amount
    └── 2023/24 Q3(a) B+-tree properties; (b) static vs dynamic; (c) string inserts + utilization

L03 Query Processing & Relational Algebra ........... 4/4 papers 🔴
    ├── 2020/21 Q1(a) γ translation; (b) NOT IN → RA; (c) evaluation steps
    ├── 2020/21 Q7(b) precedence ⚠️; (c) π over −; (d) RA lives/works   (= Tutorial 3 Q4)
    ├── 2021/22 Q5(a) key of Catalog; (b) RA Suppliers/Parts/Catalog
    ├── 2022/23 Q4(a) four steps; (c) RA First Bank Corporation + interpretation
    └── 2023/24 Q4(a) four steps; (c) RA Sailors + interpretation; Q5(a) processing stages/techniques

L04 Query Optimization .............................. 4/4 papers 🔴
    ├── 2020/21 Q2(a) logical plan; (b) Plan A vs Plan B; Q7(a) poor performance
    ├── 2021/22 Q7(a) three steps; (b) CBO vs RBO; (c) Aquarius tree   (= lecture Example 2)
    ├── 2022/23 Q4(b) logical vs physical; Q5(a) indexing role; (b) CBO vs heuristic; (c) trees vs graphs; (d) salary tree (= lecture Exercise 2)
    └── 2023/24 Q2(a) indexing influence; Q4(b) logical vs physical; Q5(b) graphs vs trees; Q5(c) Colombo tree

L05 Distributed Database Systems .................... 3/4 papers 🔴 (rising)
    ├── 2021/22 Q3(a) architecture ⚠️; (b) design issues; (c) homogeneous vs heterogeneous; (d) fragmentation & replication
    ├── 2022/23 Q6(a) DDB vs centralized; (b) CAP; (c)(i) properties (logistics); (c)(ii) CAP priorities; Q7(f) fault tolerance
    └── 2023/24 Q6(a) features & benefits; (b) fragmentation/replication; (c) distributed query processing; (d) CAP trade-offs;
                Q7(c) DDB characteristics/architecture; Q7(d)(i) distribution strategy; (d)(ii) query + transaction management

L06 Object-Relational Databases ..................... 4/4 papers 🔴 (one question or part)
    ├── 2020/21 Q4(a) object vs relational tables; (b) OID vs PK; (c) UNDER / SELECT * ⚠️
    ├── 2021/22 Q4(b) relational vs OO vs OR; (c) UML class vs entity type/table; (d) OID vs PK
    ├── 2022/23 Q7(a) ORDBs bridging OOP and RDBs
    └── 2023/24 Q7(a) key features of ORDBs

L07 Data Warehousing and OLAP ....................... 1/4 papers 🟡
    └── 2023/24 Q7(b) OLTP vs OLAP

L08 Outside the notes ⚠️
    ├── 2021/22 Q4(a) three-schema architecture
    └── 2022/23 Q7(b) NoSQL vs relational
```

## 2. Frequency and weight
Counting each lettered/roman sub-part once (a list of RA queries counts as one), under its main lesson:

| Lesson | 2020/21 | 2021/22 | 2022/23 | 2023/24 | Total | Papers | Priority |
|---|---|---|---|---|---|---|---|
| L03 + L04 Query processing / optimization / RA | 10 | 7 | 10 | 10 | **37** | 4/4 | 🔴 |
| L02 Indexing (incl. B/B+-trees) | 6 | 3 | 10 | 8 | **27** | 4/4 | 🔴 |
| L01 Storage and file organization (incl. hashing) | 5 | 10 | 6 | 4 | **25** | 4/4 | 🔴 |
| L05 Distributed DBs | 0 | 4 | 6 | 7 | **17** | 3/4 | 🔴 (rising) |
| L06 ORDBMS | 3 | 3 | 1 | 1 | **8** | 4/4 | 🔴 by frequency, lower weight |
| L07 Data warehousing | 0 | 0 | 0 | 1 | **1** | 1/4 | 🟡 |
(Counts from the analysis file; gap topics are counted under the lesson of the question they appeared in.)

## 3. Recurring structure (since 2022/23)
| Q | Topic |
|---|---|
| Q1 | disk performance, buffering, access-time calculation, sequential vs indexed |
| Q2 | indexing, clustered vs non-clustered, maintenance cost, B-tree for a range query |
| Q3 | B+-tree: properties, static vs dynamic, draw insertions/deletions, utilization |
| Q4 | query evaluation steps, logical vs physical optimization, RA queries + interpretation |
| Q5 | query trees/graphs, heuristic optimization of a given SQL query |
| Q6 | distributed DB scenario: features, fragmentation/replication, query processing, CAP |
| Q7 | short notes / mixed: ORDBMS, DW (OLTP/OLAP), DDB, NoSQL, HDD, indexing, fault tolerance |

## 4. Repeated questions (word for word or nearly)
| Question | Papers |
|---|---|
| Steps in evaluating a SQL query submitted as a string | 2020/21 Q1(c), 2022/23 Q4(a), 2023/24 Q4(a) |
| Logical vs physical query optimization with examples | 2022/23 Q4(b), 2023/24 Q4(b) |
| OIDs vs primary keys | 2020/21 Q4(b), 2021/22 Q4(d) |
| Query trees vs query graphs | 2022/23 Q5(c), 2023/24 Q5(b) |
| Buffering in disk I/O | 2022/23 Q1(b), 2023/24 Q1(b) |
| Disk performance factors / slow queries | 2022/23 Q1(a), 2023/24 Q1(a) |
| Access-time calculation at 7200 rpm | 2022/23 Q1(c), 2023/24 Q1(c) |
| Sequential vs indexed organization, frequent updates | 2022/23 Q1(d), 2023/24 Q1(d) |
| Index maintenance on insert/update/delete | 2022/23 Q2(c)(i), Q7(e), 2023/24 Q2(c)(i) |
| B-tree for a range query on one column | 2022/23 Q2(c)(ii), 2023/24 Q2(c)(ii) |
| CAP theorem | 2022/23 Q6(b)(c)(ii), 2023/24 Q6(d) |
| Clustered vs non-clustered | 2020/21 Q6(b), 2022/23 Q2(b), 2023/24 Q2(b) |
| DDB vs centralized | 2022/23 Q6(a), 2023/24 Q7(c) |
| Fragmentation/replication strategy | 2021/22 Q3(d), 2023/24 Q6(b), Q7(d)(i) |

## 5. Tutorials and lecture examples that became exam questions
| Source | Exam question |
|---|---|
| [[TUT 1 - Disk and File Calculations\|Tutorial 1 Q2]] | **2021/22 Q6** (identical) |
| [[TUT 1 - Disk and File Calculations\|Tutorial 1 Q1(e)(f)]] | method of 2022/23 Q1(c), 2023/24 Q1(c) |
| [[TUT 3 - Relational Model and Relational Algebra\|Tutorial 3 Q4]] | **2020/21 Q7(d)** (identical); ≈ 2022/23 Q4(c)(i) |
| [[TUT 5 - Relational Algebra Practice\|Tutorial 5]] (difference, "at least two") | 2023/24 Q4(c) |
| [[TUT 4 - Selectivity and Index Cost\|Tutorial 4]] Ex 1, 3, 5 | 2020/21 Q5(b)(ii), Q6(c); 2022/23 Q2(b), Q5(a); 2023/24 Q2(a)(b) |
| [[TUT 2 - Records, File Organization and B-Trees\|Tutorial 2]] Q1/Q7, Q12, Q14–15 | 2020/21 Q3(a); 2022/23 Q1(d); 2023/24 Q1(d), Q3(a) |
| Lecture L4 Exercise 2 | **2022/23 Q5(d)** (same SQL) |
| Lecture L4 Example 2 (Aquarius) | **2021/22 Q7(c)** |
| Lecture L4 essay Q10 (trees vs graphs) | 2022/23 Q5(c) (same wording) |

## 6. Lesson question notes
[[PP 01 - Data Storage and File Organization]] · [[PP 02 - Indexing Structures]] · [[PP 03 - Query Processing and Relational Algebra]] · [[PP 04 - Query Optimization]] · [[PP 05 - Distributed Database Systems]] · [[PP 06 - Object-Relational Databases]] · [[PP 07 - Data Warehousing and OLAP]] · [[PP 08.1 - Three-Schema Architecture and NoSQL vs Relational]]
