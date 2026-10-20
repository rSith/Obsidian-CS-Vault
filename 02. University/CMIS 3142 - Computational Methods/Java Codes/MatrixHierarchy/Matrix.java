package MatrixHierarchy;

// Abstract base class: every matrix type extends this and decides HOW its elements are stored.
// The operations are written ONCE here in terms of elementAt / setElement (polymorphism),
// and subclasses override them only when they can do better or must behave differently.
public abstract class Matrix {
    protected int rows;
    protected int columns;

    protected Matrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive");
        }
        this.rows = rows;
        this.columns = columns;
    }

    public int rowSize()    { return rows; }
    public int columnSize() { return columns; }

    // ---------- storage-specific: each subclass must implement ----------
    public abstract double elementAt(int i, int j);
    public abstract void setElement(int i, int j, double value);

    // ---------- operations inherited by every subclass ----------

    // The sum, difference or product of two special matrices does not keep the special
    // structure in general (e.g. symmetric x symmetric is not symmetric), so results are full.
    public Matrix add(Matrix B) {
        requireSameSize(B);
        FullMatrix C = new FullMatrix(rows, columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                C.setElement(i, j, elementAt(i, j) + B.elementAt(i, j));
        return C;
    }

    public Matrix sub(Matrix B) {
        requireSameSize(B);
        FullMatrix C = new FullMatrix(rows, columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                C.setElement(i, j, elementAt(i, j) - B.elementAt(i, j));
        return C;
    }

    public Matrix multiply(Matrix B) {
        if (columns != B.rows) {
            throw new IllegalArgumentException("Columns of A (" + columns + ") must equal rows of B (" + B.rows + ")");
        }
        FullMatrix C = new FullMatrix(rows, B.columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < B.columns; j++) {
                double sum = 0;
                for (int k = 0; k < columns; k++) sum += elementAt(i, k) * B.elementAt(k, j);
                C.setElement(i, j, sum);
            }
        return C;
    }

    public Matrix multiply(double k) {
        FullMatrix C = new FullMatrix(rows, columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                C.setElement(i, j, k * elementAt(i, j));
        return C;
    }

    // Swaps return the matrix that holds the result: normally this matrix (swapped in place),
    // but a symmetric matrix cannot store a row-swapped matrix, so it returns a new, more
    // general matrix instead. Always use it as:  A = A.swapRows(i, j);
    public Matrix swapRows(int r1, int r2) {
        checkRow(r1);
        checkRow(r2);
        for (int j = 0; j < columns; j++) {
            double t = elementAt(r1, j);
            setElement(r1, j, elementAt(r2, j));
            setElement(r2, j, t);
        }
        return this;
    }

    public Matrix swapColumns(int c1, int c2) {
        checkColumn(c1);
        checkColumn(c2);
        for (int i = 0; i < rows; i++) {
            double t = elementAt(i, c1);
            setElement(i, c1, elementAt(i, c2));
            setElement(i, c2, t);
        }
        return this;
    }

    public FullMatrix toFull() {
        FullMatrix F = new FullMatrix(rows, columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                F.setElement(i, j, elementAt(i, j));
        return F;
    }

    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) System.out.printf("%8.2f", elementAt(i, j));
            System.out.println();
        }
    }

    // ---------- helpers ----------
    protected void requireSameSize(Matrix B) {
        if (rows != B.rows || columns != B.columns) {
            throw new IllegalArgumentException("Size mismatch: " + rows + "x" + columns + " vs " + B.rows + "x" + B.columns);
        }
    }

    protected void checkRow(int i) {
        if (i < 0 || i >= rows) throw new IndexOutOfBoundsException("Row " + i + " out of range 0.." + (rows - 1));
    }

    protected void checkColumn(int j) {
        if (j < 0 || j >= columns) throw new IndexOutOfBoundsException("Column " + j + " out of range 0.." + (columns - 1));
    }

    protected void checkIndex(int i, int j) {
        checkRow(i);
        checkColumn(j);
    }
}
