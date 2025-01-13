//package assignments.ex2;
// Add your documentation below:

public class CellEntry implements Index2D {
    private int x;
    private int y;

    public CellEntry() {
        this.x = 0;
        this.y = 0;
    }

    public CellEntry(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isValid() {
        return x >= 0 && y >= 0 && x < Ex2Utils.WIDTH && y < Ex2Utils.HEIGHT;
//        return cellName.matches("^[A-Za-z]\\d{1,2}$"); //&& Integer.parseInt(cn.substring(1)) <= 99
    }

    @Override
    public int getX() {
        if (this.x == 0) {
            return Ex2Utils.ERR;
        } else {
            return this.x;
        }
    }

    @Override
    public int getY() {
        if (this.y == 0) {
            return Ex2Utils.ERR;
        } else {
            return this.y;
        }
    }

    public String toString() {
        return Ex2Utils.ABC[x] + (y + 1); // y + 1 as the row is 1-indexed
    }
}
