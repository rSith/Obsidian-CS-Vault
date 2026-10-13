---
type: past-paper
course: CMIS 3142
lesson: L03
status: complete
tags: [cmis3142, past-paper, cmis3142/L03]
---
# PP 03 · Matrix Programming: Past-Paper Questions

> [!info] Lesson: [[03.00 Matrix Programming]] · Summary: [[03.99 Summary - Matrix Programming]] · [[CMIS 3142 Past Paper Mapping]]
> Examined in **every** paper, mostly Java code. Priority 🔴. The full, tested programs live in the lesson notes; answers below give the exam-sized version and point to them.

| Paper | Q | Topic | Marks |
|---|---|---|---|
| 2023 | 4(a) | Efficient Java class for a symmetric matrix: `element`, `Store_Element`, `Multiply` | 12 |
| 2023 | 4(b) | Discuss enhancing it for a sparse symmetric matrix | 5 |
| 2023 | 4(c) | Java: row vector × column vector and column × row | 8 |
| 2024 | 3 | Inheritance & polymorphism: 6 matrix classes with add, sub, multiply, row swap, column swap | – |
| 2025 | 3(a) | Efficient representation of a sparse symmetric matrix | – |
| 2025 | 3(b)–(e) | Java: read/store, element at (i,j), display stored elements, display full matrix | – |
| 2026 | 2(a) | Suitable method to represent a sparse symmetric matrix | 10 |
| 2026 | 2(b) | Single Java program: read/store, display, element, multiply two, count non-zeros | 25 |
| 2026 | 2(c) | Changes needed for general sparse matrices | 15 |
| 2026 | 6(a) | Suitable method to represent a symmetric matrix | 10 |

---

## 2023 · Q4(a) (12 marks)
> **Question:** Write an efficient Java class for a symmetric matrix with the following methods: i. `element(i,j)` returns the element at iᵗʰ row and jᵗʰ column of the matrix. ii. `Store_Element(i,j,E)` stores the element "E" of iᵗʰ row and jᵗʰ column of the Matrix. iii. `Multiply(A)` returns the multiplication of the particular object with the symmetric matrix A. Note that the matrix returned may not be symmetric.

**Lesson:** [[03.03 Symmetric Matrix Storage]] (complete tested class)

### Expected answer
Store only the lower triangle row by row in a 1-D array of size $n(n+1)/2$; index $k = i(i+1)/2 + j$ for $i \ge j$ (0-based), swapping $i, j$ when $j > i$.
```java
public class SymmetricMatrix {
    private int n;
    private double[] a;                                   // n(n+1)/2 values

    public SymmetricMatrix(int n) { this.n = n; a = new double[n * (n + 1) / 2]; }

    private int index(int i, int j) {
        if (j > i) { int t = i; i = j; j = t; }           // A[i][j] = A[j][i]
        return i * (i + 1) / 2 + j;
    }
    public double element(int i, int j)               { return a[index(i, j)]; }
    public void   Store_Element(int i, int j, double E) { a[index(i, j)] = E; }

    public double[][] Multiply(SymmetricMatrix A) {      // result may not be symmetric
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                double sum = 0;
                for (int k = 0; k < n; k++) sum += element(i, k) * A.element(k, j);
                C[i][j] = sum;
            }
        return C;
    }
}
```

### Key points to include
- **Why efficient:** half the memory ($n(n+1)/2$ vs $n^2$); $O(1)$ access by formula.
- Index formula with its derivation (rows $0..i-1$ hold $i(i+1)/2$ values).
- `Multiply` returns a **full** matrix because a product of symmetric matrices is generally not symmetric (e.g. $\begin{bmatrix}3&4&5\\4&15&7\\5&7&3\end{bmatrix}\begin{bmatrix}1&2&0\\2&1&0\\0&0&1\end{bmatrix}$ has $c_{21} = 34 \ne c_{12} = 10$).

### How to approach this question
Explain the storage scheme in 2–3 lines and the formula, then the class; ~4 marks per method.

---

## 2023 · Q4(b) (5 marks)
> **Question:** Discuss how you can enhance the above program to facilitate a sparse symmetric matrix. (Only a discussion is sufficient; enhanced program is not needed)

