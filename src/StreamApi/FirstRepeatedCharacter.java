package StreamApi;

import java.util.HashSet;

public class FirstRepeatedCharacter {
    public static void main(String[] args) {
        String str="Java Steam is very good concept";
        HashSet<Character>set=new HashSet<>();
        Character character = str.chars()  // str.chars() → Converts the string into an IntStream (because char is internally stored as an integer in Java).
                .mapToObj(ch -> (char) ch)//.mapToObj(c -> (char) c) → Converts each integer (ASCII value) back to a Character object so we can use Java Streams properly.
                .filter(ch -> !set.add(ch))  //seen.add(ch) returns:
                                             //  true → If the character is added for the first time.
                                              // false → If the character already exists in the set.
                                              // Negating the result (!seen.add(ch)) means:
                                              //If a character is already in the set, it gets filtered (selected) as a repeated character.
                .findFirst()
                .orElse(null);
        System.out.println(character);
    }
}
