
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class PostfixCalculatorfix {

	// Methods to evalute a postfix expression
    public int evaluatePostfix(String postfixExpression) {
    	// Initialize a stack to hold operands
        Stack<Integer> stack = new Stack<>();
        String[] tokens = postfixExpression.split(" ");
        // Check if the token is a number
        for (String token : tokens) {
            try {
                if (token.matches("\\d+")) { 
                    stack.push(Integer.parseInt(token));
                } else { 
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Not valid postfix expression: missing operands");
                    }
                    int operand2 = stack.pop();
                    int operand1 = stack.pop();
                    switch (token) {
                        case "+":
                        	// Addition
                            stack.push(operand1 + operand2);
                            break;
                        case "-":
                        	// SUbtration
                            stack.push(operand1 - operand2);
                            break;
                        case "*":
                        	//Mulitplication
                            stack.push(operand1 * operand2);
                            break;
                        case "/":
                            if (operand2 == 0) {
                                throw new ArithmeticException("Division by zero");
                            }
                            stack.push(operand1 / operand2);
                            break;
                        case "%":
                            if (operand2 == 0) {
                                throw new ArithmeticException("Division by zero");
                            }
                            stack.push(operand1 % operand2);
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid operator: " + token);
                    }
                }
            } catch (Exception e) {
                // Print the error message and return Integer.MIN_VALUE to indicate an error
                System.out.println("Error: " + e.getMessage());
                return Integer.MIN_VALUE;
            }
        }

        // There should be exactly one element left in the stack, which is the result
        if (stack.size() != 1) {
            System.out.println("Error: Invalid postfix expression");
            return Integer.MIN_VALUE;
        }

        return stack.pop();
    }

 
    public void evaluateExpressionsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int expressionNumber = 1;
            while ((line = br.readLine()) != null) {
                System.out.println("Expression " + expressionNumber + ": " + line);
                int result = evaluatePostfix(line);
                if (result != Integer.MIN_VALUE) {
                    System.out.println("Result: " + result);
                }
                expressionNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PostfixCalculatorfix calculator = new PostfixCalculatorfix();

        // Example 1
        String expression1 = "4 2 * 3 +";
        System.out.println("Result 1 : " + calculator.evaluatePostfix(expression1));

        // Example 2
        String expression2 = "5 3 + 7 *";
        System.out.println("Result 2: " + calculator.evaluatePostfix(expression2));

        // Example 3
        String expression3 = "4 2 * +"; // Missing operand
        System.out.println("Result 3: " + calculator.evaluatePostfix(expression3));


    }
}
