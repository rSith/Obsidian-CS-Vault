---
type: revision
course: CMIS 3142
status: complete
tags: [cmis3142, revision, comparisons]
aliases: [CMIS 3142 Comparisons]
---
# CMIS 3142 · Important Comparisons

> [!info] Back to [[00. CMIS 3142 Course Overview|Course Overview]] · ⭐ = asked as a comparison question in a past paper.

## 1. Single vs double precision ⭐ (2023 Q1(b)) · [[01.05 IEEE 754 Double Precision]]
| | Single | Double |
|---|---|---|
| Size | 32 bits | 64 bits |
| S / E / F | 1 / 8 / 23 | 1 / 11 / 52 |
| Bias | 127 | 1023 |
| Precision | 24 bits ≈ 7 digits | 53 bits ≈ 16 digits |
| Largest | ≈ $3.4\times10^{38}$ | ≈ $1.8\times10^{308}$ |
| Smallest normal | $2^{-126}\approx1.18\times10^{-38}$ | $2^{-1022}\approx2.2\times10^{-308}$ |
| Java | `float` | `double` |

## 2. Normalised vs denormalised ⭐ (2024 Q1(b)) · [[01.06 Special Values and Denormalized Numbers]]
| | Normalised | Denormalised |
|---|---|---|
| Exponent field | 1 … 254 | 0 |
| Leading bit | hidden 1 | 0 (no hidden 1) |
| Value (single) | $1.F\times2^{E-127}$ | $0.F\times2^{-126}$ |
| Purpose | ordinary numbers | numbers very close to 0 (gradual underflow) |

## 3. Overflow vs underflow ⭐ (2025 Q1(a)) · [[01.07 Ranges, Overflow and Underflow]]
| | Overflow | Underflow |
|---|---|---|
| Cause | magnitude too large | magnitude too small |
| Result | ±∞ | denormal, then 0 |
| Seriousness | usually a bug | usually harmless (careful with division) |

## 4. Biased exponent vs two's complement · [[01.04 Biased Exponent]]
| Biased | Two's complement |
|---|---|
| $E = e + \text{bias}$, unsigned | sign encoded in top bit |
| bit-pattern order = value order (easy comparison) | not ordered for comparison |
| used for IEEE exponents | used for integers |

## 5. Round-off vs chopping ⭐ (2025 Q2(a)(b)) · [[02.02 Round-off and Chopping Errors]]
| Round-off (rounding) | Chopping (truncation) |
|---|---|
| keep nearest value | delete extra digits |
| max error ½ unit (0.005 at 2 d.p.) | max error ~1 unit (< 0.01) |
| error positive or negative → cancels | error one sign → accumulates |
| extra processing | cheap; "most computers chop" |
| 2.5153846 → 2.52 | 2.5153846 → 2.51 |

## 6. Truncation error (L02) vs truncation error (L09)
| L02 (chopping) | L09 (finite differences) |
|---|---|
| digits chopped off a number | higher-order Taylor terms dropped |
| reduced by more digits / rounding | reduced by smaller $\Delta x$ or higher-order formulas |

## 7. Round-off error vs truncation error (numerical solutions) · [[09.02 First Derivative Differences and Truncation Error]]
| Round-off | Truncation |
|---|---|
| finite precision of floating-point arithmetic | approximating an infinite process (Taylor series) |
| grows as $\Delta x$ becomes very small (cancellation) | shrinks as $\Delta x \to 0$ |

## 8. Accuracy vs precision · [[02.01 Accuracy, Precision and Error Measures]]
| Accuracy | Precision |
|---|---|
| closeness to the true value | closeness of values to each other |

## 9. Forward vs backward error ⭐ (2023 Q3(a)) · [[02.05 Well-Posed Problems, Forward and Backward Error]]
| Forward error | Backward error |
|---|---|
| $\lvert\hat y - y\rvert$ (output space) | smallest $\lvert\Delta x\rvert$ with $\hat y = f(x+\Delta x)$ (input space) |
| needs true $y$ | needs only which input gives $\hat y$ |
| √2 ≈ 1.4: 0.0142 | √2 ≈ 1.4: 0.04 |
| linked to accuracy | linked to stability |

## 10. True fractional relative error vs relative error vs approximate relative error ⭐ (2024 Q2(a))
| True fractional relative error | Relative error $\varepsilon_t$ | Approximate relative error $\varepsilon_a$ |
|---|---|---|
| $(T - A)/T$ | $(T - A)/T\times100\%$ | $(x^{new} - x^{old})/x^{new}\times100\%$ |
| needs true value | needs true value | does not; used as a stopping test |

## 11. Conditioning vs stability · [[02.06 Conditioning and Stability]]
| Conditioning | Stability |
|---|---|
| property of the problem | property of the algorithm |
| condition number | backward error |

