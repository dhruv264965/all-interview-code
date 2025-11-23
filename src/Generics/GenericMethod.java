package Generics;

public class GenericMethod {
    public static <T> void printArray(T[] array){
        for(T elememt:array){
            System.out.print(elememt+" ");
        }
        System.out.println();

    }
    public static <T, U> void printTwoItems(T item1, U item2) {
        System.out.println(item1 + " and " + item2);
    }
    public static <T> T print(T say){
        return say;
    }
    public static void main(String[] args) {
        Integer[] integerArray={1,2,3,4,5};
        String[] stringsArray={"hello","world"};
        printArray(integerArray);
        printArray(stringsArray);
        printTwoItems("hello","sir");
        printTwoItems(10,20);
    }
}
