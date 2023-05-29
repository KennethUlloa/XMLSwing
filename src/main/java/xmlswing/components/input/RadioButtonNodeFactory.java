package xmlswing.components.input;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.components.PropertiesReader;
import xmlswing.components.ComponentNode;
import xmlswing.components.CustomButtonGroup;

import javax.swing.*;
import java.awt.*;

/**
 * <h3>Properties</h3>
 * selected:
 * <ul>
 *     <li>true</li>
 *     <li>false (or anything else)</li>
 * </ul>
 * group:
 * <ul>
 *     <li>The name of the corresponding group</li>
 * </ul>
 */
public class RadioButtonNodeFactory implements TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {

                JRadioButton radioButton = new JRadioButton();
                radioButton.setText(getNode().getTextContent().trim());
                if(hasAttribute("selected")) {
                    radioButton.setSelected(getAttribute("selected").equals("true"));
                }
                if(hasAttribute("group")) {
                    CustomButtonGroup group = (CustomButtonGroup) getContainer().getRepository().obtain(getAttribute("group"));
                    if(group == null) {
                        group = new CustomButtonGroup();
                        getContainer().getRepository().register(getAttribute("group"), group);
                    }
                    group.add(radioButton);
                }
                return radioButton;
            }
        };
        PropertiesReader.setUpComponent(typeNode);
        ComponentRepository.registerNode(typeNode, container);
        return typeNode;
    }

    @Override
    public String getTagName() {
        return "RadioButton";
    }
}
