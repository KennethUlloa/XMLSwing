package xmlswing.components.input;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.XMLSwing;
import xmlswing.components.PropertiesReader;
import xmlswing.components.ComponentNode;

import javax.swing.*;
import java.awt.*;

/**
 * <h3>Properties</h3>
 * For every child you'll need to use <b>Option</b> tag containing the value to display. Currently it only supports text content
 */
public class ComboBoxNodeFactory implements TypeNodeFactory<Component, XMLSwing> {
    @Override
    public TypeNode<Component> buildNode(Node node, XMLSwing container) {
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
        container.registerNode(typeNode);
        return typeNode;
    }

    @Override
    public String getTagName() {
        return "ComboBox";
    }
}