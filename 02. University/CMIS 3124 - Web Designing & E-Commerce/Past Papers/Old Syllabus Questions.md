---
type: past-paper
course: CMIS 3124
status: complete
tags: [cmis3124, past-paper, old-syllabus]
aliases: [Old syllabus questions, CMIS 3224 old questions]
---
# Old-Syllabus Questions (2017/18 – 2019/20 papers)

> [!warning] Low priority: not in the current lecture slides
> The three older CMIS 3224 papers were set on a **previous syllabus** covering **online banking, payment cards, ACID/ICES tests and classical ciphers**. **None of these topics is in the current slides**, and **none appeared in the 2023/24 paper.** The answers below come from **general knowledge and the study guide**, not from your current lecture decks. Check them against any old notes you have.
> Where a current lecture is the *nearest* topic, it is linked.

← [[Past Paper Mapping]]

## Index
| Paper | Q | Topic |
|---|---|---|
| 2019/20 | 2(a) | Advantages of online banking vs traditional banking (5) |
| 2019/20 | 2(b) | Critique of Sri Lankan online banking (5) |
| 2019/20 | 2(c) | SMS alerts for withdrawals only (5) |
| 2018/19 & 2017/18 | 2(a)–(d) / 4(a)–(d) | Online banking: facilities, security, drawbacks, improvements |
| 2018/19 | 3(b), 5(d) | Debit vs credit card abroad |
| 2018/19 | 3(d) | Rs 100 deducted and redeposited |
| 2017/18 | 3(a)(b) | ACID and ICES tests |
| 2017/18 | 3(c)(ii) | Mistakes of each stakeholder (double charge) |
| 2017/18 | 7(b)(c)(d) | ATM stranger; paying Rs 150,000; best card in the pandemic |
| 2019/20 | 7(a)–(d); 2017/18 Q5 | Cryptography: secret vs public key; Caesar and rail fence |

*(2019/20 Q1(c) tax in GBP/LKR and 2018/19 Q1(c)(ii) pandemic precautions are answered in [[PP 10 - E-Commerce]]; ActiveX and cyber vandalism are in [[PP 13 - Web Security and Cyber Threats]].)*

---

## A. Online banking in Sri Lanka

### 2019/20 · Q2(a) (5 marks)
> **Question:** Describe the advantages of online banking compared to traditional banking.

**Nearest lesson:** [[10.02 Traditional Commerce vs E-Commerce]]
- **24×7 access from anywhere**; no travel or queues; faster transfers and bill payments.
- **Lower costs** for banks and often lower fees for customers; **real-time** balances, statements and alerts.
- **Safer during a pandemic** (contactless); environmentally friendly (less paper).

### 2019/20 · Q2(b) (5 marks)
> **Question:** Critique the drawbacks/shortcomings in Sri Lankan online banking?

**Nearest lesson:** [[13.04 Phishing]]
- **Dependence on the Internet and power**; poor rural connectivity; **digital-literacy gaps**, especially among the elderly.
- **Phishing and smishing** scams targeting customers; OTP delays; occasional downtime.
- Complex or inconsistent interfaces across banks; limited services online; fees for some transfers; slow support for disputes.

### 2019/20 · Q2(c) (5 marks)
> **Question:** A bank notifies its customers about all their withdrawals via SMS, but not about deposits. Describe the advantages and disadvantages of this method.

- **Advantages:** immediate alerts about **unauthorised withdrawals**, so fraud is detected quickly; lower SMS cost; fewer messages to ignore.
- **Disadvantages:** customers **can't confirm** that salary or payments were received; an incomplete record leads to disputes; SMS can be **spoofed (smishing)**, so alerts must not contain links ([[13.04 Phishing]]).

### Online banking: facilities, security measures, drawbacks, improvements (2018/19 Q2(a)–(d); 2017/18 Q4(a)–(d))
> **Questions:** (a) Describe the facilities provided by online banking in Sri Lanka. (b) Explain the security measures of online banking in Sri Lanka. (c) What are the drawbacks/shortcomings in Sri Lankan online banking? (d) Describe the ways to improve the security of online banking in Sri Lanka.

**(a) Facilities:** balance and transaction history, e-statements; own-account and third-party transfers (including other banks through CEFTS/LankaPay); utility and credit-card bill payments; standing orders; mobile reloads; QR payments; fixed deposits; loan repayments; cheque-book requests; stop payments; card management; 24×7 mobile apps.

**(b) Security measures:** username/password **plus OTP** by SMS or token; **2FA/MFA**, biometrics in apps ([[10.07 Authentication in E-Commerce]]); **HTTPS/TLS** encryption and certificates ([[12.04 Tools for Ethical Web Development]]); **session timeouts** ([[02.05 Sessions and Cookies]]); lockout after failed attempts; SMS/email alerts; device registration; daily transfer limits; beneficiary registration with a cooling period; fraud monitoring.

**(c) Drawbacks:** see 2019/20 Q2(b) above.

