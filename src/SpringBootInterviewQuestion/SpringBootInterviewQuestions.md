# Spring Boot Interview Questions

> All 83 topics combined for easy reading.

---

## @Component_@Service_@Repository_@Configuration

@Component is an annotation that allows Spring to automatically detect the custom beans.
1. @Component
Purpose
Marks a class as a Spring-managed component (bean).
It is a generic stereotype for any Spring-managed bean.
@Component
public class UtilityService {
    public String getMessage() {
        return "Hello from UtilityService!";
    }
}
@Component
public class UtilityService {
    public String getMessage() {
        return "Hello from UtilityService!";
    }
}
2. @Service
Purpose
A specialization of @Component.
Indicates that the class provides business logic or service-layer functionality.
@Service
public class EmployeeService {
    public String getEmployeeDetails() {
        return "Employee details";
    }
}
3. @Repository
Purpose
A specialization of @Component.
Indicates that the class interacts with the database or serves as a data access object (DAO).
Provides exception translation for database exceptions to Spring-specific exceptions
(via the @PersistenceExceptionTranslationPostProcessor).
@Repository
public class EmployeeRepository {
    public String findEmployeeById(Long id) {
        return "Employee data for ID: " + id;
    }
}
4. @Configuration
Purpose
Marks a class as a configuration class.
Used to define Spring beans explicitly using @Bean methods.
@Configuration
public class AppConfig {

    @Bean
    public UtilityService utilityService() {
        return new UtilityService();
    }
}



The annotations @Configuration, @Service, @Repository, and @Component are part of the Spring Framework's stereotype annotations. They have specific purposes, but they all help Spring detect and manage beans in the application context. Here's a detailed explanation of each and their differences:

1. @Component
Purpose
Marks a class as a Spring-managed component (bean).
It is a generic stereotype for any Spring-managed bean.
Use Case
Use it for classes that don't fit into other specific stereotypes like @Service, @Repository, or @Controller.
Example
java
Copy code
@Component
public class UtilityService {
    public String getMessage() {
        return "Hello from UtilityService!";
    }
}
2. @Service
Purpose
A specialization of @Component.
Indicates that the class provides business logic or service-layer functionality.
Use Case
Use it for classes in the service layer, which typically contain business logic.
Example
java
Copy code
@Service
public class EmployeeService {
    public String getEmployeeDetails() {
        return "Employee details";
    }
}
3. @Repository
Purpose
A specialization of @Component.
Indicates that the class interacts with the database or serves as a data access object (DAO).
Provides exception translation for database exceptions to Spring-specific exceptions (via the @PersistenceExceptionTranslationPostProcessor).
Use Case
Use it for classes in the data access layer (e.g., interacting with a database or other persistence mechanisms).
Example
java
Copy code
@Repository
public class EmployeeRepository {
    public String findEmployeeById(Long id) {
        return "Employee data for ID: " + id;
    }
}
4. @Configuration
Purpose
Marks a class as a configuration class.
Used to define Spring beans explicitly using @Bean methods.
Use Case
Use it for classes that define bean configurations programmatically.
Example
java
Copy code
@Configuration
public class AppConfig {

    @Bean
    public UtilityService utilityService() {
        return new UtilityService();
    }
}
Differences
Annotation	                        Scope of Use	              Purpose	                      Special Behavior
@Component	       Generic Spring-managed bean	    Used for general-purpose components.	        No special behavior.
@Service	       Service layer (business logic)	Used for business logic classes.	            Semantically indicates a service class.
@Repository	       Data access layer (DAO)	        Used for database interaction classes.	        Exception translation for persistence APIs.
@Configuration	   Configuration classes	        Defines beans using @Bean methods.	            Special handling for configuration classes.

---

## @ConditionalOnProperty_And_@ConditionalOnExpression

Use @ConditionalOnProperty for basic feature toggles (ON/OFF switches).
Use @ConditionalOnExpression when you need logic, multiple conditions, or math-based checks.

Both @ConditionalOnProperty and @ConditionalOnExpression are used to conditionally enable or disable a Spring
 bean based on configuration properties, but they work differently.

1️⃣ @ConditionalOnProperty (For Simple Boolean Checks)
Used when you want to enable/disable a bean based on the presence and value of a property in application.yml or application.properties.
Works only with String/Boolean values.
Does not support complex conditions like arithmetic operations or logical expressions.
✅ Use Case: Enabling/Disabling Features with a Simple Boolean Property

application.yml
feature:
  logging: true

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @ConditionalOnProperty(name = "feature.logging", havingValue = "true", matchIfMissing = false)
    public LoggingService loggingService() {
        return new LoggingService();
    }
}

How It Works?
Property Value	Bean Creation
feature.logging=true	✅ LoggingService bean is created
feature.logging=false	❌ Bean is NOT created
Property not present	❌ Bean is NOT created (default behavior)
🔹 Tip: If you want the bean to be created when the property is missing, set matchIfMissing = true.


2️⃣ @ConditionalOnExpression (For Complex Conditions & SpEL Expressions)
Used when you need more complex conditions beyond simple boolean checks.
Supports Spring Expression Language (SpEL) for logical conditions, arithmetic operations, and multi-property conditions.
More flexible than @ConditionalOnProperty.
✅ Use Case: Enable a Bean Based on Numeric Comparison or Multiple Properties

application.yml
feature:
  enable-reporting: true
  max-users: 50

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @ConditionalOnExpression("${feature.enable-reporting} and ${feature.max-users} > 20")
    public ReportingService reportingService() {
        return new ReportingService();
    }
}

How It Works?
enable-reporting	max-users	Bean Creation
true	>20	✅ Bean is created
false	>20	❌ Bean is NOT created
true	<=20	❌ Bean is NOT created
false	<=20	❌ Bean is NOT created


Scenario	Use
Enabling/disabling a bean based on a single boolean property	@ConditionalOnProperty
Enabling a bean if multiple boolean properties are true	@ConditionalOnProperty(name={"prop1", "prop2"}, havingValue="true")
Enable a bean if feature.enabled=true AND maxUsers > 10	@ConditionalOnExpression("${feature.enabled} and ${maxUsers} > 10")
Enable a bean only if a property is missing	@ConditionalOnProperty(matchIfMissing = true)
Enable a bean when x > 5 OR y == "test"	@ConditionalOnExpression("${x} > 5 or '${y}' == 'test'")

---

## @Configuration  vs @Component

“Both @Configuration and @Component mark a class as a Spring bean, but they serve different purposes.
@Configuration is specifically for defining beans using @Bean methods. Internally, Spring enhances it using CGLIB proxies
 so that method calls return Spring-managed singletons.
 @Component, on the other hand, simply registers the class as a bean without proxying. If you define methods that
  create objects inside a @Component, they won’t be managed by Spring unless explicitly annotated with
   @Bean inside a @Configuration.”

   🔹 4. Example Difference

   👉 Using @Configuration:

   @Configuration
   public class AppConfig {
       @Bean
       public B b() { return new B(); }

       @Bean
       public A a() { return new A(b()); } // returns same B bean
   }


   👉 Using @Component:

   @Component
   public class AppConfig {
       public B b() { return new B(); }
       public A a() { return new A(b()); } // creates new B each time
   }


   With @Configuration, singleton B is reused.

   With @Component, new B() will be created every time a() calls b() (not managed by container).

---

## @EnableAutoConfiguration in spring boot

 @EnableAutoConfiguration->
1->It automatically configure the bean that are present in the classpath.
2->So what ever we added in pom.xml like spring-boot-starter-web  then DispatcherServlet,WebMvcConfigurer,
   Jackson for JSON,Embedded Tomcat or other web servers are requried so no need to configure these thing
   manually
3->Reduces boilerplate configuration — so you don’t have to manually define a lot of @Bean or
   XML configuration.


if we want to exclude some of the beans then we use
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })

@SpringBootApplication-@ComponentScan
                       @EnableAutoConfiguration
                       @Configuration
---------------------------------------------------------------------------------------------------
@ComponentScan
1->Tells Spring where to find beans/components whenever it see @Component,@Service, @Repository,@Controller it
   help to discover the bean before manage by DI
2->It scan package and sub packages @ComponentScan(basePackages = "com.example.service")
2->🔁 What Happens at Spring Startup?
   When a Spring application starts, it goes through several phases:

   A->Configuration Phase (Bean Discovery):
     Spring scans your code to find all classes annotated with:@Component,@Service, @Repository,@Controller
      This is where @ComponentScan comes in — it tells Spring which packages to scan.

  B-> Registration Phase (Bean Definition Creation):
    For each discovered class, Spring creates a "Bean Definition".
    This is just metadata — Spring hasn’t created actual objects yet, but it knows what needs to be created.

  C-> Instantiation Phase (DI Happens Here):
      Spring creates actual instances of the beans.
      Then, it injects dependencies (via @Autowired, constructors, etc.).
       This is Dependency Injection (DI).

3->Example to explain-
    @Component
    public class UserService {
        @Autowired
        private UserRepository userRepository;
    }
    @Repository
    public class UserRepository {
    }
  Step 1: @ComponentScan finds both UserService and UserRepository.
  Step 2: Spring registers them as beans.
  Step 3: Spring creates a UserRepository instance.
  Step 4: Spring creates a UserService instance and injects the UserRepository into it.
  If UserRepository was not discovered, UserService would fail during DI with a "No qualifying bean" error.

---

## @PreAuthorize Annotation



---

## @Primary_And_@Qualifier

1. @Primary Annotation
Use Case: When multiple beans of the same type exist, @Primary marks one as the default bean.
Scope: Applied at the bean definition level.
Alternative to @Qualifier when no specific bean name is needed.
Example-import org.springframework.context.annotation.Primary;
        import org.springframework.stereotype.Component;

        interface PaymentService {
            void processPayment();
        }

        @Component
        @Primary // This bean will be injected by default
        class CreditCardPaymentService implements PaymentService {
            public void processPayment() {
                System.out.println("Processing payment via Credit Card.");
            }
        }

        @Component
        class PayPalPaymentService implements PaymentService {
            public void processPayment() {
                System.out.println("Processing payment via PayPal.");
            }
        }

        // Injecting the bean
        @Component
        class PaymentProcessor {
            private final PaymentService paymentService;

            public PaymentProcessor(PaymentService paymentService) { // No need for @Qualifier
                this.paymentService = paymentService;
            }

            public void makePayment() {
                paymentService.processPayment();
            }
        }

Output-
                 Processing payment via Credit Card.



2. @Qualifier Annotation
Use Case: Used when multiple beans exist and you want to inject a specific bean.
Scope: Applied at the injection point (constructor, method, or field).
Overrides @Primary if used.
Example-Example-import org.springframework.context.annotation.Primary;
                import org.springframework.stereotype.Component;

                interface PaymentService {
                    void processPayment();
                }

                @Component
                @Primary // This bean will be injected by default
                class CreditCardPaymentService implements PaymentService {
                    public void processPayment() {
                        System.out.println("Processing payment via Credit Card.");
                    }
                }

                @Component
                class PayPalPaymentService implements PaymentService {
                    public void processPayment() {
                        System.out.println("Processing payment via PayPal.");
                    }
                }

                // Injecting the bean
                @Component
                class PaymentProcessorWithQualifier {
                    private final PaymentService paymentService;

                    public PaymentProcessorWithQualifier(@Qualifier("payPalPaymentService") PaymentService paymentService) {
                        this.paymentService = paymentService;
                    }

                    public void makePayment() {
                        paymentService.processPayment();
                    }
                }
      output- Processing payment via PayPal.

    Even though CreditCardPaymentService is @Primary, @Qualifier("payPalPaymentService") overrides it.
    The bean name must match the class name in camelCase (payPalPaymentService).

    Best Practice
    Use @Primary when you have one common bean used most of the time.
    Use @Qualifier when you need to inject a specific bean in different scenarios.

---

## @Qualifier_Annoation

The @Qualifier annotation in Spring is used to resolve ambiguity when multiple beans of the same type exist in the
Spring context. By default, Spring injects beans by type. However, if there are multiple beans of the same type, you
can use @Qualifier to specify which bean to inject.

1.public interface GreetingService {
    void greet();
}
2.@Component("englishGreetingService")
public class EnglishGreetingService implements GreetingService {
    @Override
    public void greet() {
        System.out.println("Hello!");
    }
}
3.@Component("spanishGreetingService")
public class SpanishGreetingService implements GreetingService {
    @Override
    public void greet() {
        System.out.println("¡Hola!");
    }
}
4.Now, you want to inject one specific implementation into a class:
@Component
public class GreetingController {
    private final GreetingService greetingService;

    @Autowired
    public GreetingController(@Qualifier("englishGreetingService") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void sendGreeting() {
        greetingService.greet();
    }
}
5.After step 3 we can give step 5 example
Example 2: Usage with Field Injection
@Component
public class GreetingController {
    @Autowired
    @Qualifier("spanishGreetingService")
    private GreetingService greetingService;

    public void sendGreeting() {
        greetingService.greet();
    }
}
6.Example 3: Usage with @Bean Configuration
@Configuration
public class AppConfig {

    @Bean(name = "englishService")
    public GreetingService englishService() {
        return new EnglishGreetingService();
    }

    @Bean(name = "spanishService")
    public GreetingService spanishService() {
        return new SpanishGreetingService();
    }
}
7. this is for step 6
@Component
public class GreetingController {
    private final GreetingService greetingService;

    @Autowired
    public GreetingController(@Qualifier("spanishService") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void sendGreeting() {
        greetingService.greet();
    }
}

---

## @RequestHeader and @RequestBody and @ResponseBody

@RequestHeader->In Spring Boot, the @RequestHeader annotation is used to bind
a value from the HTTP request headers to a method parameter in your controller.
This allows you to easily access and utilize the values of incoming HTTP headers within your Spring applications.
Use Cases
Extracting metadata (like tokens, user-agent, locale):

@GetMapping("/secure")
public ResponseEntity<?> secureEndpoint(@RequestHeader("Authorization") String token) {
    // validate token
}
Reading all headers as a Map:

@GetMapping("/headers")
public ResponseEntity<String> readAllHeaders(@RequestHeader Map<String, String> headers) {
    headers.forEach((k, v) -> System.out.println(k + " = " + v));
    return ResponseEntity.ok("Headers printed");
}



-----------------------------------------------------------
@RequestBody-> In Spring Boot, the @RequestBody annotation is used to bind the body of an HTTP request
(typically in JSON format) to a Java object.
@RequestBody: Annotation is used to get the request body in the incoming request.
✅ Use Case: Reading JSON data from POST/PUT requests
🧠 Concept:
Maps incoming JSON/XML request body to a Java object.

Spring uses Jackson (by default) to deserialize JSON into your Java object.
example- public class Employee {
             private String name;
             private String department;
             private int salary;

             // Getters and setters
         }
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @PostMapping("/employee")
    public String saveEmployee(@RequestBody Employee employee) {
        return "Saved: " + employee.getName();
    }
}
POST /api/employee
Content-Type: application/json

{
  "name": "Dhruv",
  "department": "CSE",
  "salary": 12000
}



-------------------------------------------------------------------------------
@ResponseBody is used to tell Spring that the return value of a method should be written directly
 to the HTTP response body, rather than being interpreted as a view name (like in JSP or Thymeleaf).
 Converts the return value (Java object) to JSON (or XML) using Jackson or another message converter.

 Automatically sets the response Content-Type to application/json.
 @RestController // this includes @ResponseBody automatically
 @RequestMapping("/api")
 public class MyController {

     @GetMapping("/message")
     @ResponseBody
     public String getMessage() {
         return "Hello from @ResponseBody!";
     }
 }
-------------------------------------------------------------
| Feature              | `@RequestBody`                                                       | `@ResponseBody`                                      |
| -------------------- | -------------------------------------------------------------------- | ---------------------------------------------------- |
| **Direction**        | Reads data **from** the request body (incoming)                      | Writes data **to** the response body (outgoing)      |
| **Use Case**         | To bind the **incoming JSON/XML** to a Java object                   | To return a Java object as **JSON/XML response**     |
| **Used In**          | Controller method parameters                                         | Controller method return type                        |
| **Converter**        | Uses **HttpMessageConverter** (e.g., Jackson) to convert JSON → Java | Uses **HttpMessageConverter** to convert Java → JSON |
| **Default Behavior** | Optional unless you need full request body mapped                    | Included automatically with `@RestController`        |

---

## @Transactional

@Transactional is an annotation provided by Spring to manage transactions declaratively — it ensures
 that a series of operations either complete successfully (commit) or fail together (rollback).

 🧠 1. What it does
 "@Transactional is used to manage database transactions in Spring. It ensures that all operations inside a method
  are executed in a single transaction context. If any exception occurs, the transaction is rolled back automatically."

 ⚙️ 2. How it works under the hood
 "Spring uses AOP (Aspect-Oriented Programming) proxies to wrap the method annotated with @Transactional. Before
 the method executes, Spring opens a transaction. If the method completes successfully, it commits. If a runtime
 exception occurs, it rolls back the transaction."

 📌 3. Where and how to use
 Can be applied at class or method level.

 Typically used in Service layer (not recommended in DAO layer directly).

 In my project, we used @Transactional in the service layer to wrap complex operations like placing an order.
 The method saved the order, updated inventory, and sent notifications. If any step failed, Spring would roll
 back the DB changes automatically, ensuring data consistency. We also used rollbackFor=Exception.class when
 handling checked exceptions."


  1. Programmatic Transaction API (TransactionTemplate / PlatformTransactionManager)
  | Method             | Description                                       |
  | ------------------ | ------------------------------------------------- |
  | `getTransaction()` | Begins a new transaction or joins an existing one |
  | `commit()`         | Commits the transaction                           |
  | `rollback()`       | Rolls back the transaction                        |
@Transactional(
    rollbackFor = Exception.class,
    propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED,
    timeout = 30,
    readOnly = true
)
"I primarily use the declarative @Transactional annotation, but I understand that under the hood, Spring uses
PlatformTransactionManager to manage transactions. It internally calls methods like getTransaction(), commit(),
 and rollback(). For fine-grained control, we can also use TransactionTemplate for programmatic transactions."

---

## @Value and @ConfigurationProperties

The @Value annotation in Spring is used to inject values into fields, method parameters, or constructor
parameters — typically from property files, environment variables, or expressions.
The @Value annotation also binds values from application.properties or application.yml to Spring beans
Inject values from application.properties or application.yml
Inject default values
Inject values using SpEL

If we have some properties in application.properties file . these thing if we want to inject so we can use @Value
Annonation
Example- application.properties
        app.name=My Spring App
        app.version=1.0.1
code->
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class AppInfo {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String version;

    public void printInfo() {
        System.out.println("App Name: " + appName);
        System.out.println("Version: " + version);
    }
}
output- App Name: My Spring App
        App Version: 1.0.1


for constructor- >

use case rembember for interview -> we we are injection in constructor we need to take care if we have multiple
parameter then we need to take care it is mandatory to use @Value Annoation
example ->
     @Autowired
     public oder(Product product , @Value("care@gmail.com")String email){
     this.product=product;
     this.email-email;
     }

      here Product is autowired but for email we need to set otherwise it give error

for example-1️⃣ application.properties
greeting.message=Hello from Spring!

2️⃣ Java Class using @Value in Constructor
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GreetingService {

    private final String message;

    // Constructor injection using @Value
    public GreetingService(@Value("${greeting.message}") String message) {
        this.message = message;
    }

    public void greet() {
        System.out.println(message);
    }
}


-----------------------------------------------------------------------------------------
@ConfigurationProperties is used to map a group of related properties from application.properties or
application.yml into a POJO (Plain Old Java Object).
✔ Binds external properties (application.yml, application.properties) to Java classes.
✔ Supports prefix-based mapping for structured configuration.
✔ Works with YAML and properties files.
✔ Allows validation using @Validated.

1️⃣ Why Use @ConfigurationProperties Instead of @Value?

| Feature                | `@Value`               | `@ConfigurationProperties`         |
| ---------------------- | ---------------------- | ---------------------------------- |
| Use case               | Single value injection | Group of related properties        |
| Supports complex types | ❌                      | ✅ (maps List, Map, nested objects) |
| POJO binding           | ❌                      | ✅                                  |
| Relaxed binding        | Limited                | ✅                                  |


📌 Use @ConfigurationProperties when working with structured, reusable configurations.
📌 Use @Value for injecting a single property.

2️⃣ Setting Up @ConfigurationProperties in Spring Boot
1. Enable Configuration Properties in the Main Class
@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class) // Enable configuration binding
public class SpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }
}
✅ @EnableConfigurationProperties allows Spring Boot to scan configuration classes.

2. Define a Configuration Class (AppConfig.java)
@ConfigurationProperties(prefix = "app")       // Maps properties with 'app' prefix
public class AppConfig {

    private String name;
    private String version;

    // Getters and Setters
}
✅ Maps app.name and app.version from the properties file.

3. Define Properties in application.yml
app:
  name: "MyApp"
  version: "1.0.0"
✅ Spring Boot automatically binds these values to AppConfig.

4. Inject AppConfig into a Component
@RestController
@RequestMapping("/api")
public class ConfigController {

    private final AppConfig appConfig;

    public ConfigController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @GetMapping("/config")
    public String getConfig() {
        return "App Name: " + appConfig.getName() + ", Version: " + appConfig.getVersion();
    }
}
📌 GET Request (GET /api/config)

Response: App Name: MyApp, Version: 1.0.0
✅ The configuration is successfully injected into the controller.

3️⃣ Binding Complex Data Types (List, Map, Nested Objects)
1. Define a Complex Configuration Class
@ConfigurationProperties(prefix = "server")
public class ServerConfig {

    private List<String> hosts;
    private Map<String, String> credentials;
    private Database database;

    // Getters and Setters

    public static class Database {
        private String url;
        private String username;
        private String password;

        // Getters and Setters
    }
}
✅ Supports List, Map, and nested objects (Database).

2. Define Configuration in application.yml
server:
  hosts:
    - "host1.example.com"
    - "host2.example.com"
  credentials:
    username: "admin"
    password: "securepass"
  database:
    url: "jdbc:mysql://localhost:3306/mydb"
    username: "dbuser"
    password: "dbpass"
✅ Values are mapped automatically to ServerConfig.

3. Inject and Use the Configuration
@RestController
@RequestMapping("/api")
public class ServerController {

    private final ServerConfig serverConfig;

    public ServerController(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    @GetMapping("/server-info")
    public String getServerInfo() {
        return "Hosts: " + serverConfig.getHosts() + ", DB URL: " + serverConfig.getDatabase().getUrl();
    }
}
📌 GET Request (GET /api/server-info)

Response: Hosts: [host1.example.com, host2.example.com], DB URL: jdbc:mysql://localhost:3306/mydb
✅ Spring Boot successfully maps nested configurations.

4️⃣ Validating Configuration Properties
Spring Boot allows automatic validation of configuration properties using @Validated.

1. Enable Validation in the Configuration Class
@ConfigurationProperties(prefix = "app")
@Validated
public class AppConfig {

    @NotBlank // Ensures name is not blank
    private String name;

    @Pattern(regexp = "\\d+\\.\\d+\\.\\d+") // Ensures version follows X.Y.Z format
    private String version;

