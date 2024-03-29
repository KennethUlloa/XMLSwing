package xmlswing.components.menu;

import org.w3c.dom.Node;
import xmlswing.components.AbstractNode;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNodeFactory;

import java.awt.*;

public class MenuNodeFactory extends AbstractNodeFactory {

    @Override
    public String getTagName() {
        return "Menu";
    }

    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context) {
        return new MenuNode(node, context);
    }
}
