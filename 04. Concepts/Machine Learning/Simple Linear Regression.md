---
type: concept
status: draft
tags: [ml]
---
# Simple Linear Regression
> [!note] Definition
> Predicts the dependent variable $y$ from a **single** input feature $x$ with a straight line.
> First model in the [[ML Specialization - Course Home|ML Specialization]] (Course 1, Week 1).

## Model
$$f_{w,b}(x) = wx + b$$
- $w$: weight (slope), $b$: bias (intercept)
- *Example from the course:* predicting house price from size (square feet).

## Cost function (squared error)
$$J(w,b) = \frac{1}{2m}\sum_{i=1}^{m}\left(f_{w,b}(x^{(i)}) - y^{(i)}\right)^2$$
- Measures how far the predictions are from the real values.
- The cost surface is **bowl-shaped (convex)**, which is why gradient descent can find the minimum.

## Gradient descent
- Repeatedly nudges $w$ and $b$ in the direction that lowers $J(w,b)$ until it converges on the best-fit line.
- Update rules (simultaneous):
$$w \leftarrow w - \alpha\frac{1}{m}\sum_{i=1}^{m}\left(f_{w,b}(x^{(i)}) - y^{(i)}\right)x^{(i)} \qquad b \leftarrow b - \alpha\frac{1}{m}\sum_{i=1}^{m}\left(f_{w,b}(x^{(i)}) - y^{(i)}\right)$$
- Learning rate $\alpha$: too small → slow; too large → overshoots and diverges. → [[Gradient Descent]]
- Course notes: [[C1W1-03 Linear Regression Model]] · [[C1W1-04 Cost Function]] · [[C1W1-05 Gradient Descent]]

## My code
- Labs: https://github.com/rSith/machine-learning-specialization
- Lab notes: [[C1W1-L02 Lab - Model Representation]] · [[C1W1-L03 Lab - Cost Function]] · [[C1W1-L04 Lab - Gradient Descent]]

## Related
- [[Linear Regression]] · [[Multiple Linear Regression]] · [[Cost Function]] · [[Gradient Descent]] · [[ML Specialization - Week 01 Post]]
