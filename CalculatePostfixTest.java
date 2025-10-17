/**
 * Test class to test different expressions for postfix operation
 * 
 * @author Yunxian Ding
 * @version 10/16/2025
 */
public class CalculatePostfixTest {

	public static void main(String[] args) {    

        // Test 1: 3 2 + 5 * = 25.0
        try {
            Queue<Object> t1 = Tokenizer.readTokens("3 2 + 5 *");
            Double expected1 = 25.0;
            Double actual1 = CalculatePostfix.postfixToResult(t1);
            if (expected1.equals(actual1)) {
                System.out.println("Test 1 passed!");
            } else {
                System.out.println("Test 1 failed!");
            }
        } catch (Exception e) {
            System.out.println("Test 1 failed and threw " + e);
        }

        // Test 2: 20 10 - 5 / = 2.0
        try {
            Queue<Object> t2 = Tokenizer.readTokens("20 10 - 5 /");
            Double expected2 = 2.0;
            Double actual2 = CalculatePostfix.postfixToResult(t2);
            if (expected2.equals(actual2)) {
                System.out.println("Test 2 passed!");
            } else {
                System.out.println("Test 2 failed!");
            }
        } catch (Exception e) {
            System.out.println("Test 2 failed and threw " + e);
        }

        // Test 3: 5 2 ^ 2 * = 50.0
        try {
            Queue<Object> t3 = Tokenizer.readTokens("5 2 ^ 2 *");
            Double expected3 = 50.0;
            Double actual3 = CalculatePostfix.postfixToResult(t3);
            if (expected3.equals(actual3)) {
                System.out.println("Test 3 passed!");
            } else {
                System.out.println("Test 3 failed!");
            }
        } catch (Exception e) {
            System.out.println("Test 3 failed and threw " + e);
        }

        // Test 4: tokens queue is null
        try {
            Queue<Object> t4 = Tokenizer.readTokens(" ");
            CalculatePostfix.postfixToResult(t4);
            System.out.println("Test 4 failed because it should throw exception but got result.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 4 passed!");
        } catch (Exception e) {
            System.out.println("Test 4 failed because it threw another type of exception: " + e);
        }

        // Test 5: stack runs out of elements needed for processing an operator
        try {
            Queue<Object> t5 = Tokenizer.readTokens("1 +");
            // In this case, we could only pop num2 from the stack and the stack would be empty and throw exception
            CalculatePostfix.postfixToResult(t5);
            System.out.println("Test 5 failed because it should throw exception but got result.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 5 passed!");
        } catch (Exception e) {
            System.out.println("Test 5 failed because it threw another type of exception: " + e);
        }

        // Test 6: invalid operator
        try {
            Queue<Object> t6 = Tokenizer.readTokens("1 2 &");
            CalculatePostfix.postfixToResult(t6);
            System.out.println("Test 6 failed because it should throw exception but got result.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 6 passed!");
        } catch (Exception e) {
            System.out.println("Test 6 failed because it threw another type of exception: " + e);
        }

        // Test 7: token is neither a number or an operator
        try {
            Queue<Object> t7 = Tokenizer.readTokens("1 2 a");
            CalculatePostfix.postfixToResult(t7);
            System.out.println("Test 7 failed because it should throw exception but got result.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 7 passed!");
        } catch (Exception e) {
            System.out.println("Test 7 failed because it threw another type of exception: " + e);
        }

    }
}
