package mocking.inputs;

import mocking.ActionListenersForMock;
import mocking.JComponentMockFactory;
import mocking.MockFactory;
import mocking.MockNode;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ButtonMockFactory extends JComponentMockFactory {
    @Override
    public JComponent buildComponent(MockNode node) {
        JButton button = new JButton(node.getNode().getTextContent().trim());
        setActionListener(button, node);
        return button;
    }

    @Override
    public JComponent buildComponent(MockNode node, MockFactory mockMaker) {
        JComponent component = buildComponent(node);
        if(node.hasAttribute("id")) {
            mockMaker.register(node.getAttribute("id"), component);
        }
        return component;
    }

    private void setActionListener(JButton button, MockNode e) {
        if(e.getAttributes().containsKey("onclick")) return;
        ActionListener actionListener = ActionListenersForMock.getInstance().get(e.getAttributes().get("onclick"));
        if(actionListener == null) return;
        button.addActionListener(actionListener);
    }
}
