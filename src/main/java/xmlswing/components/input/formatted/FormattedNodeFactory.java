package xmlswing.components.input.formatted;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.components.AbstractNodeFactory;

import java.awt.*;

public class FormattedNodeFactory extends AbstractNodeFactory {
    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context) {
        return new FormattedNode(node, context);
    }

    @Override
    public String getTagName() {
        return "FormatField";
    }
}
