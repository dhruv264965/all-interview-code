package Interface;

public interface Interface1 {
    default void hello(){
        System.out.println("hello interface 1");

    }
    static public void world(){
        System.out.println("static method is called");
    }
     void eat();


}
