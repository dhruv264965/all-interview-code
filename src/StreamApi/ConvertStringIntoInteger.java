package StreamApi;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ConvertStringIntoInteger {
    public static void main(String[] args) {
        List<String> numbers = Arrays.asList("10", "20", "abc", "30");
        List<Integer> list = numbers.stream()
                        .map(s->{
                          try {
                              return Integer.parseInt(s);
                          }
                          catch (Exception e){
                              return -1;
                          }
                        })
                                .toList();
        System.out.println(list);
    }
}
