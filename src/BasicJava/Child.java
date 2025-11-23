package BasicJava;

class Parent {
    Parent(){
        System.out.println("hiiiii");
    }

    Parent (String s){
        System.out.println("Parent Class Param  "+s);
    }
}

public class Child extends Parent {

    Child (){
        super("dhruv");
         //this("dhruv");
        System.out.println("Child Class Default Constructor");
    }

    void start (String s){
//        super(s);
        System.out.println("Child Class Param Constructor "+s);
    }

    public static void main(String[] args) {
        Child obj = new Child();
        obj.start("");
    }

}
//Even though super() is not explicitly written in Child(String s), Java implicitly calls the default constructor of
// Parent when there is no explicit super() call.