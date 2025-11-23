package HashMap;

import java.util.LinkedHashMap;

public class SecondNonRepeatingCharacter {
    public static void main(String[] args) {
        String str="swiss";
        LinkedHashMap<Character,Integer>map=new LinkedHashMap<>();
        for(char ch:str.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        System.out.println(map);
        int count =0;
        for(char ch:str.toCharArray()){
            if(map.get(ch)==1){
                count++;
                if(count==2) {
                    System.out.println(ch);
                    return;
                }
            }
        }

    }
}
