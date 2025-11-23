package Interface.defaultMethod;

public interface T1 {
    void m1();
    default void m2(){
        System.out.println("hello iam m2");
    }
    static void m3(){
        System.out.println("i am static block");
    }
}
