package JAVA8Features.methodReference;

import java.util.function.BiFunction;

public class MaxWithMethodReference {
    public static void main(String[] args) {
        // Method reference to Integer.max
        BiFunction<Integer, Integer, Integer> maxFunction = Integer::max;

        // Apply the function
        int max = maxFunction.apply(10, 20);

        System.out.println("Maximum: " + max); // Output: Maximum: 20
    }
}

