---
type: lecture
course: CMIS 3114
lecture: 4
status: stub
tags: [networking]
aliases: [MAC Sublayer, Medium Access Control]
---
# The Medium Access Control (MAC) Sub-layer
> [!info] CMIS 3114 · Chapter 4
> [[CMIS 3114 Course Home]] · Exam prep: [[3114 Topic Map & Past Papers#Ch4 — The MAC Sublayer|Topic map]]
> ← [[3114-03 Data Link Layer]] | [[3114-05 Network Layer]] →

> [!tip]- Exam focus — how often each topic appeared (6 papers, 2018/19–2023/24)
> - **5/6** — ALOHA vs CSMA variants
> - **4/6** — Hidden/exposed terminals + MACA
> - **3/6** — CSMA/CD states
> - **3/6** — Collision-free (bit-map / binary countdown)
> - **2/6** — WiFi vs WiMAX vs 4G / Li-Fi / MANET ⚠️ *not in lecture notes*
>
> **Repeats to expect**
> - Binary countdown with codes 0001/0101/1000/1001 (identical in 2019/20 and 2022/23).

> [!question]- Past paper questions on this chapter (23)
> - **2019/20 Q7(a)** — Slotted ALOHA vs p-persistent CSMA
> - **2019/20 Q7(b)** — CSMA/CD states (transmission/contention/idle) diagram
> - **2019/20 Q7(c)** — Binary countdown with codes 0001, 0101, 1000, 1001
> - **2019/20 Q7(d)** — Hidden and exposed terminals; solution protocol
> - **2020/21 Q1(d)** — "In this information age, the security of data is critical" ⚠️
> - **2020/21 Q2(c)** — Advantages/disadvantages of wireless LAN
> - **2020/21 Q6(b)** — Pure ALOHA vs 1-persistent CSMA
> - **2020/21 Q6(c)** — Hidden and exposed terminal problems; solution
> - **2020/21 Q8(b)(i)** — Short note: WiFi vs WiMAX vs 4G
> - **2021/22 Q7(a)** — Slotted ALOHA vs p-persistent CSMA
> - **2021/22 Q7(b)** — Collision detection vs collision-free protocols (advantages/disadvantages)
> - **2021/22 Q7(c)** — Hidden and exposed terminal problems
> - **2021/22 Q7(d)** — Solution to 7(c)
> - **2022/23 Q2(b)(i)** — Ring topology: diagram, device, media, usage
> - **2022/23 Q2(c)** — Compare WiFi and Li-Fi ⚠️
> - **2022/23 Q2(d)** — MANET usage scenario, advantages and disadvantages ⚠️
> - **2022/23 Q6(b)** — Pure ALOHA vs p-persistent CSMA with diagrams
> - **2022/23 Q6(c)** — Collision-free protocols; basic bit-map protocol
> - **2022/23 Q6(d)** — Binary countdown with 0001, 0101, 1000, 1001
> - **2023/24 Q6(a)** — Slotted ALOHA vs 1-persistent CSMA with diagrams
> - **2023/24 Q6(b)** — CSMA/CD states diagram and implementation
> - **2023/24 Q6(c)** — Hidden and exposed terminal problems with a diagram
> - **2023/24 Q6(d)** — Solution and how it works

## Topic checklist
- [ ] **4.1** DLL = LLC + MAC; MAC address (IEEE 802.3); broadcast / multi-access channels
- [ ] **4.2** **Channel allocation problem**: static (TDM/FDM/WDM) vs dynamic, with 5 assumptions (independent traffic, single channel, observable collisions, continuous/slotted time, carrier sense or not)
- [ ] **4.3** **ALOHA**: origin, contention systems, **Pure ALOHA**, **Slotted ALOHA**
- [ ] **4.4** **CSMA**: 1-persistent, non-persistent, p-persistent; **CSMA/CD** (contention / transmission / idle states); utilisation vs load comparison
- [ ] **4.5** **Collision-free protocols**: why collisions are bad; basic **bit-map** protocol; **token passing** (token ring/bus); **binary countdown**
- [ ] **4.6** **Wireless LAN protocols**: **hidden terminal** and **exposed terminal** problems; **MACA** (RTS/CTS)
- [ ] **4.7** **IEEE 802.11 (WiFi)**
  - Infrastructure vs ad hoc mode
  - Protocol stack: 802.11b (DSSS, 2.4 GHz, 11 Mbps), 802.11a (OFDM, 5 GHz, 54 Mbps), 802.11g, 802.11n (MIMO, 600 Mbps)
  - Frame structure (data, control, management)
  - Services: association, reassociation, disassociation, authentication, WEP/WPA/WPA2 (AES), distribution, integration, data delivery, privacy, QoS scheduling, transmit power control, dynamic frequency selection
- [ ] **4.8** **Broadband wireless**: 802.16 WiMAX (OFDM, 2–10 GHz, non-line-of-sight); compare WiFi, 4G LTE and 5G

## Notes
*Work through the checklist above. Write each topic in your own words, then tick it off.*


## Key definitions
> [!note] Term
>

## Summary (in my own words)


## Open questions
- [ ]
