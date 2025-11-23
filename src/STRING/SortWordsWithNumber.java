package STRING;

import java.util.*;
import java.util.stream.Collectors;

public class SortWordsWithNumber {
    public static void main(String[] args) {
        String str="code3 love1 to2";
        // Solution using Stream Api
//        String collect = Arrays.stream(str.split("\\s+"))  //Split the input into words
//                .sorted(Comparator.comparingInt(words -> Integer.parseInt(words.replaceAll("\\D", "")))) // Sort by number
//                .map(words -> words.replaceAll("\\d", "")) // Remove numbers
//                .collect(Collectors.joining(" ")); // Join words back into a string
//        System.out.println(collect);   // Output: love to code

        String[] words=str.split("\\s+");
        // Custom comparator to sort based on the number part
        Comparator<String>sortWordsByNumber= new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // Extract the numeric part
                int num1=Integer.parseInt(o1.replaceAll("\\D+",""));
                int num2=Integer.parseInt(o2.replaceAll("\\D+",""));
                return num1-num2;
            }
        };
//        // Convert array to list for sorting
        List<String> wordList = new ArrayList<>();
        Collections.addAll(wordList, words);

        // Sort based on the numeric part
        Collections.sort(wordList, sortWordsByNumber);
        System.out.println("Sort based on numeric number "+ wordList);
        // Construct the output string
        StringBuilder output = new StringBuilder();
        for (String word : wordList) {
            output.append(word.replaceAll("\\d", "")).append(" ");
        }

        // Remove the trailing space
        String result = output.toString().trim();
        System.out.println(result); // Output: love to code

    }
}
