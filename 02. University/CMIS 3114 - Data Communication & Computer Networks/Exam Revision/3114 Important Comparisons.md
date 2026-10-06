---
type: revision
course: CMIS 3114
status: complete
tags: [cmis3114, revision, comparisons]
aliases: [CMIS 3114 comparisons]
---
# CMIS 3114 · Important Comparisons

> [!info] Every "X vs Y" the exams ask, plus the ones requested for revision. 🔴/🟡/🟢 = past-paper priority. Home: [[00. CMIS 3114 Course Overview]]

## 🔴 Telephone network vs Internet → [[02.04 Public Switched Telephone Network]]
| Telephone network | Internet |
|---|---|
| Voice; circuit switching; call setup | Data; packet switching; no setup |
| Fixed bandwidth, wasted when idle | Dynamic, shared bandwidth |
| Same path, in order; switch crash drops call | Independent routes, may reorder; reroutes |
| Central switches, simple phones; per-minute billing | Smart hosts, simple routers; flat-rate billing |
*Similar:* global WANs, same copper/fibre/microwave, multiplexed trunks, hierarchical, converging (DSL, VoIP).

## 🔴 Circuit vs packet switching → [[02.09 Switching - Circuit, Message and Packet]]
| Circuit | Packet |
|---|---|
| Setup required, dedicated path | No setup, no dedicated path |
| Fixed bandwidth; congestion at setup | Dynamic; congestion per packet |
| In order; switch crash fatal | May reorder; robust |
| No store-and-forward; per-minute charge | Store-and-forward; per-packet charge |

## 🔴 Connection-oriented vs connectionless → [[01.13 Connection-Oriented and Connectionless Services]]
| Connection-oriented | Connectionless |
|---|---|
| Setup → transfer → release (telephone) | Each message independently addressed (post) |
| Order kept; usually reliable | May reorder; best effort |
| TCP, VC networks, MPLS | UDP, IP, Ethernet |

## 🔴 TCP vs UDP → [[06.01 TCP vs UDP]]
| TCP | UDP |
|---|---|
| Connection-oriented, reliable, ordered byte stream | Connectionless, unreliable datagrams |
| Flow + congestion control; 20-byte header | None; 8-byte header; fast |
| HTTP, SMTP, FTP | DNS, VoIP, streaming, games |

## 🔴 OSI vs TCP/IP → [[01.17 OSI vs TCP-IP and the Hybrid Model]]
| OSI | TCP/IP |
|---|---|
| 7 layers; model before protocols | 4 layers; protocols before model |
| Service/interface/protocol distinct | Not distinguished |
| Network: both modes; transport: CO only | Network: CL only; transport: both |
| Protocols rarely used | Protocols used everywhere |

## 🔴 LAN vs MAN vs WAN → [[01.09 Network Classification by Scale]]
| LAN | MAN | WAN |
|---|---|---|
| Building/campus, private | City | Country/world |
| Fast, low delay | Cable TV / fibre rings | Routers, store-and-forward, Internet |

## 🔴 Broadcast vs point-to-point → [[01.07 Network Hardware and Transmission Technology]]
| Broadcast | Point-to-point |
|---|---|
| One shared channel, address field checked | Pairs of machines, routing via intermediate nodes |
| Small/local networks (WiFi) | Large networks (WAN) |

## 🔴 Star vs partial mesh (and other topologies) → [[01.08 Network Topologies]]
| Star | Partial mesh |
|---|---|
| Central switch; single point of failure | Redundant paths |
| Cheap, easy to manage; labs/offices | Costly, needs routing; city backbones |

## 🔴 P2P vs client–server → [[01.04 Client-Server and Peer-to-Peer Models]]
| Client–server | P2P |
|---|---|
| Fixed roles, central data, admin | Every node both, distributed, no central control |
| Server bottleneck / single point of failure | Scales, robust, harder to secure |

## 🔴 Distributed system vs computer network / client–server → [[01.02 Distributed Systems]]
| Distributed system | Network / client–server |
|---|---|
| Machines appear as one coherent system (middleware) | Users see the actual machines / clients address a server |

## 🔴 FDM vs TDM vs OFDM vs WDM → [[02.06 Multiplexing - FDM, OFDM, WDM and TDM]]
| FDM | TDM | OFDM | WDM |
|---|---|---|---|
| Frequency bands, guard bands | Time slots, guard time, sync | Orthogonal subcarriers, no guard bands | Wavelengths on fibre |
| Radio/TV | T1, GSM | WiFi, 4G | Fibre backbones |

## 🔴 Twisted pair vs coax vs fibre; wired vs wireless → [[02.01 Guided Transmission Media]] · [[02.02 Wireless Transmission]]
| Twisted pair | Coax | Fibre |
|---|---|---|
| Cheapest, ~100 m, noise | Shielded, cable TV | Highest bandwidth, EMI-immune, long distance |

| Wired (guided) | Wireless (unguided) |
|---|---|
| Fast, secure, reliable; no mobility; cabling | Mobile, easy deployment; interference, less secure |

