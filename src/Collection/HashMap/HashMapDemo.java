package Collection.HashMap;

import java.util.HashMap;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String,String>hp=new HashMap<>();
        hp.put("dhruv","shukla");
        System.out.println(hp);
        hp.put(new String("dhruv"),"mishra");
        System.out.println(hp.size());
        System.out.println(hp);
    }
}
