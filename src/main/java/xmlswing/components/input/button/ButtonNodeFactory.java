package xmlswing.components.input.button;

import org.w3c.dom.Node;
import xmlswing.components.AbstractNode;
import xmlswing.components.AbstractNodeFactory;
import xmlswing.XMLSwing;

import java.awt.*;

public class ButtonNodeFactory extends AbstractNodeFactory {
    @Override
    public String getTagName() {
        return "Button";
    }

    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context) {
        return new ButtonNode(node, context);
    }
}
