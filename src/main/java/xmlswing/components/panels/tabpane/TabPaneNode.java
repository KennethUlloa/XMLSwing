package xmlswing.components.panels.tabpane;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.repositories.NodeFactoryRepository;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;

import javax.swing.*;
import java.awt.*;

public class TabPaneNode extends AbstractNode<JTabbedPane> {
    public TabPaneNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public void parseChildNode(JTabbedPane parent, Node item) {
        if (TypeNode.shouldIgnore(item)) {
            return;
        }
        if (!TypeNode.hasAttribute(item, "tabName")) {
            return;
        }
        TypeNodeFactory<Component, XMLSwing<?>> factory = NodeFactoryRepository.get(item.getNodeName());
        TypeNode<Component, XMLSwing<?>> typeNode = factory.buildNode(item, getContext());
        parent.addTab(TypeNode.getAttribute(item, "tabName"), typeNode.getObject());
    }

    @Override
    public JTabbedPane getRootElement() {
        return new JTabbedPane();
    }
}
