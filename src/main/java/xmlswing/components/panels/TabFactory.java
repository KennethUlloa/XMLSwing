package xmlswing.components.panels;

import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import types.TypeNodeFactory;

import java.awt.*;

public class TabFactory implements TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        return null;
    }

    @Override
    public String getTagName() {
        return null;
    }
}
