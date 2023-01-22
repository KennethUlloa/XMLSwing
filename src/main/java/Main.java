import mocking.MockFactory;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        MockFactory mockMaker = new MockFactory(Files.newInputStream(Paths.get("./interface.xml")));
        mockMaker.setVariable("name", "Kenneth");
        mockMaker.setVariable("last-name","Ulloa");
        mockMaker.loadVariables();
        JFrame frame = mockMaker.generateFrame();
        JButton button = (JButton) mockMaker.getElement("accept");
        button.addActionListener(e -> {
            JTextField textField = (JTextField) mockMaker.getElement("name");
            JTextField textField1 = (JTextField) mockMaker.getElement("last-name");
            JOptionPane.showMessageDialog(frame, textField.getText() + " " + textField1.getText());
        });
        frame.setVisible(true);

    }
}
