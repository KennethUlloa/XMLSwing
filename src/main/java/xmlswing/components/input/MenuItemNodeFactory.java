package xmlswing.components.input;

import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import types.TypeNodeFactory;
import xmlswing.ComponentRepository;
import xmlswing.components.ComponentNode;
import xmlswing.components.PropertiesReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

public class MenuItemNodeFactory implements TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JMenuItem menuItem = new JMenuItem();
                if(hasAttribute("title")) {
                    menuItem.setText(getAttribute("title"));
                }
                return menuItem;
            }
        };
        ComponentRepository.registerNode(typeNode, container);
        PropertiesReader.setUpComponent(typeNode);
        return typeNode;
    }

    @Override
    public String getTagName() {
        return "MenuItem";
    }
}
