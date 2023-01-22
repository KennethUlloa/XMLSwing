package mocking.inputs;

import mocking.JComponentMockFactory;
import mocking.MockFactory;
import mocking.MockNode;

import javax.swing.*;

public class TextAreaMockFactory extends JComponentMockFactory {
    @Override
    public JComponent buildComponent(MockNode node) {
        JTextArea textArea = new JTextArea();
        textArea.setText(node.getNode().getTextContent().trim());
        TextCommonProperties.setUpComponent(textArea, node);
        return textArea;
    }

    @Override
    public JComponent buildComponent(MockNode node, MockFactory mockMaker) {
        JComponent component = buildComponent(node);
        if(node.hasAttribute("id")) {
            mockMaker.register(node.getAttribute("id"), component);
        }
        return component;
    }
}
