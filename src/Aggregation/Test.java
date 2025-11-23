//package Aggregation;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Test {
//    public static void main(String[] args) {
//        Student s1=new Student("Dhruv",1,"CSE");
//        Student s2=new Student("UMESH",1,"EE");
//        Student s3=new Student("NISHU",1,"CSE");
//        Student s4=new Student("ATUL",1,"EE");
//        Student s5=new Student("BASHU",1,"EE");
//
//        List<Student> cse_student=new ArrayList<>();
//        cse_student.add(s1);
//        cse_student.add(s3);
//
//        List<Student> ee_student=new ArrayList<>();
//        ee_student.add(s2);
//        ee_student.add(s4);
//        ee_student.add(s5);
//
//        Department CSE=new Department("CSE",cse_student);
//        Department EE=new Department("EE",ee_student);
//        List<Department>departments=new ArrayList<>();
//        departments.add(CSE);
//        departments.add(EE);
//
//        Institute institute=new Institute("BITS",departments);
//
//          System.out.println(institute.getTotalStudentsInInstitute());
////        System.out.println(departments);
////        System.out.println(ee_student);
//
//    }
//}
