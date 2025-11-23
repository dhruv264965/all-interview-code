package Collection.ArrayList;

import java.util.*;

public class ArrayListDemo {
    public static void main(String[] args) {
        List<Integer>l=new ArrayList<>();
        l.add(5);
        l.add(10);
        l.add(3);
        l.add(51);
        l.add(56);
        l.add(5);
        System.out.println(l);
        l.sort(Comparator.reverseOrder());
        System.out.println(l);
        System.out.println(l.get(0));
        System.out.println(l.get(l.size()-1));
        l.add(2,48);
        System.out.println(l);
     //   l.get(l.lastIndexOf(l));
        System.out.println("-------------------------------------------------------------------------");
        List<String> list_Strings = new ArrayList<String>();
        list_Strings.add("Red");
        list_Strings.add("Green");
        list_Strings.add("Orange");
        list_Strings.add("White");
        list_Strings.add("Black");
        // Print the list
        System.out.println(list_Strings);
        // Update the third element with "Yellow"
        list_Strings.set(2, "Yellow");
        // Print the list again
        System.out.println(list_Strings);
        System.out.println("--------------------------------------------------------------");

        List<String> list_Stringss = new ArrayList<String>();
        list_Stringss.add("Red");
        list_Stringss.add("Green");
        list_Stringss.add("Orange");
        list_Stringss.add("White");
        list_Stringss.add("Black");
        // Print the list
        System.out.println(list_Stringss);
        // Remove the third element from the list.
        list_Stringss.remove(2);
        // Print the list again
        System.out.println("After removing third element from the list:\n"+list_Stringss);
        if(list_Stringss.contains("Red")){
            System.out.println("found");
        }
        else System.out.println("not found");

        System.out.println("List before sort: "+list_Stringss);
        Collections.sort(list_Stringss);
        System.out.println("List after sort: "+list_Stringss);

        System.out.println("----------------------------------");

        System.out.println("List before reversing :\n" + list_Stringss);
        Collections.reverse(list_Stringss);
        System.out.println("List after reversing :\n" + list_Stringss);
        System.out.println("--------------------------------------");
        System.out.println("Original list: " + list_Strings);
        List<String> sub_List = list_Strings.subList(0, 3);
        System.out.println("List of first three elements: " + sub_List);

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        List<String> l1=Arrays.asList("mon","tue");
        System.out.println(l1);
        //l1.add("Wed");   not able to add will get exception.. this is not from list interface
                          // add delete not possible but we can set the data
        l1.set(1,"wed");
        System.out.println(l1);

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
      List<Integer>l2=  List.of(1,2,3,4,5);
        System.out.println(l2);  //here add,delete,modify not possible

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
       List<Integer>l4=new ArrayList<>();
       l4.add(5);
       l4.add(6);
       l4.add(7);
       l4.add(8);
       l4.add(9);
        System.out.println(l4);
        l4.remove(1); ///remove 1st index
        l4.remove(Integer.valueOf(7)); //remove value
        System.out.println(l4);
        System.out.println("-------------------------------------------------");

        Integer[] array1=l4.toArray(new Integer[0]);
        for(int x :array1)
        System.out.println(x);
    }
}
