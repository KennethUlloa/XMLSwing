package types;

import org.w3c.dom.Node;

public interface TypeNodeFactory<T> {
    TypeNode<T> buildNode(Node node, TypeContainer<T> container);
}
