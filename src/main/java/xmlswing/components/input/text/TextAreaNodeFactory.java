package xmlswing.components.input.text;

import org.w3c.dom.Node;
import xmlswing.components.AbstractNode;
import xmlswing.components.AbstractNodeFactory;
import xmlswing.XMLSwing;

import java.awt.*;

public class TextAreaNodeFactory extends AbstractNodeFactory {

    @Override
    public String getTagName() {
        return "TextArea";
    }

    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context) {
        return new TextAreaNode(node, context);
    }
}
