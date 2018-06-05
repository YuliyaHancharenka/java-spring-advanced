package com.epam.engx.cleancode.complextask.task1;

import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DataSet;

import java.util.List;

public class PrintUtils {

    public static String getEmptyTable(String tableName) {
        String textEmptyTable = "║ Table '" + tableName + "' is empty or does not exist ║";
        StringBuilder resultEmptyTable = new StringBuilder();
        resultEmptyTable.append("╔");
        printBorder(textEmptyTable.length() - 2, resultEmptyTable);
        resultEmptyTable.append("╗\n");
        resultEmptyTable.append(textEmptyTable).append("\n");
        resultEmptyTable.append("╚");
        printBorder(textEmptyTable.length() - 2, resultEmptyTable);
        resultEmptyTable.append("╝\n");
        return resultEmptyTable.toString();
    }

    public static int getMaxColumnSize(List<DataSet> dataSets) {
        int maxLength = 0;
        if (!dataSets.isEmpty()) {
            maxLength = getColumnMaxLength(dataSets, maxLength);
            maxLength = getDataSetValueMaxLength(dataSets, maxLength);
        }
        return maxLength;
    }

    private static int getDataSetValueMaxLength(List<DataSet> dataSets, int maxLength) {
        for (DataSet dataSet : dataSets) {
            maxLength = getValueMaxLength(maxLength, dataSet);
        }
        return maxLength;
    }

    private static int getValueMaxLength(int maxLength, DataSet dataSet) {
        List<Object> values = dataSet.getValues();
        for (Object value : values) {
            if (String.valueOf(value).length() > maxLength) {
                maxLength = String.valueOf(value).length();
            }
        }
        return maxLength;
    }

    private static int getColumnMaxLength(List<DataSet> dataSets, int maxLength) {
        List<String> columnNames = dataSets.get(0).getColumnNames();
        for (String columnName : columnNames) {
            if (columnName.length() > maxLength) {
                maxLength = columnName.length();
            }
        }
        return maxLength;
    }

    public static void printTableCell(int maxColumnSize, StringBuilder resultStringTable, int valuesLength) {
        for (int j = 0; j < (maxColumnSize - valuesLength) / 2; j++) {
            resultStringTable.append(" ");
        }
    }

    public static int updateMaxColumnSize(int maxColumnSize) {
        if (maxColumnSize % 2 == 0) {
            maxColumnSize += 2;
        } else {
            maxColumnSize += 3;
        }
        return maxColumnSize;
    }

    public static int getColumnCount(List<DataSet> dataSets) {
        int result = 0;
        if (!dataSets.isEmpty()) {
            return dataSets.get(0).getColumnNames().size();
        }
        return result;
    }

    public static void printBorder(int maxColumnSize, StringBuilder resultStringTable) {
        for (int i = 0; i < maxColumnSize; i++) {
            resultStringTable.append("═");
        }
    }

    public static void printTableCellWithValue(int maxColumnSize, StringBuilder resultStringTable, List<? extends Object> values, int column, int valuesLength) {
        printTableCell(maxColumnSize, resultStringTable, valuesLength);
        resultStringTable.append(String.valueOf(values.get(column)));
    }

    public static void printBorders(int maxColumnSize, StringBuilder resultStringTable, String str) {
        printBorder(maxColumnSize, resultStringTable);
        resultStringTable.append(str);
    }
}
