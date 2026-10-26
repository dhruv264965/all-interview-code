# suppose we have parnet class and method void start. and if we override the method in child class ..then we change access modifier and even can we modify function

```text
suppose we have parnet class and method void start. and if we override the method in child class
then we change access modifier and even can we modify function->

✅ 1. Can we change the access modifier in the overridden method?
Yes — but only to a more accessible (less restrictive) modifier, not more restrictive.

| Parent Access Modifier    | Allowed in Child                           | Not Allowed                         |
| ------------------------- | ------------------------------------------ | ----------------------------------- |
| `public`                  | `public`                                   | `protected`, `default`, `private` ❌ |
| `protected`               | `protected`, `public`                      | `default`, `private` ❌              |
| default (package-private) | `default`, `protected`, `public`           | `private` ❌                         |
| `private`                 | Not inherited, so overriding doesn’t apply |                                     |

✅ 2. Can we change the return type?
Yes — but the return type should be the same or a subclass (called covariant return type).
Example:
class Parent {
    Number start() { return 1; }
}
class Child extends Parent {
    Integer start() { return 2; } // ✅ Allowed, Integer is a subclass of Number
}

✅ 3. Can we change the method signature?
If you change only the parameter types, it's not overriding, it's overloading.
For overriding, the method name, parameters, and return type must match (return type can be covariant).

✅ 4. Can we change the function body (implementation)?
Yes, absolutely — that's the whole point of overriding: to change the logic in the child class.

✅ 5. Can we add throws clause?
You can throw same or fewer/more specific checked exceptions.
You can throw any unchecked exception (like RuntimeException).

📌 Example:
class Parent {
    protected void start() {
        System.out.println("Parent Start");
    }
}
class Child extends Parent {
    @Override
    public void start() { // Access changed from protected → public ✅
        System.out.println("Child Start");
    }
}
❌ Invalid Example (will cause compile error):
class Parent {
    public void start() {}
}
class Child extends Parent {
    @Override
    protected void start() {} // ❌ Cannot reduce visibility from public to protected
}
✅ Summary Table:
| Parent Access Modifier    | Allowed in Child                           | Not Allowed                         |
| ------------------------- | ------------------------------------------ | ----------------------------------- |
| `public`                  | `public`                                   | `protected`, `default`, `private` ❌ |
| `protected`               | `protected`, `public`                      | `default`, `private` ❌              |
| default (package-private) | `default`, `protected`, `public`           | `private` ❌                         |
| `private`                 | Not inherited, so overriding doesn’t apply |                                     |


```
