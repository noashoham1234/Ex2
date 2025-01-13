import java.io.*;
import java.util.LinkedList;
import java.util.List;

// Add your documentation below:

public class Ex2Sheet implements Sheet {
    private Cell[][] table;
    private int count;

    public Ex2Sheet(int x, int y) {
        this.count = 0;
//        this.cellL = {[""]};
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
            if (c.getType() == Ex2Utils.FORM) { //לבדוק האם הוא פורמולה ואז אם כן לחשב ואם לא אז להחזיר את התוכן
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
        return cn.matches("^[A-Za-z]\\d{1,2}$"); //&& Integer.parseInt(cn.substring(1)) <= 99
    }

    public int letterToInt(char c) {
        int ans = -1;
        String[] littleL = new String[Ex2Utils.ABC.length];
        for (int i = 0; i < Ex2Utils.ABC.length; i++) {
            littleL[i] = Ex2Utils.ABC[i].toLowerCase();
            if (Ex2Utils.ABC[i].charAt(0) == c) {
                ans = i;
            }
        }

        for (int i = 0; i < littleL.length; i++) {
            if (littleL[i].charAt(0) == c) {
                ans = i;
            }
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
        int deep = 0;
        while(deep < width()*height()) {
            for (int i = 0; i < width(); i++) {
                for (int j = 0; j < height(); j++) {
                    if (dd[i][j] == deep) {
                        eval(i, j);
                    }
                }
            }
            deep++;
        }

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
                    }
                }
            }
            depth++;
        }

        return ans;
    }

    public boolean canBeComputedNow(int x, int y) {
        boolean ans = true;
        Cell c = get(x, y);
        if ((c.getType() == 3) && (containsVaildCellName(c.getData()))) {
            ans = false;
        }

        return ans;
    }

//    public boolean canBeComputedNow(String form, List<String> list, String cellName) {
////        List<String> list = this.cellL;
//        if (isNumber(form)) {
//            return true;
//        } else if (isText(form)) {
//            return false;
//        } else if (isForm(form)) {
//            list = containsVaildCellName(form);
//            List<String> temp = list;
//            for (int i = 0; i < list.size(); i++) {
//
//            }
//            canBeComputedNow(this.table[x][y].getData(), containsVaildCellName(form), String.valueOf(x, y));
//
//
//        }
//        return true;
//    }

//    public List<String> containsVaildCellName(String s) {
//        List<String> ans = null;
//
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = i + 2; j <= s.length(); j++) {
//                String sub = s.substring(i, j);
//
//                // If the substring matches the cell format, set ans to true
//                if (vaildCellName(sub)) {
//                    assert ans != null;
//                    ans.add(sub);
////                    break; // Found a valid cell, no need to continue checking
//                }
//
////                if (ans) {
//////                    break;
////                }
//
//            }
//        }
//        return ans;
//    }

    public boolean containsVaildCellName(String s) {
        boolean ans = false;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j++) {
                String sub = s.substring(i, j);

                // If the substring matches the cell format, set ans to true
                if (vaildCellName(sub)) {
                    ans= true;
                    break; // Found a valid cell, no need to continue checking
                }

                if (ans) {
                    break;
                }
            }
        }
        return ans;
    }

    @Override
    public void load(String fileName) throws IOException {
//        LinkedList< String[] > llist = new LinkedList < > ();
//
//        String[] data;
//
//        File temp = new File("file.txt");
//        BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
//
//        XSSFWorkbook workBook = new XSSFWorkbook();
//        FileOutputStream outstream = new FileOutputStream("data.xls");
//        XSSFSheet spreadSheet = workBook.createSheet("Clean");
//
//        for (int i = 0; i < llist.size(); i++) {
//            if (i == 0) {
//                bw.newLine();
//            } else {
//                data = llist.get(i);
//
//                String empid1 = data[0];
//                String fname = data[1];
//                String ccode1 = data[2];
//
//                if (data[2].equals("IND")) {
//                    replace = data[2].replaceAll("IND", "IN");
//                    ccode1 = replace;
//                } else if (data[2].equals("USA")) {
//                    replace = data[2].replaceAll("USA", "US");
//                    ccode1 = replace;
//                } else {
//                    ccode1 = data[2];
//                }
//
//                //String newData=empid1+","+fname+","+ccode1;
//
//                XSSFRow row = spreadSheet.createRow(i);
//
//                XSSFCell cell = row.createCell(0);
//                cell.setCellValue(empid1);
//
//                cell = row.createCell(1);
//                cell.setCellValue(fname);
//
//                cell = row.createCell(2);
//                cell.setCellValue(ccode1);
//            }
//        }
//
//        workBook.write(outstream);
//        bw.close();
        // Add your code here

        /////////////////////
    }

    @Override
    public void save(String fileName) throws IOException {
//        buffer reader
//                file reader

        int num = this.count;
        // locate the file
        File fileOne = new File("exelSheet" + num + ".txt");
        FileReader inputStream = new FileReader(fileOne);
        BufferedReader reader = new BufferedReader(inputStream);


        // create a LinkedList to hold the data read
        List<Integer> numbers = new LinkedList<Integer>();

        // prepare variables to refer to the temporary objects
        String line = null;
        int number = 0;

        // start reading
        do {
            // read each line
            line = reader.readLine();

            // check if the read data is not null, so not to use null values
            if (line != null) {
                number = Integer.parseInt(line);
                numbers.add(number * 10);
            }

        } while (line != null);

        // free resources
        reader.close();

        // check the new numbers before writing to file
        System.out.println("NEW NUMBERS IN MEMORY : " + numbers);

        // assign a printer
        PrintWriter writer = new PrintWriter(fileOne);

        // write down data
        for (int newNumber : numbers) {
            writer.println(newNumber);
        }
        this.count++;

        // free resources
        writer.flush();
        writer.close();

        // Add your code here

        /////////////////////
    }

    @Override
    public String eval(int x, int y) {
        String ans = null;
        Cell c = get(x, y);
        if (c != null) {
            if (c.getType() == Ex2Utils.FORM) {
                ans = String.valueOf(computeForm(c.getData()));
            } else {
                ans = c.toString();
            }
        }
        return ans;
    }

