package OOPS;

public class Main3 {
    static int test() {
        try {
            return 1;
        } finally {
            System.out.println("finally");
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
//main calls test(), and System.out.println(test()) has to evaluate test() before it can print anything.
//Inside test(), return 1 is reached, so the return value 1 is saved.
//Before the method actually exits, the finally block runs and prints “finally”.
//Then test() returns the saved 1, and main prints it.
//
//So “finally” prints first because it happens during test(), and 1 prints after test() returns.
