package xmlswing.components.input;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.components.ComponentNode;
import xmlswing.components.PropertiesReader;
import xmlswing.types.TypeNodeFactory;

import javax.swing.*;
import java.awt.*;
/**
 * <h3>Properties</h3>
 * min:
 * <ul>
 *     <li>Minimum value for the slider</li>
 * </ul>
 * max:
 * <ul>
 *     <li>Maximum value for the slider</li>
 * </ul>
 * value:
 * <ul>
 *     <li>Current value for the slider</li>
 * </ul>
 */
public class SliderNodeFactory implements TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JSlider slider = new JSlider();
                slider.setPaintLabels(true);
                slider.setPaintTicks(false);
                if(hasAttribute("min")) {
                    try {
                        slider.setMinimum(Integer.parseInt(getAttribute("min")));
                    } catch (NumberFormatException ignored) {}
                }
                if(hasAttribute("max")) {
                    try {
                        slider.setMaximum(Integer.parseInt(getAttribute("max")));
                    } catch (NumberFormatException ignored) {}
                }
                if(hasAttribute("value")) {
                    try {
                        slider.setValue(Integer.parseInt(getAttribute("value")));
                    } catch (NumberFormatException ignored) {}
                }
                slider.setLabelTable(slider.createStandardLabels(1));
                return slider;
            }
        };
        ComponentRepository.registerNode(typeNode, container);
        PropertiesReader.setUpComponent(typeNode);
        return typeNode;
    }

    @Override
    public String getTagName() {
        return "Slider";
    }
}
