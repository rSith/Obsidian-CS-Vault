---
type: lecture
course: CMIS 3114
lecture: 3
status: stub
tags: [networking]
aliases: [Data Link Layer]
---
# The Data Link Layer
> [!info] CMIS 3114 · Chapter 3
> [[CMIS 3114 Course Home]] · Exam prep: [[3114 Topic Map & Past Papers#Ch3-L1 — Data Link Layer (Lecture 1)|Topic map]]
> ← [[3114-02 Physical Layer]] | [[3114-04 MAC Sub-layer]] →

> [!tip]- Exam focus — how often each topic appeared (6 papers, 2018/19–2023/24)
> - **5/6** — DLL functions / services to NL
> - **3/6** — Byte stuffing
> - **3/6** — Byte count
> - **3/6** — Parity / burst detection
> - **3/6** — Hamming distance / code
> - **2/6** — Sliding window
> - **2/6** — Go-Back-N
> - **1/6** — CRC
> - **1/6** — Bit stuffing
>
> **Repeats to expect**
> - Hamming code for the 16-bit message **1101001100110101** (same message in 2020/21 and 2023/24).
> - Byte stuffing, bit stuffing and byte count calculations.

> [!question]- Past paper questions on this chapter (31)
> - **2018/19 Q4(b)** — CRC: bit stream 10011001, G(x) = x³ + x + 1, transmitted string
> - **2018/19 Q4(c)** — 4th bit inverted: show it is detected
> - **2018/19 Q4(d)** — Example of an undetected error pattern
> - **2018/19 Q5(a)** — Single-bit parity to detect burst errors; max burst length
> - **2018/19 Q5(c)** — Byte stuffing of `A B ESC C ESC FLAG FLAG D`
> - **2019/20 Q6(a)** — Two main functions of the DLL
> - **2019/20 Q6(b)** — Byte count framing for ABCD / EFG / HIJKLM
> - **2019/20 Q6(c)** — Can byte count recover from an error?
> - **2019/20 Q6(d)** — Sliding window from the initial state to ACK receipt, with sequence numbers
> - **2020/21 Q5(a)** — Two DLL services to the network layer
> - **2020/21 Q5(b)** — Byte stuffing with FLAG and ESC
> - **2020/21 Q5(c)** — Parity for burst errors; max detectable burst length
> - **2020/21 Q5(d)** — 16-bit message Hamming code: number of check bits; codeword for 1101001100110101 (even parity)
> - **2020/21 Q6(a)** — Go-Back-N with window 1; what if the receiver window is large?
> - **2021/22 Q5(a)** — Two DLL services to the network layer
> - **2021/22 Q5(b)** — Byte count with an example; disadvantage; a better method
> - **2021/22 Q5(c)** — Hamming distance with an example
> - **2021/22 Q5(d)** — Show codewords 00→00000, 01→01011, 10→10101, 11→11110 correct 1-bit errors
> - **2022/23 Q5(a)** — Two main DLL functions
> - **2022/23 Q5(b)** — Sliding window with diagrams and sequence numbers
> - **2022/23 Q5(c)** — Parity for 1-bit errors; parity for burst errors
> - **2022/23 Q5(d)** — Byte stuffing with FLAG and ESC
> - **2023/24 Q3(d)(ii)** — OSI layer that converts packets to frames
> - **2023/24 Q3(d)(iii)** — OSI layer that detects/corrects errors
> - **2023/24 Q5(a)** — Two main DLL responsibilities
> - **2023/24 Q5(b)** — Bit stuffing of 01111011111011111110
> - **2023/24 Q5(c)** — Byte count with an example; disadvantage; fix
> - **2023/24 Q5(d)** — Hamming distance with an example
> - **2023/24 Q5(d)(i)** — 16-bit message: check bits needed for single-bit correction
> - **2023/24 Q5(d)(ii)** — Codeword for 1101001100110101 (even parity)
> - **2023/24 Q7(c)** — Go-Back-N with window 1; large receiver window

## Topic checklist

### Lecture 1
- [ ] **3.1.1** Functions of the DLL: service interface to the network layer, handling transmission errors, regulating data flow
- [ ] **3.1.2** Services to the network layer: virtual vs actual communication
  - Unacknowledged connectionless (Ethernet)
  - Acknowledged connectionless (802.11 WiFi)
  - Acknowledged connection-oriented (3 phases: satellite and long-distance links)
- [ ] **3.1.3** Framing: purpose, checksum per frame
  - (1) **Byte count** (cannot resynchronise after an error)
  - (2) **Flag bytes with byte stuffing** (FLAG, ESC, stuffing examples)
  - (3) **Flag bits with bit stuffing** (01111110, stuff a 0 after five 1s, USB)
  - (4) **Physical layer coding violations**; preamble plus length field (Ethernet/802.11)
- [ ] **3.1.4** Error control: ACKs, timers, sequence numbers, exactly-once delivery
- [ ] **3.1.5** Flow control: feedback-based vs rate-based

### Lecture 2
- [ ] **3.2.1** Types of transmission errors: single-bit vs burst
- [ ] **3.2.2** Error detection vs error correction (FEC) strategies
- [ ] **3.2.3** Error detection codes
  - **Parity** (even/odd; rows of parity)
  - **Interleaving** (parity computed in a different order, used for burst errors)
  - **Checksum** (16-bit Internet checksum worked example)
  - **CRC / polynomial code** (generator rules, modulo-2 division, G(x) = x⁴ + x + 1 example)
- [ ] **3.2.4** Codewords: (n, m) codeword, code rate m/n
- [ ] **3.2.5** **Hamming distance**: XOR and count; distance of a code; sparseness of codewords; detect d errors needs distance d + 1, correct d errors needs 2d + 1; four-codeword example
- [ ] **3.2.6** **Hamming code**: check bits at powers of 2, (11,7) example, computing check bits (even parity), error syndrome, correcting the error; Hamming codes for burst errors
- [ ] **3.2.7** Data link protocols: NIC, device driver, network accelerator, software-defined radio
- [ ] **3.2.8** Elementary protocols: unrestricted simplex, simplex stop-and-wait, simplex for a noisy channel
- [ ] **3.2.9** **Sliding window protocol** (window size 1, 3-bit sequence number, states a–d)
- [ ] **3.2.10** **Go-Back-N** (pipelining and error recovery; receiver window of 1 vs large, i.e. selective repeat)

## Notes
*Work through the checklist above. Write each topic in your own words, then tick it off.*


## Key definitions
> [!note] Term
>

## Summary (in my own words)


## Open questions
- [ ]
