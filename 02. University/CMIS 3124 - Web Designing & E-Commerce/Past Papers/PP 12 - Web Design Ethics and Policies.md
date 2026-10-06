---
type: past-paper
course: CMIS 3124
lesson: L12
status: complete
tags: [cmis3124, past-paper, cmis3124/L12]
---
# PP 12 · Web Design Ethics and Policies: Past-Paper Questions

> [!info] Lesson: [[12.00 Web Design Ethics and Policies]] · Summary: [[12.99 Summary - Web Design Ethics and Policies]] · [[Past Paper Mapping]]

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2023/24 | 7(d) | Ethical practices + impact on users and businesses | 6 |
| 2023/24 | 7(e) | Three types of dark patterns | 6 |
| 2019/20 | 5(a) | Features not appropriate for e-commerce websites | 5 |
| 2019/20 | 5(b)(ii) | Religious-artefact store: features not to include | 5 |
| 2019/20 | 8(b) | Use of Secure Sockets Layer | 3 |
| 2018/19 Q4(c) = 2017/18 Q2(c) | | Features that may give negative impacts (repeated) | – |

---

## 2023/24 · Q7(d) (6 marks)
> **Question:** Briefly describe ethical practices in web development and their impact on users and businesses.

**Lesson:** [[12.02 Ethical Practices in Web Development]] · [[12.03 Impact on Users and Businesses]]

### Expected answer
Ethical web development means designing, creating and maintaining websites in a way that **prioritises the rights, safety and well-being of users**, with a commitment to **transparency, accountability and fairness**.

**Ethical practices**
1. **Respect for user privacy:** strong data-protection measures; personal data collected, stored and processed securely and lawfully.
2. **Transparency and consent:** clear, understandable information on how data is used, and **explicit consent** before collecting it.
3. **Accessibility and inclusivity:** sites usable by people of all abilities (e.g. WCAG).
4. **Sustainability:** minimise energy use and carbon footprint (lighter pages, efficient hosting).
5. **Security:** robust protection against cyber threats (e.g. HTTPS/TLS, encryption).

**Impact on users:** **trust and confidence**: when users feel their data is respected they engage more; a **better user experience** that is friendlier, more accessible and easier to navigate for a diverse audience.

**Impact on businesses:** **enhanced brand reputation**; **legal compliance and risk mitigation** with laws such as GDPR and CCPA, reducing penalties and reputational damage; **long-term customer loyalty**.

### How to approach this question
About 3 marks for practices and about 1.5 each for users and businesses.

---

## 2023/24 · Q7(e) (6 marks)
> **Question:** Briefly explain three (03) types of dark patterns.

**Lesson:** [[12.06 Dark Patterns]]

### Expected answer
**Dark patterns** are deceptive design tricks in websites and apps that make users do things they did not mean to, such as paying more, subscribing, or sharing their data.
1. **Hidden costs:** extra fees such as service charges, shipping or VAT are added at the final checkout step, so the end price is much higher than advertised (e.g. a Rs 2,000 ticket becomes Rs 2,950 at payment).
2. **Roach motel:** signing up is made very easy (two steps), but cancelling the account or subscription needs many unnecessary steps (e.g. cancellation only by phone).
3. **Confirm-shaming:** the option to decline is worded to make users feel guilty, e.g. "No thanks, I don't like saving money", to guilt-trip them into accepting.

*(Other valid choices: bait and switch (Windows upgrade "close" button), forced continuity (free trial that silently starts billing; Amazon Subscribe & Save), misdirection (airline pre-selected paid seats), sneak into basket, friend spam, disguised ads (fake "Download" button), trick questions, FOMO, price-comparison prevention.)*

---

## 2019/20 · Q5(a) (5 marks)
> **Question:** Describe features that are not appropriate to be included in e-commerce websites.

**Lesson:** [[12.06 Dark Patterns]] · [[04.02 Colour Psychology and Colour Schemes]] · [[04.03 Graphics and Typography]] · [[05.09 Cognitive Load Theory]]

