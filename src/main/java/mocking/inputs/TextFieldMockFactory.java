package mocking.inputs;

import mocking.JComponentMockFactory;
import mocking.MockFactory;
import mocking.MockNode;

import javax.swing.*;

public class TextFieldMockFactory extends JComponentMockFactory {

    @Override
    public JComponent buildComponent(MockNode node) {
        JTextField textField = new JTextField();
        //String attr = MockMaker.getAttributeValue(node, "cols");
        TextCommonProperties.setUpComponent(textField, node);
        if(node.getAttributes().containsKey("cols")) {
            try {
                textField.setColumns(Integer.parseInt(node.getAttribute("cols")));
            } catch (NumberFormatException ignored) {}
        }
        textField.setText(node.getNode().getTextContent().trim());
        return textField;
    }

    @Override
    public JComponent buildComponent(MockNode node, MockFactory mockMaker) {
        JComponent component = buildComponent(node);
        System.out.println("Mock");
        if(node.hasAttribute("id")) {
            mockMaker.register(node.getAttribute("id"), component);
        }
        return component;
    }
}
