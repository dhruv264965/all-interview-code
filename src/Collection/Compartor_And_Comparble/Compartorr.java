package Collection.Compartor_And_Comparble;


import java.util.*;

class Student implements Comparator<Student> {
    private String name;
    private int id;

    public Student(String name, int id) {
        this.name = name;
        this.id  = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int compare(Student o1, Student o2) {
       return o1.getId()-o2.getId();
    }
}

public class Compartorr  {

    public static void main(String[] args) {
//        List<String> list= Arrays.asList("banana","apple","date");
//        System.out.println(list);
//        list.sort((a,b)->a.length()-b.length());
//        System.out.println(list);

        List<Student> students = new ArrayList<>();
        students.add(new Student("Charlie", 8));
        students.add(new Student("Bob", 1));
        students.add(new Student("Alice", 3));
        students.add(new Student("Akshit", 4));
      //  Comparator<Student> studentComparator = Comparator.comparing(Student::getGpa).reversed().thenComparing(Student::getName);
//        students.sort((o1, o2) -> {
//            if (o2.getGpa() - o1.getGpa() > 0) {
//                return 1;
//            } else if (o2.getGpa() - o1.getGpa() < 0) {
//                return -1;
//            } else {
//                return o2.getName().compareTo(o1.getName());
//            }
//        });
//        students.sort(studentComparator);
//        for (Student s : students) {
//            System.out.println(s.getName() + ": " + s.getGpa());
//        }

        Collections.sort(students,new IdComparator());

        System.out.println(students);
        Collections.sort(students,new NameComparator());
        System.out.println(students);


    }
}
