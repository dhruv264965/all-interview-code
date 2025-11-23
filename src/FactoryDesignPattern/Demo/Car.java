package FactoryDesignPattern.Demo;

public class Car extends Vehicle {
    public Car() {
    }
    public Car(String name,int seatingCap)
    {
        this.name = name;
        this.seatingCap = seatingCap;
    }
    @Override
    int getSeatingCapacity() {
        return this.seatingCap=seatingCap;
    }

    @Override
    String getDiscription() {
        return this.name=name;
    }
}
