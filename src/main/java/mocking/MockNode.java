package mocking;

import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MockNode {
    private GridBagConstraints constraints;
    private JComponent component;
    private boolean useConstraints;
    private HashMap<String, String> attributes;
    private Node node;
    public MockNode(Node node) {
        attributes = getAttributes(node);
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public String getAttribute(String attr) {
        return attributes.get(attr);
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public GridBagConstraints getConstraints() {

        return constraints;
    }

    public JComponent getComponent(MockFactory mockMaker) {
        if(component == null){
            component = buildNode(mockMaker);
        }
        return component;
    }

    /*public JComponent getComponent() {
        if(component == null){
            component = buildNode();
        }

        return component;
    }*/

    private JComponent buildNode(MockFactory mockMaker) {
        JComponentMockFactory componentMockFactory = MockElementFactory.getInstance().getFactory(node.getNodeName());
        if (componentMockFactory == null) return null;
        JComponent component;
        if(mockMaker == null) {
            component = componentMockFactory.buildComponent(this);
        }else {
            component = componentMockFactory.buildComponent(this, mockMaker);
        }
        CommonProperties.setUpComponent(component, node);
        return component;
    }
    public boolean shouldIgnore() {
        return node.getNodeName().equals("#text") || node.getNodeName().equals("#comment");
    }
    public static boolean shouldIgnore(Node node) {
        return node.getNodeName().equals("#text") || node.getNodeName().equals("#comment");
    }

    public boolean hasAttribute(String attribute) {
        return attributes.containsKey(attribute);
    }

    private HashMap<String, String> getAttributes(Node node) {
        HashMap<String, String> attributes = new HashMap<>();
        if(node.getAttributes() == null) return attributes;
        for(int i = 0; i < node.getAttributes().getLength() ; i++) {
            Node e = node.getAttributes().item(i);
            attributes.put(e.getNodeName(), e.getNodeValue());
        }
        HashMap<String, String> styles = CommonProperties.readStyle(node);
        for(String key: styles.keySet()) {
            attributes.put(key, styles.get(key));
        }
        return attributes;
    }


}
