# A5 DIY Calculator

Your readme should include the following information. Each student needs to submit their own reflection, even when pair programming.  Please write N/A if an item does not apply.

## Basic Information

Group Member Name(s): 
N/A

Other collaborators you worked with, including TAs (and feel free to give a shoutout to anyone who was particularly helpful): 
Thank you professor Nick for explaining postfix computation and shunting-yard algorithm!

Any references used besides JavaDoc and course materials:
I would like to cite copilot AI's help.
When trying the test cases in `CalculatePostfixTest.java`, I was surprised that I failed test1, test2 and test3 and I didn't know why. Then I aksed copilot, it explained that I was using `==` to compare Double objects. In Java, `==` compares object references, not their values. Since Double is an object type, two Double objects with the same numeric value may not be the same reference, so the comparison returns false. Thus, by changing it to `.equals()`, I passed the tests with Copilot's help.

## Reflection

Reflection on your experience with this assignment:
Overall it went smoothly. There are a lot of swith cases and if else statements but the instructions provided thorough pseduo code to follow through.

Any questions you still have?
Does a real-life calculator that takes infix operation does the same thing(first converting it to postfix expression)?