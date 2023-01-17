package mocking.panels;

import mocking.MockNode;

import java.awt.*;

public class BorderConstraintParser {
    public static String fromNode(MockNode node) {
        if(node.hasAttribute("pos")) {
            switch (node.getAttribute("pos")) {
                case "top": return BorderLayout.NORTH;
                case "left": return BorderLayout.WEST;
                case "right": return BorderLayout.EAST;
                case "bottom": return BorderLayout.SOUTH;
                case "center": return BorderLayout.CENTER;
            }
        }
        return null;
    }
}
