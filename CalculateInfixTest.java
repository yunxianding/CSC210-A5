/**
 * Test class to test different expressions for infix operation
 * 
 * @author Yunxian Ding
 * @version 10/16/2025
 */
public class CalculateInfixTest {

    public static void main(String[] args) {
        
        // Test 1: (3 + 2) * 5 = 25.0
        try {
            Queue<Object> t1 = Tokenizer.readTokens("(3 + 2) * 5");
            Double expected1 = 25.0;
            Double actual1 = CalculateInfix.infixToPostfix(t1);
            if (expected1.equals(actual1)) {
                System.out.println("Test 1 passed!");
            } else {
                System.out.println("Test 1 failed! Expected: " + expected1 + ", Got: " + actual1);
            }
        } catch (Exception e) {
            System.out.println("Test 1 failed and threw " + e);
        }

        // Test 2: 5 + (1 + 2) * 4 - 3 = 14.0
        try {
            Queue<Object> t2 = Tokenizer.readTokens("5 + (1 + 2) * 4 - 3");
            Double expected2 = 14.0;
            Double actual2 = CalculateInfix.infixToPostfix(t2);
            if (expected2.equals(actual2)) {
                System.out.println("Test 2 passed!");
            } else {
                System.out.println("Test 2 failed! Expected: " + expected2 + ", Got: " + actual2);
            }
        } catch (Exception e) {
            System.out.println("Test 2 failed and threw " + e);
        }

        // Test 3: 2 ^ 3 = 8.0 (exponentiation)
        try {
            Queue<Object> t3 = Tokenizer.readTokens("2 ^ 3");
            Double expected3 = 8.0;
            Double actual3 = CalculateInfix.infixToPostfix(t3);
            if (expected3.equals(actual3)) {
                System.out.println("Test 3 passed!");
            } else {
                System.out.println("Test 3 failed! Expected: " + expected3 + ", Got: " + actual3);
            }
        } catch (Exception e) {
            System.out.println("Test 3 failed and threw " + e);
        }

        // Test 4: 2 ^ 1 ^ 3 = 2.0 (right associativity: 2^(1^3) = 2^1 = 2)
        try {
            Queue<Object> t4 = Tokenizer.readTokens("2 ^ 1 ^ 3");
            Double expected4 = 2.0;
            Double actual4 = CalculateInfix.infixToPostfix(t4);
            if (expected4.equals(actual4)) {
                System.out.println("Test 4 passed!");
            } else {
                System.out.println("Test 4 failed! Expected: " + expected4 + ", Got: " + actual4);
            }
        } catch (Exception e) {
            System.out.println("Test 4 failed and threw " + e);
        }

        // Test 5: 10 / 2 / 5 = 1.0 (left associativity: (10/2)/5 = 5/5 = 1)
        try {
            Queue<Object> t5 = Tokenizer.readTokens("10 / 2 / 5");
            Double expected5 = 1.0;
            Double actual5 = CalculateInfix.infixToPostfix(t5);
            if (expected5.equals(actual5)) {
                System.out.println("Test 5 passed!");
            } else {
                System.out.println("Test 5 failed! Expected: " + expected5 + ", Got: " + actual5);
            }
        } catch (Exception e) {
            System.out.println("Test 5 failed and threw " + e);
        }

        // Test 6: 3 + 4 * 2 = 11.0 (precedence: 3 + (4*2))
        try {
            Queue<Object> t6 = Tokenizer.readTokens("3 + 4 * 2");
            Double expected6 = 11.0;
            Double actual6 = CalculateInfix.infixToPostfix(t6);
            if (expected6.equals(actual6)) {
                System.out.println("Test 6 passed!");
            } else {
                System.out.println("Test 6 failed! Expected: " + expected6 + ", Got: " + actual6);
            }
        } catch (Exception e) {
            System.out.println("Test 6 failed and threw " + e);
        }

        // Edge Test 7: Mismatched parentheses - missing left paren
        try {
            Queue<Object> t7 = Tokenizer.readTokens("3 + 2)");
            CalculateInfix.infixToPostfix(t7);
            System.out.println("Test 7 failed because it should throw exception but got result.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 7 passed! (caught expected IllegalArgumentException)");
        } catch (Exception e) {
            System.out.println("Test 7 failed because it threw wrong exception: " + e);
        }

        // Edge Test 8: Mismatched parentheses - missing right paren
        try {
            Queue<Object> t8 = Tokenizer.readTokens("(3 + 2");
            CalculateInfix.infixToPostfix(t8);
            System.out.println("Test 8 failed because it should throw exception but got result.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 8 passed! (caught expected IllegalArgumentException)");
        } catch (Exception e) {
            System.out.println("Test 8 failed because it threw wrong exception: " + e);
        }

        // Test 9: Complex expression with multiple operators
        try {
            Queue<Object> t9 = Tokenizer.readTokens("(5 + 3) * 2 ^ 2 - 1");
            Double expected9 = 31.0;  // (5+3) * 2^2 - 1 = 8 * 4 - 1 = 32 - 1 = 31
            Double actual9 = CalculateInfix.infixToPostfix(t9);
            if (expected9.equals(actual9)) {
                System.out.println("Test 9 passed!");
            } else {
                System.out.println("Test 9 failed! Expected: " + expected9 + ", Got: " + actual9);
            }
        } catch (Exception e) {
            System.out.println("Test 9 failed and threw " + e);
        }
    }
}
