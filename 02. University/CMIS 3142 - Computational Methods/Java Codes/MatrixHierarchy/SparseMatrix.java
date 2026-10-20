package MatrixHierarchy;

import java.util.ArrayList;

// (iii) Sparse matrix: only the non-zero elements are stored, as (row, column, value) triples.
// Any element that is not stored is zero.
public class SparseMatrix extends Matrix {
    protected static class Entry {
        int row, col;
        double value;

        Entry(int row, int col, double value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    protected final ArrayList<Entry> entries = new ArrayList<>();

    public SparseMatrix(int m, int n) {
        super(m, n);
    }

    protected Entry find(int i, int j) {
        for (Entry e : entries)
            if (e.row == i && e.col == j) return e;
        return null;
    }

    @Override
    public double elementAt(int i, int j) {
        checkIndex(i, j);
        Entry e = find(i, j);
        return (e == null) ? 0.0 : e.value;
    }

    // zeros are never stored: setting an element to 0 removes it
    @Override
    public void setElement(int i, int j, double value) {
        checkIndex(i, j);
        Entry e = find(i, j);
        if (value == 0) {
            if (e != null) entries.remove(e);
        } else if (e != null) {
            e.value = value;
        } else {
            entries.add(new Entry(i, j, value));
        }
    }

    public int nonZeros() {
        return entries.size();
    }

    // Only the stored entries are touched: relabel their row/column numbers, O(non-zeros).
    @Override
    public Matrix swapRows(int r1, int r2) {
        checkRow(r1);
        checkRow(r2);
        for (Entry e : entries) {
            if (e.row == r1) e.row = r2;
            else if (e.row == r2) e.row = r1;
        }
        return this;
    }

    @Override
    public Matrix swapColumns(int c1, int c2) {
        checkColumn(c1);
        checkColumn(c2);
        for (Entry e : entries) {
            if (e.col == c1) e.col = c2;
            else if (e.col == c2) e.col = c1;
        }
        return this;
    }
}
