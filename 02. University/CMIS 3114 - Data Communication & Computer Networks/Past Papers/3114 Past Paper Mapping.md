---
type: past-paper
course: CMIS 3114
status: complete
tags: [cmis3114, past-paper, mapping]
aliases: [CMIS 3114 past paper mapping, CMIS 3114 topic map]
---
# CMIS 3114 · Past Paper Mapping and Question Pattern Analysis

> [!info] Evidence base: the six papers 2018/19 – 2023/24 (exams held 2021 – Feb 2026). Every sub-question was read from the original papers (three are scans) and mapped to a lesson note. Questions by year: [[3114 Papers by Year]] · Home: [[00. CMIS 3114 Course Overview]]
> This page refines your earlier analysis [[3114 Topic Map & Past Papers]] (same counts, with one correction: the 2023/24 bit-stuffing string has 19 digits).

## 1. Topic → question tree

```text
01 Introduction  (≈ 37% of sub-questions; Q1–Q3)
├── Network definition / reasons / uses ... 18/19 1a · 19/20 1a, 1d · 20/21 1a · 21/22 1a · 22/23 1a · 23/24 1a
├── Distributed systems ................... 18/19 1b · 20/21 1b · 21/22 1c(i) · 22/23 1c(i) · 23/24 1b
├── P2P vs client–server .................. 19/20 1b · 20/21 1c(ii) · 22/23 1b
├── Mobile computing ...................... 18/19 2d · 19/20 1c(i) · 20/21 1c(i), 2d · 21/22 1b · 23/24 1c(i)
├── IoT / smart home / smart cars ⚠ ....... 19/20 1c(ii) · 20/21 8b(iii) · 21/22 1c(ii) · 22/23 1c(ii) · 23/24 1c(ii)
├── Wireless / WLAN / Li-Fi / MANET ....... 18/19 2c · 20/21 2c · 22/23 2c ⚠, 2d ⚠
├── Q1(d) social/technical essay .......... 20/21 1d · 21/22 1d · 22/23 1d ⚠ · 23/24 1d ⚠
├── Classification (scale) ................ 19/20 2a · 21/22 2a · 23/24 2a
├── Classification (transmission tech.) ... 20/21 2a · 22/23 2a
├── Topologies ............................ 18/19 2b · 19/20 2b · 20/21 2b · 21/22 2b · 22/23 2b · 23/24 2b
├── Protocol definition / need ............ 18/19 2a · 19/20 3a · 20/21 3a · 21/22 3a · 22/23 3a · 23/24 3a
├── Layering reasons + disadvantage ....... 18/19 3a, 3b · 19/20 3b · 20/21 3a · 21/22 3b · 23/24 3b
├── Message through layers / phys–virtual . 18/19 3c · 20/21 3c, 3d · 21/22 3c, 3d
├── Design issues of layers ............... 20/21 3b
├── Connection-oriented client–server ..... 19/20 2d · 21/22 2d · 23/24 2d
├── TCP vs UDP ............................ 19/20 3c · 22/23 3b · 23/24 3c
└── OSI vs TCP/IP (+ layer identification)  19/20 3d · 22/23 3c · 23/24 3d
02 Physical Layer  (≈ 24%; Q4 + parts of Q2, Q3, Q5/Q6)
├── Media construction / wired media ...... 19/20 4a · 20/21 4a · 21/22 4a · 22/23 4a · 23/24 4a
├── Long-distance media ................... 20/21 4b
├── Satellites ............................ 22/23 4d
├── Telephone network vs Internet ......... 19/20 2c · 21/22 2c · 23/24 2c
├── Baseband/passband, ASK/FSK ............ 19/20 4b · 21/22 4b · 22/23 4b, 4c
├── NRZ / NRZI drawing .................... 19/20 4c · 21/22 4d
├── Nyquist / Shannon / QAM ............... 18/19 3d, 4a · 19/20 4d · 22/23 3d · 23/24 4c
├── Dog with tapes (bandwidth/delay) ⚠ .... 21/22 4c
├── Multiplexing (FDM/TDM/OFDM/WDM) ....... 19/20 5a · 20/21 4c · 21/22 6a · 22/23 6a · 23/24 4b
├── CDMA calculation ...................... 20/21 4d
├── Circuit vs packet switching ........... 19/20 5b · 21/22 6b
├── GSM architecture ...................... 19/20 5c · 20/21 8b(ii) · 21/22 6c
└── Handoff ............................... 19/20 5d · 21/22 6d
03 Data Link Layer  (≈ 15%; Q5 + Q6/Q7 parts)
├── DLL functions / services .............. 19/20 6a · 20/21 5a · 21/22 5a · 22/23 5a · 23/24 5a
├── Byte count ............................ 19/20 6b, 6c · 21/22 5b · 23/24 5c
├── Byte stuffing ......................... 18/19 5c · 20/21 5b · 22/23 5d
├── Bit stuffing .......................... 23/24 5b
├── Parity / burst errors ................. 18/19 5a · 20/21 5c · 22/23 5c
├── CRC ................................... 18/19 4b, 4c, 4d
├── Hamming distance / code ............... 20/21 5d · 21/22 5c, 5d · 23/24 5d
├── Sliding window ........................ 19/20 6d · 22/23 5b
└── Go-Back-N ............................. 20/21 6a · 23/24 7c
04 MAC Sublayer  (≈ 10%; Q6/Q7)
├── ALOHA vs CSMA ......................... 19/20 7a · 20/21 6b · 21/22 7a · 22/23 6b · 23/24 6a
├── CSMA/CD states ........................ 19/20 7b · 23/24 6b  (+ 21/22 7b collision detection)
├── Collision-free (bit-map, binary cd.) .. 19/20 7c · 21/22 7b · 22/23 6c, 6d
├── Hidden/exposed + MACA ................. 19/20 7d · 20/21 6c · 21/22 7c, 7d · 23/24 6c, 6d
└── WiFi vs WiMAX vs 4G ................... 20/21 8b(i)
05 Network Layer  (≈ 13%; Q7/Q8)
├── Store-and-forward ..................... 21/22 8a · 23/24 7a
├── Services to transport layer ........... 22/23 7a
├── Routing tables / VC / label switching . 20/21 7a–d · 22/23 7b(i)–(iii)
├── Sink tree ............................. 18/19 5b · 20/21 6d · 21/22 8c · 23/24 7d
├── Flooding .............................. 21/22 8b · 23/24 7b
├── Distance vector ....................... 20/21 8b(iv) · 23/24 8d
├── Classful network counts ............... 23/24 8a
├── Subnetting / IP allocation ............ 18/19 5d · 19/20 8c · 20/21 8a · 21/22 8d · 22/23 8c · 23/24 8b
└── Network design scenario ⚠ ............. 18/19 1c · 19/20 8a, 8b · 22/23 8a, 8b · 23/24 8c
06 Transport · 07 Application · 08 Security
└── No direct questions. TCP vs UDP is examined via Chapter 1; DNS/proxy/firewall only inside design questions; security only as the 2020/21 essay.
```
⚠ = not (fully) covered by the Chapter 1–5 slides.

