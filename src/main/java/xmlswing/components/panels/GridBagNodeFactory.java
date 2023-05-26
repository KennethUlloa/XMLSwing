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

/**
 * <h3>Properties</h3>
 * <ul>
 *     <li>row-weights: values separated by space e: ("0 0 1 0 1")</li>
 *     <li>column-weights: values separated by space e: ("0 0 1 0 1")</li>
 *     <li>rows: values separated by space e: ("0 0 1 0 1")</li>
 *     <li>columns: values separated by space e: ("0 0 1 0 1")</li>
 * </ul>
 * A child node may define any or all of the following attributes in order to define its own position
 * <ul>
 *     <li>col: X axis position</li>
 *     <li>row: Y axis position</li>
 *     <li>fill: horizontal, vertical of both</li>
 *     <li>col-span: columns to be taken</li>
 *     <li>row-span: rows to be taken</li>
 *     <li>
 *         anchor: position in its cell
 *         <ul>
 *             <li>top</li>
 *             <li>top-left</li>
 *             <li>top-right</li>
 *             <li>bottom</li>
 *             <li>bottom-left</li>
 *             <li>bottom-right</li>
 *             <li>left</li>
 *             <li>right</li>
 *             <li>center</li>
 *         </ul>
 *     </li>
 *     <li>insets: interior space, e: ("0 10 34 6)</li>
 * </ul>
 *
 */
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

    @Override
    public String getTagName() {
        return "GridBag";
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

    /**
     * <ul>
     *     <li>col: X axis position</li>
     *     <li>row: Y axis position</li>
     *     <li>fill: horizontal, vertical of both</li>
     *     <li>col-span: columns to be taken</li>
     *     <li>row-span: rows to be taken</li>
     *     <li>
     *         anchor: position in its cell
     *         <ul>
     *             <li>top</li>
     *             <li>top-left</li>
     *             <li>top-right</li>
     *             <li>bottom</li>
     *             <li>bottom-left</li>
     *             <li>bottom-right</li>
     *             <li>left</li>
     *             <li>right</li>
     *             <li>center</li>
     *         </ul>
     *     </li>
     *     <li>insets: interior space, e: ("0 10 34 6)</li>
     * </ul>
     */

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
                case "center": constraints.anchor = GridBagConstraints.CENTER;
            }
        }
        if(node.hasAttribute("insets")) {
            constraints.insets = CommonProperties.insetsFromString(node.getAttribute("insets"));
        }
        return constraints;
    }
}
