package ARRAY;
// Sorting → O(N log N)
//Traversal → O(N)
//Total → O(N log N)
import java.util.Arrays;

// Problem Statement: We are given two arrays that represent the arrival and departure times of trains that stop at the
// platform. We need to find the minimum number of platforms needed at the railway station so that no train has to wait.
// Input:  N=6,
//arr[] = {9:00, 9:45, 9:55, 11:00, 15:00, 18:00}
//dep[] = {9:20, 12:00, 11:30, 11:50, 19:00, 20:00}
//Output: 3
//Explanation: There are at-most three trains at a time. The train at 11:00 arrived but the trains which had arrived
// at 9:45 and 9:55 have still not departed. So, we need at least three platforms here.
public class MinPatformRequried {
    public static void main(String[] args) {
        int[] arr = {900, 945, 955, 1100, 1500, 1800};
        int[] dep = {920, 1200, 1130, 1150, 1900, 2000};
        int platform=0;
        int result=0;
        Arrays.sort(arr);   // {900, 945, 955, 1100, 1500, 1800};
        Arrays.sort(dep);    //{920,1130,1150,1200,1900,2000};
// sort karne ke baad hum arrival wale se start kararnge and count ++  and i++ karte jayenge and jab hame dep wala milega then count --
// karnege and j++ karnege

        int n=arr.length;
        int i=0,j=0;
        while (i<n){
            if(arr[i]<=dep[j]){  // Why arr[i] <= dep[j] and not < ?
                //  Because if a train arrives exactly when another departs, we still need a platform at that instant.
                platform++;
                i++;
            }
            else if(arr[i]>dep[j]) {
                platform--;
                j++;
            }
            result=Math.max(result,platform);
        }

        System.out.println(result);

    }
}
//| Time Event   | Platform Count |
//| ------------ | -------------- |
//| 9:00 Arrive  | 1              |
//| 9:20 Depart  | 0              |
//| 9:45 Arrive  | 1              |
//| 9:55 Arrive  | 2              |
//| 11:00 Arrive | 3 ✅ (Maximum)  |
