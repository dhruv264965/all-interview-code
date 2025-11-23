package StreamApi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Employee {

    private int id;
    private String name;
    private int age;
    private String gender;
    private double salary;
    private String department;

    public Employee(int id, String name, int age, String gender, double salary, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }
}

public class HowManyMaleAndFemalInOrg {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Dhruv Shukla", 20, "Male", 50000,"IT"),
                new Employee(2, "Arti Shukla", 25, "Female", 20000,"CSE"),
                new Employee(3, "Ashish Shukla", 40, "Male", 70000,"EC"),
                new Employee(4, "Vandana Shukla", 35, "Female", 96000,"Mech"),
                new Employee(5, "Amit Shukla", 50, "Male", 96000,"CSE"),
                new Employee(6, "Shiva Shukla", 38, "Male", 58000,"Mech"),
                new Employee(7, "Ridhika trivedi", 26, "Female", 46000,"CSE"),
                new Employee(8, "Ridhika trivedi", 27, "Female", 46000,"CSE")
        );
        // get employee detils whose salary is greter than average salary
        System.out.println(" get employee detils whose salary is greter than average salary ");
        Double averageSalary = employees.stream().
                collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("average salary " +averageSalary);
        Map<Boolean, List<Employee>> salaryGreaterThanAverage = employees.stream()
                .collect(Collectors.partitioningBy(salary -> salary.getSalary() > averageSalary));
        System.out.println("salary Greater Than Average  "+ salaryGreaterThanAverage.get(true));

        //What is the average and total salary in the organization
        System.out.println("What is the average and total salary in the organization");
        String collect3 = employees.stream()
                .collect(Collectors.teeing(
                        Collectors.summarizingDouble(Employee::getSalary),
                        Collectors.averagingDouble(Employee::getSalary),
                        (avg, sum) -> avg + " " + sum
                       ));
        System.out.println(collect3);
        System.out.println("----------------------------------------------------------------");

        //What is the average salary of each deparment
        System.out.println("What is the average salary of each deparment ");
        Map<String, Double> collect5 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(collect5);
        System.out.println(" find highest salary in each department");
       employees.stream()
                       .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))))
                               .forEach((k,v)-> System.out.println(k+ " "+v.map(Employee::getSalary)));
        System.out.println("----------------------------------------------------------------");
        System.out.println(" find  second highest salary in each department");
        Map<String, Optional<Employee>> secondHighestByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                                        .skip(1) // skip highest
                                        .findFirst()
                        )
                ));
        System.out.println(secondHighestByDept);

        System.out.println("----------------------------------------------------------------");
        // How many Male and Female in the organization.
        Map<String, Long> getGenderCount = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println("Male and Female in the organization:-"+getGenderCount);
//        System.out.println("Male Employess "+getGenderCount.get("Male"));
        System.out.println("female Employess "+getGenderCount.get("Female"));
        System.out.println("----------------------------------------------------------------");
        System.out.println("print name of all department in this organization");
        //print name of all department in this organization
        employees.stream()
                        .map(Employee::getDepartment)
                .distinct()
                .forEach(name->System.out.print(name+" "));
        System.out.println("-------------------------------------------------------------------------------------------");
        //Print average age of male and female employee
        System.out.println("Print average age of male and female employee");
        Map<String, Double> averageAgeOfMaleAndFemale = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println(averageAgeOfMaleAndFemale);
        System.out.println("----------------------------------------------------------------");
        // Total number of Employee has Age more than 30
        long countEmployee=  employees.stream()
                .filter(employee -> employee.getAge()>30)
                .count();   ///count method is long type
        System.out.println("Employee has Age more than 30 Age "+countEmployee);
        System.out.println("----------------------------------------------------------------");
// seprate the employee who has younger age less than 25 and older age more than 25
        System.out.println("employee who has younger age less than 25 and older age more than 25");
        Map<Boolean, List<Employee>> collect4 = employees.stream()
                .collect(Collectors.partitioningBy(emp -> emp.getAge() > 25));
        System.out.println(collect4);
        System.out.println(collect4.get(true));
        System.out.println(collect4.get(false));
