public class firstCell {
    private String data;

    public firstCell() {
        this.data = "";
    }

    public firstCell(String data) {
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
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

    public boolean areSograimBalanced(String txt) {
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

    public boolean isForm(String text) {
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
        // Remove '=' from the beginning
        if (form.startsWith("=")) {
            form = form.substring(1);
        }
        //Remove all the spaces
        String newForm = form.replace(" ", "");
        ans = compute(newForm);
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

    private static double calculate(String str) {
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

    private static int findRightOperand(String s, int operatorPos) {
        int rightPos = operatorPos + 1;
        while (rightPos < s.length() && (Character.isDigit(s.charAt(rightPos)) || s.charAt(rightPos) == '.')) {
            rightPos++;
        }
        return rightPos;
    }


    public String toString() {
        return "[" + data + "]";
    }
}