package BasicJava;

class F{
    int a,b;
    F()
    {
        a=40;
        b=75;
        System.out.println (a+" "+b);
    }
    {
        a=50;         /*instance block it will call before constructor
                         in output instance block will excute first*/
        b=98;
        System.out.println(a+" "+b);
    }

}
public class InstanceBlock {
    public static void main(String[] args) {
        F re=new F();
    }

}

