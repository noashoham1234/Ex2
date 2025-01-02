public class Spreadsheet {
    private firstCell[][] array;
    private int width;
    private int height;

    //constructor
    public Spreadsheet() {
        this.array = new firstCell[100][23]; // there is one row for the letters and one coulomb for the numbers
    }

    //copy constructor
    public Spreadsheet(int x, int y) {
        this.array = new firstCell[x][y];
    }

    public firstCell getCell(int x, int y) {
        firstCell ans = this.array[x][y];
        return ans;
    }

    public void setCell(int x, int y, firstCell c) {
        this.array[x][y] = c;
    }

    public int xCell(firstCell c) {
        int ans = 0;
        return ans;
    }

    public int yCell(firstCell c) {
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
