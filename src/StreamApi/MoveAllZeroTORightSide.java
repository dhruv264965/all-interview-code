package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoveAllZeroTORightSide {
    public static void main(String[] args) {
        List<Integer> number = Arrays.asList(2,0,-1,4,0,3,-7,-5,9,0,7);
        List<Integer> moveAllZero = Stream.concat(number.stream().filter(n -> n != 0), number.stream().filter(n -> n == 0))
                .collect(Collectors.toList());
        System.out.println(moveAllZero);


        // another way if array is given
        int nums[]={2,0,-1,4,0,3,-7,-5,9,0,7};
        List<Integer> number1=Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer>moveAllZero1=  Stream.concat(number.stream().filter(n -> n != 0),
                        number.stream().filter(n -> n == 0))
                .collect(Collectors.toList());
         System.out.println(moveAllZero1);   // Copy the result back to the original array
//        for (int i = 0; i < nums.length; i++) {
//            nums[i] = moveAllZero1.get(i);
//        }
    }
}
