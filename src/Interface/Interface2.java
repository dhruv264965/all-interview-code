package Interface;

public interface Interface2 {
    default void hello(){
        System.out.println("hello Interface 2");

    }
    static public void world(){
        System.out.println("static1 method is called");
    }
}
