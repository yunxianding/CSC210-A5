/**
 * This class converts infix expressions to postfix and calculates the result
 * 
 * @author Yunxian Ding
 * @version 10/16/2025
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
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }
    
    /**
     * Returns true if operator is right-associative
     * 
     * @param op the operator character
     * @return true if right-associative, false if left-associative
     */
    private static boolean isRightAssociative(char op) {
        return op == '^';
    }
    
    /**
     * Converts infix expression to postfix and calculates the result
     * Uses the Shunting Yard algorithm
     * 
     * @param tokens queue of tokens in infix notation
     * @return the calculated result
     * @throws IllegalArgumentException for malformed expressions
     */
    public static Double infixToPostfix(Queue<Object> tokens) {
        if (tokens == null) {
            throw new IllegalArgumentException("The tokens queue is null.");
        }
        
        Queue<Object> outputQueue = new Queue<>();
        Stack<Character> operatorStack = new Stack<>();
        
        // Process each token using Shunting Yard algorithm
        while (!tokens.isEmpty()) {
            Object token = tokens.remove();
            
            // If token is a number, add it directly to output queue
            if (token instanceof Double) {
                outputQueue.add(token);
            }
            // If token is a character, it could be an operator or parenthesis
            else if (token instanceof Character) {
                char c = (Character) token;
                
                // If it's a left parenthesis, push onto stack
                if (c == '(') {
                    operatorStack.push(c);
                }
                // If it's a right parenthesis
                else if (c == ')') {
                    // Pop operators until we find the matching left parenthesis
                    boolean foundLeftParen = false;
                    while (!operatorStack.isEmpty()) {
                        char top = operatorStack.pop();
                        if (top == '(') {
                            foundLeftParen = true;
                            break;
                        }
                        outputQueue.add(top);
                    }
                    // If we didn't find a left parenthesis, the expression is malformed
                    if (!foundLeftParen) {
                        throw new IllegalArgumentException("Mismatched parentheses: missing left parenthesis.");
                    }
                }
                // If it's an operator
                else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                    // Pop operators from stack to output queue while they have higher or equal precedence
                    // For right-associative operators (^), only pop if strictly higher precedence
                    while (!operatorStack.isEmpty()) {
                        char stackOp = operatorStack.peek();
                        
                        // Don't pop left parenthesis
                        if (stackOp == '(') {
                            break;
                        }
                        
                        // Check precedence and associativity
                        int stackPrec = precedence(stackOp);
                        int tokenPrec = precedence(c);
                        
                        // For left-associative: pop if stack precedence >= token precedence
                        // For right-associative: pop if stack precedence > token precedence
                        boolean shouldPop;
                        if (isRightAssociative(c)) {
                            shouldPop = stackPrec > tokenPrec;
                        } else {
                            shouldPop = stackPrec >= tokenPrec;
                        }
                        
                        if (shouldPop) {
                            outputQueue.add(operatorStack.pop());
                        } else {
                            break;
                        }
                    }
                    // Push the current operator onto the stack
                    operatorStack.push(c);
                } else {
                    throw new IllegalArgumentException("Invalid character: " + c);
                }
            } else {
                throw new IllegalArgumentException("Token is neither a number nor a character.");
            }
        }
        
        // Pop any remaining operators from stack to output queue
        while (!operatorStack.isEmpty()) {
            char top = operatorStack.pop();
            // If we find a parenthesis, the expression is malformed
            if (top == '(' || top == ')') {
                throw new IllegalArgumentException("Mismatched parentheses.");
            }
            outputQueue.add(top);
        }
        
        // Now use the postfix calculator to compute the result
        return CalculatePostfix.postfixToResult(outputQueue);
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
            System.out.println(result);
        }
    }
}