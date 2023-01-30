package xmlswing.components.input;

import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import types.TypeNodeFactory;
import xmlswing.ComponentRepository;
import xmlswing.components.PropertiesReader;
import xmlswing.components.ComponentNode;

import javax.swing.*;
import java.awt.*;

public class TextAreaFactory implements TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JTextArea textArea = new JTextArea();
                textArea.setText(getNode().getTextContent().trim());
                TextCommonProperties.setUpComponent(textArea, this);
                return textArea;
            }
        };
        ComponentRepository.registerNode(typeNode, container);
        PropertiesReader.setUpComponent(typeNode);
        return typeNode;
    }

    @Override
    public String getTagName() {
        return "TextArea";
    }
}
