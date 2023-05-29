package xmlswing.components.input;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.XMLSwing;
import xmlswing.components.ComponentNode;

import javax.swing.*;
import java.awt.*;

/**
 * <h3>Properties</h3>
 * cols: number of columns to display
 */
public class TextFieldNodeFactory implements TypeNodeFactory<Component, XMLSwing> {
    @Override
    public TypeNode<Component> buildNode(Node node, XMLSwing container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JTextField textField = new JTextField();
                TextCommonProperties.setUpComponent(textField, this);
                textField.setText(getNode().getTextContent());
                if(hasAttribute("cols")) {
                    try {
                        textField.setColumns(Integer.parseInt(getAttribute("cols")));
                    } catch (NumberFormatException ignored) {}
                }

                return textField;
            }
        };
        /*
        if(container != null && componentTypeNode.hasAttribute("id")) {
            container.getRepository().register(componentTypeNode.getAttribute("id"), componentTypeNode.getObject());
        }*/
        container.registerNode(typeNode);
        return typeNode;
    }

    @Override
    public String getTagName() {
        return "TextField";
    }
}
