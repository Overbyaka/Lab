import java.util.Scanner;
import java.util.Stack;

public class Second {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(Calculator.RPNtoAnswer(Calculator.ExpressionToRPN(in.nextLine())));
    }
}
