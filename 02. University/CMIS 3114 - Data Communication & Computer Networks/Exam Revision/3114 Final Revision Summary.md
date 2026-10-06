---
type: revision
course: CMIS 3114
status: complete
tags: [cmis3114, revision, summary, cram]
aliases: [CMIS 3114 cram sheet, CMIS 3114 final revision]
---
# CMIS 3114 · Final Revision Summary (the night before)

> [!abstract] One page per chapter of "must remember". Lesson summaries: [[01.99 Summary - Introduction]] · [[02.99 Summary - Physical Layer]] · [[03.99 Summary - Data Link Layer]] · [[04.99 Summary - MAC Sublayer]] · [[05.99 Summary - Network Layer]] · Home: [[00. CMIS 3114 Course Overview]]

## Exam format
8 questions, **answer 6**, 3 hours (~30 min each). No marks printed. **Q1–Q3 = Chapter 1**, **Q4 = Physical**, **Q5 = DLL**, **Q6/Q7 = DLL/MAC/Network mix**, **Q8 = subnetting + design**.

## Ch1 Introduction
- Network = autonomous computers interconnected by a single technology, able to exchange information. Internet = network of networks. Web = distributed system on the Internet.
- Why: **resource sharing, robustness, load balancing, location independence**. Problem: **security**.
- Distributed system = independent computers appearing as **one coherent system** (middleware; Web).
- Client–server (request/reply) vs P2P (every host both; BitTorrent).
- Uses: business, home, mobile, social issues (privacy, cookies, identity theft, rights, censorship, copyright).
- Classification: connection type · **broadcast vs point-to-point** (unicast/broadcast/multicast) · **topology** (bus, star, ring, full/partial mesh, tree) · **scale** (BAN, PAN, LAN 802.3/802.5, MAN cable TV, WAN store-and-forward).
- Protocol = rules for the **format and meaning** of messages. Layering = divide and conquer; ✅ simpler, modular, flexible; ❌ overhead.
- Message: M → H4 M → H3 H4 M1 / H3 M2 → H2…T2 → bits. Routers = layers 1–3.
- Design issues: addressing, error control, QoS, flow control, multiplexing, routing, message size.
- Primitives: LISTEN, CONNECT, ACCEPT, RECEIVE, SEND, DISCONNECT (packets 1–6, phases 1–9).
- OSI 7 (P, DL, N, T, S, P, A) vs TCP/IP 4 (host-to-network, internet, transport, application) vs hybrid 5.
- TCP reliable connection-oriented byte stream; UDP unreliable connectionless.

## Ch2 Physical layer
- Twisted pair (twist cancels interference; UTP Cat 5e/6) · coax (core, insulation, braid, jacket) · fibre (core + lower-index cladding + jacket; total internal reflection; LED vs laser).
- Wireless: radio (ground waves / ionosphere), microwave (line of sight), infrared, lightwave; ISM bands.
- Satellites: GEO 35,800 km (3, 270 ms), MEO GPS 20,200 km, LEO ~1,000 km (Iridium 66, Globalstar).
- PSTN: local loop (analog), trunks (digital fibre), switching offices; LATA/LEC/IXC/POP; modem, codec, ADSL (DMT).
- Baseband NRZ/NRZI vs passband ASK/FSK/PSK/QPSK/QAM-16/64.
- FDM (guard bands) · OFDM (orthogonal, no guard) · WDM (wavelengths) · TDM (slots; T1 = 1.544 Mbps) · CDMA (chips).
- **Nyquist** $2B\log_2V$ · **Shannon** $B\log_2(1+S/N)$ · dB = 10 log₁₀(S/N).
- Circuit (setup, dedicated, fixed BW) vs packet (store-and-forward, dynamic) vs message switching.
- Mobile: 1G analog (IMTS, AMPS cells, reuse, MSC) · handoff ~300 ms · 2G GSM (SIM, BSC, MSC, VLR, HLR; 124 × 8 slots) · 2.5G EDGE/GPRS · 3G WCDMA/CDMA2000 · 4G LTE/WiMAX · 5G ~1 Gbps.
- HFC = fibre + coax; cable Internet is shared, ADSL is not.

