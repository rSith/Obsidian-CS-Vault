---
type: past-paper-index
course: CMIS 3153
status: complete
tags: [cmis3153, past-paper, index]
aliases: [CMIS 3153 Past Papers by Year]
---
# CMIS 3153 · Past Papers by Year (question → answer note)

> [!info] Back to [[00. CMIS 3153 Course Overview|Course Overview]] · Lesson view: [[CMIS 3153 Past Paper Mapping]]
> Every paper: **7 questions, attempt any 5, 2½ hours**; each question out of 100 marks.

> [!warning] Date conflict in the 2023/24 paper
> The 2023/24 paper's header reads "**February/March 2025**" (the same as the 2022/23 paper), while the file is named "Feb 2026". The header was most likely reused; the sitting was probably February 2026. Year labels below use the **academic year** printed on each paper.

## AY 2020/2021 (exam June 2023)
| Q | Part | Question | Marks | Answer |
|---|---|---|---|---|
| 1 | a | Is each RA expression (with γ) a correct translation of the GROUP BY/HAVING query? | 40 | [[PP 03.2 - SQL to Relational Algebra]] |
| | b | NOT IN query → RA / expression tree | 30 | [[PP 03.2 - SQL to Relational Algebra]] |
| | c | Steps in evaluating a SQL string, in order | 30 | [[PP 03.1 - Steps in Query Processing]] |
| 2 | a | Logical plan for 4-table join with AVG/GROUP BY | 40 | [[PP 04.2 - Query Trees and Query Graphs]] |
| | b | Two reasons Plan B (hash join, file scans) may be faster than Plan A (index NL) | 60 | [[PP 04.4 - Physical Plans, Indexing and Query Performance]] |
| 3 | a | Fixed vs variable-length records | 25 | [[PP 01.4 - Records and File Calculations]] |
| | b | Spanned vs unspanned | 25 | [[PP 01.4 - Records and File Calculations]] |
| | c | Components of a disk block address | 25 | [[PP 01.1 - Disk Hardware and Memory vs Disk]] |
| | d | Time components of a disk block access | 25 | [[PP 01.2 - Disk Performance and Access Time]] |
| 4 | a | Object tables vs relational tables | 30 | [[PP 06.1 - Object Tables, OIDs and Type Inheritance]] |
| | b | OIDs vs primary keys: two differences | 30 | [[PP 06.1 - Object Tables, OIDs and Type Inheritance]] |
| | c | SQL:2003 UNDER / typed tables: SELECT * results | 40 | [[PP 06.1 - Object Tables, OIDs and Type Inheritance]] |
| 5 | a | Primary vs secondary index with example | 30 | [[PP 02.1 - Index Purpose and Index Types]] |
| | b | Choosing the primary index; hash vs tree indexes | 30 | [[PP 02.2 - Clustered vs Unclustered Indexes and Index Selection]] |
| | c | Linear probing: 12, 18, 13, 2, 3, 23, 5, 15 into table of 10 | 40 | [[PP 01.6 - Hashing]] |
| 6 | a | Main purpose of indexes | 25 | [[PP 02.1 - Index Purpose and Index Types]] |
| | b | Clustered vs unclustered indexes | 30 | [[PP 02.2 - Clustered vs Unclustered Indexes and Index Selection]] |
| | c | Index selection for workloads `b < ?` and `c = ?` | 45 | [[PP 02.2 - Clustered vs Unclustered Indexes and Index Selection]] |
| 7 | a | Reasons for poor query performance and remedies | 20 | [[PP 04.4 - Physical Plans, Indexing and Query Performance]] |
| | b | RA operator precedence and associativity | 25 | [[PP 03.4 - Relational Algebra Theory Questions]] |
| | c | Is $\pi_A(E_1-E_2) = \pi_A(E_1)-\pi_A(E_2)$? | 25 | [[PP 03.4 - Relational Algebra Theory Questions]] |
| | d | RA on lives/works/located-in/manages (i)–(vi) | 6×5 | [[PP 03.3 - Relational Algebra Queries]] |

