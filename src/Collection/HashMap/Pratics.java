package Collection.HashMap;

import java.util.HashMap;

public class Pratics {
    public static void main(String[] args) {
        HashMap <Integer,String> hash_map1 = new HashMap <Integer,String> ();
        HashMap<Integer,String> hash_map2 = new HashMap <Integer,String> ();
        // populate hash maps
        hash_map1.put(1, "Red");
        hash_map1.put(2, "Green");
        hash_map1.put(3, "Black");
        System.out.println("\nValues in first map: " + hash_map1);
        hash_map2.put(4, "White");
        hash_map2.put(5, "Blue");
        hash_map2.put(6, "Orange");
        System.out.println("\nValues in second map: " + hash_map2);

        // put all values in secondmap
        hash_map2.putAll(hash_map1);
        System.out.println("\nNow values in second map: " + hash_map2);

        System.out.println("--------------------------------------------------------------");
        // Removing all the elements from the linked map
       // hash_map1.clear();

        System.out.println("------------------------------------------------------------------");
       // to check whether a map contains  key-value mappings (empty) or not.
        boolean result = hash_map1.isEmpty();
        // check the result
        System.out.println("Is hash map empty: " + result);
        // Removing all the elements from the linked map
       hash_map1.clear();
        // check if map is empty
        result = hash_map1.isEmpty();
        // check the result
        System.out.println("Is hash map empty: " + result);

        System.out.println("-----------------------------------------------------------------------------------");

        //cloning of hashmap
        HashMap<Integer,String> new_hash_map= new HashMap<Integer,String>();
        new_hash_map = (HashMap)hash_map2.clone();
        System.out.println(new_hash_map);

        System.out.println("-------------------------------------------------------");
        String val=(String)hash_map2.get(5);
        // check the value
        System.out.println("Value for key 3 is: " + val);
    }
}
