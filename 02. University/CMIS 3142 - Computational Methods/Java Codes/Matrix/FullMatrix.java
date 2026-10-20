public class FullMatrix {
    final int rows, cols;
    final double[][] data;

    FullMatrix(int r, int c) {
        if (r <= 0 || c <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive");
        }
        rows = r;
        cols = c;
        data = new double[r][c];
    }

    void set(int r, int c, double value) {
        data[r][c] = value;
    }

    double get(int r, int c) {
        return data[r][c];
    }

    void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    RowVector toRowVector() {
        RowVector rv = new RowVector(rows * cols);
        int k = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rv.data[k++] = data[i][j];
            }
        }
        return rv;
    }

    ColumnVector toColumnVector() {
        ColumnVector cv = new ColumnVector(rows * cols);
        int k = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cv.data[k++] = data[i][j];
            }
        }
        return cv;
    }
}
