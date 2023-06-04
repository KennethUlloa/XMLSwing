package xmlswing.components.input.text;

import org.w3c.dom.Node;
import xmlswing.components.AbstractNode;
import xmlswing.components.AbstractNodeFactory;
import xmlswing.XMLSwing;

import java.awt.*;

/**
 * <h3>Properties</h3>
 * cols: number of columns to display
 */
public class TextFieldNodeFactory extends AbstractNodeFactory {

    @Override
    public String getTagName() {
        return "TextField";
    }

    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context) {
        return new TextFieldNode(node, context);
    }
}
