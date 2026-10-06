---
type: revision
course: CMIS 3124
status: complete
tags: [cmis3124, revision, definitions]
aliases: [Definitions, CMIS 3124 definitions]
---
# Important Definitions

> [!info] Definitions follow the lecture wording as closely as possible. 🔴 = asked directly in a past paper. Home: [[00. Course Overview]] · [[High Priority Topics]]

## 00 · HTML and CSS ([[00.00 HTML and CSS Foundations]])
| Term | Definition |
|---|---|
| Website | A collection of web pages and resources accessed through a web browser. |
| HTML | HyperText Markup Language: *hypertext* = text with links; *markup* = tags that label the parts of a page. It is **not** a programming language. |
| Element | Opening tag + content + closing tag, e.g. `<p class="intro">Hi</p>`. |
| Pseudo-class | Selects an element in a particular **state or position** (`:hover`, `:nth-child`). |
| Pseudo-element | Styles **part** of an element or adds generated content (`::after`, `::placeholder`). |
| Cascade | Resolves conflicting rules by importance → specificity → source order. |

## 01 · Evolution of the Web ([[01.00 Evolution of the Web]])
| Term | Definition |
|---|---|
| WWW | A graphical entryway to the Internet and a collection of inter-linked documents, working through HTTP, URLs and HTML. |
| Web 1.0 🔴 | The earliest stage (early 1990s–early 2000s): static information portals without interactivity. |
| Web 2.0 🔴 | Empowers users to **participate, create and share** content (blogs, wikis, social media). Community-driven. |
| Web 2.5 | A transitional phase adding blockchain, NFTs and privacy features to Web 2.0 platforms. |
| Web 3.0 🔴 | The **decentralised** web: user control, privacy, peer-to-peer interaction without central authorities, data ownership. |
| Web 4.0 🔴 | The **symbiotic** web: AI + ML + connected devices give predictive, context-aware, real-time decisions. |
| Blog 🔴 | A "web log": a two-way web-based communication tool, private or public. |
| Wiki 🔴 | A web-based collaborative authoring system where anyone can add and edit articles with a browser (e.g. Wikipedia). |
| Mashup 🔴 | A page or site that combines information and services from multiple web sources. |
| RSS | Really Simple Syndication: an XML web feed with summaries and links, read by an aggregator. |
| Social bookmarking 🔴 | A browser-based way to save, sort and share web links. |

## 02 · Web Architecture ([[02.00 Web Architecture and Development]])
| Term | Definition |
|---|---|
| Web application | Software that works in a browser, hosted on a remote server, built with client- and server-side scripting. |
| PHP 🔴 | An open-source **server-side** scripting language. The server runs the code and returns **plain HTML** to the browser. |
| HTTP | A **stateless** request–response protocol between browser and server. |
| Session | A series of related browser requests from the same client during a certain time period. Data is held on the server. |
| Cookie | A small piece of text stored by the browser (name–value pairs), sent back to the server with each request. |
| Three-tier architecture 🔴 | Separates the **presentation**, **application (logic)** and **data** tiers. |
| Load balancer 🔴 | Distributes incoming requests across several app servers for scalability and availability. |
| Caching 🔴 | Stores pre-computed results so later requests are served faster. |
| CDN 🔴 | Content Delivery Network: servers around the world deliver static assets from a location near the user. |
| Web service | A software system for machine-to-machine interaction over a network; platform- and language-independent. |
| CMS 🔴 | Software that helps users create, manage, store and modify digital content **without coding**. Made of a CMA (content management application) + a CDA (content delivery application). |
| SPA | A single-page application: loads and updates content dynamically without full-page reloads. |

## 03 · Web Engineering ([[03.00 Web Engineering]])
| Term | Definition |
|---|---|
| Web engineering | A multidisciplinary approach (hypermedia, information systems, software engineering, network engineering) to building high-quality WebApps. |
| Incremental process 🔴 | Each increment passes through communication → planning → modelling → construction → deployment, and feedback shapes the next increment. |

