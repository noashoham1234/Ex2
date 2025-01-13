// Add your documentation below:

public class SCell implements Cell {
    private String line;
    private int type;
    private int order;

    public SCell(String s) {
        this.order = 0;
        setData(s);
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    //@Override
    @Override
    public String toString() {
        return getData();
    }

    @Override
    public void setData(String s) {
        this.line = s;
        this.type = determineType(s);
    }

    @Override
    public String getData() {
        return this.line;
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public void setType(int t) {
        this.type = t;
    }

    @Override
    public void setOrder(int t) {
        this.order = t;
    }

    private int determineType(String s) {
        if (s.startsWith("=")) return Ex2Utils.FORM;
        try {
            Double.parseDouble(s);
            return Ex2Utils.NUMBER;
        } catch (NumberFormatException e) {
            return Ex2Utils.TEXT;
        }
    }


    public static boolean isNumber(String text) {
        return text.matches("-?\\d+(\\.\\d+)?"); //regex that checks if the string is a number it could be a decimal number or even a negative number.
    }

    public static boolean isText(String text) {
        boolean ans = true;
        if (text.isEmpty()) {
            ans = false;
        } else if (text.equals(" ")) {
            return false;
        } else if (text.charAt(0) == '=') {
            ans = false;
        }

        return ans;
    }

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
//        String text = this.line;
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

    public double computeForm() {
        String form = this.line;

        double ans = -1.0;
        if (isForm(form)) {
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

    private double compute(String str) {
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

    private double calculate(String str) {
        // Starting with multiply and divide then add and subtract
        str = calculateOperator(str, "*");
        str = calculateOperator(str, "/");
        str = calculateOperator(str, "+");
        str = calculateOperator(str, "-");

        return Double.parseDouble(str);
    }

    private String calculateOperator(String str, String operator) {
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

    private int findLeftOperand(String s, int operatorPos) {
        int leftPos = operatorPos - 1;
        while (leftPos >= 0 && (Character.isDigit(s.charAt(leftPos)) || s.charAt(leftPos) == '.')) {
            leftPos--;
        }
        return leftPos + 1;
    }

    private int findRightOperand(String s, int operatorPos) {
        int rightPos = operatorPos + 1;
        while (rightPos < s.length() && (Character.isDigit(s.charAt(rightPos)) || s.charAt(rightPos) == '.')) {
            rightPos++;
        }
        return rightPos;
    }
}
