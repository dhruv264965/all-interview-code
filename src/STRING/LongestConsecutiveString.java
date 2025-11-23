package STRING;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LongestConsecutiveString {
    public static void main(String[] args) {
        String str="dhrubcvshuklawx";
        HashSet<Character> hp=new HashSet<>();
        for(char ch:str.toCharArray()){
            hp.add(ch);
        }
        System.out.println(hp);
        String seq="";
        List<String> sequences = new ArrayList<>();
        int max_length=0;
        for (char ch:hp){
            if(!hp.contains((char)(ch-1))){ // we always want to start from beg like if digit then 1 if char then 'a'. so that we are checking prev is existing on not like 1 prev wont exits
                int currentChar=ch;
                int current_length=1; //give current lenght 1 for that character
                StringBuilder sb=new StringBuilder();
                sb.append((char) currentChar);
                while (hp.contains((char)(currentChar+1))){ //check next exits if exists then increment length
                    currentChar++;
                    current_length++;
                    sb.append((char) currentChar);
                }
               // max_length=Math.max(current_length,max_length);
                if (current_length > max_length) {
                    max_length = current_length;
                    sequences.add(sb.toString());
                  //  seq = sb.toString();
                }
                else if (current_length == max_length) {
                    sequences.add(sb.toString());
                }
            }
        }
        System.out.println(sequences);
        System.out.println(max_length);
    }
}
