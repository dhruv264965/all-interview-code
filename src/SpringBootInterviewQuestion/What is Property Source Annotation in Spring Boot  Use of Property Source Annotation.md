# What is Property Source Annotation in Spring Boot  Use of Property Source Annotation

```text
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




```
