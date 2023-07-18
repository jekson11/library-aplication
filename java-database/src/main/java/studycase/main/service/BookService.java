package studycase.main.service;
public interface BookService {
    String insertBook(String title, String author);
    String removeBook(int id);
    String findBookById(int id);
    void findBookByTitle(String title);
    void findBookAll();
    String updateBook(int id, String titleOrAuthor, String update);
    boolean borrowBook(Integer id, String title);
    boolean returnBook(int id, String title);
    void borrowedBook();
    void notBorrowedBook();
    int bookTotals();
}
