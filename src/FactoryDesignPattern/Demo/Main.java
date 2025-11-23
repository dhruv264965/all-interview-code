package FactoryDesignPattern.Demo;

public class Main {
    public static void main(String[] args) {
        Vehicle hondacity = VehicleFactory.getVehical("Car",5,"hondacity");
        System.out.println(hondacity.getSeatingCapacity());
        System.out.println(hondacity.getDiscription());
        System.out.println();
        Vehicle cycle=VehicleFactory.getVehical("Bus",2,"Bicycle");
        System.out.println(cycle.getSeatingCapacity());
        System.out.println(cycle.getDiscription());
    }
}
