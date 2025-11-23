package Interview;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
    static boolean isAnagram(String s,String t){
        if(s.length()!=t.length()) return false;
        HashMap<Character,Integer>hp=new HashMap<>();
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            hp.put(ch, hp.getOrDefault(ch,0)+1);
        }
        for(int i=0;i<t.length();i++){
            char ch=t.charAt(i);
            hp.put(ch, hp.getOrDefault(ch,0)-1);
        }
       for(Map.Entry<Character,Integer>entry: hp.entrySet()){
           if(entry.getValue()!=0){
               return false;
           }
       }
       return true;
    }
    public static void main(String[] args) {
        String s="abcd";
        String t="dcad";
        System.out.println(isAnagram(s,t));

    }
}
