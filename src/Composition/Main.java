package Composition;

class Engine {
    private String type;

    public Engine(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

class Car {
    private Engine engine;

    public Car(String engineType) {
        engine = new Engine(engineType); // Composition
    }

    public void printDetails() {
        System.out.println("Car has an " + engine.getType() + " engine.");
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = new Car("V8");
        car.printDetails();
    }
}