**(d) Improvements:** strong authentication (MFA, OTP per transaction, biometrics); HTTPS everywhere; updated secure software; monitoring and logging plus **AI/ML fraud and anomaly detection** ([[13.11 AI and ML in Web Security]]); **Zero Trust** (continuous verification, least privilege, device checks: [[13.12 Zero Trust Security]]); **DevSecOps** for banking apps ([[13.13 DevSecOps]]); customer **awareness** campaigns against phishing; **alerts for all transactions**; session timeouts. See also [[13.10 Best Practices for Securing a Website]].

---

## B. Payment cards and banking scenarios

### Debit vs credit card abroad (2018/19 Q3(b) and Q5(d))
> **Question:** Suppose that you have a debit card and a credit card. Which card do you prefer to use when you go abroad [the United Kingdom]? Describe the reasons clearly. / Justify your answer.

- **Usually the credit card:** stronger **fraud protection and chargebacks**; fraud doesn't drain your **savings account** directly; widely accepted for hotel and car-rental deposits; emergency credit.
- Downsides: interest if not paid in full; foreign-transaction and currency-conversion fees.
- **Debit:** you spend only your own money (no debt), but stolen details can **empty your account**, and holds block funds.
- State your choice clearly, with reasons; mention **notifying the bank before travel**.

### 2018/19 · Q3(d)
> **Question:** When you update your bank passbook, you noticed that your bank deducted Rs 100.00 from your account on 01-Oct-2021 and it was redeposited on 02-Oct-2021. Describe the impact of this incident.

- Probably a **card verification hold** or a **bank error that was reversed**.
- A **temporary drop in the available balance** could make another payment bounce if the balance was tight.
- It could be a sign of an **unauthorised attempt**, so check with the bank; it shows the importance of **transaction alerts** and clear statements; a minor effect on trust.

### 2017/18 · Q3(a)(b): ACID and ICES tests
> **Question:** a) Describe ACID and ICES tests? What are the uses of these tests? b) Perform both the above tests for cash and cheques.