    // Getters and Setters
}
✅ If app.name is blank or app.version is invalid, the application fails to start.

5️⃣ Using @ConstructorBinding for Immutable Configurations
From Spring Boot 2.2+, you can use @ConstructorBinding to make configuration properties immutable (no setters required).

📌 Example: Immutable Configuration Class

@ConfigurationProperties(prefix = "app")
@ConstructorBinding
public record AppConfig(String name, String version) {}
✅ No need for setters — values are set at the time of object creation.

🎯 Summary: Best Practices for Using @ConfigurationProperties
✅ Use @ConfigurationProperties for structured configurations.
✅ Use @Validated to enforce validation rules.
✅ Use @ConstructorBinding for immutable configurations.
✅ Group related properties in a single class instead of multiple @Value injections.
✅ Keep property names consistent and meaningful.

---

## can we access private variable another class without getter and setter

Ways to access private variables without getter/setter:

1. import java.lang.reflect.Field;

   class Person {
       private String name = "Dhruv";
   }

   public class Main {
       public static void main(String[] args) throws Exception {
           Person p = new Person();

           // Get the private field
           Field field = Person.class.getDeclaredField("name");
           field.setAccessible(true);  // Bypass private access

           // Read private variable
           String value = (String) field.get(p);
           System.out.println("Private variable value: " + value);

           // Modify private variable
           field.set(p, "Updated Name");
           System.out.println("Updated value: " + field.get(p));
       }
   }

---

## Changes done in java version after java 8

1. ✅ record (Java 16+)
Perfect for creating DTOs, request/response models in REST APIs.
public record UserDTO(String name, int age) {}
Why useful?
Eliminates boilerplate for immutable objects (used in @RequestBody, @ResponseBody)
Works seamlessly with Jackson (JSON serialization)

2. ✅ var for Local Type Inference (Java 10)
Makes code cleaner in services, repos, and utility classes.
var list = new ArrayList<String>();
var user = userRepository.findById(1).orElseThrow();
Why useful?
Reduces verbosity in data access layers
Makes stream-based operations more readable

3. ✅ New String Methods (Java 11)
Improves handling of input/output, config files, etc.
String config = " \n  ";
if (config.isBlank()) {
    System.out.println("Invalid config");
}
Why useful?
Helpful in data validation, config parsing, logging, etc.

4. ✅ HTTP Client API (Java 11)
Built-in modern HTTP client — no need for RestTemplate or 3rd-party clients in some cases.
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com"))
    .build();
HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
Why useful?
Makes microservice-to-microservice calls without additional dependencies
Async and reactive supported

5. ✅ Text Blocks (Java 15+)
For writing large SQL queries, JSON templates, etc.
String query = """
    SELECT * FROM users
    WHERE role = 'ADMIN'
    ORDER BY created_at DESC
    """;
Why useful?
Cleanly write multi-line SQL/JSON in repositories or configs

6. ✅ Switch Expression (Java 14+)
Improves business logic in service layer.
String status = switch(orderStatus) {
    case PENDING -> "Waiting";
    case SHIPPED -> "Out for delivery";
    default -> "Unknown";
};
Why useful?
More concise business rule expressions
No need for verbose switch-case statements

7. ✅ Pattern Matching (Java 21+)
Makes instanceof checks cleaner.
if (obj instanceof User user) {
    System.out.println(user.getName());
}
Why useful?
Cleaner validation logic in controller or service

8. ✅ Virtual Threads (Java 21 – Loom)
Lightweight threads for handling concurrent API requests.
Thread.startVirtualThread(() -> {
    service.processData();
});
Why useful?
Handle thousands of requests without blocking main threads
Scales microservices with minimal thread pool tuning

🧠 Ideal Interview Answer:
“In Spring Boot and microservices, records are excellent for DTOs, virtual threads scale concurrency,
 and text blocks simplify writing SQL and JSON. The new HttpClient API improves inter-service calls
  without extra dependencies. Also, var and new string methods make service and utility classes more
  concise and readable.”

---

## changes done in multithreading in java 8 and after java 8

Excellent question! Multithreading in Java evolved significantly starting with Java 8 and improved
even more after Java 8, especially in terms of asynchronous, functional, and scalable concurrency.

Here's a clear comparison to help you understand and answer this confidently in interviews.

✅ Multithreading Changes in Java 8 and After
🔹 Java 8: Major Enhancements in Multithreading
Feature	Description	Example
Lambda Expressions	Concise thread creation with lambdas	new Thread(() -> System.out.println("Running")).start();
Functional Interfaces	Runnable, Callable used with lambdas	ExecutorService.submit(() -> "Task")
CompletableFuture	Advanced async programming with chaining	See example below
Stream Parallelism	Process collections in parallel	list.parallelStream().forEach(...)

✅ Java 8 Example – CompletableFuture
CompletableFuture.supplyAsync(() -> fetchData())
    .thenApply(data -> process(data))
    .thenAccept(result -> System.out.println("Done: " + result));
Non-blocking
Chaining tasks
Exception handling with .exceptionally() or .handle()

🔹 After Java 8: Even More Powerful Features
Java Version	Feature	Description
Java 9	Flow API (Reactive Streams)	Back-pressure based Pub/Sub model
Java 19–21	Virtual Threads (Project Loom)	Lightweight threads for massive scalability
Java 21	Structured Concurrency (Preview)	Group multiple threads with single cancel/handle point

✅ Java 21 Example – Virtual Thread
Thread.startVirtualThread(() -> {
    System.out.println("Handled by virtual thread: " + Thread.currentThread());
});
⚡ Virtual threads are super-lightweight, so you can create thousands of concurrent tasks with low memory usage. Perfect for microservices!

✅ Java 21 Example – Structured Concurrency (Preview)
try (var scope = StructuredTaskScope.shutdownOnFailure()) {
    Future<String> user  = scope.fork(() -> fetchUser());
    Future<String> order = scope.fork(() -> fetchOrder());

    scope.join();   // wait for both
    scope.throwIfFailed();

    return user.result() + order.result();
}
Useful for grouping and managing multiple threads as a unit.

🔁 Summary: Evolution of Java Multithreading
Version	Key Feature	Benefits
Java 8	Lambdas, CompletableFuture, parallel streams	Simpler async code
Java 9	Flow API	Reactive programming
Java 11+	Improvements to executors, HTTP client	Better async REST
Java 19+	Virtual Threads	Massive scalability
Java 21	Structured concurrency	Easier concurrent task management

🧠 Interview-Ready Answer:
“Java 8 introduced major improvements in multithreading with CompletableFuture, lambdas, and parallel
 streams, making async programming cleaner and non-blocking. After Java 8, Java 21's virtual threads
 revolutionized concurrency by allowing thousands of lightweight threads, which is perfect for handling
 high-concurrency workloads like APIs in microservices. Structured concurrency also helps manage thread
 groups as one unit, improving reliability.”

---

## Circuit Breaker in microservices

A Circuit Breaker in microservices is a fault-tolerance pattern that prevents a service from calling
a failing or slow remote service repeatedly, thereby avoiding cascading failures across the system.

here we set some thresold if that thresold limit hojayega ..then we willapply resilience
 @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackInventory") if inventory method
                  fail call fallbackfunction rather than giving error message
              after thresold the status is changed closed to open onces reach thresold
Real life - if Service A keeps calling failing Service B, the circuit breaker breaks the connection
            temporarily to protect Service A.

            ✅ Why Use Circuit Breaker in Microservices?
            Prevent repeated calls to a failing service

            Improve system stability

            Allow fallback logic (e.g., cached data or default response)

 Tip for Interview
“Circuit Breaker helps protect microservices from cascading failures. I use Resilience4j with Spring Boot
to wrap remote calls. If the downstream service fails repeatedly, the circuit opens temporarily, and
I use a fallback method to respond gracefully.”

Implementation-
🔧 Implementation using Spring Boot + Resilience4j
1️⃣ Add Dependencies (Maven)
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot3</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
2️⃣ Example Service (Calling Remote API)
@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;
    @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackInventory")
    public String getInventory() {
        return restTemplate.getForObject("http://inventory-service/inventory", String.class);
    }
    public String fallbackInventory(Exception e) {
        return "Inventory Service is currently unavailable";
    }
}
3️⃣ Configuration (application.yml)
resilience4j:
  circuitbreaker:
    instances:
      inventoryService:
        slidingWindowSize: 5
        failureRateThreshold: 50
        waitDurationInOpenState: 10s
        permittedNumberOfCallsInHalfOpenState: 2
        minimumNumberOfCalls: 5
✅ What Happens Here?
If 3 of the last 5 calls fail → Circuit opens.
Next 10 seconds → All requests fail immediately (no remote call).
After 10 seconds → Moves to half-open, allows 2 test calls.
If those succeed → Circuit closes. Otherwise → stays open.

---

## CompletableFuture Used in spring boot

Yes, I’ve used CompletableFuture in my Spring Boot project to perform non-blocking parallel execution of external API calls.

Specifically, I needed to call multiple independent 3rd-party services — like pricing, reviews, and inventory — and
combine their responses to build a complete product response.

Instead of calling APIs sequentially (slow), I used CompletableFuture.supplyAsync() to call them asynchronously
and in parallel, and then combined the results.

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ProductAggregatorService {

    public ProductDTO getProductDetails() throws ExecutionException, InterruptedException {

        CompletableFuture<String> priceFuture = CompletableFuture.supplyAsync(() -> priceService.getPrice());
        CompletableFuture<String> reviewFuture = CompletableFuture.supplyAsync(() -> reviewService.getReviews());
        CompletableFuture<String> stockFuture = CompletableFuture.supplyAsync(() -> inventoryService.getStock());

        // Combine all results after all complete
        CompletableFuture<Void> allDone = CompletableFuture.allOf(priceFuture, reviewFuture, stockFuture);

        allDone.join(); // Wait for all to finish

        // Collect results
        String price = priceFuture.get();
        String reviews = reviewFuture.get();
        String stock = stockFuture.get();

        // Merge all into a final product response
        return new ProductDTO(price, reviews, stock);
    }
}
| Feature            | `ExecutorService`                         | `CompletableFuture`                     |
| ------------------ | ----------------------------------------- | --------------------------------------- |
| Thread Management  | Manual (FixedThreadPool, shutdown needed) | Abstracted (managed by ForkJoinPool)    |
| Async Chaining     | Manual handling of future results         | Fluent `.thenApply()`, `.thenCombine()` |
| Exception Handling | Needs try-catch everywhere                | Fluent `.exceptionally()`               |
| Readability        | Boilerplate-heavy                         | Much cleaner and declarative            |
I used CompletableFuture to call 3rd-party APIs like price, reviews, and inventory in parallel without blocking the main thread.
I used supplyAsync() to run each call in a separate thread and CompletableFuture.allOf() to wait for all responses.
This improved my product service's response time by over 60% and made the code much more maintainable compared to using ExecutorService.


-------------------------------------------------------------------------------------------
how three servics and communicate and improve performance
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Service
public class ProductAggregatorService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public ProductDTO getProductDetails(String productId) throws Exception {

        CompletableFuture<String> priceFuture = CompletableFuture.supplyAsync(() ->
            webClientBuilder.build()
                .get()
                .uri("http://price-service/products/" + productId)
                .retrieve()
                .bodyToMono(String.class)
                .block()
        );

        CompletableFuture<String> reviewFuture = CompletableFuture.supplyAsync(() ->
            webClientBuilder.build()
                .get()
                .uri("http://review-service/products/" + productId)
                .retrieve()
                .bodyToMono(String.class)
                .block()
        );

        CompletableFuture<String> stockFuture = CompletableFuture.supplyAsync(() ->
            webClientBuilder.build()
                .get()
                .uri("http://inventory-service/products/" + productId)
                .retrieve()
                .bodyToMono(String.class)
                .block()
        );

        // Wait for all to complete
        CompletableFuture.allOf(priceFuture, reviewFuture, stockFuture).join();

        // Get results
        String price = priceFuture.get();
        String reviews = reviewFuture.get();
        String stock = stockFuture.get();

        return new ProductDTO(price, reviews, stock);
    }
}
I used CompletableFuture in combination with WebClient to call three microservices (price, review, inventory) in parallel.
This allowed me to reduce response time significantly and avoid blocking threads.
I used CompletableFuture.allOf() to wait for all responses, then assembled the final response object (ProductDTO) from each service's result.

---

## components of a Microservices architecture

API Gateway

Entry point for all clients (mobile, web, etc.)

Handles:

Routing requests to the right microservice

Authentication/authorization

Load balancing

Rate limiting

API versioning

Microservices

Independent, small services that handle a specific business capability.

Each has its own:

Business logic

Database (in most cases)

Communication interface (usually REST, gRPC, or messaging)

Database (per service)

Each microservice owns its data.

Avoids a single “big shared database”.

Could be:

SQL (MySQL, PostgreSQL)

NoSQL (MongoDB, Cassandra, Redis)

Or event stores (Kafka, Event Sourcing)

Service Registry & Discovery

Keeps track of available services and their locations (IP/port).

Useful because services may scale dynamically.

Tools: Eureka, Consul, Zookeeper, Kubernetes DNS

Inter-service Communication

How services talk to each other:

Synchronous: REST, gRPC

Asynchronous: Message brokers (Kafka, RabbitMQ, ActiveMQ)

Configuration Management

Centralized place to manage configs for all services.

Tools: Spring Cloud Config, Consul, etcd

Observability

Logging: Centralized logging (ELK stack: Elasticsearch + Logstash + Kibana, or Splunk)

Monitoring: Metrics & health checks (Prometheus, Grafana, New Relic)

Tracing: Distributed tracing (Jaeger, Zipkin)

Security

Authentication & Authorization (OAuth2, JWT, Keycloak)

API Gateway often enforces security policies.

Containerization & Orchestration

Services are often deployed in containers (Docker).

Managed by Kubernetes, ECS, or Docker Swarm.

Provides scaling, deployment, and resilience.

---

## Controller_RestController_ResponseBody

    1. @Controller
Purpose
Used to define a controller in a Spring MVC application for handling web requests.
Typically used for web applications that return HTML views (server-side rendered pages) or other response types
 like files.
Key Characteristics
Relies on ViewResolver (like Thymeleaf, JSP, etc.) to resolve and render the view.
Methods typically return a view name (a string) that is resolved to a physical view (like an HTML or JSP file).
@Controller
public class WebController {

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("message", "Welcome to the Home Page!");
        return "home"; // Maps to src/main/resources/templates/home.html (if using Thymeleaf)
    }
}



2. @RestController
Purpose
Specialized version of @Controller for building RESTful web services.
Combines @Controller and @ResponseBody.
Key Characteristics
The response is serialized directly to the HTTP response body (e.g., as JSON, XML, or plain text) instead of
rendering a view.
Eliminates the need to annotate each method with @ResponseBody.
@RestController
public class ApiController {

    @GetMapping("/api/greet")
    public String greet() {
        return "Hello, World!";
    }

    @GetMapping("/api/users")
    public List<String> getUsers() {
        return List.of("Alice", "Bob", "Charlie"); // Returns JSON: ["Alice", "Bob", "Charlie"]
    }
}

Key Differences
Feature	                              @Controller	                                @RestController
Purpose	                              For handling web pages (HTML views).	       For RESTful APIs (JSON, XML, etc.).
Response Type	                      Returns a view name.	                        Returns the response body (e.g., JSON).
ViewResolver Dependency	     Works with a ViewResolver (e.g., Thymeleaf, JSP).	     Does not rely on a ViewResolver.
Annotation	                          Needs @ResponseBody for REST responses.	     Combines @Controller and @ResponseBody.
Use Case	                        Server-side rendered web pages.	                 REST APIs for JSON/XML responses.

---

## Dependency_injection

Dependency Injection (DI) is a fundamental concept in Spring Boot and other frameworks of the Spring ecosystem.
It is a design pattern that allows the developer to achieve loose coupling between components by injecting
dependencies into a class rather than creating them internally.
Inversion of Control (IoC): The control of creating and managing dependencies is transferred to the Spring IoC
container.
Loose Coupling: Classes are less dependent on each other because dependencies are managed externally.
Configurable Components: Dependencies can be injected dynamically, making it easier to configure and manage
components.
Constructor based DI->
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceA {
    private final ServiceB serviceB;

    // Constructor-based injection
    @Autowired
    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public void execute() {
        serviceB.performTask();
    }
}

@Component
public class ServiceB {
    public void performTask() {
        System.out.println("Task executed by ServiceB.");
    }
}

setter based-
@Component
public class ServiceA {
    private ServiceB serviceB;

    @Autowired
    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public void doSomething() {
        serviceB.performTask();
    }
}
field based-
@Component
public class ServiceA {
    @Autowired
    private ServiceB serviceB;

    public void doSomething() {
        serviceB.performTask();
    }
}

---

## difference bet abstract and interface

https://www.hungrycoders.com/blog/when-to-use-abstract-classes-vs-interfaces-in-java

---

## Difference between Iterator and ListIterator in Java

Feature	Iterator	ListIterator
Applicable for	All Collection types (List, Set, Queue, etc.)	Only for List implementations (ArrayList,
LinkedList, etc.)
Traversal Direction	Only forward (next())	Both forward (next()) and backward (previous())
Modification	Can only remove elements (remove())	Can remove (remove()), add (add()), and update (set())
elements
Index Access	No direct access to indices	Provides index access using nextIndex() and previousIndex()
Usability	Works with any collection	Works only with lists
Example of Iterator

import java.util.*;

public class IteratorExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);
            // iterator.add("D"); // Not allowed
            // iterator.set("X"); // Not allowed
        }
    }
}
Example of ListIterator

import java.util.*;

public class ListIteratorExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        ListIterator<String> listIterator = list.listIterator();

        while (listIterator.hasNext()) {
            String element = listIterator.next();
            System.out.println(element);

            if (element.equals("B")) {
                listIterator.set("X"); // Updates "B" to "X"
                listIterator.add("Y"); // Adds "Y" after "X"
            }
        }

        System.out.println("Modified List: " + list);
    }
}
Output:


A
B
C
Modified List: [A, X, Y, C]

---

## EhCaching implemation in spring boot

In our project we are using Ehcache - we used it to cache frequently accessed data and improve performance
 by reducing redundant database calls.
step1- we define cache setting in ehcache.xml where we define
 <defaultCache   maxEntriesLocalHeap="1000" // max size
                  timeToLiveSeconds="600"    // kab tk valid hoga
                  eternal="false"  -- it will expire after timeToLiveSeconds jo time hame diya.
             memoryStoreEvictionPolicy="LRU" />  // remove least recently used entries when max size is reached.

				  Example-  <cache name="productCache"
                       maxEntriesLocalHeap="500"
                       timeToLiveSeconds="300"
                      eternal="false"
                      memoryStoreEvictionPolicy="LRU" />

step 2- Add requried dependecny
        spring-boot-starter-cache
         ehcache

step 3 - @SpringBootApplication
         @EnableCaching
		 in main class

step 4- apply caching to the method

        @Service
    public class ProductService {

    @Cacheable(value = "productCache", key = "#productId")
    public Product getProductById(Long productId) {
        // This result will be cached
        return productRepository.findById(productId).orElse(null);
    }

    @CacheEvict(value = "productCache", key = "#productId")
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}

step 5 - the data store in heap memory

---------------------------------------------------------------------
🔁 What Happens on Startup (Step-by-Step)
✅ Spring Boot scans ehcache.xml from resources/ folder.

✅ Beans like EhCacheManagerFactoryBean or auto-configured CacheManager are initialized.

✅ All <cache> definitions are registered with the cache manager.

✅ If you use @EnableCaching, Spring sets up AOP proxies to intercept @Cacheable methods.

✅ When you call a cached method, Ehcache checks its in-memory store (like a map).

✅ If no entry is found → method runs → result stored in cache.

-----------------------------------------------
When the application starts, Spring Boot auto-configures Ehcache if it detects ehcache.xml in the classpath.
It creates an EhCacheCacheManager and registers all caches defined in the XML — for example, productCache,
partMasterCache, etc."

"These caches are stored in Java heap memory by default. As soon as a method annotated with @Cacheable is
called, Spring checks if the result is already in the cache — if not, it runs the method and stores the
result in the corresponding cache."

"The actual cache data is stored in-memory inside the JVM unless we explicitly configure off-heap or disk
storage. Since Ehcache is embedded, it doesn't use any external process unless we enable disk persistence
 or clustering."

---

## Eventual consistency in microservics

Eventual Consistency between microservices refers to the concept that, in a distributed system where data
is replicated across multiple microservices or databases, eventual consistency is maintained over time.
This means that while updates to data may not be immediately reflected across all microservices, they will
eventually converge to a consistent state.

In a microservices architecture, each microservice manages its own data store, and data may be replicated or
cached across multiple microservices for performance and scalability reasons.
However, ensuring that all copies of the data remain consistent can be challenging due to the
distributed nature of the system and the potential for network delays, failures, or conflicts.

To achieve eventual consistency between microservices, various strategies, and techniques can be used, such as:

1. Asynchronous Communication
Microservices can communicate with each other asynchronously, allowing them to continue processing requests
without waiting for a response. This can help reduce the impact of network delays on consistency.

2. Eventual Consistency Models
Microservices can use eventual consistency models, where updates are propagated asynchronously and conflicts
are resolved over time. This allows each microservice to maintain its own copy of the data and eventually
 converge to a consistent state

📌 Example in Microservices
Let’s say you have:
User Service (handles user info)
Notification Service (sends welcome emails)
When a new user signs up:
User Service stores the user data.
Sends an event (UserCreated) to Kafka.
Notification Service consumes the event later and sends the email.
If Kafka is delayed, the email may not go immediately. But eventually,the email will be sent — this is
eventual consistency.

🔄 How It Works
Suppose a user updates their profile picture. In an eventually consistent system:
That update is sent to multiple services or data stores asynchronously.
Some services might see the new picture immediately.
Others might still show the old picture for a short while.
But eventually, all services will show the updated picture once the update is fully propagated.


✅ Ways to Handle Temporary Inconsistencies
1. Asynchronous Event Handling + TTL (Time-To-Live) Caching
Services cache the profile picture, but for a short time (e.g., 30 seconds).

If the update happens, the cache eventually expires and fetches the new data.
🔧 Example:
@Cacheable(value = "userProfilePic", key = "#userId", unless = "#result == null")
public String getProfilePicture(String userId) {
    return userRepository.findPictureByUserId(userId);
}

---

## ExecutorService use in spring boot project and also use callable interface

Yes, I’ve used ExecutorService in my Spring Boot microservice to handle parallel processing of independent tasks,
particularly when dealing with external API calls and batch operations.

In one of my services, I had to fetch data from multiple third-party APIs (e.g., pricing, reviews, inventory).
These APIs were independent, so instead of calling them sequentially, I used ExecutorService to call them in parallel,
 and then merged the responses.

This significantly improved performance (reduced response time from ~3s to ~1s).

ExecutorService executor = Executors.newFixedThreadPool(3);

Callable<String> callPriceAPI = () -> priceService.getPrice();
Callable<String> callReviewAPI = () -> reviewService.getReviews();
Callable<String> callInventoryAPI = () -> inventoryService.getStock();

List<Callable<String>> tasks = Arrays.asList(callPriceAPI, callReviewAPI, callInventoryAPI);

List<Future<String>> futures = executor.invokeAll(tasks);

// Collect results
List<String> responses = new ArrayList<>();
for (Future<String> future : futures) {
    responses.add(future.get());  // blocking call, but all were started in parallel
}

executor.shutdown();  // always shutdown to free threads
Reduced API response time by 2/3

Clean separation of concerns using Callable

Maintained thread safety without manual thread management

In my project, I used ExecutorService to parallelize independent API calls in a product-enrichment module.
Instead of sequentially calling multiple 3rd-party services, I submitted them as tasks and gathered the results using
invokeAll().
This drastically reduced latency and helped improve throughput.
I ensured proper cleanup by calling shutdown() after processing.

---

## FasterXML Jackson Core

