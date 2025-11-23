package FactoryDesignPattern.Demo;

public class Bike extends Vehicle{
    public Bike() {
    }
    public Bike(String name,int seatingCap)
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
