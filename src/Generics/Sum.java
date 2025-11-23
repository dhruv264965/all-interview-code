package Generics;

import java.util.Arrays;
import java.util.List;

public class Sum {
    public static double sum(List<? extends Number> numbers){  //This is also called as Upper Bound
        double sum=0;
        for (Number o:numbers){  //Number -Super class ka reference use kar sakte hai
            sum+= o.doubleValue();
        }
        return sum;
    }
    public static void main(String[] args) {
        System.out.println(sum(Arrays.asList(2,43.44,5,2)));

    }
}
