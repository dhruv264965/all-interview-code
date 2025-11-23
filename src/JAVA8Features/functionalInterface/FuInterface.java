package JAVA8Features.functionalInterface;
@FunctionalInterface
public interface FuInterface {
    void add();  //only one abstract method
    default void  sub(){    //any number of default and static method
                            //default method can override also but static method can not overide
                          //implementation is mandatory
    }
    static void mul(){

    }
}
