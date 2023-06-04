package xmlswing.components;

import org.w3c.dom.Node;
import xmlswing.components.form.FormEntry;
import xmlswing.types.TypeNode;
import xmlswing.XMLSwing;

import java.awt.*;

public abstract class AbstractNode<T extends Component> extends TypeNode<Component, XMLSwing<?>> {
    public AbstractNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public Component parseObject() {
        T parent = getRootElement();
        if(shouldParseChildren()) {
            for (int i = 0; i < getNode().getChildNodes().getLength(); i++) {
                parseChildNode(parent, getNode().getChildNodes().item(i));
            }
        }
        return parent;
    }

    public  boolean shouldParseChildren() {
        return true;
    }

    public void parseChildNode(T parent, Node item){};

    public abstract T getRootElement();

    public FormEntry<T> asFormEntry() {
        return null;
    }

}
