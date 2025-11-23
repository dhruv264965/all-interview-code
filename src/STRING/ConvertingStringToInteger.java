package STRING;

public class ConvertingStringToInteger {
    public static void main(String[] args) {

                String str = "456";

                int num1 = Integer.parseInt(str);
                Integer num2 = Integer.valueOf(str);

                System.out.println(num1); // 456
                System.out.println(num2); // 456
            }



}
