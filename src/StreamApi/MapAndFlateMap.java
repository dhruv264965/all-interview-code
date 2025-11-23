package StreamApi;

import java.util.List;
import java.util.stream.Collectors;

//ey Differences:
//map:
//
//Transforms each element of the stream into a single output element.
//The output is a Stream<T> where T is the transformed type.
//Often used when the function produces a one-to-one mapping.
//flatMap:
//
//Transforms each element into a stream of elements, then flattens all the streams into a single stream.
//The output is a "flat" Stream<T> from a nested structure.
//Often used when the function produces multiple elements or a collection.
//When to Choose:
//Use map when each input maps to exactly one output (e.g., converting or transforming objects).
//Use flatMap when each input maps to multiple outputs or needs to flatten nested structures (e.g., List<List<T>> to List<T>).
public class MapAndFlateMap {
    public static void main(String[] args) {
        List<String> sentences = List.of("Hello world", "Java Streams", "Map vs FlatMap");

        // Using map
        List<List<String>> wordsWithMap = sentences.stream()
                .map(sentence -> List.of(sentence.split(" ")))
                .collect(Collectors.toList());
        System.out.println("Using map: " + wordsWithMap);
        // Output: [[Hello, world], [Java, Streams], [Map, vs, FlatMap]]

        // Using flatMap
        List<String> wordsWithFlatMap = sentences.stream()
                .flatMap(sentence -> List.of(sentence.split(" ")).stream())
                .collect(Collectors.toList());
        System.out.println("Using flatMap: " + wordsWithFlatMap);
        // Output: [Hello, world, Java, Streams, Map, vs, FlatMap]

    }
}
