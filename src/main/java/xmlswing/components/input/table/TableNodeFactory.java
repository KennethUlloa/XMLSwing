package xmlswing.components.input.table;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.components.AbstractNodeFactory;

import java.awt.*;

/**
 * <h3>Properties</h3>
 * It has a similar structure to HTML tables but limited to th, tr, td <br>
 * th (row for headers)<br>
 * td (data)<br>
 * tr (row for data) <br>
 */
public class TableNodeFactory extends AbstractNodeFactory {

    @Override
    public String getTagName() {
        return "Table";
    }

    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context) {
        return new TableNode(node, context);
    }
}
