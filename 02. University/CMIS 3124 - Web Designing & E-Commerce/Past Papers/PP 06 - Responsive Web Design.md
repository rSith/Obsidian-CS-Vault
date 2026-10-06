---
type: past-paper
course: CMIS 3124
lesson: L6
status: complete
tags: [cmis3124, past-paper, cmis3124/L6]
---
# PP 06 · Responsive Web Design: Past-Paper Questions

> [!info] Lesson: [[06.00 Responsive Web Design]] · Summary: [[06.99 Summary - Responsive Web Design]] · [[Past Paper Mapping]]
> All of 2023/24 **Question 02** (20 marks) comes from this lesson.

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2023/24 | 2(a) | Mobile-first design | 2 |
| 2023/24 | 2(b) | Four performance techniques | 2 |
| 2023/24 | 2(c) | Pros and cons of fixed-width layout | 4 |
| 2023/24 | 2(d) | How to make pages responsive | 6 |
| 2023/24 | 2(e)(i)(ii) | Write two media queries | 6 (3 + 3) |

---

## 2023/24 · Q2(a) (2 marks)
> **Question:** What is mobile-first design?

**Lesson:** [[06.03 Mobile Web, One Web and Mobile First]]

### Expected answer
Mobile-first design is a responsive design approach that **starts by designing for the smallest (mobile) screens first** and then **adapts and enhances the layout for larger (wider) viewports**. It is implemented with base styles for mobile and **`min-width` media queries in ascending order** (progressive enhancement). This prioritises essential content and performance on mobile devices.

---

## 2023/24 · Q2(b) (2 marks)
> **Question:** List four (04) techniques to optimize the web performance.

**Lesson:** [[06.07 Web Performance Optimisation]]

### Expected answer
1. **Eliminate unnecessary downloads** / **minify** HTML, CSS and JS (remove comments and white space).
2. **Leverage browser (HTTP) caching** (with ETags).
3. **Reduce HTTP requests**: combine script files and stylesheets; reduce image requests.
4. **Optimise images** and **enable compression** (e.g. gzip).

---

## 2023/24 · Q2(c) (4 marks)
> **Question:** Briefly explain the pros and cons of fixed-width layout.

**Lesson:** [[06.02 Fixed vs Flexible Layouts]]

### Expected answer
A fixed-width layout gives columns a fixed pixel width (e.g. 960px), so the designer controls the layout.

**Pros**
- **Full designer control**: the page looks the same on every browser and screen.
- **Easier to design and code**: image sizes, column widths and line lengths are predictable.

**Cons**
- On **small screens/phones**, users must **scroll horizontally or zoom**, and content may be cut off.
- On **large screens**, it leaves **wasted white space**. It **ignores user choice** and **does not adapt** (not responsive), and text resizing can break the layout.

---

## 2023/24 · Q2(d) (6 marks)
> **Question:** Describe how web pages could be made responsive.

**Lesson:** [[06.04 Making Pages Responsive]] · [[06.05 Media Queries and Breakpoints]]

### Expected answer
Responsive web design (Ethan Marcotte, 2010) makes **one page adapt to any screen size**. It rests on three core components plus the viewport setting:

1. **Set the viewport** so the layout width equals the device width:
   ```html
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   ```
   Without it, mobile browsers render at about 980px and shrink the page.
2. **Flexible (fluid) grid:** size columns with **percentages, CSS Grid `fr`/`minmax()` or Flexbox** instead of fixed pixels, so columns resize proportionally.
   ```css
   .layout { display: grid; grid-template-columns: 2fr 1fr; }
   ```
3. **Flexible images/media:** images scale inside their containers.
   ```css
   img { max-width: 100%; height: auto; }
   ```
4. **CSS media queries and breakpoints** apply different styles when conditions are met, e.g. change one column to three columns on wider screens:
   ```css
   @media screen and (min-width: 768px) { .grid { grid-template-columns: repeat(3, 1fr); } }
   ```
5. **Design approach:** design **mobile first**, keep content parity, keep line length at 45–75 characters, and **test** with real devices, emulators and browser developer tools.

### How to approach this question
Name the **three components + viewport**, give a code line for each, and add one point on approach or testing.

---

## 2023/24 · Q2(e) (6 marks)
> **Question:** Write media queries to perform the following:
> i. Set the background color to LightGreen when the screen is in portrait orientation and the width is less than 800px
> ii. Set font-size and color to 80px and DarkBlue for devices with a minimum width of 768px

**Lesson:** [[06.05 Media Queries and Breakpoints]]

### Expected answer
**(i)**
```css
@media screen and (orientation: portrait) and (max-width: 800px) {
  body {
    background-color: LightGreen;
  }
}
```
*Note:* the lecture writes "less than 640px" as `max-width: 640px`, so this follows the slide convention. For strictly less than 800px, use `(max-width: 799px)` or `(width < 800px)`.

**(ii)**
```css
@media screen and (min-width: 768px) {
  body {
    font-size: 80px;
    color: DarkBlue;
  }
}
```

### Key points to include
- `@media` + media type + **features in parentheses** joined with **`and`**
- A selector and **complete braces**; correct property names; both declarations in (ii)
