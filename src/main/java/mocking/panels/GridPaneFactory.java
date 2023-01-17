package mocking.panels;

import mocking.JComponentMockFactory;
import mocking.MockMaker;
import mocking.MockNode;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GridPaneFactory extends PanelMockFactory {
    @Override
    public JComponent buildComponent(MockNode node) {
        JPanel panel = new JPanel(new GridLayout());
        //HashMap<String, String > attr = MockNode.getAttributes(node);

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

        addChildren(node.getNode(), panel);
        System.out.println(panel);
        return panel;
    }

    @Override
    public void addChildren(Node node, JPanel panel) {
        for(int i = 0; i < node.getChildNodes().getLength() ; i++) {
            MockNode mockNode = new MockNode(node.getChildNodes().item(i));
            if(!mockNode.shouldIgnore()) {
                if(mockNode.getComponent() != null) {
                    panel.add(mockNode.getComponent());
                }
            }
        }
    }
}
