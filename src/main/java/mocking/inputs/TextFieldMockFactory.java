package mocking.inputs;

import mocking.JComponentMockFactory;
import mocking.MockMaker;
import mocking.MockNode;
import org.w3c.dom.Node;

import javax.swing.*;

public class TextFieldMockFactory extends JComponentMockFactory {

    @Override
    public JComponent buildComponent(MockNode node) {
        JTextField textField = new JTextField();
        //String attr = MockMaker.getAttributeValue(node, "cols");
        if(node.getAttributes().containsKey("cols")) {
            try {
                textField.setColumns(Integer.parseInt(node.getAttribute("cols")));
            } catch (NumberFormatException ignored) {}
        }

        return textField;
    }
}