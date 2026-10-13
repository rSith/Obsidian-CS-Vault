---
type: tutorial
course: CMIS 3153
lesson: [L01, L02]
tutorial: T2
priority: high
status: complete
tags: [cmis3153, tutorial, cmis3153/L01, cmis3153/L02]
aliases: [Tutorial 2, ADB Tutorial 2, T2 File organization]
---
# TUT 2 · Records, File Organization and B-Trees (ADB Tutorial 2)

> [!info] Lessons: [[01.00 Data Storage and File Organization]] (Q1–Q12), [[02.00 Indexing Structures]] (Q13–Q15) · Back to [[CMIS 3153 Tutorials Index|Tutorials index]]
> **Exam links:** Q1/Q7/Q9 → 2020/21 Q3(a) (fixed vs variable records); Q10 → 2022/23 Q7(d) (file directories); Q12 → 2022/23 Q1(d), 2023/24 Q1(d); Q14–Q15 → 2023/24 Q3(a).
> Note: Q7 and Q8 repeat Q1 and Q2 word for word.

---

## Q1 (and Q7). Reasons for variable-length records
1. **Variable-length fields**: e.g. `Name` stored using only the characters needed.
2. **Repeating fields (repeating groups)**: a field with several values for one record (several phone numbers, several degrees).
3. **Optional fields**: present only for some records (e.g. `Spouse_name`).
4. **Mixed files**: records of **different record types** stored together (e.g. a STUDENT record followed by its GRADE_REPORT records clustered in the same block).
→ [[01.05 Records, Blocking and File Allocation]]

## Q2 (and Q8). Determining sizes of variable-length records with variable-length fields
The system must be able to tell where each field and each record **ends**:
- **Separator characters**: special symbols (e.g. `$`, `%`) that cannot appear in data, placed after each field, plus an end-of-record marker.
- **Length prefixes**: store the **length (in bytes) of each field** before its value (and/or the total record length at the start of the record).
- **Field-name/value pairs** `⟨name, separator, value⟩` for optional fields, with a field-type code.
- **Pointers/offsets**: a small header in the record with the offset of each variable field.
When allocating to disk, the record length = sum of actual field lengths + separators/length fields; the block then holds a variable number of records (bfr becomes an **average**), and **spanned** organization may be used to reduce wasted space.

## Q3. One file per table vs many tables in one file
| Strategy | Advantage | Disadvantage |
|---|---|---|
| **(a) Each table in a separate file** | simple; scans of one table read only its own blocks; easy to grow/drop a table; file organization can be chosen per table | queries that join tables read several files (records of related tables are far apart → more seeks); many files to manage |
| **(b) Several tables (or the whole DB) in one file** | related records can be **clustered** together (e.g. a department with its employees), so joins need fewer block reads; fewer files for the OS | scanning **one** table must skip other tables' records → slower; variable record types complicate the file; harder space management |

## Q4. Why the allocation of records to blocks affects performance
- The **block** is the unit of disk I/O, so the cost of a query ≈ the **number of blocks read**.
- If records needed together are in the **same block/consecutive blocks** (good blocking, clustering, contiguous allocation), one read fetches many useful records and seeks are avoided.
- If they are **scattered**, every record may cost a separate random access ($s + rd + btt$ ≈ 40 ms in Tutorial 1).
- Wasted space (unspanned blocks, deleted records) means more blocks for the same data → more I/O.
- Example: Tutorial 1 Q2, linear search on contiguous blocks ≈ 3.2 s vs scattered ≈ 109 s.

## Q5. Logical vs physical files (and "object" files)
> [!warning] Terminology not defined in the lecture notes
> The lecture does not define "logical file", "physical file" or "object file". The answer below uses the standard meanings. This information could not be verified from the available course materials.
- **Logical file:** the file **as seen by programs/users**: a named collection of records of a record type, accessed by operations (Open, Find, Read …), independent of where it is stored. (In some systems, e.g. IBM i, a logical file is a *view/index over* a physical file.)
- **Physical file:** the **actual stored data on the device**: blocks at specific disk addresses, with a file header recording block addresses and record formats.
- **Object file** (if meant as a file of *objects*): a file storing serialized objects (state + type information) rather than flat records; in programming, an "object file" is compiled machine code, unrelated to databases.
- **Differences:** logical = abstract, record-oriented, program view; physical = concrete, block-oriented, storage view; the DBMS/OS maps logical operations onto physical blocks (this mapping gives data independence: [[08.01 Three-Schema Architecture and Data Independence]]).

## Q6. Effect of file size on the choice of file organization
- **Small files** (a few blocks): **heap** is adequate; a full scan costs little; extra structures are not worth their overhead.
- **Medium/large files** with key lookups: **sorted file + primary index**, **hashing** (equality) or **B+-tree** (equality + ranges).
- **Very large, growing files:** static structures (sorted files, static hashing, ISAM) degrade and need **reorganization**; **dynamic** structures (B+-tree, extendible/linear hashing) adapt.
- **Huge files scanned sequentially:** contiguous allocation, large blocks, double buffering.
→ [[01.06 File Operations and Access Methods]]

