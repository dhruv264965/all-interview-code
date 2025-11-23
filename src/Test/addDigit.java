package Test;

public class addDigit {


    public static int addDigit(int num)
    {
        if(num==0) return 0;
        else if(num%9==0) return 9;
        else return num%9;
    }
    public static void main(String[] args) {
        int x=addDigit(66);
        System.out.println(x);


    }
}
