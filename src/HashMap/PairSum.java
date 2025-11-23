package HashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PairSum {

    static void printPairs(int[] arr, int n, int sum) {
        // Store counts of all elements in map m
        Map<Integer, Integer> m = new HashMap<>();

        // Traverse through all elements
        for (int i = 0; i < n; i++) {
            // Search if a pair can be formed with arr[i].
            int rem = sum - arr[i];
            if (rem == arr[i]) {
                // Check if the complement is in the map and occurs only once
                if (m.containsKey(rem) && m.get(rem) == 1) {
                    System.out.println("(" + rem + ", " + arr[i] + ")");
                }
            } else if (m.containsKey(rem) && !m.containsKey(arr[i])) {
                // Check if the complement is in the map and the current element is not in the map
                System.out.println("(" + rem + ", " + arr[i] + ")");
            }
            m.put(arr[i], m.getOrDefault(arr[i], 0) + 1); // Update the map with the current element's count
        }
    }
    public static void findPairsWithGivenSum(int[] arr, int sum) {
        // HashMap to store the element and its index
        HashMap<Integer, Integer> map = new HashMap<>();

        // Iterate over the array and find pairs
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int complement = sum - num;

            // Check if the complement exists in the map (i.e., we have seen it before)
            if (map.containsKey(complement)) {
                int index = map.get(complement); // Get the index of the complement
                System.out.println("(" + complement + ", " + num + ")");
            }

            // Add the current element and its index to the map
            map.put(num, i);
        }
    }

    // Driver function to test the above function
    public static void main(String[] args) {
        int[] arr = {1, 5, 7, -1, 5, 3, 3, 3};
        int n = arr.length;
        int sum = 6;
        printPairs(arr, n, sum);
       // findPairsWithGivenSum(arr,sum);
    }
}
//output----   (1, 5)
//             (7, -1)
//             (3, 3)

