package Collection.Compartor_And_Comparble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Employee implements Comparable<Employee> {
    private int id;
    private String name;
    private int phone;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Employee(int id, String name, int phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
       //return this.id-o.id;                 // compare Integer Increasing order
      // return this.name.compareTo(o.name);  // compare String  Increasing order
      //  return o.id-this.id;                   // compare Integer decrasing order
        return o.name.compareTo(this.name);       // compare String  decrasing order

    }

    public static void main(String[] args) {
        List<Employee> emp = new ArrayList<>();
        emp.add(new Employee(3,"dhruv",983));
        emp.add(new Employee(1,"ashish",835));
        emp.add(new Employee(2,"shukla",325));
        System.out.println(emp);
        Collections.sort(emp);
        System.out.println(emp);

    }


}




