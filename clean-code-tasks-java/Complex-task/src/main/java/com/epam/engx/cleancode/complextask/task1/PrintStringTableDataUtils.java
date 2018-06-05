package com.epam.engx.cleancode.complextask.task1;

import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DataSet;

import java.util.List;

import static com.epam.engx.cleancode.complextask.task1.PrintUtils.*;

public class PrintStringTableDataUtils {

    public static String getStringTableData(List<DataSet> dataSets) {
        int rowsCount;
        rowsCount = dataSets.size();
        int maxColumnSize = getMaxColumnSize(dataSets);
        StringBuilder resultStringTable = new StringBuilder();
        resultStringTable.append("");
        maxColumnSize = updateMaxColumnSize(maxColumnSize);
        int columnCount = getColumnCount(dataSets);

        for (int row = 0; row < rowsCount; row++) {
            List<Object> values = dataSets.get(row).getValues();

            resultStringTable.append("║");
            for (int column = 0; column < columnCount; column++) {
                printValuesInTableRowCell(maxColumnSize, resultStringTable, values, column);
            }
            resultStringTable.append("\n");
            printRowBorder(rowsCount, maxColumnSize, resultStringTable, columnCount, row);
        }
        resultStringTable.append("╚");
        for (int j = 1; j < columnCount; j++) {
            printBorders(maxColumnSize, resultStringTable, "╩");
        }
        printBorders(maxColumnSize, resultStringTable, "╝\n");
        return resultStringTable.toString();
    }

    private static void printRowBorder(int rowsCount, int maxColumnSize, StringBuilder resultStringTable, int columnCount, int row) {
        if (row < rowsCount - 1) {
            resultStringTable.append("╠");
            for (int j = 1; j < columnCount; j++) {
                printBorders(maxColumnSize, resultStringTable, "╬");
            }
            printBorders(maxColumnSize, resultStringTable, "╣\n");
        }
    }

    private static void printValuesInTableRowCell(int maxColumnSize, StringBuilder resultStringTable, List<Object> values, int column) {
        int valuesLength = String.valueOf(values.get(column)).length();
        if (valuesLength % 2 == 0) {
            printTableCellWithValue(maxColumnSize, resultStringTable, values, column, valuesLength);
            printTableCell(maxColumnSize, resultStringTable, valuesLength);
            resultStringTable.append("║");
        } else {
            printTableCellWithValue(maxColumnSize, resultStringTable, values, column, valuesLength);
            for (int j = 0; j <= (maxColumnSize - valuesLength) / 2; j++) {
                resultStringTable.append(" ");
            }
            resultStringTable.append("║");
        }
    }

}
