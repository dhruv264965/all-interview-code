package Collection.HashMap;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Employee a = new Employee(1,"Dhruv");
     //   Employee b = new Employee(2,"shukla");
        Employee c = new Employee(1,"Dhruv");
        Map<Employee, String> test = new HashMap<>();

        test.put(a, "hello");
        test.put(c, "world");
     //   test.put(a, "hello");
        System.out.println(test.size());
        System.out.println(test);
    }
}
