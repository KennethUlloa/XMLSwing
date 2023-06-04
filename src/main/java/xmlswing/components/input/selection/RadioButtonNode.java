package xmlswing.components.input.selection;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.components.CustomButtonGroup;

import javax.swing.*;

public class RadioButtonNode extends AbstractNode<JRadioButton> {
    public RadioButtonNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public JRadioButton getRootElement() {
        JRadioButton radioButton = new JRadioButton();
        radioButton.setText(getNode().getTextContent().trim());
        if(hasAttribute("selected")) {
            radioButton.setSelected(getAttribute("selected").equals("true"));
        }
        if(hasAttribute("group")) {
            CustomButtonGroup group = getContext().getElement(getAttribute("group"), CustomButtonGroup.class);
            if(group == null) {
                group = new CustomButtonGroup();
                getContext().registerElement(getAttribute("group"), group);
            }
            group.add(radioButton);
        }
        return radioButton;
    }
}
