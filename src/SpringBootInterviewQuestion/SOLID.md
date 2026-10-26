# SOLID

```text
🔹 SOLID Principles (with Examples in Java)
1. S → Single Responsibility Principle (SRP)
📌 A class should have only one reason to change.
✅ Example (Bad):
class UserService {
    public void addUser(String user) {
        // logic to add user
    }

    public void generateReport() {
        // logic to generate report
    }
}


👉 Here, UserService does two jobs (user management + reporting).

✅ Example (Good):

class UserService {
    public void addUser(String user) {
        // logic to add user
    }
}

class ReportService {
    public void generateReport() {
        // logic to generate report
    }
}


👉 Now each class has a single responsibility.

2. O → Open/Closed Principle (OCP)

📌 Classes should be open for extension but closed for modification.

✅ Example (Bad):

class Payment {
    public void pay(String type) {
        if (type.equals("CreditCard")) {
            // credit card logic
        } else if (type.equals("UPI")) {
            // UPI logic
        }
    }
}


👉 Adding new payment types requires modifying this class.

✅ Example (Good – using Polymorphism):

interface Payment {
    void pay();
}

class CreditCardPayment implements Payment {
    public void pay() {
        System.out.println("Paid using Credit Card");
    }
}

class UpiPayment implements Payment {
    public void pay() {
        System.out.println("Paid using UPI");
    }
}


👉 Now we can add new payment types without modifying existing code.

3. L → Liskov Substitution Principle (LSP)

📌 Objects of a superclass should be replaceable with objects of a subclass without breaking the application.

✅ Example (Bad):

class Bird {
    void fly() { }
}

class Penguin extends Bird {
    @Override
    void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}


👉 Violates LSP because Penguin cannot behave like a Bird.

✅ Example (Good):

abstract class Bird { }

class FlyingBird extends Bird {
    void fly() { }
}

class Penguin extends Bird {
    void swim() { }
}


👉 Now substitution works properly.

4. I → Interface Segregation Principle (ISP)

📌 Clients should not be forced to depend on methods they do not use.

✅ Example (Bad):

interface Worker {
    void work();
    void eat();
}

class Robot implements Worker {
    public void work() { }
    public void eat() { // Robots don’t eat!
        throw new UnsupportedOperationException();
    }
}


👉 Violates ISP.

✅ Example (Good):

interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {
    public void work() { }
    public void eat() { }
}

class Robot implements Workable {
    public void work() { }
}


👉 Now clients depend only on what they need.

5. D → Dependency Inversion Principle (DIP)

📌 High-level modules should not depend on low-level modules. Both should depend on abstractions.

✅ Example (Bad):

class MySQLDatabase {
    public void connect() { }
}

class UserService {
    private MySQLDatabase db = new MySQLDatabase();
}


👉 UserService is tightly coupled to MySQLDatabase.

✅ Example (Good):

interface Database {
    void connect();
}

class MySQLDatabase implements Database {
    public void connect() { }
}

class OracleDatabase implements Database {
    public void connect() { }
}

class UserService {
    private Database db;
    public UserService(Database db) {
        this.db = db;
    }
}


👉 Now we can switch databases easily (loose coupling).

🔹 Quick One-Liner Summary for Interview

S → One class = One responsibility.

O → Extend, don’t modify.

L → Subclasses should be replaceable by parent classes.

I → No unnecessary methods in interfaces.

D → Depend on abstractions, not concrete implementations.
```