## 2. Frequency and priority (papers out of 6)

**Priority rule (evidence only):** 🔴 **High** = in ≥ 4 of 6 papers, **or** 3 papers with identical wording including the latest (2023/24). 🟡 **Medium** = 2–3 papers. 🟢 **Low** = 0–1 papers (still examinable: it is in the lectures).

| Topic | 18/19 | 19/20 | 20/21 | 21/22 | 22/23 | 23/24 | Papers | Priority | Note |
|---|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|---|
| Protocol definition / need | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | 6 | 🔴 | [[01.10 Protocols and Layering]] |
| Topologies | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | 6 | 🔴 | [[01.08 Network Topologies]] |
| Define network / reasons / uses | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | 6 | 🔴 | [[01.01 Computer Networks]] |
| Subnetting / IP allocation | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | 6 | 🔴 | [[05.09 Subnetting]] |
| Framing (count / byte / bit stuffing) | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | 6 | 🔴 | [[03.02 Framing]] |
| Layering reasons + disadvantage | ✅ | ✅ | ✅ | ✅ | – | ✅ | 5 | 🔴 | [[01.10 Protocols and Layering]] |
| Distributed systems | ✅ | – | ✅ | ✅ | ✅ | ✅ | 5 | 🔴 | [[01.02 Distributed Systems]] |
| Mobile computing | ✅ | ✅ | ✅ | ✅ | – | ✅ | 5 | 🔴 | [[01.05 Mobile Computing and Wireless Networks]] |
| Classification (scale or technology) | – | ✅ | ✅ | ✅ | ✅ | ✅ | 5 | 🔴 | [[01.09 Network Classification by Scale]] |
| Q1(d) essay ⚠ | – | ✅ | ✅ | ✅ | ✅ | ✅ | 5 | 🔴 | [[01.06 Social Issues of Computer Networks]] |
| IoT / smart home / smart cars ⚠ | – | ✅ | ✅ | ✅ | ✅ | ✅ | 5 | 🔴 | [[01.20 IoT, Smart Homes and Emerging Topics]] |
| Multiplexing | – | ✅ | ✅ | ✅ | ✅ | ✅ | 5 | 🔴 | [[02.06 Multiplexing - FDM, OFDM, WDM and TDM]] |
| Media construction / wired vs wireless | – | ✅ | ✅ | ✅ | ✅ | ✅ | 5 | 🔴 | [[02.01 Guided Transmission Media]] |
| DLL functions / services | – | ✅ | ✅ | ✅ | ✅ | ✅ | 5 | 🔴 | [[03.01 Functions and Services of the Data Link Layer]] |
| Error detection/correction (parity, CRC, Hamming) | ✅ | – | ✅ | ✅ | ✅ | ✅ | 5 | 🔴 | [[03.07 Hamming Code]] |
| ALOHA vs CSMA | – | ✅ | ✅ | ✅ | ✅ | ✅ | 5 | 🔴 | [[04.03 CSMA and CSMA-CD]] |
| Nyquist / Shannon / QAM | ✅ | ✅ | – | – | ✅ | ✅ | 4 | 🔴 | [[02.08 Maximum Data Rate - Nyquist and Shannon]] |
| Hidden/exposed + MACA | – | ✅ | ✅ | ✅ | – | ✅ | 4 | 🔴 | [[04.05 Hidden and Exposed Terminals and MACA]] |
| Sink tree | ✅ | – | ✅ | ✅ | – | ✅ | 4 | 🔴 | [[05.03 Routing Algorithms and the Sink Tree]] |
| Network design scenario ⚠ | ✅ | ✅ | – | – | ✅ | ✅ | 4 | 🔴 | [[PP 05.4 - Network Design Scenarios]] |
| P2P vs client–server | – | ✅ | ✅ | – | ✅ | – | 3 | 🟡 | [[01.04 Client-Server and Peer-to-Peer Models]] |
| Connection-oriented client–server steps | – | ✅ | – | ✅ | – | ✅ | 3 | 🔴 (identical, incl. 23/24) | [[01.14 Service Primitives]] |
| Telephone network vs Internet | – | ✅ | – | ✅ | – | ✅ | 3 | 🔴 (identical, incl. 23/24) | [[02.04 Public Switched Telephone Network]] |
| TCP vs UDP | – | ✅ | – | – | ✅ | ✅ | 3 | 🔴 (incl. 23/24) | [[01.16 TCP-IP Reference Model]] |
| OSI vs TCP/IP | – | ✅ | – | – | ✅ | ✅ | 3 | 🔴 (incl. 23/24) | [[01.17 OSI vs TCP-IP and the Hybrid Model]] |
| Byte count | – | ✅ | – | ✅ | – | ✅ | 3 | 🔴 (identical, incl. 23/24) | [[03.02 Framing]] |
| Hamming distance / code | – | – | ✅ | ✅ | – | ✅ | 3 | 🔴 (16-bit message repeated) | [[03.07 Hamming Code]] |
| CSMA/CD states / collision detection | – | ✅ | – | ✅ | – | ✅ | 3 | 🔴 (incl. 23/24) | [[04.03 CSMA and CSMA-CD]] |
| Message through layers / phys–virtual | ✅ | – | ✅ | ✅ | – | – | 3 | 🟡 | [[01.11 Protocol Hierarchies and Encapsulation]] |
| Wireless/WLAN advantages | ✅ | – | ✅ | – | ✅ | – | 3 | 🟡 | [[01.05 Mobile Computing and Wireless Networks]] |
| Byte stuffing | ✅ | – | ✅ | – | ✅ | – | 3 | 🟡 | [[03.02 Framing]] |
| Parity / burst errors | ✅ | – | ✅ | – | ✅ | – | 3 | 🟡 | [[03.04 Error Detection - Parity, Interleaving and Checksum]] |
| Baseband vs passband | – | ✅ | – | ✅ | ✅ | – | 3 | 🟡 | [[02.05 Digital Modulation - Baseband and Passband]] |
| GSM architecture | – | ✅ | ✅ | ✅ | – | – | 3 | 🟡 | [[02.11 GSM Architecture]] |
| Collision-free protocols | – | ✅ | – | ✅ | ✅ | – | 3 | 🟡 | [[04.04 Collision-Free Protocols]] |
| Sliding window | – | ✅ | – | – | ✅ | – | 2 | 🟡 | [[03.09 Sliding Window and Go-Back-N]] |
| Go-Back-N | – | – | ✅ | – | – | ✅ | 2 | 🟡 | [[03.09 Sliding Window and Go-Back-N]] |
| NRZ / NRZI | – | ✅ | – | ✅ | – | – | 2 | 🟡 | [[02.05 Digital Modulation - Baseband and Passband]] |
| Circuit vs packet switching | – | ✅ | – | ✅ | – | – | 2 | 🟡 | [[02.09 Switching - Circuit, Message and Packet]] |
| Handoff | – | ✅ | – | ✅ | – | – | 2 | 🟡 | [[02.10 Mobile Telephone System and Handoff]] |
| Store-and-forward | – | – | – | ✅ | – | ✅ | 2 | 🟡 | [[05.01 Network Layer Design Issues]] |
| Flooding | – | – | – | ✅ | – | ✅ | 2 | 🟡 | [[05.05 Flooding]] |
| Routing tables / VC / label switching | – | – | ✅ | – | ✅ | – | 2 | 🟡 | [[05.02 Datagram and Virtual-Circuit Networks]] |
| Distance vector | – | – | ✅ | – | – | ✅ | 2 | 🟡 | [[05.06 Distance Vector Routing]] |
| WiFi/WiMAX/4G, Li-Fi, MANET ⚠ | – | – | ✅ | – | ✅ | – | 2 | 🟡 | [[04.07 Broadband Wireless - WiMAX, 4G and 5G]] |
| CRC | ✅ | – | – | – | – | – | 1 | 🟢 | [[03.05 Cyclic Redundancy Check (CRC)]] |
| CDMA | – | – | ✅ | – | – | – | 1 | 🟢 | [[02.07 Code Division Multiple Access (CDMA)]] |
| Satellites | – | – | – | – | ✅ | – | 1 | 🟢 | [[02.03 Communication Satellites]] |
| Bit stuffing | – | – | – | – | – | ✅ | 1 | 🟢 (part of 🔴 framing) | [[03.02 Framing]] |
| Dog/tapes ⚠ | – | – | – | ✅ | – | – | 1 | 🟢 | [[02.08 Maximum Data Rate - Nyquist and Shannon]] |
| Services NL → TL | – | – | – | – | ✅ | – | 1 | 🟢 | [[05.01 Network Layer Design Issues]] |
| Design issues of layers | – | – | ✅ | – | – | – | 1 | 🟢 | [[01.12 Design Issues for the Layers]] |
| Classful counts | – | – | – | – | – | ✅ | 1 | 🟢 | [[05.08 IP Addresses and Classful Addressing]] |
| Long-distance media | – | – | ✅ | – | – | – | 1 | 🟢 | [[02.02 Wireless Transmission]] |
| Never asked: IPv4 header, shortest path, checksum, elementary protocols, 802.11 services, example networks, standards, cable TV, PSTN politics | – | – | – | – | – | – | 0 | 🟢 | lesson notes |

