public class Main {
    public static void main(String[] args) {
        FullMatrix fm = new FullMatrix(2, 2);
        fm.set(0, 0, 1);
        fm.set(0, 1, 2);
        fm.set(1, 0, 3);
        fm.set(1, 1, 4);

        System.out.println("Full Matrix:");
        fm.display();

        System.out.println("\nAs a Row Vector:");
        RowVector rv = fm.toRowVector();
        rv.display();

        System.out.println("\nAs a Column Vector:");
        fm.toColumnVector().display();

        System.out.println("\nRow Vector back to a 2 x 2 Matrix:");
        rv.toFullMatrix(2, 2).display();
    }
}
