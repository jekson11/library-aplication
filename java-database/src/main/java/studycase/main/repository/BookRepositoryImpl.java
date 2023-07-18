package studycase.main.repository;

import com.zaxxer.hikari.HikariDataSource;
import studycase.main.entity.Book;
import studycase.main.utils.ConnectionUtil;
import studycase.main.utils.DataUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookRepositoryImpl implements BookRepository{
    private HikariDataSource dataSource;
    public BookRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void insert(Book book) {
        String sql = "INSERT INTO Book(title, author, isBorrow) VALUES(?, ?, ?)";
        try (Connection connect = ConnectionUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
             preparedStatement.setString(1, book.getTitle());
             preparedStatement.setString(2, book.getAuthor());
             preparedStatement.setBoolean(3, book.isBorrow());
             preparedStatement.executeUpdate();

             ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
             if (generatedKeys.next()){
                 int id = generatedKeys.getInt(1);
                 book.setId(id);
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public boolean remove(Integer id) {
        if (DataUtil.isExist(id)){
            String sql = "DELETE FROM book WHERE id = ?";
            try (Connection connect = ConnectionUtil.getDataSource().getConnection();
                 PreparedStatement preparedStatement = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            ){
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    @Override
    public Book findById(Integer id) {
        if (bookTotals() == 0){
            return new Book();
        } else {
            String sql = "SELECT * FROM book WHERE id = ?";
            try (Connection connect = ConnectionUtil.getDataSource().getConnection();
                 PreparedStatement preparedStatement = connect.prepareStatement(sql)
            ){
                preparedStatement.setInt(1,id);
                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    if (resultSet.next()){
                        return (new Book(
                                resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getString("author"),
                                resultSet.getBoolean("isBorrow")
                        ));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }

    @Override
    public List<Book> findByTitle(String title) {
        String sql = "SELECT * FROM book WHERE title = ?";
        try (Connection connect = ConnectionUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(sql)){
             preparedStatement.setString(1, title);

            List<Book> books = new ArrayList<>();
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    books.add(new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getBoolean("isBorrow")
                    ));
                }
                return books;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> findAll() {
        String sql = "SELECT * FROM book";
        try (Connection connect = ConnectionUtil.getDataSource().getConnection();
             Statement statement = connect.createStatement()){

            List<Book> books = new ArrayList<>();
            try (ResultSet resultSet = statement.executeQuery(sql)){
                while (resultSet.next()){
                    books.add(new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getBoolean("isBorrow")
                    ));
                }
                return books;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean update(int id, String update, String updateData) {
        if (DataUtil.isExist(id)){
            String sql = DataUtil.whatWillUpdate(update);
            try (Connection connect = ConnectionUtil.getDataSource().getConnection();
                 PreparedStatement preparedStatement = connect.prepareStatement(sql)
            ){
                preparedStatement.setString(1, updateData);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return true;
        }else {
            return false;
        }
    }
    @Override
    public boolean borrow(Integer id, String title) {
        Book findBook = findById(id);
        if (findBook.isBorrow()){
            return false;
        }else {
            String sql = "UPDATE book SET isBorrow = true WHERE id = ?";
            try (Connection connect = ConnectionUtil.getDataSource().getConnection();
                 PreparedStatement preparedStatement = connect.prepareStatement(sql)
            ){
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
    }
    @Override
    public boolean returnBook(Integer id, String title) {
        Book findBook = findById(id);
        if (!findBook.isBorrow()){
            return false;
        }else {
            String sql = "UPDATE book SET isBorrow = false WHERE id = ?";
            try (Connection connect = ConnectionUtil.getDataSource().getConnection();
                 PreparedStatement preparedStatement = connect.prepareStatement(sql)
            ){
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
    }
    @Override
    public List<Book> borrowedBook() {
        String sql = "SELECT * FROM book WHERE isBorrow = true";
        try (Connection connect = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connect.createStatement();
        ){
            List<Book> books = new ArrayList<>();
            try (ResultSet resultSet = statement.executeQuery(sql)){
                while (resultSet.next()){
                    books.add(new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getBoolean("isBorrow"))
                    );
                }
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Book> notBorrowedBook() {
        String sql = "SELECT * FROM book WHERE isBorrow = false";
        try (Connection connect = ConnectionUtil.getDataSource().getConnection();
             Statement statement = connect.createStatement();
        ){
            List<Book> books = new ArrayList<>();
            try (ResultSet resultSet = statement.executeQuery(sql)){
                while (resultSet.next()){
                    books.add(new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getBoolean("isBorrow"))
                    );
                }
                return books;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int bookTotals() {
        String sql = "SELECT COUNT(id) AS total FROM book";
        try (Connection connect = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connect.createStatement()
        ){
            try (ResultSet resultSet = statement.executeQuery(sql)){
                if (resultSet.next()){
                    return resultSet.getInt("total");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}