---
type: course
course: CMIS 3114
level: 3
semester: 1
tags: [networking]
---
# CMIS 3114 — Data Communication & Computer Networks

> [!info] Course details
> **Assessment:** Continuous assessment **30%** (mid-semester, tutorials, spot tests) · Final exam **70%**
> **Reference:** Tanenbaum, Feamster & Wetherall, *Computer Networks*, 6th ed.
> **Paper format:** 8 questions, answer 6

## Lectures
1. [[3114-01 Introduction]]
2. [[3114-02 Physical Layer]]
3. [[3114-03 Data Link Layer]]
4. [[3114-04 MAC Sub-layer]]
5. [[3114-05 Network Layer]]
6. [[3114-06 Transport Layer]]
7. [[3114-07 Application Layer]]
8. [[3114-08 Network Design & Programming Practical]]

> [!note] Chapter order
> This follows the lecture-note numbering (Ch3 = Data Link Layer, Ch4 = MAC Sub-layer). Your old outline had these two swapped.

## Exam prep
- [[3114 Topic Map & Past Papers]] — every past-paper question (2018/19–2023/24) mapped to a lecture topic
- [[3114 Ch01 Past Paper Q&A]] — Chapter 1 questions grouped by topic

> [!tip] From your exam analysis
> - Four topics appear in **every** paper: protocol definition, topologies, definition/uses of networks, and a **subnetting** calculation → [[3114-01 Introduction]], [[3114-05 Network Layer]]
> - Ch1 carries ~37% of the marks, Ch2 ~24%.
> - Calculations to master: Nyquist/Shannon with QAM, subnetting, Hamming code, byte/bit stuffing, sink tree, binary countdown.

## Progress
```base
filters:
  and:
    - course == "CMIS 3114"
    - type == "lecture"
views:
  - type: table
    name: Lectures
    order:
      - file.name
      - note.status
```