## 🔴 ALOHA and CSMA variants → [[04.03 CSMA and CSMA-CD]]
| Pure ALOHA | Slotted ALOHA | 1-persistent | Non-persistent | p-persistent |
|---|---|---|---|---|
| Any time, no sensing, ~18% | Slot start, ~37% | Sense; send immediately when idle | Busy → wait random time | Idle → send with prob. p |

## 🔴 Hidden vs exposed terminal → [[04.05 Hidden and Exposed Terminals and MACA]]
| Hidden | Exposed |
|---|---|
| Can't hear competitor → collision at receiver | Hears harmless sender → needless waiting |

## 🟡 CSMA/CD vs CSMA/CA (textbook) · collision detection vs collision-free
| CSMA/CD | CSMA/CA |
|---|---|
| Wired Ethernet; detect and abort | WiFi; avoid with backoff, RTS/CTS, ACKs |

| Collision detection | Collision-free |
|---|---|
| Simple, good at light load; wasted collisions, variable delay | No collisions, predictable; overhead (bits/token) |

## 🟡 Baseband vs passband → [[02.05 Digital Modulation - Baseband and Passband]]
| Baseband | Passband |
|---|---|
| 0 Hz upward; NRZ, NRZI; wires | Band around a carrier; ASK, FSK, PSK, QAM; wireless/optical |

## 🟡 Analog vs digital transmission (2G benefits) → [[02.10 Mobile Telephone System and Handoff]]
| Analog (1G) | Digital (2G+) |
|---|---|
| Continuous signal; no compression/encryption | Digitised, compressed (more capacity), encrypted (security), new services (SMS) |

## 🟡 Error detection vs correction → [[03.04 Error Detection - Parity, Interleaving and Checksum]]
| Detection | Correction (FEC) |
|---|---|
| Little redundancy, retransmit; parity, checksum, CRC | Much redundancy, fix at receiver; Hamming |

## 🟡 Go-Back-N vs Selective Repeat vs Stop-and-Wait → [[03.09 Sliding Window and Go-Back-N]]
| Stop-and-wait | Go-Back-N | Selective repeat |
|---|---|---|
| Window 1 | Receiver window 1; discard and resend all after error | Large receiver window; buffer, resend only bad frame |

## 🟡 Flow control vs congestion control → [[03.03 Error Control and Flow Control]]
| Flow control | Congestion control |
|---|---|
| Protects the receiver; point-to-point | Protects the network; global |

## 🟡 Datagram vs virtual-circuit networks → [[05.02 Datagram and Virtual-Circuit Networks]]
| Datagram | Virtual circuit |
|---|---|
| No setup; full address; no router state | Setup; short VC id; table entry per VC |
| Independent routing; failure loses only in-transit packets | Fixed route; failure kills VCs |
| QoS/congestion control difficult | Easy if resources reserved |

## 🟡 Static vs adaptive routing; distance vector vs link state → [[05.03 Routing Algorithms and the Sink Tree]] · [[05.04 Shortest Path Routing]]
| Static | Adaptive |
|---|---|
| Offline, loaded at boot | Reacts to topology/traffic |

| Distance vector | Link state |
|---|---|
| Vectors to neighbours; slow, count-to-infinity | Link costs flooded, Dijkstra; fast |

## 🟡 Wi-Fi vs Li-Fi; Wi-Fi vs WiMAX vs 4G → [[01.05 Mobile Computing and Wireless Networks]] · [[04.07 Broadband Wireless - WiMAX, 4G and 5G]]
| Wi-Fi | Li-Fi |
|---|---|
| Radio, through walls, interference | Visible light, room only, secure, LOS |

| Wi-Fi | WiMAX | 4G |
|---|---|---|
| WLAN, unlicensed, metres | MAN, OFDM 2–10 GHz, km | Cellular, licensed, mobility |

## 🟢 IPv4 vs IPv6 (textbook) → [[05.07 IPv4 Header]]
| IPv4 | IPv6 |
|---|---|
| 32-bit, dotted decimal, header checksum | 128-bit, hex, fixed 40-byte header |

## 🟢 Hub vs switch vs router; bridge vs switch (textbook) → [[05.10 ARP and DHCP (Beyond the Slides)]]
| Hub | Switch / bridge | Router |
|---|---|---|
| L1, repeats to all ports | L2, forwards by MAC, learns addresses | L3, forwards between networks by IP |

## 🟢 Symmetric vs asymmetric (cryptography, textbook) → [[08.01 Security Fundamentals, Cryptography and Firewalls]]
| Symmetric | Asymmetric |
|---|---|
| One shared key, fast (AES) | Public/private key pair, slow (RSA) |

## 🟢 GEO vs MEO vs LEO → [[02.03 Communication Satellites]]
| GEO | MEO | LEO |
|---|---|---|
| 35,800 km, 3 sats, ~270 ms | ~20,000 km, GPS | ~1,000 km, many sats, low delay |
