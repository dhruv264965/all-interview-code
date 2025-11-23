package BasicJava;

class SuperClass {
    void anyMethod() throws CarException {
        System.out.println("Super Class Method");
    }
}

public class SubClass extends SuperClass {
    @Override
    void anyMethod() throws CarException { // Fixed: Now throws CarException, same as SuperClass
        System.out.println("Sub Class Method");
    }

    public static void main(String[] args) {
        SubClass testException = new SubClass();
        try {
            testException.anyMethod();
            System.out.println("Inside Try");
            return;
        } catch (CarException e) {  // Catching the correct exception
            System.out.println("Inside Catch");
        } finally {
            System.out.println("Inside Finally");
        }
    }
}

class VehicleException extends Exception {
    public VehicleException(String str) {
      //  super(str); // Use super() to properly set exception message
        System.out.println("Thrown Vehicle Exception For: " + str);
    }
}

class CarException extends VehicleException {
    public CarException(String str) {
        super(str);
        //    super(str); // Use super() to properly set exception message
        System.out.println("Thrown Car Exception For: " + str);
    }
}
