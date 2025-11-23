package singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class WayToBreakSingletonDP implements Serializable , Cloneable {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        Singleton originalSingletonInstance = Singleton.getInstance();
        Singleton deplicationSingeltonInstance = Singleton.getInstance();
        System.out.println("Hashcode of originalSingletonInstance " + originalSingletonInstance.hashCode());
        System.out.println("Hashcode of deplicationSingeltonInstance " + deplicationSingeltonInstance.hashCode());

        System.out.println("----------------------------------------------------------------------");
        ///Way to break SingletonDP
        // 1- Reflection
        Class<?> singletonClass = Class.forName("singleton.Singleton");
        Constructor<Singleton> constructor = (Constructor<Singleton>) singletonClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton brokenSingeletonUsingReflactionIns = constructor.newInstance();
        System.out.println("------USING REFLACTION----");
        System.out.println("Hashcode of originalSingletonInstance " + originalSingletonInstance.hashCode());
        System.out.println("Hashcode of brokenSingeletonUsingReflactionIns " + brokenSingeletonUsingReflactionIns.hashCode());


        /// 2- Serializable

        //serialization process

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\serialization.ser"));
        objectOutputStream.writeObject(originalSingletonInstance);
        objectOutputStream.close();

        //Deserialization process

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\serialization.ser"));
        Singleton brokenSingletonUsingSerializationIns = (Singleton) objectInputStream.readObject();
        System.out.println("------USING Serializable----");
        System.out.println("Hashcode of originalSingletonInstance " + originalSingletonInstance.hashCode());
        System.out.println("Hashcode of brokenSingletonUsingSerializationIns " + brokenSingletonUsingSerializationIns.hashCode());

        /// 3- Clonning


//     Singleton brokenSingletonUsingClonningIns= (Singleton) originalSingletonInstance.clone();
//        System.out.println("------USING Clonning----");
//        System.out.println("Hashcode of originalSingletonInstance "+ originalSingletonInstance.hashCode());
//        System.out.println("Hashcode of brokenSingletonUsingClonningIns "+ brokenSingletonUsingClonningIns.hashCode());
//
//    }
    }
}
