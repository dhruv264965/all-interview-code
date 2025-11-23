package StreamApi;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamEmployee {
    private String name;
    private String address;
    private String salary;
    private String deparment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDeparment() {
        return deparment;
    }

    public void setDeparment(String deparment) {
        this.deparment = deparment;
    }

    public StreamEmployee(String name, String address, String salary, String deparment) {
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.deparment = deparment;
    }

    @Override
    public String toString() {
        return "StreamEmployee{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary='" + salary + '\'' +
                ", deparment='" + deparment + '\'' +
                '}';
    }

    public static void main(String[] args) {
        List<StreamEmployee>listEmp=List.of(
                new StreamEmployee("Dhruv","Rewa","8520","cse"),
                new StreamEmployee("raj","Rewa","78950","ec"),
                new StreamEmployee("amit","Rewa","5430","mech"),
                new StreamEmployee("ankit","Rewa","17340","ai"),
                new StreamEmployee("sachin","Rewa","10567","cse")
        );
        System.out.println(" group by department and thier employee");
        Map<String, List<StreamEmployee>> collect = listEmp.stream()
                .collect(Collectors.groupingBy(StreamEmployee::getDeparment));
        System.out.println(collect);
        System.out.println("  Name the employee whose salary more than 10K");
        List<StreamEmployee> collect1 = listEmp.stream()
                .filter(sal -> Integer.parseInt(sal.getSalary()) > 10000)
                .collect(Collectors.toList());
        System.out.println(collect1);
        System.out.println("Find the employee with the highest salary");
        Optional<StreamEmployee> maxSalaryEmp = listEmp.stream()
                .max(Comparator.comparingInt(e -> Integer.parseInt(e.getSalary())));

        maxSalaryEmp.ifPresent(e -> System.out.println(e.getName() + " => " + e.getSalary()));
        System.out.println(" Print highest paid employee from each branch");
        Map<String, Optional<StreamEmployee>> highestPerBranch = listEmp.stream()
                .collect(Collectors.groupingBy(
                        StreamEmployee::getDeparment,
                        Collectors.maxBy(Comparator.comparingInt(e -> Integer.parseInt(e.getSalary())))
                ));
        System.out.println(highestPerBranch);
        System.out.println(" Find average salary of employees");
        double avg = listEmp.stream()
                .mapToInt(e -> Integer.parseInt(e.getSalary()))
                .average()
                .orElse(0.0);
        System.out.println("Average Salary: " + avg);
        System.out.println("5. Sort employees by salary (descending)");
        List<StreamEmployee> sortedBySalary = listEmp.stream()
                .sorted((e1, e2) -> Integer.parseInt(e2.getSalary()) - Integer.parseInt(e1.getSalary()))
                .collect(Collectors.toList());
        System.out.println(sortedBySalary);



    }
}
