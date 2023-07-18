package studycase.main.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DataUtil {
    private static final Scanner scanner = new Scanner(System.in);
    public static String input(String inputUser){
        System.out.print(inputUser + " :");
        return scanner.nextLine();
    }
    public static boolean isNull(String title, String author){
        return title.isEmpty() || author.isEmpty() &&
                title.isBlank() || author.isBlank();
    }
    public static boolean isValid(String update){
        return update.equalsIgnoreCase("title")
                || update.equalsIgnoreCase("author");
    }
    public static boolean isExist(Integer id){
        String sql = "SELECT id FROM book WHERE id = ?";
        try (Connection connect = ConnectionUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(sql)
        ){
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                return resultSet.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String whatWillUpdate(String update){
        if (update.equalsIgnoreCase("title")){
            return  "UPDATE book SET title = ? WHERE id = ?";
        } else {
            return  "UPDATE book SET author = ? WHERE id = ?";
        }
    }
    public static boolean isExist(Integer id, String title){
        String sqlSelect = "SELECT * FROM book WHERE id = ? AND title = ?";
        try (Connection connect = ConnectionUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(sqlSelect)
        ){
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, title);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
