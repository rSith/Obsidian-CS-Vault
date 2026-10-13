---
type: revision
course: CMIS 3153
status: complete
tags: [cmis3153, revision, comparison]
aliases: [CMIS 3153 Comparisons, CMIS 3153 A vs B]
---
# CMIS 3153 · Important Comparisons (every "A vs B" in one place)

> [!info] Back to [[00. CMIS 3153 Course Overview|Course Overview]] · 📝 = asked in a past paper

## Storage and files
### Main memory vs disk 📝 (2021/22 Q2a)
| Main memory | Disk |
|---|---|
| volatile | non-volatile |
| ns, uniform access | ms, depends on head position (seek + rotation) |
| byte/word transfers | block transfers |
| CPU uses directly | must copy to memory first |
| expensive, GB | cheap, TB |

### Fixed vs variable-length records 📝 (2020/21 Q3a)
| Fixed | Variable |
|---|---|
| same size, fixed offsets | differ; separators/length fields |
| simple access, in-place updates | complex access; records may move |
| may waste space | space-efficient |

### Spanned vs unspanned 📝 (2020/21 Q3b)
| Unspanned | Spanned |
|---|---|
| record within one block | record may continue in another block via pointer |
| wastes $B - bfr\cdot R$ | little waste |
| fixed-length, $B > R$ | required if $R > B$; large variable records |

### Contiguous vs linked allocation
| Contiguous | Linked |
|---|---|
| fast full reads (double buffering) | slow full reads |
| hard to expand | easy to expand |

### Heap vs sorted vs hash files
| | Heap | Sorted | Hash |
|---|---|---|---|
| Insert | cheap (append) | expensive (shift half) | cheap (bucket) |
| Equality search | $b/2$ | $\log_2 b$ (on key) | ~1 block |
| Range search | full scan | efficient | not supported |
| Ordered output | sort needed | free | not supported |

### Sequential vs indexed organization 📝 (2022/23 Q1d, 2023/24 Q1d)
| Sequential (sorted) | Indexed (B+-tree) |
|---|---|
| cheap ordered batch processing | fast random access by key and by other fields (secondary indexes) |
| costly inserts/deletes, reorganization, overflow files | local, logarithmic inserts/deletes |
| no index storage | index storage + maintenance |

### Chaining vs open addressing
| Chaining | Open addressing |
|---|---|
| overflow lists per bucket | probe other slots (linear, quadratic, double hashing) |
| table never "full" | clustering; table can fill |

### Static vs dynamic hashing
| Static | Dynamic (extendible/linear) |
|---|---|
| fixed buckets; overflow; full rehash to grow | grows bucket by bucket; directory (extendible) or split pointer (linear) |

### RAID levels 📝 (2021/22 Q2d)
| RAID 0 | RAID 1 | RAID 5 | RAID 6 | RAID 10 |
|---|---|---|---|---|
| striping, no redundancy | mirroring, 50% | distributed parity, survives 1 | double parity, survives 2 | mirror + stripe, 50%, fast |

### SAN vs NAS
| SAN | NAS |
|---|---|
| block-level, Fibre Channel/iSCSI, databases | file-level, Ethernet, NFS/SMB, file sharing |

### File organization vs access method
| File organization | Access method |
|---|---|
| how records/blocks are arranged and linked | operations applied to the file |

## Indexing
### Dense vs sparse
| Dense | Sparse |
|---|---|
| entry per record | entry per block / value |
| any file | file sorted on the field |
| larger | smaller |

### Primary vs clustering vs secondary 📝 (2020/21 Q5a, 2021/22 Q1a)
| | Primary | Clustering | Secondary |
|---|---|---|---|
| Field | ordering key | ordering non-key | non-ordering |
| Entries | per block | per distinct value | per record (key) |
| Dense? | no | no | yes (key) |
| Per file | ≤ 1 (either primary or clustering) | ≤ 1 | many |

### Clustered vs non-clustered 📝 (2020/21 Q6b, 2022/23 Q2b, 2023/24 Q2b)
| Clustered | Non-clustered |
|---|---|
| rows in index order | pointers to rows in any order |
| one per table | many |
| ranges: sequential I/O | ranges: random I/O per row |
| higher insert/update cost | each index updated separately |

