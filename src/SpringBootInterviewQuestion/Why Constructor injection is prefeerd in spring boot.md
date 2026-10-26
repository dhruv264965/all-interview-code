# Why Constructor injection is prefeerd in spring boot

```text
why- make immuatble,compile time safty if we missing any parameter give nullPointerException
✅ 1. Immutability
Constructor injection ensures dependencies are final and set once at object creation.

This makes the bean immutable, which is safer and easier to reason about.

@Service
public class MyService {
    private final UserRepository repo;

    public MyService(UserRepository repo) {
        this.repo = repo;
    }
}
🔒 repo can't be changed after creation — that’s a good thing!

✅ 2. Mandatory Dependency Enforcement
With constructor injection, Spring fails fast if a required dependency is missing.

Unlike field or setter injection, it guarantees that the object can't be created without its dependencies.

🟢 This avoids NullPointerException at runtime due to uninitialized fields.
| Aspect                   | Constructor Injection ✅ | Field Injection 🚫 |
| ------------------------ | ----------------------- | ------------------ |
| Immutability             | ✅ Yes                   | ❌ No               |
| Testability (with mocks) | ✅ Easy                  | ❌ Hard             |
| Visibility of dependency | ✅ Clear in constructor  | ❌ Hidden           |
| Compile-time safety      | ✅ Yes                   | ❌ No               |
| Reflection overhead      | ❌ No                    | ✅ Yes              |


```
