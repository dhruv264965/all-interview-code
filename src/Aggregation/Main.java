package Aggregation;

class Department {
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Employee {
    private String name;
    private Department department;

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public void printDetails() {
        System.out.println(name + " works in " + department.getName() + " department.");
    }
}

public class Main {
    public static void main(String[] args) {
        Department dept = new Department("IT");
        Employee emp = new Employee("Bob", dept);
        emp.printDetails();
    }
}

