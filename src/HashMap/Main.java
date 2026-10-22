package HashMap;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("a", 3);
        map.put(null, 4);

        System.out.println(map.size());
        System.out.println(map.get("a"));
        System.out.println(map.get("z")); // null
        System.out.println(map.putIfAbsent("b", 9)); //
    }
}
//map.size() is 3. ✅ Keys are a, b and null. The second put("a", 3) replaces the value instead of adding an entry.
//map.get("a") is 3. ✅ The old value 1 was overwritten.
//map.get("z") is null, not an error. ❌ get returns null for a missing key and doesn’t throw. This also means null is ambiguous: the key may be missing, or it may be mapped to null. Use containsKey() or getOrDefault("z", 0) to tell them apart.
//map.putIfAbsent("b", 9) returns 2, not false. ❌ It returns the existing value if the key is present (and doesn’t replace it). It returns null if the key was absent and the new value was inserted. Don’t confuse it with Set.add(), which returns a boolean.