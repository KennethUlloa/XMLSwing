package xmlswing.types;

import org.w3c.dom.Node;

public interface TypeNodeFactory<T, K> {
    TypeNode<T, K> buildNode(Node node, K context);
    String getTagName();
}
