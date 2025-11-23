package StreamApi;

import java.util.Arrays;
import java.util.List;

public class StringStartWithNumber {
    public static void main(String[] args) {
        List<String>list= Arrays.asList("apple","4banana","3mongo","pineapple");
        List<String> list1 = list.stream().filter(str -> Character.isDigit(str.charAt(0))).toList();
        System.out.println(list1);
    }
}
