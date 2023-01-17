package mocking.panels;

import mocking.JComponentMockFactory;
import mocking.MockMaker;
import mocking.MockNode;
import org.w3c.dom.Node;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FlowPaneFactory extends PanelMockFactory {
    @Override
    public JComponent buildComponent(Node node) {
        System.out.println(node.getNodeName());
        JPanel panel = new JPanel(new FlowLayout());
        setAlignment(panel, node);
        addChildren(node, panel);
        return panel;
    }

    private void setAlignment(JPanel panel, Node node) {
        String property = MockMaker.getAttributeValue(node, "align");
        if(property == null) return;
        FlowLayout flowLayout = null;
        switch (property) {
            case "center": flowLayout = new FlowLayout(FlowLayout.CENTER); break;
            case "left": flowLayout = new FlowLayout(FlowLayout.LEFT); break;
            case "leading": flowLayout = new FlowLayout(FlowLayout.LEADING); break;
            case "trailing": flowLayout = new FlowLayout(FlowLayout.TRAILING); break;
            case "right": flowLayout = new FlowLayout(FlowLayout.RIGHT); break;
        }
        if(flowLayout != null) panel.setLayout(flowLayout);
    }

    @Override
    public void addChildren(Node node, JPanel panel) {
        for(int i = 0; i < node.getChildNodes().getLength() ; i++) {
            Node e = node.getChildNodes().item(i);
            if(!MockNode.shouldIgnore(e)) {
                MockNode mockNode = new MockNode(e);
                if(mockNode.getComponent() != null) {
                    panel.add(mockNode.getComponent());
                }
            }
        }
    }
}
