package StreamApi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
//You are given a list of Employee objects, each having a name and a list of skills.
//Return the names of employees who possess all the required skills.
public class EmployeeeRecord {
    record employee(String name, List<String> skills){

    }

    public static void main(String[] args) {
        List<String>requriedSkills=List.of("JS","React");
        List<employee>employeeList= Arrays.asList(
                new employee("Dhurv",List.of("JS","React","NodeJs")),
                new employee("ankit",List.of("Java","Spring","React","NodeJs")),
                new employee("raj",List.of("JS","React")),
                new employee("amit",List.of("Python","Django"))
        );
        List<String> list = employeeList.stream()
                .filter(emp -> new HashSet<>(emp.skills()).containsAll(requriedSkills))
                .map(employee::name)
                .toList();
        System.out.println(list);
    }
}
