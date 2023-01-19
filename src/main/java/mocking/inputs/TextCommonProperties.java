package mocking.inputs;

import mocking.CommonProperties;
import mocking.MockNode;

import javax.swing.text.JTextComponent;

public class TextCommonProperties {
    public static void setUpComponent(JTextComponent textComponent, MockNode node) {
        setCaret(textComponent, node);
        setSelectionColor(textComponent, node);
        setSelectedTextColor(textComponent, node);
    }

    private static void setSelectionColor(JTextComponent textComponent, MockNode node) {
        if(node.hasAttribute("selection")) {
            textComponent.setSelectionColor(CommonProperties.getColor(node.getAttribute("selection")));
        }
    }

    private static void setCaret(JTextComponent textComponent, MockNode node) {
        if(node.hasAttribute("caret")) {
            textComponent.setCaretColor(CommonProperties.getColor(node.getAttribute("caret")));
        }
    }

    private static void setSelectedTextColor(JTextComponent textComponent, MockNode node) {
        if(node.hasAttribute("selection-color")) {
            textComponent.setSelectedTextColor(CommonProperties.getColor(node.getAttribute("selection-color")));
        }
    }
}
