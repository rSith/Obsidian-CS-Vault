---
type: tutorial
course: CMIS 3153
lesson: [L02, L04]
tutorial: T4
priority: high
status: complete
tags: [cmis3153, tutorial, cmis3153/L02, cmis3153/L04, calculation]
aliases: [Tutorial 4, ADB Tutorial 4, T4 Selectivity, Index cost calculations]
---
# TUT 4 · Selectivity and Index Cost (ADB Tutorial 4)

> [!info] Lessons: [[02.00 Indexing Structures]], [[04.00 Query Optimization]] · Concept notes: [[04.07 Indexing in Query Optimization]], [[02.05 Clustered vs Non-Clustered Indexes and Index Selection]], [[02.09 Index Maintenance, Composite and Covering Indexes]] · Back to [[CMIS 3153 Tutorials Index|Tutorials index]]
> **Exam links:** Ex 1, 3, 5 support 2020/21 Q5(b)(ii), Q6(c); 2022/23 Q2(b), Q5(a); 2023/24 Q2(a)(b); 2020/21 Q2(b).

## Key ideas used throughout
$$
\text{selectivity} = \frac{\text{rows matching}}{\text{total rows}} \qquad \text{B-tree point lookup I/O} = \text{height} + 1 \ (\text{row fetch})
$$
- A **highly selective** predicate (few rows) makes an index worthwhile; low selectivity (many rows) → full scan.
- **Clustered** range scans read consecutive pages; **non-clustered** range scans can need one random I/O **per row**.
- **Bitmap** indexes suit **low-cardinality** columns.
- **Hash**: great for equality, useless for ranges.

> [!tip] Wording in the tutorial
> "Index sensitivity" is used informally to mean how **selective** an index is for a predicate. Careful: a "selectivity of 0.001%" is called *high* selectivity in the tutorial (very few rows).

---

## Worked examples (solutions given in the tutorial, explained)

### Example 1: Selectivity and index efficiency
Orders: 1,000,000 rows; `status` has 5 values: shipped 600,000, pending 250,000, cancelled 150,000, others negligible. Query: `WHERE status = 'pending'`.
1. Selectivity $= 250{,}000 / 1{,}000{,}000 = \mathbf{0.25}$ (25%).
2. Threshold 0.10 → $0.25 > 0.10$ → B-tree index **not used**; a full scan is cheaper.
3. A **bitmap index** is better: `status` has very **low cardinality** (5 values); bitmaps compress well and allow very fast filtering, especially combined with other conditions.

### Example 2: B-tree I/O cost
Height 3; each node access = 1 I/O; row fetch = 1 I/O; table = 50,000 pages. Query by email (unique).
1. Index: root → level 1 → leaf = 3, + 1 row fetch = **4 I/Os**.
2. Full scan: **50,000 I/Os**.
3. Index is massively more efficient.

### Example 3: Range scan vs full scan
5,000,000 rows; B-tree on `created_at`; 100,000 rows/day; query for 2025-01-01 to 2025-01-03; **clustered** index.
1. Matching rows $= 3 \times 100{,}000 = \mathbf{300{,}000}$.
2. Selectivity $= 300{,}000/5{,}000{,}000 = \mathbf{6\%}$ → the range scan is selective enough, especially with clustering → index used.
3. **Non-clustered:** 300,000 **random** reads → too expensive → optimizer likely chooses a **full table scan**.

### Example 4: Composite index (country, city, age)
10 M rows; 50 countries; ~200 cities per country; ~80 ages.
1. After `country='UK'`: $10{,}000{,}000/50 = \mathbf{200{,}000}$.
2. After `city='London'`: $200{,}000/200 = \mathbf{1{,}000}$.
3. After `age=30`: $1{,}000/80 = 12.5 \approx \mathbf{13}$ rows.
4. All index columns fully specified with equality → **index seek**.

