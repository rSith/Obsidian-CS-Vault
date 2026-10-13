---
type: revision
course: CMIS 3142
status: complete
tags: [cmis3142, revision, definitions]
aliases: [CMIS 3142 Definitions]
---
# CMIS 3142 · Important Definitions

> [!info] Back to [[00. CMIS 3142 Course Overview|Course Overview]] · Definitions in *italics with quotes* are close to the lecture wording; learn those word for word. 🔴/🟡/🟢 = exam priority of the topic.

## L01 Floating point numbers ([[01.00 Floating Point Numbers]])
- **Floating-point representation** 🔴: a fixed-size binary form of scientific notation with three parts, **sign, exponent, mantissa**; *more exponent bits → greater range, more mantissa bits → greater accuracy*.
- **IEEE 754** 🔴: the standard defining binary floating-point formats; single = 32 bits (1/8/23), double = 64 bits (1/11/52).
- **Normalised number** 🔴: $E$ not all 0s or all 1s; value $(-1)^S\times1.F\times2^{E-\text{bias}}$.
- **Hidden 1** 🟡: *"Normalized number: always a 1 left of binary point … do not waste a bit on this 1."*
- **Biased exponent** 🟡: *"To provide for negative exponents, we use a biased exponent. A bias is a number that is approximately midway in the range of values expressible by the exponent. We subtract the bias from the value in the exponent to determine its true value."* Bias 127 (single), 1023 (double).
- **Denormalised number** 🟡: $E = 0$, $F \ne 0$; *no hidden 1; allows numbers very close to 0*; value $(-1)^S\times0.F\times2^{-126}$ (single) / $2^{-1022}$ (double).
- **NaN** 🟡: *Not a Number*, $E$ = all 1s, $F \ne 0$; result of invalid operations (0/0, √−1).
- **Infinity** 🟡: $E$ = all 1s, $F = 0$; result of overflow or non-zero ÷ 0.
- **Overflow** 🟡: *"When the absolute value of a result becomes too large for the floating point standard, overflow occurs"*; result ±∞.
- **Underflow** 🟡: *"Occurs when a positive result becomes smaller than the smallest representable, positive number; the result is then set to 0."*
- **Gradual underflow** 🟡: loss of precision gradually (through denormals) instead of an abrupt jump to 0.

## L02 Error analysis ([[02.00 Error Analysis]])
- **Accuracy** 🟢: *how closely a computed or measured value agrees with the true value.*
- **Precision** 🟢: *how closely individual computed or measured values agree with each other.*
- **True error** 🟡: $E_t = $ true value − approximation.
- **Absolute error** 🟡: $|E_t|$.
- **True fractional relative error** 🟡: *the true error divided by the true value*, $(T - A)/T$.
- **Relative error** $\varepsilon_t$ 🟡: true fractional relative error as a percentage, $\times100\%$.
- **Approximate relative error** 🟡: $|(x^{new} - x^{old})/x^{new}|\times100\%$, used when the true value is unknown.
- **Round-off error** 🔴: *the difference between the calculated approximation of a number and its exact mathematical value due to rounding.*
- **Chopping / truncation error** 🔴: *the difference between a truncated value and the actual value; a truncated quantity has a fixed number of allowed digits, with any excess digits "chopped off".*
- **Quantization error** 🟢: the error associated with rounding/chopping.
- **Machine precision (unit round-off) $\varepsilon_{mach}$** 🟢: the largest relative error when rounding a real number to the floating-point system: $|fl(x) - x|/|x| \le \varepsilon_{mach}$ for UFL < x < OFL.
- **Well-posed problem (Hadamard)** 🟢: *has a solution, the solution is unique, the solution depends continuously on data and parameters.*
- **Forward error** 🟡: $|\hat y - y|$ (or relative $|\hat y - y|/|y|$): how far the computed answer is from the true answer.
- **Backward error** 🟡: the smallest $|\Delta x|$ (or $|\Delta x|/|x|$) with $\hat y = f(x + \Delta x)$: *"for what set of data have we actually solved the problem?"*
- **Well-conditioned / ill-conditioned problem** 🟢: *small changes in x lead to small changes in y* / *can lead to large changes in y.*
- **Condition number** 🟢: (relative change in solution)/(relative change in input) $\approx |xf'(x)/f(x)|$.
- **Stable algorithm** 🟢: *one whose result is relatively insensitive to errors made during the computations; the result is the exact solution of a nearby problem.*

## L03 Matrix programming ([[03.00 Matrix Programming]])
- **Row vector / column vector** 🟡: a $1\times n$ / $n\times1$ matrix.
- **Full matrix** 🟡: a general matrix with all elements stored.
- **Symmetric matrix** 🔴: *$A[i,j] = A[j,i]$ for all $i$ and $j$* ($A = A^T$).
- **Sparse matrix** 🔴: *a matrix in which most of the elements are zero.*
- **Sparse symmetric matrix** 🔴: symmetric and sparse; stored as non-zeros of one triangle (`a`, `diagonal`, `columnNums`, `nElements`).
- **Row-major / column-major storage** 🟢: a matrix stored row by row / column by column in a 1-D array.
- **Abstract class** 🟡: a class that cannot be instantiated and declares methods that subclasses must implement (e.g. `Matrix` with abstract `elementAt`).
- **Polymorphism** 🟡: the same method call runs the subclass's own implementation at run time.

