package mocking.panels;

import mocking.MockFactory;
import mocking.MockNode;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;

public class BorderPaneMockFactory extends PanelMockFactory {
    @Override
    public JComponent buildComponent(MockNode node) {

        return buildComponent(node, null);
    }

    @Override
    public JComponent buildComponent(MockNode node, MockFactory mockMaker) {
        JPanel panel = new JPanel(new BorderLayout());

        if(mockMaker != null) {
            if(node.hasAttribute("id")){
                mockMaker.register(node.getAttribute("id"), panel);
            }
            addChildren(node.getNode(), panel, mockMaker);
        }else {
            addChildren(node.getNode(), panel);
        }
        return panel;
    }

    @Override
    public void addChildren(Node node, JPanel panel) {
        for(int i = 0; i < node.getChildNodes().getLength() ; i++) {
            MockNode mockNode = new MockNode(node.getChildNodes().item(i));
            if(!mockNode.shouldIgnore()) {
                if(mockNode.getComponent(null) != null) {
                    panel.add(mockNode.getComponent(null), BorderConstraintParser.fromNode(mockNode));
                }
            }
        }
    }

    @Override
    public void addChildren(Node node, JPanel panel, MockFactory mockMaker) {
        for(int i = 0; i < node.getChildNodes().getLength() ; i++) {
            MockNode mockNode = new MockNode(node.getChildNodes().item(i));
            if(!mockNode.shouldIgnore()) {
                if(mockNode.getComponent(mockMaker) != null) {
                    panel.add(mockNode.getComponent(mockMaker), BorderConstraintParser.fromNode(mockNode));
                }
            }
        }
    }
}
