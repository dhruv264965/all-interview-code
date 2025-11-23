package HashMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class FirstNonRepCharacter {
    public static void main(String[] args) {
        String str="aaswiwssb";
        LinkedHashMap<Character,Integer> map=new LinkedHashMap<>();
        for(char ch:str.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        System.out.println(map);
        for(char ch:str.toCharArray()){
            if(map.get(ch)==1){
                System.out.println(ch);
                break; /// order mai sab frequency print ho gya . now jiska freq 1 hai usko print karo and ek print ke baad break kar do
            }
        }
    }
}
