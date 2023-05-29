package xmlswing.components.panels.border;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.components.AbstractNode;
import xmlswing.repositories.NodeFactoryRepository;
import xmlswing.XMLSwing;

import javax.swing.*;
import java.awt.*;

public class BorderNode extends AbstractNode<JPanel> {
    public BorderNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public void parseChildNode(JPanel parent, Node item) {
        TypeNodeFactory<Component, XMLSwing<?>> factory = NodeFactoryRepository.get(item.getNodeName());
        if(factory != null) {
            TypeNode<Component, XMLSwing<?>> childNode = factory.buildNode(item, getContext());
            parent.add(childNode.getObject(), fromNode(childNode));
        }
    }

    @Override
    public JPanel getParentElement() {
        return new JPanel(new BorderLayout());
    }

    private String fromNode(TypeNode<Component, XMLSwing<?>> node) {
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
}
