package Pattern;

public class Pattern6 {
    //1 2 3
    //1 2
    //1
    public static void nNumberTriangle(int n) {
       for(int i=1;i<=n;i++){
           for (int j=1;j<=n+1-i;j++){
               System.out.print(j+" ");
           }
           System.out.println("");
       }
    }
    public static void main(String[] args) {
       nNumberTriangle(5);
    }
}
