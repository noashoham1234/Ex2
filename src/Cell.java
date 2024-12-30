public class Cell {
    private String data;

    public Cell() {
        this.data = "";
    }

    public Cell(String data) {
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public boolean isNumber(String text) {
        boolean ans = text.matches("-?\\d+(\\.\\d+)?"); //regex that checks if the string is a number it could be a decimal number or even a negative number.
        return ans;
    }

    public boolean isText(String text) {
        boolean ans = true;
        if (text.substring(0, 1).equals("=")) {
            ans = false;
        }
        return ans;
    }

    public boolean isForm(String text) {
        boolean ans = false;
        if (text.substring(0, 1).equals("=")) {
            ans = true;
        }
        return ans;
    }

    public double computeForm(String form) {
        double ans = 0.0;
        //חישוב של כל התרגילים בסטרינג
        return ans;
    }

    public String toString() {
        return "[" + data + "]";
    }
}
