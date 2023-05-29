package xmlswing.components;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.XMLSwing;

import java.awt.*;

public abstract class AbstractNodeFactory implements TypeNodeFactory<Component, XMLSwing<?>> {
    public abstract TypeNode<Component, XMLSwing<?>> nodeBuild(Node node, XMLSwing<?> context);

    @Override
    public TypeNode<Component, XMLSwing<?>> buildNode(Node node, XMLSwing<?> context) {
        TypeNode<Component, XMLSwing<?>> typeNode = nodeBuild(node, context);
        PropertiesReader.setUpComponent(typeNode);
        if (typeNode.hasAttribute("id")) {
            context.registerElement(typeNode.getAttribute("id"), typeNode.getObject());
        }

        return null;
    }
}
