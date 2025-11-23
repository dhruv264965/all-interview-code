package Collection.ArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String>ll=new ArrayList<>();
        ll.add("Dhruv");
        ll.add("shukla");
        System.out.println(ll);
        Iterator<String> itr= ll.iterator();
        while(itr.hasNext()){
            String data= itr.next();
            System.out.println(data);
        }
    }
}
