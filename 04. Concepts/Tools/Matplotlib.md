---
type: concept
status: stub
tags: [python-tools]
---
# Matplotlib
> [!note] Definition
> Python's core plotting library: line plots, histograms, scatter plots and more.

## How it works
- `import matplotlib.pyplot as plt`, then build a figure: `plt.scatter` (points), `plt.plot` (lines), labels with `plt.title / xlabel / ylabel`, then `plt.show()`.
- `%matplotlib widget` in Jupyter makes plots interactive (sliders, clicking), as used in the cost-function lab.

## Example
> [!quote] Example
> ```python
> plt.scatter(x_train, y_train, marker='x', c='r')   # data as red crosses
> plt.plot(x_train, f_wb, c='b')                     # model line
> plt.xlabel("Size (1000 sqft)"); plt.ylabel("Price ($1000s)"); plt.show()
> ```
> Course notes: [[C1W1-L02 Lab - Model Representation]]

## Related
- [[Exploratory Data Analysis]]
