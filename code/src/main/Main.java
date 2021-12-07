package main;

import java.util.*;

class main {
    public static boolean isValid(String expression) throws Exception {
        char a, b;
        Stack<Character> h = new Stack();
        a = expression.charAt(0);
        if (a == '+' || a == '*' || a == '/' || a == '^')
            return false;
        a = expression.charAt(expression.length() - 1);
        if (a == '+' || a == '-' || a == '*' || a == '/' || a == '^')
            return false;
        for (int i = 0; i < expression.length() - 1; i++) {
            a = expression.charAt(i);
            b = expression.charAt(i + 1);
            if ((a == '+' || a == '-' || a == '*' || a == '/' || a == '^') && (b == '+' || b == '-' || b == '*' || b == '/' || b == '^'))
                return false;
        }
        for (int i = 0; i < expression.length() - 1; i++) {
            a = expression.charAt(i);
            b = expression.charAt(i + 1);
            if (((a == '+' || a == '-' || a == '*' || a == '/' || a == '^') && b == ')') || ((b == '+' || b == '*' || b == '/' || b == '^') && a == '('))
                return false;
        }
        for (int i = 0; i < expression.length(); i++) {
            a = expression.charAt(i);
            if (a == '(')
                h.push(a);
            else if (a == ')' && h.peek() == '(')
                h.pop();
        }
        if (!h.isEmpty())
            return false;
        for (int i = 0; i < expression.length() - 1; i++) {
            a = expression.charAt(i);
            b = expression.charAt(i + 1);
            if ((a >= '0' && a <= '9') && (b == '(') || (b >= '0' && b <= '9') && (a == ')'))
                return false;
        }
        for (int i = 0; i < expression.length() - 1; i++) {
            a = expression.charAt(i);
            b = expression.charAt(i + 1);
            if (a == '(' && b == ')')
                return false;
        }
        return true;
    }

    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if (op1 == '^' && (op2 == '*' || op2 == '/'))
            return false;
        if (op1 == '^' && (op2 == '^'))
            return false;
        if (op1 == '^' && (op2 == '+' || op2 == '-'))
            return false;
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    static String make(String exp) throws Exception {
        char[] tokens = exp.toCharArray();
        StringBuffer st = new StringBuffer();
        if (!isValid(exp))
            throw new Exception();
        for (int i = 0; i < tokens.length; i++) {

            if (i == 0 && tokens[i] == '-' && tokens[i + 1] == '(') {
                st.append("(-1)*");
            } else if (tokens[i] == '-' && tokens[i + 1] == '(' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9') && tokens[i - 1] != ')') {
                st.append("(-1)*");
            } else if (i == 0 && tokens[i] == '-' && tokens[i + 1] >= '0' && tokens[i + 1] <= '9' && tokens[i + 2] == '^') {
                st.append("(-1)*");
            } else if (tokens[i] == '-' && tokens[i + 1] >= '0' && tokens[i + 1] <= '9' && tokens[i + 2] == '^' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9')) {
                st.append("(-1)*");
            } else if (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.' || (tokens[i] == '-' && i == 0) || (tokens[i] == '-' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9') && (tokens[i + 1] >= '0' && tokens[i + 1] <= '9') && tokens[i - 1] != ')')) {
                StringBuffer sbuf = new StringBuffer();

                while (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.' || (tokens[i] == '-' && i == 0) || (tokens[i] == '-' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9') && (tokens[i + 1] >= '0' && tokens[i + 1] <= '9') && tokens[i - 1] != ')')) {
                    sbuf.append(tokens[i++]);
                    if (i == tokens.length)
                        break;
                }
                st.append(sbuf);
                i--;
            } else if (tokens[i] == '(')
                st.append("(");

            else if (tokens[i] == ')') {
                st.append(")");
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '^' || tokens[i] == '/') {
                st.append(tokens[i]);
            }
        }
        return String.valueOf(st);
    }

    static String infixToPostfix(String exp) {

        String result = "";
        char[] tokens = exp.toCharArray();

        java.util.Stack<Character> stack = new java.util.Stack<>();

        for (int i = 0; i < exp.length(); ++i) {

            if (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.' || (tokens[i] == '-' && i == 0) || (tokens[i] == '-' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9') && (tokens[i + 1] >= '0' && tokens[i + 1] <= '9') && tokens[i - 1] != ')')) {
                StringBuffer sbuf = new StringBuffer();

                while (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.' || (tokens[i] == '-' && i == 0) || (tokens[i] == '-' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9') && (tokens[i + 1] >= '0' && tokens[i + 1] <= '9') && tokens[i - 1] != ')')) {
                    sbuf.append(tokens[i++]);
                    if (i == tokens.length)
                        break;
                }
                result += sbuf;
                result += " ";
                i--;
            }

            else if (tokens[i] == '(')
                stack.push(tokens[i]);

            else if (tokens[i] == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                    result += " ";
                }
                stack.pop();
            } else
            {
                while (!stack.isEmpty() && hasPrecedence(tokens[i],stack.peek())) {

                    result += stack.pop();
                    result += " ";
                }
                stack.push(tokens[i]);
            }

        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                return "Invalid Expression";
            result += stack.pop();
            result += " ";
        }
        return result;
    }

    public static double evaluatePostfix(String exp) throws Exception {
        Stack<Object> stack = new Stack<>();
        char[] tokens = exp.toCharArray();
        for (int i = 0; i < exp.length(); i++) {

            StringBuffer result = new StringBuffer();

            if (tokens[i]  >= '0' && tokens[i] <= '9' || tokens[i] == '.' || (tokens[i] == '-' && i == 0) || (tokens[i] == '-' && !(tokens[i-1] >= '0' && tokens[i-1] <= '9') && (tokens[i+1] >= '0' && tokens[i+1] <= '9'))) {
                while (tokens[i] != ' ')
                    result.append(tokens[i++]);
                stack.push(Double.parseDouble(String.valueOf(result)));
                i--;
            } else if(tokens[i]!=' ') {
                double val1 = (double) stack.pop();
                double val2 = (double) stack.pop();

                double r = 0;
                switch (tokens[i]) {
                    case '+' -> r = val1 + val2;
                    case '-' -> r = val2 - val1;
                    case '*' -> r = val1 * val2;
                    case '^' -> r = Math.pow(val2, val1);
                    case '/' -> {
                        if (val1 == 0)
                            throw new
                                    UnsupportedOperationException();
                        r = val2 / val1;
                    }
                }
                System.out.println("(" + val2 + " " + tokens[i] + " " + val1 + " = " + r + ")");
                stack.push(r);
            }
        }
        return (double) stack.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            String a =main.infixToPostfix(main.make(sc.next()));
            System.out.println("Answer is: " + main.evaluatePostfix(a));
        } catch (Exception a) {
            System.out.println("ERROR");
        }


    }
}