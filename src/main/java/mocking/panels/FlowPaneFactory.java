package mocking.panels;

import mocking.MockFactory;
import mocking.MockNode;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;

public class FlowPaneFactory extends PanelMockFactory {
    @Override
    public JComponent buildComponent(MockNode node) {
        return buildComponent(node, null);
    }

    @Override
    public JComponent buildComponent(MockNode node, MockFactory mockMaker) {
        JPanel panel = new JPanel(new FlowLayout());
        setAlignment(panel, node);
        if(mockMaker == null){
            addChildren(node.getNode(), panel);
        }else {
            addChildren(node.getNode(), panel, mockMaker);
            if(node.hasAttribute("id")) {
                mockMaker.register(node.getAttribute("id"), panel);
            }
        }
        return panel;
    }

    private void setAlignment(JPanel panel, MockNode node) {
        //String property = MockMaker.getAttributeValue(node, "align");
        if(!node.hasAttribute("align")) return;
        FlowLayout flowLayout = null;
        switch (node.getAttribute("align")) {
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
            MockNode mock = new MockNode(node.getChildNodes().item(i));
            if(!mock.shouldIgnore()) {
                JComponent component = mock.getComponent(null);
                if(component != null) {
                    panel.add(component);
                }
            }
        }
    }

    @Override
    public void addChildren(Node node, JPanel panel, MockFactory mockMaker) {
        for(int i = 0; i < node.getChildNodes().getLength() ; i++) {
            MockNode mock = new MockNode(node.getChildNodes().item(i));
            if(!mock.shouldIgnore()) {
                JComponent component = mock.getComponent(mockMaker);
                if(component != null) {
                    panel.add(component);
                }
            }
        }
    }
}
