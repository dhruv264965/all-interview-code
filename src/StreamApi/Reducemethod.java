package StreamApi;

import java.util.Arrays;
import java.util.List;

//The reduce method in Java Streams is a terminal operation that aggregates elements of a stream into a single result.
// It combines the elements of the stream using an associative accumulation function, with or without an identity value.
public class Reducemethod {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Sum of numbers
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);

        System.out.println("Sum: " + sum);

        List<String> words = Arrays.asList("Java", "Stream", "Reduce");

        // Concatenate strings
        String concatenated = words.stream()
                .reduce("", (a, b) -> a + " " + b);

        System.out.println("Concatenated String:" + concatenated.trim());

    }
}
