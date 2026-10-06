---
type: revision
course: CMIS 3124
status: complete
tags: [cmis3124, revision, comparisons]
aliases: [Comparisons, X vs Y tables]
---
# Important Comparisons

> [!info] All the "X vs Y" tables in one place. 🔴 = a past paper asked for this comparison. Home: [[00. Course Overview]] · [[Important Definitions]]

## Web eras 🔴 ([[01.00 Evolution of the Web]])
| | Web 1.0 | Web 2.0 | Web 3.0 | Web 4.0 |
|---|---|---|---|---|
| Nickname | Static / read-only | Participatory / social | Decentralised / semantic | Symbiotic / intelligent |
| User role | Read | Read + write | Read + write + own | Interact with intelligent systems |
| Technology | Static HTML | Web apps, APIs, AJAX, cloud | Blockchain, P2P, smart contracts | AI + ML + IoT |
| Example | AltaVista, portals | Gmail, YouTube, blogs | DeFi, NFTs | Smart cities, self-driving cars |

## Blog vs wiki (Wikipedia) 🔴 ([[01.04 Blogs]] · [[01.05 Wikis]])
| Blog | Wiki |
|---|---|
| One author or a small team | Anyone, collaboratively |
| Readers **comment** | Users **edit** the content |
| Opinion; dated posts in reverse order | Neutral topic articles; version history |

## GET vs POST 🔴 ([[02.04 HTTP Protocol]])
| GET | POST |
|---|---|
| Data in the URL query string | Data in the request body |
| Visible, bookmarkable, cached | Not visible in the URL, not cached |
| Length-limited | No practical limit; file uploads |
| Retrieve / search | Login, payment, changing data |

## Session vs cookie ([[02.05 Sessions and Cookies]])
| Session | Cookie |
|---|---|
| Stored on the server | Stored in the browser |
| Ends at logout or timeout | Ends at browser close or expiry date |
| More secure | Can be read or changed by the client |

## Two-tier (client–server) vs three-tier 🔴 ([[02.06 Client-Server and Three-Tier Architecture]])
| Client–server | Three-tier |
|---|---|
| Client talks to the server/DB directly | Presentation → application logic → data |
| Simple, but a centralised point of failure | Each tier scales and is secured independently |
| Logic mixed with the UI or DB | Logic isolated in the middle tier |

## Software engineering vs web engineering ([[03.01 Web Engineering vs Software Engineering]])
| Software engineering | Web engineering |
|---|---|
| Small user range; specific requirements | Large user range; requirements change over time |
| Longer development time, varied budgets | Short time, small budgets |
| Security/legal less important | Security/legal very important |
| Less UI emphasis | More UI emphasis |

## UX vs UI 🔴 ([[05.01 UI vs UX]])
| UX | UI |
|---|---|
| Feel and experience; emotions, satisfaction | Visual look; aesthetics |
| Wireframes, prototypes, user flows | Mockups, graphics, final visuals |
| The whole journey | Individual screens and elements |
| Research-led; solves pain points | Implementation; pleasing designs |

## Usability testing methods 🔴 ([[05.10 Usability Testing Methods]])
| Method | Advantage | Disadvantage |
|---|---|---|
| In-person (moderated) | Observe body language; ask follow-ups | Costly; slow; few participants |
| Moderated remote | Real-time questions; no travel | Depends on technology; less body-language detail |
| Unmoderated remote | Cheap, fast, many users, natural setting | No follow-up questions |
| Guerrilla | Very cheap and quick | Participants may not be target users |
| Card sorting | Reveals users' mental model of the navigation | Doesn't test the real interface |
| Session recording | Real behaviour at scale | Shows *what*, not *why*; privacy concerns |

## Fixed vs fluid vs elastic layouts 🔴 ([[06.02 Fixed vs Flexible Layouts]])
| Fixed | Fluid | Elastic |
|---|---|---|
| px | % | em |
| Designer control; predictable | Adapts to the window | Scales with font size |
| Horizontal scrolling on small screens, wasted space on large ones | Very long lines on wide screens | Can overflow if text is enlarged |

## Graceful degradation vs progressive enhancement 🔴 ([[06.05 Media Queries and Breakpoints]])
| Graceful degradation (desktop first) | Progressive enhancement (mobile first) |
|---|---|
| Design large first, then strip down | Design small first, then add |
| `max-width` queries, descending | `min-width` queries, ascending |

## Props vs state; class vs function components ([[07.02 Components and Props]] · [[07.03 State]])
| Props | State |
|---|---|
| Passed from the parent, read-only | Owned by the component, changeable via `setState`/`useState` |

| Class component | Function component |
|---|---|
| `extends React.Component` + `render()` | A function that returns JSX |
| `this.state`, lifecycle methods | `useState`, `useEffect` |

## `.container` vs `.container-fluid` 🔴 ([[08.02 Bootstrap Containers]])
| `.container` | `.container-fluid` |
|---|---|
| Fixed max-width at each breakpoint, centred | Always 100% wide |

