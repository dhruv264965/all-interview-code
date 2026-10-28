# Internal working to set and why set not allow duplicate

```text
✅ Why Set Does Not Allow Duplicates
🔹 1. Definition & Purpose
The primary purpose of a Set is to represent a collection of unique elements — just like a mathematical set.

In math, a set like {1, 2, 3} cannot contain duplicates.

Java's Set follows the same principle: no repeated elements.

| Type            | How It Enforces Uniqueness                                                             |
| --------------- | -------------------------------------------------------------------------------------- |
| `HashSet`       | Uses a **`HashMap`** internally; values stored as keys.                                |
| `TreeSet`       | Uses a **Red-Black Tree** and compares elements using `compareTo()` or a `Comparator`. |
| `LinkedHashSet` | Uses `HashMap` with a linked list to preserve insertion order.                         |


👉 Example (How HashSet avoids duplicates):
Set<String> set = new HashSet<>();
set.add("A"); // Added
set.add("B"); // Added
set.add("A"); // Ignored (duplicate)

System.out.println(set);  // Output: [A, B]
Internally:

HashSet calls hashCode() and equals() methods.

If equals() returns true, the object is already in the set, so it’s not added again.

---------------------------------------------------------------------------------------------------------
Make Custom class and show how it is not allow duplicate
Example: Custom Class with hashCode() and equals()
import java.util.*;

class Student {
    String name;
    int id;

    Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Overriding equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return this.id == other.id && this.name.equals(other.name);
    }

    // Overriding hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return name + "-" + id;
    }
}

public class HashSetDemo {
    public static void main(String[] args) {
        Set<Student> set = new HashSet<>();

        Student s1 = new Student("Alice", 101);
        Student s2 = new Student("Alice", 101); // same data as s1

        set.add(s1);
        set.add(s2); // ❌ Duplicate — will NOT be added

        System.out.println(set); // Output: [Alice-101]
    }
}
🧠 Explanation:
hashCode(): HashSet uses it to find the bucket location.

equals(): If two objects are in the same bucket, it checks whether they are equal.

Since s1.equals(s2) returns true, s2 is not added.

----------------------------------------------------------------------------------------------------------
Custom class where we are override equals and hascode method . so duplicate will allow
No equals() / hashCode() — Objects Treated as Different

import java.util.*;

class Student {
    String name;
    int id;

    Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return name + "-" + id;
    }
}

public class HashSetWithoutOverrides {
    public static void main(String[] args) {
        Set<Student> set = new HashSet<>();

        Student s1 = new Student("Alice", 101);
        Student s2 = new Student("Alice", 101); // Looks same, but is different object

        set.add(s1);
        set.add(s2); // ✅ Added — HashSet can't detect it’s a duplicate

        System.out.println(set); // Output: [Alice-101, Alice-101]
    }
}
By default, Java uses object memory address to calculate hash and equality:

s1 == s2 → false

s1.hashCode() != s2.hashCode() → likely different

Since Java doesn’t know they "look" the same, both go into the HashSet.


```
