package FactoryDesignPattern.Demo;

import java.util.Objects;

public class VehicleFactory {
    public static Vehicle getVehical(String type, int cap, String name){
        if(Objects.equals(type,"Bike")){
            return new Bike(name,cap);
        }
        else if(Objects.equals(type,"Car")){
            return  new Car(name,cap);
        }
        else if(Objects.equals(type,"Bus")){
            return new Bus(name,cap);
        }
        return null;
    }
}
