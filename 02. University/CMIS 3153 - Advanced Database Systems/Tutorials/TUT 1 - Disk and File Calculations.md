---
type: tutorial
course: CMIS 3153
lesson: L01
tutorial: T1
priority: high
status: complete
tags: [cmis3153, tutorial, cmis3153/L01, calculation]
aliases: [Tutorial 1, ADB Tutorial 1, T1 Disk calculations]
---
# TUT 1 · Disk and File Calculations (ADB Tutorial 1)

> [!info] Lesson: [[01.00 Data Storage and File Organization]] · Formula sheet: [[01.12 Worked File and Disk Calculations]] · Back to [[CMIS 3153 Tutorials Index|Tutorials index]]
> **Exam link:** Q2 of this tutorial is **identical** to **2021/22 Q6** (100 marks). Q1(e)–(f) is the method of 2022/23 Q1(c) and 2023/24 Q1(c).

> [!tip] Before you start
> Write the formulas first, then substitute. Keep units in **ms** and **bytes/ms**. $\lfloor\ \rfloor$ = round down, $\lceil\ \rceil$ = round up.

---

## Question 1: Disk characteristics
> **Q1.** A disk: block size $B$ = 512 bytes; interblock gap $G$ = 128 bytes; 20 blocks per track; 400 tracks per surface; a disk pack of 15 **double-sided** disks.

**Surfaces:** 15 disks × 2 sides = **30 surfaces**.

### (a) Total and useful capacity of a track
$$\text{total} = 20 \times (B + G) = 20 \times 640 = \mathbf{12{,}800\ bytes} \qquad \text{useful} = 20 \times B = 20 \times 512 = \mathbf{10{,}240\ bytes}$$
*Why:* gaps take space on the track but hold no user data.

### (b) Number of cylinders
A cylinder = the tracks with the same radius on all surfaces, so number of cylinders = tracks per surface = **400**.

### (c) Capacity of a cylinder
$$\text{total} = 30 \times 12{,}800 = \mathbf{384{,}000\ bytes} \qquad \text{useful} = 30 \times 10{,}240 = \mathbf{307{,}200\ bytes}$$

### (d) Capacity of the disk pack
$$\text{total} = 400 \times 384{,}000 = \mathbf{153{,}600{,}000\ bytes} \ (\approx 153.6\ \text{MB}) \qquad \text{useful} = 400 \times 307{,}200 = \mathbf{122{,}880{,}000\ bytes}$$

### (e) 2400 rpm: transfer rate, block transfer time, rotational delay, bulk transfer rate
1. One revolution: $\dfrac{60{,}000}{2400} = 25$ ms.
2. **Transfer rate** (one full track passes the head per revolution):
$$tr = \frac{12{,}800\ \text{bytes}}{25\ \text{ms}} = \mathbf{512\ bytes/ms}$$
3. **Block transfer time:** $btt = B/tr = 512/512 = \mathbf{1\ ms}$.
4. **Average rotational delay:** half a revolution: $rd = 25/2 = \mathbf{12.5\ ms}$.
5. **Bulk transfer rate:** $btr = \dfrac{B}{B+G}\, tr = \dfrac{512}{640} \times 512 = \mathbf{409.6\ bytes/ms}$.

### (f) Average seek 30 ms: time to locate and transfer one block
$$s + rd + btt = 30 + 12.5 + 1 = \mathbf{43.5\ ms}$$

### (g) 20 random blocks vs 20 consecutive blocks with double buffering
- **20 random blocks:** each pays seek + rotation + transfer: $20 \times 43.5 = \mathbf{870\ ms}$.
- **20 consecutive blocks with double buffering:** seek and rotational delay once, then a stream:
$$s + rd + 20 \times btt = 30 + 12.5 + 20 = \mathbf{62.5\ ms}$$
More accurately with the bulk rate: $30 + 12.5 + 20 \times \dfrac{512}{409.6} = 30 + 12.5 + 25 = \mathbf{67.5\ ms}$.
- **Comparison:** consecutive reading is about **13–14× faster**: seek and rotational delay dominate random access.

---

## Question 2: STUDENT file (= 2021/22 Q6)
> **Q2.** $r$ = 20,000 STUDENT records, fixed length. Fields: Name 30, Ssn 9, Address 40, PHONE 10, Birthdate 8, Sex 1, Major_dept_code 4, Minor_dept_code 4, Class_code 4 (integer), Degree_program 3; plus a 1-byte deletion marker. Disk as in Q1.

