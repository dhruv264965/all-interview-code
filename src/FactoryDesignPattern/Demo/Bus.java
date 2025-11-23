package FactoryDesignPattern.Demo;

public class Bus extends Vehicle{
    public Bus() {
    }
    public Bus(String name,int seatingCap)
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
