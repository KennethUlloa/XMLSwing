import html.HTMLView;
import mocking.ActionListenersForMock;
import mocking.MockMaker;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        MockMaker mockMaker = new MockMaker("./interface.xml");
        mockMaker.generateFrame().setVisible(true);

    }
}
