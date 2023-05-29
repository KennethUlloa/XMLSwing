package xmlswing.components.panels.grid;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.repositories.NodeFactoryRepository;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;

import javax.swing.*;
import java.awt.*;

public class GridNode extends AbstractNode<JPanel> {
    public GridNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public void parseChildNode(JPanel parent, Node item) {
        TypeNodeFactory<Component, XMLSwing<?>> factory = NodeFactoryRepository.get(item.getNodeName());
        if(factory != null) {
            TypeNode<Component, XMLSwing<?>> childNode = factory.buildNode(item, getContext());
            parent.add(childNode.getObject());
        }
    }

    @Override
    public JPanel getParentElement() {
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
        return panel;
    }

}
