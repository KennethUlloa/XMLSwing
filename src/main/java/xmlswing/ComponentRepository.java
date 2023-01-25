package xmlswing;

import types.TypeContainer;
import types.TypeNode;
import types.TypeRepository;
import xmlswing.components.ComponentNode;

import java.awt.*;

public class ComponentRepository extends TypeRepository<Component> {
    public static void registerNode(TypeNode<Component> node, TypeContainer<Component> container) {
        if(node != null && container != null) {
            if(node.hasAttribute("id")) {
                container.getRepository().register(node.getAttribute("id"), node.getObject());
            }
        }
    }
}