### Example 5: Hash index
2,000,000 rows; 1,000 buckets; uniform.
1. Records per bucket $= 2{,}000{,}000/1{,}000 = \mathbf{2{,}000}$.
2. Expected comparisons ≈ half a bucket ≈ **1,000**.
3. B-tree: $\log_2(2{,}000{,}000) \approx \mathbf{21}$ comparisons; hash ≈ 1,000 comparisons **but fewer I/Os**. Hash is faster for exact equality; **useless for ranges, ordering and prefix matching**.

### Example 6: Index size
Key 20 B, pointer 8 B, node overhead 100 B, page 8 KB = 8,192 B; 2,000,000 rows.
1. Usable space $= 8192 - 100 = 8092$; entry $= 28$ B → $8092/28 \approx \mathbf{289}$ entries/page.
2. Leaf pages $= 2{,}000{,}000/289 \approx \mathbf{6{,}920}$.
3. Level above leaves: $6920/289 \approx 23.9 \to \mathbf{24}$ nodes; next level: $24/289 < 1 \to \mathbf{1}$ root. **Height = 3** (root → internal → leaf).
4. Point lookup ≈ 3 index I/Os + 1 row fetch = **4 I/Os**. (The tutorial prints "24/280", a typo for 24/289; the conclusion is unchanged.)

### Example 7: Covering vs non-covering
Index (last_name, first_name); 500,000 rows; ~500 rows per last name.
1. Index entries scanned = **500**.
2. Query A (`SELECT last_name, first_name …`, covering): ~3 I/Os to seek + ~2–3 leaf pages ≈ **5–6 I/Os**. Query B (`SELECT * …`, non-covering): + **500 random row lookups** ≈ **503 I/Os**.
3. Covering is efficient because **no table access** is needed.

---

## Exercises (worked solutions)

> [!note] Solutions to the exercises
> The tutorial gives no answers for the exercises. These follow the same method as the worked examples. Where data is missing, the assumption is stated.

### Exercise 1: Partial index
8,000,000 rows; 2,000,000 active; `CREATE INDEX idx_active ON users(last_login) WHERE active = true;` Query: `active = true AND last_login > NOW() - 7 days`.
- **(i) Index selectivity:** the partial index covers only active rows: $2{,}000{,}000/8{,}000{,}000 = \mathbf{0.25}$ of the table. The query's condition matches the index's `WHERE active = true`, so only these 2 M entries are searched, and the `last_login` range narrows it further.
- **(ii) Used?** **Yes, likely**: the query's predicate implies the index predicate, the index is 4× smaller than a full index, and "last 7 days" usually matches a small fraction of active users (e.g. if 5% logged in this week → 100,000 rows = 1.25% of the table). If most active users logged in this week, the range would match ~25% of the table and a full scan could win. *(Assumption: the share of recent logins is not given.)*

### Exercise 2: Membership level
5,000,000 rows: Gold 2,000,000; Silver 1,500,000; Bronze 1,200,000; Other 300,000. Query `membership_level = 'Silver'`.
1. Selectivity $= 1{,}500{,}000/5{,}000{,}000 = \mathbf{0.30}$.
2. Rule "use index only if < 0.15" → $0.30 > 0.15$ → **index not used** (full scan).
3. **Bitmap index:** yes, it helps: only 4 distinct values (**low cardinality**), compact bitmaps, fast AND/OR with other predicates. But since 30% of rows are returned, the benefit for this query alone is limited; it shines when combined with other filters.

### Exercise 3: Clustered range on order_id
`order_id BETWEEN 100000 AND 150000` → **50,000** consecutive rows; 200 rows/page.
- **(i)** Pages scanned $= 50{,}000/200 = \mathbf{250}$ (+ ~3 index pages to find the start).
- **(ii)** **Non-clustered:** rows are scattered → up to **50,000** random page reads (+ index leaf pages): about **200× more**; the optimizer would likely use a full scan instead.

