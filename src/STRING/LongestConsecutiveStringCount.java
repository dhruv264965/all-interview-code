package STRING;

import java.util.HashSet;

public class LongestConsecutiveStringCount {
    public static void main(String[] args) {
        String str="dhruvshuklawx";
//        String str="pwwkew";
        HashSet<Character> hp=new HashSet<>();
        for(char ch:str.toCharArray()){
            hp.add(ch);
        }
        System.out.println(hp);
        int max_length=0;
        for (char ch:hp){
            if(!hp.contains((char)(ch-1))){ // we always want to start from beg like if digit then 1 if char then 'a'. so that we are checking prev is existing on not like 1 prev wont exits
                int currentChar=ch;
                int current_length=1; //give current lenght 1 for that character
                while (hp.contains((char)(currentChar+1))){ //check next exits if exists then increment length
                    currentChar++;
                    current_length++;
                }
                max_length=Math.max(current_length,max_length);
            }
        }
        System.out.println(max_length);
    }
}
