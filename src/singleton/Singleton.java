package singleton;

public class Singleton {
    //step1- make private instance of Singleton class
    private static Singleton singleton;
    //step2- make private construtor
    private Singleton() {
        if(singleton!=null){
            throw new IllegalArgumentException("Object already exits");
        }
    }
    //step3- call getInstance() method public and set singleton
    public static Singleton getInstance()
    {
        if(singleton==null)
            singleton=new Singleton();
        return singleton;
    }
}
