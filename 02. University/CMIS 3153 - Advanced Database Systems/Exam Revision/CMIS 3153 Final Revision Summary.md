---
type: revision
course: CMIS 3153
status: complete
tags: [cmis3153, revision, summary]
aliases: [CMIS 3153 Final Revision, CMIS 3153 Cheat Sheet]
---
# CMIS 3153 · Final Revision Summary (the night before)

> [!info] Back to [[00. CMIS 3153 Course Overview|Course Overview]] · Also: [[CMIS 3153 High Priority Topics]] · [[CMIS 3153 Important Formulas and Calculations]] · [[CMIS 3153 Important Comparisons]] · [[CMIS 3153 Important Definitions]]

## Exam strategy
- **7 questions, answer 5, 2½ h** → ≈ **30 minutes per question**. Each question = 100 marks; a 25-mark part ≈ 7 minutes.
- Recent layout: **Q1 disk · Q2 indexing · Q3 B+-tree · Q4 processing + RA · Q5 query trees/optimization · Q6 distributed scenario · Q7 short notes**. Q1–Q5 are the most predictable.
- For "discuss/compare" parts: **definition → mechanism → table → example → conclusion**. For scenarios: **name the city/table** in every point.
- Draw diagrams (disk, double buffering, query trees, B+-trees, DDB architecture): they earn marks quickly.

## L01 Storage and files ([[01.99 Summary - Data Storage and File Organization|summary]])
- Primary (RAM, volatile, CPU-direct) · secondary (disk, persistent) · tertiary (tape) · cloud. DBs on disk: too big, persistent, cheap, online.
- Disk: platters → tracks → sectors/blocks; cylinder = same-radius tracks; **address = cylinder + surface + block**.
- **Access = s + rd + btt**; $rd = 30{,}000/p$ ms; $btt = B/tr$; $btr = \frac{B}{B+G}tr$. 7200 rpm → 4.17 ms.
- **Double buffering:** CPU processes buffer A while controller fills B; seek + rotation paid once for consecutive blocks. Store scanned files **contiguously** (same track → cylinder).
- Records: fixed vs variable (variable fields, repeating groups, optional fields, mixed files); spanned vs unspanned; $bfr = \lfloor B/R\rfloor$, $b = \lceil r/bfr\rceil$.
- Heap (cheap insert, $b/2$ search) · sorted (binary $\log_2 b$, ranges, costly inserts) · hash (≈1 block equality, no ranges; chaining/open addressing; external buckets; extendible/linear hashing).
- RAID 0/1/5/6/10 (striping, mirroring, parity). SAN block-level vs NAS file-level.
- **Tutorial 1 numbers:** R 114, bfr 4, b 5000, 3167.5 ms / 108,750 ms / 565.5 ms.

## L02 Indexing ([[02.99 Summary - Indexing Structures|summary]])
- Index = ⟨key, pointer⟩ file; secondary access path; purpose: fewer block reads. Cost: space + maintenance.
- Primary (ordering key, sparse, block anchors) · clustering (ordering non-key, per value) · secondary (non-ordering, dense, many). **One physical order → one primary/clustering index.**
- Clustered (rows in index order, great for ranges, 1 per table) vs non-clustered (pointers, random I/O, many).
- Multilevel: fan-out $fo$, $t = \lceil\log_{fo} r_1\rceil$, cost $t+1$ (lecture: 10 → 4). Static ISAM vs dynamic B+-tree.
- **B+-tree:** data in linked leaves; internal = signposts; balanced; ≥ half full. **Insert:** leaf split copy up, internal split push up, root split → taller. **Delete:** redistribute else merge; root empty → shorter. Utilization = entries/capacity.
- **2022/23 Q3:** 90 → K [90 96 99]; 42 → E[40 41] E′[42 43 44], B[40 42 45]; 79 → I[78 79] I′[80 85 86], C[60 78] C′[87 90], A[50 80]; del 23 → D[32 40] E[41 43 44] B[41 45]; del 50 → new root [40 45 50 78].
- **2023/24 Q3(c):** final root [ban day kin max]; leaves [ace ado] [ban bat bug cop] [day era fax gas] [kin let log] [max rye won]; utilization 20/24 = 83.3%.
- Maintenance: every index updated on insert/delete; indexed-column update = delete + insert; clustered key update moves the row.

## L03 Query processing and RA ([[03.99 Summary - Query Processing and Relational Algebra|summary]])
- **Steps:** parse (tokens, syntax, parse tree) → validate/translate to RA → optimize (statistics) → execution plan → execute → results. Draw the pipeline.
- σ rows · π columns · ∪ ∩ − (union-compatible) · × · ρ · ⋈ (theta, equi, natural, outer) · ÷ (for all) · γ (grouping).
- Filter → Join → Reduce; "not" = −; "all" = ÷; "at least two" = ρ self-join with ≠; "or" = ∪.
- WHERE below γ; HAVING above γ. NOT IN = difference.
- $\pi_A(E_1 - E_2) \ne \pi_A(E_1) - \pi_A(E_2)$ (π commutes only with ∪).
- Pipelining (on the fly) vs materialization.

