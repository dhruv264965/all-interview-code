package JAVA8Features;

import java.sql.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecondLargest {
    public static void main(String[] args) {
        //APPROCH -1

//        int arr[]={1,23,3,4,77,32,76};
//        int secondLargest= Arrays.stream(arr)
//                .boxed()
//                .sorted(Comparator.reverseOrder())
//                .skip(1)
//                .findFirst()
//                .get();
//        System.out.println(secondLargest);

        //APPROCH-- 2

        List<Integer>list=Arrays.asList(1,23,3,4,77,32,76);
       List<Integer>seclargest= list.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .limit(1).collect(Collectors.toList());
        System.out.println(seclargest);

    }
}
