package types;

import org.w3c.dom.Node;

public abstract class TypeNode<T> {
    private Node node;
    private T object;

    public TypeNode(Node node) {
        this.node = node;
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
        return node.getNodeName().equals("#text") || node.getNodeName().equals("#comment");
    }
}
