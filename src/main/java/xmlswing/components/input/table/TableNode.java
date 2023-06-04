package xmlswing.components.input.table;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.components.models.XMLTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class TableNode extends AbstractNode<JTable> {
    public TableNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public JTable getRootElement() {
        JTable table = new JTable();
        table.setModel(readData(getNode()));
        return table;
    }

    private TableModel readData(Node node) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> header = null;
        int columns = 0;
        for(int i = 0 ; i < node.getChildNodes().getLength() ; i++) {
            Node currNode = node.getChildNodes().item(i);
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
            if(currNode.getNodeName().equals("td")) {
                row.add(currNode.getTextContent().trim());
            }
        }
        return row;
    }
}
