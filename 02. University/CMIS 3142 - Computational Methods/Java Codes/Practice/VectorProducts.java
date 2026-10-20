public class VectorProducts {

    // (i) A * B : row vector (1 x n) times column vector (n x 1) -> scalar
    static double rowTimesColumn(double[] A, double[] B) {
        if (A.length != B.length) throw new IllegalArgumentException("Lengths must match");
        double sum = 0;
        for (int k = 0; k < A.length; k++)
            sum += A[k] * B[k];
        return sum;
    }

    // (ii) B * A : column vector (n x 1) times row vector (1 x m) -> n x m matrix
    static double[][] columnTimesRow(double[] B, double[] A) {
        double[][] C = new double[B.length][A.length];
        for (int i = 0; i < B.length; i++)
            for (int j = 0; j < A.length; j++)
                C[i][j] = B[i] * A[j];
        return C;
    }

    public static void main(String[] args) {
        double[] A = {1, 2, 3};          // row vector
        double[] B = {4, 5, 6};          // column vector
        System.out.println("A*B = " + rowTimesColumn(A, B));   // 32.0
        for (double[] row : columnTimesRow(B, A)) {
            for (double x : row) System.out.print(x + " ");
            System.out.println();
        }
    }
}