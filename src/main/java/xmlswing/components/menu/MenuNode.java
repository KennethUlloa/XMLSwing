package xmlswing.components.menu;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.repositories.NodeFactoryRepository;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;

import javax.swing.*;
import java.awt.*;

public class MenuNode extends AbstractNode<JMenu> {
    public MenuNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public void parseChildNode(JMenu parent, Node item) {
        TypeNodeFactory<Component, XMLSwing<?>> factory = NodeFactoryRepository.get(item.getNodeName());
        if(factory == null) return;
        TypeNode<Component, XMLSwing<?>> component = factory.buildNode(item, getContext());
        if(component.getObject() instanceof JMenuItem) {
            parent.add(component.getObject());
        }
    }

    @Override
    public JMenu getRootElement() {
        JMenu menu = new JMenu();
        if(hasAttribute("title")) {
            menu.setText(getAttribute("title"));
        }
        return menu;
    }
}
