package xmlswing.components.input;

import xmlswing.components.models.XMLTableModel;
import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import types.TypeNodeFactory;
import xmlswing.ComponentRepository;
import xmlswing.components.PropertiesReader;
import xmlswing.components.ComponentNode;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.Component;
import java.util.ArrayList;

/**
 * <h3>Properties</h3>
 * It has a similar structure to HTML tables but limited to th, tr, td <br>
 * th (row for headers)<br>
 * td (data)<br>
 * tr (row for data) <br>
 */
public class TableNodeFactory implements TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
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
        };
        PropertiesReader.setUpComponent(typeNode);
        ComponentRepository.registerNode(typeNode, container);
        return typeNode;
    }

    @Override
    public String getTagName() {
        return "Table";
    }
}
