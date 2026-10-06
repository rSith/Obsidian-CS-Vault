---
type: revision
course: CMIS 3114
status: complete
tags: [cmis3114, revision, definitions]
aliases: [CMIS 3114 definitions]
---
# CMIS 3114 · Important Definitions

> [!info] Write these word for word where marked *(lecture)*. Grouped by chapter. Home: [[00. CMIS 3114 Course Overview]]

## Chapter 1: Introduction
| Term | Definition |
|---|---|
| Computer network *(lecture)* | "A collection of autonomous computers interconnected by a single technology"; "the interconnection of 2 or more independent computers" able to exchange information |
| Internet | A network of networks; a collection of networks interconnected by routers |
| World Wide Web | A distributed system/application running on top of the Internet (not a network) |
| Distributed system *(lecture)* | A collection of independent computers that appears to its users as a single coherent system; built on a network using middleware |
| Middleware | Software layer above the OS that implements the distributed system's single model |
| Client–server model | Servers store data and do work; clients send requests and wait for replies (two processes) |
| Peer-to-peer | No fixed division into clients and servers; each host acts as both (e.g. BitTorrent) |
| Topology *(lecture)* | The way network elements are interconnected (the physical layout of the network) |
| Broadcast link | A single channel shared by all machines; packets carry an address field |
| Point-to-point link | Individual connections between pairs of machines; packets may pass intermediate routers |
| Unicast / broadcast / multicast | One specific receiver / all machines / a subset of machines |
| BAN / PAN / LAN / MAN / WAN | Body / one person / building–campus / city / country–world networks |
| Store-and-forward (packet-switched) subnet | Packets are received whole at each router, stored until the output line is free, then forwarded |
| Protocol *(lecture)* | A set of rules that specify the format and meaning of messages exchanged between computers across a network |
| Protocol suite / hierarchy | A set of related protocols designed for compatibility |
| Layering | Dividing network functions into a stack of layers, each offering services to the one above and hiding its implementation ("divide and conquer") |
| Peers *(lecture)* | The entities comprising the corresponding layers on different machines |
| Interface | Defines the services one layer offers the layer above |
| Service *(lecture)* | A set of primitives (operations) that a layer provides to the layer above it |
| Network architecture | A set of layers and protocols |
| Protocol stack | A list of protocols used by a system, one protocol per layer |
| Connection-oriented service | Establish a connection, use it, release it (like the telephone); order preserved |
| Connectionless service | Each message carries the full address and is routed independently (like the post office) |
| Datagram service | Unreliable, unacknowledged connectionless service |
| Service primitive | An operation (LISTEN, CONNECT, ACCEPT, RECEIVE, SEND, DISCONNECT) a user process uses to access a service |
| OSI | Open Systems Interconnection: ISO's 7-layer reference model for connecting open systems |
| TCP | Reliable, connection-oriented protocol delivering a byte stream; guarantees delivery and flow control |
| UDP | Unreliable, connectionless protocol for applications providing their own reliability / prompt delivery |
| IoT ⚠ | Network of everyday objects with sensors, actuators and connectivity that exchange data over the Internet |

## Chapter 2: Physical layer
| Term | Definition |
|---|---|
| Guided media | Signals travel along a physical path (twisted pair, coax, fibre) |
| Total internal reflection | Light hitting the core/cladding boundary above the critical angle reflects back into the core |
| Bandwidth *(lecture)* | The width of the frequency range transmitted without being strongly attenuated |
| Digital modulation *(lecture)* | The process of converting between bits and the signals that represent them |
| Baseband transmission | Signal occupies frequencies from zero to a maximum; bits converted directly (NRZ, NRZI) |
| Passband transmission | Amplitude, phase or frequency of a carrier is regulated; signal sits in a band around the carrier |
| NRZ | +V for 1, −V for 0 |
| NRZI | 1 = transition, 0 = no transition (USB) |
| ASK / FSK / PSK | Two amplitudes / two or more frequencies / phase shifts represent bits |
| QAM | Combinations of amplitude and phase; QAM-16 = 4 bits/symbol, QAM-64 = 6 |
| Multiplexing | Sharing one link among many signals |
| FDM | Spectrum divided into frequency bands, each user owning a band; guard bands |
| OFDM | Overlapping orthogonal subcarriers, no guard bands |
| WDM | FDM on fibre using different wavelengths |
| TDM | Each user gets the entire bandwidth for a short time slot; guard time |
| CDMA | Users share the same band at the same time using orthogonal chip sequences |
| Chip sequence | A station's unique m-chip bipolar code |
| SNR | Signal power ÷ noise power, usually in dB |
| Circuit switching | A dedicated physical path is set up end to end before communication |
| Packet switching | Packets sent as soon as available, no dedicated path, store-and-forward |
| Local loop / trunk / switching office | Analog twisted pair to homes / digital fibre between offices / where calls are switched |
| Handoff | Transferring a call to a new base station when the signal fades (~300 ms) |
| SIM | Subscriber Identity Module: activates the handset, holds secret codes for identification and encryption |
| BSC / MSC / VLR / HLR | Controls cell radio resources and handoff / routes calls, connects to PSTN / local database of nearby mobiles / last known location of each mobile |
| HFC | Hybrid Fiber Coax: fibre for long-haul runs, coax to the houses |
| ISM bands | Unlicensed bands for industrial, scientific and medical use (e.g. 2.4 GHz) |

