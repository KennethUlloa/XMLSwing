package mocking.panels;

import mocking.JComponentMockFactory;
import mocking.MockFactory;
import mocking.MockNode;
import mocking.models.XMLTableModel;
import org.w3c.dom.Node;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class TableFactory extends JComponentMockFactory {
    @Override
    public JComponent buildComponent(MockNode node) {
        return null;
    }

    @Override
    public JComponent buildComponent(MockNode node, MockFactory mockMaker) {
        JTable table = new JTable();
        if (mockMaker != null) {
            if(node.hasAttribute("id")) {
                mockMaker.register(node.getAttribute("id"), table);
            }
        }
        table.setModel(readData(node.getNode()));
        return table;
    }

    private TableModel readData(Node node) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> header = null;
        int columns = 0;
        for(int i = 0 ; i < node.getChildNodes().getLength() ; i++) {
            Node currNode = node.getChildNodes().item(i);
            System.out.println(currNode);
            if(currNode.getNodeName().equals("th")) {
                header = readRow(currNode);

                columns = Math.max(columns, header.size());
            }else if(currNode.getNodeName().equals("tr")) {
                ArrayList<String> row = readRow(currNode);
                data.add(row);
                columns = Math.max(columns, row.size());
            }
        }
        return new XMLTableModel(data, header, columns);
    }

    private ArrayList<String> readRow(Node node) {
        ArrayList<String> row = new ArrayList<>();
        for(int i = 0 ; i < node.getChildNodes().getLength() ; i++) {
            Node currNode = node.getChildNodes().item(i);
            System.out.println(currNode);
            if(currNode.getNodeName().equals("td")) {
                row.add(currNode.getTextContent().trim());
            }
        }
        return row;
    }


}
