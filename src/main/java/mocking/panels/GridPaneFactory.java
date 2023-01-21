package mocking.panels;

import mocking.MockFactory;
import mocking.MockNode;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;

public class GridPaneFactory extends PanelMockFactory {
    @Override
    public JComponent buildComponent(MockNode node, MockFactory mockMaker) {
        JPanel panel = new JPanel(new GridLayout());
        if(node.hasAttribute("layout")) {
            String[] values = node.getAttribute("layout").trim().split(" ");
            if(values.length == 2) {
                try {
                    panel.setLayout(new GridLayout(Integer.parseInt(values[0]), Integer.parseInt(values[1])));
                } catch (NumberFormatException ignored) {}
            }
            if(values.length == 4) {
                try {
                    panel.setLayout(new GridLayout(Integer.parseInt(values[0]), Integer.parseInt(values[1]),
                            Integer.parseInt(values[2]), Integer.parseInt(values[3])));
                } catch (NumberFormatException ignored) {}
            }
        }
        if(mockMaker != null){
            if(node.hasAttribute("id")) {
                mockMaker.register(node.getAttribute("id"), panel);
            }
            addChildren(node.getNode(), panel, mockMaker);
            return panel;
        }
        addChildren(node.getNode(), panel);
        //System.out.println(panel);
        return panel;
    }

    @Override
    public JComponent buildComponent(MockNode node) {
        return buildComponent(node, null);
    }

    @Override
    public void addChildren(Node node, JPanel panel) {
        for(int i = 0; i < node.getChildNodes().getLength() ; i++) {
            MockNode mockNode = new MockNode(node.getChildNodes().item(i));
            if(!mockNode.shouldIgnore()) {
                if(mockNode.getComponent(null) != null) {
                    panel.add(mockNode.getComponent(null));
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
                    panel.add(mockNode.getComponent(mockMaker));
                }
            }
        }
    }
}
