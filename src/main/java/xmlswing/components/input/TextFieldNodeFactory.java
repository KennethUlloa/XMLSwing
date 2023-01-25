package xmlswing.components.input;

import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import types.TypeNodeFactory;
import xmlswing.ComponentRepository;
import xmlswing.components.ComponentNode;

import javax.swing.*;
import java.awt.*;

public class TextFieldNodeFactory implements TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JTextField textField = new JTextField();
                TextCommonProperties.setUpComponent(textField, this);
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
        ComponentRepository.registerNode(typeNode, container);
        return typeNode;
    }
}
