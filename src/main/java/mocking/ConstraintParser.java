package mocking;

import org.w3c.dom.Node;

import java.awt.*;

public class ConstraintParser {
    public static GridBagConstraints fromNode(Node e) {
        GridBagConstraints constraints = new GridBagConstraints();
        String value = MockMaker.getAttributeValue(e, "col");
        if(value != null) {
            constraints.gridx = Integer.parseInt(value);
        }
        value = MockMaker.getAttributeValue(e, "row");
        if(value != null) {
            constraints.gridy = Integer.parseInt(value);
        }

        value = MockMaker.getAttributeValue(e, "fill");
        if(value != null) {
            int op = GridBagConstraints.NONE;
            switch (value.trim()){
                case "both": op = GridBagConstraints.BOTH; break;
                case "horizontal": op = GridBagConstraints.HORIZONTAL; break;
                case "vertical": op = GridBagConstraints.VERTICAL; break;
            }
            constraints.fill = op;
        }
        value = MockMaker.getAttributeValue(e, "insets");
        if(value != null) {
            constraints.insets = CommonProperties.insetsFromString(value);
        }

        return constraints;
    }
}
