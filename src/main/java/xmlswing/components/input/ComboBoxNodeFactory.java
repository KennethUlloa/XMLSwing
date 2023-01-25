package xmlswing.components.input;

import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import types.TypeNodeFactory;
import xmlswing.ComponentRepository;
import xmlswing.components.PropertiesReader;
import xmlswing.components.ComponentNode;

import javax.swing.*;
import java.awt.*;

public class ComboBoxNodeFactory implements TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JComboBox<Object> comboBox = new JComboBox<>();
                for(int i = 0 ; i < getNode().getChildNodes().getLength() ; i++) {
                    if(getNode().getChildNodes().item(i).getNodeName().equals("Option")) {
                        comboBox.addItem(getNode().getChildNodes().item(i).getTextContent().trim());
                    }
                }
                return comboBox;
            }
        };
        PropertiesReader.setUpComponent(typeNode);
        ComponentRepository.registerNode(typeNode, container);
        return typeNode;
    }
}
