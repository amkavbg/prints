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

    public PTableModel(PrinterTemplate data) {
       this.tableparm = data.getParameters();
       keys = tableparm.keySet().toArray(new String[tableparm.size()]);
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