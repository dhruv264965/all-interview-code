package Exception;

public class TryCatchDemo {
    public static void main(String[] args) {
        try {
            int x=3*9;
        }
 /*       catch (Exception e){  // if we write Exception here will will get compile time error
                        // because Airthmatic is a subclass of Exception and the first catch will already handle it
            System.out.println("Inside exception");
        }catch (ArithmeticException a ){
            System.out.println("inside Arithmatic");
        }
        */

        catch (ArithmeticException e){
            System.out.println("Inside exception");
        }
        catch (Exception a){
            System.out.println("inside Arithmatic ");
        }
    }
}
