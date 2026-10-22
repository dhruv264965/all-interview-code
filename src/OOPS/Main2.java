package OOPS;

class Animal {
    String name = "Animal";
    void speak() { System.out.println("Animal speaks"); }
}

class Dog extends Animal {
    String name = "Dog";
    void speak() { System.out.println("Dog barks"); }
}

public class Main2 {
    public static void main(String[] args) {
        Animal a = new Dog();
        System.out.println(a.name);// Animal->field are resolved at compile time not run time
        a.speak();
    }
}
// Why:
//
//a.name prints Animal. Fields are not polymorphic. They are resolved at compile time by the reference type (Animal), not the object type. Dog’s name doesn’t override Animal’s, it hides it. The Dog object actually holds both fields, and a.name picks the one from Animal. It’s the same idea as the static method hiding you answered earlier.
//a.speak() prints Dog barks. Instance methods are overridden and resolved at runtime by the actual object (Dog). You got this part right.
//
//Rule of thumb: methods follow the object, while fields and static methods follow the reference type.