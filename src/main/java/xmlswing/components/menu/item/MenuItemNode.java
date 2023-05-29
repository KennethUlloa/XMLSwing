package xmlswing.components.menu.item;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;

import javax.swing.*;

public class MenuItemNode extends AbstractNode<JMenuItem> {
    public MenuItemNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public void parseChildNode(JMenuItem parent, Node item) {

    }

    @Override
    public JMenuItem getParentElement() {
        JMenuItem menuItem = new JMenuItem();
        if(hasAttribute("keys")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(getAttribute("keys")));
        }
        menuItem.setText(getNode().getTextContent());
        return menuItem;
    }
}
