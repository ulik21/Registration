package sample;

import javafx.scene.control.Alert;

import java.sql.*;

public class DbWork {


    public boolean checkUser(User user) {

        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user u where u.login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();

            String login = null, password = null;
            while (resultSet.next()) {
                login = resultSet.getString("login");
                password = resultSet.getString("password");


            }

      return user.getLogin().equals(login) && user.getPassword().equals(password) ? true: false;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
public boolean  saveUser(User user){
    Connection connection = getConnection();
    try{
        Statement statement = connection.createStatement();
        String query = "insert into user(login,password) values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,user.getLogin());
        preparedStatement.setString(2,user.getPassword());
        int resultOfSaving = preparedStatement.executeUpdate();
        return resultOfSaving == 1? true : false;


    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }finally {
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    return false;

}





    public Connection getConnection(){
        Connection connection = null;
        try{

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Улик\\Documents\\JavaBasicProjetcs\\SQLite\\dbJavaFx.db");
            System.out.println("Connection");
        }

        catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
