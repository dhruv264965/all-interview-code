# what is the difference between creating bean with @component and with @configuration

```text
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
```
