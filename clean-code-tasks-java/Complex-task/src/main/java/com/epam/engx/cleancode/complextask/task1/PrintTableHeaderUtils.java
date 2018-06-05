package com.epam.engx.cleancode.complextask.task1;

import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DataSet;

import java.util.List;

import static com.epam.engx.cleancode.complextask.task1.PrintUtils.*;

public class PrintTableHeaderUtils {

    public static String getHeaderOfTheTable(List<DataSet> dataSets) {
        int maxColumnSize = getMaxColumnSize(dataSets);
        StringBuilder resultStringTable = new StringBuilder();
        resultStringTable.append("");
        int columnCount = getColumnCount(dataSets);
        maxColumnSize = updateMaxColumnSize(maxColumnSize);

        resultStringTable.append("╔");
        printColumnBorder(maxColumnSize, resultStringTable, columnCount, "╦");
        printBorders(maxColumnSize, resultStringTable, "╗\n");
        List<String> columnNames = dataSets.get(0).getColumnNames();

        for (int column = 0; column < columnCount; column++) {
            printColumnCell(maxColumnSize, resultStringTable, columnNames, column);
        }
        resultStringTable.append("║\n");

        printLastHeaderString(dataSets, maxColumnSize, resultStringTable, columnCount);
        return resultStringTable.toString();
    }

    private static void printColumnCell(int maxColumnSize, StringBuilder resultStringTable, List<String> columnNames, int column) {
        resultStringTable.append("║");
        int columnNamesLength = columnNames.get(column).length();
        if (columnNamesLength % 2 == 0) {
            printTableCellWithValue(maxColumnSize, resultStringTable, columnNames, column, columnNamesLength);
            printTableCell(maxColumnSize, resultStringTable, columnNamesLength);
        } else {
            printTableCellWithValue(maxColumnSize, resultStringTable, columnNames, column, columnNamesLength);
            for (int j = 0; j <= (maxColumnSize - columnNamesLength) / 2; j++) {
                resultStringTable.append(" ");
            }
        }
    }

    private static void printLastHeaderString(List<DataSet> dataSets, int maxColumnSize, StringBuilder resultStringTable, int columnCount) {
        if (!dataSets.isEmpty()) {
            resultStringTable.append("╠");
            printColumnBorder(maxColumnSize, resultStringTable, columnCount, "╬");
            printBorders(maxColumnSize, resultStringTable, "╣\n");
        } else {
            resultStringTable.append("╚");
            printColumnBorder(maxColumnSize, resultStringTable, columnCount, "╩");
            printBorders(maxColumnSize, resultStringTable, "╝\n");
        }
    }

    private static void printColumnBorder(int maxColumnSize, StringBuilder resultStringTable, int columnCount, String str) {
        for (int j = 1; j < columnCount; j++) {
            printBorders(maxColumnSize, resultStringTable, str);
        }
    }
}
