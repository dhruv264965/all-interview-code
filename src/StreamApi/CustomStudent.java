package StreamApi;

import java.util.*;
import java.util.stream.Collectors;

public class CustomStudent {
    // record is immutable  also it get store in heap memory  , It is java 7 feature...
    record Student( String studentName,int studentAge ,double studentFees
            ,String studentDepartment,int studentID,long studentPhonenum){}
    public static void main(String[] args) {
        List<Student> stu= Arrays.asList(
                new Student("Ganesh",16,23415.6,"B.Tech",1,92345678),
                new Student("Ram",17,63415.6,"M.Tech",2,82345778),
                new Student("Siya",18,83415.8,"B.Tech",3,97345778),
                new Student("Krishna",19,41955.9,"MBA",4,77365778),
                new Student("Radha",20,97955.9,"M.Tech",5,67355778),
                new Student("Radha",20,97955.9,"M.Tech",5,67355778),
                new Student("Shiv",21,92865.5,"MBA",6,77355778),
                new Student("Parvati",22,78865.6,"BBA",7,89355778)
        );
        // print All the data
        System.out.println(" 1.   print the data ");
//        for(Student s: stu){
//            System.out.println(s);
//        }
        stu.forEach(System.out::println);
        System.out.println(" remove duplicate from list");
        Set<Student>students=new HashSet<>(stu);
        for(Student s: students){
            System.out.println(s);
        }

        // Find student which has max fees
        System.out.println(" Find student which has max fees ");
        Optional<Student> max = stu.stream().max(Comparator.comparingDouble(Student::studentFees));
        System.out.println(max.get());

        // find student those student free 50k
        System.out.println(" find student those student free 50k ");
        List<Student> students1 = stu.stream().filter(s -> s.studentFees > 50000).toList();
        System.out.println(students1);

        // Increase salary fess by 10 %
        System.out.println(" Increase salary fess by 10 % ");
        List<Double> doubles = stu.stream().map(s -> s.studentFees * 1.10).toList();
        System.out.println(doubles);

        // get employee details whose salary is grater than average salary
        System.out.println(" get employee details whose salary is grater than average salary ");
        Double collect = stu.stream().collect(Collectors.averagingDouble(Student::studentFees));
        System.out.println(collect);
        List<Student> students2 = stu.stream().filter(s -> s.studentFees > collect).toList();
        System.out.println(students2);

        System.out.println(" What is the average salary of each deparment");
        Map<String, Double> collect1 = stu.stream().collect(Collectors.groupingBy(Student::studentDepartment, Collectors.averagingDouble(Student::studentFees)));
       // System.out.println(collect1);
        collect1.forEach((m,n)-> System.out.println(m+ " "+n));

        System.out.println(" student max fess in each department ");
        Map<String, Optional<Student>> collect2 = stu.stream()
                .collect(Collectors.groupingBy(Student::studentDepartment, Collectors.maxBy(Comparator.comparingDouble(Student::studentFees))));
        System.out.println(collect2);


    }
}
