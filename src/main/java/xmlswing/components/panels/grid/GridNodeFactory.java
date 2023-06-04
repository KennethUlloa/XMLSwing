package xmlswing.components.panels.grid;

import org.w3c.dom.Node;
import xmlswing.components.AbstractNode;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNodeFactory;

import java.awt.*;

/**
 * <h3>Properties</h3>
 * <ul>
 *     <li>rows</li>
 *     <li>columns</li>
 *     <li>h-gap</li>
 *     <li>v-gap</li>
 * </ul>
 */
public class GridNodeFactory extends AbstractNodeFactory {

    @Override
    public String getTagName() {
        return "Grid";
    }

    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> container) {
        return new GridNode(node, container);
    }
}
