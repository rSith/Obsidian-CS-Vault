---
type: revision
course: CMIS 3153
status: complete
tags: [cmis3153, revision, definitions]
aliases: [CMIS 3153 Definitions, CMIS 3153 Glossary]
---
# CMIS 3153 · Important Definitions (simple English, exam-ready)

> [!info] Back to [[00. CMIS 3153 Course Overview|Course Overview]] · Each term links to its full note.

## Lesson 1: Storage and files
| Term | Definition |
|---|---|
| **Primary storage** | Memory the CPU works on directly (cache, RAM); fast, small, expensive, **volatile**. [[01.01 Types of Storage and Memory Hierarchy]] |
| **Secondary storage** | Non-volatile online storage (HDD, SSD); larger, cheaper, slower; data must be copied to memory to be processed. |
| **Tertiary storage** | Archival/offline storage (tapes, optical jukeboxes, cloud archives); slowest, cheapest. |
| **Memory hierarchy** | Arrangement of storage by speed, size and cost: fast/small/dear near the CPU, slow/large/cheap far away. |
| **Persistent data** | Data that must survive for long periods (beyond program runs and power cuts). |
| **Track / cylinder / sector / block** | Concentric circle on a surface / same-radius tracks on all surfaces / fixed arc of a track (smallest hardware unit) / contiguous sectors forming the unit of transfer. [[01.02 Magnetic Disk Structure]] |
| **Block address** | ⟨cylinder no., track (surface) no., block no.⟩. |
| **Seek time** | Time to move the head to the right track. [[01.03 Disk Access Time and Performance]] |
| **Rotational delay (latency)** | Time waiting for the block to rotate under the head; average half a revolution. |
| **Block transfer time** | Time to transfer the block's data: $B/tr$. |
| **Bulk transfer rate** | Rate of useful bytes when reading consecutive blocks, accounting for gaps: $\frac{B}{B+G}tr$. |
| **Double buffering** | Using two memory buffers so the CPU processes one block while the I/O processor fills the other. [[01.04 Buffering of Blocks]] |
| **Record / record type** | A collection of related field values / the list of fields and their types. [[01.05 Records, Blocking and File Allocation]] |
| **Fixed- vs variable-length record** | All records the same size vs records of different sizes (variable fields, repeating groups, optional fields, mixed files). |
| **Blocking factor (bfr)** | Number of records per block, $\lfloor B/R\rfloor$. |
| **Spanned / unspanned** | Records may / may not cross block boundaries. |
| **File header (descriptor)** | Information needed to access a file: block addresses and record format. |
| **File organization** | How a file's records and blocks are arranged and linked on disk. [[01.06 File Operations and Access Methods]] |
| **Access method** | A set of operations that can be applied to a file. |
| **Heap file** | Unordered file; new records appended. [[01.07 Heap and Sorted Files]] |
| **Sorted (sequential) file** | Records physically ordered on an ordering field. |
| **Hash file** | Records placed in buckets by a hash function on a hash key. [[01.08 Hash Files and Collision Handling]] |
| **Collision** | Two keys hashing to the same bucket/slot. |
| **Open addressing / chaining** | Resolve collisions by probing other slots / by linked lists (overflow chains). |
| **Extendible hashing** | Dynamic hashing with a directory (global depth) of bucket pointers; buckets split as needed. [[01.09 Dynamic Hashing]] |
| **RAID** | Array of independent disks acting as one logical disk for performance and/or redundancy (striping, mirroring, parity). [[01.10 RAID]] |
| **SAN / NAS** | Block-level storage over a dedicated network / file-level storage over Ethernet. [[01.11 SAN and NAS]] |

