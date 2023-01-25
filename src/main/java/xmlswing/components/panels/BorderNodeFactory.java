package xmlswing.components.panels;

import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import types.TypeNodeFactory;
import xmlswing.components.ComponentNode;

import javax.swing.*;
import java.awt.*;

public class BorderNodeFactory implements types.TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JPanel panel = new JPanel(new BorderLayout());
                for(int i = 0; i < getNode().getChildNodes().getLength() ; i++) {
//                    MockNode mockNode = new MockNode(node.getChildNodes().item(i));
                    Node child = getNode().getChildNodes().item(i);
                    TypeNodeFactory<Component> factory = getContainer().getFactory(child.getNodeName());
                    if(factory != null) {
                        TypeNode<Component> childNode = factory.buildNode(child, getContainer());
                        panel.add(childNode.getObject(), fromNode(childNode));
                    }
                }
                return null;
            }

            private String fromNode(TypeNode<Component> node) {
                if(node.hasAttribute("pos")) {
                    switch (node.getAttribute("pos")) {
                        case "top": return BorderLayout.NORTH;
                        case "left": return BorderLayout.WEST;
                        case "right": return BorderLayout.EAST;
                        case "bottom": return BorderLayout.SOUTH;
                        case "center": return BorderLayout.CENTER;
                    }
                }
                return null;
            }
        };
        return null;
    }
}