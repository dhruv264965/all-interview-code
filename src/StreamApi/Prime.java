package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Prime {
    public static void main(String[] args) {
        int a=17;
        boolean b= IntStream.rangeClosed(2,(int) Math.sqrt(a)).noneMatch(i-> a % i == 0) && a>1;
        System.out.println(b);
        // print prime number present in the list
        List<Integer> list= Arrays.asList(1,2,3,4,5,17,36,21,97);
        List<Integer> list1 = list.stream().filter(Prime::isPrime).toList();
        System.out.println(list1);

        // print all prime numbers from 1 to 20 using the same isPrime method.
        IntStream.range(1, 20)
                .filter(Prime::isPrime)
                .forEach(n -> System.out.println(n + " is prime"));
    }
    static boolean isPrime(int num){
        if(num<=1) return false;
        for (int i=2;i<=Math.sqrt(num);i++){
            if (num%i==0) return false;

        }
        return true;
    }
}
