public class FullMatrix {
    int rows, cols;
    int[][] data;

    FullMatrix(int r, int c) {
        rows = r;
        cols = c;
        data = new int[r][c];
    }

    void set(int r, int c, int value) {
        data[r][c] = value;
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
