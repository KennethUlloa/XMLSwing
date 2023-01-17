package mocking;

import mocking.frames.FrameMock;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class MockMaker {
    private String path;
    public MockMaker(String path) {
        this.path = path;
    }

    public JPanel generatePanel() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(path));
        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();
        MockNode mockNode = new MockNode(root);
        return  (JPanel) MockElementFactory.getInstance().getFactory(root.getNodeName()).buildComponent(mockNode);
    }

    public JFrame generateFrame() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(path));
        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();
        FrameMock frameMock = new FrameMock(root);
        JFrame frame = frameMock.generate();

        for(int i = 0 ; i < root.getChildNodes().getLength() ; i++) {
            MockNode mockNode = new MockNode(root.getChildNodes().item(i));
            if(!mockNode.shouldIgnore()){
                JPanel panel = (JPanel) mockNode.getComponent();
                frame.setContentPane(panel);
                break;
            }
        }

        return frame;
    }


    public static String getAttributeValue(Node e, String attribute) {
        if(e == null) return null;
        if (e.getAttributes() == null) return null;
        if(e.getAttributes().getNamedItem(attribute) == null) return null;
        return e.getAttributes().getNamedItem(attribute).getNodeValue();
    }
}
