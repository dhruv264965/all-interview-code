package OOPS;

/*
Rules of method overriding, one example per rule.
Lines that would NOT compile are kept as comments so the file still runs.

1. Overriding can't have weaker access  (public > protected > default > private)
2. final methods can't be overridden
3. private methods can't be overridden
4. static methods can't be overridden (they are hidden)
*/
public class OverridingRules {

    // ---------- 1. Access can't be weaker ----------
    static class Animal {
        protected void sound() { System.out.println("Animal: some sound"); }
        public void eat()      { System.out.println("Animal: eating"); }
    }

    static class Dog extends Animal {
        // protected -> public : WIDER access, allowed
        @Override
        public void sound() { System.out.println("Dog: bark"); }

        // public -> protected : WEAKER access, compile error
        // @Override
        // protected void eat() { }   // error: attempting to assign weaker access privileges; was public
        //
        // Why: Animal a = new Dog(); a.eat(); compiles because Animal.eat() is public.
        // If Dog narrowed it, that call would break. A Dog must be usable wherever an Animal is (Liskov).
    }

    // ---------- 2. final methods can't be overridden ----------
    static class Account {
        public final void calculateInterest() { System.out.println("Account: fixed bank formula"); }
    }

    static class SavingsAccount extends Account {
        // @Override
        // public void calculateInterest() { }   // error: cannot override final method
        // final = "implementation is locked". Subclasses inherit it as-is.
    }

    // ---------- 3. private methods can't be overridden ----------
    static class Parent {
        private void show() { System.out.println("Parent: private show"); }

        public void callShow() { show(); }   // always binds to Parent.show()
    }

    static class Child extends Parent {
        // Not an override: Parent.show() is invisible here, so this is a brand-new method.
        // Adding @Override would be a compile error ("method does not override...").
        private void show() { System.out.println("Child: private show"); }
    }

    // ---------- 4. static methods can't be overridden (hidden) ----------
    static class Base {
        static void greet() { System.out.println("Base: hello"); }
    }

    static class Derived extends Base {
        // Method HIDING, not overriding. No runtime polymorphism for static methods.
        static void greet() { System.out.println("Derived: hello"); }
    }

    public static void main(String[] args) {
        System.out.println("--- 1. access ---");
        Animal a = new Dog();
        a.sound();              // Dog: bark (real overriding, runtime type decides)

        System.out.println("--- 2. final ---");
        new SavingsAccount().calculateInterest();   // Account: fixed bank formula

        System.out.println("--- 3. private ---");
        new Child().callShow(); // Parent: private show (Child.show() is unrelated)

        System.out.println("--- 4. static ---");
        Base b = new Derived();
        b.greet();              // Base: hello    (reference type decides -> hiding)
        Derived d = new Derived();
        d.greet();              // Derived: hello
    }
}
