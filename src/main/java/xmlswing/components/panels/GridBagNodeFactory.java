package xmlswing.components.panels;

import xmlswing.components.CommonProperties;
import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import types.TypeNodeFactory;
import xmlswing.ComponentRepository;
import xmlswing.components.PropertiesReader;
import xmlswing.components.ComponentNode;

import javax.swing.*;
import java.awt.*;

public class GridBagNodeFactory implements TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
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

                for(int i = 0; i < getNode().getChildNodes().getLength() ; i++) {
                    Node child = getNode().getChildNodes().item(i);
                    TypeNodeFactory<Component> factory = getContainer().getFactory(child.getNodeName());
                    if(factory != null) {
                        TypeNode<Component> buildNode = factory.buildNode(child, getContainer());
                        if(buildNode != null) {
                            panel.add(buildNode.getObject(), getConstraints(buildNode));
                        }
                    }
                    //TypeNode<Component> node = container.getFactory()
                }
                return panel;
            }
        };
        PropertiesReader.setUpComponent(typeNode);
        ComponentRepository.registerNode(typeNode, container);
        return typeNode;
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

    private GridBagConstraints getConstraints(TypeNode<Component> node) {
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
            }
        }
        if(node.hasAttribute("insets")) {
            constraints.insets = CommonProperties.insetsFromString(node.getAttribute("insets"));
        }
        return constraints;
    }
}
