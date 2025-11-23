package JAVA8Features.functionalInterface;

import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        Supplier<String> print=()->"Hello world";
        System.out.println(print.get());
    }
}
