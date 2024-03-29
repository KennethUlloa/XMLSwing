package xmlswing.components.frames;

import org.w3c.dom.Node;
import xmlswing.components.AbstractNode;
import xmlswing.components.AbstractNodeFactory;
import xmlswing.XMLSwing;

import java.awt.*;

public class FrameFactory extends AbstractNodeFactory {


    @Override
    public String getTagName() {
        return "Frame";
    }


    @Override
    public AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context) {
        return new FrameNode(node, context);
    }
}
