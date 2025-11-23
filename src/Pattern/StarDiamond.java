package Pattern;

public class StarDiamond {
    //  *
    // ***
    //*****
    //*****
    // ***
    //  *

    public static void nStarDiamond(int n) {
       //First Half
        //  *
        // ***
        //*****
        int num_star=1;
        for(int row=1;row<=n;row++) {
            //space
            for (int space = 1; space <= n - row; space++) {
                System.out.print(" ");
            }
            //star
            for (int star=1;star<=num_star;star++){
                System.out.print("*");
            }
            System.out.println(" ");
                num_star += 2;

//            while (num_star != 2 * row - 1) {
////                for (int star=1;star!=2*num_star-1;star++){
////                    System.out.print("*");
////                }
//                System.out.print("*");
//                num_star += 1;
//            }
//            System.out.println("");
//            num_star=0;
        }
///second half
        //*****
        // ***
        //  *
        num_star=2*(n-1)+1;
        for(int row=n;row>=1;row--){
            //space
            for(int space=1;space<=n-row;space++) {
                System.out.print(" ");
            }
                //star
                for (int star=1;star<=num_star;star++){
                    System.out.print("*");
                }
                System.out.println(" ");
                num_star-=2;
            }


    }
    public static void main(String[] args) {
        nStarDiamond(5);

    }
}
