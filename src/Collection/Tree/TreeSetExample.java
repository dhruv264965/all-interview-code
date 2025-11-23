package Collection.Tree;

import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        TreeSet<String> fruits = new TreeSet<>();
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Orange");
        fruits.add("Mango");
      //  fruits.add(null);    //// Throws NullPointerException

        System.out.println("TreeSet: " + fruits);

        //❌ TreeSet does not allow null (when using natural ordering).
        //
        //This is because it tries to compare null using compareTo(), which throws an exception.
    }
}
