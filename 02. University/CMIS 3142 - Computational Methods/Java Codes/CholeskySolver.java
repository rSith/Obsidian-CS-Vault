import java.util.Scanner;

public class CholeskySolver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of matrix (n): ");
        int n = sc.nextInt();

        double[][] A = new double[n][n];
        double[] B = new double[n];

        System.out.println("Enter matrix A (symmetric & positive definite):");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = sc.nextDouble();

        System.out.println("Enter vector B:");
        for (int i = 0; i < n; i++)
            B[i] = sc.nextDouble();

        double[][] L = choleskyDecomposition(A, n);

        double[] y = forwardSubstitution(L, B, n);
        double[] x = backwardSubstitution(L, y, n);

        System.out.println("Solution vector x:");
        for (int i = 0; i < n; i++)
            System.out.printf("x[%d] = %.6f\n", i, x[i]);

        sc.close();
    }

    // Cholesky Decomposition
    public static double[][] choleskyDecomposition(double[][] A, int n) {
        double[][] L = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                double sum = 0;
                for (int k = 0; k < j; k++)
                    sum += L[i][k] * L[j][k];

                if (i == j)
                    L[i][j] = Math.sqrt(A[i][i] - sum);
                else
                    L[i][j] = (A[i][j] - sum) / L[j][j];
            }
        }
        return L;
    }5

    // Forward substitution: L * y = B
    public static double[] forwardSubstitution(double[][] L, double[] B, int n) {
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++)
                sum += L[i][j] * y[j];
            y[i] = (B[i] - sum) / L[i][i];
        }
        return y;
    }

    // Backward substitution: L^T * x = y
    public static double[] backwardSubstitution(double[][] L, double[] y, int n) {
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++)
                sum += L[j][i] * x[j];
            x[i] = (y[i] - sum) / L[i][i];
        }
        return x;
    }
}