### Hash vs tree index 📝 (2020/21 Q5b(ii), 2021/22 Q1c)
| Hash | B+-tree |
|---|---|
| equality only, ~1 I/O | equality + range + order, height + 1 |
| sensitive to skew; rehash | balanced, dynamic |

### Single-level vs multilevel
| Single-level | Multilevel |
|---|---|
| binary search $\log_2 b_i$ | $\log_{fo} r_1$, one block per level |

### Static (ISAM) vs dynamic (B+-tree) 📝 (2023/24 Q3b)
| Static | Dynamic |
|---|---|
| fixed upper levels; overflow chains | split/merge; always balanced |
| no locking of index pages; 100% full | node locking; ~67% full |
| read-mostly, batch-loaded data | frequently updated data |

### B-tree vs B+-tree (Tutorial 2 Q15)
| B-tree | B+-tree |
|---|---|
| keys + data pointers in every node | data entries only in leaves |
| no leaf links | linked leaves |
| lower fan-out | higher fan-out, shorter tree |
| range = in-order traversal | range = leaf scan |

### Leaf split vs internal split; redistribution vs merge
| Leaf split: **copy up** first key of new leaf | Internal split: **push up** middle key |
|---|---|
| **Redistribute:** borrow from sibling; update separator | **Merge:** combine with sibling; remove/pull down separator |

### Covering vs non-covering index
| Covering | Non-covering |
|---|---|
| query answered from index | extra row fetch per match |

## Query processing and relational algebra
### Selection vs projection (lecture exercise)
| σ | π |
|---|---|
| rows, WHERE | columns, SELECT list |
| same columns | removes duplicates |

### Union vs intersection; join vs theta join; natural vs equi-join; inner vs outer join
| Union: rows in either | Intersection: rows in both |
|---|---|
| **Join (equi/natural):** equality on keys | **Theta join:** any comparison |
| **Natural:** all same-named columns, kept once | **Equi:** stated equality, both columns kept |
| **Inner:** only matches | **Outer:** keeps unmatched rows (NULLs) |

### Set vs multiset (bag) RA
| Set | Bag |
|---|---|
| duplicates removed | duplicates kept (SQL behaviour) |

### Relation vs relation schema (Tutorial 3)
| Schema | Relation |
|---|---|
| structure | current set of tuples |

### Parsing vs semantic analysis
| Parsing | Semantic analysis |
|---|---|
| syntax, tokens, parse tree | meaning: names, types against schema |

### Pipelining vs materialization
| Pipelining | Materialization |
|---|---|
| tuples streamed, no temp tables | intermediate results stored |
| less memory/I/O, early results | works for every operator |

## Query optimization
### Logical vs physical optimization 📝 (2022/23 Q4b, 2023/24 Q4b)
| Logical | Physical |
|---|---|
| what (RA operators) | how (algorithms, access paths) |
| storage-independent rewriting | cost-based choice using statistics |
| push σ/π down, reorder joins | index scan vs table scan; hash vs nested-loop join |

### Cost-based vs rule-based/heuristic 📝 (2021/22 Q7b, 2022/23 Q5b)
| Cost-based | Heuristic |
|---|---|
| estimated costs from statistics | fixed rules |
| many plans compared | one transformed plan |
| accurate but expensive | fast but may be suboptimal |

### Static vs dynamic (adaptive) optimization
| Static | Dynamic |
|---|---|
| plan fixed before execution | plan adjusted during execution from feedback |

### Query tree vs query graph 📝 (2022/23 Q5c, 2023/24 Q5b)
| Tree | Graph |
|---|---|
| RA, ordered, many per query | calculus, unordered, one per query |
| used for execution planning | shows connections; guides join ordering |

### Table scan vs index scan
| Table scan | Index scan |
|---|---|
| reads all blocks sequentially | reads few blocks; random I/O if non-clustered |
| best for low selectivity | best for high selectivity |

### Join algorithms
| Nested loop | Index nested loop | Hash join | Sort-merge |
|---|---|---|---|
| any condition, small tables | small outer + index on inner | equality, large inputs | sorted inputs |

