---
type: revision
course: CMIS 3114
status: complete
tags: [cmis3114, revision, algorithms]
aliases: [CMIS 3114 algorithms, CMIS 3114 procedures]
---
# CMIS 3114 · Important Algorithms and Step-by-Step Procedures

> [!info] Each entry: **purpose → input → steps → result**, with the past-paper example. Home: [[00. CMIS 3114 Course Overview]] · Formulas: [[3114 Important Formulas]]

## 1. Byte count framing → [[03.02 Framing]]
**Purpose:** mark frame length. **Steps:** count = characters + 1 → write count, then data. **Example:** ABCD/EFG/HIJKLM → `5 A B C D 4 E F G 7 H I J K L M`. **Limitation:** a corrupted count destroys synchronisation.

## 2. Byte stuffing → [[03.02 Framing]]
**Steps:** frame = FLAG … FLAG; in the data put **ESC before each FLAG and each ESC**; receiver drops each ESC and keeps the next byte. **Example:** `A B ESC C ESC FLAG FLAG D` → `A B ESC ESC C ESC ESC ESC FLAG ESC FLAG D`.

## 3. Bit stuffing → [[03.02 Framing]]
**Steps:** flag 01111110; after **five consecutive 1s** in the data insert **0**; receiver removes the 0 after five 1s. **Example:** `0111101111101111110` → `011110111110011111010`.

## 4. Parity and interleaving → [[03.04 Error Detection - Parity, Interleaving and Checksum]]
**Steps:** even parity bit = 1 if the count of 1s is odd. For bursts: k×n matrix → parity per column → send rows then parity row → burst ≤ n detected.

## 5. Internet checksum → [[03.04 Error Detection - Parity, Interleaving and Checksum]]
**Steps:** add 16-bit words → wrap carry → complement = checksum. Receiver adds all incl. checksum → FFFF → complement 0000 = OK. **Example:** 3456+ABCC+02BC+EEEE = 1D1CC → D1CD → checksum 2E32.

## 6. CRC → [[03.05 Cyclic Redundancy Check (CRC)]]
**Steps:** G of degree r → append r zeros → modulo-2 divide (XOR) → remainder = CRC → send message + CRC. Receiver: remainder 0 = OK.
**Examples:** `1101011011` ÷ `10011` → `11010110111110`; `10011001` ÷ `1011` → `10011001100`.

## 7. Hamming distance → [[03.06 Codewords and Hamming Distance]]
XOR the two codewords, count the 1s. Code distance = minimum over all pairs. Detect d ⇒ d+1; correct d ⇒ 2d+1.

## 8. Hamming code → [[03.07 Hamming Code]]
**Steps:** r from $m+r+1 \le 2^r$ → check bits at 1, 2, 4, 8, 16 → data in the other positions → P_k = parity of positions with bit k set → codeword. **Correction:** recompute checks → syndrome $P_8P_4P_2P_1$ = position of the error → flip.
**Example:** 16-bit `1101001100110101` → r = 5 → `011110110011001110101`.

## 9. Stop-and-wait / sliding window → [[03.09 Sliding Window and Go-Back-N]]
Send frame (seq), start timer → receiver checks, delivers, ACKs, slides window → sender slides on ACK; timeout → resend; duplicate → discard and re-ACK.

## 10. Go-Back-N vs selective repeat → [[03.09 Sliding Window and Go-Back-N]]
Pipeline frames → error at frame i → **GBN:** receiver discards i+1…, sender times out and resends i, i+1, … → **SR:** receiver buffers i+1…, NAK i, sender resends only i.

## 11. NRZI encoding → [[02.05 Digital Modulation - Baseband and Passband]]
Start at a level; for each bit: **1 → flip, 0 → stay**.

## 12. CDMA send/receive → [[02.07 Code Division Multiple Access (CDMA)]]
Binary chips → bipolar (0 → −1) → bit 1 = chips, bit 0 = negated chips → sum all stations → receiver computes $S\cdot C/m$ → +1 = 1, −1 = 0, 0 = silent. **Example:** 2020/21 → S = (−2, −2, 0, −2, 0, −2, +4, 0); S·C = −1 → C sent 0.