## Lesson 2: Indexing
| Term | Definition |
|---|---|
| **Index** | Separate file of ⟨key, pointer⟩ giving a secondary access path without moving data. [[02.01 What Is an Index]] |
| **Dense / sparse index** | Entry for every record / only for some records (e.g. one per block). |
| **Primary index** | Sparse index on the ordering key of an ordered file; one entry per block (block anchor). [[02.02 Primary Indexes]] |
| **Block anchor** | The first record in a block; its key is stored in the primary index. |
| **Clustering index** | Index on an ordering non-key field; one entry per distinct value. [[02.03 Clustering Indexes]] |
| **Secondary index** | Index on a non-ordering field; dense for keys; many allowed. [[02.04 Secondary Indexes]] |
| **Clustered / non-clustered index** | Rows stored in index order (one per table) / separate structure of pointers (many). [[02.05 Clustered vs Non-Clustered Indexes and Index Selection]] |
| **Multilevel index** | Index on an index until the top level fits one block. [[02.06 Multilevel Indexes]] |
| **Fan-out (fo)** | Index entries per block; the factor by which each level reduces the search. |
| **B-tree** | Balanced search tree; nodes half-full to full; keys with data pointers in all nodes. [[02.07 B-Trees]] |
| **B+-tree** | B-tree variant with all data entries in linked leaves and keys-only internal nodes. [[02.08 B+ Trees]] |
| **Order p (d)** | Max pointers per node (p) / min entries per node with max 2d (d). |
| **Utilization** | Entries stored ÷ maximum entries the nodes can hold. |
| **Composite index** | One index on several columns, order matters. [[02.09 Index Maintenance, Composite and Covering Indexes]] |
| **Covering index** | An index containing all columns a query needs, so the table isn't read. |
| **Selectivity** | Fraction of rows matching a predicate. [[04.07 Indexing in Query Optimization]] |

## Lesson 3: Query processing and RA
| Term | Definition |
|---|---|
| **Query processing** | Steps that turn an SQL query into an efficient plan, execute it and return results with minimum resources. [[03.01 Stages of Query Processing]] |
| **Parsing** | Tokenizing and syntax-checking the query into a parse tree. |
| **Semantic analysis** | Checking the query against the schema (names, types) and translating it to RA. |
| **Execution plan** | Detailed roadmap of operations, algorithms and access paths. |
| **Relational algebra** | Formal language of operations on relations producing relations. [[03.02 Relational Algebra Basic Operations]] |
| **Selection σ / projection π** | Keep rows satisfying a condition / keep listed columns (removing duplicates). |
| **Union-compatible** | Same number of attributes with compatible domains. |
| **Theta / equi / natural join** | Join on any comparison / on equality / on all same-named attributes. [[03.03 Set Operations and Joins]] |
| **Outer join** | Join that keeps unmatched rows, filling NULLs. |
| **Division** | Values related to *all* values of another relation. |
| **Aggregation γ** | Grouping with aggregate functions (COUNT, SUM, AVG …). |
| **Relation / relation schema** | Set of distinct tuples with atomic values / its structure (name, attributes, keys). [[03.05 Relational Algebra, SQL and Database Design]] |
| **Pipelining** | Passing tuples directly between operators without storing intermediate results. [[03.06 Pipelining and Materialization]] |

## Lesson 4: Query optimization
| Term | Definition |
|---|---|
| **Query optimization** | Choosing the most efficient evaluation plan among equivalent ones. [[04.00 Query Optimization]] |
| **Logical plan** | Abstract RA plan of *what* to do, independent of storage. [[04.03 Logical vs Physical Query Plans]] |
| **Physical plan** | Concrete plan of *how*: algorithms and access paths. |
| **Cost-based optimization** | Choosing the plan with the lowest estimated cost from statistics. [[04.04 Query Optimization Strategies]] |
| **Rule-based (heuristic) optimization** | Applying fixed rewrite rules (e.g. push selections down) without cost estimates. |
| **Heuristic** | A rule that works well in most cases but is not guaranteed in every case. |
| **Cost model** | Mathematical model estimating a plan's resource use. [[04.02 Query Cost and Cost Models]] |
| **Selection cardinality** | Expected number of records satisfying an equality condition ($s = sl \cdot r$). |
| **Query tree** | Tree of RA operators with relations as leaves; fixes execution order. [[04.05 Query Trees and Query Graphs]] |
| **Query graph** | Graph of relations (nodes) and conditions (edges); no order; one per query. |
| **Canonical (initial) tree** | × of all relations, one σ with the WHERE condition, π on top. |
| **Predicate push-down** | Moving selections as close to the base relations as possible. [[04.06 Heuristic Optimization and Transformation Rules]] |
| **Hash join / nested-loop join / sort-merge join** | Build-probe hash table / compare every pair / merge sorted inputs. [[04.01 Query Execution and Join Algorithms]] |

