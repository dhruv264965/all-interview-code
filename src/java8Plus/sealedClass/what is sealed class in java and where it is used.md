# what is sealed class in java and where it is used

```text
A sealed class in Java is a class that restricts which other classes or interfaces may extend or implement it.
This feature was introduced in Java 15 as a preview and became a standard feature in Java 17.
public sealed class Vehicle permits Car, Bike {
}
final class Car extends Vehicle {  // no one can extend
}
non-sealed class Bike extends Vehicle { // other class can extend
}

sealed → Declares the base class as sealed.
permits → Specifies which classes are allowed to extend it.
All permitted subclasses must be in the same module (or same package if no module system).

 Why Use Sealed Classes?
✅ To control inheritance and prevent unwanted extension.
✅ Enforce domain modeling rules (like an enum but more flexible).
✅ Improve exhaustiveness checking in switch statements.


📍 Where It Is Used?
🔐 1. Security-Sensitive APIs
Restrict who can extend your critical base classes.

🧠 2. Domain Modeling
E.g., represent different types of payments:
public sealed interface Payment permits CreditCard, UPI, Cash {}

public final class CreditCard implements Payment {}
public final class UPI implements Payment {}
public final class Cash implements Payment {}
Now you can do:
switch (payment) {
    case CreditCard c -> processCard(c);
    case UPI u -> processUPI(u);
    case Cash ca -> processCash(ca);
}
🔍 3. Exhaustive switch with records + pattern matching
Used with record types and pattern matching to make code more type-safe.

| Type         | Extensible? | Restriction                              |
| ------------ | ----------- | ---------------------------------------- |
| `final`      | ❌ No        | Cannot be subclassed                     |
| `abstract`   | ✅ Yes       | Can be subclassed freely                 |
| `sealed`     | ✅ Limited   | Only allowed types via `permits`         |
| `non-sealed` | ✅ Yes       | Removes restriction from a sealed parent |

“Sealed classes in Java allow us to tightly control the class hierarchy. It’s especially useful in domain-driven design
where we want to define a closed set of subclasses—like different types of payment methods, user roles, or shapes.
 It also helps the compiler perform better exhaustiveness checks in switch statements.”



```
