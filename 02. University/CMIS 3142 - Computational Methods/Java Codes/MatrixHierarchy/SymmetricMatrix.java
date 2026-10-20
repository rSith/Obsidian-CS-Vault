package MatrixHierarchy;

// (ii) Symmetric matrix: A[i][j] = A[j][i], so only the lower triangle is stored,
// row by row, in a 1-D array of n(n+1)/2 elements.
public class SymmetricMatrix extends Matrix {
    private final double[] a;

    public SymmetricMatrix(int n) {
        super(n, n);
        a = new double[n * (n + 1) / 2];
    }

    // position of A[i][j] (0-based) in the packed lower triangle
    private int index(int i, int j) {
        checkIndex(i, j);
        if (j > i) { int t = i; i = j; j = t; }   // use A[j][i] for the upper triangle
        return i * (i + 1) / 2 + j;
    }

    @Override
    public double elementAt(int i, int j) {
        return a[index(i, j)];
    }

    // setting A[i][j] also sets A[j][i] (they share one storage cell)
    @Override
    public void setElement(int i, int j, double value) {
        a[index(i, j)] = value;
    }

    // Swapping only two rows (or columns) makes A[i][j] != A[j][i], which this storage cannot
    // hold, so the result is returned as a new FullMatrix and this matrix is left unchanged.
    @Override
    public Matrix swapRows(int r1, int r2) {
        if (r1 == r2) { checkRow(r1); return this; }
        return toFull().swapRows(r1, r2);
    }

    @Override
    public Matrix swapColumns(int c1, int c2) {
        if (c1 == c2) { checkColumn(c1); return this; }
        return toFull().swapColumns(c1, c2);
    }
}