## ARIA comparisons 🔴 ([[09.05 ARIA Roles, Properties and States]] · [[09.06 When to Use ARIA]])
| Role | Property | State |
|---|---|---|
| What the element **is** | Extra semantics, rarely change | Current condition, changes often |
| `role="tab"` | `aria-controls`, `aria-labelledby` | `aria-selected`, `aria-expanded` |

| `aria-label` | `aria-labelledby` |
|---|---|
| Label text in the attribute | Points to the id of visible text |

| `tabindex="0"` | `tabindex="-1"` |
|---|---|
| Joins the natural tab order | Out of the tab order; focusable only with `focus()` |

| `aria-live="polite"` | `aria-live="assertive"` |
|---|---|
| Announced when the user is idle | Announced immediately |

## Traditional commerce vs e-commerce 🔴 ([[10.02 Traditional Commerce vs E-Commerce]])
| Traditional | E-commerce |
|---|---|
| Physical shop; limited hours | Online; 24×7 |
| Inspect goods before buying | No physical inspection |
| Local reach | Global reach, easy expansion |
| Face-to-face trust | Screen-to-face; risk of cyber fraud |
| Higher overheads | Cost-effective, automated |

## E-commerce vs e-business ([[10.03 E-Business and Architectural Framework]])
| E-commerce | E-business |
|---|---|
| Buying and selling online | All electronic business processes |
| Involves a money transaction | No money transaction needed |

## E-commerce types 🔴 ([[10.04 Types of E-Commerce and e-Government]])
| Type | Example from the 2023/24 paper |
|---|---|
| C2C | Selling a used car on ikman.lk |
| G2C | Filing taxes online |
| C2B | A freelancer selling a logo to a company |
| B2B | A raw-materials supplier portal |
| B2C | Netflix |

## 2FA vs MFA 🔴 ([[10.07 Authentication in E-Commerce]])
| 2FA | MFA |
|---|---|
| Exactly two factors | Two or more factors (know / have / are) |

## Collaborative vs content-based filtering ([[10.08 Recommendation Engines]])
| Collaborative | Content-based |
|---|---|
| Uses similar users' behaviour | Uses similar item attributes |
| Needs lots of user data (cold-start problem) | Works for new items that have descriptions |

## On-page vs off-page SEO 🔴 ([[11.06 On-Page SEO]] · [[11.07 Off-Page SEO]])
| On-page | Off-page |
|---|---|
| Titles, meta descriptions, URLs, headings, alt text, keywords | Backlinks/PageRank, social signals, sitemaps, robots.txt |
| Under the site owner's direct control | Depends on other sites and users |

## Anonymisation vs pseudonymisation ([[12.04 Tools for Ethical Web Development]])
| Anonymisation | Pseudonymisation |
|---|---|
| Identifiers removed permanently | Identifiers replaced; re-linkable with a key |

## SQLi vs XSS vs code injection 🔴 ([[13.03 SQL Injection]] · [[13.07 Cross-Site Scripting (XSS)]] · [[13.08 Code Injection]])
| | SQL injection | XSS | Code injection |
|---|---|---|---|
| What is injected | SQL | Script (JavaScript) | Application code (e.g. PHP) |
| Where it runs | Database | The victim's browser | The application server |
| Main defence | Prepared statements | Output encoding, CSP | No `eval()`; input validation |

## Virus vs worm ([[13.05 Viruses and Worms]])
| Virus | Worm |
|---|---|
| Needs a host file and user action | Self-replicates; no user action needed |
| Modifies programs | Spreads across networks (scans IPs/ports) |

## DevOps vs DevSecOps 🔴 ([[13.13 DevSecOps]])
| DevOps | DevSecOps |
|---|---|
| Dev + Ops; speed | Dev + Sec + Ops; security in every stage |
| Security checked at the end | Shift left and right |

## Perimeter security vs Zero Trust 🔴 ([[13.12 Zero Trust Security]])
| Perimeter ("castle and moat") | Zero Trust |
|---|---|
| Trust everything inside the network | Never trust, always verify |
| One-time login | Continuous validation |
| Broad internal access | Least privilege, micro-segmentation |

## Website vs PWA vs native app 🔴 ([[15.02 Platform-Specific Apps vs Websites]] · [[15.03 Progressive Web Apps]])
| Website | PWA | Native app |
|---|---|---|
| URL; no install; online only | URL + install; offline; push notifications | From an app store; built per OS |
| One codebase | One codebase | A codebase per platform |
| Limited device access | Growing device access | Full OS integration |

## AR vs VR 🔴 ([[15.05 AR and VR for the Web]])
| AR | VR |
|---|---|
| Adds digital content to the real world | Replaces the real world with a virtual one |
| Phone camera, glasses | A headset |
| Furniture placement, virtual try-on | 3D property tours, virtual tourism |

## Secret-key vs public-key cryptography (old syllabus; [[Old Syllabus Questions]])
| Secret-key (symmetric) | Public-key (asymmetric) |
|---|---|
| One shared key | A key pair (public + private) |
| Fast; key distribution is hard | Slower; enables digital signatures |
