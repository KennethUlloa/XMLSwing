package xmlswing.components.input;

import xmlswing.components.CommonProperties;
import types.TypeNode;

import javax.swing.text.JTextComponent;
import java.awt.*;

public class TextCommonProperties {
    public static void setUpComponent(JTextComponent textComponent, TypeNode<Component> node) {
        setCaret(textComponent, node);
        setSelectionColor(textComponent, node);
        setSelectedTextColor(textComponent, node);
    }
    private static void setSelectionColor(JTextComponent textComponent, TypeNode<Component> node) {
        if(node.hasAttribute("selection")) {
            textComponent.setSelectionColor(CommonProperties.getColor(node.getAttribute("selection")));
        }
    }
    private static void setCaret(JTextComponent textComponent, TypeNode<Component> node) {
        if(node.hasAttribute("caret")) {
            textComponent.setCaretColor(CommonProperties.getColor(node.getAttribute("caret")));
        }
    }
    private static void setSelectedTextColor(JTextComponent textComponent, TypeNode<Component> node) {
        if(node.hasAttribute("selection-color")) {
            textComponent.setSelectedTextColor(CommonProperties.getColor(node.getAttribute("selection-color")));
        }
    }
}