### (a) Record size
$$R = 30 + 9 + 40 + 10 + 8 + 1 + 4 + 4 + 4 + 3 + 1 = \mathbf{114\ bytes}$$

### (b) Blocking factor and number of blocks (unspanned)
$$bfr = \left\lfloor \frac{512}{114} \right\rfloor = \lfloor 4.49 \rfloor = \mathbf{4\ records/block} \qquad b = \left\lceil \frac{20{,}000}{4} \right\rceil = \mathbf{5{,}000\ blocks}$$

### (c) Linear search, blocks contiguous, double buffering
On average half the file is read: $b/2 = 2{,}500$ blocks. Contiguous + double buffering → one seek, one rotational delay:
$$s + rd + 2500 \times \frac{B}{btr} = 30 + 12.5 + 2500 \times 1.25 = \mathbf{3{,}167.5\ ms} \approx 3.17\ \text{s}$$
(Simpler estimate with $btt$: $30 + 12.5 + 2500 \times 1 = 2{,}542.5$ ms.)

### (d) Linear search, blocks not contiguous
Each block is a random access:
$$2500 \times (s + rd + btt) = 2500 \times 43.5 = \mathbf{108{,}750\ ms} \approx 108.75\ \text{s}$$

### (e) File ordered by Ssn, binary search
$$\lceil \log_2 b \rceil = \lceil \log_2 5000 \rceil = \lceil 12.29 \rceil = 13 \text{ block accesses} \qquad 13 \times 43.5 = \mathbf{565.5\ ms}$$

> [!warning] In the 2021/22 paper
> The paper omits the rpm and seek time. Write "assuming 2400 rpm and average seek time 30 ms as in the given disk (Tutorial 1)".

---

## Questions 3–4: EMPLOYEE file
> **Q3.** Disk: seek $s$ = 20 ms; rotational delay $rd$ = 10 ms; block transfer time $btt$ = 1 ms; $B$ = 2400 bytes; $G$ = 600 bytes. EMPLOYEE fields: Ssn 9, Last_name 20, First_name 20, Middle_init 1, Birth_date 10, Address 35, Phone 12, Supervisor_ssn 9, Department 4, Job_code 4, deletion marker 1. $r$ = 30,000 records, fixed length, unspanned.
> **Q4.** Write formulas and calculate: $R$, $bfr$, $b$; then (a)–(f).

**Record size, bfr, b**
$$R = 9+20+20+1+10+35+12+9+4+4+1 = \mathbf{125\ bytes}$$
$$bfr = \lfloor 2400/125 \rfloor = \mathbf{19} \qquad b = \lceil 30{,}000/19 \rceil = \lceil 1578.9 \rceil = \mathbf{1{,}579\ blocks}$$

### (a) Wasted space per block (unspanned)
$$B - bfr \times R = 2400 - 19 \times 125 = 2400 - 2375 = \mathbf{25\ bytes}$$

### (b) Transfer rate and bulk transfer rate
$$tr = \frac{B}{btt} = \frac{2400}{1} = \mathbf{2400\ bytes/ms} \qquad btr = \frac{B}{B+G}\,tr = \frac{2400}{3000} \times 2400 = \mathbf{1920\ bytes/ms}$$

### (c) Average block accesses, linear search
$$b/2 = 1579/2 = \mathbf{789.5} \approx 790 \text{ block accesses}$$

### (d) Linear search time, consecutive blocks, double buffering
$$s + rd + 789.5 \times \frac{B}{btr} = 20 + 10 + 789.5 \times 1.25 \approx \mathbf{1{,}016.9\ ms} \approx 1.02\ \text{s}$$
(With $btt$: $20 + 10 + 789.5 \times 1 = 819.5$ ms.)

### (e) Linear search time, blocks not consecutive
$$789.5 \times (s + rd + btt) = 789.5 \times 31 \approx \mathbf{24{,}474.5\ ms} \approx 24.5\ \text{s}$$

### (f) Binary search on the ordering key
$$\lceil \log_2 1579 \rceil = \lceil 10.62 \rceil = \mathbf{11} \text{ block accesses} \qquad 11 \times (20 + 10 + 1) = \mathbf{341\ ms}$$

---

## What this tutorial teaches
- Disk geometry → capacities; rpm → $rd$ and $tr$; gaps → $btr$.
- **Contiguous + double buffering** saves the seek and rotation for all but the first block ([[01.04 Buffering of Blocks]]).
- **Binary search** on a sorted file beats linear search, but each probe is a random access ([[01.07 Heap and Sorted Files]]).
- These numbers motivate **indexes** ([[02.02 Primary Indexes]]).
