package mocking.inputs;

import mocking.JComponentMockFactory;
import mocking.MockFactory;
import mocking.MockNode;

import javax.swing.*;

public class RadioButtonFactory extends JComponentMockFactory {
    @Override
    public JComponent buildComponent(MockNode node) {
        return null;
    }

    @Override
    public JComponent buildComponent(MockNode node, MockFactory mockMaker) {
        JRadioButton radioButton = new JRadioButton();
        radioButton.setText(node.getNode().getTextContent().trim());
        if(node.hasAttribute("selected")) {
            radioButton.setSelected(true);
        }

        if(mockMaker != null) {
            if(node.hasAttribute("group")) {
                ButtonGroup group = mockMaker.getButtonGroup(node.getAttribute("group"));
                if(group == null) {
                    group = new ButtonGroup();
                    mockMaker.registerButtonGroup(node.getAttribute("group"), group);
                }
                    group.add(radioButton);

            }
            if(node.hasAttribute("id")) {
                mockMaker.register(node.getAttribute("id"), radioButton);
            }
        }
        return radioButton;
    }
}