Jackson is a popular Java library used for converting Java objects to JSON and vice versa.
It is widely used in Spring Boot applications for handling JSON serialization and deserialization.
Key Features of Jackson:
✅ Converts Java objects to JSON (Serialization).
✅ Converts JSON to Java objects (Deserialization).
✅ Handles complex objects, collections, and custom serialization.
✅ Supports annotations for fine-tuned control.
✅ Can work with different formats like XML, YAML, and CSV.

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.0</version>  <!-- Use latest version -->
</dependency>
jackson-databind includes both jackson-core (basic JSON processing) and jackson-annotations (for handling
JSON mapping
with annotations).

Convert Java Object to JSON (serialization)
public class JacksonExample {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Create a User object
        User user = new User(1, "John Doe", "john@example.com");

        // Convert Java object to JSON string
        String jsonString = objectMapper.writeValueAsString(user);

        // Print JSON
        System.out.println(jsonString);
    }
}
Output (JSON)---{"user_id":1,"name":"John Doe","email":"john@example.com"}

Convert JSON to Java Object (Deserialization)
public class JacksonDeserializationExample {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // JSON string
        String json = "{\"user_id\":1,\"name\":\"John Doe\",\"email\":\"john@example.com\"}";

        // Convert JSON string to Java object
        User user = objectMapper.readValue(json, User.class);

        // Print Java object
        System.out.println("ID: " + user.getId());
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
    }
}

4. Jackson Annotations for Customization
Jackson provides annotations for more control over JSON serialization and deserialization.

4.1 @JsonIgnore (Ignore a Field in JSON)
import com.fasterxml.jackson.annotation.JsonIgnore;

class User {
    private int id;
    private String name;

    @JsonIgnore
    private String password;  // Will not be included in JSON

    // Constructor & Getters
}
Conclusion
🔹 Jackson Core (com.fasterxml.jackson.core) is used for efficient JSON parsing and processing.
🔹 It is widely used in Spring Boot, REST APIs, and microservices.
🔹 Supports annotations (@JsonProperty, @JsonIgnore, @JsonInclude) for customization.
🔹 Spring Boot uses Jackson by default for handling JSON in APIs.

---

## first level and secod level cache

"In my Spring Boot project, I used Hibernate’s second-level caching with Ehcache to avoid repeated DB hits
 for entities like Product. I enabled second-level cache in application.properties, added @Cacheable and
 Hibernate cache annotations on the entity, and defined expiry and size policies in ehcache.xml. This
 improved our read performance significantly under load."

 <config xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
         xmlns='http://www.ehcache.org/v3'>
     <cache alias="com.example.cache.Product">
         <expiry>
             <ttl unit="minutes">5</ttl>
         </expiry>
         <heap unit="entries">100</heap>
     </cache>
 </config>


spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Enable Hibernate second-level cache
spring.jpa.properties.hibernate.cache.use_second_level_cache=true
spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.jcache.JCacheRegionFactory
spring.cache.jcache.config=classpath:ehcache.xml

# Logging SQL
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

---

## get vs load

Hibernate get() fetches the entity eagerly and hits the DB immediately, returning null if not found.
In contrast, load() returns a proxy and delays the DB hit until the entity is accessed — but throws an
exception if the record doesn't exist. I use get() when I need the actual data right away and load() when
I'm okay with lazy loading or need a proxy for efficiency."

---

## how 2 databased are communicated to each other in microservics

“In microservices, we follow the Database per Service principle. So, we never directly connect to
another service's database.

If Service A needs data from Service B, it communicates through a REST API or uses asynchronous messaging
 via Kafka or RabbitMQ. This way, each service stays loosely coupled, independently scalable, and resilient.”

Scenario:
Microservice A: CustomerService with its own DB
Microservice B: InvoiceService with its own DB
If InvoiceService needs customer details:
❌ Don’t join DBs or connect to CustomerService DB directly
✅ Instead:
Call: GET /customers/{id} on CustomerService
Or subscribe to CustomerCreated Kafka event and cache minimal info

✅ Right Ways to Communicate Between Microservices (and Their Databases):
1. REST API (Synchronous Communication)
Each microservice exposes APIs. If one service needs data from another, it calls the other service's API.
🔁 Example:
Order Service needs user info → It calls User Service:
GET http://user-service/api/users/123
Result: Order Service gets data from User DB indirectly via API.
2. Kafka / RabbitMQ (Asynchronous)
Services publish events when data changes. Other services listen and update their own database.
 Example:
User Service publishes UserCreated event
Order Service listens and stores a copy of the user data it needs
This is called event-driven architecture.

diagram-- ─────────────────────────────────────────────────────────────────────
          A. ✅ Synchronous Communication via REST API
          ─────────────────────────────────────────────────────────────────────

          +-------------------+             HTTP Request           +-------------------+
          |  Service A        |  ────────────────────────────────▶ |  Service B        |
          | (Order Service)   |                                     | (User Service)    |
          |                   | ◀────────────  HTTP Response ───── |                   |
          +-------------------+                                     +-------------------+
          | DB A              |                                     | DB B              |
          +-------------------+                                     +-------------------+

          → Order Service calls User Service API to fetch data in real-time.
          → Tight timing dependency (A waits for B).


          ─────────────────────────────────────────────────────────────────────
          B. ✅ Asynchronous Communication via Kafka
          ─────────────────────────────────────────────────────────────────────

          +-------------------+                                     +-------------------+
          |  Service A        |                                     |  Service B        |
          | (User Service)    |                                     | (Order Service)   |
          |                   |                                     |                   |
          | on user created:  |                                     | Kafka Listener    |
          | ▶ Kafka Producer ─┼─────►  Kafka Topic: user-events ───▶| on UserCreated    |
          +-------------------+                                     +-------------------+
          | DB A              |                                     | DB B              |
          +-------------------+                                     +-------------------+

          → User Service publishes a message like "UserCreated".
          → Order Service listens to the event and updates its own DB.
          → Fully decoupled, highly scalable, no waiting.

---------------------------------------------------------------------------------------
example to show 2 db communicate to each other

📘 Scenario:
UserService → Has its own DB → Publishes a UserCreated event

OrderService → Has its own DB → Listens for UserCreated and stores minimal user info it needs

✅ Tools:
Spring Boot

Spring Kafka

Spring Data JPA

MySQL/PostgreSQL (for DB)

🔁 Architecture:

          ┌──────────────┐              Kafka Topic               ┌──────────────┐
          │  UserService │ ─────►  topic: user-events  ─────►    │ OrderService │
          └──────────────┘                                        └──────────────┘
               ▲ DB: User                                               ▲ DB: Order + UserRef
1️⃣ UserService – Publishes to Kafka on user creation
📦 Entity

@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
}
🚀 REST Controller

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired private UserRepository userRepo;
    @Autowired private KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) throws JsonProcessingException {
        User savedUser = userRepo.save(user);

        // Publish event
        String userEvent = mapper.writeValueAsString(savedUser);
        kafkaTemplate.send("user-events", userEvent);

        return ResponseEntity.ok("User created and event sent");
    }
}
2️⃣ OrderService – Consumes Kafka event and updates its own DB
📦 UserRef Entity (only stores partial user info)

@Entity
public class UserRef {
    @Id
    private Long id;
    private String name;
    private String email;
}
🛠️ Kafka Consumer in OrderService

@Service
public class UserEventConsumer {

    @Autowired private UserRefRepository userRefRepo;
    private final ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "user-events", groupId = "order-service")
    public void consume(String message) throws JsonProcessingException {
        UserRef user = mapper.readValue(message, UserRef.class);
        userRefRepo.save(user);
        System.out.println("✅ User info saved in OrderService DB: " + user.getName());
    }
}
🧠 Summary (For Interview):
"In a microservices setup, I avoid direct DB calls across services. Instead, I publish domain events (like UserCreated)
 to Kafka.

The OrderService listens to these events and stores the necessary user info in its own DB. This ensures data
separation, loose coupling, and scalable async communication."

---

## How and where does a Spring application start its execution

1. Where does Spring Boot application start?

A Spring Boot application always starts from the main method of the class annotated with:
@SpringBootApplication (or @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan)

Example:

@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}

2. How does it start execution?
Step 1: JVM Execution
Like any Java program, execution starts from the main method.
Here, SpringApplication.run() is the entry point into the Spring world.

Step 2: SpringApplication.run()
Bootstraps the application:
Creates an ApplicationContext (IoC container).
Starts auto-configuration (enabled by @EnableAutoConfiguration).
Performs component scanning (enabled by @ComponentScan).
Registers all Spring Beans.
Starts the embedded server (Tomcat/Jetty/Undertow) if it’s a web app.

Step 3: ApplicationContext Initialization

Beans are created and dependencies are injected.
@PostConstruct methods and lifecycle callbacks are executed.

Step 4: Run Listeners & CommandLineRunner

Spring triggers ApplicationRunner and CommandLineRunner beans after startup.
Good place for initialization logic.

Step 5: Application Ready

If it’s a web app:
Embedded Tomcat server starts.
Application listens on configured port (e.g., 8080).
If it’s a console app:
Runs till main thread ends or task is completed.

3. Summary Answer for Interview
👉 “A Spring Boot application starts execution from the main method like any normal Java application. The call to
 SpringApplication.run() bootstraps Spring. It creates the ApplicationContext, performs component scanning, applies
  auto-configuration, and starts the embedded server (if web). From that point, Spring manages the lifecycle of
  beans and the application flow.”

---

## How do you handle relationships in CRUD operations (OneToOne, OneToMany, ManyToMany)

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

---

## How do you handle validation while creating or updating an entity

Handling Validation in Spring Boot (Create/Update Operations)
Spring Boot provides Spring Validation (javax.validation / jakarta.validation) to enforce rules on incoming
data while creating or updating an entity.

Step 1: Add Validation Annotations in Entity
Modify the Employee entity with validation constraints using jakarta.validation.constraints:

package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Department is required")
    private String department;
}
🔹 @NotBlank: Ensures the field is not empty.
🔹 @Size(min, max): Defines character limits.
🔹 @Email: Ensures a valid email format.

Step 2: Apply Validation in Controller
Use @Valid annotation in the controller's request body to trigger validation.

package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(updatedEmployee);
    }
}
🔹 @Valid ensures request body validation before reaching the service layer.

Step 3: Customize Validation Error Response
By default, Spring Boot throws a MethodArgumentNotValidException when validation fails. To return custom error messages, create a Global Exception Handler:

package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // Handle other runtime exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

---

## How do you optimize a CRUD API that deals with a large dataset

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

---

## how does instanceOf pattern matching enhance type safety in java 16+

"instanceof is used to test whether a reference points to an object of a specific type. It’s particularly helpful
 in polymorphic code where you receive a superclass reference and want to perform subclass-specific logic.
  Since Java 16, instanceof also supports pattern matching, which allows us to combine the type check and cast in
  one safe step."


“Before Java 16, instanceof only told you the type, and you had to cast manually. With pattern matching, Java now lets you
 check and bind the type in a single, type-safe step, reducing boilerplate and making the code safer and more expressive.
It helps avoid ClassCastException and makes logic like object filtering, validation, and dispatching much cleaner.”

| Feature                      | Before (Java <16)    | Now (Java 16+)                                   |
| ---------------------------- | -------------------- | ------------------------------------------------ |
| Manual casting               | Yes                  | No                                               |
| Risk of `ClassCastException` | Yes                  | No (checked by compiler)                         |
| Accidental type mismatch     | Possible             | Impossible (variable bound only if type matches) |
| Scope of variable            | Global in `if` block | Local and safe                                   |

---

## How logs are working and how info, debug , error internal work



---

## how patch request work and if we update some  of the filed and other field become null then how to resolve this

WE can pass Map as request body to store the the data..below the example

Yes, you can absolutely write a generic @PatchMapping method using Java Reflection to dynamically update
any field in the entity. This avoids writing if checks for each field manually.

    @PatchMapping("/{id}")
     public ResponseEntity<Hotel> updateSomeFieldOFHotel(@PathVariable("id") Long hotelId, @RequestBody Map<String,Object>updates){
             Hotel updateSomeFieldOFHotel=hotelService.updateSomeFieldOFHotel(hotelId,updates);
             return ResponseEntity.ok(updateSomeFieldOFHotel);
     }

 @Override
    public Hotel updateSomeFieldOFHotel(Long hotelId, Map<String, Object> updates) {
        Optional<Hotel> existingHotel= hotelRepository.findById(hotelId);
        if(existingHotel.isPresent()){
             updates.forEach((key,value)->
            {
                Field field = ReflectionUtils.findField(Hotel.class, key); //to get the field from key
                field.setAccessible(true);
                ReflectionUtils.setField(field,existingHotel.get(),value); // to set the field
            });
             return hotelRepository.save(existingHotel.get());

        }
        return null;
    }

---

## how switch and yield work in java 14+

yield keyword to return a value from a switch block
Why yield Instead of return?
return exits the entire method
yield returns a value from the switch expression

✅ New Features in Java 14+ switch:

Switch as an expression (not just a statement)
Arrow syntax (->) for cleaner code
yield keyword to return a value from a switch block
Pattern matching (Java 17+)

2. Modern switch Expression (Java 14+)
int day = 2;

String result = switch (day) {
    case 1 -> "Mon";
    case 2 -> "Tue";
    default -> "Other";
};

System.out.println(result); // Tue

✅ 3. yield in block-form switch (multi-line logic)
String grade = "A";
String message = switch (grade) {
    case "A" -> "Excellent";
    case "B" -> "Good";
    case "C" -> {
        System.out.println("Logging: Grade is C");
        yield "Average";
    }
    default -> "Unknown";
};
System.out.println(message);


"Java 14 introduced switch expressions, making switch usable as an expression with a return value.
The -> syntax eliminates fall-through and makes code cleaner.
When we need multi-line logic inside a case, we use yield to return a value from that block — it improves readability
 and safety."

| Feature       | Description                                 |
| ------------- | ------------------------------------------- |
| `switch ->`   | Arrow-based, concise syntax                 |
| `yield`       | Returns a value from multi-line block cases |
| `switch` expr | Assign switch result directly               |
| Benefit       | Safer, shorter, less boilerplate            |

---

## How ThreadLocal is used in spring boot project to interceptor the request

ThreadLocal<T> is a special Java class that provides thread-local variables.
    Each thread that accesses such a variable has its own, independent copy of the variable, isolated from other threads.

🔹 Where to Use ThreadLocal?
Use ThreadLocal when:
✅ 1. You want to avoid sharing data between threads
Example: Each thread stores a request ID, database connection, or user session.
✅ 2. Storing user context or transaction info
In web apps or microservices, where each request is handled by a separate thread.
✅ 3. Avoiding synchronization for read/write access to shared data
You want thread safety without locking.

| Feature                | `ThreadLocal`                                | `synchronized`                         |
| ---------------------- | -------------------------------------------- | -------------------------------------- |
| **Thread Safety**      | Achieved by keeping local copy per thread    | Achieved by locking (mutex)            |
| **Performance**        | No locking, better performance               | Slower due to lock contention          |
| **Data Sharing**       | No data shared — completely isolated         | Shared data with synchronization       |
| **Use Case**           | Per-thread state like user sessions, db conn | Shared counter, shared data structures |
| **Garbage Collection** | Automatically cleaned after thread dies      | Not related                            |
If threads are reused (e.g., in thread pools), and you forget to remove(), it can lead to memory leaks.

Always clean up: threadLocal.remove();


------------------------------------------------------------------------------------------
Use in spring boot
Updated Architecture:
Request → Filter (set ThreadLocal) → Interceptor (log) → Controller → Service → Repository (save with requestId)
package com.example.demo.filter;

import com.example.demo.context.ThreadLocalContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            // Get request ID from header or generate new
            HttpServletRequest req = (HttpServletRequest) request;
            String requestId = req.getHeader("X-Request-ID");
            if (requestId == null || requestId.isEmpty()) {
                requestId = UUID.randomUUID().toString();
            }

            // Set in ThreadLocal
            ThreadLocalContext.setRequestId(requestId);
            chain.doFilter(request, response);
        } finally {
            // Clean up to avoid memory leaks
            ThreadLocalContext.clear();
        }
    }
}
---------------------------------------------------------------------
how i use Thread local in spring boot project

✅ How I Used ThreadLocal in My Project
Yes, I’ve used ThreadLocal in my Spring Boot project for request tracing and logging purposes.
Specifically, I used ThreadLocal in a custom Filter to store a unique request ID for each incoming HTTP request.
 This allowed me to:
Avoid passing request ID manually across layers
Ensure thread-safe per-request data
Log and persist request context in DB for debugging or auditing

🔧 What I Did:
Created a ThreadLocalContext class
To store and retrieve the request ID per thread.
In doFilter() method:
Extracted or generated a requestId from the HTTP request.
Stored it in ThreadLocal using ThreadLocalContext.set(requestId).
Cleared it using ThreadLocalContext.clear() in the finally block to avoid memory leaks.
Interceptor used the ThreadLocal to log the request ID.
Service Layer used ThreadLocal.get() to persist the request ID in the database along with the endpoint info.

💡 Why I Used ThreadLocal Instead of synchronized
ThreadLocal gave me thread isolation without needing to synchronize access.
No shared state, so no locking needed.
Better performance and cleaner code, especially with Spring's layered architecture.

@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;

            // 1. Get or generate request ID
            String requestId = req.getHeader("X-Request-ID");
            if (requestId == null) {
                requestId = UUID.randomUUID().toString();
            }

            // 2. Set in ThreadLocal
            ThreadLocalContext.setRequestId(requestId);

            // 3. Proceed with request
            chain.doFilter(request, response);
        } finally {
            // 4. Clear ThreadLocal to prevent memory leak
            ThreadLocalContext.clear();
        }
    }
}
In my project, I used ThreadLocal in a custom filter to assign a unique request ID to each incoming HTTP request.
 This ID was stored thread-locally so it could be accessed across different layers (interceptor, service, repository)
  without passing it explicitly. This helped me trace requests and persist logs easily and safely in a multi-threaded
  environment.

---

## how to create custom validation annoattion

✅ Goal: Create a custom annotation @ValidEmail that checks if the given email is valid.
✅ Step 1: Create the Annotation

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    String message() default "Invalid email format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
🔍 Explanation:
@interface ValidEmail: This defines a custom annotation named ValidEmail.

@Constraint(validatedBy = EmailValidator.class):

Tells Java that EmailValidator will contain the logic to validate this annotation.

@Target(...):

Specifies where this annotation can be used.

FIELD: You can use it on class fields (e.g., private String email).

PARAMETER: You can use it in method parameters.

@Retention(RetentionPolicy.RUNTIME):

Makes the annotation available at runtime (so Spring can use it during validation).

message():

Custom error message if the validation fails.

groups() and payload():

These are required for compatibility with the Bean Validation API (even if you don’t use them now).

✅ Step 2: Create the Validator Class
java
Copy
Edit
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) return false;

        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                       "[a-zA-Z0-9_+&*-]+)*@" +
                       "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        return email.matches(regex);
    }
}
🔍 Explanation:
implements ConstraintValidator<ValidEmail, String>:

This tells Java: "I will validate fields annotated with @ValidEmail, and those fields are of type String."

isValid(...):

This method contains your actual validation logic.

email.matches(regex) checks if the email follows a valid pattern.

Returns true if valid, otherwise false.

✅ Step 3: Use the Annotation in DTO
java
Copy
Edit
public class UserDTO {

    @ValidEmail
    private String email;

    // getters and setters
}
🔍 Explanation:
When you use @ValidEmail here, Spring will call your custom validator (EmailValidator) during form submission or API input processing.

If the email is invalid, Spring will return a 400 Bad Request with the message from message() in the annotation.

✅ Step 4: Validate in Controller
java
Copy
Edit
@PostMapping("/register")
public ResponseEntity<String> register(@Valid @RequestBody UserDTO userDto) {
    return ResponseEntity.ok("User registered successfully!");
}
🔍 Explanation:
@Valid tells Spring to trigger all validation annotations inside the UserDTO.

If email is invalid, Spring returns:

{
  "email": "Invalid email format"
}
✅ Summary:
Part	Purpose
@ValidEmail	Custom annotation declaration
EmailValidator	Actual validation logic using regex
UserDTO	Where the annotation is applied
@Valid in controller	Enables Spring validation


-------------------------------------------------------------------------------
suppose we have employee class abd fiels are name, address, EmpType. empType will be contract and parmanet . we want
to make custom annoation @EmpType
step1- first we  make interface public @interface @EmpType
      and top of that we will give @Constraint(validatedBy = EmpTypeValidate.class)--here we are writing logic
      then also give @Retention(RetentionPolicy.RUNTIME) for running runtime
      and also @Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
step2- now Employee entity class the field name EmpType we will give @EmpType annoation to annoate

---

## how to define custom exception in core java

In core Java, you can define a custom exception by extending either Exception (for checked exceptions) or
RuntimeException (for unchecked exceptions).

1. Checked Exception (Extends Exception)
Checked exceptions must be either handled using try-catch or declared using throws.


// Custom checked exception
class CustomCheckedException extends Exception {
    public CustomCheckedException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            validateAge(15);
        } catch (CustomCheckedException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }
    }

    static void validateAge(int age) throws CustomCheckedException {
        if (age < 18) {
            throw new CustomCheckedException("Age must be 18 or above.");
        }
    }
}
2. Unchecked Exception (Extends RuntimeException)
Unchecked exceptions don't need explicit handling and can propagate automatically.


// Custom unchecked exception
class CustomUncheckedException extends RuntimeException {
    public CustomUncheckedException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        validateNumber(-5);
    }

    static void validateNumber(int num) {
        if (num < 0) {
            throw new CustomUncheckedException("Number must be non-negative.");
        }
    }
}
When to Use Which?
Checked Exception → Use for expected recoverable conditions (e.g., file not found, invalid input).

Unchecked Exception → Use for programming errors (e.g., null references, invalid states).

---

## how to exclude like we dont want service then how to exclude using componentScan

If you want Spring to exclude specific components, like @Service-annotated classes, from
being picked up by @ComponentScan, you can do that using excludeFilters in the @ComponentScan annotation.
Example->@SpringBootApplication
         @ComponentScan(
             basePackages = "com.example",
             excludeFilters = @ComponentScan.Filter(
                 type = FilterType.ANNOTATION,
                 classes = Service.class
             )
         )
         public class MyApp { }

---

## how to handle bulk data using kafka

In cases where we handle bulk data — like file uploads, analytics, or large transactions — real-time
processing can become a bottleneck. So, instead of processing directly in the API, I send the data to
 Kafka or RabbitMQ.

These tools act as a message broker. The API just produces messages (events), and multiple consumer
services pull those messages and process them asynchronously. This way:

The API responds instantly.
Consumers can scale horizontally.
We get retry logic, error queues, and parallel processing for free.
This pattern increases system resilience and is a key part of a scalable, event-driven architecture.”
✅ Example Use Case
“In a previous project, we handled millions of user records for analytics. We offloaded each user
action to Kafka, and downstream microservices consumed them for processing, indexing, and reporting —
all in parallel.”

example------

Client sends a list of users via API → API sends each user as a message to Kafka → Consumer listens
and processes them in parallel.

1️⃣ Add Kafka Dependencies (Maven)

<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
2️⃣ Kafka Configuration (application.yml)

spring:
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: user-processing-group
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
3️⃣ DTO – UserDto.java

public class UserDto {
    private String name;
    private String email;
    // getters & setters
}
4️⃣ Kafka Producer – Sends Users to Kafka

