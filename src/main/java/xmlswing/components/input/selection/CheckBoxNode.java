package xmlswing.components.input.selection;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;

import javax.swing.*;

public class CheckBoxNode extends AbstractNode<JCheckBox> {
    public CheckBoxNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public JCheckBox getRootElement() {
        JCheckBox checkBox = new JCheckBox();
        checkBox.setText(getNode().getTextContent().trim());
        if(hasAttribute("selected")) {
            checkBox.setSelected(getAttribute("selected").equals("true"));
        }
        return checkBox;
    }
}
