# How do you optimize a CRUD API that deals with a large dataset

```text
When dealing with a large dataset, optimizing your Spring Boot CRUD API is crucial for performance,
scalability, and efficiency. Below are the best practices to optimize your API.

1️⃣ Use Pagination & Sorting (Avoid Fetching All Data)
❌ Bad Practice:
Fetching all records at once leads to high memory usage and slow responses.

✅ Solution:
Use pagination with Pageable to fetch data in chunks.

📌 Example: Paginated API
Controller

@GetMapping
public ResponseEntity<Page<EmployeeDTO>> getAllEmployees(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "asc") String order) {

    Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<EmployeeDTO> employees = employeeService.getAllEmployees(pageable);
    return ResponseEntity.ok(employees);
}
✔ Prevents memory overload
✔ Improves response time
✔ Supports sorting dynamically

2️⃣ Use DTOs Instead of Entities
Returning JPA entities directly exposes unnecessary data and can lead to performance issues.

✅ Solution:
Use DTOs (Data Transfer Objects) to send only required fields.

📌 Example: DTO Conversion
DTO Class

@Data
public class EmployeeDTO {
    private String name;
    private double salary;
}
Convert Entity to DTO

public EmployeeDTO toDTO(Employee employee) {
    return new EmployeeDTO(employee.getName(), employee.getSalary());
}
✔ Reduces payload size
✔ Improves API security

3️⃣ Use Indexing in the Database
For large datasets, queries should be optimized using indexes.

✅ Solution:
Use indexes for frequently queried fields.

📌 Example: Adding Index

@Entity
@Table(name = "employees", indexes = {
    @Index(name = "idx_employee_name", columnList = "name"),
    @Index(name = "idx_employee_salary", columnList = "salary")
})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double salary;
}
✔ Speeds up search queries
✔ Improves performance in large datasets

4️⃣ Use @Query with LIMIT for Large Data Queries
❌ Bad Practice:
Fetching all records without limiting results.

✅ Solution:
Use @Query with LIMIT to retrieve only the required number of records.

📌 Example: Fetching Top 10 Employees by Salary

@Query("SELECT e FROM Employee e ORDER BY e.salary DESC")
List<Employee> findTop10HighestSalaries(Pageable pageable);
✔ Reduces database load
✔ Improves API speed

5️⃣ Enable Lazy Loading for Large Relationships
❌ Bad Practice:
Using EAGER fetching loads unnecessary related entities.

✅ Solution:
Use LAZY loading for OneToMany, ManyToMany relationships.

📌 Example: Lazy Loading

@Entity
public class Department {
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees;
}
✔ Prevents excessive data loading
✔ Improves API performance

6️⃣ Use Caching to Reduce Database Calls
For frequently accessed data, use caching instead of making repeated database queries.

✅ Solution:
Use Spring Cache or Redis for caching.

📌 Example: Using Spring Cache
Enable Caching

@EnableCaching
@SpringBootApplication
public class EmployeeApplication {
}
Cache API Responses

@Cacheable(value = "employees", key = "#id")
public EmployeeDTO getEmployeeById(Long id) {
    return employeeRepository.findById(id).map(this::toDTO).orElse(null);
}
✔ Reduces database load
✔ Improves API response time

7️⃣ Use Batch Processing for Bulk Inserts/Updates
If your API handles bulk inserts or updates, avoid single row operations.

✅ Solution:
Use Spring Data JPA saveAll() or JDBC Batch Processing.

📌 Example: Bulk Insert Using saveAll()

public List<Employee> saveEmployees(List<Employee> employees) {
    return employeeRepository.saveAll(employees);
}
✔ Improves write performance
✔ Reduces transaction overhead

8️⃣ Optimize Queries Using Specifications (JpaSpecificationExecutor)
For dynamic filtering + complex queries, use JpaSpecificationExecutor.

📌 Example: Filtering Employees with Dynamic Criteria
\
public class EmployeeSpecification {
    public static Specification<Employee> hasSalaryGreaterThan(double salary) {
        return (root, query, cb) -> cb.greaterThan(root.get("salary"), salary);
    }
}
✔ Optimizes database queries dynamically
✔ Useful for API search filters

9️⃣ Use Asynchronous Processing for Heavy Operations
For time-consuming operations, use @Async to avoid blocking API requests.

📌 Example: Asynchronous API Call
java
Copy
Edit
@Async
public CompletableFuture<List<Employee>> fetchLargeEmployeeData() {
    List<Employee> employees = employeeRepository.findAll();
    return CompletableFuture.completedFuture(employees);
}
✔ Non-blocking API calls
✔ Improves request handling performance

🔟 Use Connection Pooling for Database Connections
Using HikariCP (default connection pool in Spring Boot) ensures efficient database connections.

📌 Configure HikariCP in application.properties
properties
Copy
Edit
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
✔ Prevents DB connection exhaustion
✔ Optimizes concurrent requests

🎯 Summary: Best Practices for Large Datasets
Optimization	Benefit
Use Pagination & Sorting	Prevents loading all records at once
Use DTOs Instead of Entities	Reduces API payload size
Use Indexing in DB	Improves query performance
Use @Query with LIMIT	Fetches only required data
Enable Lazy Loading	Avoids unnecessary data fetching
Use Caching (@Cacheable)	Reduces repeated DB queries
Use Batch Processing (saveAll)	Optimizes bulk inserts/updates
Use JpaSpecificationExecutor	Efficient for dynamic filtering
Use @Async for Heavy Operations	Prevents API blocking
Use Connection Pooling (HikariCP)	Efficient DB connections

```
