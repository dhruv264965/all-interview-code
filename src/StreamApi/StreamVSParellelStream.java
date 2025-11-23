package StreamApi;

import java.util.Arrays;
import java.util.List;

public class StreamVSParellelStream {
    public static void main(String[] args) {

        //Steam
        List<String>list= Arrays.asList("Dhruv","Shukla","raj");
        list.stream().
                forEach(name-> System.out.println(name+" processed by  "+Thread.currentThread().getName()));
        System.out.println(list);

        System.out.println("----------------------------------------------------------------------------------------");
        //ParellStream
        List<String>list1= Arrays.asList("Dhruv","Shukla","raj");
        list.parallelStream()
                .forEach(name-> System.out.println(name+" processed by  "+Thread.currentThread().getName()));
        System.out.println(list1);


    }
}
