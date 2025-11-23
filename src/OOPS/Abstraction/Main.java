package OOPS.Abstraction;
// Abstraction: Interface exposes only essential operations
interface RemoteControl {
    void turnOn();
    void turnOff();
}
// Implementation details are hidden from the user
class Television implements RemoteControl {
    @Override
    public void turnOn() {
        System.out.println("Television is ON");
        // Internally: connect power, enable display, etc.
    }

    @Override
    public void turnOff() {
        System.out.println("Television is OFF");
        // Internally: shut down circuits, stop signal, etc.
    }
}

public class Main {
    public static void main(String[] args) {
        RemoteControl remote = new Television();
        remote.turnOn();   // User only knows what to call, not how it's done  hide logic and complexcity
        remote.turnOff();
    }
    }