## AY 2021/2022 (exam January/February 2024)
| Q | Part | Question | Marks | Answer |
|---|---|---|---|---|
| 1 | a | Primary, clustering, secondary indexes; when used | 25 | [[PP 02.1 - Index Purpose and Index Types]] |
| | b | Why ≤ 1 primary/clustering but several secondary | 25 | [[PP 02.1 - Index Purpose and Index Types]] |
| | c | Pros and cons of hash indexes | 25 | [[PP 01.6 - Hashing]] |
| | d | How multilevel indexing improves search | 25 | [[PP 02.3 - Multilevel Indexes and Static vs Dynamic Trees]] |
| 2 | a | Disk vs main memory differences | 25 | [[PP 01.1 - Disk Hardware and Memory vs Disk]] |
| | b | Seek time, rotational delay, transfer time | 25 | [[PP 01.2 - Disk Performance and Access Time]] |
| | c | Storing a frequently sequentially scanned file | 25 | [[PP 01.3 - Buffering and Sequential File Placement]] |
| | d | RAID: objective, techniques, types | 25 | [[PP 01.7 - RAID]] |
| 3 | a | DDB architecture with diagram | 25 | [[PP 05.1 - DDB Concepts and Architecture]] |
| | b | Issues in DDB design | 25 | [[PP 05.2 - DDB Design, Fragmentation and Replication]] |
| | c | Homogeneous vs heterogeneous | 25 | [[PP 05.2 - DDB Design, Fragmentation and Replication]] |
| | d | Fragmentation and replication vs centralized | 25 | [[PP 05.2 - DDB Design, Fragmentation and Replication]] |
| 4 | a | Three-schema architecture, data independence ⚠️ | 25 | [[PP 08.1 - Three-Schema Architecture and NoSQL vs Relational]] |
| | b | Relational vs OO vs OR data models | 30 | [[PP 06.2 - Data Models and ORDB Features]] |
| | c | UML class = entity type? = table? | 20 | [[PP 06.2 - Data Models and ORDB Features]] |
| | d | OIDs vs primary keys | 25 | [[PP 06.1 - Object Tables, OIDs and Type Inheritance]] |
| 5 | a | Could sID be a key of Catalog? | 20 | [[PP 03.3 - Relational Algebra Queries]] |
| | b | RA (σ, π, ×, ⋈) on Suppliers/Parts/Catalog (i)–(v) | 80 | [[PP 03.3 - Relational Algebra Queries]] |
| 6 | a–e | STUDENT file: R, bfr, b, linear search (contiguous / not), binary search | 5×20 | [[PP 01.4 - Records and File Calculations]] |
| 7 | a | Three key steps of query optimisation, diagram | 20 | [[PP 04.1 - Logical vs Physical, Optimization Steps, CBO vs RBO]] |
| | b | Cost-based vs rule-based optimisation | 20 | [[PP 04.1 - Logical vs Physical, Optimization Steps, CBO vs RBO]] |
| | c | Aquarius query tree: SQL, inefficiency, transformations | 60 | [[PP 04.3 - Heuristic Optimization of Query Trees]] |

## AY 2022/2023 (exam February/March 2025)
| Q | Part | Question | Marks | Answer |
|---|---|---|---|---|
| 1 | a | Key factors influencing disk performance, examples | 25 | [[PP 01.2 - Disk Performance and Access Time]] |
| | b | Buffering in disk I/O; significance | 25 | [[PP 01.3 - Buffering and Sequential File Placement]] |
| | c | 7200 rpm, seek 8 ms, latency 4.17 ms → average time | 25 | [[PP 01.2 - Disk Performance and Access Time]] |
| | d | Sequential vs indexed organization (employee DB, frequent updates) | 25 | [[PP 01.5 - File Organizations and File Directories]] |
| 2 | a | What is indexing; purpose, advantages | 25 | [[PP 02.1 - Index Purpose and Index Types]] |
| | b | Clustered vs non-clustered, examples | 25 | [[PP 02.2 - Clustered vs Unclustered Indexes and Index Selection]] |
| | c(i) | Index maintenance: Students insert/delete/update | 25 | [[PP 02.4 - Index Maintenance and B-Tree Range Queries]] |
| | c(ii) | B-tree for GPA queries; structure and operations | 25 | [[PP 02.4 - Index Maintenance and B-Tree Range Queries]] |
| 3 | a–e | B+-tree: insert 90 (nodes read/updated), 42, 79; delete 23, 50 | 5×20 | [[PP 02.5 - B+ Tree Structure and Operations]] |
| 4 | a | Four steps in evaluating a SQL string | 25 | [[PP 03.1 - Steps in Query Processing]] |
| | b | Logical vs physical optimization, examples | 25 | [[PP 04.1 - Logical vs Physical, Optimization Steps, CBO vs RBO]] |
| | c(i) | RA: First Bank Corporation (1–3) | 3×10 | [[PP 03.3 - Relational Algebra Queries]] |
| | c(ii) | Describe/compare two RA expressions | 20 | [[PP 03.3 - Relational Algebra Queries]] |
| 5 | a | Role of indexing in query optimization | 20 | [[PP 04.4 - Physical Plans, Indexing and Query Performance]] |
| | b | Cost-based vs heuristic; fundamental difference | 20 | [[PP 04.1 - Logical vs Physical, Optimization Steps, CBO vs RBO]] |
| | c | Query trees vs query graphs | 20 | [[PP 04.2 - Query Trees and Query Graphs]] |
| | d | employees–departments tree; filter before/after join; order | 40 | [[PP 04.2 - Query Trees and Query Graphs]] |
| 6 | a | DDB concept vs centralized | 25 | [[PP 05.1 - DDB Concepts and Architecture]] |
| | b | CAP theorem | 25 | [[PP 05.3 - CAP Theorem and DDB Properties in Scenarios]] |
| | c(i) | Core DDB properties: logistics company | 25 | [[PP 05.3 - CAP Theorem and DDB Properties in Scenarios]] |
| | c(ii) | CAP priorities: logistics company | 25 | [[PP 05.3 - CAP Theorem and DDB Properties in Scenarios]] |
| 7 (any 4 of 6) | a | Short note: ORDBs bridging OOP and RDBs | 25 | [[PP 06.2 - Data Models and ORDB Features]] |
| | b | Short note: NoSQL vs relational ⚠️ | 25 | [[PP 08.1 - Three-Schema Architecture and NoSQL vs Relational]] |
| | c | Short note: HDD mechanical components | 25 | [[PP 01.1 - Disk Hardware and Memory vs Disk]] |
| | d | Short note: role of file directories ⚠️ | 25 | [[PP 01.5 - File Organizations and File Directories]] |
| | e | Short note: indexing impact on insert/update/delete | 25 | [[PP 02.4 - Index Maintenance and B-Tree Range Queries]] |
| | f | Short note: fault tolerance in DDBs | 25 | [[PP 05.4 - Distributed Query Processing, Transactions and Fault Tolerance]] |

