package xmlswing.components.input;

import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import xmlswing.ComponentRepository;
import xmlswing.components.CommonProperties;
import xmlswing.components.ComponentNode;
import xmlswing.components.PropertiesReader;

import javax.swing.*;
import java.awt.*;

public class SliderNodeFactory implements types.TypeNodeFactory<Component> {
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
}