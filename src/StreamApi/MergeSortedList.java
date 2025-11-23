package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeSortedList {
    public static void main(String[] args) {
        List<Integer> list= Arrays.asList(1,2,3,4,100);
        List<Integer>list1= Arrays.asList(6,78,9,99);
        List<Integer>mergeSortedList= Stream.concat(list.stream().sorted(),list1.stream().sorted()).toList();
        System.out.println(mergeSortedList);
    }
}