## Q9. When is a fixed-length representation of a variable-length record most useful?
When the **variation in length is small** (e.g. names mostly < 30 chars), when **optional fields are few** or **repeating groups have a small known maximum** (reserve, say, 3 phone slots), and when **fast, simple access and in-place updates** matter more than storage space. Then reserve the maximum size per field (using a null value for missing fields) to get fixed offsets, simple $bfr = \lfloor B/R\rfloor$ and direct record addressing.

## Q10. Information stored in file headers
A **file header (file descriptor)** holds information needed by programs accessing the file:
- **disk addresses of the file blocks** (first block, last block for heap inserts, or a table/index of blocks; bucket-to-block table for hash files);
- **record format descriptions**: for fixed-length unspanned records: **field lengths and order**; for variable-length records: **field type codes, separator characters, record type codes**;
- (typically also) number of records, blocking factor, file organization type, free-space information.
→ [[01.05 Records, Blocking and File Allocation]] §6

## Q11. File organization vs access method
- **File organization:** how a file's data is arranged into records and blocks and access structures, i.e. **how records/blocks are placed on the medium and interlinked** (heap, sorted, hashed, B-tree).
- **Access method:** the **group of operations** (Find, FindNext, Insert, Delete …) a program can apply to the file.
- Several access methods can work on one organization, but some access methods need a particular organization (no indexed access without an index).
→ [[01.06 File Operations and Access Methods]]

## Q12. Major problems with sorted files
1. **Insertion is expensive:** find the position, then **shift on average half the records** (read and rewrite half the blocks).
2. **Deletion** leaves gaps (or needs shifting); handled with **deletion markers** + periodic **reorganization**.
3. **Updating the ordering field** moves the record (delete + insert).
4. Searching on **non-ordering fields** gets no benefit (linear search).
5. **Binary search** still costs $\log_2 b$ random block accesses.
6. Workaround: an **overflow (transaction) file** of unsorted new records, periodically merged → searches must check both; extra complexity.
→ [[01.07 Heap and Sorted Files]]

## Q13. Structure of a B-tree node; order $p$
A B-tree node (order $p$):
$$\langle P_1, \langle K_1, Pr_1\rangle, P_2, \langle K_2, Pr_2\rangle, \dots, \langle K_{q-1}, Pr_{q-1}\rangle, P_q\rangle, \quad q \le p$$
- $P_i$: **tree pointers** to child nodes (NULL in leaves); $K_i$: search key values; $Pr_i$: **data pointers** to the records (or blocks) with key $K_i$.
- Keys sorted: $K_1 < K_2 < \dots < K_{q-1}$; all values $X$ in subtree $P_i$ satisfy $K_{i-1} < X < K_i$.
- **Order $p$** = the **maximum number of tree pointers** in a node (so at most $p-1$ keys). Each node except the root has at least $\lceil p/2\rceil$ tree pointers; the root has at least 2 (unless it is the only node); all leaves at the same level.
- $p$ is chosen so a node fills one disk block: $p \cdot P + (p-1)(V + P_r) \le B$.
→ [[02.07 B-Trees]]

## Q14. Internal and leaf nodes of a B+-tree; order $p$
- **Internal node:** $\langle P_1, K_1, P_2, K_2, \dots, K_{q-1}, P_q\rangle$ with $q \le p$: **only keys and tree pointers** (no data pointers). Each internal node (except the root) has at least $\lceil p/2 \rceil$ tree pointers. **Order $p$** = max tree pointers per internal node; chosen so $p \cdot P + (p-1) V \le B$.
- **Leaf node:** $\langle\langle K_1, Pr_1\rangle, \langle K_2, Pr_2\rangle, \dots, \langle K_{q-1}, Pr_{q-1}\rangle, P_{next}\rangle$: **keys with data pointers** plus a pointer to the **next leaf**. **Leaf order $p_{leaf}$** = max data entries per leaf; each leaf holds at least $\lceil p_{leaf}/2\rceil$; chosen so $p_{leaf}(V + P_r) + P \le B$.
- All leaves at the same level; every key value appears in a leaf.
→ [[02.08 B+ Trees]]

> [!example] Choosing $p$ (textbook-style)
> $B = 512$, key $V = 9$, block pointer $P = 6$, record pointer $P_r = 7$.
> Internal: $6p + 9(p-1) \le 512 \Rightarrow 15p \le 521 \Rightarrow p = 34$.
> Leaf: $16 p_{leaf} + 6 \le 512 \Rightarrow p_{leaf} = 31$.

## Q15. Why is a B+-tree usually preferred to a B-tree?
1. **Higher fan-out:** internal nodes contain no data pointers → more keys per block → **fewer levels** → fewer disk accesses.
2. **Range queries and sequential access:** all data entries are in **linked leaves**, so after one search the range is read by walking the leaf chain (a B-tree needs in-order traversal across levels).
3. **Uniform search cost:** every search goes to the leaf level.
4. **Simpler insertion/deletion:** data only in leaves; internal keys are copies.
5. **Full scans** read only the leaves.
→ [[02.08 B+ Trees]] §8
