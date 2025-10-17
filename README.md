# A5 DIY Calculator

Your readme should include the following information. Each student needs to submit their own reflection, even when pair programming.  Please write N/A if an item does not apply.

## Basic Information

Group Member Name(s): 
N/A

Other collaborators you worked with, including TAs (and feel free to give a shoutout to anyone who was particularly helpful): 
Thank you professor Nick for explaining postfix computation and shunting-yard algorithm!

Any references used besides JavaDoc and course materials:
I would like to thank copilot AI's help.
When trying the test cases in `CalculatePostfixTest.java`, I was surprised that I failed test1, test2 and test3 and I didn't know why. Then I aksed copilot, it explained that I was using `==` to compare Double objects. In Java, `==` compares object references, not their values. Since Double is an object type, two Double objects with the same numeric value may not be the same reference, so the comparison returns false. Thus, by changing it to `.equals()`, I passed the tests with Copilot's help.

## Reflection

Reflection on your experience with this assignment:

Any questions you still have?
