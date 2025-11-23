package Collection.HashMap;

import java.util.HashMap;
import java.util.Set;

public class PrintKeyAndValueInSet {
    public static void main(String[] args) {
        HashMap<Integer,String> hash_map= new HashMap<Integer,String>();

        hash_map.put(1,"Red");
        hash_map.put(2,"Green");
        hash_map.put(3,"Black");
        hash_map.put(4,"White");
        hash_map.put(5,"Blue");
        hash_map.put(2,"black");
        System.out.println(hash_map);
//
//        // get keyset value from map
//        Set keyset = hash_map.keySet();
//     //   Set ValueSet = (Set) hash_map.values();
//
//        // check key set values
//        System.out.println("Key set values are: " + keyset);
//        System.out.println("Key set values are: " + hash_map.keySet());
//        System.out.println("Value set values are: " + hash_map.values());

    }

}
