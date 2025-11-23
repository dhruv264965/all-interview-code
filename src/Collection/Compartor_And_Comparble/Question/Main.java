package Collection.Compartor_And_Comparble.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
// sort a list of student based on first name
class Student {
    private String firstName;
    private String lastName;
    private int age;

    public Student(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", "Brown", 22));
        students.add(new Student("Bob", "Smith", 20));
        students.add(new Student("Charlie", "Davis", 23));
        students.add(new Student("Dave", "Miller", 21));

        // Sort by first name using Comparator- in java 8
     //   students.sort(Comparator.comparing(Student::getFirstName));

        // before java 8
        // Create a comparator for sorting by first name
        //start
//        Comparator<Student> comparator = new Comparator<Student>() {
//            @Override
//            public int compare(Student s1, Student s2) {
//                return s1.getFirstName().compareTo(s2.getFirstName());
//            }
//        };
//
//        // Sort the list using the comparator
//        Collections.sort(students, comparator);
// end
        // when using separator comparartor
        Collections.sort(students, new FirstNameComparator());
        // Print the sorted list
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

