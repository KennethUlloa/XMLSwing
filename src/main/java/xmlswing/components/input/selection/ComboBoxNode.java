package xmlswing.components.input.selection;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;

import javax.swing.*;

public class ComboBoxNode extends AbstractNode<JComboBox<Object>> {
    public ComboBoxNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public void parseChildNode(JComboBox<Object> parent, Node item) {
        if(item.getNodeName().equals("Option"))
            parent.addItem(item.getTextContent().trim());
    }

    @Override
    public JComboBox<Object> getRootElement() {
        return new JComboBox<>();
    }
}