## 3. Question-slot pattern (8-question papers)

| Slot | Usual chapter | Typical content |
|---|---|---|
| **Q1** | Ch1 §1.1 | Define/uses/reasons · distributed system or P2P vs C–S · two short notes (mobile computing + IoT/smart home/distributed) · Q1(d) essay |
| **Q2** | Ch1 §1.2 (+ Ch2 PSTN) | Classification (scale / technology) · two topologies · Telephone vs Internet **or** WLAN / Li-Fi / MANET · connection-oriented client–server steps |
| **Q3** | Ch1 §1.3–1.4 | Protocol · layering reasons · TCP vs UDP **or** message through layers · OSI vs TCP/IP **or** phys/virtual diagram · sometimes Nyquist/Shannon |
| **Q4** | Ch2 | Media construction · baseband/passband · multiplexing · NRZ/NRZI · Nyquist/Shannon with QAM · CDMA or satellites |
| **Q5** | Ch3 (Ch2 in 2019/20) | DLL functions/services · framing (count/byte/bit stuffing) · parity / Hamming · sliding window |
| **Q6** | Ch2/Ch3/Ch4 mix | FDM/TDM, switching, GSM, handoff **or** Go-Back-N, ALOHA/CSMA, hidden/exposed, sink tree **or** DLL framing |
| **Q7** | Ch4 or Ch5 | ALOHA/CSMA, CSMA/CD, collision-free, MACA **or** routing tables/VC/label switching, store-and-forward, flooding, Go-Back-N, sink tree |
| **Q8** | Ch5 + design | Subnetting (almost always) · design scenario · distance vector · sink tree · short notes |

