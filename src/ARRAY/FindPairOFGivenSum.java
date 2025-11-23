package ARRAY;

import java.util.HashMap;

public class FindPairOFGivenSum {
    public static void main(String[] args) {
        int arr[] = {1, 3, 7, 8, 4, 9,2};
        int sum = 10;
        int count=0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int sumComp = sum - arr[i];
          //  int diffComp = arr[i] - sum;
          //  int diffComp1 = arr[i] + sum;

            if (map.containsKey(sumComp)) {
                System.out.println("(" + sumComp + " " + arr[i] + ")");
                count++;
          }
//            else if (map.containsKey(diffComp)) {
//                System.out.println("(" + diffComp + " " + arr[i] + ")");
//                count++;
//            } else if (map.containsKey(diffComp1)) {
//                System.out.println("(" + diffComp + " " + arr[i] + ")");
//                count++;
//            }
            map.put(arr[i], i);

        }
        System.out.println(count);
    }

    }

