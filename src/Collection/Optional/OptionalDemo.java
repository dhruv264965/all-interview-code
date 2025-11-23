package Collection.Optional;

import java.util.Optional;
// "Optional.of() throws NullPointerException if the value is null, while Optional.ofNullable()
// returns an empty Optional when the value is null. We use of() when we are sure the value
// is non-null, and ofNullable() when the value might be null."
public class OptionalDemo {
    public static void main(String[] args) {
        String s = "dhruv";
        String s1 = null;
        Optional<String> opt1 = Optional.of(s); // ✅ works fine
     //   Optional<String> opt2 = Optional.of(s1); // ❌ NullPointerException

        Optional<String> opt3 = Optional.ofNullable(s);  // Optional[dhruv]
        Optional<String> opt4 = Optional.ofNullable(s1); // Optional.empty
        System.out.println(opt1);
        System.out.println(opt3);
        System.out.println(opt4);

    }
}
