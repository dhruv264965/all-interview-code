package OOPS.Interitance;

class Animal {
    void eat() {
        System.out.println("This animal eats food.");
    }
}

class Mammal extends Animal {
    void walk() {
        System.out.println("Mammals walk.");
    }
}

class Dog extends Mammal {
    void bark() {
        System.out.println("The dog barks.");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Mammal m=new Dog();
        m.walk();
        m.eat();
//        dog.eat();   // From Animal
//        dog.walk();  // From Mammal
//        dog.bark();  // From Dog
    }
}

