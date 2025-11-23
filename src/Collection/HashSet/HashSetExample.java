package Collection.HashSet;

import java.util.HashSet;

public class HashSetExample {
    public static void main(String[] args) {
        HashSet<String> fruits = new HashSet<>();
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add(null);  // Adding null
        fruits.add("Orange");
        fruits.add("Mango");
        fruits.add("Banana"); // Duplicate Banana, will be ignored
        fruits.add(null);   // Duplicate null, will be ignored

        System.out.println("HashSet: " + fruits);
    }
}
