package xmlswing.components.panels;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.repositories.NodeFactoryRepository;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.types.TypeRepository;

import javax.swing.*;
import java.awt.*;

public class ScrollPaneNode extends AbstractNode<JScrollPane> {
    public ScrollPaneNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public void parseChildNode(JScrollPane parent, Node item) {
    }

    @Override
    public boolean shouldParseChildren() {
        return false;
    }

    @Override
    public JScrollPane getRootElement() {
        JScrollPane scrollPane = new JScrollPane();
        if(hasAttribute("h-policy")) {
            int policy = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED;
            switch (getAttribute("h-policy")) {
                case "always": policy = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS; break;
                case "never": policy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER; break;
            }
            scrollPane.setHorizontalScrollBarPolicy(policy);
        }

        if(hasAttribute("v-policy")) {
            int policy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
            switch (getAttribute("v-policy")) {
                case "always": policy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS; break;
                case "never": policy = JScrollPane.VERTICAL_SCROLLBAR_NEVER; break;
            }
            scrollPane.setVerticalScrollBarPolicy(policy);
        }

        Node firstChild = firstTagChild();
        if (firstChild == null) {
            return scrollPane;
        }

        TypeNodeFactory<Component, XMLSwing<?>> factory = NodeFactoryRepository.get(firstChild.getNodeName());
        if(factory == null) {
            System.out.println(firstChild.getNodeName());
            return scrollPane;
        }

        TypeNode<Component, XMLSwing<?>> typeNode = factory.buildNode(firstChild, getContext());
        scrollPane.setViewportView(typeNode.getObject());

        return scrollPane;
    }
}
