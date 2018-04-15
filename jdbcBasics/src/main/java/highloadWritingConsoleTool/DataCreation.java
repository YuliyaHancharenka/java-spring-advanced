package highloadWritingConsoleTool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class DataCreation {

    public static final String URL = "jdbc:mysql://localhost:3306/jdbcBasics";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "";
    private static Logger log = LogManager.getRootLogger();

    private static final int THREADS_QUANTITY = 15;
    private static final int TABLES_QUANTITY = 15;
    private static final int COLUMNS_QUANTITY = 5;
    private static final int ROWS_QUANTITY = 50;


    public static void main(String[] args) throws SQLException {
        List dataTypesList = List.of("VARCHAR(255)", "CHAR(50)", "INTEGER", "FLOAT", "DOUBLE", "DATE", "TIME");
        Random random = new Random();
        AtomicInteger tablesQuantity = new AtomicInteger(random.nextInt(TABLES_QUANTITY) + 1);
        int threadsQuantity = random.nextInt(THREADS_QUANTITY) + 1;
        int columnsQuantity = random.nextInt(COLUMNS_QUANTITY) + 1;
        int rowsQuantity = random.nextInt(ROWS_QUANTITY) + 1;
        log.info("\nTables quantity: " + tablesQuantity + ",\nThreads quantity: " + threadsQuantity + ",\nColumns quantity: "
                + columnsQuantity + ",\nRows quantity: " + rowsQuantity + "\n");

        log.info("\nDrop table for debug purpose\n");
        Connection conn;
        conn = getConnection();
        conn.setAutoCommit(false);
        Statement st = conn.createStatement();
        for (int i = 1; i <= TABLES_QUANTITY; i++) {
            try {
                st.execute("DROP TABLE `Table_" + i + "`;");
            } catch (SQLException e) {

            }
        }
        conn.close();

        log.info("\nCreating tables with data filling\n");
        for (int i = 0; i < threadsQuantity; i++)
            new Thread(() -> {
                Connection connection = null;
                Statement stmt = null;
                long startTime = System.currentTimeMillis();
                try {
                    connection = getConnection();
                    connection.setAutoCommit(false);
                    stmt = connection.createStatement();
                    int tableNumber = tablesQuantity.getAndDecrement();
                    while (tableNumber > 0) {
                        String tableName = "Table_" + tableNumber;
                        StringBuilder sql = new StringBuilder("CREATE TABLE " + tableName + "(");
                        List<String> columnTypeList = new ArrayList<>();

                        for (int j = 0; j < columnsQuantity; j++) {
                            String dataType = String.valueOf(dataTypesList.get(random.nextInt(dataTypesList.size() - 1) + 1));
                            columnTypeList.add(dataType);
                            sql.append("Column_" + j + " " + dataType);
                            if (j != columnsQuantity - 1) {
                                sql.append(",");
                            }
                        }
                        sql.append(");");
                        stmt.execute(sql.toString());

                        for (int j = 0; j < rowsQuantity; j++) {
                            sql = new StringBuilder("INSERT INTO " + tableName + " VALUES (");
                            for (int k = 0; k < columnsQuantity; k++) {
                                String dataType = columnTypeList.get(k);
                                LocalDateTime dateTime = LocalDateTime.now()
                                        .minusYears(random.nextInt(60 - 14) + 14)
                                        .minusMonths(random.nextInt(12))
                                        .minusDays(random.nextInt(30));
                                switch (dataType) {
                                    case "VARCHAR(255)":
                                        sql.append("'Row_" + k + "'");
                                        break;
                                    case "CHAR(50)":
                                        sql.append("'Row_" + k + "'");
                                        break;
                                    case "INTEGER":
                                        sql.append(k);
                                        break;
                                    case "DOUBLE":
                                        sql.append((double) k);
                                        break;
                                    case "FLOAT":
                                        sql.append((float) k);
                                        break;
                                    case "DATE":
                                        sql.append("'" + dateTime.toLocalDate() + "'");
                                        break;
                                    case "TIME":
                                        sql.append("'" + dateTime.toLocalTime() + "'");
                                        break;
                                }
                                if (k != columnsQuantity - 1) {
                                    sql.append(",");
                                }
                            }
                            sql.append(");");
                            stmt.execute(sql.toString());
                        }
                        tableNumber = tablesQuantity.getAndDecrement();
                    }
                    connection.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (stmt != null) {
                            stmt.close();
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                long endTime = System.currentTimeMillis();
                log.info("Execution time of " + Thread.currentThread().getName() + " - " + (endTime - startTime));
            }).start();
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}
