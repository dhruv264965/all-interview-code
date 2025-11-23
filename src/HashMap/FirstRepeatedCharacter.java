package HashMap;

import java.util.HashSet;
import java.util.LinkedHashMap;

public class FirstRepeatedCharacter {
    public static void main(String[] args) {
//        String str = "Javav";
//        HashSet<Character> map = new HashSet<>();
//
//        for (char ch : str.toCharArray()) {
//            if (map.contains(ch)) {
//                System.out.println(ch); // First repeated character found
//                return;
//            }
//            map.add(ch);
//        }
//
//        System.out.println("No repeated character found");

        String str="Javav";
        LinkedHashMap<Character,Integer> map=new LinkedHashMap<>();
        for(char ch:str.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        System.out.println(map);
        for(char ch:str.toCharArray()){
            if(map.get(ch)>1){
                System.out.println(ch);
                break; /// order mai sab frequency print ho gya . now jiska freq 1 hai usko print karo and ek print ke baad break kar do
            }
        }
    }
}
