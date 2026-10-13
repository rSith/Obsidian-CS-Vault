---
type: revision
course: CMIS 3153
status: complete
tags: [cmis3153, revision, priority]
aliases: [CMIS 3153 Priority Topics]
---
# CMIS 3153 · High Priority Topics (ranked by evidence)

> [!info] Back to [[00. CMIS 3153 Course Overview|Course Overview]] · Evidence: [[CMIS 3153 Past Paper Mapping]]
> Ranking uses **how many papers** asked the topic, **how recently**, and **how many marks**. Since 2022/23 the paper layout has been almost fixed (Q1 disk, Q2 indexing, Q3 B+-tree, Q4 processing + RA, Q5 trees/optimization, Q6 distributed scenario, Q7 mixed), so the recent two papers weigh most.

## 🔴 Tier 1: learn first (asked repeatedly, often word for word)
| # | Topic | Evidence | Learn from | Practise |
|---|---|---|---|---|
| 1 | **Steps in evaluating an SQL string** (diagram) | 2020/21 Q1c, 2022/23 Q4a, 2023/24 Q4a, Q5a | [[03.01 Stages of Query Processing]] | [[PP 03.1 - Steps in Query Processing]] |
| 2 | **Relational algebra queries + interpreting RA** | every paper (20–80 marks) | [[03.04 Writing Relational Algebra Queries]] | [[PP 03.3 - Relational Algebra Queries]], [[TUT 3 - Relational Model and Relational Algebra]], [[TUT 5 - Relational Algebra Practice]] |
| 3 | **Query tree from SQL → heuristic optimization → why better** | 2021/22 Q7c, 2022/23 Q5d, 2023/24 Q5c (≈ 60 marks each) | [[04.05 Query Trees and Query Graphs]], [[04.06 Heuristic Optimization and Transformation Rules]] | [[PP 04.3 - Heuristic Optimization of Query Trees]], [[PP 04.2 - Query Trees and Query Graphs]] |
| 4 | **B+-tree**: properties/balance, insert/delete drawings, utilization | 2022/23 Q3 (100), 2023/24 Q3 (100) | [[02.08 B+ Trees]] | [[PP 02.5 - B+ Tree Structure and Operations]] |
| 5 | **Logical vs physical optimization** | 2022/23 Q4b, 2023/24 Q4b | [[04.03 Logical vs Physical Query Plans]] | [[PP 04.1 - Logical vs Physical, Optimization Steps, CBO vs RBO]] |
| 6 | **Disk access time** (definition + calculation) | all 4 papers | [[01.03 Disk Access Time and Performance]] | [[PP 01.2 - Disk Performance and Access Time]] |
| 7 | **Buffering / double buffering** | 2021/22 Q2c, 2022/23 Q1b, 2023/24 Q1b | [[01.04 Buffering of Blocks]] | [[PP 01.3 - Buffering and Sequential File Placement]] |
| 8 | **Clustered vs non-clustered indexes; index choice** | 2020/21 Q6b-c, 2022/23 Q2b, 2023/24 Q2b | [[02.05 Clustered vs Non-Clustered Indexes and Index Selection]] | [[PP 02.2 - Clustered vs Unclustered Indexes and Index Selection]] |
| 9 | **Index maintenance on insert/update/delete** + **B-tree for a range query** | 2022/23 Q2c, Q7e; 2023/24 Q2c | [[02.09 Index Maintenance, Composite and Covering Indexes]], [[02.07 B-Trees]] | [[PP 02.4 - Index Maintenance and B-Tree Range Queries]] |
| 10 | **CAP theorem** (+ scenario priorities/trade-offs) | 2022/23 Q6b, c(ii); 2023/24 Q6d | [[05.02 CAP Theorem]] | [[PP 05.3 - CAP Theorem and DDB Properties in Scenarios]] |
| 11 | **Fragmentation & replication strategy** for a multi-branch company | 2021/22 Q3d, 2023/24 Q6b, Q7d(i) | [[05.04 Fragmentation, Replication and Allocation]] | [[PP 05.2 - DDB Design, Fragmentation and Replication]] |
| 12 | **Sequential vs indexed file organization** (frequent updates) | 2022/23 Q1d, 2023/24 Q1d | [[01.07 Heap and Sorted Files]] | [[PP 01.5 - File Organizations and File Directories]] |
| 13 | **Query trees vs query graphs** | 2022/23 Q5c, 2023/24 Q5b | [[04.05 Query Trees and Query Graphs]] | [[PP 04.2 - Query Trees and Query Graphs]] |
| 14 | **OIDs vs primary keys** | 2020/21 Q4b, 2021/22 Q4d | [[06.05 Object Identity vs Primary Key]] | [[PP 06.1 - Object Tables, OIDs and Type Inheritance]] |

