package BasicJava;
class A{
    static {
        System.out.println("I am in static block");
    }
    A(){

        System.out.println("Constuctor A");
    }
    {
        System.out.println("Instance Block");
    }
}
public class FlowOfExcutionOfConstructorStaticInstance {
    public static void main(String[] args) {
        A a=new A();
        A b=new A();
    }

}