## Chapter 3: Data link layer
| Term | Definition |
|---|---|
| Frame | DLL unit: header + payload (packet) + trailer |
| Framing | Breaking the bit stream into discrete frames so boundaries can be found |
| Byte stuffing | Inserting an ESC before any FLAG or ESC byte in the data |
| Bit stuffing | Inserting a 0 after five consecutive 1s in the data (flag 01111110) |
| Error control | ACKs, timers and sequence numbers so every frame is delivered exactly once |
| Flow control | Preventing a fast sender from swamping a slow receiver (feedback- or rate-based) |
| Single-bit vs burst error | Isolated error vs a run of consecutive errors |
| Error detection vs correction (FEC) | Deduce that an error occurred and ask for resend vs deduce the original data |
| Codeword *(lecture)* | An n-bit unit containing data and check bits; (n, m) codeword |
| Code rate | m/n, the fraction of the codeword that carries information |
| Parity bit | Bit making the number of 1s even (or odd) |
| Checksum | Group of check bits associated with a message; receiver's sum must be zero |
| CRC / polynomial code | Check bits = remainder after modulo-2 division by a generator polynomial |
| Hamming distance *(lecture)* | The number of bit positions in which two codewords differ |
| Hamming code | Error-correcting code with check bits at positions 1, 2, 4, 8, …; syndrome = error position |
| Error syndrome | The set of failed check results; gives the position of the wrong bit |
| Sliding window | Sender/receiver windows of allowed sequence numbers that move as ACKs/frames arrive |
| Go-Back-N | Receiver window 1: on error, discard later frames; sender resends from the bad frame |
| Selective repeat | Large receiver window: buffer good frames, resend only the bad one |

## Chapter 4: MAC sublayer
| Term | Definition |
|---|---|
| LLC / MAC | Logical Link Control (faces network layer) / Medium Access Control (faces physical layer, MAC address) |
| Multi-access (broadcast) channel | One channel shared by many stations |
| Contention system *(lecture)* | Multiple users share a common channel in a way that can lead to conflicts |
| Collision | Two frames overlapping in time, garbling both |
| Pure ALOHA | Transmit whenever data is ready; random wait after a collision |
| Slotted ALOHA | Transmit only at the start of a time slot |
| Carrier sense protocol | Stations listen for a carrier before acting |
| 1-persistent / non-persistent / p-persistent CSMA | Send immediately when idle / random wait when busy / send with probability p in a slot |
| CSMA/CD | Listen while transmitting and abort on collision; contention, transmission, idle states |
| Collision-free protocol | Resolves who sends before transmitting so frames never collide |
| Bit-map protocol | N reservation slots, one bit per station |
| Token passing | A token circulates; only its holder may send |
| Binary countdown | Stations broadcast addresses bit by bit (ORed); highest address wins |
| Hidden terminal problem *(lecture)* | A station can't detect a competitor because it is too far away → collision at the receiver |
| Exposed terminal problem *(lecture)* | A station hears a transmission and falsely refrains from sending |
| MACA | RTS/CTS handshake: stations hearing the CTS stay silent during the data |
| Infrastructure / ad hoc mode | Clients associate with an AP / computers send directly to each other |
| WiMAX | IEEE 802.16 broadband wireless using OFDM at 2–10 GHz, non-line-of-sight |

## Chapter 5: Network layer
| Term | Definition |
|---|---|
| Network layer job *(lecture)* | Getting packets from the source all the way to the destination, possibly over many hops |
| Datagram | A packet in a connectionless network carrying the full destination address |
| Virtual circuit | A path set up before data; packets carry a short VC identifier |
| Label switching | Routers replace incoming labels with outgoing labels to distinguish connections |
| MPLS | Multi-Protocol Label Switching: connection-oriented service in ISPs, 20-bit label |
| Routing algorithm *(lecture)* | Software deciding which output line an incoming packet is transmitted on |
| Forwarding vs routing | Look up the table for each packet vs fill in and update the tables |
| Non-adaptive vs adaptive | Routes computed offline vs reacting to topology/traffic changes |
| Optimality principle | If J is on the optimal path from I to K, the optimal J→K path follows the same route |
| Sink tree | The set of optimal routes from all sources to one destination; a tree rooted at the destination |
| Flooding | Every packet sent on every line except the arrival line |
| Distance vector routing | Each router keeps (best distance, outgoing line) per destination and exchanges vectors with neighbours |
| Prefix | A contiguous block of IP addresses; written as lowest address/length |
| Subnet mask | 1s over the network portion; ANDed with an address to get the network |
| Subnet | Part of an address block used internally as a separate network |
| TTL | IPv4 header counter limiting packet lifetime |
