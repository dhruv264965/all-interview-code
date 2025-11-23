package Recursion;

public class Factorial {
    static int fact(int n)
    {
        if(n==0) return 1;
        int partial_ans =fact(n-1);
        int ans=n*partial_ans;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(fact(5));
    }
}
