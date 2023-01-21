package mocking.panels;

import mocking.JComponentMockFactory;
import mocking.MockFactory;
import org.w3c.dom.Node;

import javax.swing.*;

public abstract class PanelMockFactory extends JComponentMockFactory {
    public abstract void addChildren(Node e, JPanel panel);
    public abstract void addChildren(Node e, JPanel panel, MockFactory mockMaker);
}
