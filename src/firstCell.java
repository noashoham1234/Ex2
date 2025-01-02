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

    public boolean areParenthesesBalanced(String txt) {
        int count = 0;

        for (int i = 0; i < txt.length(); i++) {
            char current = txt.charAt(i);
            if (current == '(') {
                count++;
            } else if (current == ')') {
                count--;
                // If the parentheses are unbalanced, return false immediately
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;  // If count is zero, the parentheses are balanced
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

        if (!areParenthesesBalanced(expression)) {
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


//    public boolean sograimAreGood (String txt){
//        boolean ans = false;
//        int count = 0;
//
//        for (int i = 0; i < txt.length(); i++) {
//            Character current = txt.charAt(i);
//            //checks if the number of ( and ) are equal
//            if (current == '(') {
//                count++;
//            } else if (current == ')') {
//                count--;
//            }
//        }
//        if (count == 0){
//            return true;
//        }
//        return ans;
//    }
//
//    public boolean isForm(String text) {
//        boolean ans = false;
//        String correctChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ+-./*()";
//        if (text.substring(0, 1).equals("=")) {
//            String newText = text.substring(1);
//            if (newText.matches(correctChars)) {
//                for (int i = 0; i < newText.length(); i++) {
//                    Character current = newText.charAt(i);
//
//                    if (sograimAreGood(newText)) {
//                        if (Character.isLetter(current) && Character.isDigit(newText.charAt(i + 1))) {
//                            if (current == '.' && Character.isDigit(newText.charAt(i - 1)) && Character.isDigit(newText.charAt(i + 1))) {
//                                if ((current == '+' || current == '-' || current == '*' || current == '/') && (Character.isDigit(newText.charAt(i + 1)) || Character.isLetter(newText.charAt(i + 1)))) {
//                                    ans = true;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return ans;
//    }

    public String computeForm(String form) {
        String ans = "";
        if (isNumber(form) || isText(form)) {
            ans = form;
        }
        if (!isForm(form)) {
            ans = "ERR_FORM";
        } else {

        }

        return ans;

    }

    public String toString() {
        return "[" + data + "]";
    }
}