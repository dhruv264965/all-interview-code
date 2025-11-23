package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SeparateMinorAndMajorInList {
    static class User{
        int id;
        String name;
//Str="abcdea"; output=abcde
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        int age;

        public User(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
    public static void main(String[] args) {
        List<User>users= Arrays.asList(
                new User(1,"dhruv",15),
                new User(2,"raj",21),
                new User(3,"amit",17),
                new User(4,"shukla",65)
        );
        Map<Boolean, List<User>> collect = users.stream().collect(Collectors.partitioningBy((user -> user.getAge() > 18)));
        System.out.println(collect);
    }
}
