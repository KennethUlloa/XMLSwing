package mocking.inputs;

import mocking.JComponentMockFactory;
import mocking.MockFactory;
import mocking.MockNode;
import org.w3c.dom.Node;

import javax.swing.*;

public class ComboBoxFactory extends JComponentMockFactory {
    @Override
    public JComponent buildComponent(MockNode node) {
        return null;
    }

    @Override
    public JComponent buildComponent(MockNode node, MockFactory mockMaker) {
        JComboBox<Object> comboBox = new JComboBox<>();
        if(mockMaker != null) {
            if(node.hasAttribute("id")) {
                mockMaker.register(node.getAttribute("id"), comboBox);
            }
        }
        addOptions(node.getNode(), comboBox);
        return comboBox;
    }

    private void addOptions(Node node, JComboBox<Object> comboBox) {
        for(int i = 0 ; i < node.getChildNodes().getLength() ; i++) {
            if(node.getChildNodes().item(i).getNodeName().equals("Option")) {
                System.out.println(node.getChildNodes().item(i).getNodeValue());
                comboBox.addItem(node.getChildNodes().item(i).getTextContent().trim());
            }
        }
    }
}
