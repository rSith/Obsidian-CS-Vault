public class ColumnVector {
    final int size;
    final double[] data;

    ColumnVector(int size) {
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
            System.out.println(data[i]);
        }
    }
}