**ACID (for transactions):** **Atomicity** (all or nothing) · **Consistency** (from one valid state to another; no money created or lost) · **Isolation** (concurrent transactions don't interfere) · **Durability** (once committed, the transaction survives failures).

**ICES (for forms of money):** **Interoperability** (exchangeable and usable across systems) · **Conservation** (value is conserved and can't be copied or vanish) · **Economy** (low cost per transaction relative to its value) · **Scalability** (works for huge numbers of users and payments).

> [!warning]
> ICES is given as in the older e-commerce textbook (Treese & Stewart), according to the study guide. Verify it with old notes.

**Uses:** ACID judges whether a **payment transaction is reliable**; ICES judges whether something **works well as money**.

| | Cash | Cheques |
|---|---|---|
| **ACID** | Atomic (handed over or not); consistent; isolated; durable (immediate, final) | **Not atomic at once** (clearing takes days, may bounce); consistency risk if funds are insufficient; durable paper record |
| **ICES** | Highly interoperable locally; conserved physically (but can be lost, stolen or counterfeited); economical for small payments; **poor scalability** for remote or large payments | Interoperable through banks; value conserved **only when cleared**; processing cost and delay (weak economy); manual processing limits scalability |

### 2017/18 · Q3(c)(ii)
> **Question:** (Debit card charged twice at a supermarket) Describe the mistakes of each stake holder involved in this process.

- **Cashier/merchant:** processed the payment twice, or didn't check whether the first attempt went through.
- **Bank/payment processor:** no duplicate-transaction check; delayed or missing alert.
- **Customer:** didn't check the receipt or SMS immediately.
*(Steps to take and further action → [[PP 10 - E-Commerce]].)*

### 2017/18 · Q7(b)
> **Question:** Suppose you visit a new place where you need to withdraw some money from an ATM machine fixed in a room. When you entered the room, another person who is not using the ATM machine is also standing there. What are the steps to be taken at this situation?

- **Don't use the ATM** while they are there; leave and use another ATM, or wait in a public area.
- If you must use it: **shield the keypad**, check for **skimming devices**, don't accept "help", keep your phone ready, and report suspicious behaviour to the bank or security.

### 2017/18 · Q7(c)
> **Question:** During the corona pandemic period, you are afraid of withdrawing money from a bank or ATM machine. But you need to pay Rs. 150,000.00 to a consultant. Describe a suitable method to pay this amount to the particular person.

- An **online or mobile banking fund transfer** (CEFTS/LankaPay) to the consultant's account, protected by an **OTP**; keep the e-receipt or reference number.
- **Verify the account details by phone first** (to avoid payment-redirection scams); check the transfer limits.

### 2017/18 · Q7(d)
> **Question:** Among ATM cards, debit card and credit card, which one is the most suitable to use during the Corona pandemic period? Justify you answer.

- A **contactless debit or credit card** (credit gives better protection for online purchases): pay **without cash or touching keypads**, and shop online with delivery.
- An ATM-only card forces cash withdrawals, touching shared machines and queuing.

---

## C. Cryptography

### Secret-key vs public/private-key (2019/20 Q7(a), 6 marks; 2017/18 Q5(a))
> **Question:** Compare and contrast public/private key cryptography and the secret key cryptography.

**Nearest lesson:** [[15.04 Blockchain]] (public-key cryptography) · [[12.04 Tools for Ethical Web Development]] (AES)

| Secret-key (symmetric) | Public/private-key (asymmetric) |
|---|---|
| **One shared key** encrypts **and** decrypts (e.g. **AES**) | A **key pair**: the **public key** (shareable, like a blockchain address) encrypts or verifies; the **private key** (kept secret) decrypts or signs |
| **Fast**; good for bulk data | **Slower** |
| Problem: **securely sharing the key**; many keys are needed for many pairs | **Solves key distribution**; enables **digital signatures, non-repudiation, certificates** |

**Similar:** both provide confidentiality using mathematical algorithms and keys.
**In practice they are combined:** HTTPS/TLS uses asymmetric cryptography to exchange a symmetric **session key**, then AES for the data.

### Caesar cipher and rail fence (2019/20 Q7(b)–(d); 2017/18 Q5(b)–(d))
> **Questions:** 2019/20: (b) Describe the shortcomings/drawbacks in the rail fence and Caesar cipher cryptography. (c) Encrypt the following sentence using rail fence cryptography and the Caesar cipher cryptography: "Computer Science is an interesting subject". (d) Propose enhanced methods for both the rail fence and Caesar cipher cryptography.
> 2017/18: (b) Describe Caesar cypher and rail fence methods. (c)(i) Describe drawbacks of both methods. (ii) … propose your own innovative modified versions … (d) Encrypt the following message using Caesar cypher and rail fence methods: "Corona Virus gave me a good rest. What about you?"

**Descriptions**
- **Caesar cipher (substitution):** shift each letter a fixed number of places (the key; classically 3): A→D, B→E, …, Z→C. Decrypt by shifting back. *E.g. HELLO → KHOOR.*
- **Rail fence (transposition):** write the message in a **zigzag** across *n* rails, then read each rail left to right. The letters are unchanged; only their order moves. *E.g. HELLO on 2 rails → HLOEL.*

**Worked encryptions** (key: Caesar shift **3**; rail fence **3 rails**; letters only, converted to capitals, spaces and punctuation removed. **State your assumptions in the exam.**)

*"Computer Science is an interesting subject"* → `COMPUTERSCIENCEISANINTERESTINGSUBJECT` (37 letters)
- **Caesar (+3):** `FRPSXWHU VFLHQFH LV DQ LQWHUHVWLQJ VXEMHFW` (with spaces kept) / `FRPSXWHUVFLHQFHLVDQLQWHUHVWLQJVXEMHFW`
- **Rail fence (3 rails):**
```text
C   U   S   N   S   N   E   N   B   T
 O P T R C E C I A I T R S I G U J C
  M   E   I   E   N   E   T   S   E
```
  Ciphertext: `CUSNSNENBT` + `OPTRCECIAITRSIGUJC` + `MEIENETSE` = **`CUSNSNENBTOPTRCECIAITRSIGUJCMEIENETSE`**
- *(2 rails: `CMUESINESNNEETNSBETOPTRCECIAITRSIGUJC`)*

*"Corona Virus gave me a good rest. What about you?"* → `CORONAVIRUSGAVEMEAGOODRESTWHATABOUTYOU` (38 letters)
- **Caesar (+3):** `FRURQD YLUXV JDYH PH D JRRG UHVW. ZKDW DERXW BRX?`
- **Rail fence (3 rails):**
```text
C   N   R   A   E   O   S   A   O   O
 O O A I U G V M A O D E T H T B U Y U
  R   V   S   E   G   R   W   A   T
```
  Ciphertext: **`CNRAEOSAOOOOAIUGVMAODETHTBUYURVSEGRWAT`**
- *(2 rails: `CRNVRSAEEGORSWAAOTOOOAIUGVMAODETHTBUYU`)*

> [!note]
> These encryptions were computed and checked programmatically. With different assumptions (e.g. 2 rails, or keeping spaces) the answer changes, so always state the key and the rules.

**Shortcomings**
- **Caesar:** only **25 possible keys**, so **brute force is trivial**; **monoalphabetic** (the same letter always maps to the same letter), so **frequency analysis** breaks it; the key must be shared secretly.
- **Rail fence:** letters aren't changed, so **letter frequencies reveal the language**; **few possible keys** (the number of rails); the zigzag pattern is easy to reconstruct.
- **Both:** no integrity or authentication; easily broken by hand today.

**Enhanced versions (own proposals)**
- **Caesar:** vary the shift per letter using a **keyword** (Vigenère idea) or the letter's position; include digits and symbols; use different shifts for vowels and consonants.
- **Rail fence:** read the rails in a **secret key order**; vary the number of rails through the message; apply several passes.
- **Combine both** (substitution then transposition = a **product cipher**), so neither frequency analysis nor pattern analysis works alone. Show a small example.
