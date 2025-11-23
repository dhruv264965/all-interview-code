package OOPS.Interitance;
class P{
    P(){
        System.out.println("P");
    }
    P(int x){
        System.out.println("PX");
    }
}
class C extends P{
    C(){
      //  super(); if we are using also it will call parent if we not use also parent will call
        super(10); // first it will call parent constructor then child constructor
//        this(10);  // sabse pahele parameterised child output- CX
//                   //                                          C
        System.out.println("C");
    }
      C(int x){
        System.out.println("CX");
    }
}
public class Main1 {
    public static void main(String[] args) {
        C aa=new C();

    }
}
