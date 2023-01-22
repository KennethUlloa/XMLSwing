package mocking.inputs;

import mocking.JComponentMockFactory;
import mocking.MockFactory;
import mocking.MockNode;

import javax.swing.*;

public class SliderFactory extends JComponentMockFactory {
    @Override
    public JComponent buildComponent(MockNode node) {
        return null;
    }

    @Override
    public JComponent buildComponent(MockNode node, MockFactory mockMaker) {
        JSlider slider = new JSlider();
        slider.setPaintLabels(true);
        slider.setPaintTicks(false);

        if(node.hasAttribute("min")) {
            try {
                slider.setMinimum(Integer.parseInt(node.getAttribute("min")));
            } catch (NumberFormatException ignored) {}
        }
        if(node.hasAttribute("max")) {
            try {
                slider.setMaximum(Integer.parseInt(node.getAttribute("max")));
            } catch (NumberFormatException ignored) {}
        }
        if(node.hasAttribute("value")) {
            try {
                slider.setMinimum(Integer.parseInt(node.getAttribute("value")));
            } catch (NumberFormatException ignored) {}
        }
        slider.setLabelTable(slider.createStandardLabels(1));
        return slider;
    }
}
