package Collection.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List<Integer> a1= new ArrayList<>();
        a1.add(1);
        a1.add(2);
        System.out.println(a1);
        List a2=a1;   // Raw type assignment( When we use raw type(List a2) we lose type safety ->can insert any object
        System.out.println(a2);
        a2.add("String");  // so we can add the element
        List<String>a3=a2; //
        System.out.println(a3);
        //a3.add(3);     // compile time error incompatible type
    }
}
