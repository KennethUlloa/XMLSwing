package xmlswing.components.input.selection;

import org.w3c.dom.Node;
import xmlswing.components.AbstractNode;
import xmlswing.components.AbstractNodeFactory;
import xmlswing.XMLSwing;

import java.awt.*;

/**
 * <h3>Properties</h3>
 * selected:
 * <ul>
 *     <li>true</li>
 *     <li>false (or anything else)</li>
 * </ul>
 */
public class CheckBoxNodeFactory extends AbstractNodeFactory {


    @Override
    public String getTagName() {
        return "CheckBox";
    }

    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context) {
        return new CheckBoxNode(node, context);
    }
}
