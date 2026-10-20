package MatrixHierarchy;

// (vi) Column vector: an n x 1 matrix stored in a 1-D array.
public class ColumnVector extends Matrix {
    private final double[] a;

    public ColumnVector(int n) {
        super(n, 1);
        a = new double[n];
    }

    @Override
    public double elementAt(int i, int j) {
        checkIndex(i, j);          // j must be 0
        return a[i];
    }

    @Override
    public void setElement(int i, int j, double value) {
        checkIndex(i, j);
        a[i] = value;
    }

    // a row swap on a column vector is just swapping two elements
    @Override
    public Matrix swapRows(int r1, int r2) {
        checkRow(r1);
        checkRow(r2);
        double t = a[r1];
        a[r1] = a[r2];
        a[r2] = t;
        return this;
    }
    // swapColumns is inherited: the only valid call is swapColumns(0, 0), which changes nothing
}
