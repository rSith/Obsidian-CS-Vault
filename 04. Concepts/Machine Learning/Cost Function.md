---
type: concept
status: draft
tags: [ml]
aliases: [Loss Function, Objective Function, J(w,b)]
---
# Cost Function
> [!note] Definition
> A function of the model's **parameters** that returns **one number** measuring how badly the model fits the training data. Training = finding the parameters that **minimise** it.

## How it works
1. Make a prediction $\hat{y}^{(i)}$ for every training example.
2. Measure the error for each example (the **loss**).
3. Average the losses over all $m$ examples. That average is the **cost** $J$.

**Loss vs cost:** *loss* = error on **one** example; *cost* = average loss over the **whole** training set.

| Model | Cost used | Formula | Course note |
|---|---|---|---|
| Linear regression | squared error | $J = \frac{1}{2m}\sum(f(x^{(i)}) - y^{(i)})^2$ | [[C1W1-04 Cost Function]] |
| Logistic regression | log loss (cross-entropy) | *C1W3 (planned)* | — |

For squared error with linear regression, $J$ is **convex** (one bowl-shaped minimum), so [[Gradient Descent]] always finds the best parameters.

## Example
> [!quote] Example
> Data $(1, 300), (2, 500)$, model $f(x) = wx + b$:
> - $w = 200, b = 100$ → predictions 300, 500 → $J = 0$ (perfect)
> - $w = 100, b = 100$ → predictions 200, 300 → $J = \frac{1}{4}(100^2 + 200^2) = 12\,500$

## Related
- [[Gradient Descent]] · [[Simple Linear Regression]] · [[Linear Regression]] · [[Model Evaluation]]
- Course notes: [[C1W1-04 Cost Function]] · [[C1W1-L03 Lab - Cost Function]]
