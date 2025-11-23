package OOPS.polymorphism;
class Animal {
    // Base class method
    public void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    // Overriding the method
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    // Overriding the method
    @Override
    public void sound() {
        System.out.println("Cat meows");
    }
}
public class RunTimePoly {
    public static void main(String[] args) {
        Animal a;   //Superclass reference
                a=new Dog();  // Object is Dog
        a.sound();            // Outputs: Dog barks
        Animal b=new Cat();
        b.sound();

    }
}
//"Runtime polymorphism allows a superclass reference to call overridden methods in a subclass.
// Java uses dynamic method dispatch to decide at runtime which method to execute based on the actual object."