## AY 2023/2024 (header: February/March 2025; file: Feb 2026)
| Q | Part | Question | Marks | Answer |
|---|---|---|---|---|
| 1 | a | Disk factors causing slow queries despite enough CPU/RAM | 25 | [[PP 01.2 - Disk Performance and Access Time]] |
| | b | Role of buffering; CPU–storage interaction | 25 | [[PP 01.3 - Buffering and Sequential File Placement]] |
| | c | 7200 rpm, 9 ms, ½ rotation, 120 MB/s, 64 KB → read time | 25 | [[PP 01.2 - Disk Performance and Access Time]] |
| | d | Sequential vs indexed for frequently updated records | 25 | [[PP 01.5 - File Organizations and File Directories]] |
| 2 | a | Indexing's influence on optimization and performance | 25 | [[PP 04.4 - Physical Plans, Indexing and Query Performance]] |
| | b | Best index for range + exact-match (clustered/non-clustered) | 25 | [[PP 02.2 - Clustered vs Unclustered Indexes and Index Selection]] |
| | c(i) | Index maintenance: Orders insert/delete/update | 25 | [[PP 02.4 - Index Maintenance and B-Tree Range Queries]] |
| | c(ii) | B-tree on Total_Amount; structure, operations, ranges | 25 | [[PP 02.4 - Index Maintenance and B-Tree Range Queries]] |
| 3 | a | B+-tree structure, properties, balance | 25 | [[PP 02.5 - B+ Tree Structure and Operations]] |
| | b | Static vs dynamic tree index | 25 | [[PP 02.3 - Multilevel Indexes and Static vs Dynamic Trees]] |
| | c(i) | B+-tree (d = 2): insert 16 strings, tree every 4 inserts | 40 | [[PP 02.5 - B+ Tree Structure and Operations]] |
| | c(ii) | Utilization of the tree | 10 | [[PP 02.5 - B+ Tree Structure and Operations]] |
| 4 | a | Four steps in evaluating a SQL string | 25 | [[PP 03.1 - Steps in Query Processing]] |
| | b | Logical vs physical optimization, examples | 25 | [[PP 04.1 - Logical vs Physical, Optimization Steps, CBO vs RBO]] |
| | c(i) | RA: Sailors/Boats/Reserves (1–3) | 3×10 | [[PP 03.3 - Relational Algebra Queries]] |
| | c(ii) | Describe/compare two RA expressions | 2×10 | [[PP 03.3 - Relational Algebra Queries]] |
| 5 | a | Query processing stages, techniques, efficiency | 20 | [[PP 03.1 - Steps in Query Processing]] |
| | b | Query graphs vs trees; join ordering | 20 | [[PP 04.2 - Query Trees and Query Graphs]] |
| | c | Employee/Department/Works: initial tree, heuristics, why better | 3×20 | [[PP 04.3 - Heuristic Optimization of Query Trees]] |
| 6 | a | Features of the Colombo/London/Singapore DDB; benefits | 25 | [[PP 05.1 - DDB Concepts and Architecture]] |
| | b | Fragmentation and replication strategy | 25 | [[PP 05.2 - DDB Design, Fragmentation and Replication]] |
| | c | Distributed query processing; challenges | 25 | [[PP 05.4 - Distributed Query Processing, Transactions and Fault Tolerance]] |
| | d | CAP trade-offs | 25 | [[PP 05.3 - CAP Theorem and DDB Properties in Scenarios]] |
| 7 | a | Key features of ORDBs | 25 | [[PP 06.2 - Data Models and ORDB Features]] |
| | b | OLTP definition; OLTP vs OLAP | 25 | [[PP 07.1 - OLTP vs OLAP]] |
| | c | DDB definition, characteristics, architecture; vs centralized | 20 | [[PP 05.1 - DDB Concepts and Architecture]] |
| | d(i) | Distribution strategy: New York/London/Singapore | 15 | [[PP 05.2 - DDB Design, Fragmentation and Replication]] |
| | d(ii) | Query processing and transaction management | 15 | [[PP 05.4 - Distributed Query Processing, Transactions and Fault Tolerance]] |

⚠️ = topic not (fully) covered in the lecture notes: see [[08.00 Supporting Topics Outside the Notes]].
