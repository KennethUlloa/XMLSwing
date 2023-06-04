package xmlswing.components.panels.flow;

import org.w3c.dom.Node;
import xmlswing.types.TypeNodeFactory;
import xmlswing.repositories.NodeFactoryRepository;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;

import javax.swing.*;
import java.awt.*;

public class FlowNode extends AbstractNode<JPanel> {
    public FlowNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public void parseChildNode(JPanel parent, Node item) {
        TypeNodeFactory<Component, XMLSwing<?>> factory = NodeFactoryRepository.get(item.getNodeName());
        if(factory != null) {
            Component component = factory.buildNode(item, getContext()).getObject();
            if(component != null) {
                parent.add(component);
            }
        }
    }

    @Override
    public JPanel getRootElement() {
        JPanel panel = new JPanel();
        if(hasAttribute("layout")) {
            switch (getAttribute("layout")) {
                case "center": panel.setLayout(new FlowLayout(FlowLayout.CENTER)); break;
                case "left": panel.setLayout(new FlowLayout(FlowLayout.LEFT)); break;
                case "right": panel.setLayout(new FlowLayout(FlowLayout.RIGHT)); break;
                case "leading": panel.setLayout(new FlowLayout(FlowLayout.LEADING)); break;
                case "trailing": panel.setLayout(new FlowLayout(FlowLayout.TRAILING)); break;
            }
        }
        return panel;
    }
}
