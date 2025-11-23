package Interview;

public class Singleton {
    private static Singleton instance;
    private Singleton(){};
    public static Singleton getInstance(){
        if(instance==null){
            instance= new Singleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton getOriginal= Singleton.getInstance();
        Singleton getOriginal1= Singleton.getInstance();
        System.out.println(getOriginal.hashCode());
        System.out.println(getOriginal1.hashCode());
    }
}