### Expected answer
1. **Dark patterns:** hidden costs, sneak into basket, forced continuity, roach motel, confirm-shaming, fake FOMO counters, disguised ads and trick questions. They are unethical and destroy trust.
2. **Poor visual design:** cluttered pages, too many colours and fonts, flashing animations, **auto-playing audio/video**, text as images, ALL CAPS, underlined text that is not a link, complementary colours for text and background.
3. **High cognitive load:** long one-page forms, **forced registration before checkout**, too many pop-ups.
4. **Security and privacy failures:** no HTTPS, storing card data insecurely, collecting unnecessary personal data, no privacy policy.
5. **Copyrighted images** used without permission, and **misleading product information**.

---

## 2019/20 · Q5(b)(ii) (5 marks)
> **Question:** You decided to sell religious (Hindu, Buddhism and Christianity) artifacts online through a website. … ii. What are the features you wish not to include on the website? Give reasons for your answer.

**Lesson:** [[12.06 Dark Patterns]] · [[04.02 Colour Psychology and Colour Schemes]] · [[12.07 Web Compliance and Copyright]] · [[10.10 GDPR]]

### Expected answer
1. **Dark patterns that exploit religious sentiment** (fake scarcity such as "only 2 blessed statues left", hidden fees, confirm-shaming): unethical, and they destroy trust.
2. **Content or imagery disrespectful to any religion**, or presentation that favours one religion over others: it would offend customers.
3. **Inappropriate ads or unrelated pop-ups** next to sacred items: they disrespect the products and distract users.
4. **Garish colour schemes, loud animations or auto-playing audio** that clash with the respectful, calm tone the products need (colour psychology).
5. **Copyrighted religious images** used without permission (legal issues), and **excessive personal data collection** (e.g. asking for the customer's religion) without need or consent (privacy, GDPR data minimisation).

---

## 2019/20 · Q8(b) (3 marks)
> **Question:** Describe the use of secured socket layer.

**Lesson:** [[12.04 Tools for Ethical Web Development]] · [[13.10 Best Practices for Securing a Website]] · [[10.05 E-Commerce Platforms]]

### Expected answer
**SSL (Secure Sockets Layer)**, today replaced by **TLS**, secures communication between a browser and a web server. It is used through **HTTPS** (the padlock icon):
1. **Encryption:** data in transit (passwords, card numbers) is encrypted, so it cannot be read if intercepted.
2. **Authentication:** the site's **SSL/TLS digital certificate**, issued by a trusted authority, proves the server is genuine. This protects against fake sites and man-in-the-middle attacks.
3. **Integrity:** data cannot be altered in transit without detection.
E-commerce platforms include **SSL certificates** and secure payment gateways for this reason, and PCI DSS requires card data to be **encrypted across public networks**.

> [!note]
> The lecture names "HTTPS with TLS" and "SSL certificates". The three functions are standard knowledge.

---

## 2018/19 · Q4(c) and 2017/18 · Q2(c)
> **Question:** Describe some features which may give some negative impacts. *(In the context of designing a website for a company selling all kinds of items online.)*

**Lesson:** [[12.06 Dark Patterns]] · [[05.09 Cognitive Load Theory]] · [[06.00 Responsive Web Design]] · [[09.03 Applying Accessible Design]]

### Expected answer
1. **Dark patterns:** hidden costs, forced continuity, roach motel, sneak into basket, false FOMO, disguised ads. Customers feel cheated and leave.
2. **Slow loading**, heavy images and broken links; a site that is **not mobile-responsive**.
3. **Complicated navigation, long checkout forms and forced sign-up** (extraneous cognitive load), which cause cart abandonment.
4. **Too many pop-ups and ads, and auto-playing media**: annoying and distracting.
5. **Poor accessibility** (no alt text, low contrast), which excludes users.
6. **Weak security** or unclear privacy and return policies, which reduce trust.
