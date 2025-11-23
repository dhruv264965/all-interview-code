package Composition;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        Library library=new Library();  //create instance of liberry
        List<Book>books=library.getTotalBooksInLibrary();
       // System.out.println(books);
        for (Book bk:books){
            System.out.println(bk);
        }
    }
}