@Service
public class UserProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String TOPIC = "bulk-users";

    public void sendUser(UserDto user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        kafkaTemplate.send(TOPIC, json);
    }
}
5️⃣ REST Controller – Accepts Bulk and Sends to Kafka

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserProducer userProducer;

    @PostMapping("/bulk")
    public ResponseEntity<String> bulkUpload(@RequestBody List<UserDto> users) {
        users.forEach(user -> {
            try {
                userProducer.sendUser(user);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return ResponseEntity.accepted().body("Users sent to Kafka for processing.");
    }
}
6️⃣ Kafka Consumer – Processes Messages Asynchronously

@Service
public class UserConsumer {

    @KafkaListener(topics = "bulk-users", groupId = "user-processing-group")
    public void consume(String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UserDto user = mapper.readValue(message, UserDto.class);
        System.out.println("✅ Processing user: " + user.getName());

        // simulate processing
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
🧠 Architecture Flow

Client ---> POST /users/bulk
        |
        |---> Controller (For each UserDto)
                  |
                  |---> Kafka Producer --> Kafka Topic (bulk-users)
                                         |
                        ------------------------------
                        |            |            |
                 Consumer1    Consumer2    Consumer3 (Parallel Processing)
💬 How to Explain in Interview
“Instead of processing bulk user data synchronously in the controller, I used Kafka as a messaging layer.
Each user is serialized to JSON and pushed to a topic.

Background consumers (with @KafkaListener) read these messages and process them asynchronously.
This decouples the API from the processing logic and improves system scalability and fault tolerance.”

---

## How to handle large number of request In a application

Senario-
suppose we have application and large number of user is accesing the application so in this situation
  user service feels so much load on this . to overcome this problem(Amazone big sell-Lakh of user
   using application at same time )

1. Load Balancing in Microservices
   Load balancing ensures incoming traffic is distributed across multiple instances of a microservice to:
    Improve reliability
    Handle more traffic
    Avoid overloading a single instance

    If you have 3 instances of a user-service running, a load balancer (LB) will forward requests
    to all of them based on a chosen algorithm (e.g., round-robin, least-connections, etc.)

    how to create 3 instance-
    An instance of a microservice means a running copy of that service.

   1. If you have a microservice called user-service, running 3 instances means you're running 3 copies
    of that application — possibly on different ports, servers, containers, or pods — at the same time.
   2.  In Kubernetes (Recommended for Production)
        Kubernetes lets you define how many replicas (instances) of a microservice you want.
        Example deployment.yaml:    \
        apiVersion: apps/v1
        kind: Deployment
        metadata:
          name: user-service
        spec:
          replicas: 3  # <--- 3 instances
          selector:
            matchLabels:
              app: user-service
          template:
            metadata:
              labels:
                app: user-service
            spec:
              containers:
                - name: user-service
                  image: your-docker-repo/user-service:latest
                  ports:
                    - containerPort: 8080
-------------------------------------------------------------
I understand making 3 instance . i want to know how load balancer (LB) will forward requests to
all of them based on a chosen algorithm (e.g., round-robin, least-connections, etc.)

🔄 How Load Balancers Forward Requests to Instances
When you have multiple instances of a microservice (like user-service), a load balancer is placed in
front of them.

It receives all client requests and forwards them to the instances based on a chosen algorithm like:
| Algorithm             | What it does                                                        |
| --------------------- | ------------------------------------------------------------------- |
| **Round Robin**       | Sends requests to instances in order (1→2→3→1→...)                  |
| **Least Connections** | Sends request to the instance with the fewest active connections    |
| **Random**            | Picks an instance randomly                                          |
| **IP Hash**           | Uses client IP hash to always go to same instance (sticky sessions) |

1️⃣ Spring Cloud LoadBalancer (Client-side)
If you're using Spring Cloud (with Eureka), the client application itself uses a load balancer
to pick which instance to call.

📦 Example:@LoadBalanced
          @Bean
          public RestTemplate restTemplate() {
              return new RestTemplate(); // Uses round-robin by default
          }


restTemplate.getForObject("http://user-service/api/users", String.class);
Here:
user-service may have 3 instances
Eureka registers all 3
Spring Cloud LoadBalancer picks one (by default using Round-Robin)
⚙️ Customize algorithm:
@Bean
ReactorLoadBalancer<ServiceInstance> loadBalancer(Environment env, LoadBalancerClientFactory factory) {
    String name = env.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
  return new RandomLoadBalancer(factory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
}

2️⃣ Kubernetes (Server-side Load Balancer)
In Kubernetes:
You define a Service object.
That acts as a load balancer to route traffic to pods.
🔧 Example:apiVersion: v1
          kind: Service
          metadata:
            name: user-service
          spec:
            selector:
              app: user-service
            ports:
              - port: 80
                targetPort: 8080
This service forwards to all matching pods (app=user-service)
Uses Round-Robin by default
Kubernetes load balances at network level.

------------------------------------------
if i want to implement Least Connections-Sends request to the instance with the fewest active connections
NGINX Load Balancer (Recommended for Least Connections)-
🧱 NGINX Config:http {
                   upstream user_backend {
                       least_conn;
                       server 127.0.0.1:8080;
                       server 127.0.0.1:8081;
                       server 127.0.0.1:8082;
                   }

                   server {
                       listen 80;

                       location / {
                           proxy_pass http://user_backend;
                       }
                   }
               }
✅ What This Does:
least_conn tells NGINX to forward requests to the backend with the fewest active connections.
You run 3 instances of user-service on ports 8080, 8081, 8082.

NGINX is a web server that also acts as a powerful reverse proxy, load balancer, and HTTP cache.

NGINX Load Balancer = A setup where NGINX forwards incoming client requests to multiple backend
servers (microservice instances) based on a configured algorithm (like round-robin or least-connections).
Advantages of NGINX-
1.NGINX can improve can compress the response
2.Sending the data in chunks
📦 Where NGINX fits in a microservices architecture:
Client → NGINX (Load Balancer) → Multiple Instances of user-service
                                     ├── http://127.0.0.1:8080
                                     ├── http://127.0.0.1:8081
                                     └── http://127.0.0.1:8082
🔁 How It Works
You run 3 instances of your Spring Boot user-service on ports 8080, 8081, and 8082.

NGINX listens on port 80.

When a client hits http://localhost/, NGINX chooses one of the 3 instances based on the algorithm
 (least_conn above) and forwards the request.

---

## How to Handle large set of incomming data

Question-- How to handle large set of data in string boot project
 Scenario:
 Imagine your application receives large JSON payloads from clients (e.g., financial transactions, logs, or IoT data).
 Instead of processing everything immediately and blocking the request thread, we will:

1 Use Kafka for distributed real-time processing
2 Use @Async to process background tasks
3 Use a database for batch storage

1--1️⃣ Kafka-Based Large Data Processing
   This method ensures scalability and real-time processing.

   Step 1: Add Kafka Dependencies
   Add this to your pom.xml:   <dependency>
                                   <groupId>org.springframework.kafka</groupId>
                                   <artifactId>spring-kafka</artifactId>
                               </dependency>
Step 2: Kafka Producer (Sending Data to Kafka)
    import org.springframework.kafka.core.KafkaTemplate;
    import org.springframework.stereotype.Service;

    @Service
    public class KafkaProducerService {

        private final KafkaTemplate<String, String> kafkaTemplate;

        public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
            this.kafkaTemplate = kafkaTemplate;
        }

        public void sendLargeData(String data) {
            kafkaTemplate.send("large-data-topic", data);
            System.out.println("Sent large data to Kafka!");
        }
    }
Step 3: Kafka Consumer (Processing Data from Kafka)
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "large-data-topic", groupId = "group_id")
    public void consumeLargeData(ConsumerRecord<String, String> record) {
        System.out.println("Received data from Kafka: " + record.value());
        processLargeData(record.value());
    }

    private void processLargeData(String jsonData) {
        // Simulate data processing (e.g., store to DB)
        System.out.println("Processing data asynchronously: " + jsonData);
    }
}
Step 4: REST API to Receive Large Data
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
public class LargeDataController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/send")
    public String receiveLargeData(@RequestBody String jsonData) {
        kafkaProducerService.sendLargeData(jsonData);
        return "Data is being processed asynchronously!";
    }
}
✅ How it Works?

The API receives a large JSON payload.
The Kafka producer sends the data to Kafka.
The Kafka consumer processes the data asynchronously.
This prevents system overload and ensures scalability.


-----------------------
2 Use @Async to process background tasks
We can process large data in parallel using @Async.

Step 1: Enable Async Processing
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfig {
}
Step 2: Implement Async Processing Service
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncProcessingService {

    @Async
    public void processLargeData(String jsonData) {
        System.out.println("Processing asynchronously: " + jsonData);
        try {
            Thread.sleep(5000); // Simulating processing delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Processing complete!");
    }
}
Step 3: Call Async Service in Controller
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncProcessingService asyncProcessingService;

    @PostMapping("/process")
    public String processLargeData(@RequestBody String jsonData) {
        asyncProcessingService.processLargeData(jsonData);
        return "Processing started asynchronously!";
    }
}
✅ How it Works?

When a request arrives, processing happens in a separate thread.
The request returns immediately while processing continues in the background.
This prevents long response times.

----------------------
3️⃣ Store Data Efficiently Using Batching
nstead of inserting data row-by-row, we use batch inserts for high performance.

Step 1: Define Entity Class
import jakarta.persistence.*;

@Entity
@Table(name = "large_data")
public class LargeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String data;

    // Constructors, Getters, Setters
}
Step 2: Create Repository
import org.springframework.data.jpa.repository.JpaRepository;

public interface LargeDataRepository extends JpaRepository<LargeData, Long> {
}
Step 3: Batch Insert Service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BatchInsertService {

    @Autowired
    private LargeDataRepository repository;

    @Transactional
    public void saveLargeDataBatch(List<LargeData> dataList) {
        repository.saveAll(dataList);
        System.out.println("Batch insert completed!");
    }
}
Step 4: Controller to Save Data in Batches
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/batch")
public class BatchInsertController {

    @Autowired
    private BatchInsertService batchInsertService;

    @PostMapping("/insert")
    public String insertLargeDataBatch() {
        List<LargeData> dataList = IntStream.range(0, 1000)
                .mapToObj(i -> new LargeData("Data " + i))
                .collect(Collectors.toList());

        batchInsertService.saveLargeDataBatch(dataList);
        return "Batch insert started!";
    }
}
✅ How it Works?

Instead of inserting records one by one, it groups multiple records into a single batch.
This reduces database load and improves performance.


💬 Interviewer: How would you handle large real-time data processing in a Spring Boot project?

✅ Your Answer:
"For handling large data in real-time, I use a combination of asynchronous processing, Kafka, and batch inserts:"

1️⃣ For immediate non-blocking responses, I use @Async to process requests in the background.
2️⃣ For scalable distributed processing, I use Kafka to send and consume data asynchronously.
3️⃣ For efficient storage, I use batch inserts instead of single-row operations.

🚀 This ensures the system handles large-scale requests without performance bottlenecks or memory

----------------
💬 Interviewer: How do you handle large file uploads in Spring Boot?

✅ Your Answer:
"To handle large file uploads efficiently in Spring Boot:"
1️⃣ I use streaming to read the file line by line, avoiding memory issues.
2️⃣ I use @Async to process the file in the background, ensuring the API remains responsive.
3️⃣ I use batch inserts to store user data in MySQL, reducing database overhead.
4️⃣ If needed, I can integrate Kafka or RabbitMQ for distributed processing.

🚀 This ensures efficient and scalable user data processing from file uploads.

---

## How to handle validation to specific API

Question-supoose in microservics project if we want validation in some of the api for example we want to email verification
 for get and post api but we dont want for delete api ..how we can do valdation

 ✅ Approach: Use Spring Interceptor or Custom Annotation + AOP
 ✅ Option 1: Spring HandlerInterceptor
 Use HandlerInterceptor to conditionally check for email verification.

 1. Create Interceptor

 @Component
 public class EmailVerificationInterceptor implements HandlerInterceptor {

     @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         String method = request.getMethod();

         // Only apply for GET and POST
         if ("GET".equalsIgnoreCase(method) || "POST".equalsIgnoreCase(method)) {
             String emailVerified = request.getHeader("X-Email-Verified"); // Or from JWT/session/db

             if (!"true".equalsIgnoreCase(emailVerified)) {
                 response.setStatus(HttpStatus.FORBIDDEN.value());
                 response.getWriter().write("Email not verified.");
                 return false;
             }
         }
         return true; // allow the request
     }
 }
 2. Register the Interceptor
 java
 Copy
 Edit
 @Configuration
 public class WebConfig implements WebMvcConfigurer {

     @Autowired
     private EmailVerificationInterceptor emailVerificationInterceptor;

     @Override
     public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(emailVerificationInterceptor)
                 .addPathPatterns("/**"); // or specific patterns
     }
 }
 ✅ Option 2: Custom Annotation + AOP (Better control)
 1. Create Custom Annotation

 @Target({ElementType.METHOD})
 @Retention(RetentionPolicy.RUNTIME)
 public @interface RequireEmailVerified {
 }
 2. Apply it to methods
 java
 Copy
 Edit
 @GetMapping("/user")
 @RequireEmailVerified
 public ResponseEntity<String> getUser() {
     return ResponseEntity.ok("User details");
 }

 @PostMapping("/user")
 @RequireEmailVerified
 public ResponseEntity<String> createUser() {
     return ResponseEntity.ok("User created");
 }

 @DeleteMapping("/user")
 public ResponseEntity<String> deleteUser() {
     return ResponseEntity.ok("User deleted"); // No annotation = no validation
 }
 3. Create AOP Aspect for Validation
 java
 Copy
 Edit
 @Aspect
 @Component
 public class EmailVerificationAspect {

     @Before("@annotation(RequireEmailVerified)")
     public void checkEmailVerified(JoinPoint joinPoint) {
         // Example: Get email verification info from header, token, DB, etc.
         ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
         String emailVerified = attributes.getRequest().getHeader("X-Email-Verified");

         if (!"true".equalsIgnoreCase(emailVerified)) {
             throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Email not verified.");
         }
     }
 }
 ✅ Summary
 Method	Flexibility	Recommended when
 Interceptor	Basic	Method-based checks (GET/POST)
 Custom Annotation + AOP	High	Per-method validation logic

---

## how we copy the data from entity calss to model class and vice versa if we have souch data we cant use dto

Using ModelMapper / MapStruct (Advanced Libraries)
If you don’t want to manually map every field and need custom mappings, use:
🔸 ModelMapper

ModelMapper mapper = new ModelMapper();
UserModel model = mapper.map(entity, UserModel.class);

🔸 MapStruct (compile-time, faster)

@Mapper
public interface UserMapper {
    UserModel toModel(UserEntity entity);
    UserEntity toEntity(UserModel model);
}
💡 When DTOs Are NOT Used
If you're working only with Model and Entity classes, you can still use the above techniques to:
Prevent exposing Entity to UI
Add computed or derived fields
Decouple persistence and domain logic
💬 Interview Tip – What to Say
Even if we’re not using a DTO, we often map between Entity and Model classes to separate persistence from business logic.
 For small models, manual mapping or BeanUtils.copyProperties works well. For complex cases, we prefer tools like
 ModelMapper or MapStruct to handle nested and custom field mappings efficiently.

---

## How would you reduce the time taken when a transaction occurs in an application

When a transaction occurs (like user payment, DB update, order placement), reducing the time taken means minimizing
 latency and bottlenecks across the stack.

Here are ways to reduce transaction time (with Java + Spring Boot perspective):

1. Database Optimization
✅ Use proper indexing on frequently queried columns (e.g., userId, transactionId).
✅ Use batch inserts/updates instead of multiple single queries.
✅ Avoid N+1 queries (use JOIN fetch, projections, DTOs).
✅ Optimize connection pooling (HikariCP) to avoid waiting for DB connections.

2. Reduce Synchronous Work

✅ Use asynchronous processing (@Async, Kafka, RabbitMQ) for non-critical tasks (e.g., sending email/notifications
 after transaction).
✅ Example: Complete DB commit first, then push notification event asynchronously.

3. Caching

✅ Use Redis/Guava Cache for frequently accessed static data (e.g., exchange rates, product prices).
✅ This reduces repeated DB calls inside a transaction.

4. Transaction Scope Management

✅ Keep the transaction boundaries small (don’t open a transaction for logging/notification).
✅ Use read-only transactions (@Transactional(readOnly = true)) when no update is required.

5. Optimized APIs

✅ Minimize payload size in request/response (avoid unnecessary fields).
✅ Use gRPC or binary protocols if low latency is critical.
✅ Enable connection pooling and HTTP/2 for faster API calls between services.

6. Parallel Execution

✅ If transaction logic involves multiple independent operations (like fraud check + loyalty points + inventory update),
process them in parallel using CompletableFuture.

7. Profiling & Monitoring

✅ Use APM tools (New Relic, AppDynamics, Spring Actuator + Micrometer) to identify bottlenecks.
✅ Optimize the slowest component (DB, service call, serialization).

8. Infrastructure

✅ Scale database with read replicas/sharding if load is high.
✅ Use load balancing at API Gateway level.
✅ Ensure enough thread pool & memory tuning in JVM.

👉 Short Interview Answer Example (STAR):
"In one of my projects, transaction time was high due to synchronous email + notification handling. I reduced it by
moving those tasks to Kafka consumers. This cut the transaction time from ~2s to ~300ms. I also optimized DB queries
with proper indexing and smaller transaction boundaries. As a result, throughput improved significantly."

---

## in spring voot where we will defining or conguration of url

1. Controller Level (Using @RequestMapping)
In a Spring Boot application, URLs are typically defined in controllers using annotations like @RequestMapping,
 @GetMapping, @PostMapping, etc.

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")  // Base URL
public class MyController {

    @GetMapping("/hello")  // Full URL: /api/hello
    public String sayHello() {
        return "Hello, Spring Boot!";
    }

    @PostMapping("/save")
    public String saveData(@RequestBody String data) {
        return "Data saved: " + data;
    }
}
2. Application Properties (application.properties or application.yml)
You can configure URL-related properties here.

Example 1: Change Server Port and Context Path
In application.properties:

properties
Copy
Edit
server.port=8081
server.servlet.context-path=/myapp
Now, the URLs will be prefixed with /myapp, e.g., http://localhost:8081/myapp/api/hello.

In application.yml:

yaml
Copy
Edit
server:
  port: 8081
  servlet:
    context-path: /myapp
Example 2: CORS Configuration
properties
Copy
Edit
spring.web.cors.allowed-origins=http://example.com
spring.web.cors.allowed-methods=GET,POST,PUT,DELETE
3. Configuration in Security (Spring Security)
If you are using Spring Security, you need to configure URL access rules.

java
Copy
Edit
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()  // Allow public URLs
                .requestMatchers("/admin/**").hasRole("ADMIN") // Restrict admin URLs
                .anyRequest().authenticated() // Require authentication for all other URLs
            )
            .formLogin()
            .and()
            .logout();
        return http.build();
    }
}
4. Routing with @PathVariable and @RequestParam
Spring Boot allows dynamic URL configurations.

Example 1: Using @PathVariable
java
Copy
Edit
@GetMapping("/user/{id}")
public String getUserById(@PathVariable int id) {
    return "User ID: " + id;
}
Access URL: http://localhost:8080/user/10

Example 2: Using @RequestParam
java
Copy
Edit
@GetMapping("/search")
public String searchUser(@RequestParam String name) {
    return "Searching for: " + name;
}
Access URL: http://localhost:8080/search?name=John

5. Configuring External API URLs in application.properties
If your app communicates with external services, define API URLs in application.properties.

properties
Copy
Edit
external.api.url=https://api.example.com/v1/
Then, use it in your service class:
pratiksha.kawade@xcela.co.inpratiksha.kawade@xcela.co.in
java
Copy
Edit
@Value("${external.api.url}")
private String apiUrl;
Summary
URL Configuration	Where to Define
REST API Endpoints	In Controllers (@RequestMapping, @GetMapping, etc.)
Context Path & Port	application.properties or application.yml
Security URL Rules	SecurityFilterChain in Security Config
Dynamic URL Parameters	@PathVariable and @RequestParam in Controllers
External API URLs	application.properties (@Value annotation in Services)

---

## Interview

1. What are the key differences between Spring and Spring Boot?

Spring and Spring Boot are both part of the larger Spring ecosystem, but they serve different purposes and offer different features
 Here are the key differences between them:
1. Purpose and Focus
Spring Framework: The core Spring Framework provides comprehensive infrastructure support for developing Java applications.
It focuses on providing a wide range of functionalities, such as dependency injection, aspect-oriented programming, transaction
management, and more. It is modular, meaning you can use only the parts you need for your application.
Spring Boot: Spring Boot is built on top of the Spring Framework and is designed to simplify the process of creating stand-alone,
production-grade Spring applications. It aims to minimize configuration and setup time by offering default configurations and
embedded servers.
2. Configuration
Spring Framework: Requires extensive configuration, usually involving XML or Java-based configuration. Developers need to manually
define beans and configure application settings.
Spring Boot: Reduces the need for manual configuration through auto-configuration and convention over configuration. It uses sensible
 defaults and annotations to automatically configure the application based on the dependencies present in the classpath.
3. Setup and Initialization
Spring Framework: Setting up a Spring application involves creating and configuring a lot of boilerplate code and configuration
files. You need to manually set up the application context and configure dependencies.
Spring Boot: Simplifies the setup process by providing starter dependencies (starter POMs) and a simplified project structure.
It also includes embedded servers, so you can run your application as a stand-alone Java application.
4. Embedded Servers
Spring Framework: Typically requires an external application server (like Tomcat, Jetty, or JBoss) to run the application.
Developers need to package and deploy their application to the server.
Spring Boot: Comes with embedded servers (Tomcat, Jetty, or Undertow), allowing you to run your application directly from the
command line without needing to deploy it to an external server. This makes development, testing, and deployment easier and faster.
5. Production-ready Features
Spring Framework: Does not include built-in production-ready features. Developers need to add and configure additional tools
and libraries for monitoring, health checks, and metrics.
Spring Boot: Provides built-in production-ready features, including health checks, metrics, application monitoring, and logging.
 These features are available out-of-the-box and require minimal configuration.
In summary, while the core Spring Framework provides the foundational tools and infrastructure for building applications,
Spring Boot streamlines the process, offering default configurations and embedded servers to create stand-alone, production-ready
applications quickly and easily.



2. Explain the concept of convention over configuration in Spring Boot.

The concept of "convention over configuration" in Spring Boot is a design philosophy aimed at reducing the amount of configuration
that developers need to provide to get their applications up and running. Instead of requiring extensive XML or Java-based
configuration, Spring Boot uses sensible defaults and conventions to automatically configure most of the components.
This makes development faster, easier, and less error-prone.
Here's a detailed breakdown of how convention over configuration is applied in Spring Boot:
1. Auto-Configuration
Spring Boot automatically configures your application based on the dependencies that are present on the classpath. For instance:
If spring-boot-starter-web is on the classpath, Spring Boot will automatically configure a web server (like Tomcat),
a DispatcherServlet, and other web-related beans.
If spring-boot-starter-data-jpa is present, it will configure an in-memory database, a DataSource, an EntityManagerFactory,
and TransactionManager by default.

2. Default Values
Spring Boot comes with default configurations that work out of the box. For example:
Embedded servers (Tomcat, Jetty) are configured to run on port 8080 by default.
Default database properties are provided for H2 in-memory database if no other database configuration is specified.

3. Starter POMs
Spring Boot provides a set of starter POMs (Project Object Models) to simplify dependency management. For example:
spring-boot-starter-web includes dependencies for building web applications, such as Spring MVC, Jackson (for JSON processing),
and an embedded Tomcat server.

