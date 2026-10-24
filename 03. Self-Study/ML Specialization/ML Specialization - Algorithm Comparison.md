---
type: revision
course: ML Specialization
status: draft
tags: [ml, ml-spec, revision, comparison]
aliases: [ML Algorithm Comparison]
---
# ML Specialization — Algorithm Comparison
> [!info] ↑ [[ML Specialization - Course Home]] · See also [[ML Specialization - Equation Sheet]] · [[ML Specialization - Glossary]]
> One row per algorithm, added as the course introduces it.

## Which kind of problem is it?
```mermaid
flowchart TD
    Q{"Do you have labels y?"} -->|yes| S{"Is y a number?"}
    Q -->|no| U{"What do you want?"}
    S -->|yes| R["Regression<br/>linear regression (C1W1–2)<br/>NN / trees (C2)"]
    S -->|no, a category| C["Classification<br/>logistic regression (C1W3)<br/>NN, softmax, trees (C2)"]
    U -->|groups| K["Clustering<br/>K-means (C3W1)"]
    U -->|unusual points| A["Anomaly detection (C3W1)"]
    U -->|fewer features| P["PCA (C3W2)"]
    Q -->|"no labels, but rewards"| RL["Reinforcement learning (C3W3)"]
```

## Algorithms so far
| Algorithm | Type | Output | Model | Cost / objective | Trained with | Introduced |
|---|---|---|---|---|---|---|
| Linear regression (1 feature) | supervised, regression | a number | $wx + b$ | squared error $J$ (convex) | gradient descent | [[C1W1-03 Linear Regression Model\|C1W1]] |

## Optimisation methods
| Method | Idea | Pros | Cons | Introduced |
|---|---|---|---|---|
| Batch gradient descent | step against the gradient using **all** $m$ examples | simple, general, works for any differentiable cost | needs a good $\alpha$; can be slow on huge datasets | [[C1W1-05 Gradient Descent\|C1W1]] |

*More rows in later batches: multiple linear regression, normal equation, logistic regression, neural networks, Adam, decision trees, random forest, XGBoost, K-means, anomaly detection, collaborative and content-based filtering, PCA, DQN.*
