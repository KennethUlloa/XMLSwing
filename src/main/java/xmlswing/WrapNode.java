package xmlswing;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;

public class WrapNode extends TypeNode<Object, Object> {
    public WrapNode(Node node) {
        super(node, null);
    }

    @Override
    public Object parseObject() {
        return null;
    }
}
