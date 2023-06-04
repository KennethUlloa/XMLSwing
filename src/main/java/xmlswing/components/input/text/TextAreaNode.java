package xmlswing.components.input.text;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.components.props.TextElementProperties;

import javax.swing.*;

public class TextAreaNode extends AbstractNode<JTextArea> {
    public TextAreaNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public JTextArea getRootElement() {
        JTextArea textArea = new JTextArea();
        textArea.setText(getNode().getTextContent().trim());
        TextElementProperties.setUpComponent(textArea, this);
        return textArea;
    }
}
