# @Value and @ConfigurationProperties

```text
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
```
