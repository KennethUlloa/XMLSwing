package xmlswing.components.input.selection;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.components.AbstractNodeFactory;

import java.awt.*;

/**
 * <h3>Properties</h3>
 * selected:
 * <ul>
 *     <li>true</li>
 *     <li>false (or anything else)</li>
 * </ul>
 * group:
 * <ul>
 *     <li>The name of the corresponding group</li>
 * </ul>
 */
public class RadioButtonNodeFactory extends AbstractNodeFactory {

    @Override
    public String getTagName() {
        return "RadioButton";
    }

    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context) {
        return new RadioButtonNode(node, context);
    }
}
