package singleton;

public class Synchorised {
    private static Synchorised instance;
    private Synchorised(){}

    public static Synchorised getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Synchorised();
                }
            }
            return instance;
        }

        return null;
    }

    public static void main(String[] args) {

    }

}
