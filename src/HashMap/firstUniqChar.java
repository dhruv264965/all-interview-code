package HashMap;

import java.util.LinkedHashMap;
import java.util.Map;

public class firstUniqChar {
   static public int firstUniqChar(String s) {
        Map<Character,Integer> hp=new LinkedHashMap<>();
        for(char ch: s.toCharArray()){
            hp.put(ch, hp.getOrDefault(ch,0)+1);
        }
        for(int i=0;i<s.length();i++){
            char ch1=s.charAt(i);
            if(hp.get(ch1)==1){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
       String s= "loveleetcode";
        System.out.println(firstUniqChar(s));

    }
}
