package Recursion;

public class PrintHelloMultTimes {
    static void printHello(int n){
        if(n<=0) return;
        printHello(n-1);
        System.out.println("hello"+n);
        System.out.println();



    }
    public static void main(String[] args) {
        printHello(5);
    }
}
