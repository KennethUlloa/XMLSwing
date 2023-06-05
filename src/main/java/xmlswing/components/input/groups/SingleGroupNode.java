package xmlswing.components.input.groups;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.components.NodeFormEntry;
import xmlswing.components.form.FormEntry;
import xmlswing.repositories.NodeFactoryRepository;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;

import javax.swing.*;
import java.awt.*;

public class SingleGroupNode extends AbstractNode<SingleGroupPane> {
    public SingleGroupNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public SingleGroupPane getRootElement() {
        SingleGroupPane panel = new SingleGroupPane();
        return panel;
    }

    @Override
    public void parseChildNode(SingleGroupPane parent, Node item) {
        TypeNodeFactory<Component, XMLSwing<?>> factory = NodeFactoryRepository.get(item.getNodeName());
        if (factory == null) {
            return;
        }
        TypeNode<Component, XMLSwing<?>> node = factory.buildNode(item, getContext());
        if(node.getObject() instanceof AbstractButton) {
            parent.getGroup().add((AbstractButton) node.getObject());
        }
        parent.add(node.getObject());
    }

    @Override
    public FormEntry<SingleGroupPane> asFormEntry() {
        return new NodeFormEntry<SingleGroupPane>(this) {
            @Override
            public void clearValue() {
                element.clear();
            }

            @Override
            public Object getRawValue() {
                AbstractButton button = element.getSelected();
                if(button != null) {
                    return button.getText();
                }
                return null;
            }
        };
    }
}
