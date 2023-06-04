package xmlswing.components.input.selection;

import org.w3c.dom.Node;
import xmlswing.components.AbstractNode;
import xmlswing.components.AbstractNodeFactory;
import xmlswing.XMLSwing;

import java.awt.*;

/**
 * <h3>Properties</h3>
 * For every child you'll need to use <b>Option</b> tag containing the value to display. Currently it only supports text content
 */
public class ComboBoxNodeFactory extends AbstractNodeFactory {

    @Override
    public String getTagName() {
        return "ComboBox";
    }

    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context) {
        return new ComboBoxNode(node, context);
    }
}