## 04 · Web Design Principles ([[04.00 Web Design Principles]])
| Term | Definition |
|---|---|
| Web design | Planning and creating websites: information architecture, UI, structure, navigation, layout, colour, fonts, imagery. |
| CARP 🔴 | **C**ontrast, **A**lignment, **R**epetition, **P**roximity: layout principles that create visual hierarchy and readability. |
| Usability 🔴 | How easy a user interface is to use (LEMES attributes). |
| Lossless / lossy compression | Lossless restores all the data; lossy permanently removes some data to make smaller files. |

## 05 · HCI ([[05.00 HCI in Web Design]])
| Term | Definition |
|---|---|
| HCI | The study of how people interact with computers, and the design of systems that are safe, effective, efficient, usable and appealing. |
| UX 🔴 | The overall **feel and experience** of a product: the user's journey, emotions and satisfaction. |
| UI 🔴 | The **visual and interactive elements** the user sees and touches. |
| Affordance | A design property that suggests how an object is used (Norman). |
| Cognitive load 🔴 | The amount of working-memory effort needed to process information. It is **intrinsic** (task difficulty), **extraneous** (bad presentation) or **germane** (building schemas). |
| Persona | A fictional, representative user built from research. |
| Card sorting | A usability method in which users group topics to reveal their mental model of the navigation. |

## 06 · Responsive Web Design ([[06.00 Responsive Web Design]])
| Term | Definition |
|---|---|
| RWD 🔴 | One site that adapts its layout to any screen using a **flexible grid, flexible images and media queries** (Ethan Marcotte, 2010). |
| Mobile first 🔴 | Design for the narrowest screen first, then add styles for wider screens with `min-width` queries (progressive enhancement). |
| Media query 🔴 | A CSS rule that applies styles only when a media type and features (e.g. width, orientation) match. |
| Breakpoint | The width at which a media query changes the layout. |
| Viewport meta tag | `<meta name="viewport" content="width=device-width, initial-scale=1.0">` makes the browser use the device width. |
| One Web | The same information and services on every device, though not necessarily the same presentation. |
| ETag | A server-generated token that lets the browser check whether its cached copy is still current. |

## 07 · React ([[07.00 ReactJS]])
| Term | Definition |
|---|---|
| React | A component-based JavaScript library for building UIs (Jordan Walke, Facebook). |
| Virtual DOM | An in-memory copy of the DOM. Changes are diffed so that only the changed parts are written to the real DOM. |
| Props / State | Props = read-only inputs from the parent. State = the component's own changeable data. |
| Hook | A function (`useState`, `useEffect`) that gives function components state and lifecycle features. |

## 08 · Bootstrap ([[08.00 Bootstrap]])
| Term | Definition |
|---|---|
| Bootstrap | A free HTML/CSS/JS framework for responsive, mobile-first websites. |
| `.container` 🔴 | A responsive fixed-width container whose max-width changes at each breakpoint. |
| `.container-fluid` | A full-width container (100% at all sizes). |
| Grid system 🔴 | A flexbox-based **12-column** layout: `.row` > `.col-{bp}-{n}`, where the column numbers add up to 12. |

## 09 · Accessibility ([[09.00 Web Accessibility]])
| Term | Definition |
|---|---|
| Web accessibility | Designing so people with disabilities can perceive, understand, navigate and interact with the Web. |
| WCAG / UAAG / ATAG | W3C WAI guidelines for **content**, **user agents** and **authoring tools**. |
| ARIA 🔴 | Accessible Rich Internet Applications: **roles, properties and states** that add semantics for assistive technologies. |
| Role / Property / State 🔴 | Role = what it is; property = extra (mostly fixed) semantics; state = current, changing condition. |
| `aria-live` | Announces dynamic content changes (off / polite / assertive). |

