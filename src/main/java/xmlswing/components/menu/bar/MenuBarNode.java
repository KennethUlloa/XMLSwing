package xmlswing.components.menu.bar;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.repositories.NodeFactoryRepository;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;

import javax.swing.*;
import java.awt.*;

public class MenuBarNode extends AbstractNode<JMenuBar> {
    public MenuBarNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public void parseChildNode(JMenuBar parent, Node item) {
        TypeNodeFactory<Component, XMLSwing<?>> factory = NodeFactoryRepository.get(item.getNodeName());
        if(factory == null) return;
        TypeNode<Component, XMLSwing<?>> component = factory.buildNode(item, getContext());
        if(component.getObject() instanceof JMenu) {
            parent.add((JMenu) component.getObject());
        }

    }

    @Override
    public JMenuBar getParentElement() {
        return new JMenuBar();
    }
}
