import java.util.Scanner;

public class MatrixToVectors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Rows m: ");    int m = sc.nextInt();
        System.out.print("Columns n: "); int n = sc.nextInt();

        double[][] A = new double[m][n];
        System.out.println("Enter the elements row by row:");
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) A[i][j] = sc.nextDouble();

        double[] B = new double[m * n];   // row vector (row-major)
        double[] C = new double[m * n];   // column vector (column-major)
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                B[i * n + j] = A[i][j];
                C[j * m + i] = A[i][j];
            }

        System.out.println("Matrix form:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) System.out.printf("%8.2f", A[i][j]);
            System.out.println();
        }
        System.out.println("Row vector   : " + java.util.Arrays.toString(B));
        System.out.println("Column vector: " + java.util.Arrays.toString(C));

        System.out.print("Element to locate (i j): ");
        int i = sc.nextInt(), j = sc.nextInt();
        System.out.println("A(" + i + "," + j + ") is B[" + (i * n + j) + "] and C[" + (j * m + i) + "]");
    }
}
