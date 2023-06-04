package xmlswing.types;

import org.w3c.dom.Node;

public abstract class TypeNode<T, K> {
    private Node node;
    private K context;
    private T object;

    public TypeNode(Node node, K context) {
        this.node = node;
        this.context = context;
        //loadAttributes();
    }

    public boolean hasAttribute(String attribute) {
        return node.getAttributes().getNamedItem(attribute) != null;
    }

    public String getAttribute(String attribute) {
        if(hasAttribute(attribute)) {
            return node.getAttributes().getNamedItem(attribute).getNodeValue();
        }
        return null;
    }

    public boolean shouldIgnore() {
        return node.getNodeName().equals("#text") || node.getNodeName().equals("#comment");
    }

    public Node getNode() {
        return node;
    }

    public T getObject() {
        if(object == null) {
            object = parseObject();
        }
        return object;
    }
    public abstract T parseObject();

    public static boolean hasAttribute(Node node, String attribute) {
        return node.getAttributes().getNamedItem(attribute) != null;
    }

    public static String getAttribute(Node node, String attribute) {
        if (TypeNode.hasAttribute(node, attribute)) {
            return node.getAttributes().getNamedItem(attribute).getNodeValue();
        }
        return null;
    }

    public static boolean shouldIgnore(Node node) {
        return node.getNodeName().trim().equals("#text") || node.getNodeName().equals("#comment");
    }

    public Node firstTagChild(String tag) {
        for(int i = 0; i < getNode().getChildNodes().getLength() ; i++) {
            Node n = getNode().getChildNodes().item(i);
            if(TypeNode.shouldIgnore(n)) {
                continue;
            }
            if (tag == null) {
                return n;
            } else if (n.getNodeName().equals(tag)) {
                return n;
            }
        }
        return null;
    }

    public Node firstTagChild() {
        return firstTagChild(null);
    }

    public K getContext() {
        return context;
    }
}
