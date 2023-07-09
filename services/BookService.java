package javadasar.studycase.perpusoop.services;

import javadasar.studycase.perpusoop.entities.Book;

public interface BookService {
    void showBook();
    boolean addBook(Book book);
    boolean removeBook(String id);
    Book[] searchBook(String title);
    Book borrowBook(String title, String id);
    Book returnBook(String title, String id);
    boolean borrowedBooks();
    boolean notBorrowedBook();
    boolean totalBooks();
    
}
