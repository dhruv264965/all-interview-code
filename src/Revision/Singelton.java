package Revision;

import singleton.Singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Singelton implements Cloneable{
    private static Singelton singelton;
    private Singelton(){

    }
    public static Singelton getInstance(){
        if(singelton==null){
            singelton=new Singelton();
        }
        return singelton;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, CloneNotSupportedException {
        Singelton getInstance=Singelton.getInstance();
        Singelton getInstance2=Singelton.getInstance();
        System.out.println(getInstance.hashCode());
        System.out.println(getInstance2.hashCode());
        System.out.println("----------------------------------------");
        //way to break Singleton design pattern
        // Using reflection
        System.out.println("Using reflection-------");
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = null;

        Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
        constructor.setAccessible(true); // Bypass private access
        instance2 = constructor.newInstance();

        System.out.println("Instance 1: " + instance1.hashCode());
        System.out.println("Instance 2: " + instance2.hashCode());

        System.out.println("USING CLONING------------");
        Singelton instance3=Singelton.getInstance();
        Singelton instance4= (Singelton) instance3.clone();
        System.out.println("Instance 3: " + instance3.hashCode());
        System.out.println("Instance 4: " + instance4.hashCode());

        System.out.println("Use synchronized blocks or Double-Checked Locking");


    }
}
