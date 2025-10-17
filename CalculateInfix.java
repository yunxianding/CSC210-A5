/**
 * This class converts infix expressions to postfix and calculates the result
 * 
 * @author Yunxian Ding
 * @version 10/17/2025
 */
public class CalculateInfix {
    
    /**
     * Returns the precedence of an operator
     * Higher number means higher precedence
     * 
     * @param op the operator character
     * @return precedence value
     */
    private static int precedence(char op) {
        switch (op) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }
    
    /**
     * This method converts infix expression to postfix and calculates the result
     * Uses the Shunting Yard algorithm
     * 
     * @param tokens queue of tokens
     * @return the calculated result
     * @throws IllegalArgumentException for special cases
     */
    public static Double infixToPostfix(Queue<Object> tokens) {

        // If tokens queue is null, throw exception
        if (tokens == null) {
            throw new IllegalArgumentException("The tokens queue is null.");
        }
        
        Queue<Object> queue = new Queue<>();
        Stack<Character> stack = new Stack<>();
        
        // Process each token using Shunting Yard algorithm
        while (!tokens.isEmpty()) {
            Object token = tokens.remove();
            
            // If token is a number, add it to the output queue
            if (token instanceof Double) {
                queue.add(token);
            }

            // If token is a character, it could be an operator or parenthesis
            else if (token instanceof Character) {
                char c = (Character) token;
                
                // If it's a left parenthesis, push it onto the stack
                if (c == '(') {
                    stack.push(c);
                }

                // If it's a right parenthesis
                else if (c == ')') {
                    // Pop operators until we find the matching left parenthesis
                    boolean foundLeftParen = false;
                    while (!stack.isEmpty()) {
                        char top = stack.pop();
                        // If we found the left parentheis, pop it from the stack and break while loop
                        if (top == '(') {
                            foundLeftParen = true;
                            break;
                        }
                        // Add other operators onto the queue
                        queue.add(top);
                    }
                    // If we didn't find a left parenthesis, throw exception
                    if (!foundLeftParen) {
                        throw new IllegalArgumentException("There is no left parenthesis in the stack.");
                    }
                }

                // If it's an operator
                else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                    // Pop operators from stack to output queue while they have great or equal precedence
                    // For right-associative operators (^), only pop if strictly higher precedence
                    while (!stack.isEmpty()) {
                        char stackOp = stack.peek();
                        
                        // Don't pop left parenthesis
                        if (stackOp == '(') {
                            break;
                        }
                        
                        // Check precedence and associativity
                        int stackPrec = precedence(stackOp);
                        int tokenPrec = precedence(c);
                        
                        boolean shouldPop;
                        // For right-associative ^ : pop if stack precedence > token precedence
                        if (tokenPrec == 3) {
                            shouldPop = stackPrec > tokenPrec;
                        } 
                        // For left-associative + - * / : pop if stack precedence >= token precedence
                        else {
                            shouldPop = stackPrec >= tokenPrec;
                        }
                        
                        // If shouldPop, pop the stack operator off the stack and add it to the output queue
                        if (shouldPop) {
                            queue.add(stack.pop());
                        } else {
                            break;
                        }
                    }

                    // When no more high-predence stack operators remain, push the current operator onto the stack
                    stack.push(c);
                } 
                
                // If the token is not a valid character, throw exception
                else {
                    throw new IllegalArgumentException("Invalid character: " + c);
                }
            } 
            
            // If the token is neither a number or an operator, throw exception
            else {
                throw new IllegalArgumentException("Token is neither a number nor a character.");
            }
        }
        
        // When there are no more tokens to read
        // While there are stilll tokens in the stack
        while (!stack.isEmpty()) {
            char top = stack.pop();
            // If we find a parenthesis, then there are mismatched parentheses, throw exception
            if (top == '(' || top == ')') {
                throw new IllegalArgumentException("Mismatched parentheses.");
            }
            // If it's an operator, pop it onto the output queue
            queue.add(top);
        }
        
        // Use the postfix calculator to compute the result
        return CalculatePostfix.postfixToResult(queue);
    }
    
    /**
     * Main method to run the infix calculator from command line
     * 
     * @param args arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage:  java CalculateInfix <expr>");
        } else {
            Queue<Object> tokens = Tokenizer.readTokens(args[0]);
            Double result = infixToPostfix(tokens);
            System.out.println("Answer: " + result);
        }
    }
}