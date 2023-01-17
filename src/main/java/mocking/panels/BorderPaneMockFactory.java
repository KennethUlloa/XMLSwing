package mocking.panels;

import mocking.JComponentMockFactory;
import mocking.MockNode;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;

public class BorderPaneMockFactory extends PanelMockFactory {
    @Override
    public JComponent buildComponent(MockNode node) {
        JPanel panel = new JPanel(new BorderLayout());
        addChildren(node.getNode(), panel);
        return panel;
    }

    @Override
    public void addChildren(Node node, JPanel panel) {
        for(int i = 0; i < node.getChildNodes().getLength() ; i++) {
            MockNode mockNode = new MockNode(node.getChildNodes().item(i));
            if(!mockNode.shouldIgnore()) {
                if(mockNode.getComponent() != null) {
                    panel.add(mockNode.getComponent(), BorderConstraintParser.fromNode(mockNode));
                }
            }
        }
    }
}
