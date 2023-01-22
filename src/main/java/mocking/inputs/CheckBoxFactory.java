package mocking.inputs;

import mocking.JComponentMockFactory;
import mocking.MockFactory;
import mocking.MockNode;

import javax.swing.*;

public class CheckBoxFactory extends JComponentMockFactory {
    @Override
    public JComponent buildComponent(MockNode node) {
        return null;
    }

    @Override
    public JComponent buildComponent(MockNode node, MockFactory mockMaker) {
        JCheckBox checkBox = new JCheckBox();
        checkBox.setText(node.getNode().getTextContent().trim());
        if(node.hasAttribute("selected")) {
            checkBox.setSelected(true);
        }
        if(mockMaker != null) {
            if(node.hasAttribute("id")) {
                mockMaker.register(node.getAttribute("id"), checkBox);
            }
        }
        return checkBox;
    }
}