### Exercise 4: Hash index load
200 buckets, 640 rows, load factor 3.2.
- **(i)** Average rows/bucket $= 640/200 = \mathbf{3.2}$.
- **(ii)** **Probability of overflow** depends on the **bucket capacity**, which is not given. With uniform hashing the number of rows in a bucket ≈ Poisson($\lambda = 3.2$): $P(\text{overflow}) = P(X > c)$.
  - capacity $c = 4$: $P(X > 4) \approx \mathbf{0.22}$ (about 22% of buckets overflow);
  - $c = 5$: $\approx 0.11$; $c = 3$: $\approx 0.40$.
  *(Assumption: capacity not stated; Poisson model is standard for uniform hashing.)*

### Exercise 5: B-tree point lookup
Height 4; index page = 1 I/O; row = 1 I/O; table 300,000 pages; `ssn = '123-45-6789'`.
- **(i)** Index: $4 + 1 = \mathbf{5}$ I/Os.
- **(ii)** Full scan: **300,000** I/Os.
- **(iii)** Optimizer selects the **index** (SSN is unique → highly selective).

### Exercise 6: Sensor logs, clustered timestamp
20,000,000 rows; ~150,000 rows/day; `timestamp BETWEEN '2025-04-01' AND '2025-04-02'`.
- **(iv)** Matching rows ≈ 2 days × 150,000 = **300,000** (counting both days, as in Example 3).
- **(v)** Selectivity $= 300{,}000/20{,}000{,}000 = \mathbf{0.015}$ (1.5%).
- **(vi)** **Yes**: a clustered range scan reads only the contiguous pages for those 2 days.
- **(vii)** Non-clustered: 300,000 random row fetches. Even at 1.5% that is a lot of random I/O; many optimizers would still choose a **full scan** (or a bitmap heap scan) depending on table size in pages; the clustered case is far better.

### Exercise 7: Composite (state, city, postal_code)
16,000,000 rows; 40 states; 100 cities per state; 50 postal codes per city.
- **(i)** After state: $16{,}000{,}000/40 = \mathbf{400{,}000}$.
- **(ii)** After city: $400{,}000/100 = \mathbf{4{,}000}$.
- **(iii)** After postal code: $4{,}000/50 = \mathbf{80}$.
- **(iv)** **Yes, an index seek**: all three columns are given with equality, in index order.

### Exercise 8: Hash on api_tokens
4,000,000 rows; 2,000 buckets; uniform.
- **(i)** Records per bucket $= \mathbf{2{,}000}$.
- **(ii)** Comparisons ≈ **1,000** on average (half a bucket).
- **(iii)** B-tree: $\log_2(4{,}000{,}000) \approx \mathbf{22}$ comparisons. Hash needs more in-bucket comparisons but typically fewer **I/Os** (one bucket read); for equality lookups the hash is faster in I/O terms; the B-tree supports ranges.

### Exercise 9: Index sizing (16 KB pages)
Page 16 KB = 16,384 B; overhead 120 B; key 32 B; pointer 8 B; 8,000,000 rows.
- **(i)** Entries/page $= \lfloor (16{,}384 - 120)/40 \rfloor = \lfloor 406.6 \rfloor = \mathbf{406}$.
- **(ii)** Leaf pages $= \lceil 8{,}000{,}000/406 \rceil = \mathbf{19{,}705}$.
- **(iii)** Level 2: $\lceil 19{,}705/406 \rceil = 49$ nodes; level 3: $\lceil 49/406\rceil = 1$ root → **height 3**.
- **(iv)** Point lookup = 3 + 1 = **4 I/Os**.

### Exercise 10: Covering vs non-covering (category, price)
1,200,000 items; 8,000 with category = 'Electronics'; 200 rows (entries) per page.
- **(i)** Index entries scanned = **8,000**.
- **(ii)** Query A (`SELECT category, price`, covering): ~3 I/Os to seek + $8{,}000/200 = 40$ leaf pages ≈ **43 page reads**.
- **(iii)** Query B (`SELECT *`, non-covering): the same 43 + up to **8,000** row fetches ≈ **8,043** (fewer if matching rows share pages).
- **(iv)** **Query A** is far more efficient: the index covers all requested columns, so the table is never touched.
