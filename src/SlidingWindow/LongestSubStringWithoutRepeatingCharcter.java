package SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LongestSubStringWithoutRepeatingCharcter {
    static boolean checkUniqueString(String s, int start, int end){
        HashSet<Character>unique=new HashSet<>();
        for(int i=start;i<=end;i++){
            char ch=s.charAt(i);
            if(unique.contains(ch)){   // geeksforgeeks  --
                return false;
            }
            unique.add(ch);
        }
        return true;
    }
    static int lengthOfLongestSubStringWithoutRepeatingCharacterBruteForce(String s){
        int result=0;
        for(int i=0;i<s.length();i++){
            for (int j=i;j<s.length();j++){
                if(checkUniqueString(s,i,j)){
                    result=Math.max(result,j-i+1);
                }
            }
        }
        return result;

    }
    static int lengthOfLongestSubStringWithoutRepeatingCharacterOptimal(String s){
        int result = 0;
        Map<Character, Integer> hp = new HashMap<>();
        int i = 0;
        int j = 0;
        while (j < s.length()) {
            char ch = s.charAt(j);
            if (hp.containsKey(ch)) {   //  // agar hamara character repeat karta hai then i index ko update kar denge
                i = Math.max(hp.get(ch), i);
            }
            result = Math.max(result, j - i + 1);
            hp.put(ch, j + 1);  // storing index +1 because if j=0 and value is "a" then it will sore[a:1]
            j++;
        }
        return result;
    }
    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> hp=new HashSet<>();
        int i=0;
        int maxLength=0;
        for(int j=0;j<s.length();j++){
            char ch=s.charAt(j);
            //if char is duplicate
            while(hp.contains(ch)){
                char leftChar=s.charAt(i);
                hp.remove(leftChar);
                i++;
            }
            //if char is unique  step 1
            hp.add(ch);
            maxLength=Math.max(maxLength,(j-i+1));
        }
        return maxLength;
    }
    public static void main(String[] args) {
//         String s="abcabcbb";
        // String s="pwwkew";
//         String s="bbbbb";
        String s="geeksforgeeks";
//        System.out.println(lengthOfLongestSubStringWithoutRepeatingCharacterBruteForce(s));
        System.out.println(lengthOfLongestSubStringWithoutRepeatingCharacterOptimal(s));
        System.out.println(lengthOfLongestSubstring(s));
    }
}
