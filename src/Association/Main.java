package Association;

class Driver {
    private String name;

    public Driver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Car {
    private String model;

    public Car(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}

public class Main {
    public static void main(String[] args) {
        Driver driver = new Driver("Alice");
        Car car = new Car("Toyota");
        System.out.println(driver.getName() + " drives a " + car.getModel());
    }
}
