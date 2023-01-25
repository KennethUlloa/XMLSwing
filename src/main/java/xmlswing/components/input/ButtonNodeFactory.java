package xmlswing.components.input;

import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import xmlswing.ComponentRepository;
import xmlswing.components.PropertiesReader;
import xmlswing.components.ComponentNode;

import javax.swing.*;
import java.awt.*;

public class ButtonNodeFactory implements types.TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JButton button = new JButton();
                button.setText(getNode().getTextContent().trim());
                return button;
            }
        };
        /*if(container != null && typeNode.hasAttribute("id")) {
            container.getRepository().register(typeNode.getAttribute("id"), typeNode.getObject());
        }*/
        ComponentRepository.registerNode(typeNode, container);
        PropertiesReader.setUpComponent(typeNode);
        return typeNode;
    }
}
