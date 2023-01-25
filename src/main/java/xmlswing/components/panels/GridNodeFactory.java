package xmlswing.components.panels;

import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import types.TypeNodeFactory;
import xmlswing.ComponentRepository;
import xmlswing.components.ComponentNode;
import xmlswing.components.PropertiesReader;

import javax.swing.*;
import java.awt.*;

public class GridNodeFactory implements types.TypeNodeFactory<java.awt.Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JPanel panel = new JPanel();
                GridLayout layout = new GridLayout();
                panel.setLayout(layout);
                if(hasAttribute("rows")) {
                    layout.setRows(Integer.parseInt(getAttribute("rows")));
                }
                if(hasAttribute("columns")) {
                    layout.setColumns(Integer.parseInt(getAttribute("columns")));
                }
                if(hasAttribute("h-gap")) {
                    layout.setHgap(Integer.parseInt(getAttribute("h-gap")));
                }
                if(hasAttribute("v-gap")) {
                    layout.setVgap(Integer.parseInt(getAttribute("v-gap")));
                }

                for(int i = 0 ; i < getNode().getChildNodes().getLength() ; i++) {
                    Node child = getNode().getChildNodes().item(i);
                    TypeNodeFactory<Component> factory = getContainer().getFactory(child.getNodeName());
                    if(factory != null) {
                        TypeNode<Component> childNode = factory.buildNode(child, getContainer());
                        panel.add(childNode.getObject());
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
