package databaseCopyConsoleTool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataCopying {

    public static final String URL = "jdbc:mysql://localhost:3306/jdbcBasics";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "";
    private static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) throws SQLException {

    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}
