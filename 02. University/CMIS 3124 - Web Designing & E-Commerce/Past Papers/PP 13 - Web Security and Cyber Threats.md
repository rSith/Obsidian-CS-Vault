---
type: past-paper
course: CMIS 3124
lesson: L13
status: complete
tags: [cmis3124, past-paper, cmis3124/L13]
---
# PP 13 · Web Security and Cyber Threats: Past-Paper Questions

> [!info] Lesson: [[13.00 Web Security and Cyber Threats]] · Summary: [[13.99 Summary - Web Security and Cyber Threats]] · [[Past Paper Mapping]]
> Online-banking and card-handling scenarios with no current lecture are in [[Old Syllabus Questions]].

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2023/24 | 3(e) | AI and ML to enhance web security | 5 |
| 2023/24 | 6(b) | Secure DevOps vs DevOps | 4 |
| 2023/24 | 6(c) | Zero Trust Security Model | 4 |
| 2023/24 | 6(d)(i)–(iv) | Short notes: SQLi, XSS, code injection, DoS | 8 |
| 2019/20 | 8(a)(i)–(vi) | Sniffers, backdoors, cyber vandalism, spoofing, DoS, MITM | 3 × 6 |
| 2018/19 | 5(b) | Impacts of sniffer programs | – |
| 2018/19 | 5(c) | Remembering passwords while travelling to the UK | – |
| 2017/18 | 6(a) | 3 threats on a client computer | – |
| 2017/18 | 6(c) | Effects of ActiveX controls | – |
| 2017/18 | 6(d) | Why confirm "not a robot" | – |
| 2017/18 | 7(a) | Two instances of credit-card robberies | – |

---

## 2023/24 · Q3(e) (5 marks)
> **Question:** Concisely explain how Artificial Intelligence (AI) and Machine Learning (ML) could be utilized to enhance web security.

**Lesson:** [[13.11 AI and ML in Web Security]] · [[14.04 Emerging AI Trends]]

### Expected answer
1. **Automated threat detection:** ML models learn normal patterns and flag **anomalies** in network traffic, user behaviour and system activity. They detect **new and emerging malware**, **phishing emails** (suspicious language and senders) and **intrusions** from traffic and logs.
2. **Incident response:** AI **automates responses**: isolating compromised systems, blocking malicious traffic, triggering alerts. It provides **threat intelligence** on attackers' tactics, techniques and procedures (TTPs), and **prioritises vulnerabilities** by severity.
3. **Prevention:** **predictive analytics** on historical data anticipate attacks; **behavioural analysis** spots unusual logins or data access; **security automation** frees experts for complex threats.
4. **Web-specific uses:** real-time **DDoS detection**, **fraud prevention** (fake accounts, payment fraud), bot detection.
5. **Limitations:** needs large, high-quality training data; data bias; **adversarial attacks** designed to fool the AI; limited explainability.

---

## 2023/24 · Q6(b) (4 marks)
> **Question:** Define Secure DevOps. How does it differ from DevOps?

**Lesson:** [[13.13 DevSecOps]]

### Expected answer
**Secure DevOps (DevSecOps)** integrates **security practices and automated security assessments throughout the DevOps CI/CD pipeline**, from planning and coding to deployment and monitoring. Security becomes a **shared responsibility** of the development, security and operations teams.

**DevOps** brings development and operations together with automation and **CI/CD** to deliver small changes quickly and reliably, but security is often a separate step at the end.

**Differences:** DevSecOps **shifts security left**: developers work with security staff before coding, with threat modelling, code analysis (SAST) and open-source checks (SCA) early. It also **shifts right**: DAST/IAST and monitoring after release. Vulnerabilities are caught **early**, when they are cheaper to fix, and regulatory compliance is built in, without slowing delivery.

---

## 2023/24 · Q6(c) (4 marks)
> **Question:** Briefly explain the Zero Trust Security Model.

**Lesson:** [[13.12 Zero Trust Security]]

### Expected answer
Zero Trust is a security strategy for modern multicloud networks based on **"never trust, always verify"**. Unlike the traditional perimeter model, which trusted everyone inside the firewall, **no user or device is trusted by default**, because cloud computing and remote work have removed the network perimeter.
- **Three principles:** (1) **continuous monitoring and validation**: every request is authenticated using context (privileges, location, device health, behaviour); (2) **least privilege**: minimum access, revoked after the session; (3) **assume breach**: segmentation, monitoring of every asset, real-time response.
- **Five pillars:** identity (IAM, SSO, MFA), devices, networks (**micro-segmentation**), applications/workloads, data (classification and encryption).
- It is implemented mainly through **ZTNA**, which connects users only to the resources they are permitted to use.

