public class RowVector {
    final int size;
    final double[] data;

    RowVector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        this.size = size;
        data = new double[size];
    }

    void set(int i, double value) {
        data[i] = value;
    }

    double get(int i) {
        return data[i];
    }

    void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    FullMatrix toFullMatrix(int r, int c) {
        if (r * c != size) {
            throw new IllegalArgumentException("r * c must equal the vector size");
        }
        FullMatrix fm = new FullMatrix(r, c);
        int k = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                fm.data[i][j] = data[k++];
            }
        }
        return fm;
    }
}
