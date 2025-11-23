package STRING;

public class ConvertingIntegerToString {
    public static void main(String[] args) {
        int num = 456;

        String str1 = String.valueOf(num);
        String str2 = Integer.toString(num);
        String str3 = num + "";

        System.out.println(str1); // "456"
        System.out.println(str2); // "456"
        System.out.println(str3); // "456"
    }
}
