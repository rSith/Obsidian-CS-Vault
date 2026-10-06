---
type: past-paper
course: CMIS 3124
lesson: L1P
status: complete
tags: [cmis3124, past-paper, cmis3124/practicals]
---
# PP 00 · HTML and CSS Foundations: Past-Paper Questions

> [!info] Section: [[00.00 HTML and CSS Foundations]] · Summary: [[00.99 Summary - HTML and CSS Foundations]] · [[Past Paper Mapping]]

> [!warning] No direct past-paper questions
> No paper has asked about the practical HTML/CSS content directly. The one related question is **2023/24 Q7(c), "Compare and contrast PHP's GET and POST methods"**, which is answered in [[PP 02 - Web Architecture and Development]] because it comes from the HTTP section of Lesson 2. Practical 2 explains the same difference.

## Practice questions (NOT from past papers)
These **self-made practice questions** come from the study guide.

### P1. Write an HTML form that posts a user's email and password to `login.php`, with labels, required validation and a submit button.
```html
<form action="login.php" method="post">
  <label for="email">Email:</label>
  <input type="email" id="email" name="email" required>
  <label for="pw">Password:</label>
  <input type="password" id="pw" name="password" minlength="6" required>
  <button type="submit">Log in</button>
</form>
```
Marks: `action` + `method="post"` (a password is sent) · each `<label for>` matches an `id` · correct `type` + `name` + `required` · a submit button.

### P2. Explain the difference between a pseudo-class and a pseudo-element, with one example of each.
- **Pseudo-class** (`:`) selects an element in a **state or position**: `a:hover`, `input:focus`, `tr:nth-child(even)`.
- **Pseudo-element** (`::`) styles **part of** an element or inserts generated content: `p::first-line`, `a::after { content: "→"; }`, `::placeholder`.

### P3. Using `<p class="intro">Welcome to Web Designing!</p>`, identify the element, opening tag, closing tag, attribute and content. Why is HTML not a programming language?
- Element: the whole `<p …>…</p>`; opening tag `<p class="intro">`; closing tag `</p>`; attribute `class="intro"`; content "Welcome to Web Designing!".
- HTML is a **markup language**: its tags label structure, and it **cannot perform logic, loops or maths**.
