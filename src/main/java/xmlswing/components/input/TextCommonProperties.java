package xmlswing.components.input;

import xmlswing.components.CommonProperties;
import types.TypeNode;

import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * <h3>Properties</h3>
 * selection: color of the selection (hex) <br>
 * caret: color of the caret (hex) <br>
 * selected-text:color of the text inside of the selection <br>
 */
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
        if(node.hasAttribute("selected-text")) {
            textComponent.setSelectedTextColor(CommonProperties.getColor(node.getAttribute("selected-text")));
        }
    }
}
