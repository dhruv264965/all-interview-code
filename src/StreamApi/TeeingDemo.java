package StreamApi;

import java.util.List;
import java.util.stream.Collectors;

//starting from Java 12, you can use the Collectors.teeing collector to compute both average and total salary in a
// single pass and return a custom result.
class SalaryStats {
    double total;
    double average;
    double min;
    double max;
    long count;

    SalaryStats(double total, double average, double min, double max, long count) {
        this.total = total;
        this.average = average;
        this.min = min;
        this.max = max;
        this.count = count;
    }
}
    class Student {
private String name;
private double salary;

// constructor, getters, and toString
public Student(String name, double salary) {
        this.name = name;
        this.salary = salary;
        }
public double getSalary() {
        return salary;
        }
        }
public class TeeingDemo {
    public static void main(String[] args) {
        List<Student> student = List.of(
                new Student("Alice", 60000),
                new Student("Bob", 75000),
                new Student("Charlie", 50000),
                new Student("Diana", 80000)
        );
        SalaryStats stats = student.stream()
                .collect(Collectors.teeing(
                        Collectors.summarizingDouble(Student::getSalary),  // Gives all summary stats
                        Collectors.averagingDouble(Student::getSalary),    // Explicitly include average (for flexibility)
                        (summary, avg) -> new SalaryStats(
                                summary.getSum(),
                                avg,                    // or summary.getAverage()
                                summary.getMin(),
                                summary.getMax(),
                                summary.getCount()
                        )
                ));
        System.out.println("Total Salary: " + stats.total);
        System.out.println("Average Salary: " + stats.average);
        System.out.println("Min Salary: " + stats.min);
        System.out.println("Max Salary: " + stats.max);
        System.out.println("Employee Count: " + stats.count);
    }
}
