import org.xml.sax.SAXException;
import xmlswing.XMLSwing;

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
        XMLSwing swing = new XMLSwing(Main.class.getResourceAsStream("gui.xml"));
        swing.getVariableProcessor().set("year","2018");
        JFrame frame = swing.getRootComponent(JFrame.class);
        frame.setVisible(true);

    }
}
