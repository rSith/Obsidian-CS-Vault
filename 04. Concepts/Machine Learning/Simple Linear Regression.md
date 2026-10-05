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
- *Write the update rules and the role of the learning rate α here.*

## My code
- Labs: https://github.com/rSith/machine-learning-specialization

## Related
- [[Linear Regression]] · [[Multiple Linear Regression]] · [[ML Specialization - Week 01 Post]]
