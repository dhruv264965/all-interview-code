package StreamApi;

import java.util.Optional;

public class LengthOfNullableString {
    public static void main(String[] args) {
        String name=null;
        String result = Optional.ofNullable(name)
                .map(val -> val.length() + " " + val)
                .orElse("Enter value");
        System.out.println(result);
    }
}
