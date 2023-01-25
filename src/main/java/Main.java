import org.xml.sax.SAXException;
import xmlswing.XMLSwing;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        XMLSwing swing = new XMLSwing(Files.newInputStream(Paths.get("./demo.xml")));
        JFrame frame = (JFrame) swing.getRootComponent();
        frame.setVisible(true);





    }
}
