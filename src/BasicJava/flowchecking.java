package BasicJava;
class Aa {

    static { System.out.println("A static"); } //   2

    { System.out.println("A instance block"); } // 4

    Aa() { System.out.println("A constructor"); } // 5

}

class Ba extends Aa {

    static { System.out.println("B static"); } //  3

    { System.out.println("B instance block"); } // 6

    Ba() { System.out.println("B constructor"); } // 7

}
public class flowchecking {
    public static void main(String[] args) {
        System.out.println("Main method called"); //  1
        new Ba();
    }
}
