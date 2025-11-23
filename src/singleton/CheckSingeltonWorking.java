package singleton;

public class CheckSingeltonWorking {
    public static void main(String[] args) {
        Singleton originalSingletonInstance=Singleton.getInstance();
        Singleton deplicationSingeltonInstance=Singleton.getInstance();
        System.out.println("Hashcode of originalSingletonInstance "+ originalSingletonInstance.hashCode());
        System.out.println("Hashcode of deplicationSingeltonInstance "+ deplicationSingeltonInstance.hashCode());

    }
}
