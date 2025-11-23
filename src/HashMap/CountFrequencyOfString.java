package HashMap;

import java.util.HashMap;
import java.util.Map;

public class CountFrequencyOfString {
    static void countFreqOfString(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(char ch:str.toCharArray()){
            if(ch!=' ')   // if we dont want space
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        System.out.println(map);  // {r=1, d=1, e=1, u=1, v=1, w=1, h=3, l=2, o=2}
        for(Map.Entry<Character,Integer>entry:map.entrySet()){
            System.out.print(entry.getKey()+""+entry.getValue()); // r1d1e1u1v1w1h3l2o2
        }
    }

    public static void main(String[] args) {
        countFreqOfString("hellohow dhruv");
    }
}