## 12. Matrix storage schemes · [[03.99 Summary - Matrix Programming]]
| | Full | Symmetric | Sparse | Sparse symmetric |
|---|---|---|---|---|
| Stores | all $n^2$ | lower triangle $\frac{n(n+1)}{2}$ | non-zeros + positions | lower-triangle non-zeros + positions |
| Access | direct | formula $O(1)$ | search | search (swap if $j > i$) |
| Row swap | allowed | breaks symmetry | allowed | breaks symmetry |

## 13. Sparse symmetric vs general sparse ⭐ (2026 Q2(c)) · [[03.05 Sparse Matrices]]
| Sparse symmetric | General sparse |
|---|---|
| lower triangle only | both triangles |
| `diagonal[]` marks row ends | `rowStart[]` (size $m+1$) |
| swap $i, j$ if $j > i$ | no swap |
| off-diagonal counted twice | count = `nElements` |
| square only | $m\times n$ |

## 14. Linked-list vs array sparse storage · [[03.05 Sparse Matrices]]
| Linked list (header/element nodes) | Arrays (triplet / compressed row) |
|---|---|
| easy insert/delete | compact, fast row access |
| pointer overhead | costly insertion |

## 15. Row vector × column vector vs column × row ⭐ (2023 Q4(c)) · [[03.01 Matrix Types and Vector Products]]
| $A$ (1×n) · $B$ (n×1) | $B$ (n×1) · $A$ (1×n) |
|---|---|
| scalar $\sum a_kb_k$ (inner product) | $n\times n$ matrix $b_ia_j$ (outer product) |

## 16. Gaussian elimination vs Gauss-Jordan · [[04.02 Forward Elimination and Back Substitution]]
| GE | Gauss-Jordan |
|---|---|
| upper triangular + back substitution | identity, no back substitution |
| ≈ $\frac23n^3$ | ≈ 50% more work |

## 17. Direct vs iterative methods
| Direct (GE, LU, Cholesky) | Iterative (Jacobi, GS, SOR) |
|---|---|
| fixed number of steps; exact in exact arithmetic | repeat until tolerance |
| round-off accumulates; fill-in for sparse | control round-off; keep sparsity; need only $A\times x$ |
| any non-singular $A$ (with pivoting) | need convergence ($\rho(T) < 1$, diagonal dominance) |
| best for small/dense | best for large/sparse |

## 18. Jacobi vs Gauss-Seidel ⭐ (2024 Q4(e), 2025 Q4(a)) · [[05.03 Convergence of Iterative Methods]]
| Jacobi | Gauss-Seidel |
|---|---|
| old values only | new values immediately |
| $T_j = D^{-1}(L+U)$ | $T_g = (D-L)^{-1}U$ |
| 2 vectors | 1 vector |
| slower | faster (Stein–Rosenberg) |
| parallel-friendly | sequential |

## 19. Gauss-Seidel vs SOR · [[08.01 SOR Method]]
| Gauss-Seidel | SOR |
|---|---|
| $x_i = x_i^{GS}$ | $x_i = (1-\omega)x_i^{old} + \omega x_i^{GS}$ |
| no parameter | $\omega \in (0, 2)$ |
| = SOR with $\omega = 1$ | faster with a good $\omega$ |

## 20. LU vs Cholesky ⭐ (2026 Q6(c) "specialty") · [[06.01 LU Decomposition]]
| LU | Cholesky |
|---|---|
| $A = LU$ | $A = LL^T$ |
| non-singular $A$ | SPD $A$ |
| ≈ $\frac23n^3$, store $L$ and $U$ | ≈ $\frac13n^3$, store $L$ only |
| pivoting may be needed | no pivoting; stable |

## 21. Fixed-point vs Newton–Raphson · [[07.02 Newton-Raphson Method]]
| Fixed-point | Newton |
|---|---|
| $x_{n+1} = g(x_n)$ | $x_{n+1} = x_n - f/f'$ |
| needs $\lvert g'\rvert < 1$ | needs $f'$, good $x_0$ |
| linear (slow) | very fast near root |

## 22. Forward vs backward vs central difference ⭐ (2026 Q3(a)) · [[09.02 First Derivative Differences and Truncation Error]]
| Forward | Backward | Central |
|---|---|---|
| $\frac{f_{i+1} - f_i}{\Delta x}$ | $\frac{f_i - f_{i-1}}{\Delta x}$ | $\frac{f_{i+1} - f_{i-1}}{2\Delta x}$ |
| $O(\Delta x)$ | $O(\Delta x)$ | $O(\Delta x^2)$ |
| left boundary | right boundary | interior; most accurate |
