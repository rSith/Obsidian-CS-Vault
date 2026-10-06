---
type: past-paper
course: CMIS 3124
lesson: L15
status: complete
tags: [cmis3124, past-paper, cmis3124/L15]
---
# PP 15 · Future Trends in Web and E-Commerce: Past-Paper Questions

> [!info] Lesson: [[15.00 Future Trends]] · Summary: [[15.99 Summary - Future Trends]] · [[Past Paper Mapping]]

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2023/24 | 3(a) | What is a PWA; how is it different from platform-specific apps | 3 |
| 2023/24 | 3(c) | How blockchain works + 2 benefits | 5 |
| 2023/24 | 3(d) | How AR and VR transform web design | 5 |

> [!note]
> The old cryptography questions (2017/18 Q5(a), 2019/20 Q7(a): secret-key vs public/private-key) are answered in [[Old Syllabus Questions]], using public-key cryptography from [[15.04 Blockchain]].

---

## 2023/24 · Q3(a) (3 marks)
> **Question:** What is a Progressive Web App (PWA)? How is it different from Platform-specific apps?

**Lesson:** [[15.03 Progressive Web Apps]] · [[15.02 Platform-Specific Apps vs Websites]]

### Expected answer
A **PWA** is an app **built using web platform technologies (HTML, CSS, JavaScript)** that provides a **user experience like a platform-specific app**, combining the best of websites and native apps. It can be **installed**, runs **full screen without the browser UI**, works **offline and in the background** (push messages, notifications) through a **service worker**, and is described to the browser by a **web app manifest**.

**Differences from platform-specific apps:**
- Native apps are built for **one OS or device class** (iOS, Android) with the **vendor's SDK**, so each platform needs separate code. A PWA uses **one codebase** for all OSs and devices.
- Native apps are distributed through the **app store** (with approval). A PWA is **accessed directly from the web via a URL**, updates instantly, and *can* also be listed in stores.
- Native apps have the **deepest OS and hardware integration** and a dedicated UI. PWAs use **progressive enhancement**: they work as a normal website where advanced features aren't supported.

---

## 2023/24 · Q3(c) (5 marks)
> **Question:** Describe how blockchain works and list two (02) benefits of it.

**Lesson:** [[15.04 Blockchain]]

### Expected answer
A blockchain is a **decentralised, distributed database (ledger)** stored across many computers (nodes), which makes it resistant to tampering.
1. **Records transactions as blocks:** each transaction is recorded as a block containing who, what, when, where, the amount and any conditions, with a **timestamp** that fixes the chronological order.
2. **Connects blocks together:** each block is linked to the previous one by a **cryptographic hash**. Because a block's hash includes the previous block's data, altering one block would change every later block.
3. **Builds an irreversible chain:** nodes validate every new block through **consensus algorithms** such as **Proof of Work** or **Proof of Stake**, and each new block strengthens the chain.
4. **Ensures trust and immutability:** past transactions become practically impossible to change, giving a **transparent, tamper-proof ledger** that prevents fraud.

**Two benefits:**
- **Enhanced security:** transactions are validated by agreement and are immutable; not even a system administrator can delete them.
- **Better traceability:** a transparent audit trail of an asset's journey (provenance), useful in supply chains.
*(Others: greater trust, increased efficiency with no reconciliation, automated transactions through smart contracts.)*

---

## 2023/24 · Q3(d) (5 marks)
> **Question:** Explain how AR and VR transform web design.

**Lesson:** [[15.05 AR and VR for the Web]]

### Expected answer
**Augmented reality (AR)** overlays digital content onto the real world. **Virtual reality (VR)** creates fully immersive digital environments. Together they make web experiences more engaging and dynamic:
1. **Enhanced user engagement:** immersive experiences increase time spent on the site and create a stronger emotional connection.
2. **Real-world integration:** AR lets customers see how a product (e.g. furniture) looks in their own home, try on clothes virtually, or view information overlaid on real locations.
3. **Immersive storytelling:** VR provides virtual showrooms and stores, 3D property tours, tourism previews of hotels and cultural sites, and interactive training and education.
4. **Improved product development:** teams visualise and refine designs together in a shared virtual space.
5. **Data insights and cost-effectiveness:** AR interactions reveal user preferences that help optimise the site, and virtual showcases reduce the need for physical showrooms and demonstrations.
