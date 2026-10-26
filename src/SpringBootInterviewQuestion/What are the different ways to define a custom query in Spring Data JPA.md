# What are the different ways to define a custom query in Spring Data JPA

```text
Spring Data JPA provides several ways to define custom queries beyond the default findBy method names.
These include JPQL, Native SQL, @Query annotation, Named Queries, Criteria API, and Specification API.

1️⃣ Using Method Names (Derived Queries)
Spring Data JPA derives queries from method names automatically.

Example

List<Employee> findByDepartment(String department);
🔹 Generated SQL

SELECT * FROM employees WHERE department = ?;
✔ Simple and requires no SQL
❌ Limited flexibility (only supports basic conditions)

2️⃣ Using @Query Annotation (JPQL & Native SQL)
2.1 JPQL (Java Persistence Query Language)
JPQL is database-independent and works with entity objects.

@Query("SELECT e FROM Employee e WHERE e.department = :department")
List<Employee> findEmployeesByDepartment(@Param("department") String department);
✔ Works across different databases
❌ No direct access to native SQL features

2.2 Native SQL Query
For complex queries with SQL functions, use native queries.

@Query(value = "SELECT * FROM employees WHERE department = :department", nativeQuery = true)
List<Employee> findEmployeesByDepartmentNative(@Param("department") String department);
✔ Uses full power of SQL
❌ Tied to a specific database
```
