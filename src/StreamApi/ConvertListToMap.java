package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertListToMap {
  static   class User{
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }

      public User(String username, String password) {
          this.username = username;
          this.password = password;
      }
  }
    public static void main(String[] args) {
        List<User>list= Arrays.asList(
                new User("1","dhruv"),
                new User("2","Shukla"),
                new User("3","raj"),
                new User("3","krinsha")
        );
        Map<String, String> collect = list.stream()
                .collect(Collectors.toMap(User::getUsername,
                                         User::getPassword,
                        (existing,duplicate)->duplicate
                                      ));
        System.out.println(collect);
        List<Integer> numbers = Arrays.asList(1, 1, 2, 3, 3, 4, 5, 5, 6, 6);
        Map<Integer, Integer> ListToMapCon = numbers.stream()
                .collect(Collectors.toMap(
                        abc->abc,
                         abc->abc,
                        (existing, duplicate) -> duplicate
                ));
        System.out.println(ListToMapCon);

        List<String> words = Arrays.asList("dhruv","shukla","sachin","ankit");
        Map<String, Integer> collect1 = words.stream().collect(Collectors.toMap(
                word -> word,
                word -> word.length()
        ));
        System.out.println(collect1);
    }
}
