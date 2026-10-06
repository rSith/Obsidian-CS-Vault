---
type: revision
course: CMIS 3124
status: complete
tags: [cmis3124, revision, cram-sheet]
aliases: [Cram sheet, Final revision, Night-before summary]
---
# Final Revision Summary (Cram Sheet)

> [!info] One page per lesson, compressed to exam-answer keywords. For depth follow the links. Home: [[00. Course Overview]] · [[High Priority Topics]] · [[Important Definitions]] · [[Important Comparisons]]

> [!tip] Exam format
> **8 questions, answer 6, 3 hours.** That is about **30 minutes per question**, so roughly **1.5 minutes per mark**. Match the length of each answer to its marks: 2 marks = 2 points, 6 marks = 3 points, each explained with an example.

## 🔴 Lesson 06: Responsive Web Design ([[06.99 Summary - Responsive Web Design|summary]])
- **RWD** = flexible grid (%, `fr`) + flexible images (`max-width:100%`) + media queries + the viewport meta tag.
- **Mobile first** = design small first, then `min-width` queries going up (progressive enhancement).
- **Fixed width:** ✔ predictable, designer control, simple · ✘ horizontal scrolling on phones, wasted space on wide screens, not accessible when zoomed.
- **Performance:** minify, compress (gzip), optimise images, cache (ETags), reduce HTTP requests, remove unnecessary downloads.
```css
@media screen and (orientation: portrait) and (max-width: 800px) { body { background-color: LightGreen; } }
@media screen and (min-width: 768px) { body { font-size: 80px; color: DarkBlue; } }
```

## 🔴 Lesson 13: Web Security ([[13.99 Summary - Web Security and Cyber Threats|summary]])
- **SQLi** → malicious SQL through inputs (`' OR 1=1 --`) → **prepared statements**, validation, least privilege, generic errors.
- **XSS** → script runs in the victim's browser; reflected / stored / DOM-based → **sanitise input, encode output, CSP, HttpOnly cookies**.
- **Code injection** → the app interprets injected code (`eval($_GET[...])`) → no `eval`, whitelist validation.
- **DoS/DDoS** → flood or crash (ping of death, smurf) → firewalls, IDS/IPS, rate/bandwidth limits, CDN, response plan.
- **Zero Trust** = never trust, always verify · continuous validation · least privilege · assume breach · pillars: identity, devices, network (micro-segmentation), apps, data.
- **DevSecOps** = security in every CI/CD stage; shift left; shared responsibility; SAST / DAST / SCA / IAST.
- **AI/ML** = detection (anomaly, malware, phishing) · response (automated, threat intelligence) · prevention (predictive, behavioural) · challenges (data, bias, adversarial attacks, explainability).

## 🔴 Lesson 10: E-Commerce ([[10.99 Summary - E-Commerce|summary]])
- **Definition:** *the use of electronic communications and digital information-processing technology in business transactions to create, transform and redefine relationships for value creation…*
- **Authentication:** why = security, fraud prevention, trust, non-repudiation · methods = password, 2FA/MFA, OTP, biometrics, digital certificates.
- **Types:** B2B · B2C · C2C · C2B · G2C · G2B · G2G (ikman.lk = C2C, tax = G2C, logo = C2B, raw materials = B2B, Netflix = B2C).
- **Benefits:** organisations = global reach, lower cost, 24×7, data/feedback · consumers = convenience, choice, price comparison, reviews.
- **Old scenarios:** argue both sides, then give a recommendation (security, trust, delivery, returns, payment method).

## 🔴 Lesson 09: Accessibility ([[09.99 Summary - Web Accessibility|summary]])
- **Accessible content:** alt text · captions/transcripts · contrast · keyboard navigation · simple navigation · labelled forms · accessible PDFs · test with a screen reader.
- **"Only use ARIA when necessary":** native HTML already carries semantics and keyboard support; wrong ARIA is worse than none → 5 rules (native first, don't change semantics, keyboard-operable, don't hide focusable items, accessible name).
- **Tab widget:** roles `tablist/tab/tabpanel` → properties `aria-controls`, `aria-labelledby`, `tabindex` → states `aria-selected`, `hidden`.

## 🔴 Lesson 02: Web Architecture ([[02.99 Summary - Web Architecture and Development|summary]])
- **3-tier:** presentation (UI) / application (logic) / data (DB). Apply it to the scenario: name the actual screens, rules and tables.
- **Components:** load balancer (spreads requests) · app server (runs the logic) · cache (fast repeat reads) · CDN (static files near the user).
- **PHP:** browser request → server runs PHP → reads `$_POST/$_GET`, queries the DB → sends **plain HTML** back.
- **CMS** = create/manage content without code; CMA + CDA; features: WYSIWYG, roles, templates, plugins, SEO.
- **GET vs POST:** URL vs body · visible vs hidden · cached vs not · retrieve vs change.

