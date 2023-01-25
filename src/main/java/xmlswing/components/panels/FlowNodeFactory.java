package xmlswing.components.panels;

import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import types.TypeNodeFactory;
import xmlswing.ComponentRepository;
import xmlswing.components.PropertiesReader;
import xmlswing.components.ComponentNode;

import javax.swing.*;
import java.awt.*;

public class FlowNodeFactory implements TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JPanel panel = new JPanel();
                for(int i = 0; i < getNode().getChildNodes().getLength() ; i++) {
                    Node child = getNode().getChildNodes().item(i);
                    TypeNodeFactory<Component> factory = getContainer().getFactory(child.getNodeName());
                    if(factory != null) {
                        Component component = factory.buildNode(child, getContainer()).getObject();
                        if(component != null) {
                            panel.add(component);
                        }
                    }
                }
                return panel;
            }
        };
        PropertiesReader.setUpComponent(typeNode);
        ComponentRepository.registerNode(typeNode, container);
        return typeNode;
    }
}
