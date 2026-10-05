---
type: lecture
course: CMIS 3114
lecture: 2
status: stub
tags: [networking]
aliases: [Physical Layer]
---
# The Physical Layer
> [!info] CMIS 3114 · Chapter 2
> [[CMIS 3114 Course Home]] · Exam prep: [[3114 Topic Map & Past Papers#Ch2 — The Physical Layer|Topic map]]
> ← [[3114-01 Introduction]] | [[3114-03 Data Link Layer]] →

> [!tip]- Exam focus — how often each topic appeared (6 papers, 2018/19–2023/24)
> - **5/6** — Multiplexing (FDM/TDM/WDM/OFDM)
> - **5/6** — Transmission media construction
> - **4/6** — Nyquist / Shannon / QAM rate
> - **4/6** — Network design diagram scenario ⚠️ *not in lecture notes*
> - **3/6** — Wireless adv/disadv (WLAN, wired vs wireless)
> - **3/6** — Telephone network vs Internet
> - **3/6** — Baseband vs passband (ASK/FSK)
> - **3/6** — GSM architecture
> - **2/6** — NRZ / NRZI drawing
> - **2/6** — Circuit vs packet switching
> - **2/6** — Handoff
> - **2/6** — WiFi vs WiMAX vs 4G / Li-Fi / MANET ⚠️ *not in lecture notes*
> - **1/6** — CDMA chip sequences
> - **1/6** — Satellites (GEO/MEO/LEO)
> - **1/6** — Dog/tape bandwidth problem ⚠️ *not in lecture notes*
>
> **Repeats to expect**
> - GSM diagram plus handoff (2019/20, 2021/22).
> - Nyquist/Shannon with QAM is a regular calculation question.

> [!question]- Past paper questions on this chapter (52)
> - **2018/19 Q1(c)** — Design a secure network diagram for 4 faculties (FoM 6 km away), FAS departments and a CMIS lab; media with justification ⚠️
> - **2018/19 Q3(d)(i)*** — 6 MHz TV channel, 6-level signals, noiseless: bits/s
> - **2018/19 Q3(d)(ii)*** — Same channel at 30 dB SNR: max rate
> - **2018/19 Q4(a)(I)** — 4 MHz cable channel, max rate with QAM-16
> - **2018/19 Q4(a)(II)** — Same with QAM-64
> - **2019/20 Q2(c)** — Telephone network vs Internet
> - **2019/20 Q4(a)(i)** — Physical construction of twisted pair (diagram)
> - **2019/20 Q4(a)(ii)** — Physical construction of optical fiber (diagram)
> - **2019/20 Q4(b)** — Baseband vs passband, with examples
> - **2019/20 Q4(c)** — NRZ and NRZI diagrams for 10111011000
> - **2019/20 Q4(d)(i)** — 8 kHz noiseless channel, QAM-16: max rate
> - **2019/20 Q4(d)(ii)** — Same channel at 30 dB SNR
> - **2019/20 Q5(a)** — Compare FDM, TDM and OFDM
> - **2019/20 Q5(b)** — Circuit vs packet switching
> - **2019/20 Q5(c)** — Identify GSM components (BSC, MSC, HLR, VLR, PSTN); landline-to-mobile call
> - **2019/20 Q5(d)** — Handoff procedure
> - **2019/20 Q8(b)** — Draw the network diagram (LANs, DNS, proxy, firewall, fiber ISP) ⚠️
> - **2020/21 Q4(a)** — Three wired media with advantages/disadvantages
> - **2020/21 Q4(b)** — Long-distance options: microwave, optical fiber, radio (advantages/disadvantages)
> - **2020/21 Q4(c)** — Compare TDM, WDM and OFDM
> - **2020/21 Q4(d)(i)** — CDMA: bipolar chip sequences for A–D
> - **2020/21 Q4(d)(ii)** — CDMA: bipolar transmission sequence for bits 1 1 0 1
> - **2020/21 Q4(d)(iii)** — How a 5th station listens to tower C
> - **2020/21 Q8(b)(i)** — Short note: WiFi vs WiMAX vs 4G
> - **2020/21 Q8(b)(ii)** — Short note: GSM mobile architecture
> - **2021/22 Q2(c)** — Telephone network vs Internet
> - **2021/22 Q4(a)(i)** — Twisted pair construction
> - **2021/22 Q4(a)(ii)** — Coaxial cable construction
> - **2021/22 Q4(a)(iii)** — Optical fiber construction
> - **2021/22 Q4(b)** — Baseband vs passband; techniques in each
> - **2021/22 Q4(c)** — Dog carrying 3 × 10 GB tapes at 18 km/h vs a 150 Mbps line; (i) speed doubled, (ii) capacity doubled, (iii) line rate doubled ⚠️
> - **2021/22 Q4(d)** — NRZ and NRZI for 11000110111
> - **2021/22 Q6(a)** — Compare FDM and TDM
> - **2021/22 Q6(b)** — Circuit vs packet switching with example usages
> - **2021/22 Q6(c)** — GSM components (BSC, MSC, HLR, VLR, PSTN) and their functions
> - **2021/22 Q6(d)** — Handoff procedure
> - **2022/23 Q2(c)** — Compare WiFi and Li-Fi ⚠️
> - **2022/23 Q3(d)(i)** — Noiseless 3 kHz binary channel cannot exceed 6000 bps
> - **2022/23 Q3(d)(ii)** — Same channel at 40 dB noise: highest rate
> - **2022/23 Q4(a)** — Wired vs wireless media: advantages/disadvantages, two examples of each
> - **2022/23 Q4(b)** — Baseband vs passband; techniques in each
> - **2022/23 Q4(c)** — How ASK and FSK send bits; efficiency of QAM-16 vs binary ASK/FSK
> - **2022/23 Q4(d)** — Three satellite types by orbit; which suits global Internet, why, and challenges
> - **2022/23 Q6(a)** — Compare FDM, TDM and OFDM
> - **2022/23 Q8(b)** — Network diagram with devices (DNS, proxy, firewall, fiber ISP) ⚠️
> - **2023/24 Q2(c)** — Telephone network vs Internet
> - **2023/24 Q4(a)(i)** — Twisted pair construction and usage
> - **2023/24 Q4(a)(ii)** — Optical fiber construction and usage
> - **2023/24 Q4(b)** — Compare TDM, WDM and OFDM
> - **2023/24 Q4(c)(i)** — Noiseless 4 kHz channel, QAM-64: max rate
> - **2023/24 Q4(c)(ii)** — Same channel at 20 dB SNR
> - **2023/24 Q8(c)** — Network diagram: router, DNS, proxy, firewall, fiber ISP, media ⚠️

## Topic checklist
- [ ] **2.1 Guided transmission media**
  - 2.1.1 Magnetic media
  - 2.1.2 Twisted pair
  - 2.1.3 Coaxial cable
  - 2.1.4 Fiber optics: total internal reflection, attenuation, fiber cable structure, LED vs semiconductor laser, fiber rings with active repeaters, global and Sri Lankan fiber / undersea cabling
- [ ] **2.2 Wireless transmission**
  - 2.2.1 Electromagnetic spectrum
  - 2.2.2 Radio transmission (VLF/LF/MF follow the earth's curvature; HF bounces off the ionosphere)
  - 2.2.3 Politics of the spectrum: ISM bands, TRC Sri Lanka
  - 2.2.4 Microwave, infrared & millimeter waves, lightwave (laser) transmission
  - 2.2.5 Google Loon balloons
- [ ] **2.3 Communication satellites**
  - 2.3.1 GEO (Clarke's 35,800 km prediction), properties (altitude, delay, number needed), satellite bands
  - 2.3.2 VSATs with a hub
  - 2.3.3 MEO (GPS, ~20,200 km)
  - 2.3.4 LEO: Iridium and Globalstar
  - 2.3.5 Satellites vs fiber
- [ ] **2.4 Public Switched Telephone Network (PSTN)**
  - 2.4.1 Structure (fully interconnected, centralized switch, two-level hierarchy); circuit route
  - 2.4.2 Major components: local loops, trunks, switching offices
  - 2.4.3 Politics: LATA, LEC, IXC, POP
  - 2.4.4 Local loop: modems, codecs, ADSL / DSL (discrete multitone), wireless local loops (LMDS)
- [ ] **2.5 Transmission of digital information (digital modulation)**
  - 2.5.1 **Baseband transmission**: NRZ, NRZI (USB)
  - 2.5.2 **Passband transmission**: ASK, FSK, PSK/BPSK, QPSK, QAM-16, QAM-64; modem constellations
- [ ] **2.6 Multiplexing**
  - 2.6.1 **FDM**: guard bands, AM radio, voice channels (3100/4000 Hz)
  - 2.6.2 **OFDM**: no guard bands, orthogonal subcarriers (WLAN, powerline, 4G)
  - 2.6.3 **WDM**
  - 2.6.4 **TDM**: guard time, T1 carrier (24 × 64 kbps = 1.544 Mbps), higher carriers
  - 2.6.5 **CDM/CDMA**: airport analogy, chips, chip sequences (bipolar ±1), 1 → sequence / 0 → negation, pairwise orthogonality, recovery with the normalised inner product S·C
- [ ] **2.7 Theoretical basis for data communication**
  - 2.7.1 Fourier analysis
  - 2.7.2 Bandwidth-limited signals, definition of bandwidth
  - 2.7.3 **Nyquist** (noiseless): max data rate = 2B log₂V; examples with BPSK/QPSK/QAM
  - 2.7.4 **SNR & decibels** (10 dB = 10, 20 dB = 100, 30 dB = 1000)
  - 2.7.5 **Shannon** (noisy): C = B log₂(1 + S/N); worked examples
- [ ] **2.8 Switching**
  - 2.8.1 Circuit switching (Strowger gear, modern exchanges)
  - 2.8.2 Packet switching (store-and-forward, out-of-order delivery, packet size limit)
  - 2.8.3 Message switching
  - 2.8.4 Circuit vs packet comparison table
- [ ] **2.9 Mobile telephone system**
  - 2.9.1 Generations 1G–5G overview
  - 2.9.2 1G IMTS; AMPS (cells, frequency reuse, smaller cells, MSC/MTSO)
  - 2.9.3 **Handoff** (fading signal, base station polls neighbours, ~300 ms)
  - 2.9.4 AMPS channel categories (control, paging, access, data)
  - 2.9.5 Analog → digital benefits; 2G D-AMPS (TDM)
  - 2.9.6 **GSM**: SIM card, architecture (BSC, MSC, VLR, HLR, PSTN), cell management, 124 channels × 8-slot TDM, framing
  - 2.9.7 2.5G EDGE/GPRS; 3G IMT-2000 (WCDMA/UMTS, CDMA2000); 4G LTE and WiMAX (802.16)
- [ ] **2.10 Cable television**
  - 2.10.1 CATV, HFC (Hybrid Fiber Coax), fiber nodes
  - 2.10.2 Internet over cable (shared medium vs ADSL)

## Notes
*Work through the checklist above. Write each topic in your own words, then tick it off.*


## Key definitions
> [!note] Term
>

## Summary (in my own words)


## Open questions
- [ ]
