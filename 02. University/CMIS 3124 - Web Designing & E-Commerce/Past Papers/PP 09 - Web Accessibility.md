---
type: past-paper
course: CMIS 3124
lesson: L9
status: complete
tags: [cmis3124, past-paper, cmis3124/L9]
---
# PP 09 · Web Accessibility: Past-Paper Questions

> [!info] Lesson: [[09.00 Web Accessibility]] · Summary: [[09.99 Summary - Web Accessibility]] · [[Past Paper Mapping]]

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2023/24 | 8(b) | Making web content accessible | 5 |
| 2023/24 | 8(c) | "Only use ARIA when absolutely necessary" | 4 |
| 2023/24 | 8(d) | ARIA roles, properties, states for a tab widget | 6 |

---

## 2023/24 · Q8(b) (5 marks)
> **Question:** Describe how you can make web content accessible to everyone despite their disabilities.

**Lesson:** [[09.03 Applying Accessible Design]] · [[09.02 Components and Accessibility Guidelines]] · [[04.04 Functional and Usable Design]]

### Expected answer
Follow the **W3C WAI's Web Content Accessibility Guidelines (WCAG)**, and make sure content, user agents (including assistive technologies such as screen readers) and authoring tools work together. Practical techniques:
1. **Visual impairments:** provide **alt text** for images; use semantic HTML and headings so **screen readers** (e.g. JAWS) can navigate; keep **high contrast** between text and background; **don't use colour alone** to convey meaning; use real, resizable text; offer **accessible PDFs** instead of image-only flyers.
2. **Hearing impairments:** provide **captions** for video and **transcripts** for audio.
3. **Motor impairments:** make everything **keyboard accessible** (tab through the site) with a visible focus; use ARIA and `tabindex` for custom widgets.
4. **Cognitive impairments:** **simple, consistent navigation**, **minimal, clear content**, and properly **labelled web forms** with helpful error messages.
5. **Test** the site with screen readers, keyboard-only use and accessibility checkers.

---

## 2023/24 · Q8(c) (4 marks)
> **Question:** "Web developers should only use Accessible Rich Internet Applications (ARIA) when absolutely necessary". Comment on this statement.

**Lesson:** [[09.04 Introduction to ARIA and the Five Rules]]

### Expected answer
The statement is correct. ARIA adds roles, properties and states that help assistive technologies, but **ARIA Rule #1 says to use native HTML when possible**: native elements such as `<button>`, `<nav>` and `<label>` already carry the correct semantics **and** built-in keyboard support. ARIA **only changes what screen readers announce; it adds no behaviour**. A `<div role="button">` is not focusable and doesn't respond to Enter unless the developer adds `tabindex` and JavaScript. **Used incorrectly, ARIA introduces significant accessibility barriers**: it can **override native semantics** (Rule #2), and ARIA labels override visible text. ARIA **is necessary** when building something complex that has **no native HTML equivalent**: custom widgets (tabs, sliders, trees), announcing dynamic content updates with `aria-live`, adding landmarks to old code, or giving an accessible name where no visible label exists.

---

## 2023/24 · Q8(d) (6 marks)
> **Question:** Consider the following simple tab pattern containing the tabs a user would click. Corresponding tab panels become visible or invisible depending on which tab is selected. Illustrate how ARIA roles, properties, and states can be used to improve the accessibility and interactivity of the tab widget.
> ```html
> <div class="tabs">
>   <div>
>     <button id="tab-1">Windows</button>
>     <button id="tab-2">macOS</button>
>     <button id="tab-3">Linux</button>
>   </div>
>   <div class="tab-panels">
>     <div id="panel-1"><p>How to run this application on Windows</p></div>
>     <div id="panel-2"><p>How to run this application on macOS</p></div>
>     <div id="panel-3"><p>How to run this application on Linux</p></div>
>   </div>
> </div>
> ```

**Lesson:** [[09.07 Accessible Tab Widget]] · [[09.05 ARIA Roles, Properties and States]]

### Expected answer
**Roles:** `role="tablist"` (with `aria-label`) on the button container, `role="tab"` on each button, `role="tabpanel"` on each panel. These tell the screen reader what the widget is.
**Properties:** `aria-controls` on each tab points to its panel; `aria-labelledby` on each panel points to its tab; `tabindex="0"` on the selected tab and `"-1"` on the others (arrow keys move between tabs); panels get `tabindex="0"`.
**States:** `aria-selected="true"` on the active tab and `"false"` on the others; `hidden` (or `aria-hidden`) on inactive panels. JavaScript updates these on click or arrow keys, so a screen reader announces e.g. "macOS, tab, selected, 2 of 3".

```html
<div class="tabs">
  <div role="tablist" aria-label="Select your operating system">
    <button role="tab" aria-selected="true"  aria-controls="panel-1" id="tab-1" tabindex="0">Windows</button>
    <button role="tab" aria-selected="false" aria-controls="panel-2" id="tab-2" tabindex="-1">macOS</button>
    <button role="tab" aria-selected="false" aria-controls="panel-3" id="tab-3" tabindex="-1">Linux</button>
  </div>
  <div class="tab-panels">
    <div id="panel-1" role="tabpanel" tabindex="0" aria-labelledby="tab-1">
      <p>How to run this application on Windows</p></div>
    <div id="panel-2" role="tabpanel" tabindex="0" aria-labelledby="tab-2" hidden>
      <p>How to run this application on macOS</p></div>
    <div id="panel-3" role="tabpanel" tabindex="0" aria-labelledby="tab-3" hidden>
      <p>How to run this application on Linux</p></div>
  </div>
</div>
```

### How to approach this question
About 2 marks each for roles, properties and states. **Write the full code and briefly explain each attribute.**