## 4. Two alternating "families" of papers
The papers clearly **reuse questions in alternate years**:

| Family A: 2019/20 → 2021/22 → 2023/24 | Family B: 2018/19 → 2020/21 → 2022/23 |
|---|---|
| Telephone network vs Internet (all three) | Byte stuffing (all three) |
| Connection-oriented client–server steps (all three) | Parity for burst errors (all three) |
| Classify by **geographical coverage/scale** (all three) | Classify by **transmission technology** (20/21, 22/23) |
| Byte count technique (all three) | Routers A–E: tables, VC, label switching (20/21, 22/23) |
| Hidden/exposed + solution (all three) | Pure ALOHA comparisons (20/21, 22/23) |
| **Star** topology (all three) | Wireless/WLAN advantages (18/19, 20/21, 22/23) |
| Media construction: twisted pair + fibre (all three) | Layered approach (18/19, 20/21) |
| Slotted ALOHA comparisons (all three) | Sink tree for router A (18/19, 20/21) |
| 192.168.1.0 into 30/30/60/100 (21/22, 23/24) | 192.168.14.0/24 department design (19/20 & 22/23 cross over) |
| GSM + handoff, NRZ/NRZI, baseband (19/20, 21/22) | *(Nyquist/Shannon, sink trees, multiplexing and distributed systems appear in both families)* |

