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

public class MenuBarNodeFactory implements TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JMenuBar menuBar = new JMenuBar();
                for(int i = 0 ; i < getNode().getChildNodes().getLength() ; i++) {
                    Node child = getNode().getChildNodes().item(i);
                    if(getContainer().getFactory(child.getNodeName()) != null) {
                        Component component = getContainer().getFactory(child.getNodeName())
                                .buildNode(child, getContainer()).getObject();
                        if(component instanceof JMenu) {
                            menuBar.add((JMenu)component);
                        }
                    }
                }
                return menuBar;
            }
        };
        PropertiesReader.setUpComponent(typeNode);
        ComponentRepository.registerNode(typeNode,container);
        return typeNode;
    }

    @Override
    public String getTagName() {
        return "MenuBar";
    }
}
