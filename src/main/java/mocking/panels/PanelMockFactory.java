package mocking.panels;

import mocking.JComponentMockFactory;
import org.w3c.dom.Node;

import javax.swing.*;

public abstract class PanelMockFactory extends JComponentMockFactory {
    public abstract void addChildren(Node e, JPanel panel);
}
