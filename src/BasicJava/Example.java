package BasicJava;

public class Example {
    int value;

    public Example() {
//        this(10);  // Line 1
    }

    public Example(int value) {
        this();  // Line 2
        this.value = value;
    }

}
//Neither constructor ever completes. The code won’t compile.

//Java rejects it because the two constructors call each other:
//Example() calls this(10), which runs Example(int).
//Example(int) calls this(), which runs Example().
//
//That is a recursive constructor invocation. The compiler reports: