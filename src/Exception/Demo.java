package Exception;

public class Demo {
    public static void main(String[] args) {
        int a=6;
        try {
            if(a>50) System.out.println("number is less ");
            else {
                throw new CustomException();
            }
        }
        catch (CustomException e) {
            System.out.println(" caught exception "  +e.getMessage());
        }
        finally {
            System.out.println(" hello i am finally block");
        }
    }
}
