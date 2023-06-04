package xmlswing.components.label;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;

import javax.swing.*;

public class LabelNode extends AbstractNode<JLabel> {

    public LabelNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public boolean shouldParseChildren() {
        return false;
    }

    @Override
    public JLabel getRootElement() {
        JLabel label = new JLabel();
        label.setText(getNode().getTextContent().trim());
        if(hasAttribute("h-align"))
            switch (getAttribute("h-align")) {
                case "right": label.setHorizontalAlignment(JLabel.RIGHT); break;
                case "center": label.setHorizontalAlignment(JLabel.CENTER); break;
                case "left": label.setHorizontalAlignment(JLabel.LEFT); break;
            }

        if(hasAttribute("v-align"))
            switch (getAttribute("v-align")) {
                case "top": label.setVerticalAlignment(JLabel.TOP); break;
                case "center": label.setVerticalAlignment(JLabel.CENTER); break;
                case "bottom": label.setVerticalAlignment(JLabel.BOTTOM); break;
            }
        return label;
    }
}