## 13. Pure / slotted ALOHA → [[04.02 ALOHA]]
Send when ready (pure) or at slot start (slotted) → central station rebroadcasts → if destroyed, wait random time, resend.

## 14. CSMA variants and CSMA/CD → [[04.03 CSMA and CSMA-CD]]
1-persistent: sense → idle: send; busy: wait until idle then send. Non-persistent: busy → random wait, re-sense. p-persistent: idle → send with prob. p else defer a slot. CSMA/CD: listen while sending → collision: abort → contention slots with random backoff → transmission → idle.

## 15. Bit-map, token passing, binary countdown → [[04.04 Collision-Free Protocols]]
Bit-map: N slots, station j sets bit j, then send in order. Token: send only with the token. Binary countdown: send address MSB first, channel ORs, drop out when your 0 is overwritten by 1 → highest address wins (0001, 0101, 1000, 1001 → **1001**).

## 16. MACA (RTS/CTS) → [[04.05 Hidden and Exposed Terminals and MACA]]
RTS (with length) → CTS (with length) → data (→ ACK). Hear RTS: silent until CTS done. Hear CTS: silent for the whole data frame.

## 17. Store-and-forward → [[05.01 Network Layer Design Issues]]
Send to nearest router → store until fully arrived, verify checksum → look up table → forward → repeat → deliver.

## 18. Sink tree (BFS) → [[05.03 Routing Algorithms and the Sink Tree]]
Root = destination → level 1 neighbours → level 2 new neighbours … → one parent each, note ties. Four past-paper trees in [[PP 05.2 - Routing - Sink Tree, Flooding and Distance Vector]].

## 19. Dijkstra shortest path → [[05.04 Shortest Path Routing]]
Source permanent at 0 → relax neighbours → make smallest tentative permanent → repeat → trace back. **Lecture graph A→D:** A-B-E-F-H-D = 10.

## 20. Flooding with damping → [[05.05 Flooding]]
Send on every line except arrival line; **hop counter** decremented, discard at 0; or **(source, sequence number)** lists to drop duplicates.

## 21. Distance vector update → [[05.06 Distance Vector Routing]]
For each destination: min over neighbours (delay to neighbour + neighbour's distance) → record distance and line. **Router J:** A 8 A, B 20 A, C 28 I, D 20 H, E 17 I, F 30 I, G 18 H, H 12 H, I 10 I, J 0 –, K 6 K, L 15 K.

## 22. Datagram tables, VCs, label switching → [[05.02 Datagram and Virtual-Circuit Networks]]
Datagram: destination → next hop per router (from shortest paths). VC: (in line, in label) → (out line, out label) set at setup. Label clash → router assigns a new outgoing label.

## 23. Subnetting → [[05.09 Subnetting]]
Round up to 2ⁿ (+2 for hosts) → largest first → align → prefix = 32 − log₂(block) → network, broadcast, usable range, mask.

## 24. Client–server connection-oriented interaction → [[01.14 Service Primitives]]
LISTEN → CONNECT (1) → ACCEPT/ACK (2) → RECEIVE → SEND request (3) → SEND reply (4) → DISCONNECT (5) → DISCONNECT (6).

## 25. Message through the layers (encapsulation) → [[01.11 Protocol Hierarchies and Encapsulation]]
M → H4 M → H3 H4 M1 / H3 M2 → H2 … T2 → bits → reverse with headers stripped.

## 26. Handoff → [[02.10 Mobile Telephone System and Handoff]]
Signal fades → old BS asks neighbours for power readings → MSC/BSC picks strongest, new channel → phone switches (~300 ms).

## 27. Textbook processes (not in the slides) ⚠
TCP three-way handshake and release → [[06.02 TCP Connection Management]] · TCP slow start / AIMD → [[06.03 TCP Flow Control and Congestion Control]] · DNS resolution → [[07.01 DNS]] · HTTP page load → [[07.02 HTTP and the Web]] · e-mail delivery → [[07.03 Email - SMTP, POP3 and IMAP]] · ARP, DHCP, switch learning → [[05.10 ARP and DHCP (Beyond the Slides)]]
