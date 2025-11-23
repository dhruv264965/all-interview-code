package Generics;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("Hello");
        list.add(123);
        list.add(3.14);

        String str = (String) list.get(0);
        String str1 = (String) list.get(1);  //class cast exception
        //Above code has 3 major issues
        //
        //No Type safety
        //Manual casting
        //No Compile Time checking
    }
}
