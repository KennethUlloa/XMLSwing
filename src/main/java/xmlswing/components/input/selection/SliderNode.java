package xmlswing.components.input.selection;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;

import javax.swing.*;

public class SliderNode extends AbstractNode<JSlider> {
    public SliderNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public JSlider getRootElement() {
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
}
