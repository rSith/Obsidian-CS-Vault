---
type: past-paper
course: CMIS 3124
lesson: L8
status: complete
tags: [cmis3124, past-paper, cmis3124/L8]
---
# PP 08 · Bootstrap: Past-Paper Questions

> [!info] Lesson: [[08.00 Bootstrap]] · Summary: [[08.99 Summary - Bootstrap]] · [[Past Paper Mapping]]

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2023/24 | 4(e)(i)(ii) | Two Bootstrap code fragments | 6 |

---

## 2023/24 · Q4(e) (6 marks)
> **Question:** Write Bootstrap code fragments to create the following:
> i. A fixed-width container with a border, padding of 3 units on all sides and background set to bg-light contextual class
> ii. A Bootstrap grid layout with three equal-width columns starting at screen width equal to or greater than 768px (medium devices)

**Lesson:** [[08.02 Bootstrap Containers]] · [[08.03 Spacing, Background, Border and Text Utilities]] · [[08.04 Bootstrap Grid System]]

### Expected answer
**(i)**
```html
<div class="container border p-3 bg-light">
  <h1>My Bootstrap page</h1>
  <p>Content inside a fixed-width, bordered container.</p>
</div>
```
- `container` → **fixed-width** (responsive max-width), *not* `container-fluid`
- `border` → border on all four sides
- `p-3` → **p**adding, blank side = **all sides**, size **3**
- `bg-light` → the light contextual background class

**(ii)**
```html
<div class="container">
  <div class="row">
    <div class="col-md-4">Column 1</div>
    <div class="col-md-4">Column 2</div>
    <div class="col-md-4">Column 3</div>
  </div>
</div>
```
- `.row` wraps the columns
- `md` = medium devices = **≥ 768px**
- `4 + 4 + 4 = 12` → three **equal** columns; below 768px they stack vertically

### How to approach this question
Every required class earns marks. **Missing one class costs marks**, so check each requirement in the sentence against a class.
