package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamIntermidateOperartor {
    public static void main(String[] args) {
        // Intermedidate operation transform one stream to another steam
        // they are lazy means they don't execute until terminal operation is invoked

        // 1. Filter
        List<String>list= Arrays.asList("Akshit","Ram","Shayam","Ghanshayam","Akshit");
        Stream<String> a = list.stream().filter(x -> x.startsWith("A")); // no filtering at this point

        long res=list.stream().filter(x -> x.startsWith("A")).count();
        System.out.println(res);

        // 2. Map
        Stream<String> stringStream = list.stream().map(x -> x.toLowerCase());
        Stream<String> stringStream1=  list.stream().map(String::toLowerCase);

        // 3 . Sorted
        Stream<String> sorted = list.stream().sorted();
        Stream<String> sortedStreamUsingComparator = list.stream().sorted((x, b) -> x.length() - b.length());

        // 4. Distinct
        System.out.println(list.stream().filter(x->x.startsWith("A")).distinct().count());

        // 5. Limit
        List<Integer> limit = Stream.iterate(1, x -> x + 1).limit(100).collect(Collectors.toList());
        System.out.println(limit);

        // 6. Skip
        List<Integer> limit1 = Stream.iterate(1, x -> x + 1).skip(10).limit(100).collect(Collectors.toList());
     //   System.out.println(limit1);

        // 7. peek
        // Performs an action on each element as it is consumed.
        Stream.iterate(1, x -> x + 1).skip(10).limit(100).peek(System.out::print).count();

        // 8. flatMap
        // Handle streams of collections, lists, or arrays where each element is itself a collection
        // Flatten nested structures (e.g., lists within lists) so that they can be processed as a single sequence of elements
        // Transform and flatten elements at the same time.
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("orange", "kiwi"),
                Arrays.asList("pear", "grape")
        );
        System.out.println(listOfLists.get(1).get(1));
        System.out.println(listOfLists.stream().flatMap(x -> x.stream()).map(String::toUpperCase).toList());
        List<String> sentences = Arrays.asList(
                "Hello world",
                "Java streams are powerful",
                "flatMap is useful"
        );
        System.out.println(sentences
                .stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .map(String::toUpperCase)
                .toList());
    }
}
