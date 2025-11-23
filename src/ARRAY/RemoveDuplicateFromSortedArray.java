package ARRAY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicateFromSortedArray {
    public static void main(String[] args) {
        Integer arr[]={1,2,2,3,4,4};  // output {1,2,3,4,null,null}
        int index=1;
        for (int i=1;i<arr.length;i++){
            if(!arr[i].equals(arr[i-1])){
                arr[index++]=arr[i];
            }
        }
        for (int i = index; i < arr.length; i++) {
            arr[i] = null; // or any marker value
        }
        System.out.println(Arrays.toString(arr));


        // using strean
        Integer arr1[]={1,2,2,3,4,4};
        List<Integer> list = Arrays.stream(arr1).distinct() .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list);
        while (list.size()< arr1.length){
            list.add(null);
        }
        Integer[] result = list.toArray(new Integer[0]);

        System.out.println(Arrays.toString(result));


    }
}