> [!warning] An observation, not a guarantee
> 2023/24 belonged to family A. If the alternation continues, the **next paper may lean towards family B** (byte stuffing, parity for burst errors, transmission-technology classification, router A–E tables/VC/label switching, Pure ALOHA comparisons, wireless/WLAN advantages and disadvantages). Topics in **every** paper (protocols, topologies, uses, subnetting, framing) must be prepared regardless.

## 5. Question styles by topic

| Topic | Common styles seen |
|---|---|
| Networks / uses | Define → list reasons → **applied scenario** (pandemic, economy, online business, healthcare) |
| Distributed / P2P | Define + **diagram** + advantages/disadvantages + example; **compare** |
| Topologies | **Diagram** + device + media + **usage**; compare two; choose for a scenario |
| Protocols / layering | Define; **reasons + one disadvantage**; layer-change effects; **process** (message through layers); **diagram** (phys/virtual) |
| Services | **Step-by-step process** (9 phases) |
| OSI / TCP/IP / TCP / UDP | **Compare with diagram**; identify layer for a task |
| Media | **Labelled construction diagram** + usage; advantages/disadvantages |
| Modulation | Define/compare; **draw waveforms** (NRZ/NRZI); efficiency |
| Data rate | **Numerical** (Nyquist, Shannon, dB, dog problem) |
| Multiplexing | **Compare** 2–3 techniques; **CDMA numerical** |
| Switching / PSTN / GSM | **Compare**; **identify components on a diagram**; **process** (call setup, handoff) |
| DLL | Describe 2 functions/services; **framing numericals**; parity/CRC/Hamming **numericals**; protocol **process diagrams** |
| MAC | **Compare** protocols with diagrams; **state diagram**; **algorithm trace** (binary countdown); problem + solution (hidden/exposed + MACA) |
| Network layer | **Process** (store-and-forward); **graph exercises** (sink tree, routing tables, DV table); **subnetting numericals**; **design** (diagram + justification) |

