import mocking.MockFactory;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        MockFactory mockMaker = new MockFactory(Files.newInputStream(Paths.get("./interface.xml")));
        //mockMaker.setVariable("name", "Kenneth");
        //mockMaker.setVariable("last-name","Ulloa");
        //mockMaker.loadVariables();
        JFrame frame = mockMaker.generateFrame();
        JButton button = (JButton) mockMaker.getElement("runBtn");
        button.addActionListener(e -> {
            JTextArea textArea = (JTextArea) mockMaker.getElement("queryArea");
            String query = textArea.getText();
            JTable table = (JTable) mockMaker.getElement("table");
            String user = ((JTextField)mockMaker.getElement("user")).getText();
            String pass = ((JTextField)mockMaker.getElement("password")).getText();
            String host = ((JTextField)mockMaker.getElement("host")).getText();
            int rows = 1 + (int)(Math.random()*100);
            int cols = 1 + (int)(Math.random()*8);
            int val = 1 + (int)(Math.random()*100);
            TableModel tableModel = new AbstractTableModel() {
                @Override
                public int getRowCount() {
                    return rows;
                }

                @Override
                public int getColumnCount() {
                    return cols;
                }

                @Override
                public Object getValueAt(int rowIndex, int columnIndex) {
                    return val;
                }
            };
            table.setModel(tableModel);

        });
        frame.setVisible(true);


    }
}
