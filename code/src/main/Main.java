package main;

import java.util.ArrayList;
import java.util.Scanner;

class main {
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

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
            else if (Character.isLetter(tokens[i])) {
                StringBuffer sbuf = new StringBuffer();
                while (Character.isLetter(tokens[i])) {
                    sbuf.append(tokens[i++]);
                    if (i==tokens.length)
                        break;
                }
                switch (String.valueOf(sbuf)) {
                    case "sin":
                        sbuf = new StringBuffer();
                        while (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.' || (tokens[i] == '-' && i == 0) || (tokens[i] == '-' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9') && (tokens[i + 1] >= '0' && tokens[i + 1] <= '9') && tokens[i - 1] != ')')) {
                            sbuf.append(tokens[i++]);
                            if (i == tokens.length)
                                break;
                        }double b = Math.toRadians(Double.parseDouble(String.valueOf(sbuf)));
                        st.append(Math.sin(b));
                        i--;
                        break;
                    case "cos":
                        sbuf = new StringBuffer();
                        while (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.' || (tokens[i] == '-' && i == 0) || (tokens[i] == '-' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9') && (tokens[i + 1] >= '0' && tokens[i + 1] <= '9') && tokens[i - 1] != ')')) {
                            sbuf.append(tokens[i++]);
                            if (i == tokens.length)
                                break;
                        }b = Math.toRadians(Double.parseDouble(String.valueOf(sbuf)));
                        st.append(Math.cos(b));
                        i--;
                        break;
                    case "tan":
                        sbuf = new StringBuffer();
                        while (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.' || (tokens[i] == '-' && i == 0) || (tokens[i] == '-' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9') && (tokens[i + 1] >= '0' && tokens[i + 1] <= '9') && tokens[i - 1] != ')')){
                            sbuf.append(tokens[i++]);
                        if (i == tokens.length)
                            break;
                }b = Math.toRadians(Double.parseDouble(String.valueOf(sbuf)));
                        st.append(Math.tan(b));
                        i--;
                        break;
                    case "log":
                        sbuf = new StringBuffer();
                        while (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.' || (tokens[i] == '-' && i == 0) || (tokens[i] == '-' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9') && (tokens[i + 1] >= '0' && tokens[i + 1] <= '9') && tokens[i - 1] != ')')){
                            sbuf.append(tokens[i++]);
                            if (i == tokens.length)
                                break;
                        }
                        st.append(Math.log(Double.parseDouble(String.valueOf(sbuf))));
                        i--;
                        break;
                    case "sqrt":
                        sbuf = new StringBuffer();
                        while (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.' || (tokens[i] == '-' && i == 0) || (tokens[i] == '-' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9') && (tokens[i + 1] >= '0' && tokens[i + 1] <= '9') && tokens[i - 1] != ')')){
                            sbuf.append(tokens[i++]);
                            if (i == tokens.length)
                                break;
                        }
                        st.append(Math.sqrt(Double.parseDouble(String.valueOf(sbuf))));
                        i--;
                        break;
                    case "e":
                        st.append(Math.exp(1));
                        i--;
                        break;
                    case "π":
                        st.append(Math.PI);
                        i--;
                        break;
                }
            }
        }
        return String.valueOf(st);
    }

    static String infixToPostfix(String exp) {
        double a;
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
                a= Double.parseDouble(String.valueOf(sbuf));
                result += String.valueOf(a);
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

    static String getInfix(String exp) throws Exception {
        Stack<String> s = new Stack<String>();
        String result = "";
        char[] tokens = exp.toCharArray();
        for (int i = 0; i < exp.length(); i++)
        {
            // Push operands
            if (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.' || (tokens[i] == '-' && i == 0) || (tokens[i] == '-' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9') && (tokens[i + 1] >= '0' && tokens[i + 1] <= '9') && tokens[i - 1] != ')'))
            {
                StringBuffer sbuf = new StringBuffer();

                while (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.' || (tokens[i] == '-' && i == 0) || (tokens[i] == '-' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9') && (tokens[i + 1] >= '0' && tokens[i + 1] <= '9') && tokens[i - 1] != ')')) {
                    sbuf.append(tokens[i++]);
                    if (i == tokens.length)
                        break;
                }
                s.push(sbuf + "");
                i--;
            }

            // We assume that input is
            // a valid postfix and expect
            // an operator.
            else if (tokens[i]!=' ')
            {
                String op1 = s.pop();
                String op2 = s.pop();
                if (isNumeric(op1) && Double.parseDouble(op1)<0)
                    s.push("(" + op2 + exp.charAt(i) +
                            "("+op1 + "))");
                else
                s.push("(" + op2 + exp.charAt(i) +
                        op1 + ")");
            }
        }

        // There must be a single element
        // in stack now which is the required
        // infix.
        return s.peek();
    }

    public static double evaluatePostfix(String exp) throws Exception {
        Stack<Double> stack = new Stack<Double>();
        char[] tokens = exp.toCharArray();
        ArrayList<String>al = new ArrayList<>();
        StringBuffer result = new StringBuffer();

        for (int i =0;i<tokens.length;i++) {
            result = new StringBuffer();
            if (tokens[i] != ' ') {
                while (tokens[i] != ' ')
                    result.append(tokens[i++]);
                i--;
            }
            if (!result.isEmpty())
            al.add(String.valueOf(result));
        }

        for (int i = 0; i < exp.length(); i++) {

            result = new StringBuffer();

            if (tokens[i]  >= '0' && tokens[i] <= '9' || tokens[i] == '.' || (tokens[i] == '-' && i == 0) || (tokens[i] == '-' && !(tokens[i-1] >= '0' && tokens[i-1] <= '9') && (tokens[i+1] >= '0' && tokens[i+1] <= '9'))) {
                while (tokens[i] != ' ')
                    result.append(tokens[i++]);
                stack.push(Double.parseDouble(String.valueOf(result)));
                i--;
            } else if(tokens[i]!=' ') {
                double val1 =  stack.pop();
                double val2 =  stack.pop();

                double r = 0;
                switch (tokens[i]) {
                    case '+' -> r = val2 + val1;
                    case '-' -> r = val2 - val1;
                    case '*' -> r = val2 * val1;
                    case '^' -> r = Math.pow(val2, val1);
                    case '/' -> {
                        if (val1 == 0)
                            throw new
                                    UnsupportedOperationException();
                        r = val2 / val1;
                    }
                }
                boolean flag=true;
                for (int j=0;j<al.size()-2;j++) {
                    if (al.get(j).equals(String.valueOf(val2)) && al.get(j + 1).equals(String.valueOf(val1)) && al.get(j + 2).equals(String.valueOf(tokens[i]))) {
                        for (int t = 0; t < 3; t++)
                            al.remove(j);
                        al.add(j, String.valueOf(r));
                        break;
                    }
                }
                result = new StringBuffer();
                for (String j: al) {
                    result.append(j+" ");
                }
                System.out.println(getInfix(String.valueOf(result)));
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