## 10 · E-Commerce ([[10.00 E-Commerce]])
| Term | Definition |
|---|---|
| E-commerce 🔴 | The use of electronic communications and digital information-processing technology in business transactions to create, transform and redefine relationships for value creation between or among organisations, and between organisations and individuals. |
| E-business | All electronic business processes (SCM, CRM, ERP…), not only buying and selling. No money transaction is needed. |
| Authentication 🔴 | Verifying a user's identity before granting access. |
| 2FA / MFA | Two factors exactly / two or more factors (something you know, have, are). |
| B2B / B2C / C2C / C2B 🔴 | Business↔business / business→consumer / consumer↔consumer / consumer→business. |
| G2C / G2B / G2G | Government services to citizens / businesses / other government agencies. |
| Shopping cart | Software that lets customers select items, review them and check out. |
| Recommendation engine | A system that suggests products using collaborative, content-based, demographic, community or hybrid filtering. |
| GDPR | The EU data protection regulation; it applies to any organisation processing EU residents' data. |
| PCI DSS | The Payment Card Industry Data Security Standard: 6 goals, 12 requirements. |

## 11 · Social Media and SEO ([[11.00 Social Media and Web Influence]])
| Term | Definition |
|---|---|
| SEO 🔴 | Improving a site so that search engines rank it higher in **organic** (unpaid) results. |
| On-page SEO 🔴 | Optimisation inside the page: titles, meta descriptions, URLs, headings, alt text, keywords. |
| Off-page SEO | Optimisation outside the page: backlinks/PageRank, social signals, sitemaps, robots.txt. |
| Influencer marketing 🔴 | A strategic collaboration with credible personalities to promote products to their followers. |
| Social commerce | Selling directly inside social-media platforms. |
| UGC | User-generated content: reviews, photos and posts made by customers. |

## 12 · Ethics ([[12.00 Web Design Ethics and Policies]])
| Term | Definition |
|---|---|
| Ethical web development 🔴 | Designing, creating and maintaining sites that prioritise users' rights, safety and well-being, with transparency, accountability and fairness. |
| Dark pattern 🔴 | A deceptive design trick that makes users do things they did not intend (pay more, subscribe, share data). |
| Anonymisation / pseudonymisation | Identifiers removed permanently / replaced but re-linkable with a key. |
| Web compliance | Meeting legal, security, accessibility and industry standards (GDPR, WCAG, PCI). |

## 13 · Security ([[13.00 Web Security and Cyber Threats]])
| Term | Definition |
|---|---|
| Web security | Protecting data in transit and safeguarding sites, apps and servers from attacks, breaches and unauthorised access. |
| SQL injection 🔴 | Inserting malicious SQL through user input so the database runs unintended queries. |
| XSS 🔴 | Cross-site scripting: an attacker's script runs in the victim's browser as if it came from the trusted site. |
| Code injection 🔴 | Injected code is interpreted or executed by the application because of poor input/output validation. |
| DoS 🔴 | Denial of service: making a service unavailable by flooding it or crashing it. DDoS uses many sources. |
| Phishing | Posing as a trusted entity to steal credentials or data. |
| Virus / worm | A virus needs a host and user action; a worm spreads by itself. |
| Zero Trust 🔴 | "Never trust, always verify": continuous validation, least privilege, assume breach. |
| DevSecOps 🔴 | Integrating security into every stage of the DevOps CI/CD pipeline as a shared responsibility. |

## 14–15 · AI and Future Trends ([[14.00 AI in Web Development]] · [[15.00 Future Trends]])
| Term | Definition |
|---|---|
| PWA 🔴 | A Progressive Web App: built with web technologies but gives an app-like experience (installable, offline, push). It needs a **manifest** + a **service worker**. |
| Service worker | A background script that intercepts network requests, enabling offline caching and push notifications. |
| Blockchain 🔴 | A decentralised, distributed database: records are stored in blocks linked by cryptographic hashes and validated by consensus, which makes them tamper-proof. |
| Smart contract | A self-executing program stored on a blockchain. |
| AR / VR 🔴 | AR overlays digital content on the real world; VR replaces it with a fully virtual world. |
