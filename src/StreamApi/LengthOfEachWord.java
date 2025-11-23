package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LengthOfEachWord {
    public static void main(String[] args) {
        String str="Big black bug bit a big black dog on his big black nose";
        System.out.println("return whose length is more than 3");
        List<Map.Entry<String, Integer>> wordlengthMoreThanThree = Arrays.stream(str.toLowerCase().split(" "))
                .distinct() ///for remove duplicate element in the output
                .map(word -> Map.entry(word, word.length()))
                .filter(x -> x.getValue() > 3)
                .collect(Collectors.toList());
        System.out.println(wordlengthMoreThanThree);

        System.out.println("------------------------------------------------------------------");
        System.out.println("Longest length word ");
        List<Map.Entry<String, Integer>> longestWord = Arrays.stream(str.toLowerCase().split(" "))
                .distinct() ///for remove duplicate element in the output
                .map(word -> Map.entry(word, word.length()))
                .sorted((a,b)-> b.getValue()- a.getValue())

                //.filter(x -> x.getValue() > 3)
                .collect(Collectors.toList());
          if(longestWord.size()>3){
              System.out.println(longestWord.get(0).getKey() +"   "+longestWord.get(0).getValue());
          }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Return all word with even length");
        List<Map.Entry<String, Integer>> evenLengthWord = Arrays.stream(str.toLowerCase().split(" "))
                .distinct() ///for remove duplicate element in the output
                .map(word -> Map.entry(word, word.length()))
                .filter(x -> x.getValue() %2==0)
                .collect(Collectors.toList());
        System.out.println(evenLengthWord);

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Count how many words start with a vowel vs consonant in a given sentence. ");
        Map<String, Long> result = Arrays.stream(str.toLowerCase().split("\\s+"))
                .filter(word -> !word.isEmpty()) // safety check
                .collect(Collectors.groupingBy(
                        word -> isVowel(word.charAt(0)) ? "vowel" : "consonant",
                        Collectors.counting()
                ));

        System.out.println(result);



    }
    private static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }
}
