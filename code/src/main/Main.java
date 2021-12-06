package main;

import java.util.*;

class main {
    public static double evaluate(String expression) throws Exception {
        char[] tokens = expression.toCharArray();

        Stack<Double> values = new Stack<Double>();
        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++) {

            if (i==0 && tokens[i] == '-' && tokens[i + 1] == '(') {
                values.push((double) -1);
                ops.push('*');
            }
            else if (tokens[i] == '-' && tokens[i + 1] == '(' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9')&& tokens[i-1]!=')') {
                values.push((double) -1);
                ops.push('*');
            }
            else if (i==0 && tokens[i] == '-'&& tokens[i + 1] >= '0' && tokens[i + 1] <= '9'&& tokens[i+2]=='^' ) {
                values.push((double) -1);
                ops.push('*');
            }
            else if (tokens[i] == '-'&& tokens[i + 1] >= '0' && tokens[i + 1] <= '9'&& tokens[i+2]=='^'&&!(tokens[i - 1] >= '0' && tokens[i - 1] <= '9') ) {
                values.push((double) -1);
                ops.push('*');
            }
            else if (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.' || (tokens[i] == '-' && i == 0 ) || (tokens[i] == '-' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9') && (tokens[i + 1] >= '0' && tokens[i + 1] <= '9')&&tokens[i-1]!=')' )) {
                StringBuffer sbuf = new StringBuffer();

                while (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.' || (tokens[i] == '-' && i == 0) || (tokens[i] == '-' && !(tokens[i - 1] >= '0' && tokens[i - 1] <= '9') && (tokens[i + 1] >= '0' && tokens[i + 1] <= '9')&&tokens[i-1]!=')')) {
                    sbuf.append(tokens[i++]);
                    if (i == tokens.length)
                        break;
                }
                values.push(Double.parseDouble(sbuf.toString()));
                i--;
            }

            else if (tokens[i] == '(')
                ops.push(tokens[i]);

            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }

            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '^' || tokens[i] == '/') {
                while (!ops.isEmpty() && hasPrecedence(tokens[i], ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(tokens[i]);
            }
        }

        while (!ops.isEmpty() && values.size() != 1)
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        return values.pop();
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

    public static double applyOp(char op, double b, double a) {
        double r = 0;
        switch (op) {
            case '+':
                r = a + b;
                break;
            case '-':
                r = a - b;
                break;
            case '*':
                r = a * b;
                break;
            case '^':
                r = Math.pow(a, b);
                break;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                r = a / b;
                break;
        }
        System.out.println("(" + a + " " + op + " " + b + " = " + r + ")");
        return r;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Answer is: " + main.evaluate(sc.next()));


    }
}