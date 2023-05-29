package xmlswing.components.input;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.XMLSwing;
import xmlswing.components.PropertiesReader;
import xmlswing.components.ComponentNode;

import javax.swing.*;
import java.awt.*;

/**
 * <h3>Properties</h3>
 * selected:
 * <ul>
 *     <li>true</li>
 *     <li>false (or anything else)</li>
 * </ul>
 */
public class CheckBoxNodeFactory implements TypeNodeFactory<Component, XMLSwing> {
    @Override
    public TypeNode<Component> buildNode(Node node, XMLSwing container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setText(getNode().getTextContent().trim());
                if(hasAttribute("selected")) {
                    checkBox.setSelected(getAttribute("selected").equals("true"));
                }
                return checkBox;
            }
        };
        PropertiesReader.setUpComponent(typeNode);
        container.registerNode(typeNode);
        return typeNode;
    }

    @Override
    public String getTagName() {
        return "CheckBox";
    }
}
