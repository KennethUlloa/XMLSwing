import org.xml.sax.SAXException;
import xmlswing.XMLSwing;
import xmlswing.components.form.EmptyValueNotAllowedException;
import xmlswing.components.form.Form;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, UnsupportedLookAndFeelException {
        XMLSwing<JFrame> swing = new XMLSwing<>(Main.class.getResourceAsStream("gui.xml"));
        JFrame frame = swing.getRootComponent();
        swing.getElement("guardar", JButton.class).addActionListener(a -> {
            try {
                System.out.println(swing.getElement("obj", Form.class).getValues());
            } catch (EmptyValueNotAllowedException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Form", JOptionPane.ERROR_MESSAGE);
            }
        });
        frame.setVisible(true);

    }
}
