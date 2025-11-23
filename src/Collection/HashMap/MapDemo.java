package Collection.HashMap;

import java.util.*;

class Student {
    int id;
    String name;

    Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }

    // Only id is used for key uniqueness
    public boolean equals(Object o) {
        if (o instanceof Student) {
            Student s = (Student) o;
            return this.id == s.id;
        }
        return false;
    }

    public int hashCode() {
        return id;
    }

    public String toString() {
        return name + "-" + id;
    }
}

public class MapDemo {
    public static void main(String[] args) {
        Map<Student, String> map = new HashMap<>();

        Student s1 = new Student("Nimit", 1);
        Student s2 = new Student("Rahul", 2);
        Student s3 = new Student("Nimit", 1); // Same id as s1

        map.put(s1, "Java");
        map.put(s2, "Spring");
        map.put(s3, "AWS");  // Should replace s1's value

        for (Map.Entry<Student, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }

        System.out.println("Size: " + map.size());
    }

    //s1 and s3 are considered equal due to same id.
    //
    //Second put() with same key replaces the value (Java → AWS).
    //
    //The final map has only 2 entries, not 3.
}

