package mocking;

import org.w3c.dom.Node;

import java.awt.*;

public class GridBagConstraintParser {
    public static GridBagConstraints fromNode(MockNode e) {
        GridBagConstraints constraints = new GridBagConstraints();
        if(e.hasAttribute("col")) {
            try {
                constraints.gridx = Integer.parseInt(e.getAttribute("col"));
            } catch (NumberFormatException ignored) {}
        }
        if(e.hasAttribute("row")) {
            try {
                constraints.gridy = Integer.parseInt(e.getAttribute("row"));
            } catch (NumberFormatException ignored) {}
        }
        if(e.hasAttribute("fill")) {
            int op = GridBagConstraints.NONE;
            switch (e.getAttribute("fill").trim()){
                case "both": op = GridBagConstraints.BOTH; break;
                case "horizontal": op = GridBagConstraints.HORIZONTAL; break;
                case "vertical": op = GridBagConstraints.VERTICAL; break;
            }
            constraints.fill = op;
        }
        if(e.hasAttribute("row-span")){
            try {
                constraints.gridwidth = Integer.parseInt(e.getAttribute("row-span"));
            } catch (NumberFormatException ignored) {}
        }
        if(e.hasAttribute("col-span")){
            try {
                constraints.gridheight = Integer.parseInt(e.getAttribute("col-span"));
            } catch (NumberFormatException ignored) {}
        }
        if(e.hasAttribute("anchor")){
            switch (e.getAttribute("anchor")) {
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
        if(e.hasAttribute("insets")) {
            constraints.insets = CommonProperties.insetsFromString(e.getAttribute("insets"));
        }

        return constraints;
    }
}
