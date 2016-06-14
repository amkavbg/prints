package ru.tokido.gui;

import javax.swing.table.AbstractTableModel;

/**
 * Created by tokido on 6/14/16.
 */
public class PTableModel extends AbstractTableModel{
    @Override
    public int getRowCount() {
        return 6;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