**Lesson:** [[03.06 Sparse Symmetric Matrices]] §4

### Expected answer
- Keep the symmetric idea (lower triangle only; swap when $j > i$) but store **only non-zero** values.
- Replace the packed array by the lecture's structure: `a` (non-zero values row by row), `columnNums` (column of each value), `diagonal` (position of each row's diagonal element in `a`, marking where each row ends; diagonal always stored), `nElements`.
- `element(i,j)`: swap if needed, search row $i$'s segment (`diagonal[i-1]+1 … diagonal[i]`) for column $j$; return 0 if not found.
- `Store_Element`: overwrite if present; otherwise insert in row $i$'s segment (shifting later entries and updating `diagonal` and `nElements`); remove the entry if $E = 0$. A linked-list version (header and element nodes) avoids shifting.
- `Multiply`: loop only over stored non-zeros; return a full/general result.
- Trade-off: memory proportional to the number of non-zeros, but access needs a search instead of a formula.

---

## 2023 · Q4(c) (8 marks)
> **Question:** Let A be a row vector and B be a column vector. Write a separate Java method to perform each of the following calculations: i. A\*B ii. B\*A

**Lesson:** [[03.01 Matrix Types and Vector Products]]

### Expected answer
$A$ is $1\times n$, $B$ is $n\times1$. $A\cdot B$ is a **scalar** $\sum a_kb_k$ (inner product); $B\cdot A$ is an **$n\times n$ matrix** with entries $b_ia_j$ (outer product).
```java
static double rowTimesColumn(double[] A, double[] B) {          // (i) A*B
    if (A.length != B.length) throw new IllegalArgumentException("Lengths must match");
    double sum = 0;
    for (int k = 0; k < A.length; k++) sum += A[k] * B[k];
    return sum;
}

static double[][] columnTimesRow(double[] B, double[] A) {      // (ii) B*A
    double[][] C = new double[B.length][A.length];
    for (int i = 0; i < B.length; i++)
        for (int j = 0; j < A.length; j++) C[i][j] = B[i] * A[j];
    return C;
}
```
Example $A = [1\ 2\ 3]$, $B = [4\ 5\ 6]^T$: $AB = 32$; $BA = \begin{bmatrix}4&8&12\\5&10&15\\6&12&18\end{bmatrix}$.

---

## 2024 · Q3
> **Question:** Using inheritance and polymorphism, write one Java class for each of the following matrix categories with the matrix operations given below: i) Full matrix ii) Symmetric matrix iii) Sparse matrix iv) Sparse symmetric matrix v) Row vector vi) Column vector. Matrix operations: i. Addition ii. Subtraction iii. Multiplication iv. Row swap v. Column swap

**Lesson:** [[03.04 Abstract Matrix Class and Inheritance]] (complete tested hierarchy, ~190 lines)

### Expected answer (structure)
1. **Abstract base class** `Matrix` (lecture slide 10) with `rows`, `columns`, abstract `elementAt(i,j)` and `setElement(i,j,v)`, and **concrete** `add`, `sub`, `multiply`, `swapRows`, `swapColumns`, `display` written once using `elementAt`/`setElement` (results returned as `FullMatrix`).
```java
abstract class Matrix {
    protected int rows, columns;
    public abstract double elementAt(int i, int j);
    public abstract void setElement(int i, int j, double v);

    public Matrix add(Matrix B) {
        if (rows != B.rows || columns != B.columns) throw new IllegalArgumentException("Size mismatch");
        FullMatrix C = new FullMatrix(rows, columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) C.setElement(i, j, elementAt(i, j) + B.elementAt(i, j));
        return C;
    }
    // sub: same with '-';  multiply: C[i][j] = sum_k elementAt(i,k) * B.elementAt(k,j)
    public void swapRows(int r1, int r2) {
        for (int j = 0; j < columns; j++) {
            double t = elementAt(r1, j); setElement(r1, j, elementAt(r2, j)); setElement(r2, j, t);
        }
    }
    // swapColumns: same over rows
}
```
2. **Subclasses**, each choosing its storage and implementing `elementAt`/`setElement`:

| Class | Storage | `elementAt(i,j)` |
|---|---|---|
| `FullMatrix` | `double[m][n]` | `a[i][j]` |
| `RowVector` | `double[n]`, rows = 1 | `a[j]` |
| `ColumnVector` | `double[n]`, columns = 1 | `a[i]` |
| `SymmetricMatrix` | `double[n(n+1)/2]` lower triangle | `a[idx(max, min)]` |
| `SparseMatrix` | lists of (row, col, value), zeros not stored | search; 0 if absent |
| `SparseSymmetricMatrix extends SparseMatrix` | only $i \ge j$ stored | swap if $j > i$, then `super.elementAt` |

3. **Overrides:** `SymmetricMatrix` and `SparseSymmetricMatrix` override `swapRows`/`swapColumns` (a row swap alone breaks symmetry: throw an exception or convert with `toFull()`, or swap row **and** column together).
4. **Polymorphism demo:** `Matrix S = new SymmetricMatrix(3); Matrix P = new SparseSymmetricMatrix(3); Matrix C = S.add(P);` works through dynamic binding of `elementAt`.

### Key points to include
- Name and use **abstract class, inheritance, overriding, polymorphism (dynamic binding)** explicitly.
- Explain why results are full matrices and why symmetric swaps are special.

### How to approach this question
If time is short, the abstract class + the storage/`elementAt`/`setElement` of each subclass + one polymorphic `main` earns most marks.

---

## 2025 · Q3(a)
> **Question:** Describe an efficient way to represent a sparse symmetric matrix to minimize the memory use in a computer program.

## 2026 · Q2(a) (10 marks)
> **Question:** Explain a suitable method to represent sparse symmetric matrix.

**Lesson:** [[03.06 Sparse Symmetric Matrices]] §1–2

### Expected answer
- Use **both** properties: symmetry → store only the **lower triangle** ($A[i][j] = A[j][i]$); sparsity → store only **non-zero** values with their positions.
- Lecture structure:
  - `a` (row vector): non-zero values of the lower triangle, row by row;
  - `columnNums` (integer vector): column number of each stored value;
  - `diagonal` (integer vector): position in `a` of each row's diagonal element (stored even if 0), so row $i$ occupies `diagonal[i-1]+1 … diagonal[i]`;
  - `nElements`: number of stored values.
- Example: $\begin{bmatrix}5&0&0&1\\0&6&2&0\\0&2&7&0\\1&0&0&8\end{bmatrix}$ → `a = [5, 6, 2, 7, 1, 8]`, `columnNums = [0, 1, 1, 2, 0, 3]`, `diagonal = [0, 1, 3, 5]`, `nElements = 6` (vs 16 full, 10 symmetric).
- Access `elementAt(i,j)`: swap if $j > i$; search row $i$'s segment for $j$; return 0 if absent.
- Alternative: orthogonal linked list (header nodes per row/column; element nodes with row, column, value, down, right links) for frequent insertions.
- Allocate arrays at the exact size (trim after reading) to minimise memory.

---

## 2025 · Q3(b)–(e) · 2026 · Q2(b) (25 marks)
> **2025 (b)** Write a Java method to read and store elements of a sparse symmetric matrix. **(c)** … return the element at a given row and column … **(d)** … display only the stored elements of the sparse symmetric matrix and the row number and the column number of each element. **(e)** … display all the elements (including zeros) of a sparse symmetric matrix in the form of a matrix.
> **2026 (b)** Write a single Java program to perform the following tasks: i) Read and store elements ii) Display the matrix iii) Return the element of a given row and column iv) Multiply two sparse symmetric matrix v) Return the number of non-zero elements.

**Lesson:** [[03.06 Sparse Symmetric Matrices]] §3 (complete tested program and output)

