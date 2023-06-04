package xmlswing.components.label;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.components.AbstractNodeFactory;

import java.awt.Component;

/**
 * <h3>Properties</h3>
 * h-align: setHorizontalAlignment
 * <ul>
 *     <li>right</li>
 *     <li>center</li>
 *     <li>left</li>
 * </ul>
 * v-align: setVerticalAlignment
 * <ul>
 *     <li>top</li>
 *     <li>center</li>
 *     <li>bottom</li>
 * </ul>
 */
public class LabelNodeFactory extends AbstractNodeFactory {

    @Override
    public String getTagName() {
        return "Label";
    }

    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context) {
        return new LabelNode(node, context);
    }
}
