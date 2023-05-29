package xmlswing.components;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xmlswing.types.TypeNode;
import xmlswing.XMLSwing;

import java.awt.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class ComponentNode extends TypeNode<Component> {
    private XMLSwing container;
    public ComponentNode(Node node, XMLSwing container) {
        super(node);
        this.container = container;
    }

    public Stream<Node> getStream() {
        NodeList nodeList = getNode().getChildNodes();
        return IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item);
    }

    public XMLSwing getContainer() {
        return container;
    }
}
