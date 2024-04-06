package oy.tol.tra;

import java.util.HashMap;

public class ParenthesisChecker {

   private ParenthesisChecker() {
   }

   /**
    * Implement this function which checks if the given string has matching opening and closing
    * parentheses.
    *
    * @param stack The stack object used in checking the parentheses from the string.
    * @param fromString A string containing parentheses to check if it is valid.
    * @return Returns the number of parentheses found from the input in total (both opening and closing).
    * @throws ParenthesesException if the parentheses did not match as intended.
    */
    public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {
        HashMap<Character, Character> matchingParentheses = new HashMap<>();
        matchingParentheses.put(')', '(');
        matchingParentheses.put('}', '{');
        matchingParentheses.put(']', '[');

        int parenthesesCount = 0;

        for (char c : fromString.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                try {
                    stack.push(c);
                    parenthesesCount++;
                } catch (StackAllocationException e) {
                    throw new ParenthesesException("Error while pushing into stack.", ParenthesesException.ErrorCode.STACK_ALLOCATION_ERROR);
                }
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    throw new ParenthesesException("Too many closing parentheses in the string.", ParenthesesException.ErrorCode.TOO_MANY_CLOSING_PARENS);
                }
                Character top = stack.pop();
                parenthesesCount++;
                if (top == null || top != matchingParentheses.get(c)) {
                    throw new ParenthesesException("Wrong kind of parenthesis in the text.", ParenthesesException.ErrorCode.WRONG_KIND_OF_PARENTHESIS);
                }
            }
        }

        if (!stack.isEmpty()) {
            throw new ParenthesesException("Too few closing parentheses in the string.", ParenthesesException.ErrorCode.TOO_FEW_CLOSING_PARENS);
        }

        return parenthesesCount;
    }
}
