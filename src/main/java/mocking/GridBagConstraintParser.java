package mocking;

import org.w3c.dom.Node;

import java.awt.*;

public class GridBagConstraintParser {
    public static GridBagConstraints fromNode(MockNode e) {
        GridBagConstraints constraints = new GridBagConstraints();
        //String value = MockMaker.getAttributeValue(e, "col");
        if(e.hasAttribute("col")) {
            constraints.gridx = Integer.parseInt(e.getAttribute("col"));
        }
        //value = MockMaker.getAttributeValue(e, "row");
        if(e.hasAttribute("row")) {
            constraints.gridy = Integer.parseInt(e.getAttribute("row"));
        }

        //value = MockMaker.getAttributeValue(e, "fill");
        if(e.hasAttribute("fill")) {
            int op = GridBagConstraints.NONE;
            switch (e.getAttribute("fill").trim()){
                case "both": op = GridBagConstraints.BOTH; break;
                case "horizontal": op = GridBagConstraints.HORIZONTAL; break;
                case "vertical": op = GridBagConstraints.VERTICAL; break;
            }
            constraints.fill = op;
        }
        //value = MockMaker.getAttributeValue(e, "insets");
        if(e.hasAttribute("insets")) {
            constraints.insets = CommonProperties.insetsFromString(e.getAttribute("insets"));
        }

        return constraints;
    }
}
