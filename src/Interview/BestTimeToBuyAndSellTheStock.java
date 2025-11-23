package Interview;
//You are given an array prices where prices[i] is the price of a given stock on the ith day.
//You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
//Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
public class BestTimeToBuyAndSellTheStock {
    public static void main(String[] args) {
        //brute force TC-n*2
        int arr[]={6,7,1,3,5,9};
        int res=0;
        for(int i=0;i<arr.length;i++){
            int buy=arr[i];
            for(int j=i+1;j<arr.length;j++){
                int sell=arr[j];
                res=Math.max(res,(sell-buy));
            }
        }
        System.out.println(res);

        // optimal force TC- n
        int arr1[]={6,7,1,3,5,9};
        int maxProfit=0;
        int minPriceSoFar=arr[0];
        for(int i=0;i<arr1.length;i++){
            minPriceSoFar=Math.min(minPriceSoFar,arr[i]);
            int profit=arr[i]-minPriceSoFar;
            maxProfit=Math.max(maxProfit,profit);
        }
        System.out.println(maxProfit);


    }
}
