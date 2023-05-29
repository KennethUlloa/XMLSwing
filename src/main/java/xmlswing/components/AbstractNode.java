package xmlswing.components;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.XMLSwing;

import java.awt.*;

public abstract class AbstractNode<T extends Component> extends TypeNode<Component, XMLSwing<?>> {
    public AbstractNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public Component parseObject() {
        T parent = getParentElement();
        for(int i = 0; i < getNode().getChildNodes().getLength(); i++) {
            parseChildNode(parent, getNode().getChildNodes().item(i));
        }
        return parent;
    }

    public abstract void parseChildNode(T parent, Node item);

    public abstract T getParentElement();

}
