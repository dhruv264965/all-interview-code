package Collection.HashSet;

import java.util.HashSet;
import java.util.Set;

class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return 100; // Force same hash code
    }

    @Override
    public boolean equals(Object obj) {
        return false; // Force inequality
    }
}

public class Demo {
    public static void main(String[] args) {
        Set<Person> set = new HashSet<>();
        set.add(new Person("Alice"));
        set.add(new Person("Bob"));

        System.out.println(set.size()); // Output: 2 ✅
    }
}

