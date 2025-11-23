package JAVA8Features;
import java.util.*;
import java.util.function.BiConsumer;

public class LamdaExp {
    public static void main(String[] args) {

     BiConsumer<Integer,Integer> biConsumer=(a,b)-> System.out.println(a+b);
     biConsumer.accept(4,8);

    }
}
