package java8Plus.sealedClass;

import java.lang.*;

sealed class Human permits Manish, Vartika, Anjali
{
    public void printName()
    {
        System.out.println("Default");
    }
}

non-sealed class Manish extends Human
{
    public void printName()
    {
        System.out.println("Manish Sharma");
    }
}

non-sealed  class Vartika extends Human  // if class is non-sealed then any other class can extend
{
    public void printName()
    {
        System.out.println("Vartika Dadheech");
    }
}
class Dhruv extends Vartika{

    public void printName() {
        System.out.println("Dhruv Shukla");
    }
}

final class Anjali extends Human  // no other class can extend
{
    public void printName()
    {
        System.out.println("Anjali Sharma");
    }
}
//class Shukla extends Anjali{   we canot extend
//
//}

public class Main
{
    public static void main(String[] args)
    {
        Human h1 = new Anjali();
        Human h2 = new Vartika();
        Human h3 = new Manish();
        Human h4=new Dhruv();

        h1.printName();
        h2.printName();
        h3.printName();
        h4.printName();
    }
}