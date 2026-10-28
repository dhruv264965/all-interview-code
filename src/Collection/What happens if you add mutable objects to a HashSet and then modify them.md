# What happens if you add mutable objects to a HashSet and then modify them

```text
 “If you add a mutable object to a HashSet, and then mutate it in a way that affects equals() or hashCode(),
 the set can no longer find or remove that object correctly. This breaks the expected behavior of the set and
  can lead to duplicates. That’s why it’s recommended to use immutable objects as elements in a HashSet.”

  You break the contract of hashCode() and equals() — and this can lead to:
  ❌ Inability to find, remove, or detect duplicates correctly
  ❌ The object becoming "invisible" to the HashSet
  ❌ Violations of set behavior (e.g., duplicate entries)

  🔍 Why does this happen?
  HashSet uses a HashMap internally, which:
  Stores elements based on their hash code (bucket location)
  Uses equals() to detect duplicates
  If you change the fields of the object after adding it, and those fields affect hashCode() or equals(), then:
  Its hashCode changes, so:
  It’s now in the “wrong” bucket
  The HashSet can’t find it anymore for operations like contains() or remove()

  class Person {
      String name;

      Person(String name) {
          this.name = name;
      }

      @Override
      public int hashCode() {
          return name.hashCode();
      }

      @Override
      public boolean equals(Object o) {
          if (this == o) return true;
          if (!(o instanceof Person)) return false;
          Person p = (Person) o;
          return name.equals(p.name);
      }
  }

  public class Main {
      public static void main(String[] args) {
          Set<Person> set = new HashSet<>();
          Person p = new Person("Alice");

          set.add(p);
          System.out.println(set.contains(p)); // true ✅

          // Now mutate the object
          p.name = "Bob";

          System.out.println(set.contains(p)); // false ❌
          set.remove(p);                       // fails silently ❌
          set.add(p);                          // added again ✅

          System.out.println(set.size());      // 2 ❌ Duplicate!
      }
  }

```
