package xmlswing.components.panels;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.components.AbstractNodeFactory;

import java.awt.*;

/**
 * <h3>Properties</h3>
 * <ul>
 *     <li>
 *         h-policy
 *         <ul>
 *             <li>always</li>
 *             <li>never</li>
 *         </ul>
 *     </li>
 *     <li>
 *         v-policy
 *         <ul>
 *             <li>always</li>
 *             <li>never</li>
 *         </ul>
 *     </li>
 * </ul>
 */
public class ScrollPaneNodeFactory extends AbstractNodeFactory {


    @Override
    public String getTagName() {
        return "ScrollPane";
    }

    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context) {
        return new ScrollPaneNode(node, context);
    }
}
