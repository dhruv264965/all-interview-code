package BasicMath;

public class SumOfDivisor {

    //Input:
    //N = 5
    //Output:
    //21
    //Explanation:
    //F(1) = 1
    //F(2) = 1 + 2 = 3
    //F(3) = 1 + 3 = 4
    //F(4) = 1 + 2 + 4 = 7
    //F(5) = 1 + 5 = 6
    //ans = F(1) + F(2) + F(3) + F(4) + F(5)
    //    = 1 + 3 + 4 + 7 + 6
    //    = 21


    //EXPLANATION

    //suppose we are given N = 5 ,
    //
    //  Now we have to find factors of 1, 2,3,4,5 that will be
    //
    //  f1 = 1, f2 = 1 + 2 , f3 = 1 + 3, f4 = 1+2+4, f5 = 1+5, and then we try to sum them so ans will be
    //
    //   ans  =  f1 + f2 + f3 + f4 + f5
    //
    //          = 1 + (1 + 2) + (1 + 3) + (1 + 2 + 4) + (1 + 5)  // equals  21
    //
    //          now club alike terms
    //
    //        = (1x5) + (2x2) + (3x1) + (4x1)+(5x1) // remains 21
    //
    //        now this part does the main trick  and here I will do some rewriting as
    //
    //       = (1 x (5/1) ) + (2 x(5/2)) + (3x(5/3)) + (4 x (5/4)) + (5x(5/5))  //still remains 21


    static long sumOfDivisors(int N){
        long sum=0;
        for(int i=1;i<=N;i++)
        {
            sum+=i*(N/i);
        }
        return sum;
    }
    public static void main(String[] args) {
        System.out.println(sumOfDivisors(5));

    }
}
