package xmlswing.components.input;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.XMLSwing;
import xmlswing.components.PropertiesReader;
import xmlswing.components.ComponentNode;

import javax.swing.*;
import java.awt.*;

public class TextAreaFactory implements TypeNodeFactory<Component, XMLSwing> {
    @Override
    public TypeNode<Component> buildNode(Node node, XMLSwing container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JTextArea textArea = new JTextArea();
                textArea.setText(getNode().getTextContent().trim());

                TextCommonProperties.setUpComponent(textArea, this);
                return textArea;
            }
        };
        container.registerNode(typeNode);
        PropertiesReader.setUpComponent(typeNode);
        return typeNode;
    }

    @Override
    public String getTagName() {
        return "TextArea";
    }
}
