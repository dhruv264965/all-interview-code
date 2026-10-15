package Collection.ArrayList;

import java.util.ArrayList;

public class NotAllowDuplicateInArrayList extends ArrayList {
    @Override
    public boolean add(Object o) {
        if(this.contains(o)){
            return true;
        }
        else return super.add(o);
    }

    public static void main(String[] args) {
        NotAllowDuplicateInArrayList allowDuplicateInArrayList=new NotAllowDuplicateInArrayList();
        allowDuplicateInArrayList.add(1);
        allowDuplicateInArrayList.add(1);
        allowDuplicateInArrayList.add(3);
        allowDuplicateInArrayList.add(2);
        allowDuplicateInArrayList.add(2);
        System.out.println(allowDuplicateInArrayList);
    }
}
