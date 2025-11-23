package Java_Interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Student{  //
    private int id;
    private  String name;

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
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
class StudentNameComparartor implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
public class SortEmployeeByNameUsingComparator {

    public static void main(String[] args) {
        List<String>listOfStudent=new ArrayList<>();
        listOfStudent.add(1,"dhruv");
        listOfStudent.add(2,"ashish");
        listOfStudent.add(2,"raj");

     //   Collections.sort(listOfStudent,SortEmployeeByNameUsingComparator());


    }
}
