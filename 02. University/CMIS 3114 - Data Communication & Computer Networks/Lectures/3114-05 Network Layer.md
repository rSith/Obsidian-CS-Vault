---
type: lecture
course: CMIS 3114
lecture: 5
status: stub
tags: [networking]
aliases: [Network Layer]
---
# The Network Layer
> [!info] CMIS 3114 · Chapter 5
> [[CMIS 3114 Course Home]] · Exam prep: [[3114 Topic Map & Past Papers#Ch5-L1 — Network Layer (Lecture 1)|Topic map]]
> ← [[3114-04 MAC Sub-layer]] | [[3114-06 Transport Layer]] →

> [!tip]- Exam focus — how often each topic appeared (6 papers, 2018/19–2023/24)
> - **6/6** — Subnetting / IP allocation
> - **4/6** — Sink tree
> - **2/6** — Store-and-forward
> - **2/6** — Flooding
> - **2/6** — Connectionless tables / VC / label switching
> - **2/6** — Distance vector routing
> - **1/6** — Services NL → TL
> - **1/6** — Classful network counts
>
> **Repeats to expect**
> - A **subnetting** calculation appears in every paper.
> - Department network + 192.168.14.0/24 with a /26 given (2019/20, 2022/23).
> - Class C 192.168.1.0 split into 30/30/60/100 hosts (2021/22, 2023/24).
> - Router A–E routing tables / VC / label switching (2020/21, 2022/23).
> - Same A–O sink-tree graph (2020/21, 2023/24); distance vector for router J (the lecture's own example).

> [!question]- Past paper questions on this chapter (26)
> - **2018/19 Q5(b)** — Draw the sink tree for router A (hops)
> - **2018/19 Q5(d)** — Allocate 4000/2000/4000/8000 addresses from 198.16.0.0 (first, last, /s)
> - **2019/20 Q8(c)** — Subnet 192.168.14.0/24 for the other 3 departments (Physics given as /26)
> - **2020/21 Q3(d)** — How a message travels host to host through the routers
> - **2020/21 Q6(d)** — Sink tree for router A (hops)
> - **2020/21 Q7(a)** — Connectionless: how routers build routing tables; table for each router (A–E)
> - **2020/21 Q7(b)** — A–D link broken: routing table changes
> - **2020/21 Q7(c)** — Virtual circuit H1–H2: tables required
> - **2020/21 Q7(d)** — H3 also connects to H2 via B: label switching
> - **2020/21 Q8(a)** — Cambridge 2048 / Oxford 4096 / Edinburgh 1024 from 194.24.0.0/20: ranges and masks
> - **2020/21 Q8(b)(iv)** — Short note: distance vector routing
> - **2021/22 Q8(a)** — Store-and-forward packet switching
> - **2021/22 Q8(b)** — Flooding: limiting its negative effects
> - **2021/22 Q8(c)** — Sink tree for router C (hops)
> - **2021/22 Q8(d)** — Class C 192.168.1.0 split into 30, 30, 60, 100 hosts: masks and ranges
> - **2022/23 Q7(a)** — Services the NL provides to the transport layer
> - **2022/23 Q7(b)(i)** — Connectionless: routing tables for routers A–E
> - **2022/23 Q7(b)(ii)** — Virtual circuit H1–H2: tables
> - **2022/23 Q7(b)(iii)** — H3 to H2 via B: label switching
> - **2022/23 Q8(c)** — 192.168.14.0/24: IP specs for Accounts, Admin, IT (HR given as /26)
> - **2023/24 Q7(a)** — Store-and-forward packet switching
> - **2023/24 Q7(b)** — Define flooding; two techniques to control it
> - **2023/24 Q7(d)** — Sink tree for router B (hops)
> - **2023/24 Q8(a)** — Maximum number of class A, B and C network IDs
> - **2023/24 Q8(b)** — 192.168.1.0 split into 30/30/60/100: mask, network address, usable range, broadcast (100 given)
> - **2023/24 Q8(d)** — Distance vector: new delays and output lines for router J (vectors from A, I, H, K)

## Topic checklist

### Lecture 1
- [ ] **5.1.1** Network layer functionality (source-to-destination, multiple hops)
- [ ] **5.1.2** Design issues
  - (1) **Store-and-forward packet switching**
  - (2) **Services provided to the transport layer** (3 goals)
  - (3) **Connectionless** implementation (datagrams, IP)
  - (4) **Connection-oriented** implementation (virtual circuits, routing tables, connection identifiers, **label switching**, **MPLS**)
- [ ] **5.1.3** Comparison: virtual-circuit vs datagram networks
- [ ] **5.1.4** **Routing algorithms**: forwarding vs routing; desirable properties; non-adaptive (static) vs adaptive (dynamic)
- [ ] **5.1.5** **Optimality principle and sink tree** (hops as the metric)
- [ ] **5.1.6** Shortest path algorithm
- [ ] **5.1.7** **Flooding**: duplicate problem; damping by hop counter and by sequence numbers per source
- [ ] **5.1.8** **Distance vector routing** (table of best distance plus outgoing line; example of the new table for J)

### Lecture 2
- [ ] **5.2.1** **IPv4 datagram header**: Version, IHL, Type of service, Total length, Identification, DF, MF, Fragment offset, TTL, Protocol, Header checksum, Source/Destination address, Options
- [ ] **5.2.2** **IP addresses**: 32-bit, dotted decimal, network/host portion, prefix, /n notation, **subnet mask** (AND operation), advantage of prefixes for routing
- [ ] **5.2.3** **Classful addressing**: Class A (128 networks × 16M hosts), B (16,384 × 65,536), C (2M × 256); special IP addresses
- [ ] **5.2.4** **Subnets and subnetting**: class B split into 64 subnets; worked example (14.24.74.0/24 split into 120, 60 and 10 hosts, largest block first)

## Notes
*Work through the checklist above. Write each topic in your own words, then tick it off.*


## Key definitions
> [!note] Term
>

## Summary (in my own words)


## Open questions
- [ ]
