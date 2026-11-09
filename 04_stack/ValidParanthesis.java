import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParanthesis {
    public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        if (c == '(' || c == '[' || c == '{') {    
            stack.push(c);
        } else {                                      
            if (stack.isEmpty()) {
                return false;                        
            }
            char top = stack.pop();                   
            if (c == ')' && top != '(') return false;
            if (c == ']' && top != '[') return false;
            if (c == '}' && top != '{') return false;
        }
    }
    return stack.isEmpty();                           
    }

    public static void main(String[] args) {
        ValidParanthesis validator = new ValidParanthesis();
        System.out.println(validator.isValid("([])"));   // expect true
        System.out.println(validator.isValid("([)]"));   // expect false
        System.out.println(validator.isValid("(("));     // expect false
        System.out.println(validator.isValid(")"));      // expect false
    }
}
