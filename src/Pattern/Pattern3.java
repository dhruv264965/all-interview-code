package Pattern;

public class Pattern3 {
    //1
    //1 2
    //1 2 3
    public static void nTriangle(int n) {
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print(j+" ");
            }
            System.out.println("");
        }
    }
    public static void main(String[] args) {
        nTriangle(5);
    }
}
