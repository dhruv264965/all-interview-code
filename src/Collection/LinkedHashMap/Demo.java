package Collection.LinkedHashMap;

import java.util.LinkedHashMap;

public class Demo {
    public static void main(String[] args) {

        LinkedHashMap<Integer,String>linkedHashMap=new LinkedHashMap<>(3,0.75f,true);
        linkedHashMap.put(2,"TWO");
        linkedHashMap.put(1,"one");
        linkedHashMap.put(3,"three");
        System.out.println(linkedHashMap);  // {2=TWO, 1=one, 3=three}
        linkedHashMap.get(1);
        System.out.println(linkedHashMap);  // {2=TWO, 3=three, 1=one}  access order
        linkedHashMap.put(4,"four");
        System.out.println(linkedHashMap);
    }
}
