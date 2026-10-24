---
type: revision
course: ML Specialization
status: draft
tags: [ml, ml-spec, revision, equations]
aliases: [ML Equation Sheet, ML formulas]
---
# ML Specialization — Equation Sheet
> [!info] ↑ [[ML Specialization - Course Home]] · See also [[ML Specialization - Algorithm Comparison]] · [[ML Specialization - Glossary]]
> Filled in week by week. Each block links to the lesson that explains it.

## Notation
| Symbol | Meaning |
|---|---|
| $x^{(i)}, y^{(i)}$ | features and target of the $i$-th training example |
| $m$ | number of training examples |
| $n$ | number of features *(from C1W2)* |
| $\hat{y}$ | prediction |
| $w, b$ | parameters (weight, bias) |
| $\alpha$ | learning rate |
| $J$ | cost function |

## Course 1 — Week 1: linear regression (one feature)
**Model** → [[C1W1-03 Linear Regression Model]]
$$f_{w,b}(x) = wx + b$$

**Squared error cost** → [[C1W1-04 Cost Function]]
$$J(w,b) = \frac{1}{2m}\sum_{i=1}^{m}\left(f_{w,b}(x^{(i)}) - y^{(i)}\right)^2$$

**Gradient descent** (repeat until convergence; update simultaneously) → [[C1W1-05 Gradient Descent]]
$$w \leftarrow w - \alpha\frac{\partial J}{\partial w} \qquad b \leftarrow b - \alpha\frac{\partial J}{\partial b}$$

**Gradients for linear regression**
$$\frac{\partial J}{\partial w} = \frac{1}{m}\sum_{i=1}^{m}\left(f_{w,b}(x^{(i)}) - y^{(i)}\right)x^{(i)} \qquad \frac{\partial J}{\partial b} = \frac{1}{m}\sum_{i=1}^{m}\left(f_{w,b}(x^{(i)}) - y^{(i)}\right)$$

## Course 1 — Week 2: multiple linear regression
*Next batch.*

## Course 1 — Week 3: logistic regression and regularization
*Planned.*

## Course 2 — neural networks, trees
*Planned.*

## Course 3 — clustering, anomaly detection, recommenders, RL
*Planned.*
