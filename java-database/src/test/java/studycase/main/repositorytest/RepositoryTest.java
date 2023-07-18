package studycase.main.repositorytest;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studycase.main.entity.Book;
import studycase.main.repository.BookRepository;
import studycase.main.repository.BookRepositoryImpl;
import studycase.main.utils.ConnectionUtil;
import java.sql.*;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class RepositoryTest {
    BookRepository bookRepository;
    HikariDataSource dataSource;

    @BeforeEach
    void setUp() {
        dataSource = ConnectionUtil.getDataSource();
        bookRepository = new BookRepositoryImpl(dataSource);

    }

    @Test
    void testInsert(){
        String sql = "DELETE FROM book";
        try(Connection connect = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connect.createStatement()
        ){
            statement.executeUpdate(sql);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Book aku = new Book("Aku", "Jekson", true);
        bookRepository.insert(aku);
        Book kau = new Book("Kau", "Ucup", false);
        bookRepository.insert(kau);
        Book dia = new Book("Dia", "Ucok", true);
        bookRepository.insert(dia);
        Book kamu = new Book("Kamu", "Ujang", true);
        bookRepository.insert(kamu);

        assertNotNull(aku);
        assertNotNull(bookRepository);
        assertEquals("Jekson", aku.getAuthor());
        assertNotNull(aku.getId());
    }

    @Test
    void testRemove(){
        String sql = "SELECT * FROM book WHERE id = 63";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()){
            try(ResultSet resultSet = statement.executeQuery(sql)){
                assertTrue(resultSet.next());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        bookRepository.remove(63);

        String sqlRemove = "SELECT * FROM book WHERE id = 63";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()){
            try(ResultSet resultSet = statement.executeQuery(sqlRemove)){
                assertFalse(resultSet.next());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testFindById(){
        Book book = bookRepository.findById(100);
        assertNotNull(book);
        assertEquals(100, book.getId());
        System.out.println((book));
        assertNull(bookRepository.findById(1));

        String sql = "DELETE FROM book";
        try(Connection connect = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connect.createStatement()
        ){
            statement.executeUpdate(sql);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Book bookNull = bookRepository.findById(99);
        assertNull(bookNull.getId());
    }

    @Test
    void testFindByTitle(){
        List<Book> aku = bookRepository.findByTitle("Aku");
        assertNotNull(aku);
        aku.forEach(System.out::println);

        assertTrue(bookRepository.findByTitle("gadak").isEmpty());

    }

    @Test
    void testFinAll(){
        List<Book> books = bookRepository.findAll();
        assertFalse(books.isEmpty());
        books.forEach(System.out::println);

        String sql = "DELETE FROM book";
        try(Connection connect = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connect.createStatement()
        ){
            statement.executeUpdate(sql);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        assertTrue(bookRepository.findAll().isEmpty());
    }

    @Test
    void testUpdate(){
        boolean update = bookRepository.update(123, "title", "Aid");
        assertTrue(update);

        String sql = "SELECT * FROM book WHERE title = 'Aid'";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()){
            try(ResultSet resultSet = statement.executeQuery(sql)){
                assertTrue(resultSet.next());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean updateFailed = bookRepository.update(2, "title", "Aid");
        assertFalse(updateFailed);
    }

    @Test
    void testBorrow(){
        boolean result = bookRepository.borrow(127, "Dia");
        assertTrue(result);
        String sql = "SELECT isBorrow FROM book WHERE id = 127";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()){
            try(ResultSet resultSet = statement.executeQuery(sql)){
                if (resultSet.next()){
                    assertTrue(resultSet.getBoolean("isBorrow"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean resultFalse = bookRepository.borrow(127, "Dia");
        assertFalse(resultFalse);
    }

    @Test
    void testReturnBook(){
        boolean result = bookRepository.returnBook(151, "Aku");
        assertTrue(result);
        String sql = "SELECT isBorrow FROM book WHERE id = 149";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()){
            try(ResultSet resultSet = statement.executeQuery(sql)){
                if (resultSet.next()){
                    assertFalse(resultSet.getBoolean("isBorrow"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean resultFailed = bookRepository.returnBook(133,"Kamu");
        assertFalse(resultFailed);
    }

    @Test
    void testBorrowedBooks(){
        List<Book> books = bookRepository.borrowedBook();
        assertNotNull(books);
        books.forEach(book -> assertTrue(book.isBorrow()));
        books.forEach(book -> System.out.println(book));

        String sql = "DELETE FROM book";
        try(Connection connect = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connect.createStatement()
        ){
            statement.executeUpdate(sql);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Book> booksNull = bookRepository.borrowedBook();
        booksNull.forEach(book -> assertNull(book.getId()));
        booksNull.forEach(book -> System.out.println(book));
    }

    @Test
    void testNotBorrowedBooks(){
        List<Book> books = bookRepository.notBorrowedBook();
        assertNotNull(books);
        books.forEach(book -> assertFalse(book.isBorrow()));
        books.forEach(book -> System.out.println(book));

        String sql = "DELETE FROM book";
        try(Connection connect = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connect.createStatement()
        ){
            statement.executeUpdate(sql);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Book> NoBooks = bookRepository.notBorrowedBook();
        NoBooks.forEach(book -> assertNull(book.getId()));
        NoBooks.forEach(book -> System.out.println(book));
    }

    @Test
    void testBookTotals(){
        int totals = bookRepository.bookTotals();
        assertEquals(4, totals);
    }

    @AfterEach
    void tearDown() {
        dataSource.close();
    }
}
