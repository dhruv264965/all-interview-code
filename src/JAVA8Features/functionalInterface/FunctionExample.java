package JAVA8Features.functionalInterface;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        Function<Integer,Integer>doubleIt=x->2*x;
        System.out.println(doubleIt.apply(100));

        Function<Integer,Integer>identity =Function.identity();
        Integer res=identity.apply(10);
        System.out.println(res);

        //Take two string and return the length of string
        BiFunction<String,String,Integer>length=(x,y)->(x+y).length();
        System.out.println(length.apply("dhru","shu"));
    }
}
