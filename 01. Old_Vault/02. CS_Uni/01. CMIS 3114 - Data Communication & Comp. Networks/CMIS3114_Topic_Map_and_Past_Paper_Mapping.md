# CMIS 3114 – Data Communication & Computer Networks
## Lecture Topic Map & Past Paper Question Mapping

**Sources analysed**

| Code | Lecture note file |
|---|---|
| **Ch1** | Chapter 01 – Introduction |
| **Ch2** | Chapter 2 – Physical Layer |
| **Ch3-L1** | Chapter 3 – Data Link Layer (L1) |
| **Ch3-L2** | Chapter 3 – Data Link Layer (L2) |
| **Ch4** | Chapter 4 – The Medium Access Control (MAC) Sublayer |
| **Ch5-L1** | Chapter 5 – Network Layer (L1) |
| **Ch5-L2** | Chapter 5 – Network Layer (L2) |

| Academic year | Exam held | Format |
|---|---|---|
| 2018/2019 | July 2021 (online) | 5 questions, answer **all** |
| 2019/2020 | June 2022 | 8 questions, answer 6 |
| 2020/2021 | June/July 2023 | 8 questions, answer 6 |
| 2021/2022 | January/February 2024 | 8 questions, answer 6 |
| 2022/2023 | February/March 2025 | 8 questions, answer 6 |
| 2023/2024 | February 2026 | 8 questions, answer 6 |

> **Note on coverage:** The Ch1 course outline lists 8 chapters (… 6. Transport Layer, 7. Application Layer, 8. Network Security). Notes were provided only for **Chapters 1–5**. A few exam sub-questions touch content outside these notes (IoT, Li-Fi, AI, DNS/proxy/firewall, security). These are flagged ⚠️ in the mapping.

---

# PART 1 — Topics & Subtopics by Lecture Note

Each subtopic has an ID (e.g. **1.3.4**) so Part 2 can point straight to it.

## Ch1 — Introduction

**Admin (slides 1–7):** Assessment (30% CA [mid-sem, tutorials, spot tests] + 70% final exam), course outline (8 chapters), reference: Tanenbaum, Feamster & Wetherall, *Computer Networks*, 6th ed.

### 1.1 Introduction
- **1.1.1** Definition of a computer network ("autonomous computers interconnected by a single technology"; 2+ independent computers able to exchange information)
- **1.1.2** Network vs Internet vs Web (Internet = network of networks; Web ≠ a network)
- **1.1.3** Distributed systems: independent computers appearing as one coherent system; middleware; WWW as an example running on top of the Internet
- **1.1.4** Evolution: mainframe → PCs/workstations → interconnected computers
- **1.1.5** Why network? Resource sharing (hardware, software, data), robustness/fault tolerance, load balancing, location independence
- **1.1.6** Problems: security (harder to protect distributed resources; the network itself as a target)
- **1.1.7** What a network does: reliable, fair, efficient communication; detects/corrects corruption, loss, duplication, out-of-order delivery; finds the optimal path
- **1.1.8** Uses: **Business applications**: resource sharing, communication (e-mail, videoconferencing), B2B, B2C, e-commerce, real-time ordering
- **1.1.9** **Client–server model**: servers and clients, request/reply between two processes
- **1.1.10** Uses: **Home applications**: remote information, person-to-person communication (e-mail, IM, social networks, wiki, VoIP), interactive entertainment (VoD, IPTV, games), e-commerce, online auctions / flea markets
- **1.1.11** **Peer-to-peer networks**: no fixed clients/servers, each host is both (e.g. BitTorrent); C2C e-commerce
- **1.1.12** **Mobile users & wireless networks / mobile computing**: notebooks, PDAs, UAVs, combining wireless and mobile computing, history of the mobile phone
- **1.1.13** **Social issues**: newsgroups, employee vs employer rights, government vs citizen, privacy, cookies, identity theft, copyright

### 1.2 Network Hardware
- **1.2.1** What a network includes: transmission hardware, special-purpose devices, protocol software; components (hosts, routers/switches/bridges, links)
- **1.2.2** Classification by **type of connection**: dedicated link, shared medium (multiple access), switched point-to-point
- **1.2.3** Classification by **transmission technology**: broadcast links vs point-to-point links; packets; addressing (unicast, broadcast, multicast)
- **1.2.4** **Topologies**: bus, star, ring, mesh (fully connected), mesh (partially connected), tree (hierarchical)
- **1.2.5** Classification by **scale**: BAN, PAN, LAN (Ethernet IEEE 802.3, Token Ring IEEE 802.5), MAN (cable TV based), WAN (hosts, subnet, transmission lines, routers, store-and-forward / packet-switched subnet, packetisation and reassembly, local routing decisions)
- **1.2.6** **Wireless networks**: signal types; system interconnection (Bluetooth), wireless LANs (802.11b/g/n WiFi), wireless WANs

### 1.3 Network Software
- **1.3.1** **Protocols**: definition (rules for format and meaning of messages); human vs computer protocol; protocol suite/hierarchy; philosopher–translator–secretary analogy
- **1.3.2** **Layering**: divide and conquer, why layers, analogies, reduces complexity, modularity, one layer changes without affecting others; no physical transfer across layers
- **1.3.3** **Protocol hierarchies**: peers, virtual vs physical communication, interfaces, services vs protocols, benefits of clean interfaces
- **1.3.4** **Message through the layers**: headers added at each layer, layer 3 splits M into M1/M2, layer 2 header + trailer, headers stripped at the receiver; sender vs receiver actions
- **1.3.5** Network architecture & protocol stack
- **1.3.6** **Design issues for the layers**: addressing, error control, QoS, flow control, multiplexing, routing, message size (disassembly and reassembly)
- **1.3.7** **Connection-oriented vs connectionless services**: phone vs post office analogy; negotiation; reliable message sequence vs byte stream; datagram, acknowledged datagram, request–reply
- **1.3.8** **Service primitives**: LISTEN, CONNECT, ACCEPT, RECEIVE, SEND, DISCONNECT; phases 1–9 of a client–server connection-oriented exchange
- **1.3.9** Relationship of services to protocols
- **1.3.10** **Layering advantages/disadvantages**: easier design, modularity, flexibility vs performance overhead

### 1.4 Reference Models
- **1.4.1** **ISO OSI model**: principles behind the layering; the 7 layers (Physical, Data Link, Network, Transport, Session, Presentation, Application) and their functions; summary
- **1.4.2** **TCP/IP model**: ARPANET/DoD history and goals; Application (HTTP, FTP, Telnet, SMTP, DNS), Transport (**TCP**: reliable, connection-oriented byte stream; **UDP**: unreliable, connectionless), Internet (IP, best effort), Host-to-Network
- **1.4.3** **OSI vs TCP/IP comparison**: 7 vs 4 layers; service/interface/protocol distinction; model-first vs protocols-first; connection modes per layer
- **1.4.4** Why OSI failed (bad timing, technology, implementation, politics); critique of TCP/IP
- **1.4.5** **Hybrid 5-layer model** (Application, Transport, Network, Link, Physical)

### 1.5 Example Networks
- **1.5.1** ARPANET (IMPs, 56 kbps, datagram subnet, growth), NSFNET
- **1.5.2** The Internet: definition, **architecture** (POP, CMTS, ISP regional network, backbone, NAP), history, growth, traditional applications
- **1.5.3** Ethernet (original architecture)
- **1.5.4** Wireless LANs 802.11: with a base station vs ad hoc; design challenges; multicell network

