package mocking.inputs;

import mocking.CommonProperties;
import mocking.JComponentMockFactory;
import mocking.MockNode;

import javax.swing.*;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;

public class TextAreaMockFactory extends JComponentMockFactory {
    @Override
    public JComponent buildComponent(MockNode node) {
        JTextArea textArea = new JTextArea();
        textArea.setText(node.getNode().getTextContent());
        TextCommonProperties.setUpComponent(textArea, node);
        /*if(node.hasAttribute("caret")) {
            textArea.setCaretColor(CommonProperties.getColor(node.getAttribute("caret")));
        }

        if(node.hasAttribute("selection")) {
            textArea.setSelectionColor(CommonProperties.getColor(node.getAttribute("selection")));
        }

        if(node.hasAttribute("selection-color")) {
            textArea.setSelectedTextColor(CommonProperties.getColor(node.getAttribute("selection-color")));
        }*/
        return textArea;
    }
}
