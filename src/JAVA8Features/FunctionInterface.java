package JAVA8Features;
public interface FunctionInterface {
    void  iePrime();  //abstract method and only one abstract method
    void isEven();
    default void printName()
    {
        System.out.println("");
    }

}