### Bitmap vs B-tree index (Tutorial 4)
| Bitmap | B-tree |
|---|---|
| low-cardinality columns; fast AND/OR | high-cardinality columns; ranges |

## Distributed databases
### Centralized vs distributed 📝 (2022/23 Q6a, 2023/24 Q7c)
| Centralized | Distributed |
|---|---|
| one site, one DBMS | many sites, fragmented/replicated data |
| simple, consistent | transparency, parallelism, availability |
| single point of failure | communication cost, complex transactions |

### Horizontal vs vertical fragmentation
| Horizontal | Vertical |
|---|---|
| rows, $\sigma_C(R)$ | columns + key, $\pi_L(R)$ |
| rebuild with ∪ | rebuild with outer union/join |

### Full vs partial vs no replication
| Full | Partial | None |
|---|---|---|
| max availability, slow updates | balance | cheap updates, low availability |

### Homogeneous vs heterogeneous 📝 (2021/22 Q3c); federated vs multidatabase
| Homogeneous | Heterogeneous |
|---|---|
| same DBMS, cooperative, less autonomy | different DBMS/schemas, limited cooperation |
| **Federated:** global schema, low autonomy | **Multidatabase:** no global schema, full autonomy |

### CP vs AP 📝 (CAP)
| CP | AP |
|---|---|
| consistent; may refuse requests | always answers; may be stale |
| HBase, ZooKeeper | Cassandra, Couchbase |

### Primary site vs primary copy vs voting; 2PC vs 3PC
| Primary site | Primary copy | Voting |
|---|---|---|
| one coordinator; bottleneck | per-item primary; directory needed | majority; more messages |
| **2PC:** prepare + commit; blocks if coordinator fails | **3PC:** + pre-commit; non-blocking | |

### Reliability vs availability
| Reliability | Availability |
|---|---|
| up at a time point | continuously up over an interval |

## ORDBMS
### Relational vs OO vs OR 📝 (2021/22 Q4b)
| Relational | OO | OR |
|---|---|---|
| flat tables, SQL | objects, classes, OQL/navigation | tables + UDTs, REFs, UNDER, SQL3 |

### Primary key vs OID 📝 (2020/21 Q4b, 2021/22 Q4d)
| PK | OID |
|---|---|
| value-based, user-chosen, changeable | system-generated, immutable, value-independent |
| visible, per table | hidden, system-wide |
| foreign keys + joins | references + dereferencing |

### Relational table vs object table 📝 (2020/21 Q4a)
| Relational | Object (typed) |
|---|---|
| column list | `OF type` |
| PK identity | OID, methods, inheritance |

### OODBMS vs RDBMS; lazy vs eager; entity class vs value type; one-to-many vs many-to-one
| OODBMS: complex data, niche | RDBMS: mature, scalable |
|---|---|
| **Lazy:** load on access | **Eager:** load with parent |
| **Entity class:** own identity, own table | **Value type:** embedded, no identity |
| **One-to-many:** collection on "one" side | **Many-to-one:** reference on "many" side |
| **OO association:** directional | **Foreign key:** non-directional |

## Data warehousing
### Database vs data warehouse; OLTP vs OLAP 📝 (2023/24 Q7b)
| OLTP / database | OLAP / warehouse |
|---|---|
| daily operations, current data | decision support, historical data |
| short read-write transactions | complex read-only queries |
| key lookups, few rows | large scans, aggregation |
| ms latency, high throughput | fast aggregation over huge data |
| normalised | star/snowflake |

### Star vs snowflake; roll-up vs drill-down; warehouse vs view; distributed vs federated warehouse
| Star: denormalised dimensions, fewer joins | Snowflake: normalised dimensions, more joins |
|---|---|
| **Roll-up:** summarise | **Drill-down:** detail |
| **Warehouse:** persistent, indexable, multidimensional | **View:** virtual, relational, one DB |
| **Distributed warehouse:** replicated metadata | **Federated warehouse:** autonomous marts, own metadata |

## Supporting
| Logical data independence: change conceptual schema, views unaffected | Physical data independence: change storage, conceptual schema unaffected |
|---|---|
| **Relational:** ACID, fixed schema, SQL, vertical scaling | **NoSQL:** flexible schema, horizontal scaling, eventual consistency |
