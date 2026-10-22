package OOPS;

public class Main4 {
    static int test() {
        try {
            return 1;          // value 1 is saved
        } finally {
            return 2;          // overrides it
        }
    }

    public static void main(String[] args) {
        System.out.println(test());   // prints 2
    }
}
// The 1 is saved when try returns, but then finally runs and executes its own return 2. The method exits there, so the saved 1 is discarded.
