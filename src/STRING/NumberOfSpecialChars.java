package STRING;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//NumberOfSpecialChars- if both  upper and lower character is present then it called NumberOfSpecialChars
public class NumberOfSpecialChars {
    public static void main(String[] args) {
        String str="aabsABDrkS";
        int count = 0;

        boolean[] present = new boolean[128]; // ASCII size

        for (int i = 0; i < str.length(); i++) {
            present[str.charAt(i)] = true;
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (present[ch] && present[Character.toUpperCase(ch)]) {
                count++;
            }
        }

        System.out.println("Count of special characters: " + count);
        System.out.println("----------------------------------------------");
        HashSet<Character>hp =new HashSet<>();
        for (char ch:str.toCharArray()){
            hp.add(ch);
        }
        System.out.println(hp);
        int count1=0;
        for(char ch='a';ch<='z';ch++){
            if(hp.contains(ch) && hp.contains(Character.toUpperCase(ch))){
                count1++;
            }
        }
        System.out.println(count1);
    }
}
