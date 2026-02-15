package ARRAY;

public class StockBuyAndSell {
    public static void main(String[] args) {
        int arr[] = {7, 10, 1, 3, 6, 9, 2};
        int maxProfit = 0;
        // brute forcr
//explanation- hum kya karenge 1st loop zero index se start karenge and 2nd loop 1 index se start karenge
// ek index ko fix kar ke ham difference nikal lenge(j-i) ka  and max difference he maxprofit hoga.
// (10-7)=3
// (1-7)=-6
// (6-7)=-1
// (2-7)=-5
//     |
//     |
// (1-9)=8--->max

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                maxProfit = Math.max(maxProfit, arr[j] - arr[i]);
            }
        }
        System.out.println(maxProfit);


        // optimal answe
        // explanation-   if we get min value present in that array then using min value hum uss array mai ek ek iterate kar ke max
        // array find kar lenge
        int minSofar = arr[0];  // because agar hum 0 le liye then hamara min value zero ho jayega but array mai min value kuch bhi ho sakti hai
        int maxProfit1 = 0;
        for (int k = 0; k < arr.length;k++){
            minSofar=Math.min(minSofar,arr[k]);
            maxProfit1=Math.max(maxProfit1,arr[k]-minSofar);
        }
        System.out.println(maxProfit1);
    }
}
