package xmlswing.components.input.selection;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.components.CustomButtonGroup;
import xmlswing.components.NodeFormEntry;
import xmlswing.components.form.BasicForm;
import xmlswing.components.form.Form;
import xmlswing.components.form.FormEntry;

import javax.swing.*;
import java.awt.*;

public class RadioButtonNode extends AbstractNode<JRadioButton> {
    public RadioButtonNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public JRadioButton getRootElement() {
        JRadioButton radioButton = new JRadioButton();
        radioButton.setText(getNode().getTextContent().trim());
        radioButton.setSelected(hasAttribute("selected"));
        return radioButton;
    }

    @Override
    public FormEntry<JRadioButton> asFormEntry() {
        return new NodeFormEntry<JRadioButton>(this) {
            @Override
            public void clearValue() {

            }

            @Override
            public Object getRawValue() {
                if(hasAttribute("value")) {
                    return getAttribute("value");
                } else{
                    return element.getText();
                }
            }
        };
    }
}
