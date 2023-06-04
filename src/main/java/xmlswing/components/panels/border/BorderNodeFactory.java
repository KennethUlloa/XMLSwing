package xmlswing.components.panels.border;

import org.w3c.dom.Node;
import xmlswing.components.AbstractNode;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNodeFactory;

import java.awt.*;

/**
 * <h3>Properties</h3>
 * pos: (this is intended to be in the child nodes) <br>
 * <ul>
 *     <li>top</li>
 *     <li>left</li>
 *     <li>right</li>
 *     <li>bottom</li>
 *     <li>center</li>
 * </ul>
 */
public class BorderNodeFactory extends AbstractNodeFactory {


    @Override
    public String getTagName() {
        return "Border";
    }

    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> container) {
        return new BorderNode(node, container);
    }
}
