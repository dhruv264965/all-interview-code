package Collection.Tree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>(Arrays.asList(50, 10, 40, 20, 30, 10));

        System.out.println(set);
        System.out.println(set.first());
        System.out.println(set.higher(30));
        System.out.println(set.floor(35));
        System.out.println(set.headSet(30));
        System.out.println(set.lower(10));
        System.out.println(set.ceiling(25));
        System.out.println(set.tailSet(30));
        System.out.println(set.last());
        System.out.println(set.pollFirst());
    }
}