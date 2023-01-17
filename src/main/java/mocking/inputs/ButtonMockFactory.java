package mocking.inputs;

import mocking.ActionListenersForMock;
import mocking.JComponentMockFactory;
import mocking.MockMaker;
import mocking.MockNode;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ButtonMockFactory extends JComponentMockFactory {
    @Override
    public JComponent buildComponent(Node node) {
        JButton button = new JButton(node.getTextContent().trim());
        setActionListener(button, node);
        return button;
    }

    private void setActionListener(JButton button, Node e) {
        String property = MockMaker.getAttributeValue(e, "onclick");
        if(property == null) return;
        ActionListener actionListener = ActionListenersForMock.getInstance().get(property);
        if(actionListener == null) return;
        button.addActionListener(actionListener);
    }
}
