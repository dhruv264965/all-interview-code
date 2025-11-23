package BasicMath;

public class CountDigit {

        //Input: n = 12
        //Output: 2
        //Explanation: 1, 2 when both divide 12 leaves remainder 0.
        //Input: n = 2446
        //Output: 1
        //Explanation: Here among 2, 4, 6 only 2 divides 2446 evenly while 4 and 6 do not.

        static int evenlyDivides(int N){
            int rem=0;
            int temp;
            int num=N;
            int count=0;
            while(N!=0){
                temp=N%10;// 22074

                if(temp!=0 && num%temp==0) count++;
                N=N/10;
            }

            return count;

        }

    public static void main(String[] args) {
        System.out.println(evenlyDivides(22074));

    }
}
