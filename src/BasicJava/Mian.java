package BasicJava;
 class StaticFlow {
    static void start(){
        System.out.println("s");
    }
    void hi(){
        System.out.println("h");
    }
}
class ch extends StaticFlow{
    static void start(){
        System.out.println("ch ");
    }
    void hi(){
        System.out.println("  chhh");
    }
    void hellow(){
        System.out.println("chhhello");
    }
}
public class Mian {
    public static void main(String[] args) {
   StaticFlow s= new ch();
      s.start(); // this is equivalent StaticFlow.start(); at compile time ..
      s.hi();
//       s.hellow(); //  comile time error we will get The reference s is of type StaticFlow, but hellow() is not declared in StaticFlow.


    }
}
