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
        XMLSwing<JDialog> swing = new XMLSwing<>(Main.class.getResourceAsStream("dialog.xml"));
        JDialog dialog = swing.getRootComponent();
        swing.getElement("guardar2", JButton.class).addActionListener(a -> {
            try {
                System.out.println(swing.getElement("obj2", Form.class).getValues());
            } catch (EmptyValueNotAllowedException e) {
                throw new RuntimeException(e);
            }
        });

        swing.getElement("cancelar2", JButton.class).addActionListener(a -> {
            swing.getElement("obj2", Form.class).clear();
        });


        XMLSwing<JFrame> xmlSwing = new XMLSwing<>(Main.class.getResourceAsStream("gui.xml"));
        JFrame frame = xmlSwing.getRootComponent();

        xmlSwing.getElement("cancelar", JButton.class).addActionListener(a -> {
            dialog.setVisible(true);
            System.out.println("Dialog!!!");
        });

        frame.setVisible(true);

    }
}
