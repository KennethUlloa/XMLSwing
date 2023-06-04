package xmlswing.components.input.text;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.components.NodeFormEntry;
import xmlswing.components.form.FormEntry;
import xmlswing.components.props.TextElementProperties;

import javax.swing.*;

public class TextFieldNode extends AbstractNode<JTextField> {
    public TextFieldNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public boolean shouldParseChildren() {
        return false;
    }

    @Override
    public JTextField getRootElement() {
        JTextField textArea = new JTextField();
        textArea.setText(getNode().getTextContent().trim());
        TextElementProperties.setUpComponent(textArea, this);
        return textArea;
    }

    @Override
    public FormEntry<JTextField> asFormEntry() {
        return new NodeFormEntry<JTextField>(this) {
            @Override
            public Object getRawValue() {
                return element.getText();
            }
        };
    }
}
