package javadasar.studycase.perpusoop.repositories;

import javadasar.studycase.perpusoop.entities.Book;
import java.util.List;

public interface BookRepository {
    List<Book> getAll();
    boolean add(Book bok);
    boolean remove(String id);
    Book[] search(String title);
    Book borrow(String title, String id);
    Book returnBook(String title, String id);
    Book[] borrowed();
    Book[] notBorrowed();
    int getTotal();

}
