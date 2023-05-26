package xmlswing.components.panels;

import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import types.TypeNodeFactory;
import xmlswing.ComponentRepository;
import xmlswing.FactoryRepository;
import xmlswing.components.ComponentNode;
import xmlswing.components.PropertiesReader;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Type;
import java.util.stream.Stream;

/**
 * <h3>Properties</h3>
 * tabName: determines whether a node should be included in the tab or not.
 */
public class TabbedPaneFactory implements TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JTabbedPane tabbedPane = new JTabbedPane();
                for(int i = 0; i < getNode().getChildNodes().getLength() ; i++) {
                    Node node = getNode().getChildNodes().item(i);
                    if (TypeNode.shouldIgnore(node)) {
                        continue;
                    }
                    if (!TypeNode.hasAttribute(node, "tabName")) {
                        continue;
                    }

                    TypeNodeFactory<Component> factory = getContainer().getFactory(node.getNodeName());
                    TypeNode<Component> typeNode1 = factory.buildNode(node, getContainer());
                    tabbedPane.addTab(TypeNode.getAttribute(node, "tabName"), typeNode1.parseObject());

                }
                return tabbedPane;
            }
        };
        ComponentRepository.registerNode(typeNode, container);
        PropertiesReader.setUpComponent(typeNode);
        return typeNode;
    }
    @Override
    public String getTagName() {
        return "TabPane";
    }
}