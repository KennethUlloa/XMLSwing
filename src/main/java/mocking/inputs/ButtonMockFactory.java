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
    public JComponent buildComponent(MockNode node) {
        JButton button = new JButton(node.getNode().getTextContent().trim());
        setActionListener(button, node);
        return button;
    }

    private void setActionListener(JButton button, MockNode e) {
        if(e.getAttributes().containsKey("onclick")) return;
        ActionListener actionListener = ActionListenersForMock.getInstance().get(e.getAttributes().get("onclick"));
        if(actionListener == null) return;
        button.addActionListener(actionListener);
    }
}