4. Spring Boot Application
A typical Spring Boot application class, annotated with @SpringBootApplication, implicitly defines a lot of configurations:

@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
This single annotation is a combination of three annotations:
@Configuration: Indicates that the class can be used by the Spring IoC container as a source of bean definitions.
@EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property
settings.
@ComponentScan: Tells Spring to look for other components, configurations, and services in the specified package, allowing it to
find the controllers.

5. Properties and YAML Files
Instead of extensive XML configuration files, Spring Boot uses application.properties or application.yml files where developers
can override the default settings:

# application.properties example
server.port=9090
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=secret

Benefits of Convention Over Configuration
Reduced Boilerplate Code: Less configuration code means less maintenance and fewer errors.
Faster Development: Developers can focus on writing business logic instead of configuring the framework.
Easier Learning Curve: New developers can get started quickly without needing to understand the complexities of the framework
 configuration.
Consistency: Using standard conventions ensures a consistent setup across different projects, making it easier for teams to work on
multiple projects.



3. What happens inside run() method of Spring Boot?

The run() method in a Spring Boot application is typically part of the SpringApplication class and serves as the main entry
point for running the application. Here's a breakdown of what happens inside the run() method:
Argument Parsing: The run() method starts by parsing the command-line arguments passed to the application. These arguments can
include various configuration options.
Application Context Creation: The method creates an instance of the ApplicationContext, which is the core container responsible for
 managing beans in Spring. By default, it uses AnnotationConfigApplicationContext or WebApplicationContext depending on whether
 it's a web application.
Environment Preparation: The method prepares the application environment. It sets up ConfigurableEnvironment, which allows access
to environment properties, including system properties and environment variables. This environment is later used to configure beans.
Banner Display: If configured, the Spring Boot banner is displayed in the console. This banner can be customized or disabled.
Application Listener Registration: The method registers various application listeners, which can listen for specific events
(e.g., context refresh, application start, or stop events).
Context Refresh: The application context is refreshed, which involves loading all the necessary beans, configuring them,
and initializing them. This is where all the @Component, @Service, @Repository, and @Controller annotated classes are scanned and
instantiated.
Command Line Runner Execution: If the application has any CommandLineRunner or ApplicationRunner beans, their run() methods are
 executed after the application context is fully initialized. This allows you to execute code after the application has started,
 which is useful for startup tasks like initializing data.
Application Start Event: An ApplicationStartedEvent is published, indicating that the application has started. This event can be used
by other components to react to the application starting.
Application Running: The application runs until it receives a shutdown signal. During this time, it can handle incoming requests
(if it's a web application) or perform background tasks.
Shutdown Hook Registration: Spring Boot registers a shutdown hook with the JVM to ensure a graceful shutdown of the application
context when the application is terminated. This ensures that resources are cleaned up and beans are properly closed.

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }




4. Explain the concept of Relaxed Binding?

Relaxed binding in Spring Boot refers to the framework’s ability to bind properties to fields in a flexible manner. It allows you
to define configuration properties in different formats and automatically maps them to the corresponding fields in your Java classes.
This flexibility is particularly useful when dealing with configuration files such as application.properties or application.yml.

Spring Boot's relaxed binding converts property names into a standardized format and matches them to the field names of your
configuration classes. Here are the different formats that are supported:

kebab-case: my-property-name
snake_case: my_property_name
camelCase: myPropertyName
UpperCamelCase or PascalCase: MyPropertyName


Spring Boot automatically normalizes these formats and binds them to the corresponding Java fields.

# application.properties
# all the below cases are valid (relaxed binding concept)
​
# kebab-case
database.database-name=myDatabase
database.user-name=myUser
database.password=myPassword
​
# snake_case
database.database_name=myDatabase
database.user_name=myUser
database.password=myPassword
​
# camelCase
database.databaseName=myDatabase
database.userName=myUser
database.password=myPassword
​
# UpperCamelCase or PascalCase
database.DatabaseName=myDatabase
database.UserName=myUser
database.Password=myPassword
​



5. What is the difference between Tomcat vs. Netty?

When working with Spring Boot and Spring WebFlux, you might have noticed that they use different embedded servers by default:
Tomcat for Spring Boot
Netty for Spring WebFlux.

But why is that? Let's explore the differences and understand the internal architectures and threading models of Tomcat and Netty.
Tomcat: The Default for Spring Boot
Apache Tomcat is a widely-used web server and servlet container. It's the default choice for traditional Spring Boot applications
due to its robustness and ease of use.

Key Features:
Servlet-Based: Tomcat is built around the Servlet API, which fits naturally with traditional Spring MVC.
Blocking I/O: Uses a thread-per-request model, which means each incoming request is handled by a separate thread.
Ease of Configuration: Simple to set up and configure with Spring Boot, making it ideal for most web applications.

Internal Architecture:
Connector: Manages the network connections, accepting and processing HTTP requests.
Container: Responsible for servlet lifecycle management, including loading, initialization, and invocation.
Threading Model: Tomcat uses a traditional thread pool where each request is processed by a separate thread from the pool.
 This model is straightforward but can lead to scalability issues under high load due to thread contention and memory overhead.

5 Parallel Requests: Tomcat assigns five threads from the pool to handle these requests. Each thread processes its request
synchronously, blocking if any I/O operations are required.

Netty: The Default for Spring WebFlux
Netty is a highly performant, asynchronous event-driven network application framework. It’s the default for Spring WebFlux,
 which is designed for reactive programming.

Key Features:

Event-Driven: Netty uses an event-driven architecture, making it suitable for handling a large number of simultaneous connections
efficiently.
Non-Blocking I/O: Utilizes non-blocking I/O, allowing it to handle many connections with fewer threads.
Scalability: Designed for high concurrency, low latency, and high throughput applications.

Internal Architecture:
Event Loop: Netty’s core component is the event loop, which manages I/O operations asynchronously. An event loop group contains one
or more event loops.
Channel: Represents a connection, such as an incoming HTTP request, and is associated with an event loop.
ThreadingModel: Netty uses a small number of threads to handle many connections. Each event loop runs in a single thread and
handles multiple channels, making it highly scalable.

5 Parallel Requests: Netty assigns these requests to its event loops. Each request is processed asynchronously, with the event
loop threads managing the I/O operations without blocking.

Thread Management:
Tomcat: Uses a larger number of threads, with each thread handling a single request synchronously. This can lead to high
memory consumption and thread contention under heavy load.
Netty: Uses fewer threads, with each thread capable of handling many requests asynchronously. This leads
to better resource utilization and scalability.

Choosing the Right Server
Tomcat: Ideal for traditional web applications with a straightforward request-response model. If you're building standard
Spring MVC applications, Tomcat’s simplicity and robustness make it a great choice.
Netty: Best suited for applications that need to handle a large number of concurrent connections with minimal resource usage.
If you're leveraging reactive programming with Spring WebFlux, Netty’s non-blocking I/O and event-driven architecture provide
superior performance and scalability.




6. Explain the use of @SpringBootApplication?

The @SpringBootApplication annotation is a cornerstone of Spring Boot applications, and its use greatly simplifies the
configuration and setup of Spring-based projects. It is typically placed on the main class of a Spring Boot application.
Here’s a detailed explanation of its use and significance:

What is @SpringBootApplication?
The @SpringBootApplication annotation is a convenience annotation that combines three other annotations:

@Configuration
@EnableAutoConfiguration
@ComponentScan
This combination provides a comprehensive setup for a Spring Boot application with minimal configuration.

Breakdown of Combined Annotations

@Configuration: This annotation indicates that the class can be used by the Spring IoC container as a source of bean definitions.
 It is a part of the core Spring framework.
@EnableAutoConfiguration: This annotation enables the auto-configuration feature of Spring Boot. Auto-configuration attempts
to automatically configure your Spring application based on the jar dependencies you have added. For example, if you have a
spring-boot-starter-web dependency, it will automatically configure a web server.
@ComponentScan: This annotation tells Spring to scan the current package and its sub-packages for Spring components.
It enables component scanning, which allows Spring to discover beans and their dependencies.

Example Usage

Here’s a simple example to demonstrate the use of @SpringBootApplication:

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
​
@SpringBootApplication
public class MySpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}



7. Difference between @Component, @Service, and @Repository?

In Spring, @Component, @Service, and @Repository are all annotations used to define Spring-managed beans. They are used to
 mark classes as Spring components, but they have different semantics and intended use cases. Here’s a detailed comparison and
 explanation of their differences:

@Component

Purpose: It is a generic stereotype for any Spring-managed component.
Usage: It is typically used for classes that do not fit into the other specific stereotypes like @Service or @Repository.
Example: General-purpose classes such as utility classes, custom validators, etc.
import org.springframework.stereotype.Component;
​
@Component
public class MyComponent {
    public void doSomething() {
        // Implementation here
    }
}

@Service

Purpose: It is a specialization of @Component for service layer classes.
Usage: It is used to mark classes that contain business logic and service-related functionality. It helps in conceptual
clarity and makes the intent of the class clear.
Additional Features: While it doesn’t provide additional behavior beyond @Component, it is useful for readability and
for tools and aspects that are aware of the service layer semantics.
Example: Business services, typically the classes that contain business logic and call methods from the repository layer.
import org.springframework.stereotype.Service;
​
@Service
public class MyService {
    public void performService() {
        // Business logic here
    }
}

@Repository

Purpose: It is a specialization of @Component for DAO (Data Access Object) classes.
Usage: It is used to mark classes that interact with the database and perform CRUD operations. This annotation helps Spring to
understand that the class is intended to interact with the database and may contain database-related exceptions.
Additional Features: It has additional features for exception translation. Spring translates database-related exceptions into
 Spring’s DataAccessException, making exception handling more consistent and portable across different databases.
Example: Classes that access the database, typically containing methods for CRUD operations.
import org.springframework.stereotype.Repository;
​
@Repository
public class MyRepository {
    public void saveData(Object data) {
        // Database interaction logic here
    }
}

Key Differences
1. Semantics and Intended Use:

@Component: Generic and can be used for any Spring-managed component.
@Service: Specifically for service layer classes containing business logic.
@Repository: Specifically for data access layer, dealing with database operations.

2. Specialized Features:

@Repository: Adds exception translation for database-related errors.
@Service and @Component: Do not provide additional features beyond bean registration, but they improve code readability and
organization by clarifying the role of the class.

3. Conceptual Layering:

Using @Service and @Repository helps in separating different layers of the application, making it easier to understand and maintain.



8. What is difference between Fixed Delay and Fixed Rate in Spring Boot?

Fixed Delay Scheduling

Fixed delay scheduling ensures that there is a fixed amount of time between the end of the last execution and the start of
the next execution. This means that the next execution will only start after the specified delay period has elapsed following
the completion of the previous execution.

Key Characteristics of Fixed Delay:

Execution Gaps: There is always a fixed gap between the end of one execution and the start of the next.
Handles Long Running Tasks: If a task takes longer to execute than the delay period, the next execution will start immediately
after the previous one finishes.
Variable Start Times: The start time of each execution can vary based on the duration of the task.
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
​
@Component
public class FixedDelayTask {
​
    @Scheduled(fixedDelay = 5000)
    public void executeTask() {
        System.out.println("Fixed delay task - " + System.currentTimeMillis());
    }
}
In above example, the executeTask method is scheduled with a fixed delay of 5000 milliseconds (5 seconds).
If the task takes 2 seconds to execute, the next execution will start 5 seconds after the current execution ends,
resulting in a total cycle of 7 seconds.

Fixed Rate Scheduling

Fixed rate scheduling ensures that tasks are executed at a fixed interval, measured from the start of the last execution to the
start of the next. This approach tries to maintain a consistent rate of execution regardless of the task duration.

Key Characteristics of Fixed Rate:

Regular Intervals: Tasks are scheduled to run at regular intervals, maintaining a consistent execution rate.
Overlapping Executions: If a task takes longer to execute than the interval period, subsequent executions may overlap, potentially
 leading to concurrency issues.
Consistent Start Times: The start times of each execution are consistent and based on the specified interval.
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
​
@Component
public class FixedRateTask {
​
    @Scheduled(fixedRate = 5000)
    public void executeTask() {
        System.out.println("Fixed rate task - " + System.currentTimeMillis());
    }
}
​
In above example, the executeTask method is scheduled with a fixed rate of 5000 milliseconds (5 seconds).
 Regardless of how long the task takes to complete, the method will be invoked every 5 seconds.
 If the task takes longer than 5 seconds to execute, the next execution will begin immediately after the current one,
  potentially causing overlapping executions.

Choosing Between Fixed Delay and Fixed Rate

Use Fixed Delay if you need to ensure there is a specific delay between the end of one execution and the start of the next.
This is suitable for tasks where the execution time can vary, and you want to avoid overlapping executions.

Use Fixed Rate if you need tasks to run at consistent intervals, regardless of how long each execution takes.
This is suitable for tasks that need to be performed at regular intervals, such as periodic data collection or status updates.




9. What are @Primary and @Qualifier annotations used for?

The @Primary and @Qualifier annotations are used in Spring to resolve bean injection ambiguity and provide more control over
which beans are injected in a given scenario. Let's explore each annotation:

@Primary Annotation

The @Primary annotation is used to indicate the primary bean to be injected when multiple beans of the same type are
available in the Spring context. It helps in scenarios where there is ambiguity regarding which bean should be injected by default.

Example:

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
​
@Component
@Primary
public class PrimaryBean implements MyInterface {
    // Implementation
}
​
@Component
class SecondaryBean implements MyInterface {
    // Implementation
}
In this example, if MyInterface is autowired into another component and multiple beans implementing MyInterface are available
in the Spring context, the bean annotated with @Primary will be injected by default.

@Qualifier Annotation

The @Qualifier annotation is used to specify which bean should be injected when multiple beans of the same type are available
and the primary bean is not desired. It allows you to specify the bean's unique identifier or name.

Example:

@Component
@Qualifier("first")
public class FirstBean implements MyInterface {
    @Override
    public void performTask() {
        System.out.println("Task performed by FirstBean");
    }
}
​
@Component
@Qualifier("second")
public class SecondBean implements MyInterface {
    @Override
    public void performTask() {
        System.out.println("Task performed by SecondBean");
    }
}
​
@Component
public class ServiceClass {
​
    private final MyInterface myInterface;
​
    // Use @Qualifier to specify the implementation to inject
    public ServiceClass(@Qualifier("first") MyInterface myInterface) {
        this.myInterface = myInterface;
    }
​
    public void executeTask() {
        myInterface.performTask();
    }
}
​
Use Cases

Primary Annotation: Use @Primary when there is a clear default bean choice for injection.
Qualifier Annotation: Use @Qualifier when you need to specify a particular bean to be injected, especially in scenarios with
multiple beans of the same type.


10. What is Spring Boot Actuator and why is it used?

Spring Boot Actuator is a sub-project of Spring Boot that provides a set of tools for monitoring and managing Spring Boot
applications. It adds several production-ready features to help you monitor and manage your application when it's pushed to
production.

Key Features of Spring Boot Actuator:

Health Checks: Provides endpoints to check the health of the application, such as /actuator/health, which reports the status
of various components and dependencies (like databases, message brokers, etc.).
Metrics: Offers a comprehensive set of metrics for various aspects of the application
(e.g., memory usage, CPU usage, HTTP request metrics, and custom metrics). This is accessible via the /actuator/metrics endpoint.
Auditing: Tracks and logs audit events, helping in tracking significant events and changes within the application.
Environment Information: Provides detailed information about the environment in which the application is running,
including system properties, environment variables, and configuration properties. This can be accessed through
the /actuator/env endpoint.
Application Configuration: Allows access to the configuration properties of the application, offering insights into
how the application is configured.

Why is Spring Boot Actuator Used?

Monitoring: Actuator helps in monitoring the application's health, performance, and resource consumption, which is
crucial for maintaining the reliability and availability of the application.
Diagnostics: Provides tools to diagnose issues within the application, such as memory leaks, thread deadlocks,
and slow performance, facilitating quicker resolution of problems.
Management: Actuator endpoints allow for the management of application configurations and runtime behavior without needing
to restart the application.
Operational Insight: Offers detailed insights into the running state of the application, which helps operations teams to
manage and support the application effectively.
Integration with Monitoring Tools: Actuator metrics can be easily integrated with various monitoring tools like Prometheus,
Grafana, and other APM (Application Performance Management) tools for comprehensive monitoring and alerting.
Ease of Use: It is easy to set up and use within a Spring Boot application, making it an accessible choice for
adding production-ready features to your application.

In summary, Spring Boot Actuator enhances the observability, management, and monitoring capabilities of a Spring Boot
application, making it a critical component for maintaining and operating applications in production environments.

By default, several actuator endpoints are enabled, but they are only accessible on the local machine for security reasons.
You can enable and configure these endpoints in your application.properties or application.yml file.

Example using application.yml:

management:
  endpoints:
    web:
      exposure:
        include: "*"
      base-path: /actuator
Once enabled and configured, you can access the actuator endpoints using a web browser or tools like curl or Postman. For example:

Health endpoint: http://localhost:8080/actuator/health

Metrics endpoint: http://localhost:8080/actuator/metrics




11. How can you validate two specific conditions in a YAML property file when creating a bean in Spring Boot?

This question is to test your knowledge about Conditional annotations as sometimes we need to instantiate a bean
based on certain conditions or values defined in the application configuration file. This can be accomplished by:

👉 Using the @ConditionalOnExpression annotation as shown below:

@RestController
@RequestMapping("/order")
@ConditionalOnExpression("${controller.enabled} and ${some.value} > 10")
public class OrderController {

}
👉 However, if you need to apply an "and" condition specifically on boolean values, you should use
the @ConditionalOnProperty annotation as shown below:

For non-boolean conditions, @ConditionalOnExpression is your go-to annotation.

@RestController
@RequestMapping("/order")
@ConditionalOnProperty({"property1","property2"})
public class OrderController {

}




12. Your Spring Boot application is experiencing performance issues under high load. What steps would you take to diagnose and
resolve the problem?

This question is to test your knowledge about profiling, monitoring and observability and tool/libraries which needs to be used
to debug performance issue.
 In simple words, profiling means recording or analysis and Profiling tools in Spring Boot are essential for identifying
 bottlenecks and optimizing performance.

Profiling: Focuses on the details of how specific parts of your application perform, helping you find and fix inefficiencies

Monitoring: Continuously checks the overall health and performance of your application in real-time

Observability: Gives a complete picture of your application's behavior by combining data from metrics, logs, and traces,
helping you understand both what is happening and why.

Profiling can take different forms, each focusing on specific aspects of your application:

CPU Profiling: Analyzes which parts of your code are consuming the most CPU resources, helping you optimize performance.
Thread and Memory Profiling: Monitors how threads are managed and how memory is used, identifying potential issues like
 memory leaks or thread contention.
Database Profiling: Examines the performance of database interactions, pinpointing slow queries or inefficient database usage.
Log Analysis: Reviews application logs to uncover hidden issues or patterns that might affect performance.
Real-Time Monitoring: Continuously tracks the application's performance as it runs, alerting you to issues as they occur.
Transaction Tracing: Follows the flow of individual transactions through the application, helping you understand where
bottlenecks or failures occur.

You can use below tools for debugging performance issue:

• JProfiler: CPU and memory usage

• VisualVM: Real time monitoring

• Actuator and Micrometer for application metrics

• Zipkin for tracing the requests






13. How would you scale a Spring Boot application to handle increased traffic, and what Spring Boot features can assist with this?

This scenario tests your understanding of system design principles, particularly Horizontal and Vertical Scaling,
 as well as how to utilize Asynchronous processing and caching in Spring Boot.

Horizontal Scaling: Involves adding more instances of your application to distribute the load, often achieved using container
orchestration platforms like Kubernetes.

Vertical Scaling: Involves upgrading your existing server with more resources (CPU, RAM) to handle increased traffic.

Asynchronous Processing: Use Spring Boot's @Async annotation to handle tasks asynchronously, freeing up resources and improving
response times.

Caching: Implement caching (using @Cacheable and @CacheEvict) to reduce database load and speed up responses by storing
frequently accessed data in memory. Two key annotations in Spring Boot which are used to implement caching are @Cacheable
and @CacheEvict

Cacheable: This annotation is used to cache the result of a method so that subsequent calls with the same parameters
bypass the method execution and fetch the result directly from the cache. It's ideal for methods that fetch data that
doesn't change frequently.

@Cacheable(value = "orders")
public List<Order> getOrders()

CacheEvict: This annotation is used to remove an entry from the cache. It’s useful when the cached data becomes outdated,
 like after updating or deleting an entry.

@CacheEvict(cacheNames = {"order", "orders"},
allEntries = true, key = "hashtag#id")
public Order updateOrder(String id, Order updatedOrder)




14. You are developing a Spring Boot application that handles user requests to access a set of APIs.
You need to implement a logging mechanism that captures the details of incoming requests (like URL, HTTP method, and request body)
 before the controller processes them. How to achieve that in Spring Boot?

This scenario tests your understanding of Interceptors in Spring boot.

Spring Interceptors allow you to intercept HTTP requests before they reach the controller and after the controller
processes them. They are ideal for cross-cutting concerns like logging, security, and data processing.

To log incoming request details, we can use a HandlerInterceptor. The preHandle method in HandlerInterceptor can be
used to capture and log the request information before it reaches the controller.

HandlerInterceptors: Operate within the Spring MVC framework, intercepting requests between the DispatcherServlet
and controllers. Ideal for handling cross-cutting concerns like detailed logging, authorization checks, and manipulating
the Spring context or model. Interceptors provide access to Handler and ModelAndView objects, enabling more fine-grained control.

Filters: Intercept requests before they reach the DispatcherServlet, suitable for broad tasks like authentication,
logging, auditing, and data compression. Filters operate outside the Spring MVC framework, allowing for decoupled functionality.

With Filters, we can manipulate requests before they reach the controllers and outside of Spring MVC. In contrast,
HandlerInterceptors offer fine-grained control for application-specific cross-cutting concerns within the Spring MVC
framework, with access to the target Handler and ModelAndView objects.



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
​
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
​
@Component // Register this as a Spring component
public class LogInterceptor implements HandlerInterceptor {
​
    // Initialize the logger
    private final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
​
    // This method is called before the controller method is invoked
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("preHandle - Request URL: " + request.getRequestURI());
        return true; // Continue the request handling
    }
​
    // This method is called after the controller method is invoked, but before the view is rendered
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        logger.info("postHandle - Request URL: " + request.getRequestURI());
    }
​
    // This method is called after the complete request is finished, for any cleanup activities
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info("afterCompletion - Request URL: " + request.getRequestURI());
        if (ex != null) {
            logger.error("Exception: ", ex); // Log any exceptions that occurred during the request
        }
    }
}
//Register Interceptor
​
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
​
@Configuration // Indicates that this class contains Spring configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
​
    // Register interceptors with the Spring application
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Register the LogInterceptor
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**"); // Intercept all paths; adjust as needed
    }
}




15. You are tasked with securing certain endpoints in a Spring Boot application so that only users with specific roles
 can access them. Users authenticate via tokens that include their roles as claims. How would you configure your application
  to ensure that access to these endpoints is restricted based on user roles, and that the role checks are applied to
  method-level security in your controllers or services?

This scenario tests your understanding of Authentication, Authorization, JWT, PreAuthorize annotation in Spring boot.

Authentication is the process of verifying the identity of a user or system. It checks whether the credentials provided
(like username and password) are valid.

Spring handles authentication using various methods like in-memory authentication, database authentication, or third-party
providers (e.g., OAuth2, LDAP). Upon successful authentication, a Authentication object is created, representing the
authenticated user.