## 🔴 Lesson 05 + 04: HCI and Design ([[05.99 Summary - HCI in Web Design|HCI]] · [[04.99 Summary - Web Design Principles|Design]])
- **UX** = experience/journey · **UI** = visual interface.
- **Cognitive load:** intrinsic (simplify) / extraneous (reduce) / germane (maximise); 4 CLT principles → chunking, progressive disclosure, consistency, familiar patterns.
- **Usability** = ease of use; testing: in-person, moderated/unmoderated remote, guerrilla, card sorting, session recording (give a pro and a con for each).
- **CARP:** Contrast · Alignment · Repetition · Proximity (each with an example).

## 🔴 Lesson 08: Bootstrap ([[08.99 Summary - Bootstrap|summary]])
```html
<div class="container border p-3 bg-light">…</div>
<div class="container"><div class="row">
  <div class="col-md-4">1</div><div class="col-md-4">2</div><div class="col-md-4">3</div>
</div></div>
```
- 12 columns · breakpoints sm 576 / md 768 / lg 992 / xl 1200 / xxl 1400.

## 🔴 Lesson 12: Ethics ([[12.99 Summary - Web Design Ethics and Policies|summary]])
- **Practices (PTASS):** privacy · transparency and consent · accessibility · sustainability · security → users trust more; businesses gain reputation, compliance and loyalty.
- **Dark patterns:** hidden costs · bait and switch · forced continuity · roach motel · misdirection · confirm-shaming · sneak into basket · disguised ads · trick questions · FOMO.

## 🔴 Lesson 01: Evolution of the Web ([[01.99 Summary - Evolution of the Web|summary]])
- 1.0 read (static) → 2.0 read-write (social, UGC) → 2.5 bridge → 3.0 own (decentralised, blockchain) → 4.0 symbiotic (AI + IoT).
- **Web 2.0 tools:** blog (comments) · wiki (collaborative editing) · mashup (combine sources) · RSS (subscribe to updates) · social bookmarking (save, tag, share links).

## 🟡 Lessons 11, 14, 15, 03
- **SEO** = higher organic ranking; on-page (title, meta, URL, headings, alt, keywords) vs off-page (backlinks, social, sitemap, robots.txt). **Influencers:** mega/macro/micro/nano; trust + urgency; sponsored posts, affiliate codes, reviews, live streams. ([[11.99 Summary - Social Media and Web Influence|summary]])
- **AI:** benefits = automation (Copilot), personalisation, chatbots, testing · limitations = bias, limited decision-making, model size, privacy. ([[14.99 Summary - AI in Web Development|summary]])
- **PWA** = web app + manifest + service worker → installable, offline, push · native = app store, per OS. **Blockchain:** blocks + timestamp → hash links → consensus (PoW/PoS) → immutable → benefits: trust, transparency, security, no middleman. **AR/VR:** try-before-you-buy, 3D tours, immersive storytelling, engagement. ([[15.99 Summary - Future Trends|summary]])
- **WebE is agile/incremental** because requirements change, time is short, budgets are small, and the user base is large → deliver in increments and adapt to feedback. ([[03.99 Summary - Web Engineering|summary]])

## 🟢 Lessons 07 and 00
- **React:** components (uppercase names) · props (read-only) vs state (`setState`/`useState`) · virtual DOM · lifecycle mount/update/unmount · hooks at the top level only. ([[07.99 Summary - ReactJS|summary]])
- **HTML/CSS:** semantic elements, forms (`action`, `method`, `label for`), selectors, cascade. ([[00.99 Summary - HTML and CSS Foundations|summary]])

## Last-minute checklist
- [ ] Can I write two media queries from a description without mistakes?
- [ ] Can I draw and explain a 3-tier diagram for any scenario?
- [ ] Can I write the four security short notes (definition + example + prevention)?
- [ ] Can I write the Bootstrap container and grid code?
- [ ] Can I write the ARIA tab-widget markup?
- [ ] Can I classify e-commerce scenarios (B2B/B2C/C2C/C2B/G2C)?
- [ ] Can I name and explain three dark patterns with examples?
- [ ] Can I explain CARP, UI vs UX, cognitive load and two usability tests?
