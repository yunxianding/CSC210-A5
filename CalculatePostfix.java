/**
 * This class calculates the solution to a postfix solution
 * 
 * @arthur Yunxian Ding
 * @version 10/16/2025
 */
public class CalculatePostfix {

    /**
     * This method calculates the value to a postfix expression
     * The input expression will stored as tokens in a queue called tokens
     * Then we do the postfix calculation to the tokens queue
     * 
     * @param tokens queue of tokens
     * @return the value of the postfix expression
     * @throws IllegalArgumentException for special cases
     */
    public static Double postfixToResult(Queue<Object> tokens) {

        // If tokens queue is null, throw exception
        if (tokens == null) {
            throw new IllegalArgumentException("The tokens queue is null.");
        }

        Stack<Double> stack = new Stack<>();

        while (!tokens.isEmpty()) {
            // Take the first token in tokens queue each time
            Object token = tokens.remove();

            // It the token is a number, push it onto the stack
            if (token instanceof Double) {
                stack.push((Double) token);
                continue;
            }

            // If the token is an operator
            else if (token instanceof Character) {
                char op = (Character) token;

                if (op == '+' || op == '-' || op == '*' || op == '/' || op == '^') {
                    // If the stack runs out of elements needed for processing an operator, throw exception
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("Stack runs out of elements.");
                    }
                    // The first double we pop will be the second number in equation
                    double num2 = stack.pop(); 
                    // If the stack runs out of elements needed for processing an operator, throw exception
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("Stack runs out of elements.");
                    }
                    // The second double we pop will be the first number in equation
                    double num1 = stack.pop(); 

                    // Combine the two numbers using the operator and push the result back onto the stack
                    double result = 0;
                    switch (op) {
                        case '+':
                            result = num1 + num2;
                            break;
                        case '-':
                            result = num1 - num2;
                            break;
                        case '*':
                            result = num1 * num2;
                            break;
                        case '/':
                            result = num1 / num2;
                            break;
                        case '^':
                            result = Math.pow(num1, num2);
                            break;
                    }
                    stack.push(result);
                    continue;
                } else {
                    throw new IllegalArgumentException("Invalid operator.");
                }
            }

            // If the token is neither a number or an operator, throw exception
            else {
                throw new IllegalArgumentException("Token is neither a number nor an operator.");
            }
        }

        // The stack should contains exactly one element, which is the result after all tokens have been processed
        // If there is no element in the stack, throw exception
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Stack is empty, no result to return.");
        }

        Double result = stack.pop();
        // If after popping the result, there's still items on the stack, throw exception
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("There are too many items on the stack in the end.");
        }
        
        return result;
    }

    /**
     * Main method to run the postfix calculator from command line
     * 
     * @param args arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage:  java CalculatePostfix <expr>");
        } else {
            Queue<Object> tokens = Tokenizer.readTokens(args[0]);
            Double result = postfixToResult(tokens);
            System.out.println(result);
        }
    }
}