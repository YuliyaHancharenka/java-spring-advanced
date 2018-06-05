package com.epam.engx.cleancode.complextask.task1;

import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.Command;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DataSet;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DatabaseManager;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.View;

import java.util.List;

import static com.epam.engx.cleancode.complextask.task1.PrintStringTableDataUtils.getStringTableData;
import static com.epam.engx.cleancode.complextask.task1.PrintTableHeaderUtils.getHeaderOfTheTable;
import static com.epam.engx.cleancode.complextask.task1.PrintUtils.getEmptyTable;

public class Print implements Command {

    private View view;
    private DatabaseManager manager;
    private String tableName;

    public Print(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    private String getTableString(List<DataSet> data) {
        int maxColumnSize;
        maxColumnSize = PrintUtils.getMaxColumnSize(data);
        if (maxColumnSize == 0) {
            return getEmptyTable(tableName);
        } else {
            return getHeaderOfTheTable(data) + getStringTableData(data);
        }
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("print ");
    }

    @Override
    public void process(String input) {
        String[] command = input.split(" ");
        if (command.length != 2) {
            throw new IllegalArgumentException("incorrect number of parameters. Expected 1, but is " + (command.length - 1));
        }
        tableName = command[1];
        List<DataSet> data = manager.getTableData(tableName);
        view.write(getTableString(data));
    }
}