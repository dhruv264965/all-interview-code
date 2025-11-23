package StreamApi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomUser {
    private String UserName;
    private   String UserGender;
    private   int UserId;
    private  double UserSalary;
    private  String  UserDepartment;
    private  int UserAge;

    public CustomUser(String userName, String userGender, int userId, double userSalary, String userDepartment, int userAge) {
        UserName = userName;
        UserGender = userGender;
        UserId = userId;
        UserSalary = userSalary;
        UserDepartment = userDepartment;
        UserAge = userAge;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserGender() {
        return UserGender;
    }

    public void setUserGender(String userGender) {
        UserGender = userGender;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public double getUserSalary() {
        return UserSalary;
    }

    public void setUserSalary(double userSalary) {
        UserSalary = userSalary;
    }

    public String getUserDepartment() {
        return UserDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        UserDepartment = userDepartment;
    }

    public int getUserAge() {
        return UserAge;
    }

    public void setUserAge(int userAge) {
        UserAge = userAge;
    }

    @Override
    public String toString() {
        return "CustomUser{" +
                "UserName='" + UserName + '\'' +
                ", UserGender='" + UserGender + '\'' +
                ", UserId=" + UserId +
                ", UserSalary=" + UserSalary +
                ", UserDepartment='" + UserDepartment + '\'' +
                ", UserAge=" + UserAge +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomUser that = (CustomUser) o;
        return UserId == that.UserId && Double.compare(that.UserSalary, UserSalary) == 0 && UserAge == that.UserAge && Objects.equals(UserName, that.UserName) && Objects.equals(UserGender, that.UserGender) && Objects.equals(UserDepartment, that.UserDepartment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UserName, UserGender, UserId, UserSalary, UserDepartment, UserAge);
    }

    public static void main(String[] args) {
        List<CustomUser>users = Arrays.asList(
                new CustomUser("Ganesh","Male", 1,5785.0,"BCA",21),
                new CustomUser("Siya","Female", 5,9765.1,"BCA",17),
                new CustomUser("Ganu","Male", 2,6785.4,"BCA",23),
                new CustomUser("Radha","Female", 3,7785.8,"B.Tech",18),
                new CustomUser("Krishna","Male", 4,8685.0,"MCA",25),
                new CustomUser("Siya","Female", 5,9765.1,"BCA",17),
                new CustomUser("Ram","Male", 6,4385.0,"MCA",19),
                new CustomUser("Ridhika","Female", 7,9685.0,"MBA",26)
        );
        System.out.println(" Print all user");
        for(CustomUser emp:users){
            System.out.println(emp);
        }
        System.out.println("-------------Get Nu ID ----------------------");
        List<CustomUser> first = users.stream().filter(x -> x.UserId ==2).toList();
        System.out.println(first);
        List<CustomUser> firstt = users.stream().distinct().filter(x -> x.UserName =="Siya").toList();
        System.out.println(firstt);

        Map<String, List<CustomUser>> collect = users.stream().distinct().collect(Collectors.groupingBy(CustomUser::getUserDepartment));
        System.out.println(collect);
        System.out.println("Min or max age");
        Optional<CustomUser> max = users.stream().min(Comparator.comparingInt(CustomUser::getUserAge));
        System.out.println(max);
        System.out.println(" how many male and female ");
        Map<String, Long> collect1 = users.stream().distinct().collect(Collectors.groupingBy(CustomUser::getUserGender, Collectors.counting()));
        System.out.println(collect1);
        System.out.println("who is earning max or min amount ");
        Optional<CustomUser> max1 = users.stream().max(Comparator.comparingDouble(CustomUser::getUserSalary));
        System.out.println(max1);
        System.out.println("separate the detilas those age are greater and 20 and less than 20");
        Map<Boolean, List<CustomUser>> collect2 = users.stream().collect(Collectors.partitioningBy(part -> part.getUserAge() > 20));
      //  System.out.println(collect2);
        System.out.println(collect2.get(true));
        System.out.println(collect2.get(false));
        System.out.println("average salry");
        Double collect3 = users.stream().collect(Collectors.averagingDouble(CustomUser::getUserSalary));
        System.out.println(collect3);
        System.out.println(" separate user details based on average salary ");
        Map<Boolean, List<CustomUser>> collect4 = users.stream().collect(Collectors.partitioningBy(cl -> cl.UserSalary > collect3));
        System.out.println(collect4);
        System.out.println(collect4.get(true));
        System.out.println(collect4.get(false));
        System.out.println("");
        System.out.println("average Age");
        Double averageAge = users.stream().collect(Collectors.averagingInt(CustomUser::getUserAge));
        System.out.println(averageAge);
        Map<Boolean, List<CustomUser>> collect5 = users.stream().collect(Collectors.partitioningBy(c2 -> c2.UserAge > averageAge));
        System.out.println(collect5.get(true));
        System.out.println("second  highest paid employee ");
        Optional<CustomUser> secondHighest = users.stream().distinct()
                        .sorted(Comparator.comparingDouble(CustomUser::getUserSalary).reversed())
                                .limit(3).skip(1).findFirst();
        System.out.println(secondHighest);
        System.out.println("top two highest paid employee ");
        List<CustomUser> customUsers = users.stream().distinct().sorted(Comparator.comparingDouble(CustomUser::getUserSalary).reversed())
                .limit(2).toList();
        System.out.println(customUsers);

    }
}
