package STRING;

import java.util.Stack;

public class ReverseString {
    public static void main(String[] args) {
        String str="dhruv";
        StringBuilder sb =new StringBuilder();
        for(int i=str.length()-1;i>=0;i--){
            sb.append(str.charAt(i));
        }
        System.out.println(sb);
        // using stack
        Stack<Character>stack=new Stack<>();
        for(int i=0;i<str.length();i++){
            stack.push(str.charAt(i));
        }
        System.out.println(stack);
        String res="";
       while (!stack.isEmpty()) {
           res+=stack.pop();
       }
        System.out.println(res);
    }
}
