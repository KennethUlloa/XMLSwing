package xmlswing.components.panels.grid;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.repositories.NodeFactoryRepository;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.components.props.ElementProperties;

import javax.swing.*;
import java.awt.*;

public class GridBagNode extends AbstractNode<JPanel> {
    public GridBagNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public void parseChildNode(JPanel parent, Node item) {
        TypeNodeFactory<Component, XMLSwing<?>> factory = NodeFactoryRepository.get(item.getNodeName());
        if(factory != null) {
            TypeNode<Component, XMLSwing<?>> buildNode = factory.buildNode(item, getContext());
            if(buildNode != null) {
                parent.add(buildNode.getObject(), getConstraints(buildNode));
            }
        }
    }

    @Override
    public JPanel getRootElement() {
        JPanel panel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        if(hasAttribute("row-weights")) {
            gridBagLayout.rowWeights = calculateWeights(getAttribute("row-weights"));
        }
        if(hasAttribute("column-weights")) {
            gridBagLayout.columnWeights = calculateWeights(getAttribute("column-weights"));
        }
        if(hasAttribute("rows")) {
            gridBagLayout.rowHeights = calculateWidths(getAttribute("rows"));
        }
        if(hasAttribute("columns")) {
            gridBagLayout.columnWidths = calculateWidths(getAttribute("columns"));
        }
        panel.setLayout(gridBagLayout);
        return panel;
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

    private GridBagConstraints getConstraints(TypeNode<Component, XMLSwing<?>> node) {
        GridBagConstraints constraints = new GridBagConstraints();
        if(node.hasAttribute("col")) {
            try {
                constraints.gridx = Integer.parseInt(node.getAttribute("col"));
            } catch (NumberFormatException ignored) {}
        }
        if(node.hasAttribute("row")) {
            try {
                constraints.gridy = Integer.parseInt(node.getAttribute("row"));
            } catch (NumberFormatException ignored) {}
        }
        if(node.hasAttribute("fill")) {
            int op = GridBagConstraints.NONE;
            switch (node.getAttribute("fill").trim()){
                case "both": op = GridBagConstraints.BOTH; break;
                case "horizontal": op = GridBagConstraints.HORIZONTAL; break;
                case "vertical": op = GridBagConstraints.VERTICAL; break;
            }
            constraints.fill = op;
        }
        if(node.hasAttribute("col-span")){
            try {
                constraints.gridwidth = Integer.parseInt(node.getAttribute("col-span"));
            } catch (NumberFormatException ignored) {}
        }
        if(node.hasAttribute("row-span")){
            try {
                constraints.gridheight = Integer.parseInt(node.getAttribute("row-span"));
            } catch (NumberFormatException ignored) {}
        }
        if(node.hasAttribute("anchor")){
            switch (node.getAttribute("anchor")) {
                case "top": constraints.anchor = GridBagConstraints.NORTH; break;
                case "top-left": constraints.anchor = GridBagConstraints.NORTHWEST; break;
                case "left": constraints.anchor = GridBagConstraints.WEST; break;
                case "bottom-left": constraints.anchor = GridBagConstraints.SOUTHWEST; break;
                case "right": constraints.anchor = GridBagConstraints.EAST; break;
                case "top-right": constraints.anchor = GridBagConstraints.NORTHEAST; break;
                case "bottom": constraints.anchor = GridBagConstraints.SOUTH; break;
                case "bottom-right": constraints.anchor = GridBagConstraints.SOUTHEAST; break;
                case "center": constraints.anchor = GridBagConstraints.CENTER;
            }
        }
        if(node.hasAttribute("insets")) {
            constraints.insets = ElementProperties.insetsFromString(node.getAttribute("insets"));
        }
        return constraints;
    }
}