## Ch3 Data link layer
- Functions: service to network layer (framing), error control, flow control.
- Services: unack connectionless (Ethernet) · ack connectionless (WiFi) · ack connection-oriented (satellite).
- Framing: byte count ✗ resync · FLAG/ESC byte stuffing · 01111110 bit stuffing (0 after five 1s) · coding violations.
- Errors: single vs burst; detection (parity, checksum, CRC) vs correction (Hamming, FEC).
- Parity burst: per-column parity, max burst = n. Checksum: one's complement. CRC: append r zeros, XOR-divide.
- Hamming distance: XOR & count; detect d → d+1, correct d → 2d+1. Hamming code: P at 1, 2, 4, 8, 16; $m+r+1\le2^r$.
- Sliding window (3-bit, 0–7), Go-Back-N (discard & resend all) vs selective repeat (buffer, resend one).

## Ch4 MAC sublayer
- DLL = LLC + MAC; static vs dynamic allocation (5 assumptions).
- Pure ALOHA (~18%) · slotted (~37%) · 1-/non-/p-persistent CSMA · CSMA/CD (contention, transmission, idle).
- Collision-free: bit-map (N slots), token passing, binary countdown (highest address wins).
- Hidden terminal (collision at receiver) · exposed (needless wait) · **MACA RTS/CTS**.
- 802.11: infrastructure vs ad hoc; b 11 Mbps 2.4 GHz DSSS · a 54 Mbps 5 GHz OFDM · g 54 Mbps 2.4 GHz · n 600 Mbps MIMO; WEP ✗ → WPA2 (AES). 802.16 WiMAX.

## Ch5 Network layer
- Store-and-forward; services to transport (3 goals); datagram (IP) vs VC (MPLS, label switching).
- Forwarding vs routing; static vs adaptive; optimality principle → **sink tree** (BFS).
- Shortest path (Dijkstra); flooding (hop counter / sequence numbers); **distance vector** (min of delay + neighbour's distance).
- IPv4 header: Version, IHL, ToS, Total length, ID, DF, MF, Fragment offset, TTL, Protocol, Checksum, Src, Dst, Options.
- IP: 32 bits, prefix /n, mask AND; classes A (2⁷ nets), B (2¹⁴), C (2²¹); special: this host, broadcast, loopback 127.
- **Subnetting:** round up, largest first, align, prefix = 32 − log₂(block).

## Ch6–8 (no slides; textbook only) ⚠
TCP handshake SYN → SYN-ACK → ACK; FIN/ACK release; flow control (rwnd) vs congestion control (cwnd, slow start, AIMD) · DNS resolution (cache → resolver → root → TLD → authoritative) · HTTP request/response · SMTP + POP3/IMAP · CIA, symmetric vs asymmetric, firewall, proxy.

## Last-minute calculation answers
| Item | Answer |
|---|---|
| 3 kHz binary / 40 dB | 6 kbps / 39.9 kbps |
| 4 kHz QAM-64 / 20 dB | 48 kbps / 26.6 kbps |
| 8 kHz QAM-16 / 30 dB | 64 kbps / 79.7 kbps |
| 6 MHz 6-level / 30 dB | 31.0 Mbps / 59.8 Mbps |
| 4 MHz QAM-16 / QAM-64 | 32 / 48 Mbps |
| Hamming 1101001100110101 | r = 5, `011110110011001110101` |
| Bit stuffing 0111101111101111110 | `011110111110011111010` |
| Byte stuffing A B ESC C ESC FLAG FLAG D | `A B ESC ESC C ESC ESC ESC FLAG ESC FLAG D` |
| CRC 10011001 / x³+x+1 | `10011001100` |
| Binary countdown 0001/0101/1000/1001 | 1001 wins |
| 192.168.1.0: 100/60/30/30 | .0/25 · .128/26 · .192/27 · .224/27 |
| 192.168.14.0/24 four depts | .0, .64, .128, .192 — all /26 (255.255.255.192) |
| Class A/B/C networks | 128 / 16,384 / 2,097,152 |
| Router J (DV) | A8A B20A C28I D20H E17I F30I G18H H12H I10I J0– K6K L15K |
| CDMA 2020/21 | S = (−2,−2,0,−2,0,−2,4,0); S·C = −1 → 0 |
