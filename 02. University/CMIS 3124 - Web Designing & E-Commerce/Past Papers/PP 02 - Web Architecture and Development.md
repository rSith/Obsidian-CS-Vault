---
type: past-paper
course: CMIS 3124
lesson: L2
status: complete
tags: [cmis3124, past-paper, cmis3124/L2]
---
# PP 02 · Web Architecture and Development: Past-Paper Questions

> [!info] Lesson: [[02.00 Web Architecture and Development]] · Summary: [[02.99 Summary - Web Architecture and Development]] · [[Past Paper Mapping]]

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2023/24 | 1(a) | Advantages and disadvantages of web apps | 2 |
| 2023/24 | 1(b) | Load balancer, web app server, cache, CDN | 4 |
| 2023/24 | 1(c) | 3-tier for online doctor channelling | 6 |
| 2023/24 | 7(a) | CMS: definition, features, benefits | 3 |
| 2023/24 | 7(b) | How PHP works | 3 |
| 2023/24 | 7(c) | GET vs POST | 2 |
| 2017/18 | 6(b)(i)–(iii) | Cookies: purpose, problems, precautions | – |

---

## 2023/24 · Q1(a) (2 marks)
> **Question:** List two (02) advantages and two (02) disadvantages of web applications.

**Lesson:** [[02.01 Web Applications and Web Technologies]]

### Expected answer
**Advantages**
1. Accessible **from anywhere** through a browser on any device or operating system, with **no installation**.
2. **Hosted centrally**, so maintenance and updates are done once on the server and every user gets them immediately.

**Disadvantages**
1. **Depend on an Internet connection**; performance drops with slow networks or heavy server load.
2. **Security risks**, because data and logic sit on a remote server that is exposed to attacks (DoS, SQL injection); there are also browser-compatibility issues.

### How to approach this question
½ mark per point, so give **exactly two of each**, one clear line each.

---

## 2023/24 · Q1(b) (4 marks)
> **Question:** Write the functionality of the load balancer, web app server, caching service, and Content Delivery Network (CDN) in the web application architecture.

**Lesson:** [[02.08 Web Application Architecture Components]]

### Expected answer
- **Load balancer:** receives incoming requests and **directs each one to one of several servers** so that no server is overloaded. This improves availability and scalability, and traffic is rerouted if a server fails.
- **Web app server:** **processes the user's request** by running the application/business logic and talking to the database, then **sends documents (HTML, JSON, XML) back to the browser**.
- **Caching service:** **stores the results of earlier requests/computations** so that later requests for the same data are answered from the cache **much faster**, which reduces load on the database.
- **CDN:** a distributed network that **delivers static files (HTML, CSS, JavaScript, images)** from servers close to the user, which lowers latency and absorbs traffic spikes.

### Key points to include
One mark each: **what it does + why it helps**.

---

## 2023/24 · Q1(c) (6 marks)
> **Question:** What is Three-tiered web architecture? Provide a breakdown of what each layer in the 3-tier architecture would be responsible for within an online doctor channeling service.

**Lesson:** [[02.06 Client-Server and Three-Tier Architecture]]

### Expected answer
**Definition:** three-tier architecture separates an application into a **presentation tier, an application (logic) tier and a data tier**. Each tier runs separately, so it can be developed, secured and scaled independently. The presentation tier never talks to the database directly; all requests pass through the logic tier.

| Tier | Responsibility in an online doctor-channelling service |
|---|---|
| **Presentation** | Web/mobile UI for patients: search doctors by specialty, hospital and date; view available sessions and fees; fill in the booking form; make payment; display the confirmation and appointment number; client-side input validation |
| **Application (logic)** | Authenticate patients; check doctor availability; allocate the appointment number and time; calculate channelling + hospital fees; process payment through a payment gateway; apply cancellation/refund rules; send SMS/email confirmations |
| **Data** | Database storing doctors, specialties, hospitals, schedules, patients, appointments and payments; executes queries and updates; keeps data consistent and backed up |

**Flow:** a patient books a slot → the logic tier checks availability in the database → saves the booking → returns a confirmation that the presentation tier displays. **Benefit:** improved security (patients never access the database directly) and scalability at peak booking times.

