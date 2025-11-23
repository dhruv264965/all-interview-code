package Composition;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;

    // Library constructor initializes books internally  ---if We want to initilize the book internally.USE constuctor

        Library() {
        // Creating a new list to hold the books
        this.books = new ArrayList<>();    //this line creates an empty ArrayList to store Book objects.
        // Internal creation of Book objects
        // Adding books to the library
        this.books.add(new Book("Effective Java", "Joshua Bloch"));
        this.books.add(new Book("Thinking in Java", "Bruce Eckel"));
        this.books.add(new Book("Java: The Complete Reference", "Herbert Schildt"));
    }

    // Method to return the list of books in the library
    public List<Book> getTotalBooksInLibrary() {
        return books;
    }
}
