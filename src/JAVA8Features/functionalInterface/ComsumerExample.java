package JAVA8Features.functionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ComsumerExample {
    public static void main(String[] args) {
        Consumer<Integer>print=(x)-> System.out.println(x);
        print.accept(51);

        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        Consumer<List<Integer>>printList=x->{
            for(int i:x){
                System.out.print(i+" ");
            }
        };
        printList.accept(integers);
    }
}
