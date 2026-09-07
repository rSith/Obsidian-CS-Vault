>[!note]
>- Simple model for predicting continuous values.
>- It finds the relationship between input features and output.
>- Used in forecasting and predicting salaries based on expeirence.

How it works :
- Finds the best-fitting line by minimizing prediction errors(least squares method).
- It calculates coefficients for variables that minimize the error in predictions.
---
>[!important] Standard Notations:
>-  $x$ = "Input" variable : feature.
>	- *for predicting price of a house, x is the size of the house*
>- $y$ = "Output" variable : target
>	- *y is the price of the house*
>- $m$ = Total number of training examples.
>- $(x,y)$= single training example.
>- $(x^i,y^i)$ = $i$ th training example.
  


## Types of Linear Regression
1. [[Simple Linear Regression]] : Predicts the dependent variable using a single independent variable
2. [[Multiple Linear Regression]] : Uses two or more independent variables to predict the dependent variable.

