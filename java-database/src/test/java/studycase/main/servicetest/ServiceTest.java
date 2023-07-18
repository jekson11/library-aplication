package studycase.main.servicetest;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import studycase.main.repository.BookRepositoryImpl;
import studycase.main.service.BookService;
import studycase.main.service.BookServiceImpl;
import studycase.main.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceTest {
    BookRepositoryImpl bookRepository;
    BookService bookService;
    HikariDataSource dataSource;
    @BeforeEach
    void setUp() {
        dataSource = ConnectionUtil.getDataSource();
        bookRepository = new BookRepositoryImpl(dataSource);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void testInsertBook(){
        String massageValid = bookService.insertBook("Kamu", "Ujang");
        String massageValid1 = bookService.insertBook("Aku", "Jekson");
        String massageValid2 = bookService.insertBook("Dia", "Ucup");
        String massageValid3 = bookService.insertBook("Kita", "Udin");
        assertEquals("Data valid", massageValid);

    }

    @Test
    void testRemoveBook(){
        String result = bookService.removeBook(91);
        assertEquals("Successful remove book", result);

        String resultNptFound = bookService.removeBook(10);
        assertEquals("Book not found", resultNptFound);

        String sql = "DELETE FROM book";
        try(Connection connect = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connect.createStatement()
        ){
            statement.executeUpdate(sql);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String resultNoBook = bookService.removeBook(6);
        assertEquals("There are no book in library", resultNoBook);

    }

    @Test
    void testFindBookById(){
        String bookFound = bookService.findBookById(103);
        assertEquals("Book found", bookFound);

        String bookNotFound = bookService.findBookById(10);
        assertEquals("Book not found", bookNotFound);

        String sql = "DELETE FROM book";
        try(Connection connect = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connect.createStatement()
        ){
            statement.executeUpdate(sql);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String bookEmpty = bookService.findBookById(102);
        assertEquals("There ara no book in library", bookEmpty);
    }

    @Test
    void testFindBookByTitle() {
        bookService.findBookByTitle("Kosong");
        bookService.findBookByTitle("Aku");

        String sql = "DELETE FROM book";
        try(Connection connect = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connect.createStatement()
        ){
            statement.executeUpdate(sql);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        bookService.findBookByTitle("Dia");
    }

    @Test
    void testFindBookAll(){
        bookService.findBookAll();

        String sql = "DELETE FROM book";
        try(Connection connect = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connect.createStatement()
        ){
            statement.executeUpdate(sql);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        bookService.findBookAll();
    }

    @Test
    void testUpdateBook(){
        String result = bookService.updateBook(123, "title", "Dia");
        assertEquals("Successful updating of the book", result);

        String resultIdNotFound = bookService.updateBook(20, "title", "Ubah");
        assertEquals("Book with id: 20 is not found", resultIdNotFound);

        String resultComment = bookService.updateBook(53, "salah", "jekson");
        assertEquals("Comment not recognized: salah", resultComment);

        String sql = "DELETE FROM book";
        try(Connection connect = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connect.createStatement()
        ){
            statement.executeUpdate(sql);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String resultNoBooks = bookService.updateBook(53, "author", "jekson");
        assertEquals("There are no books in the library", resultNoBooks);

    }

    @Test
    void testBorrowBook(){
        boolean result = bookService.borrowBook(125, "Kamu");
        assertTrue(result);

        boolean resultFailed = bookService.borrowBook(129, "Aku");
        assertFalse(resultFailed);

        boolean resultFailed2 = bookService.borrowBook(12, "salah");
        assertFalse(resultFailed2);

        boolean resultFailed3 = bookService.borrowBook(129, "Dia");
        assertFalse(resultFailed3);
    }

    @Test
    void testReturnBook(){
        boolean result = bookService.returnBook(136, "Kita");
        assertFalse(result);

        boolean resultFailed = bookService.returnBook(138, "salah");
        assertFalse(resultFailed);

        boolean resultFailed2 = bookService.returnBook(13, "salah");
        assertFalse(resultFailed2);

        boolean resultFailed3 = bookService.returnBook(13, "Kita");
        assertFalse(resultFailed3);

    }

    @Test
    void testBorrowedBook(){
        bookService.borrowedBook();

        String sql = "DELETE FROM book";
        try(Connection connect = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connect.createStatement()
        ){
            statement.executeUpdate(sql);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        bookService.borrowedBook();

    }

    @Test
    void testNotBorrowedBook(){
        bookService.notBorrowedBook();

        String sql = "DELETE FROM book";
        try(Connection connect = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connect.createStatement()
        ){
            statement.executeUpdate(sql);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        bookService.borrowedBook();
    }

    @Test
    void testBookTotals(){
        int value = bookService.bookTotals();
        assertEquals(4, value);
    }

    @AfterEach
    void tearDown() {
        dataSource.close();
    }
}
