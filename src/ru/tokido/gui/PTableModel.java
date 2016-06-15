package ru.tokido.gui;

import ru.tokido.engine.PrinterTemplate;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tokido on 6/14/16.
 */
public class PTableModel extends AbstractTableModel {

    private String[] keys;
    private Map<String, String> tableparm = new HashMap<>();
    private String columnName;

    public PTableModel(PrinterTemplate data) {
       this.tableparm = data.getParameters();
       this.columnName = data.getModel();
       keys = tableparm.keySet().toArray(new String[tableparm.size()]);
        System.out.println("tablemodel is prepare");
    }

    @Override
    public String getColumnName(int column) {
        return super.getColumnName(column);
    }

    @Override
    public int getRowCount() {
        return tableparm.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return keys[rowIndex];
        } else {
            return tableparm.get(keys[rowIndex]);
        }
    }
}