### Expected answer (core methods)
```java
double[] a; int[] diagonal, columnNums; int nElements, n;

void readAndStore(Scanner sc) {                      // lower triangle, row by row
    n = sc.nextInt();
    a = new double[n * (n + 1) / 2]; columnNums = new int[n * (n + 1) / 2]; diagonal = new int[n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j <= i; j++) {
            double v = sc.nextDouble();
            if (v != 0 || j == i) {                   // non-zeros, and always the diagonal
                a[nElements] = v; columnNums[nElements] = j;
                if (j == i) diagonal[i] = nElements;
                nElements++;
            }
        }
    a = Arrays.copyOf(a, nElements); columnNums = Arrays.copyOf(columnNums, nElements);
}

double elementAt(int i, int j) {
    if (j > i) { int t = i; i = j; j = t; }
    int start = (i == 0) ? 0 : diagonal[i - 1] + 1;
    for (int k = start; k <= diagonal[i]; k++) if (columnNums[k] == j) return a[k];
    return 0.0;
}

void displayStored() {                                // 2025 (d)
    int row = 0;
    for (int k = 0; k < nElements; k++) {
        while (k > diagonal[row]) row++;
        System.out.println("(" + row + ", " + columnNums[k] + ") = " + a[k]);
    }
}

void displayFull() {                                  // 2025 (e), 2026 (ii)
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) System.out.printf("%8.2f", elementAt(i, j));
        System.out.println();
    }
}

double[][] multiply(SparseSymmetric B) {              // 2026 (iv): result not symmetric in general
    double[][] C = new double[n][n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            for (int k = 0; k < n; k++) {
                double aik = elementAt(i, k);
                if (aik != 0) C[i][j] += aik * B.elementAt(k, j);
            }
    return C;
}

int countNonZeros() {                                 // 2026 (v)
    int count = 0;
    for (int k = 0; k < nElements; k++) {
        if (a[k] == 0) continue;
        boolean onDiag = false;
        for (int i = 0; i < n; i++) if (diagonal[i] == k) onDiag = true;
        count += onDiag ? 1 : 2;                       // off-diagonal values appear twice
    }
    return count;
}
```

### Key points to include
- $j > i$ swap; return 0 when absent; result of multiply is full; off-diagonal non-zeros counted twice.
- A `main` that calls each method (2026 asks for a **single program**).

---

## 2026 · Q2(c) (15 marks)
> **Question:** What is to be done to use the above program to do the same for sparse matrices?

**Lesson:** [[03.05 Sparse Matrices]] §4 (change table + tested general sparse program)

### Expected answer
1. **No symmetry:** store **every** non-zero (both triangles), so read the **whole** row ($j = 0..n-1$), not just $j \le i$.
2. **Shape:** allow $m\times n$ (store rows $m$ and columns $n$ separately).
3. **Row boundaries:** the diagonal is no longer guaranteed to be stored nor to end each row, so replace `diagonal[]` by `rowStart[]` (size $m+1$): row $i$ is positions `rowStart[i] … rowStart[i+1]-1` (empty rows allowed).
4. **`elementAt(i,j)`:** remove the $i \leftrightarrow j$ swap; search row $i$ directly.
5. **Display stored:** row of position $k$ from `rowStart`.
6. **Multiply:** check dimensions ($m\times n$ times $n\times p$); result $m\times p$.
7. **Count non-zeros:** simply `nElements` (no doubling).
8. **Memory:** roughly twice the storage of the symmetric version for the same matrix.

---

## 2026 · Q6(a) (10 marks)
> **Question:** Describe a suitable method to represent the symmetric matrix.

**Lesson:** [[03.03 Symmetric Matrix Storage]]

### Expected answer
- Symmetric: $A[i][j] = A[j][i]$, so only the **lower triangle including the diagonal** needs storing: $n(n+1)/2$ values instead of $n^2$ (about half; e.g. $n = 1000$: 500 500 vs 1 000 000).
- Store it **row by row in a row vector** `a` plus the size $n$ (lecture slide 13). Example: $\begin{bmatrix}3&4&5\\4&15&7\\5&7&3\end{bmatrix}$ → `a = [3, 4, 15, 5, 7, 3]`.
- **Index:** for $i \ge j$ (0-based) $k = \frac{i(i+1)}{2} + j$ (1-based: $\frac{(i-1)i}{2} + j$); if $j > i$, swap. Check: $(2,1) \to 3 + 1 = 4$ → 7 ✓.
- `elementAt`/`setElement` are $O(1)$; setting $(i,j)$ automatically sets $(j,i)$.
- Constructors: by size, from an array, from a file, from another matrix (slide 13).
- Ideal for Cholesky, which only reads the lower triangle and produces a lower-triangular $L$ that fits the same layout (2026 Q6(b), see [[PP 06 - LU and Cholesky]]).
