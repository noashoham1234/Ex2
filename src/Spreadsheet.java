public class Spreadsheet {
    private Cell[][] array;

    public Spreadsheet() {
        this.array = new Cell[100][23]; // there is one row for the letters and one coulomb for the numbers
    }

    public Spreadsheet(int x, int y) {
        this.array = new Cell[x][y];
    }

    public Cell getCell(int x, int y) {
        Cell ans = this.array[x][y];
        return ans;
    }

    public void setCell(int x, int y, Cell c) {
        this.array[x][y] = c;
    }

    public int width() {
        int ans = 0;
        return ans;

    }

    public int height() {
        int ans = 0;
        return ans;
    }

    public int xCell(Cell c) {
        int ans = 0;
        return ans;
    }

    public int yCell(Cell c) {
        int ans = 0;
        return ans;
    }

    public String eval(int x, int y) {
        String ans = "";
        return ans;
    }

//    public Cell[][] evalAll() {
//        Cell[][] ans = [""][""];
//        return ans;
//    }
//
//    public int[] depth() {
//        int[] ans = [];
//        return ans;
//    }
}
