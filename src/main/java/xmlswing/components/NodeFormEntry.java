package xmlswing.components;

import xmlswing.components.form.FormEntry;

import java.awt.*;

public abstract class NodeFormEntry<T extends Component> extends FormEntry<T> {
    public NodeFormEntry(String name, T value, boolean required) {
        super(name, value, required);
    }

    public NodeFormEntry(AbstractNode<T> node) {
        this(getId(node), (T) node.getObject(), node.hasAttribute("required"));
    }

    private static <T extends Component> String getId(AbstractNode<T> node) {
        if(node.hasAttribute("name")) {
            return node.getAttribute("name");
        }else if (node.hasAttribute("id")){
            return node.getAttribute("id");
        }
        return null;
    }

}
