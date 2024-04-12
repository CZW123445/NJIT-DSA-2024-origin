package oy.tol.tra;

import java.util.EmptyStackException;

public class ParenthesisChecker {

    private ParenthesisChecker() {
    }

        public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {
        
        int number = 0;
        
        for (int i=0;i<fromString.length();i++) {
                        char ch = fromString.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{'){
                               try {
                    stack.push(ch);
                    number++;
                } catch (Exception e) {
                    throw new ParenthesesException("Failed to push", ParenthesesException.STACK_FAILURE);
                }
            }
            
            else if (ch == ')' || ch == ']' || ch == '}'){
                
                if (stack.isEmpty()){
                    throw new ParenthesesException("There are too many closing parenthesis",ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
                }
                
                else if (findMatch(stack.peek()) != ch){
                    System.out.println(stack.peek());
                    throw new ParenthesesException("Wrong kind of parenthesis were in the text",ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
                }else {
                    stack.pop();
                    number++;
                }
            }

        }
        
        if (!stack.isEmpty()){
            throw new ParenthesesException("The string has more opening than closing parentheses.",ParenthesesException.TOO_FEW_CLOSING_PARENTHESES);
        }
        return number;
    }

    private static char findMatch(char parentheses){
        if (parentheses == '('){
            return ')';
        }else if (parentheses == '['){
            return ']';
        }else return '}';
    }
}
