package Collection.HashMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RetriveSpecficKeyAndValue {
    public static void main(String[] args) {
        HashMap<Integer,String> hash_map= new LinkedHashMap<>();
        hash_map.put(1, "Red");
        hash_map.put(null, null);    ///only one null key allow but multiple null value
        hash_map.put(3, null);
        hash_map.put(4, "White");
        hash_map.put(5, "Blue");
        hash_map.put(6, "Blue");
        hash_map.put(7, "Blue");
        hash_map.put(8, "dhruv");
        hash_map.put(9, "shukla");
        hash_map.put(9, null);

        System.out.println(hash_map);
        for(Map.Entry s: hash_map.entrySet()){
            System.out.println(s.getKey()+"  "+s.getValue());
        }
        System.out.println("----------------------------------------");
        for(Integer x:hash_map.keySet()){
            System.out.println(x);
        }
        System.out.println("---------------------------------");
     for (String sr: hash_map.values()){
         System.out.println(sr);
     }
        System.out.println(hash_map.size());


    }
}
