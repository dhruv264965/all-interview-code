package STRING;

import java.util.HashMap;
import java.util.Map;

public class WordsToNumber {

    // Map to store word to number mappings
    private static final Map<String, Integer> wordsToNumberMap = new HashMap<>();
    static {
        wordsToNumberMap.put("zero", 0);
        wordsToNumberMap.put("one", 1);
        wordsToNumberMap.put("two", 2);
        wordsToNumberMap.put("three", 3);
        wordsToNumberMap.put("four", 4);
        wordsToNumberMap.put("five", 5);
        wordsToNumberMap.put("six", 6);
        wordsToNumberMap.put("seven", 7);
        wordsToNumberMap.put("eight", 8);
        wordsToNumberMap.put("nine", 9);
        wordsToNumberMap.put("ten", 10);
        wordsToNumberMap.put("eleven", 11);
        wordsToNumberMap.put("twelve", 12);
        wordsToNumberMap.put("thirteen", 13);
        wordsToNumberMap.put("fourteen", 14);
        wordsToNumberMap.put("fifteen", 15);
        wordsToNumberMap.put("sixteen", 16);
        wordsToNumberMap.put("seventeen", 17);
        wordsToNumberMap.put("eighteen", 18);
        wordsToNumberMap.put("nineteen", 19);
        wordsToNumberMap.put("twenty", 20);
        wordsToNumberMap.put("thirty", 30);
        wordsToNumberMap.put("forty", 40);
        wordsToNumberMap.put("fifty", 50);
        wordsToNumberMap.put("sixty", 60);
        wordsToNumberMap.put("seventy", 70);
        wordsToNumberMap.put("eighty", 80);
        wordsToNumberMap.put("ninety", 90);

        wordsToNumberMap.put("hundred", 100);
        wordsToNumberMap.put("thousand", 1000);
        wordsToNumberMap.put("lakh", 100000); // New mapping for 'lakh'
    }

    public static int wordsToNumber(String input) {
        String[] words = input.split(" ");
        int result = 0;
        int current = 0;

        for (String word : words) {
            if (wordsToNumberMap.containsKey(word)) {
                int value = wordsToNumberMap.get(word);

                if (value >= 100) {
                    current *= value;
                } else {
                    current += value;
                }

                // Handle cases for thousand and lakh separately
                if (word.equals("thousand") || word.equals("lakh")) {
                    result += current;
                    current = 0;
                }
            }
        }

        // Add any remaining value
        return result + current;
    }

    public static void main(String[] args) {
        String s = "one lakh two thousand nine";
        int result = wordsToNumber(s);
        System.out.println("The number is: " + result);
    }
}
