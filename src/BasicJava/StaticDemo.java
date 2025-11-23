package BasicJava;
//In Java, the static keyword is used for memory-efficient and class-level behavior.
//Belongs to the class, not instances (objects).
//Loaded once when the class is loaded.
//Can be accessed without creating an object.
public class StaticDemo {
static int x=10; // when class is load static call one that by we are getting output 11 12 13
    StaticDemo(){  // but if we use int x=10; then we will get 11 11 11 because everytime it will call int x=10 then call constructor
        x++;
        System.out.println("Hi "+x);
    }
    void strt(){
        System.out.println("strt");
    }
    static void ttest(){
      //  int p=x;
        System.out.println("static");
       // strt();     ///complile time error
        StaticDemo a=new StaticDemo();
        a.strt();
    }

    }
class Logger {
   static void log() {
        System.out.println("Logging from Logger");
    }
}
class FileLogger extends Logger {
  static   void log() {
        System.out.println("Logging from FileLogger");
    }
    public static void main(String[] args) {
        StaticDemo d=new StaticDemo();//11
        StaticDemo e=new StaticDemo();//12
        StaticDemo f=new StaticDemo();//13
        StaticDemo.ttest();
      //  StaticDemo.x=100;
        StaticDemo dd=new StaticDemo();//101
        StaticDemo ed=new StaticDemo();//102  // Changing static variable affects all objects
        StaticDemo fd=new StaticDemo();//103

        Logger logger = new FileLogger(); // output- Logging from Logger not Logging from FileLogger becaz

        logger.log();  // ⚠️ What will this print?

  //  1. static methods cannot access non-static variables or methods directly.
 /*       public class Demo {
            int x = 10; // non-static (instance variable)

            public static void show() {
                System.out.println(x); // ❌ Compile error: Cannot make a static reference to non-static field
            }
        }                         */

        // 2. non static method can not call static method
            // but we can only call inside static method to make object

    }
}
