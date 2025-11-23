package ARRAY;

public class LeaderArray {
    static int leaderArray(int arr[]){
        int leaderArray=arr[arr.length-1];
        for(int i=arr.length-2;i>=0;i--){
            if(leaderArray<arr[i]){
                leaderArray=arr[i];
            }
        }
        return leaderArray;
    }

    public static void main(String[] args) {
        int arr[]={2,6,7,1,3};
        System.out.println(leaderArray(arr));
    }
}
