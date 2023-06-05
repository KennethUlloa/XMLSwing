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
import java.util.stream.Collectors;

public class MultipleGroupNode extends AbstractNode<MultipleGroupPane> {
    public MultipleGroupNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public void parseChildNode(MultipleGroupPane parent, Node item) {
        TypeNodeFactory<Component, XMLSwing<?>> factory = NodeFactoryRepository.get(item.getNodeName());
        if (factory == null) {
            return;
        }
        TypeNode<Component, XMLSwing<?>> node = factory.buildNode(item, getContext());
        if(node.getObject() instanceof AbstractButton) {
            parent.getButtons().add((AbstractButton) node.getObject());
        }
        parent.add(node.getObject());
    }

    @Override
    public MultipleGroupPane getRootElement() {
        MultipleGroupPane pane = new MultipleGroupPane();
        return pane;
    }

    @Override
    public FormEntry<MultipleGroupPane> asFormEntry() {
        return new NodeFormEntry<MultipleGroupPane>(this) {
            @Override
            public void clearValue() {
                element.clear();
            }

            @Override
            public Object getRawValue() {
                return element.getSelected().stream().map(AbstractButton::getText).collect(Collectors.toList());
            }
        };
    }
}
