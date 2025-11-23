package Collection.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class Set {
    public static void main(String[] args) {
        List<Integer> list= new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        System.out.println(list.get(1));
      //  list.remove(20);  // runntime error in arraylist get and remove is based on index not value
        System.out.println(list.get(1));
        list.remove(1);
        System.out.println(list.get(Integer.valueOf(40)));
    }
}
