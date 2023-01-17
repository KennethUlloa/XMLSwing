package mocking.panels;

import mocking.MockNode;
import mocking.MockMaker;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;

public class GridBagMockFactory extends PanelMockFactory {
    @Override
    public JComponent buildComponent(Node node) {
        JPanel panel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        String values = MockMaker.getAttributeValue(node, "row-display");
        if(values != null) {
            gridBagLayout.rowWeights = calculateWeights(values);
        }
        values = MockMaker.getAttributeValue(node, "column-display");
        if(values != null) {
            gridBagLayout.columnWeights = calculateWeights(values);
        }
        values = MockMaker.getAttributeValue(node, "rows");
        if(values != null) {
            gridBagLayout.rowHeights = calculateWidths(values);
        }

        values = MockMaker.getAttributeValue(node, "columns");
        if(values != null) {
            gridBagLayout.columnWidths = calculateWidths(values);
        }

        panel.setLayout(gridBagLayout);
        addChildren(node, panel);
        return panel;
    }

    @Override
    public void addChildren(Node node, JPanel panel) {
        for(int i = 0; i < node.getChildNodes().getLength() ; i++) {
            Node e = node.getChildNodes().item(i);
            if(!MockNode.shouldIgnore(e)) {
                MockNode mockNode = new MockNode(e);
                if(mockNode.getComponent() != null) {
                    panel.add(mockNode.getComponent(), mockNode.getConstraints());
                }
            }
        }
    }

    private double[] calculateWeights(String input) {
        String[] values = input.split(" ");
        double[] weights = new double[input.length() + 1];
        for(int i = 0 ; i < values.length ; i++) {
            try {
                weights[i] = Double.parseDouble(values[i]);
            } catch (NumberFormatException ignored) {}
        }
        weights[weights.length-1] = Double.MAX_VALUE;
        return weights;
    }

    private int[] calculateWidths(String input) {

        String[] values = input.trim().split(" ");
        int[] widths = new int[input.length()];
        for(int i = 0 ; i < values.length ; i++) {
            try {
                widths[i] = Integer.parseInt(values[i]);
            } catch (NumberFormatException ignored) {}
        }
        return widths;
    }


}
