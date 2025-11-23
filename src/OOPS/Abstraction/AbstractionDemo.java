package OOPS.Abstraction;
abstract class geek {
    String str;
    int id;
    abstract void turnOn();

    abstract void turnOff();
    void eat(){
                        /// for default method need to give implemenation
    }

    public geek(String str, int id) {
        this.str = str;
        this.id = id;
    }
}
class TvRemote extends geek {
    public TvRemote(String str, int id) {
        super(str, id);
    }

    @Override
    void turnOn() {
        System.out.println("TV is turned ON.");
    }

    @Override
    void turnOff() {
        System.out.println("TV is turned OFF");
    }
}
public  class AbstractionDemo {
    public static void main(String[] args) {
         geek remote =new TvRemote("hi",1);
         remote.eat();
         remote.turnOff();
         remote.turnOff();

    }
    }

