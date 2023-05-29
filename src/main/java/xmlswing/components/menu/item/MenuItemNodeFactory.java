package xmlswing.components.menu.item;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNodeFactory;
import xmlswing.types.TypeNode;

import java.awt.*;

public class MenuItemNodeFactory extends AbstractNodeFactory {
    @Override
    public TypeNode<Component, XMLSwing<?>> nodeBuild(Node node, XMLSwing<?> context) {
        return new MenuItemNode(node, context);
    }

    @Override
    public String getTagName() {
        return "MenuItem";
    }
}
