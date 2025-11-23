package ARRAY;

import java.util.HashMap;
import java.util.Stack;

public class ValidParenthis {
    static boolean isValid(String s){
        Stack<Character>stack=new Stack<>();
        // Map of closing → opening brackets
        HashMap<Character,Character>hp=new HashMap<>();
        hp.put(')', '(');
        hp.put(']', '[');
        hp.put('}', '{');
        hp.put('>', '<');
        for(char c: s.toCharArray()){
            if(hp.containsValue(c)){
                stack.push(c);
            }
            if(hp.containsKey(c)){
                if(stack.isEmpty() || stack.pop()!=hp.get(c)){
                    return false;
                }

            }
        }
        return  stack.isEmpty();
    }
    public static void main(String[] args) {
        String s1 = "()[]{}<>";
        String s2 = "(]";
        String s3 = "([{}])";
        String s4 = "<{[()]>"; // wrong closing

        System.out.println(isValid(s1)); // true
        System.out.println(isValid(s2)); // false
        System.out.println(isValid(s3)); // true
        System.out.println(isValid(s4)); // false


    }
}