## L04 Query optimization ([[04.99 Summary - Query Optimization|summary]])
- **Logical** (what; RA rewriting; storage-independent) vs **physical** (how; algorithms and access paths; cost-based).
- **CBO** (generate → cost → select; statistics) vs **RBO/heuristic** (fixed rules; fast); hybrid; ML; dynamic.
- Query **tree** (ordered, RA, many) vs **graph** (unordered, one; edges show valid joins).
- **Heuristics:** cascade σ → push σ down → most restrictive first, avoid × → σ+× → ⋈ → push π down.
- Aquarius answer: Ahmed. Canonical trees are inefficient because of Cartesian products and late selections.
- Indexes in optimization: access paths chosen by selectivity; index NL joins; avoid sorts; covering. Not used when selectivity is poor (Tutorial 4: 25% > 10%).
- Plan B beats Plan A: unclustered range index scan vs file scan; per-tuple index probes vs hash join; selection pushed down.

## L05 Distributed DBs ([[05.99 Summary - Distributed Database Systems|summary]])
- DDB = logically related DBs over a network; DDBMS makes it transparent. Benefits: scalability, availability, locality, parallelism, fault tolerance, no single point of failure.
- **CAP:** pick 2 of C, A, P; P unavoidable → choose C or A during partitions. CP: HBase/ZooKeeper; AP: Cassandra/Couchbase.
- Transparency (location, naming, replication, fragmentation); autonomy (design, communication, execution); reliability vs availability.
- Fragmentation: horizontal σ (∪), vertical π + key (outer union/join), mixed; replication full/partial/none; allocation schema.
- **Scenario recipe:** orders horizontally by branch; derived fragmentation for order lines; catalogue fully replicated; one backup replica of orders; CP for stock/payments, AP for catalogue/tracking.
- Query processing: mapping → localization → global optimization (communication) → local → join/aggregate → merge; ship smaller relation; semijoin.
- Concurrency/recovery: primary site (+backup), primary copy, voting; logs, checkpoints, **2PC** (prepare → commit), 3PC.

## L06 ORDBMS ([[06.99 Summary - Object-Relational Databases|summary]])
- RDB issues: flat, no nested sets/arrays, weak complex types. OODB failed: immature, scale, niche, encapsulation vs querying.
- ORDB features: UDTs, complex types (row, array, nested tables, varrays, JSON), methods, inheritance (`UNDER`), REF/OIDs, object views.
- **OID vs PK:** system-generated, immutable, value-independent, hidden, system-wide, referenced by pointers vs user-defined, value-based, changeable, visible, per table, referenced by FKs.
- `aTable`/`bTable` UNDER: aTable → 2 cols, 3 rows; bTable → 3 cols, 1 row.
- ORM: class↔table, object↔row, attribute↔column; JDBC/iBATIS/LINQ/JPA; lazy loading; impedance mismatch.

## L07 Data warehousing ([[07.99 Summary - Data Warehousing and OLAP|summary]])
- Inmon: **subject-oriented, integrated, nonvolatile, time-variant**.
- **OLTP** (short read-write transactions, current detailed data, ms latency, normalised) vs **OLAP** (complex read-only queries, historical summarised data, scans/aggregates, star schema).
- Cubes; roll-up, drill-down, pivot, slice-and-dice; fact vs dimension; star vs snowflake vs constellation; bitmap index; ETL; metadata.

## L08 Gaps ⚠️
- **Three-schema:** external / conceptual / internal + mappings; logical vs physical data independence ([[08.01 Three-Schema Architecture and Data Independence]]).
- **NoSQL vs relational:** flexible schema, horizontal scaling, eventual consistency vs ACID, SQL, joins; choose by consistency needs, scale, data shape ([[08.02 NoSQL vs Relational Databases]]).

## Last-minute checklist
- [ ] I can draw the query-processing pipeline.
- [ ] I can compute $s + rd + btt$ from rpm.
- [ ] I can compute R, bfr, b, linear and binary search times.
- [ ] I can fill a linear-probing table.
- [ ] I can insert into and delete from a B+-tree (d = 2) and compute utilization.
- [ ] I can write RA for "not", "all", "at least two", "same city as manager".
- [ ] I can draw a canonical query tree and optimize it step by step.
- [ ] I can answer CAP and fragmentation/replication for a three-city scenario.
- [ ] I can list two differences between OIDs and primary keys.
- [ ] I can compare OLTP and OLAP on workload, access pattern and performance.
