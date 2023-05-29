package xmlswing.components.input;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.XMLSwing;
import xmlswing.components.ComponentNode;
import xmlswing.components.PropertiesReader;

import javax.swing.*;
import java.awt.*;

public class MenuItemNodeFactory implements TypeNodeFactory<Component, XMLSwing> {
    @Override
    public TypeNode<Component> buildNode(Node node, XMLSwing container) {
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
        container.registerNode(typeNode);
        PropertiesReader.setUpComponent(typeNode);
        return typeNode;
    }

    @Override
    public String getTagName() {
        return "MenuItem";
    }
}
