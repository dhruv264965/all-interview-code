package OOPS;

/*
Constructor chaining:
 - Every constructor starts with super(...) or this(...). If you write neither, the compiler inserts super().
 - Parent constructors always finish before the child constructor body runs.
 - If the parent has no no-arg constructor, the child MUST call super(args) explicitly.
*/
public class ConstructorChaining {

    // ---------- 1. Implicit super() ----------
    static class A {
        A() { System.out.println("A()"); }
    }

    static class B extends A {
        B() {                       // compiler inserts super(); here
            System.out.println("B()");
        }
    }

    // ---------- 2. Parent without a no-arg constructor: explicit super(args) required ----------
    static class Vehicle {
        String name;
        Vehicle(String name) {
            this.name = name;
            System.out.println("Vehicle(" + name + ")");
        }
    }

    static class Car extends Vehicle {
        // Car() { }   // compile error: Vehicle has no no-arg constructor, implicit super() fails
        Car() {
            super("Car");           // must be the first statement
            System.out.println("Car()");
        }
    }

    // ---------- 3. this(...) chains constructors within the same class ----------
    static class Employee {
        String name;
        int salary;

        Employee() {
            this("Unknown", 0);     // delegates to the 2-arg constructor
            System.out.println("Employee()");
        }

        Employee(String name, int salary) {
            // implicit super() to Object here
            this.name = name;
            this.salary = salary;
            System.out.println("Employee(" + name + ", " + salary + ")");
        }
    }

    // ---------- 4. Full chain: this() -> super() ----------
    static class Manager extends Employee {
        Manager() {
            this("Boss");           // can't also call super() here; this() ends up reaching it
            System.out.println("Manager()");
        }

        Manager(String name) {
            super(name, 100000);
            System.out.println("Manager(" + name + ")");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- 1 ---");
        new B();
        // A()
        // B()

        System.out.println("--- 2 ---");
        new Car();
        // Vehicle(Car)
        // Car()

        System.out.println("--- 3 ---");
        new Employee();
        // Employee(Unknown, 0)
        // Employee()

        System.out.println("--- 4 ---");
        new Manager();
        // Employee(Boss, 100000)
        // Manager(Boss)
        // Manager()
    }
}
