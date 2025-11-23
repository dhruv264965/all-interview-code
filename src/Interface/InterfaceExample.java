package Interface;
interface  I  {
                int i=10;  // public static final
                void display();  // abstract
        }
public class InterfaceExample implements I {
    public static void main(String[] args) {
           I ref=new InterfaceExample();
           ref.display();
         //  I.i=20;    /// not posssible
        System.out.println(I.i);
    }

    @Override
    public void display() {
        System.out.println("display");
    }
}
