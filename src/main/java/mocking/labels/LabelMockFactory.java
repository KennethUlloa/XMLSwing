package mocking.labels;

import mocking.JComponentMockFactory;
import mocking.MockMaker;
import mocking.MockNode;
import org.w3c.dom.Node;

import javax.swing.*;
import java.util.HashMap;

public class LabelMockFactory extends JComponentMockFactory {


    @Override
    public JComponent buildComponent(MockNode node) {
        JLabel label = new JLabel();
        setHAlignment(label, node);
        setVAlignment(label, node);
        //System.out.println(node.getNode().);
        label.setText(node.getNode().getTextContent().trim());
        return label;
    }

    private void setHAlignment(JLabel label, MockNode node) {
        //String property = MockMaker.getAttributeValue(node, "h-align");
        if(!node.hasAttribute("h-align")) return;
        switch (node.getAttribute("h-align")) {
            case "right": label.setHorizontalAlignment(JLabel.RIGHT); break;
            case "center": label.setHorizontalAlignment(JLabel.CENTER); break;
            case "left": label.setHorizontalAlignment(JLabel.LEFT); break;
        }
    }

    private void setVAlignment(JLabel label, MockNode node) {
        if(!node.hasAttribute("v-align")) return;
        switch (node.getAttribute("v-align")) {
            case "top": label.setVerticalAlignment(JLabel.TOP); break;
            case "center": label.setVerticalAlignment(JLabel.CENTER); break;
            case "bottom": label.setVerticalAlignment(JLabel.BOTTOM); break;
        }
    }
}
