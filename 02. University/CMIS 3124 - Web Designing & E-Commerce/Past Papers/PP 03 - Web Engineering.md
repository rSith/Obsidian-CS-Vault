---
type: past-paper
course: CMIS 3124
lesson: L3
status: complete
tags: [cmis3124, past-paper, cmis3124/L3]
---
# PP 03 · Web Engineering: Past-Paper Questions

> [!info] Lesson: [[03.00 Web Engineering]] · Summary: [[03.99 Summary - Web Engineering]] · [[Past Paper Mapping]]

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2023/24 | 4(a) | Why WebE must be agile, adaptable, incremental | 2 |
| 2018/19 | 4(a) | Things to consider before planning a multi-category e-commerce website | – |
| 2017/18 | 2(a) | Same question as 2018/19 Q4(a) (repeated) | – |

---

## 2023/24 · Q4(a) (2 marks)
> **Question:** WebE process must be agile, adaptable, and incremental. Why?

**Lesson:** [[03.02 WebE Process Framework]] · [[03.01 Web Engineering vs Software Engineering]]

### Expected answer
Unlike traditional software, **WebApp requirements change with time and the web environment changes rapidly** (new content, features, devices). **Development time is short and budgets are small**, and the **user range is large and diverse**. A fixed, one-time plan would quickly go out of date. The process therefore works in **increments**: each increment passes through communication → planning → modelling → construction → deployment, is **evaluated by users**, and the next increment **adapts to that feedback**. This lets working parts be released quickly while the system keeps changing.

### Key points to include
- Changing requirements / rapid change
- Short development time + small budgets
- Feedback from each delivered increment drives the next

### How to approach this question
Two marks means **two solid reasons**. Link them to the WebE vs SE table.

---

## 2018/19 · Q4(a) and 2017/18 · Q2(a) (same question)
> **Question:** You are requested to design a website for a company which wants to sell all kinds of items (electronic items, stationaries, foot wear, etc.) via online. Explain the facts/concepts to be considered before planning the website.

**Lesson:** [[03.02 WebE Process Framework]] · [[03.04 Testing WebApps and Best Practices]] · [[10.05 E-Commerce Platforms]] · [[05.07 Navigation Design]]

### Expected answer
1. **Communication (understand the business):** formulate the company's goals and target customers; elicit requirements such as product categories, search, cart, payment methods, delivery areas and return policy; negotiate scope, budget and timeline.
2. **Planning:** estimate cost and time, analyse risks (security, traffic peaks), and schedule the work in increments.
3. **Information architecture and navigation:** a clear **category hierarchy** (Electronics → Laptops …), search and filters, and consistent menus, so that users can find items among many categories.
4. **Platform choice:** open-source, SaaS or headless platform; it must **scale** to many products and many visitors.
5. **Design goals and quality:** simplicity, consistency, brand identity, navigability, compatibility; **responsive/mobile-first** design; usability and accessibility.
6. **Security and legal:** SSL/HTTPS, a secure payment gateway, **PCI DSS**, privacy policy / **GDPR**, consumer rights (returns, refunds), copyright of product images.
7. **Operations:** inventory management, shipping and fulfilment, customer support, marketing and SEO; and **testing before launch** (content, navigation, performance, security).

### How to approach this question
Use the WebE process as the skeleton, then add e-commerce-specific concerns (platform, payment, legal). Each point should say *what* to consider and *why*.
