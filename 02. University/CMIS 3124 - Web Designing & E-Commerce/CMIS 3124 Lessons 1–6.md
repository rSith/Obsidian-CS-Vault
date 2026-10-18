CMIS 3124 · Web Designing & E-Commerce

# Short notes & mind maps · Lessons 01–06

Each lesson has a short note, an exam-focus box and a mind map, in that order. Everything comes from your lecture notes. Points marked † come from the study guide and not from the lecture slides.

highlighted = memorise word-for-word 23/24 · 8m = asked in a past paper (year · marks) † = study-guide point, not on slides

[01 Evolution](#l1)[02 Architecture](#l2)[03 Web Eng.](#l3)[04 Design](#l4)[05 HCI](#l5)[06 RWD](#l6)

Lesson 01

## Evolution of the Web

How the Web grew from read-only pages to a web of intelligent machines, the five Web 2.0 tools, and how a browser fetches a page.

High priorityIn all 4 past papersBiggest: 8-mark evolution essay

### 1Short Note

#### 1. World Wide Web (WWW)

**WWW** = a graphical entryway to the Internet and a collection of inter-linked documents (web pages) on the Internet. Pages are joined by **hyperlinks**.

Three things make the Web work:

- Protocols: rules for exchanging data. Mainly **HTTP/HTTPS**: the browser requests, the server responds.
- URL (Uniform Resource Locator): the unique **address** of each resource.
- HTML: describes the **structure** of a page; its hyperlinks join pages together.

**Internet vs Web**†: the Internet is the global **network** (infrastructure); the Web is one **service** that runs on top of it (email and file transfer are others).

#### 2. The stages of the Web

|  | Web 1.0 | Web 2.0 | Web 2.5 | Web 3.0 | Web 4.0 |
| --- | --- | --- | --- | --- | --- |
| Nickname | Static web | Participatory / social web | Transitional / hybrid | Decentralised web | Symbiotic web |
| Period | Early 1990s – early 2000s | 2000s onwards | Bridge to 3.0 | Emerging | Next stage |
| User role | Read | Read + write | Read + write + some ownership | Read + write + own | Interact with intelligent systems |
| Core tech | Static HTML pages | Dynamic web apps, APIs, cloud | Blockchain + NFTs on centralised platforms | Blockchain, P2P, smart contracts | AI + ML + IoT |
| Examples | Info portals, AltaVista | Gmail, YouTube, blogs, wikis | Social platforms with NFT wallets | DeFi, NFTs, metaverse assets | Smart cities, BCI, self-driving cars |

**The pattern to show in every essay:** the user gains more power at each stage: read → read + write → read + write + own → working with intelligent machines.

##### Web 1.0: the static web

- Static information portals that offered content without interactivity.
- Basic information sharing, not communities.
- Few images or videos because of **bandwidth constraints**.
- **Directory-like search engines** such as **AltaVista**.
- "Laid the foundation for the modern Internet" and for Web 2.0.

##### Web 2.0: the participatory web

- Empowers users to actively participate, create and share content through blogs, wikis and social media. Users supply most content (**user-generated content, UGC**).
- Emphasises community-driven collaboration, usability and interoperability.
- **Features (6):** apps like Gmail/YouTube work seamlessly in the browser · integration between services · built-in sharing tools · optimised for many devices/screens · easy content subscription (RSS) · community-driven development.
- **Impact:** e-commerce (personalised shopping, **peer reviews**) · business (marketing, engagement, remote collaboration) · education (online courses, forums) · the Internet became a participatory ecosystem of cloud apps and APIs.

##### Web 2.5: the bridge

- Transitional phase between Web 2.0 and Web 3.0; bridges centralised and decentralised technologies.
- Keeps UGC and social networking; adds **blockchain**, **tokenised ecosystems (NFTs)** and **enhanced privacy**.
- A **stepping stone**: a hybrid that balances innovation with existing infrastructure.

##### Web 3.0: the decentralised web

- Emphasises user control, privacy and decentralisation; **peer-to-peer (P2P)** interactions with no central authorities.
- Aim: a secure, transparent, interconnected ecosystem where **data ownership and interoperability** take centre stage.
- **What it does (6):** safeguards data with encryption · automates agreements without intermediaries (**smart contracts**) · financial activities on blockchain · digital assets represent real-world assets · personalises content · users own and monetise their data.
- **Uses (4):** **DeFi** (lend/borrow/trade without banks) · supply chain (immutable records) · creators earn via NFTs · metaverse (assets move between platforms).

##### Web 4.0: the symbiotic web

- AI, machine learning and connected devices converge into a seamless, intuitive ecosystem; humans and machines work together.
- **Predictive, context-aware, real-time decision-making**; blurs physical and digital.
- **Features (6):** tailored (hyper-personalised) experiences · devices work seamlessly together · immersive experiences · AI that keeps learning · **voice, gesture, brain-computer interfaces (BCI)** · real-time decisions by smart devices and bots.
- **Applications (6):** smart cities · smart homes · healthcare (wearables) · education/entertainment (AR/VR) · finance & supply chain · transport (self-driving).

##### Future of the Internet (7 directions)

1. Hyper-personalisation
2. IoT and ubiquitous computing
3. Intelligent and emotional web
4. Decentralisation at scale
5. Immersive AR/VR and the metaverse
6. Quantum internet
7. Sustainability and ethical tech

#### 3. The five Web 2.0 tools

| Tool | Lecture definition | Key points | Example / choose it when… |
| --- | --- | --- | --- |
| Blog 19/20 · 4m | "Web log". A two-way web-based communication tool; a site where people enter **thoughts, ideas, suggestions and comments**. | Can be **private** (internal) or **public**. Author posts, readers **comment** (cannot edit). Newest posts first†. | You want reader **feedback** on a story or poem. |
| Wiki 19/20 · 4m | A web-based collaborative authoring (content management) system where users create and edit content. | **Anyone** can add a new article or revise/edit an existing one **using a web browser**. Simple, easy, powerful for collaborative work. Revision history†. | **Wikipedia**. Experts or a group must **edit the same article**. |
| Mashup 3 papers | A web page or website that combines information and services from multiple sources on the Web. | A **mashup server** lets you connect, collect and mash up anything on the Web and back-end data. Uses APIs; PHP `file()`. | **HousingMaps.com** = craigslist + Google Maps. **Fishing Solutions** = Google Maps + anglers' info. |
| RSS | Really Simple Syndication: a family of **web feed formats** used for syndication of content from web pages or blogs. | Summary + link, presented as an **XML file**. An **aggregator / feed reader** checks feeds for you. Look for "subscribe", an orange icon, XML/RSS. | Keep readers **updated** automatically. |
| Social bookmarking 18/19, 19/20 | A browser-based function to save, sort and share webpage links digitally. | Via a platform or a browser extension. Folders/tags. Web-based storage, real-time updates, trend tracking → traffic and **SERP visibility**. Re-post, comment, follow. | Let researchers save, tag and find your **journal paper** (+ RSS). |

##### Business uses of mashups (6)

1. New core business apps that knit services together, with rich UIs
2. Employees build value-added apps quickly, without waiting for IT
3. Mix and match data and services in unexpected ways
4. Combine internal sources for better, smarter decisions
5. Executives understand customers and sales better
6. Used in payroll, CRM, logistics, procurement, marketing, e-commerce

##### Advantages of social bookmarking (5)

1. Access from **anywhere, any device** (stored online)
2. Organise with tags, folders, categories
3. Share and discover sources through other users
4. Real-time trends; algorithm suggests tags/users
5. Site owners gain traffic and SERP visibility

| Blog | Wiki (Wikipedia) |
| --- | --- |
| One author or a small team | Anyone, collaboratively |
| Readers only **comment** | Users **edit the article itself** |
| Personal opinions, dated posts | Neutral, topic-based articles |
| Private or public | Usually public, with revision history |

**Similarity:** both are Web 2.0 user-generated content, read in a browser and free to use.

#### 4. Web browser

A software application that lets you access and view websites and other content on the internet (Chrome, Edge, Firefox, Safari).

1 Request (HTTP request to server) → 2 Find server (DNS: domain name → IP address) → 3 Display (interpret HTML/CSS/JS and render)

**5 features:** navigation · content display · search functionality · security (encryption, virus checks) · **caching** (stores visited sites locally so they load faster).

Exam focus · Lesson 01

##### Likely questions

- **"How has the Web evolved?"** 23/24 Q1(d) · 8m: one short paragraph per stage (1.0, 2.0, 2.5, 3.0, 4.0, about 1.5 marks each) + the read → write → own → intelligent trend.
- **Short notes** (4 marks each): Blogs, Wikipedia, Mashups, Web 2.0, Social bookmarking 19/20 Q6(b).
- Blog vs Wikipedia · advantages of social bookmarking · mashup uses + 3 business instances 18/19, 17/18.
- Scenario: pick a tool (story/poem → blog; group editing → wiki; share paper → social bookmarking; updates → RSS; combine maps + listings → mashup).

##### Don't mix up

- Internet (network) vs Web (service on it).
- Blog (readers comment) vs Wiki (readers edit).
- Web 2.5 (hybrid, still centralised) vs Web 3.0 (fully decentralised, P2P).
- RSS (updates come to you) vs social bookmarking (you save and share links).

##### Lists to learn by count

- WWW components **3** · Web 2.0 features **6** · Web 2.0 tools **5** · Web 3.0 capabilities **6** / uses **4** · Web 4.0 features **6** / applications **6** · future directions **7** · mashup uses **6** · browser steps **3** / features **5**.

### 2Mind Map

Root on the left; read each branch left to right. On a phone, scroll the map sideways.

Lesson 02

## Web Architecture and Development

The "how it is built" lesson: the languages of the web, the plumbing that carries a request (URL, HTTP, sessions, cookies), the architectures that organise an app, and CMSs.

High priorityIn every paper20 marks in 2023/24

### 1Short Note

#### 1. Web applications and web technologies

**Web application** = a software program that works on a web browser, is hosted on a remote server and distributed over the Internet, and is developed using server-side and client-side scripting languages.

**Web technologies** relate to the interface between web servers and clients: **markup languages** (HTML, XML), **programming interfaces and languages** (JavaScript, PHP, APIs) and **standards for document identification and display** (URL, HTTP, CSS).

| Advantages† 23/24 · 2m | Disadvantages† |
| --- | --- |
| Any device with a browser, no installation | Needs an Internet connection |
| One central copy → updates reach everyone | Speed depends on network and server |
| Accessible anywhere, any time; cross-platform | Security and privacy risks (data on a remote server) |
| Easy to scale on the server | Browser compatibility issues; limited hardware access |

#### 2. The four web languages

| Language | Job | Lecture definition |
| --- | --- | --- |
| HTML | Structure | Which part of the page is a heading, paragraph, list, table… |
| CSS | Design | Fonts, colours, positions of parts of the page |
| JavaScript | Behaviour (client-side) | Manages behaviour: animated graphics, interactive maps, clickable buttons. **Event handlers** handle and verify user input, user actions and browser actions. |
| PHP | Server-side | "PHP: Hypertext Preprocessor": an **open-source, server-side scripting language** used to generate dynamic pages (forms, files, databases). |

##### How PHP works 23/24 Q7(b) · 3m

Key sentence: PHP code is executed on the server and the result is returned to the browser as plain HTML.

1. The browser sends an HTTP request for `welcome.php` (e.g. a form submit).
2. The web server passes the `.php` file to the **PHP engine**.
3. PHP runs: reads form data (`$_POST`/`$_GET`), queries the database, processes files.
4. PHP outputs **plain HTML**, which the server sends back. The user **never sees the PHP source**.

```
<form action="welcome.php" method="post"> Name: <input type="text" name="name"> <input type="submit"> </form>
<!-- welcome.php -->   Welcome <?php echo $_POST["name"]; ?>
```

| Client-side (JavaScript) | Server-side (PHP) |
| --- | --- |
| Runs in the browser; code visible | Runs on the server; only HTML output is sent |
| Fast UI reactions (form checks, animation) | Secure access to databases and files (login, orders) |

#### 3. URL structure and domains

**URL** = an address that indicates where a resource is located on the Internet.

https :// blog . wyb . ac.lk : 443 /articles/web.html ? id=42&sort=new #comments

**Parts:** **scheme** (rules of transmission) · **subdomain** (section of the site) · **domain** (organisation) · **TLD** (type of organisation) · **port** (type of service) · **path** (exact location) · **?** (query separator) · **query string** (parameters) · **fragment** (a location within the page).

**TLD types (6):** **gTLD** generic (.com, .org) · **geoTLD** geographic (.paris) · **grTLD** generic-restricted (.biz) · **sTLD** sponsored (.gov, .edu) · **ccTLD** country code (.lk, .uk) · **tTLD** test (.test, .localhost).

#### 4. HTTP

**HTTP** (HyperText Transfer Protocol) = the protocol a browser (client) and web server use to exchange information: request → response. HTTPS = HTTP + TLS encryption.

- **Request contains:** HTTP version · URL · method · request headers · body.
- **8 methods:** GET, POST, HEAD, PUT, DELETE, TRACE, OPTIONS, CONNECT.
- **Response:** first line = **status line** (e.g. `HTTP/1.1 200 OK`). Handled primarily by the status code, secondarily by headers.

- **Status families:** 2xx success · 3xx redirection · 4xx client error (404) · 5xx server error (500).
- HTTP is stateless: hosts don't keep information about users between requests. To remember users, use **cookies, sessions, hidden variables, URL-encoded parameters**.

##### GET vs POST 23/24 Q7(c) · 2m

|  | GET | POST |
| --- | --- | --- |
| Data goes | Appended to the URL (query string) | Inside the request body |
| Visibility | Visible in address bar, history, logs | Not shown in the URL |
| Size† | Limited by URL length | No practical limit; file uploads |
| Bookmark / cache† | Can be bookmarked and cached | Not bookmarked or cached |
| Use | Search, filters, retrieving data | Login, payments, larger or sensitive data |
| PHP | `$_GET["q"]` | `$_POST["name"]` |

**Similarities:** both are HTTP methods that send form name–value pairs to a server-side script; both are set by the form's `method` attribute.

#### 5. Sessions and cookies

**Session** = a series of related browser requests that come from the same client during a certain time period. Stored on the **server**.

- **Session management factors (4):** session length · expected number of users · data stored per user · heap size of the server.
- **Session timeout:** after a set interval the session expires and **all its data is discarded** (e.g. bank auto-logout).

**Cookie** = a small piece of text stored on a web browser: name–value pairs, **sent as an HTTP header** by the server and **sent back unchanged** on each visit.

- **Uses (3):** authenticating · session tracking · remembering user information (preferences).
- **Stops being sent when (4):** session ends (non-persistent cookie) · expiry date passes · expiry date changed to the past · the user deletes it.

|  | Session | Cookie |
| --- | --- | --- |
| Stored on | Server | Client (browser) |
| Size / safety | More data; user can't see or edit it | Small text; can be read or changed on the client |
| Ends | Logout or timeout | Browser close (non-persistent) or expiry date |

**Cookie problems†** 17/18 Q6(b): tracking/privacy (spyware), session hijacking (via XSS), cookie poisoning, shared computers. **Precautions:** clear cookies, block third-party cookies, private browsing, log out, HTTPS; developers use `Secure`, `HttpOnly`, `SameSite` and never store passwords.

#### 6. Client–server architecture

**Client–server** = a computing model in which the server hosts, delivers and manages most of the resources and services consumed by the client. One or more clients connect to a central server.

**Components:** client · server · load balancer · network-layer protocols.

**Data flow (7 steps):** client requests → load balancer routes it → server processes → server queries database → database returns data → server sends response → repeat.

##### Negative behaviours (3)

- Greater potential for failure (**centralised control**)
- Vulnerable to **DoS** (few servers, many clients)
- Very **expensive** to install and manage

##### Qualities

- **Supported:** scalability (horizontal/vertical) · availability · dependability · heterogeneity
- **Inhibited:** robustness (one overloaded server) · transparency

#### 7. Three-tier architecture 23/24 Q1(c) · 6m

| Tier | Responsibility | Technology |
| --- | --- | --- |
| Presentation | User interface: shows information, collects input | HTML, CSS, JS |
| Application (logic) | **Business logic**: rules, calculations, validation; talks to the data tier | PHP, Java, Node.js |
| Data | Stores and manages data; answers queries | MySQL, PostgreSQL |

**Key rule:** the presentation tier never talks to the database directly.

**Request flow (6):** user interaction → request processing → business logic → data access (read/write) → response → display.

**Benefits (6):** scalability · maintainability · flexibility · improved security · reliability · developer specialisation.

| Database approach (former view) | Web services approach (new view) |
| --- | --- |
| Application gets **direct access** to the DB (SELECT, INSERT, UPDATE) | Service **responds** to the application's request |
| **The application decides** what happens to the DB | Service **validates and authenticates**, then **the service decides** |

Web services add processing, e.g. credit-card transactions or real-time stock quotes and weather.

**Exam example: online doctor channelling.** **Presentation:** search doctors, view sessions and fees, booking form, pay, show confirmation. **Logic:** authenticate patients, check availability, allocate appointment number, calculate fees, process payment, send SMS. **Data:** doctors, specialties, schedules, patients, appointments, payments. Tie every point to the scenario.

#### 8. Web services, XML and JSON

**Web service** = a software system designed to support machine-to-machine interaction over a network. Uses a remote procedure call (RPC) mechanism; **platform- and language-independent** because it uses XML; uses **HTTP**. Styles: **RPC, SOAP, REST**. Problem: the "battle of the standards" (SOAP most common).

| XML | JSON |
| --- | --- |
| eXtensible Markup Language; information interchange | JavaScript Object Notation; **lightweight** data interchange |
| Building blocks: **elements** (tree) + **attributes** | Objects `{}`, arrays `[]`, key–value pairs |
| More verbose (open + close tags) | Subset of JavaScript but language-independent; alternative to XML |

#### 9. Web application architecture components (10) 23/24 Q1(b) · 4m

| Component | What it does |
| --- | --- |
| DNS | Finds the IP address for a domain name ("phone book") |
| Load balancer ★ | Directs incoming requests to one of several servers so none is overloaded → availability, scalability |
| Web app servers ★ | Process the user's request and send documents (HTML, JSON, XML) back; run the business logic |
| Databases | Organise, add, search, update, delete data, perform computations |
| Caching service ★ | Stores results of previous requests so repeated ones are answered faster; less DB load |
| Job queue (optional) | Jobs run on a schedule; has a queue + servers (e.g. sending emails) |
| Full-text search (optional) | Finds data among many documents by keyword |
| Services | Big features split into separate apps once the app grows |
| Data warehouse | Collect, store, analyse data: firehose (streaming) → cloud storage → warehouse |
| CDN ★ | Sends HTML, CSS, JS files and images, from a server near the user† → low latency |

★ = the four examined in 2023/24. One mark each: say **what it does** + **why it helps**.

#### 10. SPA and AJAX

**Single-page application (SPA)** = a web app that loads and updates content dynamically without refreshing the entire page; fewer interruptions, smoother interface.

|  | CSR (client-side) | SSR (server-side) | SSG (static generator) |
| --- | --- | --- | --- |
| HTML built | In the browser by JS | On the server per request | In advance (pre-made pages) |
| User first sees | Blank page / loader | Content ready | Static page, fast |
| Weakness | Slow for basic sites; uses device resources | Server work every request† | Not ideal for highly dynamic sites |

**AJAX** (Asynchronous JavaScript and XML) updates part of a page in the background. Combines **XHTML + CSS, DOM, XML, XMLHttpRequest, JavaScript**. **Drawbacks (3):** breaks the back button (history) · can't bookmark a page state · opens another attack vector.

#### 11. Custom development and CMS

**Custom web-app development** = creating a **unique application** for a specific set of challenges. Steps: requirement analysis → design → development → testing → maintenance. Built **in-house** or **outsourced**.

**CMS** = software that helps users create, manage, store and modify their digital content, through a user-friendly interface with **no coding**. Examples: WordPress, Drupal, Joomla, Shopify, Wix, Squarespace. 23/24 Q7(a) · 3m

| CMA: Content Management Application | CDA: Content Delivery Application |
| --- | --- |
| Lets users **add and change content** (editing side) | Back-end that **stores the content and makes it live** for visitors (publishing side) |

##### Features (what it has)

- Publishing controls (roles, permissions)
- Content editing tools (drag-and-drop, CTAs, scheduling)
- Content staging (test privately)
- Analytics
- Security (firewall, CDN vs DDoS)
- Templates and themes

##### Benefits (what you gain)

- Increased collaboration
- User-friendly, no coding
- Built-in SEO tools
- Highly scalable
- Consistent branding (tags/labels)
- Organisation (permissions, scheduling)

Exam focus · Lesson 02

##### Likely questions

- Three-tier architecture applied to a scenario 6m · load balancer / web app server / cache / CDN 4m · How PHP works 3m · CMS definition + 2 features + 2 benefits 3m · GET vs POST 2m · web app pros and cons 2m (all 2023/24).
- Cookies: purpose, problems, precautions 17/18.

##### Don't mix up

- GET (URL, visible) vs POST (body, hidden) · Session (server) vs Cookie (browser).
- Client-side vs server-side scripting · Client–server (2 roles) vs three-tier (3 layers).
- CMS **features** (what it has) vs **benefits** (what you gain) · CMA (edit) vs CDA (deliver).
- Database approach (app decides) vs web services approach (service decides) · XML vs JSON · CSR vs SSR vs SSG.

##### Lists to learn by count

- URL parts **9** · TLD types **6** · request parts **5** · HTTP methods **8** · stateless workarounds **4** · session factors **4** · cookie uses **3** / expiry **4** · client–server flow **7** / negatives **3** · three-tier flow **6** / benefits **6** · architecture components **10** · AJAX technologies **5** / drawbacks **3**.

### 2Mind Map

Root on the left; read each branch left to right. On a phone, scroll the map sideways.

Lesson 03

## Web Engineering (WebE)

Applying engineering discipline (planned process, modelling, testing) to building web applications.

Medium priority"Why agile?" 2m"Planning an e-commerce site" (repeated)

### 1Short Note

#### 1. What is web engineering?

Developing web-based systems is significantly different from traditional software development and causes many extra challenges; the nature and life cycle differ slightly.

**Multidisciplinary:** a complex web system needs a **team** with expertise from hypermedia, information systems, software engineering and network engineering.

#### 2. Software engineering vs web engineering

| Software Engineering | Web Engineering |
| --- | --- |
| Small user range | Large user range |
| Specific user requirements | Requirements change with time |
| Small growth and change | Rapidly changing |
| Budgets vary widely | Small budgets |
| Longer development time | Short development time |
| Specific hardware/software constraints | Constraints not specific (any browser/device) |
| Expertise scarce | Expertise widely available |
| Security and legal issues less important | Security and legal issues **very important** |
| Less emphasis on UI | **More** emphasis on UI |

#### 3. WebE process framework (incremental, cyclic)

Each increment **starts at Communication** and is **delivered at Deployment**; feedback then starts the next increment.

Communication → Planning → Modelling → Construction → Deployment ↺

| Activity | Actions | Meaning |
| --- | --- | --- |
| Communication | Formulation · Elicitation · Negotiation | Set goals · gather requirements · agree scope and cost |
| Planning | Estimation · Risk analysis · Scheduling · Monitoring | Effort/cost · what can go wrong · timeline · track progress |
| Modelling | Analysis · Design | Analyse content, functions, users · design UI, navigation, architecture |
| Construction | Coding · Testing | Build and test the increment |
| Deployment | Delivery · Evaluation | Release · collect user feedback |

##### Why must WebE be agile, adaptable and incremental? 23/24 Q4(a) · 2m

- **Requirements change with time** and the web changes **rapidly**, so a fixed plan soon becomes outdated.
- **Short development time and small budgets** → release working parts quickly.
- **Large, diverse user range** → feedback after each increment adapts the next one.

#### 4. Design goals (7)

Simplicity · Consistency · Identity · Robustness · Navigability · Visual appeal · Compatibility

Meanings†: simplicity = don't overload · consistency = same layout/colours/navigation everywhere · identity = matches the brand · robustness = functions are reliable · navigability = always know how to move · visual appeal = attractive · compatibility = works on all browsers, devices, speeds.

#### 5. Quality requirement tree (U-F-R-E-M)

| Attribute | Sub-attributes |
| --- | --- |
| Usability | Global site understandability · online feedback & help · interface & aesthetic features · special features |
| Functionality | Searching & retrieving · navigation & browsing · application-domain features |
| Reliability | Correct link processing · error recovery · user input validation & recovery |
| Efficiency | Response time · page generation speed · graphics generation speed |
| Maintainability | Ease of correction · adaptability · extensibility |

Memory line: "**U**sers **F**ind **R**eliable, **E**fficient, **M**aintainable apps."

#### 6. Design actions pyramid

Top = closest to the **user**, base = closest to the **technology**:

USER → Interface → Aesthetic → Content → Navigation → Architecture → Component → TECHNOLOGY

#### 7. Testing WebApps (7 types)

| Type | Checks† |
| --- | --- |
| Content | Errors in text, images, facts, spelling |
| Interface | Buttons, forms, menus behave correctly |
| Navigation | Every link and path works; no dead ends |
| Component | Each function/module works alone |
| Configuration | Different browsers, OSs, devices, servers |
| Performance | Speed under heavy load |
| Security | Vulnerabilities (SQLi, XSS, weak login) |

#### 8. WebE best practices (8)

1. Understand business needs and product objectives
2. Describe how users will interact with the WebApp
3. Always develop a project plan
4. Spend time modelling what you will build
5. Review the models for consistency and quality
6. Use tools that allow many reusable components
7. Don't reinvent when you can reuse
8. Don't rely on early users to debug: test comprehensively before release

Exam focus · Lesson 03

##### Likely questions

- **Why must the WebE process be agile, adaptable and incremental?** 23/24 · 2m: link to changing requirements, rapid change, short time, small budget, large users, feedback per increment.
- **Things to consider before planning a multi-category e-commerce site** 17/18, 18/19: goals and requirements (communication) · planning · navigation/categories · platform · design goals and quality · security and legal (HTTPS, PCI DSS, privacy) · operations · testing before launch.
- Compare WebE with SE (table of 4–5 rows) · name the 5 activities with their actions.

##### Don't mix up

- Process **activities** (5) vs their **actions** (e.g. elicitation is an action of Communication).
- Design **goals** (7) vs **quality attributes** (5) vs **design pyramid layers** (6).
- Usability (quality attribute) vs functionality (does it work).

##### Lists to learn by count

- Disciplines **4** · SE vs WebE rows **9** · activities **5** · design goals **7** · quality attributes **5** · pyramid layers **6** · test types **7** · best practices **8**.

### 2Mind Map

Root on the left; read each branch left to right. On a phone, scroll the map sideways.

Lesson 04

## Web Design Principles

Making a site look good (aesthetic design: layout, colour, graphics, typography) and work well (functional and usable design).

High priorityCARP 4mColour & typography: not asked yet (watch)

### 1Short Note

**Web design** = the planning and creation of websites: information architecture, user interface, site structure, navigation, layout, colours, fonts and imagery, combined with design principles to meet the owner's and designer's goals.

#### 1. Layout and the CARP principles 23/24 Q4(c) · 4m

Layout forms relationships and defines meaning, creates **visual hierarchy** and interest, and increases readability and usability. It should follow CARP.

| Principle | Lecture definition | Web example |
| --- | --- | --- |
| Contrast | Elements differ in colour, size, shape, texture, orientation, position or movement to group or separate them | Bright orange "Buy now" button; big bold heading |
| Alignment | The placement, position, orientation and grouping of elements | Form labels and inputs in a neat column; product grid |
| Repetition | Repeated colours, shapes, textures, fonts, graphics, spatial relationships → consistency, branding | Same header, logo, button style on every page |
| Proximity | White space and logical structure (grouping related items) to create visual unity | Photo, name, price, "Add to cart" together in one card |

#### 2. Colour

The colour scheme should enhance the page, **reinforce branding** and give good contrast. Different colours spark different emotions; colour can be about 85% of the reason for buying decisions.

| Colour | Use for | Avoid |
| --- | --- | --- |
| Red | Attention, **CTA buttons**; food, fashion, sports | Too much = aggressive; professional/luxury |
| Orange | Energy; kids; good for CTAs | With warm colours; hard to read as text |
| Yellow | Happiness, hope; catch attention | Overpowering; anxiety |
| Blue | Calm, loyalty; **IT, security, finance** | **Food** sites (curbs appetite) |
| Green | Nature, growth; health, environment; dark green = wealth | Luxury or tech products |
| Gray | Professional; backgrounds; luxury | Dull; poor for attention |
| Pink | Love, kindness; feminine products | If men are targeted |
| White | Purity, cleanliness; healthcare, science, tech | Careless pairing |

**Vocabulary:** **hue** = colour family · **tint** = hue + white · **tone** = hue + grey · **shade** = hue + black.

| Scheme | How it is built |
| --- | --- |
| Monochromatic | Shades, tints, tones of **one hue** |
| Analogous | Colours **next to each other** (dominant, supporting, accent): eye-pleasing |
| Complementary | **Directly opposite** hues (one warm, one cool): strong contrast |
| Triadic | Three colours at the corners of an **equilateral triangle** |
| Split-complementary | One colour + the two colours **beside its complement** |
| Tetradic (rectangle) | Two colours + their two opposites |
| Square | Four colours **evenly spaced** (every 90°) |

##### Choosing colours: best practices (8)

1. Analyse your target audience
2. Check which colours suit your industry (blue = banks, red = retail/food…)
3. 60-30-10 rule: 60% dominant, 30% secondary, 10% accent
4. Design in **greyscale first**
5. **No complementary colours for text and background** (they vibrate)
6. **Bright colours for CTA buttons**
7. Understand base, accent and text colours
8. Use **negative space**

**Tools:** Adobe Color CC, Colordot, Paletton, Flat UI Color Picker, Check My Colours, Canva, Khroma, Colorspace.

#### 3. Graphics

Graphics enhance pages and should be **relevant** to the site's look and feel. Tools: Photoshop, Fireworks, GIMP, Inkscape, Paint.net, Pixlr.

| Lossless compression | Lossy compression |
| --- | --- |
| All the information can be restored | Permanently eliminates certain information; cannot be fully restored |
| Larger files, perfect quality; PNG, GIF† | Much smaller files; JPEG for photos† |

#### 4. Typography

| Font | Typography |
| --- | --- |
| The style of **typeface** used to display text and other **glyphs**; controlled with CSS | The **arrangement and appearance** of text: margins, line and paragraph spacing, alignment |

**Best practices:** use **real text, not text as graphics** (pixelates when enlarged) · most readable fonts · fonts native to modern OSs. Colour and font affect the **mood** of text.

| Family | Characteristic | Examples |
| --- | --- | --- |
| Serif | Flared strokes on letter tips; thick and thin strokes | Times New Roman, Georgia |
| Sans-serif | Plain endings, blockier; no flares | Arial, Helvetica, Open Sans |
| Cursive | Like handwriting; harder to read; poor for accessibility | Monarda, Gustav |
| Fantasy | Decorative; not for long text | Cinzel, Aquary |
| Monospace | Every letter has the **same width**; for code | Courier New, Consolas |

- **Cursive:** do use for logos, headlines, short words · don't use for long text, instructions, small sizes.
- **Screen fonts:** **Verdana** (very popular on screen; "I" and "l" look different, unlike Arial) · **Tahoma** (alternative to Arial; larger than Arial, smaller than Verdana; tighter spacing).
- **Proper use:** no more than 2–3 font faces · avoid ALL CAPITALS · `<b>` = no meaning, just bold look; `<strong>` = **strong emphasis** (importance).

#### 5. Functional and usable design

**Functional** = renders without error and works as expected: **all links work, all interactivity works, the page loads quickly**.

**Usability** = how easy a user interface is to use; it measures the quality of a person's experience with a website (e.g. an FAQ that anticipates needs; information found quickly).

| HCI usability attribute (LEMES) | Question it asks |
| --- | --- |
| Learnability | How easy is it the **first time**? |
| Efficiency | Once familiar, how **quickly** can tasks be done? |
| Memorability | After a break, how easily do users **become familiar again**? |
| Errors | How many errors, how quickly do users **recover**? |
| Satisfaction | How **pleasant** is it to use? |

##### Writing web content

- No jargon or slang
- Careful with humour or clever headings
- Headings that clearly describe the content
- **Don't underline** text (looks like a link)

##### Browser compatibility

Test in many browsers and devices so the site displays consistently.

##### Accessibility basics (6)

**Accessibility** = making websites usable by people of all abilities and disabilities.

- Text links as alternatives to image links
- High contrast text/background
- **Don't use colour alone** to convey meaning
- **Alt text** for images
- Summarise tables, give headings
- Transcripts for audio, captions for video

**Multimedia** = combined use of audio, video, animation and other interactive features. Interactivity: clicking links, mouse-over images, forms and surveys, customising a page, video/audio, live chat.

| Functionality | Usability |
| --- | --- |
| Does it **work**? (links, forms, speed) | Is it **easy and pleasant** to use? (LEMES) |

Exam focus · Lesson 04

##### Likely questions

- **Explain the CARP principles** 23/24 · 4m: name → lecture definition → web example, one mark each.
- **What is usability?** + LEMES (with the testing methods from Lesson 5) 23/24 Q8(a).
- Watch (taught, not yet asked): 60-30-10 rule · colour schemes · when to use or avoid a colour for a scenario · font families · lossless vs lossy · font vs typography.

##### Don't mix up

- Font vs typography · lossless vs lossy · `<b>` vs `<strong>` · serif vs sans-serif.
- Tint (+white) vs tone (+grey) vs shade (+black).
- Complementary (opposite) vs analogous (adjacent) vs split-complementary.
- Functional (works) vs usable (easy to use).

##### Lists to learn by count

- CARP **4** · colour psychology **8** · schemes **7** · colour best practices **8** · font families **5** · LEMES **5** · content-writing rules **4** · accessibility basics **6**.

### 2Mind Map

Root on the left; read each branch left to right. On a phone, scroll the map sideways.

Lesson 05

## HCI in Web Design

Understanding users, UI vs UX, layout, appearance, navigation, the 17 UI design principles, cognitive load theory and usability testing.

High priority13 marks in 2023/24

### 1Short Note

#### 1. Design and the three interface questions

**Design** (web) = planning and arranging content, focusing on visual aspects and UX. In general, design = achieving goals within constraints (goals = purpose: who is it for, why; constraints = materials, platforms).

| Question every interface must answer | How |
| --- | --- |
| Where am I? | Show which WebApp and the location in the hierarchy (logo, title, breadcrumbs) |
| What can I do? | Show current options: available functions, live links |
| Where have I been, where am I going? | A "map" of paths: visited links, progress steps |

#### 2. Users, affordance and Norman's doors

- **Understand your materials:** **users** (psychology, social aspects, human error) · **computers** (limits, capacities, platforms) · **their interactions**.
- **Don Norman**: pioneer of UX and HCI; introduced **affordance** and **user-centred design**. A "Norman door" suggests the wrong action (if a door needs a PUSH sign, its design failed).
- Affordance = things suggest **by their shape and other attributes** what you can do with them: a mug handle affords grasping; a button-like object affords a click; icons afford clicking (learned from the "culture of computer use").

#### 3. UI vs UX 23/24 Q4(b) · 2m

| UX (User Experience) | UI (User Interface) |
| --- | --- |
| **Feel and experience**: emotions, satisfaction | **Visual aesthetics**: visual appeal |
| **Wireframes and prototypes** define flow and functionality | **Mockups and graphics** finalise the visuals |
| **Holistic** journey, seamless and meaningful | **Individual elements** and interactions |
| **Research**: user analysis, testing, strategy | **Implementation** of design choices |
| **User-centred**; solves **pain points** | **Interface-centred**; pleasing designs |
| Features: user research, usability testing, information architecture, wireframing/prototyping, interaction design, accessibility | Features: visual elements, interactivity, consistency, responsiveness, navigation, accessibility |

**One-line answer:** UX decides **what the product does and how the whole journey feels**; UI decides **how it looks** and how its elements are presented.

#### 4. HCI: definition, goals, factors, tasks

**HCI** = a multidisciplinary field that studies and designs how people interact with computer systems, aiming for user-friendly, efficient and enjoyable experiences. UIs are designed on HCI principles.

##### Goals (5)

Safety · Effectiveness · Efficiency · Usability · Appeal

##### Factors (7)

- **Organisational** (job roles, health & safety)
- **Users** (cognition, motivation, experience)
- **User interface** (input/output devices, colour, icons)
- **Task** (easy, complex, novel)
- **Constraints** (cost, time, staff)
- **System functionality** (hardware, software)
- **Productivity** (output, quality, fewer errors)

##### HCI design tasks (in order)

1. **Know your user**: **personas** (description of an example user) · **cultural probes** (qualitative data when observation is hard; probe packs) · ethnographic field studies
2. **Scenarios**: "stories for design"; reveal problems early, verify the design makes sense, communicate and validate models
3. **Screen design and layout**: grouping, order, decoration, alignment, white space. "UI is the key to usability."
4. **User action and control**: entering information (logical order, top→bottom, left→right, right-justified labels) · knowing what to do (active vs passive, standard icons) · affordance
5. **Appropriate appearance** (below)
6. **Navigation design** (below)

#### 5. Appropriate appearance and localisation

- Presentation depends on the **kind of information**, the **technology** and the **purpose** (lists → sort order; numbers → graphs; hierarchies → outlines). Let users choose the representation.
- Aesthetic designs increase satisfaction, but beauty and utility may conflict; text needs a **plain, low-contrast background**.
- **Colour:** overused "because it is there"; beware colour blindness; use sparingly to reinforce. **3D:** don't overuse; 3D text is hard to read.
- **Applying colour (5):** limit colours · show status changes · colour-code to support the task · be consistent · careful pairings.
- **Localisation / internationalisation** = change interfaces for particular cultures/languages. **Globalisation** = choose symbols that work everywhere.

#### 6. Navigation design

**Navigation** = accurately ascertaining one's position and planning and following a route.

##### 4 levels

- **Widgets** (wording of menus/buttons)
- **Screens** (page design, grouping)
- **Within the application** (site structure)
- **Environment** (web, browser, external links)

##### 4 golden rules (local structure)

- Knowing **where you are**
- Knowing **what you can do**
- Knowing **where you are going**
- Knowing **where you have been**

Breadcrumbs give live links to higher levels; needing the browser's back button to get back is **poor design**.

##### Global structure

- **Hierarchy diagram**: logical groupings, high-level breakdown
- **Network diagram**: paths and branches; more **task-oriented**

##### NSU → WoN → NN

- NSU (navigation semantic unit) = a set of information and navigation structures that fulfil a subset of related user requirements
- **WoN** (way of navigating) = the best pathway to a navigational goal for a **specific type of user**
- Each WoN = **navigational nodes** joined by **navigational links**

##### Content architecture

Linear · Grid · Hierarchical · Network

#### 7. UI design principles (17)

| Group (memory aid) | Principles |
| --- | --- |
| Predictability | **User familiarity** (user's terms) · **Consistency** (same formats) · **Minimal surprise** (predict similar commands) · **Learnability** (minimise learning and relearning) · **Human interface objects** (reuse standard controls) |
| Error safety | **Recoverability** (undo, confirm destructive actions, soft deletes) · **Maintain work-product integrity** (auto-save) · **Track state** (return later and continue) |
| Feedback & speed | **Communication** (show status) · **Latency reduction** (multi-tasking) · **Efficiency** · **Anticipation** (user's next move) |
| Clarity & inclusion | **Readability** · **User diversity** (e.g. larger text) · **Focus** (on the task) · **Visible navigation** ("work brought to them") · **Controlled autonomy** (free movement within conventions) |

#### 8. Cognitive load theory 23/24 Q4(d) · 6m

**Cognitive load** = the amount of mental effort users need to expend to navigate a website, process information and complete tasks. Too much → frustration and poor engagement.

| Type | Meaning | Designer should |
| --- | --- | --- |
| Intrinsic | Complexity of the new information itself | Simplify |
| Extraneous | Effort that distracts working memory (clutter) | Reduce |
| Germane | Deep processing, linking to previous learning | Maximise |

**Working memory** = small capacity; holds new information, forgets what isn't transferred. **Long-term memory** = huge capacity; stores what is learnt.

| CLT principle | Idea | UX techniques |
| --- | --- | --- |
| 1. Information store | Brain stores innate (primary) and learned (secondary) knowledge | Familiar, consistent patterns · simplicity · tooltips, videos, guided onboarding · personalisation |
| 2. Borrowing & reorganising | How we acquire and organise outside information | Consistent layouts · **chunking** · gradual disclosure |
| 3. Randomness as genesis | Novelty comes from experimentation; tension between new features and comprehension | Onboarding and contextual help · **progressive disclosure** · features that address user goals (no novelty for its own sake) · familiar patterns |
| 4. Narrow limits of change | Finite capacity to process at once | Break tasks into **small steps** · reveal gradually · familiar categories and labels · recognisable UI elements · clear onboarding |

**Example:** a long sign-up form becomes a 3-step form with a progress indicator and tooltips (narrow limits of change + contextual help).

#### 9. Usability testing methods (7) 23/24 Q8(a) · 5m

| Method | How it works | Pros / cons (lecture) |
| --- | --- | --- |
| In-person (in-house) | On-site; researchers moderate and observe | + in-depth info · − more resources, qualified researchers, small groups |
| Unmoderated remote | No moderator; own device, anywhere | + less costly, real-world usage |
| Moderated remote | Own environment, moderator instructs and observes | − needs trained moderators |
| Guerrilla | Prototype shown to random people in public (café) | + cheap, simple, quick · − may not be real users |
| Phone interview | Questions/instructions over the phone | + wide geographic reach · − needs well-trained moderators |
| Card sorting | Users organise virtual content cards and explain why | + shows how users see the structure; helps organise the site |
| Session recording | Records mouse movements and clicks (e.g. **Hotjar**) | Real behaviour, but not the "why"† |

**OOHDM** (Object-Oriented Hypermedia Design Method): title slide only in the deck.

Exam focus · Lesson 05

##### Likely questions

- **How does UX differ from UI?** 2m: short table, 3–4 rows.
- **Use CLT principles to reduce mental effort** 6m: define + 3 load types, then all 4 principles with techniques and at least two examples.
- **Usability + two testing methods** 5m: definition + LEMES (1m), then each method: how it works + one pro + one con (2m each).
- Possible next: explain N UI principles · navigation golden rules · NSU/WoN · affordance.

##### Don't mix up

- UI (looks, mockups) vs UX (journey, wireframes, prototypes).
- Intrinsic (simplify) vs extraneous (reduce) vs germane (maximise).
- Moderated vs unmoderated testing · hierarchy vs network diagram · localisation vs globalisation.
- HCI **goals** (5) vs HCI **factors** (7) vs usability **attributes** LEMES (5).

##### Lists to learn by count

- Interface questions **3** · HCI goals **5** / factors **7** / tasks **6** · navigation levels **4** / golden rules **4** · content architectures **4** · UI principles **17** · load types **3** · CLT principles **4** · testing methods **7**.

### 2Mind Map

Root on the left; read each branch left to right. On a phone, scroll the map sideways.

Lesson 06

## Responsive Web Design (RWD)

One website that adapts to every screen size using the same HTML: layout history, mobile first, Marcotte's three ingredients, media queries, testing and performance.

High priorityWhole 20-mark question in 2023/24Media-query code

### 1Short Note

#### 1. Evolution of web layouts

| Period | Approach | Key facts |
| --- | --- | --- |
| Mid 1990s | Free-flowing content | Content flowed to the browser margins (**Mosaic**) |
| Late 1990s | HTML4 **tables and frames** | **Netscape** introduced frames (March 1996); frames in HTML 3.0 |
| 2000s | **XHTML + CSS** | XHTML (Jan 2000); CSS 1.0 (Dec 1996), first full support IE5 Mac (Mar 2000); monitors CRT → LCD; resized windows |

**Complexity and divs:** flexible design won the debate, but XHTML/CSS made flexible multi-column layouts hard (nested divs); with the rise of the blog many designers went back to fixed widths.

#### 2. Fixed vs flexible layouts 23/24 Q2(c) · 4m

The debate (John Allsopp, *A Dao of Web Design*, 2000): **fixed-width** = the designer should be in control; **flexible (fluid or elastic)** = the user should choose the width.

| Layout | Units | Behaviour |
| --- | --- | --- |
| Fixed | Pixels (e.g. 960px) | Same width always |
| Fluid (liquid) | Percentages | Columns resize with the window |
| Elastic | em† | Scales with the user's font size |

| Fixed-width: pros† | Fixed-width: cons† |
| --- | --- |
| Designer has full control; looks the same everywhere | Small screens: horizontal scroll or zoom; content cut off |
| Easier to design; predictable line lengths and image sizes | Large screens: wasted white space |
| Consistent, pixel-precise design | Ignores user choice; **not responsive**; text resizing can break it |

#### 3. Mobile web, One Web, mobile first

- **Mobile web history:** handheld stylesheet (weak browsers) → desktop sheet scaled down → **separate mobile site** with its own domain/address (bbc.co.uk/mobile).
- One Web = providing **the same information and services regardless of device**, though not in exactly the same representation (W3C Mobile Web Best Practices).
- Mobile first 23/24 Q2(a) · 2m = standard practice to **design for narrow viewports first**, then tweak the layout for wider viewports. Base styles for mobile + `min-width` queries in ascending order (**progressive enhancement**).

#### 4. Making pages responsive 23/24 Q2(d) · 6m

**Ethan Marcotte**, "Responsive Web Design", A List Apart, **May 2010**. Three core components: flexible grid · flexible images · CSS media queries, plus the **viewport** meta tag.

##### Viewport

Mobile browsers render on a **980px** viewport and shrink it. Fix in every `<head>`:

```
<meta name="viewport"
  content="width=device-width, initial-scale=1.0" />
```

Without it, small-screen media queries may never trigger.

##### Flexible grid

Use **%**, **`fr`** units, `minmax()`, content-based track values and `flex` instead of fixed pixels.

```
.layout { display:grid; grid-template-columns: 2fr 1fr; }
```

##### Flexible images

Container gets a % width; image gets `max-width: 100%`, so it scales with its container.

```
img { max-width:100%; height:auto; }
```

**Check responsiveness:** resize the browser · responsinator.com · browser developer tools.

#### 5. Media queries and breakpoints 23/24 Q2(e) · 6m code

A **media query** asks a question about the device/viewport and applies its CSS only when the answer is true.

```
@media screen and (min-width: 768px) { h1 { font-size: 3rem; } }
/* rule   type   operator  feature: value */
```

| Part | Values |
| --- | --- |
| Media types (CSS2) | `all` · `print` · `screen` · `speech` |
| Media features (CSS3) | `width` (min/max) · `aspect-ratio` · `orientation` · `resolution` |
| Logical operators | `and` (all true) · `,` comma = or · `not` (negate) |

min-width: X = "at least X" (X and up) · max-width: X = "at most X" (X and down)

##### 2023/24 exam questions, answered

```
/* (i) LightGreen background: portrait AND width less than 800px */
@media screen and (orientation: portrait) and (max-width: 800px) {
  body { background-color: LightGreen; }
}
/* (ii) 80px DarkBlue text: minimum width of 768px */
@media screen and (min-width: 768px) {
  body { font-size: 80px; color: DarkBlue; }
}
```

Checklist: `@media` · type · each feature in **( )** with a **colon** · `and` between conditions · a selector · braces · semicolons.

| Graceful degradation | Mobile first / progressive enhancement |
| --- | --- |
| Default styles for **large** screens | Default styles for the **smallest** screen |
| `max-width` rules, **descending** (1400 → 400) | `min-width` rules, **ascending** (400 → 1400) |

- **Where:** in the stylesheet **after the baseline styles**, or load external sheets with `<link media="…">` / `@import`.
- Breakpoint = the point at which a media query introduces a style change. Best practice: breakpoints for **individual parts** of the page · design narrow first · **em-based** breakpoints scale with font size.

#### 6. Designing responsively

**Aspects (6):** content hierarchy · layout · typography · navigation · images · special content.

- **Content:** decide what shows first on small screens; **content parity** = same content on every device, maybe not in the same place.
- **Layout:** based on fluid layouts; one column on small devices, add columns as space grows; 45–75 characters per line (longer → add a breakpoint). Patterns: mostly fluid, column drop, layout shifter, off-canvas†.
- **Typography:** avoid complicated fonts on small screens; larger line height and margins on large screens.
- **Navigation:** top links, "Menu" toggle, off-canvas drawer, select menu, footer anchor.
- **Images:** responsive image markup; **smallest file by default**; keep important details (zoom or crop); avoid text in graphics.
- **Special content:** forms with Flexbox · responsive table patterns · scale video; link to a map app instead of a tiny map.

**Site testing:** real devices (best) · **emulators** (desktop app simulating a device) · **browser testing services** (subscription).

#### 7. Web performance optimisation 23/24 Q2(b) · 2m

Why: sites behave differently across regions because of Internet speed differences.

1. **Eliminate unnecessary downloads** (remove comments and white space)
2. **Leverage browser caching**
3. **Reduce HTTP requests** (combine scripts, combine stylesheets, fewer images)
4. **Image optimisation**
5. **Enable compression** (e.g. gzip)
6. **Minimise (minify) HTML, CSS and JavaScript**

##### HTTP caching and ETags

**HTTP caching**: the browser stores copies of resources and reuses them without asking the server. **Problem:** content that changes often → stale copies. **Solution:** ETags, tokens generated by the server in HTTP headers. Browser sends `If-None-Match: "v7"`; if unchanged the server replies **304 Not Modified** (no body)†.

Exam focus · Lesson 06

##### Likely questions (Q2 of 2023/24 = 20 marks)

- **Write media queries** from a sentence 6m: exact syntax matters.
- **How can pages be made responsive?** 6m: Marcotte 2010 → viewport (with code) → flexible grid → flexible images → media queries/breakpoints → mobile first and testing.
- **Pros and cons of fixed-width layout** 4m: 2 pros + 2 cons.
- **What is mobile-first design?** 2m · **Four performance techniques** 2m.

##### Don't mix up

- `min-width` (X and up) vs `max-width` (X and down).
- Graceful degradation (large first, max ↓) vs progressive enhancement / mobile first (small first, min ↑).
- Fixed (px) vs fluid (%) vs elastic (em) · One Web (same content) vs separate mobile site.
- Media **types** (CSS2) vs media **features** (CSS3).

##### Lists to learn by count

- Layout eras **3** · Marcotte components **3** (+ viewport) · media types **4** · features **4** · operators **3** · design aspects **6** · testing methods **3** · performance techniques **6**.

### 2Mind Map

Root on the left; read each branch left to right. On a phone, scroll the map sideways.

Built from your CMIS 3124 lesson notes 01–06 and past-paper notes. Marks and years refer to the past papers in your vault.