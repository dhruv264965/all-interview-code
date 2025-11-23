package singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
// in reflection we are getting different hashcode so it means it is volating the SP
// to achieve this we need to write code in private constructor
public class ReflectionDemo {
    public static void main(String[] args) {
        //create two instnce
        Singleton instance1=Singleton.getInstance();
        Singleton instance2=null;
        //using reflection break singleton patter
        try {
            // Singleton.class.getDeclaredConstructor
            Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
            //set setAccesible - true
            constructor.setAccessible(true);
            // put second instance with newInstance
            instance2=constructor.newInstance();


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("Hashcode "+instance1.hashCode());
        System.out.println("Hashcode "+instance2.hashCode());
    }
}