### How to approach this question
About 1 mark for the definition, about 1.5 per tier **applied to the scenario**, and use the flow or a benefit for the last mark.

---

## 2023/24 · Q7(a) (3 marks)
> **Question:** What is a Content Management System (CMS)? Give two (02) features and two (02) benefits of it.

**Lesson:** [[02.10 Content Management Systems]]

### Expected answer
**Definition:** a CMS is software that helps users **create, manage, store and modify digital content** through a user-friendly interface, without writing code. It has a **CMA** (content management application, used for editing) and a **CDA** (content delivery application, which stores the content and makes it live). Examples: WordPress, Drupal, Joomla.

**Features (any two):** publishing controls with roles and permissions; content editing tools (drag-and-drop, images/videos, scheduling); content staging; built-in analytics; security measures (application firewall, CDN against DDoS); templates and themes.

**Benefits (any two):** increased collaboration across teams; user-friendly with no coding skills needed; built-in SEO tools; highly scalable; consistent branding; organised content.

---

## 2023/24 · Q7(b) (3 marks)
> **Question:** How does PHP work? Briefly describe.

**Lesson:** [[02.02 Web Languages - HTML CSS JavaScript PHP]]

### Expected answer
PHP (PHP: Hypertext Preprocessor) is an **open-source, server-side scripting language** used to generate dynamic web pages. When the browser requests a `.php` page (e.g. a form submitted to `welcome.php`), the **web server passes the file to the PHP engine, which executes the code on the server**. The script can read form data (`$_GET` / `$_POST`), process files and access databases such as MySQL. The **result is returned to the browser as plain HTML**, so the user never sees the PHP code.

```php
Welcome <?php echo $_POST["name"]; ?>   <!-- browser receives: Welcome Kasun -->
```

---

## 2023/24 · Q7(c) (2 marks)
> **Question:** Compare and contrast PHP's GET and POST methods.

**Lesson:** [[02.04 HTTP Protocol]]

### Expected answer
**Similar:** both are HTTP methods that send form data from the browser to a PHP script (set with `<form method="get|post">`) and are read with `$_GET` / `$_POST`.

| GET | POST |
|---|---|
| Data appended to the **URL** as a query string | Data sent in the **request body** |
| Visible in the address bar and history; can be bookmarked and cached | Not visible in the URL; not cached or bookmarked |
| Limited length | No practical size limit; supports file upload |
| For searches and retrieving data; not for passwords | For sensitive data and actions that change data (login, payment) |

---

## 2017/18 · Q6(b)
> **Question:** i) What is the purpose of cookies? ii) What are the problems which may occur when you use cookies? iii) What are the precautions to be taken to protect yourself against cookies?

**Lesson:** [[02.05 Sessions and Cookies]] · [[13.06 Spyware]] · [[13.03 SQL Injection]]

### Expected answer
**(i) Purpose:** HTTP is stateless, so cookies (small name–value text stored by the browser and sent back to the server with each request) let a site **remember information between requests**:
- **authentication** (keep you logged in),
- **session tracking** (shopping cart, pages visited),
- **preferences** (language, theme), plus analytics and personalisation.

**(ii) Problems:**
- **Privacy/tracking:** third-party tracking cookies record browsing habits (a form of spyware).
- **Session hijacking:** a stolen session cookie (e.g. via XSS) lets an attacker impersonate you.
- **Cookie poisoning:** modified cookie values are used to inject SQL or change prices/IDs.
- **Shared computers:** the next user may be logged in as you; plain-text cookies may expose data.

**(iii) Precautions:**
- Delete cookies regularly or clear them on exit; use private browsing on shared PCs and always log out.
- Block third-party cookies; accept only necessary cookies in consent banners.
- Use HTTPS sites; keep the browser and anti-spyware updated.
- (Developers) set `Secure`, `HttpOnly` and `SameSite` flags, use short expiry, and never store passwords in cookies.

> [!warning]
> Problems and precautions are not stated on the cookie slides. They combine Lesson 2 with Lessons 12–13, as the study guide does.
