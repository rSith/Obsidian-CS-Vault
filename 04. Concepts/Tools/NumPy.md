---
type: concept
status: stub
tags: [python-tools]
---
# NumPy
> [!note] Definition
> Python library for fast numerical operations on arrays and matrices.

## How it works
- The core object is the **ndarray**: a grid of numbers of one type, with a `.shape` (e.g. `(m,)` for a 1-D array of $m$ values).
- Maths on arrays happens **element-wise** in fast C code, so `w * x + b` computes every prediction at once (**vectorization**).
- Used in every ML Specialization lab for data, models and gradients.

## Example
> [!quote] Example
> ```python
> import numpy as np
> x = np.array([1.0, 2.0])      # shape (2,)
> m = x.shape[0]                # 2 examples
> f_wb = 200 * x + 100          # array([300., 500.])
> ```
> Course notes: [[C1W1-L02 Lab - Model Representation]]

## Related
- [[Exploratory Data Analysis]]
