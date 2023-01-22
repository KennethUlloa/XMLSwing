package mocking.models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class XMLTableModel extends AbstractTableModel {

    private ArrayList<ArrayList<String>> data;
    private ArrayList<String> headers;
    private final int columns;

    public XMLTableModel(ArrayList<ArrayList<String>> data, ArrayList<String> headers, int columns) {
        this.data = data;
        this.headers = headers;
        this.columns = columns;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex >= data.get(rowIndex).size()) return null;
        return data.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        if(column >= headers.size()) return null;
        return headers.get(column);
    }
}