Authorization determines what an authenticated user is allowed to do—i.e., which resources or actions they can access
based on their roles or permissions.

Authorization is managed using roles or authorities. Spring Security evaluates these roles/authorities to grant or deny
access to specific endpoints or functionalities.

@RestController
@RequestMapping("/api")
public class SomeController {
​
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/data")
    public String adminData() {
        return "This is data only for Admins.";
    }
​
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/data")
    public String userData() {
        return "This is data for Users.";
    }
}
​




16. What are differences between @Secured and @PreAuthorize annotations in Spring Security?

@PreAuthorize is the newer and better choice over @Secured because it supports expression-based access control.
With @PreAuthorize, you can use expressions like hasRole, hasAnyRole, permitAll, and more, providing greater flexibility
in defining security rules.

// Common Built-In Expressions
// hasRole([role])
// hasAnyRole([role1, role2])
// permitAll
// denyAll
​
@GetMapping("/user") // Map GET requests to "/api/test/user"
@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')") // Require USER, MODERATOR, or ADMIN role
public String userAccess() {
    return "User Content."; // Return a message accessible by users with the required roles
}
​
/**
 * Endpoint accessible only to users with the MODERATOR role.
 *
 * @return A string message indicating moderator board content.
 */
@GetMapping("/mod") // Map GET requests to "/api/test/mod"
@Secured({"MODERATOR", "ADMIN"}) // Require MODERATOR or ADMIN role
public String moderatorAccess() {
    return "Moderator Board."; // Return a message accessible by moderators
}
​




17. What is JSON Web Token (JWT) and how is it used in Spring Boot?

JSON Web Token (JWT) is an open standard for securely transmitting information as a JSON object. It's digitally
signed using HMAC or RSA/ECDSA, ensuring the data's integrity and authenticity.

📌 Why JWT?

Authorization: Post-login, JWTs allow users to access permitted routes, services, and resources without re-authenticating.
Information Exchange: Securely transmit information, ensuring the sender's identity and data integrity.

🔍 JWT Structure:

1. Header: Specifies the token type and signing algorithm.

{ "alg": "HS256", "typ": "JWT" }

2. Payload: Contains claims (user data and metadata).

{ "sub": "1234567890", "name": "John Doe", "admin": true }

3. Signature: Verifies the token's integrity.

HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret)

🔄 How JWT Works in Spring Boot:

1. User Authentication: User logs in, and the server issues a JWT.

2. Token Usage: For subsequent requests, the client sends the JWT in the Authorization header.

Authorization: Bearer <token>

3. Server Validation: The server verifies the token's signature and grants access to protected resources if valid.

Avoid storing sensitive data in JWT payloads and ensure tokens are short-lived to enhance security.




18. What are the best practices to do Fault tolerance in Spring boot microservices?

Implementing fault tolerance in Spring Boot microservices involves several best practices to ensure that your system
remains resilient in the face of failures. Here are some key practices:

Use Circuit Breaker Pattern: Implement circuit breakers using libraries like Netflix Hystrix or Resilience4j. Circuit breakers
help in preventing cascading failures by providing a fallback mechanism when a service is not responding.
Retry Mechanisms: Implement retry mechanisms for remote service calls. Spring Retry provides support for retrying failed
operations with customizable retry policies.
Timeouts: Set appropriate timeouts for network requests to prevent threads from being blocked indefinitely. This ensures
that resources are not tied up waiting for unresponsive services.
Bulkheads: Isolate different parts of your system using bulkheads to prevent failures in one component from affecting others.
For example, you can configure thread pools for different types of tasks to prevent one type of task from overwhelming the system.
Fail Fast: Detect and respond to failures as quickly as possible to minimize their impact. This includes quickly closing
connections and releasing resources when a failure occurs.
Health Checks: Implement health checks for your microservices to monitor their status. This allows you to quickly identify
and respond to failures.
Fallback Mechanisms: Provide fallback mechanisms for critical operations to maintain functionality even when dependent
services are unavailable. This could involve returning cached data or providing default values.
Logging and Monitoring: Implement comprehensive logging and monitoring to track the performance and health of your
microservices. This enables you to identify issues early and take appropriate action.
Graceful Degradation: Design your system to gracefully degrade functionality when resources are limited or under
heavy load. This can involve prioritizing critical operations and disabling non-essential features temporarily.
Automated Testing: Include fault tolerance testing as part of your automated testing strategy.
This involves simulating failures and evaluating how your system responds under different failure scenarios.

By following these best practices, you can build fault-tolerant Spring Boot microservices that are resilient
to failures and provide a reliable user experience.




19. Explain the use of Jenkins for automated builds.

Jenkins is a popular open-source automation server that is widely used for automating various aspects of the software
development process, including building, testing, and deploying applications. Its primary use case is for
Continuous Integration (CI) and Continuous Deployment (CD). Here's a breakdown of its key features and
how it facilitates automated builds:

1. Continuous Integration (CI)

Automated Builds: Jenkins allows developers to automate the process of building their applications from source code.
This involves compiling the code, resolving dependencies, and packaging the application into executable artifacts
(e.g., JAR files for Java applications).
Scheduled Builds: Jenkins can be configured to periodically check the version control system (e.g., Git) for changes.
 When changes are detected, Jenkins triggers a build process automatically. This ensures that new changes are integrated
  into the codebase frequently and consistently.
Parallel Builds: Jenkins supports parallel execution of builds across multiple nodes (machines), enabling faster build
 times and efficient resource utilization, especially for large projects or teams with multiple developers.

2. Extensive Plugin Ecosystem

Integration with Tools and Technologies: Jenkins boasts a vast ecosystem of plugins that extend its functionality.
These plugins enable integration with various development tools, version control systems, testing frameworks, deployment
 platforms, and notification services, making it highly adaptable to different development environments and workflows.

3. Scalability and Flexibility

Distributed Builds: Jenkins supports distributed builds, allowing organizations to scale their CI infrastructure
horizontally by adding more build nodes. This helps distribute the workload and improve build performance, particularly
in larger development teams or complex projects.
Customization: Jenkins provides a high degree of customization through its web-based interface and configuration options.
 Users can define custom build steps, configure build triggers, set up notifications, and tailor the CI/CD pipeline
  to suit their specific requirements.

4. Monitoring and Reporting

Build Monitoring: Jenkins provides real-time visibility into the status and progress of builds through its web dashboard.
 Developers can monitor build logs, view test results, and track build trends over time, facilitating early detection
 of issues and ensuring the overall health of the codebase.
Integration with Reporting Tools: Jenkins integrates seamlessly with various reporting and analytics tools,
allowing teams to generate detailed reports on build metrics, code quality, test coverage, and other key
 performance indicators (KPIs). This data helps teams identify areas for improvement and make data-driven
  decisions to optimize their development process.

5. Continuous Deployment (CD)

Automated Deployment Pipelines: Jenkins can be configured to automate the deployment of applications to different
environments (e.g., staging, production) as part of a CD pipeline. This involves executing deployment scripts,
provisioning infrastructure, and managing configuration settings automatically, ensuring that changes are deployed
consistently and reliably.
Integration with Deployment Tools: Jenkins integrates with popular deployment tools and platforms such as Docker,
Kubernetes, AWS, and Heroku, allowing teams to deploy applications to cloud environments, containerized environments,
 or on-premises infrastructure seamlessly.

In summary, Jenkins simplifies the process of automated builds by providing a flexible, extensible, and scalable
automation platform that streamlines CI/CD workflows. Its rich feature set, extensive plugin ecosystem, and robust
integration capabilities make it a preferred choice for organizations seeking to automate their software development lifecycle.




20. Explain the use of @RestController? and what is Difference between @Controller vs @RestController?

Understanding @RestController in Spring Boot

@RestController

@RestController is a convenience annotation in Spring Boot that combines @Controller and @ResponseBody. It is used
to create RESTful web services. The primary function of @RestController is to simplify the creation of web services
by eliminating the need to annotate each method with @ResponseBody.

Key Points:

It marks the class as a controller where every method returns a domain object instead of a view.
By default, it applies @ResponseBody to all the methods, meaning that the return values of these methods will
be converted to JSON or XML (depending on the configuration) and sent directly in the HTTP response.

@Controller vs. @RestController

@Controller

Used to mark a class as a Spring MVC controller.
Typically used in web applications where the controller’s methods return views (like JSP, Thymeleaf templates).
Methods in a @Controller class are often used to handle HTTP requests and return model and view objects.
To return JSON or XML data from a method, you need to annotate the method with @ResponseBody.

Example of @Controller

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
​
@Controller
public class MyController {
​
    @GetMapping("/greeting")
    @ResponseBody
    public String greeting() {
        return "Hello, World!";
    }
​
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome"; // This will return a view named 'welcome'
    }
}
In this example:


greeting() method returns a plain string which is automatically converted to JSON due to @ResponseBody.
welcome() method returns a view named welcome.

@RestController

Specialized version of @Controller used for RESTful web services.
Combines the functionality of @Controller and @ResponseBody.
Every method in a @RestController class automatically returns data (usually JSON or XML) directly in the HTTP response.

Example of @RestController

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
​
@RestController
@RequestMapping("/api")
public class MyRestController {
​
    @GetMapping("/greeting")
    public String greeting() {
        return "Hello, World!";
    }
}

---

## Lazy Loading and Eager Loading

🔄 1. Lazy Loading (Default in Hibernate for collections)
Definition:
Child entities are not loaded immediately with the parent. They are loaded only when accessed.
Pros:
Improves performance by fetching only what is needed.
Saves memory and DB resources for unused associations.
Cons:
May throw LazyInitializationException if accessed outside of a session.

@Entity
public class Department {
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)  // Lazy loading
    private List<Employee> employees;
}
Department dept = session.get(Department.class, 1); // employees NOT loaded yet
dept.getEmployees().size(); // Triggers SQL query to fetch employees

-----------------------------------------------------------------
⚡ 2. Eager Loading
Definition:
Child entities are loaded immediately with the parent entity (at the time of fetching).
Pros:
Convenient if you always need the child data.
Avoids LazyInitializationException.
Cons:
Slower queries if child data is large.
Unnecessary joins or data fetched even if not used.

@Entity
public class Department {
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)  // Eager loading
    private List<Employee> employees;
}
Department dept = session.get(Department.class, 1); // employees fetched along with department


"Hibernate uses lazy loading by default for collections to optimize performance. However, if my use case
requires related entities to always be loaded together, I use eager loading. I choose based on performance
needs and access pattern. Lazy loading gives me more control, but I ensure access within the session to
avoid exceptions."

---

## New feature we are using after java 8

Here i will highlight important feture we are using after java 8

1->starting from Java 12, you can use the Collectors.teeing collector to compute both average
  and total salary in a single pass and return a custom result.
teeing allows merging two collectors into one result. It's useful when:
You want multiple aggregations at once (like summarizing + averaging).
You want to combine them into a custom object (like SalaryStats).

2-> java record come ..no need to create getter, setter and provide immutable class,
3.>

---

## PathVariable_And_RequestParam

Path Variable-->1. The @PathVariable annotation is used to retrieve data from the URL path. By defining placeholders in the request
             mapping URL, you can bind those placeholders to method parameters annotated with @PathVariable. This allows you to access
             dynamic values from the URL and use them in your code. For example, you can extract a user ID from a URL like /users/123 and
             pass it to a method that retrieves the corresponding user’s details.
             2. Both @PathVariable and @RequestParam annotations simplify the process of extracting data from incoming requests in Spring
              Boot. They provide a clean and declarative way to access dynamic values from URLs and query parameters, making it easier to
               handle and process request data in your REST API endpoints.
             3. code-
             @RestController
               @RequestMapping("/users")
               public class UserController {

                   @GetMapping("/{userId}")
                   public ResponseEntity<User> getUserDetails(@PathVariable Long userId) {
                       // Implementation to fetch user details based on the provided userId
                       // ...
                       return ResponseEntity.ok(user);
                   }
               }

              4.In the above code, the @PathVariable annotation is used to extract the userId from the URL path. The {userId} placeholder is
                 defined in the @GetMapping annotation’s URL mapping. The value of the {userId} placeholder will be extracted and passed as the
                 userId method parameter. When a request is made to /users/123, the value 123 will be passed to the getUserDetails method as the
                 userId parameter. You can then use this ID to fetch the user details from a database or any other data source.


  ------------------------------------------------------------------------------------------------------------------------------------------------
 Request Param--> 1. The @RequestParam annotation is used to extract data from the query parameters in the request URL. Query parameters
                    are the key-value pairs that appear after the ? in a URL. Let’s consider an example where you have a REST API
                    endpoint for searching users based on a query parameter:
                  2. code-
                     @RestController
                     @RequestMapping("/users")
                     public class UserController {

                         @GetMapping("/search")
                         public ResponseEntity<List<User>> searchUsers(@RequestParam("name") String name) {
                             // Implementation to search users based on the provided name
                             // ...
                             return ResponseEntity.ok(users);
                         }
                     }
                  3. In the above code, the @RequestParam annotation is used to extract the name parameter from the query parameters. The name parameter is
                     specified as an argument of the @RequestParam annotation. When a request is made to /users/search?name=John, the value John
                      will be passed to the searchUsers method as the name parameter. You can then use this parameter to perform a search operation
                      based on the provided name.

---

## Relaxed_Binding

Relaxed binding in Spring Boot allows for flexibility when mapping environment properties to your application's configuration
properties.
Here's how it works:
Case insensitivity:
Spring Boot ignores the case of property names when matching them to your configuration properties. For example, server.port and
SERVER_PORT are considered the same.
Hyphen and underscore interchangeability:
You can use hyphens or underscores interchangeably in your property names. For example, spring.datasource.url and spring_datasource_url
 are considered the same.
Nested properties:
You can use dot notation to represent nested properties in your configuration. For example, spring.datasource.username can be mapped
to a property named username within a datasource object in your configuration class.
@ConfigurationProperties("my-app")
public class MyAppConfig {
    private String myProperty;

    // Getter and setter for myProperty
}
You can now set this property in your application.properties file using any of the following formats:
my-app.my-property=value
MY_APP_MY_PROPERTY=value
my_app.myProperty=value
MY_APP_MYPROPERTY=value
Benefits:
Easier to work with environment variables:
Environment variables often use uppercase and underscores, so relaxed binding allows you to map them to your configuration
properties without any extra effort.
More flexibility in naming conventions:
You can choose the naming convention that best suits your project without worrying about exact matches.

---

## RestTemplete

RestTemplate is a synchronous HTTP client in Spring Boot that allows applications to make RESTful API
calls easily. It simplifies communication between microservices by providing methods to send HTTP requests
and handle responses.

---

## second level of cache Ehcache



---

## Singleton_And_Protype_Bean_Scope

1. Singleton Scope
Default Scope: All Spring beans are singleton by default unless specified otherwise.
Behavior: A single instance of the bean is created and shared across the entire Spring application context.
Lifecycle:
Created when the application context is initialized.
Destroyed when the application context is closed.
Component
@Scope("singleton") // Optional, as it is the default
public class SingletonBean {
    public SingletonBean() {
        System.out.println("SingletonBean instance created");
    }
}
test-
@Component
public class TestSingleton {
    private final SingletonBean bean1;
    private final SingletonBean bean2;

    public TestSingleton(SingletonBean bean1, SingletonBean bean2) {
        this.bean1 = bean1;
        this.bean2 = bean2;
    }

    public void test() {
        System.out.println(bean1 == bean2); // true
    }
}
2. Prototype Scope
Behavior: A new instance of the bean is created every time it is requested from the Spring container.
Lifecycle:
Created each time it is injected or explicitly retrieved from the application context.
Managed only at the time of creation. After creation, Spring does not manage its lifecycle.
@Component
@Scope("prototype")
public class PrototypeBean {
    public PrototypeBean() {
        System.out.println("PrototypeBean instance created");
    }
}
@Component
public class TestPrototype {
    private final PrototypeBean bean1;
    private final PrototypeBean bean2;

    public TestPrototype(PrototypeBean bean1, PrototypeBean bean2) {
        this.bean1 = bean1;
        this.bean2 = bean2;
    }

    public void test() {
        System.out.println(bean1 == bean2); // false
    }
}
Key Differences Between Singleton and Prototype Scopes
Feature	                                  Singleton	                        Prototype
Number of Instances	     One shared instance per container	          A new instance every time requested
Lifecycle Management	          Fully managed by Spring	        Managed only during creation
Default Scope	         Yes	                                                No
Use Cases	             Stateless beans	                               Stateful or short-lived beans

---

## SOLID

🔹 SOLID Principles (with Examples in Java)
1. S → Single Responsibility Principle (SRP)
📌 A class should have only one reason to change.
✅ Example (Bad):
class UserService {
    public void addUser(String user) {
        // logic to add user
    }

    public void generateReport() {
        // logic to generate report
    }
}


👉 Here, UserService does two jobs (user management + reporting).

✅ Example (Good):

class UserService {
    public void addUser(String user) {
        // logic to add user
    }
}

class ReportService {
    public void generateReport() {
        // logic to generate report
    }
}


👉 Now each class has a single responsibility.

2. O → Open/Closed Principle (OCP)

📌 Classes should be open for extension but closed for modification.

✅ Example (Bad):

class Payment {
    public void pay(String type) {
        if (type.equals("CreditCard")) {
            // credit card logic
        } else if (type.equals("UPI")) {
            // UPI logic
        }
    }
}


👉 Adding new payment types requires modifying this class.

✅ Example (Good – using Polymorphism):

interface Payment {
    void pay();
}

class CreditCardPayment implements Payment {
    public void pay() {
        System.out.println("Paid using Credit Card");
    }
}

class UpiPayment implements Payment {
    public void pay() {
        System.out.println("Paid using UPI");
    }
}


👉 Now we can add new payment types without modifying existing code.

3. L → Liskov Substitution Principle (LSP)

📌 Objects of a superclass should be replaceable with objects of a subclass without breaking the application.

✅ Example (Bad):

class Bird {
    void fly() { }
}

class Penguin extends Bird {
    @Override
    void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}


👉 Violates LSP because Penguin cannot behave like a Bird.

✅ Example (Good):

abstract class Bird { }

class FlyingBird extends Bird {
    void fly() { }
}

class Penguin extends Bird {
    void swim() { }
}


👉 Now substitution works properly.

4. I → Interface Segregation Principle (ISP)

📌 Clients should not be forced to depend on methods they do not use.

✅ Example (Bad):

interface Worker {
    void work();
    void eat();
}

class Robot implements Worker {
    public void work() { }
    public void eat() { // Robots don’t eat!
        throw new UnsupportedOperationException();
    }
}


👉 Violates ISP.

✅ Example (Good):

interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {
    public void work() { }
    public void eat() { }
}

class Robot implements Workable {
    public void work() { }
}


👉 Now clients depend only on what they need.

5. D → Dependency Inversion Principle (DIP)

📌 High-level modules should not depend on low-level modules. Both should depend on abstractions.

✅ Example (Bad):

class MySQLDatabase {
    public void connect() { }
}

class UserService {
    private MySQLDatabase db = new MySQLDatabase();
}


👉 UserService is tightly coupled to MySQLDatabase.

✅ Example (Good):

interface Database {
    void connect();
}

class MySQLDatabase implements Database {
    public void connect() { }
}

class OracleDatabase implements Database {
    public void connect() { }
}

class UserService {
    private Database db;
    public UserService(Database db) {
        this.db = db;
    }
}


👉 Now we can switch databases easily (loose coupling).

🔹 Quick One-Liner Summary for Interview

S → One class = One responsibility.

O → Extend, don’t modify.

L → Subclasses should be replaceable by parent classes.

I → No unnecessary methods in interfaces.

D → Depend on abstractions, not concrete implementations.

---

## spring bean life

No, race condition and deadlock are not the same. They are both concurrency problems in multithreaded applications,
but they are very different in cause, behavior, and impact.

---

## Spring boot profile

🔹 1. What is a Spring Profile?
A Spring profile is a way to segregate configuration so that different beans or properties can be activated
based on the current environment.

For example:
Use an H2 database in dev profile.
Use a MySQL/PostgreSQL database in prod profile.
Enable verbose logging in dev, but disable in prod.

✅ Step 1: Create Property Files for Each Profile
Create application-<profile>.properties or .yml files:
# application-dev.properties
server.port=8081
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
# application-prod.properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/proddb
spring.datasource.username=root
spring.datasource.password=prodpass

✅ Step 2: Activate the Profile

There are several ways to activate a profile:

In application.properties (default profile):     spring.profiles.active=dev
Using Command Line:                     java -jar myapp.jar --spring.profiles.active=prod

 3. Profile-Specific Beans

 You can use @Profile annotation to create beans that are only available in specific profiles:

 @Configuration
 public class AppConfig {

     @Bean
     @Profile("dev")
     public DataSource devDataSource() {
         // return H2 datasource
     }

     @Bean
     @Profile("prod")
     public DataSource prodDataSource() {
         // return MySQL/Postgres datasource
     }
 }

---

## spring-boot-starter-parent

The spring-boot-starter-parent is a special starter in Spring Boot projects. It provides a parent Maven POM
(Project Object Model) that sets up the foundational configurations for your project. By including it in your
pom.xml, you inherit a set of sensible defaults, dependency management, and other settings, saving time and effort.

Dependency versions: Handles the versions for Spring Boot, Hibernate, Jackson, Tomcat, and more.
Maven defaults: Sensible defaults for building, testing, and packaging the application.
Plugin management: Configurations for Maven plugins.
Property configurations: Common properties like java.version, encoding, etc.

---

## spring-boot-starter-web

Dependencies Included in spring-boot-starter-web
Here’s a breakdown of the main dependencies bundled with spring-boot-starter-web:

spring-web

Provides foundational web functionalities (e.g., RestTemplate, web client).
spring-webmvc

Implements the Model-View-Controller (MVC) architecture.
spring-boot-starter-json

Adds support for JSON serialization/deserialization using Jackson.
spring-boot-starter-tomcat

Includes Tomcat as the default embedded web server.
spring-boot-starter-validation

Adds support for bean validation.
spring-boot-starter

A parent starter that provides essential dependencies like logging (SLF4J, Logback).

---

## transient Keyword in Java

The transient keyword in Java is used to indicate that a field should not be serialized —
meaning it will be ignored when converting an object into a byte stream (for saving to a file,
sending over a network, etc.).

Serialization is the process of converting an object into a byte stream, but not all fields should
be serialized, such as:Passwords Sensitive user info
That’s where transient comes in — to skip such fields.
Syntax-
private transient String password;

---

## Transient_And_Version_Annoatation

@Version annotation is used to specify the version number for a specific entity. Version Number provided
using @Version annotation
is used to prevent concurrent modification to an entity. When an entity is being updated, the version number
is also incremented.
If another transaction tries to update the same entity with the older version number, an exception will be given.
The version number
helps to prevent conflicts between concurrent transactions. @Version annotation is used for optimistic locking.
 Optimistic locking is
a concurrency control mechanism in which it ensures that concurrent transactions will not conflict with each
other. If there is a chance
of conflict during transactions the transaction having an older version number will be aborted
code- package com.example.java_test_application;

      // on the below line creating an entity
      // class for the class of Employee.
      @Entity
      public class Employee {
      // on the below line creating an employee id
      // generated value with the strategy for generation type.
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long empId;
      private String employeeName;
      private String employeeQualification;

      // on the below line we are
      // using version annotation.
      @Version
      private int version;

      }
In the above example, we are considering an Employee entity with a version field annotated with @Version. The version field will be
used for optimistic locking. Whenever this entity field is being updated sequentially the value of the version is also checked to
ensure that it matches the current version in the database. If the version has changed, an exception will be thrown, indicating a
concurrent modification.


