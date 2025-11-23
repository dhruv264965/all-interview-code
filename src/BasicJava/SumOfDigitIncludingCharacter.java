package BasicJava;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SumOfDigitIncludingCharacter {
    public static void main(String[] args) {
        String s="abe13h1d24i";
        int sum=0;
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(Character.isDigit(ch)){
                sum+=Character.getNumericValue(ch);
            }
        }
        System.out.println(sum);

        //Using StreamApi

        int summ=s.chars() //This method converts the string s into a stream of characters (IntStream), where each character is represented by its Unicode value.
                .filter(Character::isDigit)//Filters the stream to include only characters that are digits, using the Character.isDigit method.
                .map(Character::getNumericValue)//Maps each character in the stream to its numeric value. For example, the character '1' becomes the integer 1, '3' becomes 3, and so on.
                .sum();
        System.out.println(summ);

        String str = "Hello";
        Stream<Byte> stream = IntStream.range(0, str.getBytes().length)
                .mapToObj(i -> str.getBytes()[i]);
       stream.forEach(System.out::println);

        String ss = "example";
        Stream<Character> charStream = ss.chars()
                .mapToObj(c -> (char) c);  // Converts IntStream to Stream<Character>

        charStream.forEach(System.out::println);


        String s1 = "convert this string to stream";
        Stream<String> wordStream = Arrays.stream(s1.split(" "));  // Splits the string into words
        wordStream.forEach(System.out::println);
        ss.codePoints()  // Converts the string into a stream of Unicode code points
                .forEach(System.out::println);
    }
}