## L04 Gaussian elimination ([[04.00 Gaussian Elimination]])
- **System of linear equations** 🔴: equations in which each unknown appears only to the first power; written $Ax = b$.
- **Augmented matrix** 🔴: $[A \mid b]$, the coefficient matrix with $b$ appended as an extra column.
- **Gaussian elimination** 🔴: a direct method that reduces $Ax = b$ by row operations to an upper triangular system $Ux = y$ and solves it by back substitution.
- **Forward elimination** 🔴: the row operations $R_i \leftarrow R_i - (a_{ik}/a_{kk})R_k$ that create zeros below the diagonal.
- **Back substitution** 🔴: solving an upper triangular system from the last equation upwards.
- **Pivot / multiplier** 🔴: the diagonal element $a_{kk}$ used to eliminate below it / $m_{ik} = a_{ik}/a_{kk}$.
- **Partial pivoting** 🟡: swapping rows so the pivot is the largest in absolute value in its column (avoids zero/small pivots).

## L05 Iterative methods ([[05.00 Iterative Methods - Jacobi and Gauss-Seidel]])
- **Iterative method** 🔴: starts from an initial guess and improves it repeatedly until a stopping criterion is met.
- **Jacobi method** 🟡: each equation solved for its diagonal unknown; every new value computed from the **previous** iteration's values only.
- **Gauss-Seidel method** 🔴: like Jacobi, but *"we use the new values as soon as they are known."*
- **Spectral radius** $\rho(T)$ 🟡: the largest absolute value of the eigenvalues of $T$; the iteration converges for every start iff $\rho(T) < 1$.
- **Strictly diagonally dominant** 🟡: $|a_{ii}| > \sum_{j\ne i}|a_{ij}|$ in every row; guarantees convergence of Jacobi and Gauss-Seidel.

## L06 LU and Cholesky ([[06.00 LU Decomposition and Cholesky]])
- **LU decomposition** 🟢: writing a non-singular $A$ as $A = LU$, $L$ lower triangular, $U$ upper triangular.
- **Lower / upper triangular matrix** 🔴: all entries above / below the main diagonal are zero.
- **Symmetric positive definite (SPD)** 🔴: $A = A^T$ and $x^TAx > 0$ for every non-zero $x$.
- **Cholesky decomposition** 🔴: $A = LL^T$ for an SPD matrix, $L$ lower triangular with positive diagonal; *succeeds iff $A$ is SPD.*

## L07 Non-linear equations ([[07.00 Non-linear Equations - Fixed Point Iteration]])
- **Algebraic equation** 🟢: $f$ is a polynomial (solutions are called roots). **Transcendental equation** 🟢: involves sin, cos, exp, log, …
- **Fixed point** 🟡: a value $s$ with $g(s) = s$.
- **Fixed-point iteration** 🟡: rewrite $f(x) = 0$ as $x = g(x)$ and compute $x_{n+1} = g(x_n)$.
- **Contraction** 🟢: $|g(x) - g(v)| \le K|x - v|$ with $K < 1$.
- **Newton–Raphson method** 🟢: $x_{n+1} = x_n - f(x_n)/f'(x_n)$, the x-intercept of the tangent.

## L08 SOR ([[08.00 Successive Over-Relaxation]])
- **Successive over-relaxation (SOR)** 🔴: Gauss-Seidel with a relaxation parameter $\omega$: $x_i^{new} = (1 - \omega)x_i^{old} + \omega x_i^{GS}$.
- **Relaxation parameter $\omega$** 🔴: weight of the Gauss-Seidel update; $\omega = 1$ is Gauss-Seidel, $1 < \omega < 2$ over-relaxation, $0 < \omega < 1$ under-relaxation.

## L09 Finite differences ([[09.00 Finite Difference Method]])
- **Discretisation** 🟡: replacing a continuous domain by a numerical grid of nodes, one algebraic equation per node.
- **Finite difference** 🟡: *a partial derivative replaced with a suitable algebraic difference quotient.*
- **Forward / backward / central difference** 🟡: $\frac{f_{i+1} - f_i}{\Delta x}$ / $\frac{f_i - f_{i-1}}{\Delta x}$ / $\frac{f_{i+1} - f_{i-1}}{2\Delta x}$.
- **Truncation error (FD)** 🟡: *the higher-order terms neglected in the Taylor series; it identifies the difference between the exact solution of a differential equation and its finite difference solution without round-off error.*
- **Order of accuracy** 🟡: the power of $\Delta x$ in the leading truncation term (forward/backward 1, central 2).
- **Forward difference operator Δ** 🟡: $\Delta f(x) = f(x + h) - f(x)$; $\Delta^k f = \Delta(\Delta^{k-1}f)$.
- **Forward difference table** 🟡: table of $y$ and successive differences $\Delta y, \Delta^2y, \dots$; $n$-th differences are constant for a degree-$n$ polynomial.
