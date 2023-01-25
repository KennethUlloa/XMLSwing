package xmlswing.components;

import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import types.TypeRepository;
import xmlswing.XMLSwing;

import java.awt.*;

public abstract class ComponentNode extends TypeNode<Component> {
    private TypeContainer<Component> container;
    public ComponentNode(Node node, TypeContainer<Component> container) {
        super(node);
        this.container = container;
    }

    public TypeContainer<Component> getContainer() {
        return container;
    }
}
