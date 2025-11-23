package HashMap;

import java.util.LinkedHashMap;

public class FirstRepeatedCharacterIndex {
    public static void main(String[] args) {
        String str="Javav";
        LinkedHashMap<Character,Integer> map=new LinkedHashMap<>();
        for(char ch:str.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        System.out.println(map);
        for(int i=0;i<str.length();i++){
            char ch= str.charAt(i);
            if(map.get(ch)>1){
                System.out.println(ch + " "+i );
                break; /// order mai sab frequency print ho gya . now jiska freq 1 hai usko print karo and ek print ke baad break kar do
            }
        }
    }
}
