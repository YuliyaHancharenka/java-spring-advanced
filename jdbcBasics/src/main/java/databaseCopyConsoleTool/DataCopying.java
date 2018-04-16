package databaseCopyConsoleTool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataCopying {

    public static final String URL = "jdbc:mysql://localhost:3306/jdbcBasics";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "";

    private static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) throws IOException {
        Connection connection = null;
        ResultSet tables = null;

        try {
            connection = getConnection();
            DatabaseMetaData dbMetaData = connection.getMetaData();
            tables = dbMetaData.getTables(null, null, null, null);

            printDbMetadata(dbMetaData, tables);
            List<String> tableList = getTables(dbMetaData);
            saveTableNamesToFile(tableList);
            saveRowsDataTofFiles(connection, tableList, args[0], args[1]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (tables != null) {
                    tables.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    private static void printDbMetadata(DatabaseMetaData dbMetaData, ResultSet tables) throws SQLException {
        log.info("DB " + dbMetaData.getDatabaseProductName()
                + " with driver " + dbMetaData.getDriverName()
                + " and max columns in GROUP BY " + dbMetaData.getMaxColumnsInGroupBy());

        while (tables.next()) {
            String tableName = tables.getString(3);
            log.info("Meta info about table " + tableName);
            ResultSet columns = dbMetaData.getColumns(null, null, tableName, null);
            while (columns.next()) {
                String name = columns.getString("COLUMN_NAME");
                String type = columns.getString("TYPE_NAME");
                int size = columns.getInt("COLUMN_SIZE");
                log.info("Column name: [" + name + "]; type: [" + type
                        + "]; size: [" + size + "]");
            }
        }
    }

    private static void saveTableNamesToFile(List<String> tablesList) throws IOException {
        log.info("Saving tables names to file");
        PrintWriter pw = new PrintWriter(new FileWriter("tables.txt"));
        tablesList.forEach(t -> {
            pw.write(t + "\r\n");
        });
        pw.close();
    }

    private static List<String> getTables(DatabaseMetaData dbMetaData) throws SQLException {
        ResultSet tables = dbMetaData.getTables(null, null, null, null);
        List<String> tablesList = new ArrayList<>();
        while (tables.next()) {
            String tableName = tables.getString(3);
            tablesList.add(tableName);
            log.info("Meta info about table " + tableName);
        }
        return tablesList;
    }

    private static void saveRowsDataTofFiles(Connection con, List<String> tablesList, String tableOrder, String rowOrder) throws IOException {
        log.info("Saving rows values to file, according to each table");
        PrintWriter pw = new PrintWriter(new FileWriter("rows.txt"));
        Integer tableSortOrder = 1;
        Integer rowSortOrder = 1;

        switch (tableOrder) {
            case "asc":
                tableSortOrder = 1;
                break;
            case "desc":
                tableSortOrder = -1;
                break;
            default:
                throw new IllegalArgumentException();
        }

        switch (rowOrder) {
            case "asc":
                rowSortOrder = 1;
                break;
            case "desc":
                rowSortOrder = -1;
                break;
            default:
                throw new IllegalArgumentException();
        }

        Integer finalSortOrderTable = tableSortOrder;
        Integer finalSortOrderRow = rowSortOrder;
        tablesList
                .stream()
                .sorted((o1, o2) -> o1.compareTo(o2) * finalSortOrderTable)
                .forEach(t -> {
                    pw.write(t + "\r\n");
                    try {
                        String query = "SELECT * FROM " + t;
                        Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        ResultSet rs = st.executeQuery(query);
                        int columnsQuantity = rs.getMetaData().getColumnCount();
                        rs.afterLast();
                        while (rs.previous()) {
                            List<String> content = new ArrayList();
                            for (int i = 1; i <= columnsQuantity; i++) {
                                content.add(rs.getString(i) + ", ");
                            }
                            content.stream()
                                    .sorted((o1, o2) -> o1.compareTo(o2) * finalSortOrderRow)
                                    .forEach(item -> {
                                        pw.append(item);
                                    });

                            pw.append("\n");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
        pw.close();
    }
}