## Lesson 5: Distributed databases
| Term | Definition |
|---|---|
| **Distributed database (DDB)** | Collection of logically related databases distributed over a network. [[05.01 DDB Concepts and Architecture]] |
| **DDBMS** | Software managing a DDB and making the distribution transparent. |
| **CAP theorem** | A distributed store can guarantee only two of consistency, availability, partition tolerance. [[05.02 CAP Theorem]] |
| **Eventual consistency** | Replicas become identical eventually if no new updates occur. |
| **Transparency** | Hiding distribution details: location, naming, replication, fragmentation. [[05.03 Transparency, Autonomy and Reliability]] |
| **Autonomy** | Degree to which sites operate independently (design, communication, execution). |
| **Reliability / availability** | Probability the system is up at a time point / continuously available over an interval. |
| **Horizontal / vertical fragmentation** | Subset of rows ($\sigma_C$) / subset of columns including the key ($\pi_L$). [[05.04 Fragmentation, Replication and Allocation]] |
| **Fragmentation schema / allocation schema** | Definition of all fragments / mapping of fragments to sites. |
| **Full / partial / no replication** | Whole DB at every site / some fragments copied / each fragment at one site. |
| **Homogeneous / heterogeneous DDBS** | Same DBMS everywhere / different DBMSs and schemas. [[05.05 Homogeneous, Heterogeneous and Federated Systems]] |
| **Federated DB / multidatabase** | Heterogeneous system with a global schema / without one (full autonomy). |
| **Semijoin** | Shipping only the join column to reduce a relation before transfer. [[05.06 Distributed Query Processing and Semijoin]] |
| **Two-phase commit (2PC)** | Coordinator asks all sites to prepare/vote, then commits only if all vote yes. [[05.07 Distributed Concurrency Control and Recovery]] |
| **Primary site / primary copy / voting** | One coordinator site / per-item primary copy / majority lock granting. |

## Lesson 6: ORDBMS
| Term | Definition |
|---|---|
| **ORDBMS** | Relational DBMS extended with object-oriented features, still using SQL. [[06.02 Object-Relational Databases and Their Features]] |
| **OODBMS** | DBMS adding persistence to OO languages; objects with state and behaviour. [[06.01 Relational vs Object-Oriented Databases]] |
| **UDT (user-defined type)** | Custom composite type with attributes (and methods). [[06.04 SQL Object-Relational Features]] |
| **Object (typed) table** | Table created `OF` a type; rows are objects with OIDs. |
| **OID** | System-generated, immutable object identifier independent of attribute values. [[06.05 Object Identity vs Primary Key]] |
| **REF / SCOPE / ->** | Reference type / table it may point into / dereferencing operator. |
| **UNDER** | Declares a subtype or subtable (inheritance). |
| **ORM** | Technique mapping classes ↔ tables, objects ↔ rows so programs use objects instead of SQL. [[06.03 Object-Relational Mapping]] |
| **Impedance mismatch** | Conceptual/technical gap between OO programs and relational storage. |
| **Lazy / eager loading** | Load related data when accessed / together with the parent. |

## Lesson 7: Data warehousing
| Term | Definition |
|---|---|
| **Data warehouse (Inmon)** | Subject-oriented, integrated, nonvolatile, time-variant collection of data supporting management decisions. [[07.01 Data Warehouse Concepts]] |
| **OLTP / OLAP** | Short operational transactions on current data / complex analysis of historical warehouse data. [[07.02 OLTP vs OLAP]] |
| **DSS / EIS** | Systems supporting decision makers with high-level data. |
| **Data mining** | Discovering unanticipated new knowledge in data. |
| **Data mart** | Department-focused subset warehouse. |
| **Data cube / hypercube** | Multidimensional array of measures by dimensions. [[07.03 Multidimensional Modeling and OLAP Operations]] |
| **Fact / dimension table** | Measures + foreign keys / descriptive attributes of a dimension. |
| **Star / snowflake schema** | Fact table with denormalised / normalised dimension tables. |
| **Roll-up / drill-down / pivot / slice-and-dice** | Summarise up / detail down / rotate axes / project on dimensions. |
| **ETL** | Extract, transform (format, clean), load data into the warehouse. [[07.04 Building a Data Warehouse]] |
| **Bitmap index** | One bit vector per value; good for low cardinality. |

## Supporting (⚠️ not in notes)
| Term | Definition |
|---|---|
| **Three-schema architecture** | External (views), conceptual (whole DB), internal (storage) levels with mappings. [[08.01 Three-Schema Architecture and Data Independence]] |
| **Logical / physical data independence** | Change conceptual schema without changing views / change internal schema without changing conceptual schema. |
| **NoSQL** | Non-relational, horizontally scalable, flexible-schema stores (key-value, document, column, graph). [[08.02 NoSQL vs Relational Databases]] |
