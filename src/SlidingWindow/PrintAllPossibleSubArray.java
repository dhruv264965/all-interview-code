package SlidingWindow;

public class PrintAllPossibleSubArray {
    static void printAllPossibleSubArray(int arr[]) {
        int n = arr.length;
        for (int st = 0; st < n; st++) {
            for (int end = st; end < n; end++) {
                System.out.print("[");
                for (int i = st; i <= end; i++) {
                    System.out.print(arr[i]+(i == end ? "" : ","));

                }
                System.out.print("]");
                System.out.println();

            }

        }
    }
    public static void main(String[] args) {
        int arr[]={1,3,1,5,8};
        printAllPossibleSubArray(arr);
    }
}
