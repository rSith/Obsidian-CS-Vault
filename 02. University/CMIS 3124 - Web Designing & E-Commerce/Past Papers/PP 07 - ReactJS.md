---
type: past-paper
course: CMIS 3124
lesson: L7
status: complete
tags: [cmis3124, past-paper, cmis3124/L7]
---
# PP 07 · ReactJS: Past-Paper Questions

> [!info] Lesson: [[07.00 ReactJS]] · Summary: [[07.99 Summary - ReactJS]] · [[Past Paper Mapping]]

> [!warning] No past-paper questions
> ReactJS has **not appeared in any of the four available papers** (2017/18, 2018/19, 2019/20, 2023/24). It is taught in the current syllabus, so it is a **"watch" topic**. It could be examined as code, the way Bootstrap and media queries were in 2023/24.

## Practice questions (NOT from past papers)
These are **self-made practice questions** in the 2023/24 style, from the study guide. They are not real exam questions.

### P1. What is the virtual DOM, and why does it make React applications efficient?
- A **lightweight copy of the DOM kept in memory** by React.
- When state or props change, React renders to the virtual DOM first and **compares (diffs)** it with the previous version.
- **Only the nodes that changed** are updated in the real browser DOM ("React only changes what needs to be changed").
- Real DOM updates are expensive, so fewer updates mean faster UIs.

### P2. Name the three phases of a React class component's lifecycle and the methods called in each.
- **Mounting:** `constructor()` → `getDerivedStateFromProps()` → `render()` → `componentDidMount()`
- **Updating:** `getDerivedStateFromProps()` → `shouldComponentUpdate()` → `render()` → `getSnapshotBeforeUpdate()` → `componentDidUpdate()`
- **Unmounting:** `componentWillUnmount()`
- Add a one-line purpose for each (e.g. `shouldComponentUpdate()` returning `false` stops the re-render). See [[07.04 Component Lifecycle]].

### P3. Write a function component `Counter` that shows a number starting at 0 and a button that increases it by 1.
```jsx
import { useState } from "react";

function Counter() {
  const [count, setCount] = useState(0);
  return (
    <>
      <p>Count: {count}</p>
      <button onClick={() => setCount(count + 1)}>Add 1</button>
    </>
  );
}
```
Marks: `useState(0)` at the top level; value shown in JSX; setter in an `onClick` arrow function; component name starts with a capital letter.

### P4. Distinguish props from state.
See the table in [[07.03 State]].
