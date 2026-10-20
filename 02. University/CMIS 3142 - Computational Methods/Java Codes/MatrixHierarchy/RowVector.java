package MatrixHierarchy;

// (v) Row vector: a 1 x n matrix stored in a 1-D array.
public class RowVector extends Matrix {
    private final double[] a;

    public RowVector(int n) {
        super(1, n);
        a = new double[n];
    }

    @Override
    public double elementAt(int i, int j) {
        checkIndex(i, j);          // i must be 0
        return a[j];
    }

    @Override
    public void setElement(int i, int j, double value) {
        checkIndex(i, j);
        a[j] = value;
    }

    // a column swap on a row vector is just swapping two elements
    @Override
    public Matrix swapColumns(int c1, int c2) {
        checkColumn(c1);
        checkColumn(c2);
        double t = a[c1];
        a[c1] = a[c2];
        a[c2] = t;
        return this;
    }
    // swapRows is inherited: the only valid call is swapRows(0, 0), which changes nothing
}
