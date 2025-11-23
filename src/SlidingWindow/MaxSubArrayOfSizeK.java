package SlidingWindow;

import java.util.HashSet;

public class
MaxSubArrayOfSizeK {
    static int maxSubArrayOfSizeK(int arr[], int n, int k) { ///T.C.: O(N) S.C.: O(1)
        int i = 0;
        int j = 0;
        int sum = 0;
        int max = 0;
        if (n < k) return -1;
        while (j < n) {
            sum += arr[j];
            if (j - i + 1 < k) { ///yaha pe hum starting ke element check kar rhe hai jb tak size k ke equal nh ho hata
                j++;
            } else if (j - i + 1 == k) {
                max = Math.max(max, sum);
                sum -= arr[i];
                i++;
                j++;
            }
        }
        return max;

    }

    static int maxSubArrayOfSizeKBruteForce(int arr[], int n, int k) {  ///T.C.: O(N^2) S.C.: O(1)
        int max = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < i + k && j < n; j++) {
                sum += arr[j];
            }
            max = Math.max(max, sum);

        }
        return max;
    }
    public static void main(String[] args) {
        int arr[]={4,7,3,1,1,9,9};
        int n=7;
        int k=3;
        System.out.println(maxSubArrayOfSizeK(arr,n,k));
       // System.out.println(maxSubArrayOfSizeKBruteForce(arr,n,k));
    }
}
