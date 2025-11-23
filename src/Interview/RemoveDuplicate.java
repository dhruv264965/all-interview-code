package Interview;

import java.util.HashSet;

public class RemoveDuplicate {
    public static void main(String[] args) {
        int arr[]={2,5,7,9,6,4,2,5,7};
        HashSet<Integer>set=new HashSet<>();
        for(int x:arr){
            set.add(x);
        }
        System.out.println(set);

    }
}