### 1.6 Network Standardization
- **1.6.1** Standards bodies: ISO, ANSI, NIST, IEEE, IAB
- **1.6.2** IEEE 802 standards: 802.3, 802.11, 802.15, 802.16

---

## Ch2 — The Physical Layer

- **2.1 Guided transmission media**
  - 2.1.1 Magnetic media
  - 2.1.2 Twisted pair
  - 2.1.3 Coaxial cable
  - 2.1.4 Fiber optics: total internal reflection, attenuation, fiber cable structure, LED vs semiconductor laser, fiber rings with active repeaters, global and Sri Lankan fiber / undersea cabling
- **2.2 Wireless transmission**
  - 2.2.1 Electromagnetic spectrum
  - 2.2.2 Radio transmission (VLF/LF/MF follow the earth's curvature; HF bounces off the ionosphere)
  - 2.2.3 Politics of the spectrum: ISM bands, TRC Sri Lanka
  - 2.2.4 Microwave, infrared & millimeter waves, lightwave (laser) transmission
  - 2.2.5 Google Loon balloons
- **2.3 Communication satellites**
  - 2.3.1 GEO (Clarke's 35,800 km prediction), properties (altitude, delay, number needed), satellite bands
  - 2.3.2 VSATs with a hub
  - 2.3.3 MEO (GPS, ~20,200 km)
  - 2.3.4 LEO: Iridium and Globalstar
  - 2.3.5 Satellites vs fiber
- **2.4 Public Switched Telephone Network (PSTN)**
  - 2.4.1 Structure (fully interconnected, centralized switch, two-level hierarchy); circuit route
  - 2.4.2 Major components: local loops, trunks, switching offices
  - 2.4.3 Politics: LATA, LEC, IXC, POP
  - 2.4.4 Local loop: modems, codecs, ADSL / DSL (discrete multitone), wireless local loops (LMDS)
- **2.5 Transmission of digital information (digital modulation)**
  - 2.5.1 **Baseband transmission**: NRZ, NRZI (USB)
  - 2.5.2 **Passband transmission**: ASK, FSK, PSK/BPSK, QPSK, QAM-16, QAM-64; modem constellations
- **2.6 Multiplexing**
  - 2.6.1 **FDM**: guard bands, AM radio, voice channels (3100/4000 Hz)
  - 2.6.2 **OFDM**: no guard bands, orthogonal subcarriers (WLAN, powerline, 4G)
  - 2.6.3 **WDM**
  - 2.6.4 **TDM**: guard time, T1 carrier (24 × 64 kbps = 1.544 Mbps), higher carriers
  - 2.6.5 **CDM/CDMA**: airport analogy, chips, chip sequences (bipolar ±1), 1 → sequence / 0 → negation, pairwise orthogonality, recovery with the normalised inner product S·C
- **2.7 Theoretical basis for data communication**
  - 2.7.1 Fourier analysis
  - 2.7.2 Bandwidth-limited signals, definition of bandwidth
  - 2.7.3 **Nyquist** (noiseless): max data rate = 2B log₂V; examples with BPSK/QPSK/QAM
  - 2.7.4 **SNR & decibels** (10 dB = 10, 20 dB = 100, 30 dB = 1000)
  - 2.7.5 **Shannon** (noisy): C = B log₂(1 + S/N); worked examples
- **2.8 Switching**
  - 2.8.1 Circuit switching (Strowger gear, modern exchanges)
  - 2.8.2 Packet switching (store-and-forward, out-of-order delivery, packet size limit)
  - 2.8.3 Message switching
  - 2.8.4 Circuit vs packet comparison table
- **2.9 Mobile telephone system**
  - 2.9.1 Generations 1G–5G overview
  - 2.9.2 1G IMTS; AMPS (cells, frequency reuse, smaller cells, MSC/MTSO)
  - 2.9.3 **Handoff** (fading signal, base station polls neighbours, ~300 ms)
  - 2.9.4 AMPS channel categories (control, paging, access, data)
  - 2.9.5 Analog → digital benefits; 2G D-AMPS (TDM)
  - 2.9.6 **GSM**: SIM card, architecture (BSC, MSC, VLR, HLR, PSTN), cell management, 124 channels × 8-slot TDM, framing
  - 2.9.7 2.5G EDGE/GPRS; 3G IMT-2000 (WCDMA/UMTS, CDMA2000); 4G LTE and WiMAX (802.16)
- **2.10 Cable television**
  - 2.10.1 CATV, HFC (Hybrid Fiber Coax), fiber nodes
  - 2.10.2 Internet over cable (shared medium vs ADSL)

---

## Ch3-L1 — Data Link Layer (Lecture 1)

- **3.1.1** Functions of the DLL: service interface to the network layer, handling transmission errors, regulating data flow
- **3.1.2** Services to the network layer: virtual vs actual communication
  - Unacknowledged connectionless (Ethernet)
  - Acknowledged connectionless (802.11 WiFi)
  - Acknowledged connection-oriented (3 phases: satellite and long-distance links)
- **3.1.3** Framing: purpose, checksum per frame
  - (1) **Byte count** (cannot resynchronise after an error)
  - (2) **Flag bytes with byte stuffing** (FLAG, ESC, stuffing examples)
  - (3) **Flag bits with bit stuffing** (01111110, stuff a 0 after five 1s, USB)
  - (4) **Physical layer coding violations**; preamble plus length field (Ethernet/802.11)
- **3.1.4** Error control: ACKs, timers, sequence numbers, exactly-once delivery
- **3.1.5** Flow control: feedback-based vs rate-based

## Ch3-L2 — Data Link Layer (Lecture 2)

- **3.2.1** Types of transmission errors: single-bit vs burst
- **3.2.2** Error detection vs error correction (FEC) strategies
- **3.2.3** Error detection codes
  - **Parity** (even/odd; rows of parity)
  - **Interleaving** (parity computed in a different order, used for burst errors)
  - **Checksum** (16-bit Internet checksum worked example)
  - **CRC / polynomial code** (generator rules, modulo-2 division, G(x) = x⁴ + x + 1 example)
- **3.2.4** Codewords: (n, m) codeword, code rate m/n
- **3.2.5** **Hamming distance**: XOR and count; distance of a code; sparseness of codewords; detect d errors needs distance d + 1, correct d errors needs 2d + 1; four-codeword example
- **3.2.6** **Hamming code**: check bits at powers of 2, (11,7) example, computing check bits (even parity), error syndrome, correcting the error; Hamming codes for burst errors
- **3.2.7** Data link protocols: NIC, device driver, network accelerator, software-defined radio
- **3.2.8** Elementary protocols: unrestricted simplex, simplex stop-and-wait, simplex for a noisy channel
- **3.2.9** **Sliding window protocol** (window size 1, 3-bit sequence number, states a–d)
- **3.2.10** **Go-Back-N** (pipelining and error recovery; receiver window of 1 vs large, i.e. selective repeat)

---

## Ch4 — The MAC Sublayer

- **4.1** DLL = LLC + MAC; MAC address (IEEE 802.3); broadcast / multi-access channels
- **4.2** **Channel allocation problem**: static (TDM/FDM/WDM) vs dynamic, with 5 assumptions (independent traffic, single channel, observable collisions, continuous/slotted time, carrier sense or not)
- **4.3** **ALOHA**: origin, contention systems, **Pure ALOHA**, **Slotted ALOHA**
- **4.4** **CSMA**: 1-persistent, non-persistent, p-persistent; **CSMA/CD** (contention / transmission / idle states); utilisation vs load comparison
- **4.5** **Collision-free protocols**: why collisions are bad; basic **bit-map** protocol; **token passing** (token ring/bus); **binary countdown**
- **4.6** **Wireless LAN protocols**: **hidden terminal** and **exposed terminal** problems; **MACA** (RTS/CTS)
- **4.7** **IEEE 802.11 (WiFi)**
  - Infrastructure vs ad hoc mode
  - Protocol stack: 802.11b (DSSS, 2.4 GHz, 11 Mbps), 802.11a (OFDM, 5 GHz, 54 Mbps), 802.11g, 802.11n (MIMO, 600 Mbps)
  - Frame structure (data, control, management)
  - Services: association, reassociation, disassociation, authentication, WEP/WPA/WPA2 (AES), distribution, integration, data delivery, privacy, QoS scheduling, transmit power control, dynamic frequency selection
- **4.8** **Broadband wireless**: 802.16 WiMAX (OFDM, 2–10 GHz, non-line-of-sight); compare WiFi, 4G LTE and 5G

---

## Ch5-L1 — Network Layer (Lecture 1)

- **5.1.1** Network layer functionality (source-to-destination, multiple hops)
- **5.1.2** Design issues
  - (1) **Store-and-forward packet switching**
  - (2) **Services provided to the transport layer** (3 goals)
  - (3) **Connectionless** implementation (datagrams, IP)
  - (4) **Connection-oriented** implementation (virtual circuits, routing tables, connection identifiers, **label switching**, **MPLS**)
- **5.1.3** Comparison: virtual-circuit vs datagram networks
- **5.1.4** **Routing algorithms**: forwarding vs routing; desirable properties; non-adaptive (static) vs adaptive (dynamic)
- **5.1.5** **Optimality principle and sink tree** (hops as the metric)
- **5.1.6** Shortest path algorithm
- **5.1.7** **Flooding**: duplicate problem; damping by hop counter and by sequence numbers per source
- **5.1.8** **Distance vector routing** (table of best distance plus outgoing line; example of the new table for J)

## Ch5-L2 — Network Layer (Lecture 2)

- **5.2.1** **IPv4 datagram header**: Version, IHL, Type of service, Total length, Identification, DF, MF, Fragment offset, TTL, Protocol, Header checksum, Source/Destination address, Options
- **5.2.2** **IP addresses**: 32-bit, dotted decimal, network/host portion, prefix, /n notation, **subnet mask** (AND operation), advantage of prefixes for routing
- **5.2.3** **Classful addressing**: Class A (128 networks × 16M hosts), B (16,384 × 65,536), C (2M × 256); special IP addresses
- **5.2.4** **Subnets and subnetting**: class B split into 64 subnets; worked example (14.24.74.0/24 split into 120, 60 and 10 hosts, largest block first)

---

# PART 2 — Past Paper Question Mapping

Legend: ⚠️ = not directly covered in the provided notes (applied, discussion or out-of-scope); the closest related note is still given.

## 2018/2019 (Exam: July 2021, online, 5 questions, answer all)

| Q | Question (summary) | Topic → Subtopic | Lecture note(s) |
|---|---|---|---|
| 1(a) | How networks support daily life in a pandemic | Uses of networks → business/home apps, person-to-person communication, e-commerce (applied) | Ch1 §1.1.8, 1.1.10 |
| 1(b) | Distributed systems vs client–server systems | Introduction → distributed systems; client–server model | Ch1 §1.1.3, 1.1.9 |
| 1(c) | Design a secure network diagram for 4 faculties (FoM 6 km away), FAS departments and a CMIS lab; media with justification | Network hardware → LAN/MAN/WAN by scale, topologies; guided media (fiber for 6 km, UTP inside buildings) ⚠️ security devices | Ch1 §1.2.4, 1.2.5; Ch2 §2.1 |
| 2(a) | Role of protocols | Network software → protocols | Ch1 §1.3.1 |
| 2(b)(i) | Topology for a backbone connecting major cities | Topologies → mesh (partial/full) | Ch1 §1.2.4 |
| 2(b)(ii) | Topology for a lab with 100 computers | Topologies → star (or tree) | Ch1 §1.2.4 |
| 2(c) | Advantages/disadvantages of wireless networks | Wireless networks; WLANs and their challenges | Ch1 §1.2.6, 1.5.4 |
| 2(d) | Mobile computing for emergency healthcare | Uses → mobile users and wireless (BAN) | Ch1 §1.1.12, 1.2.5 (BAN) |
| 3(a) | Why protocols use a layered approach | Layering → reasons and advantages | Ch1 §1.3.2, 1.3.10 |
| 3(b)(i) | Effect on layers k−1 and k+1 when layer k's **algorithms** change | Layering → independence of layers; services vs implementation | Ch1 §1.3.2, 1.3.3, 1.3.9 |
| 3(b)(ii) | Effect when the **service** of layer k changes | Protocol hierarchies → interfaces and services | Ch1 §1.3.3, 1.3.9 |
| 3(c) | Message travelling through a 5-layer protocol, with assumptions | Message through the layers (headers, M1/M2, trailer) | Ch1 §1.3.4 |
| 3(d)(i)* | 6 MHz TV channel, 6-level signals, noiseless: bits/s | Theoretical basis → Nyquist | Ch2 §2.7.3 |
| 3(d)(ii)* | Same channel at 30 dB SNR: max rate | Theoretical basis → SNR/dB, Shannon | Ch2 §2.7.4, 2.7.5 |
| 4(a)(I) | 4 MHz cable channel, max rate with QAM-16 | Passband → QAM-16 plus Nyquist | Ch2 §2.5.2, 2.7.3; (cable TV §2.10) |
| 4(a)(II) | Same with QAM-64 | Passband → QAM-64 plus Nyquist | Ch2 §2.5.2, 2.7.3 |
| 4(b) | CRC: bit stream 10011001, G(x) = x³ + x + 1, transmitted string | Error detection → CRC (modulo-2 division) | Ch3-L2 §3.2.3 |
| 4(c) | 4th bit inverted: show it is detected | Error detection → CRC receiver check | Ch3-L2 §3.2.3 |
| 4(d) | Example of an undetected error pattern | CRC → limits (errors divisible by G(x)) | Ch3-L2 §3.2.3 |
| 5(a) | Single-bit parity to detect burst errors; max burst length | Error detection → parity and interleaving | Ch3-L2 §3.2.3 |
| 5(b) | Draw the sink tree for router A (hops) | Routing → optimality principle, sink tree | Ch5-L1 §5.1.5 |
| 5(c) | Byte stuffing of `A B ESC C ESC FLAG FLAG D` | Framing → flag bytes with byte stuffing | Ch3-L1 §3.1.3 |
| 5(d) | Allocate 4000/2000/4000/8000 addresses from 198.16.0.0 (first, last, /s) | IP addressing → prefixes, subnetting/CIDR | Ch5-L2 §5.2.2, 5.2.4 |

*Printed as "iii" and "iv" in the paper.

## 2019/2020 (Exam: June 2022, 8 questions, answer 6)

| Q | Question (summary) | Topic → Subtopic | Lecture note(s) |
|---|---|---|---|
| 1(a) | Define a computer network; how it is part of daily life | Introduction → definition; uses (business/home) | Ch1 §1.1.1, 1.1.8, 1.1.10 |
| 1(b) | Peer-to-peer vs client–server | Client–server model; P2P networks | Ch1 §1.1.9, 1.1.11 |
| 1(c)(i) | Short note: mobile computing (benefits and issues) | Mobile users and wireless networks | Ch1 §1.1.12, 1.2.6 |
| 1(c)(ii) | Short note: Internet of Things | ⚠️ Not explicitly in notes. Closest: uses of networks, wireless/BAN/PAN | Ch1 §1.1.12, 1.2.5 |
| 1(d) | 3 ways networks can help restore Sri Lanka's economy | Uses → business apps, e-commerce (applied essay) | Ch1 §1.1.8, 1.1.10 |
| 2(a) | Classify networks by geographical coverage | Classification by scale → BAN, PAN, LAN, MAN, WAN | Ch1 §1.2.5 |
| 2(b)(i) | Star topology (compare) | Topologies → star | Ch1 §1.2.4 |
| 2(b)(ii) | Partial mesh topology (compare) | Topologies → partial mesh | Ch1 §1.2.4 |
| 2(c) | Telephone network vs Internet | PSTN structure and circuit switching vs Internet architecture and packet switching | Ch2 §2.4, 2.8; Ch1 §1.5.2 |
| 2(d) | Step-by-step connection-oriented client–server interaction | Service primitives (LISTEN … DISCONNECT, phases 1–9) | Ch1 §1.3.7, 1.3.8 |
| 3(a) | What a protocol is and why it is needed | Protocols | Ch1 §1.3.1 |
| 3(b) | Two reasons for layering; one disadvantage | Layering advantages/disadvantages | Ch1 §1.3.2, 1.3.10 |
| 3(c) | Compare TCP and UDP | TCP/IP model → transport layer (TCP, UDP) | Ch1 §1.4.2 |
| 3(d) | OSI vs TCP/IP with a correspondence diagram | Reference models → comparison | Ch1 §1.4.1–1.4.3 |
| 4(a)(i) | Physical construction of twisted pair (diagram) | Guided media → twisted pair | Ch2 §2.1.2 |
| 4(a)(ii) | Physical construction of optical fiber (diagram) | Guided media → fiber optics | Ch2 §2.1.4 |
| 4(b) | Baseband vs passband, with examples | Digital modulation → baseband and passband | Ch2 §2.5.1, 2.5.2 |
| 4(c) | NRZ and NRZI diagrams for 10111011000 | Baseband → NRZ, NRZI | Ch2 §2.5.1 |
| 4(d)(i) | 8 kHz noiseless channel, QAM-16: max rate | Nyquist with QAM-16 (V = 16) | Ch2 §2.7.3, 2.5.2 |
| 4(d)(ii) | Same channel at 30 dB SNR | Shannon, SNR/dB | Ch2 §2.7.4, 2.7.5 |
| 5(a) | Compare FDM, TDM and OFDM | Multiplexing → FDM, TDM, OFDM | Ch2 §2.6.1, 2.6.2, 2.6.4 |
| 5(b) | Circuit vs packet switching | Switching → comparison | Ch2 §2.8 |
| 5(c) | Identify GSM components (BSC, MSC, HLR, VLR, PSTN); landline-to-mobile call | Mobile telephone → GSM architecture and cell management | Ch2 §2.9.6 |
| 5(d) | Handoff procedure | Mobile telephone → handoff | Ch2 §2.9.3 |
| 6(a) | Two main functions of the DLL | DLL functions/services | Ch3-L1 §3.1.1, 3.1.2 |
| 6(b) | Byte count framing for ABCD / EFG / HIJKLM | Framing → byte count | Ch3-L1 §3.1.3 |
| 6(c) | Can byte count recover from an error? | Byte count → resynchronisation problem | Ch3-L1 §3.1.3 |
| 6(d) | Sliding window from the initial state to ACK receipt, with sequence numbers | Sliding window protocol (size 1, 3-bit sequence) | Ch3-L2 §3.2.9 |
| 7(a) | Slotted ALOHA vs p-persistent CSMA | MAC → ALOHA and CSMA | Ch4 §4.3, 4.4 |
| 7(b) | CSMA/CD states (transmission/contention/idle) diagram | MAC → CSMA/CD | Ch4 §4.4 |
| 7(c) | Binary countdown with codes 0001, 0101, 1000, 1001 | Collision-free → binary countdown | Ch4 §4.5 |
| 7(d) | Hidden and exposed terminals; solution protocol | WLAN protocols → hidden/exposed, MACA | Ch4 §4.6 |
| 8(a) | Most suitable topology for a faculty network (Physics, Chemistry, Maths, ICT) | Topologies (star / tree) applied | Ch1 §1.2.4 |
| 8(b) | Draw the network diagram (LANs, DNS, proxy, firewall, fiber ISP) | Network hardware and LANs; fiber media ⚠️ DNS/proxy/firewall are outside Ch1–5 | Ch1 §1.2.1, 1.2.5; Ch2 §2.1.4 |
| 8(c) | Subnet 192.168.14.0/24 for the other 3 departments (Physics given as /26) | IP addressing → subnet mask, subnetting | Ch5-L2 §5.2.2, 5.2.4 |

## 2020/2021 (Exam: June/July 2023, 8 questions, answer 6)

| Q | Question (summary) | Topic → Subtopic | Lecture note(s) |
|---|---|---|---|
| 1(a) | Define a network; advantages and disadvantages | Definition; why network; problems | Ch1 §1.1.1, 1.1.5, 1.1.6 |
| 1(b) | Distributed system with advantages, disadvantages and an example | Distributed systems | Ch1 §1.1.3 |
| 1(c)(i) | Short note: mobile computing | Mobile users and wireless | Ch1 §1.1.12 |
| 1(c)(ii) | Short note: peer-to-peer networks | P2P networks | Ch1 §1.1.11 |
| 1(d) | "In this information age, the security of data is critical" | Problems (security); social issues (privacy, cookies, identity theft); WLAN security (WEP/WPA2) ⚠️ Ch8 not provided | Ch1 §1.1.6, 1.1.13; Ch4 §4.7 |
| 2(a) | Classify networks by transmission technology | Broadcast vs point-to-point; unicast/broadcast/multicast | Ch1 §1.2.3 |
| 2(b) | Three topologies with diagrams and usages | Topologies | Ch1 §1.2.4 |
| 2(c) | Advantages/disadvantages of wireless LAN | Wireless LANs (802.11), challenges | Ch1 §1.2.6, 1.5.4; Ch4 §4.7 |
| 2(d) | Mobile computing supporting an online business | Mobile users; e-commerce | Ch1 §1.1.8, 1.1.12 |
| 3(a) | Define a protocol; why layered | Protocols; layering | Ch1 §1.3.1, 1.3.2 |
| 3(b) | Four design issues of protocol layers | Design issues for the layers | Ch1 §1.3.6 |
| 3(c) | Diagram of physical and virtual communication: 2 hosts + 2 routers on the ISO model | Protocol hierarchies (peers, virtual/physical); OSI (routers use layers 1–3) | Ch1 §1.3.3, 1.4.1 |
| 3(d) | How a message travels host to host through the routers | Message through the layers; store-and-forward | Ch1 §1.3.4, 1.2.5 (WAN); Ch5-L1 §5.1.2 |
| 4(a) | Three wired media with advantages/disadvantages | Guided media: twisted pair, coax, fiber | Ch2 §2.1.2–2.1.4 |
| 4(b) | Long-distance options: microwave, optical fiber, radio (advantages/disadvantages) | Fiber; wireless transmission (radio, microwave) | Ch2 §2.1.4, 2.2.2, 2.2.4 |
| 4(c) | Compare TDM, WDM and OFDM | Multiplexing → TDM, WDM, OFDM | Ch2 §2.6.2–2.6.4 |
| 4(d)(i) | CDMA: bipolar chip sequences for A–D | CDMA → bipolar chip sequences | Ch2 §2.6.5 |
| 4(d)(ii) | CDMA: bipolar transmission sequence for bits 1 1 0 1 | CDMA → sum of transmitted sequences | Ch2 §2.6.5 |
| 4(d)(iii) | How a 5th station listens to tower C | CDMA → normalised inner product recovery | Ch2 §2.6.5 |
| 5(a) | Two DLL services to the network layer | Services to NL (unack. connectionless, ack. connectionless, ack. connection-oriented) | Ch3-L1 §3.1.2 |
| 5(b) | Byte stuffing with FLAG and ESC | Framing → byte stuffing | Ch3-L1 §3.1.3 |
| 5(c) | Parity for burst errors; max detectable burst length | Parity and interleaving | Ch3-L2 §3.2.3 |
| 5(d) | 16-bit message Hamming code: number of check bits; codeword for 1101001100110101 (even parity) | Hamming code → check bits, calculation | Ch3-L2 §3.2.5, 3.2.6 |
| 6(a) | Go-Back-N with window 1; what if the receiver window is large? | Go-Back-N / sliding window | Ch3-L2 §3.2.9, 3.2.10 |
| 6(b) | Pure ALOHA vs 1-persistent CSMA | ALOHA; CSMA | Ch4 §4.3, 4.4 |
| 6(c) | Hidden and exposed terminal problems; solution | WLAN protocols → MACA | Ch4 §4.6 |
| 6(d) | Sink tree for router A (hops) | Optimality principle, sink tree | Ch5-L1 §5.1.5 |
| 7(a) | Connectionless: how routers build routing tables; table for each router (A–E) | Connectionless service (datagram); routing algorithms | Ch5-L1 §5.1.2(3), 5.1.4 |
| 7(b) | A–D link broken: routing table changes | Adaptive routing | Ch5-L1 §5.1.4 |
| 7(c) | Virtual circuit H1–H2: tables required | Connection-oriented service → VC tables | Ch5-L1 §5.1.2(4) |
| 7(d) | H3 also connects to H2 via B: label switching | Label switching / MPLS | Ch5-L1 §5.1.2(4) |
| 8(a) | Cambridge 2048 / Oxford 4096 / Edinburgh 1024 from 194.24.0.0/20: ranges and masks | IP addressing → subnetting | Ch5-L2 §5.2.2, 5.2.4 |
| 8(b)(i) | Short note: WiFi vs WiMAX vs 4G | 802.11; 802.16 WiMAX; 4G LTE | Ch4 §4.7, 4.8; Ch2 §2.9.7 |
| 8(b)(ii) | Short note: GSM mobile architecture | GSM | Ch2 §2.9.6 |
| 8(b)(iii) | Short note: IoT techniques for smart cars | ⚠️ Not in notes (closest: mobile/wireless networks) | Ch1 §1.1.12, 1.2.6 |
| 8(b)(iv) | Short note: distance vector routing | Distance vector routing | Ch5-L1 §5.1.8 |

## 2021/2022 (Exam: January/February 2024, 8 questions, answer 6)

| Q | Question (summary) | Topic → Subtopic | Lecture note(s) |
|---|---|---|---|
| 1(a) | Define a computer network; 4 reasons networks are required | Definition; why network | Ch1 §1.1.1, 1.1.5 |
| 1(b) | Mobile computing with a usage scenario | Mobile users and wireless | Ch1 §1.1.12 |
| 1(c)(i) | Short note: distributed system | Distributed systems | Ch1 §1.1.3 |
| 1(c)(ii) | Short note: Internet of Things | ⚠️ Not in notes | Ch1 §1.1.12, 1.2.5 |
| 1(d) | "Misinformation distribution must be controlled": social aspects | Social issues (newsgroups, rights, censorship, privacy) | Ch1 §1.1.13 |
| 2(a) | Classify networks by geographical coverage | Classification by scale | Ch1 §1.2.5 |
| 2(b)(i) | Star topology with usage | Topologies → star | Ch1 §1.2.4 |
| 2(b)(ii) | Partial mesh topology with usage | Topologies → partial mesh | Ch1 §1.2.4 |
| 2(c) | Telephone network vs Internet | PSTN and circuit switching vs Internet and packet switching | Ch2 §2.4, 2.8; Ch1 §1.5.2 |
| 2(d) | Step-by-step connection-oriented client–server interaction | Service primitives | Ch1 §1.3.7, 1.3.8 |
| 3(a) | Define a protocol; why needed | Protocols | Ch1 §1.3.1 |
| 3(b) | Reasons for layering; one disadvantage | Layering advantages/disadvantages | Ch1 §1.3.2, 1.3.10 |
| 3(c) | Message through 5 layers (diagram and assumptions) | Message through the layers | Ch1 §1.3.4 |
| 3(d) | Addressing at layer 2; 2 hosts + 2 routers: physical/virtual communication diagram | Protocol hierarchies (peers); design issue: addressing | Ch1 §1.3.3, 1.3.4, 1.3.6 |
| 4(a)(i) | Twisted pair construction | Guided media → twisted pair | Ch2 §2.1.2 |
| 4(a)(ii) | Coaxial cable construction | Guided media → coax | Ch2 §2.1.3 |
| 4(a)(iii) | Optical fiber construction | Guided media → fiber | Ch2 §2.1.4 |
| 4(b) | Baseband vs passband; techniques in each | Digital modulation (NRZ/NRZI; ASK/FSK/PSK/QAM) | Ch2 §2.5 |
| 4(c) | Dog carrying 3 × 10 GB tapes at 18 km/h vs a 150 Mbps line; (i) speed doubled, (ii) capacity doubled, (iii) line rate doubled | Data rate / transmission (Tanenbaum Ch2 problem) ⚠️ not worked in notes; related to magnetic media | Ch2 §2.1.1 (magnetic media) |
| 4(d) | NRZ and NRZI for 11000110111 | Baseband → NRZ, NRZI | Ch2 §2.5.1 |
| 5(a) | Two DLL services to the network layer | Services to NL | Ch3-L1 §3.1.2 |
| 5(b) | Byte count with an example; disadvantage; a better method | Framing → byte count; byte/bit stuffing as the fix | Ch3-L1 §3.1.3 |
| 5(c) | Hamming distance with an example | Hamming distance | Ch3-L2 §3.2.5 |
| 5(d) | Show codewords 00→00000, 01→01011, 10→10101, 11→11110 correct 1-bit errors | Hamming distance → 2d + 1 rule | Ch3-L2 §3.2.5 |
| 6(a) | Compare FDM and TDM | Multiplexing → FDM, TDM | Ch2 §2.6.1, 2.6.4 |
| 6(b) | Circuit vs packet switching with example usages | Switching | Ch2 §2.8 |
| 6(c) | GSM components (BSC, MSC, HLR, VLR, PSTN) and their functions | GSM architecture | Ch2 §2.9.6 |
| 6(d) | Handoff procedure | Handoff | Ch2 §2.9.3 |
| 7(a) | Slotted ALOHA vs p-persistent CSMA | ALOHA; CSMA | Ch4 §4.3, 4.4 |
| 7(b) | Collision detection vs collision-free protocols (advantages/disadvantages) | CSMA/CD; bit-map, token passing, binary countdown | Ch4 §4.4, 4.5 |
| 7(c) | Hidden and exposed terminal problems | WLAN protocols | Ch4 §4.6 |
| 7(d) | Solution to 7(c) | MACA (RTS/CTS) | Ch4 §4.6 |
| 8(a) | Store-and-forward packet switching | NL design issue (1) | Ch5-L1 §5.1.2(1); Ch1 §1.2.5 |
| 8(b) | Flooding: limiting its negative effects | Flooding → hop counter, sequence numbers | Ch5-L1 §5.1.7 |
| 8(c) | Sink tree for router C (hops) | Sink tree | Ch5-L1 §5.1.5 |
| 8(d) | Class C 192.168.1.0 split into 30, 30, 60, 100 hosts: masks and ranges | Subnetting | Ch5-L2 §5.2.3, 5.2.4 |

## 2022/2023 (Exam: February/March 2025, 8 questions, answer 6)

| Q | Question (summary) | Topic → Subtopic | Lecture note(s) |
|---|---|---|---|
| 1(a) | Four reasons networks are required | Why network | Ch1 §1.1.5 |
| 1(b) | P2P vs client–server: differences and similarities | Client–server; P2P | Ch1 §1.1.9, 1.1.11 |
| 1(c)(i) | Short note: distributed system | Distributed systems | Ch1 §1.1.3 |
| 1(c)(ii) | Short note: smart home | ⚠️ Not in notes (IoT/home applications; closest: home apps, PAN, wireless) | Ch1 §1.1.10, 1.2.5, 1.2.6 |
| 1(d) | "Emerging AI trend has a significant impact on computer networks": social and technical | ⚠️ Not in notes. Closest: social issues | Ch1 §1.1.13 |
| 2(a) | Classify networks by transmission technology | Broadcast vs point-to-point | Ch1 §1.2.3 |
| 2(b)(i) | Ring topology: diagram, device, media, usage | Topologies → ring (Token Ring 802.5) | Ch1 §1.2.4, 1.2.5; Ch4 §4.5 (token passing) |
| 2(b)(ii) | Partial mesh topology: diagram, device, media, usage | Topologies → partial mesh | Ch1 §1.2.4 |
| 2(c) | Compare WiFi and Li-Fi | WiFi (802.11); lightwave transmission ⚠️ Li-Fi itself is not named in the notes | Ch4 §4.7; Ch2 §2.2.4; Ch1 §1.2.6 |
| 2(d) | MANET usage scenario, advantages and disadvantages | Ad hoc wireless networking ⚠️ "MANET" not named | Ch1 §1.5.4 (ad hoc); Ch4 §4.7 (ad hoc mode) |
| 3(a) | Need for protocols; why multiple protocols are used | Protocols; protocol hierarchy/stack | Ch1 §1.3.1, 1.3.5 |
| 3(b) | TCP vs UDP: similarities and differences | TCP/IP transport layer | Ch1 §1.4.2 |
| 3(c) | OSI vs TCP/IP with a correspondence diagram | Reference model comparison | Ch1 §1.4.1–1.4.3 |
| 3(d)(i) | Noiseless 3 kHz binary channel cannot exceed 6000 bps | Nyquist (the exact lecture example) | Ch2 §2.7.3 |
| 3(d)(ii) | Same channel at 40 dB noise: highest rate | Shannon, SNR/dB | Ch2 §2.7.4, 2.7.5 |
| 4(a) | Wired vs wireless media: advantages/disadvantages, two examples of each | Guided media; wireless transmission | Ch2 §2.1, 2.2 |
| 4(b) | Baseband vs passband; techniques in each | Digital modulation | Ch2 §2.5 |
| 4(c) | How ASK and FSK send bits; efficiency of QAM-16 vs binary ASK/FSK | Passband → ASK, FSK, QAM-16 (bits per symbol) | Ch2 §2.5.2, 2.7.3 |
| 4(d) | Three satellite types by orbit; which suits global Internet, why, and challenges | Satellites → GEO, MEO, LEO (Iridium/Globalstar) | Ch2 §2.3 |
| 5(a) | Two main DLL functions | DLL functions | Ch3-L1 §3.1.1 |
| 5(b) | Sliding window with diagrams and sequence numbers | Sliding window protocol | Ch3-L2 §3.2.9 |
| 5(c) | Parity for 1-bit errors; parity for burst errors | Parity; interleaving | Ch3-L2 §3.2.3 |
| 5(d) | Byte stuffing with FLAG and ESC | Framing → byte stuffing | Ch3-L1 §3.1.3 |
| 6(a) | Compare FDM, TDM and OFDM | Multiplexing | Ch2 §2.6.1, 2.6.2, 2.6.4 |
| 6(b) | Pure ALOHA vs p-persistent CSMA with diagrams | ALOHA; CSMA | Ch4 §4.3, 4.4 |
| 6(c) | Collision-free protocols; basic bit-map protocol | Collision-free → bit-map | Ch4 §4.5 |
| 6(d) | Binary countdown with 0001, 0101, 1000, 1001 | Collision-free → binary countdown | Ch4 §4.5 |
| 7(a) | Services the NL provides to the transport layer | NL design issue (2) | Ch5-L1 §5.1.2(2) |
| 7(b)(i) | Connectionless: routing tables for routers A–E | Datagram network routing tables | Ch5-L1 §5.1.2(3), 5.1.4 |
| 7(b)(ii) | Virtual circuit H1–H2: tables | VC tables | Ch5-L1 §5.1.2(4) |
| 7(b)(iii) | H3 to H2 via B: label switching | Label switching / MPLS | Ch5-L1 §5.1.2(4) |
| 8(a) | Most suitable topology for an office network (HR, Accounts, Admin, IT) | Topologies (star/tree) applied | Ch1 §1.2.4 |
| 8(b) | Network diagram with devices (DNS, proxy, firewall, fiber ISP) | LAN design; fiber ⚠️ DNS/proxy/firewall outside Ch1–5 | Ch1 §1.2.1, 1.2.5; Ch2 §2.1.4 |
| 8(c) | 192.168.14.0/24: IP specs for Accounts, Admin, IT (HR given as /26) | Subnetting: network ID, broadcast, mask, range | Ch5-L2 §5.2.2, 5.2.4 |

## 2023/2024 (Exam: February 2026, 8 questions, answer 6)

| Q | Question (summary) | Topic → Subtopic | Lecture note(s) |
|---|---|---|---|
| 1(a) | Four usages of computer networks | Uses: business, home, mobile | Ch1 §1.1.8, 1.1.10, 1.1.12 |
| 1(b) | Distributed system with a diagram, advantages/disadvantages, example | Distributed systems | Ch1 §1.1.3 |
| 1(c)(i) | Short note: mobile computing | Mobile users and wireless | Ch1 §1.1.12 |
| 1(c)(ii) | Short note: IoT | ⚠️ Not in notes | Ch1 §1.1.12, 1.2.5 |
| 1(d) | "Social networks and AI have created uncertainty in the minds of their users" | Social issues ⚠️ AI not covered | Ch1 §1.1.13 |
| 2(a) | Classify networks by geographical scale | Classification by scale | Ch1 §1.2.5 |
| 2(b)(i) | Ring topology: diagram, devices, media, usage | Topologies → ring | Ch1 §1.2.4 |
| 2(b)(ii) | Star topology: diagram, devices, media, usage | Topologies → star | Ch1 §1.2.4 |
| 2(c) | Telephone network vs Internet | PSTN vs Internet; circuit vs packet switching | Ch2 §2.4, 2.8; Ch1 §1.5.2 |
| 2(d) | Step-by-step connection-oriented client–server interaction | Service primitives | Ch1 §1.3.7, 1.3.8 |
| 3(a) | What a protocol is and why needed | Protocols | Ch1 §1.3.1 |
| 3(b) | Two reasons for layering; a disadvantage | Layering advantages/disadvantages | Ch1 §1.3.2, 1.3.10 |
| 3(c) | Compare TCP and UDP | TCP/IP transport layer | Ch1 §1.4.2 |
| 3(d) | Compare OSI and TCP/IP with illustrations | Reference model comparison | Ch1 §1.4.1–1.4.3 |
| 3(d)(i) | OSI layer that routes packets | OSI → Network layer | Ch1 §1.4.1 |
| 3(d)(ii) | OSI layer that converts packets to frames | OSI → Data link layer (framing) | Ch1 §1.4.1; Ch3-L1 §3.1.3 |
| 3(d)(iii) | OSI layer that detects/corrects errors | OSI → Data link layer (also transport) | Ch1 §1.4.1; Ch3-L2 §3.2.2 |
| 3(d)(iv) | OSI layer for HTTP, FTP, Telnet | OSI → Application layer | Ch1 §1.4.1 |
| 4(a)(i) | Twisted pair construction and usage | Guided media → twisted pair | Ch2 §2.1.2 |
| 4(a)(ii) | Optical fiber construction and usage | Guided media → fiber | Ch2 §2.1.4 |
| 4(b) | Compare TDM, WDM and OFDM | Multiplexing | Ch2 §2.6.2–2.6.4 |
| 4(c)(i) | Noiseless 4 kHz channel, QAM-64: max rate | Nyquist with QAM-64 (V = 64) | Ch2 §2.7.3, 2.5.2 |
| 4(c)(ii) | Same channel at 20 dB SNR | Shannon, SNR/dB | Ch2 §2.7.4, 2.7.5 |
| 5(a) | Two main DLL responsibilities | DLL functions | Ch3-L1 §3.1.1 |
| 5(b) | Bit stuffing of 01111011111011111110 | Framing → bit stuffing | Ch3-L1 §3.1.3 |
| 5(c) | Byte count with an example; disadvantage; fix | Framing → byte count | Ch3-L1 §3.1.3 |
| 5(d) | Hamming distance with an example | Hamming distance | Ch3-L2 §3.2.5 |
| 5(d)(i) | 16-bit message: check bits needed for single-bit correction | Hamming code (m + r + 1 ≤ 2ʳ) | Ch3-L2 §3.2.6 |
| 5(d)(ii) | Codeword for 1101001100110101 (even parity) | Hamming code calculation | Ch3-L2 §3.2.6 |
| 6(a) | Slotted ALOHA vs 1-persistent CSMA with diagrams | ALOHA; CSMA | Ch4 §4.3, 4.4 |
| 6(b) | CSMA/CD states diagram and implementation | CSMA/CD | Ch4 §4.4 |
| 6(c) | Hidden and exposed terminal problems with a diagram | WLAN protocols | Ch4 §4.6 |
| 6(d) | Solution and how it works | MACA (RTS/CTS) | Ch4 §4.6 |
| 7(a) | Store-and-forward packet switching | NL design issue (1) | Ch5-L1 §5.1.2(1) |
| 7(b) | Define flooding; two techniques to control it | Flooding → hop counter, sequence numbers | Ch5-L1 §5.1.7 |
| 7(c) | Go-Back-N with window 1; large receiver window | Go-Back-N / sliding window | Ch3-L2 §3.2.9, 3.2.10 |
| 7(d) | Sink tree for router B (hops) | Sink tree | Ch5-L1 §5.1.5 |
| 8(a) | Maximum number of class A, B and C network IDs | Classful addressing | Ch5-L2 §5.2.3 |
| 8(b) | 192.168.1.0 split into 30/30/60/100: mask, network address, usable range, broadcast (100 given) | Subnetting | Ch5-L2 §5.2.2, 5.2.4 |
| 8(c) | Network diagram: router, DNS, proxy, firewall, fiber ISP, media | LAN/WAN design; guided media ⚠️ DNS/proxy/firewall outside Ch1–5 | Ch1 §1.2.1, 1.2.5; Ch2 §2.1 |
| 8(d) | Distance vector: new delays and output lines for router J (vectors from A, I, H, K) | Distance vector routing (the lecture example) | Ch5-L1 §5.1.8 |

---

# PART 3 — Exam Focus Analysis

## 3.1 How often each topic appears (6 papers)

| Topic | 18/19 | 19/20 | 20/21 | 21/22 | 22/23 | 23/24 | Years | Note |
|---|:-:|:-:|:-:|:-:|:-:|:-:|:-:|---|
| Protocol definition / need | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | **6/6** | Ch1 |
| Topologies | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | **6/6** | Ch1 |
| Subnetting / IP allocation | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | **6/6** | Ch5-L2 |
| Define network / reasons / uses | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | **6/6** | Ch1 |
| Layering reasons / disadvantage | ✅ | ✅ | ✅ | ✅ | – | ✅ | 5/6 | Ch1 |
| Distributed systems | ✅ | – | ✅ | ✅ | ✅ | ✅ | 5/6 | Ch1 |
| Mobile computing | ✅ | ✅ | ✅ | ✅ | – | ✅ | 5/6 | Ch1 |
| Network classification (scale / transmission tech.) | – | ✅ | ✅ | ✅ | ✅ | ✅ | 5/6 | Ch1 |
| Social / technical essay (1d) | – | ✅ | ✅ | ✅ | ✅ | ✅ | 5/6 | Ch1 ⚠️ |
| Multiplexing (FDM/TDM/WDM/OFDM) | – | ✅ | ✅ | ✅ | ✅ | ✅ | 5/6 | Ch2 |
| DLL functions / services to NL | – | ✅ | ✅ | ✅ | ✅ | ✅ | 5/6 | Ch3-L1 |
| ALOHA vs CSMA variants | – | ✅ | ✅ | ✅ | ✅ | ✅ | 5/6 | Ch4 |
| Nyquist / Shannon / QAM rate | ✅ | ✅ | – | – | ✅ | ✅ | 4/6 | Ch2 |
| Transmission media construction | – | ✅ | ✅ | ✅ | ✅ | ✅ | 5/6 | Ch2 |
| Hidden/exposed terminals + MACA | – | ✅ | ✅ | ✅ | – | ✅ | 4/6 | Ch4 |
| Sink tree | ✅ | – | ✅ | ✅ | – | ✅ | 4/6 | Ch5-L1 |
| Network design diagram scenario | ✅ | ✅ | – | – | ✅ | ✅ | 4/6 | Ch1/Ch2 ⚠️ |
| IoT / smart home / smart cars | – | ✅ | ✅ | ✅ | ✅ | ✅ | 5/6 | ⚠️ not in notes |
| P2P vs client–server | ✅ | ✅ | ✅ | – | ✅ | – | 4/6 | Ch1 |
| Wireless adv/disadv (WLAN, wired vs wireless) | ✅ | – | ✅ | – | ✅ | – | 3/6 | Ch1/Ch2 |
| Byte stuffing | ✅ | – | ✅ | – | ✅ | – | 3/6 | Ch3-L1 |
| Byte count | – | ✅ | – | ✅ | – | ✅ | 3/6 | Ch3-L1 |
| Parity / burst detection | ✅ | – | ✅ | – | ✅ | – | 3/6 | Ch3-L2 |
| Hamming distance / code | – | – | ✅ | ✅ | – | ✅ | 3/6 | Ch3-L2 |
| TCP vs UDP | – | ✅ | – | – | ✅ | ✅ | 3/6 | Ch1 |
| OSI vs TCP/IP | – | ✅ | – | – | ✅ | ✅ | 3/6 | Ch1 |
| Message through layers / virtual vs physical | ✅ | – | ✅ | ✅ | – | – | 3/6 | Ch1 |
| Connection-oriented client–server steps | – | ✅ | – | ✅ | – | ✅ | 3/6 | Ch1 |
| Telephone network vs Internet | – | ✅ | – | ✅ | – | ✅ | 3/6 | Ch2/Ch1 |
| Baseband vs passband (ASK/FSK) | – | ✅ | – | ✅ | ✅ | – | 3/6 | Ch2 |
| GSM architecture | – | ✅ | ✅ | ✅ | – | – | 3/6 | Ch2 |
| CSMA/CD states | – | ✅ | – | ✅ | – | ✅ | 3/6 | Ch4 |
| Collision-free (bit-map / binary countdown) | – | ✅ | – | ✅ | ✅ | – | 3/6 | Ch4 |
| Sliding window | – | ✅ | – | – | ✅ | – | 2/6 | Ch3-L2 |
| Go-Back-N | – | – | ✅ | – | – | ✅ | 2/6 | Ch3-L2 |
| NRZ / NRZI drawing | – | ✅ | – | ✅ | – | – | 2/6 | Ch2 |
| Circuit vs packet switching | – | ✅ | – | ✅ | – | – | 2/6 | Ch2 |
| Handoff | – | ✅ | – | ✅ | – | – | 2/6 | Ch2 |
| Store-and-forward | – | – | – | ✅ | – | ✅ | 2/6 | Ch5-L1 |
| Flooding | – | – | – | ✅ | – | ✅ | 2/6 | Ch5-L1 |
| Connectionless tables / VC / label switching | – | – | ✅ | – | ✅ | – | 2/6 | Ch5-L1 |
| Distance vector routing | – | – | ✅ | – | – | ✅ | 2/6 | Ch5-L1 |
| TCP/IP & OSI layer identification | – | – | – | – | – | ✅ | 1/6 | Ch1 |
| CRC | ✅ | – | – | – | – | – | 1/6 | Ch3-L2 |
| CDMA chip sequences | – | – | ✅ | – | – | – | 1/6 | Ch2 |
| Satellites (GEO/MEO/LEO) | – | – | – | – | ✅ | – | 1/6 | Ch2 |
| Bit stuffing | – | – | – | – | – | ✅ | 1/6 | Ch3-L1 |
| Dog/tape bandwidth problem | – | – | – | ✅ | – | – | 1/6 | Ch2 ⚠️ |
| Services NL → TL | – | – | – | – | ✅ | – | 1/6 | Ch5-L1 |
| Design issues of layers | – | – | ✅ | – | – | – | 1/6 | Ch1 |
| Classful network counts | – | – | – | – | – | ✅ | 1/6 | Ch5-L2 |
| WiFi vs WiMAX vs 4G / Li-Fi / MANET | – | – | ✅ | – | ✅ | – | 2/6 | Ch4/Ch2 ⚠️ |

## 3.2 Question-slot pattern (8-question papers, 2019/20 onward)

| Slot | Usual chapter | Typical content |
|---|---|---|
| **Q1** | Ch1 §1.1 | Define network / reasons / uses; distributed system or P2P vs client–server; short notes (mobile computing + IoT/smart home); essay on a social or technical statement |
| **Q2** | Ch1 §1.2 (+ Ch2 PSTN) | Classification (scale or transmission technology); two topologies; Telephone vs Internet / WLAN / Li-Fi / MANET; connection-oriented client–server steps |
| **Q3** | Ch1 §1.3–1.4 (+ Ch2 capacity) | Protocol definition; layering reasons; TCP vs UDP or message through layers; OSI vs TCP/IP or physical/virtual diagram; sometimes Nyquist/Shannon |
| **Q4** | Ch2 | Media construction; baseband/passband; multiplexing; NRZ/NRZI; Nyquist/Shannon with QAM; CDMA or satellites |
| **Q5** | Ch3 (or Ch2 in 2019/20) | DLL functions/services; framing (byte count / byte stuffing / bit stuffing); parity / Hamming; sliding window |
| **Q6** | Ch3-L2 / Ch4 / Ch2 | Mixes: FDM/TDM, switching, GSM, handoff **or** Go-Back-N, ALOHA/CSMA, hidden/exposed, sink tree **or** DLL framing |
| **Q7** | Ch4 or Ch5-L1 | ALOHA/CSMA, CSMA/CD, collision-free, MACA **or** routing tables, VC, label switching, store-and-forward, flooding, sink tree |
| **Q8** | Ch5 + design | Subnetting (almost always), network design diagram scenario, distance vector, sink tree, short notes |

## 3.3 Weight by lecture note (all 6 papers, sub-questions counted)

| Lecture note | Approx. sub-questions | Share |
|---|:-:|:-:|
| Ch1 – Introduction | ~80 | **~37%** |
| Ch2 – Physical Layer | ~52 | ~24% |
| Ch3-L1 – DLL (framing, services) | ~16 | ~7% |
| Ch3-L2 – DLL (errors, protocols) | ~18 | ~8% |
| Ch4 – MAC | ~22 | ~10% |
| Ch5-L1 – Network layer (routing) | ~20 | ~9% |
| Ch5-L2 – IP and subnetting | ~9 | ~4% (but in 6/6 papers) |

(Some sub-questions map to more than one note, so counts overlap slightly.)

## 3.4 Key takeaways

1. **Ch1 carries the most marks.** Q1–Q3 are almost entirely Introduction material: uses of networks, distributed/P2P/client–server, topologies, classification, protocols, layering, OSI vs TCP/IP, TCP vs UDP, and connection-oriented service primitives.
2. **Four topics appear in every paper:** protocol definition, topologies, uses/definition of networks, and a **subnetting calculation**.
3. **Calculation questions to master:**
   - Nyquist/Shannon with QAM (Ch2)
   - Subnetting (Ch5-L2)
   - Hamming code for a 16-bit message: the **same message, 1101001100110101, appears in 2020/21 and 2023/24** (Ch3-L2)
   - Byte stuffing, bit stuffing and byte count (Ch3-L1)
   - Sink tree (Ch5-L1)
   - Binary countdown with codes 0001/0101/1000/1001 (**identical in 2019/20 and 2022/23**, Ch4)
   - CDMA and CRC (one year each)
4. **Exact repeats to expect:**
   - GSM diagram plus handoff (2019/20, 2021/22)
   - Router A–E routing tables / VC / label switching (2020/21, 2022/23)
   - Department network + 192.168.14.0/24 with a /26 given (2019/20, 2022/23)
   - Class C 192.168.1.0 split into 30/30/60/100 (2021/22, 2023/24)
   - The sink-tree graph (same A–O graph in 2020/21 and 2023/24)
   - Distance vector for router J (the lecture's own example)
5. **Content outside the provided notes:** IoT, smart home, smart cars, AI, Li-Fi, MANET, and DNS/proxy/firewall in design questions. You need general or self-study knowledge for these. The design scenarios (Q8) and the Q1(d) essays are the main places they show up.