----------------------------------------------------------------------------------------------------------------------------------------------
@Transient annotation in Hibernate is used to mark a property or field in an entity class as transient. This means that the field or
property marked as transient should be excluded when the data persists in the database. Wherever an entity is mapped to a database table,
 by default all the non-transient fields and properties are persisted. There are certain fields or properties in an entity that should
 be ignored during the data persistence process such as the data which is being calculated for displaying, temporary variables, and
 other data which is relevant only within the application logic but is not required in the database. By using the @Transient annotation
  to that field or a property Hibernate can exclude that field from the persistence process and the corresponding column will not be
  created in the database table.
  code-  // on the below line we are creating an entity for the
         // ProductInfo class.
         @Entity
         public class ProductInfo {
         	// on the below line we are creating a variable for
         	// product id.
         	@Id
         	@GeneratedValue
         	private Long productID;

         	// on the below line we are creating a string variable
         	// for product name
         	private String productName;

         	// on the below line creating a variable for product
         	// price.
         	private int productPrice;

         	// on the below line we are creating a variable for
         	// discounted price and marking it as transient to
         	// exclude it from data persistence in the database.
         	@Transient
         	private int discountPrice = productPrice * 5 / 100;
         }
In the above example, we are creating a Product Info entity class which has a field for productID, product name, product price a,d a
discounted price. We are marking the discounted price property as @Transient because we have to exclude the value of this variable from
 persisting in the database. We are calculating the value for a discounted price from the product price by using a specific
 multiplication factor. Marking it with @Transient annotation will exclude this variable from adding it to our database operations.


 ---------------------------------------------------------------------------------------------------------------------------------------
 @OrderBy annotation is used in Hibernate to specify the ordering of the elements in the collection valued property of an entity class.
  It is used to define the order of the elements which should be displayed. We can order the data within the entity using a specific
  parameter in ascending or descending order.
  code- // on the below line we are creating an entity for
        // department class.
        @Entity
        public class Department {
        	// on the below line we are creating a variable for
        	// department id.
        	@Id
        	@GeneratedValue
        	private Long departmentID;

        	// on the below line we are creating a string variable
        	// for department name.
        	private String departmentName;

        	// on the below line we are creating a variable for
        	// employees list and ordering it by employee name in
        	// ascending order.
        	@OneToMany(mappedBy = "department")
        	@OrderBy("employeeName ASC")
        	private List<Employee> employees;
        }

        // on the below line we are creating an Employee class.
        @Entity
        public class Employee {
        	// on the below line we are creating an employee id
        	// variable.
        	@Id
        	@GeneratedValue
        	private Long empID;

        	// on the below line we are creating an employee name
        	// variable.
        	private String employeeName;

        	// on the below line we are creating a department
        	// variable and joining it with column using department
        	// id.
        	@ManyToOne
        	@JoinColumn(name = "departmentID")
        	private Department department;
        }
In the above example, the Department entity has a one-to-many relationship with the Employee entity. The @OrderBy annotation is applied
to the employee’s collection in the Department entity. It specifies that the associated Employee entities should be ordered by the
employee name field in ascending order. When the Department entity is being loaded from the Database, then the associated employee
entity will be called and ordered based on the employee names in ascending order. The @OrderBy annotation allows you to control the
order of elements in the collection-valued property which provides flexibility in how elements are retrieved and displayed in the
Spring application using Hibernate.

---

## WebClient

"WebClient is a non-blocking, reactive HTTP client introduced in Spring WebFlux. It is used to make
asynchronous API calls and handle responses efficiently."

💡 Why Use WebClient Instead of RestTemplate?

Non-blocking & Asynchronous → Better performance.
Supports Reactive Streams → Ideal for high-throughput microservices.
More flexible with custom configurations.
Preferred for new Spring Boot projects (Spring 5+).


:
🚀 WebClient Implementation in a Spring Boot Project
Here’s a complete Spring Boot implementation using WebClient to call an external API.

✅ Step 1: Add Dependencies
Add the required dependencies in pom.xml:

<dependencies>
    <!-- Spring Boot WebFlux for WebClient -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>
</dependencies>

✅ Step 2: Create a WebClient Configuration (Singleton Bean)
Spring Boot allows you to configure WebClient as a bean for reusability.

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com") // Set base URL
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
✅ Step 3: Create a Service to Call External API Using WebClient
This service will fetch data from an external REST API.

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {
    private final WebClient webClient;

    public ApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Fetch Data using GET request
    public Mono<String> getPostById(int id) {
        return webClient.get()
                .uri("/posts/{id}", id) // Dynamic Path Variable
                .retrieve()
                .bodyToMono(String.class);
    }

    // Create Data using POST request
    public Mono<String> createPost(String title, String body, int userId) {
        return webClient.post()
                .uri("/posts")
                .bodyValue(new PostRequest(title, body, userId))
                .retrieve()
                .bodyToMono(String.class);
    }
}


import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientExample {
    public static void main(String[] args) {
        // Create WebClient instance
        WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");

        // Make API call (GET request)
        Mono<String> response = webClient.get()
                .uri("/posts/1")
                .retrieve()
                .bodyToMono(String.class);

        // Print response
        response.subscribe(System.out::println);
    }
}
📝 Explanation:
1️⃣ Creates a WebClient instance pointing to jsonplaceholder.typicode.com.
2️⃣ Sends a GET request to /posts/1.
3️⃣ Retrieves response as Mono<String> (asynchronous).
4️⃣ subscribe() processes the response when it's available.

---

## What are the different ways to define a custom query in Spring Data JPA

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

---

## What happens when we override parent method and throwing in different Exception in checked and unchecked

For Checked Exception - IO , fileNotFound , SQL

Senario-> if parent have some exception like IO exception here if we override in child and child throwing FileNotFound
           does program compile - YES
          reason-becaz parent have IO exception and child have filenotfound . In IO exception is parent it means filenotfound
          extending Io exception  that by it is not showing error becaz fileNotFound is child of IO.
          so it will complile . beacause parent- IO  child- fileNotFound

Example-  class Parenttt{

     /*  if parent have some exception like IO exception here if we override in child and child throwing FileNotFound
       does program compile- yes
       becaz parent have IO exception and child have filenotfound . IO exception is parent it means filenotfound extend
       IO exception so it will complile but vice verssa is not impossile
       parent-> FileNotFound  , child-> IOException compile->NO     */


              public void start() throws IOException{
                  System.out.println(" Hi i am parent ");
              }
          }
          class childd extends Parenttt{
              @Override
              public void start() throws FileNotFoundException {
                  System.out.println(" Hi i am child ");
              }
          }
          public class InheritanceDemo {
              public static void main(String[] args) throws Exception {
                  childd c=new childd();
                  c.start();   // output   Hi i am child
              }

-----------------------------------------------------------------------------------------------------------
Senario-> if parent have some exception like FileNotFound exception here if we override in child and child throwing IO
           does program compile - NO
        reason- becz here in parent class FileNotFound and child class IO . compile time error fileNotFound not extend IO
           check above example and figure out
           parent-> FileNotFound  , child-> IOException compile->NO

-----------------------------------------------------------------------------------------------------------------
Senario-> if parent have some exception like IO exception here if we override in child and child throwing SQL
                     does program compile- NO
         if parent have some exception like SQL exception here if we override in child and child throwing IO
                    does program compile- NO
           reason- Both are checked exeption and standalone exception not extending anyone . But fileNotFound extending IO
                   that by it compile
    Example-import java.io.*;
            import java.sql.*;

            class Parent {
                public void read() throws IOException {
                    System.out.println("Parent read");
                }
            }

            class Child extends Parent {
                @Override
                public void read() throws SQLException { // ❌ Compile-time error
                    System.out.println("Child read");
                }
            }


---------------------------------------------------------------------------------------------------------
Senario-> If the parent method doesn’t throw any checked exception, and the child method tries to throw a checked exception,
           it will cause a compiler error.
          does program compile- NO
          class Parent {
              public void readFile() {
                  System.out.println("Parent");
              }
          }

          class Child extends Parent {
              @Override
              public void readFile() throws IOException { // ❌ compiler error
                  System.out.println("Child");
              }
          }
------------------------------------------------------------------------------------------------------
Senario-> parent-IO    , child - Exception , complile- NO
class Parent {
    public void readFile() throws IOException {}
}

class Child extends Parent {
    @Override
    public void readFile() throws Exception {} // ❌ Not allowed: Exception is broader than IOException
}

----------------------------------------------------------------------------------------------------------------
For UnChecked Exception - NullPointerException,ArrayIndexOutOfBoundsException,ArithmeticException
 because unchecked exceptions do not affect method signature compatibility.
Senario->| Case                                                                    | Allowed? | Explanation |
         | ----------------------------------------------------------------------- | -------- | ----------- |
         | Parent throws RuntimeException, child throws same                       | ✅        | Allowed     |
         | Parent throws no exception, child throws RuntimeException               | ✅        | Allowed     |
         | Parent throws RuntimeException, child throws different RuntimeException | ✅        | Allowed     |


✅ Example 1: Parent throws RuntimeException, Child throws NullPointerException (a subclass)
class Parent {
    public void process() throws RuntimeException {
        System.out.println("Parent process");
    }
}

class Child extends Parent {
    @Override
    public void process() throws NullPointerException {
        System.out.println("Child process");
    }
}
✅ Compiles and runs fine.

✅ Example 2: Parent throws nothing, Child throws ArithmeticException

class Parent {
    public void calculate() {
        System.out.println("Parent calculate");
    }
}

class Child extends Parent {
    @Override
    public void calculate() throws ArithmeticException {
        System.out.println("Child calculate");
    }
}
✅ Also compiles and runs fine, because unchecked exceptions do not affect method signature compatibility.

---

## What is a Future in Java

Future is an interface in java.util.concurrent that represents the result of an asynchronous computation.
When you submit a task using an ExecutorService, it returns a Future object that acts as a placeholder for the result,
which may not be ready immediately.

✅ Real-World Analogy
Think of ordering food at a restaurant:
You place the order (submit a task).
You get a token (Future object).
You continue doing other things (non-blocking).
Later, you check if the food is ready (isDone()).
When it is, you collect your food (get()).

🔧 How It Works Internally
1. Submit a task
You submit a Callable (or Runnable) to an ExecutorService.
Example:

java
Copy
Edit
ExecutorService executor = Executors.newSingleThreadExecutor();
Future<Integer> future = executor.submit(() -> {
    Thread.sleep(2000);
    return 42;
});
2. submit() returns a Future object
The thread pool schedules the task.

The task runs in a separate thread.

3. Use future.get() to block and retrieve result
java
Copy
Edit
System.out.println("Waiting for result...");
Integer result = future.get(); // Blocks until result is ready
System.out.println("Result: " + result);
4. Optional methods:
java
Copy
Edit
future.isDone();      // true if task is completed
future.isCancelled(); // true if cancelled
future.cancel(true);  // attempts to cancel the task
📦 Under the Hood
Internally:

The FutureTask class implements both Runnable and Future.

When you submit a Callable:

The Executor wraps it in a FutureTask.

FutureTask manages the lifecycle: running, completed, cancelled.

When the thread completes the task, FutureTask stores the result (or exception).

get() blocks if the task is still running; otherwise, it returns immediately.

💬 Interview-Safe Summary Answer:
"Future is a way to represent the result of an asynchronous operation. When I submit a task using ExecutorService,
 I get a Future object, which acts like a placeholder. I can call get() to retrieve the result, which blocks if the
 task isn’t done. This allows me to write non-blocking and efficient multi-threaded code. Under the hood,
 Java wraps tasks in a FutureTask that handles state and result management safely across threads."

🧪 Code Example: Future in Action
java
Copy
Edit
import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<String> future = executor.submit(() -> {
            Thread.sleep(3000); // simulate long task
            return "Task Completed!";
        });

        System.out.println("Main thread doing other work...");

        if (!future.isDone()) {
            System.out.println("Waiting for result...");
        }

        String result = future.get(); // blocks until done
        System.out.println("Result: " + result);

        executor.shutdown();
    }
}
🔄 Bonus: Difference Between Runnable and Callable?
Feature	Runnable	Callable
Returns value?	❌ No	✅ Yes
Can throw exception?	❌ No	✅ Yes (throws Exception)
Used with Future?	❌ No	✅ Yes (always)

---

## what is caching and how to implement

✅ What is Cache in Spring Boot?
Cache in Spring Boot is a mechanism to store method results temporarily so that repeated calls with the same
parameters do not invoke the actual method again. This improves performance and reduces database or service load.

Spring Boot provides caching abstraction over various caching providers like:
EhCache
Caffeine
Redis
Simple in-memory cache (ConcurrentHashMap-based)

We’ll use a dummy product service where:
@Cacheable caches product fetch.
@CachePut updates product and cache.
@CacheEvict removes from cache.
@Caching combines multiple behaviors.

@Service
public class ProductService {

    private Map<Integer, Product> fakeDB = new HashMap<>();

    // Initialize dummy DB
    public ProductService() {
        fakeDB.put(1, new Product(1, "Laptop"));
        fakeDB.put(2, new Product(2, "Phone"));
    }

    // ✅ @Cacheable: Cache the method result
    @Cacheable(value = "products", key = "#id")
    public Product getProductById(int id) {
        System.out.println("Fetching from DB for ID: " + id);
        return fakeDB.get(id);
    }

    // ✅ @CachePut: Always update cache and DB
    @CachePut(value = "products", key = "#product.id")
    public Product updateProduct(Product product) {
        System.out.println("Updating DB and Cache for ID: " + product.getId());
        fakeDB.put(product.getId(), product);
        return product;
    }

    // ✅ @CacheEvict: Remove entry from cache
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(int id) {
        System.out.println("Deleting product from DB and cache");
        fakeDB.remove(id);
    }

    // ✅ @Caching: Combine multiple annotations
    @Caching(
        put = { @CachePut(value = "products", key = "#product.id") },
        evict = { @CacheEvict(value = "products", key = "'allProducts'") }
    )
    public Product createProduct(Product product) {
        System.out.println("Creating new product and updating caches");
        fakeDB.put(product.getId(), product);
        return product;
    }
}

---

## what is difference betwwen hibernate and jdbc

| Feature     | JDBC (Java Database Connectivity)                        | Hibernate (ORM Framework)                     |
| ----------- | -------------------------------------------------------- | --------------------------------------------- |
| What is it? | Low-level API for interacting directly with the database | High-level ORM framework built on top of JDBC |
| Purpose     | Allows sending SQL queries manually from Java            | Maps Java objects to database tables (ORM)    |

| Feature          | JDBC                                          | Hibernate                                 |
| ---------------- | --------------------------------------------- | ----------------------------------------- |
| SQL Handling     | You write SQL manually                        | Generates SQL internally                  |
| Object Mapping   | You handle it manually                        | Automatically maps Java objects to tables |
| Boilerplate Code | More (connection, statements, resultSet, etc) | Less (uses Session, HQL, etc.)            |

| Feature       | JDBC                          | Hibernate                   |
| ------------- | ----------------------------- | --------------------------- |
| Insert/Update | Manual SQL                    | Just save/update the object |
| Delete        | Manual SQL                    | session.delete(entity)      |
| Read          | Manual mapping from ResultSet | session.get() / HQL queries |

JDBC can be faster in simple cases (no overhead).

Hibernate optimizes performance in large apps using caching, lazy loading, and batching.

JDBC gives me fine-grained control but requires more boilerplate and manual mapping. Hibernate is an
ORM that abstracts this and handles persistence automatically. I prefer Hibernate for larger applications
where maintainability and productivity matter, but I understand JDBC for situations requiring custom
SQL control.

---

## What is Dynamic Scalability in Microservices

Dynamic Scalability means your microservices-based system can automatically scale up or down
(add or remove instances of a service) based on real-time demand or load — without manual intervention.
🛠 Example:
If your user-service usually runs with 2 instances but suddenly sees a traffic spike:
The system automatically adds more instances (scale out).
When the traffic drops, it removes unused instances (scale in).

| Type                   | Description                                           |
| ---------------------- | ----------------------------------------------------- |
| **Vertical Scaling**   | Add more CPU/RAM to the same server (limited)         |
| **Horizontal Scaling** | Add more instances/machines (ideal for microservices) |

⚙️ How Dynamic Scaling Works
Metrics (CPU, memory, request count) are monitored
Autoscaling (like Kubernetes HPA or AWS Auto Scaling) watches these metrics
When thresholds are hit, it:
 Launches new instances (scale out)
 Terminates extra instances (scale in)

🎯 Why is Dynamic Scalability Important?
Benefit	Why It Matters
✅ Handles Traffic Spikes	Prevents service crashes during high demand
✅ Saves Costs	Scales down when idle = lower resource usage
✅ Improves Reliability	Avoids request failures under load
✅ Enables Microservice Independence	Each service can scale based on its own usage
✅ Supports Cloud-Native Design	Fully leverages container orchestration (Kubernetes, ECS, etc.)

🧠 Real-World Use Case
Imagine you have an e-commerce app:

During Black Friday, traffic increases 10x.

Your product-service and payment-service auto-scale to 10–20 instances to handle the load.

After the sale ends, they shrink back to 2–3 instances — saving cost.

🔧 Tools That Support Dynamic Scaling
Platform	Scaling Tool/Service
Kubernetes	Horizontal Pod Autoscaler (HPA)
AWS	Auto Scaling Groups (ASG), ECS Fargate
Azure	VM Scale Sets, AKS Autoscaler
Spring Cloud	Works with cloud platforms + actuator metrics

---

## What Is Interceptor and filter and how it is different

One Line Dif-Intercept HTTP request and response before reaching to controller
Interceptor-Allows you to intercept incoming requests or outgoing responses before or after they are handled by
a controller.
  You can use the Interceptor in Spring Boot to perform operations under the following situations −
               1.  Before sending the request to the controller
               2.  Before sending the response to the client
For example, you can use an interceptor to add the request header before sending the request to the controller and
add the response header before sending the response to the client.

step1- interceptor mai work karne ke liye in controoler class mai We need to implement HandlerInterceptor interface.
step2- It consists of 3 method and thses 3 method we need to overrride
        1.preHandle() method − Used before sending the the request to the controler. It return boolean true and
        fasle
        2.postHandle() method-Used before sending the response to the client.
        3.afterCompletion() method-Used After the completing the request and response
   example- @Component
            public class ProductServiceInterceptor implements HandlerInterceptor {
               @Override
               public boolean preHandle(
                  HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

                  return true;
               }
               @Override
               public void postHandle(
                  HttpServletRequest request, HttpServletResponse response, Object handler,
                  ModelAndView modelAndView) throws Exception {}

               @Override
               public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                  Object handler, Exception exception) throws Exception {}
            }

step3-You will have to register this Interceptor with InterceptorRegistry by using WebMvcConfigurer as shown below −
example-  @Component
          public class ProductServiceInterceptorAppConfig implements WebMvcConfigurer {
             @Autowired
             ProductServiceInterceptor productServiceInterceptor;

             @Override
             public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(productServiceInterceptor);
             }
          }

step 4- Full example i will show where at last i will explain the code

1. Create an Interceptor

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("Before API: " + request.getRequestURI());
        return true; // Continue to controller
    }
}

2. Register It
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor())
                .addPathPatterns("/api/**"); // Intercept only /api/* endpoints
    }
}

3. Example Controller
@RestController
@RequestMapping("/api")
public class MyController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }
}
🔹 What Happens When You Call /api/hello
Interceptor preHandle() runs → logs "Before API: /api/hello"
Controller method executes → returns "Hello World!"
Interceptor postHandle() and afterCompletion() (if implemented) run
Response sent to the client

Senario ----if we not add .addPathPatterns("/api/**");  api call only with /hello so can we get output
for preHandle() method


You created an interceptor, but did not use:

.addPathPatterns("/api/**")
And your API endpoint is just:

@GetMapping("/hello")
public String sayHello() {
    return "Hello!";
}
✅ What happens?
If you register your interceptor like this:

registry.addInterceptor(new LoggingInterceptor());
Then the interceptor will be applied to all endpoints by default — including /hello.

🔸 So yes: preHandle() will run for /hello.
🔹 Now, if you do this:

registry.addInterceptor(new LoggingInterceptor())
        .addPathPatterns("/api/**");  // Only apply to paths starting with /api
And you call /hello, then:

preHandle() will NOT run ❌

