package studycase.main.repository;

import studycase.main.entity.Book;
import java.util.List;

public interface BookRepository {
    void insert(Book book);
    boolean remove(Integer id);
    Book findById(Integer id);
    List<Book> findByTitle(String title);
    List<Book> findAll();
    boolean update(int id, String titleOrAuthor, String updateData);
    boolean borrow(Integer id, String title);
    boolean returnBook(Integer id, String title);
    List<Book> borrowedBook();
    List<Book> notBorrowedBook();
    int bookTotals();

}
