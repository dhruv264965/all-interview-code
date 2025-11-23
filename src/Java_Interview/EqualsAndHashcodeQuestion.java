package Java_Interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

//Question-Create a Map which contains address of each employee and uses employee object as a key. Store address of some
// employees in this map. Now create a method that accepts Map and Employee object as parameters and returns address
// of this employee.
class Employee{
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}



public class EqualsAndHashcodeQuestion { //this is for question 1
    public static String getAddress(Map<Employee,String> map, Employee employee){
        return map.get(employee);
    }
    public static Map<String, String> convertToNameAddressMapExcludingDuplicates(Map<Employee, String> employeeAddressMap) {
        // Count the occurrences of each name
        Map<String, Long> nameCounts = employeeAddressMap.keySet().stream()
                .collect(Collectors.groupingBy(Employee::getName, Collectors.counting()));

        // Filter and collect names with only one occurrence
        return employeeAddressMap.entrySet().stream()
                .filter(entry -> nameCounts.get(entry.getKey().getName()) == 1)
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(), // Use the name as the key
                        Map.Entry::getValue                // Use the address as the value
                ));
    }

    public static void main(String[] args) {
        HashMap<Employee,String>employeeAddressMap=new HashMap<>();
        Employee e1=new Employee(1,"dhruv");
        Employee e2=new Employee(2,"raj");
        Employee e3=new Employee(3,"sachin");
        Employee e4=new Employee(4,"dhruv");

        employeeAddressMap.put(e1,"jp nagar");
        employeeAddressMap.put(e2,"jaynagar nagar");
        employeeAddressMap.put(e3,"mg nagar");
        employeeAddressMap.put(e4,"vigyan nagar");

        String address=getAddress(employeeAddressMap,e1);
        System.out.println("Address "+address+" of emp "+e1);
        System.out.println("--------------------------------------------------------------");
        // if two employee has same name then we will exclude
        Map<String, String> nameToAddressMap = convertToNameAddressMapExcludingDuplicates(employeeAddressMap);
        System.out.println(nameToAddressMap);



    }
}
