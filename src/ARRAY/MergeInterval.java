package ARRAY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeInterval {  // O(n*log(n)) Time and O(n) Space
    public static int[][] mergeInterval(int[][] intervals){
      // step 1- Sort the interval
        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));
        List<int []> result=new ArrayList<>();
        int[] current=intervals[0];
        result.add(current);

        // Step 2 - Merge interval
        for(int[] interval:intervals){
            if(interval[0]<=current[1]){ // overlap
                current[1]= Math.max(interval[1],current[1]);
            }
            else {
                current=interval;  // no overlap
                result.add(current);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
    public static void main(String[] args) {
        int[][] intervals={{1,3},{2,6},{8,10},{15,18}};
        int[][] result=mergeInterval(intervals);
        for (int [] arr: result){
            System.out.print("["+arr[0]+" "+arr[1]+"]");
        }





    }
}
//Intervals: [1,3] and [2,6]
//Current merged interval: [1,3]
//So current[1] = 3
//Next interval: [2,6]
//So interval[0] = 2
//if (interval[0] <= current[1])   // 2 <= 3