//    public boolean isNumber(String text) {
//        boolean ans = text.matches("-?\\d+(\\.\\d+)?"); //regex that checks if the string is a number it could be a decimal number or even a negative number.
//        return ans;
//    }
//
//    public boolean isText(String text) {
//        boolean ans = true;
//        if (text.substring(0, 1).equals("=")) {
//            ans = false;
//        }
//        return ans;
//    }

    public static boolean areSograimBalanced(String txt) {
        boolean ans = true;
        int count = 0;

        for (int i = 0; i < txt.length(); i++) {
            char current = txt.charAt(i);
            if (current == '(') {
                count++;
            } else if (current == ')') {
                count--;
                // If the parentheses are unbalanced, return false immediately
                if (count < 0) {
                    ans = false;
                }
            }
        }
        if (count == 0) {
            ans = true;  // If count is zero, the parentheses are balanced
        }
        return ans;
    }

    public static boolean isForm(String text) {
        boolean ans = true;
        if (text.isEmpty() || !text.startsWith("=")) {
            ans = false;  // The formula must start with '='
        }

        String expression = text.substring(1);
        // Allow only valid characters in the formula
        String validChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ+-./*()";
        if (!expression.matches("[" + validChars + "]+")) {
            ans = false;
        }

        if (!areSograimBalanced(expression)) {
            ans = false;  // Check for balanced parentheses
        }

        // Check the structure of the formula (basic validation)
        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);

            boolean isnextdigit = Character.isDigit(expression.charAt(i + 1));
            if (Character.isLetter(current) && i + 1 < expression.length() && isnextdigit) {
                ans = false;  // Invalid combination: letter followed by digit
            }

            if (current == '.' && i > 0 && i + 1 < expression.length()) {
                if (!Character.isDigit(expression.charAt(i - 1)) || !isnextdigit) {
                    ans = false;  // Invalid decimal point placement
                }
            }

            if ("+-*/".indexOf(current) != -1) {
                if (i + 1 < expression.length() && !(isnextdigit || Character.isLetter(expression.charAt(i + 1)))) {
                    ans = false;  // Invalid operator placement
                }
            }
        }

        return ans;  // If all checks pass, the formula is valid
    }

    public static double computeForm(String form) {
        double ans = -1.0;
        if(isForm(form)) {
            // Remove '=' from the beginning
            if (form.startsWith("=")) {
                form = form.substring(1);
            }
            //Remove all the spaces
            String newForm = form.replace(" ", "");
            ans = compute(newForm);
        }
        return ans;
    }

    private static double compute(String str) {
        // Go over all the parentheses first
        while (str.contains("(")) {
            int startIdx = str.lastIndexOf("(");
            int endIdx = str.indexOf(")", startIdx);
            String subStr = str.substring(startIdx + 1, endIdx);
            double result = compute(subStr);
            str = str.substring(0, startIdx) + result + str.substring(endIdx + 1);
        }

        // Sends to the math operators to calculate
        return calculate(str);
    }

    private  static double calculate(String str) {
        // Starting with multiply and divide then add and subtract
        str = calculateOperator(str, "*");
        str = calculateOperator(str, "/");
        str = calculateOperator(str, "+");
        str = calculateOperator(str, "-");

        return Double.parseDouble(str);
    }

    private static String calculateOperator(String str, String operator) {
        int pos = str.indexOf(operator);
        while (pos != -1) {
            int leftPos = findLeftOperand(str, pos);
            int rightPos = findRightOperand(str, pos);
            String leftOperand = str.substring(leftPos, pos).trim();
            String rightOperand = str.substring(pos + 1, rightPos).trim();

            double leftValue = Double.parseDouble(leftOperand);
            double rightValue = Double.parseDouble(rightOperand);

            double result = 0;
            switch (operator) {
                case "+":
                    result = leftValue + rightValue;
                    break;
                case "-":
                    result = leftValue - rightValue;
                    break;
                case "*":
                    result = leftValue * rightValue;
                    break;
                case "/":
                    if (rightValue == 0) throw new ArithmeticException("Division by zero");
                    result = leftValue / rightValue;
                    break;
            }

            str = str.substring(0, leftPos) + result + str.substring(rightPos);
            pos = str.indexOf(operator);
        }
        return str;
    }

    private static int findLeftOperand(String s, int operatorPos) {
        int leftPos = operatorPos - 1;
        while (leftPos >= 0 && (Character.isDigit(s.charAt(leftPos)) || s.charAt(leftPos) == '.')) {
            leftPos--;
        }
        return leftPos + 1;
    }

    private  static int findRightOperand(String s, int operatorPos) {
        int rightPos = operatorPos + 1;
        while (rightPos < s.length() && (Character.isDigit(s.charAt(rightPos)) || s.charAt(rightPos) == '.')) {
            rightPos++;
        }
        return rightPos;
    }


}
