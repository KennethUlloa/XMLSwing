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

/**
 * <h3>Properties</h3>
 * selected:
 * <ul>
 *     <li>true</li>
 *     <li>false (or anything else)</li>
 * </ul>
 */
public class CheckBoxNodeFactory implements TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setText(getNode().getTextContent().trim());
                if(hasAttribute("selected")) {
                    checkBox.setSelected(getAttribute("selected").equals("true"));
                }
                return checkBox;
            }
        };
        PropertiesReader.setUpComponent(typeNode);
        ComponentRepository.registerNode(typeNode, container);
        return typeNode;
    }

    @Override
    public String getTagName() {
        return "CheckBox";
    }
}
