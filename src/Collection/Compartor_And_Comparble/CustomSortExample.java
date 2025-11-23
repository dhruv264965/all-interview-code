package Collection.Compartor_And_Comparble;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Persons {
    private String name;
    private int age;
    private double salary;

    public Persons(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
public class CustomSortExample {

    public static void main(String[] args) {
        List<Persons>people=new ArrayList<>();
        people.add(new Persons("Dhruv", 25, 50000));
        people.add(new Persons("Amit", 30, 70000));
        people.add(new Persons("Bhavesh", 22, 45000));
        people.add(new Persons("Chetan", 28, 60000));

        System.out.println("Before Sorting:");
        people.forEach(System.out::println);
        // ✅ Sort by Age (ascending) or decending
        people.sort(Comparator.comparingInt(Persons::getAge).reversed());
        System.out.println("\nAfter Sorting by Age:");
        people.forEach(System.out::println);

        // ✅ Sort by Salary (descending)
        people.sort(Comparator.comparingDouble(Persons::getSalary).reversed());
        System.out.println("\nAfter Sorting by Salary (Descending):");
        people.forEach(System.out::println);

        // ✅ Custom: Sort by Name, then by Age
        people.sort(Comparator.comparing(Persons::getName).thenComparing(Persons::getAge));
        System.out.println("\nAfter Sorting by Name, then Age:");
        people.forEach(System.out::println);
// Use sort → when you just want the collection reordered directly.
//
//Use stream().sorted() → when sorting is part of a pipeline (e.g., filtering + mapping + sorting + collecting).

    }
}
