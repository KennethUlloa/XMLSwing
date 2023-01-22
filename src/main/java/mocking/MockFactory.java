package mocking;

import mocking.frames.FrameMock;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MockFactory {
    private InputStream stream;
    private HashMap<String, JComponent> components;
    private HashMap<String, ButtonGroup> buttonGroups;
    private HashMap<String, String> variables;
    public MockFactory(InputStream stream) {
        //this.path = path;
        this.stream = stream;
        components = new HashMap<>();
        buttonGroups = new HashMap<>();
        variables = new HashMap<>();
    }

    public void register(String string, JComponent component) {
        components.put(string, component);
    }

    public JPanel generatePanel() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(stream);
        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();
        MockNode mockNode = new MockNode(root);
        return  (JPanel) MockElementFactory.getInstance().getFactory(root.getNodeName()).buildComponent(mockNode, this);
    }

    public JFrame generateFrame() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(stream);
        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();
        FrameMock frameMock = new FrameMock(root);
        JFrame frame = frameMock.generate();

        for(int i = 0 ; i < root.getChildNodes().getLength() ; i++) {
            MockNode mockNode = new MockNode(root.getChildNodes().item(i));
            if(!mockNode.shouldIgnore()){
                JPanel panel = (JPanel) mockNode.getComponent(this);
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

    public JComponent getElement(String id) {
        return components.get(id);
    }
    public ButtonGroup getButtonGroup(String id) {return buttonGroups.get(id);}
    public void registerButtonGroup(String id, ButtonGroup group) {
        buttonGroups.put(id, group);
    }


    public String getVariable(String variable) {
        return variables.get(variable);
    }

    public void setVariable(String variable, String value) {
        variables.put(variable, value);
    }
    public void loadVariables() {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            StringBuilder builder = new StringBuilder();
            bufferedReader.lines().forEach(a -> {
                String line = a;
                for(String var : variables.keySet()) {
                    line = line.replace("{#"+var+"#}", variables.get(var));
                }
                builder.append(line).append('\n');
            });
            //System.out.println(builder);
            stream = new ByteArrayInputStream(builder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