//        List<Employee> aTrue = collect4.get("true");
//        System.out.println(aTrue);
        System.out.println("----------------------------------------------------------------------");
        // print Salary more than 30000
        long salaryCount=employees.stream()
                .filter(employee -> employee.getSalary()>30000)
                .count();
        System.out.println("Employee count salary more than 30000:- " +salaryCount);

        System.out.println("---------------------------------------------------------------------");
        // print male name whose salary is greater 30000
        System.out.println("print male name whose salary is greater 30000");
    //Approch 1->
     employees.stream()
                .filter(employee -> employee.getGender().equals("Male") && employee.getSalary()>30000)
            .forEach(employee -> System.out.println(employee.getName()));

        //Approch 2->
        List<String> maleEmployeesWith30KSalary = employees.stream()
                .filter(employee -> employee.getGender().equals("Male") && employee.getSalary() > 30000)
                .map(Employee::getName) // Extract the names
                .collect(Collectors.toList()); // Collect into a list
        System.out.println(maleEmployeesWith30KSalary);// Print the list

        System.out.println("-------------------------------------------------------------------------------------------");
        //Find Average salary of male and female employee

        Map<String,Double>avgSalaryOfMaleAndFemale=employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Average Salary"+avgSalaryOfMaleAndFemale);
        System.out.println("Average salary of Male employees: " + avgSalaryOfMaleAndFemale.get("Male"));
        System.out.println("Average salary of Female employees: " + avgSalaryOfMaleAndFemale.get("Female"));


        System.out.println("------------------------------------------------------------------------");
        //find first name of the oldest employee.
        Optional<String>OldestEmployeeFirstName=employees.stream()
                .max(Comparator.comparingInt(Employee::getAge))// Find the oldest employee
                .map(employee -> employee.getName().split(" ")[0]);// Extract the first name
        Optional<Employee> max1 = employees.stream()
                .max(Comparator.comparingInt(Employee::getAge));
        System.out.println(max1.get().getName());
        System.out.println("First name of the oldest employee: " +  // Print the result using Optional
                OldestEmployeeFirstName.orElse("No employees found"));

        System.out.println("-------------------------------------------------------------");
        // Age of  oldest employee
        System.out.println(" Age of  oldest employee ");
        Optional<Employee> max = employees.stream().max(Comparator.comparing(Employee::getAge));
        System.out.println(max.get().getAge());

        System.out.println("-------------------------------------------------------------");
        // find employee with Highest salary
        System.out.println("Highest salary ");
        //appraoach 1
        Optional<Employee>highestSalary=employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(highestSalary.get());
        highestSalary.ifPresentOrElse(employee -> System.out.println("Name "+employee.getName()+"  salary "+employee.getSalary()),()-> System.out.println("Not found"));
        // approach 2
        Optional<Employee> collect1 = employees.stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
        if(collect1.isPresent()){
            System.out.println("highest salary "+collect1.get());
        }

        System.out.println("-------------------------------------------------------------");
        // find the employee name group by department id
        System.out.println("find the employee name group by department id");
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .forEach((k,v)-> System.out.println(k+" "+ v));

       // System.out.println(collect2);

        System.out.println("-------------------------------------------------------------");
        //average salary of each department
        System.out.println("average salary of each department");
        Map<String, Double> averageSalaryOfEachDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(averageSalaryOfEachDept);

        System.out.println("-------------------------------------------------------------");
        // find employee with Highest salary male and female employee
        System.out.println("male and female employee Highest salary ");
        Map<String, Optional<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        System.out.println(collect);
        System.out.println("male employee Highest salary");
        Optional<Employee> male = collect.get("Male");
        System.out.println(male);
        System.out.println("Female employee Highest salary");
        Optional<Employee> Female = collect.get("Female");
        System.out.println(Female);

        Optional<Employee> male1 = employees.stream()
                .filter(x -> x.getGender().equals("Male"))
                .max(Comparator.comparing(Employee::getSalary));
        System.out.println("highest male salary "+ max1);

        System.out.println("-----------------------------------------------------------------------------------");
        // find highest salary in each department
        System.out.println("find highest salary in each department");
        Map<String, Optional<Employee>> collect8 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        System.out.println(collect8);

        System.out.println("-------------------------------------------------------------------------------------");
        // find department who has maximum number of employeee
        System.out.println("find department who has maximum number of employeee");
        Map.Entry<String, Long> stringLongEntry =employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()).orElse(null);
        System.out.println(stringLongEntry);

        System.out.println("--------------------------------------------------------------------");
        // find department who has  second maximum number of employeee
        System.out.println("find department who has second maximum number of employeee");
       employees.stream()
                       .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()))
               .entrySet()
               .stream()
               .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
               .limit(2)
               .skip(1)
               .forEach((k)-> System.out.println(k));

        System.out.println("-------------------------------------------------------------------------------------");
        // list of employee name start with A
        System.out.println("list of employee name start with A");
        List<Employee> a = employees.stream()
                .filter(emp -> emp.getName().startsWith("A")).toList();
        System.out.println(a);
        System.out.println("-------------------------------------------------------------------------------------");
        //Top Two highest paid employee
        System.out.println("Top Two highest paid employee");
        List<Employee> topTwoHighestPaidEmployees = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed()) // Sort by salary in descending order
                .limit(3) // Limit to top 2 employees
                .collect(Collectors.toList());
        topTwoHighestPaidEmployees.forEach(employee -> // Print the result
                System.out.println("Employee: " + employee.getName() + ", Salary: " + employee.getSalary()));

        System.out.println("-------------------------------------------------------------------------------------");

        //Top Two highest paid employee with lexicial manner
        System.out.println("Top Two highest paid employee with lexicial manner");
        List<Employee> topTwoHighestPaidEmployeeslexicial = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed().thenComparing(Employee::getName))
                // Sort by salary in descending order
                .limit(3) // Limit to top 2 employees
                .collect(Collectors.toList());
        topTwoHighestPaidEmployeeslexicial.forEach(employee -> // Print the result
                System.out.println("Employee: " + employee.getName() + ", Salary: " + employee.getSalary()));
        List<Employee> employees2 = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed().thenComparing(Employee::getName)).toList();
        System.out.println(employees2);
        System.out.println("----------------------------------------------------------------------------");
        //Get the total salary of all employees:
        double salary=employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("total salary of all employees "+salary);
        System.out.println("------------------------------------------------------------");
        // sort the employee details based on salary then age then department
        System.out.println("sort the employee details based on salary then age then department ");
        List<Employee> employees1 = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary)
                .thenComparing(Employee::getAge).thenComparing(Employee::getDepartment)
        ).toList();
        System.out.println(employees1);

        System.out.println("------------------------------------------------------------");
       // Find all employees with duplicate names
        System.out.println(" Find all employees with duplicate names ");
        Set<String> duplicateNames = employees.stream()
                .collect(Collectors.groupingBy(Employee::getName, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        System.out.println("Duplicate Names: " + duplicateNames);


    }

}
