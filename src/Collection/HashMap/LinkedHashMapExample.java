package Collection.HashMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

// Custom class
class Employeeee {
    private int id;
    private String name;
    private double salary;

    // Constructor
    public Employeeee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    // toString for printing
    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + '}';
    }

    // Equals and hashCode (important if using as a key in HashMap)
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Employee)) return false;
//        Employee employee = (Employee) o;
//        return id == employee.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id,name);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employeeee that = (Employeeee) o;
        return id == that.id ; // here we are defining id so it will check id and remove based on ID
                               // if we define name and salary then all field should be same otherwise
                               // it will reflect the data
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name);
    }
}

public class LinkedHashMapExample {

    public static void main(String[] args) {
        // LinkedHashMap preserves insertion order
        Map<Employeeee, Integer> employeeMap = new HashMap<>();

        // Insert custom class objects
        employeeMap.put( new Employeeee(3, "dhruv", 70000),3);
        employeeMap.put(new Employeeee(1, "ridhika", 60000),2);
        employeeMap.put(new Employeeee(2, "amit", 57000),4);
        employeeMap.put( new Employeeee(2, "ankit", 56000),1);

        // Iterate and print in insertion order
        for (Map.Entry<Employeeee, Integer> entry : employeeMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " -> Value: " + entry.getValue());
        }
    }
}
