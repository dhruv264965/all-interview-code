# What are the differences between HashMap and Hashtable

```text
| Feature              | **HashMap**                                      | **Hashtable**                                |
| -------------------- | ------------------------------------------------ | -------------------------------------------- |
| **Thread Safety**    | ❌ Not thread-safe                                | ✅ Thread-safe (synchronized methods)         |
| **Performance**      | ✅ Faster (no synchronization overhead)           | ❌ Slower (due to synchronized methods)       |
| **Null Keys/Values** | ✅ Allows 1 `null` key and multiple `null` values | ❌ **Does not allow** any `null` key or value |
| **Legacy?**          | ❌ Not legacy (Java 1.2)                          | ✅ Legacy class (Java 1.0)                    |
| **Iterator**         | ✅ `Iterator` (fail-fast)                         | ❌ `Enumerator` (not fail-fast)               |
| **Preferred Use**    | Modern applications                              | Rarely used; replaced by `ConcurrentHashMap` |

🔍 Code Example: Null Behavior
✅ HashMap allows null:

import java.util.HashMap;

public class HashMapNullExample {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put(null, "NullKey");        // 1 null key allowed
        map.put("A", null);              // null value allowed
        System.out.println(map);
    }
}
🧾 Output:

{null=NullKey, A=null}
❌ Hashtable throws NullPointerException:

import java.util.Hashtable;

public class HashtableNullExample {
    public static void main(String[] args) {
        Hashtable<String, String> table = new Hashtable<>();
        table.put(null, "NullKey");     // Throws exception
    }
}
🛑 Runtime Exception:

Exception in thread "main" java.lang.NullPointerException
🧵 Thread Safety and Modern Recommendation
❌ Hashtable synchronizes every method, which is inefficient.

✅ Preferred replacement for multi-threaded environments:

Use ConcurrentHashMap instead of Hashtable.
```
