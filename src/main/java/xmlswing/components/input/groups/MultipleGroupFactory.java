package xmlswing.components.input.groups;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.components.AbstractNodeFactory;

import java.awt.*;

public class MultipleGroupFactory extends AbstractNodeFactory {
    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context) {
        return new MultipleGroupNode(node, context);
    }

    @Override
    public String getTagName() {
        return "MultipleGroup";
    }
}