---

## 2023/24 · Q6(d) (8 marks)
> **Question:** Write short notes on the following cyber threats that impact web security. Your answer should include their definition, how they happen and prevention mechanisms.
> i. SQL injection (SQLi) ii. Cross-site scripting (XSS) iii. Code Injection iv. Denial of Service

**Lesson:** [[13.03 SQL Injection]] · [[13.07 Cross-Site Scripting (XSS)]] · [[13.08 Code Injection]] · [[13.09 Denial of Service]]

### Expected answer
**(i) SQL injection.** *Definition:* the attacker inserts malicious SQL into an application's input so the database executes it, bypassing login or reading, changing or deleting data. *How:* through user input fields (e.g. `' OR 1=1 --` in a login form), **poisoned cookies** or **server variables**, when the application concatenates input into SQL. Variants include in-band, error-based, blind, out-of-band and time-based. *Prevention:* **prepared statements / parameterised queries**, stored procedures, whitelist input validation, ORM frameworks, least database privilege, generic error messages.

**(ii) Cross-site scripting (XSS).** *Definition:* a vulnerability that lets a third party **execute a script in the user's browser on behalf of the web application**, leading to account compromise, session theft, privilege escalation or malware. *How:* unsanitised input is written into pages: **reflected** (payload in the request, e.g. a search field), **stored** (saved on the server, e.g. a comment), **DOM-based** (user data passed to the DOM without sanitising). *Prevention:* **sanitise/validate input**, **encode output** (e.g. `htmlspecialchars`), Content Security Policy, HttpOnly cookies.

**(iii) Code injection.** *Definition:* injecting code that the application **interprets and executes**, exploiting poor handling of untrusted data. *How:* **lack of input/output validation**, e.g. passing a user-controlled `$_GET` value into PHP `eval()`, so the attacker's code runs on the server. *Prevention:* never pass user input to `eval()` or other interpreters; strict whitelist validation and output encoding; least privilege; code review / SAST.

**(iv) Denial of Service.** *Definition:* an attack that **denies service to legitimate users** of a computer or website by overwhelming it. *How:* **flooding** it with massive traffic, **repeated requests** to one part of the system, or **exploiting vulnerabilities** to crash it (e.g. Ping of Death, smurf attack; DDoS using botnets). *Prevention:* **firewalls/rate limiting**, **IDS/IPS**, bandwidth limits, **CDN** to distribute load, network segmentation, anti-malware, regular scans and a response plan.

### How to approach this question
2 marks each: **definition + how + prevention**, each in 1–2 sentences.

---

## 2019/20 · Q8(a) (3 marks each)
> **Question:** Describe the following terms: i. Sniffer Programs ii. Backdoors iii. Cyber Vandalism iv. Masquerading or Spoofing v. Denial-of-Service vi. Man-in-the-middle exploit

**Lesson:** [[13.02 Related Threat Terms]] · [[13.09 Denial of Service]] · [[13.06 Spyware]] · [[13.04 Phishing]]

> [!warning]
> Only (v) DoS is defined in the current lecture. (i), (ii), (iv) and (vi) are only partly covered by related topics, and (iii) is not covered. The answers below use standard security knowledge.

### Expected answer
- **(i) Sniffer programs:** software (packet sniffers) that **captures and inspects data packets** on a network. It is useful for troubleshooting, but attackers use it to steal **unencrypted passwords, emails, card numbers and session cookies**. Defence: HTTPS/TLS, VPNs, segmentation, intrusion detection.
- **(ii) Backdoors:** a **hidden entry point that bypasses normal authentication**, left by developers or installed by malware (like a Remote Access Trojan). Attackers use it for remote control, data theft, installing more malware or joining botnets. Defence: code review, patching, anti-malware, monitoring.
- **(iii) Cyber vandalism:** deliberately **damaging, defacing or destroying** websites or data (e.g. replacing a homepage with offensive content, deleting files), often through injection, XSS or stolen credentials. Defence: patching, input validation, access control, backups.
- **(iv) Masquerading/spoofing:** **pretending to be a trusted person, device or website** by faking an identity: email spoofing, **caller-ID spoofing (vishing)**, IP/DNS spoofing, look-alike phishing sites. Defence: verify senders and URLs, certificates, MFA.
- **(v) Denial-of-Service:** an attack that **makes a website or server unavailable to legitimate users** by flooding it with traffic or requests, or crashing it through a vulnerability (Ping of Death, smurf; DDoS via botnets). Defence: firewalls, IDS/IPS, CDN, bandwidth limits.
- **(vi) Man-in-the-middle exploit:** an attacker **secretly intercepts communication between two parties** (e.g. a user and a bank on public Wi-Fi) to **read or alter** it and steal credentials or sessions. Defence: **HTTPS/TLS with valid certificates**, VPN, avoiding public Wi-Fi, MFA.

---

## 2018/19 · Q5(b)
> **Question:** What are the impacts of Sniffer programs?

**Lesson:** [[13.02 Related Threat Terms]] · [[13.06 Spyware]]

### Expected answer
A sniffer captures and reads data packets crossing a network. **Impacts:** theft of **unencrypted passwords, emails and card numbers**; theft of **session cookies** leading to **session hijacking**; enables **identity theft** and **man-in-the-middle** attacks; **loss of privacy and confidentiality**; can be used to map the network for further attacks. *(Prevention: HTTPS/TLS, VPN on public Wi-Fi, switched and segmented networks, IDS.)*

---

## 2018/19 · Q5(c)
> **Question:** Suppose you have a problem to remember the passwords of email and debit cards and you always confuse the passwords. You have planned to go to United Kingdom. Clearly describe a way to remember/recall those passwords when you go to United Kingdom.

**Lesson:** [[13.10 Best Practices for Securing a Website]] (strong passwords) (partial)

### Expected answer
- Use a **reputable password manager** protected by **one strong master password and 2FA**, so you remember only one secret.
- Or create **memorable passphrases / mnemonics** (e.g. the first letters of a personal sentence) for the email password, and memorise the **PIN** separately. **Never write the PIN on or with the card**; don't store passwords in plain text, email or photos.
- **Before travelling:** set up **account recovery** (backup codes, recovery email or number), confirm that the bank's OTP or app works abroad, and keep the bank's emergency number to block the card if needed.

---

## 2017/18 · Q6(a)
> **Question:** Describe 3 threats on a client computer.

**Lesson:** [[13.01 Web Security and Major Threats]] · [[13.05 Viruses and Worms]] · [[13.06 Spyware]] · [[13.04 Phishing]]

### Expected answer
1. **Viruses and worms:** a virus replicates by inserting its code into other programs when executed and spreads through infected files and email attachments; a worm spreads by itself across networks, scanning IPs and ports. They slow the computer, corrupt or delete data, and steal information. *Prevention:* updated antivirus, patching, don't open unknown attachments.
2. **Spyware** (keyloggers, Trojans, tracking cookies, RATs): installed through unknown links, phishing or "free" software; secretly **steals personal and business information** and sends it to a third party. *Prevention:* anti-spyware, trusted downloads only.
3. **Phishing / ransomware:** phishing tricks the user into revealing credentials on fake sites; ransomware **encrypts files and demands payment**. *Prevention:* user awareness, checking URLs, backups, 2FA.

---

## 2017/18 · Q6(c)
> **Question:** What are the effects of Active X controls?

**Lesson:** [[13.02 Related Threat Terms]] (✗ not in current notes)

### Expected answer
ActiveX controls were small software components that ran inside Internet Explorer with **full access to the user's computer**. **Effects:** they made rich interactive content possible, but a **malicious or vulnerable** control could **install malware or spyware, read or delete files, or take control** of the computer. Users were often tricked into accepting them, which made them a major client-side threat. **Precautions:** only allow signed controls from trusted publishers, keep the browser updated, or use modern browsers that don't support ActiveX.

> [!warning]
> Not covered by any current lecture. This is standard knowledge.

---

## 2017/18 · Q6(d)
> **Question:** When you register for a service via online, you might have to confirm that you were not a robot. What is the reason for this?

**Lesson:** [[13.02 Related Threat Terms]] · [[13.09 Denial of Service]] (partial)

### Expected answer
This is a **CAPTCHA**, a test that tells **humans from automated bots**. Services use it to stop bots from **creating fake accounts**, **spamming**, **brute-forcing passwords**, **scraping data**, and **flooding forms with automated requests** (a DoS-style abuse that wastes server resources). It protects **data quality, server resources and other users**. Modern AI-based versions analyse user behaviour instead of asking for typed text.

---

## 2017/18 · Q7(a)
> **Question:** Give two instances of credit card robberies.

**Lesson:** [[13.04 Phishing]] · [[13.06 Spyware]] · [[10.11 PCI DSS and Consumer Rights]]

### Expected answer
1. **Phishing/smishing:** a fake bank email or SMS directs the victim to a cloned website, where they enter their card details and OTP; the criminal then uses the card.
2. **Data breach / spyware:** a merchant that stores card data insecurely (against PCI DSS) is hacked, or a **keylogger or data harvester** on an infected computer captures card details as they are typed.
*(Also acceptable: card skimming at ATMs or POS terminals, stolen card photocopies, full card numbers printed on receipts.)*
