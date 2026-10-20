package MatrixHierarchy;

// (iv) Sparse symmetric matrix: a sparse matrix that stores only the non-zeros of the
// lower triangle (i >= j). A[i][j] with j > i is read from A[j][i].
public class SparseSymmetricMatrix extends SparseMatrix {

    public SparseSymmetricMatrix(int n) {
        super(n, n);
    }

    @Override
    public double elementAt(int i, int j) {
        return (j > i) ? super.elementAt(j, i) : super.elementAt(i, j);
    }

    @Override
    public void setElement(int i, int j, double value) {
        if (j > i) super.setElement(j, i, value);
        else super.setElement(i, j, value);
    }

    // A row (or column) swap breaks symmetry, but the result is still sparse,
    // so it is returned as a new (general) SparseMatrix; this matrix is left unchanged.
    @Override
    public Matrix swapRows(int r1, int r2) {
        if (r1 == r2) { checkRow(r1); return this; }
        return toSparse().swapRows(r1, r2);
    }

    @Override
    public Matrix swapColumns(int c1, int c2) {
        if (c1 == c2) { checkColumn(c1); return this; }
        return toSparse().swapColumns(c1, c2);
    }

    // expand the stored lower triangle into a general sparse matrix (both triangles)
    public SparseMatrix toSparse() {
        SparseMatrix S = new SparseMatrix(rows, columns);
        for (Entry e : entries) {
            S.setElement(e.row, e.col, e.value);
            S.setElement(e.col, e.row, e.value);
        }
        return S;
    }
}
