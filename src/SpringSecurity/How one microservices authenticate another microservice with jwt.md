# How one microservices authenticate another microservice with jwt

```text
🛠️ Steps to Authenticate Using JWT
1️⃣ Generate JWT Token
A microservice (Client Service) generates a JWT token (or gets it from an authentication provider).
The token contains claims (user details, roles, expiration, etc.).
2️⃣ Pass JWT in API Requests
The Client Service includes the JWT token in the Authorization header (Bearer <token>) when calling the Resource Service.
3️⃣ Validate JWT in Resource Service
The Resource Service verifies the JWT using a secret key or public-private key pair.
If valid, access is granted.

2️⃣ Client Service (Caller Microservice)
This service calls the Resource Service with a JWT token.

🔹 RestTemplate with JWT Header
java
Copy
Edit
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String callResourceService() {
        String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."; // Get from Auth Service

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:8082/resource/data", String.class, entity);

        return response.getBody();
    }
}
```
