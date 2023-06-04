package xmlswing.components.input.button;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;

import javax.swing.*;

public class ButtonNode extends AbstractNode<JButton> {
    public ButtonNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public boolean shouldParseChildren() {
        return false;
    }

    @Override
    public JButton getRootElement() {
        JButton button = new JButton();
        button.setText(getNode().getTextContent().trim());
        return button;
    }
}
