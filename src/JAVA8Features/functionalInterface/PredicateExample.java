package JAVA8Features.functionalInterface;

import java.util.function.*;

public class PredicateExample {
    public static void main(String[] args) {
        Predicate<Integer> IsEven=x->x%2==0;
        System.out.println(IsEven.test(4));

        Predicate<String> wordCheckStart=x->x.toLowerCase().startsWith("d");
        System.out.println(wordCheckStart.test("dhruv"));

        Predicate<String> wordCheckEnd=x->x.toLowerCase().endsWith("v");

        Predicate<String> check=wordCheckStart.and(wordCheckEnd);
        System.out.println(check.test("dhruv"));

        BiPredicate<Integer,Integer>sum=(x,y)->(x+y)%2==0;
        System.out.println(sum.test(4,3));

        BiFunction<Integer,Integer,Integer>sum1=(a,b)->(a+b);
        System.out.println(sum1.apply(4,5));

        BiFunction<Integer,Integer,Integer> sum8=(x,y)->(x+y);
        System.out.println(sum8.apply(4,5));
        Function<Integer,Integer> sum9=(x)->(x*x);
        System.out.println(sum9.apply(4));
        Predicate<Integer> lengh=(z)->(z%2==0);
        System.out.println(lengh.test(4));
        Consumer<Integer> print=(x)-> System.out.println(x);
        print.accept(51);
        Supplier<String> printtt=()->"Hello world";
        System.out.println(printtt.get());




    }
}
