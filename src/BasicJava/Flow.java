package BasicJava;

public class Flow {
    static {
        System.out.println("static");
    }
    {
        System.out.println("instance");
    }
    Flow(){
        System.out.println("constructor");
    }
    public static void main(String[] args) {
        System.out.println(" Flow1");
        new Flow();
        System.out.println(" Flow2 ");
        new Flow();
    }
}
