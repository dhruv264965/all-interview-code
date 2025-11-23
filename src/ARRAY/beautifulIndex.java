package ARRAY;

public class beautifulIndex {
    public static void main(String[] args) {
        int A[] = {2, 8, 9, 0, 4};
        int N = A.length;
        long totalSum = 0;
        for (int num : A) {
            totalSum += num;


            long prefixSum = 0;
            for (int i = 0; i < N; i++) {
                // Check condition: 2 * prefixSum == totalSum - A[i]
                if (2 * prefixSum == totalSum - A[i]) {
                    System.out.println(i + 1);  // Convert to 1-based index
                }
                prefixSum += A[i];
            }
        }
    }
}
