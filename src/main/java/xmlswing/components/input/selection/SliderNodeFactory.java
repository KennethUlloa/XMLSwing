package xmlswing.components.input.selection;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.components.AbstractNodeFactory;

import java.awt.*;
/**
 * <h3>Properties</h3>
 * min:
 * <ul>
 *     <li>Minimum value for the slider</li>
 * </ul>
 * max:
 * <ul>
 *     <li>Maximum value for the slider</li>
 * </ul>
 * value:
 * <ul>
 *     <li>Current value for the slider</li>
 * </ul>
 */
public class SliderNodeFactory extends AbstractNodeFactory {

    @Override
    public String getTagName() {
        return "Slider";
    }

    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context) {
        return new SliderNode(node, context);
    }
}
