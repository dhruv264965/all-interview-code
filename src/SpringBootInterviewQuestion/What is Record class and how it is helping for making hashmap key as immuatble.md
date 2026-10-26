# What is Record class and how it is helping for making hashmap key as immuatble

```text
Record- to store immutable data
aim- to reduce bioler plate code
automatically generate hashcode , equals, constructor
----------------

how help to make immutable hashmap
✅ Problem Before Java Records (Pre-Java 14)
When you use a custom class as a key in a HashMap, you must override:

equals() – to check logical equality between keys

hashCode() – to ensure correct hashing behavior

🔴 If you don’t override them properly and the object is mutable, your keys may behave unpredictably in
the HashMap.

✅ Why Immutability is Important for HashMap Keys
HashMap uses hashCode() to place the key in the correct bucket. If the key's fields change after
 insertion, the object
 might not be found again.

🔧 Traditional Way (Manual Class)

class User {
    private final String name;
    private final int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Must override equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        // custom equality logic
    }

    @Override
    public int hashCode() {
        // custom hashcode logic
    }
}
⚠️ Tedious and error-prone!

✅ Java record Makes This Easy
🔥 Just one line:

public record User(String name, int id) {}
What happens here?
When you define a record:

All fields are final by default → immutable

Java automatically generates:

equals()

hashCode()

toString()

Constructor and accessors (name(), id())

✅ Example Using Record as a HashMap Key

import java.util.HashMap;
import java.util.Map;

public class RecordAsKeyDemo {
    public static void main(String[] args) {
        record User(String name, int id) {}

        Map<User, String> map = new HashMap<>();

        User user1 = new User("Alice", 101);
        User user2 = new User("Bob", 102);

        map.put(user1, "Developer");
        map.put(user2, "Tester");

        // Lookup works because equals() and hashCode() are correct and immutable
        System.out.println(map.get(new User("Alice", 101))); // Output: Developer
    }
}
✅ Output:

Developer
✅ Why It Works Well
Feature	Traditional Class	Java record
Immutable fields	Manual	Built-in (final)
equals() method	Manual	Auto-generated
hashCode() method	Manual	Auto-generated
Constructor	Manual	Auto-generated

✅ Summary
✅ record is perfect for keys in a HashMap because:

All fields are final (immutable)

equals() and hashCode() are correctly implemented

✅ No risk of mutating key state after insertion

✅ Less boilerplate, more safety
```
