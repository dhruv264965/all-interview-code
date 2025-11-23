package HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortCharacterByFreqAndLexical {

        static String frequencySort(String s) {
            // Step 1: Count the frequency of each character
            Map<Character, Integer> hp = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                hp.put(ch, hp.getOrDefault(ch, 0) + 1);
            }

            // Step 2: Convert map to a list
            List<Map.Entry<Character, Integer>> list = new ArrayList<>(hp.entrySet());

            // Step 3: Sort the list using a lambda expression
            list.sort((a, b) -> {
                int freqComparison = b.getValue() - a.getValue(); // Sort by frequency in descending order
                if (freqComparison != 0) {
                    return freqComparison; // If frequencies are different, sort by frequency
                }
                return a.getKey().compareTo(b.getKey()); // If frequencies are the same, sort lexicographically
            });
            System.out.println(list);
            for(int i=0;i< list.size();i++) {
                System.out.print(list.get(i).getKey()+"");
            }
            System.out.println();
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Character,Integer> e : list) {
                for (int i = 0; i < e.getValue(); i++) sb.append(e.getKey());
            }

            System.out.println(sb.toString()); // eeabrt
            // Step 4: Build the sorted result string
//            StringBuilder result = new StringBuilder();
//            for (Map.Entry<Character, Integer> entry : list) {
//                char character = entry.getKey();
//                int frequency = entry.getValue();
//                for (int i = 0; i < frequency; i++) {
//                    result.append(character); // Append character 'frequency' times
//                }
//            }
//            return result.toString();
            return "";
        }

        public static void main(String[] args) {
            String s = "traeeb";
            String sortedString = frequencySort(s);
            System.out.println(sortedString);  // Output: "bbAa"
        }
    }
