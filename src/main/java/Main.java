import mocking.MockFactory;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        MockFactory mockMaker = new MockFactory("./interface.xml");
        JFrame frame = mockMaker.generateFrame();
        //System.out.println("a: " + ((JTextField)mockMaker.search("text1")).getText());
        frame.setVisible(true);

    }
}
