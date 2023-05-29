package xmlswing.components.input;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.XMLSwing;
import xmlswing.components.PropertiesReader;
import xmlswing.components.ComponentNode;
import xmlswing.types.TypeNodeFactory;

import javax.swing.*;
import java.awt.*;

public class ButtonNodeFactory implements TypeNodeFactory<Component, XMLSwing> {
    @Override
    public TypeNode<Component> buildNode(Node node, XMLSwing container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JButton button = new JButton();
                button.setText(getNode().getTextContent().trim());
                return button;
            }
        };
        container.registerNode(typeNode);
        PropertiesReader.setUpComponent(typeNode);
        return typeNode;
    }

    @Override
    public String getTagName() {
        return "Button";
    }
}
