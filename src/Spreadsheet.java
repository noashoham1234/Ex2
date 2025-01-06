public class Spreadsheet {
    private firstCell[][] array;
    private int width;
    private int height;

    //constructor
    public Spreadsheet() {
        this.array = new firstCell[26][100]; // there is one row for the letters and one coulomb for the numbers
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

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public boolean checkValidCellName(String cn) {
        boolean ans = true;
        if (!Character.isLetter(cn.charAt(0)) && !Character.isDigit(cn.charAt(1)) && Integer.parseInt(cn.substring(1)) > 100 && cn.substring(1).matches("[A-Za-z0-9]")) {
            ans = false;
        }
        return ans;
    }

    public int xCell(String c) {
        int ans = -1;
        String bigL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String littleL = "abcdefghijklmnopqrstuvwxyz";
        if (checkValidCellName(c)) {
            char xCell = c.charAt(0);
            for (int i = 0; i < bigL.length(); i++) {
                if (bigL.charAt(i) == xCell) {
                    ans = i;
                }
            }
            for (int i = 0; i < littleL.length(); i++) {
                if (littleL.charAt(i) == xCell) {
                    ans = i;
                }
            }
        }
        return ans;
    }

    public int yCell(String c) {
        int ans = -1;
        if (checkValidCellName(c)) {
            ans = Integer.parseInt(c.substring(1));
        }
        return ans;
    }

    public String eval(int x, int y) {
        String ans = "";
//        this.array[x][y].computeForm();
        return ans;
    }

    public firstCell[][] evalAll() {

        firstCell[][] ans = new firstCell[width][height];
        return ans;
    }

    public int[] depth() {
        int[] ans = [];
        return ans;
    }
}
