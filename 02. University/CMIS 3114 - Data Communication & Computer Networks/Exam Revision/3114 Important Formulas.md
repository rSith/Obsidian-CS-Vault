---
type: revision
course: CMIS 3114
status: complete
tags: [cmis3114, revision, formulas, calculation]
aliases: [CMIS 3114 formulas]
---
# CMIS 3114 · Important Formulas

> [!info] Every formula needed for the calculation questions, with variable meanings and a check example. Home: [[00. CMIS 3114 Course Overview]]

## 1. Nyquist (noiseless channel) → [[02.08 Maximum Data Rate - Nyquist and Shannon]]
$$R_{max} = 2B\log_2 V \ \ \text{bits/s}$$
B = bandwidth (Hz), V = number of signal levels/symbols. *Use when the channel is noiseless or a modulation is given.*
Check: B = 3 kHz, binary → 6 kbps; QPSK → 12 kbps; QAM-16 → 24 kbps; QAM-64 → 36 kbps.

## 2. Decibels
$$\text{SNR}_{dB} = 10\log_{10}\frac{S}{N} \qquad \frac{S}{N} = 10^{\text{dB}/10}$$
10 dB → 10 · 20 dB → 100 · 30 dB → 1000 · 40 dB → 10,000.

## 3. Shannon (noisy channel)
$$C = B\log_2\left(1+\frac{S}{N}\right)\ \ \text{bits/s}$$
Use the **ratio**, not dB. If both limits apply, the achievable rate is the **smaller**.
Useful logs: $\log_2 6 = 2.585$ · $\log_2 101 = 6.658$ · $\log_2 1001 = 9.967$ · $\log_2 10001 = 13.288$. Calculator: $\log_2 x = \log_{10} x / 0.30103$.

| Past paper | Answer |
|---|---|
| 6 MHz, V = 6; 30 dB | 31.0 Mbps; 59.8 Mbps |
| 4 MHz QAM-16 / QAM-64 | 32 / 48 Mbps |
| 8 kHz QAM-16; 30 dB | 64 kbps; 79.7 kbps |
| 3 kHz binary; 40 dB | 6 kbps; 39.9 kbps |
| 4 kHz QAM-64; 20 dB | 48 kbps; 26.6 kbps |

## 4. Bits per symbol
$$\text{bits/symbol} = \log_2 V$$
ASK/FSK/BPSK 1 · QPSK 2 · QAM-16 4 · QAM-64 6.

## 5. Transfer time / physical transport (dog problem)
$$t_{line} = \frac{\text{data (bits)}}{\text{rate (bps)}}\qquad t_{carry} = \frac{\text{distance}}{\text{speed}}$$
Carrying wins when $t_{carry} < t_{line}$. Past paper: 2.4×10¹¹ bits, 150 Mbps → 1600 s; 18 km/h → d < 8 km.

## 6. T1 carrier
$$24 \times 64\ \text{kbps} = 1.536\ \text{Mbps};\ \ 193\ \text{bits} \times 8000/\text{s} = 1.544\ \text{Mbps}$$

## 7. CDMA normalised inner product
$$S\cdot C = \frac{1}{m}\sum_{i=1}^{m}S_iC_i \qquad (+1 \Rightarrow 1,\ -1 \Rightarrow 0,\ 0 \Rightarrow \text{silent})$$
Orthogonality: $A\cdot B = 0$, $A\cdot A = 1$, $A\cdot\overline{A} = -1$.

## 8. Codes
$$n = m + r \qquad \text{code rate} = \frac{m}{n}$$
$$\text{detect } d:\ D \ge d+1 \qquad \text{correct } d:\ D \ge 2d+1$$
$$\text{Hamming check bits: smallest } r \text{ with } m + r + 1 \le 2^r$$
m = 4 → 3 · m = 7 → 4 · m = 16 → 5 · m = 32 → 6.
Fraction of legal codewords: $2^m/2^n = 1/2^r$.
Interleaved parity: max burst detected = n (number of parity bits/columns).
CRC: append r zeros where r = degree of G(x) = (bits of G) − 1.

## 9. Full mesh links
$$\text{links} = \frac{n(n-1)}{2}$$

## 10. IP addressing → [[05.08 IP Addresses and Classful Addressing]] · [[05.09 Subnetting]]
$$\text{addresses in /n} = 2^{32-n}\qquad \text{usable hosts} = 2^{32-n} - 2$$
$$\text{block} = 2^{\lceil\log_2(\text{hosts}+2)\rceil}\qquad \text{prefix} = 32 - \log_2(\text{block})$$
$$\text{broadcast} = \text{network} + \text{block} - 1$$
$$\text{Class A: } 2^{7}\ \text{nets},\ 2^{24}\ \text{hosts}\quad \text{B: } 2^{14},\ 2^{16}\quad \text{C: } 2^{21},\ 2^{8}$$
Mask last-octet values: /25 128 · /26 192 · /27 224 · /28 240 · /29 248 · /30 252. Third octet: /20 240 · /21 248 · /22 252 · /19 224.

## 11. Distance vector
$$D_J(\text{dest}) = \min_{X \in \text{neighbours}}\big(\text{delay}(J \to X) + D_X(\text{dest})\big)$$

## 12. ALOHA throughput (textbook; lecture shows the graph)
$$S_{pure} = Ge^{-2G}\ (\max \approx 0.184)\qquad S_{slotted} = Ge^{-G}\ (\max \approx 0.368)$$

## 13. Sequence numbers
n-bit field → numbers 0 … $2^n - 1$ (3 bits → 0–7).
