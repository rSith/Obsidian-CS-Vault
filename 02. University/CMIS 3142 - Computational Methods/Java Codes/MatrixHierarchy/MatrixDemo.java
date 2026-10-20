package MatrixHierarchy;

public class MatrixDemo {
    public static void main(String[] args) {
        // Polymorphism: every object is used through a Matrix reference
        Matrix F = new FullMatrix(new double[][] {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        });

        Matrix S = new SymmetricMatrix(3);
        S.setElement(0, 0, 3); S.setElement(1, 0, 4); S.setElement(1, 1, 15);
        S.setElement(2, 0, 5); S.setElement(2, 1, 7); S.setElement(2, 2, 3);

        Matrix SP = new SparseMatrix(3, 3);
        SP.setElement(0, 2, 6); SP.setElement(2, 0, 1);

        Matrix SS = new SparseSymmetricMatrix(3);
        SS.setElement(0, 0, 2); SS.setElement(2, 1, 1);    // also gives A[1][2] = 1

        Matrix R = new RowVector(3), C = new ColumnVector(3);
        for (int k = 0; k < 3; k++) {
            R.setElement(0, k, k + 1);                    // [1 2 3]
            C.setElement(k, 0, k + 4);                    // [4 5 6]^T
        }

        Matrix[] all = { F, S, SP, SS, R, C };
        for (Matrix M : all) {
            System.out.println(M.getClass().getSimpleName() + " (" + M.rowSize() + "x" + M.columnSize() + "):");
            M.display();
        }

        // one add/sub/multiply method, mixing different storage types
        show("F + S",   F.add(S));
        show("S - SS",  S.sub(SS));
        show("SP + SS", SP.add(SS));
        show("S * F",   S.multiply(F));
        show("SS * SP", SS.multiply(SP));
        show("2 * S",   S.multiply(2));
        show("R * C (inner product, 1x1)", R.multiply(C));
        show("C * R (outer product, 3x3)", C.multiply(R));
        show("F * C",   F.multiply(C));

        // swaps: each class uses its own version (dynamic binding)
        show("F after swapRows(0, 2)",     F.swapRows(0, 2));
        show("SP after swapColumns(0, 2)", SP.swapColumns(0, 2));
        show("R after swapColumns(0, 2)",  R.swapColumns(0, 2));
        show("C after swapRows(0, 1)",     C.swapRows(0, 1));

        Matrix S2 = S.swapRows(0, 1);
        show("S.swapRows(0, 1) -> " + S2.getClass().getSimpleName(), S2);
        Matrix SS2 = SS.swapColumns(0, 1);
        show("SS.swapColumns(0, 1) -> " + SS2.getClass().getSimpleName(), SS2);

        System.out.println("SparseSymmetricMatrix stores " + ((SparseMatrix) SS).nonZeros()
                + " values for " + SS.rowSize() * SS.columnSize() + " elements");

        try {
            F.add(R);
        } catch (IllegalArgumentException e) {
            System.out.println("F + R: " + e.getMessage());
        }
    }

    private static void show(String label, Matrix M) {
        System.out.println(label + ":");
        M.display();
    }
}
