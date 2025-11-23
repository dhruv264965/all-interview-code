package StreamApi;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ImportantQuestionBeforeInterview {
    public static void main(String[] args) {
        //1.  remove vowel from String
         String str="HelloWorld";
        String removeVowel = str.chars()
                .filter(ch -> !"aeiouAEIOU".contains(String.valueOf((char) ch)))
                .mapToObj(ch -> String.valueOf((char) ch))
                .collect(Collectors.joining());
        System.out.println(removeVowel);

        //2. Convert List of name into Upper character
        List<String>names= Arrays.asList("dhruv","shukla");
        List<String> UpperCase = names.stream().map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(UpperCase);
        
        //3. Count the frequency of word in given string
        String str1="java is good and java is popular";
        Map<String, Long> wordFreq = Arrays.stream(str1.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(wordFreq);

        //4. Count the frequency of character in given string
        String str2="helloword";
        Map<String, Long> charFreq = Arrays.stream(str2.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(charFreq);

        //5. Length of each word in String
        String str3="java is good and popular";
        Map<String, Integer> lengthOfWord = Arrays.stream(str3.split(" "))
                .collect(Collectors.toMap(s->s,String::length));
        System.out.println(lengthOfWord);

        //6. Find First Non Repeating Character
        String str4="abcbda";
        Optional<Map.Entry<Character, Long>> firstNonRepeating = str4.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .findFirst();
        System.out.println(" Find First Non Repeating Character");
        System.out.println(firstNonRepeating.get());

        //7. Find First Repeating Character
        System.out.println(" Find First Repeating Character index");
        String str5="cabdbd";
        Optional<Map.Entry<Character, Long>> firstRepeating = str5.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() >1)
                .findFirst();
        System.out.println(firstRepeating.get());

        System.out.println(" Find First Repeating Character index");
        String str10="cabdbd";
        Optional<Map.Entry<Character, Long>> firstRepeatingindex = str10.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() >1)
                .findFirst();
        firstRepeatingindex.ifPresent(entry -> {
            char ch = entry.getKey();
            int index = IntStream.range(0, str10.length())
                    .filter(i -> str10.charAt(i) == ch)
                    .findFirst()
                    .orElse(-1);
            System.out.println("First repeating character '" + ch + "' found at index: " + index);
        });
        System.out.println(firstRepeatingindex.get());

        //8. Write a program to find second highest number
        int arr[]={3,6,4,2,7,9,9,1};
        Optional<Integer> secondHighest = Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .distinct()
                .skip(1)
                .findFirst();
        System.out.println(secondHighest.get());
        //9. Write a program to find nth smallest number
        int arr1[]={3,6,4,2,7,9,9,1};
        int n=3;
        Optional<Integer> nthSmallest = Arrays.stream(arr1)
                .boxed()
                .sorted()
                .distinct()
                .skip(n-1)
                .findFirst();
        System.out.println(nthSmallest.get());

        //10. Longest String in an array of String
        String str6="java is popular";
        Optional<String> maxLengthWord = Arrays.stream(str6.split(" "))
                .max(Comparator.comparingInt(String::length));
        System.out.println(maxLengthWord.get());

        //11. calculate Sum
        int x=543604;
        Integer sum = String.valueOf(x)
                .chars()
                .mapToObj(ch -> ch - '0')
                .reduce(0, (a, b) -> a + b);
        System.out.println(sum);
        int sum2 = String.valueOf(x)       // convert number to string
                .chars()             // IntStream of char codes
                .map(c -> c - '0')   // convert char to digit
                .sum();
        System.out.println(sum2);

        //12. Find total number of count for particular word from list
        List<String>list=Arrays.asList("java python","java htnl","java jp","js");
        long count = list.stream()
                .flatMap(word -> Arrays.stream(word.split(" ")))
                .filter(word -> word.equalsIgnoreCase("java"))
                .count();
        System.out.println(count);

        //13. Sum of first two number from list
        List<Integer>list1=Arrays.asList(10,20,30,40,50);
        int sum1 = list1.stream().limit(2)
                        .mapToInt(y-> y.intValue())
                                .sum();
        System.out.println(sum1);
        System.out.println("===========");

        //14. Sum of unique number in the list
        List<Integer>list2=Arrays.asList(10,20,30,40,50,20,30);
        Integer reduce = list2.stream().distinct()
                .reduce(0, (a, b) -> a + b);
        System.out.println(reduce);

        //15. Find 3rd Non Repeating Character
        String str7="aABbcdDefgh".toLowerCase(Locale.ROOT);
        Optional<Map.Entry<Character, Long>> thridNonRepeating = str7.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .skip(2)
                .findFirst();
        System.out.println(thridNonRepeating.get());


//        String str98="abcbda";
//        str98.chars().mapToObj(ch->(char)ch)


    }
}
