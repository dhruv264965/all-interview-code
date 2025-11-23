package Interface;
class Parent{
    int x=10;
    void start(){
        System.out.println("parent");
    }
    static int y=100;
    static void display(){
        System.out.println(" Static parent ");
    }
}
class Child extends Parent{
    int x=20;
    void start(){
        System.out.println("child");
    }
      static int y=200;
    static void display(){
        System.out.println(" Static child ");
    }
}
public class Demo {
    public static void main(String[] args) {
        Parent p = new Child();
        Child c = new Child();
        System.out.println(p.x); // 10 - EXCEPTION ( The variable is resolved at compile time based on the reference type.
      //                                                     This is called hiding, not overriding.
        System.out.println(c.x); // 20
        System.out.println(Parent.y);  // 100      // Static methods are bound at compile-time, not runtime.

                                              //That means if a subclass defines a static method with the same signature as in
                                              //      the parent, it’s not overriding — it’s hiding.
        System.out.println(Child.y); //200
        p.start();         // child                //  This is called overriding
        c.start();         // child
        Parent.display(); //  Static parent       // // Parent static method (depends on reference type)
        Child.display();  //  Static child

    }
}
