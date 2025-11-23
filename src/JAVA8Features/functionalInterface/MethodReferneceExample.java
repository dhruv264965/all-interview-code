package JAVA8Features.functionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Use method without Invoking and we use in place of lambda expression
public class MethodReferneceExample {
    public static void main(String[] args) {
        List<String> student = Arrays.asList("dhruv", "shukla", "ankit");
        //student.forEach(x-> System.out.println(x));
        student.forEach(System.out::println);  // method reference

       //Constructor reference
        List<String> name = Arrays.asList("amit", "tiwari", "raj");
        List<MobilePhone>mobilePhoneList=name.stream().map(MobilePhone::new).collect(Collectors.toList());
        System.out.println(mobilePhoneList);


    }
}
class MobilePhone{
    String name;

    public MobilePhone(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MobilePhone{" +
                "name='" + name + '\'' +
                '}';
    }
}
