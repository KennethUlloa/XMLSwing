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

/**
 * <h3>Properties</h3>
 * pos: (this is intended to be in the child nodes) <br>
 * <ul>
 *     <li>top</li>
 *     <li>left</li>
 *     <li>right</li>
 *     <li>bottom</li>
 *     <li>center</li>
 * </ul>
 */
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
                return panel;
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
                System.out.println(node.getAttribute("pos"));
                return null;
            }
        };
        ComponentRepository.registerNode(typeNode, container);
        PropertiesReader.setUpComponent(typeNode);
        return typeNode;
    }

    @Override
    public String getTagName() {
        return "Border";
    }
}
