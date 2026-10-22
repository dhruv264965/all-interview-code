package OOPS;

class A {
    A() { show(); }
    void show() { System.out.println("A show"); }
}

class B extends A {
    int x = 10;
    B() { super(); }
    @Override
    void show() { System.out.println("B show " + x); }
}

public class Main1 {
    public static void main(String[] args) {
        new B();  // B show 0
    }
}
//Step by step:
//
//new B() runs B(), which first calls super(), so A() runs.
//A() calls show(). Because of runtime polymorphism, this calls B’s overridden show(), not A’s. So “A show” never prints, since A.show() is never called.
//At that moment x is 0. B’s field initializers (x = 10) run only after super() finishes. Until then, x holds its default value, 0.
//A() finishes, then x = 10 is assigned. Nothing prints again.
