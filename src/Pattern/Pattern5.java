package Pattern;

public class Pattern5 {
    //* * *
    //* *
    //*
    public static void seeding(int n) {
        for(int i=0;i<n;i++){
            for (int j=0;j<n+1-i;j++){
                System.out.print("* ");
            }
            System.out.println("");
        }
    }
    public static void main(String[] args) {
        seeding(5);

    }
}