Because /hello does not match the pattern /api/**

-----------------------------------------------------------------------
One line Def-It intercept the HTTP request and response before sending the servlet( Dispacher servlet)
Filter- A Filter is part of the Servlet API and works at the Servlet container level. It is used to perform
filtering tasks on incoming requests and outgoing responses before they reach the servlet (including Spring's
DispatcherServlet).
   Common uses:
       Logging requests/responses
       Authentication/authorization
       Compression (e.g., GZIP)
       CORS handling
       Character encoding
       Example-->
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<MyFilter> loggingFilter(){
        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/api/*"); // Apply to specific URL patterns
        return registrationBean;
    }
}
example
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.boot.web.servlet.FilterRegistrationBean;

 @Configuration
 public class FilterConfig {
     @Bean
     public FilterRegistrationBean<MyFilter> loggingFilter(){
         FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();
         registrationBean.setFilter(new MyFilter());
         registrationBean.addUrlPatterns("/api/*"); // Apply to specific URL patterns
         return registrationBean;
     }
 }



------------------------------------------------------------------------------------
Difference And when to use -
filter- http request before servlet(Dispacther servlet)
interceptor- http request before controller but after the servlet
Flow diagraeam-
request->filter->dispacher servlet->interceptor->controller
When to use filter- when we are putting logic which is common for all the servlet like Spring Security - It use filter
                    because it applied all http request and response for all the sevlet it is gerneric code

When to use Interceptor- When we are putting the logic to 1(Specfic) sevlet,If anything specfic to application or
                        particular one servlet then we use interceptor . and also specfic to Rest Api

---

## What is Property Source Annotation in Spring Boot  Use of Property Source Annotation

✅ What is @PropertySource?
@PropertySource tells Spring to load a specific .properties file from the classpath or file system so that you can
access those properties via:

@Value("${...}")

Or through the Environment object

✅ Syntax

@PropertySource("classpath:myconfig.properties")
@Configuration
public class AppConfig {
}
✅ Use Case
While Spring Boot uses application.properties or application.yml by default, you may have custom property files like:

db.properties
aws.properties
payment-config.properties

You use @PropertySource to explicitly load those files.

✅ Example
🔹 Step 1: Create appconfig.properties
properties
app.name=SpringBootDemo
app.version=1.0.0
🔹 Step 2: Use @PropertySource to Load It

@Configuration
@PropertySource("classpath:appconfig.properties")
public class AppConfig {
}
🔹 Step 3: Inject Property Using @Value

@Component
public class AppService {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String version;

    public void printAppDetails() {
        System.out.println("App: " + appName + ", Version: " + version);
    }
}
✅ Output:

App: SpringBootDemo, Version: 1.0.0
✅ Key Points
Feature	Description
Default file	application.properties or application.yml
When to use @PropertySource	To load additional or external property files
File location	Must be in the classpath (e.g., src/main/resources) or given with file: prefix
Combined with @Configuration	Often used on a class annotated with @Configuration

❗ Limitations
Does not support YAML files — only .properties.

Spring Boot already loads application.properties automatically. So @PropertySource is only needed for additional files.

✅ Alternative in Spring Boot (Recommended)
For Spring Boot, it's often better to use:


# application.yml
spring.config.import=classpath:my-extra-config.properties
Or if external:

properties

spring.config.import=file:/path/to/extra.properties
This is better than using @PropertySource if you're not writing custom @Configuration classes.

✅ Summary
@PropertySource Feature	Description
Purpose	Manually load .properties files
Use case	Additional/custom config beyond default
Works with	@Value, Environment
Limitation	No YAML support, not needed for application.properties
Location	classpath: or file: prefix

---

## what is record and how it is different with lambok

A record is a special Java class introduced in Java 14 (preview) and made stable in Java 16,
designed to hold immutable data. It's part of Project Amber to reduce boilerplate code.
public record Employee(String name, int salary) {}
This single line:
Declares a class
Adds final fields
Creates a constructor
Adds getters (name(), salary())
Adds equals(), hashCode(), and toString() automatically
| Feature                      | `record`                         | Regular Class (Java 8)             |
| ---------------------------- | -------------------------------- | ---------------------------------- |
| **Boilerplate**              | Minimal (auto-generated methods) | Requires manual code               |
| **Immutability**             | Fields are implicitly `final`    | Must explicitly use `final`        |
| **Constructor**              | Auto-generated full constructor  | Must write manually                |
| **Getters**                  | Auto: `fieldName()`              | Need to write or generate `getX()` |
| **`equals()`, `hashCode()`** | Auto-generated                   | Must write or generate             |
| **Use case**                 | Simple DTOs / data carriers      | Full-featured business logic class |
✅ When to Use record:
Use it when:
You just need to store and transfer data
No mutable fields, setters, or complex logic required
✅ Example Use Cases:
DTOs in REST APIs (Employee, Product)
Stream results (like your example)
Keys for Maps or Sets

| Feature                   | **Lombok**                                                                   | **Record**                                                   |
| ------------------------- | ---------------------------------------------------------------------------- | ------------------------------------------------------------ |
| ✅ **Purpose**             | Reduce boilerplate with annotations (`@Getter`, `@AllArgsConstructor`, etc.) | Native Java construct for **immutable** data-only classes    |
| 🚀 **Availability**       | Works in **any Java version**, including Java 8                              | Introduced in Java 14+, stable in Java 16                    |
| ⚙️ **Dependency**         | Requires external **library**                                                | Built into the **Java language itself**                      |
| 📦 **Mutability**         | Can be mutable (supports setters)                                            | Always **immutable** (`final` fields only)                   |
| 🔧 **Customization**      | Can add fields, setters, methods, annotations                                | More restrictive – can't add setters or extend other classes |
| 🛠 **IDE/Build Support**  | May require plugins, not 100% portable                                       | Fully supported by all modern IDEs (built-in)                |
| 🧪 **Use in Spring Boot** | Very common for DTOs & entities                                              | Works well for DTOs, request/response bodies (read-only)     |
✅ Advantages of record over Lombok:
No dependency required – native Java feature
Cleaner and safer for immutable data classes
Better integration with the Java compiler and reflection
More transparent (less "magic" compared to Lombok)
✅ Advantages of Lombok over record:
Can be used in Java 8 and above
Allows mutable fields, business logic, inheritance, annotations
More flexible for JPA entities, models, and general-purpose classes

---

## What is Record class and how it is helping for making hashmap key as immuatble

Record- to store immutable data
aim- to reduce bioler plate code
automatically generate hashcode , equals, constructor
----------------

how help to make immutable hashmap
✅ Problem Before Java Records (Pre-Java 14)
When you use a custom class as a key in a HashMap, you must override:

equals() – to check logical equality between keys

hashCode() – to ensure correct hashing behavior

🔴 If you don’t override them properly and the object is mutable, your keys may behave unpredictably in
the HashMap.

✅ Why Immutability is Important for HashMap Keys
HashMap uses hashCode() to place the key in the correct bucket. If the key's fields change after
 insertion, the object
 might not be found again.

🔧 Traditional Way (Manual Class)

class User {
    private final String name;
    private final int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Must override equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        // custom equality logic
    }

    @Override
    public int hashCode() {
        // custom hashcode logic
    }
}
⚠️ Tedious and error-prone!

✅ Java record Makes This Easy
🔥 Just one line:

public record User(String name, int id) {}
What happens here?
When you define a record:

All fields are final by default → immutable

Java automatically generates:

equals()

hashCode()

toString()

Constructor and accessors (name(), id())

✅ Example Using Record as a HashMap Key

import java.util.HashMap;
import java.util.Map;

public class RecordAsKeyDemo {
    public static void main(String[] args) {
        record User(String name, int id) {}

        Map<User, String> map = new HashMap<>();

        User user1 = new User("Alice", 101);
        User user2 = new User("Bob", 102);

        map.put(user1, "Developer");
        map.put(user2, "Tester");

        // Lookup works because equals() and hashCode() are correct and immutable
        System.out.println(map.get(new User("Alice", 101))); // Output: Developer
    }
}
✅ Output:

Developer
✅ Why It Works Well
Feature	Traditional Class	Java record
Immutable fields	Manual	Built-in (final)
equals() method	Manual	Auto-generated
hashCode() method	Manual	Auto-generated
Constructor	Manual	Auto-generated

✅ Summary
✅ record is perfect for keys in a HashMap because:

All fields are final (immutable)

equals() and hashCode() are correctly implemented

✅ No risk of mutating key state after insertion

✅ Less boilerplate, more safety

---

## what is scope in spring boot with example

| Scope         | Description                                    | Applies To      |
| ------------- | ---------------------------------------------- | --------------- |
| `singleton`   | Single instance per Spring container (default) | All components  |
| `prototype`   | New instance every time it's requested         | All components  |
| `request`     | One instance per HTTP request (Web only)       | Web controllers |
| `session`     | One instance per HTTP session (Web only)       | Web controllers |
| `application` | One instance per ServletContext                | Web apps        |
| `websocket`   | One instance per WebSocket session             | WebSocket apps  |
✅ Example: Singleton vs Prototype
1️⃣ Singleton (Default)
@Component
public class MyService {
    public MyService() {
        System.out.println("MyService created");
    }
}

@RestController
public class MyController {
    @Autowired
    private MyService myService1;
    @Autowired
    private MyService myService2;
    @GetMapping("/test")
    public String test() {
        return "Are both same? " + (myService1 == myService2);  // true
    }
}
✅ Output:

MyService created
Are both same? true

2️⃣ Prototype
@Component
@Scope("prototype")
public class MyPrototypeService {
    public MyPrototypeService() {
        System.out.println("MyPrototypeService created");
    }
}
@RestController
public class MyController {
    @Autowired
    private ApplicationContext context;
    @GetMapping("/proto")
    public String test() {
        MyPrototypeService s1 = context.getBean(MyPrototypeService.class);
        MyPrototypeService s2 = context.getBean(MyPrototypeService.class);
        return "Are both same? " + (s1 == s2);  // false
    }
}
✅ Output:
MyPrototypeService created
MyPrototypeService created
Are both same? false


In Spring Boot, bean scope controls the lifecycle and number of bean instances. By default, all beans are singleton,
 meaning only one instance is created and shared. If we want a new instance every time, we use prototype scope.
  For web apps, we can use request or session scope. Scopes help in managing state correctly, especially in
  multi-threaded or web environments.


  ------------------------------------------------------------------------------
  Custom scope -

  ✅ How to Define a Custom Scope in Spring
  Step-by-step:
  🔹 Step 1: Implement org.springframework.beans.factory.config.Scope
  java
  Copy
  Edit
  import org.springframework.beans.factory.ObjectFactory;
  import org.springframework.beans.factory.config.Scope;

  import java.util.HashMap;
  import java.util.Map;

  public class MyCustomScope implements Scope {

      private Map<String, Object> scopedObjects = new HashMap<>();

      @Override
      public Object get(String name, ObjectFactory<?> objectFactory) {
          return scopedObjects.computeIfAbsent(name, k -> objectFactory.getObject());
      }

      @Override
      public Object remove(String name) {
          return scopedObjects.remove(name);
      }

      @Override
      public void registerDestructionCallback(String name, Runnable callback) {
          // Optional: clean-up code, not needed for simple use
      }

      @Override
      public Object resolveContextualObject(String key) {
          return null;
      }

      @Override
      public String getConversationId() {
          return "myCustomScope";
      }
  }
  🔹 Step 2: Register the Scope in a Configuration Class
  java
  Copy
  Edit
  import org.springframework.beans.factory.config.CustomScopeConfigurer;
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;

  import java.util.HashMap;
  import java.util.Map;

  @Configuration
  public class ScopeConfig {

      @Bean
      public static CustomScopeConfigurer customScopeConfigurer() {
          CustomScopeConfigurer configurer = new CustomScopeConfigurer();
          Map<String, Object> scopes = new HashMap<>();
          scopes.put("myScope", new MyCustomScope());
          configurer.setScopes(scopes);
          return configurer;
      }
  }
  🔹 Step 3: Use the Custom Scope
  java
  Copy
  Edit
  import org.springframework.context.annotation.Scope;
  import org.springframework.stereotype.Component;

  @Component
  @Scope("myScope")
  public class MyScopedBean {
      public MyScopedBean() {
          System.out.println("Creating a new MyScopedBean instance");
      }
  }
  🔹 Step 4: Test It
  java
  Copy
  Edit
  @Autowired
  private ApplicationContext context;

  public void testCustomScope() {
      MyScopedBean bean1 = context.getBean(MyScopedBean.class);
      MyScopedBean bean2 = context.getBean(MyScopedBean.class);
      System.out.println(bean1 == bean2);  // true if reused, false if removed in between
  }
  💬 How to Explain in Interview:
  Spring allows defining custom scopes by implementing the Scope interface. This gives full control over bean
  lifecycle and reuse. I can use this to build scopes like per-tenant or per-thread. After registering the custom scope
  with CustomScopeConfigurer, I annotate my bean with @Scope("myScope"). It’s useful when default scopes don’t meet
  app-specific requirements.

---

## what is serialization and deserialization in java

Serialization in Java is the process of converting an object into a byte stream. This byte stream can
be stored in a file, database, or transmitted over a network. The primary purpose of serialization is
to save the state of an object so that it can be recreated later.
Deserialization, on the other hand, is the reverse process of converting a byte stream back into a Java
object. This allows the object to be restored to its original state, enabling its use in the application.

Key Points:
Purpose:
Serialization enables the persistence of an object's state, while deserialization reconstructs the
object from its serialized form.
Use Cases:
Serialization is used for saving objects to files, sending objects over networks, and storing objects
in databases.
Serialization Interface:
To serialize an object, the class must implement the java.io.Serializable interface.
serialVersionUID:
It's good practice to define a serialVersionUID in classes that implement Serializable. This ensures
compatibility when deserializing objects if the class structure changes.
Transient Fields:
Fields marked as transient are not serialized, so they are not saved or restored during deserialization.
Constructors:
The constructor of the object is not called during deserialization.
In summary, serialization and deserialization enable the transportation and storage of Java objects while
 preserving their state and structure.

---

## What is the difference between @ExceptionHandler, @ControllerAdvice, and ResponseStatusException

1️⃣ @ExceptionHandler (Handle Exceptions in a Single Controller)
The @ExceptionHandler annotation is used to handle exceptions within a specific controller.

Example: Using @ExceptionHandler in a Controller

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
    }

    // Handle exception locally for this controller only
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
Key Features of @ExceptionHandler
✔ Handles specific exceptions in a single controller
✔ Useful for controller-specific exception handling
❌ Does not apply globally across all controllers

2️⃣ @ControllerAdvice (Global Exception Handling for All Controllers)
The @ControllerAdvice annotation is used for global exception handling across multiple controllers.

Example: Global Exception Handling with @ControllerAdvice

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles EmployeeNotFoundException globally
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Handles validation errors globallyd
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // Catch all unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }
}
Key Features of @ControllerAdvice
✔ Global exception handling for multiple controllers
✔ Centralized management of exception handling logic
✔ Supports multiple exception types
❌ Cannot handle exceptions locally (applies to all controllers)

---

## what is the difference between creating bean with @component and with @configuration

Main diff- if we create bean inside configuration annotation . singleton bean created and we will get bean
             in applicationContext but inside
           component everytime new instance will get and bean created when we run the application

@Component
Marks a regular Spring-managed bean.
Spring will automatically detect and register it if component scanning is enabled (@ComponentScan).
You can use it on any class (service, repository, etc.)
Beans created with @Component do not include any special behavior, like the ability to define other
beans inside them.

2.@Configuration
Specialized form of @Component.
Indicates that the class contains @Bean definitions.
Ensures that the beans defined in this class are created only once (Singleton) by enabling CGLIB proxying.
Supports bean factory methods, which can return other Spring beans.


Key Difference:
The primary difference lies in the behavior of bean definitions within the class:
A class annotated with @Configuration ensures that methods annotated with @Bean are executed
only once per container.
If you use @Component, the @Bean methods inside it might create new instances every time they are called.

🧠 Why?
@Configuration classes are subclassed at runtime (CGLIB proxy).
Spring intercepts calls to @Bean methods and ensures it returns the same singleton from the application context.
@Component classes are not proxied this way, so calling a @Bean method inside it behaves like
 a regular Java method: it creates a new object each time.

example-
📦 Example Project Structure
Common Bean Classes:
public class MyService {
    public MyService() {
        System.out.println("MyService constructor called");
    }
}

public class AppController {
    private final MyService myService;
    public AppController(MyService myService) {
        this.myService = myService;
        System.out.println("AppController constructor called with MyService: " + myService.hashCode());
    }
}
1️⃣ @Configuration Example:
@Configuration
public class ConfigExample {

    @Bean
    public MyService myService() {
        System.out.println("Calling myService()");
        return new MyService();
    }
    @Bean
    public AppController appController() {
        System.out.println("Calling appController()");
        // Spring will proxy this call and return the already created singleton
        return new AppController(myService());
    }
}
Output:
Calling myService()
MyService constructor called
Calling appController()
AppController constructor called with MyService: 12345678
✔️ Only one MyService object is created. Spring intercepts the internal call to myService().

2️⃣ @Component Example:
@Component
public class ComponentExample {
    @Bean
    public MyService myService() {
        System.out.println("Calling myService()");
        return new MyService();
    }
    @Bean
    public AppController appController() {
        System.out.println("Calling appController()");
        // Not proxied, will create a new instance
        return new AppController(myService());
    }
}
Output:
Calling myService()
MyService constructor called
Calling appController()
MyService constructor called
AppController constructor called with MyService: 87654321
❌ Two different MyService instances created — one for myService() and another for appController()
because method calls inside the @Component class aren't intercepted.

🔁 Visual Summary
@Configuration
 └── myService() → Singleton (Spring intercepts call)
 └── appController() → Uses the same singleton MyService

@Component
 └── myService() → Singleton only if called by Spring
 └── appController() → Calls myService() as regular Java method → NEW instance

---

## What is the difference between rest and soap

✅ What is SOAP (Simple Object Access Protocol)?
A protocol for accessing web services.
Uses XML format strictly.
Operates over HTTP, SMTP, etc.
Strict standards and built-in error handling (WS-Security, WS-ReliableMessaging, etc.)

✅ What is REST (Representational State Transfer)?
An architecture style, not a protocol.
Communicates via HTTP verbs (GET, POST, PUT, DELETE).
Supports multiple data formats (JSON, XML, YAML), but mostly uses JSON.
Stateless, lightweight, faster

| Feature         | SOAP                           | REST                               |
| --------------- | ------------------------------ | ---------------------------------- |
| **Type**        | Protocol                       | Architectural Style                |
| **Data format** | XML only                       | JSON (preferred), XML, others      |
| **Speed**       | Slower due to XML              | Faster, lightweight                |
| **Transport**   | HTTP, SMTP, TCP                | HTTP only                          |
| **Security**    | WS-Security                    | HTTPS + custom auth                |
| **State**       | Can be stateful                | Stateless                          |
| **Complexity**  | More complex (WSDL, envelopes) | Simple and easy to integrate       |
| **Best for**    | Enterprise-level transactions  | Mobile, public APIs, microservices |


🎯 When to Use Which?
✅ Use SOAP when:
You need enterprise-grade security (banking, financial).
You need ACID-compliant transactions (atomicity).
The client expects strict contract (WSDL).

✅ Use REST when:
You're building scalable, fast web or mobile apps.
You want flexible data formats (mostly JSON).
You're developing microservices or public APIs.

🧠 How to Explain in Interview
"SOAP is a protocol with strict rules and XML-based messaging, suitable for enterprise applications where high security
 and standardized communication are crucial. REST is an architectural style that's simpler and faster, ideal for
 lightweight, stateless APIs — especially in microservices and mobile apps.

For example, if I'm building a weather app for mobile, I'd prefer REST due to its lightweight nature and JSON support.
 But if I'm integrating with a payment gateway that requires security standards, I might go for SOAP."

---

## What is the difference between save() and saveAll() in Spring Data JPA

Both save() and saveAll() are methods provided by Spring Data JPA's JpaRepository to persist data in a
database, but they have key differences in how they handle transactions, performance, and usage.

1️⃣ save() – Saves a Single Entity
The save() method is used to persist or update a single entity in the database.

Method Signature

<S extends T> S save(S entity);
Saves a single entity (entity).

If the entity has an ID, it performs an update.

If the entity does not have an ID, it performs an insert.

Example Usage

Employee employee = new Employee(null, "John Doe", "john@example.com", "IT");
employeeRepository.save(employee);
Behavior
If employee.id is null → INSERT new record.

If employee.id is not null and exists → UPDATE existing record.

If employee.id is not null but doesn't exist → Throws EntityNotFoundException in some cases.

2️⃣ saveAll() – Saves Multiple Entities in Bulk
The saveAll() method is used to persist or update multiple entities in a single call.

Method Signature

<S extends T> List<S> saveAll(Iterable<S> entities);
Accepts a list of entities.

Performs batch processing, making it more efficient than multiple save() calls.

Still, inserts or updates entities based on their IDs.

Example Usage

List<Employee> employees = List.of(
    new Employee(null, "Alice", "alice@example.com", "HR"),
    new Employee(null, "Bob", "bob@example.com", "Finance")
);
employeeRepository.saveAll(employees);
3️⃣ Performance Differences
Feature	save() (Single Entity)	saveAll() (Multiple Entities)
Operation	Inserts/Updates 1 entity	Inserts/Updates multiple entities
Batch Processing	❌ No batch processing	✅ Uses batch processing (if enabled)
SQL Calls	Multiple calls for multiple entities	Fewer calls due to batching
Performance	Slower for large data sets	Faster for bulk operations

---

## When to use GET and POST

Use GET if:

You're just retrieving data.
The parameters (filters, search terms) are simple and not too long.
GET is NOT secure because it exposing data in url
The request can be expressed cleanly in the URL.
GET /products/search?category=books&sort=price&minPrice=100&maxPrice=500
GET-- idempotent   -- means if we hit get url we will get same response
Caching- we can achiving caching here

------------------------------------------------------------------------
Use POST if:

You have complex search criteria (e.g. nested filters, large payload).
POST is more secure because not exposing data in url
There are many fields — too long for a URL (browsers and servers typically limit URLs to ~2000 characters).
You want to send a JSON body.
You’re doing a search with sensitive data (GET exposes data in URL).
POST /products/search
Content-Type: application/json
{
  "categories": ["books", "electronics"],
  "priceRange": { "min": 100, "max": 1000 },
  "sortBy": "price",
  "inStock": true,
  "filters": {
    "author": "John Doe",
    "ratingAbove": 4.0
  }
}
POST-- NOT idempotent   -- means if we hit get url we will get same response
Caching- we can not achiving caching here

---

## When to use mysql and nosql in microservics

mysql-
1-> when we want stractural data and complex logic tthen we will go with mysql,complex relationships
     between entities
2->It follow ACID properties so that if we want consistency in database we use Mysql
3->it support complex join

nosql-
1->Use for unstractural data like list of product all product all different different properties
   then we use nosql
2->Horizontal scaling if new Product come so we can add
3->suitable for handling large volumes of data and scaling out across distributed systems.


In summary, choose MySQL for strong consistency and structured data with complex relationships.
Opt for MongoDB if you need schema flexibility, horizontal scalability, and agile development capabilities
in your microservices architecture.

---

## Why Banking and Financial Systems Prefer SOAP Over REST even when REST can be secured with OAuth and JWT

"Even though REST supports OAuth and JWT, banking and financial systems often require more advanced security features
like WS-Security, reliable messaging, and formal contracts (WSDL) for integration between large systems. SOAP provides
 built-in support for these via standards like WS-Security and WS-ReliableMessaging, which makes it a better fit for
  enterprise-grade, compliant environments like banking."

  🔐 1. Security Compliance (WS-Security)
  SOAP supports WS-Security, which includes:
  Message-level encryption
  Signature-based authentication
  Secure transmission even if the message is forwarded
  👉 REST relies mainly on HTTPS, JWT, and OAuth, which are not sufficient for deep compliance like PCI-DSS (used in banking).

  🔁 2. Reliable Messaging (WS-ReliableMessaging)
  SOAP has built-in support for:
  Retry logic
  Message acknowledgment
  Ordered delivery
  👉 REST has no built-in retry or message order guarantee.

| Feature                   | SOAP (Why Banking Chooses It)       | REST                        |
| ------------------------- | ----------------------------------- | --------------------------- |
| Security Standard         | WS-Security (Message-level)         | OAuth/JWT (Transport-level) |
| Reliable Messaging        | WS-ReliableMessaging (built-in)     | Not built-in                |
| Contract Definition       | WSDL (strongly typed)               | OpenAPI/Swagger (optional)  |
| ACID Transactions Support | WS-AtomicTransaction                | Manual implementation       |
| Interoperability          | Works with legacy systems (JMS, MQ) | REST is better for new APIs |

"For a public-facing banking app, we can use REST + JWT for performance and ease. But for internal secure systems
 like interbank transfers, SOAP is better because of WS-Security, reliable messaging, and formal contracts (WSDL)
 that ensure compliance and auditability."

---

## Why Constructor injection is prefeerd in spring boot

why- make immuatble,compile time safty if we missing any parameter give nullPointerException
✅ 1. Immutability
Constructor injection ensures dependencies are final and set once at object creation.

This makes the bean immutable, which is safer and easier to reason about.

@Service
public class MyService {
    private final UserRepository repo;

    public MyService(UserRepository repo) {
        this.repo = repo;
    }
}
🔒 repo can't be changed after creation — that’s a good thing!

✅ 2. Mandatory Dependency Enforcement
With constructor injection, Spring fails fast if a required dependency is missing.

Unlike field or setter injection, it guarantees that the object can't be created without its dependencies.

🟢 This avoids NullPointerException at runtime due to uninitialized fields.
| Aspect                   | Constructor Injection ✅ | Field Injection 🚫 |
| ------------------------ | ----------------------- | ------------------ |
| Immutability             | ✅ Yes                   | ❌ No               |
| Testability (with mocks) | ✅ Easy                  | ❌ Hard             |
| Visibility of dependency | ✅ Clear in constructor  | ❌ Hidden           |
| Compile-time safety      | ✅ Yes                   | ❌ No               |
| Reflection overhead      | ❌ No                    | ✅ Yes              |

---

## YML_AND_Application

1. Syntax and Readability
.properties

Simple key-value pairs.
Best for straightforward configurations.
Can become hard to read when the configuration is hierarchical or large.
Example:
properties
Copy code
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
YAML (.yml)

Uses a structured format with indentation.
Easier to read and maintain for hierarchical or nested configurations.
Example:
yaml
Copy code
server:
  port: 8080
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: root
    password: password


Choosing Between .properties and .yml
Use .properties if:

You prefer simplicity or have very basic configurations.
You're working in a legacy system that uses .properties.
Use YAML if:

You need to represent complex or hierarchical configurations.
You want better readability and maintainability for large configurations

---

