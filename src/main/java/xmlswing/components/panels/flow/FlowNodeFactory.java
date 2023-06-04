package xmlswing.components.panels.flow;

import org.w3c.dom.Node;
import xmlswing.components.AbstractNode;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNodeFactory;

import java.awt.*;

/**
 * <h3>Properties</h3>
 *
 */
public class FlowNodeFactory extends AbstractNodeFactory {

    @Override
    public String getTagName() {
        return "Flow";
    }

    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> container) {
        return new FlowNode(node, container);
    }
}
