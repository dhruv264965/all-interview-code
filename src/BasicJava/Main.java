package BasicJava;


class Parentt {

    Parentt (){
        this("Constructor");
        System.out.println("Parent class Default Constructor");
    }

    Parentt (String s){
        System.out.println("Parent Class Param  "+s); // 1
    }
}

public class Main extends Parentt {

    Main (){
        super("Constructor");  // we are passing constructor here so constructor will call.
        System.out.println("Child Class Default Constructor");
    }

//    Main (String s){
//        System.out.println("Child Class Param Constructor "+s);// 2
//    }

    public static void main(String[] args) {
        Main obj = new Main();
    }

}


