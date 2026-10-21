import java.util.Scanner;

/*
 * Sparse symmetric matrix (n x n).
 * Because A[i][j] = A[j][i], only the LOWER triangle (j <= i) is stored,
 * and only its NON-ZERO elements, row by row.
 * The diagonal element of every row is always stored and is the LAST
 * element of that row.  Indices are 0-based (Java style).
 */
public class SparseSymmetricMatrix {

    private int      n;          // size of the matrix (n x n)
    private double[] a;          // stored element values
    private int[]    columnNums; // column number of each stored element
    private int[]    diagonal;   // diagonal[i] = position of A[i][i] in a[]
    private int      nElements;  // number of stored elements

    // ---- Constructor 1: empty matrix of size n (capacity = max elements) ----
    public SparseSymmetricMatrix(int n, int capacity) {
        this.n = n;
        a          = new double[capacity];
        columnNums = new int[capacity];
        diagonal   = new int[n];
        nElements  = 0;
    }

    // ---- Constructor 2: build from a full 2-D array (uses lower triangle) ----
    public SparseSymmetricMatrix(double[][] full) {
        this(full.length, full.length * (full.length + 1) / 2);
        for (int i = 0; i < n; i++)
            for (int j = 0; j <= i; j++)
                store(i, j, full[i][j]);
    }

    // store one element; must be called row by row, columns in increasing order
    private void store(int i, int j, double value) {
        if (value != 0 || i == j) {          // keep non-zeros + every diagonal
            a[nElements]          = value;
            columnNums[nElements] = j;
            if (i == j) diagonal[i] = nElements;
            nElements++;
        }
    }

    // ---- Read the lower triangle from the keyboard ----
    public static SparseSymmetricMatrix read(Scanner sc) {
        System.out.print("Enter size n: ");
        int n = sc.nextInt();
        SparseSymmetricMatrix m = new SparseSymmetricMatrix(n, n * (n + 1) / 2);
        System.out.println("Enter lower triangle row by row (j <= i):");
        for (int i = 0; i < n; i++)
            for (int j = 0; j <= i; j++)
                m.store(i, j, sc.nextDouble());
        return m;
    }

    // ---- Return element at row i, column j ----
    public double elementAt(int i, int j) {
        if (j > i) { int t = i; i = j; j = t; }       // symmetry: A[i][j] = A[j][i]
        int start = (i == 0) ? 0 : diagonal[i - 1] + 1; // first element of row i
        int end   = diagonal[i];                        // last element of row i
        for (int k = start; k <= end; k++)
            if (columnNums[k] == j) return a[k];
        return 0.0;                                     // not stored -> zero
    }

    // ---- (a) Add two sparse symmetric matrices ----
    public SparseSymmetricMatrix add(SparseSymmetricMatrix B) {
        if (this.n != B.n)
            throw new IllegalArgumentException("Matrices must be the same size");
        SparseSymmetricMatrix C =
            new SparseSymmetricMatrix(n, this.nElements + B.nElements);
        for (int i = 0; i < n; i++)
            for (int j = 0; j <= i; j++)
                C.store(i, j, this.elementAt(i, j) + B.elementAt(i, j));
        return C;                   // sum of two symmetric matrices is symmetric
    }

    // ---- (b) Display in matrix form (including zeros) ----
    public void display() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.printf("%8.2f", elementAt(i, j));
            System.out.println();
        }
    }

    // ---- (c) Display non-zero elements with row and column numbers ----
    public void displayNonZero() {
        System.out.println("Row\tColumn\tValue");
        for (int i = 0; i < n; i++) {
            int start = (i == 0) ? 0 : diagonal[i - 1] + 1;
            for (int k = start; k <= diagonal[i]; k++) {
                int j = columnNums[k];
                if (a[k] == 0) continue;                  // skip a zero diagonal
                System.out.println(i + "\t" + j + "\t" + a[k]);
                if (i != j)                               // mirror element
                    System.out.println(j + "\t" + i + "\t" + a[k]);
            }
        }
    }

    public int getSize()         { return n; }
    public int getNumElements()  { return nElements; }

    // ---- Test ----
    public static void main(String[] args) {
        double[][] p = { {5, 0, 0, 3},
                         {0, 8, 0, 0},
                         {0, 0, 0, 2},
                         {3, 0, 2, 6} };
        double[][] q = { {1, 0, 4, 0},
                         {0,-8, 0, 0},
                         {4, 0, 7, 0},
                         {0, 0, 0, 1} };
        SparseSymmetricMatrix A = new SparseSymmetricMatrix(p);
        SparseSymmetricMatrix B = new SparseSymmetricMatrix(q);
        SparseSymmetricMatrix C = A.add(B);

        System.out.println("Matrix A:");   A.display();
        System.out.println("Matrix B:");   B.display();
        System.out.println("C = A + B:");  C.display();
        System.out.println("Non-zero elements of C:");
        C.displayNonZero();
    }
}
