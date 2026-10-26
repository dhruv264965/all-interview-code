# how to handle bulk data using kafka

```text
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


```
