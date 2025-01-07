import java.io.IOException;

// Add your documentation below:

public class Ex2Sheet implements Sheet {
    private Cell[][] table;

    // Add your code Here
    ////////////////

    public Ex2Sheet(int x, int y) {
        table = new SCell[x][y];
        for (int i = 0; i < x; i = i + 1) {
            for (int j = 0; j < y; j = j + 1) {
                table[i][j] = new SCell(Ex2Utils.EMPTY_CELL);
            }
        }
        eval();
    }

    public Ex2Sheet() {
        this(Ex2Utils.WIDTH, Ex2Utils.HEIGHT);
    }

    @Override
    public String value(int x, int y) {
        String ans = Ex2Utils.EMPTY_CELL;

        Cell c = get(x, y);
        if (c != null) {
            if (c.getType() == 3) { //לבדוק האם הוא פורמולה ואז אם כן לחשב ואם לא אז להחזיר את התוכן
                eval();
            } else {
                ans = c.toString();
            }
        }
        return ans;
    }

    @Override
    public Cell get(int x, int y) {
        return table[x][y];
    }

    public boolean vaildCellName(String cn) {
        boolean ans = cn.matches("^[A-Za-z]\\d{1,2}$"); //&& Integer.parseInt(cn.substring(1)) <= 99
        return ans;
    }

    public int letterToInt(char c) {
        int ans = -1;
        String bigL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String littleL = bigL.toLowerCase();
        if (bigL.indexOf(c) == -1) {
            ans = littleL.indexOf(c);
        } else {
            ans = bigL.indexOf(c);
        }
        return ans;
    }

    @Override
    public Cell get(String cords) {
        Cell ans = null;
        if (vaildCellName(cords)) {
            char xCell = cords.charAt(0);
            int wI = letterToInt(xCell);
            int hI = Integer.parseInt(cords.substring(1));
            ans = get(wI, hI);
        }
        return ans;
    }

    @Override
    public int width() {
        return table.length;
    }

    @Override
    public int height() {
        return table[0].length;
    }

    @Override
    public void set(int x, int y, String s) {
        if (isIn(x, y)) {
            Cell c = new SCell(s);
            table[x][y] = c;
        }
    }

    @Override
    public void eval() {
        int[][] dd = depth();
        // Add your code here

        // ///////////////////
    }

    @Override
    public boolean isIn(int xx, int yy) {
        boolean ans = (yy < 100 && yy >= 0) && (xx >= 0 && xx < 26);
        return ans;
    }

    @Override
    public int[][] depth() {
        int[][] ans = new int[width()][height()];
        //initiate all the places with -1
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                ans[i][j] = -1;
            }
        }

        int depth = 0;
        int count = 0;
        int max = width() * height();
        boolean flagC = true;
        while (count < max && flagC) {
            flagC = false;
            for (int x = 0; x < width(); x++) {
                for (int y = 0; y < height(); y++) {
                    if (canBeComputedNow(x, y)) { // DIY
                        ans[x][y] = depth;
                        count++;
                        flagC = true;
                    } // if
                } // for yt
            }  // for x
            depth++;
        } //while

        return ans;
    }

    public boolean canBeComputedNow(int x, int y) {
        boolean ans = true;
        Cell c = get(x, y);
        if (c.getType() == 3 && containsVaildCellName(c.getData())) {
            ans = false;
        }

        return ans;
    }

    public boolean containsVaildCellName(String s) {
        boolean ans = false;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j++) {
                String sub = s.substring(i, j);

                // If the substring matches the cell format, set ans to true
                if (vaildCellName(sub)) {
                    ans = true;
                    break; // Found a valid cell, no need to continue checking
                }

                if (ans)
                    break;
            }
        }
        return ans;
    }

    @Override
    public void load(String fileName) throws IOException {
        // Add your code here

        /////////////////////
    }

    @Override
    public void save(String fileName) throws IOException {
//        buffer reader
//                file reader
        // Add your code here

        /////////////////////
    }

    @Override
    public String eval(int x, int y) {
        String ans = null;
        if (get(x, y) != null) {
            ans = get(x, y).toString();
        }
        // Add your code here

        /////////////////////
        return ans;
    }
}
