package jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Let's get useful information after connection establishment
 */
public class InfoFromConnection {

    public static final String URL = "jdbc:mysql://localhost:3306/test";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "";


    public static void main(String[] args) {
        Logger log = LogManager.getRootLogger();
        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            log.info("Current catalog is " + connection.getCatalog());

            /*
            TRANSACTION_NONE = 0
            TRANSACTION_READ_UNCOMMITTED = 1
            TRANSACTION_READ_COMMITTED = 2
            TRANSACTION_REPEATABLE_READ = 4
            TRANSACTION_SERIALIZABLE = 8
             */
            log.info("Current transaction isolation code is " + connection.getTransactionIsolation());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
