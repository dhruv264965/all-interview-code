package OOPS;

public class Main5 {
    static int test() {
        try {
            throw new RuntimeException("Something went wrong");
        } finally {
            return 5;
        }
    }

    public static void main(String[] args) {
        System.out.println(test());   // prints 5, no exception
    }
}
// The try block throws an exception, and it should travel up to main. But finally runs
// first and executes return 5, which completes the method normally.
// The exception is lost, with no stack trace and no error. The bug is hidden,
// which is why this is bad practice. (The compiler may show a
// warning: “finally block does not complete normally”.)