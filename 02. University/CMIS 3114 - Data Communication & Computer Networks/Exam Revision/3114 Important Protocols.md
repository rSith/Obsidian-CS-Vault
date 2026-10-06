---
type: revision
course: CMIS 3114
status: complete
tags: [cmis3114, revision, protocols]
aliases: [CMIS 3114 protocols]
---
# CMIS 3114 · Important Protocols and Standards

> [!info] Protocols by layer: what each does, where it appears in the course, and whether it is lecture content. Home: [[00. CMIS 3114 Course Overview]]

| Layer | Protocol / standard | What it does | Course note | Source |
|---|---|---|---|---|
| Application | **HTTP** | Web request/response (TCP 80; HTTPS 443) | [[07.02 HTTP and the Web]] | Named in Ch1; detail textbook ⚠ |
| Application | **FTP** | File transfer | [[01.16 TCP-IP Reference Model]] | Ch1 |
| Application | **Telnet** | Remote terminal login | [[01.16 TCP-IP Reference Model]] | Ch1 |
| Application | **SMTP** | Sending/relaying e-mail | [[07.03 Email - SMTP, POP3 and IMAP]] | Named in Ch1; detail textbook ⚠ |
| Application | **DNS** | Domain name → IP address | [[07.01 DNS]] | Named in Ch1; detail textbook ⚠ |
| Application | POP3 / IMAP, DHCP | Retrieve mail; automatic IP configuration | [[07.03 Email - SMTP, POP3 and IMAP]] · [[05.10 ARP and DHCP (Beyond the Slides)]] | Textbook ⚠ |
| Transport | **TCP** | Reliable, connection-oriented byte stream; flow control | [[01.16 TCP-IP Reference Model]] · [[06.01 TCP vs UDP]] | Ch1 |
| Transport | **UDP** | Unreliable, connectionless datagrams; fast | [[01.16 TCP-IP Reference Model]] · [[06.01 TCP vs UDP]] | Ch1 |
| Network | **IP (IPv4)** | Best-effort connectionless datagram delivery; 32-bit addresses; header fields | [[05.07 IPv4 Header]] · [[05.08 IP Addresses and Classful Addressing]] | Ch1, Ch5 |
| Network | **MPLS** | Label-switched virtual circuits inside ISPs (20-bit label) | [[05.02 Datagram and Virtual-Circuit Networks]] | Ch5 |
| Network | Distance vector (e.g. RIP), flooding, shortest path | Routing algorithms | [[05.06 Distance Vector Routing]] · [[05.05 Flooding]] · [[05.04 Shortest Path Routing]] | Ch5 |
| Network/link | ARP | IP → MAC address on a LAN | [[05.10 ARP and DHCP (Beyond the Slides)]] | Textbook ⚠ |
| Data link | **Ethernet (IEEE 802.3)** | Bus-based broadcast LAN; CSMA/CD; unacknowledged connectionless service; preamble + length | [[01.09 Network Classification by Scale]] · [[04.03 CSMA and CSMA-CD]] | Ch1, Ch3, Ch4 |
| Data link | **Token Ring (IEEE 802.5)** | Ring LAN with token arbitration (4/16 Mbps) | [[01.09 Network Classification by Scale]] · [[04.04 Collision-Free Protocols]] | Ch1, Ch4 |
| Data link | **WiFi (IEEE 802.11 a/b/g/n)** | Wireless LAN; acknowledged connectionless; RTS/CTS; WEP → WPA2 (AES) | [[04.06 IEEE 802.11 WiFi]] | Ch1, Ch3, Ch4 |
| Data link | **Bluetooth (IEEE 802.15)** | PAN / system interconnection | [[01.09 Network Classification by Scale]] | Ch1 |
| Data link / PHY | **WiMAX (IEEE 802.16)** | Broadband wireless, OFDM, 2–10 GHz | [[04.07 Broadband Wireless - WiMAX, 4G and 5G]] | Ch1, Ch4 |
| Data link | PPP | Point-to-point link protocol (hybrid-model example, byte stuffing) | [[01.17 OSI vs TCP-IP and the Hybrid Model]] | Ch1 |
| Data link | Elementary protocols, sliding window, Go-Back-N, selective repeat | Reliable frame delivery | [[03.08 Elementary Data Link Protocols]] · [[03.09 Sliding Window and Go-Back-N]] | Ch3 |
| MAC | ALOHA, CSMA, CSMA/CD, bit-map, token passing, binary countdown, MACA | Channel access | [[04.00 MAC Sublayer]] | Ch4 |
| Physical | NRZ, NRZI (USB), ASK, FSK, PSK, QAM, ADSL (DMT), OFDM | Signalling / modulation | [[02.05 Digital Modulation - Baseband and Passband]] | Ch2 |
| Physical | T1, FDM, WDM, TDM, CDMA | Multiplexing | [[02.06 Multiplexing - FDM, OFDM, WDM and TDM]] | Ch2 |
| Cellular | AMPS, D-AMPS, GSM, EDGE/GPRS, WCDMA/UMTS, CDMA2000, LTE | Mobile generations | [[02.10 Mobile Telephone System and Handoff]] · [[02.11 GSM Architecture]] | Ch2 |
| Security | WEP, WPA, WPA2 (AES), TLS/HTTPS | Wireless and web security | [[04.06 IEEE 802.11 WiFi]] · [[08.01 Security Fundamentals, Cryptography and Firewalls]] | Ch4; textbook ⚠ |

## Data units by layer
| Layer | Unit | Added information |
|---|---|---|
| Application | Message | Application data |
| Transport | **Segment** (TCP) / datagram (UDP) | Ports, sequence numbers |
| Network | **Packet / datagram** | Source/destination IP, TTL |
| Data link | **Frame** | MAC addresses, trailer (CRC) |
| Physical | **Bits** | Signals |
