package xmlswing.components.label;

import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import xmlswing.ComponentRepository;
import xmlswing.components.PropertiesReader;
import xmlswing.components.ComponentNode;

import javax.swing.*;
import java.awt.Component;

public class LabelNodeFactory implements types.TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JLabel label = new JLabel();
                setHAlignment(label);
                setVAlignment(label);
                label.setText(getNode().getTextContent().trim());
                return label;
            }
            private void setHAlignment(JLabel label) {
                if(!hasAttribute("h-align")) return;
                switch (getAttribute("h-align")) {
                    case "right": label.setHorizontalAlignment(JLabel.RIGHT); break;
                    case "center": label.setHorizontalAlignment(JLabel.CENTER); break;
                    case "left": label.setHorizontalAlignment(JLabel.LEFT); break;
                }
            }
            private void setVAlignment(JLabel label) {
                if(!hasAttribute("v-align")) return;
                switch (getAttribute("v-align")) {
                    case "top": label.setVerticalAlignment(JLabel.TOP); break;
                    case "center": label.setVerticalAlignment(JLabel.CENTER); break;
                    case "bottom": label.setVerticalAlignment(JLabel.BOTTOM); break;
                }
            }
        };

        PropertiesReader.setUpComponent(typeNode);
        ComponentRepository.registerNode(typeNode, container);
        return typeNode;
    }
}