## 🔴/🟡 Tier 2: likely, worth solid preparation
| Topic | Evidence | Learn from |
|---|---|---|
| Disk performance factors / why queries are slow | 2022/23 Q1a, 2023/24 Q1a | [[01.03 Disk Access Time and Performance]] |
| DDB definition, features, vs centralized, architecture | 2021/22 Q3a, 2022/23 Q6a, 2023/24 Q6a, Q7c | [[05.01 DDB Concepts and Architecture]] |
| Distributed query processing + challenges; transaction management (2PC) | 2023/24 Q6c, Q7d(ii) | [[05.06 Distributed Query Processing and Semijoin]], [[05.07 Distributed Concurrency Control and Recovery]] |
| Cost-based vs rule-based/heuristic optimization | 2021/22 Q7b, 2022/23 Q5b | [[04.04 Query Optimization Strategies]] |
| Role of indexing in optimization | 2022/23 Q5a, 2023/24 Q2a | [[04.07 Indexing in Query Optimization]] |
| Purpose of indexes; primary/clustering/secondary; only one clustering index | 2020/21 Q5a, Q6a; 2021/22 Q1a-b; 2022/23 Q2a | [[02.01 What Is an Index]], [[02.02 Primary Indexes]], [[02.03 Clustering Indexes]], [[02.04 Secondary Indexes]] |
| ORDB key features / bridging OOP and RDBs | 2022/23 Q7a, 2023/24 Q7a | [[06.02 Object-Relational Databases and Their Features]] |
| File calculations (R, bfr, b, linear/binary search) | 2021/22 Q6 (= Tutorial 1 Q2) | [[01.12 Worked File and Disk Calculations]] |

## 🟡 Tier 3: asked once; know the main points
Fixed vs variable records; spanned vs unspanned; block address; disk vs memory; RAID; HDD mechanics; hashing with linear probing; hash pros/cons; multilevel indexes; static vs dynamic tree index; SQL → RA with γ and NOT IN; RA precedence; π over −; physical plan comparison; poor performance remedies; homogeneous vs heterogeneous; DDB design issues; fault tolerance; object tables; UNDER/typed tables; data models compared; UML class vs table; **OLTP vs OLAP**; three-schema architecture ⚠️; NoSQL vs relational ⚠️; file directories ⚠️.

## 🟢 Not yet examined (lower priority, but in lectures)
Dynamic hashing (extendible/linear); SAN vs NAS; file operations list; pipelining (supporting); join algorithms detail; cost models; ORM techniques; data cubes, star/snowflake, OLAP operations; ETL and metadata; warehouse vs views.

## Calculations you must be able to do
1. $s + rd + btt$ from rpm, seek and transfer rate ([[01.03 Disk Access Time and Performance]]).
2. R, bfr, b; linear search (contiguous vs scattered); binary search ([[01.12 Worked File and Disk Calculations]]).
3. Linear probing hash table ([[01.08 Hash Files and Collision Handling]]).
4. B+-tree insert/delete and utilization ([[02.08 B+ Trees]]).
5. Index blocks and levels: $bfr_i$, $b_i$, $\lceil\log_2 b_i\rceil+1$, $t = \lceil\log_{fo} r_1\rceil$ ([[02.06 Multilevel Indexes]]).
6. Selectivity and index vs scan decisions ([[TUT 4 - Selectivity and Index Cost]]).
7. Distributed data-transfer cost comparison ([[05.06 Distributed Query Processing and Semijoin]]).
