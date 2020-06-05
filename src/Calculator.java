import java.util.Stack;

public class Calculator {
    public static String ExpressionToRPN(String expr){
        String current = "";
        Stack <Character> stack = new Stack<>();
        int priority;

        for(int i = 0; i < expr.length(); i++){
            priority = getPriority(expr.charAt(i));
            if(priority == 0)
                current+=expr.charAt(i);
            else if(priority == 1)
                stack.push(expr.charAt(i));
            else if(priority == -1){
                current+= ' ';
                while(getPriority(stack.peek()) != 1)
                    current+=stack.pop();
                stack.pop();
            }
            else{
                current+=' ';
                while(!stack.empty()){
                    if(getPriority(stack.peek()) >= priority)
                        current+=stack.pop();
                    else
                        break;
                }
                stack.push(expr.charAt(i));
            }
        }

        while(!stack.empty())
            current+=stack.pop();

        return current;
    }

    public static double RPNtoAnswer(String rpn){
        String operand = new String();
        Stack <Double> stack = new Stack<>();
        for(int i = 0; i < rpn.length(); i++){
            if(rpn.charAt(i) == ' ')
                continue;
            if(getPriority(rpn.charAt(i)) == 0){
                while(rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);
                    if (i == rpn.length())
                        break;
                }
                stack.push(Double.parseDouble(operand));
                operand = new String();
            }
            if(getPriority(rpn.charAt(i))>1){
                double a = stack.pop();
                double b = stack.pop();
                if(rpn.charAt(i) == '+')
                    stack.push(b+a);
                else if(rpn.charAt(i) == '-')
                    stack.push(b-a);
                else if(rpn.charAt(i) == '*')
                    stack.push(b*a);
                else if(rpn.charAt(i) == '/')
                    stack.push(b/a);
                else if(rpn.charAt(i) == '^')
                    stack.push(Math.pow(b, a));
            }
        }
        return stack.pop();
    }

    private static int getPriority(char token){
        if(token == '*' || token == '/' || token == '^') return 3;
        else if(token == '+' || token == '-')            return 2;
        else if(token == '(')                            return 1;
        else if(token == ')')                            return -1;
        else                                             return 0;
    }
}
