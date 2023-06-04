package xmlswing.components;

import org.w3c.dom.Node;
import xmlswing.components.props.PropertiesReader;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.XMLSwing;

import java.awt.*;

public abstract class AbstractNodeFactory implements TypeNodeFactory<Component, XMLSwing<?>> {
    public abstract AbstractNode<? extends Component> nodeBuild(Node node, XMLSwing<?> context);

    @Override
    public TypeNode<Component, XMLSwing<?>> buildNode(Node node, XMLSwing<?> context) {
        AbstractNode<? extends Component> typeNode = nodeBuild(node, context);
        PropertiesReader.setUpComponent(typeNode);
        if (typeNode.hasAttribute("id")) {
            context.registerElement(typeNode.getAttribute("id"), typeNode.getObject());
        }

        return typeNode;
    }
}
