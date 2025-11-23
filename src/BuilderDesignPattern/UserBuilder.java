package BuilderDesignPattern;

// Immutable User class
class User {
    private final String name;
    private final int age;
    private final String email;
    private final String address;

    // Private constructor (only Builder can access it)
    private User(UserBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.address = builder.address;
    }

    // Getters (optional)
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }

    // Static Inner Builder Class
    public static class UserBuilder {
        private String name;
        private int age;
        private String email;
        private String address;

        // Builder methods
        public UserBuilder setName(String name) {
            this.name = name;
            return this;  // Return the same builder instance (method chaining)
        }

        public UserBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        // Build method to create User object
        public User build() {
            return new User(this);
        }
    }
}

