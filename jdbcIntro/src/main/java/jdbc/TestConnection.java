package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TestConnection {

    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "";

    public static void main(String[] args) {

        // First approach
        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Second approach
        try {
            Connection connection = DriverManager
                    .getConnection(URL + "?user=" + USER_NAME + "&password=" + PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Third approach
        Properties props = new Properties();
        props.setProperty("user", USER_NAME);
        props.setProperty("password", PASSWORD);
        try {
            Connection connection = DriverManager.getConnection(URL, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
