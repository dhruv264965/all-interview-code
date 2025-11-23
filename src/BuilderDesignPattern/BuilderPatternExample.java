package BuilderDesignPattern;

public class BuilderPatternExample {
    public static void main(String[] args) {
        // Using the builder to create User objects
        User user1 = new User.UserBuilder()
                .setName("John Doe")
                .setAge(30)
                .setEmail("john@example.com")
                .setAddress("123 Street")
                .build();

        User user2 = new User.UserBuilder()
                .setName("Alice")
                .setEmail("alice@example.com")  // Age & Address are optional
                .build();

        System.out.println("User 1: " + user1.getName() + ", " + user1.getAge());
        System.out.println("User 2: " + user2.getName() + ", " + user2.getEmail());
    }
}
