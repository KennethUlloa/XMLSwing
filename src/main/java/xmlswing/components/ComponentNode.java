package xmlswing.components;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import types.TypeContainer;
import types.TypeNode;
import types.TypeRepository;
import xmlswing.XMLSwing;

import java.awt.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class ComponentNode extends TypeNode<Component> {
    private TypeContainer<Component> container;
    public ComponentNode(Node node, TypeContainer<Component> container) {
        super(node);
        this.container = container;
    }

    public Stream<Node> getStream() {
        NodeList nodeList = getNode().getChildNodes();
        return IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item);
    }

    public TypeContainer<Component> getContainer() {
        return container;
    }
}
