package mocking.panels;

import mocking.GridBagConstraintParser;
import mocking.MockNode;
import mocking.MockMaker;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;

public class GridBagMockFactory extends PanelMockFactory {
    @Override
    public JComponent buildComponent(MockNode node) {
        JPanel panel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        //String values = MockMaker.getAttributeValue(node, "row-display");
        if(node.hasAttribute("row-display")) {
            gridBagLayout.rowWeights = calculateWeights(node.getAttribute("row-display"));
        }
        //values = MockMaker.getAttributeValue(node, "column-display");
        if(node.hasAttribute("column-display")) {
            gridBagLayout.columnWeights = calculateWeights(node.getAttribute("column-display"));
        }
        //values = MockMaker.getAttributeValue(node, "rows");
        if(node.hasAttribute("rows")) {
            gridBagLayout.rowHeights = calculateWidths(node.getAttribute("rows"));
        }

        //values = MockMaker.getAttributeValue(node, "columns");
        if(node.hasAttribute("columns")) {
            gridBagLayout.columnWidths = calculateWidths(node.getAttribute("columns"));
        }

        panel.setLayout(gridBagLayout);
        addChildren(node.getNode(), panel);
        return panel;
    }

    @Override
    public void addChildren(Node node, JPanel panel) {
        for(int i = 0; i < node.getChildNodes().getLength() ; i++) {
            MockNode mockNode = new MockNode(node.getChildNodes().item(i));
            if(!mockNode.shouldIgnore()) {
                //MockNode mockNode = new MockNode(e);
                if(mockNode.getComponent() != null) {
                    panel.add(mockNode.getComponent(), GridBagConstraintParser.fromNode(mockNode));
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