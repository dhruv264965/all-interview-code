package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindFirstAndFindAny {
  //findFirst and findAny are two terminal operations provided in Java's Stream API. Both are used to retrieve an
  // element from a stream, but they behave slightly differently based on the nature of the stream (sequential or parallel).
  //
  //Key Differences:
  //Behavior in Sequential Streams:
  //
  //findFirst: Returns the first element of the stream, respecting the encounter order (if defined).
  //findAny: Returns any element from the stream, but in a sequential stream, it usually behaves the same as findFirst.
  //Behavior in Parallel Streams:
  //
  //findFirst: Still respects the encounter order and returns the first element, which may involve more computational overhead.
  //findAny: May return any element, often optimized for performance in parallel streams without guaranteeing order.
    public static void main(String[] args) {


      ///Sequence stream
      List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana");

      // findFirst
      Optional<String> first = names.stream().findFirst();
      System.out.println("findFirst: " + first.orElse("Not Found")); // Output: Alice

      // findAny
      Optional<String> any = names.stream().findAny();
      System.out.println("findAny: " + any.orElse("Not Found")); // Output: Alice (in a sequential stream)



      //Parallel stream

      List<String> names1 = List.of("Alice", "Bob", "Charlie", "Diana");

      // Parallel Stream
      Optional<String> firstParallel = names.parallelStream().findFirst();
      System.out.println("Parallel findFirst: " + firstParallel.orElse("Not Found")); // Output: Alice

      Optional<String> anyParallel = names.parallelStream().findAny();
      System.out.println("Parallel findAny: " + anyParallel.orElse("Not Found")); // Output: Could be Alice, Bob, Charlie, or Diana

    }
}
