package xmlswing.components.menu.bar;

import org.w3c.dom.Node;
import xmlswing.components.AbstractNode;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNodeFactory;

import java.awt.*;

public class MenuBarNodeFactory extends AbstractNodeFactory {
    @Override
    public String getTagName() {
        return "MenuBar";
    }

    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context) {
        return new MenuBarNode(node, context);
    }
}
