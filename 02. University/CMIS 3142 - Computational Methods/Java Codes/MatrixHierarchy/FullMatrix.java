package MatrixHierarchy;

// (i) Full matrix: every element is stored in an m x n array.
public class FullMatrix extends Matrix {
    private final double[][] a;

    public FullMatrix(int m, int n) {
        super(m, n);
        a = new double[m][n];
    }

    public FullMatrix(double[][] values) {
        this(values.length, values[0].length);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                a[i][j] = values[i][j];
    }

    @Override
    public double elementAt(int i, int j) {
        checkIndex(i, j);
        return a[i][j];
    }

    @Override
    public void setElement(int i, int j, double value) {
        checkIndex(i, j);
        a[i][j] = value;
    }

    // faster than the inherited version: swap the two row references instead of every element
    @Override
    public Matrix swapRows(int r1, int r2) {
        checkRow(r1);
        checkRow(r2);
        double[] t = a[r1];
        a[r1] = a[r2];
        a[r2] = t;
        return this;
    }
}
