package ARRAY;
import java.util.Arrays;  // Import to use Arrays.sort()

public class MergeTwoSortedArray {
    static void mergeTwoSortedArray(int arr1[], int arr2[], int n, int m, int ans[]) {
        int k = 0;
        int i = 0, j = 0;

        // First, sort both arrays   //only required when array is not sorted
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        // Merge the sorted arrays into ans[]
        while (i < n && j < m) {               //  arr1-{12, 18, 40, 600};
            if (arr1[i] < arr2[j]) {           // arr2-{47, 56, 89, 90};
                ans[k++] = arr1[i++];
            } else {
                ans[k++] = arr2[j++];
            }
        }

        // Copy remaining elements from arr1 (if any)
        while (i < n) {
            ans[k++] = arr1[i++];
        }

        // Copy remaining elements from arr2 (if any)
        while (j < m) {
            ans[k++] = arr2[j++];
        }
    }

    public static void main(String[] args) {
        // Unsorted arrays
        int arr1[] = {12, 600, 18, 40};
        int arr2[] = {56, 90, 89, 47};

        int n = arr1.length;
        int m = arr2.length;
        int ans[] = new int[n + m];

        // Merge and sort
        mergeTwoSortedArray(arr1, arr2, n, m, ans);

        // Print the merged sorted array
        for (int num : ans) {
            System.out.print(num + " ");
        }
    }
}
