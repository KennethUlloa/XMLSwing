package mocking.inputs;

import mocking.JComponentMockFactory;
import mocking.MockMaker;
import org.w3c.dom.Node;

import javax.swing.*;

public class InputMockFactory extends JComponentMockFactory {

    @Override
    public JComponent buildComponent(Node node) {
        JTextField textField = new JTextField();
        String attr = MockMaker.getAttributeValue(node, "cols");
        if(attr != null) {
            textField.setColumns(Integer.parseInt(attr));
        }

        return textField;
    }
}
