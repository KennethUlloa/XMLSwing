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
        attributes = MockNode.getAttributes(node);
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public boolean isTextOrComment() {
        return node.getNodeName().equals("#text") || node.getNodeName().equals("#comment");
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

    public JComponent getComponent() {
        return component;
    }

    private void buildNode(Node e) {
        JComponentMockFactory componentMockFactory = MockElementFactory.getInstance().getFactory(e.getNodeName());
        if(componentMockFactory != null){
            component = componentMockFactory.buildComponent(e);
            CommonProperties.setUpComponent(component, e);
            if(useConstraints) {constraints = ConstraintParser.fromNode(e);}
        }
    }
    public static boolean shouldIgnore(Node node) {
        return node.getNodeName().equals("#text") || node.getNodeName().equals("#comment");
    }

    public static HashMap<String, String> getAttributes(Node node) {
        HashMap<String, String> attributes = new HashMap<>();
        for(int i = 0; i < node.getAttributes().getLength() ; i++) {
            Node e = node.getAttributes().item(i);
            attributes.put(e.getNodeName(), e.getNodeValue());
        }
        return attributes;
    }


}
