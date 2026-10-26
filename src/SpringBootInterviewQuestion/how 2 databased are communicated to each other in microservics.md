# how 2 databased are communicated to each other in microservics

```text
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


```
