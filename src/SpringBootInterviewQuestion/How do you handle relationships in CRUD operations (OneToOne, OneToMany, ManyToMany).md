# How do you handle relationships in CRUD operations (OneToOne, OneToMany, ManyToMany)

```text
1️⃣ One-to-One (@OneToOne) Relationship
Example: Employee ↔ Address (One Employee has One Address)
Each Employee has one Address.

Entity Definitions
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String country;
}
CRUD Operations
Save an Employee with Address

Employee employee = new Employee();
employee.setName("John Doe");

Address address = new Address();
address.setCity("New York");
address.setCountry("USA");

employee.setAddress(address);
employeeRepository.save(employee);
Fetch Employee with Address

Employee emp = employeeRepository.findById(1L).get();
System.out.println(emp.getAddress().getCity());
✔ cascade = CascadeType.ALL → Automatically saves the Address when saving an Employee.
✔ @JoinColumn(name = "address_id") → Ensures a foreign key in the Employee table.

2️⃣ One-to-Many (@OneToMany) Relationship
Example: One Department has Many Employees
Each Department has multiple Employees.

Entity Definitions

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();
}

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
CRUD Operations
Save Department with Employees

Department department = new Department();
department.setName("IT");

Employee emp1 = new Employee();
emp1.setName("Alice");
emp1.setDepartment(department);

Employee emp2 = new Employee();
emp2.setName("Bob");
emp2.setDepartment(department);

department.setEmployees(List.of(emp1, emp2));

departmentRepository.save(department);
Fetch Department with Employees

Department dept = departmentRepository.findById(1L).get();
System.out.println(dept.getEmployees().size()); // Prints number of employees
✔ mappedBy = "department" → Prevents unnecessary join table creation.
✔ cascade = CascadeType.ALL → Automatically saves employees when saving the department.
✔ fetch = FetchType.LAZY → Avoids loading employees until explicitly accessed (best for performance).

3️⃣ Many-to-Many (@ManyToMany) Relationship
Example: Students and Courses (Many Students enroll in Many Courses)
Each Student can enroll in many Courses, and each Course can have many Students.

Entity Definitions

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();
}

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();
}
CRUD Operations
Save Students and Courses

Student student1 = new Student();
student1.setName("Alice");

Student student2 = new Student();
student2.setName("Bob");

Course course1 = new Course();
course1.setTitle("Math");

Course course2 = new Course();
course2.setTitle("Science");

student1.getCourses().add(course1);
student1.getCourses().add(course2);

student2.getCourses().add(course1);

course1.getStudents().add(student1);
course1.getStudents().add(student2);
course2.getStudents().add(student1);

studentRepository.save(student1);
studentRepository.save(student2);
Fetch Student with Courses

Student student = studentRepository.findById(1L).get();
System.out.println(student.getCourses().size());
✔ @JoinTable → Creates a join table (student_course).
✔ Bidirectional mapping → Updates both sides of the relationship.
✔ Avoids redundant records in the database.

🔥 Best Practices for Handling Relationships in CRUD
✅ Use CascadeType.ALL Carefully
✅ Use for parent-child relationships (e.g., @OneToMany).

❌ Avoid for @ManyToMany (can lead to unintended deletions).

✅ Use FetchType.LAZY for Better Performance
Use @OneToMany(fetch = FetchType.LAZY) to avoid N+1 query problem.

Example:
```
