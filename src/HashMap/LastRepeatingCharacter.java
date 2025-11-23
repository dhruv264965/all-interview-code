package HashMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LastRepeatingCharacter {
    public static void main(String[] args) {
        String s="Javavf";
        Map<Character,Integer>hp=new LinkedHashMap<>();
        for(char ch:s.toCharArray()){
            hp.put(ch, hp.getOrDefault(ch,0)+1);

        }
        for(int i=s.length()-1;i>=0;i--){
            char ch=s.charAt(i);
            if(hp.get(ch)>1){
                System.out.println(ch);
                break;
            }
        }
    }
}
