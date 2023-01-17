package mocking.labels;

import mocking.JComponentMockFactory;
import mocking.MockMaker;
import mocking.MockNode;
import org.w3c.dom.Node;

import javax.swing.*;
import java.util.HashMap;

public class LabelMockFactory extends JComponentMockFactory {


    @Override
    public JComponent buildComponent(Node node) {
        JLabel label = new JLabel();
        setHAlignment(label, node);
        setVAlignment(label, node);
        label.setText(node.getTextContent().trim());
        return label;
    }

    private void setHAlignment(JLabel label, Node node) {
        String property = MockMaker.getAttributeValue(node, "h-align");
        if(property == null) return;
        switch (property) {
            case "right": label.setHorizontalAlignment(JLabel.RIGHT); break;
            case "center": label.setHorizontalAlignment(JLabel.CENTER); break;
            case "left": label.setHorizontalAlignment(JLabel.LEFT); break;
        }
    }

    private void setVAlignment(JLabel label, Node node) {
        String property = MockMaker.getAttributeValue(node, "v-align");
        if(property == null) return;
        switch (property) {
            case "top": label.setVerticalAlignment(JLabel.TOP); break;
            case "center": label.setVerticalAlignment(JLabel.CENTER); break;
            case "bottom": label.setVerticalAlignment(JLabel.BOTTOM); break;
        }
    }
}
