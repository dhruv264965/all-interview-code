# @ConditionalOnProperty_And_@ConditionalOnExpression

```text
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

```
