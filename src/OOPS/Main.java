package OOPS;

class Parent {
    Parent() { System.out.println("Parent"); }
}

class Child extends Parent {
    Child() {
        super(); //  // inserted by the compiler . if we remove also we will get same answer
        System.out.println("Child");
    }
}

public class Main {
    public static void main(String[] args) {
        new Child(); //output - parent
                              // child
    }
    //Why your reasoning doesn’t hold:
    //
    //new Child(); does create an object. You don’t have to assign it to a variable.
    // The object is created and then thrown away.
    //You don’t need to call a method. The constructor runs automatically when the object
    // is created, and the constructors contain System.out.println.
    //
    //Why Parent prints first: every constructor starts with an implicit super() call if
    // you don’t write one. So Child() is really:
}