**Calculation-heavy:** Nyquist/Shannon, CDMA, framing, parity, CRC, Hamming, binary countdown, sink tree, routing/DV tables, subnetting, class counts.
**Theory-heavy:** Chapter 1 (definitions, essays, comparisons), media, multiplexing, switching, GSM, DLL services, MAC comparisons.
**Comparison questions:** P2P vs C–S, distributed vs C–S, star vs partial mesh, Telephone vs Internet, TCP vs UDP, OSI vs TCP/IP, baseband vs passband, FDM/TDM/OFDM/WDM, circuit vs packet, wired vs wireless, WiFi vs Li-Fi, WiFi vs WiMAX vs 4G, ALOHA vs CSMA variants, collision detection vs collision-free.

## 6. Past-paper notes by chapter
- **01 Introduction:** [[PP 01.1 - Networks, Uses and Distributed Systems]] · [[PP 01.2 - Mobile Computing, IoT and Wireless]] · [[PP 01.3 - Social and Technical Essays]] · [[PP 01.4 - Classification and Topologies]] · [[PP 01.5 - Protocols, Layering and Services]] · [[PP 01.6 - Reference Models, TCP and UDP]]
- **02 Physical Layer:** [[PP 02.1 - Transmission Media and Satellites]] · [[PP 02.2 - Modulation and Data Rate Calculations]] · [[PP 02.3 - Multiplexing and CDMA]] · [[PP 02.4 - Switching, Telephone and Mobile Networks]]
- **03 Data Link Layer:** [[PP 03.1 - DLL Functions and Framing]] · [[PP 03.2 - Error Detection and Correction]] · [[PP 03.3 - Sliding Window and Go-Back-N]]
- **04 MAC Sublayer:** [[PP 04.1 - ALOHA, CSMA and Collision-Free Protocols]] · [[PP 04.2 - Wireless LAN Protocols and Standards]]
- **05 Network Layer:** [[PP 05.1 - Store-and-Forward, Datagram and VC Networks]] · [[PP 05.2 - Routing - Sink Tree, Flooding and Distance Vector]] · [[PP 05.3 - IP Addressing and Subnetting]] · [[PP 05.4 - Network Design Scenarios]]
- **06–08:** no direct questions (see section 1).
