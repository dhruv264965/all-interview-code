package Interview;

import java.util.Stack;

public class ReverseEachWord {
    public static void main(String[] args) {
        String str = "Dhruv shukla is good boy";
        String[] array = str.split(" ");
        String res = "";
        for (String word : array) {
            Stack<Character> stack = new Stack<>();
           for(char ch:word.toCharArray()) {
                stack.push(ch);
            }
            System.out.println(stack);
            String reversed = "";
            while (!stack.isEmpty()) {
                reversed += stack.pop();
            }
            res += reversed + " ";
        }


            System.out.println(res);
        }
}
