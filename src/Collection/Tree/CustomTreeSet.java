package Collection.Tree;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

class Employee implements Comparable<Employee>{
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);
    }
}
public class CustomTreeSet {
    public static void main(String[] args) {
     //   Set<Employee>employees=new TreeSet<>(Comparator.comparing(Employee::getName));  using Comparator we sort
        Set<Employee>employees=new TreeSet<>();
        employees.add(new Employee(1,"dhruv"));
        employees.add((new Employee(2,"amit")));
        employees.add(new Employee(3,"raj"));
        System.out.println(employees); // we will get ClassCastException
        // because So when you try to add the first element, it works. But when adding the second one, Java tries
        // to compare it with the first one and doesn’t know how to compare two Employee objects → hence ClassCastException.

        // EXCEPTION( we need to implements compareable interface to resolve the issue)

    }
}
